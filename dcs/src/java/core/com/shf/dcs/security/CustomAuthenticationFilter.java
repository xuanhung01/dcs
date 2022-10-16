package com.shf.dcs.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shf.dcs.configuration.CustomAuthenticationProvider;
import com.shf.dcs.configuration.JwtTokenProvider;
import com.shf.dcs.dao.PrivilegeRepository;
import com.shf.dcs.model.DebtUserRole;
import com.shf.dcs.model.DebtUser;
import com.shf.dcs.service.IUserService;
import com.shf.dcs.utils.Constants;
import com.shf.dcs.utils.VerifyUtils;

import vn.co.dssfw.utils.DateUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static Logger logger = Logger.getLogger(CustomAuthenticationFilter.class);

	private static final String BODY_ATTRIBUTE = CustomAuthenticationFilter.class.getSimpleName() + ".body";

	private final ObjectMapper objectMapper;

	@Autowired
	private IUserService userService;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private JwtTokenProvider tokenProvider;

	// @Autowired
	// private ThamSoService thamSoService;

	@Autowired
	private Environment env;

	public CustomAuthenticationFilter(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	/*
	 * @Override public void doFilter(ServletRequest req, ServletResponse res,
	 * FilterChain chain) throws IOException, ServletException { HttpServletRequest
	 * request = (HttpServletRequest) req; HttpServletResponse response =
	 * (HttpServletResponse) res;
	 * 
	 * // Parse the request body as a HashMap and populate a request attribute if
	 * (requiresAuthentication(request, response)) { // valid Captcha if
	 * (!rpHash(request.getParameter("defaultReal")).equals(request.getParameter(
	 * "defaultRealHash"))){ throw new
	 * InternalAuthenticationServiceException("captcha"); } }
	 * 
	 * super.doFilter(req, res, chain); }
	 */

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("j_username");
		String password = request.getParameter("j_password");
		// String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

		// valid captcha
		/*
		 * if(!VerifyUtils.validateCaptcha(gRecaptchaResponse)) { throw new
		 * InternalAuthenticationServiceException("captcha"); }
		 */

		DebtUser user = userService.findUserByUserName(userName);
		logger.info("User login : " + userName + " " + env.getProperty("ld.env"));
		// user không tồn tại
		if (user == null) {
			throw new InternalAuthenticationServiceException("not found");
		} else if (!user.getEnabled()) { // user bị disable
			throw new InternalAuthenticationServiceException("disabled");
		} else if (user.getNumberRetryLogin() != null && user.getNumberRetryLogin().equals(5)) { // bị lock
			// Chưa đủ 30 phút vẫn báo lỗi
			if (DateUtil.compare(DateUtil.getRelativeMinutes(user.getLastUpdatedDate(), 10),
					DateUtil.getCurrentDate()) > 0) {
				throw new InternalAuthenticationServiceException("lock");
			} else {
				// unlock user và reset lại từ đầu
				user.setNumberRetryLogin(null);
				user.setLastUpdatedDate(new Date());
				userService.saveUser(user);
			}
		}

		// Nếu là đối tác shbfc

		/*
		 * BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(); if
		 * (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
		 * processParternLoginFail(user); throw new
		 * InternalAuthenticationServiceException("password"); } else {
		 * processParternLoginSuccess(user); }
		 */

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<String> listPrivilegeId = new ArrayList<String>();
		if (user.getRoles() != null || user.getRoles().size() > 0) {
			for (DebtUserRole role : user.getRoles()) {
				//
				listPrivilegeId = privilegeRepository.findPrivilegeIdbyRoleId(role.getId());
				for (String PrivilegeId : listPrivilegeId) {
					// config với role HEADER,LEADER vào dashboard
					if (Constants.PRIVILEGE_ID_CALL_TEAMLEADER.equals(PrivilegeId)
							|| Constants.PRIVILEGE_ID_HARD_TEAMLEADER.equals(PrivilegeId)) {
						authorities.add(new SimpleGrantedAuthority("ROLE_" + Constants.PRIVILEGE_CALLER_ID));
					}
					authorities.add(new SimpleGrantedAuthority("ROLE_" + PrivilegeId));
				}
			}
		}

		/*
		 * if(!"shf@123456".equals(password)) { throw new
		 * InternalAuthenticationServiceException("ldap"); }
		 */
		// Trả về jwt cho người dùng.
		String jwt = tokenProvider.generateToken(StringUtils.upperCase(userName));
		userService.updateJW(StringUtils.upperCase(userName), jwt);
		request.getSession().setAttribute("JWT", jwt);
		// Your custom authentication logic here
		Authentication auth = new UsernamePasswordAuthenticationToken(StringUtils.upperCase(userName), password,
				authorities);
		return auth;
	}

	private void processParternLoginFail(DebtUser user) {
		// Xử lý count số lần login
		Integer count = user.getNumberRetryLogin();
		if (count == null) {
			count = 1;
		} else if (count < 5) {
			count++;
		}
		user.setNumberRetryLogin(count);
		user.setLastUpdatedDate(new Date());
		userService.saveUser(user);
	}

	private void processParternLoginSuccess(DebtUser user) {
		// Xử lý reset retry number
		user.setNumberRetryLogin(null);
		userService.saveUser(user);
	}

	/*
	 * protected String obtainUsername(HttpServletRequest request) {
	 * UsernamePasswordRequest usernamePasswordRequest = (UsernamePasswordRequest)
	 * request .getAttribute(BODY_ATTRIBUTE); return
	 * usernamePasswordRequest.get(getUsernameParameter()); }
	 * 
	 * protected String obtainPassword(HttpServletRequest request) {
	 * UsernamePasswordRequest usernamePasswordRequest = (UsernamePasswordRequest)
	 * request .getAttribute(BODY_ATTRIBUTE); return
	 * usernamePasswordRequest.get(getPasswordParameter()); }
	 * 
	 * private static class UsernamePasswordRequest extends HashMap<String, String>
	 * { // Nothing, just a type marker }
	 */

	private static String rpHash(String value) {
		int hash = 5381;
		value = value.toUpperCase();
		for (int i = 0; i < value.length(); i++) {
			hash = ((hash << 1) + hash) + value.charAt(i);
		}
		return String.valueOf(hash);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private boolean getLdapContext(String username, String password) {
		boolean flag = true;
		String url = env.getProperty("ld.env.url");
		try {
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.SECURITY_AUTHENTICATION, "Simple");
			env.put(Context.SECURITY_PRINCIPAL, username + "@shbfinance.com.vn");
			env.put(Context.SECURITY_CREDENTIALS, password);
			env.put(Context.PROVIDER_URL, url);
			new InitialLdapContext(env, null);
		} catch (NamingException nex) {
			flag = false;
		}
		return flag;
	}
}
