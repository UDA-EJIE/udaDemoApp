<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.AportaSubsanacionEnum"%>
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in capaExpedienteMYO" id="divEstudioExpedientes">
	<h2><spring:message code="menu.estudioExpedientes"></spring:message></h2>
	<div id="estudioExpedientes_div" class="rup-table-container">
		<div id="estudioExpedientes_feedback"></div>						<!-- Feedback de la tabla --> 
		<div id="estudioExpedientes_toolbar"></div>						<!-- Botonera de la tabla --> 
		<div id="estudioExpedientes_filter_div" class="rup-table-filter"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="estudioExpedientes_filter_form">						<!-- Formulario de filtrado -->
				<div id="estudioExpedientes_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
				<fieldset id="estudioExpedientes_filter_fieldset" class="rup-table-filter-fieldset">
					<div class="p-2">
					<div class="row">
						<!-- Campos del formulario de filtrado -->
						<div class="form-group col-xs-12 col-md-3">
							<label for="numExp_filter_table" class="control-label"><spring:message code="label.numeroExpediente"/>:</label>
							<div>
								<label for="anyo_filter_table" class="control-label oculto"><spring:message code="label.anyo"/></label>
								<input type="text" name="anyo" class=" numeric" style="width: 40px;" id="anyo_filter_table" maxlength="2"/>
								<label class="control-label" style="width: 3%;">/</label>
								<input type="text" name="numExp" class=" numeric" style="width: 75px;" id="numExp_filter_table" maxlength="6"/>
							</div>
						</div>
						
						<div class="form-group col-xs-12 col-md-2">
							<label for="idTipoExpediente_filter_table" class="control-label "><spring:message code="label.tipoExpediente"/>:</label>
							<select name="idTipoExpediente" class="form-control" id="idTipoExpediente_filter_table">
								<option value=""><spring:message code="combo.todos"/></option>
								<option value="<%=TipoExpedienteEnum.TRADUCCION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.TRADUCCION.getLabel()%>"/></option>
								<option value="<%=TipoExpedienteEnum.REVISION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.REVISION.getLabel()%>"/></option>
							</select>
						</div>
						
						<div class="form-group col-xs-12 col-md-4">
							<label for="titulo_filter_table" class="control-label "><spring:message code="label.titulo"/>:</label>
							<input type="text" name="titulo" class="form-control" id="titulo_filter_table" maxlength="150"/>
						</div>
					</div>
					<div class="row">
						<div class="form-groupcol-xs-12 col-md-3">
							<label for="fases_filter_table" class="control-label "><spring:message code="label.estadoFase"/>:</label>
							<select name="bitacoraExpediente.faseExp.id" class="form-control" id="fases_filter_table"></select>
						</div>
						<input id="estadoExpEstudioExpedientes" type="hidden" name="bitacoraExpediente.estadoExp.id" value="2"></input>
						<div class="form-group col-xs-12 col-md-3">
							<label for="subsanacion_filter_table" class="control-label "><spring:message code="label.subsanacionAport"/>:</label>
							<select name="bitacoraExpediente.subsanacionExp.indSubsanado" class="form-control" id="subsanacion_filter_table">
								<option value="" selected="true"><spring:message code="combo.todos"/></option>
								<option value="<%=AportaSubsanacionEnum.SI.getValue()%>"><spring:message code="<%=AportaSubsanacionEnum.SI.getLabel()%>"/></option>
								<option value="<%=AportaSubsanacionEnum.NO.getValue()%>"><spring:message code="<%=AportaSubsanacionEnum.NO.getLabel()%>"/></option>
								<option value="<%=AportaSubsanacionEnum.NO_REQUERIDA.getValue()%>"><spring:message code="<%=AportaSubsanacionEnum.NO_REQUERIDA.getLabel()%>"/></option>
							</select>
						</div>
						<div class="form-group col-xs-12 col-md-2">
							<label for="presupuesto_filter_table" class="control-label"><spring:message code="label.presupuesto"/>:</label>
							<select name="expedienteInterpretacion.indPresupuesto" class="form-control" id="presupuesto_filter_table">
								<option value="" selected="true"><spring:message code="combo.todos"/></option>
								<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
								<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
							</select>
						</div>
						<div class="form-group col-xs-12 col-md-2">
							<label for="bopv_filter_table" class="control-label "><spring:message code="label.bopv"/>:</label>
							<select name="expedienteTradRev.indPublicarBopv" class="form-control" id="bopv_filter_table">
								<option value="" selected="true"><spring:message code="combo.todos"/></option>
								<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
								<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-xs-6 ">
							<label for="idTiposEntidad_filter_table" class="control-label " data-i18n="label.tipoEntidadGestora"><spring:message code="label.tipoEntidadGestora"/>:</label>
							<div id="idTiposEntidad_filter_table" >
								<div class="col-md-3">
									<input type="radio" name="tipoEntidad" id="tipoEntidad_T" value="" data-on-text='<spring:message code="label.todas"/>'/>
									<label for="tipoEntidad_T" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="label.todas"/></label>
								</div>
								<c:set var="labelsTipoEntidad" value="<%=TipoEntidadEnum.values()%>"/>
								<c:forEach items="${labelsTipoEntidad}" var="tipoEntidad">
								<div class="col-md-3">
									<input type="radio" name="tipoEntidad" id="tipoEntidad_${tipoEntidad.value}" value="${tipoEntidad.value}"  data-on-text='<spring:message code="${tipoEntidad.label}"/>'/>
									<label for="tipoEntidad_${tipoEntidad.value}" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="${tipoEntidad.label}"/></label>
								</div>
								</c:forEach>
							</div>
							<input type="hidden" name="gestorExpediente.entidad.tipo" id="gestorExpedienteEntidadTipoEstudio"></input>	
						</div>
						<div class="form-group col-xs-12 col-md-5">
							<label id="labelEntidadSolicitante_filter_table" for="idEntidadSolicitante_filter_table" class="control-label " data-i18n="label.entidadGestoraExpEnEstudio"></label>
							<select id="idEntidadSolicitante_filter_table" class="form-control" name="gestorExpediente.entidad.codigoCompleto"></select>	
						</div>
					</div>
					<div class="row">
						<div id="div_contactoGestorEstudio_filter_table" class="form-group col-xs-12 col-md-5">
							<div id="autocompleteContainer_contactoGestorEstudio_filter_table">
								<label for="contactoGestorEstudio_filter_table" class="control-label " data-i18n="label.contactoGestorExpEnEstudio"><spring:message code="label.contactoGestorExpEnEstudio" />:</label>
								<input id="contactoGestorEstudio_filter_table" class="form-control" name="gestorExpediente.solicitante.dni"/>							
							</div>
						</div>
						<div class="form-group col-xs-12 col-md-5">
							<label for="tecnicoAsignado_filter_table" class="control-label " data-i18n="label.tecnicoAsignadoEstudioExpediente"><spring:message code="label.tecnicoAsignadoEstudioExpediente" />:</label>
							<input id="tecnicoAsignado_filter_table" class="form-control" name="tecnico.dni"/>
						</div>
					</div>
						<!-- Fin campos del formulario de filtrado -->
						<div id="estudioExpedientes_filter_buttonSet" class="pull-right" style="margin:0 1rem 1rem 0">
						<!-- Botón de filtrado -->
						<button id="estudioExpedientes_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
							<spring:message code="filter" />
						</button>
						
						<!-- Enlace de limpiar -->
						<a id="estudioExpedientes_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
					</div>
					</div>
					
				</fieldset>
			</form>
		</div>
		<div class="estudioExpedientes_grid_div horizontal_scrollable_table" >
			<!-- Tabla -->
			<table id="estudioExpedientes" ></table>
			<!-- Barra de paginación -->
			<div id="estudioExpedientes_pager"></div>
		</div>
		<div class="row form-group" id="leyendaGestor">
			<div class="col-xs-8">
				<div class="col-xs-1" style="width: 35px!important;">
					<i class="fa fa-exclamation-circle iconoGestorNoActivo" aria-hidden="true"></i>
				</div>
				<label class="control-label col-xs-10 textoGestorNoActivo">
					<spring:message code="label.gestorInactivo"/>
				</label>
			</div>
		</div>
	</div>
</div>

		



<div id="detalleExpediente" class="container-fluid collapsed">
	<%@include file="/WEB-INF/views/cabeceraexpediente/cabeceraexpediente.jsp"%>
	<%@include file="/WEB-INF/views/bitacoraexpediente/bitacoraexpediente.jsp"%>
	
	<div id="detalleExpediente_div" class="rup-table-container capaExpedienteMYO">
		<div id="detalleExpediente_feedback"></div>
		<div id="detalleExpediente_toolbar"></div> <!-- Botonera -->		
		<div id="detalleSubsanacion" class="rup-table-container">
		
			<form id="detalleSubsanacion_div">
			
				<fieldset id="detalleSubsanacion_filter_fieldset" class="rup-table-filter-fieldset">
					<legend><spring:message code="label.subsanacion"></spring:message></legend>
					<input type="hidden" id="idSub" name="idSub" />		            
		            <div class="form-group col-md-12 col-lg-3 grupoFechaHora">
						<label class="control-label valFecha" for="detFechaLimite" data-i18n="label.fechaLimite"><spring:message code="label.fechaLimite" /> (*):</label> 
						<input type="text" name="fechaLimite" class="form-control" id="detFechaLimite"> 
						<input type="text" name="horaLimite" class="form-control campohora" id="detHoraLimite" placeholder="hh:mm" maxlength="5">
					</div>
					<div class="form-group col-md-3">
						<label class="control-label" for="detIndSubsanado" data-i18n="label.indSubsanacion"><spring:message code="label.indSubsanacion"/> :</label>
						<input type="checkbox" id="detIndSubsanado" value="S" data-switch="true" disabled>
					</div>
					<div class="form-group col-md-6">
						<label class="control-label" for="detDetalleSubsanacion" data-i18n="label.detalleSub"><spring:message code="label.detalleSub"/> :</label>
						<textarea class="form-control resizable-textarea" id="detDetalleSubsanacion" rows="6" maxlength="4000" disabled></textarea>
					</div>
					<div class="form-group col-md-12">
						<label class="control-label" data-i18n="label.infoSub"><spring:message code="label.infoSub"/></label>
					</div>
					<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
						<div class="ui-dialog-buttonset">
							<!-- Botón Guardar -->
							<button id="detalleSubsanacion_button_save" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
			                    <spring:message code="aceptar" />
			                </button>
							<!-- Enlace cancelar -->
							<button id="detalleSubsanacion_link_cancel" type="button" 
								class="rup-enlaceCancelar" style="text-transform: none !important;"><spring:message code="rechazar" />
							</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div id="tabsExpediente"></div>
	</div>
</div>


<div id="estudioExpedientesReasignarTecnico" class="aa79b-fade collapsed capaExpedienteMYO">
	<div class="container-fluid">
		<h2>
			<spring:message code="menu.estudioExpedientes" />
			<small><spring:message code="label.detalle.reasignarTecnico" /></small>
		</h2>
		<div id="estudioExpedientesReasignarTecnico_div" class="row">
			<div id="estudioExpedientesReasignarTecnico_feedback"></div>
			<div id="estudioExpedientesReasignarTecnico_toolbar"></div>
			<div id="estudioExpedientesReasignarTecnico_filter_div">
				<fieldset id="rechazarExp_fieldset" class="form_fieldset">
					<form id="estudioExpedientesReasignarTecnicoform">
						<div class="form-group col-md-5">
							<label for="reasignarTecnicoAsignado" class="control-label" data-i18n="label.tecnicoAsignadoEstudioExpediente"><spring:message code="label.tecnicoAsignadoEstudioExpediente" />:</label>
							<input id="reasignarTecnicoAsignado" class="form-control" />
						</div>
					</form>
				</fieldset>
			</div>
		</div>
	</div>
</div>

<div id="rechazarExp" class="aa79b-fade collapsed capaExpedienteMYO">
	<div id="rechazarAjusteConsExp" class="container-fluid">
		<h2>
			<spring:message code="menu.rechazarExp" />
		</h2>
		<div id="rechazarExp_div" class="row">
			<div id="rechazarExp_toolbar"></div>
			<div id="rechazarExp_filter_div">
				<form id="rechazarExpform">
					<div id="rechazarExp_feedback"></div>
					<fieldset id="rechazarExp_fieldset" class="form_fieldset">
						<div class="form-group col-md-3">
							<label for="motivoRechazo" class="control-label" data-i18n="label.motivoRechazo"><spring:message code="label.motivoRechazo" /> (*):</label>
							<input id="motivoRechazo" name="motivoRechazo" class="form-control" />
						</div>
						<div class="form-group col-md-9">
							<label class="control-label" for="descRechazo" data-i18n="comun.descripcion"><spring:message code="comun.descripcion"/>:</label>
							<textarea class="form-control resizable-textarea" id="descRechazo" name="descRechazo" rows="6" maxlength="4000" ></textarea>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</div>

<div id="requerirSubExp" class="aa79b-fade collapsed capaExpedienteMYO">
	<div id="requerirSubExpAjusteConsExp" class="container-fluid">
		<h2>
			<spring:message code="menu.requerirSub" />
		</h2>
		<div id="requerirSubExp_div" class="row">
			<div id="requerirSubExp_toolbar"></div>
			<div id="requerirSubExp_filter_div">
				<form id="requerirSubExpform">
					<div id="requerirSubExp_feedback"></div>
					<fieldset id="requerirSubExp_fieldset" class="form_fieldset" style="padding-top:2rem">						
						<div class="form-group col-md-12 col-lg-3 grupoFechaHora">
							<label class="control-label valFecha" for="fechaLimite" data-i18n="label.fechaLimite"><spring:message code="label.fechaLimite" /> (*):</label> 
							<input type="text" name="fechaLimite" class="form-control" id="fechaLimite"> 
							<input type="text" name="horaLimite" class="form-control campohora" id="horaLimite" placeholder="hh:mm" maxlength="5">
						</div>						
						<div class="form-group col-md-9">
							<label class="control-label" for="detSubsanacion" data-i18n="label.detalleSub"><spring:message code="label.detalleSub"/> (*):</label>
							<textarea class="form-control resizable-textarea" name="detSubsanacion" id="detSubsanacion" rows="6" maxlength="4000" ></textarea>
						</div>
						<div class="form-group col-md-12">
							<fieldset id="requerirSubExp_list" class="rup-table-filter-fieldset">
								<legend><spring:message code="label.subsanacionCampos"></spring:message><input type="text" style="width:0px;visibility:hidden;" id="camposSelect" name="camposSelect" /></legend>
								<div class="form-group col-md-6">
									<label class="control-label" for="camposSub" data-i18n="label.camposSub"><spring:message code="label.camposSub"/> :</label>
									<div id="camposSub" class="subsanacionTree">
										<div id="treeCamposSub">
											<ul>
											</ul>
										</div>
									</div>
								</div>
								<div class="form-group col-md-6">
									<label class="control-label" for="docuAsociados" data-i18n="label.docuAsociados"><spring:message code="label.docuAsociados"/> :</label>
									<div id="docuAsociados" class="subsanacionTree">
										<div id="listaDocuAsociados">
											<ul>
											</ul>
										</div>
									</div>
								</div>
								
								<div class="form-group col-md-12">
										<label class="control-label" data-i18n="label.requiereRevisionDocus">(*) <spring:message code="label.requiereRevisionDocus"/></label>
									</div>
									<div class="form-group col-md-12">
										<label class="control-label" data-i18n="label.requiereAdjuntarDocumentos">(**) <spring:message code="label.requiereAdjuntarDocumentos"/></label>
									</div>
							</fieldset>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</div>
<div id="divReceptoresAutorizados" class="container-fluid aa79b-fade collapsed capaExpedienteMYO" >
	<h2><spring:message code="menu.receptoresAutorizados"/></h2>
	<div id="receptoresAutorizados_div" class="rup-table-container">
		<div id="receptoresAutorizados_feedback"></div> <!-- Feedback de la tabla -->	
		<div id="receptoresAutorizados_toolbar"></div> <!-- Botonera de la tabla -->
		<div id="receptoresAutorizados_filter_div" class="rup-table-filter" style="display:none">
			<form id="receptoresAutorizados_filter_form"> <!-- Formulario de filtrado -->
				<div id="receptoresAutorizados_filter_toolbar" class="formulario_legend"></div>	Barra de herramientas del formulario de filtrado
				<fieldset id="receptoresAutorizados_filter_fieldset" class="rup-table-filter-fieldset">
						<div class="row">
							<!-- Campos del formulario de filtrado -->
								<input type="hidden" name="anyo" class="form-control numeric" id="anyoreceptoresAutorizados_filter_table" /> 
								<input type="hidden" name="numExp" class="form-control numeric" id="numExpreceptoresAutorizados_filter_table" />
							<!-- Fin campos del formulario de filtrado -->
						</div>
						<div id="receptoresAutorizados_filter_buttonSet" class="pull-right">
							<!-- Botón de filtrado -->
							<input id="receptoresAutorizados_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' />
							<!-- Enlace de limpiar -->
							<a id="receptoresAutorizados_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
						</div>
				</fieldset>
			</form>
		</div>
		<div class="receptoresAutorizados_grid_div ">
			<!-- Tabla -->
			<table id="receptoresAutorizados"></table>
			<!-- Barra de paginación -->
			<div id="receptoresAutorizados_pager"></div>
		</div>
	</div>
	
<div id="receptorDiv">
	<div id="buscadorPersonasReceptores" class="oculto"></div>
</div>	
</div>
<div class='container-fluid aa79b-fade collapsed capaExpedienteMYO' id="divCategorizacionExpediente">
	<h2><spring:message code="menu.categorizacionExpediente"/></h2>
	<div id="categorizacionExpediente_div" class="rup-table-container">
		<div id="categorizacionExpediente_feedback"></div>
		<div id="categorizacionExpediente_toolbar"></div>
		<div id="contenFormularios" class="filterForm oculto ">
			<form id="categorizacionExpediente_filterForm" class="form-horizontal">
				<fieldset id="categorizacionExpediente_filter_fieldset" class="form_fieldset oculto">
					<div class="formulario_columna_cnt">
						<input type="hidden" id="anyo_categorizacion_expediente">
						<input type="hidden" id="numExp_categorizacion_expediente">
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	<div class="rup-table-container" >
	 	<h4><spring:message code="menu.seleccionesMetadatosCategorizacion"/></h4>
	   <fieldset>
		   <div class="list-group">
		   		<div id="categorizacionExpedientemetadatos_div" ></div>
		   </div>
	   </fieldset>
	   <fieldset>
		   <div class="row">
			   <div class="col-xs-12">
			   		<%-- <label><spring:message code="label.noSeleccionado"/>:</label>
	 				<input style="background-color:#ECECEC; width:40px;" disabled="disabled">
	 				<label style="margin-left:10px"><spring:message code="label.seleccionadoEnEstadoAlta"/>:</label>
	 				<input style="background-color:#7DD3F2; width:40px;" disabled="disabled">
	 				<label style="margin-left:10px"><spring:message code="label.seleccionadoEnEstadoBaja"/>:</label>
	 				<input style="background-color:#F0ABBF; width:40px;" disabled="disabled"> --%>
	 				<label style="margin-left:10px"><spring:message code="label.seleccionadoEnEstadoBaja"/>:</label>
	 				<del>etiketa</del>
			   </div>
		   </div>
		</fieldset>
	</div>
</div>
<div id="capasModalesDiv">
		<div id="buscadorEtiquetasExpediente" class="oculto"></div>
	</div>
<%@include file="/WEB-INF/views/expedientesrelacionados/expedientesrelacionados.jsp"%>
<%@include file="/WEB-INF/views/contactofacturacionexpediente/contactofacturacionexpediente.jsp"%>
