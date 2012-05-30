import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class CFColumn extends Panel implements MouseListener{
    private boolean gameOver=false;
    Color backgroundColor;
    Gamer cPlayer;
    public CFColumn(int numRows, Color bc) {
        super();
        backgroundColor=bc;
        setLayout(new GridLayout(numRows, 1));
        for(int i=0; i<numRows;i++){
        	CFBox tempBox=new CFBox(backgroundColor);
        	this.add(tempBox);
        	tempBox.setVisible(true);
        }
        addMouseListener(this);
    }
    public void setBackground(Color c){
        super.setBackground(c);
        for(int i=0; i<getComponentCount(); i++){
            getComponent(i).setBackground(c);
        }
    }
    public void setCurrentPlayer(Gamer player){
    	for(int i=0; i<getComponentCount(); i++){
    		((CFBox) getComponent(i)).setCurrentPlayer(player);
        }
        cPlayer=player; 
    }
    public synchronized void addPiece()
    throws InterruptedException
    {
        for(int i=0; i<this.getComponentCount(); i++)
        {
            CFBox box=(CFBox) getComponent(i);
            if (gameOver)
            {
                continue;
            }
            else if(i==getComponentCount()-1)
            {   //box is at bottom of column- piece rests here.
                box.addPiece(); box.setOwner(box.getCurrentPlayer());
                //((CFGameGrid)getParent()).endCurrentRound(); 
                notifyAll();
                getParent().notifyAll();
                break;
            }
            else if(i==0 && !box.isEmpty()) //column is already full
            {
                continue;
            }
            else if(((CFBox) getComponent(i+1)).isEmpty()){//box below current box is empty
                box.addPiece();
                Thread.sleep(50);
                box.returnToEmpty();
                continue;
            }
            else
            {   //box below current box is NOT empty- piece rests here.
                box.addPiece(); box.setOwner(box.getCurrentPlayer());
                //((CFGameGrid) getParent()).endCurrentRound(); 
                notifyAll();
                break;
            }
        }
    }
    public int height() {   return getComponentCount();   }
    public CFBox get (int slot)
    {   return (CFBox) getComponent(slot);   }
    public void endGame()
    {   gameOver=true;  }
    public boolean isFull(){
    	for(CFBox box:getComponents()){
    		if(box.isEmpty())
            {   
                return false;
            }
    	}
    	return true;
    }
	public void mouseClicked(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {
		Color restoreColor=getParent().getBackground();
        setBackground(restoreColor);
	}
	public synchronized void mouseEntered(MouseEvent arg0) {
		Color notificationColor=getBackground().darker();
        setBackground(notificationColor);
	}
	/*public CFBox getComponent(int num){
		return (CFBox) this.getComponent(num);
	}*/
	public CFBox[] getComponents(){
		CFBox[] boxArray=new CFBox[getComponentCount()];
		for(int i=0; i<getComponentCount(); i++){
			boxArray[i]=(CFBox) getComponent(i);
		}
		return boxArray;
	}
}
