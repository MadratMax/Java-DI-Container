package Instance;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
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

    public Instance(T coreInstance){
        if(coreInstance != null)
        {
            this.id = coreInstance.hashCode();
            this.type = coreInstance.getClass();
            this.siblings = new Siblings();
            this.date = new SimpleDateFormat("yyyyMMdd_HHmmssms").format(Calendar.getInstance().getTime());
            this.tag = Integer.toString(this.priority);
        }

        this.coreInstance = coreInstance;
    }

    public String getName(){
        return this.coreInstance.getClass().toString();
    }

    public String getDate() {
        return this.date;
    }

    public String getTag() {
        if(this.priority == 0)
            return "major";

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

    public void setId(int id){
        this.id = id;
    }

    public void setPriority(int priority){
        this.priority = priority;
        setTag(Integer.toString(this.priority));
    }

    public void setTag(String tag){
        this.tag = tag;
    }

    public boolean isImplementsInterface(Class iFace) {

        Class interfaceImplemented = this.coreInstance.getClass().getInterfaces()[0];
        return interfaceImplemented.equals(iFace);
    }

    public void addSibling(Instance siblingInstance){
        this.siblings.addSibling(siblingInstance);
    }

    public Siblings getSiblings(){
        return this.siblings;
    }
}
