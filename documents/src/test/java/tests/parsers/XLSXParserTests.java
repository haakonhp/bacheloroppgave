package tests.parsers;

import parsers.DocumentHybridParser;
import parsers.XLSXToCSVParser;
import hiof.gruppe25.core.models.control.Control;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.fail;

public class XLSXParserTests {
    @Test
    public void xlsx_parser_should_return_proper_list_of_arrays() {
        ClassLoader loader = this.getClass().getClassLoader();
        Path test_case = Path.of(loader.getResource("test_cases/xlsx/ExcelMaster.xlsx").getPath());
        XLSXToCSVParser parser = new XLSXToCSVParser();

        try {
            List<String[]> elements = parser.convertxlstoCSV(test_case.toUri().getPath());
            String result = elements.stream().map(Arrays::toString).collect(Collectors.joining());
            Approvals.verify(result);
        } catch (IOException e) {
            fail("Failed to load test case");
        }
    }

    @Test
    public void xlsx_parser_should_return_correct_values_when_used_via_csv_interpreter() {
        ClassLoader loader = this.getClass().getClassLoader();
        Path test_case = Path.of(loader.getResource("test_cases/xlsx/ExcelMaster.xlsx").getPath());

        DocumentHybridParser csv_parser = new DocumentHybridParser();

        List<Control> result = csv_parser.parseControlsArrayXLSX(Path.of(test_case.toUri())).getControls();
        Approvals.verify(result);
    }
}
