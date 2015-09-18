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
<h2>Validacion</h2>


<div id="feedbackErroresValidaciones"></div>
<div>
<fieldset class="alumnoFieldset">
	<legend><spring:message code="configuracion" /></legend>
	<span><spring:message code="patron.validacion.configuracion.texto" /></span>
	<br/><br/>
	
	<input type="checkbox" id="liveCheckingErrors" /><label for="liveCheckingErrors"><spring:message code="patron.validacion.configuracion.liveCheckingErrors" /></label><br/>
	<input type="checkbox" id="checkFeedbackError" checked="checked" /><label for="checkFeedbackError"><spring:message code="patron.validacion.configuracion.checkFeedbackError" /></label><br/>
	<input type="checkbox" id="checkShowErrorsFeedback" checked="checked"/><label for="checkShowErrorsFeedback"><spring:message code="patron.validacion.configuracion.checkShowErrorsFeedback" /></label><br/>
	<input type="checkbox" id="checkShowFieldErrorsTip" checked="checked"/><label for="checkShowFieldErrorsTip"><spring:message code="patron.validacion.configuracion.checkShowFieldErrorsTip" /></label><br/> 
	<br/>
	<form:errors></form:errors>
	<button id="botonConfiguracion"><spring:message code="aplicarConfiguracion" /></button>
</fieldset>
</div>

<div id="tabsValidacion"></div>
<div id="validacionesServidor"></div>
<div id="validacionesCliente"></div>
	
<div id="divValidaciones">
	<form:form id="formValidaciones" action='${pageContext.request.contextPath}/patrones/validacion/cliente' modelAttribute="alumno" >
	
	<fieldset class="alumnoFieldset">
		<legend><spring:message code="obligatoriedad" /></legend>
		<div class="two-col">
			<div class="col1">
		     	<label for="campoObligatorio1" class="label"><spring:message code="campoObligatorio" /></label>
			  	<input type="text" name="campoObligatorio1" class="formulario_linea_input abajo" size="40" id="campoObligatorio1" />
		    </div>
		    <div class="col1">
	     		<input type="checkbox" name="esObligatorio" class="formulario_linea_input" size="20" id="esObligatorio" style="float:left;"/>
		     	<label for="esObligatorio" class="label">
		     		&nbsp;<spring:message code="patron.validacion.obligatorioSi" />
	     		</label>
			  	<input type="text" name="campoObligatorio2" class="formulario_linea_input" size="40" id="campoObligatorio2" />
		    </div>
		    <div class="col1">
		     	<select id="diaObligatorio" style="float: left">
		     	</select>
		     	<label for="campoObligatorio3" class="label">
			     	&nbsp;<spring:message code="patron.validacion.obligatorioSiCombo" />
		     	</label>
			  	<input type="text" name="campoObligatorio3" class="formulario_linea_input" size="40" id="campoObligatorio3" />
		    </div>
		  </div>
	</fieldset>
	
	<fieldset class="alumnoFieldset">
		<legend><spring:message code="patron.validacion.validacionesNumericas" /></legend>
		<div class="two-col">
	   		<div class="col1">
		     	<label for="valorMinimo" class="label"><spring:message code="patron.validacion.min" /></label>
			  	<input type="text" name="valorMinimo" class="formulario_linea_input" size="40" id="valorMinimo" />
		    </div>
		    <div class="col1">
		     	<label for="valorMaximo" class="label"><spring:message code="patron.validacion.max" /></label>
			  	<input type="text" name="valorMaximo" class="formulario_linea_input" size="40" id="valorMaximo" />
		    </div>
		    <div class="col1">
		     	<label for="valorIntervalo" class="label"><spring:message code="patron.validacion.range" /></label>
			  	<input type="text" name="valorIntervalo" class="formulario_linea_input" size="40" id="valorIntervalo" />
		    </div>
		 </div>
		 <div class="two-col">
		    <div class="two-col col1">
		     	<label for="entero" class="label"><spring:message code="patron.validacion.integer" /></label>
			  	<input type="text" name="entero" class="formulario_linea_input" size="40" id="entero" />
		    </div>
		    <div class="col1">
		     	<label for="numeroDecimal" class="label"><spring:message code="patron.validacion.number" /></label>
			  	<input type="text" name="numeroDecimal" class="formulario_linea_input" size="40" id="numeroDecimal" />
		    </div>
		   
		</div>
	</fieldset>
	
	<fieldset class="alumnoFieldset">
		<legend><spring:message code="patron.validacion.validacionesTexto" /></legend>
		<div class="two-col">
			 <div class="col1">
		     	<label for="longitudMinima" class="label"><spring:message code="patron.validacion.minlength" /></label>
			  	<input type="text" name="longitudMinima" class="formulario_linea_input" size="40" id="longitudMinima" />
		    </div>
		    <div class="col1">
		     	<label for="longitudMaxima" class="label"><spring:message code="patron.validacion.maxlength" /></label>
			  	<input type="text" name="longitudMaxima" class="formulario_linea_input" size="40" id="longitudMaxima" />
		    </div>
		    <div class="col1">
		     	<label for="longitudIntervalo" class="label"><spring:message code="patron.validacion.rangelength" /></label>
			  	<input type="text" name="longitudIntervalo" class="formulario_linea_input" size="40" id="longitudIntervalo" />
		    </div>
		 </div>
		<div class="two-col">
			<div class="two-col col1">
		     	<label for="palabrasMaximo" class="label"><spring:message code="patron.validacion.maxWords" /></label>
			  	<input type="text" name="palabrasMaximo" class="formulario_linea_input" size="40" id="palabrasMaximo" />
		    </div>
		    <div class="two-col col1">
		     	<label for="palabrasMinimo" class="label"><spring:message code="patron.validacion.minWords" /></label>
			  	<input type="text" name="palabrasMinimo" class="formulario_linea_input" size="40" id="palabrasMinimo" />
		    </div>
		    <div class="two-col col1">
		     	<label for="palabrasIntervalo" class="label"><spring:message code="patron.validacion.number" /></label>
			  	<input type="text" name="palabrasIntervalo" class="formulario_linea_input" size="40" id="palabrasIntervalo" />
		    </div>
		</div>
		<div class="two-col">
			<div class="two-col col1">
		     	<label for="letrasYPuntuacion" class="label"><spring:message code="patron.validacion.letterswithbasicpunc" /></label>
			  	<input type="text" name="letrasYPuntuacion" class="formulario_linea_input" size="40" id="letrasYPuntuacion" />
		    </div>
		     <div class="two-col col1">
		     	<label for="alfanumerico" class="label"><spring:message code="patron.validacion.alphanumeric" /></label>
			  	<input type="text" name="alfanumerico" class="formulario_linea_input" size="40" id="alfanumerico" />
		    </div>
			<div class="two-col col1">
		     	<label for="soloLetras" class="label"><spring:message code="patron.validacion.lettersonly" /></label>
			  	<input type="text" name="soloLetras" class="formulario_linea_input" size="40" id="soloLetras" />
		    </div>
		
		</div>
		<div class="two-col">
			<div class="col1">
		     	<label for="soloDigitos" class="label"><spring:message code="patron.validacion.digits" /></label>
			  	<input type="text" name="soloDigitos" class="formulario_linea_input" size="40" id="soloDigitos" />
		    </div>
			<div class="col1">
		     	<label for="sinEspacios" class="label"><spring:message code="patron.validacion.nowhitespace" /></label>
			  	<input type="text" name="sinEspacios" class="formulario_linea_input" size="40" id="sinEspacios" />
		    </div>
		    <div class="two-col col1">
		     	<label for="patron" class="label"><spring:message code="patron.validacion.pattern" /></label>
			  	<input type="text" name="patron" class="formulario_linea_input" size="40" id="patron" />
		    </div>
		</div>
		
	</fieldset>
	
	<fieldset class="alumnoFieldset">
		<legend><spring:message code="patron.validacion.validacionesFormato" /></legend>
		<div class="two-col">
			<div class="col1">
		     	<label for="validacionEmail" class="label"><spring:message code="patron.validacion.email" /></label>
			  	<input type="text" name="validacionEmail" class="formulario_linea_input" size="40" id="validacionEmail" />
		    </div>
		    <div class="col1">
		     	<label for="validacionUrl" class="label"><spring:message code="patron.validacion.url" /></label>
			  	<input type="text" name="validacionUrl" class="formulario_linea_input" size="40" id="validacionUrl" />
		    </div>
		    <div class="two-col col1">
		     	<label for="dni" class="label"><spring:message code="patron.validacion.dni" /></label>
			  	<input type="text" name="dni" class="formulario_linea_input" size="40" id="dni" />
		    </div>
		</div>
		<div class="two-col">
			<div class="col1">
		     	<label for="validacionFecha" class="label"><spring:message code="patron.validacion.date" /></label>
			  	<input type="text" name="validacionFecha" class="formulario_linea_input" size="40" id="validacionFecha" />
		    </div>
		    <div class="col1">
		     	<label for="validacionFechaISO" class="label"><spring:message code="patron.validacion.dateISO" /></label>
			  	<input type="text" name="validacionFechaISO" class="formulario_linea_input" size="40" id="validacionFechaISO" />
		    </div>
		    <div class="two-col col1">
		     	<label for="tarjetaCredito" class="label"><spring:message code="patron.validacion.creditcard" /></label>
			  	<input type="text" name="tarjetaCredito" class="formulario_linea_input" size="40" id="tarjetaCredito" />
		    </div>
		</div>
		<div class="two-col">
			<div class="col1">
	     		<label for="extension" class="label"><spring:message code="patron.validacion.accept" /></label>
		  		<input type="text" name="extension" class="formulario_linea_input" size="40" id="extension" />
	    	</div>
	     	<div class="col1">
		     	<label for="clave" class="label"><spring:message code="patron.validacion.equalTo.clave" /></label>
			  	<input type="text" name="clave" class="formulario_linea_input" size="40" id="clave" /><br/>
			  	<label for="clave_confirmar" class="label"><spring:message code="patron.validacion.equalTo.confirmarClave" /></label>
			  	<input type="text" name="clave_confirmar" class="formulario_linea_input" size="40" id="clave_confirmar" />
	    	</div>
		</div>
	</fieldset>
	
	
		<input type="submit" value="Validar"  />
	
	</form:form>
</div>

<div id="divValidaciones2">
	<form:form id="formValidaciones2" action='${pageContext.request.contextPath}/patrones/validacion/cliente' modelAttribute="alumno" >
	
	<fieldset class="alumnoFieldset">
		<legend><spring:message code="options" /></legend>
			
			<input  type="radio" id="gender_male" value="m" name="gender" validate="required:true" />
			<label for="gender_male"><spring:message code="patron.validacion.primeraOpcion" /></label>
			<input  type="radio" id="gender_female" value="f" name="gender"/>
			<label for="gender_female"><spring:message code="patron.validacion.segundaOpcion" /></label>
			<label for="gender" class="error" style="display:none"><spring:message code="patron.validacion.seleccioneUnaOpcion" /></label>
			
			<br/>
			
		
	</fieldset>
	
	<fieldset class="alumnoFieldset">
		<legend><spring:message code="checkbox" /></legend>
		<fieldset class="alumnoFieldset">
		<legend><spring:message code="obligatorio" /></legend>
			<input type="checkbox" class="checkbox" id="agree" name="agree" validate="required:true" /><spring:message code="patron.validacion.aceptarCondiciones" />
			<label for="agree" class="error block" style="display:none"><spring:message code="patron.validacion.error.aceptarCondiciones" /></label>
		</fieldset>
		<fieldset class="alumnoFieldset">
				<legend><spring:message code="patron.validacion.seleccioneDosOMas" /></legend>
				<label for="spam_email"><spring:message code="correo" /></label>
				<input type="checkbox" class="checkbox" id="spam_email" value="email" name="notificacion[]" validate="required:true, minlength:2" />
			
				<label for="spam_phone"><spring:message code="sms" /></label>
				<input type="checkbox" class="checkbox" id="spam_phone" value="phone" name="notificacion[]" />
			
				<label for="spam_mail"><spring:message code="email" /></label>
				<input type="checkbox" class="checkbox" id="spam_mail" value="mail" name="notificacion[]" />

				<label for="notificacion[]" class="error" style="display:none"><spring:message code="patron.validacion.error.seleccioneDosOMas" /></label>
			</fieldset>
	</fieldset>
	
	<fieldset class="alumnoFieldset">
		<legend>Combo</legend>
		<label for="lenguaje_combo"><spring:message code="patron.validacion.seleccioneUnLenguaje" /></label><br/>
		<select id="lenguaje_combo" name="lenguaje">
			<option value=""></option>
			<option value="1">Java</option>
			<option value="2">C++</option>
			<option value="3">PHP</option>
			<option value="4">Cobol</option>
		</select>
		<label for="lenguaje" class="error" style="display:none"><spring:message code="patron.validacion.seleccioneDosNotificaciones" /></label>
		<br/><br/>
		<label for="lenguaje_combo_multi"><spring:message code="patron.validacion.seleccioneEntreDosYTres" /></label><br/>
		<select id="lenguaje_combo_multi" name="lenguajeMulti" multiple="multiple">
			<option value=""></option>
			<option value="1">Java</option>
			<option value="2">C++</option>
			<option value="3">PHP</option>
			<option value="4">Cobol</option>
		</select>
		<label for="lenguajeMulti" class="error" style="display:none"><spring:message code="patron.validacion.error.seleccioneEntreDosYTres" /></label>
		
	</fieldset>
	
	
	
	
	<fieldset class="alumnoFieldset">
		<legend><spring:message code="componentesRup" /></legend>
		<div class="two-col">
			<div class="col1">
				<label for="autocomplete" class="label"><spring:message code="patron.autocomplete" /></label>
				<input id="autocomplete" name="autocomplete" /><spring:message code="patron.validacion.autocomplete.ejemplo" />
			</div>
		</div>		
		
		<div class="two-col">
			<div class="col1">
				<label for="comboAbueloRemoto" class="label"><spring:message code="localidad" /></label>
				<select id="comboAbueloRemoto" name="provincia" class="rup-combo"><option>&nbsp;</option></select>
			</div>
			<div class="col1">
				<label for="comboPadreRemoto" class="label"><spring:message code="comarca" /></label>
				<select id="comboPadreRemoto" name="comarca" class="rup-combo"><option>&nbsp;</option></select>
			</div>
			<div class="col1">			
				<label for="comboHijoRemoto" class="label"><spring:message code="localidad" /></label>
				<select id="comboHijoRemoto" name="localidad" class="rup-combo"><option>&nbsp;</option></select>
			</div>
		</div>
		<div class="two-col">
			<div class="col1">
				<label for="fecha" class="label"><spring:message code="patron.validacion.fecha" /></label>
				<input id="fecha" name="fecha" />
			</div>
		</div>
		<div class="two-col">
			<div class="col1">
				<label for="fechaHora" class="label"><spring:message code="patron.validacion.fechaYhora" /></label>
				<input id="fechaHora" name="fechaHora" />
			</div>
		</div>
	</fieldset>
	<input type="submit" value="Validar formulario"  />
	</form:form>
</div>

<div id="divValidaciones3">

	<form:form id="formServidor" action='${pageContext.request.contextPath}/patrones/validacion/servidor' modelAttribute="alumno" >
				
				<fieldset class="alumnoFieldset">
					<legend><spring:message code="datosPersonales" /></legend>
					
					
					<span><spring:message code="patron.validacion.servidor.texto" /></span><br/><br/>
					
					<span><i><spring:message code="nombre" /> : </i></span>
					<ul style="padding: 0 3em;">
						<li><spring:message code="patron.validacion.error.campoObligatorio" /></li>
						<li><spring:message code="patron.validacion.error.longitudMax" arguments="20" /></li>
					</ul>
					<span><i><spring:message code="apellido1" arguments="" />: </i></span>
					<ul style="padding: 0 3em;">
						<li><spring:message code="patron.validacion.error.campoObligatorio" /></li>
						<li><spring:message code="patron.validacion.error.longitudMax" arguments="30" /></li>
					</ul>
					<span><i><spring:message code="apellido2" /> : </i></span>
					<ul style="padding: 0 3em;">
						<li><spring:message code="patron.validacion.error.longitudMax" arguments="30" /></li>
					</ul>
					<br/>
					
					<div class="two-col" >
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
					</fieldset>
					<input type="submit" value="Validar formulario"  />
		</form:form>
</div>

<div id="divValidaciones4">

	<form:form id="formServidor2" action='${pageContext.request.contextPath}/patrones/validacion/servidor2' modelAttribute="alumno" >
				
				<fieldset class="alumnoFieldset">
					<legend><spring:message code="datosPersonales" /></legend>
					
					<span><spring:message code="patron.validacion.servidor.texto" /></span><br/><br/>
					<ul style="padding: 0 3em;">
						<li><spring:message code="patron.validacion.servidor.ejemplo2.texto1" /></li>
						<li><spring:message code="patron.validacion.servidor.ejemplo2.texto2" /></li>
						<li><spring:message code="patron.validacion.servidor.ejemplo2.texto3" /></li>
					</ul>
					
					<div class="two-col" >
					    <div class="col1">
					       <label for="usuario" class="label"><spring:message code="usuario" /></label>
							<input type="text" name="usuario" class="formulario_linea_input" size="20" id="usuario" />
					    </div>
					
					    <div class="col1">
					        <label for="password" class="label"><spring:message code="password" /></label>
							<input type="text" name="password" class="formulario_linea_input" size="30" id="password" />
					    </div>
					</div>
					</fieldset>
					<input type="submit" value="Validar formulario"  />
		</form:form>
</div>

<div id="divValidaciones4">

</div>