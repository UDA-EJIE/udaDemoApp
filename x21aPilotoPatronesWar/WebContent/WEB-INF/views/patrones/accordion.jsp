<%--  
 -- Copyright 2012 E.J.I.E., S.A.
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
 
 <%@include file="/WEB-INF/includeTemplate.inc"%>
 
<h2>Accordion</h2>

<div id="accordionExample1" class="rup_accordion">

	<h1><a><spring:message  code="seccion1" /></a></h1>
	<div class="section">
		<img alt="La primera sección" src="${staticsUrl}/x21a/images/primera_seccion.PNG">
	</div>
	<h1><a><spring:message  code="seccion2" /></a></h1>
	<div class="section2">
		<img alt="La segunda sección" src="${staticsUrl}/x21a/images/segunda_seccion.PNG">
	</div>
	<h1><a><spring:message  code="seccion3" /></a></h1>
	<div class="section">
		<img alt="La tercera sección" src="${staticsUrl}/x21a/images/tercera_seccion.PNG">
	</div>
	<h1><a><spring:message  code="seccion4" /></a></h1>
	<div class="section2">
		<img alt="La cuarta sección" src="${staticsUrl}/x21a/images/cuarta_seccion.PNG">
	</div>
	
</div>

<br/>
<div class="separator"></div>
<div class="separator"></div>
<br/>

<div id="accordionExample2" class="rup_accordion">

	<h1><a><spring:message  code="secAuto" /></a></h1>
	<div class="sectionAuto">
		<h3 class="sectionh2">Autocomplete local</h3>
		<label for="autocomplete">Lenguaje:</label><input id="autocomplete" name="autocomplete" />
		[asp, c, c++, coldfusion, groovy, haskell, java, javascript, perl, php, python, ruby, scala]
		<br/>
		
		<h3>Autocomplete remoto</h3>
		<label for="patron">Departamento-Provincia:</label><input id="patron" name="patron" /> [Castellano: " de " // Euskara: arab, gipuz, bilb]
		<br/>
		
	</div>
	<h1><a><spring:message  code="secMaint" /></a></h1>
	<div class="sectionMaint">
		<h2 class="sectionh2">Mantenimiento Agrupado</h2>	
		<div id="maintGroup"></div>
	</div>
	<h1><a><spring:message  code="secTab" /></a></h1>
	<div class="sectionTab">
		<h2 class="sectionh2">Mantenimientos en Tabs</h2>	
		<div id="maintTab"></div>
	</div>
	<h1><a><spring:message  code="secUpload" /></a></h1>
	<div class="sectionUpload">
		<h2 class="sectionh2"><spring:message code="uploadFormMultiple" /></h2>
		<div id="fileupload_form_multiple">
			<form action="../upload/formSimple" id="usuarioFormSimple" enctype="multipart/form-data" method="POST">
				<fieldset style="border:1px solid #DADADA;" id="fieldset_formulario">
					<div id="feedback_fileupload_form_multiple"></div>
					<div class="formulario_columna_cnt">
						<div class="formulario_linea_izda_float">
							<label id="label_nombre"  for="nombre" >Nombre :</label>
							<input id="nombre" name="nombre" />
						</div>
						<div class="formulario_linea_izda_float">
							<label id="label_apellido1"  for="apellido1" >Apellido 1:</label>
							<input id="apellido1" name="apellido1" />
						</div>
						<div class="formulario_linea_izda_float">
							<label id="label_apellido2"  for="apellido2" >Apellido 2:</label>
							<input id="apellido2" name="apellido2" />
						</div>
						<div class="formulario_linea_izda_float">
							<label id="label_ejie"  for="ejie" >EJIE:</label>
							<input type="checkbox" id="ejie" name="ejie" value="1" />
						</div>
					</div>
					<div class="formulario_columna_cnt">
					&nbsp;
					</div>
					<div class="formulario_columna_cnt">
						<div class="formulario_linea_izda_float">
							Archivos adjuntos:
						</div>
					</div>
					<div class="formulario_columna_cnt">
						<div class="formulario_linea_izda_float">
					        <div id="fileupload_file_form_padre" >
						    	<div class="fileupload-buttonbar">
						        	<label for="file">Foto padre: </label>
						        	<input id="file_form_padre" type="file" name="fotoPadre">
								</div>
								<div class="fileupload-content">
							        <table class="files"></table>
							    </div>
					        </div>
					        
					        <div id="fileupload_file_form_madre">
					        	<div class="fileupload-buttonbar">
						            <label for="file">Foto madre: </label>
						            <input id="file_form_madre" type="file" name="fotoMadre">
								</div>
								<div class="fileupload-content">
							        <table class="files"></table>
							    </div>
					        </div>
						   
					    </div>
					</div>
				  	<div class="formulario_columna_cnt">
				 		<div class="formulario_linea_izda_float fileupload-buttonbar"  >
							<button id="sendButtonMultiple" type="submit" class="start">Enviar formulario</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div> 