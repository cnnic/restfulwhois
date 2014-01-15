package com.cnnic.whois.oauth.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cnnic.whois.dao.oauth.OAuthAccessorDao;

public class OauthQuartz extends QuartzJobBean {

	private OAuthAccessorDao oauthAccessorDao;
	
	public OAuthAccessorDao getOauthAccessorDao() {
		return oauthAccessorDao;
	}
	@Autowired
	public void setOauthAccessorDao(OAuthAccessorDao oauthAccessorDao) {
		this.oauthAccessorDao = oauthAccessorDao;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		this.getOauthAccessorDao().deleteInvalidDate();
		System.out.println("---------------------------");
	}
	
}
