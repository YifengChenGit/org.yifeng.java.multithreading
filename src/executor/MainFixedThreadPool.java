package executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainFixedThreadPool {

	private static final int NUM_OF_THREAD = 2;
	private static final int NUM_OF_TASK = 10;

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREAD);

		for (int i = 0; i < NUM_OF_TASK; i++) {

			int task = i;
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + " run task " + task);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}

		executorService.shutdown();
	}
}
