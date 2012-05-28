import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class CFPopup extends Dialog{
	public CFPopup(Frame f, boolean modal){
		super(f, modal);
		setResizable(false);
		setUndecorated(false);
		/*
		this.add(closeButton);
		this.setBounds(400, 800, 90, 90);
		*/
	}
	public void setVisible(boolean b){
		if(b){
			((GridLayout) this.getLayout()).setColumns(5);
			Button closeButton=new Button("Enter");
			closeButton.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent arg0) {
					setVisible(false);
			        dispose();
				}
				public void mouseEntered(MouseEvent arg0) {}
				public void mouseExited(MouseEvent arg0) {}
				public void mousePressed(MouseEvent arg0) {}
				public void mouseReleased(MouseEvent arg0) {}
				
			});
			this.add(closeButton);
			this.setBounds(30, 30, 100, 200);
			super.setVisible(b);
		}
		
	}
}
