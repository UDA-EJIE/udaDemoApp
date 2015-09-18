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
<h2>Upload</h2>

<div id="uploadTabs"></div>

<div id="fileupload_only">
	<form action="../upload" id="usuarioForm" enctype="multipart/form-data" method="POST">
		<fieldset style="border:1px solid #DADADA;" id="fieldset_formulario">
			<div class="formulario_columna_cnt">
				<div class="formulario_linea_izda_float">
			        <div class="fileupload-buttonbar">
				        <div>
				            <label for="file">Ficheros: </label>
				            <input id="file_only" type="file" name="files[]" multiple="multiple">
				            <button type="submit" class="start">Subir todos</button>
				            <button type="reset" class="cancel">Cancelar todos</button>
				            <button type="button" class="delete">Eliminar todos</button>
						</div>
			        </div>
			      
				    <div class="fileupload-content">
				        <table class="files"></table>
				        <div id="fileupload-progressbar" class="fileupload-progressbar"></div>
				    </div>
			    </div>
			  </div>
		</fieldset>
	</form>
</div>

<div id="fileupload_form">
	<form action="../upload/form" id="usuarioForm" enctype="multipart/form-data" method="POST">
		<fieldset style="border:1px solid #DADADA;" id="fieldset_formulario">
			<div id="feedback_fileupload_form"></div>
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
			        <div class="fileupload-buttonbar">
			        <div>
			            <label for="file">Fichero: </label>
			            <input id="file_form" type="file" name="file">
					</div>
			        </div>
			      
				    <div class="fileupload-content">
				        <table class="files"></table>
				        <div id="fileupload-progressbar" class="fileupload-progressbar"></div>
				    </div>
			    </div>
			  </div>
			  <div class="formulario_columna_cnt">
			 	 <div class="formulario_linea_izda_float fileupload-buttonbar"  >
					<button id="sendButton" type="submit" class="start">Enviar formulario</button>
				</div>
			</div>
		</fieldset>
	</form>
</div>

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
