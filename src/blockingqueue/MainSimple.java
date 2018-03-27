package blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MainSimple {

	private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep(100);
							System.out.println(Thread.currentThread().getName() + " is ready to put a value");
							queue.put(1);
							System.out.println(Thread.currentThread().getName() + " has put a value, current queue size " + queue.size());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
		
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep(1000);
							System.out.println(Thread.currentThread().getName() + " is ready to take a value");
							queue.take();
							System.out.println(Thread.currentThread().getName() + " has taken a value, current queue size " + queue.size());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
}
