import Container.Container;
import Container.IContainer;
import Container.InstanceSupply;
import TestData.*;
import Instance.*;

public class Main<T> {

    public static void main(String[] args) {

        containerExamples();
    }

    private static void containerExamples() {
        Container c = new Container(1);
        ILogger l1 = new Logger("qa l1");
        ILogger l2 = new Logger("qa l2");
        ILogger l3 = new DifLogger("dev l3");
        ILogger l4 = new DifLogger("dev l4");
        ILogger l5 = new Logger("prod l5");
        NoIFaceClass noIface = new NoIFaceClass("noIFace");
        ILogger difL5 = new DifLogger("prod difL5");
        ITestIFace logger2 = new Logger2("prod logger2");
        ITestIFace logger22 = new Logger2("prod logger22");

        IContainer container = new Container(11);
        container.registerInstance(difL5).setTag("prod").setPriority(1);
        container.registerInstance(l1).setTag("qa");
        container.registerInstance(l2).setTag("qa");
        container.registerInstance(l3).setTag("dev");
        container.registerInstance(l4).setTag("dev");
        container.registerInstance(l5).setTag("prod");
        container.registerInstance(noIface).setTag("noIFace");
        Instance logger22Instance = container.registerInstance(logger22).setTag("prod");
        Instance logger2Instance = container.registerInstance(logger2).setTag("prod").setPriority(0);

        ((ITestIFace) container.packageProvider().getPackage("QA").getInstance(ITestIFace.class)).write();
        ((ILogger) container.packageProvider().getPackage("dev").getInstance()).write();
        ((NoIFaceClass) container.packageProvider().getPackage("noIFace").getInstance()).write();
        ((ITestIFace) container.packageProvider().getPackage("QA").getInstance(ITestIFace.class)).write();
        ((ITestIFace) container.packageProvider().getPackage("PROD").getInstance(ITestIFace.class)).write();

        logger2Instance.setTag("qa");
        ((ITestIFace) container.packageProvider().getPackage("PROD").getInstance(ITestIFace.class)).write();

        logger22Instance.setTag("qa");
        ((ITestIFace) container.packageProvider().getPackage("PROD").getInstance(ITestIFace.class)).write();

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