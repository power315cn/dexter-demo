package com.dexter.cache.job;

import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName:	BackendJobManager 
 * @Description:
 * BackendJobManager.java是用来模拟后台JNI代码的，其中三个方法都是同步方法，
 * 并且在getJobs()方法里故意sleep(1000)来模拟后台API调用开销。
 * @author:	dexter
 * @date:	2016年5月30日 上午11:05:49 
 *  
 */
public class BackendJobManager {
	private static int id = 1;

	public static synchronized boolean submitJob(Job job) {
		return true;
	}

	public static synchronized boolean killJob(Job job) {
		return true;
	}

	public static synchronized List<Job> getJobs() {
		try {
			// Simulate time to spend
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<Job> jobs = new ArrayList<Job>();
		for (int i = 0; i < 100000; i++) {
			Job job = new Job("job_id_" + id, "job_name_" + id);
			jobs.add(job);
			id++;
		}
		return jobs;
	}
}
