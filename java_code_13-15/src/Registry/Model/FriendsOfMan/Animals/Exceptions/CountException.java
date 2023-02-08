package Registry.Model.FriendsOfMan.Animals.Exceptions;

public class CountException extends RuntimeException{
    public CountException() {
        super("Count can't increment!");
    }
}
