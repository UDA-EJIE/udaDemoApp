<%@include file="/WEB-INF/includeTemplate.inc"%>
<fieldset class="p-4">
    <legend><spring:message code="lista.filter.legend" /></legend>
    <form:form modelAttribute="usuario" id="listFilterFormRight">
        <div class="row pb-2">
            <label for="listFilterId" class="col-md-2 col-form-label"><spring:message code="lista.filter.id" />:</label>
            <div class="col-md-4">
                <form:input id="listFilterId" path="id" class="form-control"/>
            </div>
            <label for="listFilterNombre" class="col-md-2 col-form-label"><spring:message code="lista.filter.nombre" />:</label>
            <div class="col-md-4">
                <form:input id="listFilterNombre" path="nombre" class="form-control"/>
            </div>
        </div>
        <div class="row pb-2">
            <label for="listFilterApe1" class="col-md-2 col-form-label"><spring:message code="lista.filter.apellido1" />:</label>
            <div class="col-md-4">
                <form:input id="listFilterApe1" path="apellido1" class="form-control"/>
            </div>
            <label for="listFilterCodApe2" class="col-md-2 col-form-label"><spring:message code="lista.filter.apellido2" />:</label>
            <div class="col-md-4">
                <form:input id="listFilterApe2" path="apellido2" class="form-control"/>
            </div>
        </div>
        <div class="float-right">
            <button id="listFilterAceptar" class="btn btn-primary"><em class="mdi mdi-filter"></em> <spring:message code="lista.filter.filter" /> </button>
            <button id="listFilterLimpiar"><em class="mdi mdi-broom"></em><spring:message code="lista.filter.clean" /> </button>
        </div>
    </form:form>
</fieldset>
