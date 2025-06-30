<%@page import="com.ejie.aa79b.model.enums.ClasificacionDocumentosEnum"%>
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<link href="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/css/dynamic_formjQuery.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/sidl-dynamic-form-jquery.js"></script>	
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/jquery/jquery-geoEuskadi.js"></script>
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/jquery/jquery-ui-1.8.18.custom.min.js"></script>
<%@page import="com.ejie.aa79b.model.enums.EstadoGestorEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum" %>
<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoRecursoEnum" %>
<%@page import="com.ejie.aa79b.model.enums.EstadoExpedienteEnum" %>
<%@page import="com.ejie.aa79b.model.enums.FaseExpedienteEnum" %>
<%@page import="com.ejie.aa79b.model.enums.ModoDetalleExpedienteEnum" %>

<script>
	var idExpediente = '';
	if('${numExp}'!==undefined && '${numExp}'!==null && '${numExp}'!==''){
		idExpediente='${numExp}';
	}
	var anyoExpediente = '';
	if('${anyo}'!==undefined && '${anyo}'!==null && '${anyo}'!==''){
		anyoExpediente='${anyo}';
	}
	var modoDetalle = "";
	if('${modoDetalle}'!==undefined && '${modoDetalle}' !== null && '${modoDetalle}' !==''){
		modoDetalle = '${modoDetalle}';
	}
	var origen = 'A';
	var detalleSub = false;
	var subrayado = false;
	var datosFormulario = '';
	var datosFormularioDoc = '';
	var comprobarFormularios = false;
	var comprobarFormularioDoc = false;
	var omitirComprobacionFormularios = false;
	var visualizarObservaciones = false;
	
	var txtid = '<spring:message code="label.id"/>';
	var txtSolicitante = '<spring:message code="label.solicitante"/>';
	var txtDni = '<spring:message code="label.dni"/>';
	var txtTipoEntidad = '<spring:message code="label.tipoEntidad"/>';
	var txtEntidadAsoc = '<spring:message code="label.entidadAsociada"/>';	
	var txtContacto = '<spring:message code="label.contacto"/>';
	var txtPorcFact = '<spring:message code="label.porcentajeFactAplica"/>';
	var titXEntidad = '<spring:message code="label.entidadFactura"/>';
	var txtXSolicitante = '<spring:message code="label.solicEntidadFactura"/>';
	var txtFacturable = '<spring:message code="label.entidadFacturable"/>';
	var txtIVA = '<spring:message code="label.aplicaIva"/>';
	
	var modoDetalleEnum = {
			'nuevaVentana': '<%=ModoDetalleExpedienteEnum.VENTANA_NUEVA.getValue()%>',
			'pestanya': '<%=ModoDetalleExpedienteEnum.PESTANYA.getValue()%>'
	}
	
	var detalleConstants = {
		'estadoGestor': {
			'alta': '<%=EstadoGestorEnum.ALTA.getValue()%>',
			'baja': '<%=EstadoGestorEnum.BAJA.getValue()%>'
		}	
	};
	
	var datos = {
		'activo' : {
			'si' : '<%=ActivoEnum.SI.getValue()%>',
			'no' : '<%=ActivoEnum.NO.getValue()%>'
		}	
	}
	
// 	datos para navegar por tareas - INICIO
	var datosExp = {
			'tipoExp' : {
				'interpretacion' : '<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>'
			}
	};
	
	var datosTareas = {
			'estadoEjecucion' : {
				'pendienteEjecucion' : '<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()%>',
				'retrasada' : '<%=EstadoEjecucionTareaEnum.RETRASADA.getValue()%>',
				'ejecutada' : '<%=EstadoEjecucionTareaEnum.EJECUTADA.getValue()%>'
			}, 
			'tiposTarea' : {
				'proyectoTrados' : '<%=TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue()%>',
				'traducir' : '<%=TipoTareaGestionAsociadaEnum.TRADUCIR.getValue()%>',
				'revisar' : '<%=TipoTareaGestionAsociadaEnum.REVISAR.getValue()%>',
				'gestionInterpretacion' : '<%=TipoTareaGestionAsociadaEnum.GESTION_INTERPRETACION.getValue()%>',
				'interpretar' : '<%=TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue()%>',
				'entregaClienteTraduccion' : '<%=TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_TRADUCCION.getValue()%>',
				'tradEntregaClienteTraduccion' : '<%=TipoTareaGestionAsociadaEnum.TRAD_ENTREGA_CLIENTE_TRADUCCION.getValue()%>',				
				'entregaClienteRevision' : '<%=TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue()%>',
				'estudiarSubsanacion' : '<%=TipoTareaGestionAsociadaEnum.ESTUDIAR_SUBSANACION.getValue()%>',
				'noConformidadProveedor' : '<%=TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_PROVEEDOR.getValue()%>',
				'introduccionDatosPagoProv' : '<%=TipoTareaGestionAsociadaEnum.INTRODUCCION_DATOS_PAGO_PROVEEDORES.getValue()%>',
				'noConformidadCliente' : '<%=TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_CLIENTE.getValue()%>',
				'notifCorreccionesProv' : '<%=TipoTareaGestionAsociadaEnum.NOTIFICAR_CORRECCIONES_PROVEEDOR.getValue()%>',
				'corredactar' : '<%=TipoTareaGestionAsociadaEnum.CORREDACTAR.getValue()%>'
			},
			'tipoRecurso' : {
				'interno' : '<%=TipoRecursoEnum.INTERNO.getValue()%>',
				'externo' : '<%=TipoRecursoEnum.EXTERNO.getValue()%>'
			},
			'idTipoExp' : {
				'interpretacion' : '<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>'
			},
			'tiposRevision' : {
				'sencilla' : '<%=Constants.TIPO_REVISION_SENCILLA%>'
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
// 	datos para navegar por tareas - FIN
	
</script>
<script type="text/javascript">
var esTraductor = false;
var esTecnicoIzo = false;

<sec:authorize access="hasRole('ROLE_AA79B_TRADUCTOR')">
esTraductor = true;
</sec:authorize>
<sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
esTecnicoIzo = true;
</sec:authorize>

var tablaSelector = "tareasExpedientesForm";
var labelGestTrabajoInter ="<spring:message code='label.gestionInterpretacion'/>";
var labelProyTrados ="<spring:message code='label.proyectoTrados'/>";

</script>

<script type="text/javascript" src="${staticsUrl}/tinymce/jquery.tinymce.min.js"></script>
<script type="text/javascript" src="${staticsUrl}/tinymce/tinymce.min.js"></script>

<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/cabeceraDetalleExp.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/bitacoraexpediente.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/confirmartarea.js" type="text/javascript"></script>
