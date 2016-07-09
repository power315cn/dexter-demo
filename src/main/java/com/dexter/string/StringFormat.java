package com.dexter.string;

import java.util.Calendar;

/** 
 * 返回指定的格式化的字符串
 * @ClassName:	StringFormat 
 * @Description:
 * @author:	dexter
 * @date:	2016年7月9日 下午3:12:32 
 *  
 */
public class StringFormat {

	public static void main(String[] args) {
//		Format参数为格式字符串语法如下：
//		%[argument_index$][flags][width][.precision]conversion
//		“[]”里的参数表示可选。
//		可选的 argument_index ：下标是一个十进制整数，用于表明参数在参数列表中的位置。
//		可选的 flags 标志是修改输出格式的字符集。有效标志的集合取决于转换类型。
//		可选 width 最小宽度是一个非负十进制整数，表明要向输出中写入的最少字符数。
//		可选 precision 是一个非负十进制整数，通常用来限制字符数。特定行为取决于转换类型。
//		所需的 conversion转换方式是一个表明应该如何格式化参数的字符。给定参数的有效转换集合取决于参数的数据类型。
		
		//Eg1:说明argument_index和width
		String s = String.format("this is a %2$s %1$s %s %s test", "java", "C++");
		System.out.println(s);
		
//		Eg2.说明 flags
		System.out.println(String.format("%1$,09d", -3872));
		System.out.println(String.format("%1$,010d", -3872));
		System.out.println(String.format("%1$,09d", 3872));
		System.out.println(String.format("%1$9d", -14));
		System.out.println(String.format("%1$-9d", -14));
		System.out.println(String.format("%1$(9d", -14));
		System.out.println(String.format("%1$#9x", 31));
		
		//Eg3:说明conversion
		System.out.println(String.format("this is:%s %s", "string", "format"));
		System.out.println(String.format("%b, %b, %b, %b, %b, %b", "true",true, false, null, 0>1, 1, ""));
		System.out.println(String.format("%o", 31));
		System.out.println(String.format("%x", 31));
		System.out.println(String.format("%e", 12345.6987));
		System.out.println(String.format("%a", 12345.6987));
		System.out.println(String.format("%c, %c, %c, %c", 'a', 'b', 48, 98));
		
		//Eg4:格式化日期
		Calendar c = Calendar.getInstance();
		String data_str = String.format("today is: %1$tY/%1$tm/%1$te, now time is %1$tH:%1$tM:%1$tS", c);
		System.out.println(data_str);
	}
}
