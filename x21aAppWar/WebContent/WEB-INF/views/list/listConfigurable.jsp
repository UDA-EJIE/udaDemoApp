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
                    <button id="rup-list-header-sord">
                        <em class="mdi mdi-sort"></em>
                    </button>
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

<h3 style="margin-top: 20px">Configuracion</h3>

<div class="rup_list_configuracion">
    <form id="listConfigFilterForm">
        <fieldset class="p-4">
            <div class="col-md-6">
                <div class="row pb-2">
                    <label for="listConfigMultiSort" class="col-md-2 col-form-label">MultiSort:</label>
                    <div class="col-md-1">
                        <input id="listConfigMultiSort" type="checkbox" name="MultiSort" class="form-control">
                    </div>
                </div>
                <div class="row pb-2">
                    <label for="listConfigScrollList" class="col-md-2 col-form-label">Scroll List:</label>
                    <div class="col-md-1">
                        <input id="listConfigScrollList" type="checkbox" name="ScrollList" class="form-control">
                    </div>
                </div>
                <div class="row pb-2">
                    <label for="listConfigHeaderSticky" class="col-md-2 col-form-label">Header Sticky:</label>
                    <div class="col-md-1">
                        <input id="listConfigHeaderSticky" type="checkbox" name="HeaderSticky" class="form-control">
                    </div>
                </div>
                <div class="row pb-2">
                    <label for="listConfigMultiFilter" class="col-md-2 col-form-label">Multi Filter:</label>
                    <div class="col-md-1">
                        <input id="listConfigMultiFilter" type="checkbox" name="MultiFilter" class="form-control">
                    </div>
                </div>
                <div class="row pb-2">
                    <label for="listConfigPrint" class="col-md-2 col-form-label">Print:</label>
                    <div class="col-md-4">
                        <input id="listConfigPrint" type="text" name="Print" class="form-control">
                        <p>ejemplo: "./print.css"</p>
                    </div>
                </div>
            </div>
        </fieldset>

        <fieldset class="p-4">
            <div class="row">
                <div class="col-md-6">
                    <div class="row pb-2">
                        <label for="listConfigShow" class="col-md-2 col-form-label">Show:</label>
                        <div class="col-md-6">
                            <fieldset>
                                <input id="listConfigShow" type="checkbox" name="Show" class="form-control">
                                <div class="row p-4">
                                    <label for="listConfigShowAnimation">Animation:</label>
                                    <select name="ShowAnimation" id="listConfigShowAnimation">
                                        <option value="fade">fade</option>
                                        <option value="bounce">bounce</option>
                                    </select>
                                </div>
                                <div class="row p-4">
                                    <label for="listConfigShowDelay">Delay:</label>
                                    <input id="listConfigShowDelay" type="number" name="ShowDelay" class="form-control">
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="row pb-2">
                        <label for="listConfigHide" class="col-md-2 col-form-label">Hide:</label>
                        <div class="col-md-6">
                            <fieldset>
                                <input id="listConfigHide" type="checkbox" name="Show" class="form-control">
                                <div class="row p-4">
                                    <label for="listConfigHideAnimation">Animation:</label>
                                    <select name="HideAnimation" id="listConfigHideAnimation">
                                        <option value="fade">fade</option>
                                        <option value="bounce">bounce</option>
                                    </select>
                                </div>
                                <div class="row p-4">
                                    <label for="listConfigHideDelay">Delay:</label>
                                    <input id="listConfigHideDelay" type="number" name="HideDelay" class="form-control">
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
        <button id="listConfigButton">Aplicar</button>
    </form>
</div>