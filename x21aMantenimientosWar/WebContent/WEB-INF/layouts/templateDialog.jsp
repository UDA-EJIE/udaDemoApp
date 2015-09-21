<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="tiles" uri="/WEB-INF/tld/tiles-jsp.tld"%>

<div class="contenedorDialog">
	<!-- Contenidos -->
	<tiles:insertAttribute name="content" />

	<!-- Includes JS -->
	<tiles:insertAttribute name="includes" />
</div>
