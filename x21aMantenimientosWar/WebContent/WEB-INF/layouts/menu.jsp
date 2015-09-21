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

	<ul id="x21aMantenimientosWar_menu" class="rup_invisible_menu">
		<li>
			<a href="/x21aMantenimientosWar/../x21aPilotoPatronesWar/">
				<spring:message code="patrones" />
			</a>
		</li>
		<sec:authorize access="hasRole('ROLE_UDAANONYMOUS')">
			<li>
				<a class="ui-state-disabled" href="javascript:void(0)">
					<spring:message code="inicio" />
				</a>
			</li>
			<li>
				<a class="ui-state-disabled" href="javascript:void(0)">
					<spring:message code="simple" />
				</a>
			</li>
			<li>
				<a class="ui-state-disabled" href="javascript:void(0)">
					<spring:message code="multi" />
				</a>
			</li>
			<li>
				<a class="ui-state-disabled" href="javascript:void(0)">
					<spring:message code="edlinea" />
				</a>
			</li>
		</sec:authorize>
		
		<sec:authorize access="!hasRole('ROLE_UDAANONYMOUS')">
			<li>
			<a href="/x21aMantenimientosWar/">
				<spring:message code="inicio" />
				</a>
			</li>
			<li>
				<a href="/x21aMantenimientosWar/usuario/simple">
					<spring:message code="simple" />
				</a>
			</li>
			<li>
				<a href="/x21aMantenimientosWar/usuario/multi">
					<spring:message code="multi" />
				</a>
			</li>
			<li>
				<a href="/x21aMantenimientosWar/usuario/edlinea">
					<spring:message code="edlinea" />
				</a>
			</li>
		</sec:authorize>
		
		<sec:authorize access="hasRole('ROLE_ADMIN_UDA')">
		<li>
			<a>
				<spring:message code="administracion" />
			</a>
			<ul>
				<li>
					<a href="/x21aMantenimientosWar/administracion/alumno/maint">
						<spring:message code="Alumno" />
					</a>
				</li>
			</ul>
		</li>
		</sec:authorize>
		
		<sec:authorize access="!hasRole('ROLE_UDAANONYMOUS')">
		<li>
			<a>
				<spring:message code="jerarquia" />
			</a>
			<ul>
				<li>
					<a href="/x21aMantenimientosWar/usuariojerarquia/maint">
						<spring:message code="usuarioJerarquia" />
					</a>
				</li>
				<li>
					<a href="/x21aMantenimientosWar/usuariojerarquia/maintgroup">
						<spring:message code="usuarioJerarquiaGroup" />
					</a>
				</li>
				<li>
					<a href="/x21aMantenimientosWar/usuariojerarquia/maintmulti">
						<spring:message code="usuarioJerarquiaMulti" />
					</a>
				</li>
				<li>
					<a href="/x21aMantenimientosWar/usuariojerarquia/maintmultigroup">
						<spring:message code="usuarioJerarquiaGroupMulti" />
					</a>
				</li>
				<li>
					<a href="/x21aMantenimientosWar/usuariojerarquia/maintcol">
						<spring:message code="usuarioJerarquiaCol" />
					</a>
				</li>
			</ul>
		</li>
		</sec:authorize>
		
		<li>
			<a	href="http://code.google.com/p/uda/" target="_blank">
				<spring:message code="uda" />
				<span class="ui-icon uda_icon"></span>
			</a>
		</li>
	</ul>