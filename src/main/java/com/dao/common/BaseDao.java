package com.dao.common;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;

import com.entitys.BaseEntity;

public abstract class BaseDao<T extends BaseEntity> {
	
	
	protected final static String AUTO_INCREMENT = "AUTO_INCREMENT";

	private Class<T> entityClass = null;
	private static final String SQL_GET = "SELECT * FROM %s";
	
	@Autowired
	public JdbcTemplate jdbctemplate;
	@Autowired
	public JdbcOperations jdbcOperations;
	@Autowired
	public NamedParameterJdbcOperations namedParameterJdbcOperations;
	
	private static Map<String, SimpleJdbcInsertOperations> cachedInserts = new HashMap<String, SimpleJdbcInsertOperations>();
	
	@SuppressWarnings("unchecked")
	public BaseDao() {
        ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class<T>) thisType.getActualTypeArguments()[0];
    }
	
	/**
	 * 新增
	 * @param t
	 */
	public T create(T t) {
		setAddPerproties(t);
		getSimpleJdbcInsertOperations().execute(sqlParameterSource(t));
		return t;
	}
	
	/*public List<T> getAll() {
		BaseEntity t = new BaseEntity();
		return namedParameterJdbcOperations.query(sql2Get(), sqlParameterSource(t), rowMapper());
	}*/
	
	/**
	 * 更新
	 * @param t
	 * @param sqlWhere
	 * @return
	 */
	/*public int update(T t, String idColumnName) {
		setUpdatePerproties(t);
        return update(sql2Update(idColumnName), t);
    }*/
	
   /*protected int update(String updateSql, T t) {
        return namedParameterJdbcOperations.update(updateSql, sqlParameterSource(t));
    }*/
    
    protected String sql2Update(String idColumnName) {
        return getSql2UpdateAllColumn(idColumnName);
    }
	
	protected SimpleJdbcInsertOperations getSimpleJdbcInsertOperations() {
        SimpleJdbcInsertOperations simpleJdbcInsert = cachedInserts.get(tablename());
        if (simpleJdbcInsert == null) {
            simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcOperations)
                    .withTableName(tablename());
            cachedInserts.put(tablename(), simpleJdbcInsert);
        }
        return simpleJdbcInsert;
    }
	
	protected String tablename() {
        return JdbcHelper.getTablename(entityClass);
    }
	
	protected SqlParameterSource sqlParameterSource(BaseEntity t) {
		return JdbcHelper.getSqlParameterSource(t);
    }
	
	protected RowMapper<T> rowMapper() {
        return JdbcHelper.getRowMapper(entityClass);
    }
	
	protected String sql2Get() {
        return String.format(SQL_GET, tablename());
    }
	
	protected String getSql2UpdateAllColumn(String idColumnName) {
        List<String> column2property = JdbcHelper.column2properties(entityClass);
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(aliasTablename()).append(" SET ");
        boolean firstSet = true;
        for (int i=0; i<column2property.size(); i++) {
            String column = column2property.get(i);
        	if (filterUpdateColumn(column, idColumnName)) {
                if (firstSet) {
                    firstSet = false;
                } else {
                    sb.append(", ");
                }
                sb.append("a.").append(column).append("=:").append(column2property.get(i))
                        .append(" ");
            }
        }
        sb.append(" WHERE a." + idColumnName + "=:" + idColumnName);
        return sb.toString();
    }
	
	protected String aliasTablename() {
        return tablename() + " AS a ";
    }
	
	protected void setAddPerproties(T t) {
//		if (StringUtils.isEmpty(t.getDeleted())) {
//			t.setDeleted("0");
//		}
//		if (t.getCreatedTime() == null) {
//			t.setCreatedTime(DateUtil.now());
//		}
//		if (t.getModifiedTime() == null) {
//			t.setModifiedTime(DateUtil.now());
//		}
	 }
	
	protected void setUpdatePerproties(T t) {
	}
	
	//TODO 是否需要考虑时区问题
	protected Date now() {
		return new Date();
	}
	
	private boolean filterUpdateColumn(String column, String sqlWhere) {
        return !StringUtils.contains("|createdTime|createdBy|"+sqlWhere+"|",
                String.format("|%s|", column));
    }
	
}
