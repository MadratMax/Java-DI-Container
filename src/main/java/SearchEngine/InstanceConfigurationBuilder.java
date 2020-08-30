package SearchEngine;

import Instance.Instance;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class InstanceConfigurationBuilder {

    private final List<Instance> instances;
    private Instance instance;
    private final Stream<Object> stream;

    public InstanceConfigurationBuilder(List<Instance> instances){
        this.instances = instances;
        stream = Arrays.stream(this.instances.toArray());
    }

    public Instance getInstance(){
        return this.instance;
    }

    public InstanceConfigurationBuilder byTag(String tag){
        this.instance = (Instance) stream.filter(x -> ((Instance) x).getTag().equals(tag)).findFirst().orElse(null);
        return this;
    }

    public InstanceConfigurationBuilder byPriority(int priority){
        this.instance = (Instance) stream.filter(x -> ((Instance) x).getPriority() == priority).findFirst().orElse(null);
        return this;
    }

    public InstanceConfigurationBuilder byId(int id){
        this.instance = (Instance) stream.filter(x -> ((Instance) x).getId() == id).findFirst().orElse(null);
        return this;
    }

    public InstanceConfigurationBuilder byName(String name){
        this.instance = (Instance) stream.filter(x -> ((Instance) x).getName() == name).findFirst().orElse(null);
        return this;
    }
}
