package com.shf.dcs.error;

import java.util.Date;

public class NetworkConnectivityException extends CMSRuntimeException {

	private Date time;

	public NetworkConnectivityException(String msg, Date time) {
		super(msg);
		this.time = time;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
