<%@include file="/WEB-INF/includeTemplate.inc"%>
<form id="MultiPk_filter_form">						<!-- Formulario de filtrado -->
			<div id="MultiPk_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="MultiPk_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="row">
					<!-- Campos del formulario de filtrado -->
					<div class="col-xs-6 col-md-3">
						<div class="form-group form-group-sm">
							<label for="ida_filter_table" class="formulario_linea_label"><spring:message code="ida"/>:</label>
							<input type="text" name="ida" class="formulario_linea_input" id="ida_filter_table"/>
						  </div>	
					</div>
					<div class="col-xs-6 col-md-3">
						<div class="form-group form-group-sm">
							<label for="idb_filter_table" class="formulario_linea_label"><spring:message code="idb"/>:</label>
							<input type="text" name="idb" class="formulario_linea_input" id="idb_filter_table"/>
						  </div>	
					</div>
					<div class="col-xs-6 col-md-3">
						<div class="form-group form-group-sm">
							<label for="nombre_filter_table" class="formulario_linea_label"><spring:message code="nombre"/>:</label>
							<input type="text" name="nombre" class="formulario_linea_input" id="nombre_filter_table"/>
						  </div>	
					</div>
					<div class="col-xs-6 col-md-3">
						<div class="form-group form-group-sm">
							<label for="apellido1_filter_table" class="formulario_linea_label"><spring:message code="apellido1"/>:</label>
							<input type="text" name="apellido1" class="formulario_linea_input" id="apellido1_filter_table"/>
						  </div>	
					</div>
					<div class="col-xs-6 col-md-3">
						<div class="form-group form-group-sm">
							<label for="apellido2_filter_table" class="formulario_linea_label"><spring:message code="apellido2"/>:</label>
							<input type="text" name="apellido2" class="formulario_linea_input" id="apellido2_filter_table"/>
						  </div>	
					</div>
					<!-- Fin campos del formulario de filtrado -->
				</div>
				<!-- Botonera del formulario de filtrado -->
				<div id="MultiPk_filter_buttonSet" class="right_buttons">
					<!-- Enlace de limpiar -->
					<a id="MultiPk_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
					<!-- Botón de filtrado -->
					<button id="MultiPk_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" ><spring:message code="filter" /></button>
				</div>
			</fieldset>
</form>