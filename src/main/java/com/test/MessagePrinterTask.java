package com.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.utils.DateFormatUtil;

public class MessagePrinterTask implements Runnable {
	private ThreadPoolTaskExecutor te;
	
	private long startTime;
	private Date d = null;
	
	public MessagePrinterTask(){
		d = new Date();
		ApplicationContext appContext = new ClassPathXmlApplicationContext("testbean.xml");     
		te = (ThreadPoolTaskExecutor)appContext.getBean("threadPool");
		startTime = d.getTime();
		for(int i = 0; i < 55000; i++) {        
        	//te.execute( MessagePrinterTask("Message" + i));      
			te.execute(new MessagePrinterTask("Message" + i, startTime));
			
        }  
		
	}

	private String message;

	public MessagePrinterTask(String message, Long st) {
		this.message = message;
		this.startTime = st;
	}

	public void run() {
		
		Long time = d.getTime() - startTime;
		
		System.out.println(time);
	}
}