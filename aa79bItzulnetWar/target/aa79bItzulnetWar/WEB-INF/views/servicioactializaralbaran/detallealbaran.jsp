<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page	import="com.ejie.aa79b.model.enums.EstadoAlbaranEnum, com.ejie.aa79b.model.Albaran"%>
<div class="container-fluid aa79b-fade in" id="divDetalleAlbaran">
	<div id="divDetalleAlbaranCapa">
		<h2><spring:message code="menu.detalleTarea"></spring:message></h2>
		<div class="rup-table-container">
			<div id="detalleAlbaran_feedback"></div>			
			<div id="detalleAlbaran_toolbar"></div>	
			<div id="detalleAlbaran_filter_div" class="filterForm">
			<div class="p-2">
				<form>
					<fieldset id="detalleAlbaran_form_div_fieldset" class="rup-table-filter-fieldset">
					    <div class="p-2">
							<div class="row"> 
								<div class="form-group col-xl-6 col-lg-6">
									<label for="refAlbaran_detail_table" 
										class="control-label "><spring:message 
											code="label.refPuntoAlbaran" />:</label> ${albaran.refAlbaran}		
								</div>	 
								<div class="form-group col-xl-6 col-lg-6">
									<label for="fechaCreacion_detail_table" 
										class="control-label "><spring:message 
											code="label.dtcreacion" />:</label> ${albaran.fechaHoraAlta}
								</div>
							</div> 
							<div class="row"> 
								<div class="form-group col-xl-6 col-lg-6">
									<label for="estado_filter_table" class="control-label col-xs-12" data-i18n="label.estadoAlbaran"><spring:message code="label.estadoAlbaran" />:</label>
									<div id="altera_estado"></div>
									<div class="col-xs-4">
										<select name="tarea.estadoAsignado" class="form-control" id="estado_filter_table">
										</select>
									</div>
								</div>			 
							</div> 
							<div class="row"> 
								<div class="form-group col-xl-6 col-lg-6">
									<label for="importePrevisto_detail_table" class="control-label" data-i18n="label.impprevisto"><spring:message code="label.impprevisto" />:</label>
									<form:input id="importePrevisto_detail_table" path="albaran.importePrevisto" cssClass="labelInput" disabled="true"/> 
								</div>			 
								<div class="form-group col-xl-6 col-lg-6">
									<label for="importePagado_detail_table" class="control-label " data-i18n="label.imppagado"><spring:message code="label.imppagado" />:</label>
									<form:input id="importePagado_detail_table" path="albaran.importeFactura" cssClass="labelInput" disabled="true"/> 
								</div>			 
							</div> 
							<div class="row"> 
								<div class="form-group col-xl-6 col-lg-6">
									<label for="numTareasAsociadas_detail_table" class="control-label " data-i18n="label.numtareasasociadas"><spring:message code="label.numtareasasociadas" />:</label> ${albaran.numTareasAsoc}
								</div>			 
								<div class="form-group col-xl-6 col-lg-6">
									<label for="numExpAsociadas_detail_table" class="control-label " data-i18n="label.numdeexpasociados"><spring:message code="label.numdeexpasociados" />:</label> ${albaran.numExpAsoc}
								</div>			 
							</div> 
							<fieldset id="lotesAlbaran_form_div_fieldset" class="rup-table-filter-fieldset">
								<legend>
									<spring:message code="label.lotes"></spring:message>
								</legend>
							    <div class="p-2">
									<div class="row"> 
										<div class="form-group col-xl-6 col-lg-6">
											<label for="nombreLote_detail_table" 
												class="control-label  "><spring:message 
													code="label.detalle.nombre" />:</label> ${albaran.lotes.nombreLote}
										</div>	 
									</div> 
									<div class="row"> 
										<div class="form-group col-xl-6 col-lg-6">
											<label for="importeTotalAsignado_detail_table" 
												class="control-label  "><spring:message 
													code="label.imptotalasignado" />:</label>
													<form:input id="importeTotalAsignado_detail_table" path="albaran.lotes.importeAsignado" cssClass="labelInput" disabled="true"/>
										</div>	 
										<div class="form-group col-xl-6 col-lg-6">
											<label for="importeTotalConsumido_detail_table" 
												class="control-label "><spring:message 
													code="label.imptotalconsumido" />:</label>
													<form:input id="importeTotalConsumido_detail_table" path="albaran.lotes.importeConsumido" cssClass="labelInput" disabled="true"/>
										</div>
									</div> 
									<div class="row"> 
										<div class="form-group col-xl-6 col-lg-6">
											<label for="importeTotalPrevisto_detail_table" 
												class="control-label "><spring:message 
													code="label.imptotalprevisto" />:</label>
													<form:input id="importeTotalPrevisto_detail_table" path="albaran.lotes.importePrevisto" cssClass="labelInput" disabled="true"/> 
										</div>	 
										<div class="form-group col-xl-6 col-lg-6">
											<label for="importeTotalDisponible_detail_table" 
												class="control-label "><spring:message 
													code="label.imptotaldisponible" />:</label>
													<form:input id="importeTotalDisponible_detail_table" path="albaran.lotes.importeDisponible" cssClass="labelInput" disabled="true"/>  
										</div>
									</div> 
								</div>
							</fieldset>
						</div>
					</fieldset> 
				</form>	
				<fieldset id="detalleAlbaran_form_div_fieldset" class="rup-table-filter-fieldset">
					    <div class="p-2">
							<fieldset id="tareasAlbaran_filter_fieldset" class="rup-table-filter-fieldset" >
								<legend>
									<spring:message code="label.tareas"></spring:message>
								</legend>
								<div class="p-2">
									<div id="tareasAlbaran_div" class="rup-table-container container-fluid">  
										<div id="tareasAlbaran_feedback"></div>					
										<div id="tareasAlbaran_toolbar"></div>						
										<div id="tareasAlbaran_filter_div" class="rup-table-filter"> 
											<form id="tareasAlbaran_filter_form">						
												<fieldset id="tareasAlbaran_filter_fieldset" class="rup-table-filter-fieldset">
													<div class="p-2">
														<input id="refAlbaran" type="hidden" name="albaran.refAlbaran"></input>
									    				<div id="tareasAlbaran_filter_toolbar" class="formulario_legend"></div>
									    				
									    				<input type="hidden" id="idLoteTareas" name="idLote" value="${albaran.lotes.idLote}">
														<input type="hidden" id="idAlbaranTareas" name="idAlbaran" value="${albaran.idAlbaran}">
								    				</div>	
												</fieldset>
											</form>
										</div>
										<div class="tareasAlbaran_grid_div horizontal_scrollable_table" >
											<table id="tareasAlbaran" style="margin-left:0px !important;margin-right:0px !important;"></table>
											<div id="tareasAlbaran_pager"></div>
										</div>
									</div>	
								</div>
							</fieldset>
						</div>
					</fieldset>
				</div>
			</div>
		</div>	
	</div>
</div>




