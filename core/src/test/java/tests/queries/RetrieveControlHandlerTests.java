package tests.queries;

import hiof.gruppe25.core.models.control.Control;
import hiof.gruppe25.core.models.control.ControlBuilder;
import hiof.gruppe25.core.queries.retrievecontrol.RetrieveControlQuery;
import hiof.gruppe25.core.queries.retrievecontrol.RetrieveControlQueryHandler;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import support.ControlPersistenceSupport;

import java.util.List;

public class RetrieveControlHandlerTests {
    protected ControlPersistenceSupport support = new ControlPersistenceSupport();
    @Test
    public void handler_should_return_all_when_called_with_empty_query() {
        RetrieveControlQuery query = new RetrieveControlQuery();
        RetrieveControlQueryHandler handler = new RetrieveControlQueryHandler(
                support.getControlRepository(),
                support.getUnitOfWork()
        );

        support.getControlRepository().add(new ControlBuilder().setControlNumber(1).setControlID("2.1.3").build());
        support.getControlRepository().add(new ControlBuilder().setControlNumber(2).setControlID("2.1.5").build());


        List<Control> result = handler.execute(query);
        Approvals.verify(result);
    }
}
