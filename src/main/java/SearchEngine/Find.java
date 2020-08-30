package SearchEngine;

import Instance.Instance;

import java.util.List;

public class Find {

    public static By in(List<Instance> instances){
        return new By(new InstanceConfigurationBuilder(instances));
    }
}
