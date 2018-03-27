package future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainExecutorService {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<Integer> future = executorService.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				Thread.sleep(1000);
				return 0;
			}
		});
		executorService.shutdown();
		System.out.println(future.get(2, TimeUnit.SECONDS));
	}
}
