import testPackage.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static testPackage.Test.printTest;

public class Main {

    public static void main(String[] args) {
	    LoggerTest loggerTest1 = new LoggerTest();
        List<String> logger = new ArrayList<String>();
        LoggerTest loggerTest2 = new LoggerTest();

        Container container = new Container<Object>(4);
        container.addInstance(loggerTest1);
        container.addInstance(logger);
        container.addInstance(loggerTest2);
        container.addInstance(loggerTest2);
    }
}
