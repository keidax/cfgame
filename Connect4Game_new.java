
import java.util.Scanner;
/**
 * Write a description of class Connect4Game here.
 * 
 * @author (Clayton Marr) 
 * @version (April 23, 2012; completed April 25, 2012)
 */
public class Connect4Game_new
{
    private static char[][] gameboard;
    private static Gamer[] players;
    public static char[][] newBoard()
    {   char[][] board = new char[6][7]; 
        for(int r=0; r<board.length; r++)
        {
            for(int c=0; c<board[r].length; c++)
            {   board[r][c]='_';    }
        }
        return board;
    }
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
    
    public static void game()
    {
        gameboard=newBoard();
        players=new Gamer[2]; String nom; char symb, e; int ch;
        Scanner kbReader=new Scanner(System.in);
        boolean isOver=false;
        for(int p=1; (p-1)<players.length; p++)
        {
            System.out.println("Please enter Player #"+p+":");
            System.out.print("Name:\t"); nom=kbReader.nextLine();
            System.out.print("\nSymbol:\t");
            symb=(kbReader.nextLine()).charAt(0);
            players[p-1]=new Gamer(nom,symb);
        }
        System.out.println("This is the current state of the rack:"); printBoard();
        for(int p=0; !isOver; p=1-p)
        {
            symb=players[p].getSymbol();
            System.out.println(players[p].getName()+"'s turn:");
            System.out.println("In which column, numbered 0 to 6 from left-to-right, will you place your piece?"); 
            e=(kbReader.next()).charAt(0); 
            while(numValue(e)<0||numValue(e)>6)
            {
                System.out.println("That value is not applicable. Please choose a columb, 0 to 6, left-to-right.");
                e=(kbReader.next()).charAt(0); 
            }
            ch=numValue(e);
            dropPiece(gameboard, ch, symb);
            System.out.println("This is the new state of the rack:");
            printBoard();
            if(isVictory(gameboard, symb))
            {   
                System.out.println(players[p].getName()+" WINS!!!"); 
                isOver=true;
            }
            if(isFull(gameboard))
            {   System.out.println("IT'S A TIE!!"); isOver=true;    }            
        }
    }
    public static boolean isVictory(char[][] board, /*int row, int column,*/ char sym)
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
        //now test for diagonal type 1 (/ if 0 considered the bottom-most row) victory        
        for(int r=gameboard.length-4; r>0; r--) //r=0 will occur as first step of next loop
        {
            for(int c=0; c<gameboard[r].length && (r-c)>=0; c++)
            {
                if(gameboard[r-c][c]==sym)  {   count++;    }
                else    {   count=0;    }
                if(count==4)    {   return true;    }
            }
            count=0;
        }
        for(int c=0; c<=gameboard[0].length-4; c++)
        {
            for(int r=0; r<gameboard.length && (c+r)<gameboard[r].length; r++)
            {
                if(gameboard[r][c+r]==sym)  {count++;   }
                else    {   count=0;    }
                if(count==4)    {   return true;    }
            }
            count=0;
        }
        //test for diagonal type 2 (\ if 0 is considered the bottom-most row) victory  
        for(int c=3; c<gameboard[0].length; c++)
        {
            for(int r=0; r<gameboard.length && (c-r)>=0; r++)
            {
                if(gameboard[r][c-r]==sym)  {   count++;    }
                else    {   count=0;    }
                if(count==4)    {   return true;    }
            }
            count=0;
        }
        for(int r=1; r<=gameboard.length-4; r++)
        {
            for(int c=0; c>=0 && (r+c) <gameboard.length; c++)
            {
                if(gameboard[r+c][c]==sym)  {   count++;    }
                else    {   count=0;    }
                if(count==4)    {   return true;    }
            }
            count=0;
        }
        return false;
    }
    public static int numValue(char c)
    {
        return (int)c-48;
    }
    public static void printBoard()
    {
        System.out.print("\t");
        for(int c=0; c<gameboard[0].length; c++)
        {   System.out.print(c+"\t");   }
        System.out.println("");
        for(int r=gameboard.length-1; r>=0; r--)
        {
            System.out.print("\t");
            for(int c=0; c<gameboard[r].length; c++)
            {
                System.out.print(gameboard[r][c]+"\t");
            }
            System.out.println("");
        }
    }
    public static boolean isFull(char[][] board)
    {   for(int r=0; r<board.length; r++)
        {   for(int c=0; c<board[r].length; c++)
            {   if(board[r][c]=='_')    {   return false;   }   }
        }
        return true;
    }
    public static int piecesInCol (char[][] board, int c)
    {   int count=0;
        for(int r=0; r<board.length; r++)
        {
            if(board[r][c]!='_')    {   count++;    }
            else    {   return count;   }
        }
        return count; //if it reaches this point, value returned will indicate that the stack is maxed. 
    }
    public static void dropPiece (char[][] rack, int C, char symb)
    {   rack[piecesInCol(rack,C)][C]=symb;  }
}
