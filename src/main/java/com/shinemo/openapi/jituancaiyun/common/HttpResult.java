package com.shinemo.openapi.jituancaiyun.common;


public class HttpResult {

    private int status;

    private String result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "result='" + result + '\'' +
                ", status=" + status +
                '}';
    }
}
