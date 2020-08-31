package Extraction;

import Container.Container;
import Container.IContainer;
import Instance.InstanceManager;
import TestData.DifLogger;
import TestData.ILogger;
import TestData.ITestIFace;
import TestData.Logger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExtractTest {

    private IContainer container;
    private InstanceManager instanceManager;

    public ExtractTest() {
        this.container = new Container(5);
        this.instanceManager = new InstanceManager(this.container);

        ILogger l1 = new Logger("l1");
        ILogger l2 = new Logger("l2");
        ILogger l3 = new Logger("l3");
        ILogger l4 = new Logger("l4");
        ILogger difL5 = new DifLogger("difLogger");

        this.container.registerInstance(difL5).setTag("difL5").setPriority(0);
        this.container.registerInstance(l1).setTag("l1").setPriority(0);
        this.container.registerInstance(l2).setTag("l2").setPriority(1);
        this.container.registerInstance(l3).setTag("l3").setPriority(3);
        this.container.registerInstance(l4).setTag("l4").setPriority(2);
    }

    @Test
    void extractInstance() {
        ILogger l = (ILogger) this.container.extract(ILogger.class);

        assertNotNull(l, "extracted instance is null");

        // digLogger implements the same interface as other registered instances
        // since difLogger was registered first, its instance should be extracted first
        assertEquals(
                "difLogger", l.getName(),
                "expected logger instance is difLogger, but was: " + l.getName());
    }

    @Test
    void extractAllInstances() {
        ArrayList<ILogger> loggers = (ArrayList) this.container.extractAll(ILogger.class);

        loggers.toArray();

        for (int i = 0; i < loggers.size(); i++){
            if(i == 0){
                assertEquals(
                        "difLogger",
                        loggers.get(i).getName(),
                        "invalid instance has been extracted. expected difLogger, but was " + loggers.get(i).getName());
                continue;
            }

            String expectedName = "l" + i;
            assertNotNull(loggers.get(i), "extracted instance is null");
            assertEquals(expectedName, loggers.get(i).getName());
        }
    }

    @Test
    void extractWithActivatedSiblings() {
        ILogger l1 = new Logger("l1");
        ILogger l2 = new Logger("l2");
        ILogger l3 = new Logger("l3");
        ILogger l4 = new Logger("l4");
        ILogger difL5 = new DifLogger("difLogger");

        this.container.dispose();
        this.container.registerInstance(difL5).setTag("difL5").setPriority(0);
        this.container.registerInstance(l2).setTag("l2").setPriority(0);
        this.container.registerInstance(l3).setTag("l3").setPriority(1);
        this.container.registerInstance(l1).setTag("l1").setPriority(3);
        this.container.registerInstance(l4).setTag("l4").setPriority(2);
        this.container.activateSiblings();

        String expectedName1 = "l2";
        String expectedName2 = "l3";
        String expectedName3 = "l1";
        String expectedName4 = "l4";

        ITestIFace l = null;

        l = (ITestIFace) this.container.extract(ITestIFace.class);
        assertNotNull(l, "extracted instance is null");
        assertEquals(
                expectedName1,
                l.getName(),
                "invalid instance has been extracted. expected l2, but was " + l.getName());

        l = (ITestIFace) this.container.extract(ITestIFace.class);
        assertNotNull(l, "extracted instance is null");
        assertEquals(
                expectedName2,
                l.getName(),
                "invalid instance has been extracted. expected l3, but was " + l.getName());

        l = (ITestIFace) this.container.extract(ITestIFace.class);
        assertNotNull(l, "extracted instance is null");
        assertEquals(
                expectedName3,
                l.getName(),
                "invalid instance has been extracted. expected l1, but was " + l.getName());

        l = (ITestIFace) this.container.extract(ITestIFace.class);
        assertNotNull(l, "extracted instance is null");
        assertEquals(
                expectedName4,
                l.getName(),
                "invalid instance has been extracted. expected l4, but was " + l.getName());

        l = (ITestIFace) this.container.extract(ITestIFace.class);
        assertNotNull(l, "extracted instance is null");
        assertEquals(
                expectedName1,
                l.getName(),
                "invalid instance has been extracted. expected l2, but was " + l.getName());
    }

    @Test
    void deactivateSiblings() {
        this.extractWithActivatedSiblings();
        this.container.deactivateSiblings();

        String expectedName1 = "l2";

        ITestIFace l = null;

        l = (ITestIFace) this.container.extract(ITestIFace.class);
        assertNotNull(l, "extracted instance is null");
        assertEquals(
                expectedName1,
                l.getName(),
                "invalid instance has been extracted. expected l2, but was " + l.getName());

        l = (ITestIFace) this.container.extract(ITestIFace.class);
        assertNotNull(l, "extracted instance is null");
        assertEquals(
                expectedName1,
                l.getName(),
                "invalid instance has been extracted. expected l2, but was " + l.getName());
    }

    @Test
    void changePriorityAndExtract() {
        String expectedName1 = "l1";

        ITestIFace l = null;

        l = (ITestIFace) this.container.extract(ITestIFace.class);
        assertNotNull(l, "extracted instance is null");
        assertEquals(
                expectedName1,
                l.getName(),
                "invalid instance has been extracted. expected l1, but was " + l.getName());

        this.container.find().in(container.getInstancesByClass(Logger.class)).by().priority(0).instance().setPriority(5);
        this.container.find().in(container.getInstancesByClass(Logger.class)).by().priority(1).instance().setPriority(6);

        l = (ITestIFace) this.container.extract(ITestIFace.class);
        assertNotNull(l, "extracted instance is null");
        assertEquals(
                "l4",
                l.getName(),
                "invalid instance has been extracted. expected l4, but was " + l.getName());
    }

    @Test
    void extractByDifInterfaces() {
        ITestIFace l1 = (ITestIFace) this.container.extract(ITestIFace.class);
        assertNotNull(l1, "extracted instance is null");
        assertEquals(
                "l1",
                l1.getName(),
                "invalid instance has been extracted. expected l1, but was " + l1.getName());

        ILogger difLogger = (ILogger) this.container.extract(ILogger.class);
        assertNotNull(difLogger, "extracted instance is null");
        assertEquals(
                "difLogger",
                difLogger.getName(),
                "invalid instance has been extracted. expected difLogger, but was " + difLogger.getName());
    }
}
