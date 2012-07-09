/**
 * www.mbaobao.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package com.fredzhu.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;

/**
 *                       
 * @Filename HttpUtil.java
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
 *<li>Date: 2011-11-24</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 *
 */
public class HttpUtil {
	
	private static final Logger	logger			= Logger.getLogger(HttpUtil.class);
	
	private static HttpUtil		_instance;
	
	private static final String	CHARSET_UTF8	= "UTF-8";
	private static final String	CHARSET_GBK		= "GBK";
	
	private static HttpClient	httpClient		= null;
	
	private HttpUtil() {
		if (httpClient == null) {
			httpClient = getThreadSafeClient();
		}
	}
	
	public static HttpUtil getInstance() {
		if (_instance == null) {
			_instance = new HttpUtil();
		}
		return _instance;
	}
	
	@SuppressWarnings("deprecation")
	public static DefaultHttpClient getThreadSafeClient() {
		
		DefaultHttpClient client = new DefaultHttpClient();
		ClientConnectionManager mgr = client.getConnectionManager();
		HttpParams params = client.getParams();
		client = new DefaultHttpClient(new ThreadSafeClientConnManager(params,
			mgr.getSchemeRegistry()), params);
		return client;
	}
	
	/**
	 * POST请求
	 * @param url
	 * @param datas
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String doPost(String url, Map<String, String> datas) throws ClientProtocolException,
																IOException, URISyntaxException {
		logger.info("accept http post [url=" + url + ",datas=" + datas + "]");
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for (String key : datas.keySet()) {
			formparams.add(new BasicNameValuePair(key, datas.get(key)));
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
		HttpPost postData = new HttpPost(url);
		postData.setEntity(entity);
		
		HttpResponse response = httpClient.execute(postData);
		
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {
			return conver(response.getEntity());
		}
		
		return null;
	}
	
	/**
	 * GET请求
	 * @param url
	 * @param datas
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException 
	 */
	public String doGet(String url, Map<String, String> datas) throws ClientProtocolException,
																IOException, URISyntaxException {
		String requestUrl = get(url, datas);
		
		logger.info("accept http get [requestUrl=" + requestUrl + "]");
		
		HttpGet getData = new HttpGet(new URI(requestUrl));
		
		HttpResponse response = httpClient.execute(getData);
		
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {
			return conver(response.getEntity());
		}
		return null;
	}
	
	private String conver(HttpEntity entity) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(),
			"UTF-8"));
		StringBuffer buf = new StringBuffer();
		String tmp;
		while ((tmp = reader.readLine()) != null) {
			buf.append(tmp);
		}
		//close
		reader.close();
		
		return buf.toString();
	}
	
	private String get(String url, Map<String, String> params) {
		return get(url, params, CHARSET_UTF8);
	}
	
	/**
	 * @param url
	 * @param params
	 * @param object
	 * @return
	 */
	private String get(String url, Map<String, String> params, String charset) {
		
		if (url == null) {
			return null;
		}
		List<NameValuePair> qparams = getParamsList(params);
		if (qparams != null && qparams.size() > 0) {
			charset = (charset == null ? CHARSET_GBK : charset);
			String formatParams = URLEncodedUtils.format(qparams, charset);
			url = (url.indexOf("?")) < 0 ? (url + "?" + formatParams) : (url.substring(0,
				url.indexOf("?") + 1) + formatParams);
		}
		return url;
	}
	
	private List<NameValuePair> getParamsList(Map<String, String> paramsMap) {
		if (paramsMap == null || paramsMap.size() == 0) {
			return null;
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> map : paramsMap.entrySet()) {
			params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
		}
		return params;
	}
	
	//	public static void main(String[] args) {
	//		Map<String, String> datas = new HashMap<String, String>();
	//		
	//		try {
	//			String result = HttpUtil.getInstance().doPost("http://www.mbaobao.com", datas);
	//			System.out.println(result);
	//		} catch (Exception e) {
	//			logger.error("", e);
	//		}
	//	}
}
