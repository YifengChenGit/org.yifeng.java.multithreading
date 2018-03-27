package blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MainCommunicate {

	public static void main(String[] args) throws InterruptedException {
		
		Printer printer = new Printer();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					try {
						printer.subThreadPrint();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		for (int i = 0; i < 50; i++) {
			printer.mainThreadPrint();
		}
	}
}

class Printer {
	
	private final BlockingQueue<Integer> subThreadQueue = new ArrayBlockingQueue<>(1);
	private final BlockingQueue<Integer> mainThreadQueue = new ArrayBlockingQueue<>(1);

	public Printer() throws InterruptedException {
		subThreadQueue.put(1);
	}
	
	void subThreadPrint() throws InterruptedException {
		subThreadQueue.take();
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
		mainThreadQueue.put(1);
	}
	
	void mainThreadPrint() throws InterruptedException {
		mainThreadQueue.take();
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
		subThreadQueue.put(1);
	}
}
