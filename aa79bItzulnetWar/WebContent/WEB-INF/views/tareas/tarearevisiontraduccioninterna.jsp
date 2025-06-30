<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in  aa79b-content" id="divEjecutarTareaCapa">
	<div id="tareaRevisarTradInt_div">
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
		<div id="pestanasModalRevTradInt">
			<div id="tabsModalRevTradInt" class="tabsEjecutarTarea"></div>
		</div>
	
		<input type="hidden" id="idiomaDest" name="idiomaDest" />
		<div id="pestDocsRevisarTradInt">
			<div id="docsRevisarTradInt_div">
				<div id="docsRevisarTradInt_feedback"></div>						<!-- Feedback de la tabla -->
				<div id="docsRevisarTradInt_toolbar"></div>							<!-- Botonera de la tabla -->
				<div id="contenFormularios" class="filterForm oculto">	
					<form id="docsRevisarTradInt_filter_form" class="form-horizontal">						<!-- Formulario de filtrado -->
						<div id="docsRevisarTradInt_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
						<fieldset id="docsRevisarTradInt_filter_fieldset" class="rup-table-filter-fieldset">
							<input type="text" name="idTarea" id="idTarea_filter">
						</fieldset>
					</form>
				</div>
				<div class="horizontal_scrollable_table">
					<!-- Tabla -->
					<table id="docsRevisarTradInt"></table>
					<!-- Barra de paginación -->
					<div id="docsRevisarTradInt_pager"></div>
				</div>
				<div class="row form-group">
					<div class="col-xs-5">
						<div class="col-xs-1">
							<i class="fa fa-unlock" aria-hidden="true"></i>
						</div>
						<label class="control-label col-xs-10">
							<spring:message code="label.documento.docNoEncriptado"/>
						</label>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-xs-5">	
						<div class="col-xs-1">				
							<i class='fa fa-lock'></i>
						</div>	
						<label class="control-label col-xs-10">
							<spring:message code="label.documento.docEncriptado"/>
						</label>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-xs-5">
						<div class="col-xs-1">
							<i class="fa fa-file" aria-hidden="true"></i>
						</div>
						<label class="control-label col-xs-10">
							<spring:message code="label.documento.docOriginalTrad"/>
						</label>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-xs-5">	
						<div class="col-xs-1">				
							<i class='fa fa-file-text'></i>
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
							<input type="text" name="idTareaRel" id="idTareaRel_filter_Xliff">
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
						<input type="hidden" id="reqEncriptado" name="reqEncriptado" />
					</div>
				</form>
				<p class="scheduler-border" style="text-align: center;" id="txtEncriptadoFinal"><em><strong><spring:message code="mensaje.fichero.encriptadoRequerido" /></strong></em></p>
			</fieldset>
		</div>
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
	<form id="ejecutarTareaDialog_form">
		<input type="hidden" name="numExp" id="numExp_form" >
		<input type="hidden" name="anyo" id="anyo_form" >
		<input type="hidden" name="idTarea" id="idTarea_form" >
		<input type="hidden" name="tipoTarea.id015" id="idTipoTarea_form" >
		<input type="hidden" name="ejecucionTareas.horasTarea" id="horasTarea_form" >
		<input type="hidden" name="indFacturable" id="indFacturable_form_tradInt" >
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
<!-- <div id="notificarCorreccionProv_detail_div" class="aa79b-content-modal oculto" > -->
<!-- 	<div class="ui-dialog-content ui-widget-content" > -->
<!-- 		<div id="notificarCorreccionProv_detail_toolbar"></div>		Toolbar -->
<!-- 		<div id="notificarCorreccionProv_detail_feedback"></div>		Feedback del formulario de detalle -->
<!-- 		<form id="notificarCorreccionProv_detail_form">					Formulario -->
<!-- 			<div class="col-xs-12"> -->
<!-- 				<div class="row"> -->
<!-- 					<div class="form-group col-lg-6"> -->
<%-- 						<label for="documentacion_detail_table" class="control-label col-xs-4"><spring:message code="label.documentos"/> (*):</label>	 --%>
<!-- 						<div class="col-xs-12"> -->
<!-- 							<input type="hidden" name="ficheros[0].rutaPif" id="rutaPif_0"/> -->
<!-- 							<input type="hidden" name="ficheros[0].nombre" id="nombre_0"/> -->
<!-- 							<input type="hidden" name="ficheros[0].extension" id="extensionDoc056_0" readonly="readonly"/> -->
<!-- 							<input type="hidden" name="ficheros[0].tamano" id="tamanoDoc056_0" readonly="readonly"/> -->
<!-- 							<input type="hidden" name="ficheros[0].contentType" id="contentType056_0" readonly="readonly"/> -->
<!-- 							<input type="hidden" name="ficheros[0].encriptado" id="indEncriptado056_0"/> -->
<!-- 							<input type="hidden" name="ficheros[0].fechaAlta" id="fechaAlta056_0" maxlength="15" readonly="readonly"/> -->
<!-- 							<input type="hidden" name="ficheros[0].dniAlta" id="dniAlta056_0" maxlength="15" readonly="readonly"/> -->
<!-- 							<input type="hidden" name="ficheros[0].idDocRel" id="idDocRel056_0" readonly="readonly"/> -->
<!-- 							<input type="hidden" name="ficheros[0].idDocVersion" id="idDocVersion056_0" readonly="readonly"/> -->
<!-- 							<input type="hidden" name="ficheros[0].oid" id="oidFichero056_0" readonly="readonly"/> -->
<!-- 							<input type="hidden" name="ficheros[0].idDocInsertado" id="idDocInsertado_0" readonly="readonly"/> -->
												
<!-- 							<div class="form-group col-lg-12 padding-top-2">	 -->
<!-- 								<span id="enlaceDescargaDetalle_0" class="col-lg-12"></span>						 -->
<%-- 								<button id="pidButton_0" type="button" class="ui-button ui-corner-all ui-widget" style="margin-top:-4px"><spring:message code="label.adjuntarFichero"/></button> --%>
<!-- 								<input type="text" name="ficheros[0].nombreUpload" class="form-control" id="nombreFicheroInfo_0" readonly="readonly" style="width:200px; display:inline-block" /> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="form-group col-lg-6"> -->
<%-- 						<label for="obsCorreccionProv_detail_table" class="control-label col-xs-12"><spring:message code="label.observaciones"/> (*):</label> --%>
<!-- 						<div class="col-xs-12"> -->
<!-- 							<textarea  name="observaciones" class="form-control" id="obsCorreccionProv_detail_table" style="resize: none" rows="6" maxlength="2000"></textarea> -->
<!-- 						</div>	 -->
<!-- 					</div> -->
<!-- 				</div>	 -->
<!-- 			</div> -->
<!-- 		</form> -->
<!-- 	</div> -->
	
<!-- 	<div id="subidaFicheroPid" class="capaPIDenPestanaDoc oculto"> -->
<!-- 		<fieldset id="subidaFicheroPid_fieldset" >			 -->
<!-- 			<div id="subidaFicheroPid_feedback"></div> -->
<!-- 			<form id="subidaFicheroPid_form" enctype="multipart/form-data" method="POST" class="form-horizontal"> -->
<!-- 				<div class="form-group row"> -->
<%-- 					<label for="fichero" class="control-label col-md-3"><spring:message code="label.fichero" /> (*):</label> --%>
<!-- 					<div class="col-md-6" id="divFichero"> -->
<!-- 						<input type="file" name="fichero" id="fichero"  class="form-control"> -->
<!-- 					</div> -->
<!-- 					<input type="hidden" id="idBotonUpload" name="idBotonUpload" /> -->
<!-- 					<input type="hidden" id="reqEncriptado" name="reqEncriptado" /> -->
<!-- 				</div> -->
<!-- 			</form> -->
<%-- 			<p class="scheduler-border" style="text-align: center;" id="txtEncriptado"><em><strong><spring:message code="mensaje.fichero.encriptadoRequerido" /></strong></em></p> --%>
<!-- 		</fieldset> -->
<!-- 	</div> -->
<!-- </div> -->