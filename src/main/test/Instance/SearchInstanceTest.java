package Instance;

import Container.Container;
import Container.IContainer;
import SearchEngine.Find;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class SearchInstanceTest {

    private final IContainer container;
    private final InstanceManager instanceManager;

    public SearchInstanceTest() {
        this.container = new Container(3);
        this.instanceManager = new InstanceManager(this.container);
    }

    @Test
    public void searchInClassRangeByTag(){
        List<String> i1 = new ArrayList<String>();
        Map<String, String> i2 = new HashMap<String, String>();
        List<String> i3 = new ArrayList<String>();

        this.container.addInstance(i1);
        this.container.addInstance(i2);
        this.container.addInstance(i3);

        Class instClass = i3.getClass();

        Instance i = (Instance) Arrays.stream(this.container.getInstancesByClass(instClass).toArray()).reduce((a, b) -> b).orElse(null);
        i.setTag("new-tag");

        List<Instance> instancesByClass = this.container.getInstancesByClass(i3.getClass());

        Instance iByTag = Find.in(instancesByClass).by().tag("new-tag").getInstance();
        assertNotNull(iByTag, "failed to find getInstance by tag");
    }

    @Test
    public void searchInClassRangeByPriority(){
        List<String> i1 = new ArrayList<String>();
        Map<String, String> i2 = new HashMap<String, String>();
        List<String> i3 = new ArrayList<String>();

        this.container.addInstance(i1);
        this.container.addInstance(i2);
        this.container.addInstance(i3);

        List<Instance> instancesByClass = this.container.getInstancesByClass(i3.getClass());
        Instance iByPriority = Find.in(instancesByClass).by().priority(0).getInstance();
        assertNotNull(iByPriority, "failed to find getInstance by priority");
    }

    @Test
    public void searchInClassRangeById(){
        List<String> i1 = new ArrayList<String>();
        Map<String, String> i2 = new HashMap<String, String>();
        List<String> i3 = new ArrayList<String>();

        this.container.addInstance(i1);
        this.container.addInstance(i2);
        this.container.addInstance(i3);

        Class instClass = i3.getClass();

        Instance i = (Instance) Arrays.stream(this.container.getInstancesByClass(instClass).toArray()).reduce((a, b) -> b).orElse(null);
        i.setId(3232);

        List<Instance> instancesByClass = this.container.getInstancesByClass(i3.getClass());
        Instance iById = Find.in(instancesByClass).by().id(3232).getInstance();
        assertNotNull(iById, "failed to find getInstance by id");
    }

    @Test
    public void searchInInterfaceRangeById(){
        List<String> i1 = new ArrayList<String>();
        Map<String, String> i2 = new HashMap<String, String>();
        List<String> i3 = new ArrayList<String>();

        this.container.addInstance(i1);
        this.container.addInstance(i2);
        this.container.addInstance(i3);

        Class iFace = i2.getClass().getInterfaces()[0];
        List<Instance> instancesByInterface = this.container.getInstancesByInterface(iFace);

        Instance i = (Instance) Arrays.stream(this.container.getInstancesByInterface(iFace).toArray()).reduce((a, b) -> b).orElse(null);
        i.setId(3232);

        Instance iById = Find.in(instancesByInterface).by().id(3232).getInstance();
        assertNotNull(iById, "failed to find getInstance by id");
    }
}
