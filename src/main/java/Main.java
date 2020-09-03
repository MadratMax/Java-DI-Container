import Container.Container;
import Container.IContainer;
import Instance.*;
import TestData.*;
import Container.InstanceSupply;

public class Main<T> {

    public static void main(String[] args) {

        containerExamples();
    }

    private static void containerExamples() {
        Container c = new Container(1);
        ILogger l1 = new Logger("1 logger");
        ILogger l2 = new Logger("2 logger");
        ILogger l3 = new DifLogger("3 logger");
        ILogger l4 = new DifLogger("4 logger");
        ILogger l5 = new Logger("5 logger");
        NoIFaceClass noIface = new NoIFaceClass("noIFace");
        ILogger difL5 = new DifLogger("dif logger");
        ITestIFace logger2 = new Logger2("new logger");
        ITestIFace logger22 = new Logger2("new logger22");

        IContainer container = new Container(11);
        //container.registerInstance(c).setTag("c").setPriority(2);
        container.registerInstance(difL5).setTag("difL5").setPriority(1);
        container.registerInstance(l1).setTag("DEV");//.setPriority(0);//.setPriority(4);//.setPriority(4);//.Priority(4);
        container.registerInstance(l2).setTag("DEV");//.setPriority(0);//.setPriority(3);//.setPriority(3);
        container.registerInstance(l3).setTag("QA");//.setPriority(0);//.setPriority(0);
        container.registerInstance(l4).setTag("QA").setPriority(0);//.setPriority(0);//.Priority(5);
        container.registerInstance(l5).setTag("l5");//setPriority(0);//.Priority(6);
        container.registerInstance(noIface).setTag("noIFace");//setPriority(0);//.Priority(6);
        InstanceManager ll22 = container.registerInstance(logger22).setTag("QA");//setPriority(0);//.Priority(6);
        container.registerInstance(logger2).setTag("QA").setPriority(0);//.Priority(6);


        ((ITestIFace) container.packageProvider().getPackage("QA").getInstance(ITestIFace.class)).write();
        ((ILogger) container.packageProvider().getPackage("dev").getInstance()).write();
        ((NoIFaceClass) container.packageProvider().getPackage("noIFace").getInstance()).write();

        ll22.setTag("33");
        ((ITestIFace) container.packageProvider().getPackage("QA").getInstance(ITestIFace.class)).write();

        //((NoIFaceClass) container.getInstance(NoIFaceClass.class)).write();
        //container.activateSiblings();
        //container.setDefaultInterface(ILogger.class);
        //ITestIFace testL1 = (ITestIFace) container.extract(ITestIFace.class);
        InstanceSupply<ILogger> logger = container.getSupply(ILogger.class);
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
        //logger.get().write();
//        logger.get().write();
//        logger.get().write();
//        logger.get().write();
//        logger.get().write();
    }
}