package Container;

import Instance.Instance;
import Instance.InstanceManager;
import SearchEngine.Find;

import java.util.ArrayList;
import java.util.List;

public class Container <T> implements IContainer<T> {

    private int containerSize;
    private int instancesAdded;
    private InstanceManager<T> instanceManager;


    public Container(int size){
        this.containerSize = size;
        this.instanceManager = new InstanceManager<T>(this);
    }

    public int getSize(){
        return this.containerSize;
    }

    public void registerInstance(T instance){
        if(this.instancesAdded == this.containerSize)
            throw new ArrayIndexOutOfBoundsException("Exceeded Container size: " + this.containerSize);

        this.instanceManager.addInstance(instance);
        instancesAdded++;
    }

    public T extract(Class<T> classType){

        List<Instance> instancesByIFace = this.getInstancesByInterface(classType);
        return (T) Find.in(instancesByIFace).by().priority(0).instance().get();
    }

    public List<T> extractAll(Class<T> classType){

        List<T> coreInstanceList = new ArrayList<T>();
        List<Instance> instancesByIFace = this.getInstancesByInterface(classType);

        for (Instance i :
                instancesByIFace) {
            coreInstanceList.add((T) i.get());
        }

        return coreInstanceList;
    }

    public List<Instance> getInstancesByInterface(Class iFace){
        return this.instanceManager.getInstancesByInterface(iFace);
    }

    public List<Instance> getInstancesByClass(Class instanceClass){
        return this.instanceManager.getInstancesByClassName(instanceClass);
    }
}