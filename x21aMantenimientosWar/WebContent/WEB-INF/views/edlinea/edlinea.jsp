	<%@include file="/WEB-INF/views/includes/includeTemplate.inc"%>
	<h1>Edición en línea</h1>
	<div id="error" style="display:none"></div>
	<div id="EJIE_MAINT_edlinea">
		<div id="contenido" style="margin-top:0.5em;margin-bottom:0.5em;width:600px;">
			<form id="searchForm">
				<div  class="formulario_legend" id="titleSearch_edlinea"><spring:message code="searchCriteria" />:</div>
				<fieldset style="border:1px solid #DADADA;" id="FIELDSET_SEARCH_edlinea">
					<div class="formulario_columna_cnt">
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">id:</div>
							<input type="text" name="id" class="formulario_linea_input" id="id_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">nombre:</div>
							<input type="text" name="nombre" class="formulario_linea_input" id="nombre_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">apellido1:</div>
							<input type="text" name="apellido1" class="formulario_linea_input" id="apellido1_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">apellido2:</div>
							<input type="text" name="apellido2" class="formulario_linea_input" id="apellido2_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">ejie:</div>
							<input type="text" name="ejie" class="formulario_linea_input" id="ejie_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">fechaAlta:</div>
							<input type="text" name="fechaAlta" class="formulario_linea_input" id="fechaAlta_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">fechaBaja:</div>
							<input type="text" name="fechaBaja" class="formulario_linea_input" id="fechaBaja_search" />
						</div>
					</div>
					<!-- Botones -->	
				</fieldset>
			</form>
		</div>

		<div id="RUP_GRID_edlinea">
			<!-- Tabla -->
			<table id="GRID_edlinea" cellpadding="0" cellspacing="0"></table>
			<!-- Barra de paginación -->
			<div id="pager" style="text-align:center;"></div>
		</div>

		<div id="detailBody" style="padding-top: 0.6em;display:none;">
		</div>
	</div>