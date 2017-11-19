package events;

/**
 * Created by bd on 11/18/2017.
 */

public class Errorevent {

    private final String errorMessage;

    public Errorevent(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }
}
