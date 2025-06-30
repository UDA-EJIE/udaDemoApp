<%@page import="com.ejie.aa79b.model.enums.EstadoRequerimientoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.FaseExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.OrigenExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoPeticionarioEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum" %>
<%@page import="com.ejie.aa79b.model.enums.ClasificacionDocumentosEnum" %>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<link href="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/css/dynamic_formjQuery.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/sidl-dynamic-form-jquery.js"></script>	
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/jquery/jquery-geoEuskadi.js"></script>
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/jquery/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript">
	var x54j_url = "${appConfiguration['x54j.url']}";
</script>
<script type="text/javascript">
	var txtNumExp = '<spring:message code="label.numExp"/>';
	var txtTipo = '<spring:message code="label.tipo"/>';
	var txtGestorExp = '<spring:message code="label.gestorExp"/>';
	var txtEstadoFase = '<spring:message code="label.estadoFase"/>';
	var txtFechaHoraSol = '<spring:message code="label.fechaHoraSol"/>';
	var txtFechaHoraFinSol = '<spring:message code="label.fechaHoraFinSol"/>';
	var txtNumPalabras = '<spring:message code="label.numPalabras"/>';
	var txtGestorVip = '<spring:message code="label.gestorVip"/>';
	var txtBopv = '<spring:message code="label.bopv"/>';
	var txtPresupuesto = '<spring:message code="label.presupuesto"/>';
	var txtSubsanacionAport = '<spring:message code="label.subsanacionAport"/>';
	var txtTecnicoAsignado = '<spring:message code="label.tecnicoAsignado"/>';
	var idExpediente = '';
	var anyoExpediente = '';
	var detalleSub = false;
	var subrayado = false;
	var datosFormulario = '';
	var datosFormularioDoc = '';
	var comprobarFormularios = true;
	var comprobarFormularioDoc = true;
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
	var txtCentroOrganico = '<spring:message code="label.centroOrganico"/>';
	
	var fase= '<%=FaseExpedienteEnum.PDTE_TRAM_SUBSANACION.getValue()%>';
	var estado= '<%=EstadoExpedienteEnum.EN_ESTUDIO.getValue()%>';
	var estadoSub= '<%=EstadoRequerimientoEnum.PENDIENTE.getValue()%>';
	
	var origenEnum = {
			'origen' : {
				'oficio' : '<%=OrigenExpedienteEnum.OFICIO.getValue()%>',
				'solicitante' : '<%=OrigenExpedienteEnum.SOLICITANTE.getValue()%>',
				'webService' : '<%=OrigenExpedienteEnum.WEB_SERVICE.getValue()%>'
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
			},
			'idioma' : {
				'euskera' : '<%=Constants.IDIOMA_EUSKERA%>'
			}
	};
	
	var claseDocuEnum = {
			'traduccion' : '<%=ClasificacionDocumentosEnum.TRADUCCION.getValue()%>',
			'revision' : '<%=ClasificacionDocumentosEnum.REVISION.getValue()%>'
	};
	
	function comprobarFormulariosPestanas(){
		if (comprobarFormularioDoc){
			if (datosFormulario === $("#datosgeneralesexpedienteform").serialize() && datosFormularioDoc === $("#datosExpedienteTradRev_form").serialize()) {
				 return true;
			} else {
				return false;
			}
		} else {
			if (datosFormulario === $("#datosgeneralesexpedienteform").serialize()) {
				 return true;
			} else {
				return false;
			}
		}
	}
</script>
<script type="text/javascript" src="${staticsUrl}/tinymce/jquery.tinymce.min.js"></script>
<script type="text/javascript" src="${staticsUrl}/tinymce/tinymce.min.js"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/estudioexpedientes.js" type="text/javascript"></script>
<!-- <script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/expedientealta.js" type="text/javascript"></script>-->
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/cabeceraexpediente.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/bitacoraexpediente.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadorpersonas.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/receptoresautorizados.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadorexpedientesrelacionados.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadoretiquetas.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/expedientesrelacionados.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/categorizacionexpediente.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/contactofacturacionexpediente.js" type="text/javascript"></script>