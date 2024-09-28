package hiof.gruppe25.core.commands.deletecorporation;

import hiof.gruppe25.core.commands.definitions.iCommand;

import java.util.UUID;

public class DeleteCorporationCommand implements iCommand {
 public DeleteCorporationCommand(UUID deleteID) {
  this.deleteID = deleteID;
 }

 public UUID deleteID;
}
