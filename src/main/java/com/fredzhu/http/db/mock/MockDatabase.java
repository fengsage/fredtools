/**
 * www.mbaobao.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package com.fredzhu.http.db.mock;

import java.util.ArrayList;
import java.util.List;

/**
 *                       
 * @Filename MockDatabase.java
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
@SuppressWarnings("rawtypes")
public class MockDatabase {
	
	//模拟数据库表集合
	private static List<MockTable>	tables	= new ArrayList<MockTable>();
	
	public synchronized static List<MockTable> getAllTables() {
		return tables;
	}
	
	public synchronized static <T> MockTable buildTable(Class<T> tableName) {
		return buildTable(tableName, MockTable.AUTO_KEY);
	}
	
	public synchronized static <T> MockTable buildTable(Class<T> tableName, String autoKey) {
		for (MockTable table : tables) {
			if (table.getTableName().equals(tableName.getSimpleName())) {
				return table;
			}
		}
		MockTable table = new MockTable<T>(tableName, autoKey);
		tables.add(table);
		return table;
	}
	
}
