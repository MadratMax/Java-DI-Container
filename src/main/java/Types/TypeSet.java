package Types;

import Instance.Instance;

import java.lang.reflect.Type;
import java.util.*;

public class TypeSet{

    private Map<Type, List<Instance>> typeDict;

    public TypeSet(){
        this.typeDict = new HashMap<Type, List<Instance>>();
    }

    public boolean addType(Type type, Instance instance){
        if(!typeDict.keySet().contains(type))
        {
            if(this.typeDict.get(type) == null)
            {
                List<Instance> newSet = new ArrayList<Instance>();
                newSet.add(instance);
                this.typeDict.put(type, newSet);
            }

            return true;
        }

        this.typeDict.get(type).add(instance);

        return false;
    }

    public List<Instance> getInstancesByType(Type type){

        return this.typeDict.get(type);
    }
}
