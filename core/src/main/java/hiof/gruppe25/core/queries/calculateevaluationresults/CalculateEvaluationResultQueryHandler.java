package hiof.gruppe25.core.queries.calculateevaluationresults;

import hiof.gruppe25.core.models.evaluation.Evaluation;
import hiof.gruppe25.core.models.evaluationresult.EvaluationResult;
import hiof.gruppe25.core.models.evaluationresultcategory.EvaluationResultCategory;
import hiof.gruppe25.core.queries.definitions.iQueryHandler;
import hiof.gruppe25.core.persistenceinterfaces.iEvaluationRepository;
import hiof.gruppe25.core.utilities.iUnitOfWork;

public class CalculateEvaluationResultQueryHandler implements iQueryHandler<EvaluationResult, CalculateEvaluationResultQuery>  {
    private final iEvaluationRepository evaluationRepository;
    private final iUnitOfWork unitOfWork;

    public CalculateEvaluationResultQueryHandler(iEvaluationRepository evaluationRepository, iUnitOfWork unitOfWork) {
        this.evaluationRepository = evaluationRepository;
        this.unitOfWork = unitOfWork;
    }
    @Override
    public EvaluationResult execute(CalculateEvaluationResultQuery command) {
       Evaluation evaluation = evaluationRepository.getById(command.id);
       EvaluationResult result = new EvaluationResult(command.mostPressingMaxSize);
       result.setEvaluationId(evaluation.getId());

       evaluation.getAnswers().forEach((evaluationAnswer -> {
          EvaluationResultCategory category = result.accessCategory(evaluationAnswer.getControl().getCyberDefenseFunction());
          category.updateCategoryScore(evaluationAnswer);
          category.insertMostPressing(evaluationAnswer);
       }));

       unitOfWork.close();
       return result;
    }

}
