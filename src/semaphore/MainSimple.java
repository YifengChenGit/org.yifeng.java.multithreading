package semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MainSimple {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		Semaphore semaphore = new Semaphore(3);
		
		for (int i = 0; i < 10; i++) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					try {
						semaphore.acquire();
						System.out.println(Thread.currentThread().getName() + " run.");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						semaphore.release();
					}
				}
			});
		}
		
		executorService.shutdown();
	}
}
