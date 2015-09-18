	<%@include file="/WEB-INF/includeTemplate.inc"%>
	<h1><spring:message code="perfilUsuario" /></h1>
	<div id="error" style="display:none"></div>
	<div id="perfilUsuario">
		<div id="contenido" style="margin-top:0.5em;margin-bottom:0.5em;width:600px;">
			<form id="searchForm">
				<div  class="formulario_legend" id="titleSearch_perfilUsuario"><spring:message code="searchCriteria" />:</div>
				<fieldset style="border:1px solid #DADADA;" id="FIELDSET_SEARCH_perfilUsuario">
					<div class="formulario_columna_cnt">
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">id:</div>
							<input type="text" name="id" class="formulario_linea_input" id="id_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">idUsuario:</div>
							<input type="text" name="usuario.idUsuario" class="formulario_linea_input" id="usuario.idUsuario_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">codeLocalidad:</div>
							<input type="text" name="localidad.codeLocalidad" class="formulario_linea_input" id="localidad.codeLocalidad_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">codeComarca:</div>
							<input type="text" name="comarca.codeComarca" class="formulario_linea_input" id="comarca.codeComarca_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">codeProvincia:</div>
							<input type="text" name="provincia.codeProvincia" class="formulario_linea_input" id="provincia.codeProvincia_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">userId:</div>
							<input type="text" name="userId" class="formulario_linea_input" id="userId_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">pass:</div>
							<input type="text" name="pass" class="formulario_linea_input" id="pass_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">activo:</div>
							<input type="text" name="activo" class="formulario_linea_input" id="activo_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">tipo:</div>
							<input type="text" name="tipo" class="formulario_linea_input" id="tipo_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">fechaAlta:</div>
							<input type="text" name="fechaAlta" class="formulario_linea_input" id="fechaAlta_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">fechaBaja:</div>
							<input type="text" name="fechaBaja" class="formulario_linea_input" id="fechaBaja_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">horaEntrada:</div>
							<input type="text" name="horaEntrada" class="formulario_linea_input" id="horaEntrada_search" />
						</div>
					</div>
				</fieldset>
			</form>
		</div>

		<div id="RUP_GRID_perfilUsuario">
			<!-- Tabla -->
			<table id="GRID_perfilUsuario" cellpadding="0" cellspacing="0"></table>
			<!-- Barra de paginaci�n -->
			<div id="pager" style="text-align:center;"></div>
		</div>
	</div>