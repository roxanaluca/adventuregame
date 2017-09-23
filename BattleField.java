
/**
 * This class represents the whole scene of the game and contains all used events filters for user part.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.*;
import java.awt.event.*;


public class BattleField extends JPanel implements Runnable, MouseListener, MouseMotionListener
{
    private Magician myPlayer;
    private ArrayList<Element> childrens;
    private static int ERRORX = 30;
    private static int ERRORY = 30;
    
    /*
     * This constructor initialise the whole game scene. Also it adds some events triggered by the user.
     */
    
    public BattleField()
    {
        super();
        myPlayer = new Magician("Me",480,280);
        childrens = new ArrayList<>();
        addElement(myPlayer);
        startAllCharacterMovement();
        addMouseListener(this);
        addMouseMotionListener(this);
        
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), 
            "activate");
        getActionMap().put("activate", new AbstractAction() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                myPlayer.activateSpell();
            }
        });
        
        Spell s = new Spell();
        myPlayer.pickSpell(s);
        //myPlayer.activateSpell();
    }
   
    public Magician getMyPlayer()
    {
        return myPlayer;
    }
    
    public void mouseMoved(MouseEvent e) {
         myPlayer.controlPart(e.getX(),e.getY());
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
       
    }

    public void mouseReleased(MouseEvent e) {
       myPlayer.controlPart(e.getX(),e.getY());
    }

    public void mouseEntered(MouseEvent e) {
       myPlayer.controlPart(e.getX(),e.getY());
    }

    public void mouseExited(MouseEvent e) {
       
    }

    public void mouseClicked(MouseEvent e) {
        myPlayer.attackSpell(e.getX(),e.getY());
    }
    
    public void addElement(Element e)
    {
        childrens.add(e);
    }
    
    /*
     * This method starts the movement of the characters according to the main character
     * 
     * @param   none
     * @return  none
     */
    
    public void startAllCharacterMovement()
    {
        int lg = childrens.size();
        for (int i = 0;i < lg;i++)
            if (childrens.get(i) instanceof Character)
                startCharacterMovement((Character)childrens.get(i));
    }
    
    /*
     * This method triggers the action for a single character into the game screen.
     * 
     * @param   Character  -  the element that request to perform an action.
     * @return  none
     */
    
    public static void startCharacterMovement(Character elem)
    {
        if (elem == null) 
            return;
        new Thread(elem).start();
    }
    
    public boolean isBallApproaching(int x,int y)
    {
        int i;
        int lg = childrens.size();
        for (i = 0;i < lg; i++)
            if (childrens.get(i) instanceof BallEvent)
                if (Math.abs(childrens.get(i).getStartX() - x) < ERRORX 
                    && Math.abs(childrens.get(i).getStartY() - y) < ERRORY)
                    return true;
        return false;
    }
    
    /*
     * This method provides the link of the character that stays on the particular position on the screen.
     * 
     * @param   double   x,y  -  the coordinates of the screen
     * @return  Character  - the character found at position (x,y), or otherwise null
     */
    
    public Character findTarget(double x,double y)
    {
        int lg = childrens.size();
        for (int i = 0;i < lg;i++)
            if (childrens.get(i) instanceof Character)
            {
                Character ch = (Character) childrens.get(i);
                if (ch.contains(x,y))
                    return ch;
            }
        return null;
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        Thread animator;
        animator = new Thread(this);
        animator.start();
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        drawElements(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void drawElements(Graphics g)
    {
        int lg = childrens.size();
        if (myPlayer.getStrength() == 0)
            System.exit(0);
        for (int i = 0;i < lg;i++)
            try{
                if (childrens.get(i) instanceof Character)
                {
                    Character ch = (Character) childrens.get(i);
                    if (ch.getStrength() > 0)
                        ch.draw((Graphics2D) g,this);
                    else
                    {
                        childrens.remove(i);
                        i--;
                    }
                }
            }
            catch (IndexOutOfBoundsException e)
            {
                break;
            }
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            repaint();
        }
    }
    
    
    /*
    public static void saveState(RootGroup root) //throws IOException
    {
        PrintWriter out = new PrintWriter(new FileWriter("save.txt"));
        ObservableList<Node> collection = root.getChildren();
        for (Node n : collection)
            if (n instanceof Monster)
            {
                out.println(n.toString());
            }
        out.close();
    }
    */
    
}