<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid">
<h2><spring:message code="menu.detalleGrupoTrabajo"/></h2>
<hr class="mb-2">
<div id="detalle_feedback"></div> 
<div id="detalle_toolbar"></div>
<div id="detalle_div" class="formulario_legend">
	<form:form id="detalle_form" modelAttribute="grupoTrabajo">
		<fieldset id="trabajadoresgrupostrabajo_filter_fieldset">
			<legend><spring:message code="label.grupoTrabajo"/></legend>
			<!-- <div class="formulario_legend"> -->
				<div class="p-2">
					<div class="row">
						<div class="form-group col-xs-2">
							<label for="id_detail_table" class="control-label"><spring:message code="label.codigo"/>:</label>
							<form:input path="id" class="form-control col-50por" id="id_detail_table" readonly="true"/>
						</div>
						<div class="form-group col-xs-3">
							<label for="fechaAlta_detail_table" class="control-label"><spring:message code="label.fechaAlta"/>:</label>
							<form:input path="fechaAlta" class="form-control col-50por" id="fechaAlta_detail_table" readonly="true"/>
						</div>
						<div class="form-group col-xs-3">
							<label for="fechaAlta_detail_table" class="control-label"><spring:message code="label.fechaBaja"/>:</label>
							<form:input path="fechaBaja" class="form-control col-50por" id="fechaBaja_detail_table" readonly="true"/>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-3">
							<label for="idTipoExpediente_detail_table" class="control-label"><spring:message code="label.tipoExpediente"/> (*):</label>
							<form:select path="idTipoExpediente" id="idTipoExpediente_detail_table" class="form-control">
								<form:option value=""><spring:message code="combo.seleccione"/></form:option>
							<c:set var="labelsTipoExpediente" value="<%=TipoExpedienteGrupoEnum.values()%>"/>
							<c:forEach items="${labelsTipoExpediente}" var="valorTipoExpediente"> 
								<form:option value="${valorTipoExpediente.value}"><spring:message code="${valorTipoExpediente.label}"/></form:option>
							</c:forEach>
							</form:select>
						</div>
						<div class="form-group col-xs-6 col-md-4">
							<label for="descEu_detail_table" class="control-label"><spring:message code="label.descripcionEus"/> (*):</label>
							<form:input path="descEu" class="form-control" id="descEu_detail_table" maxlength="50"/>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-5">
							<label for="dniResponsable_detail_table" class="control-label"><spring:message code="label.responsable"/> (*):</label>
							<form:select path="responsable.dni" id="dniResponsable_detail_table" class="form-control">
								<form:option value=""><spring:message code="combo.seleccione"/></form:option>
							<c:forEach items="${asignadores}" var="asignador"> 
								<form:option value="${asignador.dni}"><spring:message code="${asignador.nombreCompleto}"/></form:option>
							</c:forEach>
							</form:select>
						</div>
						<div style="display:none;">
							<form:checkbox path="indBopv" class="form-control" id="indBopv_detail_table" data-switch="true" value="<%=Constants.SI%>"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<label class="control-label col-lg-12 p-0" data-i18n="numPalabrasIZO"><spring:message code="label.rangoPalabras" />:</label>
							<div class="form-group col-lg-6 p-0 numPalabrasContainer">
								<label for="palabrasDesde_detail_table" class="control-label dosPuntos col-md-6 p-0" data-i18n="label.desde"><spring:message code="label.desde" /></label>
								<div id="palabrasDesdeDiv" class="form-group  col-md-5 p-0">
									<form:input path="palabrasDesde" class="form-control numeric" id="palabrasDesde_detail_table" maxlength="6"/>
								</div>
							</div>
							<div class="form-group col-lg-6 p-0">
								<label for="palabrasHasta_detail_table" class="control-label dosPuntos col-md-6 p-0" data-i18n="label.hasta"><spring:message code="label.hasta" /></label>
								<div id="palabrasHastaDiv" class="form-group  col-md-5 p-0">
									<form:input path="palabrasHasta" class="form-control numeric" id="palabrasHasta_detail_table" maxlength="6"/>
								</div>
							</div>
						</div>
						
						<div class="form-group col-md-2">
							<label for="estado_detail_table" class="control-label"><spring:message code="label.estado"/> (*):</label>
							<form:select path="estado" id="estado_detail_table" class="form-control">
								<form:option value="<%=Constants.ALTA%>"><spring:message code="estado.alta"/></form:option>
								<form:option value="<%=Constants.BAJA%>"><spring:message code="estado.baja"/></form:option>
							</form:select>
						</div>
						
						
					</div>
				</div>
			<!-- </div>-->
		</fieldset>
	</form:form>
</div>

<div id="divTablas" class="aa79b-fade row pagMin">

<div class="form-group col-md-6">
<fieldset>
<legend><spring:message code="label.entidadesSolicitantes" /></legend>
<div id="entidades_div">
	<div id="entidades_feedback"></div>						<!-- Feedback de la tabla --> 
								<!-- Botonera de la tabla -->
	<div id="contenFormularios" class="filterForm " style="display:none;">
		<!-- Capa contenedora del formulario de filtrado -->
			<form id="entidades_filter_form" Class="form-horizontal">
			<input type="hidden" name="idGrupo" id="idGrupoEntidades" value="${grupoTrabajo.id}"/>
			<div id="entidades_filter_toolbar" class="formulario_legend" ></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="entidades_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="p-2">
					<!-- Botonera del formulario de filtrado -->
					<div class="form-group">
						<div id="entidades_filter_buttonSet" class="pull-right">
							<!-- Botón de filtrado -->
							<button id="entidades_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all"><spring:message code="filter" /></button>
							<!-- Enlace de limpiar -->
							<a id="entidades_filter_cleanLink" href="javascript:void(0)" class="btn btn-link"><spring:message code="clear" /></a>
						</div>
					</div>
				</div>
				
			</fieldset>
		</form>
	</div>
	<div id="entidades_toolbar"></div>
	<div class="table">
		<table id="entidades"></table>
	</div>
	<div id="entidades_pager"></div>
</div>
<div id="msgEntidadesObligatorias" class="">
					<legend>(*) <spring:message code="mensaje.entidadesObligatorias"/></legend>
				</div>
</fieldset>
</div>

<div class="form-group col-md-6">
<fieldset>
<legend><spring:message code="label.traductoresInterpretes" /></legend>
<div id="traductores_div">
	<div id="traductores_feedback"></div>						<!-- Feedback de la tabla --> 
								<!-- Botonera de la tabla -->
	<div id="contenFormularios" class="filterForm " style="display:none;">
		<!-- Capa contenedora del formulario de filtrado -->
			<form id="traductores_filter_form" Class="form-horizontal">
			<input type="hidden" name="idGrupo" id="idGrupoTraductores" value="${grupoTrabajo.id}"/>
			<div id="traductores_filter_toolbar" class="formulario_legend" ></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="traductores_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="p-2">
					<!-- Botonera del formulario de filtrado -->
					<div class="form-group">
						<div id="traductores_filter_buttonSet" class="pull-right">
							<!-- Botón de filtrado -->
							<button id="traductores_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all"><spring:message code="filter" /></button>
							<!-- Enlace de limpiar -->
							<a id="traductores_filter_cleanLink" href="javascript:void(0)" class="btn btn-link"><spring:message code="clear" /></a>
						</div>
					</div>
				</div>
				
			</fieldset>
		</form>
	</div>
	<div id="traductores_toolbar"></div>
	<div class="table">
		<table id="traductores"></table>
	</div>
	<div id="traductores_pager"></div>
</div>
<div id="msgTraductoresObligatorios" class="">
					<legend>(*) <spring:message code="mensaje.traductoresObligatorios"/></legend>
				</div>
</fieldset>
</div>

</div>


<div id="entidades_detail_div" class="rup-table-formEdit-detail">
	<div id ="entidades_detail_navigation"></div>			<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content" >
		<form id="entidades_detail_form">					<!-- Formulario -->
			<input type="hidden" name="idGrupo" id="idEntidades"/>
			<div id ="entidades_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			<div class="row">
				<div class="form-group col-md-12">
					<label for="idTiposEntidad_detail_table" class="control-label" data-i18n="label.tipoEntidad"><spring:message code="label.tipoEntidad"/>:</label>
					<div id="idTiposEntidad_detail_table">
						<div class="col-md-3">
							<input type="radio" name="tipoEntidad" id="tipoEntidad_detail_T" value="" data-on-text='<spring:message code="label.todas"/>'/>
							<label for="tipoEntidad_detail_T" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="label.todas"/></label>
						</div>
					<c:set var="labelsTipoEntidad" value="<%=TipoEntidadEnum.values()%>"/>
					<c:forEach items="${labelsTipoEntidad}" var="tipoEntidad">
						<div class="col-md-3">
							<input type="radio" name="tipoEntidad" id="tipoEntidad_detail_${tipoEntidad.value}" value="${tipoEntidad.value}" data-on-text='<spring:message code="${tipoEntidad.label}"/>'/>
							<label for="tipoEntidad_detail_${tipoEntidad.value}" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="${tipoEntidad.label}"/></label>
						</div>
					</c:forEach>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label for="idEntidadSolicitante_detail_table" class="control-label" data-i18n="label.entidadSolicitante"><spring:message code="label.entidadSolicitante" /> (*):</label>
					<input id="idEntidadSolicitante_detail_table" class="form-control" name="codigoCompleto"/>
				</div>
			</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Botón Guardar -->
			<button id="entidades_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="entidades_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>

<!-- Formulario de detalle -->
<div id="traductores_detail_div" class="rup-table-formEdit-detail">
	<div id ="traductores_detail_navigation"></div>			<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content" >
		<form id="traductores_detail_form">					<!-- Formulario -->
			<input type="hidden" name="idGrupo" id="idTraductores"/>
			<div id ="traductores_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			
			<div class="row">
				<div class="form-group col-md-12">
					<label for="codTrabajador_detail_table" class="control-label"><spring:message code="label.traductorInterprete"/> (*):</label>
					<select name="dniCombo" id="codTrabajador_detail_table" class="form-control"></select>
					<input type="hidden" name="dni" id="dniTraductor"/>
				</div>
			</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Botón Guardar -->
			<button id="traductores_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="traductores_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
</div>
