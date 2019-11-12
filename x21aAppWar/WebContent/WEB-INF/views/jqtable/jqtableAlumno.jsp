	<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2>alumno</h2>
	<div id="error" style="display:none"></div>
	<div id="alumno" >
		<div id=toolbar></div>
		<div id="contenido" style="margin-top:0.5em;margin-bottom:0.5em;width:600px;">
			<form:form modelAttribute="alumno" id="searchForm" >
				<div  class="formulario_legend" id="titleSearch_alumno"><spring:message code="searchCriteria" /></div>
				<fieldset style="border:1px solid #DADADA;" id="FIELDSET_SEARCH_alumno">
					<div class="form-row">
						<div class="form-group col-sm">
							<label for="usuario_search" class="formulario_linea_label"><spring:message code="usuario" /></label>
							<form:input path="usuario" class="formulario_linea_input form-control" id="usuario_search" />
						</div>
						<div class="form-group col-sm">
							<label for="nombre_search" class="formulario_linea_label"><spring:message code="nombre" /></label>
							<form:input path="nombre" class="formulario_linea_input form-control" id="nombre_search" />
						</div>
						<div class="form-group col-sm">
							<label for="apellido1_search" class="formulario_linea_label"><spring:message code="apellido1" /></label>
							<form:input path="apellido1" class="formulario_linea_input form-control" id="apellido1_search" />
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-sm">
							<label for="apellido2_search" class="formulario_linea_label"><spring:message code="apellido2" /></label>
							<form:input path="apellido2" class="formulario_linea_input form-control" id="apellido2_search" />
						</div>
						<div class="form-group col-sm">
							<label for="dni_search" class="formulario_linea_label"><spring:message code="dni" /></label>
							<form:input path="dni" class="formulario_linea_input form-control" id="dni_search" />
						</div>
					</div>
				</fieldset>
			</form:form>
		</div>

		<div id="RUP_GRID_alumno">
			<!-- Tabla -->
			<table id="GRID_alumno" cellpadding="0" cellspacing="0"></table>
		<!-- Barra de paginación -->
			<div id="pager" style="text-align:center;"></div>
		</div>
		
		
		<div id="detalleAlumno" style="display:none" >
			<form:form id="detalleAlumnoForm" modelAttribute="alumno" >
				<fieldset class="alumnoFieldset">
					<legend><spring:message code="datosPersonales" /></legend>
					<input name="id" id="id"  style="display:none;"/>
					
					<div class="two-col" style="overflow: visible;">
						<div style="width: 70%;">
						    <div class="col1">
						       <label for="nombre" class="label"><spring:message code="nombre" /></label>
								<input type="text" name="nombre" class="formulario_linea_input" size="20" id="nombre" />
						    </div>
						
						    <div class="col1">
						        <label for="apellido1" class="label"><spring:message code="apellido1" /></label>
								<input type="text" name="apellido1" class="formulario_linea_input" size="30" id="apellido1" />
						    </div>
						    
						     <div class="col1">
						        <label for="apellido2" class="label"><spring:message code="apellido2" /></label>
								<input type="text" name="apellido2" class="formulario_linea_input" size="30" id="apellido2" />
						    </div>
						</div>
						<div style="width: 30%;float:right; overflow: visible;">
							<img alt="" src="${staticsUrl}/x21a/images/no_picture.gif" width="96px" height="96px" name="imagen"  id="imagen" />
							
							<div class="col1">
						        <label for="imagenAlumno" class="label"><spring:message code="modificarImagen" /></label>
								<input type="file" name="imagenAlumno" class="formulario_linea_input" id="imagenAlumno" />
						    </div>
						    <input style="display:none;" id="nombreImagen" name="nombreImagen" />
						</div>
					</div>
					
					<div class="two-col" style="width: 70%; padding-top: 2em">
					    <div class="col1">
					       <label for="sexo" class="label"><spring:message code="sexo" /></label>
							<input type="text" name="sexo" class="formulario_linea_input" id="sexo" />
					    </div>
					
					    <div class="col1">
					        <label for="fechaNacimiento" class="label"><spring:message code="fechaNacimiento" /></label>
							<input type="text" name="fechaNacimiento" class="formulario_linea_input" id="fechaNacimiento" />
					    </div>
					    
					     <div class="col1">
					        <label for="telefono" class="label"><spring:message code="telefono" /></label>
							<input type="text" name="telefono" class="formulario_linea_input" id="telefono" />
					    </div>
					</div>
					
					<div class="two-col" style="width: 70%; padding-top: 2em">
					    <div class="col1">
					       <label for="dni" class="label"><spring:message code="dni" /></label>
							<input type="text" name="dni" class="formulario_linea_input" id="dni" />
					    </div>
					    <div class="col1">
					       <label for="importeMatricula" class="label"><spring:message code="importeMatricula" /></label>
							<input type="text" name="importeMatricula" class="formulario_linea_input" id="importeMatricula" />
					    </div>
					
					</div>
				</fieldset>
				
				<fieldset class="alumnoFieldset">
					<legend><spring:message code="datosCuentaUsuario" /></legend>
					
					<div class="two-col">
					    <div class="col1">
					       <label for="usuario" class="label"><spring:message code="usuario" /></label>
							<input type="text" name="usuario" class="formulario_linea_input" id="usuario" />
					    </div>
					    <div class="col1" id="divOldPassword">
					       <label for="oldPassword" class="label"><spring:message code="contrasenaActual" /></label>
							<input type="password" name="oldPassword" class="formulario_linea_input" id="oldPassword" />
					    </div>
					    <div class="col1">
					       <label for="password" class="label"><spring:message code="contrasena" /></label>
							<input type="password" name="password" class="formulario_linea_input" id="password" />
							<label for="password_confirm" class="label"><spring:message code="confirmarContrasena" /></label>
							<input type="password" name="password_confirm" class="formulario_linea_input" id="password_confirm" />
					    </div>
					      <div class="col1">
					       <label for="email" class="label"><spring:message code="email" /></label>
							<input type="text" name="email" class="formulario_linea_input" id="email" />
							<label for="email_confirm" class="label"><spring:message code="confirmarEmail" /></label>
							<input type="text" name="email_confirm" class="formulario_linea_input" id="email_confirm" />
					    </div>
					</div>
					
				</fieldset>
				
				<fieldset class="alumnoFieldset">
					<legend><spring:message code="datosDomicilio" /></legend>
					
					<div class="two-col">
					    <div class="col1">
					       <label for="pais" class="label"><spring:message code="pais" /></label>
					       <form:select path="pais.id" class="formulario_linea_input" id="pais" >
						       <form:options items="${paises}" itemLabel="dsO" itemValue="id"/>
					       </form:select>
					    </div>
					    <div class="col1" id="divAutonomia">
					       <label for="autonomia" class="label"><spring:message code="autonomia" /></label>
					       <form:select path="autonomia.id" class="formulario_linea_input" id="autonomia" >
						       <form:options items="${autonomias}" itemLabel="dsO" itemValue="id"/>
					       </form:select>
					    </div>
					     
					</div>
					<div class="two-col" id="divDireccionExtranjera">
						<div class="col1">
							<label for="direccion" class="label"><spring:message code="direccion" /></label>
							<textarea name="direccion" id="direccion" rows="4" cols="60">
							</textarea>
						
						</div>
					</div>
					<div class="two-col" id="divDireccionPais">
						<div class="col1">
					       <label for="provincia" class="label"><spring:message code="provincia" /></label>
							<input type="text" name="provincia.id" class="formulario_linea_input" id="provincia" />
					    </div>
						<div class="col1">
					       <label for="municipio" class="label"><spring:message code="municipio" /></label>
							<input type="text" name="municipio.id" class="formulario_linea_input" size="30" id="municipio" />
					    </div>
					     <div class="col1">
					       <label for="calle" class="label"><spring:message code="calle" /></label>
							<input type="text" name="calle.id" class="formulario_linea_input" size="50" id="calle" />
					    </div>
					    
					    <div class="col1">
					       <label for="campo" class="label"><spring:message code="calle" /></label>
							<input type="text" name="campo.valor" class="formulario_linea_input" size="50" id="campo" />
					    </div>
					</div>
					
				</fieldset>
			</form:form>
		
		</div>
		
	</div>
	