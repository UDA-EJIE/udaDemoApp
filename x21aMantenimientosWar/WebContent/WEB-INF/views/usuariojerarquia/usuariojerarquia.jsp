	<%@include file="/WEB-INF/includeTemplate.inc"%>
	<h2>${tituloPagina}</h2>
	<div id="error" style="display:none"></div>
	<div id="usuarioJerarquia">
		<div id="contenido" style="margin-top:0.5em;margin-bottom:0.5em;width:800px;">
			<form id="searchForm">
				<div  class="formulario_legend" id="titleSearch_usuarioJerarquia"><spring:message code="searchCriteria" />:</div>
				<fieldset style="border:1px solid #DADADA;" id="FIELDSET_SEARCH_usuarioJerarquia">
					<div class="formulario_columna_cnt">
<!-- 						<div class="formulario_linea_izda_float"> -->
<!-- 							<label for="id_search"class="formulario_linea_label">id:</label> -->
<!-- 							<input type="text" name="id" class="formulario_linea_input" id="id_search" /> -->
<!-- 						</div> -->
						<div class="formulario_linea_izda_float">
							<label for="nombre_search"class="formulario_linea_label">nombre:</label>
							<input type="text" name="nombre" class="formulario_linea_input" id="nombre_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<label for="apellido1_search"class="formulario_linea_label">apellido1:</label>
							<input type="text" name="apellido1" class="formulario_linea_input" id="apellido1_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<label for="apellido2_search"class="formulario_linea_label">apellido2:</label>
							<input type="text" name="apellido2" class="formulario_linea_input" id="apellido2_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<label for="ejie_search"class="formulario_linea_label">ejie:</label>
							<div style="float: left;"><select id="ejie_search" name="ejie" class="rup-combo" ></select></div>
						</div>
						<div class="formulario_linea_izda_float">
							<label for="fechaAlta_search"class="formulario_linea_label">fechaAlta:</label>
							<input type="text" name="fechaAlta" class="formulario_linea_input" id="fechaAlta_search" />
						</div>
						<div class="formulario_linea_izda_float" style="margin-left: 0.6em;">
							<label for="fechaBaja_search"class="formulario_linea_label">fechaBaja:</label>
							<input type="text" name="fechaBaja" class="formulario_linea_input" id="fechaBaja_search" />
						</div>
<!-- 						<div class="formulario_linea_izda_float"> -->
<!-- 							<label class="formulario_linea_label" for="idPadre_search ">idPadre:</label> -->
<!-- 							<input type="text" name="idPadre" class="formulario_linea_input" id="idPadre_search" /> -->
<!-- 						</div> -->
						<div class="formulario_linea_izda_float">
							<label class="formulario_linea_label" for="grupo_search ">grupo:</label>
							<input type="text" name="grupo" class="formulario_linea_input" id="grupo_search" />
						</div>
					</div>
					<div class="formulario_linea_izda_float">
						<span class="ui-icon ui-icon-triangle-1-se" style="float:left;"></span>Elementos expandidos<br/>
					</div>						
					<div class="formulario_linea_izda_float">
						<span class="ui-icon ui-icon-triangle-1-e" style="float:left;"></span>Elementos contraídos
					</div>
					<div class="formulario_linea_izda_float">
						<span class="rup-grid-jerarquia_filter  ui-icon ui-icon-star" style="float:left;"></span>Elementos que cumplen los criterios de búsqueda
					</div>
				</fieldset>
			</form>
		</div>

		<div id="RUP_GRID_usuarioJerarquia">
			<!-- Tabla -->
			<table id="GRID_usuarioJerarquia" cellpadding="0" cellspacing="0"></table>
			<!-- Barra de paginacion -->
			<div id="pager" style="text-align:center;"></div>
		</div>
	</div>