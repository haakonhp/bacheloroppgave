package hiof.gruppe25.core.queries.retrieveevaluation;

import hiof.gruppe25.core.models.evaluation.Evaluation;
import hiof.gruppe25.core.models.evaluationanswer.EvaluationAnswer;
import hiof.gruppe25.core.queries.definitions.iQueryHandler;
import hiof.gruppe25.core.persistenceinterfaces.iEvaluationRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

import java.util.List;

public class RetrieveEvaluationQueryHandler implements iQueryHandler<Evaluation, RetrieveEvaluationQuery> {
    private final iEvaluationRepository evaluationRepository;
    private final iUnitOfWork unitOfWork;

    public RetrieveEvaluationQueryHandler(iEvaluationRepository evaluationRepository, iUnitOfWork unitOfWork) {
        this.evaluationRepository = evaluationRepository;
        this.unitOfWork = unitOfWork;
    }

    @Override
    public Evaluation execute(RetrieveEvaluationQuery command) {
        Evaluation evaluation = evaluationRepository.getById(command.id);
        List<EvaluationAnswer> sortedEvals = evaluation.getAnswers();
        sortedEvals.sort(new ControlVersionComparator());
        unitOfWork.close();
        return evaluation;
    }

}
