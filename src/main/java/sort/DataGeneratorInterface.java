package sort;

import java.util.List;
import gui.*;

/**
 * Created by di on 23.11.16.
 */
public interface DataGeneratorInterface {
    public void generateData(MainForm gui);
    public List<Double> getData();
    public void sortData();
    public Integer getDataRowsCount();
    public Double getValueByRowNumber(int row);
    public void setValueByRowNumber(Double insertedValue, int row);
}
