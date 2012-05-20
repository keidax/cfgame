package cfgame;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class CFColumn extends Panel implements Runnable{
	private ArrayList<CFBox> column;

	public CFColumn(ArrayList<CFBox> c) {
		super();
		setLayout(new GridLayout(7, 1));
		column = c;
		Iterator<CFBox> iter = column.iterator();
		while (iter.hasNext()) {
			this.add(iter.next());
		}
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {
				Color notificationColor=getBackground().darker();
				setBackground(notificationColor);
			}
			public void mouseExited(MouseEvent arg0) {
				Color restoreColor=getParent().getBackground();
				setBackground(restoreColor);
			}
		});
	}
	public void setBackground(Color c){
		super.setBackground(c);
		for(CFBox box:column){
			box.setBackground(c);
		}
	}
	public void setCurrentPlayer(Gamer player){
		for(CFBox box:column){
			box.setCurrentPlayer(player);
		}
	}
	public void addPiece()
	throws InterruptedException{
		for(CFBox box:column){
			
			if(column.indexOf(box)==column.size()-1){//box is at bottom of column
				box.addPiece();
				((CFGameGrid) getParent()).endCurrentRound();
			}
			else if(column.indexOf(box)==0 && !box.isEmpty()){//column is already full
				continue;
			}
			else if(column.get(column.indexOf(box)+1).isEmpty()){//box below current box is empty
				box.addPiece();
				Thread.sleep(200);
				box.returnToEmpty();
				continue;
			}
			else{
				box.addPiece();
				((CFGameGrid) getParent()).endCurrentRound();
			}
		}
	}
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
