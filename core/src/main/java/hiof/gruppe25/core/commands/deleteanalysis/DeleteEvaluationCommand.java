package hiof.gruppe25.core.commands.deleteanalysis;

import hiof.gruppe25.core.commands.definitions.iCommand;

import java.util.UUID;

public class DeleteEvaluationCommand implements iCommand {
 public DeleteEvaluationCommand(UUID deleteID) {
  this.deleteID = deleteID;
 }

 public UUID deleteID;
}
