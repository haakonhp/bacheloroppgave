package hiof.gruppe25.core.models.frameworkreference;

import hiof.gruppe25.core.models.framework.Framework;

import java.util.List;

public class FrameworkReferenceBuilder {
    protected List<String> references;
    protected Framework framework;

    public FrameworkReferenceBuilder(List<String> references, Framework framework) {
        this.references = references;
        this.framework = framework;
    }
    public void setReferences(List<String> references) {
        this.references = references;
    }

    public void setFramework(Framework framework) {
        this.framework = framework;
    }

    public FrameworkReference build() {
        return new FrameworkReference(this);
    }
}
