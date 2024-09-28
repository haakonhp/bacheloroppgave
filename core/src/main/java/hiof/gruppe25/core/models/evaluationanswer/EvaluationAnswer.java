package hiof.gruppe25.core.models.evaluationanswer;

import hiof.gruppe25.core.models.control.Control;

import java.time.LocalDateTime;
import java.util.UUID;

public class EvaluationAnswer {
    private UUID id = UUID.randomUUID();
    private Control control;
    private LocalDateTime lastUpdatedAt;
    private int value;
    private int desiredValue;

    public EvaluationAnswer(Control control, LocalDateTime lastUpdatedAt, int value, int desiredValue) {
        this.control = control;
        this.lastUpdatedAt = lastUpdatedAt;
        this.value = value;
        this.desiredValue = desiredValue;
    }

    public EvaluationAnswer(UUID id, Control control, LocalDateTime lastUpdatedAt, int value, int desiredValue) {
        this.id = id;
        this.control = control;
        this.lastUpdatedAt = lastUpdatedAt;
        this.value = value;
        this.desiredValue = desiredValue;
    }

    public EvaluationAnswer(UUID id, LocalDateTime lastUpdatedAt, int value) {
        this.id = id;
        this.lastUpdatedAt = lastUpdatedAt;
        this.value = value;
    }

    public EvaluationAnswer() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDesiredValue() {
        return desiredValue;
    }

    public void setDesiredValue(int desiredValue) {
        this.desiredValue = desiredValue;
    }
}
