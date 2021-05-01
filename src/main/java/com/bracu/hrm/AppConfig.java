package com.bracu.hrm;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.bracu.hrm.configuration.DefaultAuthenticationSuccessHandler;
import com.bracu.hrm.dbconfig.DbType;
import com.bracu.hrm.dbconfig.RoutingDataSource;

@Configuration
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

public class AppConfig extends WebMvcConfigurerAdapter {

	/**
	 * Configure ViewResolvers to deliver preferred views.
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}

	/**
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript
	 * etc...
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
	 
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions("WEB-INF/tiles/tiles.xml");
		configurer.setCheckRefresh(true);
		return configurer;
	}

	/**
	 * Introduce a Tiles view resolver, this is a convenience implementation that
	 * extends URLBasedViewResolver.
	 * 
	 * @return tiles view resolver
	 */
	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		return resolver;
	}

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", true);
		mailProperties.put("mail.smtp.starttls.enable", true);
		mailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		//mailProperties.put("proxySet","true");
		//mailProperties.put("socksProxyHost","192.168.1.15");
		//mailProperties.put("socksProxyPort","8080");
		//Properties p = System.getProperties();
		//p.setProperty("proxySet","true");
		//p.setProperty("socksProxyHost","192.168.1.15");
		//p.setProperty("socksProxyPort","8080");
		// mailProperties.put("mail.properties.mail.smtp.socketFactory.port",465);
		// mailProperties.put("mail.properties.mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		// mailProperties.put("mail.properties.mail.smtp.socketFactory.fallback",false);

		// mailProperties.put("mail.properties.mail.smtp.ssl.enable",true);
		mailSender.setJavaMailProperties(mailProperties);
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		
	/*	Session session = Session.getInstance(mailProperties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("erp@bracu.ac.bd", "R@$@R@987987dasflkj");
			}
		});
		mailSender.setSession(session);*/
		mailSender.setProtocol("smtp");
//		mailSender.setUsername("bracu-gsuite@g.bracu.ac.bd");
//
//		mailSender.setPassword("BgsT8990");

		mailSender.setUsername("erp@bracu.ac.bd");

		mailSender.setPassword("R@$@R@987987dasflkj");

		return mailSender;
	}

	@Bean // Magic entry
	public DispatcherServlet dispatcherServlet() {
		DispatcherServlet ds = new DispatcherServlet();
		ds.setThrowExceptionIfNoHandlerFound(true);
		return ds;
	}

	/*@Primary
	@Bean
	public RoutingDataSource dataSource(@Qualifier("dataSourceMaster")BasicDataSource dataSourceMaster,@Qualifier("dataSourceReplica") BasicDataSource dataSourceReplica) {
		RoutingDataSource dataSource = new RoutingDataSource();
		dataSource.setDefaultTargetDataSource(dataSourceMaster);
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		targetDataSources.put(DbType.MASTER, dataSourceMaster);
		targetDataSources.put(DbType.REPLICA1, dataSourceReplica);
		dataSource.setTargetDataSources(targetDataSources);
		return dataSource;
	}
	
	//@Primary
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public BasicDataSource dataSourceMaster() {
		BasicDataSource dataSourceMaster = new BasicDataSource();
		return dataSourceMaster;
	}

	@Bean
	@ConfigurationProperties(prefix="spring.replica.datasource")
	public BasicDataSource dataSourceReplica() {
		BasicDataSource dataSourceReplica = new BasicDataSource();
		return dataSourceReplica;
	}*/
	
	
    @Bean(name ="masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name ="slaveDataSource")
    @ConfigurationProperties(prefix = "spring.replica.datasource")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Autowired
    @Primary
    @Bean(name ="dataSource")
    public DataSource routeDataSource() {
        return new RoutingDataSource() {{
            setDefaultTargetDataSource(masterDataSource());
            setTargetDataSources(new HashMap<Object, Object>() {{
                put(DbType.MASTER, masterDataSource());
                put(DbType.REPLICA1, slaveDataSource());
            }});
        }};
    }

 /*   @Bean
    @Primary
    public LazyConnectionDataSourceProxy lazyConnectionDataSourceProxy() {
        return new LazyConnectionDataSourceProxy(routeDataSource());
    }*/
    
   

}
