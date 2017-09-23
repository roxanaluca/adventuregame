
/**
 * This object give details about the properties and how the object can be used by the character.
 * 
 * @author Maria Roxana Luca
 * @version 1.0
 */

import javafx.scene.image.*;

public abstract class MagicObject extends Element
{
    String description; // an brief description of what the object does
    double points; // the damage applied to an enemy / the amount of health regenered
    String color; //the object appearance
    
    /*
     * Constructor for objects of class MagicObject
     */
    
    public MagicObject(String name)
    {
        super(name);
        description = "";
        points = 20;
    }
    
    public MagicObject(String name,int x,int y)
    {
        super(name,x,y);
        description = "";
        points = 20;
    }
    
    /*
     * 
     */
    
    public MagicObject(String name,double points)
    {
        super(name);
        this.points = points;
    }
    
    /*
     * Getter method for the description of the object
     * 
     * @param   none
     * @return  A string that content what the object does
     */
    
    public String getDescription()
    {
        return this.description;
    }
    
    /*
     * Getter method for the amount of energy that the object generates
     * 
     * @param   none
     * @return  A number represented the damage applied to an enemy / the amount of health regenered
     */
    
    public double getPoints()
    {
        return this.points;
    }
    
    /*
     * Setter method for the amount of energy that the object generates
     * 
     * @param   double  a number represented the damage applied to an enemy / the amount of health regenered
     * @return  none
     */
    
    public void setPoints(double points)
    {
        this.points = points;
    }
    
    /*
     * Setter method for the description of the object
     * 
     * @param   A string that content what the object does
     * @return  none
     */
    
    public void setDescription(String description)
    {
        this.description = description;
    }
}
