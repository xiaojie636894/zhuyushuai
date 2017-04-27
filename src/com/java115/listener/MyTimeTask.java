package com.java115.listener;


import java.util.Date;
import java.util.TimerTask;

public class MyTimeTask extends TimerTask{

	@Override
	public void run() {
		System.out.println(new Date()+"ִ");
	}
	
}
