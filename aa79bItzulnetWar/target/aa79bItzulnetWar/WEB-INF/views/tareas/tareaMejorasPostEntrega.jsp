<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in aa79b-content" id="divEjecutarTareaCapa">
	<div id="tareaMejorasPostEntrega_div">
		<div id="ejecutarTareaDialog_toolbar"></div>
		<div id="ejecutarTareaDialog_feedback"></div>
		<div class="row margen1TB">
			<div class="col-xs-2">
				<label class="control-label"><spring:message code="comun.tipoDeTarea" />:</label>
			</div>
			<div class="col-xs-9">
				<b><c:out value="${descripcionTarea}"></c:out></b>
			</div>
		</div>
		<input type="hidden" id="idiomaDest" name="idiomaDest" />
		<div id="pestanasModalTareaMejorasPostEntrega">
			<div id="tabsModalTareaMejorasPostEntrega" class="tabsEjecutarTarea"></div>
		</div>
		<div id="pestLerrokoa">
			<div id="lerrokoa_div">
				<div class="form-group row">
					<div class="form-group col-md-5 col-xl-5 ">
						<label for="indVigor_filter_table" class="control-label col-xs-12 noNegrita" style="font-size: 1em;"><spring:message code="label.haHabidoCorr" />?</label>
						<div class="col-xs-12">
							<input type="checkbox" name="indDesglose" class="form-control" id="ind_correcciones_filter" value="S" data-switch-pestana="true" />
						</div>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-lg-6">
						<label for="nombreFichero" class="control-label col-xs-12"><spring:message code="label.informeDeCorr" />:</label>
						<div class="col-xs-12 aa79bObsTradRevContainer">
							<span id="enlaceDescargaDetalle" style="display:none;margin-right: 1rem;"></span>           
							<div id="divFicheroFileCorreciones" class="form-group"> 
								<input type="hidden" id="idFichero">                     
				                <button id="pidButtonFile" type="button" class="ui-button ui-corner-all ui-widget no-obligatorio" style="margin-top:0"><spring:message code="label.adjuntarFichero"/></button>
				                <input type="text" name="nombreFichero" class="form-control" id="nombreFichero" readonly="readonly" style="width:200px; display:inline-block" />
							</div>
						</div>
					</div>	
				</div>
				<form id="ejecutarTareaDialog_form">
					<input type="hidden" name="numExp" id="numExp_form"> 
					<input type="hidden" name="anyo" id="anyo_form"> 
					<input type="hidden" name="idTarea" id="idTarea_form"> 
					<input type="hidden" name="tipoTarea.id015" id="idTipoTarea_form">
					<input type="hidden" name="ejecucionTareas.horasTarea" id="horasTarea_form">
					
					<div class="form-group row">
					<div class="col-xs-12">
						<div class="form-group ">
							<label for="contenido_form" class="col-xs-12 noNegrita control-label" data-i18n="label.observaciones"><spring:message code="label.observaciones" />:</label>
							<div class="col-xs-12">
								<textarea name="observacionesTareas.obsvEjecucion" id="contenido_form" class="form-control col-xs-12" style="height: 80px;"></textarea>
							</div>
						</div>
	
					</div>
				</div>
				</form>
				
			</div>
		</div>
		<div id="pestDocsXliff">
			<div id="docsXliff_div">
				<div id="docsXliff_feedback"></div>						<!-- Feedback de la tabla --> 
				<div id="docsXliff_toolbar"></div>							<!-- Botonera de la tabla -->
				<div id="contenFormulariosXliff" class="filterForm oculto">	
					<form id="docsXliff_filter_form" class="form-horizontal">						<!-- Formulario de filtrado -->
						<div id="docsXliff_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
						<fieldset id="docsXliff_filter_fieldset" class="rup-table-filter-fieldset">
							<input type="text" name="idTarea" id="idTarea_filter_Xliff">
							<input type="text" name="documentoOriginal.numExp" id="numExp_filter_Xliff">
							<input type="text" name="documentoOriginal.anyo" id="anyo_filter_Xliff">
						</fieldset>
					</form>
				</div>
				<div class="horizontal_scrollable_table">
					<!-- Tabla -->
					<table id="docsXliff"></table>
					<!-- Barra de paginación -->
					<div id="docsXliff_pager"></div>
				</div>
			</div>	
		</div>
		<div id="subidaFicheroPidFinal" class="capaPIDenPestanaDoc oculto">
			<fieldset id="subidaFicheroPidFinal_fieldset" >			
				<div id="subidaFicheroPidFinal_feedback"></div>
				<form id="subidaFicheroPidFinal_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
					<div class="form-group row">
						<label for="ficheroFinal" class="control-label col-md-3"><spring:message code="label.fichero" /> (*):</label>
						<div class="col-md-6" id="divFicheroFinal">
							<input type="file" name="fichero" id="ficheroFinal"  class="form-control">
						</div>
						<input type="hidden" id="reqEncriptado" name="reqEncriptado" />
					</div>
				</form>
				<p class="scheduler-border" style="text-align: center;" id="txtEncriptadoFinal"><em><strong><spring:message code="mensaje.fichero.encriptadoRequerido" /></strong></em></p>
			</fieldset>
		</div>
	</div>
	<div id="subidaFicheroPidXliff" class="capaPIDenPestanaDoc oculto">
		<fieldset id="subidaFicheroPidXliff_fieldset" >			
			<div id="subidaFicheroPidXliff_feedback"></div>
			<form id="subidaFicheroPidXliff_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
				<div class="form-group row">
					<label for="ficheroXliff" class="control-label col-md-3"><spring:message code="label.fichero" /> (*):</label>
					<div class="col-md-6" id="divFicheroXliff">
						<input type="file" name="fichero" id="ficheroXliff"  class="form-control">
					</div>
					<input type="hidden" id="idTareaXliff" name="idTarea" />
					<input type="hidden" id="reqEncriptadoXliff" name="reqEncriptado" />
				</div>
			</form>
		</fieldset>
	</div>
	
</div>

