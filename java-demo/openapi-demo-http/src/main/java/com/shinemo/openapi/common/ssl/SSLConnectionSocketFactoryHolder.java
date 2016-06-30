package com.shinemo.openapi.common.ssl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

public class SSLConnectionSocketFactoryHolder {

	public static SSLConnectionSocketFactory get(){
		SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new SecureRandom());
        } catch (KeyManagementException k)
        {
            throw new RuntimeException(k);
        }catch ( NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
	}
}
