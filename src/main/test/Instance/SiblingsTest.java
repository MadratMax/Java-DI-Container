package Instance;

import Container.Container;
import Container.IContainer;
import SearchEngine.Find;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiblingsTest {

    private final IContainer<Object> container;
    private final InstanceManager instanceManager;

    public SiblingsTest(){
        this.container = new Container<>(4);
        this.instanceManager = new InstanceManager(this.container);
    }

    @Test
    public void getSiblings(){
        List<String> i1 = new ArrayList<String>();
        Map<String, String> i2 = new HashMap<String, String>();
        List<String> i3 = new ArrayList<String>();
        List<String> i4 = new ArrayList<String>();

        this.container.registerInstance(i1);
        this.container.registerInstance(i2);
        this.container.registerInstance(i3);
        this.container.registerInstance(i4);

        Class instClass = i3.getClass();
        List<Instance> instancesByClass = this.container.getInstancesByClass(instClass);
        Instance iByType = this.container.find().in(instancesByClass).by().highPriority().instance();

        ArrayList siblings = iByType.getSiblings();
        assertNotNull(siblings, "failed to extract siblings");
        assertTrue(siblings.size() == 2, "expected siblings count: 2, but was " + siblings.size());
    }
}
