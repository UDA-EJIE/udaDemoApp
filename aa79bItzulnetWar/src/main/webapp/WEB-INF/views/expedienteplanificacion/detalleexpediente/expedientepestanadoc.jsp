<%@page import="com.ejie.aa79b.model.enums.ClasificacionDocumentosEnum"%>
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoRequerimientoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%> 

<div id="capaPestanaCompletaDoc" class="container-fluid  aa79b-fade in" style="padding:0 1.5rem">
<!-- Formulario de detalle de la T53 -->
	<div class="row oculto" id="notaPptoVisible" style="padding:1rem">
		<label class="control-label col-lg-12 negrita"><spring:message code="label.notaPptoVisibleUsuario"/></label>
	</div>
	<div class="row oculto" id="notaTareasEjecutadas" style="padding:1rem">
		<label class="control-label col-lg-12 negrita"><spring:message code="label.notaTareasEjecutadas"/></label>
	</div>
	<div id ="pestanyaDocPlanif_feedback"></div>
	<div id="datosExpedienteTradRev_div">
		<div id ="datosExpedienteTradRev_navigation"></div>			<!-- Barra de navegación del detalle -->
		<div class="" >
			<form id="datosExpedienteTradRev_form">					<!-- Formulario -->
				<div id ="datosExpedienteTradRev_feedback"></div>		<!-- Feedback del formulario de detalle -->			
				
					<!-- Campos del formulario de detalle -->
					<input type="hidden" name="anyo" id="anyo_exp" />
					<input type="hidden" name="numExp" id="numExp_exp" />
					
					<div class="row">
						
						<div class="form-group col-xs-6 col-lg-3">
							<label for="idRelevancia" class="control-label col-xs-12 no-padding"><spring:message code="label.relevancia"/> (*):</label>
							<div class="divComboW125">
								<input type="text" name="idRelevancia" class="form-control" id="idRelevancia_exp"/>
							</div>
						</div>	
						<div class="form-group col-xs-6 col-lg-3">
							<label for="indFacturable" class="control-label col-xs-12 no-padding"><spring:message code="label.expedienteFacturable"/> (*):</label>
							<div><input type="checkbox" name="indFacturable" class="form-control" id="indFacturable_exp" value="S" data-switch-pestana="true"/></div>
						</div>
						<div class="form-group col-xs-6 col-lg-3">
							<label for="indUrgente_exp" class="control-label col-xs-12 no-padding"><spring:message code="label.urgente"/> (*):</label>
							<div><input type="checkbox" name="indUrgente" class="form-control" id="indUrgente_exp" value="S" data-switch-pestana="true"/></div>
						</div>		
						<div id="capaRequierePpto" class="form-group col-xs-6 col-lg-3">
							<label for="indPresupuesto" class="control-label col-xs-12 no-padding"><spring:message code="label.requierePresupuesto"/> (*):</label>
							<div><input type="checkbox" name="indPresupuesto" class="form-control" id="indPresupuesto_exp" value="S" data-switch-pestana="true"/></div>
						</div>		
					</div>
					<div class="row">	
<!-- 						<div class="form-group col-xs-6 col-lg-3" style="clear: left;"> -->
<%-- 							<label for="numTotalPalSolic" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.numTotalPalSolic"/> (*):</label>						 --%>
<!-- 							<input type="text" name="numTotalPalSolic" class="form-control numeric" id="numTotalPalSolic_exp" maxlength="6" style="width: 80px" readonly="readonly" /> -->
<!-- 						</div>	 -->
						<div class="form-group col-xs-6 col-lg-3" style="clear: left;">
							<label for="numTotalPalIzo_exp" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.numTotalPalIzo"/> (*):</label>						
							<input type="text" name="numTotalPalIzo" class="form-control numeric" id="numTotalPalIzo_exp" maxlength="6" style="width: 80px" />
						</div>	
						<div class="form-group col-md-12 col-lg-3 grupoFechaHora">
			                <label class="control-label valFecha" for="fechaFinalIzo"><spring:message code="label.fechaHoraFinalIzo"/> (*):</label>
			                <input type="text" name="fechaFinalIzo" class="form-control" id="fechaFinalIzo_exp"/>
			                <input type="text" name="horaFinalIzo" class="form-control campohora" id="horaFinalIzo_exp" placeholder="hh:mm" maxlength="5"/>
			            	<button id="fechaFinalIzo_button_modif" type="button" class="oculto ui-button ui-widget ui-state-default ui-corner-all botonConCampo">
					            	<spring:message code="label.modificar" />
							</button>
			            </div>
			            <div class="form-group col-xs-6 col-lg-3">
							<label for="indDificil_exp" class="control-label col-xs-12 no-padding"><spring:message code="label.recargoPorDificultad"/> (*):</label>
							<div><input type="checkbox" name="indDificil" class="form-control" id="indDificil_exp" value="S" data-switch-pestana="true"/></div>
						</div>
<!-- 						<div class="form-group col-lg-3 col-xl-2"> -->
<%-- 							<label for="fechaFinalSolic" class="control-label valFecha col-xs-12 no-padding"><spring:message code="label.fechaFinalSolic" /> (*):</label> --%>
<!-- 							<input type="text" name="fechaFinalSolic" class="form-control" id="fechaFinalSolic_exp" style="width: 150px" readonly="readonly"/> -->
<!-- 						</div>		 -->
<!-- 						<div id="capaFechaFinalProp" class="form-group col-lg-3 col-xl-3"> -->
<%-- 							<label  for="fechaFinalProp" class="control-label valFecha col-xs-12 no-padding"><spring:message code="label.fechaFinProp"/> (*):</label> --%>
<!-- 							<input type="text" name="fechaFinalProp" class="form-control" id="fechaFinalProp_exp"  style="width: 150px" readonly="readonly"/> -->
<!-- 						</div> -->
					</div>
					
					<input type="hidden" name="fechaFinalSolic" id="fechaFinalSolic_exp" />
					<input type="hidden" name="fechaFinalProp"  id="fechaFinalProp_exp" />
					<input type="hidden" name="numTotalPalSolic" id="numTotalPalSolic_exp" />
					
					
					
					
					<div class="row">
						<div class="form-group col-lg-12">
							<fieldset>
								<legend>
									<spring:message code="comun.palabrasFacturar"/>
								</legend>
						<div class="form-group col-md-4 col-xl-3 ">
						<label for="numTotalPalFacturar" class="control-label col-md-12 p-0" data-i18n="comun.palabrasFacturar"><spring:message code="comun.palabrasFacturar" />:</label>
							<div class="form-group  col-md-4 col-xl-4 p-0">
								<input type="text" id="numTotalPalFacturar" name="numTotalPalFacturar" class="form-control" />
							</div>
						</div>
						
						<div class="form-group col-md-6 p-0">
							<label class="control-label col-lg-12 no-padding" data-i18n="comun.tramosConcordancia"><spring:message code="comun.tramosConcordancia" />:</label>
							<div class="form-group col-lg-4 p-0">
								<label for="numPalConcor084" class="control-label col-md-5 p-0" data-i18n="comun.tramosConcor1"><spring:message code="comun.tramosConcor1" />:</label>
								<div class="form-group  col-md-5 col-xl-3 p-0">
									<input type="text" id="numPalConcor084" name="numPalConcor084" class="form-control no-padding-left" />
								</div>
							</div>
							<div class="form-group col-lg-4 p-0">
								<label for="numPalConcor8594" class="control-label col-md-5 p-0" data-i18n="comun.tramosConcor2"><spring:message code="comun.tramosConcor2" />:</label>
								<div class="form-group  col-md-5 col-xl-3 p-0">
									<input type="text" id="numPalConcor8594" name="numPalConcor8594" class="form-control no-padding-left" />
								</div>
							</div>
							<div class="form-group col-lg-4 p-0">
								<label for="numPalConcor95100" class="control-label col-md-6 p-0" data-i18n="comun.tramosConcor3"><spring:message code="comun.tramosConcor3" />:</label>
								<div class="form-group  col-md-5 col-xl-3 p-0">
									<input type="text" id="numPalConcor95100" name="numPalConcor95100" class="form-control no-padding-left" />
								</div>
							</div>
						</div>
						</fieldset>
						</div>
					</div>
					
					<div class="row oculto" id="capaAceptacionPresupesto">
						<div class="form-group col-lg-12">
							<fieldset>
								<legend>
									<spring:message code="label.pptoAceptacionFechaHora"/>
								</legend>
<!-- 								<div class="form-group col-lg-4"> -->
<%-- 									<label for="requierePresupuesto_detalle" class="control-label "><spring:message code="label.requiere"/>:</label> --%>
<!-- 									<select name="requierePresupuesto" class="form-control" id="requierePresupuesto_detalle"> -->
<%-- 										<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option> --%>
<%-- 										<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option> --%>
<!-- 									</select> -->
<!-- 								</div> -->
								<div class="form-group col-sm-12 col-md-4 col-lg-4 grupoFechaHora">
					                <label class="control-label valFecha" for="fechaLimite"><spring:message code="label.fechaHoraLimiteAceptacion"/> (*):</label>
					                <input type="text" name="aceptacionPresupuesto.fechaLimite" class="form-control" id="fechaLimite"/>
					                <input type="text" name="aceptacionPresupuesto.horaLimite" class="form-control campohora" id="horaLimite" placeholder="hh:mm" maxlength="5"/>
					            </div>
					            
								<div class="form-group col-sm-12 col-md-4 col-lg-3 col-xl-2">
									<label for="presupuesto_detalle" class="control-label "><spring:message code="label.estadoAceptacion"/>:</label>
									<select name="aceptacionPresupuesto.estado" class="form-control" id="estadoPpto">
										<option value="<%=EstadoRequerimientoEnum.PENDIENTE.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.PENDIENTE.getLabel()%>"/></option>
										<option value="<%=EstadoRequerimientoEnum.ACEPTADA.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.ACEPTADA.getLabel()%>"/></option>
										<option value="<%=EstadoRequerimientoEnum.RECHAZADA.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.RECHAZADA.getLabel()%>"/></option>
									</select>
								</div>
						
							</fieldset>
						</div>
					</div>
					
					
					<div class="row oculto" id="capaAceptacionFechaFin">
						<div class="form-group col-lg-12">
							<fieldset>
								<legend>
									<spring:message code="label.negociarNuevaFechaEntrega"/>
								</legend>
								<div class="form-group col-sm-12 col-md-4 col-lg-4 grupoFechaHora">
									<label class="control-label valFecha" for="nuevaFechaIZO"><spring:message code="label.fechaHoraFinalIzoProp"/>:</label>
									<input type="text" name="nuevaFechaIZO" class="form-control" id="nuevaFechaIZO"/>
					                <input type="text" name="nuevaHoraIZO" class="form-control campohora" id="nuevaHoraIZO" placeholder="hh:mm" maxlength="5"/>
								</div>
								
								<div class="form-group col-sm-12 col-md-4 col-lg-3 grupoFechaHora">
					                <label class="control-label valFecha" for="fechaLimite"><spring:message code="label.fechaHoraLimiteAceptacion"/> (*):</label>
					                <input type="text" name="aceptacionFechaHora.fechaLimite" class="form-control" id="negocNuevaFechaLimite"/>
					                <input type="text" name="aceptacionFechaHora.horaLimite" class="form-control campohora" id="negocNuevaHoraLimite" placeholder="hh:mm" maxlength="5"/>
					            </div>
					            
								<div class="form-group col-sm-12 col-md-4 col-lg-3 col-xl-2">
									<label for="presupuesto_detalle" class="control-label "><spring:message code="label.estadoAceptacion"/>:</label>
									<select name="aceptacionFechaHora.estado" class="form-control" id="negocNuevaEstadoPpto">
										<option value="<%=EstadoRequerimientoEnum.PENDIENTE.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.PENDIENTE.getLabel()%>"/></option>
										<option value="<%=EstadoRequerimientoEnum.ACEPTADA.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.ACEPTADA.getLabel()%>"/></option>
										<option value="<%=EstadoRequerimientoEnum.RECHAZADA.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.RECHAZADA.getLabel()%>"/></option>
									</select>
								</div>
						
							</fieldset>
						</div>
					</div>
					
					
					<input type="hidden" name="palPresupuesto" id="palPresupuesto" value="${palPresupuesto}" />
					<!-- input type="text" name="numTotalPalSolic" id="numTotalPalSolic_exp" /-->
					
					<!-- Fin campos del formulario de detalle T53-->
			</form>
		</div>
		<!-- Botonera del formulario de detalle -->
		<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
			<div class="ui-dialog-buttonset">
				<!-- Botón Guardar -->
				<button id="datosExpedienteTradRev_button_save" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
					<spring:message code="save" />
				</button>
				<!-- Enlace cancelar -->
				<a href="javascript:void(0)" id="datosExpedienteTradRev_link_cancel"
					class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
			</div>
		</div>
	</div>
				
				
				
				
				
				
				
				
<div id="datosInterpretacion_div" class="oculto">
	<div id ="datosInterpretacion_navigation"></div>			<!-- Barra de navegación del detalle -->
	<div class="" >
		<form id="datosInterpretacion_form">					<!-- Formulario -->
			<div id ="datosInterpretacion_feedback"></div>		<!-- Feedback del formulario de detalle -->			
			
				<!-- Campos del formulario de detalle -->
				<input type="hidden" name="anyo" id="anyo_expI" />
				<input type="hidden" name="numExp" id="numExp_expI" />
				
				
				<div class="row" id="capaAceptacionPresupestoInterpretacion">
					<div class="form-group col-lg-12">
						<fieldset>
							<legend>
								<spring:message code="label.aceptacionPresupuesto"/>
							</legend>

							<div class="form-group col-sm-12 col-md-4 col-lg-4 grupoFechaHora">
				                <label class="control-label valFecha" for="fechaLimiteAceptacionInterp"><spring:message code="label.fechaHoraLimiteAceptacion"/> (*):</label>
				                <input type="text" name="aceptacionPresupuesto.fechaLimite" class="form-control" id="fechaLimiteAceptacionInterp"/>
				                <input type="text" name="aceptacionPresupuesto.horaLimite" class="form-control campohora" id="horaLimiteAceptacionInterp" placeholder="hh:mm" maxlength="5"/>
				            </div>
				            
							<div class="form-group col-sm-12 col-md-4 col-lg-3 col-xl-2">
								<label for="estadoPptoAceptacionInterp" class="control-label "><spring:message code="label.estadoAceptacion"/>:</label>
								<select name="aceptacionPresupuesto.estado" class="form-control" id="estadoPptoAceptacionInterp">
									<option value="<%=EstadoRequerimientoEnum.PENDIENTE.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.PENDIENTE.getLabel()%>"/></option>
									<option value="<%=EstadoRequerimientoEnum.ACEPTADA.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.ACEPTADA.getLabel()%>"/></option>
									<option value="<%=EstadoRequerimientoEnum.RECHAZADA.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.RECHAZADA.getLabel()%>"/></option>
								</select>
							</div>
					
						</fieldset>
					</div>
				</div>
				<!-- Fin campos del formulario de detalle T53-->
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Botón Guardar -->
			<button id="datosInterpretacion_button_save" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="datosInterpretacion_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>				
				
				
				
				
	<section>
	    <div>
	      <div class="row document-cabecera"></div>
	      <div class="row document-content">
	        <div class="col-md-10" id="capaDocumentos">
				<c:forEach var="documento" items="${listaDocumentos}">
					<div class="row documento-all" id="filaDoc${documento.idDoc}">
			            <div class="col-md-9 documento-first">
				              <p class="document-tit"><spring:message code="label.tipo"/>: <c:out value="${documento.claseDocumentoDesc}"></c:out></p> 
				                <c:if test="${documento.claseDocumento == '1' || documento.claseDocumento == '2' }">
					                <div class="displayAsTable">
				                		<p class="document-tipo" style="display: table-cell; padding-right: 5px;">
				                			<spring:message code="label.documentoOrigen"/> - 
				                		</p>
				               	</c:if>
				               	<p class="document-fileAndIcon">
					              <a href="#" class="document-eusk descargarDoc iconSameLine" data-id="${documento.idDoc}">
					                <c:out value="${documento.ficheros[0].nombre}"></c:out>
					                <span class="documento-archivo">(<c:out value='${ documento.ficheros[0].tamanoKB }'></c:out> Kb.)</span>
				                  </a>
									<c:choose>
					                	<c:when test="${documento.ficheros[0].encriptado == 'S'}">
						                	<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.encrip'/>"><i class="fa fa-lock" aria-hidden="true"></i></span>
					                  	</c:when>
										<c:otherwise>
											<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.noencrip'/>"><i class="fa fa-unlock" aria-hidden="true"></i></span>
										</c:otherwise>	
					                 </c:choose> 
					          </p> 
					           <c:if test="${documento.claseDocumento == '1' || documento.claseDocumento == '2' }">
					           	</div>
					           </c:if>
				              <c:if test="${documento.ficheros[1] != null}">
				              	<div class="displayAsTable">
			                		<p class="document-tipo" style="display: table-cell; padding-right: 5px;">
			                			 <spring:message code="label.documentoARevisar"/> -  
			                		</p>
		                			<p class="document-fileAndIcon">	
					              <a href="#" class="document-cast descargarDoc iconSameLine" data-id="${documento.ficheros[1].idDocInsertado}"><c:out value="${documento.ficheros[1].nombre}"></c:out>
					                <span class="documento-archivo">(<c:out value="${documento.ficheros[1].tamanoKB}"></c:out> Kb.)</span>
				                  </a>
				                  <c:choose>
					                	<c:when test="${documento.ficheros[1].encriptado == 'S'}">
						                	<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.encrip'/>"><i class="fa fa-lock" aria-hidden="true"></i></span>
					                  	</c:when>
										<c:otherwise>
											<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.noencrip'/>"><i class="fa fa-unlock" aria-hidden="true"></i></span>
										</c:otherwise>	
					                 </c:choose> 
					                 </p>
					                 </div>		
				              </c:if>
						     <c:if test="${documento.claseDocumento == 1 or documento.claseDocumento == 2}">
						     	<c:if test="${documento.tituloDifferentFromFileName}"> 	
						     		<p class="document-tipo"> <spring:message code="label.tituloAlt"/>: <c:out value="${documento.titulo}"></c:out></p>
						      	</c:if>
						     	<p class="document-tipo"> <spring:message code="label.documento.tipo"/>: <c:out value="${documento.tipoDocumentoDesc}"></c:out></p>
						     	<p class="document-izo"> <spring:message code="label.documento.numPalabrasIZO"/>:  <c:out value="${documento.numPalIzo}"></c:out></p>
						     	<p class="document-izo"> <spring:message code="label.documento.numPalabrasSolicitante"/>:  <c:out value="${documento.numPalSolic}"></c:out></p>
						     </c:if>
						     <c:if test="${documento.claseDocumento == 4}">
						     	<p class="document-izo"> <spring:message code="label.documento.numPalabrasIZO"/>:  <c:out value="${documento.numPalIzo}"></c:out></p>
						     </c:if>
						     
						     
						     
						     <c:if test="${documento.documentoFinal != null}">
						     	<p class="document-tit marginTop5"><spring:message code="label.documentoFinal"/></p> 
						     	<c:if test="${documento.isBopv == 'S' && documento.ficheros[0].extension != 'zip'}">
						             <p class="document-fileAndIcon">
							            <a href="#" class="document-cast descargarDocGeneral contentMargin iconSameLine" style="margin-right:0!important;"  data-id="${documento.documentoOriginalFinal.idDocInsertado}"> <c:out value="${documento.documentoOriginalFinal.nombre}.${documento.documentoOriginalFinal.extension}"></c:out>
							                <span class="documento-archivo">(<c:out value="${documento.documentoOriginalFinal.tamanoKB}"></c:out> Kb.)</span>
							            </a>
						                <c:choose>
						                	<c:when test="${documento.documentoOriginalFinal.encriptado == 'S'}">
							                	<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.encrip'/>"><i class="fa fa-lock" aria-hidden="true"></i></span>
						                  	</c:when>
											<c:otherwise>
												<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.noencrip'/>"><i class="fa fa-unlock" aria-hidden="true"></i></span>
											</c:otherwise>	
						                 </c:choose> 	
							              <c:if test="${documento.documentoOriginalFirmado != null && documento.documentoOriginalFirmado.idDocInsertado != null}">
							              	<a href="#" class="document-cast descargarDocGeneral contentMargin firma iconSameLine" data-id="${documento.documentoOriginalFirmado.idDocInsertado}"><i class="fa fa-pencil-square-o" aria-hidden="true" style="vertical-align: middle;"></i> <spring:message code='label.documento.verFirma'/></a>				              	
							              </c:if>
						              </p>
					            </c:if>
					            <p class="document-fileAndIcon">
							     	<a href="#" class="document-cast descargarDocGeneral contentMargin iconSameLine" style="margin-right:0!important;" data-id="${documento.documentoFinal.idDocInsertado}"> <c:out value="${documento.documentoFinal.nombre}"></c:out>
						                <span class="documento-archivo">(<c:out value="${documento.documentoFinal.tamanoKB}"></c:out> Kb.)</span>
					                </a>
					                <c:choose>
					                	<c:when test="${documento.documentoFinal.encriptado == 'S'}">
						                	<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.encrip'/>"><i class="fa fa-lock" aria-hidden="true"></i></span>
					                  	</c:when>
										<c:otherwise>
											<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.noencrip'/>"><i class="fa fa-unlock" aria-hidden="true"></i></span>
										</c:otherwise>	
					                 </c:choose> 	
						              <c:if test="${documento.documentoFinalFirmado != null && documento.documentoFinalFirmado.idDocInsertado != null}">
						              	<a href="#" class="document-cast descargarDocGeneral contentMargin firma iconSameLine" data-id="${documento.documentoFinalFirmado.idDocInsertado}"><i class="fa fa-pencil-square-o" aria-hidden="true" style="vertical-align: middle;"></i> <spring:message code='label.documento.verFirma'/></a>				              	
						              </c:if>
					              </p>
						     </c:if>
						     
						     <c:if test="${documento.justificanteRevision != null}">
						     	<p class="document-tit marginTop5"><spring:message code="label.justificanteRevision"/></p> 
						     	<p class="document-fileAndIcon">
						     	<a href="#" class="document-cast descargarDocGeneral contentMargin iconSameLine" data-id="${documento.justificanteRevision.idDocInsertado}"> <c:out value="${documento.justificanteRevision.nombre}"></c:out>
					                <span class="documento-archivo">(<c:out value="${documento.justificanteRevision.tamanoKB}"></c:out> Kb.)</span>	
				                </a>
					                <c:choose>
					                	<c:when test="${documento.justificanteRevision.encriptado == 'S'}">
						                	<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.encrip'/>"><i class="fa fa-lock" aria-hidden="true"></i></span>
					                  	</c:when>
										<c:otherwise>
											<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.noencrip'/>"><i class="fa fa-unlock" aria-hidden="true"></i></span>
										</c:otherwise>	
					                 </c:choose> 	
					              </p>
						     </c:if>
						     
			            </div>
			            <div class="col-md-1 documento-second">
			              <span class="ico-ficha-right">
			              <c:choose>
				              	<c:when test="${documento.indVisible == 'S'}">
				              	  <i class="fa fa-eye" aria-hidden="true"></i>
								</c:when>
								<c:otherwise>
				                	<i class="fa fa-eye-slash" aria-hidden="true"></i>
				                </c:otherwise>    
			                </c:choose>            
			              </span>
			              <span class="txt-ficha-right"><spring:message code="label.documento.visible"/></span>
			            </div>
			            <div class="col-md-1 documento-second">
			              <div style="border-left: 1px solid #d9dce0;">		              	
			              	<a class="verDocumento"  href="#" data-id="${documento.idDoc}" >
				                <span class="ico-ficha-right">
				                  <i class="fa fa-search" aria-hidden="true"></i>
				                </span>
				                <span class="txt-ficha-right"><spring:message code="label.ver"/></span>
			                </a>
			              </div>
			            </div>
			            
			            <div class="col-md-1 documento-second columnaEliminar">
			              <div style="border-left: 1px solid #d9dce0;">
			              	<a class="eliminarDocumento <c:if test="${documento.claseDocumento == 1 or documento.claseDocumento == 2}">ocultarSiPptoVisible</c:if>"  href="#" data-id="${documento.idDoc}">
				                <span class="ico-ficha-right">
				                  <i class="fa fa-times" aria-hidden="true"></i>
				                </span>
				                <span class="txt-ficha-right"><spring:message code="eliminar"/></span>
			                </a>
			              </div>
			            </div>
			        </div>
				</c:forEach>
				<!--  documentos tarea xliff, sdlxliff y tmx - visibles solo si traductor -->
			      	<c:if test="${listaDocumentosXliff != null && !empty listaDocumentosXliff}">
			      		<div class="row documento-all" >
			      			<div class="col-md-12 documento-first">
								<p class="document-tit"><spring:message code="label.tipo"/>: <spring:message code="label.otrosDocumentos"/></p>	
			      				<c:forEach var="documentoxliff" items="${listaDocumentosXliff}">
									<p class="document-fileAndIcon">
						              <a href="#" class="document-eusk descargarDocXliff iconSameLine" data-id="${documentoxliff.idFichero}">
						                <c:out value="${documentoxliff.nombre}"></c:out>
						                <span class="documento-archivo">(<c:out value='${documentoxliff.tamanoKB }'></c:out> Kb.)</span>
					                  </a>
										<c:choose>
						                	<c:when test="${documentoxliff.encriptado == 'S'}">
							                	<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.encrip'/>"><i class="fa fa-lock" aria-hidden="true"></i></span>
						                  	</c:when>
											<c:otherwise>
												<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.noencrip'/>"><i class="fa fa-unlock" aria-hidden="true"></i></span>
											</c:otherwise>	
						                 </c:choose> 
						          	</p>
			      				</c:forEach> 		      			
			      			</div>
		      			</div>
			      	</c:if>
	        </div>
	
	        <div class="col-md-2 document-content-right" id="capaAcciones">
	          <p class="document-tit"><spring:message code="label.acciones"/></p>
	          <div class="cubo">
	            <a href="#" id="btnNuevoDoc">
	              <span class="ico-ficha-right">
	                <i class="fa fa-paperclip" aria-hidden="true"></i>
	              </span>
	              <span class="txt-ficha-right"><spring:message code="label.adjuntarFichero"/></span>
	            </a>
	          </div>        
	        </div>
	      </div>
	      
	      
	      <!--  documentos posttraduccionBOE, si los hay -->
	      <c:if test="${documentosBOE.idTarea != null}">
		      <div class="col-md-12">
			      	<fieldset>
							<legend class="fieldset_rojo"><spring:message code="label.docsPostTraduccionBoe"/></legend>
							<c:if test="${documentosBOE.idFicheroInforme != null}">	
								<a href="#" class="document-cast descargarDocGeneral contentMargin" data-id="${documentosBOE.idFicheroInforme}">
									<spring:message code="label.informeDeCorr"/> 
									<span class="documento-archivo">(<spring:message code="label.fichero"/> <c:out value="${documentosBOE.ficheroInforme.extension} , ${documentosBOE.ficheroInforme.tamanoKB}"></c:out> Kb.)</span>
					                <c:choose>
					                	<c:when test="${documentosBOE.ficheroInforme.encriptado == 'S'}">
						                	<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.encrip'/>"><i class="fa fa-lock" aria-hidden="true"></i></span>
					                  	</c:when>
										<c:otherwise>
											<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.noencrip'/>"><i class="fa fa-unlock" aria-hidden="true"></i></span>
										</c:otherwise>	
					                 </c:choose> 	
								</a>	  
							</c:if>	
				      		<c:if test="${documentosBOE.idFicheroDoc != null}">	
								<a href="#" class="document-cast descargarDocGeneral contentMargin" data-id="${documentosBOE.idFicheroDoc}">
									<spring:message code="label.documentoDeCorr"/>
									<span class="documento-archivo">(<spring:message code="label.fichero"/> <c:out value="${documentosBOE.ficheroDoc.extension} , ${documentosBOE.ficheroDoc.tamanoKB}"></c:out> Kb.)</span>
					                <c:choose>
					                	<c:when test="${documentosBOE.ficheroDoc.encriptado == 'S'}">
						                	<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.encrip'/>"><i class="fa fa-lock" aria-hidden="true"></i></span>
					                  	</c:when>
										<c:otherwise>
											<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.noencrip'/>"><i class="fa fa-unlock" aria-hidden="true"></i></span>
										</c:otherwise>	
					                 </c:choose> 	
								</a>	  
							</c:if>	
			      	</fieldset>
		      </div>
	      </c:if>
	    </div>
	  </section>
	  
	  
	
	<!-- Formulario de detalle de DOCUMENTO -->
	<div id="documentosexpediente_detail_div" class="aa79b-content-modal" >
		<div id ="documentosexpediente_detail_navigation"></div>			<!-- Barra de navegación del detalle -->
		<div >
			<form id="documentosexpediente_detail_form">					<!-- Formulario -->
				<div id ="documentosexpediente_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
				<div class="col-xs-12">
				
					<!-- Campos del formulario de detalle -->
					<input type="hidden" name="idDoc" class="form-control" id="idDoc056_detail_table" readonly="readonly"/>
					
					<input type="hidden" name="anyo" class="form-control" id="anyo056_detail_table" readonly="readonly"/>
					<input type="hidden" name="numExp" class="form-control" id="numExp056_detail_table" readonly="readonly"/>
					
					<div class="row">
						<div class="form-group col-lg-8">
							<label for="titulo056_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.titulo"/>:</label>
							<input type="text" name="titulo" class="form-control" id="titulo056_detail_table" maxlength="150"/>
						</div>
						<div class="form-group col-lg-4" id="indVisible056_detail_table_div">
							<label for="indVisible056_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.visibleGestor"/> (*):</label>
							<input type="checkbox" name="indVisible" class="form-control" id="indVisible056_detail_table" value="S" data-switch-pestana="true"/>
						</div>
					</div>	
					<div id="capaFicheroItzulpena" class="row">
						<div id="capaFicheroItzulpenaDetach">
							<input type="hidden" name="ficheros[0].rutaPif" id="rutaPif_0"/>
							<input type="hidden" name="ficheros[0].nombre" id="nombre_0"/>
							<input type="hidden" name="ficheros[0].extension" id="extensionDoc056_0" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].tamano" id="tamanoDoc056_0" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].contentType" id="contentType056_0" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].encriptado" id="indEncriptado056_0"/>
							<input type="hidden" name="ficheros[0].fechaAlta" id="fechaAlta056_0" maxlength="15" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].dniAlta" id="dniAlta056_0" maxlength="15" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].idDocRel" id="idDocRel056_0" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].idDocVersion" id="idDocVersion056_0" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].oid" id="oidFichero056_0" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].idDocInsertado" id="idDocInsertado_0" readonly="readonly"/>
												
							<div class="form-group col-lg-12 padding-top-2">	
								<span id="enlaceDescargaDetalle_0"></span>						
								<button id="pidButton_0" type="button" class="ui-button ui-corner-all ui-widget" style="margin-top:-4px"><spring:message code="label.adjuntarFichero"/></button>
								<input type="text" name="ficheros[0].nombreUpload" class="form-control" id="nombreFicheroInfo_0" readonly="readonly" style="width:200px; display:inline-block" />
							</div>
						</div>
					</div>
					<div class="row">
						<div id="capaDetClasificacion" class="form-group col-lg-2" style="clear: left;">
							<label for="claseDocumento056_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.clasificacion"/> (*):</label>
							<select name="claseDocumento" class="form-control" id="claseDocumento056_detail_table"></select>						
						</div>
						<div id="capaDetTipo">
							<div id="capaSoloTipo" class="form-group col-lg-3">
								<label for="tipoDocumento056_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.tipo"/> (*):</label>
								<select name="tipoDocumento" class="form-control" id="tipoDocumento056_detail_table"></select>
							</div>
							<div class="form-group col-lg-2" class="control-label">
								<label for="numPalIzo056_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.numPalabrasIZO"/> (*):</label>
								<input type="text" name="numPalIzo" class="form-control numeric" id="numPalIzo056_detail_table" maxlength="6" style="width:100px"/>
							</div>	
							<div id="capaRangos">
								<div class="form-group col-md-7 p-0">
									<label class="control-label col-lg-12 no-padding" data-i18n="comun.tramosConcordancia"><spring:message code="comun.tramosConcordancia" />:</label>
									<div class="form-group col-lg-3 p-0">
										<label for="numPalConcor084DocTrabajo" class="control-label col-md-5 p-0" data-i18n="comun.tramosConcor084"><spring:message code="comun.tramosConcor084" />:</label>
										<div class="form-group  col-md-5 col-xl-5 p-0">
											<input type="text" id="numPalConcor084DocTrabajo" name="numPalConcor084" class="form-control no-padding-left" />
										</div>
									</div>
									<div class="form-group col-lg-3 p-0">
										<label for="numPalConcor8594DocTrabajo" class="control-label col-md-5 p-0" data-i18n="comun.tramosConcor8594"><spring:message code="comun.tramosConcor8594" />:</label>
										<div class="form-group  col-md-5 col-xl-5 p-0">
											<input type="text" id="numPalConcor8594DocTrabajo" name="numPalConcor8594" class="form-control no-padding-left" />
										</div>
									</div>
									<div class="form-group col-lg-3 p-0">
										<label for="numPalConcor95100DocTrabajo" class="control-label col-md-6 p-0" data-i18n="comun.tramosConcor9599"><spring:message code="comun.tramosConcor9599" />:</label>
										<div class="form-group  col-md-5 col-xl-5 p-0">
											<input type="text" id="numPalConcor9599DocTrabajo" name="numPalConcor9599" class="form-control no-padding-left" />
										</div>
									</div>
									<div class="form-group col-lg-3 p-0">
										<label for="numPalConcor100DocTrabajo" class="control-label col-md-6 p-0" data-i18n="comun.tramosConcor100"><spring:message code="comun.tramosConcor100" />:</label>
										<div class="form-group  col-md-5 col-xl-5 p-0">
											<input type="text" id="numPalConcor100DocTrabajo" name="numPalConcor100" class="form-control no-padding-left" />
										</div>
									</div>
								</div>
							</div>	
						</div>	
						<div id="capaNumPalSolic" class="form-group col-lg-3" class="control-label">
							<label for="numPalSolic056_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.numPalabrasSolicitante"/> (*):</label>
							<input type="text" name="numPalSolic" class="form-control numeric" id="numPalSolic056_detail_table" readonly="readonly" style="width:100px"/>
						</div>	
					</div>
					
					<div id="capaFicheroItzulpenaContacto">
						<div id="capaFicheroItzulpenaContactoDetach">
							<fieldset>
								<legend class="fieldset_rojo"><spring:message code="label.documento.contactoAutor"/></legend>
								<div class="row">	
									<div class="form-group col-lg-4" class="control-label">
										<label for="personaContacto056_0"><spring:message code="label.nombreapellidos"/>:</label>
										<input type="text" name="ficheros[0].contacto.persona" class="form-control" id="personaContacto056_0" maxlength="150"/>
									</div>
									<div class="form-group col-lg-4" class="control-label">
										<label for="emailContacto056_0"><spring:message code="label.email"/>:</label>
										<input type="text" name="ficheros[0].contacto.email" class="form-control" id="emailContacto056_0" maxlength="256"/>
									</div>
									<div class="form-group col-lg-4" class="control-label">
										<label for="telefonoContacto056_0"><spring:message code="label.telefono"/>:</label>
										<input type="text" name="ficheros[0].contacto.telefono" class="form-control" id="telefonoContacto056_0" maxlength="15"/>
									</div>
								</div>
								<div class="row">	
									<div class="form-group col-lg-4" class="control-label">
										<label for="entidadContacto056_0"><spring:message code="label.entidad"/>:</label>
										<input type="text" name="ficheros[0].contacto.entidad" class="form-control" id="entidadContacto056_0" maxlength="150"/>
									</div>
									<div class="form-group col-lg-8" class="control-label">
										<label for="direccionContacto056_0"><spring:message code="label.dirServicio"/>:</label>
										<input type="text" name="ficheros[0].contacto.direccion" class="form-control" id="direccionContacto056_0" maxlength="256"/>
									</div>
								</div>
							</fieldset>
						</div>
						<div id="versionesApoyo">
							<div id="capaVersiones_0"></div>
						</div>
					</div>
					<div id="capaFicheroBerrikusketa">
						<div id="capaFicheroBerrikusketaDetach">
							<c:forEach begin="0" end="1" varStatus="status">
								<fieldset id="capaFichero_${status.index}">
									<legend id="legendFichero_${status.index}" class="fieldset_rojo"><c:if test="${status.index == 0}"><spring:message code="label.documentoOrigen"/></c:if><c:if test="${status.index == 1}"><spring:message code="label.documentoARevisar"/></c:if></legend>
									
									<input type="hidden" name="ficheros[${status.index}].rutaPif" id="rutaPif_${status.index}"/>
									<input type="hidden" name="ficheros[${status.index}].nombre" id="nombre_${status.index}"/>
									<input type="hidden" name="ficheros[${status.index}].extension" id="extensionDoc056_${status.index}" readonly="readonly"/>
									<input type="hidden" name="ficheros[${status.index}].tamano" id="tamanoDoc056_${status.index}" readonly="readonly"/>
									<input type="hidden" name="ficheros[${status.index}].contentType" id="contentType056_${status.index}" readonly="readonly"/>
									<input type="hidden" name="ficheros[${status.index}].encriptado" id="indEncriptado056_${status.index}"/>
									<input type="hidden" name="ficheros[${status.index}].fechaAlta" id="fechaAlta056_${status.index}" maxlength="15" readonly="readonly"/>
									<input type="hidden" name="ficheros[${status.index}].dniAlta" id="dniAlta056_${status.index}" maxlength="15" readonly="readonly"/>
									<input type="hidden" name="ficheros[${status.index}].idDocRel" id="idDocRel056_${status.index}" readonly="readonly"/>
									<input type="hidden" name="ficheros[${status.index}].idDocVersion" id="idDocVersion056_${status.index}" readonly="readonly"/>
									<input type="hidden" name="ficheros[${status.index}].oid" id="oidFichero056_${status.index}" readonly="readonly"/>
									<input type="hidden" name="ficheros[${status.index}].idDocInsertado" id="idDocInsertado_${status.index}" readonly="readonly"/>
														
									<div id="capaDivPid_${status.index}" class="form-group col-lg-12">	
										<span id="enlaceDescargaDetalle_${status.index}"></span>						
										<button id="pidButton_${status.index}" type="button" class="ui-button ui-corner-all ui-widget" style="margin-top:-4px"><spring:message code="label.adjuntarFichero"/></button>
										<input type="text" name="ficheros[${status.index}].nombreUpload" class="form-control" id="nombreFicheroInfo_${status.index}" readonly="readonly" style="width:200px; display:inline-block" />
									</div>
									
									<fieldset  class="capaDetContacto" style="clear:left">
										<legend class="fieldset_rojo"><spring:message code="label.documento.contactoAutor"/></legend>
										<div class="row">	
											<div class="form-group col-lg-4" class="control-label">
												<label for="personaContacto056_${status.index}"><spring:message code="label.nombreapellidos"/>:</label>
												<input type="text" name="ficheros[${status.index}].contacto.persona" class="form-control" id="personaContacto056_${status.index}" maxlength="150"/>
											</div>
											<div class="form-group col-lg-4" class="control-label">
												<label for="emailContacto056_${status.index}"><spring:message code="label.email"/>:</label>
												<input type="text" name="ficheros[${status.index}].contacto.email" class="form-control" id="emailContacto056_${status.index}" maxlength="256"/>
											</div>
											<div class="form-group col-lg-4" class="control-label">
												<label for="telefonoContacto056_${status.index}"><spring:message code="label.telefono"/>:</label>
												<input type="text" name="ficheros[${status.index}].contacto.telefono" class="form-control" id="telefonoContacto056_${status.index}" maxlength="15"/>
											</div>
										</div>
										<div class="row">	
											<div class="form-group col-lg-4" class="control-label">
												<label for="entidadContacto056_${status.index}"><spring:message code="label.entidad"/>:</label>
												<input type="text" name="ficheros[${status.index}].contacto.entidad" class="form-control" id="entidadContacto056_${status.index}" maxlength="150"/>
											</div>
											<div class="form-group col-lg-8" class="control-label">
												<label for="direccionContacto056_${status.index}"><spring:message code="label.dirServicio"/>:</label>
												<input type="text" name="ficheros[${status.index}].contacto.direccion" class="form-control" id="direccionContacto056_${status.index}" maxlength="256"/>
											</div>
										</div>
									</fieldset>
									<div id="capaVersiones_${status.index}"></div>
								</fieldset>
							</c:forEach>
						</div>
					</div>
					<!-- Fin campos del formulario de detalle -->
				</div>
			</form>
		</div>
		<!-- Botonera del formulario de detalle -->
		<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
			<div class="ui-dialog-buttonset">
				<!-- Botón Guardar -->
				<button id="documentosexpediente_detail_button_save" type="button">
					<spring:message code="save" />
				</button>
				<!-- Enlace cancelar -->
				<a href="javascript:void(0)" id="documentosexpediente_detail_link_cancel"
					class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
			</div>
		</div>
	</div>
	
	
	
	<div id="subidaFicheroPid" class="capaPIDenPestanaDoc oculto">
		<fieldset id="subidaFicheroPid_fieldset" >			
			<div id="subidaFicheroPid_feedback"></div>
			<form id="subidaFicheroPid_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
				<div class="form-group row">
					<label for="fichero" class="control-label col-md-3"><spring:message code="label.fichero" /> (*):</label>
					<div class="col-md-6" id="divFichero">
						<input type="file" name="fichero" id="fichero"  class="form-control">
					</div>
					<input type="hidden" id="idBotonUpload" name="idBotonUpload" />
					<input type="hidden" id="reqEncriptado" name="reqEncriptado" />
				</div>
			</form>
			<p class="scheduler-border" style="text-align: center;" id="txtEncriptado"><em><strong><spring:message code="mensaje.fichero.encriptadoRequerido" /></strong></em></p>
		</fieldset>
	</div>

</div>
