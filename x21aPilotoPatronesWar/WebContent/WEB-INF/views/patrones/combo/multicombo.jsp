<%--  
 -- Copyright 2012 E.J.I.E., S.A.
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
<h2><spring:message code="multicombo" /></h2>

<p>Multicombo local</p>
<select multiple="multiple">
<option value="option1">Option 1</option>
<option value="option2">Option 2</option>
<option value="option3">Option 3</option>
<option value="option4">Option 4</option>
</select>
<br><br> 

<p>Multicombo local (preselección)</p>
<select multiple="multiple">
<option value="option1">Option 1</option>
<option value="option2" selected="selected">Option 2</option>
<option value="option3" selected="selected">Option 3</option>
<option value="option4">Option 4</option>
</select>
<br><br>


