<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in  aa79b-content" id="divEjecutarTareaCapa">
	<div id="tareaestudiarsubsdocu_div">
		<div id="ejecutarTareaDialog_toolbar"></div>
		<div id="ejecutarTareaDialog_feedback"></div>
		<div class="row margen1TB">
			<div class="col-xs-2">
				<label class="control-label"><spring:message code="comun.tipoDeTarea" />:</label>
			</div>
			<div class="col-xs-9">
				<b><c:out value="${descripcionTarea}"></c:out></b>
			</div>
		</div>
	
			<fieldset>
			<legend>
				<spring:message code="label.datosSubsanacion"></spring:message>
			</legend>
			<div id="estudiarSubsDocu_div">
				<div id="estudiarSubsDocu_feedback"></div>						<!-- Feedback de la tabla --> 
<!-- 				<div id="docsTraducir_toolbar"></div>							Botonera de la tabla -->
				<div id="contenFormularios" class="filterForm oculto">	
					<form id="estudiarSubsDocu_filter_form" class="form-horizontal">						<!-- Formulario de filtrado -->
						<div id="docsTraducir_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
						<fieldset id="docsTraducir_filter_fieldset" class="rup-table-filter-fieldset">
							<input type="text" name="idTarea" id="idTarea_filter">
						</fieldset>
					</form>
				</div>
					<div class="row">
						<div class="col-md-6">
							<label for="fechaHoraRegistroReq"><spring:message code="label.fechaHoraRegistroRequerimiento"/>: </label>
							<span id="fechaHoraRegistroReq"></span>
						</div>
						<div class="col-md-6">
							<label for="fechaHoraLimiteSubs"><spring:message code="label.fechaHoraLimiteSubsanacion"/>: </label>
							<span id="fechaHoraLimiteSubs"></span>
						</div>
					</div>
					<div class="row" style="margin-top: 10px;">
						<div class="col-md-12">
							<label for="detalleReq" class="col-md-2 no-padding-left"><spring:message code="label.detalleReqSub"/></label>
							<textarea disabled style="max-height: 30px; overflow-y: scroll; " class="resizable-textarea col-md-10 no-padding-left" id="detalleReq" rows="5" cols="9"></textarea>
						</div>
					</div>
					<div class="row" style="margin-top: 10px;">
						<div class="col-md-5">
							<label for="fechaHoraSubsanacion"><spring:message code="label.fechaHoraSubsanacion"/>: </label>
							<span id="fechaHoraSubsanacion"></span>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<label for="capaDocumentosDetalle"><spring:message code="label.documentos"/>: </label>
							<div style=" padding-top: 10px; height: 130px; position: relative; overflow: auto;"><div class="col-md-10 no-padding-left" id="capaDocumentosDetalle" style="width:100%; padding-right: 0px!important;"></div></div>
						</div>
					</div>
					<div class="row" style="margin-top: 10px;">
						<div class="col-xs-12">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<label class="control-label "><spring:message code="label.revisarNumPalabrasIZOdocumento" /></label>
						</div>
					</div>
			</div>
			</fieldset>
			<form id="ejecutarTareaDialog_form">
				<input type="hidden" name="numExp" id="numExp_form" >
				<input type="hidden" name="anyo" id="anyo_form" >
				<input type="hidden" name="idTarea" id="idTarea_form" >
				<input type="hidden" name="tipoTarea.id015" id="idTipoTarea_form" >
				<input type="hidden" name="ejecucionTareas.horasTarea" id="horasTarea_form" >
				<input type="hidden" name="subsanacion.indSubsanado" id="inSubsReqSubs" >
				<input type="hidden" name="subsanacion.estado" id="estadoReqSubs" >
				<input type="hidden" name="subsanacion.id" id="idReq" >
				<div class="row" style="margin-top: 10px;">
					<div class="col-md-5">
						<label for="checkSubsCorrecta"><spring:message code="label.preguntaSubsanCorrecta"/></label>
						<input type="checkbox" class="form-control" id="checkSubsCorrecta" value="S" data-switch-pestana="false"/>
					</div>
				</div>
				<div class="row" style="margin-top: 10px;">
					<div class="col-md-12" style="padding-right:2px">
						<label class="col-md-12 no-padding-left" for="motivoSubsIncorrecta"><spring:message code="label.motivoSubsanCorrecta"/>: </label>
						<textarea name="subsanacion.observRechazo" style="max-height: 30px; overflow-y: scroll;" class="form-control resizable-textarea col-md-12" id="motivoSubsIncorrecta" rows="3" cols="5"></textarea>
					</div>
				</div>
			</form>
	</div>
</div>

	<!-- Formulario de detalle de DOCUMENTO -->
	<div id="documentosexpediente_detail_div" class="aa79b-content-modal oculto" >
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
							<label for="titulo056_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.titulo"/>:</label>
							<input type="text" name="titulo" class="form-control" id="titulo056_detail_table" maxlength="150"/>
						</div>
						<div class="form-group col-lg-4">
							<label for="indVisible056_detail_table_m" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.visibleGestor"/> (*):</label>
							<input type="checkbox" name="indVisible" class="form-control" id="indVisible056_detail_table_m" value="S" data-switch-pestana="true"/>
						</div>
					</div>	
					<div id="capaFicheroItzulpena" class="row">
						<div id="capaFicheroItzulpenaDetach">
												
							<div class="form-group col-lg-12 padding-top-2">	
								<span id="enlaceDescargaDetalle_0"></span>						
							</div>
						</div>
					</div>
					<div class="row">
						<div id="capaDetClasificacion" class="form-group col-lg-2" style="clear: left;">
							<label for="claseDocumento056_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.clasificacion"/> (*):</label>
							<select name="claseDocumento" class="form-control" id="claseDocumento056_detail_table"></select>						
						</div>
						<div id="capaDetTipo">
							<div class="form-group col-lg-3">
								<label for="tipoDocumento056_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.tipo"/> (*):</label>
								<select name="tipoDocumento" class="form-control" id="tipoDocumento056_detail_table"></select>
							</div>
							<div class="form-group col-lg-3" class="control-label">
								<label for="numPalIzo056_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.numPalabrasIZO"/> (*):</label>
								<input type="text" name="numPalIzo" class="form-control numeric" id="numPalIzo056_detail_table" maxlength="6" style="width:100px"/>
							</div>	
						</div>	
						<div id="capaNumPalSolic" class="form-group col-lg-3" class="control-label">
							<label for="numPalSolic056_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.numPalabrasSolicitante"/> (*):</label>
							<input type="text" name="numPalSolic" class="form-control numeric" id="numPalSolic056_detail_table" readonly="readonly" style="width:100px"/>
						</div>	
					</div>
					
					<div id="capaFicheroItzulpenaContacto">
						<div id="capaFicheroItzulpenaContactoDetach">
							<fieldset>
								<legend class="fieldset_rojo"><spring:message code="label.documento.contactoAutor"/></legend>
								<div class="row">	
									<div class="form-group col-lg-4" class="control-label">
										<label for="personaContacto056_0"><spring:message code="label.nombreapellidos"/>:</label>
										<input type="text" name="ficheros[0].contacto.persona" class="form-control" id="personaContacto056_0" maxlength="150"/>
									</div>
									<div class="form-group col-lg-4" class="control-label">
										<label for="emailContacto056_0"><spring:message code="label.email"/>:</label>
										<input type="text" name="ficheros[0].contacto.email" class="form-control" id="emailContacto056_0" maxlength="256"/>
									</div>
									<div class="form-group col-lg-4" class="control-label">
										<label for="telefonoContacto056_0"><spring:message code="label.telefono"/>:</label>
										<input type="text" name="ficheros[0].contacto.telefono" class="form-control" id="telefonoContacto056_0" maxlength="15"/>
									</div>
								</div>
								<div class="row">	
									<div class="form-group col-lg-4" class="control-label">
										<label for="entidadContacto056_0"><spring:message code="label.entidad"/>:</label>
										<input type="text" name="ficheros[0].contacto.entidad" class="form-control" id="entidadContacto056_0" maxlength="150"/>
									</div>
									<div class="form-group col-lg-8" class="control-label">
										<label for="direccionContacto056_0"><spring:message code="label.dirServicio"/>:</label>
										<input type="text" name="ficheros[0].contacto.direccion" class="form-control" id="direccionContacto056_0" maxlength="256"/>
									</div>
								</div>
							</fieldset>
						</div>
						<div id="versionesApoyo">
							<div id="capaVersiones_0"></div>
						</div>
					</div>
					<div id="capaFicheroBerrikusketa">
						<div id="capaFicheroBerrikusketaDetach">
							<c:forEach begin="0" end="1" varStatus="status">
								<fieldset id="capaFichero_${status.index}">
									<legend id="legendFichero_${status.index}" class="fieldset_rojo"><c:if test="${status.index == 0}"><spring:message code="label.documentoOrigen"/></c:if><c:if test="${status.index == 1}"><spring:message code="label.documentoARevisar"/></c:if></legend>
									
									<input type="hidden" name="ficheros[${status.index}].rutaPif" id="rutaPif_${status.index}"/>
									<input type="hidden" name="ficheros[${status.index}].nombre" id="nombre_${status.index}"/>
									<input type="hidden" name="ficheros[${status.index}].extension" id="extensionDoc056_${status.index}" readonly="readonly"/>
									<input type="hidden" name="ficheros[${status.index}].tamano" id="tamanoDoc056_${status.index}" readonly="readonly"/>
									<input type="hidden" name="ficheros[${status.index}].contentType" id="contentType056_${status.index}" readonly="readonly"/>
									<input type="hidden" name="ficheros[${status.index}].encriptado" id="indEncriptado056_${status.index}"/>
									<input type="hidden" name="ficheros[${status.index}].fechaAlta" id="fechaAlta056_${status.index}" maxlength="15" readonly="readonly"/>
									<input type="hidden" name="ficheros[${status.index}].dniAlta" id="dniAlta056_${status.index}" maxlength="15" readonly="readonly"/>
									<input type="hidden" name="ficheros[${status.index}].idDocRel" id="idDocRel056_${status.index}" readonly="readonly"/>
									<input type="hidden" name="ficheros[${status.index}].idDocVersion" id="idDocVersion056_${status.index}" readonly="readonly"/>
									<input type="hidden" name="ficheros[${status.index}].oid" id="oidFichero056_${status.index}" readonly="readonly"/>
									<input type="hidden" name="ficheros[${status.index}].idDocInsertado" id="idDocInsertado_${status.index}" readonly="readonly"/>
														
									<div id="capaDivPid_${status.index}" class="form-group col-lg-12">	
										<span id="enlaceDescargaDetalle_${status.index}"></span>						
									</div>
									
									<fieldset  class="capaDetContacto" style="clear:left">
										<legend class="fieldset_rojo"><spring:message code="label.documento.contactoAutor"/></legend>
										<div class="row">	
											<div class="form-group col-lg-4" class="control-label">
												<label for="personaContacto056_${status.index}"><spring:message code="label.nombreapellidos"/>:</label>
												<input type="text" name="ficheros[${status.index}].contacto.persona" class="form-control" id="personaContacto056_${status.index}" maxlength="150"/>
											</div>
											<div class="form-group col-lg-4" class="control-label">
												<label for="emailContacto056_${status.index}"><spring:message code="label.email"/>:</label>
												<input type="text" name="ficheros[${status.index}].contacto.email" class="form-control" id="emailContacto056_${status.index}" maxlength="256"/>
											</div>
											<div class="form-group col-lg-4" class="control-label">
												<label for="telefonoContacto056_${status.index}"><spring:message code="label.telefono"/>:</label>
												<input type="text" name="ficheros[${status.index}].contacto.telefono" class="form-control" id="telefonoContacto056_${status.index}" maxlength="15"/>
											</div>
										</div>
										<div class="row">	
											<div class="form-group col-lg-4" class="control-label">
												<label for="entidadContacto056_${status.index}"><spring:message code="label.entidad"/>:</label>
												<input type="text" name="ficheros[${status.index}].contacto.entidad" class="form-control" id="entidadContacto056_${status.index}" maxlength="150"/>
											</div>
											<div class="form-group col-lg-8" class="control-label">
												<label for="direccionContacto056_${status.index}"><spring:message code="label.dirServicio"/>:</label>
												<input type="text" name="ficheros[${status.index}].contacto.direccion" class="form-control" id="direccionContacto056_${status.index}" maxlength="256"/>
											</div>
										</div>
									</fieldset>
									<div id="capaVersiones_${status.index}"></div>
								</fieldset>
							</c:forEach>
						</div>
					</div>
					<!-- Fin campos del formulario de detalle -->
				</div>
			</form>
		</div>
		
	</div>



