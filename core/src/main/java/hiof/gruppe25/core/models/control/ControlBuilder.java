package hiof.gruppe25.core.models.control;

import hiof.gruppe25.core.models.frameworkreference.FrameworkReference;

import java.util.List;

public class ControlBuilder {

    protected int controlNumber;
    protected String details;
    protected String controlID;
    protected String controlOverview;
    protected String controlQuestion;
    protected int priority;
    protected String source;
    protected String maturityLevel0;
    protected String maturityLevel1;
    protected String maturityLevel2;
    protected String maturityLevel3;
    protected String maturityLevel4;
    protected String maturityLevel5;
    protected String cyberDefenseFunctionColor;
    protected String cyberDefenseAssetColor;
    protected String cyberDefenseFunction;
    protected List<String> cyberDefenseAsset;
    protected List<String> controlTypes;
    protected List<FrameworkReference> implementingFrameworks;


    public ControlBuilder() {
    }

    public ControlBuilder(int controlNumber, String controlID) {
        this.controlNumber = controlNumber;
        this.controlID = controlID;
    }

    public ControlBuilder setControlNumber(int controlNumber) {
        this.controlNumber = controlNumber;
        return this;
    }

    public ControlBuilder setDetails(String details) {
        this.details = details;
        return this;
    }

    public ControlBuilder setControlOverview(String controlOverview) {
        this.controlOverview = controlOverview;
        return this;
    }

    public ControlBuilder setControlQuestion(String controlQuestion) {
        this.controlQuestion = controlQuestion;
        return this;
    }

    public ControlBuilder setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public ControlBuilder setImplementingFrameworks(List<FrameworkReference> implementingFrameworks) {
        this.implementingFrameworks = implementingFrameworks;
        return this;
    }

    public ControlBuilder setControlID(String controlID) {
        this.controlID = controlID;
        return this;
    }

    public ControlBuilder setSource(String source) {
        this.source = source;
        return this;
    }
    public ControlBuilder setMaturityLevel0(String maturityLevel0) {
        this.maturityLevel0 = maturityLevel0;
        return this;
    }


    public ControlBuilder setMaturityLevel1(String maturityLevel1) {
        this.maturityLevel1 = maturityLevel1;
        return this;
    }

    public ControlBuilder setMaturityLevel2(String maturityLevel2) {
        this.maturityLevel2 = maturityLevel2;
        return this;
    }

    public ControlBuilder setMaturityLevel3(String maturityLevel3) {
        this.maturityLevel3 = maturityLevel3;
        return this;
    }

    public ControlBuilder setMaturityLevel4(String maturityLevel4) {
        this.maturityLevel4 = maturityLevel4;
        return this;
    }

    public ControlBuilder setMaturityLevel5(String maturityLevel5) {
        this.maturityLevel5 = maturityLevel5;
        return this;
    }

    public ControlBuilder setCyberDefenseFunction(String cyberDefenseFunction) {
        this.cyberDefenseFunction = cyberDefenseFunction;
        return this;
    }

    public ControlBuilder setCyberDefenseAsset(List<String> cyberDefenseAsset) {
        this.cyberDefenseAsset = cyberDefenseAsset;
        return this;
    }

    public ControlBuilder setCyberDefenseFunctionColor(String cyberDefenseFunctionColor) {
        this.cyberDefenseFunctionColor = cyberDefenseFunctionColor;
        return this;
    }

    public ControlBuilder setCyberDefenseAssetColor(String cyberDefenseAssetColor) {
        this.cyberDefenseAssetColor = cyberDefenseAssetColor;
        return this;
    }

    public ControlBuilder setControlTypes(List<String> controlTypes) {
        this.controlTypes = controlTypes;
        return this;
    }

    public Control build() {
        return new Control(this);
    }
}

