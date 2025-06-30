<%@include file="/WEB-INF/includeTemplate.inc"%>
<div id="crearConfigurarDiv">
	<div class="aa79b-content-modal">
	<div id="crearConfigurar_div">
	<div id="crearConfigurar_feedback"></div>
		<form id="crearConfigurar_filter_form">
			<input type="hidden" name="idTarea" id="idTarea_detail_table">
			<input type="hidden" name="estadoEjecucion" id="estadoEjecucion_detail_table">
			<div class="row">
				<fieldset id="expedienteInfio_fieldset" class="form_fieldset">
					<legend>
						<spring:message code="label.datosTrabajo"/>
					</legend>
					<div class="row">
						<div class="col-md-3">
							<label for="idTrabajoTarea_detail_table" class="control-label col-md-12 p-0" data-i18n="label.id">
								<spring:message code="label.id" />:
							</label>
							<span id="idTrabajoTarea_detail_table"></span>
						</div>
						<div class="col-md-9">
							<label for="descTrabajoTarea_detail_table" class="control-label col-md-12 p-0" data-i18n="label.titulo">
								<spring:message code="label.titulo"/>: 
							</label>
							<span id="descTrabajoTarea_detail_table"></span>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3 grupoFechaHora">
								<label for="fechaInicioTrabajo_detail_table" class="control-label col-md-12 p-0" data-i18n="label.fechaHoraInicio"><spring:message code="label.fechaHoraInicio" />:</label>
								<div class="form-group  col-md-12 col-xl-12 p-0">
									<span id="fechaInicioTrabajo_detail_table"></span>
								</div>
							</div>
							<div class="col-md-4 grupoFechaHora">
								<label for="fechaFinPrevistaTrabajo_detail_table" class="control-label col-md-12 p-0" data-i18n="label.fechaHoraFinPrevistaY"><spring:message code="label.fechaHoraFinPrevistaY" />:</label>
								<div class="form-group  col-md-12 col-xl-12 p-0">
									<span id="fechaFinPrevistaTrabajo_detail_table"></span>
								</div>
							</div>
					</div>
				</fieldset>
			</div>
			<div class="row">
			<fieldset id="tareaInfo_fieldset" class="form_fieldset">
				<legend>
					<spring:message code="comun.datosTarea"/>
				</legend>
				<div class="row">					
						<div class="form-group col-xs-6">
							<label for="tipoTareaTrabajo_detail_table" class="control-label col-xs-12 no-padding-left" ><spring:message code="comun.tipoDeTarea"/> (*):</label>
							<div class="col-xs-12 no-padding-left">
								<select  name="tipoTarea.id015" class="form-control" id="tipoTareaTrabajo_detail_table" ></select>
							</div>	
						</div>
						<div class="form-group col-xs-3">
							<label for="ordenTarea_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="label.ordenacion"/> (*):</label>
							<div class="col-xs-12 no-padding-left">
								<input type=text  name="orden" class="form-control col-40por numeric" id="ordenTarea_detail_table" maxlength="2"/>
							</div>	
						</div>
					</div>
					<div class="row">
						
						<div class="form-group col-md-12 col-lg-4 grupoFechaHora ">
							<label class="control-label valFecha ajustarFieldSet" for="fechaInicioTarea_detail_table"><spring:message code="comun.fechaInicioTarea"/> (*):</label>
							<input type="text" name="fechaInicio" class="form-control" id="fechaInicioTarea_detail_table">
							<input type="text" name="horaInicio" class="form-control campohora " id="horaInicioTarea_detail_table" placeholder="hh:mm" maxlength="5">
						</div>
						<div class="form-group col-md-12 col-lg-4 grupoFechaHora ">
							<label class="control-label dosPuntos valFecha ajustarFieldSet" for="fechaFinTarea_detail_table"><spring:message code="comun.fechaFinTarea"/></label>
							<input type="text" name="fechaFin" class="form-control" id="fechaFinTarea_detail_table">
							<input type="text" name="horaFin" class="form-control campohora" id="horaFinTarea_detail_table" placeholder="hh:mm" maxlength="5">
						</div>
					</div>
					
					
			<div class="row">
			 	<div class="form-group col-xs-8">
	                <label for="personaAsignadaTarea_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="comun.asignadaA"/>:</label>
	                <div class="input-group">
	                	<input type="hidden" id="recursoAsignacion" name="recursoAsignacion" >
						<input type="hidden" id="dniAsignacion" name="personaAsignada.dni"/>
	                    <input type="text" name="personaAsignada.nombre" class="form-control" onchange="mostrarLote()" id="personaAsignadaTarea_detail_table" readOnly="readonly">
	                    <span class="input-group-addon" id="divLinkEliminar"><a href="#" id="eliminar_detail_table" class=""><spring:message code="eliminar"/></a></span>
	                    <span class="input-group-addon" id="divLinkAutoasignar"><a href="#" id="autoasignar_detail_table" class=""><spring:message code="label.autoasignar"/></a></span>
	                    <span class="input-group-addon" id="divLinkRecursoInterno"><a href="#" id="recursoInterno_detail_table" class=""><spring:message code="label.personalIzo"/></a></span>
	                </div>
	            </div>
            </div>
            
            <div id="capaFicheroItzulpena" class="row">
				<div id="capaFicheroItzulpenaDetach">
					<input type="hidden" name="documentosExpediente[0].ficheros[0].idFichero" id="idFichero_0"/>
					<input type="hidden" name="documentosExpediente[0].ficheros[0].rutaPif" id="rutaPif_0"/>
					<input type="hidden" name="documentosExpediente[0].ficheros[0].nombre" id="nombre_0"/>
					<input type="hidden" name="documentosExpediente[0].ficheros[0].extension" id="extensionDoc_0" readonly="readonly"/>
					<input type="hidden" name="documentosExpediente[0].ficheros[0].tamano" id="tamanoDoc_0" readonly="readonly"/>
					<input type="hidden" name="documentosExpediente[0].ficheros[0].contentType" id="contentType_0" readonly="readonly"/>
					<input type="hidden" name="documentosExpediente[0].ficheros[0].oid" id="oidFichero_0" readonly="readonly"/>
					<input type="hidden" name="documentosExpediente[0].ficheros[0].encriptado" id="encriptado_0" readonly="readonly"/>
										
					<div class="form-group col-lg-12 padding-top-2">							
						<span id="enlaceDescargaDetalle_0" ></span>
						<button id="pidButton_0" type="button" class="ui-button ui-corner-all ui-widget no-obligatorio" style="margin-top:-4px; height:22px"><spring:message code="label.adjuntarFichero"/></button>
						<input type="text" name="documentosExpediente[0].ficheros[0].nombreUpload" class="form-control" id="nombreFicheroInfo_0" readonly="readonly" style="width:200px; display:inline-block"/>
					</div>
				</div>
			</div>
            
			<div class="row">
				<%-- <div class="form-group col-xs-6">
					<label for="documentacion_detail_table" class="control-label dosPuntos col-xs-3 no-padding-left"><spring:message code="label.documentos"/></label>	
					<div class="form-group col-xs-4" >
						<input type="checkbox" name="responsable" id="selectTodos"  value="S">
						<label for="selectTodos" class="control-label no-padding-left"><spring:message code="label.seleccionarTodo"/></label>
					</div>
					<a href="#" id="linkDetalleExpediente" class="col-xs-2 " ><spring:message code="label.ver"/></a>
					<div class="col-xs-12 no-padding-left">
						<fieldset id="documentacion_filter_fieldset" class="rup-table-filter-fieldset">
							<div class="form-group col-md-12">
								<div id="docuAsociados" class="docuAsociadosTree">
									<div id="listaDocuAsociados">
										<ul>
										</ul>
									</div>
								</div>
							</div>
						</fieldset>
					</div>
					<div id="divDocumentosSeleccionados">
						<input type="text" style="width:0px;visibility:hidden;" id="documentosSelect" name="documentosSelect" class="form-control" />
					</div>
				</div> --%>
				<div class="form-group col-xs-6">
					<label for="observacionesTarea_detail_table" class="control-label dosPuntos col-xs-12 no-padding-left"><spring:message code="label.observaciones"/></label>
					<div class="col-xs-12 no-padding-left">
						<textarea  name="observaciones" class="form-control" id="observacionesTarea_detail_table" style="resize: none" rows="6" maxlength="2000"></textarea>
					</div>	
				</div>
			</div>
			</fieldset>
			</div>
		</form>
		</div>
	</div>
	
	<div id="buscadorPersonasIZO" class="oculto"></div>
	
	<div id="subidaFicheroPid" class="capaPIDenPestanaAlta oculto" >
	<fieldset id="subidaFicheroPid_fieldset" >
		<div id="subidaFicheroPid_feedback"></div>
		<form id="subidaFicheroPid_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
			<div class="form-group row">
				<label for="fichero" class="control-label col-md-3"><spring:message code="label.fichero" /> (*):</label>
				<div class="col-md-6" id="divFichero">
					<input type="file" name="fichero" id="fichero"  class="form-control">
				</div>
				<input type="hidden" id="idBotonUpload"  name="idBotonUpload" />
				<input type="hidden" id="reqEncriptado" name="reqEncriptado" />
			</div>
		</form>
		<p class="scheduler-border" style="text-align: center;" id="txtEncriptado"><em><strong><spring:message code="mensaje.fichero.encriptadoRequerido" /></strong></em></p>
	</fieldset>
</div>
</div>
