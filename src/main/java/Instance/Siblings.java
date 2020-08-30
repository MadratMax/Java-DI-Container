package Instance;

import java.util.ArrayList;
import java.util.List;

public class Siblings{

    private ArrayList<Instance> siblings;
    private int count;

    public Siblings(){
        this.siblings = new ArrayList<>();
    }

    public void addSibling(Instance siblingInstance){
        boolean added = this.siblings.add(siblingInstance);

        if(added)
            this.count++;
    }

    public ArrayList<Instance> get(){
        return this.siblings;
    }

    public int getCount(){
        return this.count;
    }

    public void updatePriority(int priority){
        Configurator.updatePriority(this.siblings, priority);
    }
}
