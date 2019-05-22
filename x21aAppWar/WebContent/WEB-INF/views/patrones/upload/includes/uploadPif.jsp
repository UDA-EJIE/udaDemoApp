
<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="fileupload_pif_form">
	<form action="../upload/pifForm" id="uploadPifForm" enctype="multipart/form-data" method="POST">
		<fieldset style="border:1px solid #DADADA;" id="fieldset_formulario">
			<div id="feedback_fileupload_form"></div>
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
			        <div class="rup-upload">
				        <span class="btn btn-success fileinput-button">
						      <i class="mdi mdi-plus"></i>
						      <span><spring:message code="upload.button.addFiles"/></span>
						      <input id="file_pif_form" type="file" name="file" multiple="multiple">
						</span>
				      
					    <div class="fileupload-content">
					        <div class="files list-group"></div>
					        <div id="fileupload-progressbar" class="fileupload-progressbar"></div>
					    </div>
				    </div>
			    </div>
			  </div>
			  <div class="row">
			 	 <div class="col-md-12"  >
					<button id="sendPifButton" type="submit">Enviar formulario</button>
				</div>
			</div>
		</fieldset>
	</form>
</div>