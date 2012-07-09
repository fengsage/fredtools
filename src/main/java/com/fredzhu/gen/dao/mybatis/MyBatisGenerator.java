/**
 * www.mbaobao.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package com.fredzhu.gen.dao.mybatis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fredzhu.gen.dao.DBConnection;

/**
 *                       
 * @Filename MyBatisGenerator.java
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
public class MyBatisGenerator {
	
	private Configuration	configuration;
	
	private GenerateFile[]	generates	= new GenerateFile[] { new GeneratedDaoFile(),
			new GeneratedEntityFile(), new GeneratedMapperFile() };
	
	public MyBatisGenerator(Configuration configuration) throws Exception {
		super();
		if (configuration == null) {
			throw new IllegalArgumentException("配置文件不能为空");
		} else {
			this.configuration = configuration;
		}
	}
	
	public void generate() throws SQLException, IOException, InterruptedException {
		
		//获取待处理表信息
		List<TableDetail> tables = buildTableDetails();
		for (TableDetail table : tables) {
			for (GenerateFile gen : generates) {
				File file = new File(configuration.getConfiguration("export.path")
										+ gen.getFilePackage(configuration));
				//唉,先创建一堆目录
				if (!file.exists()) {
					file.mkdirs();
				}
				String content = gen.getContent(table);
			}
		}
		
	}
	
	private List<TableDetail> buildTableDetails() {
		List<TableDetail> tables = new ArrayList<TableDetail>();
		try {
			String[] exportTabbles = configuration.getConfiguration("table.name").split(",");
			for (String table : exportTabbles) {
				String tableName = table.split(":")[0];
				String javaName = table.split(":")[1];
				TableDetail detail = new TableDetail(tableName, javaName);
				tables.add(detail);
			}
		} catch (Exception e) {
			throw new RuntimeException("table.name格式不正确," + e.getMessage());
		}
		
		//查询数据类型
		for (TableDetail table : tables) {
			try {
				Connection conn = DBConnection.getConnection(configuration);
				Statement state = conn.createStatement();
				ResultSet rs = state.executeQuery(String.format("show columns from %s",
					table.getTableName()));
				while (rs.next()) {
					String field = rs.getString("Field");
					String type = rs.getString("Type");
					table.getColumns().put(field, type);
				}
				
			} catch (SQLException e) {
				throw new RuntimeException("查询表结构类型出错," + e.getMessage());
			}
		}
		System.out.println(tables);
		return tables;
	}
	
	private void writeFile(File file, String content) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
		bw.write(content);
		bw.close();
	}
}
