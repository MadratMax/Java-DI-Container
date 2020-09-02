import Container.Container;
import Container.IContainer;
import Instance.Instance;
import TestData.DifLogger;
import TestData.ILogger;
import TestData.Logger;
import Container.InstanceSupply;
import TestData.NoIFaceClass;

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
        ILogger l5 = new Logger("5 logger");
        NoIFaceClass noIface = new NoIFaceClass("noIFace");
        ILogger difL5 = new DifLogger("dif logger");

        IContainer container = new Container(6);
        //container.registerInstance(c).setTag("c").setPriority(2);
        //container.registerInstance(difL5).setTag("difL5").setPriority(1);
        container.registerInstance(l1).setTag("l1");//.setPriority(0);//.setPriority(4);//.setPriority(4);//.Priority(4);
        container.registerInstance(l2).setTag("l2");//.setPriority(0);//.setPriority(3);//.setPriority(3);
        container.registerInstance(l3).setTag("l3");//.setPriority(0);//.setPriority(0);
        container.registerInstance(l4).setTag("l4");//.setPriority(0);//.Priority(5);
        container.registerInstance(l5).setTag("l5");//setPriority(0);//.Priority(6);
        container.registerInstance(noIface).setTag("noIFace");//setPriority(0);//.Priority(6);

        //((NoIFaceClass) container.getInstance(NoIFaceClass.class)).write();
        //container.activateSiblings();
        //container.setDefaultInterface(ILogger.class);
        //ITestIFace testL1 = (ITestIFace) container.extract(ITestIFace.class);
        InstanceSupply<NoIFaceClass> logger = container.getSupply(NoIFaceClass.class);
//        ((ILogger) container.getInstance(ILogger.class)).write();
//        ((ILogger) container.getInstance(ILogger.class)).write();
//        ((ILogger) container.getInstance(ILogger.class)).write();
//        ((ILogger) container.getInstance(ILogger.class)).write();
//        ((ILogger) container.getInstance(ILogger.class)).write();
//        ((ILogger) container.getInstance(ILogger.class)).write();
//        ((ILogger) container.getInstance(ILogger.class)).write();
//        ((ILogger) container.getInstance(ILogger.class)).write();
//        ((ILogger) container.getInstance(ILogger.class)).write();
//        ((ILogger) container.getInstance(ILogger.class)).write();
        logger.get().write();
//        logger.get().write();
//        logger.get().write();
//        logger.get().write();
//        logger.get().write();
    }
}