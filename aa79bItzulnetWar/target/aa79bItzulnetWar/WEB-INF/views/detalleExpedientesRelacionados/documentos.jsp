<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="divDocumentosExpediente">
	<fieldset id="documentos_expediente_trad_rev_filter_fieldset">
		<legend>
			<spring:message code="label.documentos"></spring:message>
		</legend>
	
		<div id ="documentosexpediente_detail_navigation"></div>			<!-- Barra de navegación del detalle -->
		<div >
			<form id="documentosexpediente_detail_form">					<!-- Formulario -->
				<div id ="documentosexpediente_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
				<div class="col-xs-12">
				
					<!-- Campos del formulario de detalle -->
					<input type="hidden" name="idDoc" class="form-control" id="idDoc056_detail_table" readonly="readonly"/>
					
					<input type="hidden" name="anyo" class="form-control" id="anyo056_detail_table" readonly="readonly"/>
					<input type="hidden" name="numExp" class="form-control" id="numExp056_detail_table" readonly="readonly"/>
					
					<div class="row">
						<div class="form-group col-lg-8">
							<label for="titulo056_detail_table" class="control-label"><spring:message code="label.titulo"/>:</label>
							<input type="text" name="documentosExpediente[0].titulo" class="form-control" id="titulo056_detail_table" maxlength="150"/>
						</div>
						<div class="form-group col-lg-4">
							<label for="indVisible056_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.visibleGestor"/> (*):</label>
							<input type="checkbox" name="documentosExpediente[0].indVisible" class="form-control" id="indVisible056_detail_table" value="S" data-switch-pestana="true"/>
						</div>
					</div>	
					<div class="row">
						<div id="capaDetClasificacion" class="form-group col-lg-2" style="clear: left;">
							<label for="claseDocumento056_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.clasificacion"/> (*):</label>
							<select name="documentosExpediente[0].claseDocumento" class="form-control" id="claseDocumento056_detail_table"></select>						
						</div>
						<div id="capaDetTipo">
							<div class="form-group col-lg-5">
								<label for="tipoDocumento056_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.tipo"/> (*):</label>
								<select name="documentosExpediente[0].tipoDocumento" class="form-control" id="tipoDocumento056_detail_table"></select>
							</div>
							<div class="form-group col-lg-3" class="control-label">
								<label for="numPalIzo056_detail_table"><spring:message code="label.documento.numPalabrasIZO"/> (*):</label>
								<input type="text" name="documentosExpediente[0].numPalIzo" class="form-control numeric" id="numPalIzo056_detail_table" maxlength="6"/>
							</div>	
							<div id="capaNumPalSolic" class="form-group col-lg-3" class="control-label">
								<label for="numPalSolic056_detail_table"><spring:message code="label.documento.numPalabrasSolicitante"/>:</label>
								<input type="text" name="documentosExpediente[0].numPalSolic" class="form-control numeric" id="numPalSolic056_detail_table" readonly="readonly"/>
							</div>	
						</div>					
					</div>
						
					<c:forEach begin="0" end="1" varStatus="status">
						<fieldset id="capaFichero_${status.index}">
							<legend><spring:message code="label.documento.infoArchivo"/> <c:if test="${status.index == 1}"><spring:message code="label.castellano"/></c:if></legend>
							
							<input type="hidden" name="documentosExpediente[0].ficheros[${status.index}].rutaPif" id="rutaOid_${status.index}"/>
							<input type="hidden" name="documentosExpediente[0].ficheros[${status.index}].extension" id="extensionDoc056_${status.index}" readonly="readonly"/>
							<input type="hidden" name="documentosExpediente[0].ficheros[${status.index}].tamano" id="tamanoDoc056_${status.index}" readonly="readonly"/>
							<input type="hidden" name="documentosExpediente[0].ficheros[${status.index}].contentType" id="contentType056_${status.index}" readonly="readonly"/>
							<input type="hidden" name="documentosExpediente[0].ficheros[${status.index}].encriptado" id="indEncriptado056_${status.index}"/>
							<input type="hidden" name="documentosExpediente[0].ficheros[${status.index}].fechaAlta" id="fechaAlta056_${status.index}" maxlength="15" readonly="readonly"/>
							<input type="hidden" name="documentosExpediente[0].ficheros[${status.index}].dniAlta" id="dniAlta056_${status.index}" maxlength="15" readonly="readonly"/>
							<input type="hidden" name="documentosExpediente[0].ficheros[${status.index}].idDocRel" id="idDocRel056_${status.index}" readonly="readonly"/>
							<input type="hidden" name="documentosExpediente[0].ficheros[${status.index}].idDocVersion" id="idDocVersion056_${status.index}" readonly="readonly"/>
							<input type="hidden" name="documentosExpediente[0].ficheros[${status.index}].oid" id="oidFichero056_${status.index}" readonly="readonly"/>
							<input type="hidden" name="documentosExpediente[0].ficheros[${status.index}].idDocInsertado" id="idDocInsertado_${status.index}" readonly="readonly"/>
												
							<div class="form-group col-lg-4">
								<label for="pidButton_${status.index}" class="control-label"><spring:message code="label.ficheroAdjunto"/>:</label>
								<input type="text" name="documentosExpediente[0].ficheros[${status.index}].nombre" class="form-control" id="nombreFicheroInfo_${status.index}" readonly="readonly"/>
							</div>
							<div class="form-group col-lg-8 no-padding" style="margin-top:20px">							
								<button id="pidButton_${status.index}" type="button"><spring:message code="label.adjuntarFichero"/></button>
								<span id="enlaceDescargaDetalle_${status.index}" style="margin-left: 1rem;"></span>
							</div>
							<fieldset  class="capaDetContacto" style="clear:left">
								<legend><spring:message code="label.documento.contactoAutor"/></legend>
								<div class="row">
									<div class="form-group col-lg-4" class="control-label">
										<label for="personaContacto056_${status.index}"><spring:message code="label.nombreapellidos"/>:</label>
										<input type="text" name="documentosExpediente[0].ficheros[${status.index}].contacto.persona" class="form-control" id="personaContacto056_${status.index}" maxlength="150"/>
									</div>
									<div class="form-group col-lg-4" class="control-label">
										<label for="emailContacto056_${status.index}"><spring:message code="label.email"/>:</label>
										<input type="text" name="documentosExpediente[0].ficheros[${status.index}].contacto.email" class="form-control" id="emailContacto056_${status.index}" maxlength="256"/>
									</div>
									<div class="form-group col-lg-4" class="control-label">
										<label for="telefonoContacto056_${status.index}"><spring:message code="label.telefono"/>:</label>
										<input type="text" name="documentosExpediente[0].ficheros[${status.index}].contacto.telefono" class="form-control" id="telefonoContacto056_${status.index}" maxlength="15"/>
									</div>
								</div>
								<div class="row">	
									<div class="form-group col-lg-4" class="control-label">
										<label for="entidadContacto056_${status.index}"><spring:message code="label.entidad"/>:</label>
										<input type="text" name="documentosExpediente[0].ficheros[${status.index}].contacto.entidad" class="form-control" id="entidadContacto056_${status.index}" maxlength="150"/>
									</div>
									<div class="form-group col-lg-8" class="control-label">
										<label for="direccionContacto056_${status.index}"><spring:message code="label.dirServicio"/>:</label>
										<input type="text" name="documentosExpediente[0].ficheros[${status.index}].contacto.direccion" class="form-control" id="direccionContacto056_${status.index}" maxlength="256"/>
									</div>
								</div>
							</fieldset>
							<div id="capaVersiones_${status.index}"></div>
						</fieldset>
					</c:forEach>
					
					<!-- Fin campos del formulario de detalle -->
				</div>
			</form>
		</div>
	
	
	</fieldset>
</div>






<div id="subidaFicheroPid" class="capaPIDenPestanaAlta" style="display:none">
	<fieldset id="subidaFicheroPid_fieldset" >
		<div id="subidaFicheroPid_feedback"></div>
		<form id="subidaFicheroPid_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
			<div class="form-group row">
				<label for="fichero" class="control-label col-md-3"><spring:message code="label.fichero" /> (*):</label>
				<div class="col-md-6" id="divFichero">
					<input type="file" name="fichero" id="fichero"  class="form-control">
				</div>
				<input type="hidden" id="idBotonUpload" name="idBotonUpload" />
				<input type="hidden" id="reqEncriptado" name="reqEncriptado" />
			</div>
		</form>
		<p class="scheduler-border" style="text-align: center;" id="txtEncriptado"><em><strong><spring:message code="mensaje.fichero.encriptadoRequerido" /></strong></em></p>
	</fieldset>
</div>