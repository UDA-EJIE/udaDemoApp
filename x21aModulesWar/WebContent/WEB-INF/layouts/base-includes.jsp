<%--  
 -- Copyright 2012 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 -- 
 -- 	 http://ec.europa.eu/idabc/eupl.html
 -- 
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia. 
 --%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<!-- Include de los elementos comunes -->
<script type="text/javascript">
var	APP_RESOURCES = 'x21a',
	CTX_PATH = '<%= request.getContextPath()%>/',
	STATICS = '${staticsUrl}',
	RUP = '${staticsUrl}/rup',
	WAR_NAME = "x21aPilotoPatrones",
	//model
	LAYOUT = "${empty defaultLayout ?  mvcInterceptor.defaultLayout : defaultLayout}",
	//mvc-config.xml
	LOCALE_COOKIE_NAME = "${localeResolver.cookieName}",
	LOCALE_PARAM_NAME = "${mvcInterceptor.paramName}",
	AVAILABLE_LANGS = "${mvcInterceptor.availableLangs}",
	//breadCrumbs
	LOGGED_USER = "${udaAuthenticationProvider.userCredentials.fullName}",
	DESTROY_XLNETS_SESSION = "${udaAuthenticationProvider.userCredentials.destroySessionSecuritySystem}";
</script>

<%-- <script src="${staticsUrl}/frontend-app-1/node_modules/systemjs/dist/system.js"></script> --%>
<%-- <script src="${staticsUrl}/frontend-app-1/systemjs.config.js"></script> --%>

<%-- <script src="${staticsUrl}/dist/bundle.js"></script> - --%>
<script>
//  System.import('${staticsUrl}/app/main.js').catch(function(err){ console.error(err); });
</script> 