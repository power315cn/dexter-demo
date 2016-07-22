package com.dexter.java.basic;

import java.lang.reflect.Field;

/** 
 * @ClassName:	IntegerDemo1 
 * @Description:
 * 为什么Java中1000==1000为false而100==100为true？
 * @author:	dexter
 * @date:	2016年7月14日 上午11:50:18 
 *  
 */
public class IntegerDemo1 {
	public static void main(String[] args) {
		int a=1000,b=1000;
		int c=100,d=100;	
		System.out.println(a==b);//true
		System.out.println(c==d);//true
		
		//如果你看去看 Integer.java 类，你会发现有一个内部私有类，IntegerCache.java，
		//它缓存了从-128到127之间的所有的整数对象。
		//所以事情就成了，所有的小整数在内部缓存
		Integer a1=1000,b1=1000;
		Integer c1=100,d1=100;
		System.out.println(a1==b1);//false
		System.out.println(c1==d1);//true
		demo();
	}
	//然而，通过反射API你会误用此功能。运行下面的代码，享受它的魅力吧
	public static void demo() {
		try {
			Class cache = Integer.class.getDeclaredClasses()[0]; //1
			Field myCache = cache.getDeclaredField("cache"); //2
			myCache.setAccessible(true);//3
			Integer[] newCache = (Integer[]) myCache.get(cache); //4
			newCache[132] = newCache[133]; //5
			int a = 2;
			int b = a + a;
			System.out.printf("%d + %d = %d", a, a, b); //
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
