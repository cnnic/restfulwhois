package com.cnnic.whois.bean;

import com.cnnic.whois.view.FormatType;

public class IpQueryParam extends QueryParam {
	private String ipInfo;
	private int ipLength;
	
	private long startHighAddr;
	private long endHighAddr;
	private long startLowAddr;
	private long endLowAddr;

	public IpQueryParam(String q, long startHighAddr, long endHighAddr,
			long startLowAddr, long endLowAddr) {
		super(q);
		this.startHighAddr = startHighAddr;
		this.endHighAddr = endHighAddr;
		this.startLowAddr = startLowAddr;
		this.endLowAddr = endLowAddr;
	}

	
	
	public IpQueryParam(FormatType formatType, PageBean page) {
		super();
		super.setFormat(formatType);
	}



	public String getIpInfo() {
		return ipInfo;
	}



	public void setIpInfo(String ipInfo) {
		this.ipInfo = ipInfo;
	}



	public int getIpLength() {
		return ipLength;
	}



	public void setIpLength(int ipLength) {
		this.ipLength = ipLength;
	}



	public long getStartHighAddr() {
		return startHighAddr;
	}

	public void setStartHighAddr(long startHighAddr) {
		this.startHighAddr = startHighAddr;
	}

	public long getEndHighAddr() {
		return endHighAddr;
	}

	public void setEndHighAddr(long endHighAddr) {
		this.endHighAddr = endHighAddr;
	}

	public long getStartLowAddr() {
		return startLowAddr;
	}

	public void setStartLowAddr(long startLowAddr) {
		this.startLowAddr = startLowAddr;
	}

	public long getEndLowAddr() {
		return endLowAddr;
	}

	public void setEndLowAddr(long endLowAddr) {
		this.endLowAddr = endLowAddr;
	}
}