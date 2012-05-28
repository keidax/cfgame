import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class CFGameGrid extends Frame implements WindowListener {
    private ArrayList<CFColumn> columns=new ArrayList<CFColumn>();
    Gamer currentPlayer=null;
    boolean currentTurnOver=false;
    private Gamer[] players=new Gamer[2];
    Color backgroundColor=Color.BLUE;
    public CFGameGrid(String title, int numRows, int numColumns) {
        super(title);
        setBackground(backgroundColor);
        setLayout(new GridLayout(1,numColumns,0,0));
        for(int i=0; i<numColumns;i++){
        	CFColumn tempColumn=new CFColumn(numRows, backgroundColor);
            columns.add(tempColumn);
            this.add(tempColumn);
        }
        addWindowListener(this);
        setSize(400, 400);
        setVisible(true);
        getPlayerInfo();
        game();
    }
    
    public synchronized void game()
    {
    	System.out.println("Game STARTED!!!");
    	update(getGraphics());
        for(int p=0; !isGameOver(); p=1-p)
        {
            changePlayerTo(players[p]);
            System.out.println("It is now "+players[p].getName()+"'s turn:");
            while(!isRoundOver()){
                try {
                    wait();
                } catch (InterruptedException e2) {}

            }
            System.out.println(players[p].getName()+"'s turn is over.");
            
        }
        System.out.println("The game is over.");
        //somehow prevent the rack from being changed further once game is over...
        if(isGridFull())  
        {
            System.out.println("The grid is full-- IT'S A TIE!!!"); 
        }
        else //someone is the winner
        {
            System.out.println(players[VictorNum()].getName()+" WINS!!!");
        }
        for(int i=0; i<this.getComponentCount(); i++) 
        {   
            getComponent(i).endGame();
        }
    }
    @SuppressWarnings("unused")
	private void getPlayerInfo(){
    	
    	String nom;
        Scanner kbReader=new Scanner(System.in);
        for(int p=1; (p-1)<players.length; p++)
        {
            System.out.println("Please enter Player #"+p+":");
            System.out.print("Name:\t"); nom=kbReader.nextLine();
            //perhaps have a method by which players choose their colors. 
            players[p-1]=new Gamer(nom);
        }
    	 
		Container inputHolder=new Container();
    	//inputHolder.add(new TextComponent("Player 1"));
        //CFPopup inputWindow = new CFPopup(this);
        players[0].setColor(Color.CYAN);
        players[1].setColor(Color.ORANGE);
        System.out.println("Player colors set");
    }
    public void setCurrentPlayer(Gamer player){
        for(int i=0; i<getComponentCount(); i++)
        {
            getComponent(i).setCurrentPlayer(player);
        }
    }
    
    public boolean isRoundOver(){
        return currentTurnOver;
    }
    
    public boolean isGameOver()
    {
        if(!(VictorNum()==-1)||isGridFull())   {   return true;    }
        else return false;
    }
    
    public int VictorNum() //precondition: p1 is the number of one of the two players in the player array
    {   
        //TODO: Implement this to check the gameboard and determine if the game has been won/tied
        int pn;
        for(pn=0; pn<=1; pn++) 
        {
            Gamer inQ=players[pn];
            int c,r,count = 0;
            for(c=0; c<columns.size(); c++) //test for vertical victory
            {
                for(r=0; r<((CFColumn)columns.get(c)).height(); r++)
                {
                    if(!((CFBox)((CFColumn)columns.get(c)).get(r)).isEmpty())
                    {   
                        if(inQ.getColor()==((CFBox)((CFColumn)columns.get(c)).get(r)).getOwner().getColor())
                        {   count++; }
                        else    count=0;
                    }
                    else    count=0;
                    if(count==4)    {   return pn;    }
                }
                count=0;
            }
            c=0; 
            for(r=0; r<((CFColumn)columns.get(0)).height(); r++) //test for horizontal victory
            {
                for(c=0; c<columns.size(); c++)
                {
                    if(!((CFBox)((CFColumn)columns.get(c)).get(r)).isEmpty())
                    {   
                        if(inQ.getColor()==((CFBox)((CFColumn)columns.get(c)).get(r)).getOwner().getColor())
                        {   count++; }
                        else    count=0;
                    }
                    else    count=0;
                    if(count==4)    {   return pn;    }
                }
                count=0;
            }
            c=0; 
            //test for diagonal type-1 (/)
            for(r=3; r<((CFColumn)columns.get(0)).height()-1; r++) //the last row will be checked at the first step of next loop
            {
                for(c=0; c<columns.size() && (r-c)>=0; c++)
                {
                    if(!((CFBox)((CFColumn)columns.get(c)).get(r-c)).isEmpty())
                    {   
                        if(inQ.getColor()==((CFBox)((CFColumn)columns.get(c)).get(r-c)).getOwner().getColor())
                        {   count++; }
                        else    count=0;
                    }
                    else    count=0;
                    if(count==4)    {   return pn;    }
                }
                count=0;
            }
            for(c=0; c<=columns.size()-4; c++)
            {
                for(r=((CFColumn)columns.get(0)).height()-1; r>=0 && (c+((CFColumn)columns.get(0)).height()-1-r)<columns.size(); r--)
                {
                    if(!((CFBox)((CFColumn)columns.get(c+((CFColumn)columns.get(0)).height()-1-r)).get(r)).isEmpty())
                    {   
                        if(inQ.getColor()==((CFBox)((CFColumn)columns.get(c+((CFColumn)columns.get(0)).height()-1-r)).get(r)).getOwner().getColor())
                        {   count++; }
                        else    count=0;
                    }
                    else    count=0;
                    if(count==4)    {   return pn;    }
                }
                count=0;
            }
            //test for diagonal type-2 (\)
            for(c=3; c<columns.size(); c++)
            {
                for(r=((CFColumn)columns.get(0)).height()-1; r>=0 && (c-((CFColumn)columns.get(0)).height()+1+r)>=0; r--)
                {
                    if(!((CFBox)((CFColumn)columns.get(c-((CFColumn)columns.get(0)).height()+1+r)).get(r)).isEmpty())
                    {   
                        if(inQ.getColor()==((CFBox)((CFColumn)columns.get(c-((CFColumn)columns.get(0)).height()+1+r)).get(r)).getOwner().getColor())
                        {   count++; }
                        else    count=0;
                    }
                    else    count=0;
                    if(count==4)    {   return pn;    }
                }
                count=0;
            }
            for(r=((CFColumn)columns.get(0)).height()-2; r>=3; r--)
            {
                for(c=columns.size()-1; c>=0 && (r-columns.size()+1+c) >=0; c--)
                {
                    if(!((CFBox)((CFColumn)columns.get(c)).get(r-columns.size()+1+c)).isEmpty())
                    {   
                        if(inQ.getColor()==((CFBox)((CFColumn)columns.get(c)).get(r-columns.size()+1+c)).getOwner().getColor())
                        {   count++; }
                        else    count=0;
                    }
                    else    count=0;
                    if(count==4)    {   return pn;    }
                }
                count=0;
            }
            /*
            //test for diagonal type-1
            for(r=((CFColumn)columns.get(0)).height()-4; r>0; r--) //r=0 will occur as first step of next loop
            {
                for(c=0; c<columns.size() && (r-c)>=0; c++)
                {
                    if(!((CFBox)((CFColumn)columns.get(c)).get(r)).isEmpty())
                    {   
                        if(inQ.getColor()==((CFBox)((CFColumn)columns.get(c)).get(r)).getOwner().getColor())
                        {   count++; }
                        else    count=0;
                    }
                    else    count=0;
                    if(count==4)    {   return pn;    }
                }
                count=0;
            }
            for(c=0; c<=columns.size()-4; c++)
            {
                for(r=0; r<((CFColumn)columns.get(0)).height() && (c+r)<columns.size(); r++)
                {
                    if(!((CFBox)((CFColumn)columns.get(c)).get(r)).isEmpty())
                    {   
                        if(inQ.getColor()==((CFBox)((CFColumn)columns.get(c)).get(r)).getOwner().getColor())
                        {   count++; }
                        else    count=0;
                    }
                    else    count=0;
                    if(count==4)    {   return pn;    }
                }
                count=0;
            }
            //test for diagonal type-2
            for(c=3; c<columns.size(); c++)
            {
                for(r=0; r<((CFColumn)columns.get(0)).height() && (c-r)>=0; r++)
                {
                    if(!((CFBox)((CFColumn)columns.get(c)).get(r)).isEmpty())
                    {   
                        if(inQ.getColor()==((CFBox)((CFColumn)columns.get(c)).get(r)).getOwner().getColor())
                        {   count++; }
                        else    count=0;
                    }
                    else    count=0;
                    if(count==4)    {   return pn;    }
                }
                count=0;
            }
            for(r=1; r<((CFColumn)columns.get(0)).height()-4; r++)
            {
                for(c=0; c>=0 && (r+c) <((CFColumn)columns.get(0)).height(); c++)
                {
                    if(!((CFBox)((CFColumn)columns.get(c)).get(r)).isEmpty())
                    {   
                        if(inQ.getColor()==((CFBox)((CFColumn)columns.get(c)).get(r)).getOwner().getColor())
                        {   count++; }
                        else    count=0;
                    }
                    else    count=0;
                    if(count==4)    {   return pn;    }
                }
                count=0;
            }*/
        }
        return -1;
    }
    public boolean isGridFull(){
        for(CFColumn column:getComponents())
        {
        	if(!column.isFull()){
        		System.out.println("Grid not full.");
        		return false;
        	}
        }
        System.out.println("Grid full!");
        return true;
    }
    public void changePlayerTo(Gamer player){
        currentTurnOver=false;
        currentPlayer=player;
        setCurrentPlayer(currentPlayer);
    }
    public synchronized void endCurrentRound(){
        System.out.println("gamegrid turn ended");
        currentTurnOver=true;
        notifyAll();
    }
    public CFColumn getComponent(int num){
		return (CFColumn) this.getComponent(num);
	}
    public CFColumn[] getComponents(){
		CFColumn[] colArray=new CFColumn[getComponentCount()];
		for(int i=0; i<getComponentCount(); i++){
			colArray[i]=getComponent(i);
		}
		return colArray;
	}
    public void windowClosing(WindowEvent e) {
        setVisible(false);
        dispose();
        System.exit(0);
    }
    public void windowClosed(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
}
