package main.java.com.shinemo.openapi.common;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import main.java.com.shinemo.openapi.common.ssl.SSLConnectionSocketFactoryHolder;
import com.shinemo.openapi.domain.Result;
import main.java.com.shinemo.openapi.util.JacksonUtil;

/**
 * 
 * For Apache httpclient
 *
 */
public class ApacheHttpsHelper {

	public HttpClient getHttpClient(){
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(SSLConnectionSocketFactoryHolder.get()).build();
		return httpClient;
	}

	public String getCharset() {
		return Charset.forName("utf-8").name();
	}
	
	public Result<String> get(String url, Map<String, String> header) {
		try{
			HttpGet getMethod = new HttpGet(url);
			if(header!=null && !header.isEmpty()){
				for(Entry<String, String> entry: header.entrySet()){
					getMethod.addHeader(entry.getKey(), entry.getValue());
				}
			}
			HttpResponse response = getHttpClient().execute(getMethod);
			String output = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
		    return Result.builder(output).status(response.getStatusLine().getStatusCode()).build();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public <T> Result<T> get(String url, Map<String, String> header,
			Class<T> returnClazz) {
		try{
			HttpGet getMethod = new HttpGet(url);
			if(header!=null && !header.isEmpty()){
				for(Entry<String, String> entry: header.entrySet()){
					getMethod.addHeader(entry.getKey(), entry.getValue());
				}
			}
			HttpResponse response = getHttpClient().execute(getMethod);
			String output = EntityUtils.toString(response.getEntity(), getCharset());
		    T data = JacksonUtil.convertFrom(output, returnClazz);
		    return Result.builder(data).status(response.getStatusLine().getStatusCode()).build();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public Result<String> post(String url, Map<String, String> header,
			HttpEntity entity) {
		try{
			HttpPost postMethod = new HttpPost(url);
//			HttpEntity entity = EntityBuilder.create().build();
			postMethod.setEntity(entity);
			if(header!=null && !header.isEmpty()){
				for(Entry<String, String> entry: header.entrySet()){
					postMethod.addHeader(entry.getKey(), entry.getValue());
				}
			}
		    HttpResponse response = getHttpClient().execute(postMethod);
		    String output = EntityUtils.toString(response.getEntity(), getCharset());
		    return Result.builder(output).status(response.getStatusLine().getStatusCode()).build();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public <T> Result<T> post(String url, Map<String, String> header,
			HttpEntity entity, Class<T> returnClazz) {
		try{
			HttpPost postMethod = new HttpPost(url);
//			HttpEntity entity = EntityBuilder.create().build();
			postMethod.setEntity(entity);
			if(header!=null && !header.isEmpty()){
				for(Entry<String, String> entry: header.entrySet()){
					postMethod.addHeader(entry.getKey(), entry.getValue());
				}
			}
		    HttpResponse response = getHttpClient().execute(postMethod);
		    String output = EntityUtils.toString(response.getEntity(), getCharset());
		    T data = JacksonUtil.convertFrom(output, returnClazz);
		    return Result.builder(data).status(response.getStatusLine().getStatusCode()).build();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
}
