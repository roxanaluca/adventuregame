
/**
 * This class stores the details for each character present on the battle field. Also, simultates the 
 * life span of each individual. 
 * 
 * @author Maria Roxana Luca
 * @version 1.0
 */

import javax.swing.JProgressBar;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public abstract class Character extends Element implements Runnable
{
    private int agility; //attribute that grants Armor and Attack Speed
    private double intelligence; //for creating spells
    private double strength; //grants heath and heath regeneration
    protected static int LEFTMOVE = 20;
    protected static int RIGHTMOVE = 20;
    protected static int UPMOVE = 20;
    protected static int DOWNMOVE = 20;
    boolean areFeatures = true;
    
    /*
     * Constructor for objects of class Character
     */
    
    public Character(String name)
    {
        super(name);
        setAgility();
        setIntelligence();
        setStrength();
    }
    
    public Character(String name, Boolean ok)
    {
        super(name,ok);
        setAgility();
        setIntelligence();
        setStrength();
    }
    
    /*
     * Constructor for objects of class Character with additional information about character 
     * (agility, intelligence, strength)
     */
    
    public Character(String name,int agility,double intelligence,double strength)
    {
        super(name);
        setAgility(agility);
        setIntelligence(intelligence);
        setStrength(strength);
    }
    
    /*
     * Constructor for objects of class Character that copies the information from an object of 
     * Character-type
     */
    public Character(Character c)
    {
        super(c.getName());
        setAgility(c.getAgility());
        setIntelligence(c.getIntelligence());
        setStrength (c.getStrength());
    }
    
    /*
     * Constructor for objects of class Character that have a fixed location on the screen
     */
    
    public Character(String name,int x,int y)
    {
        super(name,x,y);
        setAgility();
        setIntelligence();
        setStrength();
    }
    
    /*
     * Setter method for agility of the character
     * 
     * @param  agility  the decimal number that represents the reaction of the character according to
     *                  environment: defence or offence
     * @return          none
     */
    
    public void setAgility(int agility)
    {
        this.agility = agility;
    }
    
    public void setAgility()
    {
        this.agility = 500;
    }
    
    /*
     * Setter method for intelligence of the character
     * 
     * @param   intelligence  the decimal number that represents how much imagination should have the 
     *                        character at some point in game to launch one spell from magic object
     * @return                none
     */
    
    public void setIntelligence(double intelligence)
    {
        this.intelligence = intelligence;
    }
    
    public void setIntelligence()
    {
        this.intelligence = 0;
    }
    
    /*
     * Setter method for the strength of the character
     * 
     * @param   strength  the decimal number that represents how much the magic objects put up any
     *                    resistent to incoming damage
     * @return            none
     */
    
    public void setStrength(double strength)
    {
        this.strength = strength;
    }
    
    public void setStrength()
    {
        this.strength = 100;
    }
    
    /*
     * Getter method for agility of the character
     * 
     * @param   none
     * @return  the decimal number that represents the reaction of the character according to 
     *          çenvironment: defence or offence
     */
    
    public int getAgility()
    {
        return this.agility;
    }
    
    /*
     * Getter method for intelligence of the character
     * 
     * @param   none
     * @return  the decimal number that represents how much imagination should have the character at 
     *          some point in game to launch one spell from magic object
     */
    
    public double getIntelligence()
    {
        return this.intelligence;
    }
   
    /*
     * Getter method for strength of the character
     * 
     * @param   none
     * @return  the decimal number that represents how much the magic objects put up any
     *                    resistent to incoming damage
     */
    public double getStrength()
    {
        return this.strength;
    }
    
    /*
     * This method builds one line of text about one character.
     * 
     * @param   none
     * @return  one string with overall information of the character
     */
    
    public String toString()
    {
        return this.getName() +": "+ super.toString()+" agility - " + this.getAgility() + " intelligence - " 
                 + this.getIntelligence() + " strength - " + this.getStrength();
    }
    
    /*
     * This method performs the movement of the character. All exceptions about the movement of the character 
     * are mentioned.
     * 
     * @param   none
     * @return  none
     */
    
    public void run() 
    {
        try{
            this.movement();
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            System.out.println(e + " The character is unable to perform movement.");
            Element.stopForWhile(200);
        }
        catch (NullPointerException e)
        {
            System.out.println(e + " The character is unable to perform movement.");
            System.exit(0);
        }
    }

    /*
     * This method represents the algorithm that controls the movement of this character 
     * 
     * @param   none
     * @return  none
     */
    
    public abstract void movement();
   
    /*
     * This method moves the character to the left side.
     * 
     * @param   none
     * @return  none
     */
    
    public void makeStepLeft()
    {
        if (getStartX() > LEFTMOVE)
            moveBy(-LEFTMOVE,0,agility);
    }
    
     /*
     * This method moves the character to the up side.
     * 
     * @param   none
     * @return  none
     */
    
    public void makeStepUp()
    {
        if (getStartY() > UPMOVE)
            moveBy(0,-UPMOVE,agility);
    }
    
    /*
     * This method moves the character to the down side.
     * 
     * @param   none
     * @return  none
     */
    
    public void makeStepDown()
    {
        if (getEndY() + DOWNMOVE < getBoundDown())
            moveBy(0,DOWNMOVE,agility);
    }
    
     /*
     * This method moves the character to the right side.
     * 
     * @param   none
     * @return  none
     */
    
    public void makeStepRight()
    {
        if (getEndX() + RIGHTMOVE < getBoundRight())
            moveBy(RIGHTMOVE,0,agility);
    }
    
    /*
     * This method shows the entire details of the character on the screen. Notice that the 
     * position of the features is determinated according to the character appearance shown by the
     * first element of the group(type ViewImage).
     * 
     * @param   none
     * @return  none
     */
    
    public void draw(Graphics2D g,JPanel jp)
    {
        super.draw(g,jp);
        if (areFeatures)
         {
             int width = (int) Math.round(strength*imageWidth/100);
             g.drawRect(getStartX(),getEndY()+5,width,5);
         }
    }
    
    
    /*
     * This method decrese the life span of the character. It can be trigger by magicobject. Also, Adjusts
     * the progress bar of the character.
     * 
     * @param   double  - the intensitive of negative power over the character
     * @return  none
     */
    
    public void decreaseStrength(double points)
    {
        if (strength > points)
            strength -= points;
        else
            strength = 0;
    }
    
}
