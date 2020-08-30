package Container;

import Instance.Instance;
import Instance.InstanceManager;
import java.util.List;

public class Container <T> implements IContainer<T> {

    private int containerSize;
    private InstanceManager<T> instanceManager;


    public Container(int size){
        this.containerSize = size;
        this.instanceManager = new InstanceManager<T>(this);
    }

    public int getSize(){
        return this.containerSize;
    }

    public void addInstance(T instance){
        this.instanceManager.addInstance(instance);
    }

    public List<Instance> getInstancesByInterface(Class iFace){
        return this.instanceManager.getInstancesByInterface(iFace);
    }

    public List<Instance> getInstancesByClassName(Class instanceClass){
        return this.instanceManager.getInstancesByClassName(instanceClass);
    }
}