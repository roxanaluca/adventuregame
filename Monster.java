
/**
 * Write a description of class Monster here.
 * The photo for magician is taken from http://frokenflanofilosoferar.blogspot.co.uk/2010_04_01_archive.html under 
 * free license.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Monster extends Character 
{
    private double damage;
    private int originalX;
    private int originalY;
    private char state;
    private int previousStrength = 100;
    private int timeDefending = 0;
    /*
     * Constructor for objects of class Monster using the superclass construction
     */
    
    public Monster(String name)
    {
        super(name);
        setImage("monster.png");
        //setGraphicsFeatures();
    }
    
    /*
     * Constructor for objects of class Monster that a
     */
    
    public Monster(String name, String fileURL)
    {
        super(name,true);
        setImage(fileURL);
    }
    
    /*
     * Constructor for objects of class Monster using the superclass construction. All details about a monster as
     * a character is stored.
     */
    
    public Monster(Character c)
    {
        super(c);
        setImage("monster.png");
    }
    
    public void setDamage(double damage)
    {
        this.damage = damage;
    }
    
    public double getDamage()
    {
        return damage;
    }
    
    /*
     * This method change the position of the monster according to the battlefield. It also be expected to include
     * an algorithm that ensures the failure of the main character.
     * 
     * @param   none
     * @return  none
     */
    
    public void movement() 
    {
         originalX = getStartX();
         originalY = getStartY();
         state = 'R';
         while (getStrength() > 0)
         {
             switch (state)
             {
                 case 'R':
                    if (hitByMyPlayer())
                        state = 'P';
                    else
                        state = 'V';
                    
                    break;
                 case 'P':
                    if (checkBallApproaching() || hitByMyPlayer())
                    {
                        state = 'D';
                        moveDefence();
                    }
                    else 
                        state = 'R';
                    break;
                 case 'D':
                    if (checkBallApproaching() )
                    {
                        state = 'P';
                        moveDefence();
                    }
                    else 
                        state = 'R';
                    break;
                 case 'V':
                    if (hitByMyPlayer())
                        state = 'R';
                    else
                    if (!isCloseToMyPlayer())
                        moveTowardsMyPlayer();
                    else
                        state = 'A';
                    break;
                 case 'A':
                    if (isCloseToMyPlayer())
                        randomMove();
                    else 
                        if (hitByMyPlayer())
                            state = 'R';
                        else
                            state = 'V';
                 
             }
             if (isCloseToMyPlayer())
                attack();
         }
    }
    
    public boolean checkBallApproaching()
    {
        if (isLeftSideMyPlayer())
            if (Show.bf.isBallApproaching(getEndX(),getEndY()))
                return true;
                else 
            if (Show.bf.isBallApproaching(getStartX(),getEndY()))
                return true;
        return false;
    }
    
    public boolean checkPartLeft()
    {
        Magician m = (Magician) Show.bf.getMyPlayer();
        double slope = m.getStartY()/m.getStartX();
        return (getEndX() * slope - getStartY() < 0); 
    }
    
    public void moveDefence()
    {
        if (isLeftSideMyPlayer())
            if (checkPartLeft())
                makeStepUp();
            else 
                makeStepLeft();
        else 
            makeStepRight();
            
    }
    
    public void randomMove()
    {
        boolean finish = false;
         
        while (finish)
        {
             Random srand = new Random();
             switch (srand.nextInt(3))
             {
                 case 0:
                    makeStepRight();
                    break;
                 case 1:
                    makeStepLeft();
                    break;
                 case 2:
                    makeStepDown();
                    break;
                 case 3:
                    makeStepUp();
                    break;
             }
        }
    }
    
    public boolean isLeftSideMyPlayer()
    {
         Magician m = (Magician) Show.bf.getMyPlayer();
         return (m.getStartX() - getEndX() > 0);
    }
    
    public boolean isRightSideMyPlayer()
    {
        Magician m = (Magician) Show.bf.getMyPlayer();
        return (getStartX() - m.getEndX() > 0);
    }
    
    public void moveTowardsMyPlayer()
    {
         Magician m = (Magician) Show.bf.getMyPlayer();
         boolean leftside = isLeftSideMyPlayer(); 
         boolean rightside = isRightSideMyPlayer();
         boolean downside = (getEndY() + DOWNMOVE < getBoundDown());
         boolean finish = false;
         
         while (!finish)
         {
             Random srand = new Random();
             int value = srand.nextInt(3);
             switch (value)
             {
                 case 0:
                    if (leftside)
                    {
                        makeStepRight();
                        finish = true;
                    }
                    break;
                 case 1:
                    if (rightside)
                    {
                        makeStepLeft();
                        finish = true;
                    }
                    break;
                 case 2:
                    if (downside)
                    {
                        makeStepDown();
                        finish = true;
                    }  
                    break;
             }
         }
    }
    
    
    public boolean hitByMyPlayer()
    {
        if (timeDefending == 0)
        {
            int copy = previousStrength;
            previousStrength = (int)Math.round(getStrength());
            if (copy != previousStrength) 
                timeDefending = 40;
            return (copy != previousStrength);
        }
        else
        {
            timeDefending --;
            return true;
        }
    }
    
    public boolean isCloseToMyPlayer()
    {
        Magician m = (Magician) Show.bf.getMyPlayer();
        return (intersect(m,this));
    }
    
    public boolean controlStrength()
    {
        Magician m = (Magician) Show.bf.getMyPlayer();
        return (m.getStrength() <= getStrength());
    }
    
    /*
     * The monster performs the basic attack movement.
     */
    
    public void attack()
    {
        Magician m = (Magician) Show.bf.getMyPlayer();
        m.decreaseStrength(damage);
    }
    
    public String toString()
    {
        return "Monster " + super.toString();
    }
}
