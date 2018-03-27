package thread;

public class Main {

	public static void main(String[] args) {
		startThreadByOverrideRunFunction();
		startThreadByPassRunnableField();
	}

	private static void startThreadByOverrideRunFunction() {
		new Thread() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " run.");
			}
		}.start();
	}

	private static void startThreadByPassRunnableField() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " run.");
			}
		}).start();
	}
}
