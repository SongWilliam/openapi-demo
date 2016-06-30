package com.shinemo.openapi.common.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

/**
 * 信任任何
 *
 */
public class TrustAnyTrustManager implements X509TrustManager{


	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
		
	}


	public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
		
	}


	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[] {};
	}
	
}
