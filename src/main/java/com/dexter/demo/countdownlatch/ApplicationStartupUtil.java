package com.dexter.demo.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ApplicationStartupUtil {
	// List of service checkers
	private static List<BaseHealthChecker> _services;

	// This latch will be used to wait on
	private static CountDownLatch _latch;

	private ApplicationStartupUtil() {
	}

	private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();

	public static ApplicationStartupUtil getInstance() {
		return INSTANCE;
	}

	public static boolean checkExternalServices() throws Exception {
		// Initialize the latch with number of service checkers
		_latch = new CountDownLatch(3);

		// All add checker in lists
		_services = new ArrayList<BaseHealthChecker>();
		_services.add(new NetworkHealthChecker(_latch));
		_services.add(new CacheHealthChecker(_latch));
		_services.add(new DatabaseHealthChecker(_latch));

		// Start service checkers using executor framework
		 //设置特定的线程池
		Executor executor = Executors.newFixedThreadPool(_services.size());

		for (final BaseHealthChecker v : _services) {
			executor.execute(v);  //分配线程
		}
		// Now wait till all services are checked
		//调用一个CountDownLatch对象的await()方法，
		//其他的任务执行完自己的任务后调用同一个CountDownLatch对象上的countDown()方法，
		//这个调用await()方法的任务将一直阻塞等待，直到这个CountDownLatch对象的计数值减到0为止。
		_latch.await();
		System.out.println("CountDownLatch里面的线程执行完了");
		// Services are file and now proceed startup
		for (final BaseHealthChecker v : _services) {
			if (!v.isServiceUp()) {
				return false;
			}
		}
		return true;
	}
}