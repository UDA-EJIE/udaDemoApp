<%@page import="com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoRecursoEnum" %>
<script type="text/javascript">
var tipoRecurso = {
			'interno' : '<%=TipoRecursoEnum.INTERNO.getValue()%>',
			'externo' : '<%=TipoRecursoEnum.EXTERNO.getValue()%>'
		};

var tipoTareaInterpretacion = <%=TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue()%>;
var tipoTareaRevision = <%=TipoTareaGestionAsociadaEnum.REVISAR.getValue()%>;
var tipoTareaEntregaClienteRev = <%=TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue()%>;
var tipoTareaRevisarTraduccionExterna = <%=TipoTareaGestionAsociadaEnum.REVISAR_TRADUCCION.getValue()%>;

var labelTituloAlt='<spring:message code="label.tituloAlt"/>';
var labelTipo='<spring:message code="label.documento.tipo"/>';

var visualizarObservaciones = false;
</script>

<script type="text/javascript" src="${staticsUrl}/tinymce/jquery.tinymce.min.js"></script>
<script type="text/javascript" src="${staticsUrl}/tinymce/tinymce.min.js"></script>

<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/cargatrabajodetalletarea.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/cabeceradetalletarea.js" type="text/javascript"></script>

