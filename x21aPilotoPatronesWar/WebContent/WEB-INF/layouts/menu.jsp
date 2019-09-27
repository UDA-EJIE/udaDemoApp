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
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia. 
 --%>
 <%@include file="/WEB-INF/includeTemplate.inc"%>
 
	<ul id="x21aPilotoPatronesWar_menu"	class="rup_invisible_menu" style="display:flex;">
		<li>
			<spring:url value="/" context="x21aMantenimientosWar"var="urlMantenimientosWar" htmlEscape="true"/>
			<a href="${urlMantenimientosWar}">
				<spring:message code="mantenimientos" />
			</a>
		</li>
		<li>
			<spring:url value="/" var="urlPilotosWar" htmlEscape="true"/>
			<a href="${urlPilotosWar}" >
				<spring:message code="inicio" />
				<span class="ui-icon home_icon"></span>
			</a>
		</li>
		<li>
			<a>
				<spring:message code="patrones" />
				<span class="ui-icon patrones_icon"></span>
			</a>
			<ul>
				<li>
					<spring:url value="/patrones/all" var="urlAll" htmlEscape="true"/>
					<a href="${urlAll}">
						<spring:message code="all" />
					</a>
				</li>
				<li class="ui-widget-content ui-menu-divider"></li>
				<li><strong><spring:message code="titulo-notifi" /></strong></li>
				<li>
					<spring:url value="/patrones/feedback" var="urlFeedback" htmlEscape="true"/>
					<a href="${urlFeedback}">
						<spring:message code="feedback" />
						<span class="ui-icon feedback_icon"></span>
					</a>
				</li>
				<li>
					<spring:url value="/patrones/tooltip" var="urlTooltip" htmlEscape="true"/>
					<a href="${urlTooltip}">
						<spring:message code="tooltip" />
						<span class="ui-icon tooltip_icon"></span>
					</a>
				</li>
				<li>
					<spring:url value="/patrones/message" var="urlMessage" htmlEscape="true"/>
					<a href="${urlMessage}">
						<spring:message code="message" />
						<span class="ui-icon message_icon"></span>
					</a>
				</li>
				<li>
					<spring:url value="/patrones/dialog" var="urlDialog" htmlEscape="true"/>
					<a href="${urlDialog}">
						<spring:message code="dialog" />
						<span class="ui-icon dialog_icon"></span>
					</a>
				</li>
				<li class="ui-widget-content ui-menu-divider"></li>
				<li><strong><spring:message code="titulo-nave" /></strong></li>
				<li>
					<a>
						<spring:message code="menu" />
						<span class="ui-icon menu_icon"></span>
					</a>
					<ul>
						<li>
							<spring:url value="/patrones/menu" var="urlMenu" htmlEscape="true"/>
							<a href="${urlMenu}">
								<spring:message code="menuHorizontal" />
							</a>
						</li>
						<li>
							<spring:url value="/patrones/menuVertical" var="urlMenuVert" htmlEscape="true"/>
							<a href="${urlMenuVert}">
								<spring:message code="menuVertical" />
							</a>
						</li>
						<li>
							<spring:url value="/patrones/menuMixto" var="urlMenuMixto" htmlEscape="true"/>
							<a	href="${urlMenuMixto}">
								<spring:message code="menuMixto" />
							</a>
						</li>
					</ul>
				</li>
				<li>
					<spring:url value="/patrones/contextMenu" var="urlContextMenu" htmlEscape="true"/>
					<a href="${urlContextMenu}">
						<spring:message code="contextMenu" />
						<span class="ui-icon contextMenu_icon"></span>
					</a>
				</li>
				<li>
					<spring:url value="/patrones/toolbar" var="urlToolbar" htmlEscape="true"/>
					<a href="${urlToolbar}">
						<spring:message code="toolbar" />
						<span class="ui-icon toolbar_icon"></span>
					</a>
				</li>
				<li>
					<spring:url value="/patrones/button" var="urlButton" htmlEscape="true"/>
					<a href="${urlButton}">
						<spring:message code="button" />
						<span class="ui-icon toolbar_icon"></span>
					</a>
				</li>
				<li class="ui-widget-content ui-menu-divider"></li>
				<li><strong><spring:message code="titulo-estru" /></strong></li>
				<li>
					<spring:url value="/patrones/accordion" var="urlAccordion" htmlEscape="true"/>
					<a href="${urlAccordion}">
						<spring:message code="accordion" />
						<span class="ui-icon accordion_icon"></span>
					</a>
				</li>
				<li>
					<a>
						<spring:message code="tabs" />
						<span class="ui-icon tab_icon"></span>
					</a>
					<ul>
						<li>
							<spring:url value="/patrones/tabsStatic" var="urlTabsStatic" htmlEscape="true"/>
							<a href="${urlTabsStatic}">
								<spring:message code="tabsStatic" />
							</a>
						</li>
						<li>
							<spring:url value="/patrones/tabsAjax" var="urlTabsStatic" htmlEscape="true"/>
							<a	href="${urlTabsStatic}">
								<spring:message code="tabsAjax" />
							</a>
						</li>
						<li>
							<spring:url value="/patrones/tabsMixto" var="urlTabsMixto" htmlEscape="true"/>
							<a	href="${urlTabsMixto}">
								<spring:message code="tabsMixto" />
							</a>
						</li>
						<li>
							<spring:url value="/patrones/maintTab" var="urlMaintTab" htmlEscape="true"/>
							<a href="${urlMaintTab}">
								<spring:message code="maintTab" />
							</a>
						</li>
						<li>
							<spring:url value="/patrones/tabsScrollable" var="urlTabsScrollable" htmlEscape="true"/>
							<a href="${urlTabsScrollable}">
								<spring:message code="tabsScrollable" />
							</a>
						</li>
					</ul>
				</li>
				<li>
					<spring:url value="/patrones/grid" var="urlGrid" htmlEscape="true"/>
					<a href="${urlGrid}">
						<spring:message code="grid" />
						<span class="ui-icon grid_icon"></span>
					</a>
					<span class="ui-icon old_icon"></span>
					<ul>
						<li>
							<a	href="/x21aPilotoPatronesWar/patrones/gridGroup">
								<spring:message code="grid_grupo" />
							</a>
						</li>
						<li>
							<a	href="/x21aPilotoPatronesWar/patrones/grid">
								<spring:message code="grid_base" />
							</a>
						</li>
						<li>
							<a href="/x21aPilotoPatronesWar/patrones/gridTree">
								<spring:message code="grid_arbol" />
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a>
						<spring:message code="wizard" />
						<span class="ui-icon wizard_icon"></span>
					</a>
					<ul>
						<li>
							<spring:url value="/patrones/wizard" var="urlWizard" htmlEscape="true"/>
							<a	href="${urlWizard}">
								<spring:message code="wizardA" />
							</a>
						</li>
						<li>
							<spring:url value="/patrones/wizard_includeFile" var="urlWizIncludeFile" htmlEscape="true"/>
							<a href="${urlWizIncludeFile}">
								<spring:message code="wizardB" htmlEscape="true"/>
							</a>
						</li>
						<li>
							<spring:url value="/patrones/wizard_jspInclude" var="urlWizJspInclude" htmlEscape="true"/>
							<a href="${urlWizJspInclude}">
								<spring:message code="wizardC" htmlEscape="true"/>
							</a>
						</li>
						<li>
							<spring:url value="/patrones/wizard_jstlImport" var="urlWizJstlImport" htmlEscape="true"/>
							<a	href="${urlWizJstlImport}">
								<spring:message code="wizardD" htmlEscape="true"/>
							</a>
						</li>
						<li>
							<spring:url value="/patrones/wizard_dinamico" var="urlWizDinamico" htmlEscape="true"/>
							<a	href="${urlWizDinamico}">
								<spring:message code="wizardE"/>
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a>
						<spring:message code="tree" />
						<span class="ui-icon tree_icon"></span>
					</a>
					<ul>
						<li>
							<spring:url value="/patrones/trees" var="urlTrees" htmlEscape="true"/>
							<a	href="${urlTrees}">
								<spring:message code="tree_multiple_configuraciones" />
							</a>
						</li>
						<li>
							<a href="/x21aPilotoPatronesWar/patrones/trees">
								<spring:message code="tree_multiple_formatoDatos"/>
							</a>
						</li>
						<li>
							<spring:url value="/patrones/treeDAD" var="urlTreesDAD" htmlEscape="true"/>
							<a href="${urlTreesDAD}">
								<spring:message code="tree_multiple_d&d"/>
							</a>
						</li>
						<li>
							<a href="/x21aPilotoPatronesWar/patrones/trees">
								<spring:message code="tree_panel" htmlEscape="true"/>
							</a>
						</li>
					</ul>
				</li>
				<li class="ui-widget-content ui-menu-divider"></li>
				<li><strong><spring:message code="titulo-inser" /></strong></li>
				<li>
					<spring:url value="/patrones/autocomplete" var="urlAutocomplete" htmlEscape="true"/>
					<a href="${urlAutocomplete}">
						<spring:message code="autocomplete" />
						<span class="ui-icon autocomplete_icon"></span>
					</a>
				</li>
				<li>
					<a>
						<spring:message code="combo" />
						<span class="ui-icon combo_icon"></span>
					</a>
					<ul>
						<li>
							<spring:url value="/patrones/comboSimple" var="urlComboSimple" htmlEscape="true"/>
							<a href="${urlComboSimple}">
								<spring:message code="comboSimple" />
							</a>
						</li>
						<li>
							<spring:url value="/patrones/comboEnlazadoSimple" var="urlComboEnlazado" htmlEscape="true"/>
							<a href="${urlComboEnlazado}">
								<spring:message code="comboEnlazadoSimple" />
							</a>
						</li>
						<li>
							<spring:url value="/patrones/comboEnlazadoMultiple" var="urlComboEnlazadoMultiple" htmlEscape="true"/>
							<a href="${urlComboEnlazadoMultiple}">
								<spring:message code="comboEnlazadoMulti" />
							</a>
						</li>
						<li>
							<spring:url value="/patrones/multicombo" var="urlMulticombo" htmlEscape="true"/>
							<a href="${urlMulticombo}">
								<spring:message code="multicombo" />
							</a>
						</li>
					</ul>
				</li>
				<li>
					<spring:url value="/patrones/date" var="urlDate" htmlEscape="true"/>
					<a href="${urlDate}">
						<spring:message code="date" />
						<span class="ui-icon date_icon"></span>
					</a>
				</li>
				<li>
					<spring:url value="/patrones/form" var="urlForm" htmlEscape="true"/>
					<a href="${urlForm}">
						<spring:message code="form" />
						<span class="ui-icon form_icon"></span>
					</a>
				</li>
				<li>
					<spring:url value="/patrones/time" var="urlTime" htmlEscape="true"/>
					<a href="${urlTime}">
						<spring:message code="time" />
						<span class="ui-icon time_icon"></span>
					</a>
				</li>
				<li>
					<spring:url value="/patrones/upload" var="urlUpload" htmlEscape="true"/>
					<a href="${urlUpload}">
						<spring:message code="upload" />
						<span class="ui-icon upload_icon"></span>
					</a>
				</li>
				<li>
					<spring:url value="/patrones/validate" var="urlValidate" htmlEscape="true"/>
					<a href="${urlValidate}">
						<spring:message code="validate" />
						<span class="ui-icon validate_icon"></span>
					</a>
				</li>
				
				<li class="ui-widget-content ui-menu-divider"></li>
				<li><strong><spring:message code="repDatos" /></strong></li>
				<li>
					<spring:url value="/patrones/charts" var="urlCharts" htmlEscape="true"/>
					<a href="${urlCharts}">
						<spring:message code="charts" />
						<span class="ui-icon chart_icon"></span>
					</a>
				</li>
			</ul>
		</li>
<%-- <%-- 		<sec:authorize access="hasRole('ROLE_X21A-IN-0003')"> --%>
		<li>
			<a>
				<spring:message code="experimental" />
				<span class="ui-icon experimental_icon"></span>
			</a>
			<ul>
				<li>
					<spring:url value="/experimental/logLevel" var="urlLogLevel" htmlEscape="true"/>
					<a href="${urlLogLevel}">
						<spring:message code="logLevel" />
					</a>
				</li>
			<spring:url value="/iberdok/view" var="urlIberdokView" htmlEscape="true"/>
			<li><a href="${urlIberdokView}"> <spring:message code="iberdok" /> </a></li>	
				
			</ul>
		</li>
		<li>
			<a>
				<spring:message code="integracion" />
				<span class="ui-icon integracion_icon"></span>
			</a>
			<ul>
				<li>
					<spring:url value="/integracion/z-index" var="urlZindex" htmlEscape="true"/>
					<a	href="${urlZindex}">
						<spring:message code="z-index" />
						<span class="ui-icon z-index_icon"></span>
					</a>
				</li>
				<li>
					<spring:url value="/integracion/nora" var="urlNora" htmlEscape="true"/>
					<a	href="${urlNora}">
						<spring:message code="nora" />
						<span class="ui-icon nora_icon"></span>
					</a>
				</li>
				<li>
					<spring:url value="/integracion/tiny" var="urlTiny" htmlEscape="true"/>
					<a	href="${urlTiny}">
						<spring:message code="tiny" />
						<span class="ui-icon tiny_icon"></span>
					</a>
				</li>
				<li>
					<spring:url value="/integracion/webdav" var="urlWebDav" htmlEscape="true"/>
					<a	href="${urlWebDav}">
						<spring:message code="webdav" />
						<span class="ui-icon tiny_icon"></span>
					</a>
				</li>
				<li>
					<spring:url value="/integracion/pif" var="urlPif" htmlEscape="true"/>
					<a	href="${urlPif}">
						PIF
						<span class="ui-icon tiny_icon"></span>
					</a>
				</li>
				<li>
					<spring:url value="/cache/view" var="urlCacheView" htmlEscape="true"/>
					<a	href="${urlCacheView}">
						Cache
						<span class="ui-icon tiny_icon"></span>
					</a>
				</li>
			</ul>
		</li>
<%-- 		</sec:authorize> --%>
		<li>
			<spring:url value="http://uda-ejie.github.io/index.html" var="urlUdaGH" htmlEscape="true"/>
			<a	href="${urlUdaGH}" target="_blank">
				<spring:message code="uda" />
				<span class="ui-icon uda_icon"></span>
			</a>
		</li>
	</ul>