package Container;

import Instance.Instance;
import Instance.InstanceManager;
import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    private IContainer container;

    public ContainerTest() {
        this.container = new Container(2);
    }

    @Test
    void getSize() {
        assertEquals(
                2, this.container.getSize(),
                "expected container size is 2, but was: " + this.container.getSize());
    }

    @Test
    void addInstance() {
        String str = new String();
        this.container.registerInstance(str);
        int instanceCount = this.container.getInstancesByClass(str.getClass()).toArray().length;
        assertTrue(
                instanceCount == 1,
                "expected instances in container: 1, but was: " + instanceCount);
    }

    @Test
    void getInstancesByInterface() {
        IContainer container1 = new Container(1);
        IContainer container2 = new Container(2);
        this.container.registerInstance(container1);
        this.container.registerInstance(container2);
        Class iFace = container1.getClass().getInterfaces()[0];
        List<Instance> instancesByIFace = this.container.getInstancesByInterface(iFace);

        for (Instance i :
                instancesByIFace) {
            assertTrue(
                    i.getType().equals(container1.getClass()),
                    "expected instances type is Container, but was: " + i.getType());

            int instancesByIFaceCount = instancesByIFace.toArray().length;

            assertTrue(instancesByIFaceCount == 2, "expected instances count: 2, but was: " + instancesByIFaceCount);
        }
    }

    @Test
    void getInstancesByClassName() {
        String str = new String();
        String str2 = new String();
        this.container.registerInstance(str);
        this.container.registerInstance(str2);
        List<Instance> instancesByClass = this.container.getInstancesByClass(str.getClass());

        for (Instance i :
                instancesByClass) {
            assertTrue(
                    i.getType().equals(str.getClass()),
                    "expected instances type is String, but was: " + i.getType());
        }

        int instancesByClassCount = instancesByClass.toArray().length;

        assertTrue(instancesByClassCount == 2, "expected instances count: 2, but was: " + instancesByClassCount);
    }

    @Test
    void exceptionWhenContainerSizeExceeded(){
        String str = new String();
        String str2 = new String();
        this.container.registerInstance(str);
        this.container.registerInstance(str2);

        assertThrows(IndexOutOfBoundsException.class, () -> this.container.registerInstance(str2));
    }
}
