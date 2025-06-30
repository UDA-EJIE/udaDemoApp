<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%> 
<div class="container-fluid"> 
<h2><spring:message code="menu.tiposRevision"/></h2>
<hr class="mb-2">
	<div id="tiposrevision_div" class="rup-table-container">
	<div id="tiposrevision_feedback"></div>						<!-- Feedback de la tabla --> 
	<div id="tiposrevision_toolbar"></div>							<!-- Botonera de la tabla -->
	<!-- div id="tiposrevision_filter_div" class="rup-table-filter"-->
	<div id="contenFormularios" class="filterForm">	
		<!-- Capa contenedora del formulario de filtrado -->
			<form id="tiposrevision_filter_form" class="form-horizontal">						<!-- Formulario de filtrado -->
			<div id="tiposrevision_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="tiposrevision_filter_fieldset" class="rup-table-filter-fieldset">
				<!-- div class="formulario_columna_cnt"-->
				<div class="row">					
					<div class="col-xs-6 col-md-2">
						<div class="form-group">
							<label for="id018_filter_table" class="control-label col-xs-12" data-i18n="label.id"><spring:message code="label.id"/>:</label>
							<div class="col-xs-12">		
							<input type="text" name="id018" class="form-control numeric" id="id018_filter_table" maxlength="2" />
							</div>
						</div>
					</div>					
					<div class="col-xs-6 col-md-3">
						<div class="form-group">
							<label for="descEu018_filter_table" class="control-label col-xs-12" data-i18n="label.descEu"><spring:message code="label.descEu"/>:</label>
							<div class="col-xs-12">		
							<input type="text" name="descEu018" class="form-control" id="descEu018_filter_table" maxlength="50"/>
							</div>
						</div>
					</div>
					<div class="col-xs-6 col-md-3">
						<div class="form-group">
							<label for="descEs018_filter_table" class="control-label col-xs-12" data-i18n="label.descEs"><spring:message code="label.descEs"/>:</label>
							<div class="col-xs-12">		
							<input type="text" name="descEs018" class="form-control" id="descEs018_filter_table" maxlength="50"/>
							</div>
						</div>
					</div>
					<div class="col-xs-6 col-md-2">
						<div class="form-group">
							<label for="estado018_filter_table" class="control-label col-xs-12" data-i18n="label.estado"><spring:message code="label.estado"/>:</label>
							<div class="col-xs-12">									
							<select name="estado018" class="form-control" id="estado018_filter_table">
								<option value=""><spring:message code="combo.todos"/></option>
							    <option value="<%=EstadoEnum.ALTA.getValue()%>" selected="selected"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
							    <option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message code="<%=EstadoEnum.BAJA.getLabel()%>"/></option>
							</select>
							</div>
						</div>
					</div>					
					<!-- Fin campos del formulario de filtrado -->
				</div>
				<!-- Botonera del formulario de filtrado -->
				<div class="form-group">
					<div id="tiposrevision_filter_buttonSet" class="pull-right">
						<!-- Botón de filtrado -->
						<input id="tiposrevision_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' />
						<!-- Enlace de limpiar -->
						<a id="tiposrevision_filter_cleanLink" href="javascript:void(0)" class="btn btn-link"><spring:message code="clear" /></a>
					</div>
				</div>
			</fieldset>
		</form>
	</div>

	<div class="table pb-2">		
		<table id="tiposrevision"></table>
	</div>	
	<div id="tiposrevision_pager"></div>	
	
</div>	
</div>

<div class="container-fluid">
<!-- Formulario de detalle -->
<div id="tiposrevision_detail_div" class="rup-table-formEdit-detail">
	<div class="ui-dialog-content ui-widget-content" >
		<form id="tiposrevision_detail_form">					<!-- Formulario -->
			<div id ="tiposrevision_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->			
			
				<!-- Campos del formulario de detalle -->
				<div class="row"><div class="col-xs-6 col-lg-3">
					<label for="id018_detail_table" class="control-label"><spring:message code="label.id"/>:</label>
					<input type="text" name="id018" class="form-control" id="id018_detail_table" readonly="readonly" disabled="disabled"/>
				</div></div>
				<div class="row">
				<div class="form-group col-xs-12 col-lg-6" style="clear: left;">
						<label for="descEu018_detail_table" class="control-label"><spring:message code="label.descEu"/> (*):</label>
						<input type="text" name="descEu018" class="form-control" id="descEu018_detail_table" maxlength="50"/>
					</div>
					<div class="form-group col-xs-12 col-lg-6">
						<label for="descEs018_detail_table" class="control-label"><spring:message code="label.descEs"/> (*):</label>
						<input type="text" name="descEs018" class="form-control" id="descEs018_detail_table" maxlength="50"/>
					</div>					
				</div>
				<div class="row">
				<div class="form-group col-xs-12 col-lg-6" style="clear: left;">
						<label for="observEu018_detail_table" class="control-label"><spring:message code="label.queimplica"/> (<spring:message code="label.euskera"/>) (*):</label>						
						<textarea name="observEu018" class="form-control" id="observEu018_detail_table" rows="4" maxlength="400"></textarea>
					</div>		
					<div class="form-group col-xs-12 col-lg-6">
						<label for="observEs018_detail_table" class="control-label"><spring:message code="label.queimplica"/> (<spring:message code="label.castellano"/>) (*):</label>
						<textarea name="observEs018" class="form-control" id="observEs018_detail_table" rows="4" maxlength="400"></textarea>
					</div>							
				</div>
				<div class="row">
					<div class="form-group col-xs-12 col-lg-6" style="clear: left;">
						<label for="estado018_detail_table" class="control-label"><spring:message code="label.estado"/> (*):</label>
						<div class="col-xs-12 no-padding">
							<select name="estado018" class="form-control" id="estado018_detail_table">								
							    <option value="<%=EstadoEnum.ALTA.getValue()%>"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
							    <option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message code="<%=EstadoEnum.BAJA.getLabel()%>"/></option>
							</select>
						</div>
					</div>
					<div class="form-group col-xs-12 col-lg-6">
						<label for="porPalabrasRev018_detail_table" class="control-label"><spring:message code="label.xPalabras"/> (*):</label>
						<input type="text" name="porPalabrasRev018" class="form-control numeric col-20por" id="porPalabrasRev018_detail_table" maxlength="3"/>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-xs-12 col-lg-6">
						<label for="porRelTradRev018_detail_table" class="control-label"><spring:message code="label.relacionTareaRevTrad"/> (*):</label>
						<input type="text" name="porRelTradRev018" class="form-control numeric col-20por" id="porRelTradRev018_detail_table" maxlength="3"/>
					</div>
				</div>
				<!-- Fin campos del formulario de detalle -->
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Botón Guardar -->
			<button id="tiposrevision_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="tiposrevision_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
</div>