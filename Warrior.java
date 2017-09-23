
/**
 * Write a description of class Warrior here.
 * The photo is taken from http://www.wikihow.com/Draw-a-Warrior-Cat under free license.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Warrior extends Character
{
     /*
     * Constructor for objects of class Warrior using the superclass construction. All details from Character object 
     * about the main character is transferred to our object.
     */
    
    public Warrior(Character c)
    {
        super(c);
    }
    
     /*
     * Constructor for new objects of class Warrior using the superclass construction. Moreover, the representative image 
     * for warrior is set up.
     */
    
    public Warrior(String name)
    {
        super(name);
        setImage("warrior.jpg");
    }
    
    /*
     * This method change the position of the monster according to the battlefield. It also be expected to include
     * an algorithm that ensures the failure of the main character.
     * 
     * @param   none
     * @return  none
     */
    
    public void movement() {
        
    }
    
    public void attack()
    {
    }
}
