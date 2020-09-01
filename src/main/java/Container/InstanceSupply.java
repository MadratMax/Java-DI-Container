package Container;

import java.util.function.Supplier;

public class InstanceSupply<T> {

    private final Supplier<T> supplier;

    public InstanceSupply(Supplier<T> supplier){
        this.supplier = supplier;
    }

    public T get(){
        return this.supplier.get();
    }
}
