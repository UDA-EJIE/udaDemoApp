<%--  
 -- Copyright 2011 E.J.I.E., S.A.
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

<jsp:include page="includes/filterForm.jsp"></jsp:include>

<table id="example" class="tableFit table-striped table-bordered" 
	data-url-base="."
	data-filter-form="#example_filter_form">
        <thead>
            <tr>
                <th data-col-prop="id" data-col-edit="false">Id</th>
                <th data-col-prop="nombre" data-col-edit="true">Nombre</th>
                <th data-col-prop="apellido1">Primer apellido</th>
                <th data-col-prop="ejie" data-col-type="Checkbox">Ejie</th>
                <th data-col-prop="fechaAlta" data-col-sidx="fecha_alta" data-col-type="Datepicker">Fecha alta</th>
                <th data-col-prop="fechaBaja" data-col-sidx="fecha_baja" data-col-type="Datepicker">Fecha baja</th>
                <th data-col-prop="rol" data-col-type="combo">Rol</th>
            </tr>
        </thead>
</table>

<jsp:include page="includes/datatableEdit.jsp"></jsp:include>

<form id="example_tableConfiguration">
   <h3 id="pluginErrorLabel">Selección de Plugins</h3>
   <fieldset class="form-group">		    
		<div class="row">
			  <legend class="col-form-label col-sm-2 pt-0">Plugins</legend>				    
	          <div class="col-sm-10">
	              <div class="form-check custom-control custom-checkbox pluginsControl">
	                  <input type="checkbox" id="editForm" class="custom-control-input" value="0">
	                  <label for="editForm" class="custom-control-label">Edición en Formulario</label>
	              </div>
	              <div class="form-check custom-control custom-checkbox pluginsControl">
	                  <input type="checkbox" id="colReorder" class="custom-control-input" value="1">
	                  <label for="colReorder" class="custom-control-label">Col Reorder</label>
	              </div>
	              <div class="form-check custom-control custom-checkbox pluginsControl">
	                  <input type="checkbox" id="seeker" class="custom-control-input" value="2">
	                  <label for="seeker" class="custom-control-label">Seeker</label>
	              </div>
	              <div class="form-check custom-control custom-checkbox pluginsControl">
	                  <input type="checkbox" id="buttons" class="custom-control-input" value="3">
	                  <label for="buttons" class="custom-control-label">Botones</label>
	              </div>
	              <div class="form-check custom-control custom-checkbox pluginsControl">
	                  <input type="checkbox" id="groups" class="custom-control-input" value="4">
	                  <label for="groups" class="custom-control-label">Agrupamiento</label>
	              </div>
	              <div class="form-check custom-control custom-checkbox pluginsControl">
	                  <input type="checkbox" id="multiFilter" class="custom-control-input" value="5">
	                  <label for="multiFilter" class="custom-control-label">MultiFilter</label>
	              </div>
	              <div class="form-check custom-control custom-checkbox pluginsControl">
	                  <input type="checkbox" id="inlineEdit" class="custom-control-input" value="6">
	                  <label for="inlineEdit" class="custom-control-label">Edición en Linea</label>
	              </div>
	          </div>
		</div>
  	</fieldset>
	<fieldset class="form-group">
		<div class="row">
			<legend class="col-form-label col-sm-2 pt-0">Tipos de selección</legend>
			<div class="col-sm-10">
			    <div class="form-check custom-control custom-radio pluginsControl">
			        <input type="radio" id="multiSelection" class="custom-control-input" name="example_seleccionTabla" value="7">
			        <label for="multiSelection" class="custom-control-label">Multiselección</label>
			    </div>
		        <div class="form-check custom-control custom-radio pluginsControl">
		            <input type="radio" id="selection" class="custom-control-input"  name="example_seleccionTabla" value="8">
		            <label for="selection" class="custom-control-label">Selección Simple</label>
		        </div>
		        <div class="form-check custom-control custom-radio pluginsControl">
		            <input type="radio" id="noSelection" class="custom-control-input"  name="example_seleccionTabla" value="9">
		            <label for="noSelection" class="custom-control-label">Sin selección</label>
		        </div>
	        </div>
		</div>
  	</fieldset>
  	<fieldset class="form-group">		    
		<div class="row">
			  <legend class="col-form-label col-sm-2 pt-0">Pruebas</legend>				    
	          <div class="col-sm-10">
	              <div class="form-check custom-control custom-checkbox pluginsControl">
	                  <input type="checkbox" id="triggers" class="custom-control-input" value="10">
	                  <label for="triggers" class="custom-control-label">Activar Triggers en Consola</label>
	              </div>
	              <div class="form-check custom-control custom-checkbox pluginsControl">
	               <input type="checkbox" id="multiPart" class="custom-control-input" value="10">
	               <label for="multiPart" class="custom-control-label">Edición con MultiPart</label>
	          	 </div>
	          </div>
		</div>
  	</fieldset>
	<span id="pluginError"></span>
	<button id="example_aplicar" type="button" class="btn btn-primary">Aplicar Cambios</button>
</form>