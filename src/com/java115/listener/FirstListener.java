package com.java115.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class FirstListener implements ServletContextListener{
	Timer timer = new Timer();
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("-------------------------");
		timer.cancel();
	}

	public void contextInitialized(ServletContextEvent sce) {
		
		timer.schedule(new MyTimeTask(), 1000,5000);
	}

}
