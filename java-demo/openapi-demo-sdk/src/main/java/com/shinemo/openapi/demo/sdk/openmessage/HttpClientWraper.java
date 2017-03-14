package com.shinemo.openapi.demo.sdk.openmessage;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;


public class HttpClientWraper {

    public static String PostJson(String url, Map<String, Object> postParams)
            throws Exception {
        String postUrl = url;
        Gson gson = new Gson();
        HttpClient httpclient = getHttpClient();
        HttpPost post = new HttpPost(postUrl);

        post.addHeader("Content-Type", "text/plain");
        //post.addHeader("charset", "UTF-8");

        if (postParams != null) {
            StringEntity postingString = new StringEntity(gson.toJson(postParams), "UTF-8");
            post.setEntity(postingString);
        }

        HttpResponse response = httpclient.execute(post);
        HttpEntity entity = response.getEntity();
        if (null == entity) {
            throw new Exception("getEntity Failed");
        }
        String strResult = EntityUtils.toString(entity, "UTF-8");
        return strResult;
    }

    public static void main(String[] args) {
        String url = "https://api.open.jituancaiyun.com/openapi/openaccount/sendmsg?accessToken=MDAwMEZENEQ4RUQyOTRDNjJFQjAxMkY1RkI2NDVBQjU4RjQ1NzZDQzAzRDk0NDUzQzkwQUQ1QkI4Nzc4NTMwNTA2ODVEOTcwRkI1OTA5RDU3OEZCRUQwREY0NzE1QUZDMDM5Ng==";
        Map<String, Object> postParams = new HashMap<String, Object>();
        postParams.put("appId", "25575033");
        postParams.put("msgType", "text");
        Map<String, String> msgBodyParams = new HashMap<String, String>();
        msgBodyParams.put("content", "你好");
        postParams.put("msgBody", msgBodyParams);
        List<String> receiversList = new ArrayList<String>();
        receiversList.add("112093240");
        receiversList.add("110483528");
        postParams.put("receivers", receiversList);

        try {
            String re = PostJson(url, postParams);
            System.out.println(re);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static HttpClient getHttpClient() {
        try {
            SSLContext ctx = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            RequestConfig config = RequestConfig.custom()
                    .setConnectionRequestTimeout(3000)
                    .setConnectTimeout(3000)
                    .setSocketTimeout(3000)
                    .build();
            CloseableHttpClient client = HttpClientBuilder.create().setSSLContext(ctx).setDefaultRequestConfig(config).build();
            return client;
        } catch (Exception e) {

        }
        return null;
    }
}
