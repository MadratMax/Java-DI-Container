package Instance;

import Container.IContainer;
import Types.TypeSet;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstanceManager <T> {

    private final IContainer container;
    private final Instance[] instances;
    private final TypeSet typeSet;
    private int lastAddedIndex;

    public InstanceManager(IContainer container){
        this.container = container;
        this.instances = new Instance[this.container.getSize()];
        this.typeSet = new TypeSet();
    }

    public Instance addInstance(T instance){
        Instance<T> newInstance = new Instance<T>(instance);
        this.instances[lastAddedIndex] = newInstance;
        Type instanceType = this.instances[lastAddedIndex].getType();
        boolean typeAdded = this.typeSet.addType(instanceType, newInstance);
        lastAddedIndex++;

        if(!typeAdded)
            this.setSiblingRelation(instanceType, newInstance);

        return newInstance;
    }

    public List<Instance> getInstancesByInterface(Class iFace){
        List<Instance> instancesList = new ArrayList<>();

        for (int i = 0; i < this.lastAddedIndex; i++) {
            if(this.instances[i].isInterfaceImplemented(iFace)){
                instancesList.add(this.instances[i]);
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
            if(!newInstance.equals(i))
                newInstance.addSibling(i);
            if(!this.instances[Arrays.asList(this.instances).indexOf(i)].equals(newInstance))
                this.instances[Arrays.asList(this.instances).indexOf(i)].addSibling(newInstance);
            priority++;
        }

        newInstance.setPriority(priority-1);
    }

    public Instance getNextInstanceByIFaceType(List<Instance> instancesByIFace){
        Instance minInvokeCountInstance =
                this.container.find().in(instancesByIFace).by().minInvokeCount().instance();

        for (int i=0; i < instancesByIFace.size(); i++){
            Instance maxPriorityInstance =
                    this.container.find().in(instancesByIFace).by().priority(i).instance();

            if(minInvokeCountInstance.equals(maxPriorityInstance)){
                return maxPriorityInstance;
            }
        }

        return minInvokeCountInstance;
    }
}
