
/**
 * Write a description of class Show here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.util.*;

public class Show extends JFrame {
    
    final static BattleField bf = new BattleField();
    final JPanel jp = new JPanel();
    public Show() 
    {
        initUI();
    }

    private void initUI() 
    {
        setLocationRelativeTo(null);
        setSize(1000, 500);
        add(bf); 
        setVisible(true);
        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }    
    
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                Show ex = new Show();
            }
        });
        SwingUtilities.invokeLater(new FileLevel());
    }
    
}