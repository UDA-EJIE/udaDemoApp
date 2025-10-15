<%@include file="/WEB-INF/includeTemplate.inc"%>

<h2><spring:message code="lista.configurable.title" /></h2>

<jsp:include page="includes/listFilterForm.jsp"></jsp:include>

<div id="rup-list-feedback"></div>
<div id="rup-list-content">
    <div id="rup-list-header" class="row">
        <div id="rup-list-header-selectables" class="col-md-3">
            <spring:message code="lista.header.selectables" />
        </div>
        <div class="form-groupMaterial col-md-2">
            <label for="rup-list-header-rowNum"><spring:message code="lista.header.rownum" /></label>
            <select id="rup-list-header-rowNum"></select>
        </div>
        <!-- Ordenar por -->
        <div class="col-md-3">
            <div class="row">
                <div class="form-groupMaterial col-md-7">
                    <label for="rup-list-header-sidx"><spring:message code="lista.header.sidx" /></label>
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
                        <a href="javascript:void(0)" class="page-link d-none d-lg-flex" tabindex="-1"><spring:message code="lista.header.nav.prev" /></a>
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
                        <a href="javascript:void(0)" class="page-link d-none d-lg-flex"><spring:message code="lista.header.nav.next" /></a>
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
            <span class="mdi mdi-36px mdi-cog"/>
        </div>

    </div>
</div>


<div class="rup_list_configuracion">
    <form id="listConfigFilterForm" class="mt-5">
        <fieldset class="mb-3">
            <legend class="float-none"><spring:message code="lista.configurable.opciones.title" /></legend>
            <div class="mb-3">
                <label for="listConfigPrint" class="pe-4"><spring:message code="lista.configurable.opciones.selectables" /></label>
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" id="listConfigSelectNo" name="listConfigSelect" value="0" checked>
                  <label class="form-check-label" for="listConfigSelectNo"><spring:message code="lista.configurable.opciones.selectables.no" /></label>
                </div>
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" id="listConfigSelectSimple" name="listConfigSelect" value="1">
                  <label class="form-check-label" for="listConfigSelectSimple"><spring:message code="lista.configurable.opciones.selectables.simple" /></label>
                </div>
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" id="listConfigSelectMultiple" name="listConfigSelect" value="2">
                  <label class="form-check-label" for="listConfigSelectMultiple"><spring:message code="lista.configurable.opciones.selectables.multiple" /></label>
                </div>
                <div class="checkbox-material form-check-inline ms-4">
                    <input id="listConfigSuperSelect" type="checkbox" disabled>
                    <label for="listConfigSuperSelect"><spring:message code="lista.configurable.opciones.superSelect" /></label>
                </div>
            </div>

            <div class="mb-3">
                <div class="checkbox-material">
                    <input id="listConfigMultiSort" type="checkbox">
                    <label for="listConfigMultiSort"><spring:message code="lista.configurable.opciones.multiSort" /></label>
                </div>
                <div class="checkbox-material">
                    <input id="listConfigScrollList" type="checkbox">
                    <label for="listConfigScrollList"><spring:message code="lista.configurable.opciones.scrollList" /></label>
                </div>
                <div class="checkbox-material">
                    <input id="listConfigHeaderSticky" type="checkbox">
                    <label for="listConfigHeaderSticky"><spring:message code="lista.configurable.opciones.headerSticky" /></label>
                </div>
                <div class="checkbox-material">
                    <input id="listConfigMultiFilter" type="checkbox">
                    <label for="listConfigMultiFilter"><spring:message code="lista.configurable.opciones.multiFilter" /></label>
                </div>
            </div>

            <div class="form-groupMaterial">
                <input type="text" id="listConfigPrint" placeholder="print.css">
                <label for="listConfigPrint"><spring:message code="lista.configurable.opciones.print" /></label>
            </div>

            <div class="row">
                <div class="col">
                    <fieldset class="mb-3">
                        <legend class="col-form-label float-none"><spring:message code="lista.configurable.opciones.show" /></legend>
                        <div class="row">
                            <div class="mb-3 col-md">
                                <div class="checkbox-material">
                                    <input id="listConfigShow" name="Show" type="checkbox">
                                    <label for="listConfigShow"><spring:message code="lista.configurable.opciones.animationSwitch" /></label>
                                </div>
                            </div>
                            <div class="mb-3 col-md">
                                <label for="listConfigShowAnimation"><spring:message code="lista.configurable.opciones.animation" /></label>
                                <select id="listConfigShowAnimation" name="ShowAnimation" class="form-control">
                                    <option value="fade">fade</option>
                                    <option value="bounce">bounce</option>
                                </select>
                            </div>
                            <div class="mb-3 col-md">
                                <label for="listConfigShowDelay"><spring:message code="lista.configurable.opciones.animationTime" /></label>
                                <input type="number" class="form-control" name="ShowDelay" id="listConfigShowDelay" placeholder="200">
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="col">
                    <fieldset class="mb-3">
                        <legend class="col-form-label float-none"><spring:message code="lista.configurable.opciones.hide" /></legend>
                        <div class="row">
                            <div class="mb-3 col-md">
                                <div class="checkbox-material">
                                    <input id="listConfigHide" name="Hide" type="checkbox">
                                    <label for="listConfigHide"><spring:message code="lista.configurable.opciones.animationSwitch" /></label>
                                </div>
                            </div>
                            <div class="mb-3 col-md">
                                <label for="listConfigHideAnimation"><spring:message code="lista.configurable.opciones.animation" /></label>
                                <select id="listConfigHideAnimation" name="HideAnimation" class="form-control">
                                    <option value="fade">fade</option>
                                    <option value="bounce">bounce</option>
                                </select>
                            </div>
                            <div class="mb-3 col-md">
                                <label for="listConfigHideDelay"><spring:message code="lista.configurable.opciones.animationTime" /></label>
                                <input type="number" class="form-control" name="HideDelay" id="listConfigHideDelay" placeholder="200">
                            </div>
                        </div>
                    </fieldset>
                </div>
            </div>
        </fieldset>

        <button id="listConfigButton" type="button" class="btn-material btn-material-primary-medium-emphasis"><spring:message code="lista.configurable.opciones.apply" /></button>
    </form>
</div>
