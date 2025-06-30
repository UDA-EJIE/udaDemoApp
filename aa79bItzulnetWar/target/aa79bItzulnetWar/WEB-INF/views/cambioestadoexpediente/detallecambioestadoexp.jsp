<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="divDetalleCambioEstExp" class="aa79b-fade in">
	<h2><spring:message code="label.cambioEstado"></spring:message></h2>
	<div id="detalleCambioEstExp_div" class="rup-table-container">
		<div id="detalleCambioEstExp_feedback"></div>						<!-- Feedback de la tabla --> 
		<div id="detalleCambioEstExp_toolbar"></div>						<!-- Botonera de la tabla --> 
		<div id="detalleCambioEstExp_filter_div" class="filterForm"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="detalleCambioEstExp_filter_form">	
				<fieldset id="detalleCambioEstExp_filter_fieldset" class="rup-table-filter-fieldset filterCuerpo">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group col-xs-12 col-md-4">
								<label for="estadoExp_filter_table" class="control-label "><spring:message code="label.estado"/> (*):</label>
								<select name="bitacoraExpediente.estadoExp.id" class="form-control" id="estadoExp_filter_table"></select>
							</div>
							<div class="form-group col-xs-12 col-md-8">
								<label for="faseExp_filter_table" class="control-label "><spring:message code="label.fase"/> (*):</label>
								<select name="bitacoraExpediente.faseExp.id" class="form-control" id="faseExp_filter_table"></select>
							</div>
						</div>
					</div>
					<div id="divDatosAnulacionExp">
						<div class="row">
							<div class="form-group col-lg-3">
								<label for="idMotivosAnulacion_detail_table" class="control-label"><spring:message code="label.motivosAnulacion" /> (*):</label>
								<select  name="idMotivoAnulacion" class="form-control" id="idMotivosAnulacion_detail_table" ></select>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-12">
								<label for="observaciones_detail_table" class="control-label"><spring:message code="label.observaciones" />:</label>
								<textarea name="observacionesAnulExp.obsvAnulacion" class="form-control resizable-textarea" id="observaciones_detail_table" rows="4" cols="9" maxlength="4000"></textarea>
							</div>
						</div>
					</div>
					<div id="divDatosRechazoExp">
						<div class="row">
							<div class="form-group col-md-3">
								<label for="motivoRechazo" class="control-label" data-i18n="label.motivoRechazo"><spring:message code="label.motivoRechazo" /> (*):</label>
								<input id="motivoRechazo" name="motivoRechazo" class="form-control" />
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-9">
								<label class="control-label" for="descRechazo" data-i18n="comun.descripcion"><spring:message code="comun.descripcion"/>:</label>
								<textarea class="form-control resizable-textarea" id="descRechazo" name="descRechazo" rows="6" maxlength="4000" ></textarea>
							</div>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	
	<div id="leyendaCambioEstExp_div" class="rup-table-container">
		<div id="leyendaCambioEstExp_feedback"></div>						<!-- Feedback de la tabla --> 
		<div id="leyendaCambioEstExp_toolbar"></div>						<!-- Botonera de la tabla -->
		<div id="leyendaCambioEstExp_filter_div"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="leyendaCambioEstExp_filter_form">	
				<fieldset id="leyendaCambioEstExp_filter_fieldset" class="filterForm">
					<legend>
						<spring:message code="label.ayudaEstados"/>
					</legend>
					<div class="row">
						<div class="col-md-4">
							<c:forEach items="${estadosExpediente}" var="estadoExp">
								<div class="divEstadoExpLeyenda">
									<span class="negrita">
										<c:choose>
										    <c:when test="${SESSION_LANGUAGE eq LANGUAGE_EU}">
										    	${estadoExp.descEu}
										    </c:when>
										    <c:otherwise>
										    	${estadoExp.descEs}
										    </c:otherwise>
										</c:choose>
									</span>
									<c:forEach items="${fasesExpediente}" var="faseExp">
										<div>
											
											<c:if test="${estadoExp.id == faseExp.idEstadosExpediente}">
												
												<a href="#" class="confLeyenda" data-id="${estadoExp.id}_${faseExp.id}" >
													<c:choose>
													    <c:when test="${SESSION_LANGUAGE eq LANGUAGE_EU}">
													    	<input type="hidden" id="descFase${estadoExp.id}_${faseExp.id}" name="descFase${estadoExp.id}_${faseExp.id}" value="${faseExp.descEu}"/>
													    	<span class="spanFaseExpLeyenda">${faseExp.descEu}</span>
													    </c:when>
													    <c:otherwise>
													    	<input type="hidden" id="descFase${estadoExp.id}_${faseExp.id}" name="descFase${estadoExp.id}_${faseExp.id}" value="${faseExp.descEs}"/>
													    	<span class="spanFaseExpLeyenda">${faseExp.descEs}</span>
													    </c:otherwise>
													</c:choose>
												</a>
											
											</c:if>
											
										</div>
									</c:forEach>
									
								</div>
							</c:forEach>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>