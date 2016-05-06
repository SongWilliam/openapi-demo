package main.java.com.shinemo.openapi.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import main.java.com.shinemo.openapi.common.ssl.SSLSocketFactoryHolder;
import main.java.com.shinemo.openapi.common.ssl.TrustAnyHostnameVerifier;
import com.shinemo.openapi.domain.Result;

/**
 * JDK 自带http conn连接
 *
 */
public class JdkHttpsHelper {
	
	public Result<String> get(String url, Map<String, String> header){
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		InputStream inputStream = null;
		HttpsURLConnection conn = null;
		try{
			URL console = new URL(url);
			conn = (HttpsURLConnection) console.openConnection();
			conn.setSSLSocketFactory(SSLSocketFactoryHolder.get());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			inputStream = conn.getInputStream();
			byte[] buffer = new byte[1024];
			while(inputStream.read(buffer)!=-1){
				byteArrayOutputStream.write(buffer);
			}
			String returnStr = byteArrayOutputStream.toString("utf-8");
			conn.disconnect();
			byteArrayOutputStream.close();
			return Result.builder(returnStr).status(responseCode).build();
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			try {
				byteArrayOutputStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
