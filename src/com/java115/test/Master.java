package com.java115.test;

/**
 * @author master 作者：飞一样的编程
 * 建这个类的目的是：为了教会大家如果多个人同时修改一个文件
 */
public class Master {
	public void master(){
		System.out.println("我是师傅");
	}
	//以下你们几个人都这样定义一个属于自己的方法（拿吴俊为例子）
	public void wujun(){
		System.out.println("我是吴俊");
	}
	//……
}
