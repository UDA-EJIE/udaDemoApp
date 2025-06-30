 
<%@include file="/WEB-INF/includeTemplate.inc"%>

<!-- Include de los elementos comunes -->
<script type="text/javascript">
	APP_RESOURCES = 'aa79b',
	CTX_PATH = '<%= request.getContextPath()%>/',
	STATICS = '${staticsUrl}',
	RUP = '${staticsUrl}/rup',
	WAR_NAME = 'aa79bItzulnet',
	//model
	LAYOUT = '${empty defaultLayout ?  mvcInterceptor.defaultLayout : defaultLayout}',
	//mvc-config.xml
	LOCALE_COOKIE_NAME = '${localeResolver.cookieName}',
	LOCALE_PARAM_NAME = '${mvcInterceptor.paramName}',
	AVAILABLE_LANGS = '${mvcInterceptor.availableLangs}',
	//breadCrumbs
	LOGGED_USER = '${udaAuthenticationProvider.userCredentials.fullName}',
	DESTROY_XLNETS_SESSION = '${!empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal ? sessionScope.SPRING_SECURITY_CONTEXT.authentication.credentials.destroySessionSecuritySystem : sessionScope.destroySessionSecuritySystem}',
	//NORA
	SERVIDOR_NORA="${appConfiguration['SERVIDORNORA']}",
	RUP_ADAPTERS = {
		date_jqueryui: "DateJQueryUIAdapter",
		date_bootstrap: "DateBootstrapAdapter",
		time_jqueryui: "TimeJQueryUIAdapter",
		time_bootstrap: "TimeBootstrapAdapter",
    	upload: "UploadBootstrapAdapter",
        button: "ButtonBootstrapAdapter",
        toolbar_jqueryui: "ToolbarJQueryUIAdapter",
        toolbar_bootstrap: "ToolbarBootstrapAdapter",
        table_jqueryui: "TableJQueryUIAdapter",
        table_bootstrap: "TableBootstrapAdapter",
        validate_jqueryui: "ValidateJQueryUIAdapter",
        validate_bootstrap: "ValidateBootstrapAdapter"
    };
</script>

<%-- Scripts RUP sin minimizar (DESARROLLO) --%>
<%-- <%@include file="/WEB-INF/layouts/includes/rup.scripts.inc"%> --%>
<%-- Scripts RUP minimizados (PRODUCCION) --%>
<%@include file="/WEB-INF/layouts/includes/rup.scripts.min.inc"%>

<%@include file="/WEB-INF/layouts/includes/aa79b.scripts.inc"%>