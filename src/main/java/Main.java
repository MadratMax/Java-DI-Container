import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        List<String> i1 = new ArrayList<String>();
        Map<String, String> i2 = new HashMap<String, String>();
        List<String> i3 = new ArrayList<String>();

        Container container = new Container<Object>(3);
        container.addInstance(i1);
        container.addInstance(i2);
        container.addInstance(i3);
    }
}