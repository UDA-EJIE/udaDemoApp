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

<section>
	<h2>
		Mensaje
	</h2>
	<p>
		El componente tiene como objetivo mostrar al usuario de forma homogénea, clara y llamativa, los posibles mensajes que pueden desencadenar las acciones en la aplicación. 
	</p>
	<p>
		Estos mensajes predefinidos pueden ser de diferente tipología: error, confirmación, aviso o alerta.
	</p>
	<div class="example">
		<div class="row">
		    <div class="col-md-3">
		    	<button id="btnError" class="btn btn-block btn-secondary"><spring:message code="messages.showError" /></button>
		    </div>
		    <div class="col-md-3">
		    	<button id="btnConfirm" class="btn btn-block btn-secondary"><spring:message code="messages.showConfirm" /></button>
		    </div>
		    <div class="col-md-3">
		    	<button id="btnOK" class="btn btn-block btn-secondary"><spring:message code="messages.showOk" /></button>
		    </div>
		    <div class="col-md-3">
		    	<button id="btnAlert" class="btn btn-block btn-secondary"><spring:message code="messages.showAlert" /></button>
		    </div>
	  	</div>
	</div>
	
	<p>
		Por defecto también se modifica el estilo del alert nativo de JavaScript.
	</p>
	
	<div class="example">
		<div class="row">
		    <div class="col-md-3">
		    	<button id="btnAlertJS" class="btn btn-block btn-secondary"><spring:message code="messages.showJSAlert" /></button>
		    </div>
		    <div class="col-md-9">
		    	
		    </div>	
	  	</div>
	</div>
</section>




