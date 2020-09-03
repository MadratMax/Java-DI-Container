package PackageManager;

import Instance.Instance;
import java.util.ArrayList;
import java.util.Arrays;

public class InstancePackage<T> {

    private String tag;
    private Integer packageSize;
    private ArrayList<Instance> instanceList;
    private ArrayList<Class<T>> interfaceList;

    public InstancePackage(String tag){
        this.tag = tag;
        this.instanceList = new ArrayList<Instance>();
        this.interfaceList = new ArrayList<Class<T>>();
        this.packageSize = 0;
    }

    public String getTag(){
        return this.tag;
    }

    public Integer getPackageSize(){
        return this.packageSize;
    }

    public ArrayList<Class<T>> getPackageInterfaces(){
        return this.interfaceList;
    }

    public ArrayList<Instance> getInstanceList(){
        return this.instanceList;
    }

    public void add(Instance instance){
        this.instanceList.add(instance);
        Class[] iFaces = instance.getImplementedInterfaces();
        for (Class c :
                iFaces) {
            if(!this.interfaceList.contains(c)){
                this.interfaceList.add(c);
            }
        }

        this.tag = instance.getTag();
        this.packageSize++;
    }
}
