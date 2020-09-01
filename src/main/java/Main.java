import Container.Container;
import Container.IContainer;
import TestData.DifLogger;
import TestData.ILogger;
import TestData.Logger;
import Container.InstanceSupply;

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
        ILogger difL5 = new DifLogger("dif logger");

        IContainer container = new Container(6);
        //container.registerInstance(c).setTag("c").setPriority(2);
        container.registerInstance(difL5).setTag("difL5").setPriority(1);
        container.registerInstance(l1).setTag("l1").setPriority(0);
        container.registerInstance(l2).setTag("l2").setPriority(1);
        container.registerInstance(l3).setTag("l3").setPriority(2);
        container.registerInstance(l4).setTag("l4").setPriority(3);

        container.activateSiblings();
        //container.setDefaultInterface(ILogger.class);
        //ITestIFace testL1 = (ITestIFace) container.extract(ITestIFace.class);
        InstanceSupply<ILogger> logger = container.getSupply(ILogger.class);
//        ((ILogger) container.getInstance(ILogger.class)).write();
//        ((ILogger) container.getInstance(ILogger.class)).write();
//        ((ILogger) container.getInstance(ILogger.class)).write();
//        ((ILogger) container.getInstance(ILogger.class)).write();
        logger.get().write();
        logger.get().write();
        logger.get().write();
        logger.get().write();
        logger.get().write();
    }
}