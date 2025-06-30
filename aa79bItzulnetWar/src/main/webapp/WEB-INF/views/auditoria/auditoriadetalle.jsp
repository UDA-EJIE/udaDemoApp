<%@include file="/WEB-INF/includeTemplate.inc"%> 
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum" %>
<%@page import="com.ejie.aa79b.model.enums.AuditoriaTipoSeccionEnum" %>
<%@page import="com.ejie.aa79b.model.enums.EstadoAuditoriaEnum" %>
<c:set var="ACTIVO_SI" value="<%=ActivoEnum.SI.getValue()%>"/>
<c:set var="ACTIVO_NO" value="<%=ActivoEnum.NO.getValue()%>"/>
<c:set var="TIPO_SECCION_VALORACION" value="<%=AuditoriaTipoSeccionEnum.VALORACION.getValue()%>"/>
<c:set var="TIPO_SECCION_CONDICION" value="<%=AuditoriaTipoSeccionEnum.CONDICION.getValue()%>"/>
<c:set var="TIPO_SECCION_LIBRE" value="<%=AuditoriaTipoSeccionEnum.LIBRE.getValue()%>"/>
<c:set var="ESTADO_AUDITORIA_SIN_ENVIAR" value="<%=EstadoAuditoriaEnum.SIN_ENVIAR.getValue()%>"/>
<c:set var="ESTADO_AUDITORIA_CONFIRMADA" value="<%=EstadoAuditoriaEnum.CONFIRMADA.getValue()%>"/>

<div id="divDetalleAuditoria" class="rup-table-container">
	
	<div id="detalleAuditoria_datos">
		<div id="detalleAuditoria_toolbar"></div>
		<div id="detalleAuditoria_feedback"></div>
		<div id="detalleAuditoria_error" style="display:none;">
			<span>No se han podido cargar los datos de la auditoría.</span> 
		</div>
		<div id="detalleAuditoria_datos_content" class="detalle-auditoria ">
			<div class="titulo">
				<spring:message code="label.auditoriaDetalleTitle"/>
			</div>
			<form:form id="auditoria_datos_form" class="form-group" modelAttribute="auditoriaDatos">
				<form:input type="hidden" id="auditoria_datos_form_id" path="idAuditoria"readonly="true"/>
				<form:input type="hidden" id="auditoria_datos_form_anyo" path="anyo"readonly="true"/>
				<form:input type="hidden" id="auditoria_datos_form_numExp" path="numExp"readonly="true"/>
				<form:input type="hidden" id="auditoria_datos_form_estado" path="estado"readonly="true"/>
				<form:input type="hidden" id="auditoria_datos_form_dnicontactolote" path="lote.contacto.dni"readonly="true"/>
				<form:input type="hidden" id="auditoria_datos_form_idTarea" path="idTarea"readonly="true"/>
				<form:input type="hidden" id="auditoria_datos_form_idTareaAuditar" path="idTareaAuditar"readonly="true"/>
				<div class="row content-adjust">
					<!-- anyoNumexp -->
					<div class="col-lg-2 detalle-auditoria-input detalle-auditoria-form-field">
						<label for="auditoria_datos_form_anyonumexpconcatenado"><spring:message code="label.numeroExpediente"/></label>
						<form:input id="auditoria_datos_form_anyonumexpconcatenado" path="anyoNumExpConcatenado" class="form-control" readonly="true" disabled="true"/>
					</div>
					<!-- lote -->
					<div class="col-lg-2 detalle-auditoria-form-field">
						<label for="auditoria_datos_form_lote"><spring:message code="label.lote"/></label>
						<c:choose>
						    <c:when test="${SESSION_LANGUAGE eq LANGUAGE_EU}">
						    	<form:input id="auditoria_datos_form_lote" path="lote.descLoteEu" class="form-control ellipsis" readonly="true" disabled="true"/>
						    </c:when>
						    <c:otherwise>
						    	<form:input id="auditoria_datos_form_lote" path="lote.descLoteEs" class="form-control ellipsis" readonly="true" disabled="true"/>
						    </c:otherwise>
						</c:choose>
					</div>
					<!-- estadoDesc -->
					<div class="col-lg-2 detalle-auditoria-input detalle-auditoria-form-field" style="min-width: 178px;">
						<label for="auditoria_datos_form_estado_desc"><spring:message code="label.estadoAuditoria"/></label>
						<c:choose>
						    <c:when test="${SESSION_LANGUAGE eq LANGUAGE_EU}">
						    	<form:input id="auditoria_datos_form_estado_desc" path="estadoDescEu" class="form-control" readonly="true" disabled="true"/>
						    </c:when>
						    <c:otherwise>
						    	<form:input id="auditoria_datos_form_estado_desc" path="estadoDescEs" class="form-control" readonly="true" disabled="true"/>
						    </c:otherwise>
						</c:choose>
					</div>
					<!-- fecha realizacion control calidad -->
					<div class="col-md-12 col-lg-2 detalle-auditoria-fecha detalle-auditoria-form-field"  style="min-width: 300px;">
						<label for="auditoria_datos_form_fechaEnvio"><spring:message code="label.fechaRealizacionControlCalidad"/></label>
						<form:input id="auditoria_datos_form_fechaEnvio" path="fechaHoraEnvio" class="form-control" readonly="true" disabled="true"/>
					</div>
					<!-- fecha confirmacion auditoria-->
					<c:if test="${auditoriaDatos.estado == ESTADO_AUDITORIA_CONFIRMADA}">
						<div class="col-md-12 col-lg-3 detalle-auditoria-fecha detalle-auditoria-form-field"  style="min-width: 260px;">
							<label for="auditoria_datos_form_fechaConfirmacion"><spring:message code="label.fechaConfirmacionAuditoria"/></label>
							<form:input id="auditoria_datos_form_fechaConfirmacion" path="fechaHoraConfirmacion" class="form-control" readonly="true" disabled="true"/>
						</div>
					</c:if>
				</div>
			</form:form>
			<!-- comprobar seccion vacia en service. Si vacia no meter en modelo para mostrar -->
			<c:forEach var="seccion" items="${auditoriaSecciones}">
				<div id="seccion_container_${seccion.idSeccion}" class="${seccion.indRespuesta == ACTIVO_SI ? 'ind-respuesta' : '' }" data-idseccion="${seccion.idSeccion}" data-tiposeccion="${seccion.tipoSeccion}">
						<div class="${seccion.indRespuesta == ACTIVO_SI ? 'titulo-seccion-respuesta' : 'titulo-seccion' }">
							<c:out value="${seccion.nombre}" />
						</div>
						<input type="hidden" id="indObservaciones_${seccion.idSeccion}" value="${seccion.indObservaciones}">
						<c:if test="${seccion.indObservaciones == ACTIVO_SI}">
							<div class="form-group content-adjust">
								<label for="auditoria_seccion_observ-${seccion.idSeccion}"><spring:message code="label.observaciones"/></label>
								<c:choose>
									<c:when test="${auditoriaDatos.estado == ESTADO_AUDITORIA_SIN_ENVIAR && seccion.indRespuesta == ACTIVO_NO}">
										<textarea id="auditoria_seccion_observ-${seccion.idSeccion}" name="observ" maxlength="4000" rows="5" cols="1" class="form-control"><c:out value="${seccion.observ}"/></textarea>
									</c:when>
									<c:otherwise>
										<textarea id="auditoria_seccion_observ-${seccion.idSeccion}" disabled="disabled" name="observ" maxlength="4000" rows="5" cols="1" class="form-control"><c:out value="${seccion.observ}"/></textarea>
									</c:otherwise>
								</c:choose>		
							</div>
						</c:if>
						<!-- campos de seccion - comprobamos que no este vacia antes de pintar la tabla -->
						<c:if test="${seccion.camposSeccionCount > 0}" >
							<!-- campos en funcion de TIPO_SECCION_0C3 -->
							<c:if test="${seccion.tipoSeccion == TIPO_SECCION_VALORACION or seccion.tipoSeccion == TIPO_SECCION_CONDICION}">
								<!-- ruptable -->
								<div class="content-adjust-table">
									<div id="auditoria_seccion_${seccion.tipoSeccion}_${seccion.idSeccion}_div" class="rup-table-container">
										<div id="auditoria_seccion_${seccion.tipoSeccion}_${seccion.idSeccion}_toolbar"></div>
										<div id="auditoria_seccion_${seccion.tipoSeccion}_${seccion.idSeccion}_filter_div" class="rup-table-filter" style="display: none">
											<form id="auditoria_seccion_${seccion.tipoSeccion}_${seccion.idSeccion}_filter_form" class="form-horizontal">
												<div id="auditoria_seccion_${seccion.tipoSeccion}_${seccion.idSeccion}_filter_toolbar"></div>
												<input type="hidden" name="idSeccion" value="${seccion.idSeccion}">
												<input type="hidden" name="idAuditoria" value="${auditoriaDatos.idAuditoria}">
											</form>
										</div>
										<div id="contenFormularios" class="filterForm ">
											
											<div id="auditoria_seccion_${seccion.tipoSeccion}_${seccion.idSeccion}_grid_div" >
												<!-- Tabla -->
												<table id="auditoria_seccion_${seccion.tipoSeccion}_${seccion.idSeccion}" 
														data-seccion="${seccion.idSeccion}" 
														data-tiposeccion="${seccion.tipoSeccion}"
														data-indrespuesta="${seccion.indRespuesta}"></table>
												<!-- Barra de paginación -->
												<div id="auditoria_seccion_${seccion.tipoSeccion}_${seccion.idSeccion}_pager" style="display:none"></div>
											</div>
										</div>
									</div>
								</div>
							<!-- campos de valoracion -->
								<c:if test="${seccion.tipoSeccion == TIPO_SECCION_VALORACION}">
									<div style="padding: 0px 22px;">
										<input type="hidden" id="campo_valor_min_aprobado-${seccion.idSeccion}" value="${seccion.valorMinAprobado}"></input>
										<input type="hidden" id="campo_valor_min_peligro-${seccion.idSeccion}" value="${seccion.valorMinPeligro}"></input>
										<input type="hidden" id="campo_sumatorio-${seccion.idSeccion}" value="${seccion.resulAuditoria}"></input>
										<span style="width: 49%; display: inline-block;font-weight: bold;"><spring:message code="aa79b.tabla.valoracion"></spring:message></span>
										<span id="campo_suma_icono-${seccion.idSeccion}"  style="width: 7%; display: inline-block;font-weight: bold; font-size:17px;"></span>
										<span id="campo_sumatorio_porc-${seccion.idSeccion}"  style="width: 43%; display: inline-block; text-align: right;font-weight: bold;"></span>
									</div>
									<div style="padding: 0px 22px;">
										<div class="form-group">
											<span class="detalle-auditoria-porcentaje-icono-container"><i class="fa fa-check ok-color" aria-hidden="true"></i></span>
											<span><spring:message code="label.expresion.mayorOIgual" arguments="${seccion.valorMinAprobado}"/></span>
										</div>
										<div class="form-group">
											<span class="detalle-auditoria-porcentaje-icono-container"><i class="fa fa-exclamation warning-color" aria-hidden="true"></i></span>
											<span><spring:message code="label.expresion.mayorOIgualYMenorQue" arguments="${seccion.valorMinPeligro},${seccion.valorMinAprobado}"/> </span>
										</div>
										<div>
											<span class="detalle-auditoria-porcentaje-icono-container"><i class="fa fa-times errorColor" aria-hidden="true"></i></span>
											<span><spring:message code="label.expresion.menorQue" arguments="${seccion.valorMinPeligro}"/></span>
										</div>
									</div>
								</c:if>
							</c:if>
						</c:if>
						
						<c:if test="${seccion.tipoSeccion == TIPO_SECCION_LIBRE}">
							<div class="content-adjust-table">
									<div id="auditoria_seccion_${seccion.tipoSeccion}_${seccion.idSeccion}_div" class="rup-table-container">
										<div id="auditoria_seccion_${seccion.tipoSeccion}_${seccion.idSeccion}_toolbar"></div>
										<div id="auditoria_seccion_${seccion.tipoSeccion}_${seccion.idSeccion}_filter_div" class="rup-table-filter" style="display: none">
											<form id="auditoria_seccion_${seccion.tipoSeccion}_${seccion.idSeccion}_filter_form" class="form-horizontal">
												<div id="auditoria_seccion_${seccion.tipoSeccion}_${seccion.idSeccion}_filter_toolbar"></div>
												<input type="hidden" name="idSeccion" value="${seccion.idSeccion}">
												<input type="hidden" name="idAuditoria" value="${auditoriaDatos.idAuditoria}">
											</form>
										</div>
										<div id="contenFormularios" class="filterForm ">
											
											<div id="auditoria_seccion_${seccion.tipoSeccion}_${seccion.idSeccion}_grid_div" >
												<!-- Tabla -->
												<table id="auditoria_seccion_${seccion.tipoSeccion}_${seccion.idSeccion}" 
														data-seccion="${seccion.idSeccion}" 
														data-tiposeccion="${seccion.tipoSeccion}"
														data-indrespuesta="${seccion.indRespuesta}"></table>
												<!-- Barra de paginación -->
												<div id="auditoria_seccion_${seccion.tipoSeccion}_${seccion.idSeccion}_pager" style="display:none"></div>
											</div>
										</div>
									</div>
								</div>
						</c:if>
					</div>
			</c:forEach>
			<!-- switch de auditoria visible -->
			<div class="form-groupcol-xs-12 col-md-3 content-adjust">
                <label class="control-label col-xs-12 no-padding-left" for="auditoria_visible"><spring:message code="label.auditoriaHacerVisible"/>:</label>
                <input type="checkbox" name="indenviado" id="auditoria_visible" value="S" data-switch-pestana="true">
            </div>
		</div>
	</div>
	<!-- Formulario de detalle de DOCUMENTO -->
	<div id="documentoSeccionAuditoria_detail_div" class="aa79b-content-modal" >
		<div id ="documentoSeccionAuditoria_detail_navigation"></div>			<!-- Barra de navegación del detalle -->
		<input type="hidden" id="idTablaDocAuditoria" name="idTablaDocAuditoria" />
		<form id="documentoSeccionAuditoria_detail_form">					<!-- Formulario -->
			<div id ="documentoSeccionAuditoria_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			<div class="col-xs-12">
					<input type="hidden" id="idDocAuditoria" name="idAuditoria" />
					<input type="hidden" id="idSeccionAuditoria" name="idSeccion" />
					<input type="hidden" name="nombre" id="nombre_0"/>
					<input type="hidden" name="extension" id="extensionDoc_0" readonly="readonly"/>
					<input type="hidden" name="tamano" id="tamanoDoc_0" readonly="readonly"/>
					<input type="hidden" name="contentType" id="contentType_0" readonly="readonly"/>
					<input type="hidden" name="encriptado" id="indEncriptado_0"/>
					<input type="hidden" name="oid" id="oidFichero_0" readonly="readonly"/>
				<div class="row">
					<div class="form-group col-lg-8">
						<label for="tituloDocumentoSeccionAuditoria_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.titulo"/>:</label>
						<input type="text" name="tituloFichero" class="form-control" id="tituloDocumentoSeccionAuditoria_detail_table" maxlength="200"/>
					</div>
				</div>	
				<div id="capaFicheroSeccionAuditoria" class="row">
					<div id="capaFicheroSeccionAuditoriaDetach">
						<div class="form-group col-lg-12 padding-top-2">	
							<button id="pidButton_0" type="button" class="ui-button ui-corner-all ui-widget" style="margin-top:-4px"><spring:message code="label.adjuntarFichero"/></button>
							<input type="text" name="nombreFicheroInfo_0" class="form-control" id="nombreFicheroInfo_0" readonly="readonly" style="width:200px; display:inline-block" />
						</div>
					</div>
				</div>
			</div>
		</form>
		<!-- Botonera del formulario de detalle -->
		<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
			<div class="ui-dialog-buttonset">
				<!-- Botón Guardar -->
				<button id="documentoSeccionAuditoria_detail_button_save" type="button">
					<spring:message code="save" />
				</button>
				<!-- Enlace cancelar -->
				<a href="javascript:void(0)" id="documentoSeccionAuditoria_detail_link_cancel"
					class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
			</div>
		</div>
	</div>
	<div id="subidaFicheroPidAuditoria" class="capaPIDenPestanaDoc oculto">
			<fieldset id="subidaFicheroPidAuditoria_fieldset" >			
				<div id="subidaFicheroPidAuditoria_feedback"></div>
				<form id="subidaFicheroPidAuditoria_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
					<div class="form-group row">
						<label for="ficheroAuditoria" class="control-label col-md-3"><spring:message code="label.fichero" /> (*):</label>
						<div class="col-md-6" id="divFicheroAuditoria">
							<input type="file" name="fichero" id="ficheroAuditoria"  class="form-control">
						</div>
						<input type="hidden" id="reqEncriptadoDocAuditoria" name="reqEncriptado" />
					</div>
				</form>
			</fieldset>
		</div>
</div>