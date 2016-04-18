package com.shinemo.openapi.jituancaiyun.shedule;

import java.util.TimerTask;

import com.shinemo.openapi.jituancaiyun.api.AccessTokenVisitor;
/**
 * accessToken定时刷新器
 *
 */
public class AccessTokenRefreshTask extends TimerTask {

	@Override
	public void run() {
		Boolean accessTokenResult = AccessTokenVisitor.refresh();
		if(!accessTokenResult){
			//处理刷新失败
		}
	}

}
