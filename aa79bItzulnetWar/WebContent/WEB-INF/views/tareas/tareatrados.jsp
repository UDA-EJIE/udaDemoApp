<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in aa79b-content" id="divEjecutarTareaCapa">
	<div id="tareaTrados_div">
		<div id="ejecutarTareaDialog_toolbar"></div>
		<div id="ejecutarTareaDialog_feedback"></div>
		<div class="row">
			<div class="col-xs-2">
				<label class="control-label"><spring:message code="comun.tipoDeTarea"/>:</label>
			</div>
			<div class="col-xs-9">
				<b><c:out value="${descripcionTarea}"></c:out></b>
			</div>
		</div>
	
		<div id="pestanasModalTrados" class="mt-10">
			<div id="tabsModalTrados" class="tabsEjecutarTarea"></div>
		</div>
	
		<div id="pestanaTradosTarea"  style="display:none;">
			<div id="pestanaTradosTarea_div">
				<div id="pestanaTradosTarea_feedback"></div>
				<div id="pestanaTradosTarea_toolbar"></div>
				<div id="contentFormulariosPestanaTradosTarea" class="filterForm ">
					<div >
						<form id="subidaFicheroTrados_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
							<div class="form-group row">
								<label for="ficheroTrados" class="control-label col-md-12"><spring:message code="label.ficheroTrados" /> (*):</label>
								<div class=" col-md-9">
									<button id="pidButtonFinalXml" type="button" onclick="clickPidButtonFinalXml()" class="btnPID ui-button ui-corner-all ui-widget" style="margin-top: 0px;"><spring:message code="label.adjuntarFichero" /></button>
									<input id="nombreFicheroXml" style="width:320px;" type="text" />
									<button id="descargarFicheroXml" type="button" onclick="clickDescargarXml()" class="ui-button ui-corner-all ui-state-default ui-widget rup-enlaceCancelar" style="margin-top:0; display:none;float: none;"><spring:message code="label.descargarFichero"/></button>
								</div>
								<div class="col-md-6 oculto" id="divFicheroTrados">
									<input type="file" name="fichero" id="fichero"  class="form-control">
								</div>
								<input type="hidden" id="idTareaFichero" name="idTarea" />
								<input type="hidden" id="indPresupuestoExpTareaTrad" name="indPresupuesto"/>
								<input type="hidden" id="anyoExpTareaTrad" name="anyo" />
								<input type="hidden" id="numExpTareaTrad" name="numExp"/>
								<input type="hidden" id="idFicheroTradosXml">
							</div>
						</form>
						<input type="hidden" id="fechaLimSeleccionable"/>
						<input type="hidden" id="horaLimSeleccionable"/>
						<input type="hidden" id="minFechaLimiteAceptacion"/>
						<input type="hidden" id="minHoraLimiteAceptacion"/>
					</div>
					<fieldset id="fieldsetPresupuesto">
						<legend><spring:message code="label.presupuestoYFechaHoraEntrega"/></legend>
						<div id="pestanaTareaDatosPresupuesto"> 
						<form id="ejecutarTareaDialog_form">
							<input type="hidden" name="numExp" id="numExp_form" >
							<input type="hidden" name="anyo" id="anyo_form" >
							<input type="hidden" name="idTarea" id="idTarea_form" >
							<input type="hidden" name="tipoTarea.id015" id="idTipoTarea_form" >
							<input type="hidden" id="indPresupuesto" name="indPresupuesto"/>
							<input type="hidden" name="ejecucionTareas.horasTarea" id="horasTarea_form" >
							<input type="hidden" name="indVisible" id="indVisible_form" >
							<input type="hidden" name="subsanacion.presupuesto" id="presupuesto_form" >
							<div class="row">
								<div class="col-md-2 no-padding-right mh_61" id="checkPresupuesto">
									<label for="checkReqPresupuesto"><spring:message code="label.requierePresupuesto"/></label>
									<input type="checkbox" class="form-control" id="checkReqPresupuesto" value="S" data-switch-pestana=true />
									<label id="enlaceVerPresupuesto" class="input-group-link" style="margin-left: 10px; display: none;"><a href="#" onclick="verPresupuesto()"><spring:message code="label.ver" /></a></label>
								</div>
								<div class="col-md-2 mh_61" id="divImportePresupuesto">
									<label for="importePresupuesto"><spring:message code="label.importePresupuesto"/></label>
									<input class="numeric form-control" id="importePresupuesto" readonly="readonly"  ></input>
								</div>
								<div class="col-md-2 mh_61" id="divCheckVisible">
									<label for="checkVisible"><spring:message code="label.visible"/></label>
									<input type="checkbox" class="form-control" id="checkVisible" value="S" data-switch-pestana="true"/>
								</div>
								<div class="form-group col-md-12 col-lg-5 grupoFechaHora mh_61" id="divFechaNegociacionEntregaIzo">
									<label for="fechaNegociacionEntregaIzo" class="control-label valFecha" data-i18n="label.negociarFechaEntregaIzo"><spring:message code="label.negociarFechaEntregaIzo" />:</label>
									<input type="text" id="fechaNegociacionEntregaIzo" name="fechaEntrega" class="form-control" onchange="cambiosTrue()" >
									<input type="text" id="horaNegociacionEntregaIzo" name="horaEntrega" class="form-control col-md-3 campohora" maxlength="10" placeholder="hh:mm">
								</div>
								<div class="form-group col-md-12 col-lg-3 grupoFechaHora mh_61" id="divFechaLimiteAceptacion">
									<label for="fechaLimiteAceptacion" class="control-label valFecha" data-i18n="label.fechaHoraLimiteAceptacion"><spring:message code="label.fechaHoraLimiteAceptacion" />:</label>
									<input type="text" id="fechaLimiteAceptacion" name="subsanacion.fechaLimite" class="form-control" onchange="cambiosTrue()" >
									<input type="text" id="horaLimiteAceptacion" name="subsanacion.horaLimite" class="form-control campohora" maxlength="5" placeholder="hh:mm">
								</div>
							</div>
							</form>
						</div>
					</fieldset>
				</div>
			</div>
		</div>

		<div id="pestanaDetallePresupuesto" style="display:none;">
			<div id="pestanaDetallePresupuesto_div">
				<div id="pestanaDetallePresupuesto_feedback"></div>
				<div id="pestanaDetallePresupuesto_toolbar"></div>
				<div id="contentFormulariosPestanaDetallePresupuesto" class="filterForm ">
					<label><spring:message code="label.aContDetallePresupuesto" />:</label>
					<fieldset>
						<legend><spring:message code="label.presupuesto" /></legend>
						<div>
							<label><spring:message code="label.tema" />:</label>
							<span id="tituloTareaTrados"></span>
						</div>
						<div>
							<label><spring:message code="label.numTotalPalabras" />:</label>
							<span id="numTotalPalTareaTrados"></span>
						</div>
						<div>
							<label><spring:message code="label.importeTotalIvaIncluido" />:</label>
							<span id="presupuestoTareaTrados"></span>
						</div>
						<div>
							<label><spring:message code="label.fechaHoraEntregaIzo" />:</label>
							<span id="fechaHoraEntregaIzoTareaTrados"></span>
						</div>
					</fieldset>
					<div style="    margin-bottom: 10px;">
						<label><spring:message code="label.infoTenidaEnCuenta" />:</label>
					</div>
					<table id="pestanyaDetallePresupTabla1" style="width:100%;  margin-bottom: 10px;" class=" tablaConBorde tablaLineasImpares">
					  <tr>
					    <th><spring:message code="label.numExp"/></th>
					    <th><spring:message code="label.tipoExp"/></th> 
					    <th><spring:message code="label.fechaSolicitud"/></th>
					    <th><spring:message code="label.idiomaDestino"/></th>
					    <th><spring:message code="label.relevanciaTextos"/></th>
					    <th><spring:message code="label.bopv"/></th>
					    <th><spring:message code="label.tarifaPalabra"/></th>
					  </tr>
					  <tr>
					    <td id="numExpTareaTrados"></td>
					    <td id="tipoExpTareaTrados"></td> 
					    <td id="fechaSolicitudExpTareaTrados"></td>
					    <td id="idiomaDestinoExpTareaTrados"></td>
					    <td id="relevanciaTextosExpTareaTrados"></td>
					    <td id="bopvExpTareaTrados"></td>
					    <td id="tarifaPalabraExpTareaTrados"></td>
					  </tr>
					</table>
					<table id="pestanyaDetallePresupTabla2" style="width:100%" class=" tablaConBorde tablaLineasImpares">
					  <tr>
					    <th><spring:message code="label.numTotalPalabras"/></th>
					    <th><spring:message code="label.ceroOchentaycuatroPorciento"/></th> 
					    <th><spring:message code="label.ochentaycincoNoventaycuatroPorciento"/></th>
					    <th><spring:message code="label.noventaycincoCienPorciento"/></th>
					    <th><spring:message code="label.apremioSinMax"/></th>
					    <th><spring:message code="label.dificultad"/></th>
					  </tr>
					  <tr>
					    <td id="numTotalPalExpTareaTrados"></td>
					    <td id="084ExpTareaTrados"></td> 
					    <td id="8594ExpTareaTrados"></td>
					    <td id="95100ExpTareaTrados"></td>
					    <td id="apremioExpTareaTrados"></td>
					    <td id="dificultadExpTareaTrados"></td>
					  </tr>
					</table>
					<div class="col-md-2 no-padding-left">
						<label><spring:message code="label.importeBase"/>:</label>
						<span id="importeBaseExpTareaTrados" class="col-md-10 form-control"></span>
					</div>
					<div class="col-md-2">
						<label><spring:message code="label.impIva"/>:</label>
						<span id="porcentajeIvaExpTareaTrados" class="col-md-10 form-control" ></span>
					</div>
					<div class="col-md-2">
						<label><spring:message code="label.total"/>:</label>
						<span id="totalExpTareaTrados" class="col-md-10 form-control"></span>
					</div>
				</div>
			</div>
		</div>
		<div id="pestanaInforFichero"  style="display:none;">
				<div id="pestanaDetallePresupuesto_div">
					<div id="pestanaDetallePresupuesto_feedback"></div>
					<div id="pestanaDetallePresupuesto_toolbar"></div>
					<div id="contentFormulariosPestanaDetallePresupuesto" class="filterForm ">
						<label class="col-md-12"><spring:message code="label.infoExtraidaDeXml" /></label>
						<div class="col-md-6">
							<label><spring:message code="label.fechaHoraEjecucion" />:</label>
							<span id="fechaHoraEjExpTareaTrados"></span>
						</div>
						<div class="col-md-6">
							<label><spring:message code="label.nombreProyecto" />:</label>
							<span id="nombreProyExpTareaTrados"></span>
						</div>
						<%-- <div class="col-md-6">
							<label><spring:message code="label.memoriasTraduccion" />:</label>
							<fieldset>
								<div id="metadatosExpTareaTrados" style="overflow-y: auto;height: 121px;"></div>
							</fieldset>
						</div> --%>
						<div class="col-md-12">
							<fieldset>
								<legend><spring:message code="label.numeroPalabras" /></legend>
								<div class="col-md-12">
									<label><spring:message code="label.numTotPalSolic" />:</label>
									<span id="numTotalPalSolicExpTareaTrados"></span>
								</div>
								<div class="col-md-12">
									<label><spring:message code="label.numTotPalIzo" />:</label>
									<span id="numTotalPalIzoExpTareaTrados"></span>
								</div>
								<div class="col-md-12">
									<label><spring:message code="label.numTotPalXml" />:</label>
									<span id="numTotalPalXmlExpTareaTrados"></span><span id="desfaseNoAsumibleIcon" class="oculto" style="margin-left: 5px;"><i  class="fa fa-exclamation-triangle" aria-hidden="true"></i></span>
								</div>
								<div class="col-md-12">
									<label><spring:message code="label.concordancia084" />:</label>
									<span id="concor084ExpTareaTrados"></span>
								</div>
								<div class="col-md-12">
									<label><spring:message code="label.concordancia8594" />:</label>
									<span id="concor8594ExpTareaTrados"></span>
								</div>
								<div class="col-md-12">
									<label><spring:message code="label.concordancia95100" />:</label>
									<span id="concor95100ExpTareaTrados"></span>
								</div>
							</fieldset>
						</div>
						<div class="col-xs-12">
							<fieldset>
								<legend><spring:message code="label.numTotalPalabrasPorDocumento" /></legend>
								<div id="listaDocumentosExpTareaTrados"  style="overflow-y: auto;min-height: 55px;"></div>
							</fieldset>
						</div>
						<div class="col-xs-12">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<label class="control-label "><spring:message code="label.numPalabrasXmlSuperioriAIzo" /></label>
						</div>
					</div>
				</div>
			</div>
	</div>
</div>