package hiof.gruppe25.core.commands.createreport;

import hiof.gruppe25.core.models.questionresponse.QuestionResponse;
import hiof.gruppe25.core.commands.definitions.iCommand;

import java.util.List;

public class CreateReportCommand implements iCommand {
    public int id;
    public List<QuestionResponse> responses;
}
