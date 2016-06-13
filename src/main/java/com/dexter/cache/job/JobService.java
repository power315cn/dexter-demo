package com.dexter.cache.job;

import java.util.List;

/**  
 * 一个作用服务接口，描述提供给前台使用的一些方法
 * 这里为了方便只提供了三个方法，两个更新方法，一个查询方法。
 *    
 */
public interface JobService {
	boolean submitJob(Job job);

	boolean killJob(Job job);

	List<Job> getJobs();
}
