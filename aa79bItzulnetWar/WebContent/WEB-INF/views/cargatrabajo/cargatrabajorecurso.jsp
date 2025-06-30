<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum"%>
<div class="container-fluid aa79b-fade in" id="divCargaTrabajoExpedientes">
	<div id="busquedaGeneral_filter_div" class="rup-table-filter oculto"> <!-- Capa contenedora del formulario de filtrado -->
		<form id="busquedaGeneral_filter_form" class="filterShadow filter">	
			<div id="busquedaGeneral_filter_toolbar" class="formulario_legend filterCabecera"></div>
			<div class="formulario_columna_cnt">
				<input type="hidden" name="tipo" id="tipo" value="">
			</div>
		</form>
	</div>
	<div class="col-xl-12  col-xs-12  col-sm-12">
		<div id="ganttRecurso" class="gantt"></div>
	</div>
	<div class="row">
		<div class="form-group col-lg-12">
			<div class="col-xs-12">
				<div id="tipoTareaTal" class="control-label col-xs-1 no-padding-left estadoPendienteAceptacion ganttIndex"></div>
				<label for="tipoTarea_detail_table"
					class="control-label col-xs-3 no-padding-left"><spring:message
					code='<%=EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getLabel()%>' /></label> 
			</div>
			</div>
			</div>		
					
	<div class="row">
		<div class="form-group col-lg-12">
			<div class="col-xs-12">		
				<div id="tipoTareaTal" class="control-label col-xs-1 no-padding-left estadoRechazada ganttIndex"></div>
				<label for="tipoTarea_detail_table"
					class="control-label col-xs-1 no-padding-left"><spring:message
					code='<%=EstadoAceptacionTareaEnum.RECHAZADA.getLabel()%>' /></label> 
				</div>
			</div>
			</div>	
				<div class="row">
		<div class="form-group col-lg-12">
			<div class="col-xs-12">	
				<div id="tipoTareaTal" class="control-label col-xs-1 no-padding-left estadoAceptadaPdteEjecucion ganttIndex"></div>
				<label for="tipoTarea_detail_table"
					class="control-label col-xs-3 no-padding-left"><spring:message
					code='<%=EstadoAceptacionTareaEnum.ACEPTADA.getLabel()%>' />/<spring:message
					code='<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel()%>' /></label>
				</div>
			</div>
			</div>	
				<div class="row">
		<div class="form-group col-lg-12">
			<div class="col-xs-12">	 
				<div id="tipoTareaTal" class="control-label col-xs-1 no-padding-left estadoRetrasada ganttIndex"></div>
				<label for="tipoTarea_detail_table"
					class="control-label col-xs-1 no-padding-left"><spring:message
					code='<%=EstadoEjecucionTareaEnum.RETRASADA.getLabel()%>' /></label> 
			</div> 
		</div>
	</div> 
</div>