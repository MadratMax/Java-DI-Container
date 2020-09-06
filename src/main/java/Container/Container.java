package Container;

import Instance.Instance;
import Instance.InstanceManager;
import PackageManager.InstancePackageProvider;
import SearchEngine.Find;

import java.util.ArrayList;
import java.util.List;

public class Container <T> implements IContainer<T> {

    private int containerSize;
    private int instancesAdded;
    private InstanceManager<T> instanceManager;
    private IExtractor<T> extractor;
    private boolean activatedSiblings;
    private Class<T> defaultIFace;

    public Container(int size){
        this.containerSize = size;
        this.instanceManager = new InstanceManager<T>(this);
        this.extractor = new Extractor<T>(this.instanceManager);
    }

    @Override
    public void setDefaultInterface(Class<T> interfaceType) {
        this.defaultIFace = interfaceType;
    }

    @Override
    public void activateSiblings(){
        this.activatedSiblings = true;
    }

    @Override
    public void deactivateSiblings(){
        this.activatedSiblings = false;
    }

    @Override
    public int getSize(){
        return this.containerSize;
    }

    @Override
    public Instance registerInstance(T instance){
        if(this.instancesAdded == this.containerSize)
            throw new ArrayIndexOutOfBoundsException("Exceeded Container size: " + this.containerSize);

        instancesAdded++;
        return this.instanceManager.addInstance(instance);
    }

    @Override
    public InstanceSupply<T> getSupply() {
        if(defaultIFace == null)
            throw new NullPointerException("No default interface type provided");

        return this.extractor.getSupplier();
    }

    @Override
    public InstanceSupply<T> getSupply(Class<T> iFaceType) {
        return this.extractor.getSupplier(iFaceType);
    }

    @Override
    public T getInstance(Class<T> interfaceType){
        if(this.activatedSiblings){
            return this.extractor.extract(interfaceType);
        }

        Instance i =
                (Instance) this.find()
                        .in(this.extractor.getInstancesByInterface(interfaceType))
                        .by()
                        .highPriority()
                        .instance();

        if(i == null)
            throw new NullPointerException(
                    "Failed to extract instance that implements " + interfaceType.toString() + ".");

        return (T) i.getCoreInstance();
    }

    @Override
    public ArrayList<T> getAllInstances(Class<T> interfaceType){
        ArrayList<T> coreInstanceList = new ArrayList<T>();
        List<Instance> instancesByIFace = this.extractor.getInstancesByInterface(interfaceType);

        for (Instance i :
                instancesByIFace) {
            coreInstanceList.add((T) i.getCoreInstance());
        }

        return coreInstanceList;
    }

    @Override
    public List<Instance> getInstancesByInterface(Class superClass) {
        return this.extractor.getInstancesByInterface(superClass);
    }

    @Override
    public List<Instance> getInstancesByClass(Class instanceClass) {
        return this.extractor.getInstancesByClass(instanceClass);
    }

    @Override
    public Find find(){
        return this.instanceManager.find();
    }

    @Override
    public void dispose(){
        this.instanceManager = null;
        this.extractor.dispose();
        this.instancesAdded = 0;
        this.instanceManager = new InstanceManager<T>(this);
        this.extractor = new Extractor<T>(this.instanceManager);
    }

    @Override
    public InstancePackageProvider packageProvider() {
        return this.instanceManager.getPackageProvider();
    }
}