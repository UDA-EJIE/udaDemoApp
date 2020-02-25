<%@include file="/WEB-INF/includeTemplate.inc"%>
<fieldset class="p-4">
    <legend></legend>
    <form:form modelAttribute="usuario" id="listFilterFormRight">
        <div class="row pb-2">
            <label for="listFilterId" class="col-md-2 col-form-label">Id:</label>
            <div class="col-md-4">
                <form:input id="listFilterId" path="id" class="form-control"/>
            </div>
            <label for="listFilterNombre" class="col-md-2 col-form-label">Nombre:</label>
            <div class="col-md-4">
                <form:input id="listFilterNombre" path="nombre" class="form-control"/>
            </div>
        </div>
        <div class="row pb-2">
            <label for="listFilterApe1" class="col-md-2 col-form-label">Apellido 1:</label>
            <div class="col-md-4">
                <form:input id="listFilterApe1" path="apellido1" class="form-control"/>
            </div>
            <label for="listFilterCodApe2" class="col-md-2 col-form-label">Apellido 2:</label>
            <div class="col-md-4">
                <form:input id="listFilterApe2" path="apellido2" class="form-control"/>
            </div>
        </div>
        <div class="float-right">
            <button id="listFilterAceptar" class="btn btn-primary"><em class="mdi mdi-filter"></em> Filtrar </button>
            <button id="listFilterLimpiar"><em class="mdi mdi-broom"></em>Limpiar </button>
        </div>
    </form:form>
</fieldset>
