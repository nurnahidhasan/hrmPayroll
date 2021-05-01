<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel">
    <div class="panel-body">
        <div class="bg-default content-box  pad10A mrg8T">
            <h3 class="title-hero">
                Tax Certificate
            </h3>

        <div class="example-box-wrapper">
            <form class="form-horizontal bordered-row" name="item" id="itemForm" method="POST" action="${contextPath}/item/save" modelAttribute="item"  data-parsley-validate="">
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Party Name</label>
                            <div class="col-sm-9">
                                <select class="form-control" name="partyName" id="partyName" required="required">
                                    <option value="">--Select--</option>
                                    <c:forEach var="department" items="${thirdPartyList}">
                                        <option value="${department.id}">${department.partyName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4" id="departmentDiv">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Section</label>
                            <div class="col-sm-9">
                                <select class="form-control" name="section" id="section" required="required">
                                    <option value="ALL">All</option>

                                </select>
                            </div>
                        </div>
                    </div>


                </div>

                <div class="row">
                    <input type="hidden" id="csr-token"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden"   name="id" id="id" value="">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">From Date</label>
                            <div class="col-sm-6">
                                <input type="text"  value="" name="fromDate" id="fromDate" path="fromDate" placeholder="Required Field" required class=" bootstrap-datepicker form-control tempDateFrom"
                                       data-date-format="MM-dd-yy">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">To Date</label>
                            <div class="col-sm-6">
                                <input type="text"  value="" name="toDate" id="toDate" path="toDate" placeholder="Required Field" required class=" bootstrap-datepicker form-control tempDateFrom"
                                       data-date-format="MM-dd-yy">
                            </div>
                        </div>
                    </div>

                </div>


                    <input style="float: left; margin-left: 5px;" type="button" value="Print" onclick="printReport('print')" class="btn btn-lg btn-primary"/>
                    <input style="float: left; margin-left: 5px;" type="button" value="Email" onclick="printReport('email')" class="btn btn-lg btn-primary"/>
                    <%--<input style="float: left; margin-left: 5px;" type="button" value="Reset" onclick="resetForm()" class="btn btn-lg btn-primary"/>--%>
                    <div style="clear: both"></div>
            </form>
        </div>
        </div>
    </div>

</div>


<style type="text/css">
    .bg-red{
        background: #cf4436;
        color: #FFFFFF;}
    .bg-green{
        background: #449d44;
        color: #FFFFFF;}
    .bg-azure{
        background: #d67520;
        color: #FFFFFF;}

</style>


<script type="text/javascript">
    $( document ).ready(function() {
        $(function() { "use strict";
            $('.bootstrap-datepicker').bsdatepicker({
                format: 'mm-dd-yyyy',
            });
        });

    });

    function printReport(val){

        var partyId = $('#partyName').val();
        var fromDate = $('#fromDate').val();
        var toDate = $('#toDate').val();

        var sectionName = $('#section').text();
        var sectionCode = $('#section').val();
//        if(salaryType==1){
//            if(pinNo==''){
//                getSuccessMessage(3,"Please Enter Valid PIN No.",'Pay Slip')
//                return false;
//            }
//            departmentId = 'ALL'
//        }else {
//            pinNo = 'ALL'
//
//
//        }
        window.open("${contextPath}/taxbankpayment/printTaxCertificate/"+partyId+"/"+fromDate+"/"+toDate+"/"+'sectionName'+"/"+'sectionCode', '_blank',);
    }

    function getSuccessMessage(type,msg,header ){
        var color = '';
        if(type==1) {
            var color = 'bg-green';
        }else if(type==2){
            var color = 'bg-red';
        }else if(type==3){
            var color = 'bg-azure';
        }
        $.jGrowl(""+ msg +"", {
            header: header,
            sticky: false,
            position: 'bottom-right',
            theme: color
        });
    }
</script>