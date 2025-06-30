<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<div id="divRequerirSubsanacionDetalle">
	<div id="requerirSubsanacionDetalleDiv" class="container-fluid">
		<h2><spring:message code="menu.requerirSub"></spring:message></h2>
		<hr class="mb-2">
		<div class="rup-table-container">
			<div id="requerirSubsanacionDetalle_feedback"></div>						
			<div id="requerirSubsanacionDetalle_toolbar"></div>	
			<div id="contenFormularios" class="rup-table-filter">
				<form id="requerirSubsanacionDetalle_filter_form" class="form-horizontal">
					<div id="requerirSubsanacionDetalle_filter_toolbar"
						class="formulario_legend filterCabecera"></div>
					<fieldset id="requerirSubsanacionDetalle_filter_fieldset"
						class="rup-table-filter-fieldset filterCuerpo">
						<div class="p-2">
							<div class="row">
								<div class="form-group col-md-12 col-lg-5 grupoFechaHora">
									<label class="control-label valFecha" for="detFechaLimite" data-i18n="label.fechaLimite"><spring:message code="label.fechaLimite" /> (*):</label> 
									<input type="text" name="fechaLimite" class="form-control" id="detFechaLimite"> 
									<input type="text" name="horaLimite" class="form-control campohora" id="detHoraLimite" placeholder="hh:mm" maxlength="5">
								</div>
								<div class="form-group col-md-12 col-lg-2">
									<label for="permitirDocs" class="control-label "><spring:message code="label.permiteNuevaDocu"/>:</label>
									<div>
										<input type="checkbox" name="indDocNuevos" class="form-control"
											id="permitirDocs" value="S" data-switch-pestana="true" />
									</div>
								</div>
							</div>
							<div class="row" style="margin-top: 10px;">
								<fieldset id="docuAsociados_fieldset" class="rup-table-filter-fieldset col-xs-12 col-md-5" style="height: 118px;">
									<legend>
										<spring:message code="label.docuAsociados" />
										<input type="text" style="width:0px;visibility:hidden;" id="docusSelect" name="docusSelect" />
									</legend>
									<div id="docuAsociados" class="subsanacionTree" style="height: 90px;">
										<div id="listaDocuAsociados">
											<ul>
											</ul>
										</div>
									</div>
								</fieldset>
								<div class="form-group col-xs-12 col-md-6">
									<label class="control-label" for="detSubsanacion" data-i18n="label.detalleSub"><spring:message code="label.detalleSub"/> (*):</label>
									<textarea class="form-control resizable-textarea" name="detSubsanacion" id="detSubsanacion" rows="6" maxlength="4000" ></textarea>
								</div>
							</div>
							<div class="row">
								<fieldset id="tareas_fieldset" class="rup-table-filter-fieldset">
								<legend>
									<spring:message code="label.infoTareaSubsana" />
								</legend>
									<div class="p-2">
										<input type="hidden" id="fechaFinalIzo" name="fechaFinalIzo" >
										<input type="hidden" id="horaFinalIzo" name="horaFinalIzo" >
										<div class="form-group col-md-12 col-lg-3 grupoFechaHora">
											<label class="control-label valFecha" for="fechaIni"><spring:message
													code="comun.fechaInicioTarea" /> (*):</label> <input type="text"
												name="tarea.fechaIni" class="form-control" id="fechaIni"> <input
												type="text" name="tarea.horaIni" class="form-control campohora"
												id="horaIni" placeholder="hh:mm" maxlength="5">
										</div>
										<div class="form-group col-md-12 col-lg-3 grupoFechaHora">
											<label class="control-label valFecha" for="fechaFin"><spring:message
													code="comun.fechaFinTarea" /> (*):</label> <input type="text"
												name="tarea.fechaFin" class="form-control" id="fechaFin"> <input
												type="text" name="tarea.horaFin" class="form-control campohora"
												id="horaFin" placeholder="hh:mm" maxlength="5">
										</div>
										<div class="form-group col-md-12 col-lg-2">
											<label for="horasPrevistas"
												class="control-label" data-i18n="label.horaPrevista"><spring:message
													code="label.horaPrevista" /> (*):</label>
<!-- 													<div class="form-group"> -->
												<input type="text" id="horasPrevistas" name="tarea.horasPrevistas"
													class="form-control col-40por " placeholder="hh:mm" maxlength="5" >
<!-- 													</div> -->
										</div>
										<div class="form-group col-xs-12 col-md-12">
											<label class="control-label" for="observaciones" data-i18n="label.observaciones"><spring:message code="label.observaciones"/> (*):</label>
											<textarea class="form-control resizable-textarea" name="tarea.observaciones" id="observaciones" rows="6" maxlength="4000" ></textarea>
										</div>
									</div>
								</fieldset>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</div>