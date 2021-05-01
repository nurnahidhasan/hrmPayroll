<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel">
    <div class="panel-body">
        <h3 class="title-hero">
            Create New Employee
        </h3>
        
        <div class="example-box-wrapper">
           <form:form method="POST" action="${contextPath}/emp/save" modelAttribute="employee" class="form-horizontal bordered-row" data-parsley-validate="">
         	<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
         
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">PIN</label>
                            <div class="col-sm-6">
                                <input type="text" placeholder="Required Employee PIN" value ="${employee.pin}" required class="form-control" name="pin">
                            	<form:errors path="pin" cssclass="error"></form:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Full Name</label>
                            <div class="col-sm-6">
                                <input type="text" value ="${employee.fullName}" name="fullName" data-parsley-minlength="6" placeholder="At least 6 characters" required class="form-control">
                           <form:errors path="fullName" cssclass="error"></form:errors>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Date of Birth</label>
                            <div class="col-sm-6">
                                <div class="input-prepend input-group">
                                    <span class="add-on input-group-addon">
                                        <i class="glyph-icon icon-calendar"></i>
                                    </span>
                                    <input type="text" name="dateOfBirith" value ="31-12-2017" class="bootstrap-datepicker form-control" required data-date-format="dd-MM-yyyy">
                                	<form:errors path="dateOfBirith" cssclass="error"></form:errors>
                                </div>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Email</label>
                            <div class="col-sm-6">
                                <input type="text" name ="email" data-parsley-type="email" value ="${employee.email}" placeholder="Email address" required class="form-control">
                            	<form:errors path="email" cssclass="error"></form:errors>
                            </div>
                        </div>
                        
                          <div class="form-group">
                    <label class="col-sm-3 control-label">Gender</label>
                    <div class="col-sm-6">
                        <select name="gender.id" class="custom-select">
 							<option value="">Please Select</option>
						    <c:forEach var="gender" items="${genderList}">
						    <option value="${gender.id}" ${gender.id == employee.gender.id ? 'selected="selected"' : ''}>${gender.column1}</option>
						    </c:forEach>

                        <!-- 	<option>Please Select</option>
                          	<option>Male</option>
                            <option>Female</option>
                            <option>Others</option> -->
                        </select>
                    </div>
                </div>
                
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Father's Name</label>
                            <div class="col-sm-6">
                                <input type="text" placeholder="Required Field" value ="${employee.fatherName}" required class="form-control" name="fatherName">
                           <form:errors path="fatherName" cssclass="error"></form:errors>
                            </div>
                        </div>
                       <!--  <div class="form-group">
                            <label class="col-sm-3 control-label">Max-length</label>
                            <div class="col-sm-6">
                                <input type="text" data-parsley-maxlength="6" placeholder="At most 6 characters" required class="form-control">
                            </div>
                        </div> -->
                        <!-- <div class="form-group">
                            <label class="col-sm-3 control-label">Range length</label>
                            <div class="col-sm-6">
                                <input type="text" data-parsley-range="[5,10]" placeholder="Between 5 and 10 characters" required class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">RegExp</label>
                            <div class="col-sm-6">
                                <input type="text" data-parsley-pattern="#[A-Fa-f0-9]{6}" placeholder="Hexadecimal Color Code" required class="form-control">
                            </div>
                        </div>
 -->
                        
                    </div>
                    
                    
                    <div class="col-md-6">
                    
                    <div class="form-group">
                            <label class="col-sm-3 control-label">Mother's Name</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="motherName" value ="${employee.motherName}">
                            </div>
                        </div>
                        
                          <div class="form-group">
                            <label class="col-sm-3 control-label">Spouse's Name</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="spouseName" value ="${employee.spouseName}">
                            </div>
                        </div>
                       <!--  <div class="form-group">
                            <label class="col-sm-3 control-label">URL</label>
                            <div class="col-sm-6">
                                <input type="text" data-parsley-type="url" placeholder="URL address" required class="form-control">
                            </div>
                        </div> -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">National ID</label>
                            <div class="col-sm-6">
                                <input type="text" data-parsley-type="digits" placeholder="Enter your nid number" name="nid" value ="${employee.nid}" required class="form-control">
                            </div>
                        </div>
                       <!--  <div class="form-group">
                            <label class="col-sm-3 control-label">Alphanum</label>
                            <div class="col-sm-6">
                                <input type="text" data-parsley-type="alphanum" placeholder="Alphanumeric only" required class="form-control">
                            </div>
                        </div> -->
                        
                        <div class="form-group">
                    <label class="col-sm-3 control-label">Nationality</label>
                    <div class="col-sm-6">
                        <select name="nationality.id" class="custom-select">
                        	<option>Please Select</option>
                        	 <c:forEach var="nationality" items="${nationalityList}">
						    <option value="${nationality.id}" ${nationality.id == employee.nationality.id ? 'selected="selected"' : ''}>${nationality.column1}</option>
						    </c:forEach>
                          <!-- 	<option>Bangladesh</option>
                            <option>USA</option>
                            <option>UK</option> -->
                        </select>
                    </div>
                </div>
                
                
                 
                        <div class="form-group">
                    <label class="col-sm-3 control-label">Marrital Status</label>
                    <div class="col-sm-6">
                        <select name="marritalStatus.id" class="custom-select">
                        	<option>Please Select</option>
                           <c:forEach var="marritalStatus" items="${marritalStatusList}">
						    <option value="${marritalStatus.id}" ${marritalStatus.id == employee.marritalStatus.id ? 'selected="selected"' : ''}>${marritalStatus.column1}</option>
						    </c:forEach>
                        </select>
                    </div>
                </div>
                       <!--  <div class="form-group">
                            <label class="col-sm-3 control-label">Password</label>
                            <div class="col-sm-6">
                                <input type="text" id="ps1" required class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Repeat Password</label>
                            <div class="col-sm-6">
                                <input type="text" data-parsley-equalto="#ps1" required class="form-control">
                            </div>
                        </div> -->
                        <!-- <div class="form-group">
                            <label class="col-sm-3 control-label">Checkbox</label>
                            <div class="col-sm-6">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" required name="terms">
                                        Accept Terms &amp; Conditions
                                    </label>
                                </div>
                            </div>
                        </div> -->
                    </div>
                </div>
                <div class="bg-default content-box text-center pad20A mrg25T">
                    <button type="submit" class="btn btn-lg btn-primary">Save Employee</button>
                </div>
             </form:form>
        </div>
    </div>
</div>
                    