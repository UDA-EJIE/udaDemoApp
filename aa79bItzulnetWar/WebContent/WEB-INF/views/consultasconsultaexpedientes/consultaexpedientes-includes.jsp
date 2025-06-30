<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<link href="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/css/dynamic_formjQuery.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/sidl-dynamic-form-jquery.js"></script>	
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/jquery/jquery-geoEuskadi.js"></script>
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/jquery/jquery-ui-1.8.18.custom.min.js"></script>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum" %>
<%@page import="com.ejie.aa79b.model.enums.EstadoExpedienteEnum" %>
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum" %>


<script type="text/javascript">
var consExp = {
		'estadoExpediente' : {
			'alta' : '<%=EstadoExpedienteEnum.ALTA_EXPEDIENTE.getValue()%>',
			'enEstudio' : '<%=EstadoExpedienteEnum.EN_ESTUDIO.getValue()%>',
			'enCurso' : '<%=EstadoExpedienteEnum.EN_CURSO.getValue()%>',
			'rechazado' : '<%=EstadoExpedienteEnum.RECHAZADO.getValue()%>',
			'anulado' : '<%=EstadoExpedienteEnum.ANULADO.getValue()%>',
			'cerrado' : '<%=EstadoExpedienteEnum.CERRADO.getValue()%>',
			'finalizado' : '<%=EstadoExpedienteEnum.FINALIZADO.getValue()%>'
		},
		'tipoExpediente' : {
			'traduccion' : '<%=TipoExpedienteEnum.TRADUCCION.getValue()%>',
			'revision' : '<%=TipoExpedienteEnum.REVISION.getValue()%>',
			'interpretacion' : '<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>',
			'tradrev' : '<%=TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getCode()%>'
		},
		'activo' : {
			'si' : '<%=ActivoEnum.SI.getValue()%>',
			'no' : '<%=ActivoEnum.NO.getValue()%>',
			'todos' : ''
		}
};
</script>
<script type="text/javascript">
var esTraductor = false;
var esTecnicoIzo = false;
var idiomaDestino;
var expedienteConfidencial;
var tablaSelector = null;
var fechaFinalIZO;
var horaFinalIZO;
var idTipoTarea;

<sec:authorize access="hasRole('ROLE_AA79B_TRADUCTOR')">
esTraductor = true;
</sec:authorize>
<sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
esTecnicoIzo = true;
</sec:authorize>

</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/consultaexpedientes.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadorexpedientesrelacionados.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadoretiquetas.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/utils/aa79bUtils.js" type="text/javascript"></script>