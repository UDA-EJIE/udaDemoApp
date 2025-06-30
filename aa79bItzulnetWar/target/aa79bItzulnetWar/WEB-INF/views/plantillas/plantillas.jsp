<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid">
	<h2><spring:message code="menu.plantillas" /></h2>
	<hr class="mb-2">
	<div id="plantillas_div" class="rup-table-container">
		<div id="plantillas_feedback"></div>
		<!-- Feedback de la tabla -->
		<div id="plantillas_toolbar"></div>
		<!-- Botonera de la tabla -->
		
<div id="plantillas_filter_div" class="filterForm oculto"> 
		<form id="plantillas_filter_form" class="form-horizontal">
		<div id="plantillas_filter_toolbar" class="formulario_legend"></div>
		<fieldset id="plantillas_filter_fieldset" class="rup-table-filter-fieldset ">
		
		
							<div id="plantillas_filter_buttonSet" class="pull-right" style="margin:0 1rem 1rem 0">
								<!-- Botón de filtrado -->
								<!-- input id="estudioExpedientes_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' /-->
								
								<button id="plantillas_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
									<spring:message code="filter" />
								</button>
								
								<!-- Enlace de limpiar -->
								<a id="plantillas_filter_cleanLinkModificado" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
							</div>
		
		</fieldset>
		</form>
</div>		
		
		<!-- Tabla -->
		<div class="table pb-2">
			<table id="plantillas"></table>
		</div>
		<!-- Barra de paginación -->
		<div id="plantillas_pager"></div>
	</div>
</div>

<div class="container-fluid">
	<!-- Formulario de detalle -->
	<div id="plantillas_detail_div" class="rup-table-formEdit-detail">
		<!-- Barra de navegación del detalle -->
		<div class="ui-dialog-content ui-widget-content">
				
			<form id="subidaFicheroPid_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
				<!-- Formulario -->
				<div id="plantillas_detail_feedback"></div>
				<!-- Feedback del formulario de detalle -->

				<!-- Campos del formulario de detalle -->
				<div class="row">

				<div class="form-group col-lg-4">
					<input type="hidden" name="idPlantilla" id="id0A7" readonly="readonly"/>
					<input type="hidden" name="nombrePlantilla" id="nombrePlantilla0A7" readonly="readonly"/>
					<input type="hidden" name="varPlantilla" id="varPlantilla0A7" readonly="readonly"/>
					<input type="hidden" name="idFicheroPlantilla" id="idFicheroPlantilla0A7" readonly="readonly"/>
				
					<input type="hidden" name="data.id" id="codigo" readonly="readonly"/>
					<input type="hidden" name="data.fichero" id="titulo" readonly="readonly"/>
					<input type="hidden" name="data.extension" id="extension" readonly="readonly"/>
					<input type="hidden" name="data.contentType" id="contentType" readonly="readonly"/>
					<input type="hidden" name="data.tamano" id="tamano" readonly="readonly"/>
					<input type="hidden" name="data.encriptado" id="encriptado" readonly="readonly"/>
					<input type="hidden" name="data.rutaPif" id="rutaPif" readonly="readonly"/>
					<input type="hidden" name="data.oid" id="oidFichero" readonly="readonly"/>
					<input type="hidden" name="data.nombre" id="nombre" readonly="readonly"/>
				</div>

				</div>
				<div class="row">
					<div class="form-group col-lg-12">
						<label for="nombrePlantilla0A7_detail_table"><spring:message code="label.plantillasMenu" />(*):</label> 
						<input type="text" maxlength="50" name="nombrePlantilla0A7" class="form-control" id="nombrePlantilla0A7_detail_table" readonly="readonly" disabled="disabled" />
					</div>
				</div>
				<div class="row">
					<div class="form-group  col-lg-5">
						<label for="fichero_detail_table" class="control-label"><spring:message code="label.fichero" />:</label><br>
						<div id="enlaceNombre" class="flotaIzda"></div>
					</div>
					<div class="form-group  col-lg-2">
						<button id="pidButtonFinal" type="button" onclick="clickPidButtonFinal()" class="btnPID ui-button ui-corner-all ui-widget" style="margin-top:18px"><spring:message code="label.fichero" /></button>
					</div>
					<div id="subidaFicheroPid" class="capaPIDenPestanaDoc oculto">
						<fieldset id="subidaFicheroPid_fieldset" >			
							<div id="subidaFicheroPid_feedback"></div>
								<div class="form-group row">
									<label for="fichero" class="control-label col-md-5"><spring:message code="label.fichero" /> (*):</label>
									<div class="col-md-5" id="divFichero">
										<input type="file" name="fichero" id="ficheroFile" class="form-control">
									</div>
									<input type="hidden" id="idBotonUpload" name="idBotonUpload"/>
									<input type="hidden" id="reqEncriptado" name="reqEncriptado" />
								</div>
						</fieldset>
					</div>

					<div class="form-group col-lg-5">
						<input type="text" maxlength="250" name="ficheroNombre" class="form-control" id="ficheroNombre" style="margin-top:18px" readonly="readonly"/>
					</div>
							
				</div>
				<div class="row">
					<div class="form-group  col-lg-12">
						<label for="varPlantilla0A7_detail_table" class="control-label"><spring:message	code="label.plantillasVariables" />:</label>
						<textarea name="varPlantilla0A7" maxlength="4000" class="form-control" id="varPlantilla0A7_detail_table" readonly="readonly" disabled="disabled" /></textarea>
					</div>
				</div>
				<!-- Fin campos del formulario de detalle -->

			</form>
		</div>
		<!-- Botonera del formulario de detalle -->
		<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
			<div class="ui-dialog-buttonset">
				<!-- Botón Guardar -->
				<button id="plantillas_detail_button_save" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
					<spring:message code="save" />
				</button>
				<!-- Enlace cancelar -->
				<a href="javascript:void(0)" id="plantillas_detail_button_cancel"
					class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
			</div>
		</div>
	</div>
</div>