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
		<spring:url value="/" var="urlx21" htmlEscape="true"/>
		<a class="navbar-brand" href="${urlx21}">
			<spring:message code="app.title" />
		</a>
		<ul class="nav navbar-nav">
			<li class="nav-item dropdown">
				<!-- <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="componentes" /><span class="caret"></span></a> -->
				<spring:url value="#" var="urlHashtag" htmlEscape="true"/>
				<a class="nav-link dropdown-toggle" href="${urlHashtag}"
					id="responsiveNavbarDropdown" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">
					<spring:message code="componentes" />
				</a>
				<div class="dropdown-menu"
					aria-labelledby="responsiveNavbarDropdown">
					<spring:url value="/patrones/feedback" var="urlFeedback" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlFeedback}">
						<i class="fa fa-check-square" aria-hidden="true"></i>
						<spring:message code="feedback" />
					</a>
					<spring:url value="/patrones/tooltip" var="urlTooltip" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlTooltip}">
						<i class="fa fa-comment-o" aria-hidden="true"></i>
						<spring:message code="tooltip" />
					</a>
					<spring:url value="/patrones/message" var="urlMessage" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlMessage}">
						<i class="fa fa-envelope" aria-hidden="true"></i>
						<spring:message code="message" />
					</a>
					<spring:url value="/patrones/dialog" var="urlDialog" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlDialog}">
						<i class="fa fa-window-restore" aria-hidden="true"></i>
						<spring:message code="dialog" />
					</a>
					<spring:url value="/patrones/progressbar" var="urlProgressbar" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlProgressbar}">
						<i class="fa fa-hourglass-half" aria-hidden="true"></i>
						<spring:message code="progressBar" />
					</a>
					<div class="dropdown-divider"></div>
					<spring:url value="/patrones/contextMenu" var="urlContextMenu" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlContextMenu}">
						<i class="fa fa-list-alt" aria-hidden="true"></i>
						<spring:message code="contextMenu" />
					</a>
					<spring:url value="/patrones/button" var="urlButton" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlButton}">
						<i class="fa fa-hand-pointer-o" aria-hidden="true"></i>
						<spring:message code="button" />
					</a>
					<spring:url value="/patrones/toolbar" var="urlToolbar" htmlEscape="true"/>
					<a class="dropdown-item" href="/x21aAppWar/patrones/toolbar">
						<i class="fa fa-wrench" aria-hidden="true"></i>
						<spring:message code="toolbar" />
					</a>
					<div class="dropdown-divider"></div>
					<spring:url value="/patrones/accordion" var="urlAccordion" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlAccordion}">
						<i class="fa fa-list" aria-hidden="true"></i>
						<spring:message code="accordion" />
					</a>

					<div class="dropdown-submenu">
						<a class="dropdown-item dropdown-toggle" href="${urlHashtag}">
							<spring:message code="tabs" />
						</a>
						<div class="dropdown-menu menu-right">
							<spring:url value="/patrones/tabsStatic" var="urlTabsStatic" htmlEscape="true"/>
							<a class="dropdown-item" href="${urlTabsStatic}">
								<spring:message code="tabsStatic" />
							</a>
							<spring:url value="/patrones/tabsAjax" var="urlTabsAjax" htmlEscape="true"/>
							<a class="dropdown-item" href="${urlTabsAjax}">
								<spring:message code="tabsAjax" />
							</a>
							<spring:url value="/patrones/tabsMixto" var="urlTabsMixto" htmlEscape="true"/>
							<a class="dropdown-item" href="${urlTabsMixto}">
								<spring:message code="tabsMixto" />
							</a>
							<!-- FIXME <a class="dropdown-item" href="/x21aAppWar/patrones/maintTab"><spring:message code="maintTab" /></a> -->
							<spring:url value="/patrones/tabsScrollable" var="urlTabsScrollable" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlTabsScrollable}">
								<spring:message code="tabsScrollable" />
							</a>

						</div>
					</div>

					<div class="dropdown-submenu">
						<a class="dropdown-item dropdown-toggle" href="${urlHashtag}">
							<i class="fa fa-magic" aria-hidden="true"></i>
							<spring:message code="wizard" htmlEscape="true" />
						</a>
						<div class="dropdown-menu menu-right">
							<spring:url value="/patrones/wizard" var="urlWizard" htmlEscape="true"/>
							<a class="dropdown-item" href="${urlWizard}">
								<spring:message code="wizardA" />
							</a>
							<spring:url value="/patrones/wizard_includeFile" var="urlWizardIncludeFile" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlWizardIncludeFile}">
								<spring:message code="wizardB" htmlEscape="true" />
							</a>
							<spring:url value="/patrones/wizard_jspInclude" var="urlWizardJspInclude" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlWizardJspInclude}">
								<spring:message code="wizardC" htmlEscape="true" />
							</a>
							<spring:url value="/patrones/wizard_jstlImport" var="urlWizardJstlImport" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlWizardJstlImport}">
								<spring:message code="wizardD" htmlEscape="true" />
							</a>
							<spring:url value="/patrones/wizard_dinamico" var="urlWizardDinamico" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlWizardDinamico}">
								<spring:message code="wizardE" />
							</a>
						</div>
					</div>

					<div class="dropdown-submenu">
						<a class="dropdown-item dropdown-toggle" href="${urlHashtag}">
							<i class="fa fa-sitemap" aria-hidden="true"></i>
							<spring:message code="tree" />
						</a>
						<div class="dropdown-menu menu-right">
							<spring:url value="/patrones/trees" var="urlTrees" htmlEscape="true"/>
							<a class="dropdown-item" href="${urlTrees}">
								<spring:message code="tree_multiple_configuraciones" />
							</a>
							<spring:url value="/patrones/treeDAD" var="urlTreeDAD" htmlEscape="true"/>
							<a class="dropdown-item" href="${urlTreeDAD}">
								<spring:message code="tree_multiple_d&d" />
							</a>
						</div>
					</div>

					<div class="dropdown-divider"></div>
					<spring:url value="/patrones/autocomplete" var="urlAutocomplete" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlAutocomplete}">
						<spring:message code="autocomplete" />
					</a>

					<div class="dropdown-submenu">
						<a class="dropdown-item dropdown-toggle" href="${urlHashtag}">
							<spring:message code="combo" />
						</a>
						<div class="dropdown-menu menu-right">
							<spring:url value="/patrones/comboSimple" var="urlComboSimple" htmlEscape="true"/>
							<a class="dropdown-item" href="${urlComboSimple}">
								<spring:message code="comboSimple" />
							</a>
							<spring:url value="/patrones/comboEnlazadoSimple" var="urlComboEnlazadoSimple" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlComboEnlazadoSimple}">
								<spring:message code="comboEnlazadoSimple" />
							</a>
							<spring:url value="/patrones/comboEnlazadoMultiple" var="urlComboEnlazadoMultiple" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlComboEnlazadoMultiple}">
								<spring:message code="comboEnlazadoMulti" />
							</a>
							<spring:url value="/patrones/multicombo" var="urlMulticombo" htmlEscape="true"/>
							<a class="dropdown-item" href="${urlMulticombo}">
								<spring:message code="multicombo" />
							</a>
							<spring:url value="/patrones/comboMantenimiento" var="urlComboMant" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlComboMant}">Combo anidado
								con mantenimiento</a>
						</div>
					</div>

					<spring:url value="/patrones/slider" var="urlSlider" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlSlider}">
						<i class="fa fa-sliders" aria-hidden="true"></i>
						<spring:message code="slider" />
					</a>
					<spring:url value="/patrones/date" var="urlDate" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlDate}">
						<i class="fa fa-calendar" aria-hidden="true"></i>
						<spring:message code="date" />
					</a>
					<spring:url value="/patrones/form" var="urlForm" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlForm}">
						<i class="fa fa-wpforms" aria-hidden="true"></i>
						<spring:message code="form" />
					</a>
					<spring:url value="/patrones/time" var="urlTime" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlTime}">
						<i class="fa fa-clock-o" aria-hidden="true"></i>
						<spring:message code="time" />
					</a>
					<spring:url value="/patrones/spinner" var="urlSpinner" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlSpinner}">
						<i class="fa fa-spinner" aria-hidden="true"></i>
						<spring:message code="spinner" />
					</a>
					<spring:url value="/patrones/upload" var="urlUpload" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlUpload}">
						<i class="fa fa-upload" aria-hidden="true"></i>
						<spring:message code="upload" />
					</a>
					
					<div class="dropdown-submenu">
						<a class="dropdown-item dropdown-toggle" href="${urlHashtag}">
							<i class="fa fa-check" aria-hidden="true"></i>
							<spring:message code="validate" />
						</a>
						<div class="dropdown-menu menu-right">
							<spring:url value="/patrones/validate" var="urlValidate" htmlEscape="true"/>
							<a class="dropdown-item" href="${urlValidate}">
								<spring:message code="validate.configuration" />
							</a>
							<spring:url value="/patrones/validateRules" var="urlValidateRules" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlValidateRules}">
								<spring:message code="validate.rules" />
							</a>

						</div>
					</div>

					<div class="dropdown-divider"></div>
					<spring:url value="/patrones/charts" var="urlCharts" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlCharts}">
						<i class="fa fa-bar-chart" aria-hidden="true"></i>
						<spring:message code="charts.charts" />
					</a>
				</div>
			</li>


			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="${urlHashtags}" id="calendarDropdown"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<i class="fa fa-calendar pr-2"></i>
					<spring:message code="calendario" />
				</a>
				<div class="dropdown-menu" aria-labelledby="calendarDropdown">
					<spring:url value="/patrones/calendar/page" var="urlCalendar" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlCalendar}">
						<spring:message code="calendario.simple" />
					</a>
					<spring:url value="/patrones/calendar/pageDouble" var="urlCalendarDouble" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlCalendarDouble}">
						<spring:message code="calendario.doble" />
					</a>
				</div>
			</li>


			<li class="nav-item dropdown">
				<spring:url value="" var="" htmlEscape="true"/>
				<a class="nav-link dropdown-toggle" href="${urlHashtag}" id="datatableDropdown"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					</i>
					<spring:message code="tabla" />
				</a>
				<div class="dropdown-menu" aria-labelledby="datatableDropdown">
					<spring:url value="/table/configurable" var="urlDatatableConfigurable" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlDatatableConfigurable}">
						<spring:message code="tabla.configurable" />
					</a>
					<spring:url value="/table/multipk" var="urlDatatableMultiPK" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlDatatableMultiPK}">
						<spring:message code="tabla.multipk" />
					</a>
					<spring:url value="/table/masterDetail" var="urlDatatableMasterDetail" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlDatatableMasterDetail}">
						<spring:message code="tabla.masterDetail" />
					</a>
					<spring:url value="/table/tableDialog" var="urlDatatableDialog" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlDatatableDialog}">
						<spring:message code="tabla.tableDialog" />
					</a>
					<div class="dropdown-divider"></div>
					<div class="dropdown-submenu">
						<spring:url value="" var="" htmlEscape="true"/>
						<a class="dropdown-item dropdown-toggle" href="${urlHashtag}">
							<spring:message code="tablaLegacy" />
						</a>
						<div class="dropdown-menu menu-right">
							<spring:url value="/tableLegacy/filtroSimple" var="urlLegacyFiltroSimple" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlLegacyFiltroSimple}">
								<spring:message code="tablaLegacy.filtroSimple" />
							</a>
							<spring:url value="/tableLegacy/formEditAutogenerated" var="urlLegacyFormEditAutogen" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlLegacyFormEditAutogen}">
								<spring:message code="tablaLegacy.edicionFormularioAuto" />
							</a>
							<spring:url value="/tableLegacy/formEdit" var="urlLegacyFormEdit" htmlEscape="true"/>
							<a class="dropdown-item" href="/x21aAppWar/tableLegacy/formEdit">
								<spring:message code="tablaLegacy.edicionFormulario" />
							</a>
							<spring:url value="/tableLegacy/formEditMultiselection" var="urlLegacyFormEditMultiselection" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlLegacyFormEditMultiselection}">
								<spring:message
									code="tablaLegacy.edicionFormularioMultiseleccion" />
							</a>
							<spring:url value="/tableLegacy/inlineEditExcelMode" var="urlLegacyInlineExcelMode" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlLegacyInlineExcelMode}">
								<spring:message code="tablaLegacy.edicionLineaExcel" />
							</a>
							<spring:url value="/tableLegacy/inlineEdit" var="urlLegacyInline" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlLegacyInline}">
								<spring:message code="tablaLegacy.edicionLinea" />
							</a>
							<spring:url value="/tableLegacy/inlineEditMultiselection" var="urlLegacyInlineEditMultiselection" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlLegacyInlineEditMultiselection}">
								<spring:message code="tablaLegacy.edicionLineaMultiseleccion" />
							</a>
							<spring:url value="/tableLegacy/grouping" var="urlLegacyGrouping" htmlEscape="true"/>
							<a class="dropdown-item" href="${urlLegacyGrouping}">
								<spring:message code="tablaLegacy.agrupamiento" />
							</a>
							<spring:url value="/tableLegacy/masterDetail" var="urlLegacyMasterDetail" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlLegacyMasterDetail}">
								<spring:message code="tablaLegacy.maestroDetalle" />
							</a>
							<spring:url value="/tableLegacy/tableLoadOnStartUp" var="urlLegacyLoadOnStartup" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlLegacyLoadOnStartup}">
								<spring:message code="tablaLegacy.loadStartupFalse" />
							</a>
							<spring:url value="/tableLegacy/dialog" var="urlLegacyDialog" htmlEscape="true"/>
							<a class="dropdown-item" href="${urlLegacyDialog}">
								<spring:message code="tablaLegacy.tablaDialogo" />
							</a>
							<spring:url value="/tableLegacy/tableRadiobutton" var="urlLegacyRadiobutton" htmlEscape="true"/>
							<a class="dropdown-item"
								href="${urlLegacyRadiobutton}">
								<spring:message code="tablaLegacy.tablaRadioButton" />
							</a>
							<!-- FIXME <a class="dropdown-item" href="/x21aAppWar/tableLegacy/tabs"><spring:message code="tablaLegacy.tablaPestañas" /></a> -->
						</div>
					</div>
				</div>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="${urlHashtag}"
					id="gridResponsiveDropdown" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">
					<spring:message code="gridResponsive" />
				</a>
				<div class="dropdown-menu" aria-labelledby="gridResponsiveDropdown">
					<spring:url value="/bootstrap/stackedHorizontal" var="urlBsStackHorizontal" htmlEscape="true"/>
					<a class="dropdown-item"
						href="${urlBsStackHorizontal}">
						<spring:message code="gridResponsive.stackedHorizontal" />
					</a>
					<spring:url value="/bootstrap/mobileDesktop" var="urlBsMobileDesktop" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlBsMobileDesktop}">
						<spring:message code="gridResponsive.MobileDesktop" />
					</a>
					<spring:url value="/bootstrap/mobileTabletDesktop" var="urlBsMobileTabletDesktop" htmlEscape="true"/>
					<a class="dropdown-item"
						href="${urlBsMobileTabletDesktop}">
						<spring:message code="gridResponsive.MobileTabletDesktop" />
					</a>
					<spring:url value="/bootstrap/exampleForm" var="urlBsExampleForm" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlBsExampleForm}">
						<spring:message code="gridResponsive.ExampleForm" />
					</a>
				</div>
			</li>

			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="${urlHashtag}" id="styleGuideDropdown"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<spring:message code="stylingGuide" />
				</a>
				<div class="dropdown-menu" aria-labelledby="styleGuideDropdown">
					<spring:url value="/styleGuide" var="urlStyleGuide" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlStyleGuide}">
						<spring:message code="stylingGuide" />
					</a>
				</div>
			</li>


			<!-- Integración -->
			<li class="nav-item dropdown">
				<spring:url value="" var="" htmlEscape="true"/>
				<a class="nav-link dropdown-toggle" href="${urlHashtag}"
					id="integrationDropdown" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">
					<spring:message code="integracion" />
				</a>
				<div class="dropdown-menu" aria-labelledby="integrationDropdown">
					<spring:url value="/integracion/geoEuskadi" var="urlIntGeoEus" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlIntGeoEus}">
						<spring:message code="geoEuskadi" />

					</a>


					<!-- FIXME <a class="dropdown-item" href="/x21aAppWar/integracion/nora">
                                    <spring:message code="nora" />

                                </a> -->
					<spring:url value="/integracion/tiny" var="urlIntTiny" htmlEscape="true"/>
					<a class="dropdown-item" href="/x21aAppWar/integracion/tiny">
						<spring:message code="tiny" />
					</a>
					<spring:url value="/integracion/webdav" var="urlIntWebDav" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlIntWebDav}">
						<spring:message code="webdav" />
					</a>

					<!-- FIXME <a class="dropdown-item" href="/x21aAppWar/integracion/pif">
                                     <spring:message code="integracion.pif" />
                                </a> -->
                    <spring:url value="/integracion/cache/view" var="urlIntCacheView" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlIntCacheView}">
						<spring:message code="integracion.cache" />
					</a>

				</div>
			</li>


		</ul>
		<ul class="nav navbar-nav float-md-right rup-nav-tools">
			<li class="nav-item">
				<a class="nav-link rup-nav-tool-icon" href="${urlHashtag}" id="x21aApp_language"
					data-toggle="dropdown">
					<i class="fa fa-globe" aria-hidden="true"></i><span
						data-rup-lang-current=""></span>
				</a>
				<div class="dropdown-menu" aria-labelledby="x21aApp_language">
				</div>
			</li>
			<li class="nav-item">
				<spring:url value="http://uda-ejie.github.io/" var="urlUdaEjie" htmlEscape="true"/>
				<a class="nav-link rup-nav-tool-icon"
					href="${urlUdaEjie}" id="uda_github">
					<i class="fa fa-github" aria-hidden="true"></i>
				</a>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle rup-nav-tool-icon" href="${urlHashtag}"
					id="x21aApp_releases" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">
					<i class="fa fa-cog " aria-hidden="true"></i>
				</a>
				<div class="dropdown-menu dropdown-menu-right"
					aria-labelledby="x21aApp_releases">
					<spring:url value="/" context="x21aPilotoPatronesWar" var="urlPilotoPatronesWar" htmlEscape="true"/>
					<a class="dropdown-item" href="${urlPilotoPatronesWar}">
						<spring:message code="udaLegacy" />
					</a>
				</div>
			</li>
			<li class="nav-item">
				<spring:url value="" var="" htmlEscape="true"/>
				<a class="nav-link rup-nav-user rup-nav-tool-icon" href="${urlHashtag}">
					<i class="fa fa-user-circle-o " aria-hidden="true"></i>
				</a>
			</li>
			<li class="nav-item swingTop">
				<spring:url value="javascript:void(0)" var="urlToTop" htmlEscape="true"/>
				<a class="nav-link rup-nav-user rup-nav-tool-icon"
					href="${urlToTop}">
					<i class="fa fa-arrow-circle-up " aria-hidden="true"></i>
				</a>
			</li>
		</ul>
	</div>
	<!--/.navbar-collapse -->

</nav>

<div id="overlay"></div>
