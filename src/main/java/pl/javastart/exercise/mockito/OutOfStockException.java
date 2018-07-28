package pl.javastart.exercise.mockito;

public class OutOfStockException extends RuntimeException {

    public OutOfStockException(){
        super("Brak wystarczajacych srodkow!");
    }

}
