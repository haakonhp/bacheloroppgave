package hiof.gruppe25.core.commands.createcorporation;

import hiof.gruppe25.core.commands.definitions.iCommand;
import hiof.gruppe25.core.commands.definitions.iCommandHandler;
import hiof.gruppe25.core.models.corporation.Corporation;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

public class CreateCorporationCommandHandler implements iCommandHandler {
    private final iRepository<Corporation> controlRepository;
    private final iUnitOfWork unitOfWork;

    public CreateCorporationCommandHandler(iRepository<Corporation> corporationRepository, iUnitOfWork unitOfWork) {
        this.controlRepository = corporationRepository;
        this.unitOfWork = unitOfWork;
    }
    public void execute(CreateCorporationCommand command) {
        Corporation corporation = new Corporation(
                command.companyName,
                command.companySector,
                command.contactPerson,
                command.contactEmail,
                command.contactNumber,
                command.numberOfEmployees
        );
        controlRepository.add(corporation);
        unitOfWork.saveChanges();
        unitOfWork.close();
    }

    @Override
    public void execute(iCommand command) {
        execute((CreateCorporationCommand) command);
    }
}
