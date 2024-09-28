package tests.utilities;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import utilities.EncodingChecker;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Objects;

public class CSVEncodingValidatorTests {
    @Test
    public void validator_returns_iso_when_file_is_iso_and_assumed_iso() {
        ClassLoader loader = this.getClass().getClassLoader();

        String config = Objects.requireNonNull(loader.getResource("configs/charset_expect_ISO.config")).getPath();

        EncodingChecker encodingChecker = new EncodingChecker(config);

        Path test_case = Path.of(loader.getResource("test_cases/test_case_iso.csv").getPath());

        Charset charset = encodingChecker.findValidCharset(test_case);
        Approvals.verify(charset);
    }
    @Test
    public void validator_returns_iso_when_file_is_iso_and_assumed_xroman() {
        ClassLoader loader = this.getClass().getClassLoader();

        String config = Objects.requireNonNull(loader.getResource("configs/charset_expect_xroman.config")).getPath();

        EncodingChecker encodingChecker = new EncodingChecker(config);

        Path test_case = Path.of(loader.getResource("test_cases/test_case_iso.csv").getPath());

        Charset charset = encodingChecker.findValidCharset(test_case);
        Approvals.verify(charset);
    }

    @Test
    public void validator_returns_xroman_when_file_is_xroman_and_assumed_xroman() {
        ClassLoader loader = this.getClass().getClassLoader();

        String config = Objects.requireNonNull(loader.getResource("configs/charset_expect_xroman.config")).getPath();

        EncodingChecker encodingChecker = new EncodingChecker(config);

        Path test_case = Path.of(loader.getResource("test_cases/test_case_xroman.csv").getPath());

        Charset charset = encodingChecker.findValidCharset(test_case);
        Approvals.verify(charset);
    }

    @Test
    public void validator_returns_xroman_when_file_is_xroman_but_assumed_iso() {
        ClassLoader loader = this.getClass().getClassLoader();

        String config = Objects.requireNonNull(loader.getResource("configs/charset_expect_ISO.config")).getPath();

        EncodingChecker encodingChecker = new EncodingChecker(config);

        Path test_case = Path.of(loader.getResource("test_cases/test_case_xroman.csv").getPath());

        Charset charset = encodingChecker.findValidCharset(test_case);
        Approvals.verify(charset);
    }

}
