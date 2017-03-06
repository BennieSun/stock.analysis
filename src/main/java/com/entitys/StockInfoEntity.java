package com.entitys;

import org.springframework.stereotype.Component;


/**
 * 股票info
 * @author joesun
 *
 */
public class StockInfoEntity extends BaseEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1552658152205764182L;
	
	private String stockCode;//股票Code
	private String stockName;//股票名字
	
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
}
