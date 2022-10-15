package com.shf.dcs.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.shf.dcs.dao.PrivilegeRepository;
import com.shf.dcs.model.DebtUserPrivilege;
import com.shf.dcs.model.DebtUserRole;
import com.shf.dcs.model.DebtUser;
import com.shf.dcs.service.IUserService;
import com.shf.dcs.utils.Constants;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private static Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Autowired
	private Environment env;
	
	@SuppressWarnings("static-access")
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        logger.info("User login : "+ userName +" "+env.getProperty("ld.env"));
        // 1. Tìm kiếm user trong bảng
        DebtUser user = userService.findUserByUserName(userName);
        if(user == null) {
        	return null;
        } else  if (!user.getEnabled()) {
        	throw new InternalAuthenticationServiceException("disabled");
        }
        
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        List<String> listPrivilegeId = new ArrayList<String>();
        if(user.getRoles() != null || user.getRoles().size() > 0) {
        	for (DebtUserRole role : user.getRoles()) {
        		listPrivilegeId = privilegeRepository.findPrivilegeIdbyRoleId(role.getId());
        		for (String PrivilegeId : listPrivilegeId) {
        			// config với role HEADER,LEADER vào dashboard
        			if(Constants.PRIVILEGE_ID_CALL_TEAMLEADER.equals(PrivilegeId) || Constants.PRIVILEGE_ID_HARD_TEAMLEADER.equals(PrivilegeId)) {
        				authorities.add(new SimpleGrantedAuthority("ROLE_"+Constants.PRIVILEGE_CALLER_ID));
        			}
        			authorities.add(new SimpleGrantedAuthority("ROLE_"+PrivilegeId));
				}
			}
        }
        
        // 2. Tìm kiếm user trong ldap
        if(Constants.ENV_LIVE.equals(env.getProperty("ld.env")) && !getLdapContext(userName, password)) {
        	logger.info("User :"+ userName +"&password :"+password);
        	throw new InternalAuthenticationServiceException("ldap");
        }
        
        /*if(!"shf@123456".equals(password)) {
        	throw new InternalAuthenticationServiceException("ldap");
        }*/
        
        // Your custom authentication logic here
		
        Authentication auth = new UsernamePasswordAuthenticationToken(StringUtils.upperCase(userName),password, authorities);
        return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	
	// UTIL

	public final Collection<? extends GrantedAuthority> getAuthorities(
			final Collection<DebtUserRole> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}

	private final List<String> getPrivileges(final Collection<DebtUserRole> roles) {
		final List<String> privileges = new ArrayList<String>();
		final List<DebtUserPrivilege> collection = new ArrayList<DebtUserPrivilege>();
		for (final DebtUserRole role : roles) {
			collection.addAll(role.getPrivileges());
		}
		for (final DebtUserPrivilege item : collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}

	private final List<GrantedAuthority> getGrantedAuthorities(
			final List<String> privileges) {
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (final String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));

			if (privilege.equalsIgnoreCase("SYSTEM_PRIVILEGE")) {
				authorities.add(new SimpleGrantedAuthority("ROLE_SADMIN"));
			} else if (privilege.equalsIgnoreCase("REPORT_FORM_PRIVILEGE")) {
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			} else if (privilege
					.equalsIgnoreCase("EDIT_USER_PROFILE_PRIVILEGE")) {
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			}

		}
		return authorities;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private boolean getLdapContext(String username, String password) {
		boolean flag = true;
		String url = env.getProperty("ld.env.url");
		try {
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.SECURITY_AUTHENTICATION, "Simple");
			env.put(Context.SECURITY_PRINCIPAL, username+"@shbfinance.com.vn");
			env.put(Context.SECURITY_CREDENTIALS, password);
			env.put(Context.PROVIDER_URL, url);
			new InitialLdapContext(env, null);
		} catch (NamingException nex) {
			flag = false;
		}
		return flag;
	}
}
