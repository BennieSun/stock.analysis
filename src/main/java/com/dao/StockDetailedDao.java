package com.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.controller.StockDBController;
import com.dao.common.BaseDao;
import com.dao.common.JdbcHelper;
import com.entitys.StockDetailedEntity;
import com.entitys.StockInfoEntity;
import com.utils.DateFormatUtil;

@Repository
public class StockDetailedDao extends BaseDao<StockInfoEntity>{
	
	private static Logger log = Logger.getLogger(StockDetailedDao.class);

	public List<StockInfoEntity> getAllStockDetailed() throws Exception  {
		return jdbcOperations.query("SELECT * FROM t_stock_detailed", JdbcHelper.getRowMapper(StockInfoEntity.class));
	}
	
	/**
	 * 存储StockInfo Data
	 * @param stockInfoEntity
	 * @return
	 */
	public int saveStockDetailed(StockDetailedEntity stockDetailedEntity) throws Exception {
		int count = namedParameterJdbcOperations.getJdbcOperations().update("INSERT INTO t_stock_detailed VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
				null,
				stockDetailedEntity.getStockId(),
				stockDetailedEntity.getLatestPrice(),
				stockDetailedEntity.getUpDown(),
				stockDetailedEntity.getMainForceBillIncome(),
				stockDetailedEntity.getMainForceBillIncomePercentage(),
				stockDetailedEntity.getHugeBillIncome(),
				stockDetailedEntity.getHugeBillIncomePercentage(),
				stockDetailedEntity.getBigBillIncome(),
				stockDetailedEntity.getBigBillIncomePercentage(),
				stockDetailedEntity.getMediumBillIncome(),
				stockDetailedEntity.getMediumBillIncomePercentage(),
				stockDetailedEntity.getSmallBillIncome(),
				stockDetailedEntity.getSmallBillIncomePercentage(),
				stockDetailedEntity.getImportTime());
		
		
		return count;
	}

	public List<StockDetailedEntity> findDetailedByIDAndCurrentTime(int stockId,String startDate,String endDate) throws Exception {
		return jdbctemplate.query("select * from t_stock_detailed where stockid=? and importTime BETWEEN ? and ?",
					new Object[]{stockId,startDate,endDate},JdbcHelper.getRowMapper(StockDetailedEntity.class));
	}
	
	public int updateStockDetailed(StockDetailedEntity stockDetailedEntity,String startDate,String endDate) throws Exception {
		return jdbctemplate
				.update("UPDATE t_stock_detailed SET latestPrice = ?,upDown=?,mainForceBillIncome=?,mainForceBillIncomePercentage=?, hugeBillIncome=?,hugeBillIncomePercentage=?,bigBillIncome=?,bigBillIncomePercentage=?,mediumBillIncome=?,mediumBillIncomePercentage=?,smallBillIncome=?,smallBillIncomePercentage=?,importTime=? WHERE stockid=? and importTime BETWEEN ? and ?",
						new Object[] {
								stockDetailedEntity.getLatestPrice(),
								stockDetailedEntity.getUpDown(),
								stockDetailedEntity
										.getMainForceBillIncome(),
								stockDetailedEntity
										.getMainForceBillIncomePercentage(),
								stockDetailedEntity.getHugeBillIncome(),
								stockDetailedEntity
										.getHugeBillIncomePercentage(),
								stockDetailedEntity.getBigBillIncome(),
								stockDetailedEntity
										.getBigBillIncomePercentage(),
								stockDetailedEntity.getMediumBillIncome(),
								stockDetailedEntity
										.getMediumBillIncomePercentage(),
								stockDetailedEntity.getSmallBillIncome(),
								stockDetailedEntity
										.getSmallBillIncomePercentage(),
								stockDetailedEntity.getImportTime(),
								stockDetailedEntity.getStockId(),startDate,endDate});
		
	}
	
	/**
	 * 根据stockCode返回limitCount的List<StockDetailedEntity>
	 * @param stockCode
	 * @param limitCount
	 * @return
	 * @throws Exception
	 */
	public List<StockDetailedEntity> findDetailedByStockCode(String stockCode, int limitCount) throws Exception {
		return jdbctemplate.query("select * from t_stock_detailed where stockid=(select id from t_stock_info where stockcode=?) and HOUR(importTime) between 13 and 23 order by importTime desc limit ?",
					new Object[]{stockCode,limitCount},JdbcHelper.getRowMapper(StockDetailedEntity.class));
	}
}
