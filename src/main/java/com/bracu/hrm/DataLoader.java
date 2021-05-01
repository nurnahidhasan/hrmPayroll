package com.bracu.hrm;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.bracu.hrm.dao.DesignationDao;
import com.bracu.hrm.dao.EntityTypeDao;
import com.bracu.hrm.dao.SetupEntityDao;
import com.bracu.hrm.model.Role;
import com.bracu.hrm.model.User;
import com.bracu.hrm.model.org.Company;
import com.bracu.hrm.model.org.Designation;
import com.bracu.hrm.model.settings.EntityType;
import com.bracu.hrm.model.settings.SetupEntity;
import com.bracu.hrm.service.CompanyService;
import com.bracu.hrm.service.RoleService;
import com.bracu.hrm.service.UserService;

@Component
public class DataLoader implements ApplicationRunner {

    //private UserRepository userRepository;
	
	//@Autowired
	private DesignationDao designationDao;
	
	
	@Autowired
	private EntityTypeDao entityTypeDao;

	@Autowired
	SetupEntityDao setupEntityDao;
	
	@Autowired
	 private CompanyService companyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
    @Autowired
    public DataLoader(DesignationDao designationDao) {
        this.designationDao = designationDao;
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) {
    	
    	System.out.println("Please have patient while loading default data...");
       // userRepository.save(new User());
    
    	Company company;
    	
   	if(companyService.findAll().size() <=0) {
   		 company = new Company();
    		company.setAddress("66 Mohakhali\n" + 
    				"Dhaka 1212\n" + 
    				"Bangladesh");
    		company.setEmail("info@bracu.ac.bd");
    		company.setFax("N/A");
    		company.setName("BRAC University");
    		company.setPhone1("N/A");
    		company.setPhone2("N/A");
    		company.setShortName("BU");
    		company.setVersion(0);
    		company.setWebUrl("www.bracu.ac.bd");
    		companyService.save(company);
   		
    	}else {
   	
   	
    company = companyService.findByName("BRAC University");
    	}
   	

   	if(roleService.findAll().size() <= 0) {
   		Role roleAdmin = new Role();
   		roleAdmin.setName("ROLE_ADMIN");
   		roleService.save(roleAdmin);
   		
   		Role roleUser = new Role();
   		roleUser.setName("ROLE_USER");
   		roleService.save(roleUser);
   	}
   	
   	if(userService.findAll().size() <=0) {
   		User user = new User();
   		user.setCompany(company);
   		user.setEmail("rana771@gmail.com");
   		user.setFullName("Ripon Rana");
   		user.setPassword("12345678");
   		user.setPasswordConfirm("12345678");
   		user.setUsername("sadmin");
   		userService.save(user);
   	}
   	
    	if(designationDao.findAll().size() <= 0){
    		Designation designationManager = new Designation();
    		designationManager.setVersion(0);
    		designationManager.setName("Manager");
    		designationManager.setShortName("Manager");
    		designationManager.setFunctionalName("Manager");
    		designationManager.setLevel(5);    		
    		designationDao.save(designationManager);
    		
    		
    		Designation designationVc = new Designation();
    		designationVc.setVersion(0);
    		designationVc.setName("Vice chancellor");
    		designationVc.setShortName("VC");
    		designationVc.setFunctionalName("Vice chancellor");
    		designationVc.setLevel(1);    		
    		designationDao.save(designationVc);
    		
    		Designation designationFaculty = new Designation();
    		designationFaculty.setVersion(0);
    		designationFaculty.setName("Faculty");
    		designationFaculty.setShortName("Faculty");
    		designationFaculty.setFunctionalName("Faculty");
    		designationFaculty.setLevel(3);    		
    		designationDao.save(designationFaculty);
    	}
    	
    	if(entityTypeDao.findAll().size() <= 0){
    		EntityType entityTypeGender = new EntityType();
    		entityTypeGender.setVersion(0);
    		entityTypeGender.setName("Gender");
    		entityTypeGender.setColumn1("Name");
    		entityTypeDao.save(entityTypeGender);
    		
    		SetupEntity setupEntityMale =new SetupEntity();
    		setupEntityMale.setColumn1("Male");
    		setupEntityMale.setEntityType(entityTypeGender);
    		setupEntityDao.save(setupEntityMale);
    		
    		SetupEntity setupEntityFemale =new SetupEntity();
    		setupEntityFemale.setColumn1("Female");
    		setupEntityFemale.setEntityType(entityTypeGender);
    		setupEntityDao.save(setupEntityFemale);
    		
    		EntityType entityTypeMarritalStatus = new EntityType();
    		entityTypeMarritalStatus.setVersion(0);
    		entityTypeMarritalStatus.setName("Marrital Status");
    		entityTypeMarritalStatus.setColumn1("Status Name");
    		entityTypeDao.save(entityTypeMarritalStatus);
    		
    		SetupEntity setupEntitySingle =new SetupEntity();
    		setupEntitySingle.setColumn1("Single");
    		setupEntitySingle.setEntityType(entityTypeMarritalStatus);
    		setupEntityDao.save(setupEntitySingle);
    		SetupEntity setupEntityMarried =new SetupEntity();
    		setupEntityMarried.setColumn1("Married");
    		setupEntityMarried.setEntityType(entityTypeMarritalStatus);
    		setupEntityDao.save(setupEntityMarried);
    		
    		
    		EntityType entityTypeCountry = new EntityType();
    		entityTypeCountry.setVersion(0);
    		entityTypeCountry.setName("Country");
    		entityTypeCountry.setColumn1("Name");
    		entityTypeDao.save(entityTypeCountry);
    		
    		SetupEntity setupEntityBangladesh =new SetupEntity();
    		setupEntityBangladesh.setColumn1("Bangladesh");
    		setupEntityBangladesh.setEntityType(entityTypeCountry);
    		setupEntityDao.save(setupEntityBangladesh);
    		SetupEntity setupEntityJapan =new SetupEntity();
    		setupEntityJapan.setColumn1("Japan");
    		setupEntityJapan.setEntityType(entityTypeCountry);
    		setupEntityDao.save(setupEntityJapan);
    		
    		EntityType entityTypeNationallity = new EntityType();
    		entityTypeNationallity.setVersion(0);
    		entityTypeNationallity.setName("Nationality");
    		entityTypeNationallity.setColumn1("Name");
    		entityTypeDao.save(entityTypeNationallity);
    		SetupEntity setupEntityBangladeshi =new SetupEntity();
    		setupEntityBangladeshi.setColumn1("Bangladeshi");
    		setupEntityBangladeshi.setEntityType(entityTypeNationallity);
    		setupEntityDao.save(setupEntityBangladeshi);
    		SetupEntity setupEntityJapani =new SetupEntity();
    		setupEntityJapani.setColumn1("Japani");
    		setupEntityJapani.setEntityType(entityTypeNationallity);
    		setupEntityDao.save(setupEntityJapani);
    		
    		EntityType entityTypeReligion = new EntityType();
    		entityTypeReligion.setVersion(0);
    		entityTypeReligion.setName("Religion");
    		entityTypeReligion.setColumn1("Name");
    		entityTypeDao.save(entityTypeReligion);
    		
    		
    		SetupEntity setupEntityIslam =new SetupEntity();
    		setupEntityIslam.setColumn1("Islam");
    		setupEntityIslam.setEntityType(entityTypeReligion);
    		setupEntityDao.save(setupEntityIslam);
    		
    		SetupEntity setupEntityChristian =new SetupEntity();
    		setupEntityChristian.setColumn1("Cristian");
    		setupEntityChristian.setEntityType(entityTypeReligion);
    		setupEntityDao.save(setupEntityChristian);
    	}
    	

    	
    
    }

}