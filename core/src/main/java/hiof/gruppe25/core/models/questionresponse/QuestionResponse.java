package hiof.gruppe25.core.models.questionresponse;

public class QuestionResponse {
    int id;
    int reportedValue;
    int referencedControl;

    public QuestionResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReportedValue() {
        return reportedValue;
    }

    public void setReportedValue(int reportedValue) {
        this.reportedValue = reportedValue;
    }

    public int getReferencedControl() {
        return referencedControl;
    }

    public void setReferencedControl(int referencedControl) {
        this.referencedControl = referencedControl;
    }
}
