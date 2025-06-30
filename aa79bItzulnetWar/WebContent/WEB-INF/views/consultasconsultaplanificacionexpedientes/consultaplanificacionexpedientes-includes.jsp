<%@page import="com.ejie.aa79b.model.enums.ClasificacionDocumentosEnum"%>
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.FaseExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoRecursoEnum" %>
<%@page import="com.ejie.aa79b.common.Constants" %>
<link href="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/css/dynamic_formjQuery.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/sidl-dynamic-form-jquery.js"></script>	
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/jquery/jquery-geoEuskadi.js"></script>
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/jquery/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript">
	var idTarea;
	var anyoExpediente;
	var idExpediente; 
	var idTipoTarea;
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
	var datos = {
		'activo' : {
			'si' : '<%=ActivoEnum.SI.getValue()%>',
			'no' : '<%=ActivoEnum.NO.getValue()%>'
		}	
	}
	
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
			'cerrado' : '<%=FaseExpedienteEnum.CERRADO.getValue()%>'
		}	
	};
	
	var recursoInterno = '<%=TipoRecursoEnum.INTERNO.getValue()%>';
	var camposReport;
	
	if($.rup.lang === 'es'){
		camposReport = '<ul><li><input type="checkbox" checked="checked" id="3" value="anyoNumExpConcatenado" name="Nº Exp.">Nº Exp.</li><li><input type="checkbox" checked="checked" id="4" value="tipoExpedienteDescEs" name="Tipo">Tipo</li><li><input type="checkbox" checked="checked" id="5" value="titulo" name="Título">Título</li><li><input type="checkbox" checked="checked" id="6" value="fechaHoraPrevistaInicio" name="Fecha/Hora inicio prevista">Fecha/Hora inicio prevista</li><li><input type="checkbox" checked="checked" id="7" value="fechaHoraPrevistaFin" name="Fecha/Hora fin prevista">Fecha/Hora fin prevista</li><li><input type="checkbox" checked="checked" id="8" value="fechaHoraAlta" name="Fecha/Hora Solicitud">Fecha/Hora Solicitud</li><li><input type="checkbox" checked="checked" id="9" value="expedienteTradRev.fechaHoraFinalIZO" name="Fecha/Hora entrega IZO">Fecha/Hora entrega IZO</li><li><input type="checkbox" checked="checked" id="10" value="expedienteTradRev.fechaHoraEntregaReal" name="Fecha/Hora entrega real">Fecha/Hora entrega real</li><li><input type="checkbox" checked="checked" id="11" value="numPalTramos" name="Nº palabras IZO">Nº palabras IZO</li></ul>';
	} else {
		camposReport = '<ul><li><input type="checkbox" checked="checked" id="3" value="anyoNumExpConcatenado" name="Esp.-zk.">Esp.-zk.</li><li><input type="checkbox" checked="checked" id="4" value="tipoExpedienteDescEu" name="Mota">Mota</li><li><input type="checkbox" checked="checked" id="5" value="titulo" name="Titulua">Izenburua</li><li><input type="checkbox" checked="checked" id="6" value="fechaHoraPrevistaInicio" name="Aurreikusitako hasiera-data/-ordua">Aurreikusitako hasiera-data/-ordua</li><li><input type="checkbox" checked="checked" id="7" value="fechaHoraPrevistaFin" name="Aurreikusitako amaiera-data/-ordua">Aurreikusitako amaiera-data/-ordua</li><li><input type="checkbox" checked="checked" id="8" value="fechaHoraAlta" name="Eskaeraren data/ordua">Eskaeraren data/ordua</li><li><input type="checkbox" checked="checked" id="9" value="expedienteTradRev.fechaHoraFinalIZO" name="IZO entrega Data/Ordua">IZO entrega Data/Ordua</li><li><input type="checkbox" checked="checked" id="10" value="expedienteTradRev.fechaHoraEntregaReal" name="Benetan entregatutako Data/Ordua">Benetan entregatutako Data/Ordua</li><li><input type="checkbox" checked="checked" id="11" value="numPalTramos" name="Hitz-kopurua (IZO)">Hitz-kopurua (IZO)</li></ul>';
	}
	var tablaSelector = "tareasExpedientesForm";
	var labelGestTrabajoInter ="<spring:message code='label.gestionInterpretacion'/>";
	var labelProyTrados ="<spring:message code='label.proyectoTrados'/>";
	var estado;
	var fase;
	
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadorpersonas.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadorexpedientesrelacionados.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadorlotes.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/confirmartarea.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/consultaplanificacionexpedientes.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadoretiquetas.js" type="text/javascript"></script>