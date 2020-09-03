package Instance;

import Container.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstanceManagerTest {

    private final IContainer container;

    public InstanceManagerTest() {
        this.container = new Container(2);

    }

    @Test
    void addInstance() {
        List<String> list = new ArrayList<>();
        this.container.registerInstance(list);

        Class instClass = list.getClass();
        Instance i = (Instance) Arrays.stream(this.container.getInstancesByClass(instClass).toArray()).findFirst().get();
        assertTrue(i.getType().equals(instClass), "expected getInstance type is List<String>, but was: " + i.getType());
    }

    @Test
    void priorityIsDifferent() {
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        this.container.registerInstance(list);
        this.container.registerInstance(list2);

        Class instClass = list.getClass();
        Instance i = (Instance) Arrays.stream(this.container.getInstancesByClass(instClass).toArray()).findFirst().get();
        Instance i2 = (Instance) Arrays.stream(this.container.getInstancesByClass(instClass).toArray()).reduce((a, b) -> b).orElse(null);

        assertTrue(i.getPriority() == 0, "expected priority: 0, but was: " + i.getPriority());
        assertTrue(i2.getPriority() == 1, "expected priority: 1, but was: " + i2.getPriority());
    }

    @Test
    void setTag() {
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        this.container.registerInstance(list);
        this.container.registerInstance(list2);

        Class instClass = list.getClass();
        Instance i = (Instance) Arrays.stream(this.container.getInstancesByClass(instClass).toArray()).findFirst().orElse(null);
        Instance i2 = (Instance) Arrays.stream(this.container.getInstancesByClass(instClass).toArray()).reduce((a, b) -> b).orElse(null);
        i2.setTag("new-tag");

        assertTrue(i2.getTag().equals("new-tag"), "expected updated tag name: new-tag, but was: " + i.getTag());
    }
}