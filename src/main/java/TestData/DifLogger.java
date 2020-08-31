package TestData;

public class DifLogger implements ILogger {

    private String message;

    public DifLogger(String message){
        this.message = message;
    }

    @Override
    public String getName() {
        return this.message;
    }

    @Override
    public void write() {
        System.out.println(this.message);
    }
}
