package com.dexter.java.enumDemo;

public class EnumTest {
	public static void main(String[] args) {
		System.out.println(ColorEnum.RED.getIndex());
		System.out.println(ColorEnum.RED.getName());
		System.out.println(ColorEnum.getName(2));
		
		
		System.out.println(Color.RED.toString());
	}
}
