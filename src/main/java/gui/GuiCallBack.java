package gui;

import java.util.LinkedList;

/**
 * Created by di on 25.11.16.
 */
public interface GuiCallBack {
    void setSummaryLabelText(String textValue);
    void updateTable(LinkedList<Double> data);
    void updateProgressBar(int percentage);
}
