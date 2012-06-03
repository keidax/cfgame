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
    CFLock lock;
    public CFColumn(int numRows, Color bc, CFLock l) {
        super();
        lock=l;
        backgroundColor=bc;
        setLayout(new GridLayout(numRows, 1));
        for(int i=0; i<numRows;i++){
        	CFBox tempBox=new CFBox(backgroundColor, lock);
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
    public synchronized boolean addPiece()
    throws InterruptedException
    {
    	lock.lock();
    	System.out.println("column locked");
        for(int i=0; i<this.getComponentCount(); i++)
        {
            CFBox box=(CFBox) getComponent(i);
            if (gameOver)
            {
                continue;
            }
            else if(i==getComponentCount()-1)
            {   //box is at bottom of column- piece rests here.
                box.addPiece();
                box.setOwner(box.getCurrentPlayer());
                System.out.println("ending current round...");
                ((CFGameGrid) getParent().getParent()).endCurrentRound();
                lock.unlock();
                System.out.println("column unlocked");
                return true;
            }
            else if(i==0 && !box.isEmpty()) //column is already full
            {
            	lock.unlock();
                System.out.println("column unlocked");
                return false;
            }
            else if(((CFBox) getComponent(i+1)).isEmpty()){//box below current box is empty
                box.addPiece();
                Thread.sleep(50);
                box.returnToEmpty();
                continue;
            }
            else
            {   //box below current box is NOT empty- piece rests here.
                box.addPiece();
                box.setOwner(box.getCurrentPlayer());
                System.out.println("ending current round...");
                ((CFGameGrid) getParent().getParent()).endCurrentRound();
                lock.unlock();
                System.out.println("column unlocked");
                return true;
            }
        }
        lock.unlock();
        System.out.println("column unlocked");
        return false;
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
	public void mouseExited(MouseEvent arg0) {;
	}
	public synchronized void mouseEntered(MouseEvent arg0) {
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
