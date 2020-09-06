package Container;

import Instance.*;
import PackageManager.InstancePackageProvider;
import SearchEngine.Find;

import java.util.List;

public interface IContainer <T> {

    void setDefaultInterface(Class<T> interfaceType);
    void activateSiblings();
    void deactivateSiblings();
    int getSize();
    Instance registerInstance(T instance);
    InstanceSupply<T> getSupply();
    InstanceSupply<T> getSupply(Class<T> iFaceType);
    T getInstance(Class<T> classType);
    List<T> getAllInstances(Class<T> classType);
    List<Instance> getInstancesByInterface(Class superClass);
    List<Instance> getInstancesByClass(Class instanceClass);
    InstancePackageProvider packageProvider();
    Find find();
    void dispose();
}
