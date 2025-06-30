<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="divExpedientesRelacionados" class="container-fluid aa79b-fade collapsed capaExpedienteMYO">
	<h2><spring:message code="menu.expedientesRelacionados"/></h2>
	<div id="expedientesRelacionados_div" class="rup-table-container">
		<div id="expedientesRelacionados_feedback"></div>			
		<div id="expedientesRelacionados_toolbar"></div>						<!-- Botonera de la tabla -->
		<div id="contenFormularios" class="filterForm oculto">			<!-- Capa contenedora del formulario de filtrado -->
			<form id="expedientesRelacionados_filter_form" class="form-horizontal">				<!-- Formulario de filtrado -->
				<div id="expedientesRelacionados_filter_toolbar" class="oculto"></div>	<!-- Barra de herramientas del formulario de filtrado -->
				<fieldset id="expedientesRelacionados_filter_fieldset" class="oculto">
					<div class="formulario_columna_cnt">
						<input type="hidden" name="anyo" id="anyo_detail_filter" >
						<input type="hidden" name="numExp" id="numExp_detail_filter" >
					</div>
				</fieldset>
			</form>
		</div>
		
		<div class="table pb-2">
			<!-- Tabla -->
			<table id="expedientesRelacionados"></table>
		</div>
		<!-- Barra de paginación -->
		<div id="expedientesRelacionados_pager"></div>
		
	</div>
	
	<div id="expRelacionadosDiv">
		<div id="buscadorExpedientesRelacionados" class="oculto"></div>
	</div>
</div>