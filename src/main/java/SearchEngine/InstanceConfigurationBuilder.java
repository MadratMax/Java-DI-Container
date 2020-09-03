package SearchEngine;

import Instance.Instance;
import Instance.InstanceManager;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InstanceConfigurationBuilder<T> {

    private List<Instance> instances;
    private Instance _instance;
    private InstanceManager instanceManager;
    private final Stream<Object> stream;
    private int instanceCount;

    public InstanceConfigurationBuilder(List<Instance> instances, InstanceManager instanceManager){
        this.instances = instances;
        stream = Arrays.stream(this.instances.toArray());
        this.instanceCount = this.instances.toArray().length;
        this.instanceManager = instanceManager;
    }

    public Instance instance(){
        return this.instanceManager.getInstance();
    }

    public InstanceConfigurationBuilder byTag(String tag){
        this._instance = (Instance) stream.filter(x -> ((Instance) x).getTag() == tag).findFirst().orElse(null);
        this.instanceManager.setCurrent(this._instance);
        return this;
    }

    public InstanceConfigurationBuilder byPriority(int priority){
        this._instance = (Instance) stream.filter(x -> ((Instance) x).getPriority() == priority).findFirst().orElse(null);
        this.instanceManager.setCurrent(this._instance);
        return this;
    }

    public InstanceConfigurationBuilder byId(String id){
        this._instance = (Instance) stream.filter(x -> ((Instance) x).getId() == id).findFirst().orElse(null);
        this.instanceManager.setCurrent(this._instance);
        return this;
    }

    public InstanceConfigurationBuilder byName(String name){
        this._instance = (Instance) stream.filter(x -> ((Instance) x).getName() == name).findFirst().orElse(null);
        this.instanceManager.setCurrent(this._instance);
        return this;
    }

    public InstanceConfigurationBuilder byType(Class<T> classType){
        Predicate<Instance> filterPredicate = item -> ((Instance) item).getType().equals(classType);

        List<Instance> filteredPOJOs = this.instances.stream()
                .filter(filterPredicate)
                .collect(Collectors.toList());

        this.instances = filteredPOJOs;
        this._instance = (Instance) stream.filter(x -> ((Instance) x).getType().equals(classType)).findAny().orElse(null);
        this.instanceManager.setCurrent(this._instance);
        return this;
    }

    public InstanceConfigurationBuilder byInterface(Class<T> iFaceType){
        Predicate<Instance> filterPredicate = item -> ((Instance) item).isInterfaceImplemented(iFaceType);

        this.instances = this.instances.stream()
                .filter(filterPredicate)
                .collect(Collectors.toList());
        this._instance = (Instance) stream.filter(x -> ((Instance) x).isInterfaceImplemented(iFaceType)).findAny().orElse(null);
        this.instanceManager.setCurrent(this._instance);
        return this;
    }

    public InstanceConfigurationBuilder byLowPriority(){
        this._instance =
                this.instances.stream().max(Comparator.comparing(Instance::getPriority)).orElse(null);
        this.instanceManager.setCurrent(this._instance);
        return this;
    }

    public InstanceConfigurationBuilder byHighPriority(){
        this._instance =
                this.instances.stream().min(Comparator.comparing(Instance::getPriority)).orElse(null);
        this.instanceManager.setCurrent(this._instance);
        return this;
    }

    public InstanceConfigurationBuilder byMinInvokeCount(){
        this._instance =
                this.instances.stream().min(Comparator.comparing(Instance::getInvokeCount)).orElse(null);
        this.instanceManager.setCurrent(this._instance);
        return this;
    }
}