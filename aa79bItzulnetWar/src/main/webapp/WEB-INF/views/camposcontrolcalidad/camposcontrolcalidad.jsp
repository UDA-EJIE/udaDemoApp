<%@page import="com.ejie.aa79b.model.enums.TipoCampoAuditoriaEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid">
	<h2>
		<spring:message code="menu.camposControlCalidad" />
	</h2>
	<hr class="mb-2">
	<div id="camposControlCalidad_div" class="rup-table-container">
		<div id="camposControlCalidad_feedback"></div>
		<!-- Feedback de la tabla -->
		<div id="camposControlCalidad_toolbar"></div>
		<!-- Botonera de la tabla -->
		<div id="contenFormularios" class="filterForm ">
			<!-- Capa contenedora del formulario de filtrado -->
			<form id="camposControlCalidad_filter_form" Class="form-horizontal">
				<!-- Formulario de filtrado -->
				<div id="camposControlCalidad_filter_toolbar" class="formulario_legend"></div>
				<!-- Barra de herramientas del formulario de filtrado -->
				<fieldset id="camposControlCalidad_filter_fieldset"
					class="rup-table-filter-fieldset">
					<div class="p-2">
						<div class=" row">
							<!-- Campos del formulario de filtrado -->
							<div class="form-group col-xs-6 col-md-4">
								<label for="nombreCampo_filter_table" class="control-label"
									data-i18n="label.nombreCampo"><spring:message
										code="label.nombreCampo" />:</label> <input type="text"
									name="nombreCampo" class="form-control"
									id="nombreCampo_filter_table" maxlength="4000" />
							</div>
							<div class="form-group col-md-3">
								<label for="idSeccion_filter_table" class="control-label"><spring:message code="label.seccion" />:</label> 
								<select name="seccion.id"
									id="idSeccion_filter_table" class="form-control">
								</select>
							</div>
							<div class="form-group col-md-2">
								<label for="indVisible_filter_table" class="control-label"
									data-i18n="label.visible"><spring:message
										code="label.visible" />:</label> 
								<select name="indVisible"
									id="indVisible_filter_table" class="form-control">
									<option value="" selected="selected"><spring:message code="combo.todos"/></option>
									<option value="<%=Constants.SI%>"><spring:message code="comun.si"/></option>	
									<option value="<%=Constants.NO%>"><spring:message code="comun.no"/></option>
								</select>
							</div>
						</div>
						<!-- Fin campos del formulario de filtrado -->
					
					<!-- Botonera del formulario de filtrado -->
					<div class="form-group ">
						<div id="camposControlCalidad_filter_buttonSet" class="pull-right">
							<!-- Botón de filtrado -->
							<input id="camposControlCalidad_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />'/>
							<!-- Enlace de limpiar -->
							<a id="camposControlCalidad_filter_limpiar" href="javascript:void(0)"
								class="rup-enlaceCancelar"><spring:message code="clear" /></a>
						</div>
					</div>
					</div>
				</fieldset>
			</form>
		</div>

		<div class="table pb-2">
			<table id="camposControlCalidad"></table>
		</div>
		<div id="camposControlCalidad_pager"></div>
	</div>
</div>
	
<div class="container-fluid">
	<!-- Formulario de detalle -->
	<div id="camposControlCalidad_detail_div" class="rup-table-formEdit-detail">
		<!-- Barra de navegación del detalle -->
		<div class="ui-dialog-content ui-widget-content">
			<form id="camposControlCalidad_detail_form">
				<!-- Formulario -->
				<div id="camposControlCalidad_detail_feedback"></div>
				<!-- Feedback del formulario de detalle -->

				<div class="row">
					<div class="form-group  col-lg-4">
						<label for="idCampoControlCalidad_detail_table" class="control-label"><spring:message code="label.id" />:</label> 
						<input type="text" name="idCampoControlCalidad" class="form-control numeric col-40por" id="idCampoControlCalidad_detail_table" maxlength="2" readonly="readonly" disabled="disabled" />
					</div>
				</div>
				 <div class="row">
					<div class="form-group  col-lg-12">
						<label for="idSeccion_detail_table" class="control-label"
									data-i18n="label.seccion"><spring:message
										code="label.seccion" />:</label> 
						<select name="seccion.id"
									id="idSeccion_detail_table" class="form-control" disabled>
									
								</select> 
					</div>
				</div>
				<div class="row">
					<div class="form-group  col-lg-12">
						<label for="nombreCampo_detail_table" class="control-label"><spring:message code="label.nombreCampo" />(*):</label> 
						<input type="text" name="nombreCampo" class="form-control" id="nombreCampo_detail_table" maxlength="4000"/>
					</div>
				</div>
				<div class="row">
					<div class="form-group  col-lg-6">
						<label for="tipoCampo_detail_table" class="control-label"><spring:message
								code="label.tipoCampo" />(*):</label> 
						<select name="tipoCampo" id="tipoCampo_detail_table" class="form-control" disabled="disabled">
							<option value="" ></option>
							<option value="<%=TipoCampoAuditoriaEnum.CAMPO_VALORACION.getValue()%>"><spring:message code="<%=TipoCampoAuditoriaEnum.CAMPO_VALORACION.getLabel()%>"/></option>
							<option value="<%=TipoCampoAuditoriaEnum.CAMPO_CONDICION.getValue()%>" ><spring:message code="<%=TipoCampoAuditoriaEnum.CAMPO_CONDICION.getLabel()%>"/></option>
							<option value="<%=TipoCampoAuditoriaEnum.CAMPO_TEXTO.getValue()%>" ><spring:message code="<%=TipoCampoAuditoriaEnum.CAMPO_TEXTO.getLabel()%>"/></option>
						</select>
					</div>
					
					<div class="form-group  col-lg-3">
						<label for="indVisible_detail_table" class="control-label"><spring:message code="label.visible"/>:</label>
						<div class="col-xs-12 no-padding">
							<input type="checkbox" name="indVisible" class="form-control" value="S" data-switch="true" id="indVisible_detail_table"/>
						</div>
					</div>
				</div>
				 <div class="row">
					<div class="form-group  col-lg-3">
						<label for="indObservaciones_detail_table" class="control-label"><spring:message code="label.indObservaciones"/>:</label>
						<div class="col-xs-12 no-padding">
							<input type="checkbox" name="indObservaciones" class="form-control" value="S" data-switch="true" id="indObservaciones_detail_table"/>
						</div>
					</div>
					
					<div class="form-group  col-lg-3">
						<label for="indObligatorio_detail_table" class="control-label"><spring:message code="label.obligatorio"/>:</label>
						<div class="col-xs-12 no-padding">
							<input type="checkbox" name="indObligatorio" class="form-control" value="S" data-switch="true" id="indObligatorio_detail_table"/>
						</div>
					</div>
				</div> 
				<div class="row">
					<div class="form-group col-xs-12">
						<label for="notaOk_detail_table" class="control-label"><spring:message code="label.notaOk"/>:</label>
						<div class="col-xs-12">	
							<input type="text" name="notaOk" class="form-control" id="notaOk_detail_table" maxlength="250" tabindex="1"/>
						</div>	
					</div>
				</div>
				<div class="row">
					<div class="form-group col-xs-12">
						<label for="notaNoOk_detail_table" class="control-label"><spring:message code="label.notaNoOk"/>:</label>
						<div class="col-xs-12">	
							<input type="text" name="notaNoOk" class="form-control" id="notaNoOk_detail_table" maxlength="250" tabindex="1"/>
						</div>	
					</div>
				</div>
				<div class="row">
					<div id="idPeso_detail_table_container" class="form-group col-lg-3">
						<label for="idPeso_detail_table" class="control-label"><spring:message code="label.pesoValoracion" />(*):</label>
						<select name="peso.id" id="idPeso_detail_table" class="form-control col-40por">
						</select>
					</div>
				</div>
				<div class="row">
					<div class="form-group  col-lg-4">
						<label for="orden_detail_table" class="control-label"><spring:message
								code="label.orden" />(*):</label> <input type="text"
							name="orden" class="form-control col-40por numeric"
							id="orden_detail_table" maxlength="2" />
					</div>
				</div> 

			</form>
		</div>
		<!-- Botonera del formulario de detalle -->
		<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
			<div class="ui-dialog-buttonset">
				<!-- Botón Guardar -->
				<button id="camposControlCalidad_detail_button_save" type="button">
					<spring:message code="save" />
				</button>
				<!-- Enlace cancelar -->
				<a href="javascript:void(0)" id="camposControlCalidad_detail_link_cancel"
					class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
			</div>
		</div>
	</div>
</div>
	<!-- <div id="buscadorPersonas" style="display:none"></div>  -->