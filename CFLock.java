
public class CFLock {
	boolean isLocked;
	
	public synchronized void lock(){
		while(isLocked){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isLocked=true;
	}
	
	public synchronized void unlock(){
		isLocked=false;
		notify();
	}
}
