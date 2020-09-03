package Priority;

import Container.Container;
import Container.IContainer;
import Instance.Instance;
import Instance.InstanceManager;
import TestData.DifLogger;
import TestData.ILogger;
import TestData.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriorityTest {

    private IContainer container;

    public PriorityTest() {
        this.container = new Container(5);
    }

    @Test
    void autoAssignmentPriority() {

        ILogger l1 = new Logger("l1");
        ILogger l2 = new Logger("l2");
        ILogger l3 = new Logger("l3");
        ILogger l4 = new Logger("l4");
        ILogger difL5 = new DifLogger("difLogger");

        int p1 = this.container.registerInstance(difL5).setTag("difL5").getPriority();
        int p2 = this.container.registerInstance(l1).setTag("l1").getPriority();
        int p3 = this.container.registerInstance(l2).setTag("l2").getPriority();
        int p4 = this.container.registerInstance(l3).setTag("l3").getPriority();
        int p5 =  this.container.registerInstance(l4).setTag("l4").getPriority();

        assertEquals(
                0, p1,
                "expected priority: 0, but was: " + p1);
        assertEquals(
                0, p2,
                "expected priority: 0, but was: " + p2);
        assertEquals(
                1, p3,
                "expected priority: 1, but was: " + p3);
        assertEquals(
                2, p4,
                "expected priority: 2, but was: " + p4);
        assertEquals(
                3, p5,
                "expected priority: 3, but was: " + p5);
    }

    @Test
    void updatePriorityInDifferentTypes() {

        ILogger l1 = new Logger("l1");
        ILogger l2 = new Logger("l2");
        ILogger difL5 = new DifLogger("difLogger");

        Instance difL = this.container.registerInstance(difL5).setTag("difL5").getInstance(); // priority 0
        Instance i1 = this.container.registerInstance(l1).setTag("l1").getInstance();         // priority 0
        Instance i2 = this.container.registerInstance(l1).setTag("l2").getInstance();         // priority 1

        difL.setPriority(0);
        assertTrue(difL.getPriority() == 0, "expected priority: 0, but was " + difL.getPriority());
        assertTrue(i1.getPriority() == 0, "expected priority: 0, but was " + i1.getPriority());

        i2.setPriority(0);
        assertTrue(i2.getPriority() == 0, "expected priority: 0, but was " + i1.getPriority());
        assertTrue(difL.getPriority() == 0, "expected priority: 0, but was " + difL.getPriority());
        assertTrue(i1.getPriority() == 1, "expected priority: 1, but was " + i1.getPriority());
    }

    @Test
    void updatePriorityAmongSameTypes() {

        ILogger l1 = new Logger("l1");
        ILogger l2 = new Logger("l2");
        ILogger l3 = new Logger("l3");
        ILogger l4 = new Logger("l4");
        ILogger difL5 = new DifLogger("difLogger");

        Instance difL = this.container.registerInstance(difL5).setTag("difL5").getInstance(); // priority 0
        Instance i1 = this.container.registerInstance(l1).setTag("l1").getInstance(); // priority 0
        Instance i2 = this.container.registerInstance(l2).setTag("l2").getInstance(); // priority 1
        Instance i3 = this.container.registerInstance(l3).setTag("l3").getInstance(); // priority 2
        Instance i4 = this.container.registerInstance(l4).setTag("l4").getInstance(); // priority 3

        i4.setPriority(1);
        assertTrue(i2.getPriority() == 3, "expected priority: 3, but was " + i2.getPriority());
    }

    @Test
    void updatePriorityWithBoundToSiblingCount() {

        ILogger l1 = new Logger("l1");
        ILogger l2 = new Logger("l2");
        ILogger l3 = new Logger("l3");
        ILogger l4 = new Logger("l4");

        Instance i1 = this.container.registerInstance(l1).setTag("l1").getInstance(); // default priority: 0
        Instance i2 = this.container.registerInstance(l2).setTag("l2").getInstance(); // default priority: 1
        Instance i3 = this.container.registerInstance(l3).setTag("l3").getInstance(); // default priority: 2
        Instance i4 = this.container.registerInstance(l4).setTag("l4").getInstance(); // default priority: 3

        i1.setPriority(3);
        assertTrue(i4.getPriority() == 4, "expected priority: 4, but was " + i4.getPriority());

        i2.setPriority(4);
        assertTrue(i4.getPriority() == 5, "expected priority: 5, but was " + i4.getPriority());
    }

    @Test
    void removeMajorPriorityAndExtract() {

        ILogger l1 = new Logger("l1");
        ILogger l2 = new Logger("l2");
        ILogger l3 = new Logger("l3");
        ILogger l4 = new Logger("l4");

        Instance i1 = this.container.registerInstance(l1).setTag("l1").getInstance(); // default priority: 0
        Instance i2 = this.container.registerInstance(l2).setTag("l2").getInstance(); // default priority: 1
        Instance i3 = this.container.registerInstance(l3).setTag("l3").getInstance(); // default priority: 2
        Instance i4 = this.container.registerInstance(l4).setTag("l4").getInstance(); // default priority: 3

        i1.setPriority(6);
        i2.setPriority(5);
        i3.setPriority(4);

        Instance majorPriorityInstance =
                this.container.find().in(container.getInstancesByClass(Logger.class)).by().priority(0).instance();

        assertTrue(
                majorPriorityInstance == null,
                "instance with 0 priority should not exist");

        String extractedInstanceName = ((ILogger) this.container.getInstance(ILogger.class)).getName();

        assertEquals(
                "l4",
                extractedInstanceName,
                "expected extracted instance is l4, but was " + extractedInstanceName);
    }

    @Test
    void thereIsNoIdenticalPriority() {

        ILogger l1 = new Logger("l1");
        ILogger l2 = new Logger("l2");
        ILogger l3 = new Logger("l3");
        ILogger l4 = new Logger("l4");

        Instance i1 = this.container.registerInstance(l1).setTag("l1").getInstance(); // default priority: 0
        Instance i2 = this.container.registerInstance(l2).setTag("l2").getInstance(); // default priority: 1
        Instance i3 = this.container.registerInstance(l3).setTag("l3").getInstance(); // default priority: 2
        Instance i4 = this.container.registerInstance(l4).setTag("l4").getInstance(); // default priority: 3

        i3.setPriority(0);

        Instance majorPriorityInstance =
                this.container.find().in(container.getInstancesByClass(Logger.class)).by().priority(0).instance();

        assertEquals(
                i3.get(), majorPriorityInstance.get(),
                "failed to update priority");

        int minPriority =
                this.container
                        .find()
                        .in(container.getInstancesByInterface(ILogger.class))
                        .by()
                        .lowPriority()
                        .instance()
                        .getPriority();

        assertEquals(
                4,
                minPriority,
                "expected min priority is 4, but was " + minPriority);
    }
}
