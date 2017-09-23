
/**
 * This class is concerned about the movement and the position of the character in the battlefield. Also, this
 * class includes the visual representation of the character.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.JPanel;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Dimension;

public abstract class Element 
{
    private int x; //x - position of ...
    private int y; //y - position of ...
    public static int DISPLAYHEIGHT = 500; //the height of the game screen
    public static int DISPLAYWIDTH = 1000; //the width of the game screen
    public static int DELAY = 25;
    private String name;
    private static Random srand = new Random(); 
    private Image background;
    private boolean isImage;
    protected int imageHeight;
    protected int imageWidth;
    
    /*
     * Constructor for objects of class Elements. Initially the element is placed to the top left side corner
     * of the application screen.
     */
    
    public Element(String name)
    {
        setX(0);
        setY(0);
        this.name = name;
    }
    
    /*
     * Constructor for objects of class Elements that generates values
     */
    
    public Element(String name, Boolean ok)
    {
        setX(srand.nextInt(DISPLAYWIDTH));
        setY(srand.nextInt(DISPLAYHEIGHT));
        this.name = name;
    }
    
    /*
     * Constructor for objects of class Element using customer values
     */
    
    public Element(String name,int x,int y)
    {
        this.name = name;
        setX(x);
        setY(y);
    }
    
    /*
     * This method sets the 
     */
    
    public void setRandomValue()
    {
        setX(srand.nextInt(DISPLAYWIDTH));
        setY(srand.nextInt(DISPLAYHEIGHT));
    }
    
    public void draw(Graphics2D g,JPanel jp) 
    {
        g.drawImage(background,this.x,this.y,jp);
        Toolkit.getDefaultToolkit().sync();
    }
    
    /*
     * This method set up an ilustrative image for this element object
     * 
     * @param   String  the full name and address of the image according to the the main directory of 
     *                  project
     * @return  none
     */
    
    public void setImage(String src)
    {
        try 
        {
            ImageIcon ii = new ImageIcon(src);
            imageHeight = ii.getIconHeight();
            imageWidth = ii.getIconWidth();
            background = ii.getImage();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Source of image not found!");
        }
    }
    
    /*
     * Getter method for the name of the element
     * 
     * @param   none 
     * @return  the string that shows the name which the character is recognised in the game
     */
    
    public String getName()
    {
        return this.name;
    }
    
    /*
     * Getter method for x - coordinate 
     * 
     * @param   none
     * @return  the x - coordonate where the element is on the table
     */
    
    protected int getStartX()
    {
        return x;
    }
    
    /*
     * Getter method for y - coordinate
     * 
     * @param   none
     * @return  the y - coordonate where the element is on the window 
     */
    
    protected int getStartY()
    {
        return y;
    }
    
    /*
     * Setter method for x - coordinate
     * 
     * @param   int  represents the x - position of the element
     * @return  none
     */
    
    protected void setX(int x)
    {
        this.x = x;
    }
    
    /*
     * Setter method for y - coordinate
     * 
     * @param   int  represents the y - position of the element
     * @return  none
     */
    
    protected void setY(int y)
    {
        this.y = y;
    }
    
    /*
     * Getter method that returns the end x-coordinate of the element
     * 
     * @param   none
     * @return  none
     */
    
    public int getEndX()
    {
        return this.x + this.imageWidth;
    }
    
    /*
     * Getter method that returns the end y-coordinate of the element
     * 
     * @param   none
     * @return  none
     */
    
    public int getEndY()
    {
        return this.y + this.imageHeight;
    }
    
    /*
     * This method build one line of text about the position of element.
     * 
     * @param   none
     * @return  one string with overall information of the element
     */
    
    public String toString()
    {
        return "x - " + this.x + " y - " + this.y;
    }
   
    /*
     * This method performs the motion of the element. 
     * 
     * @param   x, y  -  the distance that the character want to go through
     *          elem  -  the node that the motion is applied
     * @return  none
     */
    
    protected final void moveBy(int x,int y,int time) 
    {   
        long beforeTime, timeDiff, sleep;
        
        int divideTime = 10;
        int unitX = Math.round(x/divideTime);
        int unitY = Math.round(y/divideTime);
        
        beforeTime = System.currentTimeMillis();

        for (int i = 0; i < divideTime; i++)
        {
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            setX((int)getStartX() + unitX);
            setY((int)getStartY() + unitY);
            stopForWhile(Math.round(sleep + time/divideTime));
            
            beforeTime = System.currentTimeMillis();
        }
    }
    
    /*
     * This method cease the execution time of the object's thread after several milliseconds.
     * 
     * @param   millis - the time that the thread should be suspended
     * @return  none
     */
    
    public static void stopForWhile(long millis)
    {
        try{
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
            System.out.println("Not working the player");
        }
    }
    
    /*
     * This method displays the x-coordinate bound where the character can be entirely shown on the screen.
     * 
     * @param   none
     * @return  none
     */
    
    protected double getBoundRight()
    {
        return DISPLAYWIDTH-imageWidth;
    }
    
    /*
     * This method displays the y-coordinate bound where the character can be entirely shown on the screen.
     * 
     * @param   none
     * @return  none
     */
    
    protected double getBoundDown()
    {
        return DISPLAYHEIGHT-imageHeight;
    }
    
    /*
     * This method checks if the x-coordinate covers the screen
     */
    
    public static boolean checkX(double x)
    {
        return (x > 0 && x < DISPLAYWIDTH);
    }
    
    /*
     * This method checks if the y-coordinate covers the screen
     */
    
    public static boolean checkY(double y)
    {
        return (y > 0 && y < DISPLAYHEIGHT);
    }
    
    /*
     * This method checks if a particular point on the screen is covered by the character.
     * 
     * 
     */
    
    public boolean contains(double xi,double yi)
    {
        return (getStartX() < xi && xi < getEndX() && getStartY() < yi && yi < getEndY());
    }
    
    public static boolean intersect(Element a,Element b)
    {
        return ((intersectSegments(a.getStartX(),a.getEndX(),b.getStartX(),b.getEndX())) &&
               (intersectSegments(a.getStartY(),a.getEndY(),b.getStartY(),b.getEndY())));
    }
    
    public static boolean intersectSegments(double x1,double y1,double x2,double y2)
    {
        return ((x2-y1)*(x1-y2) > 0);
    }
}
