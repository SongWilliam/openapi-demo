package com.shinemo.openapi.jituancaiyun.common;

import org.apache.http.client.CredentialsProvider;

public class HttpClientConfig {

	private boolean isAuth;

	/**
	 * If isAuth is true, credProvider can't be null.
	 */
	private CredentialsProvider credProvider;

	private boolean isGZip;

	private boolean isAutoRedirect;

	/**
	 * 9:0.9, 0:1.0, 1:1.1
	 */
	private int httpVersion;

	private boolean useExpectContinue;

	private int connectionTimeout;

	private int socketBufferSize;

	private int soTimeout;

	private int connectionManagerTimeout;

	private boolean staleCheckingEnabled;

	private boolean tcpNoDelay;

	private String contentCharset;

	private String userAgent;

	private int maxConnectionsPerRoute;

	private int maxTotalConnections;

	private int httpPort;

	private int httpsPort;

	public boolean isAuth() {
		return isAuth;
	}

	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}

	public CredentialsProvider getCredProvider() {
		return credProvider;
	}

	public void setCredProvider(CredentialsProvider credProvider) {
		this.credProvider = credProvider;
	}

	public boolean isGZip() {
		return isGZip;
	}

	public void setGZip(boolean isGZip) {
		this.isGZip = isGZip;
	}

	public boolean isAutoRedirect() {
		return isAutoRedirect;
	}

	public void setAutoRedirect(boolean isAutoRedirect) {
		this.isAutoRedirect = isAutoRedirect;
	}

	public int getHttpVersion() {
		return httpVersion;
	}

	public void setHttpVersion(int httpVersion) {
		this.httpVersion = httpVersion;
	}

	public boolean isUseExpectContinue() {
		return useExpectContinue;
	}

	public void setUseExpectContinue(boolean useExpectContinue) {
		this.useExpectContinue = useExpectContinue;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getSocketBufferSize() {
		return socketBufferSize;
	}

	public void setSocketBufferSize(int socketBufferSize) {
		this.socketBufferSize = socketBufferSize;
	}

	public int getSoTimeout() {
		return soTimeout;
	}

	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}

	public int getConnectionManagerTimeout() {
		return connectionManagerTimeout;
	}

	public void setConnectionManagerTimeout(int connectionManagerTimeout) {
		this.connectionManagerTimeout = connectionManagerTimeout;
	}

	public boolean isStaleCheckingEnabled() {
		return staleCheckingEnabled;
	}

	public void setStaleCheckingEnabled(boolean staleCheckingEnabled) {
		this.staleCheckingEnabled = staleCheckingEnabled;
	}

	public boolean isTcpNoDelay() {
		return tcpNoDelay;
	}

	public void setTcpNoDelay(boolean tcpNoDelay) {
		this.tcpNoDelay = tcpNoDelay;
	}

	public String getContentCharset() {
		return contentCharset;
	}

	public void setContentCharset(String contentCharset) {
		this.contentCharset = contentCharset;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public int getMaxConnectionsPerRoute() {
		return maxConnectionsPerRoute;
	}

	public void setMaxConnectionsPerRoute(int maxConnectionsPerRoute) {
		this.maxConnectionsPerRoute = maxConnectionsPerRoute;
	}

	public int getMaxTotalConnections() {
		return maxTotalConnections;
	}

	public void setMaxTotalConnections(int maxTotalConnections) {
		this.maxTotalConnections = maxTotalConnections;
	}

	public int getHttpPort() {
		return httpPort;
	}

	public void setHttpPort(int httpPort) {
		this.httpPort = httpPort;
	}

	public int getHttpsPort() {
		return httpsPort;
	}

	public void setHttpsPort(int httpsPort) {
		this.httpsPort = httpsPort;
	}

	@Override
	public String toString() {
		return "HttpClientConfig [connectionManagerTimeout="
				+ connectionManagerTimeout + ", connectionTimeout="
				+ connectionTimeout + ", contentCharset=" + contentCharset
				+ ", credProvider=" + credProvider + ", httpPort=" + httpPort
				+ ", httpVersion=" + httpVersion + ", httpsPort=" + httpsPort
				+ ", isAuth=" + isAuth + ", isAutoRedirect=" + isAutoRedirect
				+ ", isGZip=" + isGZip + ", maxConnectionsPerRoute="
				+ maxConnectionsPerRoute + ", maxTotalConnections="
				+ maxTotalConnections + ", soTimeout=" + soTimeout
				+ ", socketBufferSize=" + socketBufferSize
				+ ", staleCheckingEnabled=" + staleCheckingEnabled
				+ ", tcpNoDelay=" + tcpNoDelay + ", useExpectContinue="
				+ useExpectContinue + ", userAgent=" + userAgent + "]";
	}

	public static HttpClientConfig getDefaultConfig() {
		HttpClientConfig config = new HttpClientConfig();

		config.setAuth(false);
		config.setAutoRedirect(true);
		config.setConnectionManagerTimeout(15000);
		config.setConnectionTimeout(15000);
		config.setContentCharset("UTF-8");
		config.setCredProvider(null);
		config.setGZip(false);
		config.setHttpPort(8090);
		config.setHttpsPort(8089);
		config.setHttpVersion(1);
		config.setMaxConnectionsPerRoute(200);
		config.setMaxTotalConnections(500);
		config.setSocketBufferSize(1048576);
		config.setSoTimeout(15000);
		config.setStaleCheckingEnabled(false);
		config.setTcpNoDelay(true);
		config.setUseExpectContinue(false);

		return config;
	}

}
