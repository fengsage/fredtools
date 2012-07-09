/**
 * www.mbaobao.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package com.fredzhu.util;

import java.util.Properties;

/**
 *                       
 * @Filename SystemUtil.java
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
 *<li>Date: 2012-4-22</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 *
 */
public class SystemUtil {
	
	public static boolean isWindows() {
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		if (os.startsWith("win") || os.startsWith("Win")) {
			return true;
		} else {
			return false;
		}
	}
	
}
