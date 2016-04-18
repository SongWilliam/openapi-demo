package com.shinemo.openapi.jituancaiyun.common;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;


public class HttpClientUtils {

	private static int timeout = -1;

	public static HttpClient getDefaultHttpClient() {
		HttpClientFactory factory = HttpClientFactory.getInstance();
		HttpClientConfig config = HttpClientConfig.getDefaultConfig();
		if (timeout > 0) {
			config.setConnectionTimeout(timeout);
			config.setSoTimeout(timeout);
		}
		return factory.createHttpClient(config);
	}

	public static String requestPost(String url, String content, Map<String, String> header) throws Exception {
		HttpPost method = new HttpPost(url);

		if (header != null) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				method.addHeader(entry.getKey(), entry.getValue());
			}
		}

		if (content != null) {
			HttpEntityEnclosingRequestBase entityRequestBase = (HttpEntityEnclosingRequestBase) method;
			AbstractHttpEntity reqEntity = new StringEntity(content, "UTF-8");
			entityRequestBase.setEntity(reqEntity);
		}

		return request(method);
	}

	public static String requestPost(String url, byte[] content, Map<String, String> header) throws Exception {
		HttpPost method = new HttpPost(url);

		if (header != null) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				method.addHeader(entry.getKey(), entry.getValue());
			}
		}

		if (content != null) {
			HttpEntityEnclosingRequestBase entityRequestBase = (HttpEntityEnclosingRequestBase) method;
			AbstractHttpEntity reqEntity = new ByteArrayEntity(content);
			entityRequestBase.setEntity(reqEntity);
		}

		return request(method);
	}
	
	public static String requestPost(String url, Map<String, String> content, Map<String, String> header) throws Exception {
        HttpPost method = new HttpPost(url);

        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                method.addHeader(entry.getKey(), entry.getValue());
            }
        }
        if (content != null) {
            HttpEntityEnclosingRequestBase entityRequestBase = (HttpEntityEnclosingRequestBase) method;
            List<NameValuePair> lst = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : content.entrySet()) {
                lst.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            AbstractHttpEntity reqEntity = new UrlEncodedFormEntity(lst, Charset.forName("UTF-8"));
            entityRequestBase.setEntity(reqEntity);
        }
        return request(method);
    }

	public static String requestGet(String url) {
		return request(new HttpGet(url));
	}
	
	public static HttpResult get(String url){
		HttpClient client = getDefaultHttpClient();
		HttpResponse response = null;
		try {
			response = client.execute(new HttpGet(url));
			HttpEntity entity = response.getEntity();
			int status = response.getStatusLine().getStatusCode();
			String str = StringUtils.getStringFromStream(entity.getContent());
			HttpResult result = new HttpResult();
			result.setStatus(status);
			result.setResult(str);
			return result;
		} catch (Exception e) {
			throw new RuntimeException("request failed",e);
		}
	}

	public static String requestPut(String url, String content, Map<String, String> header)
			throws UnsupportedEncodingException {
		HttpPut put = new HttpPut(url);

		if (header != null) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				put.addHeader(entry.getKey(), entry.getValue());
			}
		}

		if (content != null && !content.trim().isEmpty()) {
			HttpEntityEnclosingRequestBase entityRequestBase = (HttpEntityEnclosingRequestBase) put;
			AbstractHttpEntity reqEntity = new StringEntity(content);
			entityRequestBase.setEntity(reqEntity);
		}

		return request(put);
	}

	public static String requestGet(String url, Map<String, String> header) throws UnsupportedEncodingException {
		HttpGet get = new HttpGet(url);

		if (header != null) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				get.addHeader(entry.getKey(), entry.getValue());
			}
		}

		return request(get);

	}

	public static String requestPostWithStream(String url, byte[] content, Map<String, String> header) throws Exception {
		HttpPost method = new HttpPost(url);

		if (header != null) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				method.addHeader(entry.getKey(), entry.getValue());
			}
		}

		if (content != null) {
			HttpEntityEnclosingRequestBase entityRequestBase = (HttpEntityEnclosingRequestBase) method;
			AbstractHttpEntity reqEntity = new ByteArrayEntity(content);
			entityRequestBase.setEntity(reqEntity);
		}

		return request(method);
	}
	
	public static InputStream requestStream(String url, Map<String, String> header) {
		HttpGet get = new HttpGet(url);

		if (header != null) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				get.addHeader(entry.getKey(), entry.getValue());
			}
		}

		try {
			HttpClient client = getDefaultHttpClient();
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();

			if (response.getStatusLine().getStatusCode() < 300) {
				return entity.getContent();
			} else {
				throw new RuntimeException("request failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private static String request(HttpRequestBase method) {
		HttpClient client = getDefaultHttpClient();
		HttpResponse response = null;
		try {
			response = client.execute(method);
			HttpEntity entity = response.getEntity();
			int status = response.getStatusLine().getStatusCode();
			String str = StringUtils.getStringFromStream(entity.getContent());
			HttpResult result = new HttpResult();
			result.setStatus(status);
			result.setResult(str);
			return JsonUtils.convertFrom(result);
		} catch (Exception e) {
			throw new RuntimeException("request failed",e);
		}
	}
}
