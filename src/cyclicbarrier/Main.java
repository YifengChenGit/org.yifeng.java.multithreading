package cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
		
		for (int i = 0; i < 3; i++) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					try {
						
						System.out.println(Thread.currentThread().getName() + " run 1");
						Thread.sleep(1000);
						cyclicBarrier.await();
						
						System.out.println(Thread.currentThread().getName() + " run 2");
						Thread.sleep(1000);
						cyclicBarrier.await();
						
						System.out.println(Thread.currentThread().getName() + " run 3");
						Thread.sleep(1000);
						cyclicBarrier.await();
						
						System.out.println(Thread.currentThread().getName() + " run 4");
						Thread.sleep(1000);
						cyclicBarrier.await();
						
					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		executorService.shutdown();
	}
}
