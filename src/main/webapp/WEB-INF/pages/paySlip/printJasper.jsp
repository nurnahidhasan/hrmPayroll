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
                                <label class="col-sm-3 control-label">Input Your Text</label>
                                <div class="col-sm-9">
                                    <textarea id="" cols="70" rows="5" id="employeeId" name="employeeId" maxlength="200"></textarea>
                                </div>
                            </div>
                        </div>



                    </div>




                    <input style="float: left; margin-left: 5px;" type="button" value="Print" onclick="printReport('print')" class="btn btn-lg btn-primary"/>
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

        var employeeId = $('textarea:input[name=employeeId]').val();
        window.open("${contextPath}/payment/printEmpIdJasper/"+employeeId, '_blank');
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