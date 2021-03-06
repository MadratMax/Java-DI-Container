package TestData;

public class Logger implements ILogger, ITestIFace{
    private String message;

    public Logger(String message){
        this.message = message;
    }

    @Override
    public String getName() {
        return this.message;
    }

    public void write(){
        System.out.println(this.message);
    }
}
