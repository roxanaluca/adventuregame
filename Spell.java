
/**
 * Write a description of class Spell here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.geom.*;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.*;

public class Spell extends MagicObject
{   
    ArrayList<BallEvent> balls = new ArrayList<>();
    private static int DIMENSION = 25;
    private int speed;
    
    public Spell()
    {
        super("ball");
        speed = 500;
    }
    
    public Spell(int x,int y)
    {
        super("ball",x,y);
    }
    
    @Override
    public void draw(Graphics2D g, JPanel jp)
    {
        Ellipse2D.Double circle =  new Ellipse2D.Double(getStartX(), getStartY(), DIMENSION, DIMENSION);
        g.fill(circle);
        addBallCircle(g);
    }
    
    private void addBallCircle(Graphics2D g)
    {
        boolean ok = false;
        for (int i = 0; i < balls.size();i++)
            try
            {
                if (balls.get(i).notFinished())
                {
                    BallEvent b = balls.get(i);
                    Ellipse2D.Double circle = new Ellipse2D.Double(b.getStartX(),b.getStartY(), DIMENSION, DIMENSION);
                    g.fill(circle);
                }
                else
                {
                    balls.remove(i);
                    i--;
                }
            }
            catch (IndexOutOfBoundsException e)
            {
                break;
            }
    }
    
    public Spell(double points)
    {
        super("ball",points);
    }
     
    public int getSpeed()
    {
        return speed;
    }
    
    /*
     * This method create a spell event
     */
    
    public synchronized void launchBall(int x,int y)
    {
        BallEvent be = new BallEvent(this,x,y);
        balls.add(be);
        new Thread(be).start();
    }
    
}
