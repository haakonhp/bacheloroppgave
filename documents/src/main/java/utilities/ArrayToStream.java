package utilities;

import com.opencsv.CSVWriter;

import java.io.*;
import java.util.List;

public class ArrayToStream {
    public static InputStream toCSVStream(List<String[]> values) {
        try {
            PipedInputStream input = new PipedInputStream();
            PipedOutputStream output = new PipedOutputStream(input);

            try (input) {
                new Thread(() -> {
                    try (output) {
                        CSVWriter writer = new CSVWriter(new OutputStreamWriter(output));
                        writer.writeAll(values);
                        writer.close();
                    } catch (IOException iox) {
                        throw new RuntimeException();
                    }
                }).start();

                return new ByteArrayInputStream(input.readAllBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
