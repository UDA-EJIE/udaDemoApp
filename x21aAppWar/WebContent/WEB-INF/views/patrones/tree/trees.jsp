<%--  
 -- Copyright 2013 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>

<%@include file="/WEB-INF/includeTemplate.inc"%>
<section class="content">
	<h2>
		<spring:message code="tree.trees" />
	</h2>

	<div id="treeCodeDialog" style="display: none">
		<div id="htmlCode" class="codePanel codePanelLeft"></div>
		<div id="javaScriptCode" class="codePanel codePanelRight"></div>
	</div>

	<div id="tree_example" class="card p-3">
		<ul>
			<spring:url value="#" var="urlHashtag" htmlEscape="true"/>
			<li id="node1"><a href="${urlHashtag}">Padre 1</a>
				<ul>
					<li id="node11"><a href="${urlHashtag}">Padre 1.1</a>
						<ul>
							<li id="node111"><a href="${urlHashtag}">Hoja 1.1.1</a></li>
							<li id="node112"><a href="${urlHashtag}">Padre 1.1.2</a>
								<ul>
									<li id="node1121"><a href="${urlHashtag}">Hoja 1.1.2.1</a></li>
									<li id="node1122"><a href="${urlHashtag}">Hoja 1.1.2.2</a></li>
								</ul></li>
						</ul></li>
					<li id="node12"><a href="${urlHashtag}">Hoja 1.2></a></li>
				</ul></li>
		</ul>
	</div>

	<div id="model_example_1" class="model_example_1_code">
		<ul>
			<li id="node1"><a href="${urlHashtag}"><spring:message
						code="treeExample.Hijo1" /></a>
				<ul>
					<li id="node11"><a href="${urlHashtag}"><spring:message
								code="treeExample.Hijo2" /></a>
						<ul>
							<li id="node111"><a href="${urlHashtag}"><spring:message
										code="treeExample.hoja" /></a></li>
							<li id="node112"><a href="${urlHashtag}"><spring:message
										code="treeExample.Hijo3" /></a>
								<ul>
									<li id="node1121"><a href="${urlHashtag}"><spring:message
												code="treeExample.hoja" /></a></li>
								</ul></li>
							<li id="node113"><a href="${urlHashtag}"><spring:message
										code="treeExample.Hijo3" /></a>
								<ul>
									<li id="node1131"><a href="${urlHashtag}"><spring:message
												code="treeExample.hoja" /></a></li>
									<li id="node1132"><a href="${urlHashtag}"><spring:message
												code="treeExample.hoja" /></a></li>
								</ul></li>
						</ul></li>
					<li id="node12"><a href="${urlHashtag}"><spring:message
								code="treeExample.Hijo2" /></a>
						<ul>
							<li id="node121"><a href="${urlHashtag}"><spring:message
										code="treeExample.Hijo3" /></a>
								<ul>
									<li id="node1211"><a href="${urlHashtag}"><spring:message
												code="treeExample.hoja" /></a></li>
									<li id="node1212"><a href="${urlHashtag}"><spring:message
												code="treeExample.hoja" /></a></li>
									<li id="node1213"><a href="${urlHashtag}"><spring:message
												code="treeExample.hoja" /></a></li>
									<li id="node1214"><a href="${urlHashtag}"><spring:message
												code="treeExample.hoja" /></a></li>
								</ul></li>
							<li id="node122"><a href="${urlHashtag}"><spring:message
										code="treeExample.hoja" /></a></li>
						</ul></li>
					<li id="node13"><a href="${urlHashtag}"><spring:message
								code="treeExample.hoja" /></a></li>
				</ul></li>
			<li id="node2"><a href="${urlHashtag}"><spring:message
						code="treeExample.hoja" /></a></li>
		</ul>
	</div>

	<div id="model_example_2" class="model_example_2_code">
		<ul>
			<li id="nodeA"><a href="${urlHashtag}"><spring:message
						code="treeExample.HijoA" /></a>
				<ul>
					<li id="nodeAA"><a href="${urlHashtag}"><spring:message
								code="treeExample.HijoA" /> <spring:message
								code="treeExample.HijoA" /></a></li>
					<li id="nodeAB"><a href="${urlHashtag}"><spring:message
								code="treeExample.HijoA" /> <spring:message
								code="treeExample.HijoB" /></a></li>
					<li id="nodeAC"><a href="${urlHashtag}"><spring:message
								code="treeExample.HijoA" /> <spring:message
								code="treeExample.HijoC" /></a>
						<ul>
							<li id="nodeACA"><a href="${urlHashtag}"><spring:message
										code="treeExample.HijoA" /> <spring:message
										code="treeExample.HijoC" /> <spring:message
										code="treeExample.HijoA" /></a></li>
							<li id="nodeACB"><a href="${urlHashtag}"><spring:message
										code="treeExample.HijoA" /> <spring:message
										code="treeExample.HijoC" /> <spring:message
										code="treeExample.HijoB" /></a></li>
						</ul></li>
				</ul></li>
			<li id="nodeB"><a href="${urlHashtag}"><spring:message
						code="treeExample.HijoB" /></a></li>
			<li id="nodeC"><a href="${urlHashtag}"><spring:message
						code="treeExample.HijoC" /></a>
				<ul>
					<li id="nodeCA"><a href="${urlHashtag}"><spring:message
								code="treeExample.HijoC" /> <spring:message
								code="treeExample.HijoA" /></a></li>
					<li id="nodeCB"><a href="${urlHashtag}"><spring:message
								code="treeExample.HijoC" /> <spring:message
								code="treeExample.HijoB" /></a></li>
				</ul></li>
		</ul>
	</div>

	<section class="row">
		<div class="col-xl-3  col-12  col-sm-4">
			<div class="butstyle">
				<h3 class="rup_tree_h3">
					<spring:message code="tree.example.default" />
				</h3>
				<div id="defaultDemo" class="rup_tree model_example_1"></div>
				<div id="codeDefaultControl" class="controlsMainCode">
					<button id="btnDefaultDemo">
						<spring:message code="tree.verCodigo" />
					</button>
				</div>
			</div>
		</div>

		<div class="col-xl-3  col-12  col-sm-4">
			<div class="butstyle">
				<h3 class="rup_tree_h3">
					<spring:message code="tree.example.specificStyle" />
				</h3>
				<div id="specificStyleDemo" class="rup_tree model_example_1"></div>
				<div id="codeSpecificStyleControl" class="controlsMainCode">
					<button id="btnSpecificStyleDemo">
						<spring:message code="tree.verCodigo" />
					</button>
				</div>
			</div>
		</div>
		<div class="col-xl-3  col-12  col-sm-4">
			<div class="butstyle">
				<h3 class="rup_tree_h3">
					<spring:message code="tree.example.changeView" />
				</h3>
				<div id="changeViewDemo" class="rup_tree model_example_1_button"></div>

				<div id="changeViewExampleControl" class="controlsMainExample">
					<button id="toggle_dots" style="width: 32%">
						<spring:message code="treeExample.dots" />
					</button>
					<button id="toggle_icons" style="width: 32%">
						<spring:message code="treeExample.icons" />
					</button>
					<button id="changeTheme" style="width: 32%">
						<spring:message code="treeExample.theme" />
					</button>
				</div>

				<div id="codeChangeViewControl" class="controlsMainCode">
					<button id="btnChangeViewDemo">
						<spring:message code="tree.verCodigo" />
					</button>
				</div>
			</div>
		</div>


		<div class="col-xl-3  col-12  col-sm-4">
			<div class="butstyle">
				<h3 class="rup_tree_h3">
					<spring:message code="tree.example.jqueryuiStyles" />
				</h3>
				<div id="jqueryuiStylesDemo"
					class="rup_tree model_example_1_jqueryui"></div>

				<div id="jqueryuiStylesViewControl" class="controlsMainCode">
					<button id="btnJqueryuiStylesDemo">
						<spring:message code="tree.verCodigo" />
					</button>
				</div>
			</div>
		</div>
	</section>
	<section class="row">
		<div class="col-xl-3  col-12  col-sm-4">
			<div class="butstyle">
				<h3 class="rup_tree_h3">
					<spring:message code="tree.example.select" />
				</h3>
				<div id="selectDemo" class="rup_tree model_example_1_button"></div>

				<div id="selectExampleControl" class="controlsMainExample">
					<button id="saveSelection" style="width: 32%">
						<spring:message code="treeExample.save" />
					</button>
					<button id="loadSelection" style="width: 32%">
						<spring:message code="treeExample.load" />
					</button>
					<button id="deselectAll" style="width: 32%">
						<spring:message code="treeExample.deselect" />
					</button>
				</div>

				<div id="codeSelectControl" class="controlsMainCode">
					<button id="btnSelectDemo">
						<spring:message code="tree.verCodigo" />
					</button>
				</div>
			</div>
		</div>

		<div class="col-xl-3  col-12  col-sm-4">
			<div class="butstyle">
				<h3 class="rup_tree_h3">
					<spring:message code="tree.example.hotkeys" />
				</h3>
				<div id="hotkeysDemo" class="rup_tree model_example_1_button"></div>

				<div id="hotkeysExampleControl" class="controlsMainExample">
					<button id="enableHotkeys" style="width: 49%">
						<spring:message code="treeExample.enable" />
					</button>
					<button id="disableHotkeys" style="width: 49%">
						<spring:message code="treeExample.disable" />
					</button>
				</div>

				<div id="codeHotkeysControl" class="controlsMainCode">
					<button id="btnHotkeysDemo">
						<spring:message code="tree.verCodigo" />
					</button>
				</div>
			</div>
		</div>

		<div class="col-xl-3  col-12  col-sm-4">
			<div class="butstyle">
				<h3 class="rup_tree_h3">
					<spring:message code="tree.example.sort" />
				</h3>
				<div id="sortDemo" class="rup_tree model_example_1_button"></div>

				<div id="sortExampleControl" class="controlsMainExample">
					<button id="renameButton" style="width: 50%; text-align: center;">
						<spring:message code="treeExample.rename" />
					</button>
					<label><spring:message code="treeExample.renameKey" /></label>
				</div>

				<div id="codeSortControl" class="controlsMainCode">
					<button id="btnSortDemo">
						<spring:message code="tree.verCodigo" />
					</button>
				</div>
			</div>
		</div>
		<div class="col-xl-3  col-12  col-sm-4">
			<div class="butstyle">
				<h3 class="rup_tree_h3">
					<spring:message code="tree.example.checkbox" />
				</h3>
				<div id="checkboxDemo" class="rup_tree model_example_1_button"></div>

				<div id="checkboxExampleControl" class="controlsMainExample">
					<button id="checkboxShowHide" style="width: 35%">
						<spring:message code="treeExample.showHide" />
					</button>
					<button id="checkboxCheckAll"
						style="width: 31%; white-space: nowrap;">
						<spring:message code="treeExample.checkAll" />
					</button>
					<button id="checkboxUncheckAll"
						style="width: 31%; white-space: nowrap;">
						<spring:message code="treeExample.unCheckAll" />
					</button>
				</div>

				<div id="codeCheckboxControl" class="controlsMainCode">
					<button id="btnCheckboxDemo">
						<spring:message code="tree.verCodigo" />
					</button>
				</div>
			</div>
		</div>
	</section>
	<section class="row">
		<div class="col-xl-3  col-12  col-sm-4">
			<div class="butstyle">
				<h3 class="rup_tree_h3">
					<spring:message code="tree.example.contextmenu" />
				</h3>
				<div id="contextmenuDemo" class="rup_tree model_example_1"></div>

				<div id="codeContextmenuControl" class="controlsMainCode">
					<button id="btnContextmenuDemo">
						<spring:message code="tree.verCodigo" />
					</button>
				</div>
			</div>
		</div>

		<div class="col-xl-3  col-12  col-sm-4">
			<div class="butstyle">
				<h3 class="rup_tree_h3">
					<spring:message code="tree.example.unique" />
				</h3>
				<div id="uniqueDemo" class="rup_tree model_example_1_button"></div>

				<div id="uniqueExampleControl" class="controlsMainExample">
					<button id="uniqueRenameButton"
						style="width: 49%; text-align: center;">
						<spring:message code="treeExample.rename" />
					</button>
					<button id="uniqueNewButton"
						style="width: 49%; text-align: center;">
						<spring:message code="treeExample.new" />
					</button>
				</div>

				<div id="uniqueControl" class="controlsMainCode">
					<button id="btnUniqueDemo">
						<spring:message code="tree.verCodigo" />
					</button>
				</div>
			</div>
		</div>
		<div class="col-xl-3  col-12  col-sm-4">
			<div class="butstyle">
				<h3 class="rup_tree_h3">Carga datos json</h3>
				<div id="jsonTreeDemo" class="rup_tree model_example_1"></div>

				<div id="codeJsonTreeControl" class="controlsMainCode">
					<button id="btnJsonTreeDemo">
						<spring:message code="tree.verCodigo" />
					</button>
				</div>
			</div>
		</div>
		<div class="col-xl-3  col-12  col-sm-4">
			<div class="butstyle">
				<h3 class="rup_tree_h3">Carga datos xml</h3>
				<div id="xmlTreeDemo" class="xml_tree model_example_1"></div>

				<div id="codeXmlTreeControl" class="controlsMainCode">
					<button id="btnXmlTreeDemo">
						<spring:message code="tree.verCodigo" />
					</button>
				</div>
			</div>
		</div>
	</section>
	<section class="row">
		<div class="col-xl-3  col-12  col-sm-4">
			<div class="butstyle">
				<h3 class="rup_tree_h3">Carga datos mediante AJAX (json)</h3>
				<div id="ajaxTreeDemo" class="ajax_tree model_example_1"></div>

				<div id="codeAjaxTreeControl" class="controlsMainCode">
					<button id="btnAjaxTreeDemo">
						<spring:message code="tree.verCodigo" />
					</button>
				</div>
			</div>
		</div>
	</section>
</section>