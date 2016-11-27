package components.models;

import sort.DataGeneratorFactory;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by di on 23.11.16.
 */
public class AutoUpdatableTableModel extends AbstractTableModel {

    private String[] header = {"Number from list"};
    private LinkedList<Double> rows;

    public AutoUpdatableTableModel()
    {
        rows = DataGeneratorFactory.getInstance().getData();
    }


    public int getRowCount() {
        return rows.size();
    }

    public int getColumnCount() {
        return header.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Double value = rowIndex >= 0 ? rows.get(rowIndex) : null;
        return value;
    }

    public void addRows(LinkedList<Double> rows) {
        this.rows.addAll(rows);
    }
}
