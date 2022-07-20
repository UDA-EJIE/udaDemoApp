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

<%@include file="/WEB-INF/includeTemplate.inc"%>

<section class="row">
	<div class="col-12">
		<h2 class="title mb-3">
			Ejemplo <spring:message code="patron.formulario.titulo" />
		</h2>
			<div id="divModelView" class="m-3">


			<form:form id="formModelView" action='${pageContext.request.contextPath}/patrones/form/ejemploModelView' modelAttribute="alumno">

				<fieldset class="alumnoFieldset">
					<legend>
						<spring:message code="datosPersonales" />
					</legend>
					
					<div class="form-row">
						<div class="form-groupMaterial col-sm">
							<form:input path="nombre" size="20" id="nombreModel"/>
							<label for="nombreModel" class="label"><spring:message code="nombre" /></label>
						</div>

						<div class="form-groupMaterial col-sm">
							<form:input path="apellido1" size="30" id="apellido1Model"/>
							<label for="apellido1Model" class="label"><spring:message code="apellido1" /></label>
						</div>

						<div class="form-groupMaterial col-sm">
							<form:input path="apellido2" size="30" id="apellido2Model"/>
							<label for="apellido2Model" class="label"><spring:message code="apellido2" /></label>
						</div>
						
						<div class="form-groupMaterial col-sm">
							<form:input path="id" size="30" id="idModel"/>
						<label for="idModel" class="label">Id</label>
						</div>
						<div class="form-groupMaterial col-sm">
						<spring:url value="../../table/${usuarioDATA.id}" var="urlDetalle" htmlEscape="true"/>
						
						<a id="urlLink" href="${urlDetalle}"><span class="datosResumen " >Ir al Detalle -> <c:out value="${alumno.nombre}"/></span></a>
						</div>
					</div>

					<div class="form-row">
						<div class="form-groupMaterial col-sm">
							<form:input path="sexo" id="sexoModel"/>
							<label for="sexoModel" class="label"><spring:message code="sexo" /></label>
						</div>
						
						<div class="form-groupMaterial col-sm">
							<form:input path="dni" id="dniModel"/>
							<label for="dniModel" class="label"><spring:message code="dni" /></label>
						</div>

						<div class="form-groupMaterial col-sm">
							<form:input path="telefono" id="telefonoModel"/>
							<label for="telefonoModel" class="label"><spring:message code="telefono" /></label>
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
							<label for="provinciaModel" class="label"><spring:message code="provincia" /></label>
						</div>
					</div>

				</fieldset>

				<button class="btn-material btn-material-primary-high-emphasis mt-2" id="buttonReturn" type="button">volver</button>
				
				<spring:url value="/patrones/form" var="form" htmlEscape="true"/>
                <a class="d-none" href="${form}" id="linkReturn">
                <spring:message code="form" />
                </a>

			</form:form>
		</div>

		
	</div>
</section>