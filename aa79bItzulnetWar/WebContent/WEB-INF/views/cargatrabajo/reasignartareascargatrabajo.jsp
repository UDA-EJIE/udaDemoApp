<%@include file="/WEB-INF/includeTemplate.inc"%>

<div  id="divReasignarTareasCargaTrabajo">
		<h2>
			<spring:message code="comun.reasignacionDeTareas" />
		</h2>
		<div id="reasignarTareasCargaTrabajo_div" class="row">
			<div id="reasignarTareasCargaTrabajo_feedback"></div>
			<div id="reasignarTareasCargaTrabajo_toolbar"></div>
			<div id="reasignarTareasCargaTrabajo_filter_div">
				<form id="reasignarTareasCargaTrabajoform">
				<fieldset id="reasignarTareasCargaTrabajo_fieldset" class="form_fieldset">
					<div class="p-2">
					<div class="row">
						<input type="hidden" id="tipoEntidadCargTrab" name="entidad.tipo"/>
						<input type="hidden" id="idEntidadCargTrab" name="entidad.codigo"/>
						<input type="hidden" id="dniReasignacionCargTrab" name="dni"/>
						<input type="hidden" id="tareaTrabajoCargTrab" name="tareaTrabajo" value="${tareaTrabajo}"/>
						<div class="form-group col-md-8 col-xl-8 ">
							<label for="asignadoA" class="control-label col-md-12 p-0" data-i18n="label.asignadoA"><spring:message code="label.asignadoA"/> (*):</label>
							 <div class="input-group">
								<input type="text" id="asignadoACargTrab" name="nombre" class="form-control" disabled="disabled" readOnly="readonly"/>
		                    	<span class="input-group-addon autoasignarLink_div" id="divLinkAutoasignarCargTrab"><a href="#" id="autoasignarCargTrabLink" class="linkReasignacion"><spring:message code="label.autoasignar"/></a></span>
		                   	 	<span class="input-group-addon personalIzoLink_div" id="divLinkRecursoInternoCargTrab"><a href="#" id="personalIzoCargTrabLink" class="linkReasignacion"><spring:message code="label.personalIzo"/></a></span>
							</div>
						</div>
					</div>
					</div>
					<div>
						<legend>(*) <spring:message code="label.tareasEjecutadaNoReasignable"/></legend>
					</div>
				</fieldset>
				</form>
			</div>
		</div>
		<div id="buscadorIZOCargTrab" class="oculto"></div>
</div>