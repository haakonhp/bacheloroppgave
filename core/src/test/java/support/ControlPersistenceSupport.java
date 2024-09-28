package support;

import fakes.FakeControlRepository;
import fakes.FakeUnitOfWork;
import hiof.gruppe25.core.models.control.Control;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

public class ControlPersistenceSupport {
    protected iUnitOfWork uow;
    protected iRepository<Control> repository;

    public ControlPersistenceSupport() {
        setUow(new FakeUnitOfWork());
        repository = new FakeControlRepository();
    }

    public iRepository<Control> getControlRepository() {
        return repository;
    }

    public iUnitOfWork getUnitOfWork() {
        return uow;
    }

    public void setUow(iUnitOfWork uow) {
        this.uow = uow;
    }

    public void cleanup() throws Exception {
        uow.close();
    }
}
