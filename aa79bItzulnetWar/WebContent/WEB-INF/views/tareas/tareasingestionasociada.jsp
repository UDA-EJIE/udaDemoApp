<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in aa79b-content" id="divEjecutarTareaCapa">
	<div id="tareasSinGestion_div">
		<div id="ejecutarTareaDialog_toolbar"></div>
		<div id="ejecutarTareaDialog_feedback"></div>
				<div class="row margen1TB">
			<div class="col-xs-3">
				<label class="control-label"><spring:message code="comun.tipoDeTarea" />:</label>
			</div>
			<div class="col-xs-9">
				<b><c:out value="${descripcionTarea}"></c:out></b>
			</div>
		</div>		

		<form id="ejecutarTareaDialog_form">
			<input type="hidden" name="numExp" id="numExp_form" >
			<input type="hidden" name="anyo" id="anyo_form" >
			<input type="hidden" name="idTarea" id="idTarea_form" >
			<input type="hidden" name="tipoTarea.id015" id="idTipoTarea_form" >
			<input type="hidden" name="ejecucionTareas.horasTarea" id="horasTarea_form" >
						
			<div class="row">
				<div class="form-group col-md-6 col-xl-6">
					<label for="tarea_realizada_filter" class="col-xs-12 noNegrita" data-i18n="label.enVigor"><spring:message code="comun.seHaRealizTarea" /></label>
					<div class="col-xs-12">
						<input type="checkbox" name="ejecucionTareas.indRealizada" class="form-control" id="tarea_realizada_filter" value="S" data-switch-pestana="true" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="form-group ">
						<label for="contenido_form" class="col-xs-12 noNegrita control-label" data-i18n="label.observaciones"><spring:message code="label.observaciones" />:</label>
						<div class="col-xs-12">
							<textarea name="observacionesTareas.obsvEjecucion" id="contenido_form" class="form-control col-xs-12" style="height: 80px;"></textarea>
						</div>
					</div>

				</div>
			</div>
		</form>
	</div>
</div>