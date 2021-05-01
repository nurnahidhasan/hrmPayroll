<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel">
    <div class="panel-body">
        <h3 class="title-hero">
            Create Leave Type
        </h3>

        <div class="example-box-wrapper">
            <form class="form-horizontal bordered-row" name="leaveType" id="leaveTYpeFormId" method="POST"
                  data-parsley-validate="">
                <div class="row">
                    <input type="hidden" id="csr-token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="id" id="id" value="">
                    <input type="hidden" name="version" id="version" value="">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Name:</label>
                            <div class="col-sm-6">
                                <input type="text" name="name" id="leavetypeName" path="name" placeholder="Name"
                                       required class="form-control">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="bg-default content-box text-center pad20A mrg10T">
                    <input id="saveButton" style="float: left; margin-left: 5px;" type="button" value="Save"
                           onclick="saveLeaveType()" class="btn btn-lg btn-primary"/>
                    <input id="deleteButton" style="float: left; margin-left: 5px; display: none;" type="button"
                           value="Delete" onclick="deleteLeaveType()" class="btn btn-lg btn-primary"/>
                    <input style="float: left; margin-left: 5px;" type="button" value="Cancel" onclick="Server.resetForm('leaveTYpeFormId')" class="btn btn-lg btn-primary"/>
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
        var url = '${contextPath}/leavetype/list'   ;
        var formId = 'leaveTYpeFormId';
        var colModel = [
            {label: 'id', name: 'id', key: true, width: 75, hidden: true},
            {label: 'Leave Type', name: 'name', width: 800},
        ]
        Server.list(header, url, colModel, formId)


    });


    function deleteLeaveType() {
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        var formId=$('#deleteButton').closest('form').attr('id');
        var leaveTypeId = $('#id').val();
        var url = '/leavetype/delete/';
        Server.delete(header, url, leaveTypeId, formId);
    }

    function edit(leaveTypeId) {
        var action = "${contextPath}" + "/leavetype/edit/";
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        Server.edit(header, action, leaveTypeId,setDataToEdit);

    }
    function setDataToEdit(result) {
        console.log(result);
        $('#id').val(result.id);
        $('#leavetypeName').val(result.name);
        $('#version').val(result.version);
        $('#saveButton').val('Update');
    }
    function saveLeaveType() {
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        var formId=$('#saveButton').closest('form').attr('id');
        var frm = $('#leaveTYpeFormId');
        frm.validate();
        var isValid = frm.valid();
        if (!isValid) {
            return false;
        }
        var leavetype = {
            "id": $('#id').val(),
            "name": $.trim($('#leavetypeName').val()),
            "version" : $('#version').val()
        }
        var action = "";
        if ($('#id').val() > 0) {
            var action = "${contextPath}" + "/leavetype/update";
        } else {
            var action = "${contextPath}" + "/leavetype/save";
        }
        //var data=$('#leaveTYpeFormId').serialize();
        Server.save(header, leavetype, action, formId);
        Server.resetForm(formId);
    }


</script>
