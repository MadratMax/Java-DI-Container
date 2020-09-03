package PackageManager;

import SearchEngine.Find;

public class InstancePackageProvider {

    private PackageManager packageManager;
    private Find find;
    private Package pack;
    private CurrentPack current;

    public InstancePackageProvider(PackageManager packageManager, Find find){
        this.packageManager = packageManager;
        this.find = find;
        this.current = new CurrentPack();
        this.pack = new Package(this.current, this.find);
    }

    public Package getPackage(String tag) throws NullPointerException{
        this.current.setCurrent(this.packageManager.getPackage(tag));
        return pack;
    }
}