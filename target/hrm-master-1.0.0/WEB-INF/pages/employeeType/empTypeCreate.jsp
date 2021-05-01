<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel">
     <div class="panel-body">
         <h3 class="title-hero">
             Create Employee Type
         </h3>

         <div class="example-box-wrapper">
             <form class="form-horizontal bordered-row" name="empType" id="empTypeFormId" method="post" >
                 <div class="row">
                     <input type="hidden" id="csr-token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                     <input type="hidden" name="id" id="id" value="">
                     <input type="hidden" name="version" id="version" value="">


                         <div class="col-md-4">
                             <div class="form-group">
                                 <label class="col-sm-4 control-label">Name</label>
                                 <div class="col-md-8">
                                     <input type="text" name="name" id="employeeTypeName" path="name" placeholder="name"
                                            required class="form-control">
                                 </div>
                             </div>
                         </div>
                     </div>

                 <div class="row">
                     <div class="col-md-4">
                         <div class="form-group">
                             <label class="col-sm-4 control-label">Note</label>
                             <div class="col-md-8">
                             <input type="text" name="note" id="note" path="name" placeholder="note"
                                    required class="form-control">
                             <%--<textarea type="text" name="note" id="note" path="name" cols="10" rows="3">--%>
                                    <%--</textarea>--%>
                             </div>
                         </div>
                     </div>
                 </div>

                 <div class="bg-default content-box text-center pad20A mrg10T">
                 <input id="saveButton" style="float: left; margin-left: 5px;" type="button" value="Save"
                        onclick="saveEmployeeType()" class="btn btn-lg btn-primary"/>
                 <input id="deleteButton" style="float: left; margin-left: 5px; display: none;" type="button"
                        value="Delete" onclick="deleteEmployeeType()" class="btn btn-lg btn-primary"/>
                 <input style="float: left; margin-left: 5px;" type="button" value="Cancel" onclick="Server.resetForm('empTypeFormId')" class="btn btn-lg btn-primary"/>
                 <div style="clear: both"></div>
                 </div>

                 <div class="bg-default content-box text-center pad20A mrg25T grid-resize">
                     <table id="jqGrid"></table>
                     <div id="jqGridPager"></div>
                 </div>
             </form>
         </div>
     </div>
</div>

<style type="text/css">
    .bg-red {
        background:  #cf4436;
        color: #FFFFFF;
    }

    .bg-green {
        background: #449d44;
        color: #FFFFFF;
    }

    .bg-azure {
        background: #d67520;
        color: #FFFFFF;
    }
</style>

<script type="text/javascript" src="../../static/custom/commons.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
       var header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        var caption = "Employee Type Information";
        var formId = "empTypeFormId";
        var url = '${contextPath}/emptype/list';
        var colModel = [
        {label: 'id', name: 'id', key: true, width: 75, hidden: true},
        {label: 'Employee Type', name: 'name', width:400},
        {label: 'Note', name: 'note' , width:400 }
         ]
        Server.list(header, url, colModel, formId, caption)
    });

    function deleteEmployeeType() {
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        var formId=$('#deleteButton').closest('form').attr('id');
        var content = 'Are you sure you want to delete the Employee Type ?';
        var caption = "Employee Type Information";
        var empTypeId = $('#id').val();
        var url = '/emptype/delete/';
        Server.delete(header, url, empTypeId, formId, content, caption);
    }

    function edit(employeeTypeId) {
        var action = "${contextPath}" + "/emptype/edit/";
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        // console.log();
        Server.edit(header, action, employeeTypeId, setDataToEdit);

    }

    function setDataToEdit(result) {
        // console.log(result);
        $('#id').val(result.id);
        $('#employeeTypeName').val(result.name);
        $('#note').val(result.note);
        $('#version').val(result.version);
        $('#saveButton').val('Update');
    }

    function saveEmployeeType() {
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };

        var formId = $('#saveButton').closest('form').attr('id');
        var caption = "Employee Type Information";
        var frm = $('#empTypeFormId');
        frm.validate();
        var isValid = frm.valid();
        if(!isValid) {
            return false;
        }

        var empType= {
            "id": $('#id').val(),
            "name": $.trim($('#employeeTypeName').val()),
            "note": $('#note').val(),
            "version": $('#version').val()
        }

        var action = "";
        if ($('#id').val() > 0) {
            var action = "${contextPath}" + "/emptype/update";
        } else {
            var action = "${contextPath}" + "/emptype/save";
        }
        // console.log(action);
        Server.save(header, empType, action, formId, caption);
        Server.resetForm(formId);
    }

</script>