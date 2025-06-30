<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid" id="divCargaSolicitudes">
	<h2 data-i18n="label.encriptacionficheros">
		<spring:message code="label.encriptacionficheros" />
	</h2>
	<hr class="mb-2">

	<div id="ficheroEncriptar" class="row">
		<fieldset id="ficheroEncriptar_fieldset" class="form_fieldset">
			<div id="ficheroEncriptar_feedback"></div>
			<form id="ficheroEncriptar_form" enctype="multipart/form-data"
				method="POST" class="form-horizontal">
				<br/>
				<div class="form-group row">
					<input type="hidden" id="password" name="password"
						class="form-control col-md-3">
					<div class="form-group col-md-4">
						<div id="idTiposEntidad_filter_table">
							<div class="col-md-12">
								<input type="radio" name="opcion" id="opcion_E" value="E"
									checked /> <label for="opcion_E"
									class="radio-inline rup-table-filter-fieldset"
									data-i18n="label.encriptar"><spring:message
										code="label.encriptar" /></label>
							</div>
							<div class="col-md-12">
								<input type="radio" name="opcion" id="opcion_D" value="D" /> <label
									for="opcion_D" class="radio-inline rup-table-filter-fieldset"
									data-i18n="label.desencriptar"><spring:message
										code="label.desencriptar" /></label>
							</div>
						</div>
					</div>
					<div class="form-group col-md-6">
						<div class="col-md-9" id="divFicheroEncriptar" hidden>
							<input type="file" name="ficheroFile" id="ficheroFile"
								class="form-control">
						</div>
						<div id="divNombreFicheroEncriptar" style="display: none">
							<div class="col-md-6">
								<input type="text" id="nombreFicheroEncriptar"
									class="form-control" readonly="readonly">
							</div>
						</div>
						<button id="ficheroAttach" name="ficheroAttach" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" style="margin-top:0"><spring:message code="label.adjuntarFichero"/></button>
						<input type="text" class="form-control" id="nombreFicheroAttach" name="nombreFicheroAttach" readonly="readonly" style="width:300px; display:inline-block"/>
					</div>
				</div>
				<!-- Botonera del formulario de detalle -->
				<div class="pull-right">
					<button id="enviarButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" > 
						<spring:message code="ejecutar" />
					</button>
				</div>
			</form>
		</fieldset>
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
