package Container;

import Instance.Instance;
import Types.TypeSet;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Container <T> implements IContainer<T> {

    private Instance[] instances;
    private TypeSet typeSet;
    private int containerSize;
    private int lastAddedIndex;

    public Container(int size){
        this.containerSize = size;
        this.instances = new Instance[size];

        this.typeSet = new TypeSet();
    }

    public int getSize(){
        return this.containerSize;
    }

    public void addInstance(T instance){
        if(this.lastAddedIndex == containerSize)
            throw new ArrayIndexOutOfBoundsException("Exceeded Container size: " + this.containerSize);

        Instance<T> newInstance = new Instance<T>(instance);
        this.instances[lastAddedIndex] = newInstance;
        Type instanceType = this.instances[lastAddedIndex].getType();
        boolean typeAdded = this.typeSet.addType(instanceType, newInstance);
        lastAddedIndex++;

        if(!typeAdded)
            this.setSiblingRelation(instanceType, newInstance);
    }

    public List<Instance> getInstancesByInterface(Class superClass){
        List<Instance> instancesList = new ArrayList<Instance>();

        for (Instance i :
                this.instances) {
            if(i.isExtends(superClass)){
                instancesList.add(i);
            }
        }

        return instancesList;
    }

    public List<Instance> getInstancesByClassName(Class instanceClass){
        return this.typeSet.getInstancesByType(instanceClass);
    }

    private void setSiblingRelation(Type type, Instance newInstance) {
        int priority = 0;
        List<Instance> instances = this.typeSet.getInstancesByType(type);

        for (Instance i :
                instances) {
            newInstance.addSibling(i);
            this.instances[Arrays.asList(this.instances).indexOf(i)].addSibling(newInstance);
            newInstance.setPriority(priority++);
        }
    }
}