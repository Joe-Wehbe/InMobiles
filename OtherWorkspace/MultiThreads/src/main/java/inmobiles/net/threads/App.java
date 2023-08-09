package inmobiles.net.threads;

public class App {
	
    public static void main(String[] args) {
    	
    	System.out.println("Started thread main");

    	TimeGenerator tg = new TimeGenerator();
    	Thread t = new Thread(tg);
    	t.start();
    	
    	while(true) {
    		System.out.println("This is thread: " + Thread.currentThread().getName());
    		
    		try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}

    }
}
