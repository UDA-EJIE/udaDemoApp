<%--  
 -- Copyright 2011 E.J.I.E., S.A.
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
<h2>Detalle usuario</h2> <!-- Titulo pagina -->

<div>

	<form:form id="formDetalleUsuario" modelAttribute="usuario" >
		
		<div><b>Nombre :</b> ${usuario.apellido1} ${usuario.apellido2},${usuario.nombre}</div><br/>
		<div><b>Rol :</b> ${usuario.rol}</div><br/>
		<div><b>Ejie :</b> ${usuario.ejie}</div><br/>
		<div><b>Fecha alta :</b> ${usuario.fechaAlta}</div><br/>
		<div><b>Fecha baja :</b> ${usuario.fechaBaja}</div><br/>
		
	</form:form>

</div>	

