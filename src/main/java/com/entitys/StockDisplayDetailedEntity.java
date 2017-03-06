package com.entitys;

import java.io.Serializable;

public class StockDisplayDetailedEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5193150540405415925L;
	
	private int num;
	private String index;
	private String code;
	private String name;
	private String latestPrice;
	private String upAndDown;
	private String mainForceBillIncome;
	private String mainForceBillIncomePercentage;
	private String hugeBillIncome;
	private String hugeBillIncomePercentage;
	private String bigBillIncome;
	private String bigBillIncomePercentage;
	private String mediumBillIncome;
	private String mediumBillIncomePercentage;
	private String smallBillIncome;
	private String smallBillIncomePercentage;

	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getIndex() {
		return index;
	}


	public void setIndex(String index) {
		this.index = index;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLatestPrice() {
		return latestPrice;
	}


	public void setLatestPrice(String latestPrice) {
		this.latestPrice = latestPrice;
	}


	public String getUpAndDown() {
		return upAndDown;
	}


	public void setUpAndDown(String upAndDown) {
		this.upAndDown = upAndDown;
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


	@Override
	public String toString() {
		return "code: " + code + ", name: " + name + ", latestPrice: "
				+ latestPrice + ", upAndDown: " + upAndDown;
	}
}
