package cal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimplePoolSizeCaculatorImpl extends PoolSizeCalculator {

	@Override
	protected Runnable creatTask() {
		return new AsyncIOTask();
	}

	@Override
	protected BlockingQueue createWorkQueue() {
		return new LinkedBlockingQueue(1000);
	}

	@Override
	protected long getCurrentThreadCPUTime() {
		return ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
	}

	public static void main(String[] args) {
		PoolSizeCalculator poolSizeCalculator = new SimplePoolSizeCaculatorImpl();
		poolSizeCalculator.calculateBoundaries(new BigDecimal(1.0), new BigDecimal(100000));
	}

}
