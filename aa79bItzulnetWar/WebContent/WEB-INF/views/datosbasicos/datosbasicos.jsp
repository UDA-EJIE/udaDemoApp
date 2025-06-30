<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid">
	<h2>
		<spring:message code="menu.datosBasicos" />
	</h2>
	<div id="datosBasicos_feedback"></div>
	<div id="accordionDatosBasicos" class="rup_accordion">
		<h1>
			<a><spring:message code="label.calculoPlazoMinimoRequerido" /></a>
		</h1>
		<div>
			<div id="plazoMinimoRequerido_toolbar" class="formulario_legend"></div>
			<form:form id="plazoMinimoRequerido_form"
				modelAttribute="datosBasicos">
				<form:hidden path="usuarioNormal.id" />
				<form:hidden path="usuarioNormal.nivelUsuario" />
				<form:hidden path="usuarioVIP.id" />
				<form:hidden path="usuarioVIP.nivelUsuario" />
				<fieldset>
					<legend>
						<spring:message code="label.usuarioNormal" />
					</legend>
					<div class="row">
						<div class="form-group col-xs-6 col-md-4">
							<label for="usuarioNormal_palabrasTradHora" class="control-label"><spring:message
									code="label.palabrasTradHora" /> (*):</label>
							<form:input id="usuarioNormal_palabrasTradHora"
								path="usuarioNormal.palabrasTradHora"
								class="form-control col-30por" maxlength="4" />
						</div>
						<div class="form-group col-xs-6 col-md-4">
							<label for="usuarioNormal_palabrasRevHora" class="control-label"><spring:message
									code="label.palabrasRevHora" /> (*):</label>
							<form:input id="usuarioNormal_palabrasRevHora"
								path="usuarioNormal.palabrasRevHora"
								class="form-control col-30por" maxlength="4" />
						</div>
						<div class="form-group col-xs-6 col-md-4">
							<label for="usuarioNormal_horasGestionTrad" class="control-label"><spring:message
									code="label.horasGestionTrad" /> (*):</label>
							<form:input id="usuarioNormal_horasGestionTrad"
								path="usuarioNormal.horasGestionTrad"
								class="form-control col-30por" maxlength="3" />
						</div>
					</div>
					<div class="row">
						<div class="form-group col-xs-6 col-md-4">
							<label for="usuarioNormal_horasGestionRev" class="control-label"><spring:message
									code="label.horasGestionRev" /> (*):</label>
							<form:input id="usuarioNormal_horasGestionRev"
								path="usuarioNormal.horasGestionRev"
								class="form-control col-30por" maxlength="3" />
						</div>
						<div class="form-group col-xs-6 col-md-4">
							<label for="usuarioNormal_horasEmisionPresupuesto"
								class="control-label"><spring:message
									code="label.horasEmisionPresupuesto" /> (*):</label>
							<form:input id="usuarioNormal_horasEmisionPresupuesto"
								path="usuarioNormal.horasEmisionPresupuesto"
								class="form-control col-30por" maxlength="3" />
						</div>
						<div class="form-group col-xs-6 col-md-4">
							<label for="usuarioNormal_horasAceptPresupuesto"
								class="control-label"><spring:message
									code="label.horasAceptPresupuesto" /> (*):</label>
							<form:input id="usuarioNormal_horasAceptPresupuesto"
								path="usuarioNormal.horasAceptPresupuesto"
								class="form-control col-30por" maxlength="3" />
						</div>
					</div>
				</fieldset>
				<fieldset>
					<legend>
						<spring:message code="label.usuarioVIP" />
					</legend>
					<div class="row">
						<div class="form-group col-xs-6 col-md-4">
							<label for="usuarioVIP_palabrasTradHora" class="control-label"><spring:message
									code="label.palabrasTradHora" /> (*):</label>
							<form:input id="usuarioVIP_palabrasTradHora"
								path="usuarioVIP.palabrasTradHora"
								class="form-control col-30por" maxlength="4" />
						</div>
						<div class="form-group col-xs-6 col-md-4">
							<label for="usuarioVIP_palabrasRevHora" class="control-label"><spring:message
									code="label.palabrasRevHora" /> (*):</label>
							<form:input id="usuarioVIP_palabrasRevHora"
								path="usuarioVIP.palabrasRevHora" class="form-control col-30por"
								maxlength="4" />
						</div>
						<div class="form-group col-xs-6 col-md-4">
							<label for="usuarioVIP_horasGestionTrad" class="control-label"><spring:message
									code="label.horasGestionTrad" /> (*):</label>
							<form:input id="usuarioVIP_horasGestionTrad"
								path="usuarioVIP.horasGestionTrad"
								class="form-control col-30por" maxlength="3" />
						</div>
					</div>
					<div class="row">
						<div class="form-group col-xs-6 col-md-4">
							<label for="usuarioVIP_horasGestionRev" class="control-label"><spring:message
									code="label.horasGestionRev" /> (*):</label>
							<form:input id="usuarioVIP_horasGestionRev"
								path="usuarioVIP.horasGestionRev" class="form-control col-30por"
								maxlength="3" />
						</div>
						<div class="form-group col-xs-6 col-md-4">
							<label for="usuarioVIP_horasEmisionPresupuesto"
								class="control-label"><spring:message
									code="label.horasEmisionPresupuesto" /> (*):</label>
							<form:input id="usuarioVIP_horasEmisionPresupuesto"
								path="usuarioVIP.horasEmisionPresupuesto"
								class="form-control col-30por" maxlength="3" />
						</div>
						<div class="form-group col-xs-6 col-md-4">
							<label for="usuarioVIP_horasAceptPresupuesto"
								class="control-label"><spring:message
									code="label.horasAceptPresupuesto" /> (*):</label>
							<form:input id="usuarioVIP_horasAceptPresupuesto"
								path="usuarioVIP.horasAceptPresupuesto"
								class="form-control col-30por" maxlength="3" />
						</div>
					</div>
				</fieldset>
			</form:form>
		</div>
		<h1>
			<a><spring:message code="label.calculoHorasPrevistas" /></a>
		</h1>
		<div>
			<div id="calculoHorasPrevistas_toolbar" class="formulario_legend"></div>
			<form:form id="calculoHorasPrevistas_form" method="post"
				modelAttribute="datosBasicos">
				<form:hidden id="configHorasPrevistasLimPalConcor_id" path="configHorasPrevistasLimPalConcor.id" />
				<fieldset>
					<div class="row">
						<div class="form-group col-xs-6 col-md-4">
							<label for="configHorasPrevistasLimPalConcor_limPalConcor"
								class="control-label"><spring:message
									code="label.limPalConcor" /> (*):</label>
							<form:input id="configHorasPrevistasLimPalConcor_limPalConcor"
								path="configHorasPrevistasLimPalConcor.limPalConcor"
								class="form-control col-30por" maxlength="6" />
						</div>
						<div class="form-group col-xs-6 col-md-4">
							<label for="configHorasPrevistasLimPalConcor_tiempoExtraGest"
								class="control-label"><spring:message
									code="label.tiempoExtraGest" /> (*):</label>
							<form:input id="configHorasPrevistasLimPalConcor_tiempoExtraGest"
								path="configHorasPrevistasLimPalConcor.tiempoExtraGest"
								class="form-control col-50por campohora10" placeholder="hh:mm" maxlength="10" />
						</div>
					</div>
				</fieldset>
			</form:form>
					<div id="configHorasPrevistas_div" class="rup-table-container formulario_legend">
						<div id="configHorasPrevistas_toolbar"></div>
						<div id="configHorasPrevistass_filter_div" class="rup-table-filter"
							style="display: none">
							<!-- Capa contenedora del formulario de filtrado -->
							<form id="configHorasPrevistas_filter_form">
<!-- 								Formulario de filtrado -->
								<div id="configHorasPrevistas_filter_toolbar"></div>
<!-- 								Barra de herramientas del formulario de filtrado -->
								<fieldset id="configHorasPrevistas_filter_fieldset"
									class="rup-table-filter-fieldset">
									<div class="formulario_columna_cnt"></div>
								</fieldset>
							</form>
						</div>
						<div id="configHorasPrevistas_grid_div">
							<input type="hidden" name="id"
								id="id_detail_filter_table">
							<!-- Tabla -->
							<table id="configHorasPrevistas"></table>
							<!-- Barra de paginación -->
							<div id="configHorasPrevistas_pager"></div>
						</div>
					</div>
		</div>
		<!-- mishel -->
		<h1>
			<a><spring:message code="label.calculoHorasPrevistasProveExt" /></a>
		</h1>
		<div>
			<div id="calculoHorasPrevistasProveExt_toolbar" class="formulario_legend"></div>
			<form:form id="calculoHorasPrevistasProveExt_form" method="post"
				modelAttribute="datosBasicos">
				<form:hidden id="configHorasPrevistasProveExt_id"  path="configHorasPrevistasProvExtLimPalConcor.id" />
					<fieldset>
					<div class="row">
						<div class="form-group col-xs-6 col-md-4">
							<label for="configHorasPrevistasProveExt_limPalConcor"
								class="control-label"><spring:message
									code="label.limPalConcorProvExt" /> (*):</label>
							<form:input id="configHorasPrevistasProveExt_limPalConcor"
								path="configHorasPrevistasProvExtLimPalConcor.limPalConcor"
								class="form-control col-30por" maxlength="6" />
						</div>
					</div>
					</fieldset>
			</form:form>
			<div id="configHorasPrevistasProveExt_div" class="rup-table-container formulario_legend">
						<div id="configHorasPrevistasProveExt_toolbar"></div>
						<div id="configHorasPrevistassProveExt_filter_div" class="rup-table-filter"
							style="display: none">
							<!-- Capa contenedora del formulario de filtrado -->
							<form id="configHorasPrevistasProveExt_filter_form">
<!-- 								Formulario de filtrado -->
								<div id="configHorasPrevistasProveExt_filter_toolbar"></div>
<!-- 								Barra de herramientas del formulario de filtrado -->
								<fieldset id="configHorasPrevistasProveExt_filter_fieldset"
									class="rup-table-filter-fieldset">
									<div class="formulario_columna_cnt"></div>
								</fieldset>
							</form>
						</div>
						<div id="configHorasPrevistasProveExt_grid_div">
							<input type="hidden" name="id"
								id="id_detail_filter_table">
							<!-- Tabla -->
							<table id="configHorasPrevistasProveExt"></table>
							<!-- Barra de paginación -->
							<div id="configHorasPrevistasProveExt_pager"></div>
						</div>
					</div>
		</div>
		<!-- termina mishel -->
		<h1>
			<a><spring:message code="label.calculoPresupuestoTrados" /></a>
		</h1>
		<div>
			<div id="calculoPresupuestoTrados_toolbar" class="formulario_legend"></div>
			<form:form id="calculoPresupuestoTrados_form" method="post"
				modelAttribute="datosBasicos">
				<form:hidden path="configCalculoPresupuesto.id" />
				<fieldset>
					<div class="row">
						<div class="form-group col-xs-6 col-md-4">
							<label for="configCalculoPresupuesto_palTrados"
								class="control-label"><spring:message
									code="label.palTrados" /> (*):</label>
							<form:input id="configCalculoPresupuesto_palTrados"
								path="configCalculoPresupuesto.palTrados"
								class="form-control col-30por" maxlength="6" />
						</div>
						<div class="form-group col-xs-6 col-md-4">
							<label for="configCalculoPresupuesto_palPresupuesto"
								class="control-label"><spring:message
									code="label.palPresupuesto" /> (*):</label>
							<form:input id="configCalculoPresupuesto_palPresupuesto"
								path="configCalculoPresupuesto.palPresupuesto"
								class="form-control col-30por" maxlength="6" />
						</div>
						<div class="form-group col-xs-6 col-md-4">
							<label for="configCalculoPresupuesto_porDesviacionPal"
								class="control-label"><spring:message
									code="label.porDesviacionPal" /> (*):</label>
							<form:input id="configCalculoPresupuesto_porDesviacionPal"
								path="configCalculoPresupuesto.porDesviacionPal"
								class="form-control col-30por" maxlength="3" />
						</div>
					</div>
				</fieldset>
			</form:form>
		</div>

		<h1>
			<a><spring:message code="label.perfilSolicitante" /></a>
		</h1>
		<div>
			<div id="perfilSolicitante_toolbar" class="formulario_legend"></div>
			<form:form id="perfilSolicitante_form" method="post"
				modelAttribute="datosBasicos">
				<form:hidden path="configPerfilSolicitante.id" />
				<fieldset>
					<div class="row">
						<div class="form-group col-xs-12 col-md-3">
							<label for="configPerfilSolicitante_diasNoConformidad"
								class="control-label"><spring:message
									code="label.diasNoConformidad" /> (*):</label>
							<form:input id="configPerfilSolicitante_diasNoConformidad"
								path="configPerfilSolicitante.diasNoConformidad"
								class="form-control col-30por" maxlength="3" />
						</div>
						<div class="form-group col-xs-12 col-md-9">
							<label for="configPerfilSolicitante_urlAyudaNumPal"
								class="control-label"><spring:message
									code="label.urlAyudaNumPal" /> (*):</label>
							<form:input id="configPerfilSolicitante_urlAyudaNumPal"
								path="configPerfilSolicitante.urlAyudaNumPal"
								class="form-control" maxlength="250" />
						</div>
					</div>
				</fieldset>
				<fieldset>
					<legend>
						<spring:message code="label.declaracionResponsable" />
					</legend>
					<%-- <div class="row">
						<div class="form-group col-xs-12 col-md-12">
							<label for="configPerfilSolicitante_declaracionResponsable"
								class="control-label"><spring:message
									code="label.declaracionResponsable" />:</label>
						</div>
					</div> --%>
					<div class="row">
						<div class="col-xs-12 col-md-6">
							<!-- <fieldset>
								<legend>
									<spring:message code="label.castellano" />
									(*):
								</legend>-->
								<label for="configPerfilSolicitante_declaracionResponsableEs"
								class="control-label"><spring:message
									code="label.castellano" /> (*):</label>
								<div class="form-group ">
									<form:textarea
										id="configPerfilSolicitante_declaracionResponsableEs"
										path="configPerfilSolicitante.declaracionResponsableEs"
										class="form-control" rows="4" />
								</div>
							<!-- </fieldset> -->
						</div>
						<div class="col-xs-12 col-md-6">
							<%-- <fieldset>
								<legend>
									<spring:message code="label.euskera" />
									(*):
								</legend> --%>
								<label for="configPerfilSolicitante_declaracionResponsableEu"
								class="control-label"><spring:message
									code="label.euskera" /> (*):</label>
								<div class="form-group ">
									<form:textarea
										id="configPerfilSolicitante_declaracionResponsableEu"
										path="configPerfilSolicitante.declaracionResponsableEu"
										class="form-control" rows="4" />
								</div>
							<!-- </fieldset> -->
						</div>
					</div>
				</fieldset>
			</form:form>
		</div>
		<h1>
			<a><spring:message code="label.pagoProveeedores" /></a>
		</h1>
		<div>
			
			<div id="pagoProveedores_div" class="rup-table-container formulario_legend">
				<div id="pagoProveedores_toolbar"></div>
				<div id="pagoProveedores_filter_div" class="rup-table-filter"
					style="display: none">
					<!-- Capa contenedora del formulario de filtrado -->
					<form id="pagoProveedores_filter_form">
						<!-- Formulario de filtrado -->
						<div id="pagoProveedores_filter_toolbar"></div>
						<!-- Barra de herramientas del formulario de filtrado -->
						<fieldset id="pagoProveedores_filter_fieldset"
							class="rup-table-filter-fieldset">
							<div class="formulario_columna_cnt"></div>
						</fieldset>
					</form>
				</div>
				<div id="pagoProveedores_grid_div">
					<input type="hidden" name="idEmpresaProv029"
						id="idEmpresaProv029_detail_filter_table">
					<!-- Tabla -->
					<table id="pagoProveedores"></table>
					<!-- Barra de paginación -->
					<div id="pagoProveedores_pager"></div>
				</div>
			</div>
			

			<!-- Formulario de detalle -->
			<div id="pagoProveedores_detail_div"
				class="rup-table-formEdit-detail">
				<!-- Barra de navegación del detalle -->
				<div class="ui-dialog-content ui-widget-content">
					<form id="pagoProveedores_detail_form">
						<!-- Formulario -->
						<input type="hidden" name="id">
						<div id="pagoProveedores_detail_feedback"></div>
						<!-- Feedback del formulario de detalle -->
						<div class="row">
							<div class="form-group col-lg-4">
								<label for="porIva_detail_table" class="control-label"><spring:message
										code="label.porIva" /> (*):</label> <input type="text" name="porIva"
									class="form-control col-50por digits" id="porIva_detail_table"
									maxlength="2" />
							</div>
							<div class="form-group col-lg-3">
								<label for="vigente_detail_table" class="control-label"><spring:message
										code="label.enVigor" />:</label> <input type="checkbox"
									name="vigente" class="form-control" id="vigente_detail_table"
									value="<%=Constants.SI%>" />
							</div>
						</div>
						<div class="row">
							<div class="form-group  col-lg-4">
								<label for="fechaDesde_detail_table"
									class="control-label valFecha"><spring:message
										code="label.fechaDesde" /> (*):</label> <input type="text"
									name="fechaDesde" class="form-control"
									id="fechaDesde_detail_table" maxlength="10" />
							</div>
							<div class="form-group  col-lg-4">
								<label for="fechaHasta_detail_table"
									class="control-label valFecha"><spring:message
										code="label.fechaHasta" />:</label> <input type="text"
									name="fechaHasta" class="form-control"
									id="fechaHasta_detail_table" maxlength="10" />
							</div>
						</div>
					</form>
				</div>
				<!-- Botonera del formulario de detalle -->
				<div
					class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
					<div class="ui-dialog-buttonset">
						<!-- Botón Guardar -->
						<button id="pagoProveedores_detail_button_save" type="button">
							<spring:message code="save" />
						</button>
						<!-- Enlace cancelar -->
						<a href="javascript:void(0)"
							id="pagoProveedores_detail_link_cancel"
							class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
					</div>
				</div>
			</div>

		</div>
		<h1>
			<a><spring:message code="label.configEmailsAviso" /></a>
		</h1>
		<div>
			<div id="configEmailsAviso_toolbar" class="formulario_legend"></div>
			<form:form id="configEmailsAviso_form" method="post"
				modelAttribute="datosBasicos">
				<fieldset>
				<form:hidden path="configDireccionEmail.id" />
				<form:hidden path="configDireccionEmailInterpretacion.id" />
				<form:hidden path="configHorasPrevistasLimPalConcor.id" />
				<div id="contenFormularios" class="filterForm">
					<div id="tipoRelevancia_filter_toolbara" class="">
						
							<%-- 					<legend><spring:message code="label.avisos"/></legend> --%>
							<div class="row">
								<div class="form-group col-xs-6 col-md-4">
									<label for="configDireccionEmail_dirEmail"
										class="control-label"><spring:message
											code="label.remitente" /> (*):</label>
									<form:input id="configDireccionEmail_dirEmail"
										path="configDireccionEmail.dirEmail" class="form-control"
										maxlength="150" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-6 col-md-4">
									<label for="configDireccionEmailInterpretacion_dirEmail"
										class="control-label"><spring:message
											code="label.emailAvisosInterpretacion" /> (*):</label>
									<form:input id="configDireccionEmailInterpretacion_dirEmail"
										path="configDireccionEmailInterpretacion.dirEmail" class="form-control"
										maxlength="500" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-2" id="listaSelTipoAviso">
									<div class="list-group" id="list-tab" role="tablist">
										<c:forEach items="${tiposAviso}" var="tipoAviso">
											<a class="list-group-item list-group-item-action"
												data-toggle="list" href="#texto_${tipoAviso.id}" role="tab">${SESSION_LANGUAGE eq LANGUAGE_ES ? tipoAviso.descEs : tipoAviso.descEu}</a>
										</c:forEach>
									</div>
								</div>
								<div class="col-md-10">
									<div class="tab-content" id="nav-tabContent">
										<c:forEach items="${datosBasicos.textoEmails}"
											var="textoEmail" varStatus="loop">
											<div class="tab-pane fade" id="texto_${textoEmail.id}"
												role="tabpanel" aria-labelledby="list-profile-list">
												<form:hidden path="textoEmails[${loop.index}].id" />
												<div class="col-xs-12">
													<label for="destinatario_${loop.index}"
														class="control-label"><spring:message
															code="label.destinatario" />:</label>
													<form:input id="destinatario_${loop.index}"
														path="textoEmails[${loop.index}].tipoAviso.destinatarioDesc"
														class="form-control col-20por" readonly="true" />
												</div>
												<div class="col-xs-12 col-md-6">
													<fieldset>
														<legend>
															<spring:message code="label.castellano" />
														</legend>
														<div class="form-group">
															<label for="asuntoEs_${loop.index}" class="control-label"><spring:message
																	code="label.asunto" />:</label>
															<form:input id="asuntoEs_${loop.index}"
																path="textoEmails[${loop.index}].asuntoEs"
																class="form-control" maxlength="150" />
														</div>
														<div class="form-group">
															<label for="textoEs_${loop.index}" class="control-label"><spring:message
																	code="label.texto" />:</label>
															<form:textarea id="textoEs_${loop.index}"
																path="textoEmails[${loop.index}].textoMailEs"
																class="form-control" />
														</div>
													</fieldset>
												</div>
												<div class="col-xs-12 col-md-6">
													<fieldset>
														<legend>
															<spring:message code="label.euskera" />
														</legend>
														<div class="form-group">
															<label for="asuntoEu_${loop.index}" class="control-label"><spring:message
																	code="label.asunto" />:</label>
															<form:input id="asuntoEu_${loop.index}"
																path="textoEmails[${loop.index}].asuntoEu"
																class="form-control" maxlength="150" />
														</div>
														<div class="form-group">
															<label for="textoEu_${loop.index}" class="control-label"><spring:message
																	code="label.texto" />:</label>
															<form:textarea id="textoEu_${loop.index}"
																path="textoEmails[${loop.index}].textoMailEu"
																class="form-control" />
														</div>
													</fieldset>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						
					</div>
				</div>
				</fieldset>
			</form:form>
		</div>

		<h1>
			<a><spring:message code="label.libroRegistro" /></a>
		</h1>
		<div>
			<div id="libroRegistro_toolbar" class="formulario_legend"></div>
			<form:form id="libroRegistro_form" method="post"
				modelAttribute="datosBasicos">
				<form:hidden path="libroRegistro.id" />
				<fieldset>
					<div class="row">
						<div class="form-group col-lg-1">
							<label for="libroRegistro_indActivo" class="control-label"><spring:message
									code="label.activo" />(*):</label>
							<form:checkbox id="libroRegistro_indActivo"
								path="libroRegistro.indActivo" data-switch="true"
								class="form-control" value="<%=Constants.SI%>" />
						</div>
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</div>