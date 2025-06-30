<%@page import="com.ejie.aa79b.model.enums.FaseExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.ClasificacionDocumentosEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.ClasificacionDocumentosEnum" %>
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoRecursoEnum" %>
<%@page import="com.ejie.aa79b.common.Constants" %>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<link href="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/css/dynamic_formjQuery.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/sidl-dynamic-form-jquery.js"></script>	
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/jquery/jquery-geoEuskadi.js"></script>
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/jquery/jquery-ui-1.8.18.custom.min.js"></script>

<script>

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
			'revisarTraduccionExterna' : '<%=TipoTareaGestionAsociadaEnum.REVISAR_TRADUCCION.getValue()%>',
			'introduccionDatosPagoProv' : '<%=TipoTareaGestionAsociadaEnum.INTRODUCCION_DATOS_PAGO_PROVEEDORES.getValue()%>',
			'noConformidadCliente' : '<%=TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_CLIENTE.getValue()%>',
			'notifCorreccionesProv' : '<%=TipoTareaGestionAsociadaEnum.NOTIFICAR_CORRECCIONES_PROVEEDOR.getValue()%>',
			'corredactar' : '<%=TipoTareaGestionAsociadaEnum.CORREDACTAR.getValue()%>',
			'revisarTraduccionInterna' : '<%=TipoTareaGestionAsociadaEnum.REVISAR_TRADUCCION_INTERNA.getValue()%>',
		},
		'tipoRecurso' : {
			'interno' : '<%=TipoRecursoEnum.INTERNO.getValue()%>',
			'externo' : '<%=TipoRecursoEnum.EXTERNO.getValue()%>'
		},
		'tiposRevision' : {
			'sencilla' : '<%=Constants.TIPO_REVISION_SENCILLA%>'
		},
		'idTipoExp' : {
			'interpretacion' : '<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>',
			'traduccion' : '<%=TipoExpedienteEnum.TRADUCCION.getValue()%>',
			'revision' : '<%=TipoExpedienteEnum.REVISION.getValue()%>'
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
	}
	
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
	var tablaSelector = "tareasExpedientesForm";
	
	var dniUsuSesion = '${xlnetAuthenticationProvider.userCredentials.nif}';
	var nombreUsuSesion = '${xlnetAuthenticationProvider.userCredentials.name} ${xlnetAuthenticationProvider.userCredentials.surname}';
	
	var esTraductorOInterprete = false, esTecnicoIzo = false, esAsignador = false;

	<sec:authorize access="hasRole('ROLE_AA79B_TRADUCTOR')">
	esTraductorOInterprete = true;
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_AA79B_INTERPRETE')">
	esTraductorOInterprete = true;
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
		esTecnicoIzo = true;
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_AA79B_ASIGNADOR')">
		esAsignador = true;
	</sec:authorize>
	
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadorpersonas.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadorexpedientesrelacionados.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadorlotes.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/dashboard.js" type="text/javascript"></script>
