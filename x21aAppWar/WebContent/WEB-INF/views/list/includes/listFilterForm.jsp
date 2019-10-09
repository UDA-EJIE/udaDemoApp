<%@include file="/WEB-INF/includeTemplate.inc"%>
<form:form modelAttribute="usuario" id="listFilterForm">
        <fieldset>
            <div class="row pb-2">
                <div class="col-md-1"></div>
                <div class="col-md-4">
                    <div class="row">
                        <label for="listFilterId">Id:</label>
                        <div class="col-md-1"></div>
                        <form:input id="listFilterId" path="id" class="col-md-9"/>
                    </div>
                </div>
                <div class="col-md-2"></div>
                <div class="col-md-4">
                    <div class="row">
                        <label for="listFilterNombre">Nombre:</label>
                        <div class="col-md-1"></div>
                        <form:input id="listFilterNombre" path="nombre" class="col-md-9"/>
                    </div>
                </div>
            </div>
            <div class="row pb-2">
                <div class="col-md-1"></div>
                <div class="col-md-4">
                    <div class="row">
                        <label for="listFilterApe1">Edad:</label>
                        <div class="col-md-1"></div>
                        <form:input id="listFilterApe1" path="apellido1" class="col-md-9"/>
                    </div>
                </div>
                <div class="col-md-2"></div>
                <div class="col-md-4">
                    <div class="row">
                        <label for="listFilterCodApe2">Codigo cliente:</label>
                        <div class="col-md-1"></div>
                        <form:input id="listFilterApe2" path="apellido2" class="col-md-8"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-5"></div>
                <button id="listFilterAceptar" class="mdi mdi-filter col-md-2"> Filtrar </button>
                <div class="col-md-1"></div>
                <button id="listFilterLimpiar" class="mdi mdi-broom col-md-2"> Limpiar </button>
            </div>
        </fieldset>
    </form:form>