<%@include file="/WEB-INF/includeTemplate.inc"%>
<script type="text/javascript">
var listaEntidadFactu = ${contactoFactuList};
var txtEntidadAsoc = '<spring:message code="label.entidadAsociada"/>';	
var txtContacto = '<spring:message code="label.contacto"/>';
var txtPorcFact = '<spring:message code="label.porcentajeFactAplica"/>';
var txtFacturable = '<spring:message code="label.entidadFacturable"/>';
var txtIVA = '<spring:message code="label.aplicaIva"/>';
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/entidadcontactofacturacion.js" type="text/javascript"></script>