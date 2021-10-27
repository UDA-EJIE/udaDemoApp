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

	<h2 class="title mb-3">
		<spring:message code="tree.treeDAD" />
	</h2>

	<!-- Árbol con ordenación -->
	<fieldset id="treeAreaExchangePanel" class="treeAreaExchangePanel">
		<legend class="treeAreaExchangePanel_legend">
			<spring:message code="tree.treeDAD.reorder" />
		</legend>
		<div id="tasksReorderTree" class="ExchangePanel_tree"></div>
		<div id="tasksReorderTreeUniqueControl" class="DADcontrolsMainCode">
			<button id="btnReorderTreeUniqueControl">
				<spring:message code="tree.verCodigo.ordenacion" />
			</button>
		</div>
	</fieldset>

	<!-- Árboles con intercambio -->
	<fieldset id="treeNodesExchangePanel" class="treeNodesExchangePanel">
		<legend class="treeNodesExchangePanel_legend">
			<spring:message code="tree.treeDAD.exchange" />
		</legend>
		<div>
			<div class="enLinea panelLeftWorkers">
				<div id="tasksTree" class="ExchangePanel_tree"></div>

			</div>
			<!-- 		<div class="treeNodesExchangePanel_center"> -->
		<!-- 			<div class="treeNodesExchangePanel_center_line"></div> -->
		<%-- 			<img src="${staticsUrl}/x21a/images/twoWayArrows.png" --%>
		<!-- 				class="treeNodesExchangePanel_center_img" -->
		<%-- 				alt="<spring:message code="tree.treeDAD.Interchange" />" /> --%>
		<!-- 		</div> -->
		<div class="enLinea panelRightWorkers">
			<div id="workersTree" class="ExchangePanel_tree"></div>
			
		</div>
			<div id="tasksExchangeTreeUniqueControl" class="DADcontrolsMainCode">
				<button id="btnExchangeTreeUniqueControl">
					<spring:message code="tree.verCodigo.intercambio" />
				</button>
			</div>
	</fieldset>

	<fieldset id="treeNodeAreaExchangePanel"
		class="treeNodeAreaExchangePanel">
		<legend class="treePromotionsDismissalsPanel_legend">
			<spring:message code="tree.treeDAD.exchange" />
		</legend>
		<div class="promotionsDismissalsExchangePanel">
			<div class="promotionsDismissalsExchangePanel_left">
				<div id="promotionsDismissalsTree" class="promotionsDismissals_tree"></div>
			</div>
			<div class="promotionsDismissalsExchangePanel_center">
				<div class="promotionsDismissalsExchangePanel_center_line"></div>
				<div class="promotionsDismissalsExchangePanel_center_img_div">
					<img src="${staticsUrl}/x21a/images/rightArrow.png"
						class="promotionsDismissalsExchangePanel_center_img"
						alt="<spring:message code="tree.treeDAD.rightDirec" />" />
				</div>
			</div>
			<div class="promotionsDismissalsExchangePanel_right">
				<div class="promotionsDismissalsActionPanel">
					<h3 class="promotionsDismissalsDestiny">
						<spring:message code="tree.treeDAD.promotions" />
					</h3>
					<ul class="promotionsDismissalsActionPanel_list"></ul>
				</div>
				<div class="promotionsDismissalsActionPanel_line"></div>
				<div
					class="promotionsDismissalsActionPanel promotionsDismissalsActionPanelCenter">
					<h3 class="promotionsDismissalsDestiny">
						<spring:message code="tree.treeDAD.formation" />
					</h3>
					<ul class="promotionsDismissalsActionPanel_list"></ul>
				</div>
				<div class="promotionsDismissalsActionPanel_line"></div>
				<div class="promotionsDismissalsActionPanel">
					<h3 class="promotionsDismissalsDestiny">
						<spring:message code="tree.treeDAD.dismissals" />
					</h3>
					<ul class="promotionsDismissalsActionPanel_list"></ul>
				</div>
			</div>
		</div>
		<div id="tasksPromotionsDismissalsUniqueControl"
			class="DADcontrolsMainCode PromotionsDismissalsMainCode">
			<button id="btnPromotionsDismissalsUniqueControl">
				<spring:message code="tree.verCodigo.distribucion" />
			</button>
		</div>
	</fieldset>

</section>
