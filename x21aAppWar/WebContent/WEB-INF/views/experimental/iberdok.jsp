<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2 class="title mb-3">
	<spring:message code="iberdok.title" />
</h2>

<!-- TABLA DOCUMENTOS -->
<spring:url value="/iberdok/filter" var="url"/>
<form:form modelAttribute="randomForm" id="iberdokTable_filter_form" action="${url}">
	<!-- Barra de herramientas del formulario de filtrado -->
	<div id="iberdokTable_filter_toolbar" class="formulario_legend"></div>
	<fieldset id="iberdokTable_filter_fieldset" class="rup-table-filter-fieldset">
		<div class="form-row">
			<!-- Campos del formulario de filtrado -->
			<div class="form-groupMaterial col-sm">
				<form:input path="idDocumento" id="idDocumento_filter_table" />
				<label for="idDocumento_filter_table">
					<spring:message code="idDocumento"/>
				</label>
			</div>
		</div>
		<!-- Botonera del formulario de filtrado -->
	    <div id="iberdokTable_filter_buttonSet" class="text-right">
	    	<!-- Botón de limpiar -->
	        <button id="iberdokTable_filter_cleanButton" type="button" class="btn-material btn-material-primary-low-emphasis mr-2">
	        	<i class="mdi mdi-eraser"></i>
	        	<span>
					<spring:message code="clear" />
				</span>
	        </button>
	        <!-- Botón de filtrado -->
	        <button id="iberdokTable_filter_filterButton" type="button" class="btn-material btn-material-primary-high-emphasis">
	        	<i class="mdi mdi-filter"></i>
	        	<span>
					<spring:message code="filter" />
				</span>        	
	        </button>
	    </div>
	</fieldset>
</form:form>

<table id="iberdokTable" class="tableFit table-striped table-bordered table-material"
	data-url-base="."
	data-filter-form="#iberdokTable_filter_form">
        <thead>
            <tr>
                <th data-col-prop="id" data-col-edit="false">ID</th>
                <th data-col-prop="nombre">Nombre</th>
                <th data-col-prop="idModelo" data-col-sidx="id_modelo">ID Modelo</th>
                <th data-col-prop="idDocumento" data-col-sidx="id_documento">ID Documento</th>
                <th data-col-prop="usuario" data-col-sidx="usuario">Usuario</th>
                <th data-col-prop="fechaApp" data-col-sidx="fecha_app">Fecha Editado</th>
                <th data-col-prop="fechaIberdok" data-col-sidx="fecha_iberdok">Fecha Vuelta de iberdok</th>
                <th data-col-prop="docFinalizado" data-col-sidx="estado">Estado</th>
            </tr>
        </thead>
</table>

<jsp:include page="iberdokEditForm.jsp"></jsp:include>