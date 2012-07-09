/**
 * www.mbaobao.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package com.fredzhu.http.db.mock;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *                       
 * @Filename MockTable.java
 *
 * @Description 
 *
 * @Version 1.0
 *
 * @Author fred
 *
 * @Email kuci@mbaobao.com
 *       
 * @History
 *<li>Author: fred</li>
 *<li>Date: 2012-4-12</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 *
 */
public class MockTable<T> implements Serializable {
	
	/** Comment for <code>serialVersionUID</code> */
	private static final long	serialVersionUID	= -3951496365427983788L;
	
	public static final String	AUTO_KEY			= "id";
	
	private String				autoKey				= AUTO_KEY;				//自增键名称
																				
	private String				tableName;
	
	private List<T>				datas				= new ArrayList<T>();
	
	private int					index				= 1;
	
	public synchronized T addData(T obj) {
		try {
			Method method = obj.getClass().getMethod(buildSetMethodName(autoKey), Integer.class);
			method.invoke(obj, index++);
			datas.add(obj);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("亲,添加记录失败:" + obj);
	}
	
	public List<T> getAllRows() {
		return datas;
	}
	
	public Integer getRowCount() {
		return datas.size();
	}
	
	public synchronized void cleanTable() {
		datas.clear();
		index = 1;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public MockTable(Class<T> tableName, String autoKey) {
		super();
		this.tableName = tableName.getSimpleName();
		this.autoKey = autoKey;
	}
	
	public MockTable(Class<T> tableName) {
		super();
		this.tableName = tableName.getSimpleName();
	}
	
	private String buildSetMethodName(String name) {
		StringBuffer sb = new StringBuffer();
		sb.append("set");
		sb.append(name.substring(0, 1).toUpperCase());
		sb.append(name.substring(1));
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return String.format("MockTable [tableName=%s, index=%s]", tableName, index);
	}
	
}
