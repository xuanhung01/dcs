package com.shf.dcs.configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shf.dcs.model.DebtUserPrivilege;
import com.shf.dcs.security.CustomAuthenticationFilter;
import com.shf.dcs.security.CustomLogoutHandler;
import com.shf.dcs.security.JwtAuthenticationFilter;
import com.shf.dcs.service.IRoleService;

@Configuration
@ComponentScan(basePackages = { "com.shf.dcs.security"})
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	// Security settings
	public boolean useCaptcha = true;
	
    @Autowired
    CustomAuthenticationProvider customAuthProvider;
    
	@Autowired
	private IRoleService roleService;

	@Autowired
	private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
	
	//@Autowired
	//private CustomLogoutHandler customLogoutHandler;
		
	public SecurityConfig() {
		super();
	}
	
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthProvider);
    }

	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		logger.info("Setup Spring security");
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
		
		List<DebtUserPrivilege> listPrivilege = roleService.findAllParentNotNull();
		// @formatter:off
		http.csrf().ignoringAntMatchers("/j_spring_security_logout**").and()
				.authorizeRequests()
					.antMatchers(
						"/j_spring_security_check*",
						"/login*",
						"/csrf",
						"/j_spring_security_logout*",
						"/invalidSession",
						"/killSession",
						"/logout*", 
						"/ajax/**", 
						"/resources/**",
						"/file*",
						"/file**")
				.permitAll().and()
				.exceptionHandling().accessDeniedPage("/invalidSession").and()
	            // .csrf().csrfTokenRepository(csrfTokenRepository()).ignoringAntMatchers("/j_spring_security_logout**").and()
				.addFilterBefore(filter, CsrfFilter.class)
				.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilterAfter(customAuthFilter(), RequestHeaderAuthenticationFilter.class)
				/*.formLogin()
					.loginPage("/login")
					.loginProcessingUrl("/j_spring_security_check")
					// .defaultSuccessUrl("/admin/dashboard")
					.failureUrl("/login?error=true")
					.successHandler(myAuthenticationSuccessHandler)
					.usernameParameter("j_username")
					.passwordParameter("j_password")
				.permitAll().and()*/
				.sessionManagement()
					//.invalidSessionUrl("/invalidSession")
					.sessionFixation().newSession().and()
				.logout()
					//.invalidateHttpSession(false)
					.logoutUrl("/j_spring_security_logout")
					.deleteCookies("JSESSIONID")
					.logoutSuccessUrl("/")
					//.logoutSuccessHandler(customLogoutHandler)
					.invalidateHttpSession(false)
					.clearAuthentication(true)
					.permitAll();
        //
		for (DebtUserPrivilege privilege : listPrivilege) {		
			http.authorizeRequests().antMatchers(privilege.getReqUrl()).access("hasRole('ROLE_"+privilege.getId()+"')");
		}
	}
	
   @Bean
    public UsernamePasswordAuthenticationFilter customAuthFilter() throws Exception {
        UsernamePasswordAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(objectMapper());
        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/j_spring_security_check"));
        authenticationFilter.setUsernameParameter("j_username");
        authenticationFilter.setPasswordParameter("j_password");
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        authenticationFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error=true"));
        authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        authenticationFilter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy());

        return authenticationFilter;
    }

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

	public boolean isUseCaptcha() {
		return useCaptcha;
	}

	public void setUseCaptcha(boolean useCaptcha) {
		this.useCaptcha = useCaptcha;
	}
	
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return myAuthenticationSuccessHandler;
    }

    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new CompositeSessionAuthenticationStrategy(Arrays.asList(
            new ChangeSessionIdAuthenticationStrategy()/*,
            new CsrfAuthenticationStrategy(csrfTokenRepository())*/
        ));
    }

    /*@Bean
    public CsrfTokenRepository csrfTokenRepository() {
        return new LazyCsrfTokenRepository(new HttpSessionCsrfTokenRepository());
    }*/
	
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}