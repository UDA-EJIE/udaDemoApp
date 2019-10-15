<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 -- 
 -- http://ec.europa.eu/idabc/eupl.html
 -- 
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia. 
 --%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<nav class="rup-navbar navbar">
	<button type="button" class="navbar-toggler d-lg-none" type="button"
		data-toggle="rup-collapse" data-target="#navbarResponsive"
		aria-controls="navbarResponsive" aria-expanded="false"
		aria-label="Toggle navigation"></button>
	<div id="navbarResponsive"
		class="collapse navbar-toggleable-md col-md-12 no-gutter">
		<spring:url value="/" var="welcome" htmlEscape="true"/>
		<a class="navbar-brand text-decoration-none" href="${welcome}">
			<i class="mdi mdi-home" aria-hidden="true"></i>
			<spring:message code="app.title" />
		</a>
		<ul class="nav navbar-nav">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#"
					id="responsiveNavbarDropdown" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">
					<i class="mdi mdi-apps" aria-hidden="true"></i>
					<spring:message code="componentes" />
				</a>
				<div class="dropdown-menu"
					aria-labelledby="responsiveNavbarDropdown">
					<spring:url value="/patrones/feedback" var="feedback" htmlEscape="true"/>
					<a class="dropdown-item" href="${feedback}">
						<i class="mdi mdi-message-alert" aria-hidden="true"></i>
						<spring:message code="feedback" />
					</a>
					<spring:url value="/patrones/tooltip" var="tooltip" htmlEscape="true"/>
					<a class="dropdown-item" href="${tooltip}">
						<i class="mdi mdi-message-processing" aria-hidden="true"></i>
						<spring:message code="tooltip" />
					</a>
					<spring:url value="/patrones/message" var="message" htmlEscape="true"/>
					<a class="dropdown-item" href="${message}">
						<i class="mdi mdi-message-text" aria-hidden="true"></i>
						<spring:message code="message" />
					</a>
					<spring:url value="/patrones/dialog" var="dialog" htmlEscape="true"/>
					<a class="dropdown-item" href="${dialog}">
						<i class="mdi mdi-forum" aria-hidden="true"></i>
						<spring:message code="dialog" />
					</a>
					<spring:url value="/patrones/progressbar" var="progressbar" htmlEscape="true"/>
					<a class="dropdown-item" href="${progressbar}">
						<i class="mdi mdi-timer-sand" aria-hidden="true"></i>
						<spring:message code="progressBar" />
					</a>
					<div class="dropdown-divider"></div>
					<spring:url value="/patrones/contextMenu" var="contextMenu" htmlEscape="true"/>
					<a class="dropdown-item" href="${contextMenu}">
						<i class="mdi mdi-tooltip" aria-hidden="true"></i>
						<spring:message code="contextMenu" />
					</a>
					<spring:url value="/patrones/button" var="button" htmlEscape="true"/>
					<a class="dropdown-item" href="${button}">
						<i class="mdi mdi-cursor-pointer" aria-hidden="true"></i>
						<spring:message code="button" />
					</a>
					<spring:url value="/patrones/toolbar" var="toolbar" htmlEscape="true"/>
					<a class="dropdown-item" href="${toolbar}">
						<i class="mdi mdi-wrench" aria-hidden="true"></i>
						<spring:message code="toolbar" />
					</a>
					<div class="dropdown-divider"></div>
					<spring:url value="/patrones/accordion" var="accordion" htmlEscape="true"/>
					<a class="dropdown-item" href="${accordion}">
						<i class="mdi mdi-format-list-bulleted" aria-hidden="true"></i>
						<spring:message code="accordion" />
					</a>

					<div class="dropdown-submenu">
						<a class="dropdown-item dropdown-toggle" href="#">
							<i class="mdi mdi-tab" aria-hidden="true"></i>
							<spring:message code="tabs" />
						</a>
						<div class="dropdown-menu menu-right">
						    <spring:url value="/patrones/tabsStatic" var="tabsStatic" htmlEscape="true"/>
							<a class="dropdown-item" href="${tabsStatic}">
								<spring:message code="tabsStatic" />
							</a>
							<spring:url value="/patrones/tabsAjax" var="tabsAjax" htmlEscape="true"/>
							<a class="dropdown-item" href="${tabsAjax}">
								<spring:message code="tabsAjax" />
							</a>
							<spring:url value="/patrones/tabsMixto" var="tabsMixto" htmlEscape="true"/>
							<a class="dropdown-item" href="${tabsMixto}">
								<spring:message code="tabsMixto" />
							</a>
							<spring:url value="/patrones/tabsScrollable" var="tabsScrollable" htmlEscape="true"/>
							<a class="dropdown-item" href="${tabsScrollable}">
								<spring:message code="tabsScrollable" />
							</a>

						</div>
					</div>

					<div class="dropdown-submenu">
						<a class="dropdown-item dropdown-toggle" href="#">
							<i class="mdi mdi-teach" aria-hidden="true"></i>
							<spring:message code="wizard" htmlEscape="true" />
						</a>
						<div class="dropdown-menu menu-right">
						    <spring:url value="/patrones/wizard" var="wizard" htmlEscape="true"/>
							<a class="dropdown-item" href="${wizard}">
								<spring:message code="wizardA" />
							</a>
							<spring:url value="/patrones/wizard_includeFile" var="wizard_includeFile" htmlEscape="true"/>
							<a class="dropdown-item" href="${wizard_includeFile}">
								<spring:message code="wizardB" htmlEscape="true" />
							</a>
							<spring:url value="/patrones/wizard_jspInclude" var="wizard_jspInclude" htmlEscape="true"/>
							<a class="dropdown-item" href="${wizard_jspInclude}">
								<spring:message code="wizardC" htmlEscape="true" />
							</a>
							<spring:url value="/patrones/wizard_jstlImport" var="wizard_jstlImport" htmlEscape="true"/>
							<a class="dropdown-item" href="${wizard_jstlImport}">
								<spring:message code="wizardD" htmlEscape="true" />
							</a>
							<spring:url value="/patrones/wizard_dinamico" var="wizard_dinamico" htmlEscape="true"/>
							<a class="dropdown-item" href="${wizard_dinamico}">
								<spring:message code="wizardE" />
							</a>
						</div>
					</div>

					<div class="dropdown-submenu">
						<a class="dropdown-item dropdown-toggle" href="#">
							<i class="mdi mdi-file-tree" aria-hidden="true"></i>
							<spring:message code="tree" />
						</a>
						<div class="dropdown-menu menu-right">
						    <spring:url value="/patrones/trees" var="trees" htmlEscape="true"/>
							<a class="dropdown-item" href="${trees}">
								<spring:message code="tree_multiple_configuraciones" />
							</a>
							<spring:url value="/patrones/treeDAD" var="treeDAD" htmlEscape="true"/>
							<a class="dropdown-item" href="${treeDAD}">
								<spring:message code="tree_multiple_d&d" />
							</a>
						</div>
					</div>

					<div class="dropdown-divider"></div>
					<spring:url value="/patrones/autocomplete" var="autocomplete" htmlEscape="true"/>
					<a class="dropdown-item" href="${autocomplete}">
						<spring:message code="autocomplete" />
					</a>

					<div class="dropdown-submenu">
						<a class="dropdown-item dropdown-toggle" href="#">
							<spring:message code="combo" />
						</a>
						<div class="dropdown-menu menu-right">
						<spring:url value="/patrones/comboSimple" var="comboSimple" htmlEscape="true"/>
							<a class="dropdown-item" href="${comboSimple}">
								<spring:message code="comboSimple" />
							</a>
							<spring:url value="/patrones/comboEnlazadoSimple" var="comboEnlazadoSimple" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${comboEnlazadoSimple}">
								<spring:message code="comboEnlazadoSimple" />
							</a>
							<spring:url value="/patrones/comboEnlazadoMultiple" var="comboEnlazadoMultiple" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${comboEnlazadoMultiple}">
								<spring:message code="comboEnlazadoMulti" />
							</a>
							<spring:url value="/patrones/multicombo" var="multicombo" htmlEscape="true"/>
							<a class="dropdown-item" href="${multicombo}">
								<spring:message code="multicombo" />
							</a>
							<spring:url value="/patrones/comboMantenimiento" var="comboMantenimiento" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${comboMantenimiento}">Combo anidado
								con mantenimiento</a>
						</div>
					</div>

                    <spring:url value="/patrones/slider" var="slider" htmlEscape="true"/>
					<a class="dropdown-item" href="${slider}">
						<i class="mdi mdi-tune" aria-hidden="true"></i>
						<spring:message code="slider" />
					</a>
					<spring:url value="/patrones/date" var="" htmlEscape="true"/>
					<a class="dropdown-item" href="${date}">
						<i class="mdi mdi-calendar-month" aria-hidden="true"></i>
						<spring:message code="date" />
					</a>


					<div class="dropdown-submenu">
					    <spring:url value="/patrones/" var="" htmlEscape="true"/>
						<a class="dropdown-item dropdown-toggle" href="#">
							<i class="mdi mdi-calendar" aria-hidden="true"></i>
							<spring:message code="calendario" />
						</a>
						<div class="dropdown-menu menu-right" aria-labelledby="calendarDropdown">
						    <spring:url value="/patrones/calendar/page" var="calendar" htmlEscape="true"/>
							<a class="dropdown-item" href="${calendar}">
								<spring:message code="calendario.simple" />
							</a>
							<spring:url value="/patrones/calendar/pageDouble" var="calendarDouble" htmlEscape="true"/>
							<a class="dropdown-item" href="${calendarDouble}">
								<spring:message code="calendario.doble" />
							</a>
						</div>
					</div>

                    <spring:url value="/patrones/form" var="form" htmlEscape="true"/>
					<a class="dropdown-item" href="${form}">
						<i class="mdi mdi-card-text-outline" aria-hidden="true"></i>
						<spring:message code="form" />
					</a>
					<spring:url value="/patrones/time" var="time" htmlEscape="true"/>
					<a class="dropdown-item" href="${time}">
						<i class="mdi mdi-timer" aria-hidden="true"></i>
						<spring:message code="time" />
					</a>
					<spring:url value="/patrones/spinner" var="spinner" htmlEscape="true"/>
					<a class="dropdown-item" href="${spinner}">
						<i class="mdi mdi-spin mdi-loading" aria-hidden="true"></i>
						<spring:message code="spinner" />
					</a>
					<spring:url value="/patrones/upload" var="upload" htmlEscape="true"/>
					<a class="dropdown-item" href="${upload}">
						<i class="mdi mdi-cloud-upload" aria-hidden="true"></i>
						<spring:message code="upload" />
					</a>
					<div class="dropdown-submenu">
						<a class="dropdown-item dropdown-toggle" href="#">
							<i class="mdi mdi-check" aria-hidden="true"></i>
							<spring:message code="validate" />
						</a>
						<div class="dropdown-menu menu-right">
						    <spring:url value="/patrones/validate" var="validate" htmlEscape="true"/>
							<a class="dropdown-item" href="${validate}">
								<spring:message code="validate.configuration" />
							</a>
							<spring:url value="/patrones/validateRules" var="validateRules" htmlEscape="true"/>
							<a class="dropdown-item" href="${validateRules}">
								<spring:message code="validate.rules" />
							</a>

						</div>
					</div>

					<div class="dropdown-divider"></div>
					<spring:url value="/patrones/charts" var="charts" htmlEscape="true"/>
					<a class="dropdown-item" href="${charts}">
						<i class="mdi mdi-chart-bar" aria-hidden="true"></i>
						<spring:message code="charts.charts" />
					</a>
				</div>
			</li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="listDropdown" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                    <i class="mdi mdi-view-sequential" aria-hidden="true"></i>
                    <spring:message code="lista" />
                </a>
                <div class="dropdown-menu" aria-labelledby="listaDropdown">
                    <spring:url value="/lista/simple" var="listaSimple" htmlEscape="true"/>
                	<a class="dropdown-item" href="${listaSimple}">
                		<spring:message code="lista.simple" />
                	</a>
                	<spring:url value="/lista/multiOrd" var="listaMultiOrd" htmlEscape="true"/>
                	<a class="dropdown-item" href="${listaMultiOrd}">
                        <spring:message code="lista.multiOrd" />
                    </a>
                </div>
            </li>

			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="tableDropdown"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<i class="mdi mdi-table" aria-hidden="true"></i>
					<spring:message code="tabla" />
				</a>
				<div class="dropdown-menu" aria-labelledby="tableDropdown">
				    <spring:url value="/table/configurable" var="tableConfigurable" htmlEscape="true"/>
					<a class="dropdown-item" href="${tableConfigurable}">
						<spring:message code="tabla.configurable" />
					</a>
					<spring:url value="/table/double" var="tableDouble" htmlEscape="true"/>
					<a class="dropdown-item" href="${tableDouble}">
						<spring:message code="tabla.double" />
					</a>
					<spring:url value="/table/multipk" var="tableMultipk" htmlEscape="true"/>
					<a class="dropdown-item" href="${tableMultipk}">
						<spring:message code="tabla.multipk" />
					</a>
					<spring:url value="/table/masterDetail" var="tableMasterDetail" htmlEscape="true"/>
					<a class="dropdown-item" href="${tableMasterDetail}">
						<spring:message code="tabla.masterDetail" />
					</a>
					<spring:url value="/table/tableDialog" var="tableDialog" htmlEscape="true"/>
					<a class="dropdown-item" href="${tableDialog}">
						<spring:message code="tabla.tableDialog" />
					</a>
					<spring:url value="/table/dynamicColumns" var="tableDynamicColumns" htmlEscape="true"/>
					<a class="dropdown-item" href="${tableDynamicColumns}">
						<spring:message code="tabla.dynamicColumns" />
					</a>
					<a class="dropdown-item" href="${staticsUrl}/rup/html/specRunner.html" target="_blank">
						<spring:message code="Test para la tabla" />
					</a>
					<div class="dropdown-divider"></div>
					<div class="dropdown-submenu">
						<a class="dropdown-item dropdown-toggle" href="#">
							<spring:message code="tablaLegacy" />
						</a>
						<div class="dropdown-menu menu-right">
						    <spring:url value="/tableLegacy/filtroSimple" var="tableLegacyFiltroSimple" htmlEscape="true"/>
							<a class="dropdown-item" href="${tableLegacyFiltroSimple}">
								<spring:message code="tablaLegacy.filtroSimple" />
							</a>
							<spring:url value="/tableLegacy/formEditAutogenerated" var="tableLegacyFormEditAutogenerated" htmlEscape="true"/>
							<a class="dropdown-item" href="${tableLegacyFormEditAutogenerated}">
								<spring:message code="tablaLegacy.edicionFormularioAuto" />
							</a>
							<spring:url value="/tableLegacy/formEdit" var="tableLegacyFormEdit" htmlEscape="true"/>
							<a class="dropdown-item" href="${tableLegacyFormEdit}">
								<spring:message code="tablaLegacy.edicionFormulario" />
							</a>
							<spring:url value="/tableLegacy/formEditMultiselection" var="tableLegacyFormEditMultiselection" htmlEscape="true"/>
							<a class="dropdown-item" href="${tableLegacyFormEditMultiselection}">
								<spring:message
									code="tablaLegacy.edicionFormularioMultiseleccion" />
							</a>
							<spring:url value="/tableLegacy/inlineEditExcelMode" var="tableLegacyInlineEditExcelMode" htmlEscape="true"/>
							<a class="dropdown-item" href="${tableLegacyInlineEditExcelMode}">
								<spring:message code="tablaLegacy.edicionLineaExcel" />
							</a>
							<spring:url value="/tableLegacy/inlineEdit" var="tableLegacyInlineEdit" htmlEscape="true"/>
							<a class="dropdown-item" href="${tableLegacyInlineEdit}">
								<spring:message code="tablaLegacy.edicionLinea" />
							</a>
							<spring:url value="/tableLegacy/inlineEditDynamicColumns" var="tableLegacyInlineEditDynamicColumns" htmlEscape="true"/>
							<a class="dropdown-item" href="${tableLegacyInlineEditDynamicColumns}">
								<spring:message code="tablaLegacy.edicionLineaColumnasDinamicas" />
							</a>
							<spring:url value="/tableLegacy/inlineEditMultiselection" var="tableLegacyInlineEditMultiselection" htmlEscape="true"/>
							<a class="dropdown-item" href="${tableLegacyInlineEditMultiselection}">
								<spring:message code="tablaLegacy.edicionLineaMultiseleccion" />
							</a>
							<spring:url value="/tableLegacy/grouping" var="tableLegacyGrouping" htmlEscape="true"/>
							<a class="dropdown-item" href="${tableLegacyGrouping}">
								<spring:message code="tablaLegacy.agrupamiento" />
							</a>
							<spring:url value="/tableLegacy/masterDetail" var="tableLegacyMasterDetail" htmlEscape="true"/>
							<a class="dropdown-item" href="${tableLegacyMasterDetail}">
								<spring:message code="tablaLegacy.maestroDetalle" />
							</a>
							<spring:url value="/tableLegacy/tableLoadOnStartUp" var="tableLegacyTableLoadOnStartUp" htmlEscape="true"/>
							<a class="dropdown-item" href="${tableLegacyTableLoadOnStartUp}">
								<spring:message code="tablaLegacy.loadStartupFalse" />
							</a>
							<spring:url value="/tableLegacy/dialog" var="tableLegacyDialog" htmlEscape="true"/>
							<a class="dropdown-item" href="${tableLegacyDialog}">
								<spring:message code="tablaLegacy.tablaDialogo" />
							</a>
							<spring:url value="/tableLegacy/tableRadiobutton" var="tableLegacyTableRadiobutton" htmlEscape="true"/>
							<a class="dropdown-item" href="${tableLegacyTableRadiobutton}">
								<spring:message code="tablaLegacy.tablaRadioButton" />
							</a>
						</div>
					</div>
				</div>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#"
					id="gridResponsiveDropdown" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">
					<i class="mdi mdi-view-grid" aria-hidden="true"></i>
					<spring:message code="gridResponsive" />
				</a>
				<div class="dropdown-menu" aria-labelledby="gridResponsiveDropdown">
				    <spring:url value="/bootstrap/stackedHorizontal" var="stackedHorizontal" htmlEscape="true"/>
					<a class="dropdown-item"
						href="${stackedHorizontal}">
						<spring:message code="gridResponsive.stackedHorizontal" />
					</a>
					<spring:url value="/bootstrap/mobileDesktop" var="mobileDesktop" htmlEscape="true"/>
					<a class="dropdown-item" href="${mobileDesktop}">
						<spring:message code="gridResponsive.MobileDesktop" />
					</a>
					<spring:url value="/bootstrap/mobileTabletDesktop" var="mobileTabletDesktop" htmlEscape="true"/>
					<a class="dropdown-item" href="${mobileTabletDesktop}">
						<spring:message code="gridResponsive.MobileTabletDesktop" />
					</a>
					<spring:url value="/bootstrap/exampleForm" var="exampleForm" htmlEscape="true"/>
					<a class="dropdown-item" href="${exampleForm}">
						<spring:message code="gridResponsive.ExampleForm" />
					</a>
				</div>
			</li>

			<li class="nav-item dropdown">
			    <spring:url value="/patrones/" var="" htmlEscape="true"/>
				<a class="nav-link dropdown-toggle" href="#" id="styleGuideDropdown"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<i class="mdi mdi-format-line-style" aria-hidden="true"></i>
					<spring:message code="stylingGuide" />
				</a>
				<div class="dropdown-menu" aria-labelledby="styleGuideDropdown">
				    <spring:url value="/styleGuide" var="styleGuide" htmlEscape="true"/>
					<a class="dropdown-item" href="${styleGuide}">
						<spring:message code="stylingGuide" />
					</a>
				</div>
			</li>


			<!-- Integración -->
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#"
					id="integrationDropdown" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">
					<spring:message code="integracion" />
				</a>
				<div class="dropdown-menu" aria-labelledby="integrationDropdown">
                    <spring:url value="/integracion/geoEuskadi" var="geoEuskadi" htmlEscape="true"/>
					<a class="dropdown-item" href="${geoEuskadi}">
						<spring:message code="geoEuskadi" />

					</a>
                    <spring:url value="/integracion/tiny" var="tiny" htmlEscape="true"/>
					<a class="dropdown-item" href="${tiny}">
						<spring:message code="tiny" />
					</a>
					<spring:url value="/integracion/webdav" var="webdav" htmlEscape="true"/>
					<a class="dropdown-item" href="${webdav}">
						<spring:message code="webdav" />
					</a>
                    <spring:url value="/integracion/cache/view" var="cacheView" htmlEscape="true"/>
					<a class="dropdown-item" href="${cacheView}">
						<spring:message code="integracion.cache" />
					</a>
				</div>
			</li>


		</ul>
		<ul class="nav navbar-nav float-md-right rup-nav-tools">
			<li class="nav-item">
				<a class="nav-link rup-nav-tool-icon" href="#" id="x21aApp_language"
					data-toggle="dropdown">
					<i class="mdi mdi-earth" aria-hidden="true"></i>
					<span data-rup-lang-current=""></span>
				</a>
				<div class="dropdown-menu" aria-labelledby="x21aApp_language">
				</div>
			</li>
			<li class="nav-item">
				<a class="nav-link rup-nav-tool-icon"
					href="http://uda-ejie.github.io/" id="uda_github">
					<i class="mdi mdi-github-circle" aria-hidden="true"></i>
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link rup-nav-tool-icon" href="#" id="x21aApp_releases"
					data-toggle="dropdown">
					<i class="mdi mdi-settings" aria-hidden="true"></i>
				</a>
				<div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow-center">
					<a class="dropdown-item" href="/x21aPilotoPatronesWar/" aria-labelledby="x21aApp_uda_2">
						<spring:message code="udaLegacy2x" />
					</a>
					<a class="dropdown-item" href="/x21aApp3xWar/" aria-labelledby="x21aApp_uda_3">
						<spring:message code="udaLegacy3x" />
					</a>
				</div>
			</li>
			<li class="nav-item">
				<a class="nav-link rup-nav-user rup-nav-tool-icon" href="#">
					<i class="mdi mdi-account-circle" aria-hidden="true"></i>
				</a>
			</li>
			<li class="nav-item swingTop">
				<a class="nav-link rup-nav-user rup-nav-tool-icon"
					href="javascript:void(0)">
					<i class="mdi mdi-arrow-up" aria-hidden="true"></i>
				</a>
			</li>
		</ul>
	</div>
	<!--/.navbar-collapse -->

</nav>

<div id="overlay"></div>
