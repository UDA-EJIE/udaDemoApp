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

<div class="container-fluid dashboard-container">


  <!-- New Dashboard dialog -->
  <div id="newDashboardDialog" style="display:none">
    <div class="row col-md-12">
      <p>Se va a crear un nuevo escritorio.</p>
      <p>Para ello, debe de introducir un nombre con el que se identificará.</p>
    </div>
    <div class="row col-md-12">
      <div class="form-group">
			     <label for="feedback_type">Nombre:</label>
					 <input id="feedback_imgClass" class="form-control" type="text" placeholder="feedbackImgPruebas" />
      </div>
    </div>
  </div>

  <!-- New Dashboard dialog -->
  <div id="addWidgetDialog">
    <div class="row col-md-12">
      <p>Se va a añadir un widget al escritorio.</p>
      <p>Por fevor, seleccione el widget que desea añadir:</p>
    </div>
    <div class="row col-md-12">
      <div class="form-group">
        <ul class="list-group">
          <a id="newWidgetHtmlInline" class="list-group-item" >
            <h5 class="list-group-item-heading">Widget HTML inline</h5>
            <p class="list-group-item-text">El contenido del widget consiste en código HTML indicado como parámetro en la declaración del mismo.</p>
          </a>
          <a  id="newWidgetTemplateInline" class="list-group-item">
            <h5 class="list-group-item-heading">Widget HTML Inline</h5>
            <p class="list-group-item-text">El contenido del widget se define en un <code>div</code> existente en la propia página.</p>
          </a>
          <a  id="newWidgetTemplateXhr" class="list-group-item">
            <h5 class="list-group-item-heading">Widget HTML Remoto</h5>
            <p class="list-group-item-text">El contenido del widget se obtiene a partir de la respuesta HTML realizada a la <code>url</code> especificada en la definición del widget.</p>
          </a>
        </ul>
      </div>
    </div>
  </div>

  <!-- FAB Button -->
  <button type="button" id="fabButton" data-fab="true" data-fixed="true" data-layer="fabButtonDiv"><span class="mdi mdi-settings" /></button>
  <div id="fabButtonDiv">

	</div>
  <div class="dashboard-aside">
    <div class="dashboard-menu-title">
      <a id="currentDashboardLink">
        <i class="mdi mdi-monitor-dashboard" aria-hidden="true"></i><span class="current-dashboard" id="currentDashboard"></span>
      </a>
    </div>
    <ul>
      <li>
      	<spring:url value="#dashboardList" var="urlDList" htmlEscape="true"/>
        <a data-toggle="collapse" href="${urlDList}" aria-expanded="false" aria-controls="dashboardList">
          <i class="mdi mdi-desktop-mac" aria-hidden="true"></i><span>Escritorios</span>
        </a>
        <div class="collapse" id="dashboardList">
          <ul>

          </ul>
        </div>

      </li>
      <li>
      	<spring:url value="#toolsList" var="urlToolList" htmlEscape="true"/>
        <a data-toggle="collapse" href="${urlToolList}" aria-expanded="false" aria-controls="toolsList">
          <i class="mdi mdi-settings" aria-hidden="true"></i><span>Acciones</span>
        </a>
        <div class="collapse" id="toolsList">
          <ul>
            <li><a id="addWidget">Añadir widget</a></li>
            <li><a id="newDashboard">Nuevo Escritorio</a></li>
            <li><a id="deleteDashboard">Eliminar Escritorio</a></li>
            <li><a id="saveDashboard">Guardar Escritorio</a></li>
          </ul>
        </div>

      </li>
    </ul>
  </div>
  <div class="dashboard-main">
    <div class="">

      <section>
        <div class="row">
          <div class="col-md-12">
            <br/>
          </div>
        </div>
      </section>

      <div id="dashboard" class="grid-stack" >

      </div>
    </div>


    <script type="text/template" id="templateInline">

    Prueba de template
    </script>


    <script type="text/template" id="templateConfig">

    Dialogo de configuración
    </script>
  </div>
</div>
