package com.shinemo.openapi.jituancaiyun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class AppUserUrlConn {

	public static void main(String[] args) throws Exception {
		InputStreamReader reader = null;
		BufferedReader in = null;
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(
					"https://api.open.jituancaiyun.com/openapi/token/get?appId=42979611&appSecret=7511B4A213A7710C");
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			// TODO checkResponse
			reader = new InputStreamReader(conn.getInputStream());
			in = new BufferedReader(reader);
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			conn.disconnect();
			String returnStr = response.toString();
			System.out.println(returnStr);
		} catch (Exception e) {
			// TODO handler exception
		} finally {
			if (in != null) {
				in.close();
			}
			if (reader != null) {
				reader.close();
			}
		}
	}

	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

}
