package parsers;

import dtos.ControlDTO;
import singletons.HeaderSet;
import singletons.SourceSet;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import hiof.gruppe25.core.models.control.Control;
import hiof.gruppe25.core.models.framework.Framework;
import hiof.gruppe25.core.models.results.ControlResult;
import hiof.gruppe25.core.models.source.Source;
import hiof.gruppe25.core.persistenceinterfaces.iDocumentHandler;
import utilities.ArrayToStream;
import utilities.DTOConverter;
import utilities.EncodingChecker;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DocumentHybridParser implements iDocumentHandler {
    private final EncodingChecker encodingChecker;

    public DocumentHybridParser() {
        encodingChecker = new EncodingChecker();
    }

    public DocumentHybridParser(EncodingChecker encodingChecker) {
        this.encodingChecker = encodingChecker;
    }

    private <T> List<T> parse(Reader reader, Class<T> type) throws IllegalStateException {
        CsvToBeanBuilder<T> beanBuilder = new CsvToBeanBuilder<>(new CSVReader(reader));
        CsvToBean<T> csvToBean = beanBuilder.withType(type).build();

        return new ArrayList<>(csvToBean.parse());
    }

    private <T> List<T> parse(List<String[]> objects, Class<T> type) {
        return parse(new InputStreamReader(ArrayToStream.toCSVStream(objects)), type);
    }

    private List<ControlDTO> parseEstimateCharset(Path path) {
        try {
            return parse(Files.newBufferedReader(path, encodingChecker.findValidCharset(path)), ControlDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Read was determined possible, but parsing failed." + e);
        }
    }

    public ControlResult parseControlsCSV(Path path) {
        HeaderSet.initialize();

        List<Control> controls = DTOConverter.convertList(parseEstimateCharset(path), Control.class);
        List<Framework> headers = HeaderSet.getFrameworks();
        List<Source> sources = SourceSet.getSources();

        return new ControlResult(controls, headers, sources);
    }

    public ControlResult parseControlsArrayXLSX(Path path) {
        XLSXToCSVParser converter = new XLSXToCSVParser();
        try {
            List<String[]> stream = converter.convertxlstoCSV(path.toString());
            HeaderSet.initialize();

            List<Control> controls = DTOConverter.convertList(parse(stream, ControlDTO.class), Control.class);
            List<Framework> headers = HeaderSet.getFrameworks();
            List<Source> sources = SourceSet.getSources();

            return new ControlResult(controls, headers, sources);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}