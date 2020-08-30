package Instance;

import Container.Container;
import Container.IContainer;
import SearchEngine.Find;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiblingsTest {

    private final IContainer container;
    private final InstanceManager instanceManager;

    public SiblingsTest(){
        this.container = new Container(4);
        this.instanceManager = new InstanceManager(this.container);
    }

    @Test
    public void getSiblings(){
        List<String> i1 = new ArrayList<String>();
        Map<String, String> i2 = new HashMap<String, String>();
        List<String> i3 = new ArrayList<String>();
        List<String> i4 = new ArrayList<String>();

        this.container.addInstance(i1);
        this.container.addInstance(i2);
        this.container.addInstance(i3);
        this.container.addInstance(i4);

        Class instClass = i3.getClass();
        List<Instance> instancesByClass = this.container.getInstancesByClass(instClass);
        Instance iByType = Find.in(instancesByClass).by().highPriority().getInstance();

        Siblings siblings = iByType.getSiblings();
        assertNotNull(siblings, "failed to get siblings");
        assertTrue(siblings.getCount() == 2, "expected siblings count: 2, but was " + siblings.getCount());
    }
}
