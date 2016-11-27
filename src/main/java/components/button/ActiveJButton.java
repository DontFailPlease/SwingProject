package components.button;

import gui.MainForm;
import sort.DataGeneratorFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by di on 23.11.16.
 */
public class ActiveJButton extends JButton implements ActionListener {

    public static final int GENERATE_TYPE = 0;
    public static final int SORT_TYPE = 1;


    public enum Types {GENERATE_TYPE, SORT_TYPE};
    private Color STANDART_BACK_COLOR = null;
    private Color ON_HOVER_BACK_COLOR = new Color(100, 115, 100);
    private Color ON_CLICK_BACK_COLOR = new Color(100, 120, 100);
    MainForm gui;

    Types type;
    public ActiveJButton(String text, Types type, MainForm gui)
    {
        super(text);
        this.gui = gui;
        this.type = type;
        this.addActionListener(this);
        this.setFont(new Font("Times new Roman", Font.ITALIC, 30));
        this.setForeground(Color.BLACK);
        STANDART_BACK_COLOR = this.getBackground();
    }

    public void actionPerformed(ActionEvent ev)
    {
        try
        {
            getAccesibleMethod();
        }
        catch(UnknownTypeException e) {
            e.printStackTrace();
        }
    }

    private void getAccesibleMethod() throws UnknownTypeException
    {
        if(type == Types.GENERATE_TYPE)
        {
            DataGeneratorFactory.getInstance().generateData(gui);
        }
        else if(type == Types.SORT_TYPE)
        {
            DataGeneratorFactory.getInstance().sortData();
        }
        else
        {
            new UnknownTypeException();
        }
    }
}

