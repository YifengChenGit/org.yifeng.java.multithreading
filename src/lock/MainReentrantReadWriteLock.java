package lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainReentrantReadWriteLock {

	private static final int THREAD_NUM = 2;
	private static final int EXECUTION_PER_THREAD = 10000;

	public static void main(String[] args) {

		Counter2 counter = new Counter2();

		for (int i = 0; i < THREAD_NUM; i++) {

			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int i = 0; i < EXECUTION_PER_THREAD; i++) {
						counter.increase();
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

			}).start();

			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int i = 0; i < EXECUTION_PER_THREAD; i++) {
						counter.print();
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

			}).start();
		}
	}
}

class Counter2 {

	private int count = 0;
	private ReadWriteLock lock = new ReentrantReadWriteLock();

	void increase() {
		try {
			lock.writeLock().lock();
			count++;
		} finally {
			lock.writeLock().unlock();
		}
	}

	void print() {
		try {
			lock.readLock().lock();
			System.out.println(count);
		} finally {
			lock.readLock().unlock();
		}
	}
}
