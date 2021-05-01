package com.bracu.hrm.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class ExceptionResolver   extends
SimpleMappingExceptionResolver {

protected boolean enabled = false;

public ExceptionResolver(boolean enabled) {
this.enabled = enabled;
}

/**
* Enabled or not?
* 
* @return Is enabled?
*/
public boolean isEnabled() {
return enabled;
}

/**
* Allow this resolver to be turned on and off whilst the application is
* running.
* 
* @param enabled
*            Set to enabled?
*/
public void setEnabled(boolean enabled) {
this.enabled = enabled;
}

/**
* Resolver only handles exceptions if enabled. Overrides method inherited
* from {@link AbstractHandlerExceptionResolver}
*/
@Override
protected boolean shouldApplyTo(HttpServletRequest request, Object handler) {
return enabled && super.shouldApplyTo(request, handler);
}

}