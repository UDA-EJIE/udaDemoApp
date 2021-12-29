<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2 class="title mb-3">
	<spring:message code="iberdok.title" />
</h2>

<!-- TABLA DOCUMENTOS -->
<!-- Formulario necesario para garantizar el correcto funcionamiento con Hdiv cuando filter = 'noFilter' -->
<spring:url value="/iberdok/filter" var="url"/>
<form:form modelAttribute="randomForm" id="iberdokTable_filter_form" class="d-none" action="${url}"/>

<table id="iberdokTable" class="tableFit table-striped table-bordered table-material"
	data-url-base="."
	data-filter-form="#iberdokTable_filter_form">
        <thead>
            <tr>
                <th data-col-prop="id" data-col-edit="false">ID</th>
                <th data-col-prop="nombre">Nombre</th>
                <th data-col-prop="idModelo">ID Modelo</th>
                <th data-col-prop="idDocumento">ID Documento</th>
                <th data-col-prop="estado">Estado</th>
            </tr>
        </thead>
</table>

<jsp:include page="iberdokEdit.jsp"></jsp:include>