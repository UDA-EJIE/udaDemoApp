<%--  
 -- Copyright 2019 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2 class="title mb-3">Tabla configurable</h2> <!-- Titulo pagina -->

<jsp:include page="includes/tableFilterForm.jsp"></jsp:include>

<spring:url value="/table/filter" var="noFilterURL"/>
<form:form modelAttribute="usuario" id="example_noFilter_form" class="d-none" action="${noFilterURL}" method="POST"/>

<spring:url value="/table/search" var="seekerURL"/>
<form:form modelAttribute="usuario" id="example_seeker_form" class="d-none" action="${seekerURL}" method="POST">
	<form:input path="nombre" id="nombre_example_seeker_form" />
	<form:select path="apellido1" id="apellido1_example_seeker_form" />
	<form:select path="apellido2" id="apellido2_example_seeker_form" />
	<form:input path="fechaAlta" id="fechaAlta_example_seeker_form" />
	<form:input path="fechaBaja" id="fechaBaja_example_seeker_form" />
	<form:radiobutton path="ejie" value="0"/>
	<form:radiobutton path="ejie" value="1"/>
	<form:select path="rol" id="rol_example_seeker_form" />
</form:form>

<table id="example" class="tableFit table-striped table-bordered table-material" data-url-base="." data-filter-form="#example_filter_form">
	<thead>
		<tr>
			<th data-col-prop="nombre" data-col-edit="true">Nombre</th>
			<th data-col-prop="apellido1">Primer apellido</th>
			<th data-col-prop="apellido2">Segundo apellido</th>
			<th data-col-prop="ejie" data-col-type="Checkbox">Ejie</th>
			<th data-col-prop="fechaAlta" data-col-sidx="fecha_alta" data-col-type="Datepicker">Fecha alta</th>
			<th data-col-prop="fechaBaja" data-col-sidx="fecha_baja" data-col-type="Datepicker">Fecha baja</th>
			<th data-col-prop="rol" data-col-type="select">Rol</th>
		</tr>
	</thead>
</table>

<jsp:include page="includes/tableEdit.jsp"></jsp:include>

<form:form modelAttribute="options" method="POST" id="example_tableConfiguration" class="mt-5">
	<h3 id="pluginErrorLabel">Configuración de la tabla</h3>
	<fieldset class="form-group">
		<legend class="col-form-label col-sm-2 pt-0">Plugins</legend>
		<div class="col-sm-10">
			<div class="checkbox-material pluginsControl">
				<form:checkbox path="plugins" id="colReorder" value="1"/>
				<label for="colReorder">Reordenar columnas (col reorder)</label>
			</div>
			<div class="checkbox-material pluginsControl">
				<form:checkbox path="plugins" id="seeker" value="2"/>
				<label for="seeker">Buscador (seeker)</label>
			</div>
			<div class="checkbox-material pluginsControl">
				<form:checkbox path="plugins" id="buttons" value="3"/>
				<label for="buttons">Botonera</label>
			</div>
			<div class="checkbox-material pluginsControl">
				<form:checkbox path="plugins" id="groups" value="4"/>
				<label for="groups">Agrupamiento</label>
			</div>
		</div>
	</fieldset>
	<fieldset class="form-group">
		<legend class="col-form-label col-sm-2 pt-0">Tipos de filtrado</legend>
		<div class="col-sm-10">
			<div class="radio-material pluginsControl">
				<form:radiobutton path="filterType" id="multiFilter" value="5"/>
				<label for="multiFilter">Múltiple (MultiFilter)</label>
			</div>
			<div class="radio-material pluginsControl">
				<form:radiobutton path="filterType" id="simpleFilter" value="15"/>
				<label for="simpleFilter">Simple</label>
			</div>
			<div class="radio-material pluginsControl">
				<form:radiobutton path="filterType" id="noFilter" value="12"/>
				<label for="noFilter">Sin filtro</label>
			</div>
		</div>
	</fieldset>
	<fieldset class="form-group">
		<legend class="col-form-label col-sm-2 pt-0">Tipos de selección</legend>
		<div class="col-sm-10">
			<div class="radio-material pluginsControl">
				<form:radiobutton path="selectionType" id="multiSelection" value="7"/>
				<label for="multiSelection">Múltiple</label>
			</div>
			<div class="radio-material pluginsControl">
				<form:radiobutton path="selectionType" id="selection" value="8"/>
				<label for="selection">Simple</label>
			</div>
			<div class="radio-material pluginsControl">
				<form:radiobutton path="selectionType" id="noSelection" value="9"/>
				<label for="noSelection">Sin selección</label>
			</div>
		</div>
	</fieldset>
	<fieldset class="form-group">
		<legend class="col-form-label col-sm-2 pt-0">Tipos de edición</legend>
		<div class="col-sm-10">
			<div class="radio-material pluginsControl">
				<form:radiobutton path="editType" id="editForm" value="0"/>
				<label for="editForm">Edición en formulario (necesita tener los botones activos)</label>
			</div>
			<div class="radio-material pluginsControl">
				<form:radiobutton path="editType" id="inlineEdit" value="6"/>
				<label for="inlineEdit">Edición en línea (necesita tener los botones activos)</label>
			</div>
			<div class="radio-material pluginsControl">
				<spring:url value="/table/addFromNewWindow" var="addFromNewWindow"/>
				<form:radiobutton path="editType" id="editFormTargetBlank" value="13" data-add-new-window-url="${addFromNewWindow}" data-edit-new-window-url="/x21aAppWar/table/editFromNewWindow/" />
				<label for="editFormTargetBlank">Edición en formulario sobre nueva pestaña (necesita tener los botones activos)</label>
			</div>
			<div class="radio-material pluginsControl">
				<form:radiobutton path="editType" id="noEdit" value="14"/>
				<label for="noEdit">Sin edición</label>
			</div>
		</div>
	</fieldset>
	<fieldset class="form-group">
		<legend class="col-form-label col-sm-2 pt-0">Pruebas</legend>
		<div class="col-sm-10">
			<div class="checkbox-material pluginsControl">
				<form:checkbox path="otros" id="triggers" value="10"/>
				<label for="triggers">Activar triggers en consola</label>
			</div>
			<div class="checkbox-material pluginsControl">
			<form:checkbox path="otros" id="multipart" value="11"/>
				<label for="multipart">Edición con multipart (solo funciona con la opción de edición en formulario)</label>
			</div>
		</div>
	</fieldset>
	<span id="pluginError"></span>
	<button id="example_aplicar" type="button" class="btn-material btn-material-primary-medium-emphasis">Aplicar cambios</button>
</form:form>
