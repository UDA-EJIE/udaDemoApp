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

		<h2>
			<spring:message code="feedback.title" />
		</h2>
		<hr>
		<p>
			<spring:message code="feedback.paragraph.line1" />
		</p>
		<p>
			<spring:message code="feedback.paragraph.line2" />
		</p>
		<ul>
			<li>
				<strong><spring:message code="feedback.list.error"/></strong> <spring:message code="feedback.list.typeError"/>
			</li>
			<li>
				<strong><spring:message code="feedback.list.warning"/></strong> <spring:message code="feedback.list.typeWarning"/>
			</li>
			<li>
				<strong><spring:message code="feedback.list.ok"/></strong> <spring:message code="feedback.list.typeOk"/>
			</li>
		</ul>
		<div class="example">
			<div id="feedbackOk" role="alert"></div>
			<div id="feedbackAlert" role="alert"></div>
			<div id="feedbackError" role="alert"></div>
		</div>

		<br>

		<h3>
			<spring:message code="patrones.testIt" />
		</h3>
		<p>
			<spring:message code="feedback.testIt.line1" />
		</p>

		<div class="example">
			<div id="feedback" role="alert"></div>
		</div>

		<form>
			<div class="mb-3 row">
				<label for="feedback_type" class="col-md-1 col-12 col-form-label">type:</label>
				<div class="col-lg-2 col-md-2 col-12">
					<select id="feedback_type" class="form-control">
						<option value="">---</option>
						<option value="ok" selected="selected">ok</option>
						<option value="alert">alert</option>
						<option value="error">error</option>
					</select>
				</div>
				<label for="feedback_gotoTop"
					class="col-md-1 col-12 col-form-label">gotoTop:</label>
				<div class="col-lg-2 col-md-2 col-12">
					<select id="feedback_gotoTop" class="form-control">
						<option value="true">true</option>
						<option value="false">false</option>
					</select>
				</div>
				<label for="feedback_block"
					class="col-md-1 col-12 col-form-label">block:</label>
				<div class="col-lg-2 col-md-2 col-12">
					<select id="feedback_block" class="form-control">
						<option value="true">true</option>
						<option value="false">false</option>
					</select>
				</div>
				<label for="feedback_closeLink"
					class="col-md-1 col-12 col-form-label">closeLink:</label>
				<div class="col-lg-2 col-md-2 col-12">
					<select id="feedback_closeLink" class="form-control">
						<option value="true">true</option>
						<option value="false">false</option>
					</select>
				</div>
			</div>

			<div class="mb-3 row">
				<label for="feedback_imgClass" class="col-lg-1 col-form-label">imgClass:</label>
				<div class="col-lg-2">
					<input id="feedback_imgClass" class="form-control" type="text"
						placeholder="feedbackImgPruebas" />
				</div>
				<label for="feedback_delay" class="col-lg-1 col-form-label">delay
					(ms):</label>
				<div class="col-lg-2">
					<input id="feedback_delay" class="form-control" type="text" />
				</div>
				<label for="feedback_fadeSpeed" class="col-lg-1 col-form-label">fadeSpeed
					(ms):</label>
				<div class="col-lg-2">
					<input id="feedback_fadeSpeed" class="form-control" type="text" />
				</div>
			</div>

			<div class="btn-group" role="group">
				<button id="boton_create" type="button" class="btn btn-secondary">_Create</button>
				<button id="boton_destroy" type="button" class="btn btn-secondary">Destroy</button>
				<button id="boton_set" type="button" class="btn btn-secondary">Set</button>
				<button id="boton_setOptions" type="button"
					class="btn btn-secondary">Set_options</button>
				<button id="boton_setType" type="button" class="btn btn-secondary">Set_type</button>
				<button id="boton_setImgClass" type="button"
					class="btn btn-secondary">Set_imgClass</button>
				<button id="boton_hide" type="button" class="btn btn-secondary">Hide</button>
				<button id="boton_close" type="button" class="btn btn-secondary">Close</button>
				<button id="boton_show" type="button" class="btn btn-secondary">Show</button>
			</div>
		</form>

	</div>
</section>