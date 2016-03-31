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
 
	<ul id="x21aPilotoPatronesWar_menu"	class="rup_invisible_menu">
		<li>
			<a href="/x21aPilotoPatronesWar/../x21aMantenimientosWar/">
				<spring:message code="mantenimientos" />
			</a>
		</li>
		<li>
			<a href="/x21aPilotoPatronesWar/" >
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
					<a href="/x21aPilotoPatronesWar/patrones/all">
						<spring:message code="all" />
					</a>
				</li>
				<li class="ui-widget-content ui-menu-divider"></li>
				<li><strong><spring:message code="titulo-notifi" /></strong></li>
				<li>
					<a href="/x21aPilotoPatronesWar/patrones/feedback">
						<spring:message code="feedback" />
						<span class="ui-icon feedback_icon"></span>
					</a>
				</li>
				<li>
					<a href="/x21aPilotoPatronesWar/patrones/tooltip">
						<spring:message code="tooltip" />
						<span class="ui-icon tooltip_icon"></span>
					</a>
				</li>
				<li>
					<a href="/x21aPilotoPatronesWar/patrones/message">
						<spring:message code="message" />
						<span class="ui-icon message_icon"></span>
					</a>
				</li>
				<li>
					<a href="/x21aPilotoPatronesWar/patrones/dialog">
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
							<a href="/x21aPilotoPatronesWar/patrones/menu">
								<spring:message code="menuHorizontal" />
							</a>
						</li>
						<li>
							<a href="/x21aPilotoPatronesWar/patrones/menuVertical">
								<spring:message code="menuVertical" />
							</a>
						</li>
						<li>
							<a	href="/x21aPilotoPatronesWar/patrones/menuMixto">
								<spring:message code="menuMixto" />
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="/x21aPilotoPatronesWar/patrones/contextMenu">
						<spring:message code="contextMenu" />
						<span class="ui-icon contextMenu_icon"></span>
					</a>
				</li>
				<li>
					<a href="/x21aPilotoPatronesWar/patrones/toolbar">
						<spring:message code="toolbar" />
						<span class="ui-icon toolbar_icon"></span>
					</a>
				</li>
				<li>
					<a href="/x21aPilotoPatronesWar/patrones/button">
						<spring:message code="button" />
						<span class="ui-icon toolbar_icon"></span>
					</a>
				</li>
				<li class="ui-widget-content ui-menu-divider"></li>
				<li><strong><spring:message code="titulo-estru" /></strong></li>
				<li>
					<a href="/x21aPilotoPatronesWar/patrones/accordion">
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
							<a href="/x21aPilotoPatronesWar/patrones/tabsStatic">
								<spring:message code="tabsStatic" />
							</a>
						</li>
						<li>
							<a	href="/x21aPilotoPatronesWar/patrones/tabsAjax">
								<spring:message code="tabsAjax" />
							</a>
						</li>
						<li>
							<a	href="/x21aPilotoPatronesWar/patrones/tabsMixto">
								<spring:message code="tabsMixto" />
							</a>
						</li>
						<li>
							<a href="/x21aPilotoPatronesWar/patrones/maintTab">
								<spring:message code="maintTab" />
							</a>
						</li>
						<li>
							<a href="/x21aPilotoPatronesWar/patrones/tabsScrollable">
								<spring:message code="tabsScrollable" />
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="/x21aPilotoPatronesWar/patrones/grid">
						<spring:message code="grid" />
						<span class="ui-icon grid_icon"></span>
					</a>
					<span class="ui-icon old_icon"></span>
<!-- 					<ul> -->
<!-- 						<li> -->
<!-- 							<a	href="/x21aPilotoPatronesWar/patrones/gridGroup"> -->
<%-- 								<spring:message code="grid_grupo" /> --%>
<!-- 							</a> -->
<!-- 						</li> -->
<!-- 						<li> -->
<!-- 							<a	href="/x21aPilotoPatronesWar/patrones/grid"> -->
<%-- 								<spring:message code="grid_base" /> --%>
<!-- 							</a> -->
<!-- 						</li> -->
<!-- 						<li> -->
<!-- 							<a href="/x21aPilotoPatronesWar/patrones/gridTree"> -->
<%-- 								<spring:message code="grid_arbol" /> --%>
<!-- 							</a> -->
<!-- 						</li> -->
<!-- 					</ul> -->
				</li>
				<li>
					<a>
						<spring:message code="wizard" />
						<span class="ui-icon wizard_icon"></span>
					</a>
					<ul>
						<li>
							<a	href="/x21aPilotoPatronesWar/patrones/wizard">
								<spring:message code="wizardA" />
							</a>
						</li>
						<li>
							<a href="/x21aPilotoPatronesWar/patrones/wizard_includeFile">
								<spring:message code="wizardB" htmlEscape="true"/>
							</a>
						</li>
						<li>
							<a href="/x21aPilotoPatronesWar/patrones/wizard_jspInclude">
								<spring:message code="wizardC" htmlEscape="true"/>
							</a>
						</li>
						<li>
							<a	href="/x21aPilotoPatronesWar/patrones/wizard_jstlImport">
								<spring:message code="wizardD" htmlEscape="true"/>
							</a>
						</li>
						<li>
							<a	href="/x21aPilotoPatronesWar/patrones/wizard_dinamico">
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
							<a	href="/x21aPilotoPatronesWar/patrones/trees">
								<spring:message code="tree_multiple_configuraciones" />
							</a>
						</li>
<!-- 						<li> -->
<!-- 							<a href="/x21aPilotoPatronesWar/patrones/trees"> -->
<%-- 								<spring:message code="tree_multiple_formatoDatos"/> --%>
<!-- 							</a> -->
<!-- 						</li> -->
						<li>
							<a href="/x21aPilotoPatronesWar/patrones/treeDAD">
								<spring:message code="tree_multiple_d&d"/>
							</a>
						</li>
<!-- 						<li> -->
<!-- 							<a href="/x21aPilotoPatronesWar/patrones/trees"> -->
<%-- 								<spring:message code="tree_panel" htmlEscape="true"/> --%>
<!-- 							</a> -->
<!-- 						</li> -->
					</ul>
				</li>
				<li class="ui-widget-content ui-menu-divider"></li>
				<li><strong><spring:message code="titulo-inser" /></strong></li>
				<li>
					<a href="/x21aPilotoPatronesWar/patrones/autocomplete">
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
							<a href="/x21aPilotoPatronesWar/patrones/comboSimple">
								<spring:message code="comboSimple" />
							</a>
						</li>
						<li>
							<a href="/x21aPilotoPatronesWar/patrones/comboEnlazadoSimple">
								<spring:message code="comboEnlazadoSimple" />
							</a>
						</li>
						<li>
							<a href="/x21aPilotoPatronesWar/patrones/comboEnlazadoMultiple">
								<spring:message code="comboEnlazadoMulti" />
							</a>
						</li>
						<li>
							<a href="/x21aPilotoPatronesWar/patrones/multicombo">
								<spring:message code="multicombo" />
							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="/x21aPilotoPatronesWar/patrones/date">
						<spring:message code="date" />
						<span class="ui-icon date_icon"></span>
					</a>
				</li>
				<li>
					<a href="/x21aPilotoPatronesWar/patrones/form">
						<spring:message code="form" />
						<span class="ui-icon form_icon"></span>
					</a>
				</li>
				<li>
					<a href="/x21aPilotoPatronesWar/patrones/time">
						<spring:message code="time" />
						<span class="ui-icon time_icon"></span>
					</a>
				</li>
				<li>
					<a href="/x21aPilotoPatronesWar/patrones/upload">
						<spring:message code="upload" />
						<span class="ui-icon upload_icon"></span>
					</a>
				</li>
				<li>
					<a href="/x21aPilotoPatronesWar/patrones/validate">
						<spring:message code="validate" />
						<span class="ui-icon validate_icon"></span>
					</a>
				</li>
			</ul>
		</li>
<%-- 		<sec:authorize access="hasRole('ROLE_X21A-IN-0003')"> --%>
		<li>
			<a>
				<spring:message code="experimental" />
				<span class="ui-icon experimental_icon"></span>
			</a>
			<ul>
				<li>
					<a href="/x21aPilotoPatronesWar/experimental/maestro_detalle">
						<spring:message code="maestro_detalle" />
					</a>
				</li>
				<li>
					<a href="/x21aPilotoPatronesWar/experimental/mant_multi_entidad">
						<spring:message code="mant_multi_entidad" />
					</a>
				</li>
				<li>
					<a>
						<spring:message code="mant_clave_compuesta" />
					</a>
					<ul>
						<li>
							<a	href="/x21aPilotoPatronesWar/experimental/mant_clave_compuesta_multi">
								<spring:message code="mant_clave_compuesta_multi" />
							</a>
						</li>
						<li>
							<a	href="/x21aPilotoPatronesWar/experimental/mant_clave_compuesta_edlinea">
								<spring:message code="mant_clave_compuesta_edlinea" />
							</a>
						</li>
					</ul>
				</li>
				
				<!--test Tabs Pagind  -->
				<li>
					<a href="/x21aPilotoPatronesWar/experimental/tabsPaging">
						<spring:message code="tabsPaging" />
					</a>
				</li>
				
						<!--test logLevel -->
				<li>
					<a href="/x21aPilotoPatronesWar/experimental/logLevel">
						<spring:message code="logLevel" />
					</a>
				</li>
				
				
			</ul>
		</li>
		<li>
			<a>
				<spring:message code="integracion" />
				<span class="ui-icon integracion_icon"></span>
			</a>
			<ul>
				<li>
					<a	href="/x21aPilotoPatronesWar/integracion/z-index">
						<spring:message code="z-index" />
						<span class="ui-icon z-index_icon"></span>
					</a>
				</li>
				<li>
					<a	href="/x21aPilotoPatronesWar/integracion/nora">
						<spring:message code="nora" />
						<span class="ui-icon nora_icon"></span>
					</a>
				</li>
				<li>
					<a	href="/x21aPilotoPatronesWar/integracion/tiny">
						<spring:message code="tiny" />
						<span class="ui-icon tiny_icon"></span>
					</a>
				</li>
				<li>
					<a	href="/x21aPilotoPatronesWar/integracion/webdav">
						<spring:message code="webdav" />
						<span class="ui-icon tiny_icon"></span>
					</a>
				</li>
				<li>
					<a	href="/x21aPilotoPatronesWar/integracion/pif">
						PIF
						<span class="ui-icon tiny_icon"></span>
					</a>
				</li>
				<li>
					<a	href="/x21aPilotoPatronesWar/integracion/cache/view">
						Cache
						<span class="ui-icon tiny_icon"></span>
					</a>
				</li>
			</ul>
		</li>
<%-- 		</sec:authorize> --%>
		<li>
			<a	href="http://uda-ejie.github.io/index.html" target="_blank">
				<spring:message code="uda" />
				<span class="ui-icon uda_icon"></span>
			</a>
		</li>
	</ul>