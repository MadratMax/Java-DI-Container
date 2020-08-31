import Container.Container;
import Container.IContainer;
import SearchEngine.Find;
import TestData.DifLogger;
import TestData.ILogger;
import TestData.Logger;
import java.util.ArrayList;

public class Main<T> {

    public static void main(String[] args) {

        containerExamples();
    }

    private static void containerExamples() {
        Container c = new Container(1);
        ILogger l1 = new Logger("1 logger");
        ILogger l2 = new Logger("2 logger");
        ILogger l3 = new Logger("3 logger");
        ILogger l4 = new Logger("4 logger");
        ILogger difL5 = new DifLogger();

        IContainer container = new Container(6);
        //container.registerInstance(c);
        container.registerInstance(l2);
        container.registerInstance(l3);
        //container.registerInstance(l4);
        container.registerInstance(difL5);
        //container.registerInstance(l1);

        Find.in(container.getInstancesByInterface(ILogger.class)).by().highPriority().instance().setPriority(5);
        Find.in(container.getInstancesByInterface(ILogger.class)).by().priority(1).instance().setPriority(0);

        ArrayList<ILogger> all = (ArrayList) container.extractAll(ILogger.class);

        for (ILogger l :
                all) {
            l.write();
        }

        //ILogger l = (ILogger) container.extract(ILogger.class);

        //l.write();
    }


}