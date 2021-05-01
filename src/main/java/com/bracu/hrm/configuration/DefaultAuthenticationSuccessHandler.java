package com.bracu.hrm.configuration;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bracu.hrm.cache.CacheService;
import com.bracu.hrm.model.User;
import com.bracu.hrm.model.org.Company;
import com.bracu.hrm.service.CompanyService;
import com.bracu.hrm.service.UserService;

@Component
public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Autowired
	private CacheService cacheService;
	@Autowired
	private UserService userService;
	@Autowired
	private CompanyService companyService;
	 @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, 
	      HttpServletResponse response, Authentication authentication)
	      throws IOException {
		 	//System.out.print(authentication.getName());
		 	User user = userService.findByUsername(authentication.getName());
		 	cacheService.setUserCase(user);
		 	//Company company =companyService.findById(user.getCompany().getId());
		 	cacheService.setCompanyCase(user.getCompany());
	        handle(request, response, authentication);
	        clearAuthenticationAttributes(request);
	    }
	 
	 protected void handle(HttpServletRequest request, 
		      HttpServletResponse response, Authentication authentication)
		      throws IOException {
		  
		        String targetUrl = determineTargetUrl(authentication);
		 
		        if (response.isCommitted()) {
		            logger.debug(
		              "Response has already been committed. Unable to redirect to "
		              + targetUrl);
		            return;
		        }
		 
		        redirectStrategy.sendRedirect(request, response, targetUrl);
		    }
	 protected String determineTargetUrl(Authentication authentication) {
	        boolean isUser = false;
	        boolean isAdmin = false;
	        Collection<? extends GrantedAuthority> authorities
	         = authentication.getAuthorities();
	        for (GrantedAuthority grantedAuthority : authorities) {
	            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
	                isUser = true;
	                break;
	            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
	                isAdmin = true;
	                break;
	            }
	        }
	 
	      /*  if (isUser) {
	            return "/dashboard";
	        } else if (isAdmin) {
	            return "/home";
	        } else {
	            return "/";
	        }*/
	        return "/";
	    }
	 
	 
	 protected void clearAuthenticationAttributes(HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        if (session == null) {
	            return;
	        }
	        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	    }
	 
	    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
	        this.redirectStrategy = redirectStrategy;
	    }
	    protected RedirectStrategy getRedirectStrategy() {
	        return redirectStrategy;
	    }
	 
/*	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication authentication)
			throws IOException, ServletException {
		
		
		Collectionextends GrantedAuthority> authorities = authentication.getAuthorities();
		authorities.forEach(authority -> {
			if(authority.getAuthority().equals("ROLE_USER")) {
				try {
					redirectStrategy.sendRedirect(arg0, arg1, "/user");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(authority.getAuthority().equals("ROLE_ADMIN")) {
				try {
					redirectStrategy.sendRedirect(arg0, arg1, "/admin");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
	            throw new IllegalStateException();
	        }
		});
		
	}*/
	
	
	
	
	
 
}
