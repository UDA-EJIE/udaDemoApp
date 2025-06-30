<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@ taglib prefix="sec" uri="/WEB-INF/tld/security.tld" %>

<script type="text/javascript">
	var txtindteletrabajo = '<spring:message code="label.teletrabajador"/>';
	var txtObsvHorario = '<spring:message code="label.obsvHorario"/>';
	var txtHorasGestion = '<spring:message code="label.horasGestion"/>';
	var txtFechaDesde= '<spring:message code="label.fechaDesde"/>';
	var txtFechaHasta= '<spring:message code="label.fechaHasta"/>';
	var txtTipoJornada= '<spring:message code="label.tipoJornada"/>';
	var txtObsvDia= '<spring:message code="label.obsvDia"/>';
	var horasGestionExp= '<%=Constants.DEFAULT_HORAS_GESTION_EXP_CLA_PERSONAL%>';
	var esTecnico = false;
	<sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
	esTecnico = true;
	</sec:authorize>
	var dniUsuSesion = ${xlnetAuthenticationProvider.userCredentials.nif};
	var nombreUsuSesion = '${xlnetAuthenticationProvider.userCredentials.name} ${xlnetAuthenticationProvider.userCredentials.surname}';
</script>

<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/calendariopersonal.js" type="text/javascript"></script>