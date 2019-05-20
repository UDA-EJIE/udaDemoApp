<%@include file="/WEB-INF/includeTemplate.inc"%>
<form id="usuario_filter_form">						<!-- Formulario de filtrado -->
			<div id="usuario_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="usuario_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="form-row">
					<!-- Campos del formulario de filtrado -->
					<div class="form-group col-sm">
						<div class="form-group form-group-sm">
							<label for="id_filter_table" class="formulario_linea_label"><spring:message code="id"/></label>
							<input type="text" name="id" class="formulario_linea_input form-control" id="id_filter_table"/>
						  </div>	
					</div>
					<div class="form-group col-sm">
						<div class="form-group form-group-sm">
							<label for="nombre_filter_table" class="formulario_linea_label"><spring:message code="nombre"/></label>
							<input type="text" name="nombre" class="formulario_linea_input form-control" id="nombre_filter_table"/>
						  </div>	
					</div>
					<div class="form-group col-sm">
						<div class="form-group form-group-sm">
							<label for="apellido1_filter_table" class="formulario_linea_label"><spring:message code="apellido1"/></label>
							<input type="text" name="apellido1" class="formulario_linea_input form-control" id="apellido1_filter_table"/>
						  </div>	
					</div>
					<div class="form-group col-sm">
						<div class="form-group form-group-sm">
							<label for="apellido2_filter_table" class="formulario_linea_label"><spring:message code="apellido2"/></label>
							<input type="text" name="apellido2" class="formulario_linea_input form-control" id="apellido2_filter_table"/>
						  </div>	
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm">
						<div class="form-group form-group-sm">
							<label for="ejie_filter_table" class="formulario_linea_label"><spring:message code="ejie"/></label>
							<input type="text" name="ejie" class="formulario_linea_input form-control" id="ejie_filter_table"/>
						  </div>	
					</div>
					<div class="form-group col-sm">
						<div class="form-group form-group-sm">
							<label for="fechaAlta_filter_table" class="formulario_linea_label"><spring:message code="fechaAlta"/></label>
							<input type="text" name="fechaAlta" class="formulario_linea_input form-control" id="fechaAlta_filter_table"/>
						  </div>	
					</div>
					<div class="form-group col-sm">
						<div class="form-group form-group-sm">
							<label for="fechaBaja_filter_table" class="formulario_linea_label"><spring:message code="fechaBaja"/></label>
							<input type="text" name="fechaBaja" class="formulario_linea_input form-control" id="fechaBaja_filter_table"/>
						  </div>	
					</div>
					<div class="form-group col-sm">
						<div class="form-group form-group-sm">
							<label for="rol_filter_table" class="formulario_linea_label"><spring:message code="rol"/></label>
							<input type="text" name="rol" class="formulario_linea_input form-control" id="rol_filter_table"/>
						  </div>	
					</div>
					<div class="form-group col-sm">
						<div class="form-group form-group-sm">
							<label for="fechaModif_filter_table" class="formulario_linea_label"><spring:message code="fechaModif"/></label>
							<input type="text" name="fechaModif" class="formulario_linea_input form-control" id="fechaModif_filter_table"/>
						  </div>	
					</div>
				</div>

				<!-- Botonera del formulario de filtrado -->
				<div id="usuario_filter_buttonSet" class="right_buttons">
					<!-- Botón de limpiar -->
			        <button id="usuario_filter_cleanButton" type="button" class="btn btn-primary rup-limpiar">
			        	<i class="mdi mdi-eraser"></i>
			        	<span>
			        		<spring:message code="clear" />
			        	</span>
			        </button>
			        <!-- Botón de filtrado -->
			        <button id="usuario_filter_filterButton" type="button" class="btn btn-info rup-filtrar">
			        	<i class="mdi mdi-filter"></i>
			        	<span>
			        		<spring:message code="filter" />
			        	</span>
			        </button>
			    </div>
			</fieldset>
</form>