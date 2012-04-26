package cfgame;
import java.awt.Color;


/**
 * Write a description of class gamer here.
 * 
 * @author (Clayton Marr) 
 * @version (April 23, 2012)
 */
public class gamer
{
	private Color myColor=null;
    private String myName;
    private char mySymbol;
    public gamer(){  //default
    	myName=" "; mySymbol='0';   }
    public gamer(String name, char symbol){
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

