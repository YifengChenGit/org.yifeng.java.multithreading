package timer;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

	private static final long DELAY_MILLIS = 5000;
	private static final long PERIOD_MILLIS = 1000;
	
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("Timer task run");
			}
		}, DELAY_MILLIS, PERIOD_MILLIS);
	}
}
