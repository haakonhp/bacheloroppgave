package hiof.gruppe25.core.models.framework;


public class Framework {
    private String framework;

    public Framework(FrameworkBuilder frameworkBuilder) {
        this.framework = frameworkBuilder.framework;
    }

    public Framework() {
    }

    protected void setFramework(String framework) {
        this.framework = framework;
    }


    public String getFramework() {
        return framework;
    }

    @Override
    public String toString() {
        return "Framework{" +
                ", framework='" + framework + '\'' +
                '}';
    }
}
