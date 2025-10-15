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

		<h2>Accordion</h2>
		<hr>

		<div id="accordionExample1" class="rup_accordion">

			<h3>
				<a><spring:message code="seccion1" /></a>
			</h3>
			<div class="section">
				<img alt="La primera sección"
					src="${staticsUrl}/x21a/images/primera_seccion.PNG">
			</div>
			<h3>
				<a><spring:message code="seccion2" /></a>
			</h3>
			<div class="section2">
				<img alt="La segunda sección"
					src="${staticsUrl}/x21a/images/segunda_seccion.PNG">
			</div>
			<h3>
				<a><spring:message code="seccion3" /></a>
			</h3>
			<div class="section">
				<img alt="La tercera sección"
					src="${staticsUrl}/x21a/images/tercera_seccion.PNG">
			</div>
			<h3>
				<a><spring:message code="seccion4" /></a>
			</h3>
			<div class="section2">
				<img alt="La cuarta sección"
					src="${staticsUrl}/x21a/images/cuarta_seccion.PNG">
			</div>

		</div>

		<br />
		<div class="separator"></div>
		<div class="separator"></div>
		<br />

		<div id="accordionExample2" class="rup_accordion">

			<h3>
				<a><spring:message code="secAuto" /></a>
			</h3>
			<div class="sectionAuto">
				<h4 class="sectionh4">Autocomplete local</h4>
				<div class="row">
					<div class="form-groupMaterial col-sm">
				    	<select id="autocomplete"></select>
						<label for="autocomplete">Lenguaje</label>
						<p>[asp, c, c++, coldfusion, groovy, haskell, java, javascript, perl, php, python, ruby, scala]</p>
				    </div>
				</div>
				
				<h4>Autocomplete remoto</h4>
				<div class="row">   
				    <div class="form-groupMaterial col-sm">
				    	<select id="patron"></select>
				    	<label for="patron">Departamento-Provincia</label>
				    	<p>[Castellano: " de " // Euskara: arab, gipuz, bilb]</p>
				    </div>
				</div>
			</div>
			<h3>
				<a><spring:message code="secMaint" /></a>
			</h3>
			<div class="sectionMaint">
				<h4 class="sectionh4">Mantenimiento Agrupado</h4>
				<div id="maintGroup"></div>
			</div>
			<h3>
				<a><spring:message code="secTab" /></a>
			</h3>
			<div class="sectionTab">
				<h4 class="sectionh4">Mantenimientos en Tabs</h4>
				<div id="maintTab"></div>
			</div>
			<h3>
				<a><spring:message code="secUpload" /></a>
			</h3>
			<div class="sectionUpload">
				<h4 class="sectionh4">
					<spring:message code="uploadFormMultiple" />
				</h4>
				<div id="fileupload_form_multiple">
					<form:form action="../upload/formSimple" modelAttribute="alumno"
						enctype="multipart/form-data" method="POST" id="usuarioFormSimple">
						<fieldset style="border: 1px solid #DADADA;"
							id="fieldset_formulario">
							<div id="feedback_fileupload_form_multiple"></div>
							<div class="formulario_columna_cnt">
								<div class="form-groupMaterial col-sm">
									<form:input path="nombre" id="nombre" />
									<label id="label_nombre" for="nombre">Nombre</label>
								</div>
								<div class="form-groupMaterial col-sm">
									<form:input path="apellido1" id="apellido1" />
									<label id="label_apellido1" for="apellido1">Apellido 1</label>
								</div>
								<div class="form-groupMaterial col-sm">
									<form:input path="apellido2" id="apellido2" />
									<label id="label_apellido2" for="apellido2">Apellido 2</label>
								</div>
								<div class="checkbox-material col-sm">
									<form:checkbox path="ejie" id="ejie" value="1"/>
									<label id="label_ejie" for="ejie">EJIE</label>
								</div>
							</div>
							<div class="formulario_columna_cnt">&nbsp;</div>
							<div class="formulario_columna_cnt">
								<p>Archivos adjuntos:</p>
							</div>
							<div class="formulario_columna_cnt">
								<div class="formulario_linea_izda_float">
									<div id="fileupload_file_form_padre">
										<div class="fileupload-buttonbar">
											<div class="row">
												<div class="form-groupMaterial col-sm">
											    	<input id="file_form_padre" type="file" name="fotoPadre" />
													<label for="file">Foto padre</label>
											    </div>
											</div>
										</div>
										<div class="fileupload-content">
											<table class="files"></table>
										</div>
									</div>

									<div id="fileupload_file_form_madre">
										<div class="fileupload-buttonbar">
											<div class="row">
												<div class="form-groupMaterial col-sm">
											    	<input id="file_form_madre" type="file" name="fotoMadre" />
													<label for="file">Foto madre</label>
											    </div>
											</div>
										</div>
										<div class="fileupload-content">
											<table class="files"></table>
										</div>
									</div>

								</div>
							</div>
							<div class="formulario_columna_cnt">
								<div class="formulario_linea_izda_float fileupload-buttonbar">
									<button id="sendButtonMultiple" type="submit" class="start btn-material btn-material-primary-high-emphasis">
										Enviar formulario
									</button>
								</div>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>