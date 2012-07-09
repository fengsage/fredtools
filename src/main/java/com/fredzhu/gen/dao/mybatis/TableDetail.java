/**
 * www.mbaobao.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package com.fredzhu.gen.dao.mybatis;

import java.util.HashMap;
import java.util.Map;

/**
 *                       
 * @Filename TableDetail.java
 *
 * @Description 
 *
 * @Version 1.0
 *
 * @Author kuci
 *
 * @Email kuci@mbaobao.com
 *       
 * @History
 *<li>Author: kuci</li>
 *<li>Date: 2012-5-15</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 *
 */
public class TableDetail {
	
	private String				tableName;									//表明
																			
	private String				javaName;
	
	private Map<String, String>	columns	= new HashMap<String, String>();	//列明:列类型
																			
	/**
	 * 构建一个<code>TableDetail.java</code>
	 * @param tableName
	 * @param javaName
	 */
	public TableDetail(String tableName, String javaName) {
		super();
		this.tableName = tableName;
		this.javaName = javaName;
	}
	
	/**
	 * @return Returns the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	
	/**
	 * @return Returns the javaName
	 */
	public String getJavaName() {
		return javaName;
	}
	
	/**
	 * @return Returns the columns
	 */
	public Map<String, String> getColumns() {
		return columns;
	}
	
	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TableDetail [tableName=" + tableName + ", javaName=" + javaName + ", columns="
				+ columns + "]";
	}
	
}
