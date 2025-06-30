<%@include file="/WEB-INF/includeTemplate.inc"%>

<div class="container-fluid aa79b-fade in" id="divConfeccionarFactExp">
	<h2><spring:message code="menu.consultaFacturacion"></spring:message><span style="font-size: 11px;padding-left: 10px;"><spring:message code="menu.confeccionarfactura"></spring:message></span></h2>
	<div id="confeccionarFactExp_div" class="rup-table-container">
		<div id="confeccionarFactExp_feedback"></div>						<!-- Feedback de la tabla --> 
		<div id="confeccionarFactExp_toolbar"></div>						<!-- Botonera de la tabla --> 
		<div id="confeccionarFactExp_filter_div" class="filterForm" style="border:none;"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="confeccionarFactExp_filter_form" class="form-horizontal">	
				<input type="hidden" name="idTipoExpediente" id="idTipoExpedienteFilter">
				<input type="hidden" name="entidadContactoFactura.entidad.tipo" id="tipoEntidadFilter">
				<input type="hidden" name="entidadContactoFactura.entidad.codigo" id="idEntidadFilter">
				<input type="hidden" name="entidadContactoFactura.solicitante.dni" id="dniContactoFilter">
				<div id="confeccionarFactExp_filter_toolbar" class="formulario_legend oculto" ></div>
				<fieldset id="confeccionarFactExp_filter_fieldset" class="rup-table-filter-fieldset ">
					<legend>
						<spring:message code="label.datosCliente"></spring:message>
					</legend>
					<div class="row marginBot5">
						<label class="control-label col-md-1 no-padding-right" style="width:150px" data-i18n="label.cif"><spring:message code="label.cif" />:</label>
						<label class="control-label col-md-1 labelInput" id="cif"></label>
						<label class="control-label col-md-3 no-padding-right" style="width:185px;" data-i18n="organismoOEntidad"><spring:message code="label.organismoOEntidad" />:</label>
						<label class="control-label col-md-6 labelInput" id="descEuEntidad"></label>
					</div>
					<div class="row marginBot5" id="capacontacto">
						<label class="control-label col-md-2 col-lg-1 no-padding-right" style="width:150px" data-i18n="label.contacto"><spring:message code="label.contacto" />:</label>
						<label class="control-label col-md-10 labelInput" id="contacto"></label>
					</div>
					<div class="row marginBot5">
						<label class="control-label col-md-1 no-padding-right" style="width:150px" data-i18n="label.domicilio"><spring:message code="label.domicilio" />:</label>
						<label class="control-label col-md-9 labelInput" id="domicilio"></label>
					</div>
				</fieldset>
			</form>
			<fieldset id="confeccionarFactExp_filter_fieldset" class="rup-table-filter-fieldset ">
				<legend>
					<spring:message code="label.expedientesFacturables"></spring:message>
				</legend>
				<div class="confeccionarFactExp_grid_div horizontal_scrollable_table" >
					<!-- Tabla -->
					<table id="confeccionarFactExp"></table>
					<!-- Barra de paginación -->
					<div id="confeccionarFactExp_pager"></div>
				</div>
				<label id="mensajeTablaInterpretacion"></label>
			</fieldset>
			<div style="text-align:right">
				<label class="control-label col-md-10 no-padding-right" data-i18n="label.importeBase "><spring:message code="label.importeBase" />:</label>
				<label class="control-label col-md-2 no-padding-left"><span id="importeBaseTotal"></span>€</label>
				<label class="control-label col-md-10 no-padding-right" data-i18n="label.importeIVAAplicado"><spring:message code="label.importeIVAAplicado" />:</label>
				<label class="control-label col-md-2 no-padding-left"><span id="importeIVAAplicadoTotal"></span>€</label>
				<label class="control-label col-md-10 no-padding-right" data-i18n="label.totalMayus"><spring:message code="label.totalMayus" />:</label>
				<label class="control-label col-md-2 no-padding-left"><span id="total"></span>€</label>
				<label class="control-label col-md-12 " data-i18n="label.calculosRealizadosExpSelecc">*<spring:message code="label.calculosRealizadosExpSelecc" /></label>
			</div>
		</div>
	</div>
</div>