package com.shf.dcs.configuration;

import java.util.Arrays;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.shf.dcs.validation.EmailValidator;
import com.shf.dcs.validation.PasswordMatchesValidator;

import freemarker.template.TemplateExceptionHandler;

@Configuration
@ComponentScan(basePackages = { "com.shf.dcs.controller", "com.shf.dcs.validation" })
@EnableWebMvc
@EnableCaching
public class SpringMvcConfig extends WebMvcConfigurerAdapter  {
	
	private static Logger logger = Logger.getLogger(SpringAppConfig.class);
	
	public static final long MAX_FILESIZE = FileUtils.ONE_MB * 20;
	@Autowired
	ServletContext context;

	public SpringMvcConfig() {
		super();
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getMultipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxInMemorySize(2000000);
		commonsMultipartResolver.setMaxUploadSize(MAX_FILESIZE);
		return commonsMultipartResolver;
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		// messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		// messageSource.setCacheSeconds(0);
		// messageSource.setBasename("messages");
		messageSource.setBasename("classpath:messages");
		return messageSource;
	}

	@Bean
	public freemarker.template.Configuration freeMakerConfiguration() {
		freemarker.template.Configuration cfg = new freemarker.template.Configuration();

		cfg.setServletContextForTemplateLoading(context, "WEB-INF/templates");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		return cfg;
	}

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		super.addViewControllers(registry);
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/",
				"/resources/");
	}

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		registry.addInterceptor(localeChangeInterceptor);
	}

	// beans

	@Bean
	public ViewResolver viewResolver() {
		final InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		return bean;
	}

	@Bean
	public LocaleResolver localeResolver() {
		final CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
		// cookieLocaleResolver.setDefaultLocale(new Locale("vi", "VI"));
		return cookieLocaleResolver;
	}

	@Bean
	public EmailValidator usernameValidator() {
		return new EmailValidator();
	}

	@Bean
	public PasswordMatchesValidator passwordMatchesValidator() {
		return new PasswordMatchesValidator();
	}
	
	@Bean
	public LocalValidatorFactoryBean validator()
	{
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}

	
    @Bean
    public CacheManager cacheManager() {
    	logger.info("DCS: Setup caches");

		// Danh muc dia ban
		Cache allProvinceCache = new ConcurrentMapCache("allProvinceCache");
		Cache allDistrictCache = new ConcurrentMapCache("allDistrictCache");
		Cache allWardCache = new ConcurrentMapCache("allWardCache");
		
		// danh mục chưa sử dụng
		Cache allDebtSysNodeCodeTypeCache = new ConcurrentMapCache("allDebtSysNodeCodeTypeCache");
		Cache allDebtSysGroupCache = new ConcurrentMapCache("allDebtSysGroupCache");
		Cache allDebtSysLimitNodeCodeCache = new ConcurrentMapCache("allDebtSysLimitNodeCodeCache");
		Cache allDebtGroupTeam = new ConcurrentMapCache("allDebtGroupTeam");
		// adhoc search
		Cache allAdhocDebtCustomer = new ConcurrentMapCache("allAdhocDebtCustomer");
		Cache allAdhocDebtCustomerLdDetailV = new ConcurrentMapCache("allAdhocDebtCustomerLdDetailV");
		Cache allDebtAdMapRelation = new ConcurrentMapCache("allDebtAdMapRelation");
		Cache allDebtDocumentDetailCategory = new ConcurrentMapCache("allDebtDocumentDetailCategory");
		Cache allDebtDocumentTypeCategory = new ConcurrentMapCache("allDebtDocumentTypeCategory");
		
		//Cache allDebtLdPaidHistoryCache = new ConcurrentMapCache("allDebtLdPaidHistoryCache");
		
		// danh mục under user
		//Cache allUnderUserCaches = new ConcurrentMapCache("allUnderUserCaches");
		//Cache allLevelUserCaches = new ConcurrentMapCache("allLevelUserCaches");
		
		SimpleCacheManager manager = new SimpleCacheManager();
		manager.setCaches(Arrays.asList(allProvinceCache,allDistrictCache,allWardCache,
				allDebtSysGroupCache,allDebtSysNodeCodeTypeCache,allDebtSysLimitNodeCodeCache,
				allAdhocDebtCustomer, allAdhocDebtCustomerLdDetailV,allDebtGroupTeam, allDebtAdMapRelation,
				allDebtDocumentDetailCategory,allDebtDocumentTypeCategory));

		return manager;
    }

}