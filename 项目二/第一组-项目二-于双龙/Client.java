import java.util.concurrent.CountDownLatch;

public class Client {
	
	private int numOfClient=0;

	public Client(int num) {
		numOfClient=num;
	}
	
	public int getNumOfClient() {
		return numOfClient;
	}
	
	public void startThread() {
		
		final CountDownLatch count = new CountDownLatch(1); 

		for(int i=0;i<numOfClient;i++) {
			ClientThread ct = new ClientThread(count);		
			Thread t = new Thread(ct);
			t.start();			
		}	
		
		count.countDown();		
		
	}

}
