package countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		CountDownLatch countDownLatch = new CountDownLatch(3);
		
		executorService.submit(new Runnable() {
			
			@Override
			public void run() {
				try {
					countDownLatch.await();
					System.out.println("Count is 0.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		executorService.submit(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					try {
						System.out.println("Current count: " + countDownLatch.getCount());
						Thread.sleep(1000);
						countDownLatch.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		executorService.shutdown();
	}
}
