<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2><spring:message code="perfilUsuario" /></h2>
<div id="error" style="display:none"></div>
<div id="perfilUsuario">
	<div id="contenido" style="margin-top:0.5em;margin-bottom:0.5em;width:600px;">
		<form id="searchForm">
			<div  class="formulario_legend" id="titleSearch_perfilUsuario"><spring:message code="searchCriteria" />:</div>
			<fieldset style="border:1px solid #DADADA;" id="FIELDSET_SEARCH_perfilUsuario">
				<div class="formulario_columna_cnt">
					<div class="formulario_linea_izda_float">
						<label for="id_search" class="formulario_linea_label">id:</label>
						<input type="text" name="id" class="formulario_linea_input" id="id_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="usuario.idUsuario_search" class="formulario_linea_label">idUsuario:</label>
						<input type="text" name="usuario.idUsuario" class="formulario_linea_input" id="usuario.idUsuario_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="localidad.codeLocalidad_search" class="formulario_linea_label">codeLocalidad:</label>
						<input type="text" name="localidad.codeLocalidad" class="formulario_linea_input" id="localidad.codeLocalidad_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="comarca.codeComarca_search" class="formulario_linea_label">codeComarca:</label>
						<input type="text" name="comarca.codeComarca" class="formulario_linea_input" id="comarca.codeComarca_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="provincia.codeProvincia_search" class="formulario_linea_label">codeProvincia:</label>
						<input type="text" name="provincia.codeProvincia" class="formulario_linea_input" id="provincia.codeProvincia_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="userId_search" class="formulario_linea_label">userId:</label>
						<input type="text" name="userId" class="formulario_linea_input" id="userId_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="pass_search" class="formulario_linea_label">pass:</label>
						<input type="text" name="pass" class="formulario_linea_input" id="pass_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="activo_search" class="formulario_linea_label">activo:</label>
						<input type="text" name="activo" class="formulario_linea_input" id="activo_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="tipo_search" class="formulario_linea_label">tipo:</label>
						<input type="text" name="tipo" class="formulario_linea_input" id="tipo_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="fechaAlta_search" class="formulario_linea_label">fechaAlta:</label>
						<input type="text" name="fechaAlta" class="formulario_linea_input" id="fechaAlta_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="fechaBaja_search" class="formulario_linea_label">fechaBaja:</label>
						<input type="text" name="fechaBaja" class="formulario_linea_input" id="fechaBaja_search" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="horaEntrada_search" class="formulario_linea_label">horaEntrada:</label>
						<input type="text" name="horaEntrada" class="formulario_linea_input" id="horaEntrada_search" />
					</div>
				</div>
			</fieldset>
		</form>
	</div>

	<div id="RUP_GRID_perfilUsuario">
		<!-- Tabla -->
		<table id="GRID_perfilUsuario" cellpadding="0" cellspacing="0"></table>
		<!-- Barra de paginación -->
		<div id="pager" style="text-align:center;"></div>
	</div>
</div>


<!-- Formulario de detalle -->
<div id="detallePerfilUsuario">

	

</div>