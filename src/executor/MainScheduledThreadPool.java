package executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainScheduledThreadPool {

	private static final int NUM_OF_THREAD = 1;

	public static void main(String[] args) {

		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(NUM_OF_THREAD);
		executorService.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " run task");
			}
		}, 3, 1, TimeUnit.SECONDS);
	}
}
