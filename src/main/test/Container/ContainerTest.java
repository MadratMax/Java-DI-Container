package Container;

import Instance.InstanceManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    private IContainer container;
    private InstanceManager instanceManager;

    public ContainerTest(){
        this.container = new Container(2);
        this.instanceManager = new InstanceManager(this.container);
    }

    @Test
    void getSize() {
        assertEquals(2, this.container.getSize());
    }

    @Test
    void addInstance() {
    }

    @Test
    void getInstancesByInterface() {
    }

    @Test
    void getInstancesByClassName() {
    }
}