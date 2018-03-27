package future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class MainCompletionService {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
		for (int i = 0; i < 10; i++) {
			int value = i;
			completionService.submit(new Callable<Integer>() {
				
				@Override
				public Integer call() throws Exception {
					int sleep = new Random().nextInt(5000);
					Thread.sleep(sleep);
					System.out.println("Value: " + value + "; sleep " + sleep);
					return value;
				}
			});
		}
		executorService.shutdown();

		for (int i = 0; i < 10; i++) {
			System.out.println(completionService.take().get());
		}
	}
}
