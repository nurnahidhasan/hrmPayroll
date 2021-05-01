<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel">
    <div class="panel-body">
        <div class="bg-default content-box  pad10A mrg8T">
            <h3 class="title-hero">
                PF Slip
            </h3>

        <div class="example-box-wrapper">
            <form class="form-horizontal bordered-row" name="item" id="itemForm" method="POST" action="${contextPath}/item/save" modelAttribute="item"  data-parsley-validate="">
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">PF Type</label>
                            <div class="col-sm-9">
                                <select class="form-control" name="salaryType" id="salaryType" required="required">
                                    <option value="2">All</option>
                                    <option value="1" selected="selected">Individual</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4" id="departmentDiv" style="display: none;">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Department</label>
                            <div class="col-sm-9">
                                <select class="form-control" name="departmentList" id="departmentList" required="required">
                                    <option value="ALL">All</option>
                                    <c:forEach var="department" items="${departmentList}">
                                        <option value="${department.id}">${department.name}</option>
                                    </c:forEach>
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
                            <label class="col-sm-3 control-label">Pin No</label>
                            <div class="col-sm-6">
                                <input type="text"  value="" name="pinNo" id="pinNo" path="pinNo" placeholder="Required Field" required class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Financial Year</label>
                            <div class="col-sm-6">
                                <select class="form-control" name="salaryYear" id="salaryYear" required="required">
                                    <c:forEach var="years" items="${yearList}">
                                        <option value="${years}">${years}</option>
                                    </c:forEach>
                                </select>
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
        $("#salaryType").change(function(){

            if($(this).val()==1){
                $('#pinNo').val('');
                $('#pinNo').attr( "disabled", false );
                $('#departmentDiv').hide();
            }else {
                $('#pinNo').val('');
                $('#pinNo').attr( "disabled", true );
                $('#departmentDiv').show();
            }

        });

    });

    function printReport(val){

        var salaryType = $('#salaryType').val();
        var departmentId = $('#departmentList').val();
        var pinNo = $('#pinNo').val();

        var salaryMonth = $('#salaryMonth').val();
        var salaryYear = $('#salaryYear').val();
        if(salaryType==1){
            if(pinNo==''){
                getSuccessMessage(3,"Please Enter Valid PIN No.",'Pay Slip')
                return false;
            }
            departmentId = 'ALL'
        }else {
            pinNo = 'ALL'


        }
        window.open("${contextPath}/payment/printAllPf/"+salaryType+"/"+pinNo+"/"+"/"+salaryYear+"/"+val+"/"+departmentId, '_blank',);
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