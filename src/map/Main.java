package map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

	private static Map<Thread, Double> map = new ConcurrentHashMap<>();

	public static void main(String[] args) {

		for (int i = 0; i < 2; i++) {

			new Thread(new Runnable() {

				@Override
				public void run() {
					double value = Math.random();
					map.put(Thread.currentThread(), value);
					System.out.println(Thread.currentThread().getName() + " set value: " + value);
					System.out.println(Thread.currentThread().getName() + " get value: " + map.get(Thread.currentThread()));
				}

			}).start();
		}
	}
}
