package com.bracu.hrm.dbconfig;

import org.aspectj.lang.ProceedingJoinPoint;  
import org.aspectj.lang.annotation.Around;  
import org.aspectj.lang.annotation.Aspect;  
import org.aspectj.lang.annotation.Pointcut;  
import org.springframework.beans.factory.annotation.Value;  
import org.springframework.core.Ordered;  
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReadOnlyConnectionInterceptor implements Ordered {

    private int order;

    @Value("20")
    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
       return order;
    }

    @Pointcut(value="execution(public * *(..))")
    public void anyPublicMethod() { }

    @Around("@annotation(readOnlyConnection)")
    public Object proceed(ProceedingJoinPoint pjp, ReadOnlyConnection readOnlyConnection) throws Throwable {
        try {
            DbContextHolder.setDbType(DbType.REPLICA1);
            Object result = pjp.proceed();
            DbContextHolder.clearDbType();
            return result;
        } finally {
            // restore state
            DbContextHolder.clearDbType();
        }
    }
}
