package TestData;

public class Logger implements ILogger
{
    private String message;

    public Logger(String message){
        this.message = message;
    }

    public void write(){
        System.out.println(this.message);
    }
}
