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

<%@include file="/WEB-INF/includeTemplate.inc"%>

<h2 class="title mb-3"><spring:message code="tab.ajaxLoad" /></h2>
<div id="tabs"></div>

<div id="testButtons" style="padding-top: 0.8em">
	<button title="<spring:message code="tab.changeUrlTab.tooltip" />" id="changeUrlTab" class="btn-material btn-material-primary-high-emphasis m-2"><spring:message code="tab.changeUrlTab" /></button>
	<button title="<spring:message code="tab.AddTab.tooltip" />" id="addSubPestana" class="btn-material btn-material-primary-high-emphasis m-2"><spring:message code="tab.AddTab" /></button>
	<button title="<spring:message code="tab.AddSubTab.tooltip" />" id="addSubLevelPestana" class="btn-material btn-material-primary-high-emphasis m-2"><spring:message code="tab.AddSubTab" /></button>
	<button title="<spring:message code="tab.AddSubTabPlusTab.tooltip" />" id="addSubLevelPestanaMasPestaña" class="btn-material btn-material-primary-high-emphasis m-2"><spring:message code="tab.AddSubTabPlusTab" /></button>
	<button title="<spring:message code="tab.mainTab.tooltip" />" id="addSubLevelPestana3Maint" class="btn-material btn-material-primary-high-emphasis m-2"><spring:message code="tab.mainTab" /></button>
</div>

<div class="estiloo" style="display: none;">

	<h1>Pestañas de navegación</h1>
	<p id="docu">
		<spring:url value="/" var="urlHashtag" htmlEscape="true"/>
		<a target="_blank" href="${urlHashtag}">Descargar documentación</a>
	</p>
	<br>

	<h2>Descripción</h2>
	<p>Menú de navegación de la aplicación que divide ésta en secciones
		claramente excluyentes entre sí y da acceso a ellas.</p>
	<h2>Usar cuando</h2>
	<p>Cuando tengamos una aplicación web cuyos contenidos se puedan
		dividir en secciones claramente excluyentes entre sí y queramos
		proporcionar a los usuarios un menú de navegación para navegar por
		ellas.</p>

	<h2>Accesibilidad</h2>
	<p>
		Es muy importante que los contenidos están estructurados y
		jerarquizados correctamente en función de los modelos mentales de los
		usuarios y que los rótulos sean sencillos de comprender. Cuanto mís
		lógica, sencilla y fácil de comprender sea la navegación mís accesible
		será, especialmente para los usuarios con problemas cognitivos o
		sordera prelocutiva ya que estos perfiles de accesibilidad pueden
		tener problemas a la hora de comprender estructuras de información
		complejas o terminología muy específica.<br> <br>Los menús
		de navegación basados en pestañas, si están bien implementados, pueden
		suponer una mejora de accesibilidad notable ya que facilitan mucho la
		comprensión de la estructura de la aplicación y del funcionamiento del
		sistema de navegación.
	</p>
	<p>
		<em>Nivel de accesibilidad alcanzado por cada tecnología</em>:
	</p>
	<ul>
		<li style="margin-left: 25px; margin-top: 20px;"><em>Geremua
				2:</em> El componente ha sido implementado con jQuery, con lo que al
			ejecutar javascript no cumple las normas de accesibilidad.</li>
		<li style="margin-left: 25px;"><em>.NET:</em> El componente ha
			sido implementado con jQuery, con lo que al ejecutar javascript no
			cumple las normas de accesibilidad.</li>
	</ul>
	<h2>Patrón relacionado con</h2>
	<ul>
		<li style="margin-left: 25px; margin-top: 20px;">2.1.1.
			Organización y jerarquía de pantalla</li>
		<li style="margin-left: 25px;">2.2.1. Menús de navegación</li>
	</ul>
	<form:form class="form-horizontal" method="post" modelAttribute="alumno">
						<div class="form-groupMaterial col-md-3 col-sm-6 col-xs-12">
						<form:input id="t06FechaEntradaPaciente" path="fechaNacimiento2" type="text" />
						<label for="t06FechaEntradaPaciente"><spring:message code="fecha" /><span class="text-muted" id="fecha-mask"></span></label>
						</div>
	</form:form>

</div>