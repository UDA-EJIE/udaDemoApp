<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page	import="com.ejie.aa79b.model.enums.EstadoAlbaranEnum, com.ejie.aa79b.model.Pagamentos"%>


<div id="divDetalleAlbaran">
	<h2><spring:message code="menu.facturacion.albaranes" />: 
		<spring:message code="label.detalle.proveedores" />
	</h2>
	<hr class="mb-2" >
	
	<div class="rup-table-container">
	<div id="albaranesdetalle_feedback"></div>
	<div id="albaranesdetalle_toolbar"></div>
	<div id="albaranesdetallefilter_form">

	<div id="capaAlbaranyLote">

		<div class="p-2" style="margin: 0px 15px;">
			<div class="row">
				<div class="form-group col-xs-5">
					<label class="control-label"><spring:message code="label.refalbaran" />:</label> ${pagamento.refAlbaran}
				</div>
				<div class="form-group col-xs-5">
					<label class="control-label"><spring:message code="label.dtcreacion" />:</label> ${pagamento.fechaHoraAlta}
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-4">
					<label class="control-label"><spring:message code="label.estadoAlbaran" />:</label> ${pagamento.descEstadoAlbaran}
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-5">
					<label class="control-label"><spring:message code="label.impprevisto" />:</label> 
					<form:input id="importeTotal" path="pagamento.importeTotal" cssClass="labelInput" disabled="true"/>
				</div>
				<div class="form-group col-xs-5">
					<label class="control-label"><spring:message code="label.imppagado" />:</label>
					<form:input id="importeFactura" path="pagamento.importeFactura" cssClass="labelInput" disabled="true"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-5">
					<label class="control-label"><spring:message code="label.numtareasasociadas" />:</label> ${pagamento.ntareas}
				</div>
				<div class="form-group col-xs-5">
					<label class="control-label"><spring:message code="label.numexpasociados" />:</label> ${pagamento.nexpedientes}
				</div>
			</div>
		</div>

		<fieldset class="rup-table-filter-fieldset">
			<legend>
				<spring:message code="label.lote"></spring:message>
			</legend>
	
			<div class="row">
				<div class="col-xs-12 col-md-5">
					<label class="control-label"><spring:message code="label.detalle.nombre" />:</label> ${lote.nombreLote}
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-md-5">
					<label class="control-label"><spring:message code="label.imptotalasignado" />:</label> 
					<form:input id="importeAsignado" path="lote.importeAsignado" cssClass="labelInput" disabled="true"/>
				</div>
	
				<div class="col-xs-12 col-md-5">
					<label class="control-label"><spring:message code="label.imptotalconsumido" />:</label>
					<form:input id="importeConsumido" path="lote.importeConsumido" cssClass="labelInput" disabled="true"/>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-md-5">
					<label class="control-label"><spring:message code="label.imptotalprevisto" />:</label> 
					<input type="hidden" id="impprevisto" name="impprevisto" value="${lote.importePrevisto}" />
					<form:input id="importePrevistoTxt" path="lote.importePrevisto" cssClass="labelInput" disabled="true"/>
				</div>
				<div class="col-xs-12 col-md-6">
					<label class="control-label"><spring:message code="label.importeTotalDisponible" />:</label> 
					<form:input id="importeDisponible" path="lote.importeDisponible" cssClass="labelInput" disabled="true"/>
				</div>
			</div>
		</fieldset>
	</div>

	<fieldset class="rup-table-filter-fieldset" style="margin-top: 15px">
		<legend><spring:message code="label.tareas"></spring:message></legend>
      
		<div id="tableTareas_div" class="rup-table-container-tareas">
			<div id="tableTareas_filter_div" class="rup-table-filter oculto">
				<form id="tableTareas_filter_form">
					<div id="tableTareas_filter_toolbar" class="formulario_legend"></div>

					<input type="hidden" name="idLote" value="${pagamento.idLote}">
					<input type="hidden" name="idAlbaran" value="${pagamento.idAlbaran}">
				</form>
			</div>

			<div id="tableTareas_grid_div" class="horizontal_scrollable_table">
				<!-- Tabla -->
				<table id="tableTareas"></table>
				<!-- Barra de paginación -->
				<div id="tableTareas_pager"></div>
			</div>
		</div>
	</fieldset>
</div>
</div>
</div>