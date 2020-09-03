package SearchEngine;

import Instance.*;

import java.util.List;

public class Find {

    private final InstanceManager instanceManager;

    public Find(InstanceManager instanceManager){
        this.instanceManager = instanceManager;
    }

    public By in(List<Instance> instances){
        InstanceConfigurationBuilder builder = new InstanceConfigurationBuilder(instances, this.instanceManager);
        return new By(builder);
    }
}
