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
<h2>Tabla configurable</h2> <!-- Titulo pagina -->

<jsp:include page="includes/tableFilterForm.jsp"></jsp:include>

<table id="example" class="tableFit table-striped table-bordered table-material"
	data-url-base="."
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

<form:form modelAttribute="options" method="POST" id="example_tableConfiguration" model class="mt-5">
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
	                  <form:checkbox path="plugins" id="seeker" value="2"/>
	                  <label for="seeker">Seeker</label>
	              </div>
	              <div class="checkbox-material pluginsControl">
	                  <form:checkbox path="plugins" id="buttons" value="3"/>
	                  <label for="buttons">Botones</label>
	              </div>
	              <div class="checkbox-material pluginsControl">
	                  <form:checkbox path="plugins" id="groups" value="4"/>
	                  <label for="groups">Agrupamiento</label>
	              </div>
	              <div class="checkbox-material pluginsControl">
	                  <form:checkbox path="plugins" id="multiFilter" value="5"/>
	                  <label for="multiFilter">MultiFilter</label>
	              </div>
	              <div class="checkbox-material pluginsControl">
	                  <form:checkbox path="plugins" id="inlineEdit" value="6"/>
	                  <label for="inlineEdit">Edición en Linea</label>
	              </div>
	              <div class="checkbox-material pluginsControl">
	                  <form:checkbox path="plugins" id="sinFiltro" value="12"/>
	                  <label for="sinFiltro">Sin Filtro</label>
	              </div>
	          </div>
		</div>
  	</fieldset>
	<fieldset class="form-group">
		<div class="row">
			<legend class="col-form-label col-sm-2 pt-0">Tipos de selección</legend>
			<div class="col-sm-10">
			    <div class="radio-material pluginsControl">
			        <form:radiobutton id="multiSelection" path="tipoSeleccionTabla" value="7"/>
			        <label for="multiSelection">Multiselección</label>
			    </div>
		        <div class="radio-material pluginsControl">
		            <form:radiobutton id="selection" path="tipoSeleccionTabla" value="8"/>
		            <label for="selection">Selección Simple</label>
		        </div>
		        <div class="radio-material pluginsControl">
		            <form:radiobutton id="noSelection" path="tipoSeleccionTabla" value="9"/>
		            <label for="noSelection">Sin selección</label>
		        </div>
	        </div>
		</div>
  	</fieldset>
  	<fieldset class="form-group">
		<div class="row">
			  <legend class="col-form-label col-sm-2 pt-0">Pruebas</legend>
	          <div class="col-sm-10">
	              <div class="checkbox-material pluginsControl">
	                  <form:checkbox path="otros" id="triggers" value="10"/>
	                  <label for="triggers">Activar Triggers en Consola</label>
	              </div>
	              <div class="checkbox-material pluginsControl">
                       <form:checkbox path="otros" id="multiPart" value="11"/>
                       <label for="multiPart">Edición con MultiPart</label>
	          	 </div>
	          </div>
		</div>
  	</fieldset>
	<span id="pluginError"></span>
	<button id="example_aplicar" type="button" class="btn-material btn-material-primary-medium-emphasis">Aplicar Cambios</button>
</form:form>
