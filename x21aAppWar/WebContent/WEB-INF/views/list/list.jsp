<%@include file="/WEB-INF/includeTemplate.inc"%>

<h2>Componente rup_list</h2>

<jsp:include page="includes/listFilterForm.jsp"></jsp:include>

<div id="rup-list-feedback"></div>
<div id="rup-list-content">
    <fieldset id="rup-list-header">
        <div class="row">
            <div id="rup-list-header-selectables" class="col-md-3">
                Selección:
            </div>
            <div class="col-md-2">
                <label for="rup-list-header-rowNum">Elementos por página:</label>
                <select id="rup-list-header-rowNum"></select>
            </div>
            <!-- Ordenar por -->
            <div class="col-md-4">
                <div class="row">
                    <div class="col-md-7">
                        <label for="rup-list-header-sidx">Ordenar por:</label>
                        <select id="rup-list-header-sidx"></select>
                    </div>
                    <div class="col-md-2">
                        <button id="rup-list-header-sord">
                            <i class="mdi mdi-sort"></i>
                        </button>
                    </div>
                </div>
            </div>
            <!-- Navegación -->
            <div class="col-md-2">
                <nav id="rup-list-header-nav">
                    <ul class="pagination">
                        <li id="rup-list-header-page-prev" class="page-item disabled">
                            <a href="javascript:void(0)" class="page-link d-none d-lg-flex" tabindex="-1">Anterior</a>
                            <a href="javascript:void(0)" class="page-link d-lg-none" tabindex="-1">
                                <span class="mdi mdi-arrow-right-bold-circle-outline mdi-rotate-180"/>
                            </a>
                        </li>
                        <li class="page-item page-separator disabled">
                            <a class="page-link" tabindex="-1">...</a>
                        </li>
                        <li class="page-item page-separator disabled">
                            <a class="page-link" tabindex="-1">...</a>
                        </li>
                        <li id="rup-list-header-page-next" class="page-item disabled">
                            <a href="javascript:void(0)" class="page-link d-none d-lg-flex">Siguiente</a>
                            <a href="javascript:void(0)" class="page-link d-lg-none" tabindex="-1">
                                <span class="mdi mdi-arrow-right-bold-circle-outline"/>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </fieldset>
    <div id="rup-list"></div>
    <div id="rup-list-itemTemplate" class="row list-item">
        <div class="col-md-1" style="border-right: 1px solid gray;display:flex;justify-content:center;align-items:center;">
            <span class="mdi mdi-account-circle" style="transform:scale(2);"/>
        </div>
        <div class="col-md-2" style="border-right: 1px solid gray;display:flex;align-items:center;flex-direction:column;">
            <div style="border-bottom: 1px gray solid;">
                <b><span id="id_label" /></b>: <span id="id_value"/>
            </div>
            <div>
                <b><span id="nombre_label"/></b>: <span id="nombre_value"/>
            </div>

        </div>
        <div class="col-md-2" style="border-right: 1px solid gray;display:flex;align-items:center;flex-direction:column;">
            <div><span id="apellido1_label"/></div>
            <div><span id="apellido1_value"/></div>
        </div>
        <div class="col-md-4" style="border-right: 1px solid gray;display:flex;align-items:center;flex-direction:column;">
            <div style="border-bottom: 1px solid gray;">
                <span id="apellido2_label"></span>: <span id="apellido2_value"></span>
            </div>
            <div>
                <span id="ejie_label"></span>: <span id="ejie_value"></span>
            </div>
        </div>
        <div class="col-md-3" style="display: flex; justify-content:space-around;">
            <span class="mdi mdi-36px mdi-account-edit"/>
            <span class="mdi mdi-36px mdi-account-remove"/>
            <span class="mdi mdi-36px mdi-settings"/>
        </div>

    </div>
</div>