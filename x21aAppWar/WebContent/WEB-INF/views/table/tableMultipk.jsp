<%@include file="/WEB-INF/includeTemplate.inc"%>

<h2>Tabla con múltiples PKs</h2>

<jsp:include page="includes/tableMultiPkFilterForm.jsp"></jsp:include>

<table id="MultiPk" class="tableFit table-striped table-bordered table-material" 
	data-url-base="./multipk"
	data-filter-form="#MultiPk_filter_form">
    <thead>
		<tr>
			<th data-col-prop="nombre" data-col-sidx="NOMBRE">Nombre</th>
			<th data-col-prop="apellido1" data-col-sidx="APELLIDO1">Primer apellido</th>
			<th data-col-prop="apellido2" data-col-sidx="APELLIDO2">Segundo apellido</th>
		</tr>
	</thead>
</table>

<jsp:include page="includes/tableMultiPkEdit.jsp"></jsp:include>

<form:form modelAttribute="options" method="POST" id="multipk_tableConfiguration" class="mt-5">
   <h3 id="pluginErrorLabel">Selección de Plugins</h3>
   <fieldset class="form-group">		    
		<div class="row">
			  <legend class="col-form-label col-sm-2 pt-0">Plugins</legend>				    
	          <div class="col-sm-10">
	              <div class="checkbox-material pluginsControl">
	                  <form:checkbox path="plugins" id="editForm" value="0"/>
	                  <label for="editForm">Edición en Formulario</label>
	              </div>
	              <div class="checkbox-material pluginsControl">
	                  <form:checkbox path="plugins" id="colReorder" value="1"/>
	                  <label for="colReorder">Col Reorder</label>
	              </div>
	              <div class="checkbox-material pluginsControl">
	                  <form:checkbox path="plugins" id="seeker" value="3"/>
	                  <label for="seeker">Seeker</label>
	              </div>
	              <div class="checkbox-material pluginsControl">
	                  <form:checkbox path="plugins" id="buttons" value="5"/>
	                  <label for="buttons">Botones</label>
	              </div>
	              <div class="checkbox-material pluginsControl">
	                  <form:checkbox path="plugins" id="groups" value="6"/>
	                  <label for="groups">Agrupamiento</label>
	              </div>
	              <div class="checkbox-material pluginsControl">
	                  <form:checkbox path="plugins" id="inlineEdit" value="6"/>
	                  <label for="inlineEdit">Edición en Linea</label>
	              </div>
	          </div>
		</div>
  	</fieldset>
	<fieldset class="form-group">
		<div class="row">
			<legend class="col-form-label col-sm-2 pt-0">Tipos de selección</legend>
			<div class="col-sm-10">
			    <div class="radio-material pluginsControl">
			        <form:radiobutton id="multiSelection" path="tipoSeleccionTabla" value="2"/>
			        <label for="multiSelection">Multiselección</label>
			    </div>
		        <div class="radio-material pluginsControl">
		            <form:radiobutton id="selection" path="tipoSeleccionTabla" value="4"/>
		            <label for="selection">Selección Simple</label>
		        </div>
		        <div class="radio-material pluginsControl">
		            <form:radiobutton id="noSelection" path="tipoSeleccionTabla" value="7"/>
		            <label for="noSelection">Sin selección</label>
		        </div>
	        </div>
		</div>
  	</fieldset>
	<span id="pluginError"></span>
	<button id="multipk_aplicar" type="button" class="btn-material btn-material-primary-medium-emphasis">Aplicar Cambios</button>
</form:form>