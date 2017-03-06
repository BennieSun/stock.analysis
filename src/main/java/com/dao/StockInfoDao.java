package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dao.common.BaseDao;
import com.dao.common.JdbcHelper;
import com.entitys.StockInfoEntity;

@Repository
public class StockInfoDao extends BaseDao<StockInfoEntity>{

	public List<StockInfoEntity> getAllStockInfo() throws Exception {
		return jdbcOperations.query("SELECT * FROM t_stock_info", JdbcHelper.getRowMapper(StockInfoEntity.class));
	}
	
	public List<StockInfoEntity> getInfoCountById(String stockCode) throws Exception {
		return jdbctemplate.query("SELECT * FROM t_stock_info where stockCode=?",
				new Object[]{stockCode},JdbcHelper.getRowMapper(StockInfoEntity.class));
	}
	
	public int getStockInfoId(String stockCode) throws Exception {
		StockInfoEntity stockInfoEntity = jdbctemplate.queryForObject("SELECT id FROM t_stock_info where stockCode=?",new Object[]{stockCode},JdbcHelper.getRowMapper(StockInfoEntity.class));
		return stockInfoEntity.getId();
	}

	/**
	 * 存储StockInfo Data
	 * @param stockInfoEntity
	 * @return
	 */
	public int saveStockInfo(StockInfoEntity stockInfoEntity) throws Exception {
		return namedParameterJdbcOperations.getJdbcOperations().update("INSERT INTO t_stock_info VALUES (?,?,?)", null,stockInfoEntity.getStockCode(),stockInfoEntity.getStockName());
	}
}
