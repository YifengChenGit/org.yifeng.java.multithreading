package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainReentrantLock {

	private static final int COUNT_INCREASE_PER_THREAD = 100000;

	public static void main(String[] args) throws InterruptedException {

		Counter1 counter = new Counter1();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < COUNT_INCREASE_PER_THREAD; i++) {
					counter.increase();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < COUNT_INCREASE_PER_THREAD; i++) {
					counter.increase();
				}
			}
		});

		t1.start();
		t2.start();
		t1.join();
		t2.join();
		counter.print();
	}
}

class Counter1 {

	private int count = 0;
	private Lock lock = new ReentrantLock();

	void increase() {
		try {
			lock.lock();
			count++;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	void print() {
		System.out.println(count);
	}
}
