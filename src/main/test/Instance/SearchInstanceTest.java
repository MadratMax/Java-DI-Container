package Instance;

import Container.Container;
import Container.IContainer;
import SearchEngine.Find;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class SearchInstanceTest {

    private final IContainer container;

    public SearchInstanceTest() {
        this.container = new Container(3);
    }

    @Test
    public void searchInClassRangeByTag(){
        List<String> i1 = new ArrayList<String>();
        Map<String, String> i2 = new HashMap<String, String>();
        List<String> i3 = new ArrayList<String>();

        this.container.registerInstance(i1);
        this.container.registerInstance(i2);
        this.container.registerInstance(i3);

        Class instClass = i3.getClass();

        Instance i = (Instance) Arrays.stream(this.container.getInstancesByClass(instClass).toArray()).reduce((a, b) -> b).orElse(null);
        i.setTag("new-tag");

        List<Instance> instancesByClass = this.container.getInstancesByClass(i3.getClass());

        Instance iByTag = this.container.find().in(instancesByClass).by().tag("new-tag").instance();
        assertNotNull(iByTag, "failed to find instance by tag");
    }

    @Test
    public void searchInClassRangeByPriority(){
        List<String> i1 = new ArrayList<String>();
        Map<String, String> i2 = new HashMap<String, String>();
        List<String> i3 = new ArrayList<String>();

        this.container.registerInstance(i1);
        this.container.registerInstance(i2);
        this.container.registerInstance(i3);

        List<Instance> instancesByClass = this.container.getInstancesByClass(i3.getClass());
        Instance iByPriority = this.container.find().in(instancesByClass).by().priority(0).instance();
        assertNotNull(iByPriority, "failed to find instance by priority");
    }

    @Test
    public void searchInClassRangeById(){
        List<String> i1 = new ArrayList<String>();
        Map<String, String> i2 = new HashMap<String, String>();
        List<String> i3 = new ArrayList<String>();

        this.container.registerInstance(i1);
        this.container.registerInstance(i2);
        this.container.registerInstance(i3);

        Class instClass = i3.getClass();

        Instance i = (Instance) Arrays.stream(this.container.getInstancesByClass(instClass).toArray()).reduce((a, b) -> b).orElse(null);
        i.setId("3232");

        List<Instance> instancesByClass = this.container.getInstancesByClass(i3.getClass());
        Instance iById = this.container.find().in(instancesByClass).by().id("3232").instance();
        assertNotNull(iById, "failed to find instance by id");
    }

    @Test
    public void searchInClassRangeByType(){
        List<String> i1 = new ArrayList<String>();
        Map<String, String> i2 = new HashMap<String, String>();
        List<String> i3 = new ArrayList<String>();

        this.container.registerInstance(i1);
        this.container.registerInstance(i2);
        this.container.registerInstance(i3);

        Class instClass = i3.getClass();

        List<Instance> instancesByClass = this.container.getInstancesByClass(i3.getClass());
        Instance iByType = this.container.find().in(instancesByClass).by().type(instClass).instance();
        assertEquals(instClass, iByType.getType(), "expected type: " + instClass + " , but was: " + iByType.getType());
    }

    @Test
    public void searchInInterfaceRangeById(){
        List<String> i1 = new ArrayList<String>();
        Map<String, String> i2 = new HashMap<String, String>();
        List<String> i3 = new ArrayList<String>();

        this.container.registerInstance(i1);
        this.container.registerInstance(i2);
        this.container.registerInstance(i3);

        Class iFace = i2.getClass().getInterfaces()[0];
        List<Instance> instancesByInterface = this.container.getInstancesByInterface(iFace);

        Instance i = (Instance) Arrays.stream(this.container.getInstancesByInterface(iFace).toArray()).reduce((a, b) -> b).orElse(null);
        i.setId("3232");

        Instance iById = this.container.find().in(instancesByInterface).by().id("3232").instance();
        assertNotNull(iById, "failed to find instance by id");
    }
}
