package hiof.gruppe25.core.models.results;

import hiof.gruppe25.core.models.control.Control;
import hiof.gruppe25.core.models.framework.Framework;
import hiof.gruppe25.core.models.source.Source;

import java.util.List;

public class ControlResult {
    private final List<Control> controls;
    private final List<Framework> frameworks;

    private final List<Source> sources;

    public ControlResult(List<Control> controls, List<Framework> frameworks, List<Source> sources) {
        this.controls = controls;
        this.frameworks = frameworks;
        this.sources = sources;
    }

    public List<Control> getControls() {
        return controls;
    }

    public List<Framework> getFrameworks() {
        return frameworks;
    }

    public List<Source> getSources() {
        return sources;
    }
}
