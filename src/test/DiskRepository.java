package test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DiskRepository {

	private static final Map<Disk, Integer> mockDiskMemoryInGbMap = new ConcurrentHashMap<>();

	static {
		mockDiskMemoryInGbMap.put(Disk.C, 10);
		mockDiskMemoryInGbMap.put(Disk.D, 20);
		mockDiskMemoryInGbMap.put(Disk.E, 30);
		mockDiskMemoryInGbMap.put(Disk.F, 40);
	}

	int getDiskMemoryInGb(Disk disk) {
		return mockDiskMemoryInGbMap.get(disk);
	}
}
