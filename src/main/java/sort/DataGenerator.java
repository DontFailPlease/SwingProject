package sort;

import gui.MainForm;

import javax.swing.*;
import java.util.*;

/**
 * Created by di on 23.11.16.
 */

public class DataGenerator implements DataGeneratorInterface {

    private static Random randomvalues = new Random();
    private final double LIST_SIZE_BORDER = Math.pow(10.0, 7.0);
    private final int PORTION_SIZE_FOR_PUBLISHING = 1000000;
    private final int DELAY_BETWEEN_PORTIONS = 100; // milliseconds
    private LinkedList<Double> data;

    public DataGenerator() {
        data = new LinkedList<>();
    }

    synchronized public void generateData(MainForm gui)
    {
        LongTimeDataLoader backGroundOperationWorker = new LongTimeDataLoader(gui);
        backGroundOperationWorker.execute();
    }

    public LinkedList<Double> getData() {
        return data;
    }

    synchronized public void sortData() {
        Collections.sort(data);
        //MainForm.updateTable();        //MainForm.updateTable();
    }

    public Integer getDataRowsCount() {
        Integer rowsCount = data == null ? null : data.size();
        return rowsCount;
    }

    public Double getValueByRowNumber(int row) {
        Double resultValue = data == null ? null : row >= 0 ? data.get(row) : null;
        return resultValue;
    }

    public void setValueByRowNumber(Double insertedValue, int row) {
        if (data != null) {
            data.set(row, insertedValue);
        }
    }

    /*
     * Class LongTimeLoader need for manipulate with large array of data.
     * One of the task is generating and populating data in "data" linked list.
     */

    private class LongTimeDataLoader extends SwingWorker<LinkedList<Double>, LinkedList<Double>>
    {
        MainForm gui;
        LongTimeDataLoader(MainForm gui)
        {
            this.gui = gui;
        }

        @Override
        protected LinkedList<Double> doInBackground() throws Exception
        {
            return generateDataActions();
        }

        @Override
        protected void done()
        {

        }

        /*
         * How can I send data in List<Double> or LinkedList<Double>
         * instead of List<LinkedList<Double>>.
         * Cause: SwingWorker-super class with method process with specified parameter type
         * like List<V>.
         */

        @Override
        protected void process(List<LinkedList<Double>> generatedData) {

            data.addAll(generatedData.get(0));
            gui.updateTable(data);
            gui.setSummaryLabelText(Integer.toString(data.size()));
            gui.updateProgressBar(getProgress());
        }

        synchronized private LinkedList<Double> generateDataActions() {
            for (int numRowsAlreadyGenerate = PORTION_SIZE_FOR_PUBLISHING; numRowsAlreadyGenerate <= LIST_SIZE_BORDER;)
            {
                try {
                    LinkedList<Double> portion = getPortionOfData(PORTION_SIZE_FOR_PUBLISHING);
                    publish(portion);
                    setProgress((int)(numRowsAlreadyGenerate / LIST_SIZE_BORDER * 100));
                    numRowsAlreadyGenerate += PORTION_SIZE_FOR_PUBLISHING;
                    Thread.currentThread().sleep(DELAY_BETWEEN_PORTIONS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return data;
        }

        private LinkedList<Double> getPortionOfData(int portionSize)
        {
            LinkedList<Double> newValues = new LinkedList<>();
            for(int i = 0; i < portionSize; i ++) {
                newValues.add(randomvalues.nextDouble() * LIST_SIZE_BORDER);
            }
            return newValues;
        }
    }
}
