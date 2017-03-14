package com.shinemo.openapi.demo.sdk.openmessage;

import com.shinemo.openapi.demo.sdk.factory.OpenapiClientFactory;
import com.shinemo.openapi.java.sdk.client.OpenapiClient;
import com.shinemo.openapi.java.sdk.exception.OpenapiException;
import com.shinemo.openapi.java.sdk.request.OpenAccountMsgRequest;
import com.shinemo.openapi.java.sdk.response.OpenAccountMsgResponse;

import java.util.ArrayList;
import java.util.List;

public class OpenAccountMsg {

    public static void main(String[] args) {
        List<String> receiversList = new ArrayList<String>();
        receiversList.add("112093240");
        receiversList.add("11048352");
        // 发送文本消息
        //OpenAccountMsgRequest request = new OpenAccountMsgRequest.OpenAccountMsgBuilder().textMsgBuilder("应用id", "消息内容", "消息接受者列表").build();
        OpenAccountMsgRequest request = new OpenAccountMsgRequest.OpenAccountMsgBuilder().textMsgBuilder("25575033", "你好", receiversList).build();
        // 发送图片消息
        //OpenAccountMsgRequest request1 = new OpenAccountMsgRequest.OpenAccountMsgBuilder().picMsgBuilder("应用id", "图片url", "消息接受者列表").build();
        OpenAccountMsgRequest request1 = new OpenAccountMsgRequest.OpenAccountMsgBuilder().picMsgBuilder("25575033", "https://app-icon-qn.jituancaiyun.com/-34-1488450864594.jpg", receiversList).build();
        // 发送图文消息(富文本消息)
        List<OpenAccountMsgRequest.MsgBody.Article> articles =new ArrayList<OpenAccountMsgRequest.MsgBody.Article>();
        //OpenAccountMsgRequest.MsgBody.Article article = new OpenAccountMsgRequest.MsgBody.Article("标题", "描述", "图片url", "点击图片跳转地址");
        OpenAccountMsgRequest.MsgBody.Article article = new OpenAccountMsgRequest.MsgBody.Article("标题：测试", "描述：测试", "https://app-icon-qn.jituancaiyun.com/-34-1488450864594.jpg", "http://www.baidu.com");
        articles.add(article);articles.add(article);articles.add(article);articles.add(article);articles.add(article);articles.add(article);articles.add(article);
        //OpenAccountMsgRequest request2 = new OpenAccountMsgRequest.OpenAccountMsgBuilder().newsMsgBuilder("应用id", "发送文章列表，单次最多发送4篇文章", "接受者列表").build();
        OpenAccountMsgRequest request2 = new OpenAccountMsgRequest.OpenAccountMsgBuilder().newsMsgBuilder("25575033", articles, receiversList).build();

        OpenapiClient client = OpenapiClientFactory.getClient();
        try {
            OpenAccountMsgResponse response = client.execute(request2, "MDAwMEZENEQ4RUQyOTRDNjJFQjAxMkY1RkI2NDVBQjU4RjQ1QzgzMzA0MDkzN0ZCNkNERURFQ0UxQzc1RkM5NDNBMzc4OTkxNTg1OTMyMzg2OTUzNkVGREZCNjdFQUQzMkU2Nw==");
            System.out.println(response.toString());
        } catch (OpenapiException e) {
            // TODO handle
            e.printStackTrace();
        } catch (Exception e){
            // TODO handle
            e.printStackTrace();
        }
    }

}
