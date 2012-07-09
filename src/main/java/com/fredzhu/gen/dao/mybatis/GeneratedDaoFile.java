/**
 * www.mbaobao.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package com.fredzhu.gen.dao.mybatis;


/**
 *                       
 * @Filename GeneratedDaoFile
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
public class GeneratedDaoFile implements GenerateFile {
	
	/**
	 * @param table
	 * @return
	 * @see com.fredzhu.gen.dao.mybatis.GenerateFile#getContent(com.fredzhu.gen.dao.mybatis.TableDetail)
	 */
	@Override
	public String getContent(TableDetail table) {
		return null;
	}
	
	/**
	 * @return
	 * @see com.fredzhu.gen.dao.mybatis.GenerateFile#genFilePackage()
	 */
	@Override
	public String getFilePackage(Configuration config) {
		return config.getConfiguration("java.src") + "/"
				+ config.getConfiguration("java.dao.package");
	}
	
}
