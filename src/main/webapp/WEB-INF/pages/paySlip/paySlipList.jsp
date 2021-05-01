<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel">
    <div class="panel-body">
        <h3 class="title-hero">
            Pay Slip Email List
        </h3>
                <div class="bg-default content-box text-center pad20A mrg25T">
                    <table id="jqGrid"></table>
                    <div id="jqGridPager"></div>
                </div>
        <div class="modal-footer">
            <input style="float: left; margin-left: 5px;" type="button" value="Print" onclick="printList()" class="btn btn-lg btn-primary"/>
        </div>
    </div>

    <input type="hidden" id="csr-token"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
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
    var reqTempId = 0;
    $( document ).ready(function() {
        //Start JqGrid
        $("#jqGrid").jqGrid({
            url: '${contextPath}/payment/mailList',
            mtype: "POST",
            styleUI : 'Bootstrap',
            postData: { 'X-CSRF-TOKEN': $('#csr-token').val(),
                '${_csrf.parameterName}': $('#csr-token').val()},
            contentType : "application/json",
            datatype: "json",
            colModel: [
                { label: 'id', name: 'id', align:'center', key: true, width: 75,hidden:true },
                { label:'Content', name: 'content', align:'center',width: 150 },
                { label:'Note', name: 'note', align:'center',width: 150 },
                { label:'Date', name: 'monthAndYear', align:'center',width: 150},
                { label:'Status', name: 'status', align:'center',width: 150 },
            ],
            pager: "#jqGridPager",
            rowNum:10,
            rownumbers: true,
            rowList:[10,20,30,50,100,500],
            viewrecords: true,
            sortorder: "asc",
            caption:" Requisition List",
            autoWidth: true,
            width:900,
            height:243,
            altRows:true,
            scrollOffset: 0,
            onSelectRow: function() {
                var myGrid = $('#jqGrid'),
                        selectedRowId = myGrid.jqGrid ('getGridParam', 'selrow'),
                        cellValue = myGrid.jqGrid ('getCell', selectedRowId, 'id');
                edit(cellValue);
            },
            loadComplete:function() {
                var ids = jQuery("#jqGrid").jqGrid('getDataIDs');
//                for (key in ids) {
//                    var reqId = $("#jqGrid").jqGrid('getCell', ids[key], 'id');
//                    var status = $("#jqGrid").jqGrid('getCell', ids[key], 'status');
//                    jQuery("#jqGrid").jqGrid('setRowData', ids[key], {action:'<a style="text-decoration: underline;" href="javascript:void(0)" onclick="details(\''+reqId+'\',\''+status+'\')">' + ' Details </a>'});
//                    if(status=='Prepared') {
//                        jQuery("#jqGrid").jqGrid('setRowData', ids[key], {edit: "<a style='text-decoration: underline;' href='javascript:void(0)' onclick='edit(" + reqId + ")'>" + " Edit </a>"});
//                    }
//                }
            },
        });

        //End JqGrid



        $("#jqGrid").jqGrid("setLabel", "rn", "SL.");
    });

    function details(id,status){
        window.location.href = "${contextPath}/requisition/details/"+id;
    }
    function edit(id){
        window.location.href = "${contextPath}/requisition/edit/"+id;
    }
    function printList(){
        window.location.href = "${contextPath}/requisition/requisitionList/";
    }
</script>