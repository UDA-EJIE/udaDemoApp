<%@include file="/WEB-INF/includeTemplate.inc"%>
<div id="divCategorizacionExpediente">
	<h2><spring:message code="menu.categorizacionExpediente"/></h2>
	<div id="categorizacionExpediente_div" class="rup-table-container">
		<div id="categorizacionExpediente_feedback"></div>
		<div id="categorizacionExpediente_toolbar"></div>
		<div id="contenFormularios" class="filterForm oculto ">
			<form id="categorizacionExpediente_filterForm" class="form-horizontal">
				<fieldset id="categorizacionExpediente_filter_fieldset" class="form_fieldset oculto">
					<div class="formulario_columna_cnt">
						<input type="hidden" id="anyo_categorizacion_expediente">
						<input type="hidden" id="numExp_categorizacion_expediente">
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	<div class="rup-table-container" >
	 	<h4><spring:message code="menu.seleccionesMetadatosCategorizacion"/></h4>
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
	 				<!-- <input style="background-color:#F0ABBF; width:40px;" disabled="disabled"> -->
			   </div>
		   </div>
		</fieldset>
	</div>
</div>
<div id="capasModalesDiv">
	<div id="buscadorEtiquetasPlanificacion" class="oculto"></div>
</div>