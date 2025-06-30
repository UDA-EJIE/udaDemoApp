<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in" id="divGruposTrabajo"> 
		<h2>
			<spring:message code="label.title" />
			<small><spring:message code="label.traductorInterprete" /></small>
		</h2>
		<hr class="mb-2">
		<div id="trabajadoresgrupostrabajo_div" class="rup-table-container">
		<div id="trabajadoresgrupostrabajo_feedback"></div>						<!-- Feedback de la tabla --> 
		<div id="trabajadoresgrupostrabajo_toolbar"></div>							<!-- Botonera de la tabla -->
		<div id="trabajadoresgrupostrabajo_filter_div" class="rup-table-filter">	<!-- Capa contenedora del formulario de filtrado -->
				<form id="trabajadoresgrupostrabajo_filter_form">						<!-- Formulario de filtrado -->
				<div id="trabajadoresgrupostrabajo_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
				<fieldset id="trabajadoresgrupostrabajo_filter_fieldset" class="rup-table-filter-fieldset">
					<div class="formulario_columna_cnt">
						<!-- Campos del formulario de filtrado -->
						<div class="formulario_linea_izda_float">
							<label for="idGrupo028_filter_table" class="formulario_linea_label"><spring:message code="label.grupoTrabajo"/>:</label>
							<input type="text" name="gruposTrabajo.descEs026" class="form-control" id="gruposTrabajo.descEs026_filter_table" readonly="readonly" disabled="disabled" value="Grupo trabajo 1"/>
						</div>
						<div class="formulario_linea_izda_float">
							<label for="codTrabajador028_filter_table" class="formulario_linea_label"><spring:message code="label.codTrabajador"/>:</label>
							<select name="codTrabajador028" id="codTrabajador028_filter_table" class="form-control">
								<option value=""></option>
								<option value="1">Trabajador 1</option>
							</select>
						</div>
						<!-- Fin campos del formulario de filtrado -->
					</div>
					<!-- Botonera del formulario de filtrado -->
					<div id="trabajadoresgrupostrabajo_filter_buttonSet" class="pull-right">
						<!-- Botón de filtrado -->
						<input id="trabajadoresgrupostrabajo_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' />
						<!-- Enlace de limpiar -->
						<a id="trabajadoresgrupostrabajo_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
					</div>
				</fieldset>
			</form>
		</div>
	
		<div id="trabajadoresgrupostrabajo_grid_div">
			<!-- Tabla -->
			<table id="trabajadoresgrupostrabajo"></table>
			<!-- Barra de paginación -->
			<div id="trabajadoresgrupostrabajo_pager"></div>
		</div>
	</div>	
</div>

<!-- Formulario de detalle -->
<div id="trabajadoresgrupostrabajo_detail_div" class="rup-table-formEdit-detail">
	<div id ="trabajadoresgrupostrabajo_detail_navigation"></div>			<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content" >
		<form id="trabajadoresgrupostrabajo_detail_form">					<!-- Formulario -->
			<div id ="trabajadoresgrupostrabajo_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			<div class="floating_left_pad_right">
			
				<!-- Campos del formulario de detalle -->
				<div class="floating_left_pad_right one-column">
					<label for="idGrupo028_detail_table"><spring:message code="label.grupoTrabajo"/>:</label>
					<input type="text" name="gruposTrabajo.descEs026" class="form-control" id="gruposTrabajo.descEs026_filter_table" readonly="readonly" disabled="disabled" value="Grupo trabajo 1"/>
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="codTrabajador028_detail_table"><spring:message code="label.codTrabajador"/>:</label>
					<select name="codTrabajador028" id="codTrabajador028_detail_table" class="form-control">
						<option value=""></option>
						<option value="1">Trabajador 1</option>
					</select>
				</div>
				<!-- Fin campos del formulario de detalle -->
				
			</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Botón Guardar -->
			<button id="trabajadoresgrupostrabajo_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="trabajadoresgrupostrabajo_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
