package cfgame;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class CFBox extends Canvas {
    private Gamer currentPlayer;
    private boolean isOccupied;
    private Gamer owner; 
    public CFBox() {
        super();
        isOccupied=false;
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent arg0) {
                try {
                    ((CFColumn) getParent()).addPiece();
                    
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
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
    //This is a test! Editing within GitHub!
    //int testInt =0;
    //testInt++;
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
    
    
}
