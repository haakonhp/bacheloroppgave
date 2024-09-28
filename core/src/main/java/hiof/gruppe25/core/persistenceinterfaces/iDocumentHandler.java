package hiof.gruppe25.core.persistenceinterfaces;

import hiof.gruppe25.core.models.results.ControlResult;

import java.nio.file.Path;

public interface iDocumentHandler {
     ControlResult parseControlsCSV(Path path);
     ControlResult parseControlsArrayXLSX(Path path);
}
