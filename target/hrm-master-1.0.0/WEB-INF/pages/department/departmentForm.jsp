<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel">
    <div class="panel-body">
        <h3 class="title-hero">
            Create Department
        </h3>

        <div class="example-box-wrapper">
            <form class="form-horizontal bordered-row" name="depaqrtment" id="departmentFormId" method="POST"
                      data-parsley-validate="">

                <div class="row">
                    <input type="hidden" id="csr-token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="id" id="id" value="">
                    <input type="hidden" name="version" id="version" value="">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Name:</label>
                            <div class="col-sm-6">
                                <input type="text" name="name" id="departmentName" path="name" placeholder="Name"
                                       required class="form-control">
                            </div>
                        </div>
                    </div>
                </div>
                    <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Short Name:</label>
                            <div class="col-sm-6">
                                <input type="text" name="sName" id="shortName" path="name" placeholder="Name"
                                       required class="form-control">
                            </div>
                        </div>
                    </div>
                </div>

                    <div class="bg-default content-box text-center pad20A mrg10T">
                        <input id="saveButton" style="float: left; margin-left: 5px;" type="button" value="Save"
                               onclick="saveDepartment()" class="btn btn-lg btn-primary"/>
                        <input id="deleteButton" style="float: left; margin-left: 5px; display: none;" type="button"
                               value="Delete" onclick="deleteDepartment()" class="btn btn-lg btn-primary"/>
                        <input style="float: left; margin-left: 5px;" type="button" value="Cancel" onclick="Server.resetForm('departmentFormId')" class="btn btn-lg btn-primary"/>
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
        background: #cf4436;
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
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        var url = '${contextPath}/dept/list';
        var formId = 'departmentFormId';
        var caption = "Department Information"
        var colModel = [
            {label: 'id', name: 'id', key: true, width: 200, hidden: true},
            {label: 'Name', name: 'name', width: 400},
            {label: 'Short Name', name: 'shortName', width: 400},
            // {label: 'Department', name: 'department', width: 200},
        ]

        // console.log(url);
        // console.log(formId);
        // console.log(colModel);
        Server.list(header, url, colModel, formId, caption)


    });


    function deleteDepartment() {
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        var formId=$('#deleteButton').closest('form').attr('id');
        var content = 'Are you sure you want to delete the Department?';
        var caption = "Department Information";
        var departmentId = $('#id').val();
        var url = '/dept/delete/';
        Server.delete(header, url, departmentId, formId, content, caption);
    }

    function edit(departmentId) {
        var action = "${contextPath}" + "/dept/edit/";
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        // console.log(departmentId);
        Server.edit(header, action, departmentId, setDataToEdit);

    }

    function setDataToEdit(result) {
        console.log(result);
        $('#id').val(result.id);
        $('#departmentName').val(result.name);
        $('#shortName').val(result.shortName);
        $('#version').val(result.version);
        $('#saveButton').val('Update');
    }
    function saveDepartment() {
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        var formId=$('#saveButton').closest('form').attr('id');
        var caption = "Department Information";

        var frm = $('#departmentFormId');
        frm.validate();
        var isValid = frm.valid();
        if (!isValid) {
            return false;
        }
        var department = {
            "id": $('#id').val(),
            "name": $.trim($('#departmentName').val()),
            "shortName": $.trim($('#shortName').val()),
            "version" : $('#version').val()
        }
        console.log(department);

        var action = "";
        if ($('#id').val() > 0) {
            var action = "${contextPath}" + "/dept/update";
        } else {
            var action = "${contextPath}" + "/dept/save";
        }
        //var data=$('#departmentFormId').serialize();
        // console.log(header);
        // console.log(department);
        // console.log(action);
        // console.log(formId);

        Server.save(header, department, action, formId, caption);
        Server.resetForm(formId);
    }


</script>