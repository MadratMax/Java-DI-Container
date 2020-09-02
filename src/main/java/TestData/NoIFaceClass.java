package TestData;

public class NoIFaceClass {

    private final String message;

    public NoIFaceClass(String message){
        this.message = message;
    }

    public String getName() {
        return this.message;
    }

    public void write(){
        System.out.println(this.message);
    }
}
