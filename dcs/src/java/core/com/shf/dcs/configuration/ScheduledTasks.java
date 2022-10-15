package com.shf.dcs.configuration;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledTasks {
	
	private static Logger logger = Logger.getLogger(ScheduledTasks.class);

    // 1 phút
    @Scheduled(cron = "0 40 6,12 * * *")
    public void IaaSStatusRefresh(){
    	try {
    		logger.info("Clear ALL CACHE");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
    }
}