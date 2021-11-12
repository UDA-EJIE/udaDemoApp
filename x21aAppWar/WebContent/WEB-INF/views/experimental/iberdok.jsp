<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2 class="title mb-3">
	<spring:message code="iberdok.title" />
</h2>

<!-- TABLA DOCUMENTOS -->
<!-- Formulario necesario para garantizar el correcto funcionamiento con Hdiv cuando filter = 'noFilter' -->
<spring:url value="/iberdok/filter" var="url"/>
<form:form modelAttribute="randomForm" id="iberdokTable_filter_form"  action="${url}">
			<div id="iberdokTable_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="iberdokTable_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="idDocumento_filter_table" class="formulario_linea_label">
							<spring:message code="idDocumento"/>
						</label>
						<form:input path="idDocumento" class="formulario_linea_input form-control" id="idDocumento_filter_table" />
					</div>
					<!-- Fin campos del formulario de filtrado -->
				</div>
				<!-- Botonera del formulario de filtrado -->
				<div id="iberdokTable_filter_buttonSet" class="right_buttons">
					<button id="iberdokTable_filter_cleanButton" type="button" class="btn btn-primary rup-limpiar">
			        	<i class="fa fa-eraser"></i>
			        	<span>
			        		<spring:message code="clear" />
			        	</span>
			        </button>
			        <button id="iberdokTable_filter_filterButton" type="button" class="btn btn-primary rup-filtrar">
			        	<i class="fa fa-filter"></i>
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

<jsp:include page="iberdokEdit.jsp"></jsp:include>