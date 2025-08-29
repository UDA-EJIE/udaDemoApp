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

<!-- URL a usar en formularios -->
<spring:url value="autocomplete/remote" var="remoto"/>
<spring:url value="autocomplete/remoteEnlazadoProvincia" var="remotoEnlazado"/>

<section class="row">
	<div class="col-12">

		<h2 class="title mb-3">
			<spring:message code="selectAutocomplete.title" />
		</h2>
		
		<div class="row mt-4">
			<div class="col-sm-6">
				<h3 class="col-sm-12">Selectbox local</h3>
				<div class="form-groupMaterial col-sm-12">
					<select id="selectboxLocal" name="selectboxLocal"></select> 					 
					<label for="selectboxLocal">Lenguaje</label>
					<p class="mt-2">[asp, c, c++, coldfusion, groovy, haskell, java, javascript, perl, php, python, ruby, scala]</p>
				</div>
			</div>
			
			<div class="col-sm-6">
				<form:form id="selectboxRemoto_form" modelAttribute="departamentoProvincia" action="${remoto}" method="GET">
					<h3 class="col-sm-12">Selectbox remoto</h3>
					<div class="form-groupMaterial col-sm-12">
						<form:select id="selectboxRemoto" path="code">
							<form:option value="">---</form:option>
						</form:select> 
						<label for="selectboxRemoto">Departamento-Provincia</label>
						<p class="mt-2">[Castellano: " de " // Euskara: arab, gipuz, bilb]</p>
					</div>
				</form:form>
			</div>
		</div>
		
		<div class="row mt-4">
			<div class="col-sm-6">
				<form:form id="selectRemoto_form" modelAttribute="provincia" action="${remotoEnlazado}" method="GET">
					<h3 class="col-sm-12">Selectbox remoto (con evento en el select)</h3>
					<div class="form-groupMaterial col-sm-12">
						<form:select id="selectRemoto" path="code">
							<form:option value="">---</form:option>
						</form:select> 
						<label for="selectRemoto">Select remoto</label>
						<p class="mt-2">[Alava, Gipuzcoa, Vizcaya]</p>
					</div>
				</form:form>
			</div>
			<div class="col-sm-6">
				<form:form id="autocompleteGet_form" modelAttribute="departamentoProvincia" action="${remoto}" method="GET">
					<h3 class="col-sm-12">Autocomplete remoto (el selectbox remoto cambia sus valores)</h3>
					<div class="form-groupMaterial col-sm-12">
						<form:select id="autocompleteGet" path="code">
							<form:option value="">---</form:option>
						</form:select> 
						<label for="autocompleteGet">Departamento-Provincia</label>
						<p class="mt-2">[Castellano: " de " // Euskara: arab, gipuz, bilb]</p>
					</div>
				</form:form>
			</div>
		</div>
		
		<div class="row mt-4">
			<div class="col-sm-4">
				<h3 class="col-sm-12">Autocomplete local con función select</h3>
				<div class="form-groupMaterial col-sm-12">
					<select id="autocomplete" name="autocomplete"> </select> 
					<label for="autocomplete">Lenguaje</label>
					<p class="mt-2">[asp, c, c++, coldfusion, groovy, haskell, java, javascript, perl, php, python, ruby, scala]</p>
				</div>
			</div>
			
			<div class="col-sm-4">
				<h3 class="col-sm-12">Autocomplete local sin acentos</h3>
				<div class="form-groupMaterial col-sm-12">
					<select id="autocompleteNotAccent" name="autocompleteNotAccent" > </select> 
					<label for="autocompleteNotAccent">Lenguaje</label>
					<p class="mt-2">[asp, c, c++, coldfusion, groovy, haskell, java, javascript, perl, php, python, ruby, scala]</p>
				</div>
			</div>
			
			<div class="col-sm-4">
				<form:form id="patron_form" modelAttribute="departamentoProvincia" action="${remoto}" method="GET">
					<h3 class="col-sm-12">Autocomplete remoto sin Cachear</h3>
					<div class="form-groupMaterial col-sm-12">
						<form:select id="patron" path="code">
							<form:option value="">---</form:option>
						</form:select> 
						<label for="patron">Departamento-Provincia</label>
						<p class="mt-2">[Castellano: " de " // Euskara: arab, gipuz, bilb]</p>
					</div>
				</form:form>
			</div>	
		</div>
		
				<div class="row mt-4">
			<div class="col-sm-4">
				<h3 class="col-sm-12">Autocomplete remoto con cacheUrl a True</h3>
				<div class="form-groupMaterial col-sm-12">
					<select id="autocompleteCacheUrl" name="autocomplete"> </select> 
					<label for="autocomplete">Departamento-Provincia</label>
				</div>
			</div>
			
		</div>
	</div>
</section>