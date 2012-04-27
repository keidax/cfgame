package cfgame;
import java.awt.Color;
import java.util.*;
/**
 * Write a description of class Connect4Game here.
 * 
 * @author (Clayton Marr) 
 * @version (April 23, 2012)
 */
public class Connect4Game
{
	private CFGameGrid grid;
	public Connect4Game(){
		grid=new CFGameGrid("ConnectFour");
		game();
	}
    private char[][] gameboard;
    private Gamer[] players;
    private static Gamer currentGamer;
    public char[][] newBoard()
    {   char[][] board = new char[6][7]; 
        for(int r=0; r<board.length; r++)
        {
            for(int c=0; c<board[r].length; c++)
            {   board[r][c]='_';    }
        }
        return board;
    }
    public boolean isFull(char[][] board)
    {   for(int r=0; r<board.length; r++)
        {   for(int c=0; c<board[r].length; c++)
            {   if(board[r][c]=='_')    {   return false;   }   }
        }
        return true;
    }
    public boolean isGridFull(){
    	//TODO implement this to actually check grid and return a value
    	
    	return false;
    }
    public int piecesInCol (char[][] board, int c)
    {   int count=0;
        for(int r=board.length-1; r>=0; r--)
        {
            if(board[r][c]!='_')    {   count++;    }
            else    {   return count;   }
        }
        return count; //if it reaches this point, value returned will indicate that the stack is maxed. 
    }
    public void dropPiece (char[][] rack, int C, char symb)
    {   rack[piecesInCol(rack,C)][C]=symb;  }
    /*public static void InitiatePlayers(String args[])
    {   players=new gamer[2]; String nom; char symb;
        Scanner kbReader=new Scanner(System.in);
        for(int p=1; (p-1)<players.length; p++)
        {
            System.out.println("Please enter Player#"+p);
            System.out.print("Name:\t"); nom=kbReader.nextLine();
            System.out.print("\nSymbol:\t");
            symb=(kbReader.next()).charAt(0);
            players[p-1]=new gamer(nom,symb);
        }
    }*/
    
    @SuppressWarnings("unused")
	public void game()
    {
        gameboard=newBoard();
        players=new Gamer[2]; String nom; char symb, e; int ch, r;
        Scanner kbReader=new Scanner(System.in);
        boolean isOver=false, isTie=false;
        
        /*
        for(int p=1; (p-1)<players.length; p++)
        {
            System.out.println("Please enter Player#"+p);
            System.out.print("Name:\t"); nom=kbReader.nextLine();
            System.out.print("\nSymbol:\t");
            symb=(kbReader.nextLine()).charAt(0);
            players[p-1]=new Gamer(nom,symb);
        }
        */
        players[0]=new Gamer("player",'p');
        players[0].setColor(Color.CYAN);
        players[1]=new Gamer("comp",'c');
        players[1].setColor(Color.ORANGE);
        for(int p=0; !isOver; p=1-p)
        {
        	currentGamer=players[p];
            symb=players[p].getSymbol();
            System.out.println(players[p].getName()+"'s turn:");
            System.out.println("The current rack:");
            printBoard();
            System.out.println("In which column, numbered 0 to 6 from left-to-right, will you place your piece?"); 
            e=(kbReader.next()).charAt(0); 
            while(numValue(e)<0||numValue(e)>6)
            {
                System.out.println("That value is not applicable. Please choose a columb, 0 to 6, left-to-right.");
                e=(kbReader.next()).charAt(0); 
            }
            ch=numValue(e);
            r=piecesInCol(gameboard,ch);
            dropPiece(gameboard, ch, symb);
            if(isVictory(gameboard, symb))
            {   
                System.out.println(players[p].getName()+" WINS!!!"); 
                isOver=true;
            }
            if(isFull(gameboard))
            {   System.out.println("IT'S A TIE!!"); isOver=true;    }            
        }
    }
    public boolean isVictory(char[][] board, /*int row, int column,*/ char sym)
    //might need to make this method more efficient
    {
        int count=0;
        
        for(int r=0; r<board.length; r++)//test for horizontal victory
        {
            for(int c=0; c<board[r].length; c++)
            {
                if(board[r][c]==sym) count++;
                else    count=0;
                if(count==4)    {   return true;    }
            }
            count=0;
        }
        for(int c=0; c<board[0].length; c++)//test for vertical victory
        {
            for(int r=0; r<board.length;r++)
            {
                if(board[r][c]==sym) count++;
                else    count=0;
                if(count==4)    {   return true;    }
            }
            count=0;
        }
        //now test for diagonal victory... somehow. 
        //...
        return false;
    }
    public int numValue(char c)
    {
        return (int)c-48;
    }
    public void printBoard()
    {
        for(int r=0; r<gameboard.length; r++)
        {
            for(int c=0; c<gameboard[r].length; c++)
            {
                System.out.print(gameboard[r][c]+"\t");
            }
            System.out.println("");
        }
    }
    public static Gamer getCurrentGamer(){
    	return currentGamer;
    }
}
