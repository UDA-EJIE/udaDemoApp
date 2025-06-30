<%@include file="/WEB-INF/includeTemplate.inc"%>

<div class="container-fluid aa79b-fade in">
	<h2><spring:message code="menu.calendarioPersonal" /></h2>
	<hr class="mb-2">
	<!-- Formulario de detalle -->
	<div id="calendariopersonal_detail_div">
		<div class="ui-dialog-content ui-widget-content" >
			<form id="calendariopersonal_detail_form">					<!-- Formulario -->
				<div id ="calendariopersonal_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
				<div class="row">
					<div class="form-group col-xs-12 col-md-6">
						<label for="dni_detail_table" class="control-label col-xs-12"><spring:message code="label.dni"/>:</label>
						<div class="col-xs-12">
							<select name="dni" id="dni_detail_table" class="form-control"></select>
						</div>						
					</div>
					<div class="form-group col-xs-12 col-md-6">
						<label for="observHorario_detail_table" class="control-label col-xs-12"><spring:message code="label.obsvHorario"/> (*):</label>
						<div class="col-xs-12"><input type="text" name="observHorario" class="form-control col-xs-12" id="observHorario_detail_table" maxlength="250"/></div>
					</div>
					<div class="form-group col-xs-12 col-md-6" style="clear: left;">
						<label for="indTeletrabajo_detail_table" class="control-label col-xs-12"><spring:message code="label.teletrabajador"/> (*):</label>
						<div class="col-xs-12"><input type="checkbox" name="indTeletrabajo" class="form-control col-xs-12" id="indTeletrabajo_detail_table"  value="S"/></div>
					</div>
					<div class="form-group col-xs-12 col-md-6" >
						<label for="horasGestionExp_detail_table" class="control-label col-xs-12"><spring:message code="label.horasGestion"/>:</label>
						<div class="col-xs-12"><input type="text" name="horasGestionExp" class="form-control numeric col-10por" id="horasGestionExp_detail_table" maxlength="2"/></div>
					</div>
					<input type="hidden" name="existe" id="existe" />
					
					<!-- Fin campos del formulario de detalle -->
					
				</div>
			</form>
		</div>
		<!-- Botonera del formulario de detalle -->
		<div id="botoneraCalendario" class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
			<div class="ui-dialog-buttonset">
				<!-- Botón Guardar -->
				<button id="calendariopersonal_detail_button_save" type="button">
					<spring:message code="save" />
				</button>
				<!-- Enlace cancelar -->
				<a href="javascript:void(0)" id="calendariopersonal_detail_link_cancel"
					class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
			</div>
		</div>
	</div>
</div>


<div id="divAusencias">
	<div class="container-fluid">
		<h2>			
			<spring:message code="label.ausencias" />
		</h2>

		<hr class="mb-2">
		<div id="ausencias_div" class="rup-table-container">
			
			<div id="ausencias_feedback"></div>
			
			<div id="ausencias_toolbar"></div>
			<div id="contenFormularios" class="filterForm">
				<form id="ausencias_filter_form">
					<div id="ausencias_filter_toolbar" class="formulario_legend"
						style="display: none;"></div>
					<fieldset id="ausencias_filter_fieldset"
						class="rup-table-filter-fieldset">
						<div class="row">																						
							<div class="col-xs-12 col-md-6">
								<div class="form-group">
									<label for="calendarioPersonal.anyo_filter_table" class="control-label col-xs-3 col-md-2 col-lg-2"><spring:message code="label.anyo"/>:</label>
									<div class="col-xs-8">	
									<input type="text" name="anyo" class="form-control numeric col-30por" id="anyo025_filter_table" maxlength="4"/>
									</div>
								</div>
							</div>
							<input type="hidden" name="dni" id="dni025_filter_table" class="form-control" readonly="readonly" disabled="disabled" />	
						</div>				
						<div class="form-group ">
							<div id="ausencias_filter_buttonSet" class="pull-right">						
								<button id="ausencias_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all"><spring:message code="filter" /></button>						
<%-- 									<a id="ausencias_filter_cleanLink" href="javascript:void(0)" class="btn btn-link"><spring:message code="clear" /></a> --%>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
			<div class="table pb-2">
				<table id="ausencias"></table>
			</div>
			<div id="ausencias_pager"></div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div id="ausencias_detail_div" class="rup-table-formEdit-detail">
		<div class="ui-dialog-content ui-widget-content">
			<form id="ausencias_detail_form">
				<div id ="ausencias_detail_feedback"></div>	
				<input type="hidden" name="dni" id="dni025_detail_table"/>
				<input type="hidden" name="anyo" id="anyo025_detail_table"/>
				<input type="hidden" name="idAusencia" id="idAusencia_detail_table"/>	
				<input type="hidden" name="numTareasConflicto" id="numTareasConflicto"/>	
				<div class="row">
					<div class="form-group col-xs-6">
						<label for="tipoJornada_detail_table" class="control-label"><spring:message code="label.tipoJornada"/> (*):</label>
						<div class="col-xs-12 no-padding">
							<select name="tipoJornada" id="tipoJornada_detail_table" class="form-control"></select>
						</div>
					</div>
				</div>	
				<div class="row">
					<div class="form-group col-lg-6">
						<label for="fechaDesde_detail_table" class="control-label valFecha"><spring:message code="label.fechaDesde"/> (*):</label>
						<input type="text" name="fechaDesde" class="form-control" id="fechaDesde_detail_table"/>
					</div>
					<div class="form-group col-lg-6 ">
						<label for="fechaHasta_detail_table" class="control-label valFecha"><spring:message code="label.fechaHasta"/> (*):</label>
						<input type="text" name="fechaHasta" class="form-control" id="fechaHasta_detail_table"/>
					</div>
				</div>	
				<div class="row">
					<div class="form-group col-xs-12">
						<label for="observDia_detail_table" class="control-label"><spring:message code="label.obsvDia"/> (*):</label>
						<div class="col-xs-12">	
							<input type="text" name="observDia" class="form-control" id="observDia_detail_table" maxlength="250" tabindex="1"/>
						</div>	
					</div>
				</div>
				
			</form>
		</div>		
		<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
			<div class="ui-dialog-buttonset">			
				<button id="ausencias_detail_button_save" type="button">
					<spring:message code="save" />
				</button>				
				<a href="javascript:void(0)" id="ausencias_detail_link_cancel"
					class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
			</div>
		</div>
	</div>
</div>

<div id="divCalendario">
	<div class="container-fluid">	
		<h2><small><spring:message code="menu.calendarioPersonal" /></small></h2>
		<hr class="mb-2">
		<div id="calendario_div" class="rup-table-container">
			<div id="calendario_feedback"></div>
			<div id="calendario_toolbar"></div>	
						
			<div class="col-xs-12 calendarioServicio no-padding">
					<!-- label class="control-label izda" for="festivos"><spring:message code="label.festivos"/>:</label> -->	
					<div id="leyendaCal" class="col-xs-12" style="border: 1px solid #ccc; margin-bottom: 20px; padding: 5px 0;">
						<div  class="col-xs-7 no-padding">
							<label class="col-xs-3 no-padding"><span class="leyenda festivo">12</span> <spring:message code="label.festivos"/></label>
							<label class="col-xs-3 no-padding" style="padding-left:0;padding-right:0"><span class="leyenda jornadaI">12</span> <spring:message code="label.jornadaintensiva"/></label>
							<label class="col-xs-3 no-padding"><span class="leyenda vacaciones">12</span> <spring:message code="label.vacaciones"/></label>
							<label class="col-xs-3 no-padding"><span class="leyenda formacion">12</span> <spring:message code="label.formacion"/></label>
						</div>
						<div  class="col-xs-5 no-padding">
							<label class="col-xs-4 no-padding"><span class="leyenda baja">12</span> <spring:message code="label.baja"/></label>
							<label class="col-xs-4 no-padding"><span class="leyenda interpretacion">12</span> <spring:message code="label.interpretacion"/></label>	
							<label class="col-xs-4 no-padding"><span class="leyenda otros">12</span> <spring:message code="label.otros"/></label>							
						</div>
						
											
					</div>														
					<div id="previewcalendario" class="calendarioSinActive"></div>
			</div>
		</div>
	</div>						
</div>