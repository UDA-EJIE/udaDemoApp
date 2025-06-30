<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in" id="divBorradorFactura">
	<h2><spring:message code="menu.consultaFacturacion"></spring:message><span style="font-size: 11px;padding-left: 10px;"><spring:message code="menu.borradorFactura"></spring:message></span></h2>
	<div id="borradorFacturaMain_toolbar"></div>
		<div id="borradorFactura_div" class="rup-table-container" style="margin: 0 auto;">
		<div id="borradorFactura_feedback"></div>						<!-- Feedback de la tabla --> 
		<div id="borradorFactura_toolbar" style="display:none;border: none;"></div>						<!-- Botonera de la tabla --> 
		<div id="borradorFactura_filter_div" class="filterForm"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="borradorFactura_filter_form" class="form-horizontal" style="border:none;">	
	<fieldset id="borradorFactura_filter_fieldset" class="rup-table-filter-fieldset ">
		<legend>
			<spring:message code="label.datosCliente"></spring:message>
		</legend>
		<div class="row">
			<label class="control-label col-md-1 no-padding-right" style="width:150px"  data-i18n="label.cif"><spring:message code="label.cif" />:</label>
			<label class="control-label col-md-1  labelInput" id="cif"></label>
			<label class="control-label col-md-3 no-padding-right" style="width:185px;" data-i18n="organismoOEntidad"><spring:message code="label.organismoOEntidad" />:</label>
			<label class="control-label col-md-6  labelInput" id="descEuEntidad"></label>
		</div>
		<div class="row" id="capacontacto">
			<label class="control-label col-md-2 col-lg-2 no-padding-right" style="width:150px" data-i18n="label.contacto"><spring:message code="label.contacto" />:</label>
			<label class="control-label col-md-10  labelInput" id="contacto"></label>
		</div>
		<div class="row">
			<label class="control-label col-md-1 no-padding-right" style="width:150px" data-i18n="label.domicilio"><spring:message code="label.domicilio" />:</label>
			<label class="control-label col-md-9  labelInput" id="domicilio"></label>
		</div>
	</fieldset>
	<table id="tabla1" style="width:100%;  margin-bottom: 10px; margin-top: 10px;" class=" tablaConBorde tablaLineasImpares">
		 <tr>
		   <th><spring:message code="label.concepto"></spring:message></th>
		   <th><spring:message code="label.cantidad"></spring:message></th> 
		   <th style="width: 10%;"><spring:message code="label.total"> (€)</spring:message></th>
		 </tr>
		 <tr>
		   <td><spring:message code="mensaje.ExpTramitadosServicTraduct"></spring:message></td>
		   <td id="cantidadExp"></td> 
		   <td class="txtDcha"><span id="totalOne"></span></td>
 		</tr>
	</table>
	<table id="tabla1" style="width:100%;  margin-bottom: 10px;" class=" tablaConBorde tablaLineasImpares">
		 <tr>
		   <th style="width: 63%;"></th>
		   <th><spring:message code="label.impBase"> (€)</spring:message></th> 
		   <th style="width: 10%;"><spring:message code="label.iva"> (€)</spring:message></th>
		   <th style="width: 10%;"><spring:message code="label.total"> (€)</spring:message></th>
		 </tr>
		 <tr>
		   <td><spring:message code="label.totalMayus"></spring:message></td>
		   <td class="txtDcha"><span id="importeBase"></span></td> 
		   <td class="txtDcha"><span id="importeIva"></span></td>
		   <td class="txtDcha"><span id="totalTwo"></span></td>
 		</tr>
	</table>

				<input type="hidden" name="idTipoExpediente" id="idTipoExpedienteFilter">
				<input type="hidden" name="entidadContactoFactura.entidad.tipo" id="tipoEntidadFilter">
				<input type="hidden" name="entidadContactoFactura.entidad.codigo" id="idEntidadFilter">
				<input type="hidden" name="entidadContactoFactura.solicitante.dni" id="dniContactoFilter">
				<div id="borradorFactura_filter_toolbar" class="formulario_legend oculto"></div>
				
			</form>
			<fieldset id="borradorFactura_filter_fieldsetss" class="rup-table-filter-fieldset ">
				<legend>
					<spring:message code="label.expedientesAsociadosAFactura"></spring:message>
				</legend>
				<div class="borradorFactura_grid_div horizontal_scrollable_table" >
					<!-- Tabla -->
					<table id="borradorFactura"></table>
					<!-- Barra de paginación -->
					<div id="borradorFactura_pager"></div>
				</div>
				<label id="mensajeTablaInterpretacion"></label>
			</fieldset>
		</div>
	</div>
</div>