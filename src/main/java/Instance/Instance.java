package Instance;

import SearchEngine.Find;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class Instance<T> {

    private int id;
    private boolean isInvoked;
    private int invokeCount;
    private Type type;
    private T coreInstance;
    private Siblings siblings;
    private int priority;
    private String date;
    private String tag;
    private Class[] implementedIFaces;

    public Instance(T coreInstance){
        if(coreInstance != null)
        {
            this.id = coreInstance.hashCode();
            this.type = coreInstance.getClass();
            this.siblings = new Siblings();
            this.date = new SimpleDateFormat("yyyyMMdd_HHmmssms").format(Calendar.getInstance().getTime());
            this.tag = Integer.toString(this.priority);
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

    public int getId(){
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

    public Instance setId(int id){
        this.id = id;
        return this;
    }

    public Instance setPriority(int priority){
        if(this.priority == priority)
            return this;

        this.priority = priority;

        int priorityExtender = this.siblings.getCount();
        Instance lastEmptyInstance = null;
        Instance samePriorityInstance = new Find().in(siblings.get()).by().priority(priority).instance();

        if(samePriorityInstance != null){
            while (samePriorityInstance.getPriority() == priority){
                lastEmptyInstance = new Find().in(siblings.get()).by().priority(priorityExtender).instance();

                if(lastEmptyInstance == null){
                    samePriorityInstance.setPriority(priorityExtender++);
                }
                priorityExtender++;
            }
        }

        return this;
    }

    public Instance setTag(String tag){
        this.tag = tag;
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

    private Class[] getImplementedInterfaces(){
        return this.coreInstance.getClass().getInterfaces();
    }
}
