package PackageManager;

public class CurrentPack {

    private InstancePackage current;

    public void setCurrent(InstancePackage pack){
        this.current = pack;
    }

    public InstancePackage getCurrent(){
        return this.current;
    }
}