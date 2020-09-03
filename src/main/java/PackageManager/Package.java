package PackageManager;

import Instance.Instance;
import SearchEngine.Find;

public class Package<T>{
    private Find find;
    private CurrentPack current;

    Package(CurrentPack current, Find find){
        this.current = current;
        this.find = find;
    }

    public Instance getInstance(Class<T> classType){
        if(this.current == null){
            throw new NullPointerException("Package tag was not defined");
        }
        return this.find.in(this.current.getCurrent().getInstanceList()).by().type(classType).byHighPriority().instance();
    }
}