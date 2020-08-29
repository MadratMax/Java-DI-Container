
public class Counter {
    private int creatingAttempt;


    public Counter(){
        this.creatingAttempt++;
    }

    public int getCreatingAttempt(){
        return this.creatingAttempt;
    }

    public void increaseCounter(){
        this.creatingAttempt++;
    }
}
