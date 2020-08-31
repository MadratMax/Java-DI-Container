import Container.Container;
import Container.IContainer;
import TestData.DifLogger;
import TestData.ILogger;
import TestData.ITestIFace;
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
        container.registerInstance(c).setTag("c");;
        container.registerInstance(l1).setTag("l1");;
        container.registerInstance(l2).setTag("l2");;
        container.registerInstance(l4).setTag("l4");
        container.registerInstance(difL5).setTag("difL5");
        container.registerInstance(l1).setTag("l1");;

        container.find().in(container.getInstancesByInterface(ITestIFace.class)).by().highPriority().instance().setPriority(5);
        container.find().in(container.getInstancesByInterface(ILogger.class)).by().priority(1).instance().setPriority(0);





        container.find().in(container.getInstancesByInterface(ILogger.class)).by().highPriority().instance().setPriority(33);
        container.find().in(container.getInstancesByInterface(ILogger.class)).by().tag("l4").instance().setPriority(44);

        ITestIFace l = (ITestIFace) container.extract(ITestIFace.class);

        l.write();

        container.dispose();
        ArrayList<ILogger> all = (ArrayList) container.extractAll(ILogger.class);
        for (ILogger lw :
                all) {
            lw.write();
        }

        container.registerInstance(difL5).setTag("difL5");
        ILogger newLogger = (ILogger) container.extract(ILogger.class);

        newLogger.write();
    }
}