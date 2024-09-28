package hiof.gruppe25.core.models.frameworkreference;

import hiof.gruppe25.core.models.framework.Framework;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FrameworkReference {
    private UUID frameworkReferenceListId = UUID.randomUUID();
    private List<String> dbReferences = new ArrayList<>();
    private Framework framework;

    public FrameworkReference() {
    }

    public FrameworkReference(FrameworkReferenceBuilder builder) {
        this.framework = builder.framework;
        this.dbReferences = builder.references;
    }

    protected void setDbReferences(List<String> dbReferences) {
        this.dbReferences = dbReferences;
    }

    protected void setFramework(Framework framework) {
        this.framework = framework;
    }

    public List<String> getDbReferences() {
        return dbReferences;
    }

    public Framework getFramework() {
        return framework;
    }

    public UUID getFrameworkReferenceListId() {
        return frameworkReferenceListId;
    }

    public void setFrameworkReferenceListId(UUID frameworkReferenceListId) {
        this.frameworkReferenceListId = frameworkReferenceListId;
    }

    @Override
    public String toString() {
        return "FrameworkReference{" +
                "dbReferences=" + dbReferences +
                ", framework=" + framework +
                '}';
    }
}
