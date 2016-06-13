package com.dexter.cache.job;

/** 
 * @ClassName:	Test 
 * @Description:
 * 测试类，分不使用Cache和使用Cache两种情况进行测试。其中测试中模拟了50-100个
 * 线程来进行并发测试，从结果看效果还是一幕了然的。对于使用Cache的情况，如果并发的
 * 更新操作越少，并发查询的效果越好，因此使用Cache在大量查询的应用中还是有比较大的用处的。 
 * @author:	dexter
 * @date:	2016年5月30日 上午11:06:33 
 *  
 */
public class Test {
	public static void main(String[] args) throws Exception {
//		 testWithoutCache();
		testWithCache();
	}

	private static void testWithoutCache() {
		for (int idx = 1; idx <= 100; idx++) {
			final String thread_name = "thread_" + idx;
			new Thread(new Runnable() {
				public void run() {
					long begin = System.currentTimeMillis();
					JobService jobService = new JobServiceImplWithoutCache();
					jobService.getJobs();
					long end = System.currentTimeMillis();
					System.out.println("Time for " + thread_name + " : "
							+ (end - begin));
				}
			}).start();
		}
	}

	private static void testWithCache() throws Exception {
		for (int idx = 1; idx <= 100; idx++) {
			final String thread_name = "thread_" + idx;
			new Thread(new Runnable() {
				public void run() {
					long begin = System.currentTimeMillis();
					JobService jobService = new JobServiceImplWithCache();
					jobService.getJobs();
					long end = System.currentTimeMillis();
					System.out.println("Time for " + thread_name + " : "
							+ (end - begin));
				}
			}).start();
		}
//		for (int idx = 1; idx < 10; idx++) {
//			final String thread_name = "submit_job_thread_" + idx;
//			final String jobId = "job_" + idx;
//			final String jobName = "job_" + idx;
//			new Thread(new Runnable() {
//				public void run() {
//					long begin = System.currentTimeMillis();
//					JobService jobService = new JobServiceImplWithCache();
//					jobService.submitJob(new Job(jobId, jobName));
//					long end = System.currentTimeMillis();
//					System.out.println("Time for " + thread_name + " : "
//							+ (end - begin));
//				}
//			}).start();
//		}
	}
}
