package main.java.com.shinemo.openapi.common;

import main.java.com.shinemo.openapi.common.ssl.SSLSocketFactoryHolder;
import main.java.com.shinemo.openapi.common.ssl.TrustAnyHostnameVerifier;

import okhttp3.OkHttpClient;

/**
 * For okHttpClients
 *
 */
public class OkHttpsHelper {

	public static OkHttpClient getHttpCLient() {
		try {
			OkHttpClient.Builder builder = new OkHttpClient.Builder();
			builder.sslSocketFactory(SSLSocketFactoryHolder.get());
			builder.hostnameVerifier(new TrustAnyHostnameVerifier());

			OkHttpClient okHttpClient = builder.build();
			return okHttpClient;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
