<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2>${tituloPagina}</h2> <!-- Titulo pagina -->

<span> Weblogic Name : <%=System.getProperty("weblogic.Name")%></span>

<div id="table_div" class="rup-table-container">
	<div id="table_feedback"></div>
	<div id="table_toolbar"></div>
	<div id="table_filter_div" class="rup-table-filter">
		<form id="table_filter_form">
			<div id="table_filter_toolbar" class="formulario_legend"></div>
			<fieldset id="table_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="formulario_columna_cnt">
					<div class="floating_left_pad_right one-column">
						<label for="name_detailForm_table"><spring:message code="name" />:</label>
						<input type="text" name="nameLog" class="formulario_linea_input" id="name_filter_table" />
					</div>
					<div class="floating_left_pad_right one-column">
						<label for="level_detail_table"><spring:message code="level" />:</label>
						<select name="levelLog" class="formulario_linea_input" id="level_filter_table" ></select>
					</div>
				
				</div>
				<div id="table_filter_buttonSet" class="right_buttons">
					<button id="table_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" ><spring:message code="filter" /></button>
					<a id="table_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
				</div>
			</fieldset>
		</form>
	</div>

	<div id="table_grid_div">
		<!-- Tabla -->
		<table id="table"></table>
		<!-- Barra de paginaci�n -->
		<div id="table_pager"></div>
	</div>
</div>	

<div id="table_detail_div" class="rup-table-formEdit-detail">
	<div id ="table_detail_navigation"></div>
	<div class="ui-dialog-content ui-widget-content" >
		<form id="table_detail_form">
			<div id ="table_detail_feedback"></div>
			<div class="floating_left_pad_right">
				<div class="floating_left_pad_right one-column">
					<label for="name_detailForm_table"><spring:message code="name" />:</label>
					<input type="text" name="nameLog" class="formulario_linea_input" id="name_detail_table" />
				</div>
				<div class="floating_left_pad_right one-column">
					<label for="level_detail_table"><spring:message code="level" />:</label>
					<select name="levelLog" class="formulario_linea_input" id="level_detail_table" ></select>
				</div>
				
			</div>
		</form>
	</div>
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<button id="table_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<button id="table_detail_button_save_repeat" type="button">
				<spring:message code="saveAndContinue" />
			</button>
			<a href="javascript:void(0)" role="button" id="table_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
	
	
	
	
	
</div>


