<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2>Tabla configurable con multiples PKs</h2>

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

<form id="multipk_tableConfiguration">
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
	                  <input type="checkbox" id="seeker" class="custom-control-input" value="3">
	                  <label for="seeker" class="custom-control-label">Seeker</label>
	              </div>
	              <div class="form-check custom-control custom-checkbox pluginsControl">
	                  <input type="checkbox" id="buttons" class="custom-control-input" value="5">
	                  <label for="buttons" class="custom-control-label">Botones</label>
	              </div>
	              <div class="form-check custom-control custom-checkbox pluginsControl">
	                  <input type="checkbox" id="groups" class="custom-control-input" value="6">
	                  <label for="groups" class="custom-control-label">Agrupamiento</label>
	              </div>
	          </div>
		</div>
  	</fieldset>
	<fieldset class="form-group">
		<div class="row">
			<legend class="col-form-label col-sm-2 pt-0">Tipos de selección</legend>
			<div class="col-sm-10">
			    <div class="form-check custom-control custom-radio pluginsControl">
			        <input type="radio" id="multiSelection" class="custom-control-input" name="multipk_seleccionTabla" value="2">
			        <label for="multiSelection" class="custom-control-label">Multiselección</label>
			    </div>
		        <div class="form-check custom-control custom-radio pluginsControl">
		            <input type="radio" id="selection" class="custom-control-input"  name="multipk_seleccionTabla" value="4">
		            <label for="selection" class="custom-control-label">Selección Simple</label>
		        </div>
		        <div class="form-check custom-control custom-radio pluginsControl">
		            <input type="radio" id="noSelection" class="custom-control-input"  name="multipk_seleccionTabla" value="7">
		            <label for="noSelection" class="custom-control-label">Sin selección</label>
		        </div>
	        </div>
		</div>
  	</fieldset>
	<span id="pluginError"></span>
	<button id="multipk_aplicar" type="button" class="btn btn-primary">Aplicar Cambios</button>
</form>