package inmobiles.net.threads;

import java.time.LocalTime;

public class TimeGenerator implements Runnable{

	public void run(){
		
		System.out.println("Started runnable on thread: " + Thread.currentThread().getName());
		while(true) {
			LocalTime time = LocalTime.now();
			System.out.println(time);
			
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
