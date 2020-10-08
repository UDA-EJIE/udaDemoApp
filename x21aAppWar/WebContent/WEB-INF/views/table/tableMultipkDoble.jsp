<%@include file="/WEB-INF/includeTemplate.inc"%>

<h2>Tabla con multiples PKs</h2>
<jsp:include page="includes/tableMultiPkFilterForm.jsp"></jsp:include>
<table id="MultiPk" class="tableFit table-striped table-bordered table-material" 
	data-url-base="../multipk"
	data-filter-form="#MultiPk_filter_form">
    <thead>
		<tr>
			<th data-col-prop="ida" data-col-sidx="IDA" >ida</th>
			<th data-col-prop="idb" data-col-sidx="IDB" >idb</th>
			<th data-col-prop="nombre" data-col-sidx="NOMBRE" >nombre</th>
			<th data-col-prop="apellido1" data-col-sidx="APELLIDO1" >apellido1</th>
			<th data-col-prop="apellido2" data-col-sidx="APELLIDO2" >apellido2</th>
		</tr>
	</thead>
</table>
<jsp:include page="includes/tableMultiPkEdit.jsp"></jsp:include>
</br>

<h2>Tabla Simple</h2>
<jsp:include page="includes/tableFilterForm.jsp"></jsp:include>
<table id="example" class="tableFit table-striped table-bordered table-material"
	data-url-base=".."
	data-filter-form="#example_filter_form">
        <thead>
            <tr>
                <th data-col-prop="nid" data-col-sidx="id" data-col-edit="false">Id</th>
                <th data-col-prop="nombre" data-col-edit="true">Nombre</th>
                <th data-col-prop="apellido1">Primer apellido</th>
                <th data-col-prop="ejie" data-col-type="Checkbox">Ejie</th>
                <th data-col-prop="fechaAlta" data-col-sidx="fecha_alta" data-col-type="Datepicker">Fecha alta</th>
                <th data-col-prop="fechaBaja" data-col-sidx="fecha_baja" data-col-type="Datepicker">Fecha baja </th>
                <th data-col-prop="rol" data-col-type="combo">Rol</th>
            </tr>
        </thead>
</table>
<jsp:include page="includes/tableEdit.jsp"></jsp:include>
