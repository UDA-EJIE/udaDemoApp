	<%@include file="/WEB-INF/includeTemplate.inc"%>
	<h1>compuesta</h1>
	<div id="error" style="display:none"></div>
	<div id="compuesta">
		<div id="contenido" style="margin-top:0.5em;margin-bottom:0.5em;width:600px;">
			<form id="searchForm">
				<div  class="formulario_legend" id="titleSearch_compuesta"><spring:message code="searchCriteria" />:</div>
				<fieldset style="border:1px solid #DADADA;" id="FIELDSET_SEARCH_compuesta">
					<div class="formulario_columna_cnt">
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">idA:</div>
							<input type="text" name="idA" class="formulario_linea_input" id="idA_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">idB:</div>
							<input type="text" name="idB" class="formulario_linea_input" id="idB_search" />
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
							<div class="formulario_linea_label">fechaAlta:</div>
							<input type="text" name="fechaAlta" class="formulario_linea_input" id="fechaAlta_search" />
						</div>
					</div>
					<!-- Botones -->	
				</fieldset>
			</form>
		</div>

		<div id="RUP_GRID_compuesta">
			<!-- Tabla -->
			<table id="GRID_compuesta" cellpadding="0" cellspacing="0"></table>
			<!-- Barra de paginación -->
			<div id="pager" style="text-align:center;"></div>
		</div>

		<div id="detailBody" style="padding-top: 0.6em;display:none;">
		</div>
	</div>