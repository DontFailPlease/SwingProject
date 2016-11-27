package components.button;

/**
 * Created by di on 23.11.16.
 */
public class UnknownTypeException extends Exception{

    public UnknownTypeException()
    {

    }

    public String toString()
    {
        return "Unknown type of button. Value of type variable can be equals only one of next values: [" +
                ActiveJButton.GENERATE_TYPE + ", " + ActiveJButton.SORT_TYPE + "]";
    }
}
