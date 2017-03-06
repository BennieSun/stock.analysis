package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import com.entitys.StockDetailedEntity;
import com.entitys.StockDisplayDetailedEntity;
import com.entitys.StockInfoEntity;
import com.events.StockDBDataEvent;
import com.service.StockDetailedService;
import com.service.StockInfoService;
import com.utils.task.SimpleGetStockImfoTask;
import com.utils.task.base.TaskExcuteListener;


@Controller
public class PageSourceController {
	
	private static Logger log = Logger.getLogger(PageSourceController.class);
	
	@Autowired
	StockInfoService stockInfoService;
	@Autowired
	StockDetailedService stockDetailedService;
	@Autowired
	StockDBDataEvent stockDBDataEvent;
	@Autowired
	SimpleGetStockImfoTask simpleGetStockImfoTask;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String stockDetailCharts(Model model){
//		List<Menu> list = menuService.getAll();
//		model.addAttribute("menus", list);
		log.info("charts");
		
		return "charts";
    }
	
	
	@RequestMapping(value = "/info", method = {RequestMethod.GET,RequestMethod.POST})
    public String index(Model model, @ModelAttribute StockInfoEntity entity) throws Exception{
//		List<Menu> list = menuService.getAll();
//		model.addAttribute("menus", list);
		log.info("index");
		//return "main";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", entity.getId());
		map.put("stockCode",entity.getStockCode());
		map.put("stockName", entity.getStockName());
		map.put("test", "123456");
		model.addAllAttributes(map);
		
		//List<StockInfoEntity> xx = new StockInfoService().getAllStockInfo();
//		stockInfoService = new StockInfoService();
		List<StockInfoEntity> xx = stockInfoService.getAllStockInfo();
		log.info(xx.get(1).getStockCode());
		
		for (StockInfoEntity stockInfoEntity : xx) {
			log.info(stockInfoEntity.getId());
			log.info(stockInfoEntity.getStockCode());
			log.info(stockInfoEntity.getStockName());
			log.info("============================");
		}
		
		return "index";
    }
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index(Model model){
//		List<Menu> list = menuService.getAll();
//		model.addAttribute("menus", list);
		log.info("main");
		
		return "main";
    }
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndViewContainer index(Model model, @ModelAttribute StockDetailedEntity entity){
//		List<Menu> list = menuService.getAll();
//		model.addAttribute("menus", list);
		log.info("index");
		//return "main";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", entity.getId());
		map.put("serverCode",entity.getBigBillIncome());
		map.put("efunRole", entity.getBigBillIncomePercentage());
		map.put("test", "123456");
		model.addAllAttributes(map);
		
		ModelAndViewContainer view = new ModelAndViewContainer();
        view.setViewName("redirect:http://www.oschina.net");
        return view;
    }
	
	
	/**
	 * 存储数据到DB
	 * 还缺少1.当天数据重复插入验证StockDetialedEntity
	 */
	List<StockDisplayDetailedEntity> filteredList = null;
	Object obj = new Object(); 
	@RequestMapping(value = "/StockInfo", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView stockAnalytics(HttpServletRequest request, 
    		HttpServletResponse response)throws Exception{
		
		synchronized (obj) {
			simpleGetStockImfoTask.setTokenToUrl("894050c76af8597a853f5b408b759f5d");
			simpleGetStockImfoTask.setListener(new TaskExcuteListener() {
				public <T> void onTaskFinish(String[] dataString)
						throws Exception {
					
					ArrayList<StockDisplayDetailedEntity> entityList = stockDBDataEvent.stringArray2EntityList(dataString,null);
					filteredList = entityList;
				}
			});
			simpleGetStockImfoTask.execute();
		}
		
		return new ModelAndView("show", "simpleGetStockInfoTaskList", filteredList);
	}
}
