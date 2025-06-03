<%@include file="/WEB-INF/includeTemplate.inc"%>

<div class="row">
    <div class="col-md-6">
        <h2><spring:message code="lista.configurableLeft" /></h2>
        
        <jsp:include page="includes/listFilterFormDobleLeft.jsp"></jsp:include>
        
        <div id="rup-list-left-feedback"></div>
        <div id="rup-list-left-content">
            <div id="rup-list-left-header" class="row">
                <div id="rup-list-left-header-selectables" class="col-md-3">
                    <spring:message code="lista.header.selectables" />
                </div>
                <div class="form-groupMaterial col-md-2">
                    <label for="rup-list-left-header-rowNum"><spring:message code="lista.header.rownum" /></label>
                    <select id="rup-list-left-header-rowNum"></select>
                </div>
                <!-- Ordenar por -->
                <div class="col-md-3">
                    <div class="row">
                        <div class="form-groupMaterial col-md-7">
                            <label for="rup-list-left-header-sidx"><spring:message code="lista.header.sidx" /></label>
                            <select id="rup-list-left-header-sidx"></select>
                        </div>
                        <div class="col-md-2">
                            <button id="rup-list-left-header-sord"></button>
                        </div>
                    </div>
                </div>
                <!-- Navegación -->
                <div class="col-md-4">
                    <nav id="rup-list-left-header-nav">
                        <ul class="pagination">
                            <li id="rup-list-left-header-page-prev" class="page-item disabled">
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
                            <li id="rup-list-left-header-page-next" class="page-item disabled">
                                <a href="javascript:void(0)" class="page-link d-none d-lg-flex"><spring:message code="lista.header.nav.next" /></a>
                                <a href="javascript:void(0)" class="page-link d-lg-none" tabindex="-1">
                                    <span class="mdi mdi-arrow-right-bold-circle-outline"/>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div id="rup-list-left"></div>
            <div id="rup-list-left-itemTemplate" class="row list-item">
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
            <form id="listConfigLeftFilterForm" class="mt-5">
                <fieldset class="mb-3">
                    <legend><spring:message code="lista.configurable.opciones.title" /></legend>
                    <div class="mb-3">
                        <label for="listConfigLeftPrint" class="pr-4"><spring:message code="lista.configurable.opciones.selectables" /></label>
                        <div class="form-check form-check-inline">
                          <input class="form-check-input" type="radio" id="listConfigLeftSelectNo" name="listConfigLeftSelect" value="0" checked>
                          <label class="form-check-label" for="listConfigLeftSelectNo"><spring:message code="lista.configurable.opciones.selectables.no" /></label>
                        </div>
                        <div class="form-check form-check-inline">
                          <input class="form-check-input" type="radio" id="listConfigLeftSelectSimple" name="listConfigLeftSelect" value="1">
                          <label class="form-check-label" for="listConfigLeftSelectSimple"><spring:message code="lista.configurable.opciones.selectables.simple" /></label>
                        </div>
                        <div class="form-check form-check-inline">
                          <input class="form-check-input" type="radio" id="listConfigLeftSelectMultiple" name="listConfigLeftSelect" value="2">
                          <label class="form-check-label" for="listConfigLeftSelectMultiple"><spring:message code="lista.configurable.opciones.selectables.multiple" /></label>
                        </div>
                        <div class="checkbox-material form-check-inline ms-4">
                            <input id="listConfigLeftSuperSelect" type="checkbox" disabled>
                            <label for="listConfigLeftSuperSelect"><spring:message code="lista.configurable.opciones.superSelect" /></label>
                        </div>
                    </div>

                    <div class="mb-3">
                        <div class="checkbox-material">
                            <input id="listConfigLeftMultiSort" type="checkbox">
                            <label for="listConfigLeftMultiSort"><spring:message code="lista.configurable.opciones.multiSort" /></label>
                        </div>
                        <div class="checkbox-material">
                            <input id="listConfigLeftScrollList" type="checkbox">
                            <label for="listConfigLeftScrollList"><spring:message code="lista.configurable.opciones.scrollList" /></label>
                        </div>
                        <div class="checkbox-material">
                            <input id="listConfigLeftHeaderSticky" type="checkbox">
                            <label for="listConfigLeftHeaderSticky"><spring:message code="lista.configurable.opciones.headerSticky" /></label>
                        </div>
                        <div class="checkbox-material">
                            <input id="listConfigLeftMultiFilter" type="checkbox">
                            <label for="listConfigLeftMultiFilter"><spring:message code="lista.configurable.opciones.multiFilter" /></label>
                        </div>
                    </div>

                    <div class="form-groupMaterial">
                        <div class="form-groupMaterial">
                            <input type="text" id="listConfigLeftPrint" placeholder="print.css">
                            <label for="listConfigLeftPrint"><spring:message code="lista.configurable.opciones.print" /></label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col">
                            <fieldset class="mb-3">
                                <legend class="col-form-label"><spring:message code="lista.configurable.opciones.show" /></legend>
                                <div class="row">
                                    <div class="mb-3 col-md">
                                        <div class="checkbox-material">
                                            <input id="listConfigLeftShow" name="Show" type="checkbox">
                                            <label for="listConfigLeftShow"><spring:message code="lista.configurable.opciones.animationSwitch" /></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-3 col-md">
                                    <label for="listConfigLeftShowAnimation"><spring:message code="lista.configurable.opciones.animation" /></label>
                                    <select id="listConfigLeftShowAnimation" name="ShowAnimation" class="form-control">
                                        <option value="fade">fade</option>
                                        <option value="bounce">bounce</option>
                                    </select>
                                </div>
                                <div class="mb-3 col-md">
                                    <label for="listConfigLeftShowDelay"><spring:message code="lista.configurable.opciones.animationTime" /></label>
                                    <input type="number" class="form-control" name="ShowDelay" id="listConfigLeftShowDelay" placeholder="200">
                                </div>
                            </fieldset>
                        </div>
                        <div class="col">
                            <fieldset class="mb-3">
                                <legend class="col-form-label"><spring:message code="lista.configurable.opciones.hide" /></legend>
                                <div class="row">
                                    <div class="mb-3 col-md">
                                        <div class="checkbox-material">
                                            <input id="listConfigLeftHide" name="Hide" type="checkbox">
                                            <label for="listConfigLeftHide"><spring:message code="lista.configurable.opciones.animationSwitch" /></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-3 col-md">
                                    <label for="listConfigLeftHideAnimation"><spring:message code="lista.configurable.opciones.animation" /></label>
                                    <select id="listConfigLeftHideAnimation" name="HideAnimation" class="form-control">
                                        <option value="fade">fade</option>
                                        <option value="bounce">bounce</option>
                                    </select>
                                </div>
                                <div class="mb-3 col-md">
                                    <label for="listConfigLeftHideDelay"><spring:message code="lista.configurable.opciones.animationTime" /></label>
                                    <input type="number" class="form-control" name="HideDelay" id="listConfigLeftHideDelay" placeholder="200">
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </fieldset>

                <button id="listConfigLeftButton" type="button" class="btn-material btn-material-primary-medium-emphasis"><spring:message code="lista.configurable.opciones.apply" /></button>
            </form>
        </div>
    </div>

    <div class="col-md-6">
        <h2><spring:message code="lista.configurableRight" /></h2>
        
        <jsp:include page="includes/listFilterFormDobleRight.jsp"></jsp:include>
        
        <div id="rup-list-right-feedback"></div>
        <div id="rup-list-right-content">
            <div id="rup-list-right-header" class="row">
                <div id="rup-list-right-header-selectables" class="col-md-3">
                    <spring:message code="lista.header.selectables" />
                </div>
                <div class="form-groupMaterial col-md-2">
                    <label for="rup-list-right-header-rowNum"><spring:message code="lista.header.rownum" /></label>
                    <select id="rup-list-right-header-rowNum"></select>
                </div>
                <!-- Ordenar por -->
                <div class="col-md-3">
                    <div class="row">
                        <div class="form-groupMaterial col-md-7">
                            <label for="rup-list-right-header-sidx"><spring:message code="lista.header.sidx" /></label>
                            <select id="rup-list-right-header-sidx"></select>
                        </div>
                        <div class="col-md-2">
                            <button id="rup-list-right-header-sord"></button>
                        </div>
                    </div>
                </div>
                <!-- Navegación -->
                <div class="col-md-4">
                    <nav id="rup-list-right-header-nav">
                        <ul class="pagination">
                            <li id="rup-list-right-header-page-prev" class="page-item disabled">
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
                            <li id="rup-list-right-header-page-next" class="page-item disabled">
                                <a href="javascript:void(0)" class="page-link d-none d-lg-flex"><spring:message code="lista.header.nav.next" /></a>
                                <a href="javascript:void(0)" class="page-link d-lg-none" tabindex="-1">
                                    <span class="mdi mdi-arrow-right-bold-circle-outline"/>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div id="rup-list-right"></div>
            <div id="rup-list-right-itemTemplate" class="row list-item">
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
            <form id="listConfigRightFilterForm" class="mt-5">
                <fieldset class="mb-3">
                    <legend><spring:message code="lista.configurable.opciones.title" /></legend>
                    <div class="mb-3">
                        <label for="listConfigRightPrint" class="pr-4"><spring:message code="lista.configurable.opciones.selectables" /></label>
                        <div class="form-check form-check-inline">
                          <input class="form-check-input" type="radio" id="listConfigRightSelectNo" name="listConfigRightSelect" value="0" checked>
                          <label class="form-check-label" for="listConfigRightSelectNo"><spring:message code="lista.configurable.opciones.selectables.no" /></label>
                        </div>
                        <div class="form-check form-check-inline">
                          <input class="form-check-input" type="radio" id="listConfigRightSelectSimple" name="listConfigRightSelect" value="1">
                          <label class="form-check-label" for="listConfigRightSelectSimple"><spring:message code="lista.configurable.opciones.selectables.simple" /></label>
                        </div>
                        <div class="form-check form-check-inline">
                          <input class="form-check-input" type="radio" id="listConfigRightSelectMultiple" name="listConfigRightSelect" value="2">
                          <label class="form-check-label" for="listConfigRightSelectMultiple"><spring:message code="lista.configurable.opciones.selectables.multiple" /></label>
                        </div>
                        <div class="checkbox-material form-check-inline ms-4">
                            <input id="listConfigRightSuperSelect" type="checkbox" disabled>
                            <label for="listConfigRightSuperSelect"><spring:message code="lista.configurable.opciones.superSelect" /></label>
                        </div>
                    </div>

                    <div class="mb-3">
                        <div class="checkbox-material">
                            <input id="listConfigRightMultiSort" type="checkbox">
                            <label for="listConfigRightMultiSort"><spring:message code="lista.configurable.opciones.multiSort" /></label>
                        </div>
                        <div class="checkbox-material">
                            <input id="listConfigRightScrollList" type="checkbox">
                            <label for="listConfigRightScrollList"><spring:message code="lista.configurable.opciones.scrollList" /></label>
                        </div>
                        <div class="checkbox-material">
                            <input id="listConfigRightHeaderSticky" type="checkbox">
                            <label for="listConfigRightHeaderSticky"><spring:message code="lista.configurable.opciones.headerSticky" /></label>
                        </div>
                        <div class="checkbox-material">
                            <input id="listConfigRightMultiFilter" type="checkbox">
                            <label for="listConfigRightMultiFilter"><spring:message code="lista.configurable.opciones.multiFilter" /></label>
                        </div>
                    </div>

                    <div class="mb-3">
                        <div class="form-groupMaterial">
                            <input type="text" id="listConfigRightPrint" placeholder="print.css">
                            <label for="listConfigRightPrint"><spring:message code="lista.configurable.opciones.print" /></label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col">
                            <fieldset class="mb-3">
                                <legend class="col-form-label"><spring:message code="lista.configurable.opciones.show" /></legend>
                                <div class="row">
                                    <div class="mb-3 col-md">
                                        <div class="checkbox-material">
                                            <input id="listConfigRightShow" name="Show" type="checkbox">
                                            <label for="listConfigRightShow"><spring:message code="lista.configurable.opciones.animationSwitch" /></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-3 col-md">
                                    <label for="listConfigRightShowAnimation"><spring:message code="lista.configurable.opciones.animation" /></label>
                                    <select id="listConfigRightShowAnimation" name="ShowAnimation" class="form-control">
                                        <option value="fade">fade</option>
                                        <option value="bounce">bounce</option>
                                    </select>
                                </div>
                                <div class="mb-3 col-md">
                                    <label for="listConfigRightShowDelay"><spring:message code="lista.configurable.opciones.animationTime" /></label>
                                    <input type="number" class="form-control" name="ShowDelay" id="listConfigRightShowDelay" placeholder="200">
                                </div>
                            </fieldset>
                        </div>
                        <div class="col">
                            <fieldset class="mb-3">
                                <legend class="col-form-label"><spring:message code="lista.configurable.opciones.hide" /></legend>
                                <div class="row">
                                    <div class="mb-3 col-md">
                                        <div class="checkbox-material">
                                            <input id="listConfigRightHide" name="Hide" type="checkbox">
                                            <label for="listConfigRightHide"><spring:message code="lista.configurable.opciones.animationSwitch" /></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-3 col-md">
                                    <label for="listConfigRightHideAnimation"><spring:message code="lista.configurable.opciones.animation" /></label>
                                    <select id="listConfigRightHideAnimation" name="HideAnimation" class="form-control">
                                        <option value="fade">fade</option>
                                        <option value="bounce">bounce</option>
                                    </select>
                                </div>
                                <div class="mb-3 col-md">
                                    <label for="listConfigRightHideDelay"><spring:message code="lista.configurable.opciones.animationTime" /></label>
                                    <input type="number" class="form-control" name="HideDelay" id="listConfigRightHideDelay" placeholder="200">
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </fieldset>

                <button id="listConfigRightButton" type="button" class="btn-material btn-material-primary-medium-emphasis"><spring:message code="lista.configurable.opciones.apply" /></button>
            </form>
        </div>
        
    </div>
</div>