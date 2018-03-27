package sync;

public class Main {

	private static final int COUNT_INCREASE_PER_THREAD = 10000;

	public static void main(String[] args) throws InterruptedException {

		Counter counter = new Counter();

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

class Counter {

	private int count = 0;

	synchronized void increase() {
		count++;
	}
	
	void print() {
		System.out.println(count);
	}
}
