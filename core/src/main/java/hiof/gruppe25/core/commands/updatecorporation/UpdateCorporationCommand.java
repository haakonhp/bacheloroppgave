package hiof.gruppe25.core.commands.updatecorporation;

import hiof.gruppe25.core.commands.definitions.iCommand;

import java.util.UUID;

public class UpdateCorporationCommand implements iCommand {
 public UUID id;
 public String companyName;
 public String companySector;
 public String contactPerson;
 public String contactEmail;
 public int numberOfEmployees;
 public int contactNumber;
}
