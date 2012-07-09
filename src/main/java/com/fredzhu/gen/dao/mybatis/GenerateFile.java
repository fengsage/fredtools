/**
 * www.mbaobao.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package com.fredzhu.gen.dao.mybatis;

/**
 *                       
 * @Filename GenerateFile.java
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
public interface GenerateFile {
	
	public String getContent(TableDetail table);
	
	public String getFilePackage(Configuration config);
	
}
