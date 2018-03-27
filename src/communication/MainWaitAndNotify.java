package communication;

public class MainWaitAndNotify {

	private static final int LOOP = 50;

	public static void main(String[] args) {

		Printer2 printer = new Printer2();

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

class Printer2 {

	private static final int SUB_THREAD_EXECUTION_TIMES = 5;
	private static final int MAIN_THREAD_EXECUTION_TIMES = 10;

	private boolean isSubThreadTurn = true;

	synchronized void subThreadPrint() throws InterruptedException {
		
		while (!isSubThreadTurn) {
			this.wait();
		}

		for (int i = 0; i < SUB_THREAD_EXECUTION_TIMES; i++) {
			System.out.println("Sub-thread run: " + i);
		}

		isSubThreadTurn = false;
		this.notify();
	}

	synchronized void mainThreadPrint() throws InterruptedException {
		
		while (isSubThreadTurn) {
			this.wait();
		}

		for (int i = 0; i < MAIN_THREAD_EXECUTION_TIMES; i++) {
			System.out.println("Main-thread run: " + i);
		}

		isSubThreadTurn = true;
		this.notify();
	}
}
