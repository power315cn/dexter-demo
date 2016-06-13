package com.dexter.cache.job;

import java.util.List;

/**
 * 一个没用使用Cache的JobService接口实现类
 * @author Administrator
 *
 */
public class JobServiceImplWithoutCache implements JobService {
	public boolean submitJob(Job job) {
		return BackendJobManager.submitJob(job);
	}

	public boolean killJob(Job job) {
		return BackendJobManager.killJob(job);
	}

	public List<Job> getJobs() {
		return BackendJobManager.getJobs();
	}
}
