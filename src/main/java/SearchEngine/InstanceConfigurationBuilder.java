package SearchEngine;

import Instance.Instance;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class InstanceConfigurationBuilder {

    private final List<Instance> instances;
    private Instance _instance;
    private final Stream<Object> stream;
    private int instanceCount;

    public InstanceConfigurationBuilder(List<Instance> instances){
        this.instances = instances;
        stream = Arrays.stream(this.instances.toArray());
        this.instanceCount = this.instances.toArray().length;
    }

    public Instance instance(){
        return this._instance;
    }

    public InstanceConfigurationBuilder byTag(String tag){
        this._instance = (Instance) stream.filter(x -> ((Instance) x).getTag().equals(tag)).findFirst().orElse(null);
        return this;
    }

    public InstanceConfigurationBuilder byPriority(int priority){
        this._instance = (Instance) stream.filter(x -> ((Instance) x).getPriority() == priority).findFirst().orElse(null);
        return this;
    }

    public InstanceConfigurationBuilder byId(int id){
        this._instance = (Instance) stream.filter(x -> ((Instance) x).getId() == id).findFirst().orElse(null);
        return this;
    }

    public InstanceConfigurationBuilder byName(String name){
        this._instance = (Instance) stream.filter(x -> ((Instance) x).getName().equals(name)).findFirst().orElse(null);
        return this;
    }

    public InstanceConfigurationBuilder byType(Type type){
        this._instance = (Instance) stream.filter(x -> ((Instance) x).getType().equals(type)).findFirst().orElse(null);
        return this;
    }

    public InstanceConfigurationBuilder byHighPriority(){
        this._instance =
                this.instances.stream().min(Comparator.comparing(Instance::getPriority)).orElse(null);
        return this;
    }

    public InstanceConfigurationBuilder byMinInvokeCount(){
        this._instance =
                this.instances.stream().min(Comparator.comparing(Instance::getInvokeCount)).orElse(null);
        return this;
    }
}