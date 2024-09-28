package tests.parsers;

import parsers.DocumentHybridParser;
import hiof.gruppe25.core.models.control.Control;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import utilities.EncodingChecker;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class CSVParserTests {
    @Test
    public void parser_returns_error_free_file_when_file_is_iso_and_assumed_iso() {
        ClassLoader loader = this.getClass().getClassLoader();

        String config = Objects.requireNonNull(loader.getResource("configs/charset_expect_iso.config")).getPath();
        EncodingChecker encodingChecker = new EncodingChecker(config);

        Path test_case = Path.of(loader.getResource("test_cases/test_case_ISO.csv").getPath());
        DocumentHybridParser parser = new DocumentHybridParser(encodingChecker);

        List<Control> results = parser.parseControlsCSV(test_case).getControls();

        Approvals.verify(results);
    }

    @Test
    public void parser_returns_error_free_file_when_file_is_iso_and_assumed_xroman() {
        ClassLoader loader = this.getClass().getClassLoader();

        String config = Objects.requireNonNull(loader.getResource("configs/charset_expect_xroman.config")).getPath();
        EncodingChecker encodingChecker = new EncodingChecker(config);

        Path test_case = Path.of(loader.getResource("test_cases/test_case_ISO.csv").getPath());
        DocumentHybridParser parser = new DocumentHybridParser(encodingChecker);

        List<Control> results = parser.parseControlsCSV(test_case).getControls();

        Approvals.verify(results);
    }


    @Test
    public void parser_returns_error_free_file_when_file_is_xroman_and_assumed_iso() {
        ClassLoader loader = this.getClass().getClassLoader();

        String config = Objects.requireNonNull(loader.getResource("configs/charset_expect_iso.config")).getPath();
        EncodingChecker encodingChecker = new EncodingChecker(config);

        Path test_case = Path.of(loader.getResource("test_cases/test_case_xroman.csv").getPath());
        DocumentHybridParser parser = new DocumentHybridParser(encodingChecker);

        List<Control> results = parser.parseControlsCSV(test_case).getControls();

        Approvals.verify(results);
    }


    @Test
    public void parser_returns_error_free_file_when_file_is_xroman_and_assumed_xroman() {
        ClassLoader loader = this.getClass().getClassLoader();

        String config = Objects.requireNonNull(loader.getResource("configs/charset_expect_xroman.config")).getPath();
        EncodingChecker encodingChecker = new EncodingChecker(config);

        Path test_case = Path.of(loader.getResource("test_cases/test_case_xroman.csv").getPath());
        DocumentHybridParser parser = new DocumentHybridParser(encodingChecker);

        List<Control> results = parser.parseControlsCSV(test_case).getControls();

        Approvals.verify(results);
    }
}
