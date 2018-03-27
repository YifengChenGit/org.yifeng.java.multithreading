package semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class MainReentrantLockFeature {

	public static void main(String[] args) throws InterruptedException {

		ExecutorService executorService = Executors.newFixedThreadPool(3);
		Counter counter = new Counter();
		for (int i = 0; i < 3; i++) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					for(int j = 0; j < 100000; j++) {
						counter.increaes();
					}
				}
			});
		}
		executorService.shutdown();
		executorService.awaitTermination(10, TimeUnit.SECONDS);
		counter.print();
	}
}

class Counter {
	
	private int count = 0;
	private Semaphore semaphore = new Semaphore(1);
	
	void increaes() {
		try {
			semaphore.acquire();
			count++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
	
	void print() {
		System.out.println(count);
	}
}
