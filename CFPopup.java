import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class CFPopup extends Dialog{
	private TextField field1=null, field2=null; int width=100, height=150;
	public CFPopup(Frame f, boolean modal){
		super(f, modal);
		setResizable(false);
		setUndecorated(false);
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridLayout(5,1));
        add(new Label("Player 1 Name:"));
        field1=new TextField("",30);
        add(field1);
        add(new Label("Player 2 Name:"));
        field2=new TextField("",30);
        add(field2);
		Button closeButton=new Button("Enter");
		closeButton.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				if(!(field1.getText().isEmpty() && field2.getText().isEmpty()) 
						&& !(field1.getText().equalsIgnoreCase(field2.getText()))){
		        	dispose();
				}
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		this.add(closeButton);
		//this.setBounds(30, 30, 100, 150);
		this.setBounds(f.getX()+f.getWidth()/2-width/2, f.getY()+f.getHeight()/2-height/2, width, height);
		((GridLayout) getLayout()).setVgap(5);
	}
	public String getPlayer1Name(){
		return field1.getText();
	}
	public String getPlayer2Name(){
		return field2.getText();
	}
}
