package cfgame;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class CFGameGrid extends Frame implements WindowListener {
	private ArrayList<CFColumn> columns=new ArrayList<CFColumn>();
	Gamer currentPlayer=null;
	boolean currentTurnOver=false;
	private Gamer[] players=new Gamer[2];
	public CFGameGrid(String title) {
		super(title);
		for(int i=0; i<6;i++){
			ArrayList<CFBox> tempList=new ArrayList<CFBox>();
			for(int j=0; j<7;j++){
				tempList.add(new CFBox());
			}
			CFColumn temp=new CFColumn(tempList);
			columns.add(temp);
		}
		setLayout(new GridLayout(1,6,0,0));
		addWindowListener(this);
		Iterator<CFColumn> iter=columns.iterator();
		while(iter.hasNext()){
			add(iter.next());
		}
		setSize(400, 400);
		setVisible(true);
	}
	public synchronized void game()
    {
		players[0]=new Gamer("player",'p');
        players[0].setColor(Color.CYAN);
        players[1]=new Gamer("comp",'c');
        players[1].setColor(Color.ORANGE);
        for(int p=0; !isGameOver(); p=1-p)
        {
        	changePlayer(players[p]);
        	System.out.println("players changed");
        	while(!isRoundOver()){
        		try {
                    wait();
                } catch (InterruptedException e2) {}

        	}
        	System.out.println("turn over");
        	
        }
    }

	public void setCurrentPlayer(Gamer player){
		for(CFColumn col:columns){
			col.setCurrentPlayer(player);
		}
	}
	
	public boolean isRoundOver(){
		return currentTurnOver;
	}
	public boolean isGameOver(){
		//TODO: Implement this to check the gameboard and determine if the game has been won/tied
		return false;
	}
	public boolean isGridFull(){
    	//TODO implement this to actually check grid and return a value
    	
    	return false;
    }
	public void changePlayer(Gamer player){
		currentTurnOver=false;
		currentPlayer=player;
		setCurrentPlayer(currentPlayer);
	}
	public synchronized void endCurrentRound(){
		System.out.println("gamegrid turn ended");
		currentTurnOver=true;
		notifyAll();
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
