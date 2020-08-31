package Container;

import java.lang.reflect.Type;
import java.util.List;

import Instance.Instance;
import SearchEngine.Find;

public interface IContainer <T> {

    int getSize();
    void registerInstance(T instance);
    T extract(Class<T> classType);
    List<T> extractAll(Class<T> classType);
    List<Instance> getInstancesByInterface(Class superClass);
    List<Instance> getInstancesByClass(Class instanceClass);
    Find find();
}
