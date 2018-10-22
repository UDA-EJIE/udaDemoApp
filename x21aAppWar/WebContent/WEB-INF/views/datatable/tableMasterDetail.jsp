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
<h2>Tabla Maestro Detalle</h2> <!-- Titulo pagina -->
<h2>Comarca</h2>
	<div id="comarca_filter_div"  class="rup-table-filter">
		<form id="comarca_filter_form">
			<div  id="comarca_filter_toolbar" class="formulario_legend"></div>
			<fieldset id="comarca_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="code_filter_comarca" class="formulario_linea_label">code</label>
						<input type="text" name="code" class="formulario_linea_input form-control" id="code_filter_comarca" />
					</div>
					<div class="form-group col-sm">
						<label for="provinciaCode_filter_comarca" class="formulario_linea_label">provinciaCode</label>
						<input type="text" name="provincia.code" class="formulario_linea_input form-control" id="provinciaCode_filter_comarca" />
					</div>
					<div class="form-group col-sm">
						<label for="descEs_filter_comarca" class="formulario_linea_label">descEs</label>
						<input type="text" name="descEs" class="formulario_linea_input form-control" id="descEs_filter_comarca" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="descEu_filter_comarca" class="formulario_linea_label">descEu</label>
						<input type="text" name="descEu" class="formulario_linea_input form-control" id="descEu_filter_comarca" />
					</div>
					<div class="form-group col-sm">
						<label for="css_filter_comarca" class="formulario_linea_label">css</label>
						<input type="text" name="css" class="formulario_linea_input form-control" id="css_filter_comarca" />
					</div>
				</div>
				<div id="comarca_filter_buttonSet" class="right_buttons">
                   <button id="comarca_filter_cleanButton" type="button" class="btn btn-warning rup-limpiar">
                             <i class="fa fa-eraser"></i>
                      <span>
                             <spring:message code="clear" />
                      </span>
                       </button>
                       <button id="comarca_filter_filterButton" type="button" class="btn rup-filtrar rup-filter-dropdown">
                             <i class="fa fa-filter"></i>
                      <span>
                             <spring:message code="filter" />
                      </span>
                    </button>
               </div>

			</fieldset>
		</form>
	</div>

<table id="comarca" class="tableFit table-striped table-bordered" 
	data-url-base="../jqGridComarca"
	data-filter-form="#comarca_filter_form" 
	cellspacing="0">
        <thead>
            <tr>
                <th data-col-prop="code">code</th>
                <th data-col-prop="descEs" data-col-sidx="desc_Es">descEs</th>
                <th data-col-prop="descEu" data-col-sidx="desc_Eu">descEu</th>
                <th data-col-prop="css" >css</th>
                <th data-col-prop="provincia.code" data-col-sidx="provincia.code" >provincia.code</th>
                <th data-col-prop="provincia.descEs" data-col-sidx="provincia.descEs">provincia.descEs</th>
            </tr>
        </thead>
        <tfoot>
          <tr>
              <th>code</th>
              <th>descEs</th>
              <th>descEu</th>
              <th>css</th>
              <th>provincia.code</th>
              <th>provincia.descEs</th>
          </tr>
        </tfoot>
</table>

<!-- <h2>table</h2> -->

<div id="comarca_detail_div" class="rup-table-formEdit-detail">
	<div id ="comarca_detail_navigation"></div>
	<div class="ui-dialog-content ui-widget-content" >
		<form id="comarca_detail_form">
			<div id ="comarca_detail_feedback"></div>
			
			<div class="form-row">    
				<div class="form-group col-sm">
					<label for="code_detailForm_table" class="formulario_linea_label">code</label>
			    	<input type="text" name="code" class="formulario_linea_input form-control" id="code_detailForm_table" />
			    </div>
			    
			    <div class="form-group col-sm">
			    	<label for="descEs_detail_table" class="formulario_linea_label">descEs</label>
			    	<input type="text" name="descEs" class="formulario_linea_input form-control" id="descEs_detail_table" />
			    </div>
			</div>
			<div class="form-row">       
			    <div class="form-group col-sm">
			    	<label for="descEu_detail_table" class="formulario_linea_label">descEu</label>
			    	<input type="text" name="descEu" class="formulario_linea_input form-control" id="descEu_detail_table" />
			    </div>  
			    
			    <div class="form-group col-sm">
			    	<label for="css_detail_table" class="formulario_linea_label">css</label>
			    	<input type="text" name="css" class="formulario_linea_input form-control" id="css_detail_table" />
			    </div>
			</div>
			<div class="form-row">  
			    <div class="form-group fix-align col-sm">
			    	<label for="provinciaCode_detail_table" class="formulario_linea_label">provincia.code</label>
			    	<input type="text" name="provincia.code" class="formulario_linea_input form-control" id="provinciaCode_detail_table" />
			    </div>
			</div>	
		</form>
	</div>
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset rup_tableEdit_buttonsContainerResposive">
			<button id="comarca_detail_button_save" class="btn btn-outline-primary rup_tableEdit_buttonsResposive" type="button">
				<spring:message code="save" />
			</button>
			<button id="comarca_detail_button_save_repeat" class="btn btn-outline-primary rup_tableEdit_buttonsResposive" type="button">
				<spring:message code="saveAndContinue" />
			</button>
			<button id="comarca_detail_button_cancel" class="btn btn-outline-primary rup_tableEdit_buttonsResposive" type="button">
				<spring:message code="cancel" />
			</button>
		</div>
	</div>
</div>

<h2>Localidad</h2>

	<div id="localidad_filter_div"  class="rup-table-filter">
		<form id="localidad_filter_form">
			<div  id="localidad_filter_toolbar" class="formulario_legend"></div>
			<fieldset id="localidad_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="code_filter_localidad" class="formulario_linea_label">code</label>
						<input type="text" name="code" class="formulario_linea_input form-control" id="code_filter_localidad" />
					</div>
					<div class="form-group col-sm">
						<label for="descEs_filter_localidad" class="formulario_linea_label">descEs</label>
						<input type="text" name="descEs" class="formulario_linea_input form-control" id="descEs_filter_localidad" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="descEu_filter_localidad" class="formulario_linea_label">descEu</label>
						<input type="text" name="descEu" class="formulario_linea_input form-control" id="descEu_filter_localidad" />
					</div>
					<div class="form-group col-sm">
						<label for="css_filter_localidad" class="formulario_linea_label">css</label>
						<input type="text" name="css" class="formulario_linea_input form-control" id="css_filter_localidad" />
					</div>
				</div>
				<div id="localidad_filter_buttonSet" class="right_buttons">
                       <button id="localidad_filter_cleanButton" type="button" class="btn btn-warning rup-limpiar">
                             <i class="fa fa-eraser"></i>
                      <span>
                             <spring:message code="clear" />
                      </span>
                       </button>
                       <button id="localidad_filter_filterButton" type="button" class="btn rup-filtrar rup-filter-dropdown">
                             <i class="fa fa-filter"></i>
                      <span>
                             <spring:message code="filter" />
                      </span>
                       </button>
                 </div>

			</fieldset>
		</form>
	</div>
	
<table id="localidad" class="tableFit table-striped table-bordered" 
	data-url-base="../jqGridLocalidad"
	data-filter-form="#localidad_filter_form" 
	cellspacing="0">
        <thead>
            <tr>
                <th data-col-prop="code" data-col-sidx="code">code</th>
                <th data-col-prop="descEs" data-col-sidx="t1.desc_Es">descEs</th>
                <th data-col-prop="descEu" data-col-sidx="desc_Eu">descEu</th>
                <th data-col-prop="css" >css</th>
            </tr>
        </thead>
        <tfoot>
          <tr>
              <th>code</th>
              <th>descEs</th>
              <th>descEu</th>
              <th>css</th>
          </tr>
        </tfoot>
</table>	

<!-- <h2>table</h2> -->

<div id="localidad_detail_div" class="rup-table-formEdit-detail">
	<div id ="localidad_detail_navigation"></div>
	<div class="ui-dialog-content ui-widget-content" >
		<form id="localidad_detail_form">
			<div id ="localidad_detail_feedback"></div>
			
			<div class="form-row">    
				<div class="form-group col-sm">
					<label for="code_detailForm_table" class="formulario_linea_label">code</label>
			    	<input type="text" name="code" class="formulario_linea_input form-control" id="code_detailForm_table" />
			    </div>
			    
			    <div class="form-group col-sm">
			    	<label for="descEs_detail_table" class="formulario_linea_label">descEs</label>
			    	<input type="text" name="descEs" class="formulario_linea_input form-control" id="descEs_detail_table" />
			    </div>
			</div>
			<div class="form-row">       
			    <div class="form-group col-sm">
			    	<label for="descEu_detail_table" class="formulario_linea_label">descEu</label>
			    	<input type="text" name="descEu" class="formulario_linea_input form-control" id="descEu_detail_table" />
			    </div>  
			    
			    <div class="form-group col-sm">
			    	<label for="css_detail_table" class="formulario_linea_label">css</label>
			    	<input type="text" name="css" class="formulario_linea_input form-control" id="css_detail_table" />
			    </div>
			</div>
			<div class="form-row">  
			    <div class="form-group fix-align col-sm">
			    	<label for="comarcaCode_detail_table" class="formulario_linea_label">comarca.code</label>
			    	<input type="text" name="comarca.code" class="formulario_linea_input form-control" id="comarcaCode_detail_table" />
			    </div>
			    <div class="form-group fix-align col-sm">
			    	<label for="comarcaCss_detail_table" class="formulario_linea_label">comarca.css</label>
			    	<input type="text" name="comarca.css" class="formulario_linea_input form-control" id="comarcaCss_detail_table" />
			    </div>
			</div>
			<div class="form-row">  
			    <div class="form-group fix-align col-sm">
			    	<label for="comarcaDescEs_detail_table" class="formulario_linea_label">comarca.descEs</label>
			    	<input type="text" name="comarca.descEs" class="formulario_linea_input form-control" id="comarcaDescEs_detail_table" />
			    </div>
			    <div class="form-group fix-align col-sm">
			    	<label for="comarcaDescEu_detail_table" class="formulario_linea_label">comarca.descEu</label>
			    	<input type="text" name="comarca.descEu" class="formulario_linea_input form-control" id="comarcaDescEu_detail_table" />
			    </div>
			</div>	
		</form>
	</div>
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset rup_tableEdit_buttonsContainerResposive">
			<button id="localidad_detail_button_save" class="btn btn-outline-primary rup_tableEdit_buttonsResposive" type="button">
				<spring:message code="save" />
			</button>
			<button id="localidad_detail_button_save_repeat" class="btn btn-outline-primary rup_tableEdit_buttonsResposive" type="button">
				<spring:message code="saveAndContinue" />
			</button>
			<button id="localidad_detail_button_cancel" class="btn btn-outline-primary rup_tableEdit_buttonsResposive" type="button">
				<spring:message code="cancel" />
			</button>
		</div>
	</div>
</div>
