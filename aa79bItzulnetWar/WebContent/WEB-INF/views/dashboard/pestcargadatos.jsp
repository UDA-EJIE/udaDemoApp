<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum"%>
<div id="divPestDatosCarga">
		<h2><spring:message code="menu.cargaTrabajo"></spring:message></h2>
		<div id="busquedaDatosCarga_div" class="rup-table-container">
		<div id="busquedaDatosCarga_feedback" class="oculto"></div>
		<div id="busquedaDatosCarga_toolbar"></div>	
		<div id="contenFormularios" class="filterForm "> <!-- Capa contenedora del formulario de filtrado -->
			<form id="busquedaDatosCarga_filter_form" class="filterShadow filter">
				<div id="busquedaDatosCarga_filter_toolbar" class="formulario_legend filterCabecera"></div>
				<fieldset id="busquedaDatosCarga_filter_fieldset">
					<div id="capaTxtFiltro" class="row">
						<div class="form-group col-xs-12 mt-10">
							<label for="filtro_combo" class="control-label" data-i19n="label.planif.filtro"><spring:message code="label.planif.filtro" />:</label>
							<span class="negrita" id="txtFiltro"></span> 
						</div>
					</div>
					<div class="row oculto">
						<input name="idsGrupoTrabajo" class="form-control" id="filtroDatosCarga"/>
					</div>

					<div id="busquedaDatosCarga_filter_buttonSet" class="oculto" style="margin:0 1rem 1rem 0">
						<button id="busquedaDatosCarga_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
							<spring:message code="filter" />
						</button>
						<a id="busquedaDatosCarga_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
					</div>
				</fieldset>
			</form>
		</div>
		<div id="contenedorTablaBusquedaDatos" class="table pb-2 horizontal_scrollable_table" >
			<!-- Tabla -->
			<table id="busquedaDatosCarga"></table>
			<!-- Barra de paginación -->
			<div id="busquedaDatosCarga_pager"></div>
		</div>
		
		<div class="row form-group" id="leyendaRecurso">
			<div class="col-xs-12">
				<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
				<label class="control-label"><spring:message code="comun.recursoNoDisponible"/></label>
			</div>
		</div>
		
	</div>
</div>
