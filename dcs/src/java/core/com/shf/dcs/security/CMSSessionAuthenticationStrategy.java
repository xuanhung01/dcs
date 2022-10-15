package com.shf.dcs.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;


public class CMSSessionAuthenticationStrategy extends
		ConcurrentSessionControlAuthenticationStrategy {
	
	SessionRegistry sr;

	@Autowired
	public CMSSessionAuthenticationStrategy(SessionRegistry sr) {
		super(sr);
		this.sr = sr;
	}

	@Override
	public void onAuthentication(Authentication authentication,
			HttpServletRequest request, HttpServletResponse response) {
		if (sr.getAllPrincipals().size() > 250) {
			throw new SessionAuthenticationException(
					"Maximum number of users exceeded");
		}
		super.onAuthentication(authentication, request, response);
	}
}