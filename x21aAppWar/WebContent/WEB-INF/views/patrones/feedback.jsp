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
	<h2><spring:message code="feedback.title" /></h2>
	<p>
      	<spring:message code="feedback.paragraph.line1" />
    </p>
    <p>
    	<spring:message code="feedback.paragraph.line2" />
      
    </p>
    <ul>
    	<li><spring:message code="feedback.list.typeError" /></li>
    	<li><spring:message code="feedback.list.typeWarning" /></li>
    	<li><spring:message code="feedback.list.typeOk" /></li>
    </ul>
	<div class="example">
		<div id="feedbackOk" role="alert"></div>
      	<div id="feedbackAlert" role="alert"></div>
      	<div id="feedbackError"role="alert"></div>
	</div>
	
	<section>
		<h3><spring:message code="patrones.testIt" /></h3>
		<p>
			<spring:message code="feedback.testIt.line1" />
		</p>
		
		<div class="container-fluid">
			<div class="row">
				<div class="example">
					<div id="feedback" role="alert"></div>
				</div>
			
			</div>
			<form>
				<div class="row">
				    <div class="col-md-3">
				    	<div class="form-group">
					      	<label for="feedback_type">type:</label>
							<select id="feedback_type" class="form-control">
								<option value="">---</option>
								<option value="ok" selected="selected">ok</option>
								<option value="alert">alert</option>
								<option value="error">error</option>
							</select>
						</div>
				    </div>
				    <div class="col-md-3">
				    	<div class="form-group">
					      	<label for="feedback_gotoTop">gotoTop:</label>
							<select id="feedback_gotoTop" class="form-control">
								<option value="true">true</option>
								<option value="false">false</option>
							</select>  
						</div>
				    </div>
				    <div class="col-md-3">
				    	<div class="form-group">
					      	<label for="feedback_block">block:</label>
							<select id="feedback_block" class="form-control">
								<option value="true">true</option>
								<option value="false">false</option>
							</select>
						</div>
				    </div>
				    <div class="col-md-3">
				    	<div class="form-group">
					      	<label for="feedback_closeLink">closeLink:</label>
							<select id="feedback_closeLink" class="form-control">
								<option value="true">true</option>
								<option value="false">false</option>
							</select>
						</div>
				    </div>
			  	</div>
			  	<div class="row">
				    <div class="col-md-4">
				    	<div class="form-group">
					      	<label for="feedback_imgClass">imgClass:</label> 
							<input id="feedback_imgClass" class="form-control" type="text" placeholder="feedbackImgPruebas" />  
						</div>
				    </div>
				    <div class="col-md-4">
				    	<div class="form-group">
					      	<label for="feedback_delay">delay (ms):</label>
							<input id="feedback_delay" class="form-control" type="text" />
						</div>
				    </div>
				    <div class="col-md-4">
				    	<div class="form-group">
					      	<label for="feedback_fadeSpeed">fadeSpeed (ms):</label>
							<input id="feedback_fadeSpeed" class="form-control" type="text" />
						</div>
				    </div>
			  	</div>
			  	
			  	<div class="row">
			  		<div class="col-md-2 col-lg-1">
			  			<button id="boton_create" type="button" class="btn btn-secondary btn-block">_Create</button>
			  		</div>
			  		<div class="col-md-2 col-lg-1">
			  			<button id="boton_destroy" type="button" class="btn btn-secondary btn-block">Destroy</button>
					</div>
			  		<div class="col-md-4 col-lg-1">
			  			<button id="boton_set" type="button" class="btn btn-secondary btn-block">Set</button>
					</div>
			  		<div class="col-md-4 col-lg-2">
			  			<button id="boton_setOptions" type="button" class="btn btn-secondary btn-block">Set_options</button>
					</div>
			  		<div class="col-md-3 col-lg-2">
			  			<button id="boton_setType" type="button" class="btn btn-secondary btn-block">Set_type</button>
					</div>
			  		<div class="col-md-3 col-lg-2">
			  			<button id="boton_setImgClass" type="button" class="btn btn-secondary btn-block">Set_imgClass</button>
					</div>
			  		<div class="col-md-2 col-lg-1">
			  			<button id="boton_hide" type="button" class="btn btn-secondary btn-block">Hide</button>
					</div>
			  		<div class="col-md-2 col-lg-1">
			  			<button id="boton_close" type="button" class="btn btn-secondary btn-block">Close</button>
					</div>
			  		<div class="col-md-2 col-lg-1">
			  			<button id="boton_show" type="button" class="btn btn-secondary btn-block">Show</button>
					</div>
			  		
			  	
			  	</div>
			</form>
		</div>
	
	</section>

</section>
