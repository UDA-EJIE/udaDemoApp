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

   <div class="form-group">
       <label id="pluginErrorLabel" for="plugin[]">Selección de Plugins</label>
   </div>
			    
	<div class="row">
				    
          <div class="col-xs-6">
              <div class="checkbox">
                  <label>
                      <input type="checkbox" value="0" id="editForm"> Edición en Formulario
                  </label>
              </div>
              <div class="checkbox">
                  <label>
                      <input type="checkbox" value="1" id="colReorder"> Col Reorder
                  </label>
              </div>
              <div class="checkbox">
                  <label>
                      <input type="checkbox" value="2" id="multiSelection"> Multiselección
                  </label>
              </div>
              <div class="checkbox">
                  <label>
                      <input type="checkbox" value="3" id="seeker"> Seeker
                  </label>
              </div>
	</div>
						
          <div class="col-xs-6">
              <div class="checkbox">
                  <label>
                      <input type="checkbox" value="4" id="selection" > Selección Simple
                  </label>
              </div>
              <div class="checkbox">
                  <label>
                      <input type="checkbox" value="5" id="buttons"> Botones
                  </label>
              </div>
              <div class="checkbox">
                  <label>
                      <input type="checkbox" value="6" id="groups" > Agrupamiento
                  </label>
              </div>
	</div>
			 		
</div>
<span id="pluginError"></span>
<button id="multipk_aplicar" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">Aplicar Cambios</button>