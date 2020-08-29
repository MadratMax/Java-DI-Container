package Instance;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Instance<T> {

    private int id;
    private boolean isInvoked;
    private int invokeCount;
    private Type type;
    private T instance;
    private Siblings siblings;
    private int priority;
    private String date;

    public Instance(T instance){
        if(instance != null)
        {
            this.id = instance.hashCode();
            this.type = instance.getClass();
            this.siblings = new Siblings();
            this.date = new SimpleDateFormat("yyyyMMdd_HHmmssms").format(Calendar.getInstance().getTime());
        }

        this.instance = instance;
    }

    public String getName(){
        return this.instance.getClass().toString();
    }

    public String getDate() {
        return this.date;
    }

    public int getId(){
        return this.id;
    }

    public boolean isInvoked(){
        this.invokeCount++;
        return this.isInvoked;
    }

    public int getInvokeCount(){
        return this.invokeCount;
    }

    public Type getType(){
        return this.type;
    }

    public T getInstance(){
        return this.instance;
    }

    public void setId(int id){
        this.id = id;
    }

    public void addSibling(Instance siblingInstance){
        this.siblings.addSibling(siblingInstance);
    }

    public void setPriority(int priority){
        this.priority = priority;
    }

    public int getPriority(){
        return this.priority;
    }

    public boolean isExtends(Class requiredClass) {

        Class interfaceImplemented = this.instance.getClass().getInterfaces()[0];

            try {
                return interfaceImplemented.equals(requiredClass);
            } catch(Exception e) {}

        return false;
    }
}
