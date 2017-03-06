package com.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.entitys.StockDisplayDetailedEntity;
import com.utils.DateFormatUtil;


public class main {
	
	
	public static int dg(int value){
		if(value==1)
			return 1;
		else
			return value*dg(value-1);
	}

	public static void main(String[] args) throws Exception {

//		int sum=100;
//		int empt = 100;
//		while(empt>0){
//			sum = sum + empt/2;
//			empt = empt/2 + empt%2;
//			System.out.println(empt);
//		}
//		
//		JSONObject json = new JSONObject();
//		
//		String xx = (String) json.get("xxx");
		//new MessagePrinterTask();
		
		for(int j=0;j<20;j++){
			
			final int  i = j;
			
			new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						saveStock(i);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			
			
		}
	}

	private static void saveStock(int i) throws Exception{
		System.out.println("***"+ i);
		
		throw new RuntimeException("存在重复数据！");
	}

	private static void mineCode() {
		int j = 0;
		int count = 0;
		int money = 100;
		for(int i=money;i>0;i--){
			j++;
			System.out.println("买第"+(money-i+1)+"瓶");
			if(j>1){
				j++;
				count++;
				System.out.println("换购第"+count+"瓶");
			}
		}
		System.out.println("总瓶:"+j);
		
		System.out.println(dg(5));
		
//		System.out.println(new Integer(3)==new Integer(3));
//		
//		String mStr = null;
//		System.out.println(mStr.length());
	}

}
