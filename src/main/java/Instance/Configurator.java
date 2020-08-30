package Instance;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Configurator {

    public static void updatePriority(ArrayList<Instance> siblings, int priority){

        int sibCount = siblings.size();

        for (int i = priority; i < sibCount; i++){
            int actualPriority = siblings.get(i).getPriority();
           siblings.get(i).setPriority(actualPriority+1);
        }
    }
}
