<%@page import="com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoRecursoEnum" %>
<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<script type="text/javascript">
var tipoRecurso = {
			'interno' : '<%=TipoRecursoEnum.INTERNO.getValue()%>',
			'externo' : '<%=TipoRecursoEnum.EXTERNO.getValue()%>'
		};

var tipoTareaInterpretacion = <%=TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue()%>;
var tipoTareaRevision = <%=TipoTareaGestionAsociadaEnum.REVISAR.getValue()%>;
var tipoTareaEntregaClienteRev = <%=TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue()%>;
var tipoTareaRevisarTraduccionExterna = <%=TipoTareaGestionAsociadaEnum.REVISAR_TRADUCCION.getValue()%>;
var tareaEjecutada = <%=EstadoEjecucionTareaEnum.EJECUTADA.getValue()%>;
var labelTituloAlt='<spring:message code="label.tituloAlt"/>';
var labelTipo='<spring:message code="label.documento.tipo"/>';
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/cargatrabajodetalletareatrabajo.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/cabeceradetalletareatrabajo.js" type="text/javascript"></script>

