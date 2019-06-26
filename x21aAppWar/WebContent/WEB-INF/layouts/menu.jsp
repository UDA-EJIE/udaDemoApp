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
		<a class="navbar-brand text-decoration-none" href="/x21aAppWar/">
			<i class="mdi mdi-home" aria-hidden="true"></i>
			<spring:message code="app.title" />
		</a>
		<ul class="nav navbar-nav">
			<li class="nav-item dropdown">
				<!-- <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="componentes" /><span class="caret"></span></a> -->
				<a class="nav-link dropdown-toggle" href="#"
					id="responsiveNavbarDropdown" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">
					<i class="mdi mdi-apps" aria-hidden="true"></i>
					<spring:message code="componentes" />
				</a>
				<div class="dropdown-menu"
					aria-labelledby="responsiveNavbarDropdown">
					<a class="dropdown-item" href="/x21aAppWar/patrones/feedback">
						<i class="mdi mdi-message-alert" aria-hidden="true"></i>
						<spring:message code="feedback" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/patrones/tooltip">
						<i class="mdi mdi-message-processing" aria-hidden="true"></i>
						<spring:message code="tooltip" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/patrones/message">
						<i class="mdi mdi-message-text" aria-hidden="true"></i>
						<spring:message code="message" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/patrones/dialog">
						<i class="mdi mdi-forum" aria-hidden="true"></i>
						<spring:message code="dialog" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/patrones/progressbar">
						<i class="mdi mdi-timer-sand" aria-hidden="true"></i>
						<spring:message code="progressBar" />
					</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="/x21aAppWar/patrones/contextMenu">
						<i class="mdi mdi-tooltip" aria-hidden="true"></i>
						<spring:message code="contextMenu" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/patrones/button">
						<i class="mdi mdi-cursor-pointer" aria-hidden="true"></i>
						<spring:message code="button" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/patrones/toolbar">
						<i class="mdi mdi-wrench" aria-hidden="true"></i>
						<spring:message code="toolbar" />
					</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="/x21aAppWar/patrones/accordion">
						<i class="mdi mdi-format-list-bulleted" aria-hidden="true"></i>
						<spring:message code="accordion" />
					</a>

					<div class="dropdown-submenu">
						<a class="dropdown-item dropdown-toggle" href="#">
							<i class="mdi mdi-tab" aria-hidden="true"></i>
							<spring:message code="tabs" />
						</a>
						<div class="dropdown-menu menu-right">
							<a class="dropdown-item" href="/x21aAppWar/patrones/tabsStatic">
								<spring:message code="tabsStatic" />
							</a>
							<a class="dropdown-item" href="/x21aAppWar/patrones/tabsAjax">
								<spring:message code="tabsAjax" />
							</a>
							<a class="dropdown-item" href="/x21aAppWar/patrones/tabsMixto">
								<spring:message code="tabsMixto" />
							</a>
							<!-- FIXME <a class="dropdown-item" href="/x21aAppWar/patrones/maintTab"><spring:message code="maintTab" /></a> -->
							<a class="dropdown-item"
								href="/x21aAppWar/patrones/tabsScrollable">
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
							<a class="dropdown-item" href="/x21aAppWar/patrones/wizard">
								<spring:message code="wizardA" />
							</a>
							<a class="dropdown-item"
								href="/x21aAppWar/patrones/wizard_includeFile">
								<spring:message code="wizardB" htmlEscape="true" />
							</a>
							<a class="dropdown-item"
								href="/x21aAppWar/patrones/wizard_jspInclude">
								<spring:message code="wizardC" htmlEscape="true" />
							</a>
							<a class="dropdown-item"
								href="/x21aAppWar/patrones/wizard_jstlImport">
								<spring:message code="wizardD" htmlEscape="true" />
							</a>
							<a class="dropdown-item"
								href="/x21aAppWar/patrones/wizard_dinamico">
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
							<a class="dropdown-item" href="/x21aAppWar/patrones/trees">
								<spring:message code="tree_multiple_configuraciones" />
							</a>
							<a class="dropdown-item" href="/x21aAppWar/patrones/treeDAD">
								<spring:message code="tree_multiple_d&d" />
							</a>
						</div>
					</div>

					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="/x21aAppWar/patrones/autocomplete">
						<spring:message code="autocomplete" />
					</a>

					<div class="dropdown-submenu">
						<a class="dropdown-item dropdown-toggle" href="#">
							<spring:message code="combo" />
						</a>
						<div class="dropdown-menu menu-right">
							<a class="dropdown-item" href="/x21aAppWar/patrones/comboSimple">
								<spring:message code="comboSimple" />
							</a>
							<a class="dropdown-item"
								href="/x21aAppWar/patrones/comboEnlazadoSimple">
								<spring:message code="comboEnlazadoSimple" />
							</a>
							<a class="dropdown-item"
								href="/x21aAppWar/patrones/comboEnlazadoMultiple">
								<spring:message code="comboEnlazadoMulti" />
							</a>
							<a class="dropdown-item" href=/x21aAppWar/patrones/multicombo>
								<spring:message code="multicombo" />
							</a>
							<a class="dropdown-item"
								href=/x21aAppWar/patrones/comboMantenimiento>Combo anidado
								con mantenimiento</a>
						</div>
					</div>

					<a class="dropdown-item" href="/x21aAppWar/patrones/slider">
						<i class="mdi mdi-tune" aria-hidden="true"></i>
						<spring:message code="slider" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/patrones/date">
						<i class="mdi mdi-calendar-month" aria-hidden="true"></i>
						<spring:message code="date" />
					</a>
					
					
					<div class="dropdown-submenu">
						<a class="dropdown-item dropdown-toggle" href="#">
							<i class="mdi mdi-calendar" aria-hidden="true"></i>
							<spring:message code="calendario" />
						</a>
						<div class="dropdown-menu menu-right" aria-labelledby="calendarDropdown">
							<a class="dropdown-item" href="/x21aAppWar/patrones/calendar/page">
								<spring:message code="calendario.simple" />
							</a>
							<a class="dropdown-item" href="/x21aAppWar/patrones/calendar/pageDouble">
								<spring:message code="calendario.doble" />
							</a>
						</div>
					</div>
					
					<a class="dropdown-item" href="/x21aAppWar/patrones/form">
						<i class="mdi mdi-card-text-outline" aria-hidden="true"></i>
						<spring:message code="form" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/patrones/time">
						<i class="mdi mdi-timer" aria-hidden="true"></i>
						<spring:message code="time" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/patrones/spinner">
						<i class="mdi mdi-spin mdi-loading" aria-hidden="true"></i>
						<spring:message code="spinner" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/patrones/upload">
						<i class="mdi mdi-cloud-upload" aria-hidden="true"></i>
						<spring:message code="upload" />
					</a>
					<div class="dropdown-submenu">
						<a class="dropdown-item dropdown-toggle" href="#">
							<i class="mdi mdi-check" aria-hidden="true"></i>
							<spring:message code="validate" />
						</a>
						<div class="dropdown-menu menu-right">
							<a class="dropdown-item" href="/x21aAppWar/patrones/validate">
								<spring:message code="validate.configuration" />
							</a>
							<a class="dropdown-item"
								href="/x21aAppWar/patrones/validateRules">
								<spring:message code="validate.rules" />
							</a>

						</div>
					</div>

					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="/x21aAppWar/patrones/charts">
						<i class="mdi mdi-chart-bar" aria-hidden="true"></i>
						<spring:message code="charts.charts" />
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
					<a class="dropdown-item" href="/x21aAppWar/table/configurable">
						<spring:message code="tabla.configurable" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/table/multipk">
						<spring:message code="tabla.multipk" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/table/masterDetail">
						<spring:message code="tabla.masterDetail" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/table/tableDialog">
						<spring:message code="tabla.tableDialog" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/table/dynamicColumns">
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
							<a class="dropdown-item"
								href="/x21aAppWar/tableLegacy/filtroSimple">
								<spring:message code="tablaLegacy.filtroSimple" />
							</a>
							<a class="dropdown-item"
								href="/x21aAppWar/tableLegacy/formEditAutogenerated">
								<spring:message code="tablaLegacy.edicionFormularioAuto" />
							</a>
							<a class="dropdown-item" href="/x21aAppWar/tableLegacy/formEdit">
								<spring:message code="tablaLegacy.edicionFormulario" />
							</a>
							<a class="dropdown-item"
								href="/x21aAppWar/tableLegacy/formEditMultiselection">
								<spring:message
									code="tablaLegacy.edicionFormularioMultiseleccion" />
							</a>
							<a class="dropdown-item"
								href="/x21aAppWar/tableLegacy/inlineEditExcelMode">
								<spring:message code="tablaLegacy.edicionLineaExcel" />
							</a>
							<a class="dropdown-item"
								href="/x21aAppWar/tableLegacy/inlineEdit">
								<spring:message code="tablaLegacy.edicionLinea" />
							</a>
							<a class="dropdown-item"
								href="/x21aAppWar/tableLegacy/inlineEditMultiselection">
								<spring:message code="tablaLegacy.edicionLineaMultiseleccion" />
							</a>
							<a class="dropdown-item" href="/x21aAppWar/tableLegacy/grouping">
								<spring:message code="tablaLegacy.agrupamiento" />
							</a>
							<a class="dropdown-item"
								href="/x21aAppWar/tableLegacy/masterDetail">
								<spring:message code="tablaLegacy.maestroDetalle" />
							</a>
							<a class="dropdown-item"
								href="/x21aAppWar/tableLegacy/tableLoadOnStartUp">
								<spring:message code="tablaLegacy.loadStartupFalse" />
							</a>
							<a class="dropdown-item" href="/x21aAppWar/tableLegacy/dialog">
								<spring:message code="tablaLegacy.tablaDialogo" />
							</a>
							<a class="dropdown-item"
								href="/x21aAppWar/tableLegacy/tableRadiobutton">
								<spring:message code="tablaLegacy.tablaRadioButton" />
							</a>
							<!-- FIXME <a class="dropdown-item" href="/x21aAppWar/tableLegacy/tabs"><spring:message code="tablaLegacy.tablaPestañas" /></a> -->
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
					<a class="dropdown-item"
						href="/x21aAppWar/bootstrap/stackedHorizontal">
						<spring:message code="gridResponsive.stackedHorizontal" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/bootstrap/mobileDesktop">
						<spring:message code="gridResponsive.MobileDesktop" />
					</a>
					<a class="dropdown-item"
						href="/x21aAppWar/bootstrap/mobileTabletDesktop">
						<spring:message code="gridResponsive.MobileTabletDesktop" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/bootstrap/exampleForm">
						<spring:message code="gridResponsive.ExampleForm" />
					</a>
				</div>
			</li>

			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="styleGuideDropdown"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<i class="mdi mdi-format-line-style" aria-hidden="true"></i>
					<spring:message code="stylingGuide" />
				</a>
				<div class="dropdown-menu" aria-labelledby="styleGuideDropdown">
					<a class="dropdown-item" href="/x21aAppWar/styleGuide">
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

					<a class="dropdown-item" href="/x21aAppWar/integracion/geoEuskadi">
						<spring:message code="geoEuskadi" />

					</a>


					<!-- FIXME <a class="dropdown-item" href="/x21aAppWar/integracion/nora">
                                    <spring:message code="nora" />

                                </a> -->

					<a class="dropdown-item" href="/x21aAppWar/integracion/tiny">
						<spring:message code="tiny" />
					</a>
					<a class="dropdown-item" href="/x21aAppWar/integracion/webdav">
						<spring:message code="webdav" />
					</a>

					<!-- FIXME <a class="dropdown-item" href="/x21aAppWar/integracion/pif">
                                     <spring:message code="integracion.pif" />
                                </a> -->

					<a class="dropdown-item" href="/x21aAppWar/integracion/cache/view">
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
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle rup-nav-tool-icon" href="#"
					id="x21aApp_releases" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">
					<i class="mdi mdi-settings" aria-hidden="true"></i>
				</a>
				<div class="dropdown-menu dropdown-menu-right"
					aria-labelledby="x21aApp_releases">
					<a class="dropdown-item" href="/x21aPilotoPatronesWar/">
						<spring:message code="udaLegacy" />
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
