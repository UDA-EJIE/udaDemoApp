<%@include file="/WEB-INF/includeTemplate.inc"%>

<!-- Include de los elementos comunes -->
<script type="text/javascript">
	APP_RESOURCES = 'x21a',
	CTX_PATH = '<%= request.getContextPath()%>/',
	RUP = '${staticsUrl}/rup',
	STATICS = '${staticsUrl}',
	DEFAULT_LANGUAGE = "${defaultLanguage}",
	LAYOUT = "${defaultLayout}",
	WAR_NAME = "x21aPilotoPatrones",
	AVAILABLE_LANGS = "es, eu, en, fr";
</script>

<%@include file="/WEB-INF/layouts/includes/rup.scripts.inc"%>
<!--%@include file="/WEB-INF/views/includes/rup.scripts.min.js"%-->
<%@include file="/WEB-INF/layouts/includes/x21a.scripts.inc"%>