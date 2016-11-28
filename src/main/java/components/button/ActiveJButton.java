package components.button;

import gui.MainForm;
import data.DataGeneratorFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by di on 23.11.16.
 */
public class ActiveJButton extends JButton implements ActionListener, MouseListener {

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

        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setBackground(ON_CLICK_BACK_COLOR);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setBackground(ON_CLICK_BACK_COLOR);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setBackground(STANDART_BACK_COLOR);
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent evt) {

        setBackground(ON_HOVER_BACK_COLOR);
    }
    @Override
    public void mouseExited(java.awt.event.MouseEvent evt) {
        setBackground(STANDART_BACK_COLOR);
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

