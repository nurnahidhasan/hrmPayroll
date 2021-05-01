<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel">
    <div class="panel-body">
        <h3 class="title-hero">
            Create Human Resource Year
        </h3>

        <div class="example-box-wrapper">
            <form class="form-horizontal bordered-row" name="leaveType" id="hrYearId" method="POST"
                  data-parsley-validate="">
                <div class="row">
                    <input type="hidden" id="csr-token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="id" id="id" value="">
                    <input type="hidden" name="version" id="version" value="">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">From Date</label>
                            <div class="col-sm-6">
                                <input type="text" name="DateFrom" id="dateFrom"  class="bootstrap-datepicker form-control tempDateFrom" value="" data-date-format="mm-dd-yy">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">To Date</label>
                            <div class="col-sm-6">
                                <input type="text" name="dateTo" id="dateTo"  class="bootstrap-datepicker form-control tempDateFrom " value="" data-date-format="mm-dd-yy">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="bg-default content-box text-center pad20A mrg10T">
                    <input id="saveButton" style="float: left; margin-left: 5px;" type="button" value="Open"
                           onclick="saveHrYear()" class="btn btn-lg btn-primary"/>
                    <%--<input id="deleteButton" style="float: left; margin-left: 5px; display: none;" type="button"
                           value="Delete" onclick="deleteHrYear()" class="btn btn-lg btn-primary"/>--%>
                    <input style="float: left; margin-left: 5px;" type="button" value="Cancel" onclick="Server.resetForm('hrYearId')" class="btn btn-lg btn-primary"/>
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
<script type="text/javascript">
    $(document).ready(function () {
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        var url = '${contextPath}/hryear/list';
        var formId = 'hrYearId';
        var colModel = [
            {label: 'id', name: 'id', key: true, width: 75, hidden: true},
            {label: 'Date Form', name: 'dateFrom', width: 250},
            {label: 'Date To', name: 'dateTo', width: 250},
            {label: 'Status', name: 'isOpen', width: 250, editable: true, formatter:statusFomatter},
        ]
        function statusFomatter (cellvalue, options, rowObject)
        {
            if (cellvalue == true)
                return "Open";
            else if (cellvalue == false)
                return "Closed";
        }
        Server.list(header, url, colModel, formId)
        $(function() { "use strict";
            $('.bootstrap-datepicker').bsdatepicker({
                format: 'mm-dd-yyyy'
            });
        });


    });

    function saveHrYear() {
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        var formId=$('#saveButton').closest('form').attr('id');
        var frm = $('#hrYearId');
        frm.validate();
        var isValid = frm.valid();
        if (!isValid) {
            return false;
        }
        var dateForm=new Date($('#dateFrom').val());
        var dateTo=new Date($('#dateTo').val());
        var hrYear = {
            "id": $('#id').val(),
            "dateFrom":dateForm ,
            "dateTo":dateTo ,
            "version" : $('#version').val()
        }
        var action = "";
        if ($('#id').val() > 0) {
            var action = "${contextPath}" + "/hryear/update";
        } else {
            var action = "${contextPath}" + "/hryear/save";
        }
        Server.save(header, hrYear, action, formId);
        Server.resetForm(formId);

    }


</script>
