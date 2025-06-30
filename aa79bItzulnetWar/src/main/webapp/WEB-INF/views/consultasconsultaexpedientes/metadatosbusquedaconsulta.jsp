<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="divCategorizacionExpediente">
		<h2>
			<spring:message code="titulo.metadatosBusqueda" />
		</h2>
		<div id="metadatosBusquedaConsulta_div" class="row">
			<div id="metadatosBusquedaConsulta_feedback"></div>
			<div id="metadatosBusquedaConsulta_toolbar"></div>
			<div id="metadatosBusquedaConsulta_filter_div" class="oculto">
				<fieldset id="metadatosBusquedaConsulta_fieldset" class="form_fieldset">
					<form id="metadatosBusquedaConsulta">
						<div class="form-group col-md-4">
<%-- 							<label for="metadatosBusquedaConsulta" class="control-label"><spring:message code="label.gestor" />:</label> --%>
<!-- 							<select id="metadatosBusquedaConsulta" class="form-control" /> -->
						</div>
					</form>
				</fieldset>
			</div>
		</div>
		<div class="rup-table-container" >
	 	<h4><spring:message code="menu.seleccionesMetadatosCategorizacion"/></h4>
	   	<div class="row" id="avisoExpedientesMasivo">
			<label style="margin-left:10px" class="control-label col-lg-12 rojoImportant"><spring:message code="label.notaEtiquetasBusquedaMasiva"/></label>
		</div>
	   <fieldset>
		   <div class="list-group">
		   		<div id="categorizacionExpedientemetadatos_div" ></div>
		   </div>
	   </fieldset>
	   <fieldset>
		   <div class="row">
			   <div class="col-xs-12">
			   		<label style="margin-left:10px"><spring:message code="label.seleccionadoEnEstadoBaja"/>:</label>
	 				<del>etiketa</del>
			   		<%-- <label><spring:message code="label.noSeleccionado"/>:</label>
	 				<input style="background-color:#ECECEC; width:40px;" disabled="disabled">
	 				<label style="margin-left:10px"><spring:message code="label.seleccionadoEnEstadoAlta"/>:</label>
	 				<input style="background-color:#7DD3F2; width:40px;" disabled="disabled">
	 				<label style="margin-left:10px"><spring:message code="label.seleccionadoEnEstadoBaja"/>:</label>
	 				<input style="background-color:#F0ABBF; width:40px;" disabled="disabled"> --%>
			   </div>
		   </div>
		</fieldset>
	</div>
</div>
<div id="capasModalesDiv">
	<div id="buscadorEtiquetasMasivo" class="oculto"></div>
</div>