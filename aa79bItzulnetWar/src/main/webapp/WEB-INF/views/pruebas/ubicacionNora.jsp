<%@include file="/WEB-INF/includeTemplate.inc"%>

<div class="container-fluid" id="divCargaSolicitudes"> 
	<h2>PRUEBAS</h2>
	<hr class="mb-2">
	<form id="ubicacionNora_form" class="form-horizontal">
		<div id="direccionDiv">
				<input type="hidden" id="dirNora" name="dirNora" value="${direccion.dirNora}">
				<input type="hidden" id="noraId" name="codNora" value="${direccion.codNora}">
				<input type="hidden" id="tipoNora" name="tipoNora" value="${direccion.tipoNora}">
				<input type="hidden" id="paisId" name="pais.id" value="${direccion.pais.id}">
				<input type="hidden" id="provinciaId" name="provincia.id" value="${direccion.provincia.id}">
				<input type="hidden" id="municipioId" name="municipio.id" value="${direccion.municipio.id}">
				<input type="hidden" id="localidadId" name="localidad.id" value="${direccion.localidad.id}">
				<input type="hidden" id="calleId" name="calle.id" value="${direccion.calle.id}">
				<input type="hidden" id="portalId" name="portal.id" value="${direccion.portal.id}">
			
				<fieldset class="fieldset_remarcado">
					<legend>Dirección</legend>
					<!-- Toolbar -->
					<div id="direccion_toolbar" class="separarIzq5"></div>
					<div class="form-group row">
						<label for="provincia" class="control-label col-md-1 col-xs-10">
							T.H.:
						</label>
						<div class="col-md-4 col-xs-10">
							<input	type="text" name="provincia.dsO" class="form-control" id="provincia" value="${direccion.provincia.dsO}" readonly="readonly"/>
						</div>
						<label for="municipio" class="control-label col-md-1 col-xs-10">
							Municipio:
						</label>
						<div class="col-md-4 col-xs-10">
							<input	type="text" name="municipio.dsO" class="form-control" id="municipio" value="${direccion.municipio.dsO}" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group row">
						<label for="localidad" class="control-label col-md-1 col-xs-10">
							Localidad:
						</label>
						<div class="col-md-4 col-xs-10">
							<input	type="text" name="localidad.dsO" class="form-control" id="localidad" value="${direccion.localidad.dsO}" readonly="readonly"/>
						</div>
						<label for="pais" class="control-label col-md-1 col-xs-10">
							País:
						</label>
						<div class="col-md-4 col-xs-10">
							<input	type="text"  name="pais.dsO"  class="form-control" id="pais" value="${direccion.pais.dsO}" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group row">
						<label for="calle" class="control-label col-md-1 col-xs-10">
							Calle:
						</label>
						<div class="col-md-4 col-xs-10">
							<input	type="text" name="calle.dsO" class="form-control" id="calle" value="${direccion.calle.dsO}" readonly="readonly"/>
						</div>
						<label for="portal" class="control-label col-md-1 col-xs-10">
							Portal:
						</label>
						<div class="col-md-2 col-xs-10">
							<input	type="text" name="portal.txtPortal" class="form-control" id="portal" value="${direccion.portal.txtPortal}" readonly="readonly"/>
						</div>
						<label for="cp" class="control-label col-md-1 col-xs-10">
							C.P.:
						</label>
						<div class="col-md-2 col-xs-10">
							<input	type="text" name="codPostal" class="form-control" id="cp" value="${direccion.codPostal}" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group row">
						<label for="escalera" class="control-label col-md-1 col-xs-10">
							Escalera:
						</label>
						<div class="col-md-1 col-xs-10">
							<input	type="text" name="escalera" class="form-control" id="escalera" value="${direccion.escalera}" readonly="readonly"/>
						</div>
						<label for="piso" class="control-label col-md-1 col-xs-10">
							Piso:
						</label>
						<div class="col-md-1 col-xs-10">
							<input	type="text" name="piso" class="form-control" id="piso" value="${direccion.piso}" readonly="readonly"/>
						</div>
						<label for="mano" class="control-label col-md-1 col-xs-10">
							Mano:
						</label>
						<div class="col-md-1 col-xs-10">
							<input	type="text" name="mano" class="form-control" id="mano" value="${direccion.mano}" readonly="readonly"/>
						</div>
						<label for="puerta" class="control-label col-md-1 col-xs-10">
							Puerta:
						</label>
						<div class="col-md-1 col-xs-10">
							<input	type="text" name="puerta" class="form-control" id="puerta" value="${direccion.puerta}" readonly="readonly"/>
						</div>
					</div>
					
					<div class="form-group row">
						<label for="aproxPostal" class="control-label col-md-1 col-xs-10">
							Dirección:
						</label>
						<div class="col-md-8 col-xs-10">
							<input	type="text" name="aprox" class="form-control" id="aproxPostal" value="${direccion.aprox}" readonly="readonly"/>
						</div>
					</div>
				</fieldset>
			</div>
		</form>
		<!-- Botonera del formulario de detalle -->
		<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
			<div class="ui-dialog-buttonset">
				<!-- Botón Guardar -->
				<button id="form_button_save" type="button">
					<spring:message code="save" />
				</button>
			</div>
		</div>
	</div>