<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in" id="divPreciosPublicos">
	<h2><spring:message code="menu.ordenPreciosPublicos"/></h2>
	<hr class="mb-2">
	<div id="preciospublicos_div" class="rup-table-container">
		<div id="preciospublicos_feedback"></div>						<!-- Feedback de la tabla --> 
		<div id="preciospublicos_toolbar"></div>							<!-- Botonera de la tabla -->
		<div id="preciospublicos_filter_div" class="rup-table-filter">	<!-- Capa contenedora del formulario de filtrado -->
				<form id="preciospublicos_filter_form">						<!-- Formulario de filtrado -->
				<div id="preciospublicos_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
				<fieldset id="preciospublicos_filter_fieldset" class="rup-table-filter-fieldset">
					<!-- Campos del formulario de filtrado -->
					<div class="p-2">
					<div class="row">
						<div class="col-xs-8 col-lg-8 col-md-8">
							<div class="form-group ">
								<label for="titulo_filter_table" class="control-label col-xs-12" data-i18n="label.tituloOrden"><spring:message code="label.tituloOrden"/>:</label>
								<div class="col-xs-12">		
									<input type="text" name="titulo" class="form-control" id="titulo_filter_table" maxlength="250"/>
								</div>
							</div>
						</div>
						<div class="col-xs-4 col-lg-2 col-md-3">
							<div class="form-group ">
								<label for="indVigor_filter_table" class="control-label col-xs-12" data-i18n="label.enVigor"><spring:message code="label.enVigor"/>:</label>
								<div class="col-xs-12">		
									<select name="indVigor" id="indVigor_filter_table" class="form-control">
										<option value=""><spring:message code="combo.todos"/></option>
										<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
										<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
									</select>
								</div>
							</div>
						</div>
					</div>
					</div>
					<!-- Fin campos del formulario de filtrado -->
					<!-- Botonera del formulario de filtrado -->
					<div id="preciospublicos_filter_buttonSet" class="pull-right">
						<!-- Botón de filtrado -->
						<input id="preciospublicos_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' />
						<!-- Enlace de limpiar -->
						<a id="preciospublicos_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
					</div>
				</fieldset>
			</form>
		</div>
	
		<div id="preciospublicos_grid_div">
			<!-- Tabla -->
			<table id="preciospublicos"></table>
			<!-- Barra de paginación -->
			<div id="preciospublicos_pager"></div>
		</div>
	</div>
</div>

<!-- FORMULARIO DETALLE - INICIO -->
<div id="divPreciosPublicosDetalle" class="aa79b-fade collapsed">
	<div class="container-fluid">
		<h2>
			<spring:message code="menu.ordenPreciosPublicos"></spring:message>
			<small><spring:message code="menu.ordenPreciosPublicos.detalle"></spring:message></small>
		</h2>
		<hr class="mb-2">
		<div id="preciospublicosdetalle_feedback"></div>
		<div id="preciospublicosdetalle_toolbar"></div>
		<div id="preciospublicos_detail_div" class="rup-table-container">
			<form id="datosGeneralesform">
				<input type="hidden" name="id" id="id_detail_form"/>
				<fieldset>
					<legend><spring:message code="label.datosGenerales"></spring:message></legend>
					<div class="row">
						<div class="form-group col-lg-10">
							<label class="control-label" for="titulo_detail_form"><spring:message code="label.titulo" /> (*):</label>
							<input type="text" name="titulo" class="form-control" id="titulo_detail_form" maxlength="250" >
						</div>
						<div class="form-group col-lg-2">
							<label class="control-label col-xs-12 no-padding" for="indVigor_detail_form"><spring:message code="label.enVigor" /> (*):</label>
							<input type="checkbox" name="indVigor" id="indVigor_detail_form" class="form-control" value="S" data-switch="true">
						</div>
						
					</div>
					<div class="row">
						<div class="form-group col-lg-8">
							<label class="control-label" for="urlOrden_detail_form"><spring:message code="label.url"></spring:message> (*):</label>
							<input type="text" name="urlOrden" class="form-control" id="urlOrden_detail_form" maxlength="250" >
						</div>
						<div class="form-group col-lg-2">
							<label class="control-label" for="fechaVigor_detail_form"><spring:message code="label.fechaEntrada" /> (*):</label>
							<input type="text" name="fechaVigor" class="form-control" id="fechaVigor_detail_form" style="width:80px">
						</div>
						<div class="form-group col-lg-2">
							<label class="control-label" for="fechaFinVigor_detail_form"><spring:message code="label.fechaFin" />:</label>
							<input type="text" name="fechaFinVigor" class="form-control" id="fechaFinVigor_detail_form" style="width:80px">
						</div>
						
					</div>
				</fieldset>		
				<fieldset id="nuevoIvaDiv" class="aa79b-fade collapsed">
					<legend><spring:message code="label.ivaYVigencia" /></legend>
					<div class="row">
						<div class="form-group  col-lg-3">
							<label for="porIva_detail_table" class="control-label"><spring:message code="label.iva" /> (*):</label> 
							<input type="text" maxlength="3" name="ivaVigente.porIva" class="form-control numeric col-40por" id="porIva_detail_form" />
						</div>
						<div class="form-group col-lg-3">
							<label for="fechaDesdeVigencia_detail_table" class="control-label"><spring:message code="label.fechaDesde" /> (*):</label>
							<input type="text" name="ivaVigente.fechaDesdeVigencia" class="form-control" id="fechaDesdeVigencia_detail_form" />
						</div>
					</div>
				</fieldset>	
			</form>
			<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
				<div class="ui-dialog-buttonset">
					<button id="datosGeneralesform_detail_button_save" type="button">
						<spring:message code="save" />
					</button>
					<a href="javascript:void(0)" id="datosGeneralesform_detail_link_cancel"
								class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
				</div>
			</div>
			<div id="accordionPreciosPublicos" class="rup_accordion">
				<h1><a> <spring:message code="label.ivaYVigencia" /> </a></h1>
				<div id="ivapreciospublicos_div" class="rup-table-container">
					<div id="ivapreciospublicos_toolbar"></div>
					<div id="ivapreciospublicos_feedback"></div>
					<div id="ivapreciospublicos_filter_div" class="rup-table-filter" style="display:none">
						<form id="ivapreciospublicos_filter_form">
							<div id="ivapreciospublicos_filter_toolbar" class="formulario_legend"></div>
							<div class="formulario_columna_cnt">
								<input type="hidden" name="idOrden" id="idOrden_detail_filter_table" >
							</div>
						</form>
					</div>
					<div id="ivapreciospublicos_grid_div">
						<!-- Tabla -->
						<table id="ivapreciospublicos"></table>
						<!-- Barra de paginación -->
						<div id="ivapreciospublicos_pager"></div>
					</div>
				</div>
				<h1><a> <spring:message code="label.cuantias.serviciosTraduccion" /> </a></h1>
				<div id="cuantiatrad_div" class="rup-table-container">
					<div id="cuantiatrad_toolbar"></div>
					<div id="cuantiatrad_feedback"></div>
					<div id="cuantiatrad_filter_div" class="rup-table-filter" style="display:none">
						<form id="cuantiatrad_filter_form">
							<div id="cuantiatrad_filter_toolbar" class="formulario_legend"></div>
							<div class="formulario_columna_cnt">
								<input type="hidden" name="idOrden" id="idOrdenCuantiaTrad_detail_filter_table" >
							</div>
						</form>
					</div>
					<div id="cuantiatrad_grid_div">
						<!-- Tabla -->
						<table id="cuantiatrad"></table>
						<!-- Barra de paginación -->
						<div id="cuantiatrad_pager" class="oculto"></div>
					</div>
				</div>
				
				<h1><a> <spring:message code="label.cuantias.serviciosRevision" /> </a></h1>		
				<div id="cuantiarev_div" class="rup-table-container">
					<div id="cuantiarev_toolbar"></div>
					<div id="cuantiarev_feedback"></div>
					<div id="cuantiarev_filter_div" class="rup-table-filter" style="display:none">
						<form id="cuantiarev_filter_form">
							<div id="cuantiarev_filter_toolbar" class="formulario_legend"></div>
							<div class="formulario_columna_cnt">
								<input type="hidden" name="idOrden" id="idOrdenCuantiaRev_detail_filter_table" >
							</div>
						</form>
					</div>
					<div id="cuantiarev_grid_div">
						<!-- Tabla -->
						<table id="cuantiarev"></table>
						<!-- Barra de paginación -->
						<div id="cuantiarev_pager"></div>
					</div>
				</div>
				
				
				
				<h1><a> <spring:message code="label.cuantias.tarifExpTradRev" /> </a></h1>
				<div id="tarificacionexp_div">
					<div id="tarificacionexp_toolbar" class="formulario_legend"></div>
					<form id="tarificacionexp_detail_form">
					
						<input type="hidden" name="idOrden" id="idOrden_tarificacionexp_table"/>
						<input type="hidden" name="existeReg" id="existeReg_detail_table"/>
						<fieldset>
						<div class="row">
							<div class="form-group col-lg-4">
								<label for="porRecargoDif_detail_table" class="control-label"><spring:message code="label.recargoDificultad" /> (*):</label> 
								<input type="text" maxlength="3" name="porRecargoDif" class="form-control col-40por numeric" id="porRecargoDif_detail_table"/>
							</div>						
							<div class="form-group  col-lg-4">
								<label for="porRecargoUrg_detail_table" class="control-label"><spring:message code="label.recargoUrgencia" /> (*):</label> 
								<input type="text" maxlength="3" name="porRecargoUrg" class="form-control numeric col-40por" id="porRecargoUrg_detail_table" />
							</div>
							<div class="form-group  col-lg-4">
								<label for="precioMinimo_detail_table" class="control-label"><spring:message code="label.precioMinimo" /> (*):</label> 
								<input type="text" maxlength="9"  name="precioMinimo" class="form-control decimal col-40por"  id="precioMinimo_detail_table" data-decim="2" />
							</div>
						</div>
						</fieldset>
						<fieldset>
							<legend><spring:message code="label.precioTraduccionesTramos"></spring:message></legend>
							<div class="row">
								<div class="form-group col-xs-12">
									<label for="numPalabrasTarifConcor_detail_table" class="control-label"><spring:message code="label.numPalabrasTarif" /> (*):</label>
									<input type="text" maxlength="6" name="numPalabrasTarifConcor" class="form-control numeric" id="numPalabrasTarifConcor_detail_table" style="width:80px" />
								</div>
							</div>
							<div class="row">		
								
								<label for="porPalabraConcor084_detail_table" class="control-label col-xs-12"><spring:message code="label.porcentajePalabraCoincidencia" /></label>
								<div class="col-xs-1"></div>
								<div class="form-group col-lg-3">
									<label for="porPalabraConcor95100_detail_table" class="control-label"><spring:message code="label.95100por" /> (*):</label> 
									<input type="text" maxlength="3" name="porPalabraConcor95100" class="numeric col-30por" id="porPalabraConcor95100_detail_table" />
								</div>
								<div class="form-group col-lg-3">
									<label for="porPalabraConcor8594_detail_table" class="control-label"><spring:message code="label.8594por" /> (*):</label> 
									<input type="text" maxlength="3" name="porPalabraConcor8594" class="numeric col-30por" id="porPalabraConcor8594_detail_table" />
								</div>
								<div class="form-group col-lg-3">
									<label for="porPalabraConcor084_detail_table" class="control-label"><spring:message code="label.85Menorpor" /> (*):</label> 
									<input type="text" maxlength="3" name="porPalabraConcor084" class="numeric col-30por" id="porPalabraConcor084_detail_table" />
								</div>																
							</div>
						</fieldset>
					</form>					
				</div>				
				
				<h1><a> <spring:message code="label.cuantias.serviciosInterpretacion" /> </a></h1>

				<div id="cuantiainter_div">
					<div id="cuantiainter_toolbar" class="formulario_legend"></div>
					<form id="cuantiainter_detail_form">
					
						<input type="hidden" name="idOrden" id="idOrden_cuantiainter_table"/>
						<input type="hidden" name="existeReg" id="existeRegCuantiaInter_detail_table"/>
	
    					<fieldset>
							<legend><spring:message code="label.precioMinInterp"></spring:message></legend>					
							<div class="row">
								<div class="form-group col-xs-6 col-md-4">
									<label for="precMinCae_detail_table" class="control-label"><spring:message code="label.encae" /> (*):</label> 
									<input type="text" maxlength="8" name="precMinCae" class="form-control col-50por decimal" id="precMinCae_detail_table"  data-decim="2"/>
								</div>						
								<div class="form-group col-xs-6 col-md-4">
									<label for="precMinNoCae_detail_table" class="control-label"><spring:message code="label.noencae" /> (*):</label> 
									<input type="text" maxlength="8" name="precMinNoCae" class="form-control col-50por decimal" id="precMinNoCae_detail_table" data-decim="2" />
								</div>								
							</div>
						</fieldset>
						<fieldset>
							<legend><spring:message code="label.congresosPluriling"></spring:message></legend>							
							<div class="row">																		
								<div class="form-group col-xs-6 col-md-4">
									<label for="precJornCongresoEu_detail_table" class="control-label"><spring:message code="label.precioJornada" /> (*):</label> 
									<input type="text" maxlength="8" name="precJornCongresoEu" class="form-control col-50por decimal" id="precJornCongresoEu_detail_table" data-decim="2" />
								</div>
								<div class="form-group col-xs-6 col-md-4">
									<label for="precMediaCongresoEu_detail_table" class="control-label"><spring:message code="label.precioMedJornada" /> (*):</label> 
									<input type="text" maxlength="8" name="precMediaCongresoEu" class="form-control col-50por decimal" id="precMediaCongresoEu_detail_table" data-decim="2" />
								</div>
								<div class="form-group col-xs-6 col-md-4">
									<label for="precHoraCongresoEu_detail_table" class="control-label"><spring:message code="label.precioHoraOFrac" /> (*):</label> 
									<input type="text" maxlength="6" name="precHoraCongresoEu" class="form-control col-50por decimal" id="precHoraCongresoEu_detail_table" data-decim="2" />
								</div>																
							</div>
						</fieldset>
  					
						<fieldset>
							<legend><spring:message code="label.congresosPlurilingNoEus"></spring:message></legend>							
							<div class="row">																		
								<div class="form-group col-xs-6 col-md-4">
									<label for="precJornCongresoNoEu_detail_table" class="control-label"><spring:message code="label.precioJornada" /> (*):</label> 
									<input type="text" maxlength="8" name="precJornCongresoNoEu" class="form-control col-50por decimal" id="precJornCongresoNoEu_detail_table" data-decim="2" />
								</div>
								<div class="form-group col-xs-6 col-md-4">
									<label for="precMediaCongresoNoEu_detail_table" class="control-label"><spring:message code="label.precioMedJornada" /> (*):</label> 
									<input type="text" maxlength="8" name="precMediaCongresoNoEu" class="form-control col-50por decimal" id="precMediaCongresoNoEu_detail_table" data-decim="2" />
								</div>
								<div class="form-group col-xs-6 col-md-4">
									<label for="precHoraCongresoNoEu_detail_table" class="control-label"><spring:message code="label.precioHoraOFrac" /> (*):</label> 
									<input type="text" maxlength="6" name="precHoraCongresoNoEu" class="form-control col-50por decimal" id="precHoraCongresoNoEu_detail_table" data-decim="2" />
								</div>																
							</div>
						</fieldset>
						
						<fieldset>
							<legend><spring:message code="label.paramJornadas"></spring:message></legend>
							<label for="jornCompHorasDesde_detail_table" class="control-label"><spring:message code="label.jornadaCompleta" /> (*):</label> 							
							<div class="row">
								<div class="col-xs-1"></div>										
								<div class="form-group col-xs-6 col-md-4">
									<label for="jornCompHorasDesde_detail_table" class="control-label"><spring:message code="label.horasDesde" /> (*):</label> 
									<input type="text" maxlength="1" name="jornCompHorasDesde" class="col-30por numeric" id="jornCompHorasDesde_detail_table" />
								</div>
								<div class="form-group col-xs-6 col-md-5">
									<label for="jornCompHorasHasta_detail_table" class="control-label"><spring:message code="comun.y" /> (*):</label> 
									<input type="text" maxlength="1" name="jornCompHorasHasta" class="col-30por numeric" id="jornCompHorasHasta_detail_table" />
									<label for="jornMediaHorasHasta_detail_table" class="control-label"><spring:message code="label.horasInclusive" /></label> 
								</div>																						
							</div>
							<label for="jornMediaHorasDesde_detail_table" class="control-label"><spring:message code="label.mediaJornada" /> (*):</label> 							
							<div class="row">
								<div class="col-xs-1"></div>										
								<div class="form-group col-xs-6 col-md-4">
									<label for="jornMediaHorasDesde_detail_table" class="control-label"><spring:message code="label.horasDesde" /> (*):</label> 
									<input type="text" maxlength="1" name="jornMediaHorasDesde" class="col-30por numeric" id="jornMediaHorasDesde_detail_table" />
								</div>
								<div class="form-group col-xs-6 col-md-5">
									<label for="jornMediaHorasHasta_detail_table" class="control-label"><spring:message code="comun.y" /> (*):</label> 
									<input type="text" maxlength="1" name="jornMediaHorasHasta" class="col-30por numeric" id="jornMediaHorasHasta_detail_table" />
									<label for="jornMediaHorasHasta_detail_table" class="control-label"><spring:message code="label.horasInclusive" /></label> 
								</div>																						
							</div>
						</fieldset>
   						
						<fieldset>
							<legend><spring:message code="label.interpretacionReunion"></spring:message></legend>					
							<div class="row">
								<div class="form-group col-xs-6 col-md-4">
									<label for="precHoraReunionCae_detail_table" class="control-label" style="display:block"><spring:message code="label.encae" /> (*):</label> 
									<label for="precHoraReunionCae_detail_table" class="control-label"><spring:message code="label.euroHora" /></label> <input type="text" maxlength="6" name="precHoraReunionCae" class="form-control col-50por decimal" id="precHoraReunionCae_detail_table" style="display:inline-block" data-decim="2"/> 
								</div>						
								<div class="form-group col-xs-6 col-md-4">
									<label for="precHoraReunionNoCae_detail_table" class="control-label" style="display:block"><spring:message code="label.noencae" /> (*):</label> 
									<label for="precHoraReunionCae_detail_table" class="control-label"><spring:message code="label.euroHora" /></label> <input type="text" maxlength="6" name="precHoraReunionNoCae" class="form-control col-50por decimal" id="precHoraReunionNoCae_detail_table" style="display:inline-block" data-decim="2" /> 
								</div>								
							</div>
						</fieldset>
					</form>					
				</div>				
			</div>
			<p style="margin-top:1rem" class="rojoImportant negrita"> <spring:message code="label.ivaIncluido" /></p>
		</div>
	</div>
</div>				
<!-- FORMULARIO DETALLE - FIN -->

<!-- FORMULARIO DETALLE: IVA Y VIGENCIA - INICIO -->
<div class="container-fluid">
	<div id="ivapreciospublicos_detail_div" class="rup-table-formEdit-detail">
		<div class="ui-dialog-content ui-widget-content">
			<div id ="ivapreciospublicos_detail_feedback"></div>
			<form id="ivapreciospublicos_detail_form">
				<input type="hidden" name="idOrden" id="idOrdenIva_detail_table"/>
				<div class="row">
					<div class="form-group  col-lg-4">
						<label for="id_detail_table" class="control-label"><spring:message code="label.id" />:</label> 
						<input type="text" name="id" class="form-control col-40por" id="id_detail_table" readonly="readonly" disabled="disabled"/>
					</div>
				</div>
				<div class="row">
					<div class="form-group  col-lg-6">
						<label for="porIva_detail_table" class="control-label"><spring:message code="label.iva" /> (*):</label> 
						<input type="text" maxlength="3" name="porIva" class="form-control numeric col-40por" id="porIva_detail_table" />
					</div>
					<div class="form-group  col-lg-4">
						<label for="indVigente_detail_table" class="control-label"><spring:message code="label.enVigor" />:</label> 
						<input type="checkbox" name="indVigente" id="indVigente_detail_table" class="form-control" value="S" data-switch="true">
					</div>
				</div>
				<div class="row">
					<div class="form-group col-lg-6">
						<label for="fechaDesdeVigencia_detail_table" class="control-label valFecha"><spring:message code="label.fechaDesde" /> (*):</label>
						<input type="text" name="fechaDesdeVigencia" class="form-control" id="fechaDesdeVigencia_detail_table" />
					</div>
					<div class="form-group col-lg-6">
						<label for="fechaHastaVigencia_detail_table" class="control-label valFecha"><spring:message code="label.fechaHasta" />:</label> 
						<input type="text" name="fechaHastaVigencia" class="form-control" id="fechaHastaVigencia_detail_table" />
					</div>
				</div>
			</form>
		</div>
		<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
			<div class="ui-dialog-buttonset">
				<button id="ivapreciospublicos_detail_button_save" type="button">
					<spring:message code="save" />
				</button>
				<a href="javascript:void(0)" id="ivapreciospublicos_detail_link_cancel"
					class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
			</div>
		</div>
	</div>
</div>
<!-- FORMULARIO DETALLE: IVA Y VIGENCIA - FIN -->


<!-- FORMULARIO DETALLE: CUANTIA TRADUCCIÓN - INICIO -->
<div class="container-fluid">
	<div id="cuantiatrad_detail_div" class="rup-table-formEdit-detail">
		<div class="ui-dialog-content ui-widget-content">
			<div id ="cuantiatrad_detail_feedback"></div>
			<form id="cuantiatrad_detail_form">
				<input type="hidden" name="idTipoRelevancia" id="idTipoRelevancia_detail_table"/>
				<input type="hidden" name="idOrden" id="idOrdenCuantiaTrad_detail_table"/>
				<div class="row">
					<div class="form-group  col-lg-4">
						<label for="id_detail_table" class="control-label"><spring:message code="label.relevancia" />:</label> 
						<input type="text" name="tipoRelevancia.descRelevanciaEu" class="form-control col-66por" id="descRelevanciaEu_detail_table" readonly="readonly" disabled="disabled"/>
					</div>
				</div>
				<div class="row">
					<div class="form-group  col-lg-6">
						<label for="tarifaEsEu_detail_table" class="control-label"><spring:message code="label.tarifaEsEu" /> (*):</label> 
						<input type="text" name="tarifaEsEu" class="form-control decimal col-66por" id="tarifaEsEuTrad_detail_table" maxlength="6" data-decim="4"/>
					</div>
					<div class="form-group  col-lg-6">
						<label for="tarifaEuEs_detail_table" class="control-label"><spring:message code="label.tarifaEuEs" /> (*):</label> 
						<input type="text" name="tarifaEuEs" class="form-control decimal col-66por" id="tarifaEuEsTrad_detail_table" maxlength="6" data-decim="4"/>
					</div>
				</div>
			</form>
		</div>
		<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
			<div class="ui-dialog-buttonset">
				<button id="cuantiatrad_detail_button_save" type="button">
					<spring:message code="save" />
				</button>
				<a href="javascript:void(0)" id="cuantiatrad_detail_link_cancel"
					class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
			</div>
		</div>
	</div>
</div>
<!-- FORMULARIO DETALLE: CUANTIA TRADUCCIÓN - FIN -->


<!-- FORMULARIO DETALLE: CUANTIA REVISIÓN - INICIO -->
<div class="container-fluid">
	<div id="cuantiarev_detail_div" class="rup-table-formEdit-detail">
		<div class="ui-dialog-content ui-widget-content">
			<div id ="cuantiarev_detail_feedback"></div>
			<form id="cuantiarev_detail_form">
				<input type="hidden" name="idTipoRelevancia" id="idTipoRelevancia_detail_table"/>
				<input type="hidden" name="idOrden" id="idOrdenCuantiaRev_detail_table"/>
				<div class="row">
					<div class="form-group  col-lg-4">
						<label for="id_detail_table" class="control-label"><spring:message code="label.relevancia" /> (*):</label> 
						<input type="text" name="tipoRelevancia.descRelevanciaEu" class="form-control col-66por" id="descRelevanciaEu_detail_table" readonly="readonly" disabled="disabled"/>
					</div>
				</div>
				<div class="row">
					<div class="form-group  col-lg-6">
						<label for="tarifaEsEu_detail_table" class="control-label"><spring:message code="label.tarifaEsEu" /> (*):</label> 
						<input type="text" name="tarifaEsEu" class="form-control decimal col-66por" id="tarifaEsEuRev_detail_table" maxlength="6" data-decim="4"/>
					</div>
					<div class="form-group  col-lg-6">
						<label for="tarifaEuEs_detail_table" class="control-label"><spring:message code="label.tarifaEuEs" /> (*):</label> 
						<input type="text" name="tarifaEuEs" class="form-control decimal col-66por" id="tarifaEuEsRev_detail_table" maxlength="6" data-decim="4"/>
					</div>
				</div>
			</form>
		</div>
		<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
			<div class="ui-dialog-buttonset">
				<button id="cuantiarev_detail_button_save" type="button">
					<spring:message code="save" />
				</button>
				<a href="javascript:void(0)" id="cuantiarev_detail_link_cancel"
					class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
			</div>
		</div>
	</div>
</div>
<!-- FORMULARIO DETALLE: CUANTIA REVISIÓN - FIN -->