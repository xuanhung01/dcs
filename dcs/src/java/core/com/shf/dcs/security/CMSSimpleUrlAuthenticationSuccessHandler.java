package com.shf.dcs.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.shf.dcs.dao.PrivilegeRepository;
import com.shf.dcs.dao.UserRepository;
import com.shf.dcs.model.DebtUserRole;
import com.shf.dcs.model.DebtUser;
import com.shf.dcs.utils.Constants;
import com.shf.dcs.utils.DateUtilDcs;

@Component("CMSAuthenticationSuccessHandler")
public class CMSSimpleUrlAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {
	
	// FIXME: Exprired session in 30 minutes
	private static final int SESSION_DURATION = 480 * 60;

	private static Logger logger = Logger.getLogger(CMSSimpleUrlAuthenticationSuccessHandler.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication) throws IOException {
		final HttpSession session = request.getSession(false);
		if (session != null) {
			session.setMaxInactiveInterval(SESSION_DURATION);
		}
		// logger.info("AAAAAAAAAAA"+session.getId());
		// load menu trái
		String userName = authentication.getName();
        // Tìm kiếm user trong bảng
		DebtUser user = userRepository.findByUsername(StringUtils.upperCase(userName));
        
        List<String> listRoleChildId = new ArrayList<String>();
        List<String> listRoleParentId = new ArrayList<String>();
        if(user.getRoles() != null || user.getRoles().size() > 0) {
        	for (DebtUserRole role : user.getRoles()) {
        		listRoleChildId = privilegeRepository.findPrivilegeIdbyRoleId(role.getId());
        		listRoleParentId = privilegeRepository.findPrivilegeParentIdbyRoleId(role.getId());
			}
        }
        
        // set model in request
        request.getSession().setAttribute("listMenuLeftChild", listRoleChildId);
        request.getSession().setAttribute("listMenuLeftParent", listRoleParentId);
        request.getSession().setAttribute("dateTimeLogin", DateUtilDcs.convertDateToStringFull(new Date()));
        request.getSession().setAttribute("operatorName", user.getRealname());
        request.getSession().setAttribute("operatorCode", user.getUsername());
        
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
	}

	protected void handle(final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication) throws IOException {

		String targetUrl = Constants.URL_DASH_BOARD;

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to "+ targetUrl);
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	
	protected void clearAuthenticationAttributes(
			final HttpServletRequest request) {
		final HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	
	public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
}