package com.mb.util;

import java.util.ArrayList;
import java.util.List;


/**
 * 查询分页辅助类
 * @author ssy
 *
 */
public class QueryHelper {
	
	private String fromClause; //from子句
	private String whereClause=""; //where子句
	private String orderByClause="";  //orderBy子句
	
	private List<Object> parameters = new ArrayList<Object>();
	
	/** 生成FROM 子句*/
	public QueryHelper(Class clazz,String alias){
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias ;
	}
	
	/** 生成WHERE 子句*/
	public QueryHelper addCondition(String condition){
		if(whereClause.length()==0){
			whereClause = " WHERE " + condition ;
		}else{
			whereClause += " AND " + condition ;
		}
		return this;
		
	}
	public QueryHelper addCondition(String condition,Object... params){
		if(whereClause.length()==0){
			whereClause = " WHERE " + condition ;
		}else{
			whereClause += " AND " + condition ;
		}
		
		if(params!=null){
			for (Object o : params) {
				parameters.add(o);
			}
		}
		return this;
		
	}
	
	public QueryHelper addCondition(boolean append, String condition, Object... params) {
		if (append) {
			addCondition(condition, params);
		}
		return this;
	}
	
	/** 生成ORDER BY子句*/
	public QueryHelper addOrderProperty(String propertyName,boolean asc){
		if(orderByClause.length()==0){
			orderByClause = " ORDER BY " + propertyName + (asc ? " ASC" : " DESC");
		}else{
			orderByClause += "," + propertyName + (asc ? " ASC" : " DESC");
		}
		
		return this;
	}
	
	
	public QueryHelper addOrderProperty(boolean append,String propertyName,boolean asc) {
		if (append) {
			addOrderProperty(propertyName, asc);
		}
		return this;
	}
	
	/** 获取生成的用于查询数据列表的HQL */
	public String getListQueryHql(){
		return fromClause + whereClause + orderByClause ;
	}
	
	
	public String getCountQueryHql() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	
	//================
	public List<Object> getParameters() {
		return parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}
}
