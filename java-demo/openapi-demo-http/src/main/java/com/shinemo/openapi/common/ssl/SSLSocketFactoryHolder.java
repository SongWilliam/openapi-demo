package com.shinemo.openapi.common.ssl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;


public class SSLSocketFactoryHolder {

	public static SSLSocketFactory get(){
		SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (KeyManagementException k)
        {
            throw new RuntimeException(k);
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

	}
	
}
