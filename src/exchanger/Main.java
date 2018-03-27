package exchanger;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		Exchanger<Integer> exchanger = new Exchanger<>();
		
		for (int i = 0; i < 2; i++) {
			
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					try {
						int value = new Random().nextInt();
						System.out.println(Thread.currentThread().getName() + " original value: " + value);
						value = exchanger.exchange(value);
						System.out.println(Thread.currentThread().getName() + " new value: " + value);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		executorService.shutdown();
	}
}
