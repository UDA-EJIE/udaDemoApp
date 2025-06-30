<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid" id="divCargaSolicitudes"> 
	<h2>PRUEBAS</h2>
	<hr class="mb-2">
	<div id="cargaSolicitudes_div" class="rup-table-container ivapContentNoFloat">
		<div id="cargaSolicitudes_feedback"></div>
		<div id="cargaSolicitudes_toolbar"></div>
		
		<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
			<div class="ui-dialog-buttonset">
				<!-- Botón Guardar -->
				<button id="pidButton" type="button">
					PID - PIF
				</button>
				<button id="encriptarButton" type="button">
					ENCRIPTACIÓN
				</button>
				<button id="sendMailButton" type="button">
					ENVIAR MAIL PRUEBAS
				</button>
				<button id="libroRegistroButton" type="button">
					LIBRO REGISTRO
				</button>
				<button id="batchButton" type="button">
					BATCH
				</button>
				<button id="icsButton" type="button">
					DESCARGAR ICS
				</button>
				<button id="descargarOidButton" type="button">
					DESCARGAR PRUEBA
				</button>
					
			</div>
			<!-- BUSCADOR DE PERSONAS BUTTON - INICIO -->
			<div class="ui-dialog-buttonset">
				<button id="buscadorPersonasButton" type="button">
					BUSCADOR PERSONAS
				</button>
			</div>
			<!-- BUSCADOR DE PERSONAS BUTTON - FIN -->
			<!-- BUSCADOR DE LOTES BUTTON - INICIO -->
			<div class="ui-dialog-buttonset">
				<button id="buscadorLotesButton" type="button">
					BUSCADOR LOTES
				</button>
			</div>
			<!-- BUSCADOR DE LOTES BUTTON - FIN -->
			
		</div>
	</div>
	<div id="subidaFicheroPid">
		<fieldset id="subidaFicheroPid_fieldset" >
			<legend class="scheduler-border">Subir nuevo fichero</legend>
			<div id="subidaFicheroPid_feedback"></div>
			<form id="subidaFicheroPid_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
				<div class="form-group row">
					<label for="fichero" class="control-label col-md-3">*Fichero:</label>
					<div class="col-md-6" id="divFichero">
						<input type="file" name="fichero" id="fichero"  class="form-control">
					</div>
					<div id="divNombreFichero" style="display:none">
						<div class="col-md-6">
							<input type="text" id="nombreFichero" class="form-control" readonly="readonly">
						</div>
						<div class="col-md-2">
							<input id="boton_modificar" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="comun.modificar" />' />
						</div>
					</div>
				</div>
			</form>
			<form id="cargaSolicitudes_form" class="form-horizontal" method="POST">
				<input type="hidden" name="rutaPif" id="rutaPif"/>
			</form>
		</fieldset>
	</div>
	
	<div id="ficheroEncriptar">
		<fieldset id="ficheroEncriptar_fieldset" >
			<legend class="scheduler-border">Encriptar un fichero</legend>
			<div id="ficheroEncriptar_feedback"></div>
			<form id="ficheroEncriptar_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
				<div class="form-group row">
					<div id="idTiposEntidad_filter_table">
						<div class="col-md-3">
							<input type="radio" name="opcion" id="opcion_E" value="E" checked/>
							<label for="opcion_E" class="radio-inline rup-table-filter-fieldset">Encriptar</label>
						</div>
						<div class="col-md-3">
							<input type="radio" name="opcion" id="opcion_D" value="D"/>
							<label for="opcion_D" class="radio-inline rup-table-filter-fieldset">Desencriptar</label>
						</div>
					</div>
				</div>
				<div class="form-group row">
					<label for="ficheroEncriptar" class="control-label col-md-3">*Fichero:</label>
					<div class="col-md-6" id="divFicheroEncriptar">
						<input type="file" name="ficheroFile" id="ficheroFile"  class="form-control">
					</div>
					<div id="divNombreFicheroEncriptar" style="display:none">
						<div class="col-md-6">
							<input type="text" id="nombreFicheroEncriptar" class="form-control" readonly="readonly">
						</div>
					</div>
				</div>
				<!-- Botonera del formulario de detalle -->
				<div class="pull-right">
					<button id="enviarButton" type="button">
						<spring:message code="save" />
					</button>
					<!-- Enlace cancelar -->
					<a href="javascript:void(0)" id="cancelarButton" class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
				</div>
			</form>
		</fieldset>
	</div>
	<!-- BUSCADOR DE PERSONAS MODAL - INICIO -->
	<div id="buscadorPersonas" style="display:none"></div>
	<!-- BUSCADOR DE PERSONAS MODAL - FIN -->
	<!-- BUSCADOR DE LOTES MODAL - INICIO -->
	<div id="buscadorLotes" style="display:none"></div>
	<!-- BUSCADOR DE LOTES MODAL - FIN -->
	
	
</div>
