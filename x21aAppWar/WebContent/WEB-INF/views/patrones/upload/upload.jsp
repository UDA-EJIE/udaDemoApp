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
	<h2><spring:message code="upload.title" /></h2>
	<p>
      	<spring:message code="upload.paragraph.line1" />
    </p>
    <p>
    	<spring:message code="upload.paragraph.line2" />
    </p>
    
    <h3>Configuración básica</h3>
    <p>
    	<spring:message code="upload.paragraph.basic" />
    </p>
    
    <div class="example">
		<jsp:include page="includes/basic.jsp"></jsp:include>
	</div>
	
	<h3>Cola de subida</h3>
	
	<p>
    	El componente permite definir una cola en el que los ficheros seleccionados son adjuntados sin ser enviados automáticamente.
    </p>
    <p>
    	Una vez seleccionados los ficheros, se permite actuar sobre ellos de manera global o individual. Las acciónes que se permiten son:
    </p>
    <ul>
    	<li>Subir todos los ficheros encolados.</li>
    	<li>Cancelar y eliminar de la cola todos los ficheros previamente adjuntados.</li>
    	<li>Subir un fichero de manera individual.</li>
    	<li>Cancelar y eliminar de la cola un fichero concreto.</li>
    	<li>Eliminar un fichero que ya haya sido subido.</li>
    	<li>Descargar un fichero que ya haya sido subido.</li>
    </ul>
    <p>
    	La configuración del componente que hace uso de todas las opciones sería la siguiente:
    </p>
    
    <div class="example">
		<jsp:include page="includes/uploadQueue.jsp"></jsp:include>
	</div>
	
	<h3>Subida en formulario</h3>
	
	<p>
    	El componente también permite seleccionar ficheros para su subida y que estos sean enviados junto con el resto de datos del formulario al realizarse el submit. Para ello se deberá de hacer uso de las propiedades:
    </p>
   	<ul>
   		<li><code>fileInput</code>: Identifica el <code>input</code> de tipo <code>file</code>.</li>
   		<li><code>submitFormButton</code>: Identifica el botón que realizará el envío del formulario.</li>
   		<li><code>submitInForm</code>: Propiedad que teniendo el valor de <code>true</code> configura el componente para este modo de funcionamiento.</li>
   	</ul>
    
    
    <div class="example">
		<jsp:include page="includes/uploadForm.jsp"></jsp:include>
	</div>
    
    <h3>Múltiples upload en formulario</h3>
    
    <p>
    	Por supuesto, es posible incluir varios componentes de subida de ficheros en el mismo formulario:
    </p>
    
    <div class="example">
		<jsp:include page="includes/uploadMultipleForm.jsp"></jsp:include>
	</div>
    
    
    <h3>Integración con PIF</h3>
    
    <p>
    	A continuación se muestra un ejemplo de como integrar el componente Upload con PIF.
    </p>
    
    <div class="example">
		<jsp:include page="includes/uploadPif.jsp"></jsp:include>
	</div>
</section>
