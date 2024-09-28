package tests.controllers.getters;

import hiof.gruppe25.core.models.control.Control;
import hiof.gruppe25.core.models.control.ControlBuilder;
import hiof.gruppe25.core.models.framework.Framework;
import hiof.gruppe25.core.models.framework.FrameworkBuilder;
import hiof.gruppe25.core.models.frameworkreference.FrameworkReference;
import hiof.gruppe25.core.models.frameworkreference.FrameworkReferenceBuilder;
import hiof.gruppe25.persistence.repositories.ControlRepository;
import hiof.gruppe25.persistence.repositories.FrameworkRepository;
import hiof.gruppe25.persistence.utilities.JPAUnitOfWork;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
public class controlGetters {
    public void createTestData(ControlRepository repository, FrameworkRepository frameworkRepository) {
        List<String> assets = new ArrayList<>();
        assets.add("People");
        assets.add("Networks");

        List<String> referencedVia = new ArrayList<>();
        referencedVia.add("5.1");
        referencedVia.add("5.3");

        Framework testFramework = new FrameworkBuilder(2, "Test 2").build();
        Framework testFramework2 = new FrameworkBuilder(3, "Test 3").build();

        FrameworkReference frameworkReference = new FrameworkReferenceBuilder(referencedVia, testFramework).build();
        FrameworkReference frameworkReference2 = new FrameworkReferenceBuilder(referencedVia, testFramework).build();
        FrameworkReference frameworkReference3 = new FrameworkReferenceBuilder(referencedVia, testFramework).build();

        List<FrameworkReference> frameworks1 = new ArrayList<>();
        List<FrameworkReference> frameworks2 = new ArrayList<>();
        List<FrameworkReference> frameworks3 = new ArrayList<>();

        frameworks1.add(frameworkReference);
        frameworks2.add(frameworkReference2);
        frameworks3.add(frameworkReference3);

        frameworkRepository.add(testFramework);
        frameworkRepository.add(testFramework2);

        Control control1 = new ControlBuilder().setControlNumber(1).setCyberDefenseAsset(assets).setSource("source1").setImplementingFrameworks(frameworks1).build();
        Control control2 = new ControlBuilder().setControlNumber(2).setCyberDefenseAsset(assets).setSource("source2").setImplementingFrameworks(frameworks2).build();
        Control control3 = new ControlBuilder().setControlNumber(3).setCyberDefenseAsset(assets).setSource("source3").setImplementingFrameworks(frameworks3).build();
        Control control4 = new ControlBuilder().setControlNumber(4).setCyberDefenseAsset(assets).setSource("source4").build();

        repository.add(control1);
        repository.add(control2);
        repository.add(control3);
        repository.add(control4);
    }

    @Test
    public void fetching_all_controllers_should_result_in_proper_objects() {
        JPAUnitOfWork uow = new JPAUnitOfWork();
        ControlRepository repository = new ControlRepository(uow);
        FrameworkRepository frameworkRepository = new FrameworkRepository(uow);
        createTestData(repository,frameworkRepository);
        Approvals.verify(repository.getAll());
        uow.close();
    }

    @Test
    public void getting_filtered_by_framework_sources_and_framework_with_proper_filters_should_return_corresponding_elements() {
        JPAUnitOfWork uow = new JPAUnitOfWork();
        ControlRepository repository = new ControlRepository(uow);
        FrameworkRepository frameworkRepository = new FrameworkRepository(uow);

        createTestData(repository,frameworkRepository);

        List<String> sources = new ArrayList<>();
        List<String> frameworks = new ArrayList<>();

        sources.add("source1");
        sources.add("source2");
        sources.add("source3");
        sources.add("source4");
        frameworks.add("Test 3");
        frameworks.add("Test 2");

        List<Control> controls = repository.getFilteredBySourcesAndFrameworks(sources, frameworks);

        Approvals.verify(controls);
        uow.close();
    }
    @Test
    public void getting_filtered_by_framework_sources_and_framework_with_improper_frameworks_should_return_nothing() {
        JPAUnitOfWork uow = new JPAUnitOfWork();
        ControlRepository repository = new ControlRepository(uow);
        FrameworkRepository frameworkRepository = new FrameworkRepository(uow);

        createTestData(repository,frameworkRepository);
        List<String> sources = new ArrayList<>();
        List<String> frameworks = new ArrayList<>();

        sources.add("source1");
        sources.add("source2");
        sources.add("source3");
        sources.add("source4");
        frameworks.add("Test 3a");
        frameworks.add("Test 2a");

        List<Control> controls = repository.getFilteredBySourcesAndFrameworks(sources, frameworks);

        Approvals.verify(controls);
        uow.close();
    }
    @Test
    public void getting_filtered_by_framework_sources_and_framework_with_improper_sources_should_return_nothing() {
        JPAUnitOfWork uow = new JPAUnitOfWork();
        ControlRepository repository = new ControlRepository(uow);
        FrameworkRepository frameworkRepository = new FrameworkRepository(uow);
        createTestData(repository,frameworkRepository);

        List<String> sources = new ArrayList<>();
        List<String> frameworks = new ArrayList<>();

        sources.add("source1a");
        sources.add("source2a");
        sources.add("source3a");
        sources.add("source4a");
        frameworks.add("Test 3");
        frameworks.add("Test 2");

        List<Control> controls = repository.getFilteredBySourcesAndFrameworks(sources, frameworks);
        Approvals.verify(controls);
        uow.close();
    }
}
