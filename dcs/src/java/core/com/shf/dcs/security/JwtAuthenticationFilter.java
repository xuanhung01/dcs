package com.shf.dcs.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import com.shf.dcs.configuration.JwtTokenProvider;
import com.shf.dcs.model.DebtUser;
import com.shf.dcs.service.IUserService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private static final Logger logger = Logger.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private IUserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			// Lấy jwt từ request
			String username = null;
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication != null) {
				if (!(authentication instanceof AnonymousAuthenticationToken)) {
					username = authentication.getName();
				}
				if(StringUtils.isNotEmpty(username)) {
					String jwt = (String)request.getSession().getAttribute("JWT");
					if (StringUtils.isNotEmpty(jwt) && tokenProvider.validateToken(jwt)) {
						// tìm kiếm xem token và username có đang valid trong DEBT_USER không
						DebtUser user = userService.findUserByUserName(username);
						if(user == null || !user.getUsername().equals(username) || !user.getWebJsessionId().equals(jwt)) {
							response.sendError(603);
						} else {
							// Nếu người dùng hợp lệ, set thông tin cho Seturity Context
							user.setLastUpdatedWebJwDate(new Date());
							userService.saveUser(user);
						}
					}
				}
			}
		} catch (Exception ex) {
			logger.error(ExceptionUtils.getStackTrace(ex));
		}

		filterChain.doFilter(request, response);
	}
}
