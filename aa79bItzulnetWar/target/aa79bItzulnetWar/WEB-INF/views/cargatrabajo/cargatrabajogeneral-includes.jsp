<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@ taglib prefix="sec" uri="/WEB-INF/tld/security.tld" %>
<%@page import="com.ejie.aa79b.model.enums.FaseExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoPeticionarioEnum" %>
<%@page import="com.ejie.aa79b.model.enums.ClasificacionDocumentosEnum" %>
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum" %>
<script src="${staticsUrl}/rup/js/externals/jquery.fn.gantt.js" type="text/javascript"></script>
<script src="${staticsUrl}/rup/js/externals/boot.js" type="text/javascript"></script>
<script type="text/javascript">
var dniUsuSesion = ${xlnetAuthenticationProvider.userCredentials.nif};
var esAsignador = false;
<sec:authorize access="hasRole('ROLE_AA79B_ASIGNADOR')">
esAsignador = true;
</sec:authorize>

var labelFichero='<spring:message code="label.fichero"/>';
var labelTipo='<spring:message code="label.documento.tipo"/>';
var labelNumPalIzo='<spring:message code="label.documento.numPalabrasIZO"/>';
var labelVisible='<spring:message code="label.documento.visible"/>';
var labelVer='<spring:message code="label.ver"/>';
var labelEliminar='<spring:message code="eliminar"/>';
var labelEncrip='<spring:message code="label.documento.encrip"/>';
var labelNoEncrip='<spring:message code="label.documento.noencrip"/>';
var labelCastellano='<spring:message code="label.castellano"/>';
var labelDescargar='<spring:message code="label.descargarFichero"/>';
var labelVersiones='<spring:message code="label.versionesAnteriores"/>';
var estadoAceptacion = {
		'value' : {
			'pdteAceptacion' : '<%=EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue()%>',
			'aceptada' : '<%=EstadoAceptacionTareaEnum.ACEPTADA.getValue()%>',
			'rechazada' : '<%=EstadoAceptacionTareaEnum.RECHAZADA.getValue()%>'
		},
		'label' : {
			'pdteAceptacion' : '<%=EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getLabel()%>',
			'aceptada' : '<%=EstadoAceptacionTareaEnum.ACEPTADA.getLabel()%>',
			'rechazada' : '<%=EstadoAceptacionTareaEnum.RECHAZADA.getLabel()%>'
		}
	};
	
var estadoEjecucion = {
		'value' : {
			'pdteEjecucion' : '<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()%>',
			'retrasada' : '<%=EstadoEjecucionTareaEnum.RETRASADA.getValue()%>',
			'ejecutada' : '<%=EstadoEjecucionTareaEnum.EJECUTADA.getValue()%>'
		},
		'label' : {
			'pdteEjecucion' : '<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel()%>',
			'retrasada' : '<%=EstadoEjecucionTareaEnum.RETRASADA.getLabel()%>',
			'ejecutada' : '<%=EstadoEjecucionTareaEnum.EJECUTADA.getLabel()%>'
		}
	};
	
var datosExp = {
		'tipoExp' : {
			'interpretacion' : '<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>',
			'traduccion' : '<%=TipoExpedienteEnum.TRADUCCION.getValue()%>',
			'revision' : '<%=TipoExpedienteEnum.REVISION.getValue()%>'
		},
		'tipoEntidad' : {
			'entidad' : '<%=TipoEntidadEnum.ENTIDAD.getValue()%>',
			'departamento' : '<%=TipoEntidadEnum.DEPARTAMENTO.getValue()%>',
			'empresa' : '<%=TipoEntidadEnum.EMPRESA.getValue()%>'
		},
		'tipoPeticionario' : {
			'administracionPublica' : '<%=TipoPeticionarioEnum.ADMIN_PUBLICA.getValue()%>',
			'particular' : '<%=TipoPeticionarioEnum.PARTICULAR.getValue()%>'
		}
};

var datosTareas = {
		'idTipoExp' : {
			'interpretacion' : '<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>',
			'traduccion' : '<%=TipoExpedienteEnum.TRADUCCION.getValue()%>',
			'revision' : '<%=TipoExpedienteEnum.REVISION.getValue()%>'
		},
		'estadoEjecucion': {
			'ejecutada' : '<%=EstadoEjecucionTareaEnum.EJECUTADA.getValue()%>'
		}
	};

	var datosDocumentos = {
		'clasificacion' : {
			'traduccion' : '<%=ClasificacionDocumentosEnum.TRADUCCION.getValue()%>',
			'revision' : '<%=ClasificacionDocumentosEnum.REVISION.getValue()%>',
			'referencia' : '<%=ClasificacionDocumentosEnum.APOYO.getValue()%>',
			'trabajo' : '<%=ClasificacionDocumentosEnum.TRABAJO.getValue()%>'
		}
	};
	
	var datos = {
		'activo' : {
			'si' : '<%=ActivoEnum.SI.getValue()%>',
			'no' : '<%=ActivoEnum.NO.getValue()%>'
		}	
	};
	
	var datosExpediente = {
		'estado' : {
			'cerrado' : '<%=EstadoExpedienteEnum.CERRADO.getValue()%>',
			'encurso' : '<%=EstadoExpedienteEnum.EN_CURSO.getValue()%>'
		},
		'fase' : {
			'cerrado' : '<%=FaseExpedienteEnum.CERRADO.getValue()%>',
			'pdteTram' : '<%=FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue()%>'
		}	
	};
	
var idiomaDestino;
var expedienteConfidencial;
var origen;
var tablaSelector = null;
var ordenTarea;
var fechaFinalIZO;
var horaFinalIZO;
var tipoExp;
var consultaPlanif = false;
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/confirmartarea.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/cargatrabajogeneral.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadorpersonas.js" type="text/javascript"></script>