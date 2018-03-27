package communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainCondition {

	private static final int LOOP = 50;

	public static void main(String[] args) {

		Printer1 printer = new Printer1();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					try {
						printer.subThreadPrint();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}).start();

		for (int i = 0; i < LOOP; i++) {
			try {
				printer.mainThreadPrint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Printer1 {

	private static final int SUB_THREAD_EXECUTION_TIMES = 5;
	private static final int MAIN_THREAD_EXECUTION_TIMES = 10;

	private boolean isSubThreadTurn = true;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	void subThreadPrint() throws InterruptedException {
		try {

			lock.lock();
			while (!isSubThreadTurn) {
				condition.await();
			}

			for (int i = 0; i < SUB_THREAD_EXECUTION_TIMES; i++) {
				System.out.println("Sub-thread run: " + i);
			}

			isSubThreadTurn = false;
			condition.signal();

		} finally {
			lock.unlock();
		}
	}

	void mainThreadPrint() throws InterruptedException {
		try {

			lock.lock();
			while (isSubThreadTurn) {
				condition.await();
			}

			for (int i = 0; i < MAIN_THREAD_EXECUTION_TIMES; i++) {
				System.out.println("Main-thread run: " + i);
			}

			isSubThreadTurn = true;
			condition.signal();

		} finally {
			lock.unlock();
		}
	}
}
