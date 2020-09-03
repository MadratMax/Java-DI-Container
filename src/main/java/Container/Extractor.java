package Container;

import Instance.Instance;
import Instance.InstanceManager;

import java.util.List;
import java.util.function.Supplier;

public class Extractor <T> implements IExtractor<T>{
    private InstanceManager instanceManager;
    private Class<T> defaultIFace;

    public Extractor(InstanceManager instanceManager){
        this.instanceManager = instanceManager;
    }

    public InstanceSupply<T> getSupplier(){
        return new InstanceSupply((this.getSupplier(this.getInstancesByInterface(this.defaultIFace))));
    }

    public InstanceSupply<T> getSupplier(Class<T> iFaceType){
        return new InstanceSupply(this.getSupplier(this.getInstancesByInterface(iFaceType)));
    }

    public T extract(Class<T> iFaceType){
        return this.getNextInstance(this.getInstancesByInterface(iFaceType));
    }

    @Override
    public List<Instance> getInstancesByInterface(Class iFace){
        return this.instanceManager.getInstancesByInterface(iFace);
    }

    @Override
    public List<Instance> getInstancesByClass(Class instanceClass){
        return this.instanceManager.getInstancesByClassName(instanceClass);
    }

    public void dispose(){
        this.defaultIFace = null;
        this.instanceManager = null;
    }

    private Supplier<T> getSupplier(List<Instance> instancesByIFace){
        return (() -> (T)this.getNextInstance(instancesByIFace));
    }

    private T getNextInstance(List<Instance> instancesByIFace) {
        return (T) this.instanceManager.getNextInstanceByIFaceType(instancesByIFace).get();
    }
}
