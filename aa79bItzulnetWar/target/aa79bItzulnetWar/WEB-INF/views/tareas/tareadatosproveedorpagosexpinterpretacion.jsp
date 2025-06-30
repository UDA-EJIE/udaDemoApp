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
		<form id="ejecutarTareaDialog_form">
			<input type="hidden" name="numExp" id="numExp_form" >
			<input type="hidden" name="anyo" id="anyo_form" >
			<input type="hidden" name="idTarea" id="idTarea_form" >
			<input type="hidden" name="tipoTarea.id015" id="idTipoTarea_form" >
			<input type="hidden" name="ejecucionTareas.horasTarea" id="horasTarea_form" >
					
			<fieldset>
				<legend>
					<spring:message code="label.datosTareaInterpretacion" />
				</legend>
				<div class="row">

					<div class="form-group col-xs-6 col-lg-6 col-md-6">
						<label for="tipoEntidadSolicitanteDesc"
							class="form-group col-xs-7 col-lg-7 col-md-7 noNegrita"
							id="divEjecutarTareaDatosProv"><spring:message
								code="comun.fechaHoraInicioPrevista" />:</label> <label
							id="fechaInicio_form"></label> <label id="horaInicio_form"></label>

					</div>

					<div class="form-group col-xs-6 col-lg-6 col-md-6">
						<label for="fechaFin_form"
							class="form-group col-xs-7 col-lg-7 col-md-7 noNegrita"><spring:message
								code="comun.fechaHoraFinalizacion" />:</label> <label
							id="fechaFin_form"></label> <label id="horaFin_form"></label>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-xs-12 col-lg-12 col-md-12">
						<label for="asignadoA_form"
							class="noNegrita form-group col-xs-3 col-lg-3 col-md-3"
							id="divEjecutarTareaDatosProv"><spring:message
								code="comun.asignadaA" />:</label> <label id="asignadoA_form"
							style="margin-left: 27px;"></label>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-xs-6 col-lg-6 col-md-6">
						<label for="tipoEntidadSolicitanteDesc"
							class="noNegrita form-group col-xs-7 col-lg-7 col-md-7"
							id="divEjecutarTareaDatosProv"><spring:message
								code="label.horaPrevista" />:</label><label id="horasPrevistas_form"></label>
					</div>
					<div class="form-group col-xs-6 col-lg-6 col-md-6">
						<label for="tipoEntidadSolicitanteDesc"
							class="noNegrita form-group col-xs-7 col-lg-7 col-md-7"><spring:message
								code="label.horasRealesDedic" />:</label> <label id="horasReales_form"></label>
					</div>
				</div>
			</fieldset>



			<!-- Comienzan campos editables -->
			<div class="row">
				<div class="col-xs-7 no-padding"></div>
				<div class="form-group col-xs-5 no-padding" style=" margin-top: 5px;">
					<label for="importeBase_form" class="control-label col-xs-6 noNegrita txtDcha"><spring:message code="label.importeBase" />(*):</label> 
					<div class="col-xs-6 no-padding">
						<input type="text" name="datosPagoProveedoresInt.importeBase" class="decimal form-control" id="importeBase_form" maxlength="9" data-decim="2" />
					</div>
					
				</div>
			</div>
			<div class="row">
				<div class="col-xs-8 no-padding"></div>
				<div class="form-group col-xs-4 no-padding">
					<label for="ivaValor_form" class="control-label col-xs-4 noNegrita txtDcha"><spring:message code="label.iva" />(*):</label> 
					<div class="col-xs-3">
						<input type="text" name="datosPagoProveedoresInt.porIva" class="form-control numeric" id="ivaTantoPorCiento_form" maxlength="3" /> 
					</div>
					<div class="col-xs-5 no-padding">
						<input type="text" name="datosPagoProveedoresInt.importeIva" class="decimal form-control" id="ivaValor_form" maxlength="9" data-decim="2" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-8 no-padding"></div>
				<div class="form-group col-xs-4 no-padding"> 
					<label for="total_form" class="noNegrita control-label col-xs-6 txtDcha"><spring:message code="label.total" />:</label> 
					<div class="col-xs-6 no-padding">
						<input type="text" name="datosPagoProveedoresInt.importeTotal" class="decimal form-control col-xs-5" id="total_form" maxlength="10" data-decim="2" readonly="readonly" />
					</div>
				</div>
			</div>
		</form>
	</div>
</div>