package parsers;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class XLSXToCSVParser {
    public static final String BREAK_POINT = "|$|$|";
    private final int offsetBy = 2;

    public List<String[]> convertxlstoCSV(String path) throws IOException {
        try (Workbook wb = WorkbookFactory.create(new File(path))) {
            return csvConverter(wb.getSheetAt(0));
        }
    }


    private Triple<Integer, Integer, String[]> handleHeaders(Sheet sheet) {
        Row row;
        int breakPointObserved = 0;

        ArrayList<String> headers = new ArrayList<>();
        row = sheet.getRow(offsetBy);

        for (int i = 0;;i++) {
            if (row.getCell(i) == null || row.getCell(i).getCellType() == CellType.BLANK) {
                return new ImmutableTriple<>(breakPointObserved, i, headers.toArray(new String[0]));
            }
            if (breakPointObserved == 0 && row.getCell(i).getRichStringCellValue().toString().contains(BREAK_POINT)) {
                breakPointObserved = i;
                headers.add("Rammeverk");
            }
            if (breakPointObserved == 0) {
                headers.add(handleCell(row.getCell(i)));
            }
        }
    }


    private List<String[]> csvConverter(Sheet sheet) {
        Row row;
        List<String[]> parsedBody = new ArrayList<>();

        Triple<Integer, Integer, String[]> headerResult = handleHeaders(sheet);

        for (int i = offsetBy + 1; i < sheet.getLastRowNum() + 1; i++) {
            int writeIndex = i - offsetBy - 1;
            row = sheet.getRow(i);

            if (row == null) {
                continue;
            }
            if (Objects.equals(String.valueOf(row.getCell(0).getRichStringCellValue()), "")) {
                break;
            }

            parsedBody.add(writeIndex, new String[headerResult.getLeft() + 1]);
            parsedBody.get(writeIndex)[headerResult.getLeft()] = "";

            for (int j = 0; j < headerResult.getMiddle(); j++) {
                if (row.getCell(j) != null && row.getCell(j).getCellType() != CellType.BLANK) {
                    if (j > headerResult.getLeft()) {
                        parsedBody.get(writeIndex)[headerResult.getLeft()] += handleCell(sheet.getRow(offsetBy).getCell(j)).replace("\n", "") + ";" + handleCell(row.getCell(j)) + "|";
                    } else {
                        parsedBody.get(writeIndex)[j] = handleCell(row.getCell(j));
                    }
                }
            }

            if (parsedBody.get(writeIndex)[headerResult.getLeft()].endsWith("|")) {
                String parsedValue = parsedBody.get(writeIndex)[headerResult.getLeft()];
                parsedBody.get(writeIndex)[headerResult.getLeft()] = parsedValue.substring(0, parsedValue.length() - 1);
            }
        }

        parsedBody.add(0, headerResult.getRight());

        return parsedBody;
    }

    private String handleCell(Cell cell) {
        return switch (cell.getCellType()) {
            case BLANK -> null;
            case NUMERIC -> String.valueOf((int)cell.getNumericCellValue());
            case STRING -> cell.getRichStringCellValue().getString();
            case FORMULA -> handleFormula(cell);
            default -> cell.toString();
        };
    }

    private String handleFormula(Cell cell) {
        return switch (cell.getCachedFormulaResultType()) {
            case NUMERIC -> String.valueOf((int)cell.getNumericCellValue());
            case STRING -> cell.getRichStringCellValue().getString();
            default -> null;
        };
    }
}
