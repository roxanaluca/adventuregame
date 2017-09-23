
/**
 * This class describes a magician that can be controlled by the player.
 * The photo for magician is taken from http://clipart-library.com/hacer-cliparts.html under 
 * free license.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.geom.*;

public class Magician extends Character 
{
    ArrayList<Spell> items = new ArrayList<Spell>();
    boolean leftSide; //true for left side, false for right side 
    boolean activate;
    int synchronised ;
    Spell sp;
    Image imageLeftHand_LeftSide; 
    Image imageLeftHand_RightSide;
    Image imageRightHand_LeftSide;
    Image imageRightHand_RightSide;
    
    double vecx;
    double vecy;
    double angle;
    double tg;
    
     /*
     * Constructor for new objects of class Magician using the superclass construction.
     * Moreover, the representative image for magician is set up.
     */
    
    public Magician(String name)
    {
        super(name);
        init();
        setImage("body-left.png");
        activate = false;
        leftSide = true; 
        synchronised = 0;
    }
    
    /*
     * Constructor for new objects of class Magician and defines the character location.
     * Moreover, the representative image for magician is set up.
     */
    public Magician(String name,int x,int y)
    {
        super(name,x,y);
        init();
        setImage("body-left.png");
        activate = false;
        leftSide = true; 
        synchronised = 0;
    }
    
    /*
     * Constructor for objects of class Magician using the superclass construction. All details from Character object 
     * about the main character is transferred to our object.
     */
    
    public Magician(Character c)
    {
        super(c);
        setImage("body-left.png");
    }
    
    /*
     * 
     */
    
    public void init()
    {
        ImageIcon ii = new ImageIcon("lefthand-leftside.png");
        imageLeftHand_LeftSide = ii.getImage();
        ImageIcon ii1 = new ImageIcon("lefthand-rightside.png");
        imageLeftHand_RightSide = ii1.getImage();
        ImageIcon ii2 = new ImageIcon("righthand-leftside.png");
        imageRightHand_LeftSide = ii2.getImage();
        ImageIcon ii3 = new ImageIcon("righthand-rightside.png");
        imageRightHand_RightSide = ii3.getImage();
    }
    
    private void setLeftSideAngle()
    {
        if (synchronised == 0)
        {
            tg = vecy/vecx;
            angle = Math.atan(tg);
            double degree = angle*180/Math.PI;
            if (Math.abs(degree) > 30) 
            {
                if (degree < 0)
                {
                    angle = -Math.PI/6;
                    tg = -Math.sqrt(3)/3;
                }
                else
                {
                    angle = Math.PI/6;
                    tg = Math.sqrt(3)/3;
                }   
            }
        }
        else 
            synchronised --;
    }
    
    public void setLeftHandsLeftSide(Graphics2D g,JPanel jp)
    {
        AffineTransform originalTransform = g.getTransform();
        AffineTransform actualTransform = new AffineTransform();
        if (angle <0.01 && angle > -0.01)
        {
            g.drawImage(imageLeftHand_LeftSide, getStartX() - 5, getStartY() + 67, jp);
            return;
        }
        
        actualTransform.setToRotation(angle);
        
        int xx = (int)Math.round(Math.sin(Math.PI/2 + angle)*(getStartX() - 15)) 
                +(int)Math.round(Math.sin(angle)*( getStartY() + 67));
        int yy = (int)Math.round(Math.cos(Math.PI/2 + angle)*(getStartX() - 15)) 
                +(int)Math.round(Math.cos(angle)*( getStartY() + 67)) - (int) Math.round(tg*75);
                
        g.setTransform(actualTransform);
        g.drawImage(imageLeftHand_LeftSide,xx, yy , jp);
        g.setTransform(originalTransform);
        //double degree = angle*180/Math.PI;
        //g.draw(new Ellipse2D.Double(getStartX() - 15 ,getStartY() + 67 - degree,10,10));
    }
    
    public void setRightHandsLeftSide(Graphics2D g,JPanel jp)
    {
        AffineTransform originalTransform = g.getTransform();
        AffineTransform actualTransform = new AffineTransform();
        if (angle <0.01 && angle > -0.01)
        {
            g.drawImage(imageRightHand_LeftSide, getStartX() - 30, getStartY() + 40, jp);
            return;
        }
        
        actualTransform.setToRotation(angle);
        
        int xx = (int)Math.round(Math.sin(Math.PI/2 + angle)*(getStartX() - 30)) 
                +(int)Math.round(Math.sin(angle)*( getStartY() + 40));
        int yy = (int)Math.round(Math.cos(Math.PI/2 + angle)*(getStartX() - 30)) 
                +(int)Math.round(Math.cos(angle)*( getStartY() + 40)) - (int) Math.round(tg*75);
       
        g.setTransform(actualTransform);
        g.drawImage(imageRightHand_LeftSide, xx, yy, jp);
        g.setTransform(originalTransform);
    }
    
    public void setHandsRightSide(Graphics2D g,JPanel jp)
    {
        
        AffineTransform originalTransform = g.getTransform();
        AffineTransform actualTransform = new AffineTransform();
        
        double tg = vecy/vecx; 
        double angle = (-1)* Math.atan(tg);
        actualTransform.setToRotation(angle);
        
        int xx = (int)Math.round(Math.sin(Math.PI/2 + angle)*(getEndX() - 65)) +
                (int)Math.round(Math.sin((Math.PI - angle))* (getStartY() + 41));
        int yy = (int)Math.round(Math.cos(Math.PI/2 + angle)*(getEndX() - 65)) +
                (-1)*(int)Math.round(Math.cos((Math.PI - angle))* (getStartY() + 41)) - (int) Math.round(75*tg);
        
        g.setTransform(actualTransform);
        g.drawImage(imageLeftHand_RightSide, xx, yy, jp);
        g.setTransform(originalTransform);
        
    }
    
    /*
     * This method change the position of the magician according to the battlefield. It also be expected to include
     * an algorithm that ensures the failure of the main character.
     * 
     * @param   none
     * @return  none
     */
    
    public synchronized void movement() 
    {
        while (true)
        {
            makeStepRight();
            makeStepLeft();
        }
    }
    
    /*
     * This method allocates an spell to the inventor of the magician. Initially, the spell is not  
     * automatically activate to use it.
     * 
     * @param   Spell  s - the magic object that it is picked up by magician.
     * @return  none
     */
    
    public void pickSpell(Spell s)
    {
        items.add(s);
    }
   
    /*
     * This method makes visible spell choice by the user in the inventory. 
     * 
     * @param   none
     * @return  none
     */
    
    public void activateSpell()
    {
        activate = true;
        sp = items.get(0);
    }
    
    @Override 
    public void draw(Graphics2D g, JPanel jp)
    {
        if (leftSide)
        {
            setLeftSideAngle();
            setRightHandsLeftSide(g,jp);
        }
        else 
            setHandsRightSide(g,jp);
        super.draw(g,jp);
        if (activate){
            if (leftSide)
            {
                sp.setX(getStartX() - 25 - (int)Math.round(30*angle));
                sp.setY(getStartY() + 65 - (int)Math.round(70*angle));
            }
            else
            {
                sp.setX(getEndX() - 10);
                sp.setY(getStartY() + 75);
            }
            sp.draw(g,jp);
        }
        if (leftSide)
             setLeftHandsLeftSide(g,jp);
    }
    
    /*
     * This method removes the spell visible on the screen.
     * 
     * @param   none
     * @return  none
     */
    
    public void deactivateSpell()
    {
        activate = false;
    }
    
    /*
     * This method launch the basic attacks of the magician
     * 
     * @param   none
     * @return  none
     */
    
    public void attackSpell(int x,int y)
    {
        if (activate)
            items.get(0).launchBall(x,y);
        else 
            attack();
    }
    
    /*
     * This method perform a basic attack from magician.
     */
    
    public void attack()
    {
        angle = 0;
        synchronised = 100;
        Character ch = Show.bf.findTarget(getStartX() - 25,getStartY() + 65);
        if (ch != null && ch != this)
            ch.decreaseStrength(20);
    }
    
    /*
     * This method changes the side of the magician according to the mouse location.
     * 
     * @param   double  x,y - the mouse coordinates
     * @return  none
     */
    public void controlPart(double x,double y)
    {
        
        if (x > (getStartX() + getEndX())/2)
        {
            if (leftSide)
            {
                setImage("body-right.png");
                leftSide = false;
            }
        }
        else if (!leftSide)
        {
            setImage("body-left.png");
            leftSide = true;
        }
        vecy = getStartY() + 53 - y;
        if (leftSide)
            vecx = getStartX() + 12 - x;
        else 
            vecx = x - getEndX() + 75;
    }
}
