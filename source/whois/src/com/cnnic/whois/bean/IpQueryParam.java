package com.cnnic.whois.bean;

public class IpQueryParam extends QueryParam {
	private long startHighAddr;
	private long endHighAddr;
	private long startLowAddr;
	private long endLowAddr;

	public IpQueryParam(String q, String role, long startHighAddr, long endHighAddr,
			long startLowAddr, long endLowAddr) {
		super(q, role);
		this.startHighAddr = startHighAddr;
		this.endHighAddr = endHighAddr;
		this.startLowAddr = startLowAddr;
		this.endLowAddr = endLowAddr;
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