<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in  aa79b-content" id="divEjecutarTareaCapa">
	<div id="tareaRevisionEntregaClte_div">
		<div id="ejecutarTareaDialog_toolbar"></div>
		<div id="ejecutarTareaDialog_feedback"></div>
		<input type="hidden" name="lotes.idLote" id="idLote_detail_table">
		<div class="row margen1TB">
			<div class="col-xs-2">
				<label class="control-label"><spring:message code="comun.tipoDeTarea" />:</label>
			</div>
			<div class="col-xs-9">
				<b><c:out value="${descripcionTarea}"></c:out></b>
			</div>
		</div>
		
		<div id="datosProveedor">
			<div class="row margen1TB">
				<fieldset id="penalLote_fieldset">
					<legend><spring:message code="label.penalLote" /></legend>
					<div class="p-2">
						<div class="row">
							<div class="form-group col-lg-3">
								<label class="control-label" for="retraso_revEntregaClte"><spring:message code="label.retraso"/>:</label>
								<label class="control-label" id="retraso_revEntregaClte"></label>
								<input type="hidden" id="retrasoVal_revEntregaClte" >
							</div>
						</div>
					</div>
				</fieldset>
			</div>
			
			<div class="row margen1TB">
				<div class="form-group col-lg-3">
					<label class="control-label" for="porPenalizacionSwitch_revEntregaClte"><spring:message code="label.penalizacionPorRetraso"/>:</label>
					<input type="checkbox" name="datosPagoProveedores.indRecargoFormato" id="porPenalizacionSwitch_revEntregaClte" value="S" data-switch-pestana="true">
				</div>
				<div class="form-group col-lg-4">
					<label class="control-label" for="porcentajePenalizacion_revEntregaClte"><spring:message code="label.porcentajePenalizacion"/>:</label>
					<input type="text" style="width:100px" class="form-control numeric" id="porcentajePenalizacion_revEntregaClte" disabled maxlength="3" />
				</div>
			</div>	
			
			<div class="row margen1TB">
				<div class="form-group col-lg-3">
					<label for="calidad_combo_revEntregaClte" class="control-label"><spring:message code="label.calidadTrabajoRealizado" /></label>
					<select class="form-control" id="calidad_combo_revEntregaClte" ></select>
				</div>
			</div>
		</div>
		
		<div id="pestanasModalRevEntregaClte">
			<div id="tabsModalRevEntregaClte" class="tabsEjecutarTarea"></div>
		</div>
		<input type="hidden" id="idiomaDest" name="idiomaDest" />
		<div id="pestDocsOriginales" class="marginTop20">
			<div id="docsOriginales_div">
				<div id="docsOriginales_feedback"></div>						<!-- Feedback de la tabla --> 
				<div id="notaFirma"class="oculto"><span class="spanAviso">(*) <spring:message code="label.documento.notaFirmaNecesaria" /></span></div>
				<div id="notaDocsFirma"class="oculto"><span class="spanAviso">(*) <spring:message code="label.notaDocsFirma" /></span></div>
				<div id="docsOriginales_toolbar" class="oculto"></div>
				<div id="contenFormularios" class="filterForm oculto">	
					<form id="docsOriginales_filter_form" class="form-horizontal">						<!-- Formulario de filtrado -->
						<div id="docsOriginales_filter_toolbar" class="formulario_legend"></div>		<!-- Barra de herramientas del formulario de filtrado -->
						<fieldset id="docsOriginales_filter_fieldset" class="rup-table-filter-fieldset">
							<input type="text" name="idTarea" id="idTarea_filter">
						</fieldset>
					</form>
				</div>
				<div class="horizontal_scrollable_table">
					<!-- Tabla -->
					<table id="docsOriginales"></table>
					<!-- Barra de paginación -->
					<div id="docsOriginales_pager"></div>
				</div>
				<div class="leyendaDocs">
					<p><span class="ico-ficha-encriptado"><i class="fa fa-lock" aria-hidden="true"></i></span> <spring:message code="label.documento.docEncriptado"/></p>
					<p><span class="ico-ficha-encriptado"><i class="fa fa-unlock" aria-hidden="true"></i></span> <spring:message code="label.documento.docNoEncriptado"/></p>
					<p><span class="ico-ficha-encriptado"><i class="fa fa-file" aria-hidden="true"></i></span> <spring:message code="label.documento.original"/></p>
				</div>
			</div>
		</div>
		<div id="pestDocsRevisados" class="marginTop20">
			<div id="docsRevisados_div">
				<div id="docsRevisados_feedback"></div>						<!-- Feedback de la tabla -->
				<div id="docsRevisados_toolbar" class="oculto"></div>		<!-- Toolbar de la tabla --> 
				<div id="contenFormulariosRevisados" class="filterForm oculto">	
					<form id="docsRevisados_filter_form" class="form-horizontal">						<!-- Formulario de filtrado -->
						<div id="docsRevisados_filter_toolbar" class="formulario_legend"></div>			<!-- Barra de herramientas del formulario de filtrado -->
						<fieldset id="docsRevisados_filter_fieldset" class="rup-table-filter-fieldset">
							<input type="text" name="anyo" id="anyo_filter_Revisados">
							<input type="text" name="numExp" id="numExp_filter_Revisados">
							<input type="text" name="idTarea" id="idTarea_filter_Revisados">
						</fieldset>
					</form>
				</div>
				<div class="horizontal_scrollable_table">
					<!-- Tabla -->
					<table id="docsRevisados"></table>
					<!-- Barra de paginación -->
					<div id="docsRevisados_pager"></div>
				</div>
				<div class="leyendaDocs">
					<p><span class="ico-ficha-encriptado"><i class="fa fa-lock" aria-hidden="true"></i></span> <spring:message code="label.documento.docEncriptado"/></p>
					<p><span class="ico-ficha-encriptado"><i class="fa fa-unlock" aria-hidden="true"></i></span> <spring:message code="label.documento.docNoEncriptado"/></p>
					<p><span class="ico-ficha-encriptado"><i class="fa fa-file" aria-hidden="true"></i></span> <spring:message code="label.documento.original"/></p>
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
				
<!-- 		<div id="subidaFicheroPid" class="capaPIDenPestanaDoc oculto"> -->
<!-- 			<fieldset id="subidaFicheroPid_fieldset" >			 -->
<!-- 				<div id="subidaFicheroPid_feedback"></div> -->
<!-- 				<form id="subidaFicheroPid_form" enctype="multipart/form-data" method="POST" class="form-horizontal"> -->
<!-- 					<div class="form-group row"> -->
<%-- 						<label for="fichero" class="control-label col-md-3"><spring:message code="label.fichero" /> (*):</label> --%>
<!-- 						<div class="col-md-6" id="divFichero"> -->
<!-- 							<input type="file" name="fichero" id="fichero"  class="form-control"> -->
<!-- 						</div> -->
<!-- 						<input type="hidden" id="idBotonUpload" /> -->
<!-- 						<input type="hidden" id="reqEncriptado" name="reqEncriptado" /> -->
<!-- 					</div> -->
<!-- 				</form> -->
<%-- 				<p class="scheduler-border" style="text-align: center;" id="txtEncriptado"><em><strong><spring:message code="mensaje.fichero.encriptadoRequerido" /></strong></em></p> --%>
<!-- 			</fieldset> -->
<!-- 		</div> -->
		
		<div id="subidaFicheroPidFinal" class="capaPIDenPestanaDoc oculto">
			<fieldset id="subidaFicheroPidFinal_fieldset" >			
				<div id="subidaFicheroPidFinal_feedback"></div>
				<form id="subidaFicheroPidFinal_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
					<div class="form-group row">
						<label for="ficheroFinal" class="control-label col-md-3"><spring:message code="label.fichero" /> (*):</label>
						<div class="col-md-6" id="divFicheroFinal">
							<input type="file" name="fichero" id="ficheroFinal"  class="form-control">
						</div>
<!-- 						<input type="hidden" id="idDocOriginal" name="idDocOriginal" /> -->
<!-- 						<input type="hidden" id="idTarea" name="idTarea" /> -->
<!-- 						<input type="hidden" id="idTablaIntermedia" name="idTablaIntermedia" value="92" /> -->
						<input type="hidden" id="reqEncriptado" name="reqEncriptado" />
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
		<input type="hidden" name="datosPagoProveedores.indPenalizacion" id="indPenalizacion_form_revEntregaClte" >
		<input type="hidden" name="datosPagoProveedores.porPenalizacion" id="porPenalizacion_form_revEntregaClte" >
		<input type="hidden" name="datosPagoProveedores.nivelCalidad" id="nivelCalidad_form_revEntregaClte" >
		<input type="hidden" name="indFacturable" id="indFacturable_form_revEntregaClte" >
	</form>
</div>

<!-- Formulario Generar una no conformidad -->
<div id="generarNoConformidad_detail_div" class="aa79b-content-modal oculto" >
	<div id ="generarNoConformidad_detail_navigation"></div>			<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content" >
		<form id="generarNoConformidad_detail_form">					<!-- Formulario -->
			<div id="generarNoConformidad_detail_toolbar"></div>		<!-- Toolbar -->
			<div id="generarNoConformidad_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			<div class="col-xs-12">
				<fieldset>
					<legend>
						<spring:message code="label.fechaHoraFinPrevistaTareaRev"></spring:message>
					</legend>
					<div class="row">
						<div class="form-group col-xs-12">
							<b><span id="fechaHoraFinPrevista"></span></b>
						</div>
					</div>
				</fieldset>
				<div class="row">
					<div class="form-group col-md-12 col-lg-4 grupoFechaHora">
						<label class="control-label valFecha" for="fechaIniNoConformidad_detail_table"><spring:message code="comun.fechaInicioTarea"/> (*):</label>
						<input type="text" name="fechaIni" class="form-control" id="fechaIniNoConformidad_detail_table">
						<input type="text" name="horaIni" class="form-control campohora" id="horaIniNoConformidad_detail_table" placeholder="hh:mm" maxlength="5">
					</div>
					<div class="form-group col-md-12 col-lg-4 grupoFechaHora">
						<label class="control-label valFecha" for="fechaFinNoConformidad_detail_table"><spring:message code="comun.fechaFinTarea"/> (*):</label>
						<input type="text" name="fechaFin" class="form-control" id="fechaFinNoConformidad_detail_table">
						<input type="text" name="horaFin" class="form-control campohora" id="horaFinNoConformidad_detail_table" placeholder="hh:mm" maxlength="5">
					</div>
					<div class="form-group col-lg-4">
						<label for="horaPrevistaNoConformidad_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="comun.horaPrevista"/> (*):</label>
						<div class="col-xs-5 no-padding">
							<input type=text  name="horasPrevistas" class="form-control" id="horaPrevistaNoConformidad_detail_table" placeholder="hh:mm" maxlength="5"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-lg-6">
						<label for="documentacion_detail_table" class="control-label col-xs-8 no-padding"><spring:message code="label.docNoConformidad"/> :</label>	
						<div class="col-xs-12 no-padding-left">
							<input type="hidden" name="ficheros[0].rutaPif" id="rutaPif_1"/>
							<input type="hidden" name="ficheros[0].nombre" id="nombre_1"/>
							<input type="hidden" name="ficheros[0].extension" id="extensionDoc056_1" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].tamano" id="tamanoDoc056_1" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].contentType" id="contentType056_1" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].encriptado" id="indEncriptado056_1"/>
							<input type="hidden" name="ficheros[0].fechaAlta" id="fechaAlta056_1" maxlength="15" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].dniAlta" id="dniAlta056_1" maxlength="15" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].idDocRel" id="idDocRel056_1" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].idDocVersion" id="idDocVersion056_1" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].oid" id="oidFichero056_1" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].idDocInsertado" id="idDocInsertado_1" readonly="readonly"/>
												
							<div class="form-group col-lg-12 padding-top-2 no-padding-left">	
								<span id="enlaceDescargaDetalle_1" class="col-lg-12"></span>						
								<button id="pidButton_1" type="button" class="ui-button ui-corner-all ui-widget no-obligatorio" style="margin-top:-4px"><spring:message code="label.adjuntarFichero"/></button>
								<input type="text" name="ficheros[0].nombreUpload" class="form-control" id="nombreFicheroInfo_1" readonly="readonly" style="width:200px; display:inline-block" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-lg-6">
						<label for="documentacionNoConformidad_detail_table" class="control-label col-xs-4 no-padding"><spring:message code="label.documentos"/> (*):</label>	
						<div class="col-xs-12 no-padding-left">
							<fieldset id="documentacion_filter_fieldset" class="rup-table-filter-fieldset">
								<div class="form-group col-md-12">
									<div id="docsNoConformidad" class="docuAsociadosTree">
										<div id="listaDocsNoConformidad">
											<ul>
											</ul>
										</div>
									</div>
								</div>
							</fieldset>
						</div>
						<div id="divDocsSeleccionadosNoConformidad">
							<input type="text" style="width:0px;visibility:hidden;" id="documentosSelectNoConformidad" name="documentosSelectNoConformidad" class="form-control"/>
						</div>
					</div>
					<div class="form-group col-lg-6">
						<label for="obsNoConformidad_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.observaciones"/> (*):</label>
						<div class="col-xs-12 no-padding">
							<textarea  name="observaciones" class="form-control" id="obsNoConformidad_detail_table" style="resize: none" rows="6" maxlength="2000"></textarea>
						</div>	
					</div>
				</div>	
			</div>
		</form>
	</div>
</div>

<!-- Formulario Notificar una correccion al proveedor -->
<div id="notificarCorreccionProv_detail_div" class="aa79b-content-modal oculto" >
	<div class="ui-dialog-content ui-widget-content" >
		<div id="notificarCorreccionProv_detail_toolbar"></div>		<!-- Toolbar -->
		<div id="notificarCorreccionProv_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
		<form id="notificarCorreccionProv_detail_form">					<!-- Formulario -->
			<div class="col-xs-12">
				<div class="row">
					<div class="form-group col-lg-6">
						<label for="documentacion_detail_table" class="control-label col-xs-4"><spring:message code="label.documentos"/> (*):</label>	
						<div class="col-xs-12">
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
								<span id="enlaceDescargaDetalle_0" class="col-lg-12"></span>						
								<button id="pidButton_0" type="button" class="ui-button ui-corner-all ui-widget" style="margin-top:-4px"><spring:message code="label.adjuntarFichero"/></button>
								<input type="text" name="ficheros[0].nombreUpload" class="form-control" id="nombreFicheroInfo_0" readonly="readonly" style="width:200px; display:inline-block" />
							</div>
						</div>
					</div>
					<div class="form-group col-lg-6">
						<label for="obsCorreccionProv_detail_table" class="control-label col-xs-12"><spring:message code="label.observaciones"/> (*):</label>
						<div class="col-xs-12">
							<textarea  name="observaciones" class="form-control" id="obsCorreccionProv_detail_table" style="resize: none" rows="6" maxlength="2000"></textarea>
						</div>	
					</div>
				</div>	
			</div>
		</form>
	</div>
	<div id="subidaFicheroPid" class="capaPIDenPestanaDoc oculto">
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
</div>