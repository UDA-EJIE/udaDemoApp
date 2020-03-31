<%@include file="/WEB-INF/includeTemplate.inc"%>

<h2>Componente rup_list Configurable</h2>

<jsp:include page="includes/listFilterForm.jsp"></jsp:include>

<div id="rup-list-feedback"></div>
<div id="rup-list-content">
    <div id="rup-list-header" class="row">
        <div id="rup-list-header-selectables" class="col-md-3">
            Opciones de selección:
        </div>
        <div class="col-md-2">
            <label for="rup-list-header-rowNum">Elementos por página:</label>
            <select id="rup-list-header-rowNum"></select>
        </div>
        <!-- Ordenar por -->
        <div class="col-md-3">
            <div class="row">
                <div class="col-md-7">
                    <label for="rup-list-header-sidx">Ordenar por:</label>
                    <select id="rup-list-header-sidx"></select>
                </div>
                <div class="col-md-2">
                    <button id="rup-list-header-sord"></button>
                </div>
            </div>
        </div>
        <!-- Navegación -->
        <div class="col-md-4">
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
    <div id="rup-list"></div>
    <div id="rup-list-itemTemplate" class="row list-item">
        <div class="col-md-1" style="border-right: 1px solid gray;display:flex;justify-content:center;align-items:center;">
            <span class="mdi mdi-account-circle" style="transform:scale(2);"/>
        </div>
        <div class="col-md-2" style="border-right: 1px solid gray;display:flex;align-items:center;flex-direction:column;">
            <div style="border-bottom: 1px gray solid;">
                <strong><span id="id_label" /></strong>: <span id="id_value"/>
            </div>
            <div>
                <strong><span id="nombre_label"/></strong>: <span id="nombre_value"/>
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


<div class="rup_list_configuracion">
    <form id="listConfigFilterForm" class="mt-5">
        <fieldset class="form-group">
            <legend>Configuracion</legend>
            <div class="form-group">
                <label for="listConfigPrint" class="pr-4">Seleccionables:</label>
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" id="listConfigSelectNo" name="listConfigSelect" value="0" checked>
                  <label class="form-check-label" for="listConfigSelectNo">No</label>
                </div>
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" id="listConfigSelectSimple" name="listConfigSelect" value="1">
                  <label class="form-check-label" for="listConfigSelectSimple">Simple</label>
                </div>
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" id="listConfigSelectMultiple" name="listConfigSelect" value="2">
                  <label class="form-check-label" for="listConfigSelectMultiple">Múltiple</label>
                </div>
                <div class="checkbox-material form-check-inline ml-4">
                    <input id="listConfigSuperSelect" type="checkbox" disabled>
                    <label for="listConfigSuperSelect">Super Select</label>
                </div>
            </div>

            <div class="form-group">
                <div class="checkbox-material">
                    <input id="listConfigMultiSort" type="checkbox">
                    <label for="listConfigMultiSort">MultiSort</label>
                </div>
                <div class="checkbox-material">
                    <input id="listConfigScrollList" type="checkbox">
                    <label for="listConfigScrollList">Scroll List</label>
                </div>
                <div class="checkbox-material">
                    <input id="listConfigHeaderSticky" type="checkbox">
                    <label for="listConfigHeaderSticky">Header Sticky</label>
                </div>
                <div class="checkbox-material">
                    <input id="listConfigMultiFilter" type="checkbox">
                    <label for="listConfigMultiFilter">Multi Filter</label>
                </div>
            </div>

            <div class="form-groupMaterial">
                <input type="text" id="listConfigPrint" placeholder="print.css">
                <label for="listConfigPrint">Print</label>
            </div>

            <div class="row">
                <div class="col">
                    <fieldset class="form-group">
                        <legend class="col-form-label">Show</legend>
                        <div class="row">
                            <div class="form-group col-md">
                                <div class="checkbox-material">
                                    <input id="listConfigShow" name="Show" type="checkbox">
                                    <label for="listConfigShow">On/Off</label>
                                </div>
                            </div>
                            <div class="form-group col-md">
                                <label for="listConfigShowAnimation">Animation</label>
                                <select id="listConfigShowAnimation" name="ShowAnimation" class="form-control">
                                    <option value="fade">fade</option>
                                    <option value="bounce">bounce</option>
                                </select>
                            </div>
                            <div class="form-group col-md">
                                <label for="listConfigShowDelay">Delay</label>
                                <input type="number" class="form-control" name="ShowDelay" id="listConfigShowDelay" placeholder="200">
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="col">
                    <fieldset class="form-group">
                        <legend class="col-form-label">Hide</legend>
                        <div class="row">
                            <div class="form-group col-md">
                                <div class="checkbox-material">
                                    <input id="listConfigHide" name="Hide" type="checkbox">
                                    <label for="listConfigHide">On/Off</label>
                                </div>
                            </div>
                            <div class="form-group col-md">
                                <label for="listConfigHideAnimation">Animation</label>
                                <select id="listConfigHideAnimation" name="HideAnimation" class="form-control">
                                    <option value="fade">fade</option>
                                    <option value="bounce">bounce</option>
                                </select>
                            </div>
                            <div class="form-group col-md">
                                <label for="listConfigHideDelay">Delay</label>
                                <input type="number" class="form-control" name="HideDelay" id="listConfigHideDelay" placeholder="200">
                            </div>
                        </div>
                    </fieldset>
                </div>
            </div>
        </fieldset>

        <button id="listConfigButton" type="button" class="btn-material btn-material-primary-medium-emphasis">Aplicar</button>
    </form>
</div>
