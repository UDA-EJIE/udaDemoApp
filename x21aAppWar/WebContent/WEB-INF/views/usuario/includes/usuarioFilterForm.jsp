<form id="usuario_filter_form">						<!-- Formulario de filtrado -->
			<div id="usuario_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="usuario_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="formulario_columna_cnt">
					<!-- Campos del formulario de filtrado -->
					<div class="formulario_linea_izda_float">
						<label for="id_filter_table" class="formulario_linea_label"><spring:message code="id"/>:</label>
						<input type="text" name="id" class="formulario_linea_input" id="id_filter_table"/>
					</div>
					<div class="formulario_linea_izda_float">
						<label for="nombre_filter_table" class="formulario_linea_label"><spring:message code="nombre"/>:</label>
						<input type="text" name="nombre" class="formulario_linea_input" id="nombre_filter_table"/>
					</div>
					<div class="formulario_linea_izda_float">
						<label for="apellido1_filter_table" class="formulario_linea_label"><spring:message code="apellido1"/>:</label>
						<input type="text" name="apellido1" class="formulario_linea_input" id="apellido1_filter_table"/>
					</div>
					<div class="formulario_linea_izda_float">
						<label for="apellido2_filter_table" class="formulario_linea_label"><spring:message code="apellido2"/>:</label>
						<input type="text" name="apellido2" class="formulario_linea_input" id="apellido2_filter_table"/>
					</div>
					<div class="formulario_linea_izda_float">
						<label for="ejie_filter_table" class="formulario_linea_label"><spring:message code="ejie"/>:</label>
						<input type="checkbox" name="ejie" class="formulario_linea_input" id="ejie_filter_table"/>							
					</div>
					<div class="formulario_linea_izda_float">
						<label for="fechaAlta_filter_table" class="formulario_linea_label"><spring:message code="fechaAlta"/>:</label>
						<input type="text" name="fechaAlta" class="formulario_linea_input" id="fechaAlta_filter_table"/>
					</div>
					<div class="formulario_linea_izda_float">
						<label for="fechaBaja_filter_table" class="formulario_linea_label"><spring:message code="fechaBaja"/>:</label>
						<input type="text" name="fechaBaja" class="formulario_linea_input" id="fechaBaja_filter_table"/>
					</div>
					<div class="formulario_linea_izda_float">
						<label for="rol_filter_table" class="formulario_linea_label"><spring:message code="rol"/>:</label>
						<input type="text" name="rol" class="formulario_linea_input" id="rol_filter_table"/>
					</div>
					<div class="formulario_linea_izda_float">
						<label for="fechaModif_filter_table" class="formulario_linea_label"><spring:message code="fechaModif"/>:</label>
						<input type="text" name="fechaModif" class="formulario_linea_input" id="fechaModif_filter_table"/>
					</div>
					<!-- Fin campos del formulario de filtrado -->
				</div>
				<!-- Botonera del formulario de filtrado -->
				<div id="usuario_filter_buttonSet" class="right_buttons">
					<!-- Botón de filtrado -->
					<input id="usuario_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' />
					<!-- Enlace de limpiar -->
					<a id="usuario_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
				</div>
			</fieldset>
</form>