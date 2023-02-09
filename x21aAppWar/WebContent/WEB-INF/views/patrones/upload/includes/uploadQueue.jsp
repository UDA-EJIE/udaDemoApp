
<%@include file="/WEB-INF/includeTemplate.inc"%>


<div id="fileupload_only">
	<spring:url value="../upload/add" var="url"/>
	<form:form action="${url}" id="usuarioForm" enctype="multipart/form-data" method="POST" modelAttribute="collection">
		<fieldset id="fieldset_formulario" class="rup-upload">
			<div class="formulario_columna_cnt">
				<div class="formulario_linea_izda_float">
			        <div class="fileupload-buttonbar">
				        <div>
							<span class="btn btn-success fileinput-button">
							      <i class="mdi mdi-plus" aria-hidden="true"></i>
							      <span><spring:message code="upload.button.addFiles"/></span>
							      <form:input path="files" type="file" multiple="multiple" id="file_only" />
							</span>
				    				            
				            <button type="submit" class="btn btn-primary start">
			                    <i class="mdi mdi-upload" aria-hidden="true"></i>
			                    <span>Subir todos</span>
			                </button>
			                <button type="reset" class="btn btn-secondary cancel">
			                    <i class="mdi mdi-close-circle-outline" aria-hidden="true"></i>
			                    <span>Cancelar todos</span>
			                </button>
			                <button type="button" class="btn btn-secondary delete">
			                    <i class="mdi mdi-delete" aria-hidden="true"></i>
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

