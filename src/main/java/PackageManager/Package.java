package PackageManager;

import SearchEngine.Find;

public class Package<T>{
    private Find find;
    private CurrentPack current;

    Package(CurrentPack current, Find find){
        this.current = current;
        this.find = find;
    }

    public T getInstance(Class<T> interfaceType){
        if(this.current == null){
            throw new NullPointerException("Package tag was not defined");
        }
        return (T) this.find.in(this.current.getCurrent().getInstanceList()).by().interfaceType(interfaceType).byHighPriority().instance().getCoreInstance();
    }

    public T getInstance(){
        if(this.current == null){
            throw new NullPointerException("Package tag was not defined");
        }
        return (T) this.find.in(this.current.getCurrent().getInstanceList()).by().highPriority().instance().getCoreInstance();
    }
}