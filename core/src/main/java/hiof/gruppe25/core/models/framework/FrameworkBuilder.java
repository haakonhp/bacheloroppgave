package hiof.gruppe25.core.models.framework;

public class FrameworkBuilder {
    protected String framework;

    public FrameworkBuilder() {
    }

    public FrameworkBuilder(int frameworkNumber, String framework) {
        this.framework = framework;
    }


    public FrameworkBuilder setFramework(String framework) {
        this.framework = framework;
        return this;
    }

    public Framework build() {
        return new Framework(this);
    }
}
