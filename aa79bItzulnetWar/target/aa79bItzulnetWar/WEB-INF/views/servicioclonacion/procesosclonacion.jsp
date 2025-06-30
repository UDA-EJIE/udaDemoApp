<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoExpedienteEnum"%>
<div id="divProcesosClonacion">
	<h2><spring:message code="titulo.procesosClonacion"></spring:message></h2>
	<hr class="mb-2">
	<div class="rup-table-container">
		<div id="clonarProcesosExpediente_feedback"></div>						
		<div id="clonarProcesosExpediente_toolbar"></div>	
		<div id="contenFormularios" class="rup-table-filter">
			<form id="clonarProcesosExpediente_filter_form" class="form-horizontal">
				<div id="clonarProcesosExpediente_filter_toolbar"
					class="formulario_legend filterCabecera"></div>
				<fieldset id="clonarProcesosExpediente_filter_fieldset"
					class="rup-table-filter-fieldset filterCuerpo">
					<div class="p-2">
						<div class="row">
							<!-- Campos del formulario de filtrado -->
							
							<div class="form-group col-xs-12 col-md-2">
								<label for="idTipoExpediente_filter_table" class="control-label "><spring:message code="label.tipoExpediente"/>:</label>
								<select name="idTipoExpediente" class="form-control" id="idTipoExpediente_filter_table">
									<option value=""><spring:message code="combo.todos"/></option>
									<option value="<%=TipoExpedienteEnum.TRADUCCION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.TRADUCCION.getLabel()%>"/></option>
									<option value="<%=TipoExpedienteEnum.REVISION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.REVISION.getLabel()%>"/></option>
								</select>
							</div>
							<div class="form-group col-xs-12 col-md-4">
								<label for="titulo_filter_table" class="control-label "><spring:message code="label.titulo"/>:</label>
								<input type="text" name="titulo" class="form-control" id="titulo_filter_table" maxlength="150"/>
							</div>
							
						</div>
						<div class="row">
							<div class="form-group col-xs-12 col-md-3">
								<label for="numExpOrig_filter_table" class="control-label"><spring:message code="label.numExpClonar"/>:</label>
								<div>
									<label for="anyoOrig_filter_table" class="control-label oculto"><spring:message code="label.anyo"/></label>
									<input type="text" name="anyoOrig" class="numeric" style="width: 40px;" id="anyoOrig_filter_table" maxlength="2"/>
									<label class="control-label" style="width: 3%;">/</label>
									<input type="text" name="numExpOrig" class="numeric" style="width: 75px;" id="numExpOrig_filter_table" maxlength="6"/>
								</div>
							</div>
							<div class="form-group col-xs-12 col-md-3">
								<label for="numExpClon_filter_table" class="control-label"><spring:message code="label.numExpClonado"/>:</label>
								<div>
									<label for="anyoClon_filter_table" class="control-label oculto"><spring:message code="label.anyo"/></label>
									<input type="text" name="anyoClon" class="numeric" style="width: 40px;" id="anyoClon_filter_table" maxlength="2"/>
									<label class="control-label" style="width: 3%;">/</label>
									<input type="text" name="numExpClon" class="numeric" style="width: 75px;" id="numExpClon_filter_table" maxlength="6"/>
								</div>
							</div>
						</div>
						
						<div id="clonarProcesosExpediente_filter_buttonSet" class="pull-right" style="margin: 0 1rem 1rem 0">
							<button id="clonarProcesosExpediente_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
								<spring:message code="filter" />
							</button>
							<a id="clonarProcesosExpediente_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="clonarProcesosExpediente_grid_div horizontal_scrollable_table" >
			<!-- Tabla -->
			<table id="clonarProcesosExpediente" ></table>
			<!-- Barra de paginación -->
			<div id="clonarProcesosExpediente_pager"></div>
		</div>
	</div>
</div>