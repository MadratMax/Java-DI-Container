package PackageManager;

import Instance.Instance;

import java.util.ArrayList;
import java.util.Arrays;

public class PackageManager {

    private InstancePackage[] instancePackages;
    private Integer addedPackages;
    private ArrayList<String> ids;
    private ArrayList<String> tags;

    public PackageManager(Integer size){
        this.instancePackages = new InstancePackage[size];
        this.ids = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.addedPackages = 0;
    }

    public String define(Instance instance, String oldTag){
        if(!this.ids.contains(instance.getId())){
            this.ids.add(instance.getId());

            if(!this.tags.contains(instance.getTag())){
                this.createPackage(instance.getTag(), instance);
            }
            else {
                 this.findPackageByTag(instance.getTag()).add(instance);
            }
        }
        else{
            this.findPackageByTag(oldTag).remove(instance);
            this.createPackage(instance.getTag(), instance);
        }

        return instance.getTag();
    }

    public InstancePackage getPackage(String tag){
        return this.findPackageByTag(tag);
    }

    private void createPackage(String tag, Instance instance){
        this.addFirstInstanceToPackage(instance, tag);
        this.tags.add(tag);
    }

    private void addFirstInstanceToPackage(Instance instance, String tag){
        InstancePackage newPack = new InstancePackage(tag);
        newPack.add(instance);
        this.instancePackages[addedPackages] = newPack;
        addedPackages++;
    }

    private InstancePackage findPackageByTag(String tag){
        return ((InstancePackage) Arrays.stream(this.instancePackages).filter(x -> x.getTag().toLowerCase().equals(tag.toLowerCase())).findFirst().orElse(null));
    }
}
