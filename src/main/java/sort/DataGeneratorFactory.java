package sort;

/**
 * Created by di on 23.11.16.
 */
public class DataGeneratorFactory {
    private static DataGenerator dataGeneratorObject = null;

    public static DataGenerator getInstance()
    {
        if(dataGeneratorObject == null) {
            dataGeneratorObject = new DataGenerator();
        }
        return dataGeneratorObject;
    }
}
