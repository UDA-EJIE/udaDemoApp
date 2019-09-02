<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2>${tituloPagina}</h2> <!-- Titulo pagina -->

<span> Weblogic Name : <%=System.getProperty("weblogic.Name")%></span>

<div id="table_div" class="rup-table-container">
	<div id="table_feedback"></div>
	<div id="table_toolbar"></div>
	<div id="table_filter_div" class="rup-table-filter">
		<form:form modelAttribute="formRandom" id="table_filter_form">
			<div id="table_filter_toolbar" class="formulario_legend"></div>
			<fieldset id="table_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="name_detailForm_table" class="formulario_linea_label"><spring:message code="name" /></label>
						<form:input path="nameLog" class="formulario_linea_input form-control" id="name_filter_table" />
					</div>
					<div class="form-group col-sm">
						<label for="level_detail_table" class="formulario_linea_label"><spring:message code="level" /></label>
						<form:select path="levelLog" class="formulario_linea_input form-control" id="level_filter_table" />
					</div>
				
				</div>
				
				<div id="table_filter_buttonSet" class="right_buttons">
			        <button id="table_filter_cleanButton" type="button" class="btn btn-primary rup-limpiar">
			        	<i class="mdi mdi-eraser"></i>
			        	<span>
			        		<spring:message code="clear" />
			        	</span>
			        </button>
			        <button id="table_filter_filterButton" type="button" class="btn btn-info rup-filtrar">
			        	<i class="mdi mdi-filter"></i>
			        	<span>
			        		<spring:message code="filter" />
			        	</span>
			        </button>
			    </div>
			</fieldset>
		</form:form>
	</div>

	<div id="table_grid_div">
		<!-- Tabla -->
		<table id="table"></table>
		<!-- Barra de paginación -->
		<div id="table_pager"></div>
	</div>
</div>	

<div id="table_detail_div" class="rup-table-formEdit-detail">
	<div id ="table_detail_navigation"></div>
	<div class="ui-dialog-content ui-widget-content" >
		<form:form modelAttribute="randomForm" id="table_detail_form">
			<div id ="table_detail_feedback"></div>
			<div class="form-row">
				<div class="form-group col-sm">
					<label for="name_detailForm_table" class="formulario_linea_label"><spring:message code="name" /></label>
					<form:input path="nameLog" class="formulario_linea_input form-control" id="name_detail_table" />
				</div>
				<div class="form-group col-sm">
					<label for="level_detail_table" class="formulario_linea_label"><spring:message code="level" /></label>
					<form:select path="levelLog" class="formulario_linea_input form-control" id="level_detail_table" />
				</div>
				
			</div>
		</form:form>
	</div>
	
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset rup_jqtableEdit_buttonsContainerResposive">
			<button id="table_detail_button_save" class="btn btn-outline-primary rup_jqtableEdit_buttonsResposive" type="button">
				<spring:message code="save" />
			</button>
			<button id="table_detail_button_save_repeat" class="btn btn-outline-primary rup_jqtableEdit_buttonsResposive" type="button">
				<spring:message code="saveAndContinue" />
			</button>
			<button id="table_detail_button_cancel" class="btn btn-outline-primary rup_jqtableEdit_buttonsResposive" type="button">
				<spring:message code="cancel" />
			</button>
		</div>
	</div>	
	
</div>


