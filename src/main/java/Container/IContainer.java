package Container;

import Instance.Instance;
import SearchEngine.Find;

import java.util.List;

public interface IContainer <T> {

    void activateSiblings();
    void deactivateSiblings();
    int getSize();
    Instance registerInstance(T instance);
    T extract(Class<T> classType);
    List<T> extractAll(Class<T> classType);
    List<Instance> getInstancesByInterface(Class superClass);
    List<Instance> getInstancesByClass(Class instanceClass);
    Find find();
    void dispose();
}
