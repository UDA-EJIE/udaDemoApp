<div class="row">
    <div class="col-md-6">
        <h2>Componente rup_list (left)</h2>
        
        <jsp:include page="includes/listFilterFormDobleLeft.jsp"></jsp:include>
        
        <div id="rup-list-left-feedback"></div>
        <div id="rup-list-left-content">
            <div id="rup-list-left-header" class="row">
                <div id="rup-list-left-header-selectables" class="col-md-3">
                    Opciones de selección:
                </div>
                <div class="col-md-2">
                    <label for="rup-list-left-header-rowNum">Elementos por página:</label>
                    <select id="rup-list-left-header-rowNum"></select>
                </div>
                <!-- Ordenar por -->
                <div class="col-md-3">
                    <div class="row">
                        <div class="col-md-7">
                            <label for="rup-list-left-header-sidx">Ordenar por:</label>
                            <select id="rup-list-left-header-sidx"></select>
                        </div>
                        <div class="col-md-2">
                            <button id="rup-list-left-header-sord">
                                <em class="mdi mdi-sort"></em>
                            </button>
                        </div>
                    </div>
                </div>
                <!-- Navegación -->
                <div class="col-md-4">
                    <nav id="rup-list-left-header-nav">
                        <ul class="pagination">
                            <li id="rup-list-left-header-page-prev" class="page-item disabled">
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
                            <li id="rup-list-left-header-page-next" class="page-item disabled">
                                <a href="javascript:void(0)" class="page-link d-none d-lg-flex">Siguiente</a>
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
    </div>
    <div class="col-md-6">
    <h2>Componente rup_list (right)</h2>
        
        <jsp:include page="includes/listFilterFormDobleRight.jsp"></jsp:include>
        
        <div id="rup-list-right-feedback"></div>
        <div id="rup-list-right-content">
            <div id="rup-list-right-header" class="row">
                <div id="rup-list-right-header-selectables" class="col-md-3">
                    Opciones de selección:
                </div>
                <div class="col-md-2">
                    <label for="rup-list-right-header-rowNum">Elementos por página:</label>
                    <select id="rup-list-right-header-rowNum"></select>
                </div>
                <!-- Ordenar por -->
                <div class="col-md-3">
                    <div class="row">
                        <div class="col-md-7">
                            <label for="rup-list-right-header-sidx">Ordenar por:</label>
                            <select id="rup-list-right-header-sidx"></select>
                        </div>
                        <div class="col-md-2">
                            <button id="rup-list-right-header-sord">
                                <em class="mdi mdi-sort"></em>
                            </button>
                        </div>
                    </div>
                </div>
                <!-- Navegación -->
                <div class="col-md-4">
                    <nav id="rup-list-right-header-nav">
                        <ul class="pagination">
                            <li id="rup-list-right-header-page-prev" class="page-item disabled">
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
                            <li id="rup-list-right-header-page-next" class="page-item disabled">
                                <a href="javascript:void(0)" class="page-link d-none d-lg-flex">Siguiente</a>
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
    </div>
</div>