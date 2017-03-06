package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.events.StockDBDataEvent;
import com.utils.DateFormatUtil;
import com.utils.task.SimpleGetStockImfoTask;
import com.utils.task.base.TaskExcuteListener;


@Controller
public class StockDBController{
	@Autowired
	StockDBDataEvent stockDBDataEvent;
	
	@Autowired
	SimpleGetStockImfoTask simpleGetStockImfoTask;
	
	private static Logger log = Logger.getLogger(StockDBController.class);
	Object obj = new Object();
	
	@RequestMapping(value = "/saveAndUpdateStockDB", method = {RequestMethod.GET,RequestMethod.POST})
    public void stockAnalytics(HttpServletRequest request, 
    		HttpServletResponse response)throws Exception{
		log.info("class StockDBController Method stockAnalytics is Start.==="+DateFormatUtil.getNowTimeStr());
		synchronized (obj) {
			simpleGetStockImfoTask.setTokenToUrl("894050c76af8597a853f5b408b759f5d");
			simpleGetStockImfoTask.setListener(new TaskExcuteListener() {

				public <T> void onTaskFinish(String[] dataString)
						throws Exception {
					stockDBDataEvent.stringArray2EntityList(dataString, "saveDB");
				}
			});
			simpleGetStockImfoTask.execute();
		}
		log.info("class StockDBController Method stockAnalytics is finish!==="+DateFormatUtil.getNowTimeStr());
	}
}
