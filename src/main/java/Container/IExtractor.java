package Container;

import Instance.Instance;

import java.util.List;

public interface IExtractor <T>{
    InstanceSupply<T> getSupplier();
    InstanceSupply<T> getSupplier(Class<T> iFaceType);
    T extract(Class<T> iFaceType);
    List<Instance> getInstancesByInterface(Class iFace);
    List<Instance> getInstancesByClass(Class instanceClass);
    void dispose();
}
