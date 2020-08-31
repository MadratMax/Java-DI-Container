package Container;

import Instance.Instance;
import Instance.InstanceManager;
import SearchEngine.By;
import SearchEngine.Find;

import java.util.ArrayList;
import java.util.Arrays;
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

    public T extract(Class<T> interfaceType){

        List<Instance> instancesByIFace = this.getInstancesByInterface(interfaceType);
        Instance i = (Instance) this.find().in(instancesByIFace).by().highPriority().instance();

        if(i == null)
            throw new NullPointerException("Failed to extract instance that implements " + interfaceType.toString() + ".");

        return (T) i.get();
    }

    public ArrayList<T> extractAll(Class<T> interfaceType){

        ArrayList<T> coreInstanceList = new ArrayList<T>();
        List<Instance> instancesByIFace = this.getInstancesByInterface(interfaceType);

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

    public Find find(){
        return new Find();
    }
}