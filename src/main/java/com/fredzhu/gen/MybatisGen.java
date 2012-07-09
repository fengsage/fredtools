/**
 * www.mbaobao.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package com.fredzhu.gen;

import java.io.File;

import com.fredzhu.gen.dao.mybatis.Configuration;
import com.fredzhu.gen.dao.mybatis.MyBatisGenerator;

/**
 *                       
 * @Filename MybatisGen.java
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
public class MybatisGen {
	
	private static final String	GEN_FILE	= genFile();
	
	private static String genFile() {
		return MybatisGen.class.getResource("/").getFile() + "mybatis-gen.properties";
	}
	
	public static void main(String[] args) throws Exception {
		File configurationFile = new File(GEN_FILE);
		if (!configurationFile.exists()) {
			throw new RuntimeException(GEN_FILE + ",文件不存在!");
		}
		Configuration config = new Configuration(configurationFile);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config);
		myBatisGenerator.generate();
	}
	
}
