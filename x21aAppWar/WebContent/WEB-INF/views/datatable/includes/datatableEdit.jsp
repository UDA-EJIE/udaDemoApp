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
<!-- Titulo pagina -->
<!-- <h2>table</h2> -->

<div id="example_detail_div" class="rup-table-formEdit-detail">
	<div id ="example_detail_navigation"></div>
	<div class="dialog-content-material" >
		<form id="example_detail_form" class="rup_form">
			<div id ="example_detail_feedback"></div>
			
			<div class="form-row">    
				<div class="form-groupMaterial col-sm">
			    	<input type="text" name="id" class="formulario_linea_input form-control" id="id_detailForm_table" />
					<label for="id_detailForm_table" class="formulario_linea_label"><spring:message code="id" /></label>
			    </div>
			    
			    <div class="form-groupMaterial col-sm">
			    	<input type="text" name="nombre" class="formulario_linea_input form-control" id="nombre_detail_table" />
			    	<label for="nombre_detail_table" class="formulario_linea_label"><spring:message code="nombre" /></label>
			    </div>
			</div>
			<div class="form-row">       
			    <div class="form-groupMaterial col-sm">
			    	<input type="text" name="apellido1" class="formulario_linea_input form-control" id="apellido1_detail_table" />
			    	<label for="apellido1_detail_table" class="formulario_linea_label"><spring:message code="apellido1" /></label>
			    </div>  
			    
			    <div class="form-groupMaterial col-sm">
			    	<input type="text" name="apellido2" class="formulario_linea_input form-control" id="apellido2_detail_table" />
			    	<label for="apellido2_detail_table" class="formulario_linea_label"><spring:message code="apellido2" /></label>
			    </div>
			</div>
			<div class="form-row">       
			    <div class="form-groupMaterial fix-align col-sm">
			    	<input type="text" name="fechaBaja" class="formulario_linea_input form-control" id="fechaBaja_detail_table" />
			    	<label for="fechaBaja_detail_table" class="formulario_linea_label"><spring:message code="fechaBaja" /></label>
			    </div>
			    
			    <div class="form-groupMaterial fix-align col-sm">
			    	<input type="text" name="fechaAlta" class="formulario_linea_input form-control" id="fechaAlta_detail_table" />
			    	<label for="fechaAlta_detail_table" class="formulario_linea_label"><spring:message code="fechaAlta" /></label>
			    </div>
			</div>
			<div class="form-row">
			    <div class="form-groupMaterial col-sm checkbox-material">
			    	<input type="checkbox" name="ejie" class="formulario_linea_input form-control" id=ejie_detail_table value="1" />
			    	<label for="ejie_detail_table" class="formulario_linea_label"><spring:message code="ejie" /></label>
			    </div> 
			    
			    <div class="form-groupMaterial fix-align col-sm">
			    	<input type="text" name="rol" class="formulario_linea_input form-control" id="rol_detail_table" />
			    	<label for="rol_detail_table" class="formulario_linea_label"><spring:message code="rol" /></label>
			    </div>
			</div>			
		</form>
	</div>
	<div class="rup-datatable-buttonpanel-material ui-helper-clearfix">
		<div class="text-right">
			<button id="example_detail_button_save" class="btn-material btn-material-sm btn-material-primary-high-emphasis rup_tableEdit_buttonsResposive" type="button">
				<spring:message code="save" />
			</button>
			<button id="example_detail_button_save_repeat" class="btn-material btn-material-sm btn-material-primary-high-emphasis rup_tableEdit_buttonsResposive" type="button">
				<spring:message code="saveAndContinue" />
			</button>
			<button id="example_detail_button_cancel" class="btn-material btn-material-sm btn-material-primary-low-emphasis rup_tableEdit_buttonsResposive" type="button">
				<spring:message code="cancel" />
			</button>
		</div>
	</div>
</div>
