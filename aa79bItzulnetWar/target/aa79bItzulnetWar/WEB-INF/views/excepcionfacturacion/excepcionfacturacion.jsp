<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExcepcionEnum"%>

<div class="container-fluid" id ="divExcepcionFacturacion">
<h2><spring:message code="menu.datosFacturacionEntidad"/></h2>
<hr class="mb-2">
	<div id="excepcionfacturacion_div" class="rup-table-container">
		<div id="excepcionfacturacion_feedback"></div>						<!-- Feedback de la tabla --> 
									<!-- Botonera de la tabla -->
		
		<div id="excepcionfacturacion_filter_div" class="filterForm" >	<!-- Capa contenedora del formulario de filtrado -->
				<form id="excepcionfacturacion_filter_form">						<!-- Formulario de filtrado -->
				<div id="excepcionfacturacion_filter_toolbar" class="oculto"></div>	<!-- Barra de herramientas del formulario de filtrado -->
				<fieldset id="excepcionfacturacion_filter_fieldset" class="rup-table-filter-fieldset">
					<div class="p-2">
						<div class="row">						
							<!-- Campos del formulario de filtrado -->
							<div class="form-group col-lg-5 col-xl-5">
									<label for="idTiposEntidad_filter_table" class="control-label" data-i18n="label.tipoEntidad"><spring:message code="label.tipoEntidad"/>:</label>
									<div id="idTiposEntidad_filter_table">
										<div class="col-lg-2 no-padding">
											<input type="radio" name="tipoEntidad_filter" id="tipoEntidad_filter_T" value="" data-on-text='<spring:message code="label.todas"/>'/>
											<label for="tipoEntidad_filter_T" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="label.todas"/></label>
										</div>
										<c:set var="labelsTipoEntidad" value="<%=TipoEntidadEnum.values()%>"/>
										<c:forEach items="${labelsTipoEntidad}" var="tipoEntidad">
										<div class="<c:if test="${tipoEntidad.value == 'E'}"> col-lg-4</c:if><c:if test="${tipoEntidad.value != 'E'}"> col-lg-3</c:if> no-padding">
											<input type="radio" name="tipoEntidad_filter" id="tipoEntidad_filter_${tipoEntidad.value}" value="${tipoEntidad.value}"  data-on-text='<spring:message code="${tipoEntidad.label}"/>'/>
											<label for="tipoEntidad_filter_${tipoEntidad.value}" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="${tipoEntidad.label}"/></label>
										</div>
										</c:forEach>
									</div>
								</div>
								<div class="form-group col-lg-4">
									<label for="idEntidadSolicitante_filter_table" class="control-label" data-i18n="label.entidadSolicitante"><spring:message code="label.entidadSolicitante" />:</label>
									<input id="idEntidadSolicitante_filter_table" class="form-control" name="entidadSolicitante.codigoCompleto" maxlength="100" />
								</div>
								<div class="form-group col-lg-3 col-xl-3">
									<label for="tipoExcepcion" class="control-label" data-i18n="label.excepcion.tipoExcepcion"><spring:message code="label.excepcion.tipoExcepcion" />:</label>
									<select name="tipoExcepcion036" id="tipoExcepcion" class="form-control">						
										<option value="<%=TipoExcepcionEnum.PORENTIDAD.getValue()%>"><spring:message code="<%=TipoExcepcionEnum.PORENTIDAD.getLabel()%>"/></option>
										<option value="<%=TipoExcepcionEnum.PORSOLICITANTE.getValue()%>"><spring:message code="<%=TipoExcepcionEnum.PORSOLICITANTE.getLabel()%>"/></option>
									</select>
								</div>
								<input type="hidden" name="idEntidad036" id="idEntidad036" />						
								<input type="hidden" name="tipoEntidad036" id="tipoEntidad036" />						
							<!-- Fin campos del formulario de filtrado -->
						</div>					
						<!-- Botonera del formulario de filtrado -->
						<div class="form-group">
							<div id="facturacionentidad_filter_buttonSet" class="pull-right">
								<!-- Botón de filtrado -->
								<button id="facturacionentidad_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all"><spring:message code="filter" /></button>
								<!-- Enlace de limpiar -->
								<a id="facturacionentidad_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
							</div>
						</div>
					</div>	
				</fieldset>
			</form>
		</div>
	
		<div id="excepcionfacturacion_grid_div" class="collapsed">
			<p style="margin-top:1.5rem"><strong id="titTabla"></strong></p>
			<div id="excepcionfacturacion_toolbar"></div>
			<!-- Tabla -->
			<table id="excepcionfacturacion"></table>
			<!-- Barra de paginación -->
			<div id="excepcionfacturacion_pager"></div>
		</div>
	</div>	
</div>

<div class="container-fluid">
	<!-- Formulario de detalle -->
	<div id="excepcionfacturacion_detail_div" class="rup-table-formEdit-detail aa79b-content-modal">
		<!-- div id ="excepcionfacturacion_detail_navigation"></div-->			<!-- Barra de navegación del detalle -->
		<div class="ui-dialog-content ui-widget-content" >
			<form id="excepcionfacturacion_detail_form">					<!-- Formulario -->
				<div id ="excepcionfacturacion_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
				<div class="">				
					<!-- Campos del formulario de detalle -->
					<input type="hidden" name="id036" id="id036_detail_table"/>
					<input type="hidden" name="tipoExcepcion036" class="formulario_linea_input" id="tipoExcepcion036_detail_table"/>
					<input type="hidden" name="tipoEntidad036" class="formulario_linea_input" id="tipoEntidad036_detail_table"/>
					<input type="hidden" name="idEntidad036" class="formulario_linea_input" id="idEntidad036_detail_table"/>
					<input type="hidden" name="tipoEntidadAsoc036" class="formulario_linea_input" id="tipoEntidadAsoc036_detail_table"/>
					<input type="hidden" name="idEntidadAsoc036" class="formulario_linea_input" id="idEntidadAsoc036_detail_table"/>
					
					<div class="form-group col-md-12">
						<label for="infoEntidadSolicitante" class="control-label" data-i18n="label.entidadSolicitante"><spring:message code="label.entidadSolicitante" />: </label>
						<span id="infoEntidadSolicitante"></span>
					</div>
					<div class="form-group col-md-12"  style="margin-bottom:1rem">
						<label for="infoTipoExcepcion" class="control-label" data-i18n="label.excepcion.tipoExcepcion"><spring:message code="label.excepcion.tipoExcepcion" />: </label>
						<span id="infoTipoExcepcion"></span>
					</div>
					
					<div class="form-group col-md-12" id="capaSolicitante">
						<div id="capaSol">
							<label for="personasSolicitantes" class="control-label" data-i18n="label.solicitante"><spring:message code="label.solicitante" /> (*):</label>
							<div class="divComboW125">	
							<select id="personasSolicitantes" class="form-control" name="personasSolicitantes.dniTrabajador037"></select>
							</div>
						</div>
					</div>
					<fieldset>
						<legend><spring:message code="label.entidadSFactura"/></legend>
						<div class="form-group col-md-12" style="margin-top:1rem">
							<label for="idTiposEntidad_detail_table" class="control-label" data-i18n="label.tipoEntidad"><spring:message code="label.tipoEntidad"/>:</label>
							<div id="idTiposEntidad_detail_table">
								<div class="col-md-3">
									<input type="radio" name="tipoEntidad" id="tipoEntidad_T" value="" data-on-text='<spring:message code="label.todas"/>'/>
									<label for="tipoEntidad_T" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="label.todas"/></label>
								</div>
								<c:set var="labelsTipoEntidad" value="<%=TipoEntidadEnum.values()%>"/>
								<c:forEach items="${labelsTipoEntidad}" var="tipoEntidad">
								<div class="col-md-3">
									<input type="radio" name="tipoEntidad" id="tipoEntidad_${tipoEntidad.value}" value="${tipoEntidad.value}"  data-on-text='<spring:message code="${tipoEntidad.label}"/>'/>
									<label for="tipoEntidad_${tipoEntidad.value}" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="${tipoEntidad.label}"/></label>
								</div>
								</c:forEach>
							</div>
						</div>
						<div class="form-group col-md-12">
							<label for="idEntidadAsociada" class="control-label" data-i18n="label.entidadAsociada"><spring:message code="label.entidadAsociada" /> (*):</label>
							<input id="idEntidadAsociada" class="form-control" name="idEntidadAsoc036_autocomplete"/>
						</div>
						
						
						<div class="form-group col-md-12" style="margin-top:1rem">
							<label for="dniContacto036_detail_table" class="control-label" data-i18n="label.contacto"><spring:message code="label.contactoFactura" /> (*):</label>
							<select name="dniContacto036" id="dniContacto036_detail_table" class="form-control"></select>
						</div>
					</fieldset>
					
					<div class="form-group col-md-12" id="capaPorcentaje" style="margin-top:1.5rem">
						<label for="porFactura036_detail_table" class="control-label"><spring:message code="label.porcentajeFactAplica"/> (*):</label>
						<input type="text" name="porFactura036" class="form-control numeric col-30por" id="porFactura036_detail_table" maxlength="3"/>
					</div>
					<!-- Fin campos del formulario de detalle -->
					
				</div>
			</form>
		</div>
		<!-- Botonera del formulario de detalle -->
		<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
			<div class="ui-dialog-buttonset">
				<!-- Botón Guardar -->
				<button id="excepcionfacturacion_detail_button_save" type="button">
					<spring:message code="save" />
				</button>
				<!-- Enlace cancelar -->
				<a href="javascript:void(0)" id="excepcionfacturacion_detail_cancel"
					class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
			</div>
		</div>
	</div>
</div>
