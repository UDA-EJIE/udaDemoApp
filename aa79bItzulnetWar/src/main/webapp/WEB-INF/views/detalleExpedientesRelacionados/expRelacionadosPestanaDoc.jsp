<%@page import="com.ejie.aa79b.model.enums.ClasificacionDocumentosEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoRequerimientoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%> 

<div id="capaPestanaCompletaDoc" class="container-fluid  aa79b-fade in" style="padding:0 1.5rem">
<!-- Formulario de detalle de la T53 -->
	<div id="datosExpedienteTradRev_div">
		<div id ="datosExpedienteConsulta_navigation"></div>			<!-- Barra de navegación del detalle -->
			<form id="datosExpedienteConsulta_form">					<!-- Formulario -->
				<div id ="datosExpedienteConsulta_feedback"></div>		<!-- Feedback del formulario de detalle -->			
				
					<!-- Campos del formulario de detalle -->
					<input type="hidden" name="anyo" id="anyo_exp" />
					<input type="hidden" name="numExp" id="numExp_exp" />
					
					<div id="datosTradRevDivDocConsulta" style="display:none;">
						<div class="row">
						<div class="form-group col-xs-6 col-lg-3">
							<label for="idRelevancia" class="no-padding"><spring:message code="label.relevancia"/> (*):</label>
							<div class="divComboW125">
								<input type="text" name="idRelevancia" class="form-control" id="idRelevancia_exp"/>
							</div>
						</div>	
						<div class="form-group col-xs-6 col-lg-3">
							<label for="indFacturable" class="no-padding"><spring:message code="label.expedienteFacturable"/> (*):</label>
							<div><input type="checkbox" name="indFacturable" class="form-control" id="indFacturable_exp" value="S" data-switch-pestana="true"/></div>
						</div>
						<div class="form-group col-xs-6 col-lg-3">
							<label for="indUrgente_exp" class="no-padding"><spring:message code="label.urgente"/> (*):</label>
							<div><input type="checkbox" name="indUrgente" class="form-control" id="indUrgente_exp" value="S" data-switch-pestana="true"/></div>
						</div>	
						<div id="capaRequierePpto" class="form-group col-xs-6 col-lg-3 no-padding-left no-padding-right" style="width: 180px;">
							<label for="indPresupuesto" class="no-padding"><spring:message code="label.requierePresupuesto"/> (*):</label>
							<div><input type="checkbox" name="indPresupuesto" class="form-control" id="indPresupuesto_exp" value="S" data-switch-pestana="true"/></div>
						</div>		
					</div>
					
					<div class="row">	
						<div class="form-group col-xs-6 col-lg-3" style="clear: left;">
							<label for="numTotalPalIzo_exp" class="no-padding"><spring:message code="label.documento.numTotalPalIzo"/> (*):</label>						
							<input type="text" name="numTotalPalIzo" class="form-control numeric" id="numTotalPalIzo_exp" maxlength="6" style="width: 80px" />
						</div>	
						<div class="form-group col-md-12 col-lg-3 grupoFechaHora">
			                <label class="valFecha" for="fechaFinalIzo"><spring:message code="label.fechaHoraFinalIzo"/> (*):</label>
			                <input type="text" name="fechaFinalIzo" class="form-control" id="fechaFinalIzo_exp"/>
			                <input type="text" name="horaFinalIzo" class="form-control campohora" id="horaFinalIzo_exp" placeholder="hh:mm" maxlength="5"/>
			            </div>
			             <div class="form-group col-xs-6 col-lg-3">
							<label for="indDificil_exp" class="no-padding"><spring:message code="label.recargoPorDificultad"/> (*):</label>
							<div><input type="checkbox" name="indDificil" class="form-control" id="indDificil_exp" value="S" data-switch-pestana="true"/></div>
						</div>
					</div>
					<input type="hidden" name="palPresupuesto" id="palPresupuesto" value="${palPresupuesto}" />
					<c:if test="${'S' eq datosFactYPresup.indFacturable }">
						<div id="datosFacturacionPestanaDocConsulta" class="row">
							<div class="form-group col-lg-12">
								<fieldset>
									<legend>
										<spring:message code="comun.palabrasFacturar"/>
									</legend>
							<div class="form-group col-md-4 col-xl-3 ">
							<label for="numTotalPalFacturar" class="control-label col-md-12 p-0" data-i18n="comun.palabrasFacturar"><spring:message code="comun.palabrasFacturar" />:</label>
								<div class="form-group  col-md-4 col-xl-4 p-0">
									<input type="text" id="numTotalPalFacturar" name="numTotalPalFacturar" class="form-control" value="${datosFactYPresup.datosFacturacionExpediente.numTotalPalFacturar }"/>
								</div>
							</div>
							
							<div class="form-group col-md-6 p-0">
								<label class="control-label col-lg-12 no-padding" data-i18n="comun.tramosConcordancia"><spring:message code="comun.tramosConcordancia" />:</label>
								<div class="form-group col-lg-4 p-0">
									<label for="numPalConcor084" class="control-label col-md-5 p-0" data-i18n="comun.tramosConcor1"><spring:message code="comun.tramosConcor1" />:</label>
									<div class="form-group  col-md-5 col-xl-3 p-0">
										<input type="text" id="numPalConcor084" name="numPalConcor084" class="form-control no-padding-left" value="${datosFactYPresup.datosFacturacionExpediente.numPalConcor084 }" />
									</div>
								</div>
								<div class="form-group col-lg-4 p-0">
									<label for="numPalConcor8594" class="control-label col-md-5 p-0" data-i18n="comun.tramosConcor2"><spring:message code="comun.tramosConcor2" />:</label>
									<div class="form-group  col-md-5 col-xl-3 p-0">
										<input type="text" id="numPalConcor8594" name="numPalConcor8594" class="form-control no-padding-left" value="${datosFactYPresup.datosFacturacionExpediente.numPalConcor8594 }"/>
									</div>
								</div>
								<div class="form-group col-lg-4 p-0">
									<label for="numPalConcor95100" class="control-label col-md-6 p-0" data-i18n="comun.tramosConcor3"><spring:message code="comun.tramosConcor3" />:</label>
									<div class="form-group  col-md-5 col-xl-3 p-0">
										<input type="text" id="numPalConcor95100" name="numPalConcor95100" class="form-control no-padding-left" value="${datosFactYPresup.datosFacturacionExpediente.numPalConcor95100 }" />
									</div>
								</div>
							</div>
							</fieldset>
							</div>
						</div>
					</c:if>
					<div class="row oculto" id="capaAceptacionFechaFin">
						<div class="form-group col-lg-12">
							<fieldset>
								<legend>
									<spring:message code="label.negociarNuevaFechaEntrega"/>
								</legend>
								<div class="form-group col-sm-12 col-md-4 col-lg-4 grupoFechaHora">
									<label class="control-label valFecha" for="nuevaFechaIZO"><spring:message code="label.fechaHoraFinalIzoProp"/>:</label>
									<input type="text" name="aceptacionFechaHora.fechaEntrega" class="form-control" id="nuevaFechaIZO"/>
					                <input type="text" name="aceptacionFechaHora.horaEntrega" class="form-control campohora" id="nuevaHoraIZO" placeholder="hh:mm" maxlength="5"/>
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
					<c:if test="${'S' eq datosFactYPresup.indPresupuesto }">
						<div id="datosPresupuestoPestanaDocConsulta" class="row " id="capaAceptacionPresupesto" >
							<div class="form-group col-lg-12">
								<fieldset>
									<legend>
										<spring:message code="label.pptoAceptacionFechaHora"/>
									</legend>
									<div class="form-group col-sm-12 col-md-4 col-lg-3 grupoFechaHora">
						                <label class="control-label valFecha" for="datosPresupuestoPestanaDocConsultaFechaLimite"><spring:message code="label.fechaHoraLimiteAceptacion"/> (*):</label>
						                <input type="text" name="aceptacionPresupuesto.fechaLimite" class="form-control" id="datosPresupuestoPestanaDocConsultaFechaLimite" value="${datosFactYPresup.aceptacionPresupuesto.fechaLimite}"/>
						                <input type="text" name="aceptacionPresupuesto.horaLimite" class="form-control campohora" id="horaLimite" placeholder="hh:mm" maxlength="5"value="${datosFactYPresup.aceptacionPresupuesto.horaLimite}"/>
						            </div>
						            
									<div class="form-group col-sm-12 col-md-4 col-lg-3 col-xl-2">
										<label for="datosPresupuestoPestanaDocConsultaEstadoPpto" class="control-label "><spring:message code="label.estadoAceptacion"/>:</label>
										<select name="aceptacionPresupuesto.estado" class="form-control" id="datosPresupuestoPestanaDocConsultaEstadoPpto" >
											<option value="<%=EstadoRequerimientoEnum.PENDIENTE.getValue()%>" <c:if test="${datosFactYPresup.aceptacionPresupuesto.estado==1}">selected="selected"</c:if>><spring:message code="<%=EstadoRequerimientoEnum.PENDIENTE.getLabel()%>"/></option>
											<option value="<%=EstadoRequerimientoEnum.ACEPTADA.getValue()%>"  <c:if test="${datosFactYPresup.aceptacionPresupuesto.estado==2}">selected="selected"</c:if>><spring:message code="<%=EstadoRequerimientoEnum.ACEPTADA.getLabel()%>"/></option>
											<option value="<%=EstadoRequerimientoEnum.RECHAZADA.getValue()%>" <c:if test="${datosFactYPresup.aceptacionPresupuesto.estado==3}">selected="selected"</c:if>><spring:message code="<%=EstadoRequerimientoEnum.RECHAZADA.getLabel()%>"/></option>
										</select>
									</div>
							
								</fieldset>
							</div>
						</div>
					</c:if>
					</div>
					<div id="datosInterDivDocConsulta">
						<c:if test="${not empty datosFactYPresupInter}">
						<div id="datosPresupuestoInterPestanaDocConsultaInter" class="row " >
							<div class="form-group col-lg-12">
								<fieldset>
									<legend>
										<spring:message code="label.aceptacionPresupuesto"/>
									</legend>
									<div class="form-group col-sm-12 col-md-4 col-lg-3 grupoFechaHora">
						                <label class="control-label valFecha" for="datosPresupuestoPestanaDocConsultaFechaLimite"><spring:message code="label.fechaHoraLimiteAceptacion"/> (*):</label>
						                <input type="text" name="aceptacionPresupuesto.fechaLimite" class="form-control" id="datosPresupuestoPestanaDocConsultaFechaLimiteInter" value="${datosFactYPresupInter.fechaLimite}"/>
						                <input type="text" name="aceptacionPresupuesto.horaLimite" class="form-control campohora" id="datosPresupuestoPestanaDocConsultaHoraLimiteInter" placeholder="hh:mm" maxlength="5"value="${datosFactYPresupInter.horaLimite}"/>
						            </div>
						            
									<div class="form-group col-sm-12 col-md-4 col-lg-3 col-xl-2">
										<label for="datosPresupuestoPestanaDocConsultaEstadoPptoInter" class="control-label "><spring:message code="label.estadoAceptacion"/>:</label>
										<select name="aceptacionPresupuesto.estado" class="form-control" id="datosPresupuestoPestanaDocConsultaEstadoPptoInter" >
											<option value="<%=EstadoRequerimientoEnum.PENDIENTE.getValue()%>" <c:if test="${datosFactYPresupInter.estado==1}">selected="selected"</c:if>><spring:message code="<%=EstadoRequerimientoEnum.PENDIENTE.getLabel()%>"/></option>
											<option value="<%=EstadoRequerimientoEnum.ACEPTADA.getValue()%>"  <c:if test="${datosFactYPresupInter.estado==2}">selected="selected"</c:if>><spring:message code="<%=EstadoRequerimientoEnum.ACEPTADA.getLabel()%>"/></option>
											<option value="<%=EstadoRequerimientoEnum.RECHAZADA.getValue()%>" <c:if test="${datosFactYPresupInter.estado==3}">selected="selected"</c:if>><spring:message code="<%=EstadoRequerimientoEnum.RECHAZADA.getLabel()%>"/></option>
										</select>
									</div>
							
								</fieldset>
							</div>
						</div>
					</c:if>
					</div>
					
					<!-- Fin campos del formulario de detalle T53-->
			</form>
	</div>
	
	<section>
	    <div>
	      <div class="row document-cabecera"></div>
	      <div class="row document-content ">
	        <div class="col-md-10" id="capaDocumentos">
	        
				<c:forEach var="documento" items="${listaDocumentos}">
					<div class="row documento-all" id="filaDoc${documento.idDoc}">
			            <div class="documento-first" style="border-radius: 8px;">
				              <p class="document-tit"><spring:message code="label.tipo"/>: <c:out value="${documento.claseDocumentoDesc}"></c:out></p>
				              <c:if test="${documento.claseDocumento == '1' || documento.claseDocumento == '2' }">
					                <div class="displayAsTable">
				                		<p class="document-tipo" style="display: table-cell; padding-right: 5px;">
				                			<spring:message code="label.documentoOrigen"/> - 
				                		</p>
				               	</c:if>
				               <p class="document-fileAndIcon">
				              <a href="#" class="document-eusk descargarDoc iconSameLine" data-id="${documento.idDoc}"> <c:out value="${documento.ficheros[0].nombre}"></c:out>
				                <span class="documento-archivo">(<c:out value="${documento.ficheros[0].tamanoKB}"></c:out> Kb.)</span>
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
				              <c:if test="${documento.documentoOriginalFirmado != null && documento.documentoOriginalFirmado.idDocInsertado != null}">
				              	<a href="#" class="document-cast descargarDocGeneral contentMargin firma" data-id="${documento.documentoOriginalFirmado.idDocInsertado}"><i class="fa fa-pencil-square-o" aria-hidden="true" style="vertical-align: middle;"></i> <spring:message code='label.documento.verFirma'/></a>				              	
				              </c:if>
					           <c:if test="${documento.claseDocumento == '1' || documento.claseDocumento == '2' }">
					           	</div>
					           </c:if>
				              <c:if test="${documento.ficheros[1] != null}">
					              <div class="displayAsTable">
			                		<p class="document-tipo" style="display: table-cell; padding-right: 5px;">
			                			 <spring:message code="label.documentoARevisar"/> -  
			                		</p>
		                			<p class="document-fileAndIcon">	
		                			<a href="#" class="document-cast descargarDoc iconSameLine" data-id="${documento.ficheros[1].idDocInsertado}"> <c:out value="${documento.ficheros[1].nombre}"></c:out>
					                <span class="documento-archivo">( <c:out value="${documento.ficheros[1].tamanoKB}"></c:out> Kb.)</span>	
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
						            	<a href="#" class="document-cast descargarDocGeneral iconSameLine" data-id="${documento.documentoOriginalFinal.idDocInsertado}"> <c:out value="${documento.documentoOriginalFinal.nombre}"></c:out>
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
						              	<a href="#" class="document-cast descargarDocGeneral contentMargin firma" data-id="${documento.documentoOriginalFirmado.idDocInsertado}"><i class="fa fa-pencil-square-o" aria-hidden="true" style="vertical-align: middle;"></i> <spring:message code='label.documento.verFirma'/></a>				              	
						              </c:if>
						              </p>
					            </c:if>
					            <p class="document-fileAndIcon">
							     	<a href="#" class="document-cast descargarDocGeneral iconSameLine" data-id="${documento.documentoFinal.idDocInsertado}"> <c:out value="${documento.documentoFinal.nombre}"></c:out>
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
					              	<a href="#" class="document-cast descargarDocGeneral contentMargin firma" data-id="${documento.documentoFinalFirmado.idDocInsertado}"><i class="fa fa-pencil-square-o" aria-hidden="true" style="vertical-align: middle;"></i> <spring:message code='label.documento.verFirma'/></a>				              	
					              </c:if>
					              </p>
						     </c:if>
						     
						     <c:if test="${documento.justificanteRevision != null}">
						     	<p class="document-tit marginTop5"><spring:message code="label.justificanteRevision"/></p> 
						     	<p class="document-fileAndIcon">
						     		<a href="#" class="document-cast descargarDocGeneral" data-id="${documento.justificanteRevision.idDocInsertado}"> <c:out value="${documento.justificanteRevision.nombre}"></c:out>
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
	     	<div class="col-md-2 document-content-right">
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
</div> 
<!-- Formulario de detalle de DOCUMENTO -->
	<div id="documentosexpediente_detail_div" class="aa79b-content-modal " style="display:none;">
		<div id ="documentosexpediente_detail_navigation"></div>			<!-- Barra de navegación del detalle -->
		<div >
			<form id="documentosexpedienteConsulta_detail_form">					<!-- Formulario -->
				<div id ="documentosexpedienteConsulta_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
				<div class="col-xs-12">
				
					<!-- Campos del formulario de detalle -->
					<input type="hidden" name="idDoc" class="form-control" id="idDoc056_detail_table" readonly="readonly"/>
					
					<input type="hidden" name="anyo" class="form-control" id="anyo056Consulta_detail_table" readonly="readonly"/>
					<input type="hidden" name="numExp" class="form-control" id="numExp056Consulta_detail_table" readonly="readonly"/>
					
					<div class="row">
						<div class="form-group col-lg-8">
							<label for="titulo056Consulta_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.titulo"/>:</label>
							<input type="text" name="titulo" class="form-control" id="titulo056Consulta_detail_table" maxlength="150"/>
						</div>
						<div class="form-group col-lg-4">
							<label for="indVisible056Consulta_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.visibleGestor"/> (*):</label>
							<input type="checkbox" name="indVisible" class="form-control" id="indVisible056Consulta_detail_table" value="S" data-switch-pestana="true"/>
						</div>
					</div>	
					<div id="capaFicheroItzulpena" class="row">
						<div id="capaFicheroItzulpenaDetach">
							<input type="hidden" name="ficheros[0].nombre" id="nombreConsulta"/>
							<input type="hidden" name="ficheros[0].extension" id="extensionDoc056Consulta" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].tamano" id="tamanoDoc056Consulta" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].contentType" id="contentType056Consulta" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].encriptado" id="indEncriptado056Consulta"/>
							<input type="hidden" name="ficheros[0].oid" id="oidFichero056Consulta" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].tipoDocumento" id="tipoDocumento" readonly="readonly"/>
							<input type="hidden" name="ficheros[0].idDocInsertado" id="idDocInsertadoConsulta" readonly="readonly"/>
												
							<div class="form-group col-lg-12 padding-top-2">	
								<span id="enlaceDescargaDetalleConsulta"></span>						
								<button id="pidButtonConsulta" type="button" class="ui-button ui-corner-all ui-widget" style="margin-top:-4px"><spring:message code="label.adjuntarFichero"/></button>
								<input type="text" name="ficheros[0].nombreUpload" class="form-control" id="nombreFicheroInfoConsulta" readonly="readonly" style="width:200px; display:inline-block" />
							</div>
						</div>
					</div>
					<div class="row">
						<div id="capaDetClasificacionConsulta" class="form-group col-lg-2" style="clear: left;">
							<label for="claseDocumentoCombo_detail_table" class="control-label col-xs-12 no-padding"><spring:message code="label.documento.clasificacion"/> (*):</label>
							<select name="claseDocumento" class="form-control" id="claseDocumentoCombo_detail_table"></select>						
						</div>
					<!-- Fin campos del formulario de detalle -->
				</div>
			</form>
		</div>
		<!-- Botonera del formulario de detalle -->
		<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
			<div class="ui-dialog-buttonset">
				<!-- Botón Guardar -->
				<button id="documentosexpedienteConsulta_detail_button_save" type="button">
					<spring:message code="save" />
				</button>
				<!-- Enlace cancelar -->
				<a href="javascript:void(0)" id="documentosexpedienteConsulta_detail_link_cancel"
					class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
			</div>
		</div>
	</div>
	<div id="subidaFicheroPidConsulta" class="capaPIDenPestanaDoc oculto">
		<fieldset id="subidaFicheroPidConsulta_fieldset" >			
			<div id="subidaFicheroPidConsulta_feedback"></div>
			<form id="subidaFicheroPidConsulta_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
				<div class="form-group row">
					<label for="fichero" class="control-label col-md-3"><spring:message code="label.fichero" /> (*):</label>
					<div class="col-md-6" id="divFichero">
						<input type="file" name="fichero" id="ficheroConsulta"  class="form-control">
					</div>
					<input type="hidden" id="idBotonUploadConsulta" name="idBotonUpload" />
					<input type="hidden" id="reqEncriptadoConsulta" name="reqEncriptado" />
				</div>
			</form>
			<p class="scheduler-border" style="text-align: center;" id="txtEncriptado"><em><strong><spring:message code="mensaje.fichero.encriptadoRequerido" /></strong></em></p>
		</fieldset>
	</div>
