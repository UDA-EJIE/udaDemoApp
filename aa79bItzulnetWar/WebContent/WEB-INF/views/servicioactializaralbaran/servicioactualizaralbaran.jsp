<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>
<div id="divActualizarAlbaranGeneral">
	<div id="actualizarAlbaranGeneralDiv" class="container-fluid">
		<h2><spring:message code="menu.actualizacionAlbaran"></spring:message></h2>
		<hr class="mb-2">
		<div class="rup-table-container">
			<div id="actualizarAlbaran_feedback"></div>						
			<div id="actualizarAlbaran_toolbar"></div>	
			<div id="contenFormularios" class="rup-table-filter">
				<form id="actualizarAlbaran_filter_form" class="form-horizontal">
					<div id="actualizarAlbaran_filter_toolbar"
						class="formulario_legend filterCabecera"></div>
					<fieldset id="actualizarAlbaran_filter_fieldset"
						class="rup-table-filter-fieldset filterCuerpo">
						<div class="p-2">
							<div class="row">
								<div class="form-group col-xs-12 col-md-4">
									<label for="empresaProv_filter_table" class="control-label"><spring:message code="label.empproveedora1"/>:</label>
									<select name="lotes.empresasProveedoras.codigo" id="empresaProv_filter_table" class="form-control"></select>
								</div>
								<div class="form-group col-xs-12 col-md-4">
									<label for="nombreLote_filter_table" class="control-label">(*) <spring:message code="label.lote" />:</label>
									<select name="lotes.idLote" id="nombreLote_filter_table" class="form-control"></select>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12 col-md-3">
									<label for="refAlbaran_filter_table" class="control-label"><spring:message code="label.refAlbaran1" />:</label>
									<input type="text" id="refAlbaran_filter_table" class="form-control" name="refAlbaran"></input>	
								</div>
							</div>
						</div>
						<div id="actualizarAlbaran_filter_buttonSet" class="pull-right" style="margin:0 1rem 1rem 0">
							<!-- Botón de filtrado -->
							<button id="actualizarAlbaran_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
								<spring:message code="filter" />
							</button>
							<!-- Enlace de limpiar -->
							<a id="actualizarAlbaran_filter_cleanLink_boton" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
						</div>
					</fieldset>
				</form>
			</div>
			<div class="actualizarAlbaran_grid_div horizontal_scrollable_table" >
				<!-- Tabla -->
				<table id="actualizarAlbaran" ></table>
				<!-- Barra de paginación -->
				<div id="actualizarAlbaran_pager"></div>
			</div>
		</div>
	</div>
</div>