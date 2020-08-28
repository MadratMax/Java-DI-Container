import java.lang.reflect.Type;

public class Instance<T> {

    private int id;
    private boolean isInvoked;
    private int invokeCount;
    private Type type;
    private T instance;
    private Siblings siblings;
    private int priority;

    public Instance(T instance){
        if(instance != null)
        {
            this.id = instance.hashCode();
            this.type = instance.getClass();
            this.siblings = new Siblings();
        }

        this.instance = instance;
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

    public void decreasePriority(){
        this.priority++;
    }
}
