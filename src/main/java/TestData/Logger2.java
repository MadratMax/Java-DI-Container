package TestData;

public class Logger2 implements ITestIFace{
    private String message;

    public Logger2(String message){
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
