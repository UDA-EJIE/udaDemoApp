<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum"%>
<div id="divPestDatos">
		<h2><spring:message code="menu.planificacion"></spring:message></h2>
		<div id="busquedaDatos_div" class="rup-table-container">
		<div id="busquedaDatos_feedback"></div>
		<div id="busquedaDatos_toolbar"></div>	
		<div id="contenFormularios" class="filterForm "> <!-- Capa contenedora del formulario de filtrado -->
			<form id="busquedaDatos_filter_form" class="filterShadow filter">
				<div id="busquedaDatos_filter_toolbar" class="formulario_legend filterCabecera"></div>
				<fieldset id="busquedaDatos_filter_fieldset">	
					<div id="capaTxtFiltro" class="row">
						<div class="form-group col-xs-12 mt-10">
							<label for="filtro_combo" class="control-label" data-i19n="label.planif.filtro"><spring:message code="label.planif.filtro" />:</label>
							<span class="negrita" id="txtFiltro"></span> 
						</div>
					</div>
					<div id="capaGrupoTrabajo" class="row oculto">
						<div class="form-group col-xs-12">
							<label for="grupoTrabajo_combo" class="control-label"><spring:message code="label.grupoTrabajo" />:</label>
							<span class="negrita" id="txtGrupoTrabajo"></span> 
						</div>
					</div>
					<div id="capaTxtAsignador" class="row oculto">
						<div class="form-group col-xs-12">
							<label for="asignador_combo" class="control-label"><spring:message code="label.asignador"/>:</label>
							<span class="negrita" id="txtAsignador"></span> 
						</div>
					</div>

					<div id="busquedaDatos_filter_buttonSet" class="oculto" style="margin:0 1rem 1rem 0">
						<button id="busquedaDatos_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
							<spring:message code="filter" />
						</button>
						<a id="busquedaDatos_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
					</div>
					
					<div class="row oculto">
						<select name="filtroDatos" class="form-control" id="filtro_combo" ></select>
						<select name="idsGrupoTrabajo" class="form-control" id="grupoTrabajo_combo" ></select>
						<select name="tecnico.dni" class="form-control" id="asignador_combo" ></select>
					</div>
					
				</fieldset>
			</form>
		</div>
		<div id="contenedorTablaBusquedaDatos" class="table pb-2 horizontal_scrollable_table" >
			<!-- Tabla -->
			<table id="busquedaDatos"></table>
			<!-- Barra de paginación -->
			<div id="busquedaDatos_pager"></div>
		</div>
		
				<div class="row form-group" id="leyendaUrgente">
			<div class="col-xs-4">
				<div class="col-xs-1"><div class="boxUrgente"></div></div>
				<label class="control-label"><spring:message code="label.urgenteIconDesc"/></label>
			</div>
		</div>
		<div class="row form-group" id="leyendaRecurso">
			<div class="col-xs-12">
				<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
				<label class="control-label"><spring:message code="comun.recursoNoDisponible"/></label>
			</div>
		</div>
		
	</div>
</div>