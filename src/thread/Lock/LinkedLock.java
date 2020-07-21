package thread.Lock;

public class LinkedLock {

  private int insert = 0;
  private int search = 0;
  private int remove = 0;
  private int removeRequests = 0;
   
 
  public synchronized void lockInsert() throws InterruptedException{
    insert++;
  }
  
  public synchronized void unlockInsert(){
    insert--;
  }
  
  public synchronized void lockSearch() throws InterruptedException{
    while(remove > 0 || removeRequests > 0 ){
      wait();
    }
    search++;
  }

  public synchronized void unlockSearch(){
    search--;
    notifyAll();
}
 
  public synchronized void lockRemove() throws InterruptedException{
	removeRequests++;

    while(search > 0){
      wait();
    }
    removeRequests--;
    remove++;
  }

  public synchronized void unlockRemove() throws InterruptedException{
	remove--;
    notifyAll();
  }
}