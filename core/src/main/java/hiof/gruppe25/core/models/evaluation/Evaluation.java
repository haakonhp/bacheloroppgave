package hiof.gruppe25.core.models.evaluation;

import hiof.gruppe25.core.models.evaluationanswer.EvaluationAnswer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Evaluation {
    private UUID id = UUID.randomUUID();
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID corporation;
    private int createdByTemplate;
    private List<EvaluationAnswer> answers;
    private int answerCount;
    private int completedAnswerCount;

    public Evaluation() {
    }

    public Evaluation(String name, LocalDateTime createdAt, UUID corporation, int createdByTemplate, List<String> sources, List<String> frameworks, List<EvaluationAnswer> answers, int answerCount) {
        this.name = name;
        this.createdAt = createdAt;
        this.corporation = corporation;
        this.createdByTemplate = createdByTemplate;
        this.answers = answers;
        this.answerCount = answerCount;
    }

    public Evaluation(UUID id, String name, LocalDateTime createdAt, UUID corporation, int createdByTemplate, List<EvaluationAnswer> answers) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.corporation = corporation;
        this.createdByTemplate = createdByTemplate;
        this.answers = answers;
    }

    public Evaluation(UUID id, String name, LocalDateTime createdAt, UUID corporation, int createdByTemplate, int answerCount, LocalDateTime updatedAt, int completedAnswerCount) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.corporation = corporation;
        this.createdByTemplate = createdByTemplate;
        this.answerCount = answerCount;
        this.updatedAt = updatedAt;
        this.completedAnswerCount = completedAnswerCount;
    }

    public Evaluation(UUID id, String name, LocalDateTime createdAt, LocalDateTime updatedAt, UUID corporation, int createdByTemplate, List<EvaluationAnswer> answers, int answerCount, int completedAnswerCount) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.corporation = corporation;
        this.createdByTemplate = createdByTemplate;
        this.answers = answers;
        this.answerCount = answerCount;
        this.completedAnswerCount = completedAnswerCount;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getCorporation() {
        return corporation;
    }

    public void setCorporation(UUID corporation) {
        this.corporation = corporation;
    }

    public int getCreatedByTemplate() {
        return createdByTemplate;
    }

    public void setCreatedByTemplate(int createdByTemplate) {
        this.createdByTemplate = createdByTemplate;
    }

    public List<EvaluationAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<EvaluationAnswer> answers) {
        this.answers = answers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getCompletedAnswerCount() {
        return completedAnswerCount;
    }

    public void setCompletedAnswerCount(int completedAnswerCount) {
        this.completedAnswerCount = completedAnswerCount;
    }

}
