package com.shf.dcs.boot;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.shf.dcs.configuration.SecurityConfig;
import com.shf.dcs.configuration.SpringAppConfig;
import com.shf.dcs.configuration.SpringMvcConfig;

@Component
public class CMSWebInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringAppConfig.class };
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter utf8EncodingFilter = new CharacterEncodingFilter();
		utf8EncodingFilter.setEncoding("utf-8");
		utf8EncodingFilter.setForceEncoding(true);
		return new Filter[] { utf8EncodingFilter };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringMvcConfig.class, SecurityConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(
			ServletRegistration.Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}
}