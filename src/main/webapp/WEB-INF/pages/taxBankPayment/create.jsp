<%--
  Created by IntelliJ IDEA.
  User: rana
  Date: 3/11/18
  Time: 3:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel">
    <div class="panel-body">
        <input type="hidden" id="csr-token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <h3 class="title-hero">
            Employee Children
        </h3>

        <div class="example-box-wrapper col-md-12">
            <form class="form-horizontal bordered-row" modelAttribute="taxBankPayment" name="taxBankPayment"
                  id="taxBankPaymentId" method="POST"
                  data-parsley-validate="">
                <div class="row">
                    <input type="hidden" name="id" id="taxId" >
                    <input type="hidden" name="version" id="version" >
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Challan No:</label>
                            <div class="col-sm-8">
                                <input type="text" name="challanNumber" id="challanNoId" path="challanNo" placeholder="challan No"
                                       required class="form-control"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Challan Date</label>
                            <div class="col-sm-8">
                                <div class="input-prepend input-group">
                                    <span class="add-on input-group-addon">
                                        <i class="glyph-icon icon-calendar"></i>
                                    </span>
                                    <input type="text" name="challanDate:chalDate" id="challanDateId"
                                           class=" bootstrap-datepicker form-control tempDateFrom"
                                            data-date-format="MM-dd-yy">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-8">
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Challan No:</label>
                            <div class="col-sm-8">
                                <input type="text" name="bankName" id="bankNameId" path="bankName" placeholder="Bank Name"
                                       required class="form-control"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Total Amount:</label>
                            <div class="col-sm-8">
                                <input type="text" name="totalAmount" id="totalAmountId" path="totalAmount" placeholder="Total Amount"
                                       required class="form-control"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="bg-default content-box text-center pad20A mrg10T">
                    <input id="saveButton" style="float: left; margin-left: 5px;" type="button" value="Save"
                           onclick="save()" class="btn btn-lg btn-primary"/>
                    <input id="deleteButton" style="float: left; margin-left: 5px; display: none;" type="button"
                           value="Delete" onclick="deleteTax()" class="btn btn-lg btn-primary"/>
                    <input style="float: left; margin-left: 5px;" type="button" value="Cancel"
                           onclick="Server.resetForm('taxBankPaymentId')" class="btn btn-lg btn-primary"/>
                    <div style="clear: both"></div>
                </div>
                <div class="bg-default content-box text-center pad20A mrg25T grid-resize">
                    <table id="jqGridTaxPayment"></table>
                    <div id="jqGridPager"></div>
                </div>
            </form>
        </div>
    </div>

</div>
<script type="text/javascript">
    $(document).ready(function () {
        $(function() { "use strict";
            $('.bootstrap-datepicker').bsdatepicker({
                format: 'mm-dd-yyyy',
            });
        });
        var gridId = "jqGridTaxPayment";
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val(),
        };
        var url = "${pageContext.request.contextPath}/taxbankpayment/list/";
        var formId = "taxBankPaymentId";
        var caption = 'Tax Bank Payment Information'
        var urlmethod = 'POST'
        var colModel = [
            {label: 'id', name: 'id',key: true, width: 75},
            {label: 'Challan No', name: 'challan_number', width: 150},
            {label: 'Challan Date', name: 'challan_date', width: 150},
            {label: 'Bank Name', name: 'bank_name', width: 150},
            {label: 'Total Amount ', name: 'total_amount', width: 150},

        ]
        Server.list(header, url, colModel, formId, caption, urlmethod, gridId);
    });
    function edit(taxpaymentId) {
        var action = "${pageContext.request.contextPath}/taxbankpayment/edit/";
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        Server.edit(header, action, taxpaymentId, setDataToEdit);

    }
    function setDataToEdit(result) {
        console.log(result.challan_number)
        Server.resetForm('taxBankPaymentId');
        $('#taxId').val(result.id);
        $('#version').val(result.version);
        $('#challanNoId').val(result.challan_number)
        $('#bankNameId').val(result.bank_name)
        $('#challanDateId').val(result.challan_date)
        $('#totalAmountId').val(result.total_amount);
        $('#saveButton').val('Update');
    }
    function deleteTax() {
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        var content = 'Are you sure you want to delete this Tax Payment ?';
        var formId ="taxBankPaymentId"
        //var formId = $('#deleteButton').closest('form').attr('id');
        var caption = "Tax  Payment Information"
        var url = "${pageContext.request.contextPath}/taxbankpayment/delete/";
        var gridId = "jqGridTaxPayment"
        var _form_values = $('#taxBankPaymentId').serializeJSON({
            customTypes: {
                chalDate: function(str) { // value is always a string
                    var strdate=new Date($('#challanDateId').val());
                    return new Date(strdate);
                },
            }
        });
        Server.delete(header, url, _form_values, formId, content, caption, gridId);
    }

    /*
     *save Tax Bank Payment
     */
    function save() {
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        var caption = "Tax Bank Payment Information"
        var formId ="taxBankPaymentId" /*$('#saveButton').closest('form').attr('id')*/;
        var gridId = "jqGridTaxPayment";
        var _form_values = $('#taxBankPaymentId').serializeJSON({
            customTypes: {
                chalDate: function(str) { // value is always a string
                    var strdate=new Date($('#challanDateId').val());
                    return strdate;
                },
            }
        });
        console.log(_form_values);
        if ($('#taxId').val() > 0) {
            var action = "${pageContext.request.contextPath}/taxbankpayment/update";
        } else {
            var action = "${pageContext.request.contextPath}/taxbankpayment/save";
        }

        Server.save(header, _form_values, action, formId, caption, gridId);
        Server.resetForm(formId);
    }


</script>
