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

<table id="example" class="table table-striped table-bordered" 
	data-url-base="./simple"
	data-filter-form="#example_filter_form" 
	cellspacing="0" width="100%">
        <thead>
            <tr>
                <th data-col-prop="id">Id</th>
                <th data-col-prop="nombre">Nombre</th>
                <th data-col-prop="apellido1">Primer apellido</th>
                <th data-col-prop="ejie" data-col-type="Checkbox">Ejie</th>
                <th data-col-prop="fechaAlta" data-col-sidx="fecha_alta" data-col-type="Datepicker">Fecha alta</th>
                <th data-col-prop="fechaBaja" data-col-sidx="fecha_baja" data-col-type="Datepicker">Fecha baja</th>
                <th data-col-prop="rol" data-col-type="combo">Rol</th>
            </tr>
        </thead>
        <tfoot>
          <tr>
              <th>Id</th>
              <th>Nombre</th>
              <th>Primer apellido</th>
              <th>Ejie</th>
              <th>Fecha alta</th>
              <th>Fecha baja</th>
              <th>Rol</th>
          </tr>
        </tfoot>
</table>

<jsp:include page="includes/datatableEdit.jsp"></jsp:include>


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
<button id="example_aplicar" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">Aplicar Cambios</button>
