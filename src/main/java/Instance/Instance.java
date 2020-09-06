package Instance;

import PackageManager.PackageManager;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;

public class Instance<T> {

    private PackageManager packageManager;
    private String id;
    private boolean isInvoked;
    private int invokeCount;
    private Type type;
    private T coreInstance;
    private Siblings siblings;
    private int priority;
    private String date;
    private String tag;
    private Class[] implementedIFaces;

    public Instance(T coreInstance, PackageManager packageManager){
        if(coreInstance != null)
        {
            this.packageManager = packageManager;
            this.id = UUID.randomUUID().toString();
            this.type = coreInstance.getClass();
            this.siblings = new Siblings();
            this.date = new SimpleDateFormat("yyyyMMdd_HHmmssms").format(Calendar.getInstance().getTime());
            this.coreInstance = coreInstance;
            this.implementedIFaces = this.getImplementedInterfaces();
        }
    }

    public String getName(){
        return this.coreInstance.getClass().toString();
    }

    public String getDate() {
        return this.date;
    }

    public String getTag() {
        return this.tag;
    }

    public int getPriority(){
        return this.priority;
    }

    public String getId(){
        return this.id;
    }

    public boolean isInvoked(){
        return this.isInvoked;
    }

    public int getInvokeCount(){
        return this.invokeCount;
    }

    public Type getType(){
        return this.type;
    }

    public T get(){
        this.invokeCount++;
        isInvoked = true;
        return this.coreInstance;
    }

    public Instance setId(String id){
        this.id = id;
        return this;
    }

    public Instance setPriority(int priority){
        if(this.priority == priority)
            return this;

        this.priority = priority;

        Integer priorityExtender = this.siblings.getCount();
        Instance lastEmptyInstance = null;
        //Instance samePriorityInstance = new Find().in(siblings.get()).by().priority(priority).instance();
        Instance samePriorityInstance =
                (Instance) siblings.get().stream().filter(x -> ((Instance) x).getPriority() == priority).findFirst().orElse(null);

        if(samePriorityInstance != null){
            while (samePriorityInstance.getPriority() == priority){
                Integer ext = priorityExtender;
                lastEmptyInstance = (Instance) siblings.get().stream().filter(x -> ((Instance) x).getPriority() == ext).findFirst().orElse(null);

                if(lastEmptyInstance == null){
                    samePriorityInstance.setPriority(priorityExtender++);
                }
                priorityExtender++;
            }
        }

        return this;
    }

    public Instance setTag(String tag){
        String oldTag = this.tag;
        this.tag = tag;
        this.packageManager.define(this, oldTag);
        return this;
    }

    public boolean isInterfaceImplemented(Class iFace) {
        return Arrays.stream(this.implementedIFaces).anyMatch(x -> x.equals(iFace));
    }

    public void addSibling(Instance siblingInstance){
        this.siblings.addSibling(siblingInstance);
    }

    public ArrayList<Instance> getSiblings(){
        return this.siblings.get();
    }

    public int getSiblingCount(){
        return this.siblings.getCount();
    }

    public Class[] getImplementedInterfaces(){
        return this.coreInstance.getClass().getInterfaces();
    }
}
