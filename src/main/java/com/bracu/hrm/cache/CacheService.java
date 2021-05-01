package com.bracu.hrm.cache;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.bracu.hrm.model.User;
import com.bracu.hrm.model.org.Company;

@Component
public final class CacheService {
	
	private User user;
	private Company company;
     
     
   // @CachePut
    public void setUserCase(User user){
      this.user = user;
    }
     
   // @Cacheable
    public User getUser(){
        return user;
    }
     
    
  // @CachePut
   public void setCompanyCase(Company company){
     this.company = company;
   }
    
   //@Cacheable
   public Company getCompnay(){
       return company;
   }
   
}
