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
			<input type="hidden" name="ejecucionTareas.horasTarea" id="horasTarea_form">

			<fieldset>
				<legend>
					<label for="id018_filter_table" class="col-xs-12 noNegrita" data-i18n="label.infoInterpretacion"><spring:message code="label.infoInterpretacion" /></label>
				</legend>
				<div class="col-xs-12">
					<div class="col-xs-6">
						<div class="col-xs-12">
							<label for="id018_filter_table" class="col-xs-5 noNegrita" data-i18n="comun.fechaHoraInicio"><spring:message code="comun.fechaHoraInicio" />:</label> 
							<label id="fechaIni_form" class="col-xs-4"></label> 
							<label id="horaIni_form" class="col-xs-3"></label>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="col-xs-12">
							<label for="id018_filter_table" class="col-xs-5 noNegrita" data-i18n="comun.fechaHoraFin"><spring:message code="comun.fechaHoraFin" />:</label> 
							<label id="fechaFin_form" class="col-xs-4"></label> 
							<label id="horaFin_form" class="col-xs-3"></label>
						</div>
					</div>
				</div>
				<br />
			</fieldset>
			
			
			
			
			<div class="p-2">
				<div class="col-xs-12">
					<div class="col-xs-6">
						<div class="col-xs-12">
							<label for="numInterpretes_form" class="col-xs-12 noNegrita" data-i18n="label.numInterpretes"><spring:message code="label.numInterpretes" /> (*):</label>
							<div class="col-xs-6">
								<input type="text" name="gestionInterpretacion.numInterpretes" class="form-control numeric col-lg-12" id="numInterpretes_form" maxlength="2" />
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="col-xs-12">
							<label for="horasPrevistasInterpretacion_form" class="col-xs-12 noNegrita" data-i18n="comun.horaPrevInterpretacion"><spring:message code="comun.horaPrevInterpretacion" /> (*):</label>
							<div class="col-xs-6">
								<input type="text" name="gestionInterpretacion.horasPrevistasInterpretacion" class="form-control col-lg-4 campohora10" id="horasPrevistasInterpretacion_form" maxlength="10" placeholder="hh:mm"/>
							</div>
						</div>
					</div>
				</div>
			</div>
				
			<div id="presupuesto_div">
				<fieldset id="presupuesto_filter_fieldset">
					<legend style="font-size: 14px;">
						<label for="id018_filter_table" class="col-xs-12 noNegrita" data-i18n="label.presupuesto"><spring:message code="label.presupuesto" />:</label>
					</legend>
					<div class="row">
						<div class="col-xs-3">
							<div class="form-group" id="checkPresupuesto">
								<label for="checkReqPresupuesto" class="col-xs-12 noNegrita" data-i18n="label.requierePresupuesto"><spring:message code="label.requierePresupuesto" />:</label> 
								<input type="checkbox" name="indPpto" class="form-control" id="checkReqPresupuesto" value="S" data-switch-pestana="true" disabled />
								<label id="enlaceVerPresupuesto" class="input-group-link" style="margin-left: 10px; display: none;"><a href="#" onclick="verPresupuesto()"><spring:message code="label.ver" /></a></label>
							</div>
						</div>
						<div class="col-xs-2">
							<div class="form-group">
								<label for="importe_form" class="col-xs-12 noNegrita" data-i18n="label.importe"><spring:message code="label.importe" />:</label> 
								<input type="text" name="gestionInterpretacion.presupuesto" class="form-control col-xs-5 decimal" id="importe_form"  maxlength="9" data-decim="2" disabled/>
							</div>
						</div>
						<div class="col-xs-2">
							<div class="form-group">
								<label for="checkVisible" class="col-xs-12 noNegrita" data-i18n="label.documento.visible"><spring:message code="label.documento.visible" />:</label> 
								<input type="checkbox" name="gestionInterpretacion.indVisible" class="form-control" id="checkVisible" value="S" data-switch-pestana="true" />
							</div>
						</div>
						<div class="form-group col-xs-4 grupoFechaHora">
							<label class="control-label valFecha noNegrita" for="fechaLimite"><spring:message code="label.fechaHoraLimiteAceptacion" /> (*):</label> 
							<input type="text" name="subsanacion.fechaLimite" class="form-control" id="fechaLimite"> 
							<input type="text" name="subsanacion.horaLimite" class="form-control campohora" id="horaLimite" placeholder="hh:mm" maxlength="5">
						</div>
					</div>
				</fieldset>
			</div>
		</form>
		<input type="hidden" id="fechaLimSeleccionable"/>
		<input type="hidden" id="horaLimSeleccionable"/>
		<input type="hidden" id="minFechaLimiteAceptacion"/>
		<input type="hidden" id="minHoraLimiteAceptacion"/>
	</div>
</div>