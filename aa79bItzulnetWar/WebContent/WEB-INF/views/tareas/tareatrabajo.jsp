<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in aa79b-content" id="divEjecutarTareaCapa">
	<div id="tareasSinGestion_div">
		<div id="ejecutarTareaTrabajoDialog_toolbar"></div>
		<div id="ejecutarTareaTrabajoDialog_feedback"></div>
				<div class="row margen1TB">
			<div class="col-xs-3">
				<label class="control-label"><spring:message code="comun.tipoDeTarea" />:</label>
			</div>
			<div class="col-xs-9">
				<b><c:out value="${descripcionTarea}"></c:out></b>
			</div>
		</div>		

		<form:form id="ejecutarTareaTrabajoDialog_form" modelAttribute="datEjecTarea">
			<form:input type="hidden" path="idTarea" id="idTarea_ejec_tar_trabajo_form"></form:input>
			<form:input type="hidden" path="trabajo.idTrabajo" id="idTrabajo_ejec_tar_trabajo_form"></form:input>
			<form:input type="hidden" path="tipoTarea.id015" id="idTipoTarea_ejec_tar_trabajo_form"></form:input>
			<form:input type="hidden" path="horasTarea" id="horasTarea_form"></form:input>
						
			<div class="row">
				<div class="form-group col-md-12 col-xl-12">
					<label for="observaciones_ejec_tar_trabajo_form" class="col-xs-12 noNegrita" data-i18n="label.observaciones"><spring:message code="label.observaciones" /></label>
					<div class="col-xs-12">
						<form:textarea path="observacionesTareas.obsvEjecucion" class="form-control" id="observaciones_ejec_tar_trabajo_form" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="form-group ">
						<label id="labelIdFicheroSalida_ejec_tar_trabajo_form" for="idFicheroSalida_ejec_tar_trabajo_form" class="col-xs-12 noNegrita control-label" data-i18n="label.documento"><spring:message code="label.documento" />:</label>
						<div class="col-xs-12">
							<form:input type="hidden" id="idFicheroSalida_ejec_tar_trabajo_form" path="documentoSalida.idFichero" />
							<form:input type="hidden" id="nombreSalida_ejec_tar_trabajo_form" path="documentoSalida.nombre" />
							<form:input type="hidden" id="tituloSalida_ejec_tar_trabajo_form" path="documentoSalida.titulo" />
							<form:input type="hidden" id="indEncriptadoSalida_ejec_tar_trabajo_form" path="documentoSalida.encriptado"/>
							<form:input type="hidden" id="oidSalida_ejec_tar_trabajo_form" path="documentoSalida.oid"/>
							<span id="enlaceDescargaDetalle_2" style="display:contents;"></span> 
							<div id="capaBtnPID" class="inline flotaIzda">
								<button id="pidButton_ficheroSubida" type="button" class="ui-button ui-corner-all ui-widget no-obligatorio" style="margin-top:-4px; height:22px"><spring:message code="label.adjuntarFichero"/></button>
							</div>
							<div class="docSalidaTareaTrabajo flotaIzda pl10"></div>
						</div>
					</div>
				</div>
			</div>
		</form:form>
		<div id="subidaFicheroPid" class="capaPIDenPestanaDoc oculto">
			<fieldset id="subidaFicheroPid_fieldset" >			
				<div id="subidaFicheroPid_feedback"></div>
				<form id="subidaFicheroPidTareaTrabajo_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
					<div class="form-group row">
						<label for="fichero" class="control-label col-md-3"><spring:message code="label.fichero" /> (*):</label>
						<div class="col-md-6" id="divFichero">
							<input type="file" name="fichero" id="fichero"  class="form-control">
						</div>
						<input type="hidden" id="idBotonUpload" name="idBotonUpload"/>
						<input type="hidden" id="reqEncriptado" name="reqEncriptado" />
					</div>
				</form>
				<p class="scheduler-border" style="text-align: center;" id="txtEncriptado"><em><strong><spring:message code="mensaje.fichero.encriptadoRequerido" /></strong></em></p>
			</fieldset>
		</div>
	</div>
</div>