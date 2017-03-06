package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.StockInfoDao;
import com.entitys.StockInfoEntity;

@Service
public class StockInfoService {

	@Autowired
	private StockInfoDao stockInfoDao;
	
	/**
	 * 保存对象
	 * @param stockInfoEntity
	 * @return
	 * @throws Exception 
	 */
	public int saveStockInfo(StockInfoEntity stockInfoEntity) throws Exception {
		return stockInfoDao.saveStockInfo(stockInfoEntity);
	}
	
	/**
	 * 获取条数
	 * @param stockCode
	 * @return
	 * @throws Exception 
	 */
	public String getInfoCountById(String stockCode) throws Exception{
		List<StockInfoEntity> stockInfoList = stockInfoDao.getInfoCountById(stockCode);
		if(stockInfoList.size()==0){
			return "0";
		}else if(stockInfoList.size()==1){
			return "1";
		}else if(stockInfoList.size()==2){
			throw new Exception("stockInfoList 有两条数据");
		}
		else{
			throw new Exception("存在多条重复数据！");
		}
	}
	
	/**
	 * 获取ID
	 * @param stockCode
	 * @return
	 * @throws Exception 
	 */
	public int getStockInfoId(String stockCode) throws Exception{
		return stockInfoDao.getStockInfoId(stockCode);
	}
	
	/**
	 * 获取全部对象
	 * @return
	 */
	public List<StockInfoEntity> getAllStockInfo() throws Exception {
		return stockInfoDao.getAllStockInfo();
	}
}
