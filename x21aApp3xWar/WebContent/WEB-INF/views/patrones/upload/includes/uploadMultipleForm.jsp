 <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>


<div id="fileupload_form_multiple">
	<form action="../upload/formSimple" id="uploadFormMultiple" enctype="multipart/form-data" method="POST">
		<fieldset style="border:1px solid #DADADA;" id="fieldset_formulario">
			<div id="feedback_fileupload_form_multiple"></div>
			<div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<label id="label_nombre"  for="nombre" >Nombre :</label>
						<input id="nombre" name="nombre" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label id="label_apellido1"  for="apellido1" >Apellido 1:</label>
						<input id="apellido1" name="apellido1" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label id="label_apellido2"  for="apellido2" >Apellido 2:</label>
						<input id="apellido2" name="apellido2" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label id="label_ejie"  for="ejie" >EJIE:</label>
						<input type="checkbox" id="ejie" name="ejie" value="1" />
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					Archivos adjuntos:
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 ">
			        <div id="fileupload_file_form_padre" class="rup-upload">
				        <span class="btn btn-success fileinput-button">
						      <i class="glyphicon glyphicon-plus"></i>
						      <span>Seleccione foto padre...</span>
						      <input id="file_form_padre" type="file" name="fotoPadre"  />
						</span>
						<div class="fileupload-content">
					        <div class="files list-group"></div>
					        
					    </div>
				    </div>
			    </div>
			  </div>
			  <div class="row">
				<div class="col-md-12 ">
			        <div id="fileupload_file_form_madre" class="fileupload_file_form_madre rup-upload">
				        <span class="btn btn-success fileinput-button">
						      <i class="glyphicon glyphicon-plus"></i>
						      <span>Seleccione foto madre...</span>
						      <input id="file_form_madre" type="file" name="fotoMadre"  />
						</span>
						<div class="fileupload-content">
					        <div class="files list-group"></div>
					        
					    </div>
				    </div>
			    </div>
			  </div>
			  
			  <div class="row">
			 	 <div class="col-md-12"  >
					<button id="sendButtonMultiple" type="submit">Enviar formulario</button>
				</div>
			</div>
		</fieldset>
	</form>
</div>