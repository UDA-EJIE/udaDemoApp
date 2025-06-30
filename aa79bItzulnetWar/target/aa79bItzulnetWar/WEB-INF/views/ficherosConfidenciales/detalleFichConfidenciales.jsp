<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="divDetalleFichConfi" class="container-fluid aa79b-fade in">
	<%@include file="/WEB-INF/views/cabeceraexpediente/cabeceraexpediente.jsp"%>
	<%@include file="/WEB-INF/views/bitacoraexpediente/bitacoraexpediente.jsp"%>
	<div id="detalleFichConfi_div" class="rup-table-container">
		<div id="detalleFichConfi_feedback"></div>						<!-- Feedback de la tabla --> 
		<div id="detalleFichConfi_toolbar"></div>						<!-- Botonera de la tabla --> 
		<div id="detalleFichConfi_filter_div"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="detalleFichConfi_filter_form" method="POST">
				<fieldset id="detalleFichConfi_filter_fieldset" class="form_fieldset ">
				<section>
			      	<div class="col-md-12" id="capaDocumentos">
			      		<input type="hidden" id="nombreFicheroAttach" name="nombre"/>
			      		<input type="hidden" id="tamanoFichero" name="tamano">
			      		<input type="hidden" id="contentTypeFichero" name="contentType">
			      		<input type="hidden" id="oidFichero" name="oid">
			      		<input type="hidden" id="idFichero" name="codigo">
			      		<!-- Utilizamos el campo encriptado para que viaje el valor de la contraseña, ya que -->
			      		<!-- es del mismo tipo y así evitamos definir un nuevo campo en el objeto Fichero -->
			      		<input type="hidden" id="password" name="encriptado">
						<c:forEach var="documento" items="${listaDocumentos}">
							<div class="row documento-all documento-all-fichConf" id="filaDoc${documento.idDoc}">
					            <div class="col-md-9 documento-first">
						              <p class="document-tit"><spring:message code="label.tipo"/>: <c:out value="${documento.claseDocumentoDesc}"></c:out></p> 
						              <a href="#" class="document-eusk descargarDoc" data-id="${documento.idDoc}">
						              	<span>
							                <c:if test="${documento.claseDocumento == '2' }">
							               		<spring:message code="label.documentoOrigen"/> - 
							               	</c:if>
							                <c:out value="${documento.titulo}"></c:out>
						                </span>
						                <span class="documento-archivo">(<spring:message code="label.fichero"/> <c:out value='${documento.ficheros[0].extension}, ${ documento.ficheros[0].tamanoKB }'></c:out> Kb.)</span>
										<c:choose>
						                	<c:when test="${documento.ficheros[0].encriptado == 'S'}">
							                	<input type="hidden" id="nombreFicheroAttach_${documento.idDoc}" name="nombreFicheroAttach_${documento.idDoc}" value="${documento.titulo}.${documento.ficheros[0].extension}"/>
							                	<input type="hidden" id="tamanoFichero_${documento.idDoc}" name="tamanoFichero_${documento.idDoc}" value="${documento.ficheros[0].tamano}"/>
							                	<input type="hidden" id="contentTypeFichero_${documento.idDoc}" name="contentTypeFichero_${documento.idDoc}" value="${documento.ficheros[0].contentType}"/>
							                	<input type="hidden" id="oidFichero_${documento.idDoc}" name="oidFichero_${documento.idDoc}" value="${documento.ficheros[0].oid}"/>
							                	<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.encrip'/>"><i class="fa fa-lock" aria-hidden="true"></i></span>
							                	<span id="desencriptar_button_${documento.idDoc}" class="rup-enlaceCancelar ui-button ui-widget ui-state-default ui-corner-all" style="float: none !important;line-height: 0px !important;">
													<spring:message code="label.desencriptar" />
												</span>
						                  	</c:when>
											<c:otherwise>
												<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.noencrip'/>"><i class="fa fa-unlock" aria-hidden="true"></i></span>
											</c:otherwise>	
						                 </c:choose> 
						              </a>
						              <c:if test="${documento.ficheros[1] != null}">
							              <a href="#" class="document-cast descargarDoc" data-id="${documento.ficheros[1].idDocInsertado}"> 
							              	<span>
							              		<spring:message code="label.documentoARevisar"/> - <c:out value="${documento.titulo}"></c:out>
							              	</span>
							                <span class="documento-archivo">(<spring:message code="label.fichero"/> <c:out value="${documento.ficheros[1].extension} , ${documento.ficheros[1].tamanoKB}"></c:out> Kb.)</span>
							                <c:choose>
							                	<c:when test="${documento.ficheros[1].encriptado == 'S'}">
								                	<input type="hidden" id="nombreFicheroAttach_${documento.ficheros[1].idDocInsertado}" name="nombreFicheroAttach_${documento.ficheros[1].idDocInsertado}" value="${documento.titulo}.${documento.ficheros[1].extension}"/>
								                	<input type="hidden" id="tamanoFichero_${documento.ficheros[1].idDocInsertado}" name="tamanoFichero_${documento.ficheros[1].idDocInsertado}" value="${documento.ficheros[1].tamano}"/>
								                	<input type="hidden" id="contentTypeFichero_${documento.ficheros[1].idDocInsertado}" name="contentTypeFichero_${documento.ficheros[1].idDocInsertado}" value="${documento.ficheros[1].contentType}"/>
								                	<input type="hidden" id="oidFichero_${documento.ficheros[1].idDocInsertado}" name="oidFichero_${documento.ficheros[1].idDocInsertado}" value="${documento.ficheros[1].oid}"/>
								                	<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.encrip'/>"><i class="fa fa-lock" aria-hidden="true"></i></span>
								                	<span id="desencriptar_button_${documento.ficheros[1].idDocInsertado}" class="rup-enlaceCancelar ui-button ui-widget ui-state-default ui-corner-all" style="float: none !important;line-height: 0px !important;">
														<spring:message code="label.desencriptar" />
													</span>
							                  	</c:when>
												<c:otherwise>
													<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.noencrip'/>"><i class="fa fa-unlock" aria-hidden="true"></i></span>
												</c:otherwise>	
							                 </c:choose> 		
							              </a>
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
								     	<a href="#" class="document-cast descargarDocGeneral contentMargin" data-id="${documento.documentoFinal.idDocInsertado}"> 
								     		<span><c:out value="${documento.documentoFinal.nombre}"></c:out></span>
							                <span class="documento-archivo">(<spring:message code="label.fichero"/> <c:out value="${documento.documentoFinal.extension} , ${documento.documentoFinal.tamanoKB}"></c:out> Kb.)</span>
							                <c:choose>
							                	<c:when test="${documento.documentoFinal.encriptado == 'S'}">
								                	<input type="hidden" id="nombreFicheroAttach_${documento.documentoFinal.idDocInsertado}" name="nombreFicheroAttach_${documento.documentoFinal.idDocInsertado}" value="${documento.titulo}.${documento.documentoFinal.extension}"/>
								                	<input type="hidden" id="tamanoFichero_${documento.documentoFinal.idDocInsertado}" name="tamanoFichero_${documento.documentoFinal.idDocInsertado}" value="${documento.documentoFinal.tamano}"/>
								                	<input type="hidden" id="contentTypeFichero_${documento.documentoFinal.idDocInsertado}" name="contentTypeFichero_${documento.documentoFinal.idDocInsertado}" value="${documento.documentoFinal.contentType}"/>
								                	<input type="hidden" id="oidFichero_${documento.documentoFinal.idDocInsertado}" name="oidFichero_${documento.documentoFinal.idDocInsertado}" value="${documento.documentoFinal.oid}"/>
								                	<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.encrip'/>"><i class="fa fa-lock" aria-hidden="true"></i></span>
								                	<span id="desencriptar_button_${documento.documentoFinal.idDocInsertado}" class="rup-enlaceCancelar ui-button ui-widget ui-state-default ui-corner-all" style="float: none !important;line-height: 0px !important;">
														<spring:message code="label.desencriptar" />
													</span>
							                  	</c:when>
												<c:otherwise>
													<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.noencrip'/>"><i class="fa fa-unlock" aria-hidden="true"></i></span>
												</c:otherwise>	
							                 </c:choose> 		
							              </a>
								     </c:if>
								     <c:if test="${documento.justificanteRevision != null}">
								     	<p class="document-tit marginTop5"><spring:message code="label.justificanteRevision"/></p> 
								     	<a href="#" class="document-cast descargarDocGeneral contentMargin" data-id="${documento.justificanteRevision.idDocInsertado}"> 
								     		<span><c:out value="${documento.justificanteRevision.nombre}"></c:out></span>
							                <span class="documento-archivo">(<spring:message code="label.fichero"/> <c:out value="${documento.justificanteRevision.extension} , ${documento.justificanteRevision.tamanoKB}"></c:out> Kb.)</span>
							                <c:choose>
							                	<c:when test="${documento.justificanteRevision.encriptado == 'S'}">
								                	<input type="hidden" id="nombreFicheroAttach_${documento.justificanteRevision.idDocInsertado}" name="nombreFicheroAttach_${documento.justificanteRevision.idDocInsertado}" value="${documento.justificanteRevision.nombre}.${documento.justificanteRevision.extension}"/>
								                	<input type="hidden" id="tamanoFichero_${documento.justificanteRevision.idDocInsertado}" name="tamanoFichero_${documento.justificanteRevision.idDocInsertado}" value="${documento.justificanteRevision.tamano}"/>
								                	<input type="hidden" id="contentTypeFichero_${documento.justificanteRevision.idDocInsertado}" name="contentTypeFichero_${documento.justificanteRevision.idDocInsertado}" value="${documento.justificanteRevision.contentType}"/>
								                	<input type="hidden" id="oidFichero_${documento.justificanteRevision.idDocInsertado}" name="oidFichero_${documento.justificanteRevision.idDocInsertado}" value="${documento.justificanteRevision.oid}"/>
								                	<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.encrip'/>"><i class="fa fa-lock" aria-hidden="true"></i></span>
								                	<span id="desencriptar_button_${documento.justificanteRevision.idDocInsertado}" class="rup-enlaceCancelar ui-button ui-widget ui-state-default ui-corner-all" style="float: none !important;line-height: 0px !important;">
														<spring:message code="label.desencriptar" />
													</span>
							                  	</c:when>
												<c:otherwise>
													<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.noencrip'/>"><i class="fa fa-unlock" aria-hidden="true"></i></span>
												</c:otherwise>	
							                 </c:choose> 		
							              </a>
								     </c:if>
								     <c:if test="${(documento.ficherosTraduccionesIntermedias != null) && (documento.ficherosTraduccionesIntermedias[0] != null)}">
								     	<p class="document-tit marginTop5"><spring:message code="label.documentosIntermedios"/></p> 
								     </c:if>
								     
								     <c:forEach var="documentoIntermedio" items="${documento.ficherosTraduccionesIntermedias}">
								     	
								     	<a href="#" class="document-cast descargarDocGeneral contentMargin" data-id="${documentoIntermedio.idDocInsertado}"> 
								     		<span><c:out value="${documentoIntermedio.tituloAux}"></c:out></span>
							                <span class="documento-archivo">(<spring:message code="label.fichero"/> <c:out value="${documentoIntermedio.extension} , ${documentoIntermedio.tamanoKB}"></c:out> Kb.)</span>
							                <c:choose>
							                	<c:when test="${documentoIntermedio.encriptado == 'S'}">
								                	<input type="hidden" id="nombreFicheroAttach_${documentoIntermedio.idDocInsertado}" name="nombreFicheroAttach_${documentoIntermedio.idDocInsertado}" value="${documento.titulo}.${documentoIntermedio.extension}"/>
								                	<input type="hidden" id="tamanoFichero_${documentoIntermedio.idDocInsertado}" name="tamanoFichero_${documentoIntermedio.idDocInsertado}" value="${documentoIntermedio.tamano}"/>
								                	<input type="hidden" id="contentTypeFichero_${documentoIntermedio.idDocInsertado}" name="contentTypeFichero_${documentoIntermedio.idDocInsertado}" value="${documentoIntermedio.contentType}"/>
								                	<input type="hidden" id="oidFichero_${documentoIntermedio.idDocInsertado}" name="oidFichero_${documentoIntermedio.idDocInsertado}" value="${documentoIntermedio.oid}"/>
								                	<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.encrip'/>"><i class="fa fa-lock" aria-hidden="true"></i></span>
								                	<span>(<c:out value="${documentoIntermedio.descTarea}"></c:out>)</span>
								                	<span id="desencriptar_button_${documentoIntermedio.idDocInsertado}" class="rup-enlaceCancelar ui-button ui-widget ui-state-default ui-corner-all" style="float: none !important;line-height: 0px !important;">
														<spring:message code="label.desencriptar" />
													</span>
							                  	</c:when>
												<c:otherwise>
													<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.noencrip'/>"><i class="fa fa-unlock" aria-hidden="true"></i></span>
												</c:otherwise>	
							                 </c:choose> 		
							              </a>
								     </c:forEach>
								     
								     <c:if test="${(documento.justificantesRevisionIntermedias != null) && (documento.justificantesRevisionIntermedias[0] != null)}">
								     	<p class="document-tit marginTop5"><spring:message code="label.justificantesIntermedios"/></p> 
								     </c:if>
								     
								     <c:forEach var="documentoIntermedio" items="${documento.justificantesRevisionIntermedias}">
								     	
								     	<a href="#" class="document-cast descargarDocGeneral contentMargin" data-id="${documentoIntermedio.idDocInsertado}"> 
								     		<span><c:out value="${documentoIntermedio.tituloAux}"></c:out></span>
							                <span class="documento-archivo">(<spring:message code="label.fichero"/> <c:out value="${documentoIntermedio.extension} , ${documentoIntermedio.tamanoKB}"></c:out> Kb.)</span>
							                <c:choose>
							                	<c:when test="${documentoIntermedio.encriptado == 'S'}">
								                	<input type="hidden" id="nombreFicheroAttach_${documentoIntermedio.idDocInsertado}" name="nombreFicheroAttach_${documentoIntermedio.idDocInsertado}" value="${documento.titulo}.${documentoIntermedio.extension}"/>
								                	<input type="hidden" id="tamanoFichero_${documentoIntermedio.idDocInsertado}" name="tamanoFichero_${documentoIntermedio.idDocInsertado}" value="${documentoIntermedio.tamano}"/>
								                	<input type="hidden" id="contentTypeFichero_${documentoIntermedio.idDocInsertado}" name="contentTypeFichero_${documentoIntermedio.idDocInsertado}" value="${documentoIntermedio.contentType}"/>
								                	<input type="hidden" id="oidFichero_${documentoIntermedio.idDocInsertado}" name="oidFichero_${documentoIntermedio.idDocInsertado}" value="${documentoIntermedio.oid}"/>
								                	<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.encrip'/>"><i class="fa fa-lock" aria-hidden="true"></i></span>
								                	<span>(<c:out value="${documentoIntermedio.descTarea}"></c:out>)</span>
								                	<span id="desencriptar_button_${documentoIntermedio.idDocInsertado}" class="rup-enlaceCancelar ui-button ui-widget ui-state-default ui-corner-all" style="float: none !important;line-height: 0px !important;">
														<spring:message code="label.desencriptar" />
													</span>
							                  	</c:when>
												<c:otherwise>
													<span class="ico-ficha-encriptado" title="<spring:message code='label.documento.noencrip'/>"><i class="fa fa-unlock" aria-hidden="true"></i></span>
												</c:otherwise>	
							                 </c:choose> 		
							              </a>
								     </c:forEach>
								     
					            </div>
					        </div>
						</c:forEach>
						
			       	</div>
				</section>
				</fieldset>
			</form>
		</div>
	</div>
	
	<div id="passwordFichero">
		<fieldset id="passwordFichero_fieldset">
			<legend class="scheduler-border"
				data-i18n="label.introduceContrasenia">
				<spring:message code="label.introduceContrasenia" />
			</legend>
			<div id="passwordFichero_feedback"></div>
			<form id="passwordFichero_form" class="form-horizontal">
				<div class="form-group">
					<label for="passwordEncriptar" class="control-label col-md-3"
						data-i18n="label.contrasenia"><spring:message
							code="label.contrasenia" /> (*):</label> <input type="password"
						id="passwordEncriptar" name="passwordEncriptar"
						class="form-control col-md-3">
				</div>
				<!-- Botonera del formulario de detalle -->
				<div class="pull-right" style="margin-top: 8px;">

					<button id="enviarPasswordButton" type="button"
						class="ui-button ui-widget ui-state-default ui-corner-all"
						style="margin-bottom: 0px !important;">
						<spring:message code="aceptar" />
					</button>


					<!-- Enlace cancelar -->
					<a href="javascript:void(0)" id="cancelarPasswordButton"
						class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
				</div>
			</form>
		</fieldset>
	</div>
</div>