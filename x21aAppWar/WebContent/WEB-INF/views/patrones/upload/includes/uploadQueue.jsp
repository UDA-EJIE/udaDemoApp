
<%@include file="/WEB-INF/includeTemplate.inc"%>


<div id="fileupload_only">
	<!-- <form action="../upload" id="usuarioForm" enctype="multipart/form-data" method="POST"> -->
	<form:form action="../upload" id="usuarioForm" enctype="multipart/form-data" method="POST" modelAttribute="collection">
		<fieldset id="fieldset_formulario" class="rup-upload">
			<div class="formulario_columna_cnt">
				<div class="formulario_linea_izda_float">
			        <div class="fileupload-buttonbar">
				        <div>
<!-- 				            <label for="file">Ficheros: </label> -->
<!-- 				            <input id="file_only" type="file" name="files[]" multiple="multiple"> -->
							<span class="btn btn-success fileinput-button">
							      <i class="fa fa-plus" aria-hidden="true"></i>
							      <span><spring:message code="upload.button.addFiles"/></span>
							      <!-- <input id="file_only" type="file" name="files[]" multiple="multiple" /> -->
							      <form:input path="files" type="file" multiple="multiple" id="file_only" />
							</span>
				    				            
				            <button type="submit" class="btn btn-primary start">
			                    <i class="fa fa-upload" aria-hidden="true"></i>
			                    <span>Subir todos</span>
			                </button>
			                <button type="reset" class="btn btn-secondary cancel">
			                    <i class="fa fa-times-circle" aria-hidden="true"></i>
			                    <span>Cancelar todos</span>
			                </button>
			                <button type="button" class="btn btn-secondary delete">
			                    <i class="fa fa-trash" aria-hidden="true"></i>
			                    <span>Eliminar todos</span>
			                </button>
				            
				            
						</div>
			        </div>
			      
				    <div class="fileupload-content">
				        <div class="files list-group"></div>
				        <div id="fileupload-progressbar" class="fileupload-progressbar"></div>
				    </div>
			    </div>
			  </div>
		</fieldset>
	</form:form>
</div>

