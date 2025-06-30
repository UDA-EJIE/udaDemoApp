<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.PuntosMenuEnum"%>
<%@page import="com.ejie.aa79b.model.enums.AccionesEnum"%>
<div id="bitacora_expediente">
	<div class="row bitacora-cabecera">
		<div class="col-md-7 col-lg-8 col-xl-9">
			<span class="ico-bitacora"> <i class="fa fa-indent"
				aria-hidden="true"></i>
			</span>
			<p class="cabecera-bitacora-scroll"><spring:message code="bitacora.titulo"/></p>
		</div>
		<div class="col-md-5 col-lg-4 col-xl-3 cabecera-second pull-right" style="display: flex;justify-content: right;align-items: center;">
			<span id="addRegistro_button" class="ico-bitacora-print right-line"> 
				<i class="fa fa-plus fa-x2" aria-hidden="true"></i>
			</span> 
			<a id="collapseBitacora_button" class="right-line" data-toggle="collapse" data-parent="#accordion" href="#collapseBitacora" style="overflow: visible !important;max-height: 100% !important;">
				<span class="ico-bitacora-options"> 
					<i class="fa fa-angle-down fa-x2" aria-hidden="true"></i>
				</span>
			</a>
			<span style="padding-top:1px">
				<a id="notasExpediente_button" href="javascript:void(0)" class="notaBitacora"><spring:message code="bitacora.notas" /></a>
				<a id="comunicacionesExpediente_button" href="javascript:void(0)" style="color: #ba1944;padding: 10px 5px 10px 0px;" class="mayuscula oculto"><spring:message code="bitacora.comunicaciones" /></a>
		    </span>
		</div>
	</div>
	<div class="panel-collapse collapse" id="collapseBitacora">
		<div class="row bitacora-content">
			<div id="bitacoraExpediente_div" class="bitacora-scroll scrollmenu ">
			</div>
		</div>
	</div>

	<div id="addRegistro_dialog" class="rup-table-formEdit-detail">
		<div id="addRegistro_detail_navigation"></div>
		<!-- Barra de navegación del detalle -->
		<div class="ui-dialog-content ui-widget-content">
			<form id="addRegistro_form">
				<input type="hidden" name="idPuntoMenu" value="<%=PuntosMenuEnum.TRAMITACION_EXPEDIENTES.getValue() %>"  />
				<input type="hidden" name="idAccion" value="<%=AccionesEnum.MANUAL.getValue() %>" />
				<input type="hidden" name="idEstadoBitacora" id="idEstadoBitacora_bitacora" />
				<input type="hidden" name="anyo" id="anyoExp_bitacora" />
				<input type="hidden" name="numExp" id="numExp_bitacora" />
				<div class="row">
					<div class="form-group col-lg-12">
						<label for="observ" class="control-label"><spring:message code="label.detalle.accion"/>(*):</label> 
						<textarea name="observ" class="form-control resizable-textarea" id="observ" rows="1" cols="9" maxlength="2000"></textarea>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div id="listaComunicaciones_dialog" class="rup-table-formEdit-detail" style="padding:0px">
		<div id="listaComunicaciones_detail_navigation"></div>
		<!-- Barra de navegacion del detalle -->
		<div class="ui-dialog-content ui-widget-content">
			<div id="listaComunicaciones_div" class="rup-table-container">
				<div id="listaComunicaciones_feedback"></div>
				<div id="listaComunicaciones_toolbar"></div>
				<div id="listaComunicaciones_filter_div"> 
					<form id="listaComunicaciones_filter_form">
						<div id="listaComunicaciones_filter_toolbar" class="formulario_legend oculto"></div>
						<fieldset id="listaComunicaciones_filter_fieldset" class="mt-1 mb-1" style="border:none">
							<input type="hidden" name="anyo" id="anyoExp_comunic_bitacora" />
							<input type="hidden" name="numExp" id="numExp_comunic_bitacora" />
							<input type="hidden" name="refTramitagune" id="refTramitagune_comunic_bitacora" />
<!-- 							<div id="listaComunicaciones_filter_buttonSet" class="pull-right oculto" style="margin:0 1rem 1rem 0"> -->
<%-- 								<input id="estudioExpedientes_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' /> --%>
								
<!-- 								<button id="listaComunicaciones_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all"> -->
<%-- 									<spring:message code="filter" /> --%>
<!-- 								</button> -->
<%-- 								<a id="listaComunicaciones_filter_cleanLinkModificado" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a> --%>
<!-- 							</div> -->
							
							<div class="col-md-3">
								<input type="radio" name="tipoBusqueda" id="opcionBusqueda_E" value="E" checked/>
								<label for="opcionBusqueda_E" class="radio-inline rup-table-filter-fieldset"><spring:message code="label.comunicaciones.esteExpediente" /></label>
							</div>
							<div class="col-md-5">
								<input type="radio" name="tipoBusqueda" id="opcionBusqueda_R" value="R"/>
								<label for="opcionBusqueda_R" class="radio-inline rup-table-filter-fieldset"><spring:message code="label.comunicaciones.expRelacionados" /></label>
							</div>
						</fieldset>
					</form>
				</div>		
<!-- 				Tabla -->
				<div class="table pb-2">
					<table id="listaComunicaciones"></table>
				</div>
<!-- 				Barra de paginacion -->
				<div id="listaComunicaciones_pager"></div>
			</div>
		</div>
	</div>
	
	
	<div id="nuevaComunicacion_dialog" class="rup-table-formEdit-detail">
		<div id="nuevaComunicacion_detail_navigation"></div>
		<div class="ui-dialog-content ui-widget-content">
			<div id="nuevaComunicacion_feedback"></div>
			<form id="subidaFicheroComunic_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
				<input type="hidden" name="anyo" id="anyoExp_nuevaComunic_bitacora" />
				<input type="hidden" name="numExp" id="numExp_nuevaComunic_bitacora" />
				<input type="hidden" name="refTramitagune" id="refTramitagune_nuevaComunic_bitacora" />
				<input type="hidden" name="idFichero0D4" id="idFichero0D4_nuevaComunic_bitacora" readonly="readonly"/>
				<input type="hidden" name="id" id="codigo_nuevaComunic_bitacora" readonly="readonly"/>
				<input type="hidden" name="extension" id="extension_nuevaComunic_bitacora" readonly="readonly"/>
				<input type="hidden" name="contentType" id="contentType_nuevaComunic_bitacora" readonly="readonly"/>
				<input type="hidden" name="tamano" id="tamano_nuevaComunic_bitacora" readonly="readonly"/>
				<input type="hidden" name="encriptado" id="encriptado_nuevaComunic_bitacora" readonly="readonly"/>
				<input type="hidden" name="rutaPif" id="rutaPif_nuevaComunic_bitacora" readonly="readonly"/>
				<input type="hidden" name="oid" id="oidFichero_nuevaComunic_bitacora" readonly="readonly"/>
				<input type="hidden" name="nombre" id="nombre_nuevaComunic_bitacora" readonly="readonly"/>
				
				<div class="row">
					<div class="form-group col-lg-12">
						<label for="asunto_nuevaComunic_bitacora" class="control-label"><spring:message code="label.asunto"/> (*):</label> 
						<input type="text" name="asunto" class="form-control resizable-textarea" id="asunto_nuevaComunic_bitacora" maxlength="500"/>
					</div>
					<div class="form-group col-lg-12">
						<label for="mensaje_nuevaComunic_bitacora" class="control-label"><spring:message code="label.mensaje"/> (*):</label> 
						<textarea name="mensaje" class="form-control resizable-textarea" id="mensaje_nuevaComunic_bitacora" rows="6" cols="9" maxlength="4000"></textarea>
					</div>
					
				</div>
				<div class="row">	
					<div class="form-group  col-lg-2">
						<button id="pidButtonComunic" type="button"  class="btnPID ui-button ui-corner-all ui-widget" style="margin-top:18px"><spring:message code="label.fichero" /></button>
					</div>
					<div id="subidaFicheroBitacora" class="capaPIDenPestanaDoc oculto">
						<fieldset id="subidaFicheroBitacora_fieldset">			
							<div id="subidaFicheroBitacora_feedback"></div>
								<div class="form-group row">
									<label for="ficheroFileComunic" class="control-label col-md-5"><spring:message code="label.fichero" />:</label>
									<div class="col-md-5" id="divFichero">
										<input type="file" name="fichero" id="ficheroFileComunic" class="form-control">
									</div>
									<input type="hidden" id="idBotonUpload" name="idBotonUpload" />
									<input type="hidden" id="reqEncriptado" name="reqEncriptado" />
								</div>
						</fieldset>
					</div>
					<div class="form-group col-lg-5">
						<input type="text" maxlength="250" name="ficheroNombre" class="form-control" id="ficheroNombre_nuevaComunic_bitacora" style="margin-top:18px" readonly="readonly"/>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<div id="verComunicacion_dialog" class="rup-table-formEdit-detail">
		<div id="verComunicacion_detail_navigation"></div>
		<div class="ui-dialog-content ui-widget-content">
			<div id="verComunicacion_feedback"></div>
			<form id="verComunicacion_form">
				<div class="row">
					<div class="form-group col-lg-12">
						<label class="control-label col-lg-2"><spring:message code="label.fechaHora"/>:</label> 
						<div id="capaMostrarFecha" class="col-lg-10"></div>
					</div>
					<div class="form-group col-lg-12">
						<label class="control-label col-lg-2"><spring:message code="label.remitente2"/>:</label> 
						<div id="capaMostrarRemitente" class="col-lg-10"></div>
					</div>
					<div class="form-group col-lg-12">
						<label class="control-label col-lg-12"><spring:message code="label.asunto"/>:</label> 
						<div id="capaMostrarAsunto" class="padding-left-20"></div>
					</div>
					<div class="form-group col-lg-12 marginTop10">
						<label class="control-label col-lg-12"><spring:message code="label.mensaje"/>:</label> 
						<div id="capaMostrarMensaje" class="padding-left-20"></div>
					</div>
					
					<div class="form-group col-lg-12 marginTop10" id="capaAdjunto">
						<label class="control-label col-lg-2"><spring:message code="label.fichero"/>:</label> 
						<div class="col-lg-10">
							<a href="#" id="enlaceAdjunto"></a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>