package cfgame;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class CFBox extends Canvas {
	private Color currentPlayerColor;
	private boolean isOccupied;
	public CFBox() {
		super();
		currentPlayerColor=Color.WHITE;
		isOccupied=false;
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				((CFColumn) getParent()).addPiece(Connect4Game.getCurrentGamer());
			}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {
				Color notificationColor=getBackground().darker();
				getParent().setBackground(notificationColor);
			}
			public void mouseExited(MouseEvent arg0) {
				Color restoreColor=getParent().getParent().getBackground();
				getParent().setBackground(restoreColor);
			}
		});
	}

	public void paint(Graphics g) {
		drawCircle(g);
		
	}
	public Color getCurrentPlayerColor(){return currentPlayerColor;}
	public void setCurrentPlayerColor(Color c){currentPlayerColor = c;}
	public void returnToEmpty(){currentPlayerColor = Color.WHITE;}
	private void drawCircle(Graphics g){
		g.setColor(currentPlayerColor);
		//g.fillOval(3, 3, this.getWidth() - 6, this.getHeight() - 6);
		int sideLength=Math.min(this.getWidth()-6, this.getHeight()-6);
		int extraX=this.getWidth()-sideLength;
		int extraY=this.getHeight()-sideLength;
		g.fillOval(extraX/2, extraY/2, sideLength, sideLength);
	}
	public boolean isEmpty(){
		return !isOccupied;
	}
	public void addPiece(Gamer g){
		setCurrentPlayerColor(g.getColor());
		isOccupied=true;
		update(getGraphics());
	}
}
