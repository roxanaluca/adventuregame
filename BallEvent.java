
/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class BallEvent extends Element implements Runnable
{
    private double distance = 20;
    private double xPlus = 10; 
    private double yPlus = 10;
    private double points;
    private boolean finished;
    private Spell origin;
    
    public BallEvent(Spell s,double x,double y)
    { 
        super("ball_in_action");
        setX(s.getStartX());
        setY(s.getStartY());
        origin = s;
        
        double ll = Math.sqrt((x-getStartX())*(x-getStartX())+(y-getStartY())*(y-getStartY()));
        xPlus = (getStartX()-x)/ll*10*(-1);
        yPlus = (getStartY()-y)/ll*10*(-1);
        finished = false;
    }
    
    /*
     * This method manages the triggering event of the spell 
     */
    
    public void run()
    {
        int l = 0;
        int stopTime = (int) Math.round(distance*1000/origin.getSpeed());
        while (l < distance && Element.checkX(getStartX()) && Element.checkY(getStartY() ))
        {
            if (l>5)
            {
                try
                {
                    Character ch = Show.bf.findTarget(getStartX(),getStartY());
                    ch.decreaseStrength(origin.getPoints());   
                    break;
                }
                catch (NullPointerException e)
                {
                    //e.printStackTrace();
                }
            }
            setX((int) Math.round(getStartX() + xPlus));
            setY((int) Math.round(getStartY() + yPlus));
            Element.stopForWhile(stopTime);
            l++;
        }
        finished = true;
    }
    
    public boolean notFinished()
    {
        return !finished;
    }
    
}
