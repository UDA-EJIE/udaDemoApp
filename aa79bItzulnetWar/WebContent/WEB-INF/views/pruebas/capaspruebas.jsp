<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid" id="divCargaSolicitudes"> 
	<h2>CAPAS PRUEBAS</h2>
	<hr class="mb-2">
	<div id="padre">
		<div id="estudioExpedientes_div" class="rup-table-container">
			<div id="estudioExpedientes_feedback"></div>						<!-- Feedback de la tabla --> 
			<div id="estudioExpedientes_filter_div" class="rup-table-filter"> <!-- Capa contenedora del formulario de filtrado -->
				<form id="estudioExpedientes_filter_form">						<!-- Formulario de filtrado -->
					<div id="estudioExpedientes_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
				</form>
			</div>
			<div class="estudioExpedientes_grid_div horizontal_scrollable_table" >
				<!-- Tabla -->
				<table id="estudioExpedientes" ></table>
				<!-- Barra de paginación -->
				<div id="estudioExpedientes_pager"></div>
			</div>
			<button id="rupListButton" type="button">
				Otro listado
			</button>
		</div>
	</div>
	<div id="hijo"></div>
</div>
