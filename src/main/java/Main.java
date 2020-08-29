import Container.Container;
import Instance.Instance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main<T> {

    public static void main(String[] args) {

        Container c = new Container(1);
        List<String> i1 = new ArrayList<String>();
        Map<String, String> i2 = new HashMap<String, String>();
        List<String> i3 = new ArrayList<String>();

        Container container = new Container<Object>(6);
        container.addInstance(i1);
        container.addInstance(i2);
        container.addInstance(i3);

        container.addInstance(c);
        container.addInstance(c);
        container.addInstance(c);
        Class iFace = c.getClass().getInterfaces()[0];
        List<Instance> instancesByInterface = container.getInstancesByInterface(iFace);

        for (Instance i :
                instancesByInterface) {
            System.out.println(i.getName() + " priority: " + i.getPriority() + " date: " + i.getDate());
        }

        List<Instance> instancesByClass = container.getInstancesByClassName(i3.getClass());

        for (Instance i :
                instancesByClass) {
            System.out.println(i.getName() + " priority: " + i.getPriority() + " date: " + i.getDate());
        }
    }
}
