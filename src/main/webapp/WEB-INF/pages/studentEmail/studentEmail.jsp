<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel">
    <div class="panel-body">
        <div class="bg-default content-box  pad10A mrg8T">
            <h3 class="title-hero">
                Student G-Suite
            </h3>

        <div class="example-box-wrapper">
            <form class="form-horizontal bordered-row" name="item" id="itemForm" method="POST" action="${contextPath}/item/save" modelAttribute="item"  data-parsley-validate="">
            </br>
            </br>
                    <select id="offset" style="width: 250px;">
                            <option value="0">0</option>
                            <option value="500">500</option>
                            <option value="1000">1000</option>
                            <option value="1500">1500</option>
                            <option value="2000">2000</option>
                            <option value="2500">2500</option>
                            <option value="3000">3000</option>
                            <option value="3500">3500</option>
                            <option value="4000">4000</option>
                            <option value="4500">4500</option>
                            <option value="5000">5000</option>
                            <option value="5500">5500</option>
                            <option value="6000">6000</option>
                            <option value="6500">6500</option>
                            <option value="7000">7000</option>
                            <option value="7500">7500</option>
                            <option value="8000">8000</option>
                            <option value="8500">8500</option>
                            <option value="9000">9000</option>
                            <option value="9500">9500</option>
                            <option value="10000">10000</option>
                            <option value="10500">10500</option>
                    </select>
                </br>
                </br>
                    <%--<input style="float: left; margin-left: 5px;" type="button" value="Print" onclick="printReport('print')" class="btn btn-lg btn-primary"/>--%>
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
        window.open("${contextPath}/payment/emailAll/"+$('#offset').val(), '_blank',);
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