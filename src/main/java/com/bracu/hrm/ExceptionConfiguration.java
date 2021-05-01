package com.bracu.hrm;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 
 * @author Ripon Rana
 */
@Configuration
@Profile("java-config")
public class ExceptionConfiguration {

	protected Logger logger;

	public ExceptionConfiguration() {
		logger = LoggerFactory.getLogger(getClass());
		logger.info("Creating ExceptionConfiguration");
	}

	/**
	 * Only invoked if the "global" profile is active.
	 * 
	 * @return The new resolver
	 */
	@Bean(name = "simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
		logger.info("Creating SimpleMappingExceptionResolver");
		SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();

		Properties mappings = new Properties();
		mappings.setProperty("DatabaseException", "databaseException");
		mappings.setProperty("InvalidCreditCardException", "creditCardError");

		r.setExceptionMappings(mappings); // None by default
		r.setExceptionAttribute("ex"); // Default is "exception"
		r.setWarnLogCategory("demo1.ExceptionLogger"); // No default


		r.setDefaultErrorView("defaultErrorPage");
		return r;
	}
}