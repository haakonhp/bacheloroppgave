package hiof.gruppe25.core.commands.updatecorporation;

import hiof.gruppe25.core.commands.definitions.iCommand;
import hiof.gruppe25.core.commands.definitions.iCommandHandler;
import hiof.gruppe25.core.models.corporation.Corporation;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

public class UpdateCorporationCommandHandler implements iCommandHandler {
    private final iRepository<Corporation> corporationRepository;
    private final iUnitOfWork unitOfWork;

    public UpdateCorporationCommandHandler(iRepository<Corporation> corporationRepository, iUnitOfWork unitOfWork) {
        this.corporationRepository = corporationRepository;
        this.unitOfWork = unitOfWork;
    }
    public void execute(UpdateCorporationCommand command) {
      Corporation corporation = new Corporation(command.id, command.companyName, command.companySector, command.contactNumber, command.contactPerson, command.contactEmail, command.numberOfEmployees);
      corporationRepository.merge(corporation);
      unitOfWork.saveChanges();
      unitOfWork.close();
    }

    @Override
    public void execute(iCommand command) {
        execute((UpdateCorporationCommand) command);
    }
}
