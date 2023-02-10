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
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"%>
<%@taglib prefix="form" uri="/WEB-INF/tld/x38-form.tld"%>

<!-- URL a usar en formularios -->
<spring:url value="comboEnlazadoSimple/provinciaComarcaLocalidadDTO" var="remoto"/>
<spring:url value="comboEnlazadoSimple/comarcaLocalidadDTO" var="remotoAgrupado"/>

<h2 class="title mb-3">Combo Enlazado (simple)</h2>

<div class="container-fluid">
	<div class="form-row">
		<fieldset id="local" class="col-sm mr-sm-5">
			<legend>Local</legend>
			<div class="form-groupMaterial">
				<select id="comboAbuelo"></select>
				<label for="comboAbuelo">Provincia</label>
			</div>
			
			<div class="form-groupMaterial">
				<select id="comboPadre"></select>
				<label for="comboPadre">Comarca</label>
			</div>
			
			<div class="form-groupMaterial">
				<select id="comboHijo"></select>
				<label for="comboHijo">Localidad</label>
			</div>
		</fieldset>
		
		<fieldset id="remote" class="col-sm mr-sm-5">
			<legend>Remoto</legend>
			<form:form id="comboRemoto_form" modelAttribute="provinciaComarcaLocalidadDTO" action="${remoto}" method="GET">
				<div class="form-groupMaterial">
					<form:select id="comboAbueloRemoto" path="codeProvincia" />
					<label for="comboAbueloRemoto">Provincia</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="comboPadreRemoto" path="codeComarca" />
					<label for="comboPadreRemoto">Comarca</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="comboHijoRemoto" path="codeLocalidad" />
					<label for="comboHijoRemoto">Localidad</label>
				</div>
			</form:form>
		</fieldset>
		
		<!--
		<fieldset id="mixto" class="col-sm">
			<legend>Mixto I</legend>
			
			<div class="form-groupMaterial">
				<label for="mixto_comboAbueloRemoto">Provincia (remoto)</label>
				<select id="mixto_comboAbueloRemoto" name="provincia"><option>&nbsp;</option></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="mixto_comboPadre">Comarca (local)</label>
				<select id="mixto_comboPadre" name="comarca"><option>&nbsp;</option></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="mixto_comboHijoRemoto">Localidad (remoto)</label>
				<select id="mixto_comboHijoRemoto"><option>&nbsp;</option></select>
			</div>
		</fieldset>
		-->
	</div>
</div>

<div class="container-fluid mt-4">
	<div class="form-row">
		<!--
		<div id="mixto2" class="col-sm">
			<fieldset class="combo_fieldset mr-sm-3">
				<legend class="combo_legend">Mixto II</legend>
				
				<div class="form-groupMaterial">
					<label for="mixto2_comboAbuelo">Provincia (local)</label>
					<select id="mixto2_comboAbuelo" name="provincia"><option>&nbsp;</option></select>
				</div>
				
				<div class="form-groupMaterial">
					<label for="mixto2_comboPadreRemoto">Comarca (remoto)</label>
					<select id="mixto2_comboPadreRemoto"><option>&nbsp;</option></select>
				</div>
				
				<div class="form-groupMaterial">
					<label for="mixto2_comboHijo">Localidad (local)</label>
					<select id="mixto2_comboHijo"><option>&nbsp;</option></select>
				</div>
			</fieldset>
		</div>
		-->
		
		<div id="remoteGroup" class="col-sm">
			<fieldset>
				<legend>Remoto agrupado</legend>
				<form:form id="comboRemotoAgrupado_form" modelAttribute="comarcaLocalidadDTO" action="${remotoAgrupado}" method="GET">
			<!--	<div class="form-groupMaterial">	-->
			<!-- 		<label for="mixto2_comboAbuelo">Provincia (local)</label>	-->
			<!-- 		<select id="mixto2_comboAbuelo" name="provincia"><option>&nbsp;</option></select>	-->
			<!-- 	</div>	 -->
					<div class="form-groupMaterial">
						<form:select id="remoteGroup_comboPadre" path="codeProvincia" />
						<label for="remoteGroup_comboPadre">Provincia</label>
					</div>
					
					<div class="form-groupMaterial">
						<form:select id="remoteGroup_comboHijo" path="codeComarcaLocalidad" />
						<label for="remoteGroup_comboHijo">Comarca y Localidades</label>
					</div>
				</form:form>
			</fieldset>
		</div>
	</div>
</div>
