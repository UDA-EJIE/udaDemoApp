<%@include file="/WEB-INF/includeTemplate.inc"%>
<h1>Maestro-Detalle</h1>
<div id="error" style="display:none"></div>

<h2>Comarca</h2>
<div id="comarca">
	<div id="contenido" style="margin-top:0.5em;margin-bottom:0.5em;width:600px;">
		<form id="searchForm">
			<div  class="formulario_legend" id="titleSearch_comarca"><spring:message code="searchCriteria" />:</div>
			<fieldset style="border:1px solid #DADADA;" id="FIELDSET_SEARCH_comarca">
				<div class="formulario_columna_cnt">
					<div class="formulario_linea_izda_float">
						<div class="formulario_linea_label">code:</div>
						<input type="text" name="code" class="formulario_linea_input" id="code_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<div class="formulario_linea_label">codeProvincia:</div>
						<input type="text" name="provincia.codeProvincia" class="formulario_linea_input" id="provincia.codeProvincia_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<div class="formulario_linea_label">descEs:</div>
						<input type="text" name="descEs" class="formulario_linea_input" id="descEs_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<div class="formulario_linea_label">descEu:</div>
						<input type="text" name="descEu" class="formulario_linea_input" id="descEu_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<div class="formulario_linea_label">css:</div>
						<input type="text" name="css" class="formulario_linea_input" id="css_search" />
					</div>
				</div>
				<!-- Botones -->	
			</fieldset>
		</form>
	</div>

	<div id="RUP_GRID_comarca">
		<!-- Tabla -->
		<table id="GRID_comarca" cellpadding="0" cellspacing="0"></table>
		<!-- Barra de paginación -->
		<div id="pager_comarca" style="text-align:center;"></div>
	</div>
</div>

<h2>Localidad</h2>
<div id="localidad">
	<div id="contenido" style="margin-top:0.5em;margin-bottom:0.5em;width:600px;">
		<form id="searchFormDetalle">
			<div  class="formulario_legend" id="titleSearch_localidad"><spring:message code="searchCriteria" />:</div>
			<fieldset style="border:1px solid #DADADA;" id="FIELDSET_SEARCH_localidad">
				<div class="formulario_columna_cnt">
					<div class="formulario_linea_izda_float">
						<div class="formulario_linea_label">code:</div>
						<input type="text" name="code" class="formulario_linea_input" id="code_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<div class="formulario_linea_label">codeComarca:</div>
						<input type="text" name="comarca.codeComarca" class="formulario_linea_input" id="comarca.codeComarca_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<div class="formulario_linea_label">descEs:</div>
						<input type="text" name="descEs" class="formulario_linea_input" id="descEs_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<div class="formulario_linea_label">descEu:</div>
						<input type="text" name="descEu" class="formulario_linea_input" id="descEu_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<div class="formulario_linea_label">css:</div>
						<input type="text" name="css" class="formulario_linea_input" id="css_search" />
					</div>
				</div>
				<!-- Botones -->	
			</fieldset>
		</form>
	</div>

	<div id="RUP_GRID_localidad">
		<!-- Tabla -->
		<table id="GRID_localidad" cellpadding="0" cellspacing="0"></table>
		<!-- Barra de paginación -->
		<div id="pager_localidad" style="text-align:center;"></div>
	</div>

</div>