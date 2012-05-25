 
import java.awt.Color;


/**
 * Write a description of class gamer here.
 * 
 * @author (Clayton Marr) 
 * @version (April 23, 2012)
 */
public class Gamer
{
	private Color myColor=null;
    private String myName;
    private char mySymbol;
    public Gamer()//default
    {   myName=" "; mySymbol='0';   }
    public Gamer(String name)
    {   myName=name; mySymbol='0';  }
    public Gamer(String name, char symbol){
    	myName=name; mySymbol=symbol;   }
    public void setName(String newName){
    	myName=newName; }
    public void setSymbol(char newSymb){
    	mySymbol=newSymb;   }
    public void setColor(Color c){
    	myColor=c;
    }
    public String 	getName() {		return myName;}
    public char 	getSymbol() {	return mySymbol;}
    public Color 	getColor(){		return myColor;}
    public String toString()
    {   return "Player Name: "+myName+";\tSymbol: "+mySymbol;   }
}

