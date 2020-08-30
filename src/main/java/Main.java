import Container.Container;
import Container.IContainer;
import SearchEngine.Find;
import TestData.DifLogger;
import TestData.ILogger;
import TestData.Logger;
import Instance.*;

import java.util.List;

public class Main<T> {

    public static void main(String[] args) {

        containerExamples();
    }

    private static void containerExamples() {
        ILogger l1 = new Logger("1 logger");
        ILogger l2 = new Logger("2 logger");
        ILogger l3 = new Logger("3 logger");
        ILogger l4 = new Logger("4 logger");
        ILogger difL5 = new DifLogger();

        IContainer container = new Container(5);
        container.registerInstance(l2);
        container.registerInstance(l3);
        container.registerInstance(l4);
        container.registerInstance(difL5);
        container.registerInstance(l1);

        Instance i = Find.in(container.getInstancesByInterface(ILogger.class)).by().highPriority().instance();
        //i.setPriority(5);

        List<ILogger> all = container.extractAll(ILogger.class);

        Logger l = (Logger) container.extract(ILogger.class);

        l.write();
    }


}