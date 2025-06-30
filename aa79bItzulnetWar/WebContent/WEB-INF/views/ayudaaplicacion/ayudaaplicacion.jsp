<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid">
	<h2>
		<spring:message code="menu.edicionAyuda" />
	</h2>
	<hr class="mb-2">
	<div id="ayudaaplicacion_div" class="rup-table-container">
		<div id="ayudaaplicacion_feedback"></div>
		<div id="ayudaaplicacion_toolbar" class="rup-toolbar ui-widget-header ui-widget ui-widget-content">
			<div id="ayudaaplicacion_toolbar-rightButtons" class="dcha">
				<div id="ayudaaplicacion_toolbar##reports-mbutton-group" class="rup-mbutton ">
					<button type="button" data-mbutton="true" id="ayudaAplicacion_pdfCreatorButton" class="ui-button ui-corner-all ui-widget rup-button">
						<i class="fa fa-file-pdf-o" aria-hidden="true"></i>
						<span class="rup-ui-button-text"><spring:message code="exportarPDF"/></span>
					</button>
				</div>
			</div>
		</div>							<!-- Botonera de la tabla -->
		<!-- Feedback de la tabla -->
		<div id="contenFormularios" class="filterForm ">
			<!-- Capa contenedora del formulario de filtrado -->
			<form id="ayudaAplicacion_filter_form" Class="form-horizontal">
				<!-- Formulario de filtrado -->
				<div id="ayudaAplicacion_filter_toolbar" class="formulario_legend"></div>
				<!-- Barra de herramientas del formulario de filtrado -->
				
				<div class="row ">
					<!-- Campos del formulario de filtrado -->
					<div class="col-xs-12 col-md-3 arbol-ayuda">
						<div class="form-group">
							<label for="uniqueDemo" class="control-label col-xs-12" ><spring:message code="label.seccionesAyuda"/>:</label>
							<div class="col-xs-12">
								<div id="uniqueDemoButtons_toolbar" class="rup-toolbar ui-widget-header ui-widget ui-widget-content">
									<div id="uniqueDemoButtons_toolbar-rightButtons">
										<div id="uniqueDemoButtons_toolbar##reports-mbutton-group" class="rup-mbutton" style="width:100%;">
											<button type="button" data-mbutton="true" id="ayudaAplicacion_create_createButton" class="ui-button ui-corner-all ui-widget rup-button">
												<i class="fa fa-file-o" aria-hidden="true"></i>
												<span class="rup-ui-button-text"><spring:message code="crear"/></span>
											</button>
											<button type="button" data-mbutton="true" id="ayudaAplicacion_rename_renameButton" class="ui-button ui-corner-all ui-widget rup-button">
												<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
												<span class="rup-ui-button-text"><spring:message code="renombrar"/></span>
											</button>
											<button type="button" data-mbutton="true" id="ayudaAplicacion_remove_removeButton" class="ui-button ui-corner-all ui-widget rup-button">
												<i class="fa fa-remove" aria-hidden="true"></i>
												<span class="rup-ui-button-text"><spring:message code="eliminar"/></span>
											</button>
										</div>
									</div>
								</div>
								<div id="uniqueDemo" class="rup_tree"></div>
							</div>
						</div>
						
					</div>
					
					<!-- Campos del formulario de filtrado -->
					<div class="col-xs-12 col-md-9 ayuda-app-content">
						<div id="ayudaAplicacion_content_div">
							<div class="form-group">
								<label id="labelContenidoAyuda" for="uniqueDemo" class="control-label col-xs-12 label-grande"><spring:message code="label.contenidoAyuda"/>:</label>
								<div id="contenido_texto" class="col-xs-12" style="padding-right: 1px; padding-bottom: 15px;">
									<textarea name="contenido_form" id="contenido_form"></textarea>
									<input type="hidden" name="idAyudaContenido" id="idAyudaContenido" >
									<input type="hidden" name="contenidoAyuda" id="contenidoAyuda" >
								</div>
							</div>

							<!-- Botonera del formulario de filtrado -->
							<div class="form-group ">
								<div id="ayudaAplicacion_filter_buttonSet" class="pull-right ">
									<!-- Botón de filtrado -->
									<button id="ayudaAplicacion_save_saveButton" type="button"
										class="ui-button ui-widget ui-state-default ui-corner-all">
										<spring:message code="save" />
									</button>
									<a id="ayudaAplicacion_cancel_cancelButton" href="javascript:void(0)"
										class="rup-enlaceCancelar">
										<spring:message code="cancel" />
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<input type="file" id="fileOculto" style="position:absolute;top:-100px;"/>
