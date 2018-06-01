<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2>Multi PK</h2>

<jsp:include page="includes/MultiPkFilterForm.jsp"></jsp:include>

<table id="MultiPk" class="table table-striped table-bordered" 
	data-url-base="./multipk"
	data-filter-form="#MultiPk_filter_form" 
	cellspacing="0" width="100%">
        <thead>
            <tr>
	                <th data-col-prop="ida" data-col-sidx="IDA" >ida</th>
	                <th data-col-prop="idb" data-col-sidx="IDB" >idb</th>
	                <th data-col-prop="nombre" data-col-sidx="NOMBRE" >nombre</th>
	                <th data-col-prop="apellido1" data-col-sidx="APELLIDO1" >apellido1</th>
	                <th data-col-prop="apellido2" data-col-sidx="APELLIDO2" >apellido2</th>
            </tr>
        </thead>
        <tfoot>
          <tr>
	              <th>ida</th>
	              <th>idb</th>
	              <th>nombre</th>
	              <th>apellido1</th>
	              <th>apellido2</th>
          </tr>
        </tfoot>
</table>

<jsp:include page="includes/MultiPkEdit.jsp"></jsp:include>