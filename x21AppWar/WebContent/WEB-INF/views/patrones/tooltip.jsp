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
<section>
	<h2><spring:message  code="tooltip.title" /></h2>
	<p>
		<spring:message  code="tooltip.paragraph.line1" />
	</p>
	<p>
		<spring:message  code="tooltip.paragraph.line2" />
	</p>
	<p>
		<spring:message  code="tooltip.paragraph.line3" />
	</p>
	
	<div class="example container-fluid">
		<div class="row">
		    <div class="col-md-6">
		    	<div class="form-group">
			      	<label for="nombre"><spring:message  code="nombre" /></label>
			      	<input id="nombre" name="nombre" class="form-control" title="<spring:message  code='nombre' />"/>
					  
				</div>
		    </div>
		    <div class="col-md-6">
		    	<div class="form-group">
			      	<label for="apellido"><spring:message  code="apellido1" />:</label>
			      	<input id="apellido" name="apellido" class="form-control"  title="<spring:message  code='apellido1' />"/>
				</div>
		    </div>
		</div>
	</div>
	
	<p>
		<spring:message  code="tooltip.paragraph.line4" />
	</p>
	
	
	<div class="example container-fluid">
		<div class="row">
		    <div class="col-md-12">
		    	<div class="form-group">
			      	<label for="code"><spring:message  code="codigo" /></label>
			      	<input id="code" name="code" /><img id="codeTooltip" alt="Imagen Tooltip" src="${staticsUrl}/rup/basic-theme/images/rup.confirm.png" />					  
				</div>
		    </div>
		   
		</div>
	</div>
	
	<p>
		<spring:message  code="tooltip.paragraph.line5" />
	</p>
	
	<div class="example container-fluid">
		<div class="row">
		    <div class="col-md-12">
		    	<div class="form-group">
			      	<label for="identificador"><spring:message  code="comun.identificador" /></label>
			      	<input id="identificador" name="identificador" /><img id="idTooltip" alt="Imagen Tooltip" src="${staticsUrl}/rup/basic-theme/images/rup.confirm.png" />					  
				</div>
		    </div>
		   
		</div>
	</div>
	
	<p>
		<spring:message  code="tooltip.paragraph.line6" />
	</p>
	
	
	<div class="example container-fluid">
		<div class="row">
		    <div class="col-md-12">
		    	<div class="form-group">
			      	<label for="htmlTooltip">Html:</label>
			      	<input id="htmlTooltip" name="htmlTooltip" /><img id="idHtmlTooltip" alt="Imagen Tooltip" src="${staticsUrl}/rup/basic-theme/images/rup.confirm.png" />					  
				</div>
		    </div>
		   
		</div>
	</div>
	
	<div id="accordionExample1" class="rup_accordion" style="display: none;">
	
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
	
	
</section>