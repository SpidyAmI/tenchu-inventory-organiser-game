package Model.Exceptions;

public class NotExistingCode extends NinjaException {
    public NotExistingCode() {
        super("The code is not valid!");
    }
}
