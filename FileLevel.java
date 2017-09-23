
/**
 * This class represents a current level. It stores the dates of a character from a file called level.txt and each of 
 * them is represented on the screen. The time between characters is also measured.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;

public class FileLevel implements Runnable
{
    private int number; // the number of the characters on the screen
    private BufferedReader bf;
    private char list[]; // the list of characters
    
    /*
     * This constructor initialise the file handler of the game. Otherwise, the class will receive precompute 
     * dates for a minimalise level.
     */
    
    public FileLevel()
    {
        try
        {
            bf = new BufferedReader(new FileReader("level.txt"));
            number = Integer.parseInt(bf.readLine());
            readWholeFile();
        }
        catch (FileNotFoundException e){
            System.out.println("doesn't work");
        }
        catch (IOException e)
        {
            System.out.println("doesn't work");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("doesn't work");
        }
        
    }
    
    /*
     * Getter method for the number of the character.
     */
    
    public int getNumber()
    {
        return number;
    }
    
    private String createName()
    {
        String[] names = {"Austen","Carmelita","Cerys","Dariela","Dolly","Birch","Morrison","Redmond","Romulus",
            "Socrates","Tiberius","Walden","Wilfred"};
        Random srand = new Random();
        int index = srand.nextInt(13);
        return names[index];
    }
    
    /*
     * This method overload the details about character from the GUI simulators.
     * 
     * @param   Character  an object that it is shown on the screen (initially it is invisible).
     * @return  Character  a character with modified properties 
     */
    
    public Element createElement(String[] strs)
    {
        Element ch;
        switch (strs[0].charAt(0))
        {
            case 'M': ch = new Monster(createName());break;
            case 'W': ch = new Warrior(createName());break;
            default: ch = null; break;
        }
        ch.setX(Integer.parseInt(strs[1]));
        ch.setY(Integer.parseInt(strs[2]));
        try{
            Monster m = (Monster) ch;
            m.setAgility(Integer.parseInt(strs[4]));
            m.setDamage(Integer.parseInt(strs[3]));
            ch = m;
        }
        catch(IndexOutOfBoundsException e)
        {
            
        }
        catch (ClassCastException e)
        {
            
        }
        return ch;
    }
    
    public void run()
    {
        new FileLevel();
    }
    
    /*
     * 
     */
    
    private void readWholeFile() throws IOException
    {
        int i = 0;
        while (true)
        {
            String str = bf.readLine();
            if (str == null) 
                break;
            String[] strs = str.split(" ");
            if (strs[0].charAt(0) == 'T')
                 Element.stopForWhile(Integer.parseInt(strs[1])); 
            else 
            {
                 Element ch = createElement(strs);
                 Show.bf.addElement(ch);
                 if (ch instanceof Character)
                    BattleField.startCharacterMovement((Character)ch);
            }
        }
    }
    
}
