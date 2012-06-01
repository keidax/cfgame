import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class CFBox extends Canvas implements MouseListener{
    private Gamer currentPlayer;
    private boolean isOccupied;
    private Gamer owner;
    Color backgroundColor;
    CFLock lock;
    public CFBox(Color bc, CFLock l) {
        super();
        lock =l;
        backgroundColor=bc;
        isOccupied=false;
        this.addMouseListener(this);
    }

    public void paint(Graphics g) {
        drawCircle(g);
    }
    public void returnToEmpty(){
        isOccupied=false;
        update(getGraphics());
    }
    private void drawCircle(Graphics g){
        if(isOccupied){
            g.setColor(currentPlayer.getColor());
        }else{
            g.setColor(Color.WHITE);
        }
        //g.fillOval(3, 3, this.getWidth() - 6, this.getHeight() - 6);
        int sideLength=Math.min(this.getWidth()-6, this.getHeight()-6);
        int extraX=this.getWidth()-sideLength;
        int extraY=this.getHeight()-sideLength;
        g.fillOval(extraX/2, extraY/2, sideLength, sideLength);
    }
    public boolean isEmpty(){
        return !isOccupied;
    }
    public void addPiece(){
        isOccupied=true;
        update(getGraphics());
    }
    public void setCurrentPlayer(Gamer player){
        if(isOccupied){
            return;
        }
        currentPlayer=player;
    }
    public void setOwner(Gamer player)
    {
        if(isOccupied){ owner=player;    }
        else    return;
    }
    
    public Gamer getCurrentPlayer()  {   return currentPlayer;   }
    
    public Gamer getOwner() {   return owner;   }
    public void mouseClicked(MouseEvent arg0) {
    	System.out.println("mouse clicked");
        try {
        	System.out.println("unlocking box...");
        	lock.unlock();
        	System.out.println("box unlocked");
			((CFColumn) getParent()).addPiece();
			System.out.println("relocking box...");
			lock.lock();
			System.out.println("box relocked");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
    public void mouseEntered(MouseEvent arg0) {
    	try {
			lock.lock();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
    	System.out.println("mouse entered; locked");
        getParent().setBackground(backgroundColor.darker());
        System.out.println("waiting...");
    }
    public void mouseExited(MouseEvent arg0) {
    	System.out.println("mouse exited; unlocked");
        getParent().setBackground(backgroundColor.brighter());
        lock.unlock();
    }
    
}
