package com.shf.dcs.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecaptchaResponse {
	private boolean success;
	private String challenge_ts;
	private String hostName;

	@JsonProperty("success")
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@JsonProperty("challenge_ts")
	public String getChallenge_ts() {
		return challenge_ts;
	}

	public void setChallenge_ts(String challenge_ts) {
		this.challenge_ts = challenge_ts;
	}

	@JsonProperty("hostname")
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	@Override
	public String toString() {
		return "RecaptchaResponse [success=" + success + ", challenge_ts=" + challenge_ts + ", hostName=" + hostName
				+ "]";
	}
}
