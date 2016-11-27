package sort;

import gui.MainForm;

import javax.swing.*;
import java.util.*;

/**
 * Created by di on 23.11.16.
 * Class for data generation in one or more threads.
 * Generating data produces in other threads, but data sorting works in main thread.
 */

public class DataGenerator implements DataGeneratorInterface {

    private static Random randomvalues = new Random();
    private final double LIST_SIZE_BORDER = Math.pow(10.0, 6.0);
    private final int PORTION_SIZE_FOR_PUBLISHING = 1000;
    private LinkedList<Double> data;

    DataGenerator() {
        data = new LinkedList<>();
    }

    synchronized public void generateData(MainForm gui)
    {
        LongTimeDataLoader backGroundOperationWorker = new LongTimeDataLoader(gui);
        backGroundOperationWorker.execute();
    }

    synchronized  private void addDataPortion(LinkedList<Double> dataPortion)
    {
        data.addAll(dataPortion);
    }

    public LinkedList<Double> getData() {
        return data;
    }

    synchronized public void sortData() {
        Collections.sort(data);
    }

    public Integer getDataRowsCount() {
        return data == null ? null : data.size();
    }

    public Double getValueByRowNumber(int row) {
        return  data == null ? null : row >= 0 ? data.get(row) : null;
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
            for (LinkedList dataForAddition : generatedData) {
                addDataPortion(dataForAddition);
                gui.updateTable(data);
                gui.setSummaryLabelText(Integer.toString(data.size()));
                gui.updateProgressBar(getProgress());
            }
        }

        synchronized private LinkedList<Double> generateDataActions() {
            for (int numRowsAlreadyGenerate = PORTION_SIZE_FOR_PUBLISHING; numRowsAlreadyGenerate <= LIST_SIZE_BORDER;)
            {
                publish(getPortionOfData(PORTION_SIZE_FOR_PUBLISHING));
                setProgress((int)(numRowsAlreadyGenerate / LIST_SIZE_BORDER * 100));
                numRowsAlreadyGenerate += PORTION_SIZE_FOR_PUBLISHING;
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
