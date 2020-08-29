package Container;

import Instance.Instance;
import Types.TypeSet;

import java.lang.reflect.Type;
import java.util.Arrays;

public class Container <T>{

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

    private void setSiblingRelation(Type type, Instance instance) {
        for (Instance i :
                this.typeSet.getInstancesByType(type)) {
            instance.addSibling(i);
            this.instances[Arrays.asList(this.instances).indexOf(i)].addSibling(instance);
            instance.decreasePriority();
        }
    }
}