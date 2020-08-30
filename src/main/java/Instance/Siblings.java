package Instance;

import java.util.ArrayList;
import java.util.List;

public class Siblings <Instance>{

    private List<Instance> siblings;
    private int count;

    public Siblings(){
        this.siblings = new ArrayList<Instance>();
    }

    public void addSibling(Instance siblingInstance){
        boolean added = this.siblings.add(siblingInstance);

        if(added)
            this.count++;
    }

    public int getCount(){
        return this.count;
    }
}
