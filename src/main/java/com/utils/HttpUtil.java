package com.utils;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
public class HttpUtil {

	private static HttpPost getHttpPost(String url) {
		HttpPost request = new HttpPost(url);
		return request;
	}

	private static HttpResponse getHttpResponse(HttpPost request)
			throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter("http.connection.timeout",
				Integer.valueOf(30000));
		httpClient.getParams().setParameter("http.socket.timeout",
				Integer.valueOf(30000));
		return httpClient.execute(request);
	}

	public static String executePostRequest(String url, String jason) {
		String reqResult = null;
		System.out.println(url);
		HttpPost request = getHttpPost(url);
		try {
			HttpEntity entity = new StringEntity(jason, "UTF-8");
			request.setEntity(entity);
			request.setHeader("Content-Type",
					"application/json; encoding=utf-8");
			HttpResponse response = getHttpResponse(request);
			if (response == null) {
				return null;
			}
			System.out.println("tatusCode:"
					+ response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				reqResult = EntityUtils.toString(response.getEntity(), "UTF-8");
				if (StringUtils.isEmpty(reqResult)) {
					return null;
				}
				return reqResult;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reqResult;

	}

	public static String executePostRequest(String url,
			List<NameValuePair> params) {
		String reqResult = null;
		HttpPost request = getHttpPost(url);
		try {
			HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
			request.setEntity(entity);
			HttpResponse response = getHttpResponse(request);
			if (response == null) {
				return null;
			}
			System.out.println("tatusCode:"
					+ response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				reqResult = EntityUtils.toString(response.getEntity(), "UTF-8");
				if (StringUtils.isEmpty(reqResult)) {
					return null;
				}
				return reqResult;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reqResult;
	}

	public static HttpGet getHttpGet(String uri) {
		return new HttpGet(uri);
	}

	public static String executeGetRequest(String uri){
		return getHttpResponse(getHttpGet(uri));
	}
	
	public static String getHttpResponse(HttpGet httpGet) {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter("http.connection.timeout",
				Integer.valueOf(10000));
		client.getParams().setParameter("http.socket.timeout",
				Integer.valueOf(10000));
		try {
			HttpResponse response = client.execute(httpGet);
			return EntityUtils.toString(response.getEntity(), "gb2312");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}