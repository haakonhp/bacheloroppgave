package hiof.gruppe25.core.models.control;

import hiof.gruppe25.core.models.frameworkreference.FrameworkReference;

import java.util.List;

public class Control {
    private int controlNumber;
    private String details;
    private String controlID;
    private String controlOverview;
    private String controlQuestion;
    private int priority;
    private String source;
    private String maturityLevel0;
    private String maturityLevel1;
    private String maturityLevel2;
    private String maturityLevel3;
    private String maturityLevel4;
    private String maturityLevel5;
    private String cyberDefenseFunction;

    private String cyberDefenseFunctionColor;

    private String cyberDefenseAssetColor;
    private List<String> cyberDefenseAsset;

    private List<String> controlTypes;
    private List<FrameworkReference> implementingFrameworks;



    public Control(ControlBuilder builder) {
        this.controlNumber = builder.controlNumber;
        this.details = builder.details;
        this.controlID = builder.controlID;
        this.controlOverview = builder.controlOverview;
        this.controlQuestion = builder.controlQuestion;
        this.priority = builder.priority;
        this.source = builder.source;
        this.maturityLevel0 = builder.maturityLevel0;
        this.maturityLevel1 = builder.maturityLevel1;
        this.maturityLevel2 = builder.maturityLevel2;
        this.maturityLevel3 = builder.maturityLevel3;
        this.maturityLevel4 = builder.maturityLevel4;
        this.maturityLevel5 = builder.maturityLevel5;
        this.cyberDefenseFunction = builder.cyberDefenseFunction;
        this.cyberDefenseAsset = builder.cyberDefenseAsset;
        this.cyberDefenseAssetColor = builder.cyberDefenseAssetColor;
        this.cyberDefenseFunctionColor = builder.cyberDefenseFunctionColor;
        this.implementingFrameworks = builder.implementingFrameworks;
        this.controlTypes = builder.controlTypes;
    }

    public Control() {
    }


    // Protected setters are useful for JPA implementations to build instances of objects. SetVisible calls allows these to be run with
    // Lesser visibility, which allows builders to be used as default outside of those contexts. This does however make some assumptions
    // about the "outer layers" of the model, which is unfortunate, but creates no dependencies otherwise.
    protected void setControlNumber(int controlNumber) {
        this.controlNumber = controlNumber;
    }

    protected void setDetails(String details) {
        this.details = details;
    }

    protected void setControlOverview(String controlOverview) {
        this.controlOverview = controlOverview;
    }

    protected void setControlQuestion(String controlQuestion) {
        this.controlQuestion = controlQuestion;
    }

    protected void setPriority(int priority) {
        this.priority = priority;
    }

    protected void setControlID(String controlID) {
        this.controlID = controlID;
    }

    protected void setImplementingFrameworks(List<FrameworkReference> implementingFrameworks) {
        this.implementingFrameworks = implementingFrameworks;
    }

    protected void setSource(String source) {
        this.source = source;
    }

    protected void setMaturityLevel0(String maturityLevel0) {
        this.maturityLevel0 = maturityLevel0;
    }

    protected void setMaturityLevel1(String maturityLevel1) {
        this.maturityLevel1 = maturityLevel1;
    }

    protected void setMaturityLevel2(String maturityLevel2) {
        this.maturityLevel2 = maturityLevel2;
    }

    protected void setMaturityLevel3(String maturityLevel3) {
        this.maturityLevel3 = maturityLevel3;
    }

    protected void setMaturityLevel4(String maturityLevel4) {
        this.maturityLevel4 = maturityLevel4;
    }

    protected void setMaturityLevel5(String maturityLevel5) {
        this.maturityLevel5 = maturityLevel5;
    }

    protected void setCyberDefenseFunction(String cyberDefenseFunction) {
        this.cyberDefenseFunction = cyberDefenseFunction;
    }

    protected void setCyberDefenseAsset(List<String> cyberDefenseAsset) {
        this.cyberDefenseAsset = cyberDefenseAsset;
    }

    public int getControlNumber() {
        return controlNumber;
    }

    public String getDetails() {
        return details;
    }

    public String getControlOverview() {
        return controlOverview;
    }

    public String getControlQuestion() {
        return controlQuestion;
    }

    public int getPriority() {
        return priority;
    }

    public List<FrameworkReference> getImplementingFrameworks() {
        return implementingFrameworks;
    }

    public String getControlID() {
        return controlID;
    }

    public String getSource() {
        return source;
    }

    public String getMaturityLevel0() {
        return maturityLevel0;
    }

    public String getMaturityLevel1() {
        return maturityLevel1;
    }

    public String getMaturityLevel2() {
        return maturityLevel2;
    }

    public String getMaturityLevel3() {
        return maturityLevel3;
    }

    public String getMaturityLevel4() {
        return maturityLevel4;
    }

    public String getMaturityLevel5() {
        return maturityLevel5;
    }

    public String getCyberDefenseFunction() {
        return cyberDefenseFunction;
    }

    public List<String> getCyberDefenseAsset() {
        return cyberDefenseAsset;
    }

    public String getCyberDefenseFunctionColor() {
        return cyberDefenseFunctionColor;
    }

    protected void setCyberDefenseFunctionColor(String cyberDefenseFunctionColor) {
        this.cyberDefenseFunctionColor = cyberDefenseFunctionColor;
    }

    public String getCyberDefenseAssetColor() {
        return cyberDefenseAssetColor;
    }

    protected void setCyberDefenseAssetColor(String cyberDefenseAssetColor) {
        this.cyberDefenseAssetColor = cyberDefenseAssetColor;
    }

    public List<String> getControlTypes() {
        return controlTypes;
    }

    protected void setControlTypes(List<String> controlTypes) {
        this.controlTypes = controlTypes;
    }

    @Override
    public String toString() {
        return "Control{" +
                "controlNumber=" + controlNumber +
                ", details='" + details + '\'' +
                ", controlID='" + controlID + '\'' +
                ", controlOverview='" + controlOverview + '\'' +
                ", controlQuestion='" + controlQuestion + '\'' +
                ", priority=" + priority +
                ", source='" + source + '\'' +
                ", maturityLevel0='" + maturityLevel0 + '\'' +
                ", maturityLevel1='" + maturityLevel1 + '\'' +
                ", maturityLevel2='" + maturityLevel2 + '\'' +
                ", maturityLevel3='" + maturityLevel3 + '\'' +
                ", maturityLevel4='" + maturityLevel4 + '\'' +
                ", maturityLevel5='" + maturityLevel5 + '\'' +
                ", cyberDefenseFunction='" + cyberDefenseFunction + '\'' +
                ", cyberDefenseFunctionColor='" + cyberDefenseFunctionColor + '\'' +
                ", cyberDefenseAssetColor='" + cyberDefenseAssetColor + '\'' +
                '}';
    }
}
