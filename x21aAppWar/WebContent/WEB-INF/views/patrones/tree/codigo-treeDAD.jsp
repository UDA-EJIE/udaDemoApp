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

<div id="treeCodeDialog" style="display:none"></div>

<div id="AccordionCode" class="treeAccordionCode">

	<div id="reorderAccordionCode" class="rup_accordion treeAccordionCodeObject">
	
		<h1><a><spring:message  code="tree.treeDAD.code.htmlCode" /></a></h1>
		<div class="treeAccordionCodeSecction">
			<code>
				&lt;!-- Árbol con ordenación --&gt;<br/>
				&lt;div id="tasksReorderTree"&gt;&lt;/div&gt;<br/>
			</code>
		</div>
		<h1><a><spring:message  code="tree.treeDAD.code.jsTreeCode" /></a></h1>
		<div class="treeAccordionCodeSecction">
			<code>
				/* Ejemplo de arbol de tareas pendientes con reordenacion */<br/>
				$("#tasksReorderTree").rup_tree({<br/>
				&nbsp;&nbsp;&nbsp;"json_data" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"data": tasksTreeJson<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;"types" : tasksTreeTypes,<br/>
				&nbsp;&nbsp;&nbsp;"crrm" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"move" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"check_move" : function (moveObject){<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;var moveObjectParent = this._get_parent(moveObject.o);<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if(!moveObjectParent) return false;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;moveObjectParent = moveObjectParent == -1 ? this.get_container() : moveObjectParent;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if(moveObjectParent === moveObject.np) return true;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if(moveObjectParent[0] && moveObject.np[0] && moveObjectParent[0] === moveObject.np[0]) return true;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return false;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;"dnd" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"enable" : true,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"drop_target" : false,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"drag_target" : false<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				});<br/>
			</code>
		</div>
		<h1><a><spring:message  code="tree.treeDAD.code.jsTreeData" /></a></h1>
		<div class="treeAccordionCodeSecction">
			<code>
				/* Codigo JSon del árbol de tareas */<br/>
				var tasksTreeJson = [<br/>
				&nbsp;&nbsp;&nbsp;{"data" : <br/>{ 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"raiz")<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;"attr" : { "id" : "raiz", "rel" : "tasks"},<br/>
				&nbsp;&nbsp;&nbsp;"state" : "open",<br/>
				&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"informes")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "informes", "rel" : "forms"},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"state" : "open",<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"informe_trimestral")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "informe_trimestral", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"informe_gastos_generales")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "informe_trimestral", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"informe_desperfectos")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "informe_trimestral", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"informe_deudas")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "informe_trimestral", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"informe_calidad")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "informe_trimestral", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"informe_perdidas")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "informe_trimestral", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;]},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"transportes")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "transportes", "rel" : "Transportation"},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"state" : "open",<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"pedido_lujua")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "pedido_lujua", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"pedido_cemento")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "pedido_cemento", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"pedido_martillo")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "pedido_martillo", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;]},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"reparaciones")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "reparaciones", "rel" : "repair"},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"state" : "open",<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"reparacion_fachada")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "reparacion_fachada", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"reparacion_indundacion")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "reparacion_indundacion", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"reparacion_suelo")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "reparacion_suelo", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"reparacion_ratas")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "reparacion_ratas", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>         
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;]},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"suministros")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "suministros", "rel" : "suppliers"},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"state" : "open",<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"mp_ladrillos")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "mp_ladrillos", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"mp_clavos")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "mp_clavos", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"mp_cemento")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "mp_cemento", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"mp_recambios")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "mp_recambios", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"mp_jabon")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "mp_jabon", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"mp_botas")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "mp_botas", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"mp_cascos")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "mp_cascos", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>          
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;]},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"facturas")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "facturas", "rel" : "invoice"},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"state" : "open",<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"factura_VH")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "factura_VH", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"factura_Lindo")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "factura_Lindo", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"factura_devolucion")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "factura_devolucion", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"factura_OM")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "factura_OM", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;]}<br/>
				&nbsp;&nbsp;&nbsp;]},<br/>
				];<br/>
			</code>
		</div>
		<h1><a><spring:message  code="tree.treeDAD.code.jsTypesCode" /></a></h1>
		<div class="treeAccordionCodeSecction">
			<code>
				/* Tipos para la gestión de tareas */<br/>
				var tasksTreeTypes = {<br/>
				&nbsp;&nbsp;&nbsp;"valid_children" : ["tasks"],<br/>
				&nbsp;&nbsp;&nbsp;"types" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"tasks" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/PendingWorks.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["forms", "invoice", "repair", "suppliers", "Transportation"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"forms" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/forms.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["job"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"invoice" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/invoice.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["job"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"repair" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/repair.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["job"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"job" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/job.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["none"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"suppliers" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/suppliers.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["job"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"Transportation" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/Transportation.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["job"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				};<br/>
			</code>
		</div>
	</div>
	
	<div id="exchangeAccordionCode" class="rup_accordion treeAccordionCodeObject">
	
		<h1><a><spring:message  code="tree.treeDAD.code.htmlCode" /></a></h1>
		<div class="treeAccordionCodeSecction">
			<code>
				&lt;!-- Árboles con intercambio --&gt;<br/>
				&lt;legend class="treeNodesExchangePanel_legend"&gt;&lt;spring:message code="tree.treeDAD.exchange" /&gt;&lt;/legend&gt;<br/>
				&lt;div class="treeNodesExchangePanel_left"&gt;<br/>
				&nbsp;&nbsp;&nbsp;&lt;div id="tasksTree" class="ExchangePanel_tree"&gt;&lt;/div&gt;<br/>
				&lt;/div&gt;<br/>
				&lt;div class="treeNodesExchangePanel_center"&gt;<br/>
				&nbsp;&nbsp;&nbsp;&lt;div class="treeNodesExchangePanel_center_line"&gt;&lt;/div&gt;<br/>
				&nbsp;&nbsp;&nbsp;&lt;img src="${staticsUrl}/x21a/images/twoWayArrows.png" class="treeNodesExchangePanel_center_img" alt="&lt;spring:message code="tree.treeDAD.Interchange" /&gt;"/&gt;<br/>
				&lt;/div&gt;<br/>
				&lt;div class="treeNodesExchangePanel_right"&gt;<br/>
				&nbsp;&nbsp;&nbsp;&lt;div id="workersTree" class="ExchangePanel_tree"&gt;&lt;/div&gt;<br/>
				&lt;/div&gt;<br/>	
			</code>
		</div>
		<h1><a><spring:message  code="tree.treeDAD.code.jsTreeCode" /></a></h1>
		<div class="treeAccordionCodeSecction">
			<code>
				/* Ejemplo de arboles con distribucion de tareas (D&D) */<br/>
				$("#tasksTree").rup_tree({<br/>
				&nbsp;&nbsp;&nbsp;"json_data" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"data": tasksTreeJson<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;"crrm" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"move" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"check_move" : function (moveObject) {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return false;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;"types" : tasksTreeTypes,<br/>
				&nbsp;&nbsp;&nbsp;"dnd" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"enable" : true,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"drop_target" : false,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"drag_target" : false<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				});<br/>	
				<br/>
				$("#workersTree").rup_tree({<br/>
				&nbsp;&nbsp;&nbsp;"json_data" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"data": workersTreeJson<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;"crrm" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"move" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"check_move" : function (moveObject) {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;var moveObjectParent = moveObject.op;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if(moveObject.op.parents("#tasksTree").length > 0){<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return true;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;} else {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return false;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;"types" : workersTreeTypes,<br/>
				&nbsp;&nbsp;&nbsp;"dnd" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"enable" : true,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"drop_target" : false,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"drag_target" : false<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				});<br/>
				<br/>	
				/* Gestion del panel visual */<br/>
				var treeNodesExchangePanelPosition = $("#treeNodesExchangePanel").position();<br/>
				$(".treeNodesExchangePanel_center_img").css("top",treeNodesExchangePanelPosition.top+(($("#treeNodesExchangePanel").height())/2)-(($(".treeNodesExchangePanel_center_img").height())/2))<br/>
				&nbsp;&nbsp;&nbsp;.css("left",treeNodesExchangePanelPosition.left+(($("#treeNodesExchangePanel").width())/2)-(($(".treeNodesExchangePanel_center_img").width())/2));<br/>
				$(".treeNodesExchangePanel_center").css("visibility", "visible");<br/>
				$(".treeNodesExchangePanel_center_img").bind("click", function(){<br/>
				&nbsp;&nbsp;&nbsp;$.rup_messages("msgError", {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;title: $.rup.i18nParse($.rup.i18n.app["treeNodesExchangePanel_center"],"errorOp"),<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;message: $.rup.i18nParse($.rup.i18n.app["treeNodesExchangePanel_center"],"funtError")<br/>
				&nbsp;&nbsp;&nbsp;});<br/>
				});<br/>
			</code>
		</div>
		<h1><a><spring:message  code="tree.treeDAD.code.jsTreeData" /></a></h1>
		<div class="treeAccordionCodeSecction">
			<code>
				/* Codigo JSon del árbol de tareas */<br/>
				var tasksTreeJson = [<br/>
				&nbsp;&nbsp;&nbsp;{"data" : <br/>{ 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"raiz")<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;"attr" : { "id" : "raiz", "rel" : "tasks"},<br/>
				&nbsp;&nbsp;&nbsp;"state" : "open",<br/>
				&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"informes")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "informes", "rel" : "forms"},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"state" : "open",<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"informe_trimestral")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "informe_trimestral", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"informe_gastos_generales")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "informe_trimestral", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"informe_desperfectos")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "informe_trimestral", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"informe_deudas")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "informe_trimestral", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"informe_calidad")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "informe_trimestral", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"informe_perdidas")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "informe_trimestral", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;]},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"transportes")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "transportes", "rel" : "Transportation"},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"state" : "open",<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"pedido_lujua")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "pedido_lujua", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"pedido_cemento")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "pedido_cemento", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"pedido_martillo")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "pedido_martillo", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;]},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"reparaciones")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "reparaciones", "rel" : "repair"},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"state" : "open",<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"reparacion_fachada")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "reparacion_fachada", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"reparacion_indundacion")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "reparacion_indundacion", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"reparacion_suelo")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "reparacion_suelo", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"reparacion_ratas")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "reparacion_ratas", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>         
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;]},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"suministros")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "suministros", "rel" : "suppliers"},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"state" : "open",<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"mp_ladrillos")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "mp_ladrillos", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"mp_clavos")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "mp_clavos", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"mp_cemento")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "mp_cemento", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"mp_recambios")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "mp_recambios", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"mp_jabon")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "mp_jabon", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"mp_botas")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "mp_botas", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"mp_cascos")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "mp_cascos", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>          
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;]},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"facturas")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "facturas", "rel" : "invoice"},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"state" : "open",<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"factura_VH")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "factura_VH", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"factura_Lindo")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "factura_Lindo", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"factura_devolucion")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "factura_devolucion", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"factura_OM")<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : { "id" : "factura_OM", "rel" : "job"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;]}<br/>
				&nbsp;&nbsp;&nbsp;]},<br/>
				];<br/>
				<br/>
				<br/>
				/* Codigo JSon del árbol de trabajadores */<br/>
				var workersTreeJson = [<br/>
			   	&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"workers")<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;"attr" : { "id" : "raiz", "rel" : "workers"},<br/>
				&nbsp;&nbsp;&nbsp;"state" : "open",<br/>
				&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Abanca Rodrigez Silvia"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Almonzon Mendia Juan"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Alonso Ruiz Laura"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Gil Sandia Marta"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Jiménez Arriurtua Francisco"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Mantenimientos Jet"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "enterprise"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Matias S.L."<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "enterprise"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Montajes Loiu"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "enterprise"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Mortaro Filon Sara"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Ortiz Dulon Jose"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Padilla Alcantara Sergio"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Puertas y molduras Sanz"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "enterprise"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Randal Sweder Moly"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Ruiz de Santiesteban Pedro"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Sánchez Rodin Pablo"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;]},<br/>
				];<br/>
			</code>
		</div>
		<h1><a><spring:message  code="tree.treeDAD.code.jsTypesCode" /></a></h1>
		<div class="treeAccordionCodeSecction">
			<code>
				/* Tipos para la gestión de tareas */<br/>
				var tasksTreeTypes = {<br/>
				&nbsp;&nbsp;&nbsp;"valid_children" : ["tasks"],<br/>
				&nbsp;&nbsp;&nbsp;"types" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"tasks" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/PendingWorks.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["forms", "invoice", "repair", "suppliers", "Transportation"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"forms" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/forms.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["job"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"invoice" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/invoice.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["job"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"repair" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/repair.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["job"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"job" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/job.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["none"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"suppliers" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/suppliers.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["job"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"Transportation" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/Transportation.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["job"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				};<br/>
				<br/>
				<br/>
				/* Tipos para la gestion de trabajadores */<br/>
				var workersTreeTypes = {<br/>
				&nbsp;&nbsp;&nbsp;"valid_children" : ["workers"],<br/>
				&nbsp;&nbsp;&nbsp;"types" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"enterprise" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/enterprise.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["job"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"worker" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/worker.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["job"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"workers" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/workers.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["worker", "enterprise"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"job" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/job.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["none"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				};<br/>
			</code>
		</div>
	</div>
	
	<div id="promotionsDismissalsAccordionCode" class="rup_accordion treeAccordionCodeObject">
	
		<h1><a><spring:message  code="tree.treeDAD.code.htmlCode" /></a></h1>
		<div class="treeAccordionCodeSecction">
			<div class="">
				<code>
					&lt;!-- Árbol con Drag&Drop a paneles --&gt;<br/>
					&lt;div class="promotionsDismissalsExchangePanel"&gt;<br/>
					&lt;div class="promotionsDismissalsExchangePanel_left"&gt;<br/>
					&nbsp;&nbsp;&nbsp;&lt;div id="promotionsDismissalsTree" class="promotionsDismissals_tree"&gt;&lt;/div&gt;<br/>
					&lt;/div&gt;<br/>
					<br/>
					&lt;div class="promotionsDismissalsExchangePanel_center"&gt;<br/>
					&nbsp;&nbsp;&nbsp;&lt;div class="promotionsDismissalsExchangePanel_center_line"&gt;&lt;/div&gt;<br/>
					&nbsp;&nbsp;&nbsp;&lt;div class="promotionsDismissalsExchangePanel_center_img_div"&gt;<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;img src="${staticsUrl}/x21a/images/rightArrow.png" class="promotionsDismissalsExchangePanel_center_img" alt="&lt;spring:message code="tree.treeDAD.rightDirec" /&gt;"/&gt;<br/>
					&nbsp;&nbsp;&nbsp;&lt;/div&gt;<br/>
					&lt;/div&gt;<br/>
					<br/>
					&lt;div class="promotionsDismissalsExchangePanel_right"&gt;<br/>
					&nbsp;&nbsp;&nbsp;&lt;div class="promotionsDismissalsActionPanel"&gt;<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;h3 class="promotionsDismissalsDestiny"&gt;&lt;spring:message code="tree.treeDAD.promotions" /&gt;&lt;/h3&gt;<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;ul class="promotionsDismissalsActionPanel_list"&gt;&lt;/ul&gt;<br/>
					&nbsp;&nbsp;&nbsp;&lt;/div&gt;<br/>
					&nbsp;&nbsp;&nbsp;&lt;div class="promotionsDismissalsActionPanel_line"&gt;&lt;/div&gt;<br/>
					&nbsp;&nbsp;&nbsp;&lt;div class="promotionsDismissalsActionPanel promotionsDismissalsActionPanelCenter"&gt;<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;h3 class="promotionsDismissalsDestiny"&gt;&lt;spring:message code="tree.treeDAD.formation" /&gt;&lt;/h3&gt;<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;ul class="promotionsDismissalsActionPanel_list"&gt;&lt;/ul&gt;<br/>
					&nbsp;&nbsp;&nbsp;&lt;/div&gt;<br/>
					&nbsp;&nbsp;&nbsp;&lt;div class="promotionsDismissalsActionPanel_line"&gt;&lt;/div&gt;<br/>
					&nbsp;&nbsp;&nbsp;&lt;div class="promotionsDismissalsActionPanel"&gt;<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;h3 class="promotionsDismissalsDestiny"&gt;&lt;spring:message code="tree.treeDAD.dismissals" /&gt;&lt;/h3&gt;<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;ul class="promotionsDismissalsActionPanel_list"&gt;&lt;/ul&gt;<br/>
					&nbsp;&nbsp;&nbsp;&lt;/div&gt;<br/>
					&lt;/div&gt;<br/>
				</code>
			</div>
		</div>
		<h1><a><spring:message  code="tree.treeDAD.code.jsTreeCode" /></a></h1>
		<div class="treeAccordionCodeSecction">
			<code>
				/* Árbol y código asociado a la gestión de ascensos y despidos */<br/>
				$("#promotionsDismissalsTree").rup_tree({<br/>
				&nbsp;&nbsp;&nbsp;"json_data" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"data": workersDepartmentTreeJson<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;"types" : workersTreeTypes,<br/>
				&nbsp;&nbsp;&nbsp;"crrm" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"move" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"check_move" : function (moveObject) {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;var moveObjectParent = this._get_parent(moveObject.o);<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if(!moveObjectParent) return false;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;moveObjectParent = moveObjectParent == -1 ? this.get_container() : moveObjectParent;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if(moveObjectParent === moveObject.np) return true;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if(moveObjectParent[0] && moveObject.np[0] && moveObjectParent[0] === moveObject.np[0]) return true;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return false;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;"sort" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"enable" : true<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;"dnd" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"drop_check" : function (data) {<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if((!data.r.hasClass("promotionsDismissalsActionPanel_list"))||(data.o.attr("rel") !== "worker")) {<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return false;<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return {<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;after : false,<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;before : false,<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;inside : true<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;};<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"drop_finish" : function (data) {<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;data.r.append(data.o.clone());<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"enable" : true,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"drag_target" : false,<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"drop_target" : ".promotionsDismissalsActionPanel_list"<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				});<br/>
			</code>
		</div>
		<h1><a><spring:message  code="tree.treeDAD.code.jsTreeData" /></a></h1>
		<div class="treeAccordionCodeSecction">
			<code>
				/* Codigo JSon del árbol de trabajadores */<br/>
				var workersDepartmentTreeJson = [<br/>
			   	&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"depAdmin")<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;"attr" : { "id" : "department", "rel" : "workers"},<br/>
				&nbsp;&nbsp;&nbsp;"state" : "open",<br/>
				&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Abanca Rodrigez Silvia"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Alonso Ruiz Laura"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Gil Sandia Marta"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Jiménez Arriurtua Francisco"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Padilla Alcantara Sergio"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Sánchez Rodin Pablo"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;]},<br/>
				&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"depClientes")<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
			    &nbsp;&nbsp;&nbsp;"attr" : { "id" : "department", "rel" : "workers"},<br/>
				&nbsp;&nbsp;&nbsp;"state" : "close",<br/>
				&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Alzola Urierate Leticia"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Aranguren Loinaz Jose"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Bermejo Solo Ana"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Garzon Alonso Luis"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Jerez Templado Bisball"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Noiz Sapuerta Gordi"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>          
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Ortiz Dulon Jose"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Prudencio Fratan Armand"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Ruiz de Santiesteban Pedro"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;]},<br/>
				&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"depReparaciones")<br/>
				&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;"attr" : { "id" : "department", "rel" : "workers"},<br/>
				&nbsp;&nbsp;&nbsp;"state" : "close",<br/>
				&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Anemo Muñoz Jon"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Almonzon Mendia Juan"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Bornaz Satrustegui Armando"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Muleto Delito Afelio"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Gaztedi Jobar Maria"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Zarate Oligarco Ramon"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
			  	&nbsp;&nbsp;&nbsp;]},<br/>
				&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : $.rup.i18nParse($.rup.i18n.app["tasksTree"],"depSuministros")<br/>
			    &nbsp;&nbsp;&nbsp;},<br/>
			    &nbsp;&nbsp;&nbsp;"attr" : { "id" : "department", "rel" : "workers"},<br/>
				&nbsp;&nbsp;&nbsp;"state" : "close",<br/>
				&nbsp;&nbsp;&nbsp;"children" : [<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Eredia Puyol Maider"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Montero Rucio Luis"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
			  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Perez Mendia Jone"<br/>
			  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{"data" : {<br/> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title" : "Puertas y molduras Sanz"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"attr" : {"rel" : "worker"}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
			    &nbsp;&nbsp;&nbsp;]}<br/>
				];<br/>
			</code>
		</div>
		<h1><a><spring:message  code="tree.treeDAD.code.jsTypesCode" /></a></h1>
		<div class="treeAccordionCodeSecction">
			<code>
				/* Tipos para la gestion de trabajadores */<br/>
				var workersTreeTypes = {<br/>
				&nbsp;&nbsp;&nbsp;"valid_children" : ["workers"],<br/>
				&nbsp;&nbsp;&nbsp;"types" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"enterprise" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/enterprise.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["job"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"worker" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/worker.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["job"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"workers" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/workers.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["worker", "enterprise"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"job" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"icon" : {<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"image" : $.rup.STATICS+"/x21a/images/job.png"<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"valid_children" : ["none"]<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
				&nbsp;&nbsp;&nbsp;}<br/>
				};<br/>
			</code>
		</div>
	</div>
	
</div>
