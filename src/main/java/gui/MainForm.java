package gui;

import components.button.ActiveJButton;
import components.models.AutoUpdatableTableModel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.util.LinkedList;


/**
 * Created by di on 23.11.16.
 */
public class MainForm extends JFrame implements GuiCallBack{
    private JButton dataGeneratorButton;
    private JButton startButton;
    private JPanel parentPanel;
    private JSplitPane dataSplitPane;
    private JTable arrayTable;
    private int width, height;
    private JSplitPane leftSplitPane;
    private JScrollPane scrollPane;
    private JToolBar dataToolBar;
    private JSplitPane rightSplitPane;
    private static AutoUpdatableTableModel model;
    private JLabel dataSummaryRows;
    private JProgressBar progressBar;

    @Override
    public void setSummaryLabelText(final String textValue) {
        if(SwingUtilities.isEventDispatchThread())
        {
            dataSummaryRows.setText(textValue);
        }
        else
        {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    dataSummaryRows.setText(textValue);
                }
            });
        }
    }

    @Override
    public void updateTable(LinkedList<Double> newData)
    {
        if(SwingUtilities.isEventDispatchThread())
        {
            model.fireTableDataChanged();
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    model.fireTableDataChanged();
                }
            });
        }
    }

    @Override
    public void updateProgressBar(final int percentage) {
        if(SwingUtilities.isEventDispatchThread())
        {
            progressBar.setValue(percentage);
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    progressBar.setValue(percentage);
                }
            });
        }
    }

    public MainForm(int width, int height)
    {
        this.width = width;
        this.height = height;
        generateComponentsGUI();
        addComponentsGUI();
    }

    private void generateComponentsGUI()
    {
        dataGeneratorButton = new ActiveJButton("Generate data", ActiveJButton.Types.GENERATE_TYPE, this);
        startButton = new ActiveJButton("Sort data", ActiveJButton.Types.SORT_TYPE, this);

        createParentSplitPane();
        createLeftSplitPane();
        createTable();
        createDataToolBar();
        createDataSplitPane();

        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void createParentSplitPane()
    {
        dataSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        dataSplitPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));
        dataSplitPane.setDividerLocation(width / 4);
        dataSplitPane.setAutoscrolls(true);
    }

    private void createTable()
    {
        model = new AutoUpdatableTableModel();
        arrayTable = new JTable(model);
        arrayTable.setAutoscrolls(true);
        scrollPane = new JScrollPane(arrayTable);
    }

    private void createLeftSplitPane()
    {
        leftSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        leftSplitPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));
        leftSplitPane.setDividerLocation(height / 2);
    }

    private void createDataToolBar()
    {
        dataToolBar = new JToolBar();
        JLabel summaryText = new JLabel("summary: ");
        summaryText.setFont(getLabelFont());

        dataSummaryRows = new JLabel("0");
        dataSummaryRows.setFont(getLabelFont());

        dataToolBar.add(summaryText);
        dataToolBar.add(new JToolBar.Separator());
        dataToolBar.add(dataSummaryRows);

        progressBar = new JProgressBar(0, 100);
        dataToolBar.add(progressBar);
    }


    private Font getLabelFont()
    {
        return new Font("Times New Roman", Font.PLAIN, 10);
    }

    private void createDataSplitPane()
    {
        rightSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        rightSplitPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        rightSplitPane.setOpaque(true);
        rightSplitPane.setDividerLocation(this.height - this.height / 10);
    }

    private void addComponentsGUI()
    {
        leftSplitPane.add(dataGeneratorButton);
        leftSplitPane.add(startButton);
        rightSplitPane.add(scrollPane);
        rightSplitPane.add(dataToolBar);

        dataSplitPane.add(leftSplitPane);
        dataSplitPane.add(rightSplitPane);

        this.add(dataSplitPane);
        this.setVisible(true);
    }
}
