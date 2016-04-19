package com.dexter.common.delayqueue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class Cache<K, V> {
	private ConcurrentMap<K, V> cacheObjMap = new ConcurrentHashMap<K, V>();
	private ConcurrentMap<String, Object> delayObjMap = new ConcurrentHashMap<String, Object>();
	private DelayQueue<DelayItem<Pair<K, V>>> q = new DelayQueue<DelayItem<Pair<K, V>>>();

	private Thread daemonThread;
	
	public Cache() {

		Runnable daemonTask = new Runnable() {
			public void run() {
				daemonCheck();
			}
		};

		daemonThread = new Thread(daemonTask);
		daemonThread.setDaemon(true);
		daemonThread.setName("Cache Daemon");
		daemonThread.start();
	}

	private void daemonCheck() {
		for (;;) {
			try {
				DelayItem<Pair<K, V>> delayItem = q.take();
				if (delayItem != null) {
					// ��ʱ������
					Pair<K, V> pair = delayItem.getItem();
					cacheObjMap.remove(pair.first, pair.second); // compare and
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				break;
			}
		}

		
		System.out.println("cache service stopped.");
	}

	// ��ӻ������
	public void put(K key, V value, long time, TimeUnit unit) {
		Object oldValue = delayObjMap.get(key);
//		V oldValue = cacheObjMap.put(key, value);
		if (oldValue != null) {
			q.remove(oldValue);
			delayObjMap.remove(key);
		}
		long nanoTime = TimeUnit.NANOSECONDS.convert(time, unit);
		DelayItem<Pair<K, V>> di = new DelayItem<Pair<K, V>>(new Pair<K, V>(key, value), nanoTime);
		cacheObjMap.put(key, value);
		delayObjMap.put(key.toString(), di);
		q.put(di);
//		System.err.println(q.toString());
	}

	public V get(K key) {
		return cacheObjMap.get(key);
	}
	
	public int size() {
		return cacheObjMap.size();
	}

	// ������ں���
	public static void main(String[] args) throws Exception {
		Cache<Integer, String> cache = new Cache<Integer, String>();
		cache.put(1, "aaaa", -1, TimeUnit.SECONDS);
		Thread.sleep(1000 * 2);
		{
			String str = cache.get(1);
			System.out.println(str);
		}

		Thread.sleep(1000 * 2);
		{
			String str = cache.get(1);
			System.out.println(str);
		}
	}
}