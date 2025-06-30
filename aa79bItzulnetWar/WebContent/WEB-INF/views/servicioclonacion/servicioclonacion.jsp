<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoExpedienteEnum"%>
<div id="divClonacion">
	<div id="divClonacionCapa" class="container-fluid">
		<div id="divClonacionGeneral">
			<h2><spring:message code="menu.clonacionExpedientes"></spring:message></h2>
			<hr class="mb-2">
			<div class="rup-table-container">
				<div id="clonarExpediente_feedback"></div>						
				<div id="clonarExpediente_toolbar"></div>	
				<div id="contenFormularios" class="rup-table-filter">
					<form id="clonarExpediente_filter_form" class="form-horizontal">
						<div id="clonarExpediente_filter_toolbar"
							class="formulario_legend filterCabecera"></div>
						<fieldset id="clonarExpediente_filter_fieldset"
							class="rup-table-filter-fieldset filterCuerpo">
							<div class="p-2">
								<div class="row">
									<!-- Campos del formulario de filtrado -->
									<div class="form-group col-xs-12 col-md-2">
										<label for="idTipoExpediente_filter_table" class="control-label "><spring:message code="label.tipoExpediente"/>:</label>
										<div class="divComboW125">
										<select name="idTipoExpediente" class="form-control" id="idTipoExpediente_filter_table">
											<option value=""><spring:message code="combo.todos"/></option>
											<option value="<%=TipoExpedienteEnum.TRADUCCION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.TRADUCCION.getLabel()%>"/></option>
											<option value="<%=TipoExpedienteEnum.REVISION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.REVISION.getLabel()%>"/></option>
										</select>
										</div>
									</div>
									<div class="form-group col-xs-12 col-md-2">
										<label for="numExp_filter_table" class="control-label"><spring:message code="label.numeroExpediente"/>:</label>
										<div>
											<label for="anyo_filter_table" class="control-label oculto"><spring:message code="label.anyo"/></label>
											<input type="text" name="anyo" class=" numeric" style="width: 40px;" id="anyo_filter_table" maxlength="2"/>
											<label class="control-label" style="width: 3%;">/</label>
											<input type="text" name="numExp" class=" numeric" style="width: 75px;" id="numExp_filter_table" maxlength="6"/>
										</div>
									</div>
									
									<div class="form-group col-xs-12 col-md-4">
										<label for="titulo_filter_table" class="control-label "><spring:message code="label.titulo"/>:</label>
										<input type="text" name="titulo" class="form-control" id="titulo_filter_table" maxlength="150"/>
									</div>
									<div class="form-group col-xs-12 col-md-2">
										<label for="estadoX_filter_table" class="control-label "><spring:message code="label.estadoExpediente"/>:</label>
										<select name="estadoX" class="form-control" id="estadoX_filter_table">
											<option value="<%=EstadoExpedienteEnum.EN_ESTUDIO.getValue()%>"><spring:message code="label.enEstudio"/></option>
										</select>
									</div>
								</div>
								<div id="clonarExpediente_filter_buttonSet" class="pull-right" style="margin: 0 1rem 1rem 0">
									<button id="clonarExpediente_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
										<spring:message code="filter" />
									</button>
									<a id="clonarExpediente_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="clonarExpediente_grid_div horizontal_scrollable_table" >
					<!-- Tabla -->
					<table id="clonarExpediente" ></table>
					<!-- Barra de paginación -->
					<div id="clonarExpediente_pager"></div>
				</div>
			</div>
		</div>
	</div>
</div>