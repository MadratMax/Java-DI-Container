import java.util.ArrayList;
import java.util.List;

public class Siblings <Instance>{

    private List<Instance> siblings;

    public Siblings(){
        this.siblings = new ArrayList<Instance>();
    }

    public void addSibling(Instance siblingInstance){
        this.siblings.add(siblingInstance);
    }
}
