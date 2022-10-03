<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<section class="row">
	<div class="col-12">
		<h2 class="title mb-3">
			<spring:message code="patron.formulario.titulo" />
		</h2>

		<div id="feedbackMensajes"></div>
		<div id="tabsFormulario"></div>
		<div id="divformHttpSubmit" class="m-3">


			<!-- 			<fieldset class="alumnoFieldset"> -->
			<!-- 					<legend>Configuracion</legend> -->
			<!-- 					<input type="checkbox" id="liveCheckingErrors" checked="checked" />Comprobar mensajes al vuelo -->
			<!-- 					<input type="checkbox" id="checkFeedbackError" checked="checked" />Mostrar mensaje en feedback  -->
			<!-- 					<input type="checkbox" id="checkShowErrorsFeedback" />Mostrar errores en feedback -->
			<!-- 					<input type="checkbox" id="checkShowFieldErrorsTip" />Mostrar errores de campos como tip -->
			<!-- 					<br/> -->

			<!-- 					<select id="formTypeCombo"> -->
			<!-- 						<option value="ajax" selected="selected">Ajax Submit</option> -->
			<!-- 						<option value="http">Http Submit</option> -->
			<!-- 					</select> -->
			<!-- 					<button id="botonConfiguracion">Configurar</button> -->
			<!-- 				</fieldset> -->

			<form:form id="formHttpSubmit" action='${pageContext.request.contextPath}/patrones/form/ejemplo' modelAttribute="alumno">

				<fieldset class="alumnoFieldset">
					<legend>
						<spring:message code="datosPersonales" />
					</legend>
					
					<div class="form-row">
						<div class="form-groupMaterial col-sm">
							<form:input path="nombre" size="20" id="nombre"/>
							<label for="nombre" class="label"><spring:message code="nombre" /></label>
						</div>

						<div class="form-groupMaterial col-sm">
							<form:input path="apellido1" size="30" id="apellido1"/>
							<label for="apellido1" class="label"><spring:message code="apellido1" /></label>
						</div>

						<div class="form-groupMaterial col-sm">
							<form:input path="apellido2" size="30" id="apellido2"/>
							<label for="apellido2" class="label"><spring:message code="apellido2" /></label>
						</div>
					</div>

					<div class="form-row">
						<div class="form-groupMaterial col-sm">
							<form:input path="sexo" id="sexo"/>
							<label for="sexo" class="label"><spring:message code="sexo" /></label>
						</div>
						
						<div class="form-groupMaterial col-sm">
							<form:input path="dni" id="dni"/>
							<label for="dni" class="label"><spring:message code="dni" /></label>
						</div>

						<div class="form-groupMaterial col-sm">
							<form:input path="fechaNacimiento2" id="fechaNacimiento"/>
							<label for="fechaNacimiento" class="label"><spring:message code="fechaNacimiento" /></label>
						</div>
		
						<div class="form-groupMaterial col-sm">
							<form:input path="telefono" id="telefono"/>
							<label for="telefono" class="label"><spring:message code="telefono" /></label>
						</div>
					</div>

				</fieldset>

				<fieldset class="alumnoFieldset">
					<legend>
						<spring:message code="datosCuentaUsuario" />
					</legend>

					<div class="form-row">
						<div class="form-groupMaterial col-12">
							<form:input path="usuario" id="usuario"/>
							<label for="usuario" class="label"><spring:message code="usuario" /></label>
						</div>
						<div class="form-row col-6">
							<div class="form-groupMaterial col-sm">
								<form:password path="password" id="password"/>
								<label for="password" class="label"><spring:message code="contrasena" /></label>
							</div>
							<div class="form-groupMaterial col-sm">
								<form:password path="password" id="password_confirm"/>
								<label for="password_confirm" class="label"><spring:message code="confirmarContrasena" /></label>
							</div>
						</div>
						<div class="form-row col-6">
							<div class="form-groupMaterial col-sm">
								<form:input path="email" id="email"/>
								<label for="email" class="label"><spring:message code="email" /></label>
							</div>
							<div class="form-groupMaterial col-sm">
								<form:input path="email" id="email_confirm"/>
								<label for="email_confirm" class="label"><spring:message code="confirmarEmail" /></label>
							</div>
						</div>
					</div>

				</fieldset>

				<fieldset class="alumnoFieldset">
					<legend>
						<spring:message code="datosDomicilio" />
					</legend>

					<div class="form-row">
						<div class="form-groupMaterial col-sm">
							<form:select path="pais.id" id="pais">
								<form:options items="${model.paises}" itemLabel="dsO" itemValue="id" />
							</form:select>
							<label for="nombre" class="label"><spring:message code="pais" /></label>
						</div>
						<div class="form-groupMaterial col-sm">
							<form:select path="autonomia" id="autonomia">
								<form:options items="${model.autonomias}" itemLabel="dsO" itemValue="rowId" />
							</form:select>
							<label for="autonomia" class="label"><spring:message code="autonomia" /></label>
						</div>
						<div class="form-groupMaterial col-sm">
							<form:input path="provincia.id" id="provincia"/>
							<label for="provincia" class="label"><spring:message code="provincia" /></label>
						</div>
					</div>

					<div class="form-row">
						<div class="form-groupMaterial col-sm">
							<form:input path="municipio.id" size="30" id="municipio"/>
							<label for="municipio" class="label"><spring:message code="municipio" /></label>
						</div>
						<div class="form-groupMaterial col-sm">
							<form:input path="calle.id" size="50" id="calle"/>
							<label for="calle" class="label"><spring:message code="calle" /></label>
						</div>
					</div>

				</fieldset>

				<button class="btn-material btn-material-primary-high-emphasis mt-2" type="submit">Enviar</button>

			</form:form>


			<fieldset class="alumnoFieldset mt-4">
				<legend>
					<spring:message code="patron.formulario.funcionesAjaxForm" />
				</legend>
				<div>
					<span><spring:message code="patron.formulario.funcionesAjaxForm.formSerialize" /></span>
					<code>
						<br />
						<spring:message
							code="patron.formulario.funcionesAjaxForm.formSerialize.ejemplo" />
						<br />
					</code>
					<button id="botonFormSerialize" class="btn-material btn-material-primary-high-emphasis mb-4">
						<spring:message code="patron.formulario.funcionesAjaxForm.formSerialize" />
					</button>
					<div id="resultadoFormSerialize" class="col1"></div>
				</div>
				<div>
					<span><spring:message code="patron.formulario.funcionesAjaxForm.fieldSerialize" /></span>
					<code>
						<br />
						<spring:message code="patron.formulario.funcionesAjaxForm.fieldSerialize.ejemplo" />
						<br />
					</code>
					<button id="botonFieldSerialize" class="btn-material btn-material-primary-high-emphasis mb-4">
						<spring:message code="patron.formulario.funcionesAjaxForm.fieldSerialize" />
					</button>
					<div id="resultadoFieldSerialize" class="col1"></div>
				</div>
				<div>
					<span><spring:message code="patron.formulario.funcionesAjaxForm.fieldValue" /></span>
					<code>
						<br />
						<spring:message code="patron.formulario.funcionesAjaxForm.fieldValue.ejemplo" />
						<br />
					</code>
					<button id="botonFieldValue" class="btn-material btn-material-primary-high-emphasis mb-4">
						<spring:message code="patron.formulario.funcionesAjaxForm.fieldValue" />
					</button>
					<div id="resultadoFieldValue" class="col1"></div>
				</div>
				<div>
					<span><spring:message code="patron.formulario.funcionesAjaxForm.resetForm" /></span>
					<code>
						<br />
						<spring:message code="patron.formulario.funcionesAjaxForm.resetForm.ejemplo" />
						<br />
					</code>
					<button id="botonResetForm" class="btn-material btn-material-primary-high-emphasis mb-4">
						<spring:message code="patron.formulario.funcionesAjaxForm.resetForm" />
					</button>
					<div id="resultadoResetForm" class="col1"></div>
				</div>
				<div>
					<span><spring:message code="patron.formulario.funcionesAjaxForm.clearForm" /></span>
					<code>
						<br />
						<spring:message code="patron.formulario.funcionesAjaxForm.clearForm.ejemplo" />
						<br />
					</code>
					<button id="botonClearForm" class="btn-material btn-material-primary-high-emphasis mb-4">
						<spring:message code="patron.formulario.funcionesAjaxForm.clearForm" />
					</button>
					<div id="resultadoClearForm" class="col1"></div>
				</div>
				<div>
					<span><spring:message code="patron.formulario.funcionesAjaxForm.clearFields" /></span>
					<code>
						<br />
						<spring:message code="patron.formulario.funcionesAjaxForm.clearFields.ejemplo" />
						<br />
					</code>
					<button id="botonClearFields" class="btn-material btn-material-primary-high-emphasis mb-4">
						<spring:message code="patron.formulario.funcionesAjaxForm.clearFields" />
					</button>
					<div id="resultadoClearFields" class="col1"></div>
				</div>


			</fieldset>
		</div>
		
	<div id="divModelView" class="m-3">


			<form:form id="formModelView" action='../patrones/form/ejemploFormNavigate' modelAttribute="alumno" method="post">

				<fieldset class="alumnoFieldset">
					<legend>
						<spring:message code="datosPersonales" />
					</legend>
					
					<div class="form-row">
						<div class="form-groupMaterial col-sm">
							<form:input path="nombre" size="20" id="nombreModel" required="true"/>
							<label for="nombre" class="label"><spring:message code="nombre" /></label>
						</div>

						<div class="form-groupMaterial col-sm">
							<form:input path="apellido1" size="30" id="apellido1Model"/>
							<label for="apellido1" class="label"><spring:message code="apellido1" /></label>
						</div>

						<div class="form-groupMaterial col-sm">
							<form:input path="apellido2" size="30" id="apellido2Model"/>
							<label for="apellido2" class="label"><spring:message code="apellido2" /></label>
						</div>
					</div>

					<div class="form-row">
						<div class="form-groupMaterial col-sm">
							<form:input path="sexo" id="sexoModel"/>
							<label for="sexo" class="label"><spring:message code="sexo" /></label>
						</div>
						
						<div class="form-groupMaterial col-sm">
							<form:input path="dni" id="dniModel"/>
							<label for="dni" class="label"><spring:message code="dni" /></label>
						</div>

						<div class="form-groupMaterial col-sm">
							<form:input path="telefono" id="telefonoModel"/>
							<label for="telefono" class="label"><spring:message code="telefono" /></label>
						</div>
					</div>

				</fieldset>
				<fieldset class="alumnoFieldset">
					<legend>
						<spring:message code="datosDomicilio" />
					</legend>

					<div class="form-row">
						<div class="form-groupMaterial col-sm">
							<form:select path="pais.id" id="paisModel">
								<form:options items="${model.paises}" itemLabel="dsO" itemValue="id" />
							</form:select>
							<label for="nombre" class="label"><spring:message code="pais" /></label>
						</div>
						<div class="form-groupMaterial col-sm">
							<form:select path="autonomia" id="autonomiaModel">
								<form:options items="${model.autonomias}" itemLabel="dsO" itemValue="rowId" />
							</form:select>
							<label for="autonomia" class="label"><spring:message code="autonomia" /></label>
						</div>
						<div class="form-groupMaterial col-sm">
							<form:input path="provincia.id" id="provinciaModel"/>
							<label for="provincia" class="label"><spring:message code="provincia" /></label>
						</div>
					</div>

				</fieldset>

				<button class="btn-material btn-material-primary-high-emphasis mt-2" type="submit">Enviar</button>

			</form:form>
		</div>

		<div id="divMultiplesEntidades" class="m-3">
			<%-- <form id="formMultientidades"
				action='${pageContext.request.contextPath}patrones/form/multientidades'
				method="post"> --%>
			<form:form id="formMultientidades" modelAttribute="alumnoDepartamento" method="post" >
				<fieldset class="alumnoFieldset">
					<legend>
						<spring:message code="patron.formulario.envioVariasEntidades"/>
					</legend>

					<span><spring:message code="patron.formulario.envioVariasEntidades.descripcion" /></span>

					<fieldset class="alumnoFieldset mt-4">
						<legend>
							<spring:message code="alumno" />
						</legend>
						<div class="form-row">
							<div class="form-groupMaterial col-sm">
								<form:input path="alumno.nombre" size="20" id="alumno_nombre_multi"/>
								<label for="alumno_nombre_multi" class="label"><spring:message code="nombre" /></label>
							</div>

							<div class="form-groupMaterial col-sm">
								<form:input path="alumno.apellido1" size="30" id="alumno_apellido1_multi"/>
								<label for="alumno_apellido1_multi" class="label"><spring:message code="apellido1" /></label>
							</div>

							<div class="form-groupMaterial col-sm">
								<form:input path="alumno.apellido2" size="30" id="alumno_apellido2_multi"/>
								<label for="alumno_apellido2_multi" class="label"><spring:message code="apellido2" /></label>
							</div>
						</div>
					</fieldset>
					<fieldset class="alumnoFieldset">
						<legend>
							<spring:message code="departamento" />
						</legend>
						<div class="form-row">
							<div class="form-groupMaterial col-sm">
								<form:input path="departamento.code" size="20" id="departamento_code_multi"/>
								<label for="departamento_code_multi" class="label"><spring:message code="codigo" /></label>
							</div>

							<div class="form-groupMaterial col-sm">
								<form:input path="departamento.descEs" size="30" id="departamento_descEs_multi"/>
								<label for="departamento_descEs_multi" class="label"><spring:message code="descEs" /></label>
							</div>

							<div class="form-groupMaterial col-sm">
								<form:input path="departamento.descEu" size="30" id="departamento_descEu_multi"/>
								<label for="departamento_descEu_multi" class="label"><spring:message code="descEu" /></label>
							</div>
						</div>
					</fieldset>
					<button class="btn-material btn-material-primary-high-emphasis mt-2" type="submit"><spring:message code="patron.formulario.enviarMultientidad" /></button>
				</fieldset>
			</form:form>

			<%-- <form id="formMultientidadesMismoTipo"
				action='${pageContext.request.contextPath}patrones/form/multientidadesMismoTipo'
				method="post"> --%>
			<form:form id="formMultientidadesMismoTipo" modelAttribute="formComarcas" method="post">
				<fieldset class="alumnoFieldset">
					<legend>
						<spring:message code="patron.formulario.envioVariasEntidadesIguales" />
					</legend>

					<span><spring:message code="patron.formulario.envioVariasEntidadesIguales.descripcion" /></span>

					<fieldset class="alumnoFieldset mt-4">
						<legend>
							<spring:message code="comarca" />
						</legend>
						<div class="form-row">
							<div class="form-groupMaterial col-sm">
								<form:input path="comarca1.code" size="20" id="comarca1_code_multi"/>
								<label for="comarca1_code_multi" class="label"><spring:message code="codigo" /></label>
							</div>

							<div class="form-groupMaterial col-sm">
								<form:input path="comarca1.descEs" size="30" id="comarca1_descEs_multi"/>
								<label for="comarca1_descEs_multi" class="label"><spring:message code="descEs" /></label>
							</div>

							<div class="form-groupMaterial col-sm">
								<form:input path="comarca1.descEu" size="30" id="comarca1_descEu_multi"/>
								<label for="comarca1_descEu_multi" class="label"><spring:message code="descEu" /></label>
							</div>
						</div>
					</fieldset>
					<fieldset class="alumnoFieldset">
						<legend>
							<spring:message code="comarca" />
						</legend>
						<div class="form-row">
							<div class="form-groupMaterial col-sm">
								<form:input path="comarca2.code" size="20" id="comarca2_code_multi"/>
								<label for="comarca2_code_multi" class="label"><spring:message code="codigo" /></label>
							</div>

							<div class="form-groupMaterial col-sm">
								<form:input path="comarca2.descEs" size="30" id="comarca2_descEs_multi"/>
								<label for="comarca2_descEs_multi" class="label"><spring:message code="descEs" /></label>
							</div>

							<div class="form-groupMaterial col-sm">
								<form:input path="comarca2.descEu" size="30" id="comarca2_descEu_multi"/>
								<label for="comarca2_descEu_multi" class="label"><spring:message code="descEu" /></label>
							</div>
						</div>
					</fieldset>
					<fieldset class="alumnoFieldset">
						<legend>
							<spring:message code="comarcaYprovincia" />
						</legend>
						<i><spring:message code="comarca" /></i>
						<div class="form-row mb-4">
							<div class="form-groupMaterial col-sm">
								<form:input path="comarca3.code" size="20" id="comarca3_code_multi"/>
								<label for="comarca3_code_multi" class="label"><spring:message code="codigo" /></label>
							</div>

							<div class="form-groupMaterial col-sm">
								<form:input path="comarca3.descEs" size="30" id="comarca3_descEs_multi"/>
								<label for="comarca3_descEs_multi" class="label"><spring:message code="descEs" /></label>
							</div>

							<div class="form-groupMaterial col-sm">
								<form:input path="comarca3.descEu" size="30" id="comarca3_descEu_multi"/>
								<label for="comarca3_descEu_multi" class="label"><spring:message code="descEu" /></label>
							</div>
						</div>
						<i><spring:message code="provincia" /></i>
						<div class="form-row">
							<div class="form-groupMaterial col-sm">
								<form:input path="comarca3.provincia.code" size="20" id="comarca3_provincia_code_multi"/>
								<label for="comarca3_provincia_code_multi" class="label"><spring:message code="codigo" /></label>
							</div>

							<div class="form-groupMaterial col-sm">
								<form:input path="comarca3.provincia.descEs" size="30" id="comarca3_provincia_descEs_multi"/>
								<label for="comarca3_provincia_descEs_multi" class="label"><spring:message code="descEs" /></label>
							</div>

							<div class="form-groupMaterial col-sm">
								<form:input path="comarca3.provincia.descEu" size="30" id="comarca3_provincia_descEu_multi"/>
								<label for="comarca3_descEu_multi" class="label"><spring:message code="descEu" /></label>
							</div>
						</div>
					</fieldset>
					<button class="btn-material btn-material-primary-high-emphasis mt-2" type="submit"><spring:message code="patron.formulario.enviarMultientidad" /></button>
				</fieldset>
			</form:form>
		</div>

		<div id="divSubidaFicheros" class="m-3">
			<fieldset class="alumnoFieldset">
				<legend>Subida de ficheros</legend>

				<%-- <form id="formSubidaArchivos"
					action='${pageContext.request.contextPath}patrones/form/subidaArchivos'
					method="post"> --%>
				<form:form id="formSubidaArchivos" method="post" modelAttribute="alumno">

					<div id="feedback_fileupload_form_multiple"></div>
					<div class="form-row">
						<div class="form-groupMaterial col-sm">
							<form:input path="nombre"/>
							<label id="label_nombre" for="nombre">Nombre :</label>
						</div>
						<div class="form-groupMaterial col-sm">
							<form:input path="apellido1"/>
							<label id="label_apellido1" for="apellido1">Apellido 1:</label>
						</div>
						<div class="form-groupMaterial col-sm">
							<form:input path="apellido2"/>
							<label id="label_apellido2" for="apellido2">Apellido 2:</label>
						</div>
						<div class="checkbox-material col-sm">
							<form:checkbox path="ejie" id="label_ejie" value="1"/>
							<label for="label_ejie">EJIE</label>
						</div>
					</div>
					<div class="form-row">
						<span>Archivos adjuntos:</span>
					</div>
					<div class="form-row">
						<div id="fileupload_file_form_padre" class="form-groupMaterial col-sm">
							<div class="fileupload-buttonbar">
								<input id="file_form_padre" type="file" name="fotoPadre">
								<label for="file">Foto padre: </label> 
							</div>
							<div class="fileupload-content">
								<table class="files"></table>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div id="fileupload_file_form_madre" class="form-groupMaterial col-sm">
							<div class="fileupload-buttonbar">
								<input id="file_form_madre" type="file" name="fotoMadre">
								<label for="file">Foto madre: </label>
							</div>
							<div class="fileupload-content">
								<table class="files"></table>
							</div>
						</div>
					</div>
					<div class="formulario_columna_cnt">
						<div class="formulario_linea_izda_float fileupload-buttonbar">
							<button id="sendButtonArchivos" class="btn-material btn-material-primary-high-emphasis mt-2 start" type="submit">Enviar formulario</button>
						</div>
					</div>
				</form:form>
			</fieldset>
		</div>
	</div>
</section>