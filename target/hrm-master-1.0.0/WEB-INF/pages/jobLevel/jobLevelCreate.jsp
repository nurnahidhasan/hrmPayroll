<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel">
     <div class="panel-body">
         <h3 class="title-hero">
             Job Level Type
         </h3>

         <div class="example-box-wrapper">
             <form class="form-horizontal bordered-row" name="jobLevel" id="jobLevelFormId" method="post" >
                 <div class="row">
                     <input type="hidden" id="csr-token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                     <input type="hidden" name="id" id="id" value="">
                     <input type="hidden" name="version" id="version" value="">


                         <div class="col-md-4">
                             <div class="form-group">
                                 <label class="col-sm-4 control-label">Level</label>
                                 <div class="col-md-8">
                                     <input type="text" name="name" id="jobLevelName" path="name" placeholder="name"
                                            required class="form-control">
                                 </div>
                             </div>
                         </div>
                     </div>

                 <div class="row">
                     <div class="col-md-4">
                         <div class="form-group">
                             <label class="col-sm-4 control-label">Int Level</label>
                             <div class="col-md-8">
                             <input type="text" name="intLevel" id="intLevel" path="intLevel" placeholder="Int Level"
                                    required class="form-control">
                             </div>
                         </div>
                     </div>
                 </div>

                 <div class="row">
                     <div class="col-md-4">
                         <div class="form-group">
                             <label class="col-sm-4 control-label">Note</label>
                             <div class="col-md-8" style="background: #0cc285">
                                 <%--<input type="text" name="note" id="note" path="name" placeholder="note"--%>
                                        <%--required class="form-control">--%>
                                 <textarea type="text" name="note" id="note" path="name" cols="20" rows="3">
                                 </textarea>
                             </div>
                         </div>
                     </div>
                 </div>

                 <div class="bg-default content-box text-center pad20A mrg10T">
                 <input id="saveButton" style="float: left; margin-left: 5px;" type="button" value="Save"
                        onclick="saveJobLevelType()" class="btn btn-lg btn-primary"/>
                 <input id="deleteButton" style="float: left; margin-left: 5px; display: none;" type="button"
                        value="Delete" onclick="deleteJobLevelType()" class="btn btn-lg btn-primary"/>
                 <input style="float: left; margin-left: 5px;" type="button" value="Cancel" onclick="Server.resetForm('jobLevelFormId')" class="btn btn-lg btn-primary"/>
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
        var caption = "Job Level Information";
        var formId = "jobLevelFormId";
        var url = '${contextPath}/joblevel/list';
        var colModel = [
        {label: 'id', name: 'id', key: true, width: 75, hidden: true},
        {label: 'Level', name: 'level', width:200},
        {label: 'Int Level', name: 'intLevel', width:200},
        {label: 'Note', name: 'note' , width:400 }
         ]
        Server.list(header, url, colModel, formId, caption)
    });

    function deleteJobLevelType() {
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        var formId=$('#deleteButton').closest('form').attr('id');
        var content = 'Are you sure you want to delete the Job Level ?';
        var caption = "Job Level Information";
        var JobLevelId = $('#id').val();
        var url = '/joblevel/delete/';
        Server.delete(header, url, JobLevelId, formId, content, caption);
    }

    function edit(jobLevelId) {
        var action = "${contextPath}" + "/joblevel/edit/";
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };

        Server.edit(header, action, jobLevelId, setDataToEdit);

    }

    function setDataToEdit(result) {
        // console.log(result);
        $('#id').val(result.id);
        $('#jobLevelName').val(result.level);
        $('#note').val(result.note);
        $('#intLevel').val(result.intLevel);
        $('#version').val(result.version);
        $('#saveButton').val('Update');
    }

    function saveJobLevelType() {
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };

        var formId = $('#saveButton').closest('form').attr('id');
        var caption = "Job Level Information";
        var frm = $('#jobLevelFormId');
        frm.validate();
        var isValid = frm.valid();
        if(!isValid) {
            return false;
        }

        var jobLevel= {
            "id": $('#id').val(),
            "level": $.trim($('#jobLevelName').val()),
            "intLevel": $.trim($('#intLevel').val()),
            "note": $('#note').val(),
            "version": $('#version').val()
        }

        var action = "";
        if ($('#id').val() > 0) {
            var action = "${contextPath}" + "/joblevel/update";
        } else {
            var action = "${contextPath}" + "/joblevel/save";
        }
        alert(action)
        console.log(jobLevel);
        Server.save(header, jobLevel, action, formId, caption);
        Server.resetForm(formId);
    }

</script>