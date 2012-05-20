package cfgame;
/**
 * Write a description of class gamer here.
 * 
 * @author (Clayton Marr) 
 * @version (April 23, 2012)
 */
public class gamer_new
{
    private String myName;
    private char mySymbol;
    public gamer_new()  //default
    {   myName=" "; mySymbol='0';   }
    public gamer_new(String name, char symbol)
    {   myName=name; mySymbol=symbol;   }
    public void setName(String newName)
    {   myName=newName; }
    public void setSymbol (char newSymb)
    {   mySymbol=newSymb;   }
    public String getName() {   return myName;  }
    public char getSymbol() {   return mySymbol;    }
    public String toString()
    {   return "Player Name: "+myName+";\tSymbol: "+mySymbol;   }
}

