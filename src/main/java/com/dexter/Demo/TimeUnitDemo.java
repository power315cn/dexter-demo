package com.dexter.Demo;

import java.util.concurrent.TimeUnit;

public class TimeUnitDemo {

	public static void main(String[] args) throws InterruptedException {
		System.out.println(TimeUnit.HOURS.toMinutes(1));//1个小时转换为60分钟
		System.out.println(TimeUnit.HOURS.toSeconds(1));//1个小时转换为3600秒
		System.out.println(TimeUnit.SECONDS.toHours(12345));//3h
		System.out.println(TimeUnit.HOURS.toDays(24));
		//1小时转换为秒
		System.out.println(TimeUnit.SECONDS.convert(1, TimeUnit.HOURS));
		System.out.println("2秒后显示");
		TimeUnit.SECONDS.sleep(2);
		//Thread.sleep(2000);
		System.out.println("2秒到了");
	}
	
}
