package com.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.StockDetailedDao;
import com.entitys.StockDetailedEntity;
import com.entitys.StockInfoEntity;
import com.enums.StockDayEnum;
import com.enums.StockTimeEnum;
import com.utils.DateFormatUtil;

@Service
public class StockDetailedService {
	private static Logger log = Logger.getLogger(StockDetailedDao.class);

	@Autowired
	private StockDetailedDao stockDetailedoDao;
	
	public List<StockInfoEntity> getAllStockInfo() throws Exception {
		return stockDetailedoDao.getAllStockDetailed();
	}
	
	public int saveStockDetailed(StockDetailedEntity stockDetailedEntity) throws Exception {
		if (DateFormatUtil.getNowTimeDate().before(getBeforTenMinitCurrentTimeDate("MORNINGSTART"))) {
			stockDetailedEntity.setImportTime(DateFormatUtil.stringToDate(getYearMouthDay("BEFORDAY")+" 23:59:59",DateFormatUtil.DATE_DEFAULT_FORMAT));
		}
		return stockDetailedoDao.saveStockDetailed(stockDetailedEntity);
	}
	
	/**
	 * 根据参数查询数据是否存在
	 * @param stockId
	 * @param currentTime
	 * @return
	 * @throws Exception
	 */
	public String findDetailedCountByIDAndCurrentTime(int stockId)
			throws Exception {
		List<StockDetailedEntity> stockDetailedList = null;
		
		log.info(DateFormatUtil.getNowTimeStr() + " method findDetailedCountByIDAndCurrentTime:" + stockId);
		
		String stockRefreshPeriod = stockRefreshPeriod();
		if ("MORNINGSTART".equals(stockRefreshPeriod)) {// 早上开市8:50之前算昨天的数据
			stockDetailedList = stockDetailedoDao.findDetailedByIDAndCurrentTime(stockId,
					getBeforDayCurrentTime("AFTERNOONSTART"),
					getBeforTenMinitCurrentTimeStr("AFTERNOONSTART"));
		}

		if ("NOONSTART".equals(stockRefreshPeriod)) {// 早上开市8:50－12:50之间算今天上午数据
			stockDetailedList = stockDetailedoDao.findDetailedByIDAndCurrentTime(stockId, 
							getBeforTenMinitCurrentTimeStr("MORNINGSTART"),
							getBeforTenMinitCurrentTimeStr("AFTERNOONSTART"));
		}

		if ("AFTERNOONSTART".equals(stockRefreshPeriod)) {// 下午开市12:50－24:00前算今天下午数据
			stockDetailedList = stockDetailedoDao.findDetailedByIDAndCurrentTime(stockId, DateFormatUtil
							.getCurrTimeOfAfternoonStart(DateFormatUtil.getNowYearMouthDayStr()),
							getYearMouthDay("AFTERDAY"));
		}
		

		// List<StockDetailedEntity> stockDetailedList =
		// stockDetailedoDao.findDetailedByIDAndCurrentTime(stockId,
		// currentTime);
		
		if(null == stockDetailedList){
			log.info("stockDetailedList is null."+DateFormatUtil.getNowTimeStr() + ":::::"+stockId);
			throw new Exception("null == stockDetailedList");
		}
		
		if (stockDetailedList.size() == 0) {
			return "0";
		} else if (stockDetailedList.size() == 1) {
			return "1";
		} else {
			log.info("stockDetailedList is null."+DateFormatUtil.getNowTimeStr() + ":::::"+stockId);
			throw new Exception("存在重复数据！");
		}
	}

	/**
	 * 前10分钟
	 * @return
	 */
	private static Date getBeforTenMinitCurrentTimeDate(String normalTime) {
		if(normalTime.equals(StockTimeEnum.MORNINGSTART.toString())){//8:50
			return DateFormatUtil.updateCurrentTimeDate(DateFormatUtil.getCurrTimeOfMorningStart(DateFormatUtil.getNowYearMouthDayStr()),0,-10);
		}else if(normalTime.equals(StockTimeEnum.MORNINGEND.toString())){//11:20
			return DateFormatUtil.updateCurrentTimeDate(DateFormatUtil.getCurrTimeOfMorningEnd(DateFormatUtil.getNowYearMouthDayStr()),0,-10);
		}else if(normalTime.equals(StockTimeEnum.AFTERNOONSTART.toString())){//12:50
			return DateFormatUtil.updateCurrentTimeDate(DateFormatUtil.getCurrTimeOfAfternoonStart(DateFormatUtil.getNowYearMouthDayStr()),0,-10);
		}else if(normalTime.equals(StockTimeEnum.AFTERNOONEND.toString())){//14:50
			return DateFormatUtil.updateCurrentTimeDate(DateFormatUtil.getCurrTimeOfAfternoonEnd(DateFormatUtil.getNowYearMouthDayStr()),0,-10);
		}else{
			try {
				throw new Exception("don't normalTime at method getBeforeTenMinitCurrentTime  ");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 前10分钟
	 * @return
	 */
	private static String getBeforTenMinitCurrentTimeStr(String normalTime){
		return DateFormatUtil.dateToString(getBeforTenMinitCurrentTimeDate(normalTime),DateFormatUtil.DATE_DEFAULT_FORMAT);
	}
	
	/**
	 * 
	 * @return
	 */
	private static String getBeforDayCurrentTime(String normalTime) {
		
		if(normalTime.equals(StockTimeEnum.AFTERNOONSTART.toString())){
			return DateFormatUtil.updateCurrentTimeStr(getBeforTenMinitCurrentTimeStr("AFTERNOONSTART"), -1, 0);
		}else{
			try {
				throw new Exception("don't normalTime at method getBeforDayCurrentTime!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
		
	}

	/**
	 * 天
	 * @return
	 */
	public static String getYearMouthDay(String dayFlag) {
		if(dayFlag.equals(StockDayEnum.AFTERDAY.toString())){
			return DateFormatUtil.updateCurrentYearMouthDayStr(DateFormatUtil.getNowYearMouthDayStr(), 0, 0, 1);
		}else if(dayFlag.equals(StockDayEnum.BEFORDAY.toString())){
			return DateFormatUtil.updateCurrentYearMouthDayStr(DateFormatUtil.getNowYearMouthDayStr(), 0, 0, -1);
		}else if(dayFlag.equals(StockDayEnum.CURRENTDAR.toString())){
			return DateFormatUtil.getNowYearMouthDayStr();
		}else{
			try {
				throw new Exception("don't dayFlag at method getYearMouthDay");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 更新
	 * @param stockDetailedEntity
	 * @param currentTime
	 * @return
	 * @throws Exception
	 */
	public int updateStockDetailed(StockDetailedEntity stockDetailedEntity,String currentTime) throws Exception {
		
		log.info(DateFormatUtil.getNowTimeStr() + " method updateStockDetailed:" + stockDetailedEntity.getStockId());
		
		String stockRefreshPeriod = stockRefreshPeriod();
		
		if ("MORNINGSTART".equals(stockRefreshPeriod)) {// 早上开市8:50之前算昨天的数据
			stockDetailedEntity.setImportTime(DateFormatUtil.stringToDate(getYearMouthDay("BEFORDAY")+" 23:59:59",DateFormatUtil.DATE_DEFAULT_FORMAT));
			return stockDetailedoDao.updateStockDetailed(stockDetailedEntity,
					getBeforDayCurrentTime("AFTERNOONSTART"),
					getBeforTenMinitCurrentTimeStr("MORNINGSTART"));
		}
		
		if ("NOONSTART".equals(stockRefreshPeriod)) {// 早上开市8:50－12:50之间算今天上午数据
			return stockDetailedoDao.updateStockDetailed(stockDetailedEntity,
					getBeforTenMinitCurrentTimeStr("MORNINGSTART"),
					getBeforTenMinitCurrentTimeStr("AFTERNOONSTART"));
		}
		
		if ("AFTERNOONSTART".equals(stockRefreshPeriod)) {// 下午开市12:50－24:00前算今天下午数据
			return stockDetailedoDao.updateStockDetailed(stockDetailedEntity,
					getBeforTenMinitCurrentTimeStr("AFTERNOONSTART"),
					getYearMouthDay("AFTERDAY"));
		}
		
		return 0;
	}
	
	/**
	 * 根据stockCode查询买入－卖出情况
	 * @param stockCode
	 * @param limitCount
	 * @return
	 * @throws Exception
	 */
	public List<StockDetailedEntity> findDetailedListByStockCode(String stockCode, int limitCount)
				throws Exception {
		return stockDetailedoDao.findDetailedByStockCode(stockCode, limitCount);
	}
	
	private String stockRefreshPeriod() throws Exception {
		if (DateFormatUtil.getNowTimeDate().before(getBeforTenMinitCurrentTimeDate("MORNINGSTART"))) {// 早上开市8:50之前算昨天的数据
			log.info("before MORNINGSTART"+DateFormatUtil.getNowTimeStr());
			return "MORNINGSTART";
		}else if ((DateFormatUtil.getNowTimeDate().after(getBeforTenMinitCurrentTimeDate("MORNINGSTART"))
				&& DateFormatUtil.getNowTimeDate().before(getBeforTenMinitCurrentTimeDate("AFTERNOONSTART")))
				||DateFormatUtil.getNowTimeDate().equals(DateFormatUtil.getDateChangeHourMinuteSecond(DateFormatUtil.getNowTimeDate(), 
						new Object[] {8, 50}))) {// 早上开市8:50－12:50之间算今天上午数据
			log.info("after MORNINGSTART before  AFTERNOONSTART"+DateFormatUtil.getNowTimeStr());
			return "NOONSTART";
		}else if (DateFormatUtil.getNowTimeDate().after(getBeforTenMinitCurrentTimeDate("AFTERNOONSTART"))
				||DateFormatUtil.getNowTimeDate().equals(DateFormatUtil.getDateChangeHourMinuteSecond(DateFormatUtil.getNowTimeDate(), 
						new Object[] {12, 50}))) {// 下午开市12:50－24:00前算今天下午数据
			log.info("before AFTERNOONSTART"+DateFormatUtil.getNowTimeStr());
			return "AFTERNOONSTART";
		}else{
			log.info("don't find at Method stockRefreshPeriod"+DateFormatUtil.getNowTimeStr());
			throw new Exception("don't find at Method stockRefreshPeriod"+DateFormatUtil.getNowTimeStr());
		}
	}
}
