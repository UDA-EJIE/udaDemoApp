<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.AccionesEnum"%>
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<div id="divConsultaRegistroAcciones">
	<div id="consultaRegistroAccionesGeneralDiv" class="container-fluid">
		<h2><spring:message code="menu.consultaRegistroAcciones"></spring:message></h2>
		<div class="rup-table-container">
			<div id="consultaRegistroAcciones_feedback"></div>						
			<div id="consultaRegistroAcciones_toolbar"></div>	
			<div id="contenFormularios" class="rup-table-filter">
				<form id="consultaRegistroAcciones_filter_form" class="form-horizontal">
					<div id="consultaRegistroAcciones_filter_toolbar"
						class="formulario_legend filterCabecera"></div>
					<fieldset id="consultaRegistroAcciones_filter_fieldset"
						class="rup-table-filter-fieldset filterCuerpo">
						<div class="p-2">
							<div class="row">
								<div class="form-group col-xs-12 col-md-3">
									<label for="usuario_filter_table" class="control-label "><spring:message code="label.usuario"/>:</label>
									<input type="text" name="usuarioRegistro" class="form-control" id="usuario_filter_table" maxlength="15"/>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-12 col-lg-3 grupoFechaHora ">
									<label class="control-label valFecha" data-i18n="label.fechaRegistro"><spring:message code="label.fechaRegistro" />-<spring:message code="label.desde" />:</label>
									<div>
										<input type="text" id="fechaRegistroDesde_filter" name="fechaRegistroDesde"  class="form-control"  >
										<input type="text" id="horaRegistroDesde_filter" name="horaRegistroDesde"  maxlength="5" placeholder="hh:mm" class="form-control campohora">
									</div>
								</div>
								<div class="form-group col-md-12 col-lg-3 grupoFechaHora ">
									<label class="control-label valFecha" data-i18n="label.fechaRegistro"><spring:message code="label.fechaRegistro" />-<spring:message code="label.hasta" />:</label>
									<div>
										<input type="text" id="fechaRegistroHasta_filter" name="fechaRegistroHasta"  class="form-control" >
										<input type="text" id="horaRegistroHasta_filter" name="horaRegistroHasta" maxlength="5" placeholder="hh:mm" class="form-control campohora">
									</div>
								</div>
<!-- 							</div> -->
<!-- 							<div class="row"> -->
								<div class="form-group col-xs-12 col-md-4">
									<label for="ptoAplicacion_filter_table" class="control-label no-padding-left"><spring:message code="label.ptoAplicacion"/>:</label>
									<div class="col-xs-12 no-padding-left">
										<select  name="idPuntoMenu" class="form-control" id="ptoAplicacion_filter_table" ></select>
									</div>	
								</div>
								<div class="form-group col-xs-12 col-md-2">
									<label for="accion_filter_table" class="control-label "><spring:message code="label.accion"/>:</label>
									<select name="idAccion" class="form-control" id="accion_filter_table">
										<option value=""><spring:message code="combo.todos"/></option>
										<option value="<%=AccionesEnum.ALTA.getValue()%>"><spring:message code="<%=AccionesEnum.ALTA.getLabel()%>"/></option>
										<option value="<%=AccionesEnum.MODIFICACION.getValue()%>"><spring:message code="<%=AccionesEnum.MODIFICACION.getLabel()%>"/></option>
										<option value="<%=AccionesEnum.BORRADO.getValue()%>"><spring:message code="<%=AccionesEnum.BORRADO.getLabel()%>"/></option>
										<option value="<%=AccionesEnum.MANUAL.getValue()%>"><spring:message code="<%=AccionesEnum.MANUAL.getLabel()%>"/></option>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-lg-12 no-padding">
									<fieldset>
										<legend>
											<spring:message code="label.expedienteAfectado"/>
										</legend>
										<div class="row">
											<div class="form-group col-xs-12 col-md-2">
												<label for="idTipoExpediente_filter_table" class="control-label "><spring:message code="label.tipoExpediente"/>:</label>
												<div class="divComboW125">
													<select name="idTipoExpediente" class="form-control" id="idTipoExpediente_filter_table">
														<option value="" selected="true"><spring:message code="combo.todos"/></option>
														<option value="<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.INTERPRETACION.getLabel()%>"/></option>
														<option value="<%=TipoExpedienteEnum.TRADUCCION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.TRADUCCION.getLabel()%>"/></option>
														<option value="<%=TipoExpedienteEnum.REVISION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.REVISION.getLabel()%>"/></option>
													</select>
												</div>
											</div>
											<div class="form-group col-xs-12 col-md-3">
												<label for="numExp_filter_table" class="control-label"><spring:message code="label.numeroExpediente"/>:</label>
												<div>
													<label for="anyo_filter_table" class="control-label oculto"><spring:message code="label.anyo"/></label>
													<input type="text" name="anyo" class=" numeric" style="width: 40px;" id="anyo_filter_table" maxlength="2"/>
													<label class="control-label" style="width: 3%;">/</label>
													<input type="text" name="numExp" class=" numeric" style="width: 75px;" id="numExp_filter_table" maxlength="6"/>
												</div>
											</div>
											<div class="form-group col-lg-2 divComboW125" id="divIndConfidencial" >
												<label class="control-label col-xs-12 no-padding-left" for="indConfidencial"><spring:message code="label.indConfidencial"/>:</label>
												<select  name="expedienteTradRev.indConfidencial" id="indConfidencial">
													<option value="" selected="true"><spring:message code="combo.todos"/></option>
													<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
													<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
												</select>
											</div>
										</div>
									</fieldset>
									</div>
								</div>
							<div id="consultaRegistroAcciones_filter_buttonSet" class="pull-right"
								style="margin: 0 1rem 1rem 0">
								<button id="consultaRegistroAcciones_filter_filterButton"
									type="button"
									class="ui-button ui-widget ui-state-default ui-corner-all">
									<spring:message code="filter" />
								</button>
								<a id="consultaRegistroAcciones_filter_cleanLink"
									href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message
										code="clear" /></a>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
			<div class="consultaRegistroAcciones_grid_div horizontal_scrollable_table" >
				<!-- Tabla -->
				<table id="consultaRegistroAcciones" ></table>
				<!-- Barra de paginación -->
				<div id="consultaRegistroAcciones_pager"></div>
			</div>
		</div>
		<div id="detalleAccion_dialog" class="rup-table-formEdit-detail">
			<div id="detalleAccion_detail_navigation"></div>
			<!-- Barra de navegación del detalle -->
			<div class="ui-dialog-content ui-widget-content">
				<form id="detalleAccion_form">
					<div class="row" style="padding-left:5px;">
						<div class="col-xs-12 no-padding-left">
							<textarea class="form-control resizable-textarea" id="detalleAccion_detail_table" rows="1" cols="9" disabled ></textarea>
						</div>	
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

