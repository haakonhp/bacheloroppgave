import parsers.DocumentHybridParser;

import hiof.gruppe25.core.models.results.ControlResult;

import hiof.gruppe25.persistence.repositories.ControlRepository;
import hiof.gruppe25.persistence.repositories.FrameworkRepository;
import hiof.gruppe25.persistence.repositories.SourceRepository;
import hiof.gruppe25.persistence.utilities.JPAUnitOfWork;

import java.nio.file.Path;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        DocumentHybridParser parser = new DocumentHybridParser();

        ControlResult results = parser.parseControlsArrayXLSX(Path.of(Objects.requireNonNull(Main.class.getClassLoader()
                .getResource("csv/MAL_Master_Types.xlsx")).getPath()));

        JPAUnitOfWork unitOfWork = new JPAUnitOfWork();
        ControlRepository controlRepository = new ControlRepository(unitOfWork);
        FrameworkRepository frameworkRepository = new FrameworkRepository(unitOfWork);
        frameworkRepository.mergeAll(results.getFrameworks());
        SourceRepository sourceRepository = new SourceRepository(unitOfWork);

        sourceRepository.mergeAll(results.getSources());
        controlRepository.mergeAll(results.getControls());

        unitOfWork.saveChanges();
    }
}

