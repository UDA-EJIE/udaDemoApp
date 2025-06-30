<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.NivelUsuarioEnum" %>

<div id="divEstudioEstimado">
	<h2><spring:message code="comun.estudioEstimado"/></h2>
	<div id="estudioEstimado_div" class="rup-table-container">
		<div id="estudioEstimado_feedback"></div>						<!-- Feedback de la tabla --> 					<!-- Botonera de la tabla -->
		<div id="estudioEstimado_toolbar" class="mt-1"></div> 
		<div id="estudioEstimadol_filter_div"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="estudioEstimado_filter_form">	
				<div id="estudioEstimado_filter_toolbar" class="formulario_legend" hidden = "hidden"></div>
				<fieldset id="estudioEstimado_filter_fieldset" class="form_fieldset">
					<div class="row">
						<div class="form-group col-lg-2">
							<label for="tipoTareaEstudio_detail_table" class="control-label"><spring:message code="comun.tipoDeTarea" /> (*):</label>
							<div class="divComboW125">
								<select  name="tiposTarea.id015" class="form-control" id="tipoTareaEstudio_detail_table" ></select>
							</div>
						</div>
						<div class="form-group col-md-12 col-lg-3 grupoFechaHora">
							<label class="control-label valFecha" for="fechaInicioEstudio_detail_table"><spring:message code="comun.fechaInicioTarea"/>:</label>
							<input type="text" name="fechaInicio" class="form-control" id="fechaInicioEstudio_detail_table">
							<input type="text" name="horaInicio" class="form-control campohora" id="horaInicioEstudio_detail_table" placeholder="hh:mm" maxlength="5">
						</div>
						<div class="form-group col-md-12 col-lg-3 grupoFechaHora">
							<label class="control-label valFecha" for="fechaFinEstudio_detail_table"><spring:message code="comun.fechaFinTarea"/>:</label>
							<input type="text" name="fechaFin" class="form-control" id="fechaFinEstudio_detail_table">
							<input type="text" name="horaFin" class="form-control campohora" id="horaFinEstudio_detail_table" placeholder="hh:mm" maxlength="5">
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-3 col-xl-2 ">
							<label for="numTotalPal_filter" class="control-label col-md-12 p-0" data-i18n="comun.numPalabrasIZO"><spring:message code="comun.numPalabrasIZO"/>:</label>
								<div class="form-group  col-md-4 col-xl-4 p-0">
									<input type="text" id="numTotalPal_filter" name="datosTareaTrados.numTotalPal" class="form-control">
								</div>
							</div>
							
							<div class="form-group col-md-6 p-0">
								<label class="control-label col-lg-12 p-0" data-i18n="comun.tramosConcordancia"><spring:message code="comun.tramosConcordancia" />:</label>
								<div class="form-group col-lg-3 p-0">
									<label for="numPalConcor084_filter" class="control-label col-md-5 p-0" data-i18n="comun.tramosConcor084"><spring:message code="comun.tramosConcor084" />:</label>
									<div class="form-group  col-md-5 col-xl-3 p-0">
										<input type="text" id="numPalConcor084_filter" name="datosTareaTrados.numPalConcor084" class="form-control no-padding-left">
									</div>
								</div>
								<div class="form-group col-lg-3 p-0">
									<label for="numPalConcor8594_filter" class="control-label col-md-5 p-0" data-i18n="comun.tramosConcor8594"><spring:message code="comun.tramosConcor8594" />:</label>
									<div class="form-group  col-md-5 col-xl-3 p-0">
										<input type="text" id="numPalConcor8594_filter" name="datosTareaTrados.numPalConcor8594" class="form-control no-padding-left">
									</div>
								</div>
								<div class="form-group col-lg-3 p-0">
									<label for="numPalConcor9599_filter" class="control-label col-md-6 p-0" data-i18n="comun.tramosConcor9599"><spring:message code="comun.tramosConcor9599" />:</label>
									<div class="form-group  col-md-5 col-xl-3 p-0">
										<input type="text" id="numPalConcor9599_filter" name="datosTareaTrados.numPalConcor9599" class="form-control no-padding-left">
									</div>
								</div>
								<div class="form-group col-lg-3 p-0">
									<label for="numPalConcor100_filter" class="control-label col-md-6 p-0" data-i18n="comun.tramosConcor100"><spring:message code="comun.tramosConcor100" />:</label>
									<div class="form-group  col-md-5 col-xl-3 p-0">
										<input type="text" id="numPalConcor100_filter" name="datosTareaTrados.numPalConcor100" class="form-control no-padding-left">
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-2">
								<label for="nivelUsuarioEstudio_detail_table" class="control-label"><spring:message code="comun.nivelUsuario" /> (*):</label>
								<div class="divComboW200">
									<select  name="nivUsuario" class="form-control" id="nivelUsuarioEstudio_detail_table" >
										<option value="" selected="true"><spring:message code="combo.todos"/></option>
										<option value="<%=NivelUsuarioEnum.NORMAL.getValue()%>"><spring:message code="<%=NivelUsuarioEnum.NORMAL.getLabel()%>"/></option>
										<option value="<%=NivelUsuarioEnum.VIP.getValue()%>"><spring:message code="<%=NivelUsuarioEnum.VIP.getLabel()%>"/></option>
									</select>
								</div>
							</div>
							<div class="form-group col-lg-2">
								<label for="tipoRelevancia_detail_table" class="control-label"><spring:message code="label.tipoRelevancia" /> (*):</label>
								<div class="divComboW125">
									<select  name="idTipoRelevancia" class="form-control" id="tipoRelevancia_detail_table" ></select>
								</div>
							</div>
						</div>
					<div id="estudioEstimado_filter_buttonSet" class="pull-right" style="margin:0 1rem 1rem 0">
						<button id="estudioEstimado_button" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
							<spring:message code="comun.calcular" />
						</button>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="estudioEstimado_grid_div horizontal_scrollable_table" >
			<!-- Tabla -->
			<table id="estudioEstimado" ></table>
			<!-- Barra de paginación -->
			<div id="estudioEstimado_pager"></div>
		</div>
	</div>
</div>