/**
 * www.mbaobao.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package com.fredzhu.gen.dao.mybatis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *                       
 * @Filename Configuration.java
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
public class Configuration {
	
	private Properties	properties;
	
	public Configuration(File configurationFile) throws FileNotFoundException, IOException {
		this.properties = new Properties();
		this.properties.load(new FileInputStream(configurationFile));
	}
	
	public String getConfiguration(String key) {
		return properties.getProperty(key);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String filePath = Configuration.class.getResource("/").getFile() + "mybatis-gen.properties";
		Configuration conf = new Configuration(new File(filePath));
		System.out.println(conf.getConfiguration("jdbc.driver"));
	}
	
}
