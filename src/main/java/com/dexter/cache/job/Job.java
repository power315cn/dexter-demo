package com.dexter.cache.job;

import java.io.Serializable;

/**
 * 一个作业类，描述基本作业性息。
 * 
 * @author Administrator
 *
 */
public class Job implements Serializable {
	private static final long serialVersionUID = 2950354239977937401L;
	private String id;
	private String name;

	public Job() {
	}

	public Job(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
