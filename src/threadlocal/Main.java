package threadlocal;

public class Main {

	private static ThreadLocal<Data> threadLocal = new ThreadLocal<>();

	public static void main(String[] args) {

		for (int i = 0; i < 2; i++) {

			new Thread(new Runnable() {

				@Override
				public void run() {
					double a = Math.random();
					double b = Math.random();
					threadLocal.set(new Data(a, b));
					System.out.println(Thread.currentThread().getName() + " set a: " + a + "; set b: " + b);
					System.out.println(Thread.currentThread().getName() + " get a: " + threadLocal.get().getA() + "; get b: " + threadLocal.get().getB());
				}

			}).start();
		}
	}
}

class Data {
	
	private double a;
	private double b;

	Data(double a, double b) {
		this.a = a;
		this.b = b;
	}

	double getA() {
		return a;
	}

	double getB() {
		return b;
	}
}
