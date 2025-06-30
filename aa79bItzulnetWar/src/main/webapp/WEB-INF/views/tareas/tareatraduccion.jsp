<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in  aa79b-content" id="divEjecutarTareaCapa">
	<div id="tareaTraduccion_div">
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
	
<!-- 			<fieldset> -->
<!-- 			<legend> -->
<%-- 				<spring:message code="label.datosExpediente"></spring:message> --%>
<!-- 			</legend> -->
			<div id="pestanasModalTraduccion">
				<div id="tabsModalTraduccion" class="tabsEjecutarTarea"></div>
			</div>
			<div id="pestDocsTraducir">
				<div id="docsTraducir_div">
					<div id="docsTraducir_feedback"></div>						<!-- Feedback de la tabla --> 
	<!-- 				<div id="docsTraducir_toolbar"></div>							Botonera de la tabla -->
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
			
<!-- 			</fieldset> -->
				
		<div id="subidaFicheroPid" class="capaPIDenPestanaDoc oculto">
			<fieldset id="subidaFicheroPid_fieldset" >			
				<div id="subidaFicheroPid_feedback"></div>
				<form id="subidaFicheroPid_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
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
	</form>
</div>




