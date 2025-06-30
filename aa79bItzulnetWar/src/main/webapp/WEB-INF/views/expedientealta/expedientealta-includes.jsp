<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoPeticionarioEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum" %>
<link href="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/css/dynamic_formjQuery.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/sidl-dynamic-form-jquery.js"></script>	
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/jquery/jquery-geoEuskadi.js"></script>
<script type="text/javascript" src="${appConfiguration['SERVIDORNORA']}/sidl/sidljQuery/lib/jquery/jquery-ui-1.8.18.custom.min.js"></script>

<script>
	var idExpediente = '';
	if('${idExpediente}'!==undefined && '${idExpediente}'!==null && '${idExpediente}'!==''){
		idExpediente='${idExpediente}';
	}
	var anyoExpediente = '';
	if('${anyoExpediente}'!==undefined && '${anyoExpediente}'!==null && '${anyoExpediente}'!==''){
		anyoExpediente='${anyoExpediente}';
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
	var txtCentroOrganico = '<spring:message code="label.centroOrganico"/>';
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

<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/expedientealta.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadorexpedientesrelacionados.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/expedientesrelacionados.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/cabeceraexpediente.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/bitacoraexpediente.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/contactofacturacionexpediente.js" type="text/javascript"></script>