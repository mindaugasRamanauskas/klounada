package lt.baltic.talents.superhero.klounada.daos;

public class Errors extends Exception {

    public static final Errors REGISTER_EXISTS = new Errors(1000, "errors.backend.register.exists");
    public static final Errors REGISTER_FAILURE = new Errors(1001, "errors.backend.register.failure");

    private int errorCode;
    private String message;

    public Errors(int errorCode, String message) {
        super(message);

        this.errorCode = errorCode;
        this.message = message;
    }

    public Errors(int errorCode, String message, Throwable exception) {
        super(message, exception);

        this.errorCode = errorCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
