package com.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entitys.StockDetailedEntity;
import com.service.StockDetailedService;
import com.utils.DateFormatUtil;

@Controller
public class PageCharts {
	
	@Autowired
	StockDetailedService stockDetailedService;
	
	private static Logger log = Logger.getLogger(PageCharts.class);

	@RequestMapping(value = "/stockDetailCharts", method = {RequestMethod.GET,RequestMethod.POST})
	public void stockDetail1Charts(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
			String stockCode = request.getParameter("stockCode");
			int limitCount = Integer.parseInt(request.getParameter("limitCount"));

			List<StockDetailedEntity> stockDetailedList = stockDetailedService.findDetailedListByStockCode(stockCode, limitCount);
			List<Double> latestPrice = new ArrayList<Double>();
			List<Double> upDown = new ArrayList<Double>();
			List<Double> mainForceBillIncome = new ArrayList<Double>();
			List<Double> hugeBillIncome = new ArrayList<Double>();
			List<Double> bigBillIncomeList = new ArrayList<Double>();
			List<Double> mediumBillIncome = new ArrayList<Double>();
			List<Double> smallBillIncome = new ArrayList<Double>();
			List<String> time = new ArrayList<String>();
			
			if (stockDetailedList.size() == 0) {
				response.getWriter().println(new JSONArray());
				response.getWriter().close();
				return;
			}else{
				
				int j = 0;
				for(int i =stockDetailedList.size(); i>0;i--){
					StockDetailedEntity stockDetailedEntity = stockDetailedList.get(i-1);
					latestPrice.add(j,Double.parseDouble(stockDetailedEntity.getLatestPrice())*100);
					upDown.add(j,Double.parseDouble(stockDetailedEntity.getUpDown())*100);
					mainForceBillIncome.add(j,Double.parseDouble(stockDetailedEntity.getMainForceBillIncome()));
					hugeBillIncome.add(j,Double.parseDouble(stockDetailedEntity.getHugeBillIncome()));
					bigBillIncomeList.add(j,Double.parseDouble(stockDetailedEntity.getBigBillIncome()));
					mediumBillIncome.add(j,Double.parseDouble(stockDetailedEntity.getMediumBillIncome()));
					smallBillIncome.add(j,Double.parseDouble(stockDetailedEntity.getSmallBillIncome()));
					time.add(j,DateFormatUtil.dateToString(stockDetailedEntity.getImportTime(), DateFormatUtil.DATE_YEAR_MOUTH_DAY_FORMAT));
				    j++;

				}
			}
			
			JSONArray jsonArray = new JSONArray();
			
			JSONObject latestPriceObj = new JSONObject();
			latestPriceObj.put("name", "latestPrice*100");
			latestPriceObj.put("data", latestPrice);

			JSONObject upDownObj = new JSONObject();
			upDownObj.put("name", "upDown*100");
			upDownObj.put("data", upDown);
			
			JSONObject mainForceBillIncomeObj = new JSONObject();
			mainForceBillIncomeObj.put("name", "mainForceBillIncome");
			mainForceBillIncomeObj.put("data", mainForceBillIncome);
			
			JSONObject hugeBillIncomeObj = new JSONObject();
			hugeBillIncomeObj.put("name", "hugeBillIncome");
			hugeBillIncomeObj.put("data", hugeBillIncome);

        JSONObject bigBillIncomeListObj = new JSONObject();
			bigBillIncomeListObj.put("name", "bigBillIncomeList");
			bigBillIncomeListObj.put("data", bigBillIncomeList);
			
			JSONObject mediumBillIncomeObj = new JSONObject();
			mediumBillIncomeObj.put("name", "mediumBillIncome");
			mediumBillIncomeObj.put("data", mediumBillIncome);
			
			JSONObject smallBillIncomeObj = new JSONObject();
			smallBillIncomeObj.put("name", "smallBillIncome");
			smallBillIncomeObj.put("data", smallBillIncome);
			
			jsonArray.put(0,time);
			jsonArray.put(latestPriceObj);
			jsonArray.put(upDownObj);
			jsonArray.put(mainForceBillIncomeObj);
			jsonArray.put(hugeBillIncomeObj);
			jsonArray.put(bigBillIncomeListObj);
			jsonArray.put(mediumBillIncomeObj);
			jsonArray.put(smallBillIncomeObj);
			
			response.getWriter().println(jsonArray.toString());
			response.getWriter().close();
	    }
}
