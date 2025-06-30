<%@include file="/WEB-INF/includeTemplate.inc"%>

<div class="container-fluid aa79b-fade in"  id="calculoExpFacturacion">
	<%@include file="/WEB-INF/views/cabeceraexpediente/cabeceraexpediente.jsp"%>
	<div id="calculoExpedienteFacturacionMain_toolbar" style="margin-top: 10px;"></div>
	<div id="calculoExpedienteFacturacion_div" class="rup-table-container" >
		<div id="calculoExpedienteFacturacion_feedback"></div>						<!-- Feedback de la tabla --> 
		<div id="calculoExpedienteFacturacion_toolbar" style="display:none;"></div>
		<div id="calculoExpedienteFacturacion_filter_div" class="filterForm"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="calculoExpedienteFacturacion_filter_form" class="form-horizontal" style="border:none;">
			<div id="calculoExpedienteFacturacion_filter_toolbar" class="formulario_legend" style="display:none;"></div>
				<fieldset id="calculoExpedienteFacturacion_filter_fieldset" class="rup-table-filter-fieldset noBorder" style="margin-bottom: 5px;" >
		<div class="row marginTop5 marginBot5">
			<%@include file="/WEB-INF/views/calculoexpedientefacturacion/datosGeneralesExp.jsp"%>
			<div id="divFechaIzoYBopv">
				<label for="fechaEntregaIzo" class="control-label col-md-3 fEILabelWidth"><spring:message code="label.fechaHoraEntregaIzo"></spring:message>: </label>
				<label id="fechaEntregaIzo" class="control-label col-md-2 labelInput"></label>
				<label for="bopv" class="control-label col-md-1 bopvLabelWidth"><spring:message code="label.bopv"></spring:message>: </label>
				<label id="bopv" class="control-label col-md-1 labelInput"></label>
			</div>
		</div>
		<div id="divIdiomaRevDest" class="row marginBot5">
			<label for="idiomaRevDest" class="control-label col-md-4 idiomaLabel"><spring:message code="label.idiomaRevDestTraduccion"></spring:message>: </label>
			<label id="idiomaRevDest" class="control-label col-md-2 labelInput"></label>
		</div>
		<div class="row marginBot5">
			<div class="col-md-3 factSwitchDiv">
				<label class="" for="checkExpFacturable"><spring:message code="label.expedienteFacturable"></spring:message>: </label>
				<input type="checkbox" class="form-control" id="checkExpFacturable" value="S" data-switch-pestana=true />
			</div>
			<div id="datosExpFacturableTradRev01">
				<div class="col-md-2">
					<label for="relevancia" class="no-padding-left"><spring:message code="label.relevancia"></spring:message>: </label>
					<label id="relevancia" class="control-label col-md-12 labelInput"><spring:message code="label.sencilla"></spring:message></label>
				</div>
				<div class="col-md-3 no-padding-right">
					<label class="" for="recargoDificultad"><spring:message code="label.recargoPorDificultad"></spring:message>: </label>
					<input type="checkbox" class="form-control" id="recargoDificultad" value="S" data-switch-pestana=true />
				</div>
				<div class="col-md-3 no-padding-right">
					<label class="" for="recargoUrgencia"><spring:message code="label.porcentajeRecargoUrgencia"></spring:message>: </label>
					<input type="checkbox" class="form-control" id="recargoUrgencia" value="S" data-switch-pestana=true />
				</div>
			</div>
			<%@include file="/WEB-INF/views/calculoexpedientefacturacion/datosExpFacturableInterp01.jsp"%>
		</div>
		<%@include file="/WEB-INF/views/calculoexpedientefacturacion/datosExpFacturableInterp02.jsp"%>
		<div id="datosExpFacturableInterp03" class="row marginBot5">
			<div class="control-label col-md-3">
				<label for="numInterpretes" class="no-padding-left "><spring:message code="label.numInterpretes"></spring:message>: </label>
				<label id="numInterpretes" class="control-label col-md-12 labelInput"></label>
			</div>
			<div class="control-label col-md-5">
				<label for="horasReales" class=""><spring:message code="label.horasRealesInterpPorInterprete"></spring:message>: </label>
				<label id="horasReales" class="control-label col-md-12 labelInput"></label>
			</div>
		</div>
		<fieldset>
			<%@include file="/WEB-INF/views/calculoexpedientefacturacion/datosOrdenPreciosPublicos.jsp"%>
			<div class="row">
				<div id="divTarifaPalabra">
					<label for="tarifaPalabra" class="control-label col-md-2 tarifaPalLabel"><spring:message code="label.tarifaEPorPalabra"></spring:message>: </label>
					<label id="tarifaPalabra" class="control-label col-md-1 labelInput"></label>
				</div>
				<%@include file="/WEB-INF/views/calculoexpedientefacturacion/datosIva.jsp"%>
				<div id="datosExpFacturableTradRev05">
					<label for="precioMinimoTradRev" class="control-label col-md-2" style="width:150px;"><spring:message code="label.precioMinimo"></spring:message>: </label>
					<label id="precioMinimoTradRev" class="control-label col-md-1 labelInput"></label>
				</div>
			</div>
			<div class="row marginBot5">
				<div id="datosExpFacturableTradRev02">
					<label for="porcRecargoPorDificultad" class="control-label col-md-4" style="width:280px;"><spring:message code="label.porcentajeRecargoEspDificultad"></spring:message>: </label>
					<label id="porcRecargoPorDificultad" class="control-label col-md-1 labelInput"></label>
					<label for="porcRecargoPorUrgencia" class="control-label col-md-3" style="width:210px;"><spring:message code="label.porcentajeRecargoUrgencia"></spring:message>: </label>
					<label id="porcRecargoPorUrgencia" class="control-label col-md-1 labelInput"></label>
					
				</div>
				<%@include file="/WEB-INF/views/calculoexpedientefacturacion/datosExpFacturableInterp06.jsp"%>
			</div>
			<div class="row marginBot5">
				<div id="datosExpFacturableTradRev03" class="col-md-12 no-padding-left">
					<fieldset>
						<legend>
							<spring:message code="label.precioTradSegunConcor"></spring:message> 
						</legend>
						<label for="numPalExpTarificacion" class="control-label col-md-9 tarifAplicLabel"><spring:message code="label.numPalabrasTarif"/>: </label>
						<label id="numPalExpTarificacion" class="control-label col-md-2 labelInput"></label>
						<label class="control-label col-md-12"><spring:message code="label.porcentajeFactPalConcor"></spring:message>: </label>
						<label for="porcFactPalabraConcor084" class="control-label col-md-1 concor084DefaultWith"><spring:message code="comun.tramosConcor1"></spring:message>: </label> 
						<label id="porcFactPalabraConcor084" class="control-label col-md-1 labelInput"></label>
						<label for="porcFactPalabraConcor8594" class="control-label col-md-1 concor8594DefaultWith"><spring:message code="comun.tramosConcor2"></spring:message>: </label>  
						<label id="porcFactPalabraConcor8594" class="control-label col-md-1 labelInput"></label>
						<label for="porcFactPalabraConcor95100" class="control-label col-md-1 concor95100DefaultWith"><spring:message code="comun.tramosConcor3"></spring:message>: </label>
						<label id="porcFactPalabraConcor95100" class="control-label col-md-1 labelInput"></label>
					</fieldset>
				</div>
				<%@include file="/WEB-INF/views/calculoexpedientefacturacion/datosExpFacturableInterp05.jsp"%>
			</div>
			<div id="datosExpFacturableTradRev04" class="row">
				<div class="col-md-6 no-padding-left">
					<fieldset id="numPalabrasExp_filter_fieldset" >
						<legend>
							<spring:message code="label.numPalExpediente"></spring:message>
						</legend>
						<div class="p-2">	
							<div class="row">
								<label for="numTotPalabrasSolic" class="control-label col-xl-5 col-lg-7 numPalLabel"><spring:message code="label.numTotPalSolic"></spring:message>: </label>
								<label id="numTotPalabrasSolic" class="control-label col-md-3 labelInput"></label>
							</div>
							<div class="row">
								<label for="numTotPalabrasIzo" class="control-label col-xl-5 col-lg-7 numPalLabel"><spring:message code="label.numTotPalIzo"></spring:message>: </label>
								<label id="numTotPalabrasIzo" class="control-label col-md-3 labelInput"></label>
							</div>
							<div class="row">
								<label for="numTotPalabrasXml" class="control-label col-xl-5 col-lg-7 numPalLabel"><spring:message code="label.numTotPalXml"></spring:message>: </label>
								<label id="numTotPalabrasXml" class="control-label col-md-3 labelInput"></label>
							</div>
							<div class="row">
								<label for="xmlConcor084" class="control-label col-xl-5 col-lg-7 numPalLabel">&#8226; <spring:message code="label.concordancia084"></spring:message>: </label>
								<label id="xmlConcor084" class="control-label col-md-3 labelInput"></label>
							</div>
							<div class="row">
								<label for="xmlConcor8594" class="control-label col-xl-5 col-lg-7 numPalLabel">&#8226; <spring:message code="label.concordancia8594"></spring:message>: </label> 
								<label id="xmlConcor8594" class="control-label col-md-3 labelInput"></label>
							</div>
							<div class="row">
								<label for="xmlConcor95100" class="control-label col-xl-5 col-lg-7 numPalLabel">&#8226; <spring:message code="label.concordancia95100"></spring:message>: </label>
								<label id="xmlConcor95100" class="control-label col-md-3 labelInput"></label>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-6">
					<fieldset  id="numPalabrasFact_filter_fieldset">
						<legend>
							<spring:message code="label.numPalFactCliente"></spring:message>
						</legend>
						<div class="p-2">	
							<div class="row">
								<div class="col-xl-12 col-lg-12 pl-0">
									<label for="numTotPalabras" class="control-label col-md-7 numPalLabel"><spring:message code="label.numTotPal"></spring:message>: </label>
									<label id="numTotPalabras" class="control-label col-md-3 labelInput"></label>
								</div>
							</div>
							<div class="row">
								<div class="col-xl-12 col-lg-12 pl-0">
									<label for="numTotPalabrasConcor084" class="control-label col-md-7 numPalLabel">&#8226; <spring:message code="label.concordancia084"></spring:message>: </label>
									<label id="numTotPalabrasConcor084" class="control-label col-md-3 labelInput"></label>
								</div>
							</div>
							<div class="row">
								<div class="col-xl-12 col-lg-12 pl-0">
									<label for="numTotPalabrasConcor8594" class="control-label col-md-7 numPalLabel">&#8226; <spring:message code="label.concordancia8594"></spring:message>: </label>
									<label id="numTotPalabrasConcor8594" class="control-label col-md-3 labelInput"></label>
								</div>
							</div>
							<div class="row">
								<div class="col-xl-12 col-lg-12 pl-0">
									<label for="numTotPalabrasConcor95100" class="control-label col-md-7 numPalLabel">&#8226; <spring:message code="label.concordancia95100"></spring:message>: </label>
									<label id="numTotPalabrasConcor95100" class="control-label col-md-3 labelInput"></label>
								</div>
							</div>
						</div>
					</fieldset>
				</div>
			</div>
		</fieldset>
		</fieldset>
		</form>
		
		<fieldset id="calculoExpedienteFacturacion_ddd" class="noBorder">
			<legend>
				<spring:message code="label.entYcontAFact"></spring:message>
			</legend>
			<div id="calculoExpedienteFacturacion_grid_div" class="horizontal_scrollable_table">
				<!-- Tabla -->
				<table id="calculoExpedienteFacturacion"></table>
				<!-- Barra de paginación -->
				<div id="calculoExpedienteFacturacion_pager"></div>
			</div>
			<div class="observacionesContainer" id="labelContactoNoVinculado" style="display:none;">
				<p><span class="ico-ficha-encriptado marginRigt5"><i class="fa fa-exclamation-circle" aria-hidden="true"></i></span><spring:message code="label.contactoNoVinculadoEntidad"/></p>
			</div>
		</fieldset>
		<fieldset class="noBorder marginBot5">
			<%@include file="/WEB-INF/views/calculoexpedientefacturacion/datosImportes.jsp"%>
		</fieldset>
		</div>
	</div>
</div>
