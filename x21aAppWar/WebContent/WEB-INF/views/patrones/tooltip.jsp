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

<section class="row">
	<div class="col-12">
		<h2>
			<spring:message code="tooltip.title" />
		</h2>
		<hr>

		<p>
			<spring:message code="tooltip.paragraph.line1" />
		</p>
		<p>
			<spring:message code="tooltip.paragraph.line2" />
		</p>
		<p>
			<spring:message code="tooltip.paragraph.line3" />
		</p>

		<div class="example">
			<div class="form-group row">
				<div class="col-md-6">
					<label for="nombre"><spring:message code="nombre" /></label> <input
						id="nombre" name="nombre" class="form-control"
						title="<spring:message  code='nombre' />" />

				</div>
				<div class="col-md-6">
					<label for="apellido"><spring:message code="apellido1" />:</label>
					<input id="apellido" name="apellido" class="form-control"
						title="<spring:message  code='apellido1' />" />
				</div>
			</div>
		</div>

		<p>
			<spring:message code="tooltip.paragraph.line4" />
		</p>

		<div class="example">
			<div class="form-group row">
				<label for="code" class="col-lg-1 col-md-2 col-form-label"><spring:message
						code="codigo" /></label>
				<div class="col-lg-3">
					<div class="input-group">
						<input id="code" name="code" type="text" class="form-control">
						<span class="input-group-btn">
							<button id="codeTooltip" class="btn btn-secondary" type="button">
								<i class="fa fa-question-circle" aria-hidden="true"></i>
							</button>
						</span>
					</div>
				</div>
			</div>
		</div>

		<p>
			<spring:message code="tooltip.paragraph.line5" />
		</p>

		<div class="example">
			<div class="form-group row">
				<label for="identificador" class="col-lg-1 col-md-2 col-form-label"><spring:message
						code="comun.identificador" /></label>
				<div class="col-lg-3">
					<div class="input-group">
						<input id="identificador" name="identificador" type="text"
							class="form-control"> <span class="input-group-btn">
							<button id="idTooltip" class="btn btn-secondary" type="button">
								<i class="fa fa-question-circle" aria-hidden="true"></i>
							</button>
						</span>
					</div>
				</div>

			</div>
		</div>

		<p>
			<spring:message code="tooltip.paragraph.line6" />
		</p>

		<div class="example">
			<div class="form-group row">
				<label for="htmlTooltip" class="col-lg-1 col-md-2 col-form-label">Html:</label>
				<div class="col-lg-3">
					<div class="input-group">
						<input id="htmlTooltip" name="htmlTooltip" type="text"
							class="form-control"> <span class="input-group-btn">
							<button id="idHtmlTooltip" class="btn btn-secondary"
								type="button">
								<i class="fa fa-question-circle" aria-hidden="true"></i>
							</button>
						</span>
					</div>
				</div>

			</div>
		</div>

		<div id="accordionExample1" class="rup_accordion"
			style="display: none;">

			<h1>
				<a><spring:message code="seccion1" /></a>
			</h1>
			<div class="section">
				<img alt="La primera sección"
					src="${staticsUrl}/x21a/images/primera_seccion.PNG">
			</div>
			<h1>
				<a><spring:message code="seccion2" /></a>
			</h1>
			<div class="section2">
				<img alt="La segunda sección"
					src="${staticsUrl}/x21a/images/segunda_seccion.PNG">
			</div>
			<h1>
				<a><spring:message code="seccion3" /></a>
			</h1>
			<div class="section">
				<img alt="La tercera sección"
					src="${staticsUrl}/x21a/images/tercera_seccion.PNG">
			</div>
			<h1>
				<a><spring:message code="seccion4" /></a>
			</h1>
			<div class="section2">
				<img alt="La cuarta sección"
					src="${staticsUrl}/x21a/images/cuarta_seccion.PNG">
			</div>
		</div>
	</div>
</section>