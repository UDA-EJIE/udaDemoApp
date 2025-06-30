<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in aa79b-content" id="divEjecutarTareaCapa">
	<div id="tareaNoConformidadCliente_div">
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
		<div id="usuariosEjecTareasPrev" class="row margen1TB oculto">
			<div class="col-xs-12">
				<label id="usuariosEjecTareasPrevLabel" class="errorColor"></label>
			</div>
		</div>
		<form id="ejecutarTareaDialog_form">
			<input type="hidden" name="numExp" id="numExp_form" >
			<input type="hidden" name="anyo" id="anyo_form" >
			<input type="hidden" name="idTarea" id="idTarea_form" >
			<input type="hidden" name="tipoTarea.id015" id="idTipoTarea_form" >
						
			<div class="row">
				<div class="col-xs-5 no-padding">
					<div class="form-group">
						<label for="tarifaEsEu_detail_table" class="col-xs-12 noNegrita control-label" data-i18n="horasRealesejec"><spring:message code="label.horasRealesejec" /> (hh:mm) (*):</label>
						<div class="col-xs-12">
							<input type="text" name="ejecucionTareas.horasTarea" class="form-control inline campohora10" id="horasRealesTarea_form" maxlength="10" style="width:100px" placeholder="hh:mm" />
							<c:if test="${tipoTarea == 8}">
								<span class="input-group-link oculto" id="linkObservaciones"><a href="#" id="observaciones_link" class=""><spring:message code="label.verObservaciones"/></a></span>
							</c:if>
						</div>
					</div>
				</div>
				<c:if test="${tipoTarea == 8}">
					<div class="col-xs-5 no-padding">
						<div class="form-group">
							<label for="porUsoEu_detail_table" class="col-xs-12 noNegrita control-label" data-i18n="porUsoEuskera"><spring:message code="label.porUsoEuskera" /> (*):</label>
							<div class="col-xs-12">
							<input type="text" name="ejecucionTareas.porUsoEuskera" class="form-control numeric porcentaje" id="porUsoEu_detail_table" maxlength="3" />
							</div>
						</div>
					</div>
				</c:if>
			</div>
		</form>
	</div>
</div>