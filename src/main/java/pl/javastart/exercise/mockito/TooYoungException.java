package pl.javastart.exercise.mockito;

public class TooYoungException extends RuntimeException {

    public TooYoungException(){
        super("Klient jest za młody na ten zakup!");
    }
}
