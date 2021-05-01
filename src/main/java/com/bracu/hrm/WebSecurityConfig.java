package com.bracu.hrm;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bracu.hrm.configuration.DefaultAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    @Autowired
	private DataSource dataSource;
	
    @Autowired
	private DefaultAuthenticationSuccessHandler successHandler;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

/*	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder());
	}*/

	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      //  http.csrf().disable();
    	  http
          .authorizeRequests()
          	.antMatchers("/list").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
          	.antMatchers("/employeeList").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
          	.antMatchers("/edit-user-*").access("hasRole('ADMIN') or hasRole('DBA')")
              .antMatchers("/static/**", "/registration").permitAll()
              .anyRequest().authenticated()
              .and()
          .formLogin().successHandler(successHandler)
              .loginPage("/login")
              .permitAll()
              .and()
          .logout()
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/").and().exceptionHandling()
			.accessDeniedPage("/403");
    	  
  		http.exceptionHandling().accessDeniedPage("/403");
  		//http.exceptionHandling().
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
    
    
}