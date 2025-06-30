<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in aa79b-content" id="divEjecutarTareaCapa">
	<div id="tareaCorredaccion_div">
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
		
		<div id="pestanasModalEntregaCliente">
			<div id="tabsModalEntregaCliente" class="tabsEjecutarTarea"></div>
		</div>
	
		<div id="pestDocsEntregar">
			<div id="docsTraducir_div">
				<div id="docsTraducir_feedback"></div>
				<div id="notaFirma"class="oculto"><span class="spanAviso">(*) <spring:message code="label.documento.notaFirmaNecesaria" /></span></div>
				<div id="notaDocsFirma"class="oculto"><span class="spanAviso">(*) <spring:message code="label.notaDocsFirma" /></span></div>
				<div id="docsTraducir_toolbar" class="oculto"></div>							<!-- Botonera de la tabla -->
				<div id="contenFormularios" class="filterForm oculto">	
					<form id="docsTraducir_filter_form" class="form-horizontal">						<!-- Formulario de filtrado -->
						<div id="docsTraducir_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
						<fieldset id="docsTraducir_filter_fieldset" class="rup-table-filter-fieldset">
							<input type="text" name="idTarea" id="idTarea_filter">
						</fieldset>
					</form>
				</div>
				<input type="hidden" id="idiomaDest" name="idiomaDest" />	
				<div class="horizontal_scrollable_table">
					<!-- Tabla -->
					<table id="docsTraducir"></table>
					<!-- Barra de paginación -->
					<div id="docsTraducir_pager"></div>
				</div>
				<div class="row form-group">
					<div class="col-xs-4">
						<div class="col-xs-1">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</div>
						<label class="control-label col-xs-10">
							<spring:message code="label.documento.docEncriptado"/>
						</label>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-xs-4">
						<div class="col-xs-1">
							<i class="fa fa-unlock" aria-hidden="true"></i>
						</div>
						<label class="control-label col-xs-10">
							<spring:message code="label.documento.docNoEncriptado"/>
						</label>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-xs-4">
						<div class="col-xs-1">
							<i class="fa fa-file" aria-hidden="true"></i>
						</div>
						<label class="control-label col-xs-10">
							<spring:message code="label.documento.original"/>
						</label>
					</div>
				</div>
				
			</div>
		</div>
		<div id="pestDocsTraducidos">
			<div id="docsTraducidos_div">
				<div id="docsTraducidos_feedback"></div>						<!-- Feedback de la tabla --> 
				<div id="contenFormulariosRevisados" class="filterForm oculto">	
					<form id="docsTraducidos_filter_form" class="form-horizontal">						<!-- Formulario de filtrado -->
						<div id="docsTraducidos_filter_toolbar" class="formulario_legend"></div>			<!-- Barra de herramientas del formulario de filtrado -->
						<fieldset id="docsTraducidos_filter_fieldset" class="rup-table-filter-fieldset">
							<input type="text" name="anyo" id="anyo_filter_Traducidos">
							<input type="text" name="numExp" id="numExp_filter_Traducidos">
							<input type="text" name="idTarea" id="idTarea_filter_Traducidos">
						</fieldset>
					</form>
				</div>
				<div class="horizontal_scrollable_table">
					<!-- Tabla -->
					<table id="docsTraducidos"></table>
					<!-- Barra de paginación -->
					<div id="docsTraducidos_pager"></div>
				</div>
				<div class="row form-group">
					<div class="col-xs-4">
						<div class="col-xs-1">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</div>
						<label class="control-label col-xs-10">
							<spring:message code="label.documento.docEncriptado"/>
						</label>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-xs-4">
						<div class="col-xs-1">
							<i class="fa fa-unlock" aria-hidden="true"></i>
						</div>
						<label class="control-label col-xs-10">
							<spring:message code="label.documento.docNoEncriptado"/>
						</label>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-xs-4">
						<div class="col-xs-1">
							<i class="fa fa-file" aria-hidden="true"></i>
						</div>
						<label class="control-label col-xs-10">
							<spring:message code="label.documento.original"/>
						</label>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-xs-4">
						<div class="col-xs-1">
							<i class="fa fa-file-text" aria-hidden="true"></i>
						</div>
						<label class="control-label col-xs-10">
							<spring:message code="label.documento.docTrabajo"/>
						</label>
					</div>
				</div>
			</div>	
		</div>
		
		<div id="pestDocsXliff">
			<div id="docsXliff_div">
				<div id="docsXliff_feedback"></div>						<!-- Feedback de la tabla --> 
					<div id="docsXliff_toolbar"></div>							<!-- Botonera de la tabla -->
					<div id="contenFormulariosXliff" class="filterForm oculto">	
						<form id="docsXliff_filter_form" class="form-horizontal">						<!-- Formulario de filtrado -->
							<div id="docsXliff_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
							<fieldset id="docsXliff_filter_fieldset" class="rup-table-filter-fieldset">
								<input type="text" name="idTarea" id="idTarea_filter_Xliff">
								<input type="text" name="documentoOriginal.numExp" id="numExp_filter_Xliff">
								<input type="text" name="documentoOriginal.anyo" id="anyo_filter_Xliff">
							</fieldset>
						</form>
					</div>
					<div class="horizontal_scrollable_table">
						<!-- Tabla -->
						<table id="docsXliff"></table>
						<!-- Barra de paginación -->
						<div id="docsXliff_pager"></div>
					</div>
				
			</div>	
		</div>
		
		
		<div id="subidaFicheroPidFinal" class="capaPIDenPestanaDoc oculto">
			<fieldset id="subidaFicheroPidFinal_fieldset" >			
				<div id="subidaFicheroPidFinal_feedback"></div>
				<form id="subidaFicheroPidFinal_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
					<div class="form-group row">
						<label for="ficheroFinal" class="control-label col-md-3"><spring:message code="label.fichero" /> (*):</label>
						<div class="col-md-6" id="divFicheroFinal">
							<input type="file" name="fichero" id="ficheroFinal"  class="form-control">
						</div>
						<input type="hidden" id="reqEncriptadoFinal" name="reqEncriptado" />
					</div>
				</form>
				<p class="scheduler-border" style="text-align: center;" id="txtEncriptadoFinal"><em><strong><spring:message code="mensaje.fichero.encriptadoRequerido" /></strong></em></p>
			</fieldset>
		</div>
		<div id="subidaFicheroPidXliff" class="capaPIDenPestanaDoc oculto">
			<fieldset id="subidaFicheroPidXliff_fieldset" >			
				<div id="subidaFicheroPidXliff_feedback"></div>
				<form id="subidaFicheroPidXliff_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
					<div class="form-group row">
						<label for="ficheroXliff" class="control-label col-md-3"><spring:message code="label.fichero" /> (*):</label>
						<div class="col-md-6" id="divFicheroXliff">
							<input type="file" name="fichero" id="ficheroXliff"  class="form-control">
						</div>
						<input type="hidden" id="idTareaXliff" name="idTarea" />
						<input type="hidden" id="reqEncriptadoXliff" name="reqEncriptado" />
					</div>
				</form>
			</fieldset>
		</div>
	</div>
	<form id="ejecutarTareaDialog_form">
		<input type="hidden" name="numExp" id="numExp_form" >
		<input type="hidden" name="anyo" id="anyo_form" >
		<input type="hidden" name="idTarea" id="idTarea_form" >
		<input type="hidden" name="tipoTarea.id015" id="idTipoTarea_form" >
		<input type="hidden" name="ejecucionTareas.horasTarea" id="horasTarea_form" >
	</form>
</div>





<!-- Formulario de detalle de DOCUMENTO -->
		<div id="documentosexpediente_detail_div" class="aa79b-content-modal" >
			<div id ="documentosexpediente_detail_navigation"></div>			<!-- Barra de navegación del detalle -->
			<div  >
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
								<label for="indVisible056_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.visibleGestor"/> (*):</label>
								<input type="checkbox" name="indVisible" class="form-control" id="indVisible056_detail_table" value="S" data-switch-pestana="true"/>
							</div>
						</div>	
						<div id="capaFicheroItzulpena" class="row">
							<div id="capaFicheroItzulpenaDetach">
								<input type="hidden" name="ficheros[0].rutaPif" id="rutaPif_0"/>
								<input type="hidden" name="ficheros[0].nombre" id="nombre_0"/>
								<input type="hidden" name="ficheros[0].extension" id="extensionDoc056_0" readonly="readonly"/>
								<input type="hidden" name="ficheros[0].tamano" id="tamanoDoc056_0" readonly="readonly"/>
								<input type="hidden" name="ficheros[0].contentType" id="contentType056_0" readonly="readonly"/>
								<input type="hidden" name="ficheros[0].encriptado" id="indEncriptado056_0"/>
								<input type="hidden" name="ficheros[0].fechaAlta" id="fechaAlta056_0" maxlength="15" readonly="readonly"/>
								<input type="hidden" name="ficheros[0].dniAlta" id="dniAlta056_0" maxlength="15" readonly="readonly"/>
								<input type="hidden" name="ficheros[0].idDocRel" id="idDocRel056_0" readonly="readonly"/>
								<input type="hidden" name="ficheros[0].idDocVersion" id="idDocVersion056_0" readonly="readonly"/>
								<input type="hidden" name="ficheros[0].oid" id="oidFichero056_0" readonly="readonly"/>
								<input type="hidden" name="ficheros[0].idDocInsertado" id="idDocInsertado_0" readonly="readonly"/>
													
								<div class="form-group col-lg-12 padding-top-2">	
									<span id="enlaceDescargaDetalle_0"></span>						
									<button id="pidButton_0" type="button" class="ui-button ui-corner-all ui-widget" style="margin-top:-4px"><spring:message code="label.adjuntarFichero"/></button>
									<input type="text" name="ficheros[0].nombreUpload" class="form-control" id="nombreFicheroInfo_0" readonly="readonly" style="width:200px; display:inline-block" />
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
						<!-- Fin campos del formulario de detalle -->
						
					</div>
				</form>
			</div>
			<!-- Botonera del formulario de detalle -->
			<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
				<div class="ui-dialog-buttonset">
					<!-- Botón Guardar -->
					<button id="documentosexpediente_detail_button_save" type="button">
						<spring:message code="save" />
					</button>
					<!-- Enlace cancelar -->
					<a href="javascript:void(0)" id="documentosexpediente_detail_link_cancel"
						class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
				</div>
			</div>
		</div>
	
		<!-- FIN Formulario de detalle de DOCUMENTO -->