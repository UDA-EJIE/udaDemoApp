<%@page import="com.ejie.aa79b.model.enums.TipoPeticionarioEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="divExpedienteInterpretacion">
	<fieldset id="datos_expediente_interpretacion_filter_fieldset">
		<legend>
			<spring:message code="label.datosExpedienteInterpretacion"></spring:message>
		</legend>
		<input type="hidden" id="dirNora" name="expedienteInterpretacion.dirNora.dirNora">
		<input type="hidden" id="noraId" name="expedienteInterpretacion.dirNora.codNora">
		<input type="hidden" id="tipoNora" name="expedienteInterpretacion.dirNora.tipoNora">
		<input type="hidden" id="paisId" name="expedienteInterpretacion.dirNora.pais.id">
		<input type="hidden" id="provinciaId" name="expedienteInterpretacion.dirNora.provincia.id">
		<input type="hidden" id="municipioId" name="expedienteInterpretacion.dirNora.municipio.id">
		<input type="hidden" id="localidadId" name="expedienteInterpretacion.dirNora.localidad.id">
		<input type="hidden" id="calleId" name="expedienteInterpretacion.dirNora.calle.id">
		<input type="hidden" id="portalId" name="expedienteInterpretacion.dirNora.portal.id">
		<input type="hidden" id="provincia" name="expedienteInterpretacion.dirNora.provincia.dsO"/>
		<input type="hidden" id="municipio"/>
		<input type="hidden" id="localidad" name="expedienteInterpretacion.dirNora.localidad.dsO"/>
		<input type="hidden" id="pais"/>
		<input type="hidden" id="calle" name="expedienteInterpretacion.dirNora.calle.dsO"/>
		<input type="hidden" id="portal" name="expedienteInterpretacion.dirNora.portal.txtPortal"/>
		<input type="hidden" id="cp" name="expedienteInterpretacion.dirNora.codPostal"/>
		<input type="hidden" id="escalera" name="expedienteInterpretacion.dirNora.escalera"/>
		<input type="hidden" id="piso" name="expedienteInterpretacion.dirNora.piso"/>
		<input type="hidden" id="mano" name="expedienteInterpretacion.dirNora.mano"/>
		<input type="hidden" id="puerta" name="expedienteInterpretacion.dirNora.puerta"/>
		<input type="hidden" id="aproxPostal" name="expedienteInterpretacion.dirNora.aprox"/>
		<div class="row">
			<div class="form-group col-lg-2">
				<label class="control-label" for="modoInterpretacion"><spring:message code="label.modoInterpretacion"/> (*):</label>
				<div class="divComboModoInterpretacion">
					<select name="expedienteInterpretacion.modoInterpretacion" id="modoInterpretacion" class="form-control">
					</select>
				</div>
			</div>
			<div class="form-group col-lg-2">
				<label class="control-label" for="tipoActo"><spring:message code="label.tipoActo"/> (*):</label>
				<div class="divComboTipoActo">
					<select name="expedienteInterpretacion.tipoActo" id="tipoActo" class="form-control">
					</select>
				</div>
			</div>
			<div class="form-group col-lg-3">
				<label class="control-label" for="tipoPeticionario"><spring:message code="label.tipoPeticionario"/> (*):</label>
				<div class="divComboTipoPeticionario">
					<select name="expedienteInterpretacion.tipoPeticionario" id="tipoPeticionario" class="form-control">
						<option value=""><spring:message code="combo.seleccione"/></option>
						<option value="<%=TipoPeticionarioEnum.ADMIN_PUBLICA.getValue()%>"><spring:message code="<%=TipoPeticionarioEnum.ADMIN_PUBLICA.getLabel()%>"/></option>
						<option value="<%=TipoPeticionarioEnum.PARTICULAR.getValue()%>"><spring:message code="<%=TipoPeticionarioEnum.PARTICULAR.getLabel()%>"/></option>
					</select>
				</div>
			</div>
			<div class="form-group col-lg-3">
				<label class="control-label" for="indProgramada"><spring:message code="label.interpretacionProgramada"/>:</label>
				<input type="checkbox" name="expedienteInterpretacion.indProgramada" id="indProgramada" value="S" data-switch-pestana="true">
			</div>
			<div class="form-group col-lg-1">
				<label class="control-label col-100por" for="indPresupuesto"><spring:message code="label.presupuesto"/>:</label>
				<input type="checkbox" name="expedienteInterpretacion.indPresupuesto" id="indPresupuesto" value="S" data-switch-pestana="true">
			</div>
		</div>
		<div class="row">
            <div class="form-group col-md-12 col-lg-5 grupoFechaHora grupoFechaHoraInter">
                <label class="control-label valFecha" for="fechaIni"><spring:message code="label.fechaHoraIniInterpretacion"/> (*):</label>
                <input type="text" name="expedienteInterpretacion.fechaIni" class="form-control" id="fechaIni">
                <input type="text" name="expedienteInterpretacion.horaIni" class="form-control campohora" id="horaIni" placeholder="hh:mm" maxlength="5">
            </div>
            <div class="form-group col-md-12 col-lg-5 grupoFechaHora grupoFechaHoraFinInter">
                <label class="control-label valFecha" for="fechaFin"><spring:message code="label.fechaHoraFinInterpretacion"/> (*):</label>
                <input type="text" name="expedienteInterpretacion.fechaFin" class="form-control" id="fechaFin">
                <input type="text" name="expedienteInterpretacion.horaFin" class="form-control campohora" id="horaFin" placeholder="hh:mm" maxlength="5">
            </div>
            
        </div>
		<div class="row">
			<div class="form-group col-lg-10">
				<label class="control-label" for="lugarInterpretacion"><spring:message code="label.lugarInterpretacion"/> (*):</label>
				<textarea name="expedienteInterpretacion.dirNora.txtdirec" class="form-control resizable-textarea" id="lugarInterpretacion" rows="1" cols="9" readonly="readonly" disabled="disabled"></textarea>
			</div>
			<div class="form-group col-lg-2">
				<div id="buscar_direccion_nora_div" class="link">
					<a id="buscar_direccion_nora" href="#" class="rup-enlaceCancelar izda enlace"><spring:message code="label.buscarDireccion" /></a>
				</div>
			</div>
		</div>
		
		<fieldset>
			<legend><spring:message code="label.contacto"></spring:message></legend>
				<div class="row">
				<div class="form-group col-lg-4">
					<label class="control-label" for="personaContacto"><spring:message code="label.nombreapellidos"/>:</label>
					<input type="text" name="expedienteInterpretacion.personaContacto" class="form-control" id="personaContacto" maxlength="125">
				</div>
				<div class="form-group col-lg-3">
					<label class="control-label" for="emailContacto"><spring:message code="label.email"/>:</label>
					<input type="text" name="expedienteInterpretacion.emailContacto" class="form-control email" id="emailContacto" maxlength="250">
				</div>
				<div class="form-group col-lg-2">
					<label class="control-label" for="telefonoContacto"><spring:message code="label.telefono"/>:</label>
					<input type="text" name="expedienteInterpretacion.telefonoContacto" class="form-control col-66por" id="telefonoContacto" maxlength="15">
				</div>
				</div>
		</fieldset>
		
		<div class="row">
			<div class="form-group col-lg-9">
				<label class="control-label" for="observacionesExpInter"><spring:message code="label.observaciones"/>:</label>
				<textarea name="expedienteInterpretacion.observaciones.observaciones" class="form-control resizable-textarea" id="observacionesExpInter" rows="1" cols="9" maxlength="4000"></textarea>
			</div>
		</div>
	</fieldset>
</div>