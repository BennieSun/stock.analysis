package com.entitys;

import java.util.Date;

/**
 * 股票info
 * @author joesun
 *
 */
public class StockDetailedEntity extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7812981538620481309L;
	
	private int stockId;//
	private String latestPrice;//最后的价格
	private String upDown;//股票涨、降（百分比）
	private String mainForceBillIncome;//今日主力净流入-净额
	private String mainForceBillIncomePercentage;//今日主力净流入-净额比（百分比）
	private String hugeBillIncome;//今日超大单净流入-净额
	private String hugeBillIncomePercentage;//今日超大单净流入-净额比（百分比）
	private String bigBillIncome;//今日大单净流入-净额
	private String bigBillIncomePercentage;//今日大单净流入-净额比（百分比）
	private String mediumBillIncome;//今日中单净流入-净额
	private String mediumBillIncomePercentage;//今日中单净流入-净额比（百分比）
	private String smallBillIncome;//今日小单净流入－净额
	private String smallBillIncomePercentage;//今日小单净流入－净额比（百分比）
	private Date importTime;//导入日期
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getLatestPrice() {
		return latestPrice;
	}
	public void setLatestPrice(String latestPrice) {
		this.latestPrice = latestPrice;
	}
	public String getUpDown() {
		return upDown;
	}
	public void setUpDown(String upDown) {
		this.upDown = upDown;
	}
	public String getMainForceBillIncome() {
		return mainForceBillIncome;
	}
	public void setMainForceBillIncome(String mainForceBillIncome) {
		this.mainForceBillIncome = mainForceBillIncome;
	}
	public String getMainForceBillIncomePercentage() {
		return mainForceBillIncomePercentage;
	}
	public void setMainForceBillIncomePercentage(
			String mainForceBillIncomePercentage) {
		this.mainForceBillIncomePercentage = mainForceBillIncomePercentage;
	}
	public String getHugeBillIncome() {
		return hugeBillIncome;
	}
	public void setHugeBillIncome(String hugeBillIncome) {
		this.hugeBillIncome = hugeBillIncome;
	}
	public String getHugeBillIncomePercentage() {
		return hugeBillIncomePercentage;
	}
	public void setHugeBillIncomePercentage(String hugeBillIncomePercentage) {
		this.hugeBillIncomePercentage = hugeBillIncomePercentage;
	}
	public String getBigBillIncome() {
		return bigBillIncome;
	}
	public void setBigBillIncome(String bigBillIncome) {
		this.bigBillIncome = bigBillIncome;
	}
	public String getBigBillIncomePercentage() {
		return bigBillIncomePercentage;
	}
	public void setBigBillIncomePercentage(String bigBillIncomePercentage) {
		this.bigBillIncomePercentage = bigBillIncomePercentage;
	}
	public String getMediumBillIncome() {
		return mediumBillIncome;
	}
	public void setMediumBillIncome(String mediumBillIncome) {
		this.mediumBillIncome = mediumBillIncome;
	}
	public String getMediumBillIncomePercentage() {
		return mediumBillIncomePercentage;
	}
	public void setMediumBillIncomePercentage(String mediumBillIncomePercentage) {
		this.mediumBillIncomePercentage = mediumBillIncomePercentage;
	}
	public String getSmallBillIncome() {
		return smallBillIncome;
	}
	public void setSmallBillIncome(String smallBillIncome) {
		this.smallBillIncome = smallBillIncome;
	}
	public String getSmallBillIncomePercentage() {
		return smallBillIncomePercentage;
	}
	public void setSmallBillIncomePercentage(String smallBillIncomePercentage) {
		this.smallBillIncomePercentage = smallBillIncomePercentage;
	}
	public Date getImportTime() {
		return importTime;
	}
	public void setImportTime(Date importTime) {
		this.importTime = importTime;
	}
}
