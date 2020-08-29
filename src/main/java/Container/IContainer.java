package Container;

import java.lang.reflect.Type;
import java.util.List;

import Instance.Instance;

public interface IContainer <T> {

    int getSize();
    void addInstance(T instance);
    List<Instance> getInstancesByInterface(Class superClass);
    List<Instance> getInstancesByClassName(Class instanceClass);
}
