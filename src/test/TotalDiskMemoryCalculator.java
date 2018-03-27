package test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TotalDiskMemoryCalculator {
	
	private static DiskRepository diskRepository = new DiskRepository();

	public static void main(String[] args) throws InterruptedException {
		
		AtomicInteger totalDiskMemoryInGb = new AtomicInteger(0);
		CountDownLatch diskMemoryCountDownLatch = new CountDownLatch(Disk.values().length);
		
		ExecutorService executorService = Executors.newFixedThreadPool(Disk.values().length);
		for (Disk disk : Disk.values()) {
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					int diskMemoryInGb = diskRepository.getDiskMemoryInGb(disk);
					totalDiskMemoryInGb.addAndGet(diskMemoryInGb);
					diskMemoryCountDownLatch.countDown();
				}
			});
		}
		executorService.shutdown();
		
		diskMemoryCountDownLatch.await();
		System.out.println(totalDiskMemoryInGb.get());
	}
}
