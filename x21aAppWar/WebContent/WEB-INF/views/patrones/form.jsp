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
		<h2>
			<spring:message code="patron.formulario.titulo" />
		</h2>
		<hr>

		<div id="feedbackMensajes"></div>
		<div id="tabsFormulario"></div>
		<div id="divformHttpSubmit">


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

			<form:form id="formHttpSubmit"
				action='${pageContext.request.contextPath}patrones/form/ejemplo'
				modelAttribute="alumno">

				<fieldset class="alumnoFieldset">
					<legend>
						<spring:message code="datosPersonales" />
					</legend>
					<div class="two-col">
						<div class="col1">
							<label for="nombre" class="label"><spring:message
									code="nombre" /></label> <input type="text" name="nombre"
								class="formulario_linea_input" size="20" id="nombre" />
						</div>

						<div class="col1">
							<label for="apellido1" class="label"><spring:message
									code="apellido1" /></label> <input type="text" name="apellido1"
								class="formulario_linea_input" size="30" id="apellido1" />
						</div>

						<div class="col1">
							<label for="apellido2" class="label"><spring:message
									code="apellido2" /></label> <input type="text" name="apellido2"
								class="formulario_linea_input" size="30" id="apellido2" />
						</div>
					</div>

					<div class="two-col">
						<div class="col1">
							<label for="sexo" class="label"><spring:message
									code="sexo" /></label> <input type="text" name="sexo"
								class="formulario_linea_input" id="sexo" />
						</div>

						<div class="col1">
							<label for="fechaNacimiento" class="label"><spring:message
									code="fechaNacimiento" /></label> <input type="text"
								name="fechaNacimiento" class="formulario_linea_input"
								id="fechaNacimiento" />
						</div>

						<div class="col1">
							<label for="telefono" class="label"><spring:message
									code="telefono" /></label> <input type="text" name="telefono"
								class="formulario_linea_input" id="telefono" />
						</div>
					</div>

					<div class="two-col">
						<div class="col1">
							<label for="dni" class="label"><spring:message code="dni" /></label>
							<input type="text" name="dni" class="formulario_linea_input"
								id="dni" />
						</div>

					</div>

				</fieldset>

				<fieldset class="alumnoFieldset">
					<legend>
						<spring:message code="datosCuentaUsuario" />
					</legend>

					<div class="two-col">
						<div class="col1">
							<label for="usuario" class="label"><spring:message
									code="usuario" /></label> <input type="text" name="usuario"
								class="formulario_linea_input" id="usuario" />
						</div>
						<div class="col1">
							<div>
								<label for="password" class="label"><spring:message
										code="contrasena" /></label> <input type="password" name="password"
									class="formulario_linea_input" id="password" />
							</div>
							<div>
								<label for="password_confirm" class="label"><spring:message
										code="confirmarContrasena" /></label> <input type="password"
									name="password_confirm" class="formulario_linea_input"
									id="password_confirm" />
							</div>
						</div>
						<div class="col1">
							<div>
								<label for="email" class="label"><spring:message
										code="email" /></label> <input type="text" name="email"
									class="formulario_linea_input" id="email" />
							</div>
							<div>
								<label for="email_confirm" class="label"><spring:message
										code="confirmarEmail" /></label> <input type="text"
									name="email_confirm" class="formulario_linea_input"
									id="email_confirm" />
							</div>
						</div>
					</div>

				</fieldset>

				<fieldset class="alumnoFieldset">
					<legend>
						<spring:message code="datosDomicilio" />
					</legend>

					<div class="two-col">
						<div class="col1">
							<label for="nombre" class="label"><spring:message
									code="pais" /></label>
							<form:select path="pais.id" class="formulario_linea_input"
								id="pais">
								<form:options items="${model.paises}" itemLabel="dsO"
									itemValue="id" />
							</form:select>
							<!-- 						   <input type="text" name="pais.id" class="formulario_linea_input" id="pais" /> -->
						</div>
						<div class="col1">
							<label for="autonomia" class="label"><spring:message
									code="autonomia" /></label>
							<form:select path="autonomia.id" class="formulario_linea_input"
								id="autonomia">
								<form:options items="${model.autonomias}" itemLabel="dsO"
									itemValue="id" />
							</form:select>
							<!-- 							<input type="text" name="autonomia.id" class="formulario_linea_input" id="autonomia" /> -->
						</div>
						<div class="col1">
							<label for="provincia" class="label"><spring:message
									code="provincia" /></label> <input type="text" name="provincia.id"
								class="formulario_linea_input" id="provincia" />
						</div>
					</div>

					<div class="two-col">
						<div class="col1">
							<label for="municipio" class="label"><spring:message
									code="municipio" /></label> <input type="text" name="municipio.id"
								class="formulario_linea_input" size="30" id="municipio" />
						</div>
						<div class="col1">
							<label for="calle" class="label"><spring:message
									code="calle" /></label> <input type="text" name="calle.id"
								class="formulario_linea_input" size="50" id="calle" />
						</div>
					</div>

				</fieldset>

				<input type="submit" value="Enviar" />

			</form:form>


			<fieldset class="alumnoFieldset">
				<legend>
					<spring:message code="patron.formulario.funcionesAjaxForm" />
				</legend>
				<div>
					<span><spring:message
							code="patron.formulario.funcionesAjaxForm.formSerialize" /></span>
					<code>
						<br />
						<spring:message
							code="patron.formulario.funcionesAjaxForm.formSerialize.ejemplo" />
						<br />
					</code>
					<button id="botonFormSerialize">
						<spring:message
							code="patron.formulario.funcionesAjaxForm.formSerialize" />
					</button>
					<div id="resultadoFormSerialize" class="col1"></div>
				</div>
				<div>
					<span><spring:message
							code="patron.formulario.funcionesAjaxForm.fieldSerialize" /></span>
					<code>
						<br />
						<spring:message
							code="patron.formulario.funcionesAjaxForm.fieldSerialize.ejemplo" />
						<br />
					</code>
					<button id="botonFieldSerialize">
						<spring:message
							code="patron.formulario.funcionesAjaxForm.fieldSerialize" />
					</button>
					<div id="resultadoFieldSerialize" class="col1"></div>
				</div>
				<div>
					<span><spring:message
							code="patron.formulario.funcionesAjaxForm.fieldValue" /></span>
					<code>
						<br />
						<spring:message
							code="patron.formulario.funcionesAjaxForm.fieldValue.ejemplo" />
						<br />
					</code>
					<button id="botonFieldValue">
						<spring:message
							code="patron.formulario.funcionesAjaxForm.fieldValue" />
					</button>
					<div id="resultadoFieldValue" class="col1"></div>
				</div>
				<div>
					<span><spring:message
							code="patron.formulario.funcionesAjaxForm.resetForm" /></span>
					<code>
						<br />
						<spring:message
							code="patron.formulario.funcionesAjaxForm.resetForm.ejemplo" />
						<br />
					</code>
					<button id="botonResetForm">
						<spring:message
							code="patron.formulario.funcionesAjaxForm.resetForm" />
					</button>
					<div id="resultadoResetForm" class="col1"></div>
				</div>
				<div>
					<span><spring:message
							code="patron.formulario.funcionesAjaxForm.clearForm" /></span>
					<code>
						<br />
						<spring:message
							code="patron.formulario.funcionesAjaxForm.clearForm.ejemplo" />
						<br />
					</code>
					<button id="botonClearForm">
						<spring:message
							code="patron.formulario.funcionesAjaxForm.clearForm" />
					</button>
					<div id="resultadoClearForm" class="col1"></div>
				</div>
				<div>
					<span><spring:message
							code="patron.formulario.funcionesAjaxForm.clearFields" /></span>
					<code>
						<br />
						<spring:message
							code="patron.formulario.funcionesAjaxForm.clearFields.ejemplo" />
						<br />
					</code>
					<button id="botonClearFields">
						<spring:message
							code="patron.formulario.funcionesAjaxForm.clearFields" />
					</button>
					<div id="resultadoClearFields" class="col1"></div>
				</div>


			</fieldset>
		</div>

		<div id="divMultiplesEntidades">
			<form id="formMultientidades"
				action='${pageContext.request.contextPath}patrones/form/multientidades'
				method="post">
				<fieldset class="alumnoFieldset">
					<legend>
						<spring:message code="patron.formulario.envioVariasEntidades" />
					</legend>

					<span><spring:message
							code="patron.formulario.envioVariasEntidades.descripcion" /></span>

					<fieldset class="alumnoFieldset">
						<legend>
							<spring:message code="alumno" />
						</legend>
						<div class="two-col">
							<div class="col1">
								<label for="alumno_nombre_multi" class="label"><spring:message
										code="nombre" /></label> <input type="text" name="alumno.nombre"
									class="formulario_linea_input" size="20"
									id="alumno_nombre_multi" />
							</div>

							<div class="col1">
								<label for="alumno_apellido1_multi" class="label"><spring:message
										code="apellido1" /></label> <input type="text"
									name="alumno.apellido1" class="formulario_linea_input"
									size="30" id="alumno_apellido1_multi" />
							</div>

							<div class="col1">
								<label for="alumno_apellido2_multi" class="label"><spring:message
										code="apellido2" /></label> <input type="text"
									name="alumno.apellido2" class="formulario_linea_input"
									size="30" id="alumno_apellido2_multi" />
							</div>
						</div>
					</fieldset>
					<fieldset class="alumnoFieldset">
						<legend>
							<spring:message code="departamento" />
						</legend>
						<div class="two-col">
							<div class="col1">
								<label for="departamento_code_multi" class="label"><spring:message
										code="codigo" /></label> <input type="text" name="departamento.code"
									class="formulario_linea_input" size="20"
									id="departamento_code_multi" />
							</div>

							<div class="col1">
								<label for="departamento_descEs_multi" class="label"><spring:message
										code="descEs" /></label> <input type="text"
									name="departamento.descEs" class="formulario_linea_input"
									size="30" id="departamento_descEs_multi" />
							</div>

							<div class="col1">
								<label for="departamento_descEu_multi" class="label"><spring:message
										code="descEu" /></label> <input type="text"
									name="departamento.descEu" class="formulario_linea_input"
									size="30" id="departamento_descEu_multi" />
							</div>
						</div>
					</fieldset>
					<input type="submit"
						value="<spring:message code="patron.formulario.enviarMultientidad" />" />
				</fieldset>
			</form>

			<form id="formMultientidadesMismoTipo"
				action='${pageContext.request.contextPath}patrones/form/multientidadesMismoTipo'
				method="post">
				<fieldset class="alumnoFieldset">
					<legend>
						<spring:message
							code="patron.formulario.envioVariasEntidadesIguales" />
					</legend>

					<span><spring:message
							code="patron.formulario.envioVariasEntidadesIguales.descripcion" /></span>

					<fieldset class="alumnoFieldset">
						<legend>
							<spring:message code="comarca" />
						</legend>
						<div class="two-col">
							<div class="col1">
								<label for="comarca1_code_multi" class="label"><spring:message
										code="codigo" /></label> <input type="text" name="comarca1.code"
									class="formulario_linea_input" size="20"
									id="comarca1_code_multi" />
							</div>

							<div class="col1">
								<label for="comarca1_descEs_multi" class="label"><spring:message
										code="descEs" /></label> <input type="text" name="comarca1.descEs"
									class="formulario_linea_input" size="30"
									id="comarca1_descEs_multi" />
							</div>

							<div class="col1">
								<label for="comarca1_descEu_multi" class="label"><spring:message
										code="descEu" /></label> <input type="text" name="comarca1.descEu"
									class="formulario_linea_input" size="30"
									id="comarca1_descEu_multi" />
							</div>
						</div>
					</fieldset>
					<fieldset class="alumnoFieldset">
						<legend>
							<spring:message code="comarca" />
						</legend>
						<div class="two-col">
							<div class="col1">
								<label for="comarca2_code_multi" class="label"><spring:message
										code="codigo" /></label> <input type="text" name="comarca2.code"
									class="formulario_linea_input" size="20"
									id="comarca2_code_multi" />
							</div>

							<div class="col1">
								<label for="comarca2_descEs_multi" class="label"><spring:message
										code="descEs" /></label> <input type="text" name="comarca2.descEs"
									class="formulario_linea_input" size="30"
									id="comarca2_descEs_multi" />
							</div>

							<div class="col1">
								<label for="comarca2_descEu_multi" class="label"><spring:message
										code="descEu" /></label> <input type="text" name="comarca2.descEu"
									class="formulario_linea_input" size="30"
									id="comarca2_descEu_multi" />
							</div>
						</div>
					</fieldset>
					<fieldset class="alumnoFieldset">
						<legend>
							<spring:message code="comarcaYprovincia" />
						</legend>
						<br /> <i><spring:message code="comarca" /></i> <br />
						<div class="two-col">
							<div class="col1">
								<label for="comarca3_code_multi" class="label"><spring:message
										code="codigo" /></label> <input type="text" name="comarca3.code"
									class="formulario_linea_input" size="20"
									id="comarca3_code_multi" />
							</div>

							<div class="col1">
								<label for="comarca3_descEs_multi" class="label"><spring:message
										code="descEs" /></label> <input type="text" name="comarca3.descEs"
									class="formulario_linea_input" size="30"
									id="comarca3_descEs_multi" />
							</div>

							<div class="col1">
								<label for="comarca3_descEu_multi" class="label"><spring:message
										code="descEu" /></label> <input type="text" name="comarca3.descEu"
									class="formulario_linea_input" size="30"
									id="comarca3_descEu_multi" />
							</div>
						</div>
						<br /> <br /> <br /> <i><spring:message code="provincia" /></i>
						<br />
						<div class="two-col">
							<div class="col1">
								<label for="comarca3_provincia_code_multi" class="label"><spring:message
										code="codigo" /></label> <input type="text"
									name="comarca3.provincia.code" class="formulario_linea_input"
									size="20" id="comarca3_provincia_code_multi" />
							</div>

							<div class="col1">
								<label for="comarca3_provincia_descEs_multi" class="label"><spring:message
										code="descEs" /></label> <input type="text"
									name="comarca3.provincia.descEs" class="formulario_linea_input"
									size="30" id="comarca3_provincia_descEs_multi" />
							</div>

							<div class="col1">
								<label for="comarca3_descEu_multi" class="label"><spring:message
										code="descEu" /></label> <input type="text"
									name="comarca3.provincia.descEu" class="formulario_linea_input"
									size="30" id="comarca3_provincia_descEu_multi" />
							</div>
						</div>
					</fieldset>
					<input type="submit"
						value="<spring:message code="patron.formulario.enviarMultientidad" />" />
				</fieldset>
			</form>
		</div>

		<div id="divSubidaFicheros">
			<fieldset class="alumnoFieldset">
				<legend>Subida de ficheros</legend>

				<form id="formSubidaArchivos"
					action='${pageContext.request.contextPath}patrones/form/subidaArchivos'
					method="post">

					<div id="feedback_fileupload_form_multiple"></div>
					<div class="formulario_columna_cnt">
						<div class="formulario_linea_izda_float">
							<label id="label_nombre" for="nombre">Nombre :</label> <input
								id="nombre" name="nombre" />
						</div>
						<div class="formulario_linea_izda_float">
							<label id="label_apellido1" for="apellido1">Apellido 1:</label> <input
								id="apellido1" name="apellido1" />
						</div>
						<div class="formulario_linea_izda_float">
							<label id="label_apellido2" for="apellido2">Apellido 2:</label> <input
								id="apellido2" name="apellido2" />
						</div>
						<div class="formulario_linea_izda_float">
							<label id="label_ejie" for="ejie">EJIE:</label> <input
								type="checkbox" id="ejie" name="ejie" value="1" />
						</div>
					</div>
					<div class="formulario_columna_cnt">&nbsp;</div>
					<div class="formulario_columna_cnt">
						<div class="formulario_linea_izda_float">Archivos adjuntos:
						</div>
					</div>
					<div class="formulario_columna_cnt">
						<div class="formulario_linea_izda_float">
							<div id="fileupload_file_form_padre">
								<div class="fileupload-buttonbar">
									<label for="file">Foto padre: </label> <input
										id="file_form_padre" type="file" name="fotoPadre">
								</div>
								<div class="fileupload-content">
									<table class="files"></table>
								</div>
							</div>

							<div id="fileupload_file_form_madre">
								<div class="fileupload-buttonbar">
									<label for="file">Foto madre: </label> <input
										id="file_form_madre" type="file" name="fotoMadre">
								</div>
								<div class="fileupload-content">
									<table class="files"></table>
								</div>
							</div>

						</div>
					</div>
					<div class="formulario_columna_cnt">
						<div class="formulario_linea_izda_float fileupload-buttonbar">
							<button id="sendButtonArchivos" type="submit" class="start">Enviar
								formulario</button>
						</div>
					</div>
				</form>
			</fieldset>
		</div>
	</div>
</section>