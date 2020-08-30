package TestData;

public class DifLogger implements ILogger {

    @Override
    public void write() {
        System.out.println("dif logger");
    }
}
