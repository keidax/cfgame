 
import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class CFColumn extends Panel implements Runnable{
    private ArrayList<CFBox> column=new ArrayList<CFBox>(); private boolean gameOver=false;
    Color backgroundColor;
    Gamer cPlayer;
    public CFColumn(int numRows, Color bc) {
        super();
        backgroundColor=bc;
        setLayout(new GridLayout(numRows, 1));
        for(int i=0; i<numRows;i++){
        	CFBox tempBox=new CFBox(backgroundColor);
        	column.add(tempBox);
        	this.add(tempBox);
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
        cPlayer=player; 
    }
    public void addPiece()
    throws InterruptedException
    {   
        CFBox lastBox; 
        for(int i=0; i<column.size(); i++)
        {
            CFBox box=column.get(i);
            if (gameOver)
            {
                continue;
            }
            else if(i==column.size()-1)
            {   //box is at bottom of column- piece rests here.
                box.addPiece(); box.setOwner(box.getCurrentPlayer());
                ((CFGameGrid)getParent()).endCurrentRound(); break;
            }
            else if(i==0 && !box.isEmpty()) //column is already full
            {
                continue;
            }
            else if(column.get(i+1).isEmpty()){//box below current box is empty
                box.addPiece();
                Thread.sleep(200);
                box.returnToEmpty();
                continue;
            }
            else
            {   //box below current box is NOT empty- piece rests here.
                box.addPiece(); box.setOwner(box.getCurrentPlayer());
                ((CFGameGrid) getParent()).endCurrentRound(); break;
            }
        }
        /*
        for(CFBox box:column)
        {            
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
        }*/
        //lastBox.setOwner(cPlayer); 
    }
    public void run() {
        // TODO Auto-generated method stub
        
    }
    public int height() {   return column.size();   }
    public CFBox get (int slot)
    {   return column.get(slot);   }
    public void endGame()
    {   gameOver=true;  }
}
