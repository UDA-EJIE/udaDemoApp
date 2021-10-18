<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2>x21aAlumno</h2>

<jsp:include page="includes/x21aAlumnoFilterForm.jsp"></jsp:include>

<table id="x21aAlumno" class="tableFit table-striped table-bordered table-material" 
	data-url-base="../x21aalumno"
	data-filter-form="#x21aAlumno_filter_form">
	<thead>
		<tr>
	    	<th data-col-prop="id" data-col-sidx="ID" data-col-type="Text">id</th>
			<th data-col-prop="usuario" data-col-sidx="USUARIO" >usuario</th>
			<th data-col-prop="password" data-col-sidx="PASSWORD" >password</th>
			<th data-col-prop="nombre" data-col-sidx="NOMBRE" >nombre</th>
			<th data-col-prop="apellido1" data-col-sidx="APELLIDO1" >apellido1</th>
			<th data-col-prop="provinciaId" data-col-sidx="PROVINCIA_ID" >provinciaId</th>
			<th data-col-prop="localidadId" data-col-sidx="LOCALIDAD_ID" >localidadId</th>
			<th data-col-prop="comarcaId" data-col-sidx="COMARCA_ID" >comarcaId</th>
			<th data-col-prop="municipioId" data-col-sidx="MUNICIPIO_ID" >municipioId</th>
			<th data-col-prop="autonomiaId" data-col-sidx="AUTONOMIA_ID" >autonomiaId</th>
	    </tr>
	</thead>
</table>

<jsp:include page="includes/x21aAlumnoEdit.jsp"></jsp:include>