package cfgame;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


@SuppressWarnings("serial")
public class CFGameGrid extends Frame implements WindowListener {
	private ArrayList<CFColumn> columns=new ArrayList<CFColumn>();
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
	public void setCurrentPlayerColor(Color c){
		for(CFColumn col:columns){
			col.setCurrentPlayerColor(c);
		}
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
