<%--  
 -- Copyright 2012 E.J.I.E., S.A.
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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ include file="/WEB-INF/includeTemplate.inc" %>
<%@ taglib prefix="tiles" uri="/WEB-INF/tld/tiles-jsp.tld" %>

	<!-- Datos asociados a las llamadas variables de mantenimientos -->
	<tiles:importAttribute name="maint" />
	<tiles:importAttribute name="maintName"/>
	
	<!-- Contenidos -->
	
	<%@include file="/WEB-INF/includeTemplate.inc"%>
	<h1>Selección simple</h1>
	
	<div id="error_${maintName}" style="display:none"></div>
	<div id="simple_${maintName}">
	
		<div id="contenido_${maintName}" style="margin-top:0.5em;margin-bottom:0.5em;width:600px;">
			<form id="searchForm_${maintName}">
				<div  class="formulario_legend" id="titleSearch_simple_${maintName}"><spring:message code="searchCriteria" />:</div>
				<fieldset style="border:1px solid #DADADA;" id="FIELDSET_SEARCH_simple_${maintName}">
					<div class="formulario_columna_cnt">
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">id:</div>
							<input type="text" name="id" class="formulario_linea_input" id="id_search_${maintName}" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">nombre:</div>
							<input type="text" name="nombre" class="formulario_linea_input" id="nombre_search_${maintName}" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">apellido1:</div>
							<input type="text" name="apellido1" class="formulario_linea_input" id="apellido1_search_${maintName}" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">apellido2:</div>
							<input type="text" name="apellido2" class="formulario_linea_input" id="apellido2_search_${maintName}" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">ejie:</div>
							<div style="float: left;"><select name="ejie" id="ejie_search_${maintName}" class="rup-combo" ></select></div>
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">fechaAlta:</div>
							<input type="text" name="fechaAlta" class="formulario_linea_input" id="fechaAlta_search_${maintName}" />
						</div>
						<div class="formulario_linea_izda_float" style="clear: left;">
							<div class="formulario_linea_label">fechaBaja:</div>
							<input type="text" name="fechaBaja" class="formulario_linea_input" id="fechaBaja_search_${maintName}" />
						</div>
					</div>
				</fieldset>
			</form>
		</div>

	
		<div id="RUP_${maintName}">
			<!-- Tabla -->
			<table id="${maintName}" cellpadding="0" cellspacing="0"></table>
			<!-- Barra de paginación -->
			<div id="pager_${maintName}" style="text-align:center;"></div>
		</div>
	</div>
	
	<!-- Includes JS -->
	<script src="${staticsUrl}/x21a/scripts/x21aMantenimientos/${maint}" type="text/javascript"></script>
