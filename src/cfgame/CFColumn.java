package cfgame;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class CFColumn extends Panel {
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
	public void setCurrentPlayerColor(Color c){
		for(CFBox box:column){
			box.setCurrentPlayerColor(c);
		}
	}
	public boolean addPiece(Gamer g){
		for(CFBox box:column){
			
			if(column.indexOf(box)==column.size()-1){
				box.addPiece(g);
				return true;
			}
			else if(column.indexOf(box)==0 && !box.isEmpty()){
				return false;
			}
			else if(column.get(column.indexOf(box)+1).isEmpty()){
				box.setCurrentPlayerColor(g.getColor());
				wait(300);
				box.returnToEmpty();
				continue;
			}
			else{
				box.addPiece(g);
				return true;
			}
		}
		return false;
	}
	private void wait(int millis){
		long timeEnd, timeStart;
		timeStart=timeEnd=System.currentTimeMillis();
		while(timeEnd-timeStart<millis){
			timeEnd=System.currentTimeMillis();
		}
	}
}
