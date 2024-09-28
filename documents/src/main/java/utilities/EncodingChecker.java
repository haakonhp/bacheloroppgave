package utilities;


import hiof.gruppe25.persistence.configuration.ConfigLoader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Path;
import java.util.*;

public class EncodingChecker {
    private final int maxThreshold;
    private final int charsToRead;
    private final String suspiciousCharacters;
    private final List<Charset> expectedCharsets = new ArrayList<>();

    public EncodingChecker() {
        this(Objects.requireNonNull(EncodingChecker.class.getClassLoader()
                .getResource("configs/charset_estimate.config")).getPath());
    }

    public EncodingChecker(String resourcePath) {
        String maxThresholdDefault = "5";
        String charsToReadDefault = "1024";
        String suspiciousCharactersDefault = "É¯Â";

        Properties properties = ConfigLoader.loadProperties(resourcePath);

        if(properties == null) {
            maxThreshold = Integer.parseInt(maxThresholdDefault);
            charsToRead = Integer.parseInt(charsToReadDefault);
            suspiciousCharacters = suspiciousCharactersDefault;
            return;
        }

        maxThreshold = Integer.parseInt(properties.getProperty("estimate.max_errors", maxThresholdDefault));
        charsToRead = Integer.parseInt(properties.getProperty("estimate.read_chars", charsToReadDefault));
        suspiciousCharacters = properties.getProperty("estimate.suspicious_characters", suspiciousCharactersDefault);

        String[] expectedCharsetsStrings = properties.getProperty("estimate.expected_charsets", "UTF_8,x-MacRoman,ISO_8859_1")
                .split(",");

        Arrays.stream(expectedCharsetsStrings).forEach((charset) -> expectedCharsets.add(Charset.forName(charset)));
    }

    public Charset findValidCharset(Path path) {
        for (Charset charset : expectedCharsets) {
            if (validateCharset(path, charset)) {
                return charset;
            }
        }
        return null;
    }
    public Boolean validateCharset(Path path, Charset charset) {
        try {
            byte[] bytes = new byte[charsToRead];

            try(InputStream stream = new FileInputStream(path.toString())) {
                stream.read(bytes, 0, charsToRead);
            }

            CharsetDecoder charsetDecoder = Charset.forName("Force fallback", charset).newDecoder();
            CharBuffer chars = charsetDecoder.decode(ByteBuffer.wrap(bytes));

            List<Integer> identifiedCharacters = Arrays.stream(suspiciousCharacters.chars().toArray())
                    .boxed()
                    .toList();

            return evaluateStrangeChars(chars, identifiedCharacters);
        } catch (Exception e) {
            return false;
        }
    }
    private Boolean evaluateStrangeChars(CharBuffer buffer, List<Integer> suspiciousChars) {
        long countOfElements = buffer.chars().parallel().filter(suspiciousChars::contains).count();
        return countOfElements < maxThreshold;
    }
}
