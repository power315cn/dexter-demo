package com.dexter.cache.job;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import java.util.List;


/** 
 * @ClassName:	JobServiceImplWithCache 
 * @Description: 
 * JobServiceImplWithCache.java一个使用ehcache作为缓冲机制的JobService接口实现类。
 * 这里只做测试用，所以没有负责最终CacheManager的清理工作，生产环境下应该提供一个CacheManager的
 * 封装来管理所有的缓存。这里需要注意的是Cache对象的创建，其中最后两个参数5和2分别表示
 * timeToLiveSeconds - the default amount of time to live for an element from its creation date
 * timeToIdleSeconds - the default amount of time to live for an element from its last accessed or modified date
 * 另外注意在submit和kill方法里，都对cache做了同步更新的操作，从而保证每次getJobs取的数据都是最新的。 
 * @author:	dexter
 * @date:	2016年5月30日 上午11:04:44 
 *  
 */
public class JobServiceImplWithCache implements JobService {
	private static CacheManager cacheManager;
	private static final Cache cache;
	private static final String JOBS_CACHE_NAME = "jobsCache";
	private static final String JOBS_CACHE_KEY = "jobs";

	static {
		cacheManager = new CacheManager();
		cache = new Cache(JOBS_CACHE_NAME, 1000, false, false, 5, 2);
		cacheManager.addCache(cache);
		// cacheManager.shutdown();
	}

	public boolean submitJob(Job job) {
		synchronized (cache) {
			cache.remove(JOBS_CACHE_KEY);
		}
		return BackendJobManager.submitJob(job);
	}

	public boolean killJob(Job job) {
		synchronized (cache) {
			cache.remove(JOBS_CACHE_KEY);
		}
		return BackendJobManager.killJob(job);
	}

	public List<Job> getJobs() {
		List<Job> jobs;
		synchronized (cache) {
			Element ele = cache.get(JOBS_CACHE_KEY);
			if (ele != null) {
				jobs = (List<Job>) ele.getValue();
			} else {
				System.out.println("Call backend API.");
				jobs = BackendJobManager.getJobs();
				cache.put(new Element(JOBS_CACHE_KEY, jobs));
			}
		}
		return jobs;
	}
}
