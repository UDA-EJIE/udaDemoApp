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
<h2 class="title mb-3">TinyMCE</h2>

<textarea id="editor" rows="20" style="width:100%" class="tinymce">
&lt;p&gt;
Este es un texto de ejemplo que puede editar en el editor &lt;strong&gt;TinyMCE &lt;/strong&gt; (v. 3.5.7).
&lt;/p&gt;
&lt;p&gt;
Nam nisi elit, cursus in rhoncus sit amet, pulvinar laoreet leo. Nam sed lectus quam, ut sagittis tellus. Quisque dignissim mauris a augue rutrum tempor. Donec vitae purus nec massa vestibulum ornare sit amet id tellus. Nunc quam mauris, fermentum nec lacinia eget, sollicitudin nec ante. Aliquam molestie volutpat dapibus. Nunc interdum viverra sodales. Morbi laoreet pulvinar gravida. Quisque ut turpis sagittis nunc accumsan vehicula. Duis elementum congue ultrices. Cras faucibus feugiat arcu quis lacinia. In hac habitasse platea dictumst. Pellentesque fermentum magna sit amet tellus varius ullamcorper. Vestibulum at urna augue, eget varius neque. Fusce facilisis venenatis dapibus. Integer non sem at arcu euismod tempor nec sed nisl. Morbi ultricies, mauris ut ultricies adipiscing, felis odio condimentum massa, et luctus est nunc nec eros.
&lt;/p&gt;
</textarea>

<!-- Some integration calls -->
<div class="tiny_links">
	<a id="show">[Show]</a>
	<a id="hide">[Hide]</a>
	<a id="bold">[Bold]</a>
	<a id="getContents">[Get contents]</a>
	<a id="getHTML">[Get selected HTML]</a>
	<a id="getText">[Get selected text]</a>
	<a id="getElement">[Get selected element]</a>
	<a id="insertHTML">[Insert HTML]</a>
	<a id="replace">[Replace selection]</a>
</div>