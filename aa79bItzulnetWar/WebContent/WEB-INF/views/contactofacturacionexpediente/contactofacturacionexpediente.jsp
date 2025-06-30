<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExcepcionEnum"%>


<div class="container-fluid collapsed capaExpedienteMYO" id ="divContactoFacturacion">
<h2 id="titTabla"><spring:message code="titulo.entidadContactoFacturacion"/></h2>
	<div id="contactofacturacionexp_div" class="rup-table-container">
		<div id="contactofacturacionexp_feedback"></div>						<!-- Feedback de la tabla --> 
		<div id="contactofacturacionexp_toolbar"></div>							<!-- Botonera de la tabla -->
		<div id="contactofacturacionexp_filter_div" class="rup-table-filter" style="display:none">	<!-- Capa contenedora del formulario de filtrado -->
				<form id="contactofacturacionexp_filter_form">						<!-- Formulario de filtrado -->
					<div id="contactofacturacionexp_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
					<input type="hidden" name="anyo" id="anyo_filtro_fact" >
					<input type="hidden" name="numExp" id="numExp_filtro_fact" >
				</form>
		</div>
		<div id="contactofacturacionexp_grid_div" class="table pb-2">
			<!-- Tabla -->
			<table id="contactofacturacionexp"></table>
			<!-- Barra de paginación -->
			<div id="contactofacturacionexp_pager"></div>
		</div>
	</div>	

	<!-- Formulario de detalle -->
	<div id="contactofacturacionexp_detail_div" class="rup-table-formEdit-detail aa79b-content-modal">
		<!-- div id ="contactofacturacionexp_detail_navigation"></div-->			<!-- Barra de navegación del detalle -->
		<div class="ui-dialog-content ui-widget-content" >
			<form id="contactofacturacionexp_detail_form">					<!-- Formulario -->
				<div id ="contactofacturacionexp_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
				<fieldset class="form_fieldset">		
					<!-- Campos del formulario de detalle -->
					<input type="hidden" name="anyo" id="anyo058_detail_table" >
					<input type="hidden" name="numExp" id="numExp058_detail_table" >
						
					<input type="hidden" name="id058" class="formulario_linea_input" id="id058_detail_table"/>
					<input type="hidden" name="tipoEntidadAsoc058" class="formulario_linea_input" id="tipoEntidadAsoc058_detail_table"/>
					<input type="hidden" name="idEntidadAsoc058" class="formulario_linea_input" id="idEntidadAsoc058_detail_table"/>
										
					<div class="form-group col-md-12">
						<label for="idTiposEntidad_detail_table" class="control-label" data-i18n="label.tipoEntidad"><spring:message code="label.tipoEntidad"/>:</label>
						<div id="idTiposEntidad_detail_table">
							<div class="col-md-3">
								<input type="radio" name="tipoEntidadFact" id="tipoEntidad_T" value="" data-on-text='<spring:message code="label.todas"/>'/>
								<label for="tipoEntidad_T" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="label.todas"/></label>
							</div>
							<c:set var="labelsTipoEntidad" value="<%=TipoEntidadEnum.values()%>"/>
							<c:forEach items="${labelsTipoEntidad}" var="tipoEntidad">
							<div class="col-md-3">
								<input type="radio" name="tipoEntidadFact" id="tipoEntidad_${tipoEntidad.value}" value="${tipoEntidad.value}"  data-on-text='<spring:message code="${tipoEntidad.label}"/>'/>
								<label for="tipoEntidad_${tipoEntidad.value}" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="${tipoEntidad.label}"/></label>
							</div>
							</c:forEach>
						</div>
					</div>
					<div class="form-group col-md-12">
						<label for="idEntidadAsociada" class="control-label" data-i18n="label.entidadAsociada"><spring:message code="label.entidadAsociada" /> (*):</label>
						<input id="idEntidadAsociada" class="form-control" name="idEntidadAsoc058_autocomplete"/>
					</div>
					
					
					<div class="form-group col-md-12" style="margin-top:2rem">
						<label for="dniContacto058_detail_table" class="control-label" data-i18n="label.contacto"><spring:message code="label.contacto" />:</label>
<!-- 						<input id="dniContacto058_detail_table" class="form-control" name="dniContacto058"/> -->
						<select id="dniContacto058_detail_table" name="dniContacto058" class="form-control"></select>
					</div>
					
					<div class="form-group col-md-12" id="capaPorcentaje">
						<label for="porFactura058_detail_table" class="control-label"><spring:message code="label.porcentajeFactAplica"/> (*):</label>
						<input type="text" name="porFactura058" class="form-control numeric col-30por" id="porFactura058_detail_table" maxlength="3"/>
					</div>
					<!-- Fin campos del formulario de detalle -->
					
				</fieldset>
			</form>
		</div>
		<!-- Botonera del formulario de detalle -->
		<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
			<div class="ui-dialog-buttonset">
				<!-- Botón Guardar -->
				<button id="contactofacturacionexp_detail_button_save" type="button">
					<spring:message code="save" />
				</button>
				<!-- Enlace cancelar -->
				<a href="javascript:void(0)" id="contactofacturacionexp_detail_link_cancel"
					class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
			</div>
		</div>
	</div>
</div>
