package SearchEngine;

import Instance.Instance;

import java.util.List;

public class Find {

    public Find(){

    }

    public By in(List<Instance> instances){
        return new By(new InstanceConfigurationBuilder(instances));
    }
}
