package Instance;

import Container.IContainer;
import PackageManager.InstancePackageProvider;
import PackageManager.PackageManager;
import SearchEngine.Find;
import Types.TypeSet;

import java.lang.reflect.Type;
import java.util.*;

public class InstanceManager <T> {

    private final IContainer container;
    private final PackageManager packageManager;
    private InstancePackageProvider packageProvider;
    private Find find;
    private final Instance[] instances;
    private final TypeSet typeSet;
    private int lastAddedIndex;
    private Instance current;

    public InstanceManager(
            IContainer container){
        this.container = container;
        this.find = new Find(this);
        this.packageManager = new PackageManager(this.container.getSize());
        this.packageProvider = new InstancePackageProvider(this.packageManager, this.find);
        this.typeSet = new TypeSet();
        this.instances = new Instance[this.container.getSize()];
    }

    public InstancePackageProvider getPackageProvider(){
        return this.packageProvider;
    }

    public Instance instance() throws NullPointerException{
        return this.current;
    }

    public InstanceManager addInstance(T instance){
        Instance<T> newInstance = new Instance<T>(instance);
        this.instances[lastAddedIndex] = newInstance;
        Type instanceType = this.instances[lastAddedIndex].getType();
        boolean typeAdded = this.typeSet.addType(instanceType, newInstance);
        lastAddedIndex++;

        if(!typeAdded)
            this.setSiblingRelation(instanceType, newInstance);
        this.current = newInstance;
        return this;
    }

    public InstanceManager setCurrent(Instance instance){
        this.current = instance;
        return this;
    }

    public Instance getInstance(){
        return this.current;
    }

    public String getTag(){
        return this.current.getTag();
    }

    public InstanceManager setTag(String tag){
        this.current.setTag(tag);
        this.packageManager.define(this.current);
        return this;
    }

    public int getPriority(){
        return this.current.getPriority();
    }

    public InstanceManager setPriority(int priority){
        this.current.setPriority(priority);
        return this;
    }

    public String getId(){
        return this.current.getId();
    }

    public InstanceManager setId(String id){
        this.current.setId(id);
        return this;
    }

    public Find find(){
        return this.find;
    }

    public List<Instance> getInstancesByInterface(Class iFace){
        List<Instance> instancesList = new ArrayList<>();

        for (int i = 0; i < this.lastAddedIndex; i++) {
            if(this.instances[i].isInterfaceImplemented(iFace)){
                instancesList.add(this.instances[i]);
            }
        }

        if(instancesList.size() == 0){
            throw new NullPointerException("No elements found with interface " + iFace.toString());
        }

        return instancesList;
    }

    public List<Instance> getInstancesByClassName(Class instanceClass){
        return this.typeSet.getInstancesByType(instanceClass);
    }

    public Instance getNextInstanceByIFaceType(List<Instance> instancesByIFace){
        Instance minInvokeCountInstance =
                this.find().in(instancesByIFace).by().minInvokeCount().instance();

        ArrayList<Instance> maxPriorityInstances = new ArrayList<Instance>();

        int minP = instancesByIFace.stream().max(Comparator.comparing(Instance::getPriority)).get().getPriority();

        for (int i=0; i <= minP; i++){
            Instance maxPriorityInstance =
                    this.find().in(instancesByIFace).by().priority(i).instance();

            if(maxPriorityInstance != null){
                maxPriorityInstances.add(maxPriorityInstance);
            }
        }

        Instance i = maxPriorityInstances.stream().min(Comparator.comparing(Instance::getInvokeCount)).get();
        return i;
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

        int minPriority = instances.stream().max(Comparator.comparing(Instance::getPriority)).get().getPriority();
        newInstance.setPriority(minPriority+1);
    }
}
