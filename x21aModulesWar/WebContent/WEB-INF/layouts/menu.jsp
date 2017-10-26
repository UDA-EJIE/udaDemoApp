<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 -- 
 -- http://ec.europa.eu/idabc/eupl.html
 -- 
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia. 
 --%>
 <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
 <%@include file="/WEB-INF/includeTemplate.inc"%>
 
<nav class="rup-navbar navbar">
  <button type="button" class="navbar-toggler hidden-lg-up"  type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"></button>
  <div id="navbarResponsive" class="collapse navbar-toggleable-md">
    <a class="navbar-brand" href="#">Uda</a>
    <ul class="nav navbar-nav">
      <li class="nav-item dropdown">
        <!-- <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Componentes <span class="caret"></span></a> -->
        <a class="nav-link dropdown-toggle" href="#" id="responsiveNavbarDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Componentes</a>
        <div class="dropdown-menu" aria-labelledby="responsiveNavbarDropdown">
            <a class="dropdown-item" href="/x21aAppWar/patrones/feedback"><i class="fa fa-check-square" aria-hidden="true"></i><spring:message code="feedback" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/tooltip"><i class="fa fa-comment-o" aria-hidden="true"></i><spring:message code="tooltip" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/message"><i class="fa fa-envelope" aria-hidden="true"></i><spring:message code="message" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/dialog"><i class="fa fa-window-restore" aria-hidden="true"></i><spring:message code="dialog" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/progressbar"><i class="fa fa-hourglass-half" aria-hidden="true"></i>Barra de progreso</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="/x21aAppWar/patrones/contextMenu"><i class="fa fa-list-alt" aria-hidden="true"></i><spring:message code="contextMenu" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/button"><i class="fa fa-hand-pointer-o" aria-hidden="true"></i><spring:message code="button" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/toolbar"><i class="fa fa-wrench" aria-hidden="true"></i><spring:message code="toolbar" /></a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="/x21aAppWar/patrones/accordion"><i class="fa fa-list" aria-hidden="true"></i><spring:message code="accordion" /></a>

            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#">Pestañas</a>
              <div class="dropdown-menu menu-right" >
                <a class="dropdown-item" href="/x21aAppWar/patrones/tabsStatic"><spring:message code="tabsStatic" /></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/tabsAjax"><spring:message code="tabsAjax" /></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/tabsMixto"><spring:message code="tabsMixto" /></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/maintTab"><spring:message code="maintTab" /></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/tabsScrollable"><spring:message code="tabsScrollable" /></a>
                
              </div>
            </div>

            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#">Wizard</a>
              <div class="dropdown-menu menu-right" >
                <a class="dropdown-item" href="/x21aAppWar/patrones/wizard"><spring:message code="wizardA" /></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/wizard_includeFile"><spring:message code="wizardB" htmlEscape="true"/></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/wizard_jspInclude"><spring:message code="wizardC" htmlEscape="true"/></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/wizard_jstlImport"><spring:message code="wizardD" htmlEscape="true"/></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/wizard_dinamico"><spring:message code="wizardE" /></a>
              </div>
            </div>

            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#">Arbol</a>
              <div class="dropdown-menu menu-right" >
                <a class="dropdown-item" href="/x21aAppWar/patrones/trees"><spring:message code="tree_multiple_configuraciones" /></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/treeDAD"><spring:message code="tree_multiple_d&d"/></a>
              </div>
            </div>

            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="/x21aAppWar/patrones/autocomplete"><spring:message code="autocomplete" /></a>

            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#">Combo</a>
              <div class="dropdown-menu menu-right">
                <a class="dropdown-item" href="/x21aAppWar/patrones/comboSimple"><spring:message code="comboSimple" /></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/comboEnlazadoSimple"><spring:message code="comboEnlazadoSimple" /></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/comboEnlazadoMultiple"><spring:message code="comboEnlazadoMulti" /></a>
                <a class="dropdown-item" href=/x21aAppWar/patrones/multicombo><spring:message code="multicombo" /></a>
              </div>
            </div>

            <a class="dropdown-item" href="/x21aAppWar/patrones/slider">Deslizador</a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/date"><i class="fa fa-calendar" aria-hidden="true"></i><spring:message code="date" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/form"><spring:message code="form" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/time"><i class="fa fa-clock-o" aria-hidden="true"></i><spring:message code="time" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/spinner">Spinner</a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/upload"><spring:message code="upload" /></a>
            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#"><spring:message code="validate" /></a>
              <div class="dropdown-menu menu-right">
              	<a class="dropdown-item" href="/x21aAppWar/patrones/validate">Configuración</a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/validateRules">Reglas de validación</a>
                
              </div>
            </div>
            
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="/x21aAppWar/patrones/charts"><i class="fa fa-bar-chart" aria-hidden="true"></i><spring:message code="charts.charts" /></a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="tableDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Tabla</a>
        <div class="dropdown-menu" aria-labelledby="tableDropdown">
          <a class="dropdown-item" href="/x21aAppWar/table/filtroSimple">Filtro simple</a>
          <a class="dropdown-item" href="/x21aAppWar/table/formEditAutogenerated">Edición en formulario (autogenerado)</a>
          <a class="dropdown-item" href="/x21aAppWar/table/formEdit">Edición en formulario</a>
          <a class="dropdown-item" href="/x21aAppWar/table/formEditMultiselection">Edición en formulario con multiselección</a>
          <a class="dropdown-item" href="/x21aAppWar/table/inlineEditExcelMode">Edición en línea (Modo Excel)</a>
          <a class="dropdown-item" href="/x21aAppWar/table/inlineEdit">Edición en línea</a>
          <a class="dropdown-item" href="/x21aAppWar/table/inlineEditMultiselection">Edición en línea con multiselección</a>
          <a class="dropdown-item" href="/x21aAppWar/table/grouping">Tabla con agrupamiento</a>
          <a class="dropdown-item" href="/x21aAppWar/table/masterDetail">Maestro - Detalle</a>
          <a class="dropdown-item" href="/x21aAppWar/table/tableLoadOnStartUp">LoadOnStartUp = false</a>
          <a class="dropdown-item" href="/x21aAppWar/table/dialog">Tabla en diálogo</a>
          <a class="dropdown-item" href="/x21aAppWar/table/tableRadiobutton">tableRadiobutton</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="gridResponsiveDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Grid responsive</a>
        <div class="dropdown-menu" aria-labelledby="gridResponsiveDropdown">
          <a class="dropdown-item" href="/x21aAppWar/bootstrap/stackedHorizontal">Stacked to horizontal</a>
          <a class="dropdown-item" href="/x21aAppWar/bootstrap/mobileDesktop">Mobile and desktop</a>
          <a class="dropdown-item" href="/x21aAppWar/bootstrap/mobileTabletDesktop">Mobile, Tablet and desktop</a>
          <a class="dropdown-item" href="/x21aAppWar/bootstrap/exampleForm">Formulario ejemplo</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dashboardDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><spring:message code="dashboard" /></a>
        <div class="dropdown-menu" aria-labelledby="dashboardDropdown">
          <a class="dropdown-item" href="/x21aAppWar/dashboard/dashboardSimple"><spring:message code="dashboard.simple.title" /></a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="styleGuideDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Guía de estilos</a>
        <div class="dropdown-menu" aria-labelledby="styleGuideDropdown">
          <a class="dropdown-item" href="/x21aAppWar/styleGuide">Guía de estilos</a>
        </div>
      </li>
    </ul>
    <ul class="nav navbar-nav float-md-right rup-nav-tools">
      <li class="nav-item">
        <a class="nav-link rup-nav-tool-icon" href="#" id="x21aApp_language" data-toggle="dropdown"><i class="fa fa-globe" aria-hidden="true"></i><span data-rup-lang-current=""></span></a>
		<div class="dropdown-menu" aria-labelledby="x21aApp_language">
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link rup-nav-tool-icon" href="#"><i class="fa fa-cog " aria-hidden="true"></i></a>
      </li>
      <li class="nav-item">
        <a class="nav-link rup-nav-user rup-nav-tool-icon" href="#"><i class="fa fa-user-circle-o " aria-hidden="true"></i></a>
      </li>
      <li class="nav-item swingTop">
        <a class="nav-link rup-nav-user rup-nav-tool-icon" href="javascript:void(0)"><i class="fa fa-arrow-circle-up " aria-hidden="true"></i></a>
      </li>
    </ul>
  </div><!--/.navbar-collapse -->

</nav>

<div id="overlay"></div>