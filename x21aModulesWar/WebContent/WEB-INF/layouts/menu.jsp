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
  	<spring:url value="#" var="urlHashtag" htmlEscape=""/>
    <a class="navbar-brand" href="${urlHashtag}">Uda</a>
    <ul class="nav navbar-nav">
      <li class="nav-item dropdown">
        <!-- <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Componentes <span class="caret"></span></a> -->
        <a class="nav-link dropdown-toggle" href="${urlHashtag}" id="responsiveNavbarDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Componentes</a>
        <div class="dropdown-menu" aria-labelledby="responsiveNavbarDropdown">
        	<spring:url value="/patrones/feedback" context="x21aAppWar" var="urlFeedback" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlFeedback}"><i class="fa fa-check-square" aria-hidden="true"></i><spring:message code="feedback" /></a>
            <spring:url value="/patrones/tooltip" context="x21aAppWar" var="urlTooltip" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlTooltip}"><i class="fa fa-comment-o" aria-hidden="true"></i><spring:message code="tooltip" /></a>
            <spring:url value="/patrones/message" context="x21aAppWar" var="urlMessage" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlMessage}"><i class="fa fa-envelope" aria-hidden="true"></i><spring:message code="message" /></a>
            <spring:url value="/patrones/dialog" context="x21aAppWar" var="urlDialog" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlDialog}"><i class="fa fa-window-restore" aria-hidden="true"></i><spring:message code="dialog" /></a>
            <spring:url value="/patrones/progressbar" context="x21aAppWar" var="urlProgressbar" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlProgressbar}"><i class="fa fa-hourglass-half" aria-hidden="true"></i>Barra de progreso</a>
            <div class="dropdown-divider"></div>
            <spring:url value="/patrones/contextMenu" context="x21aAppWar" var="urlContextMenu" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlContextMenu}"><i class="fa fa-list-alt" aria-hidden="true"></i><spring:message code="contextMenu" /></a>
            <spring:url value="/patrones/button" context="x21aAppWar" var="urlButton" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlButton}"><i class="fa fa-hand-pointer-o" aria-hidden="true"></i><spring:message code="button" /></a>
            <spring:url value="/patrones/toolbar" context="x21aAppWar" var="urlToolbar" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlToolbar}"><i class="fa fa-wrench" aria-hidden="true"></i><spring:message code="toolbar" /></a>
            <div class="dropdown-divider"></div>
            <spring:url value="/patrones/accordion" context="x21aAppWar" var="urlAccordion" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlAccordion}"><i class="fa fa-list" aria-hidden="true"></i><spring:message code="accordion" /></a>

            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="${urlHashtag}">Pestañas</a>
              <div class="dropdown-menu menu-right" >
              	<spring:url value="/patrones/tabsStatic" context="x21aAppWar" var="urlTabsStatic" htmlEscape="true"/>
                <a class="dropdown-item" href="${urlTabsStatic}"><spring:message code="tabsStatic" /></a>
                <spring:url value="/patrones/tabsAjax" context="x21aAppWar" var="urlTabsAjax" htmlEscape="true"/>
                <a class="dropdown-item" href="${urlTabsAjax}"><spring:message code="tabsAjax" /></a>
                <spring:url value="/patrones/tabsMixto" context="x21aAppWar" var="urlTabsMixto" htmlEscape="true"/>
                <a class="dropdown-item" href="${urlTabsMixto}"><spring:message code="tabsMixto" /></a>
                <spring:url value="/patrones/maintTab" context="x21aAppWar"var="urlMaintTab" htmlEscape="true"/>
                <a class="dropdown-item" href="${urlMaintTab}"><spring:message code="maintTab" /></a>
                <spring:url value="/patrones/tabsScrollable" context="x21aAppWar"var="urlTabsScrollable" htmlEscape="true"/>
                <a class="dropdown-item" href="${urlTabsScrollable}"><spring:message code="tabsScrollable" /></a>
                
              </div>
            </div>

            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="${urlHashtag}">Wizard</a>
              <div class="dropdown-menu menu-right" >
              	<spring:url value="/patrones/wizard" context="x21aAppWar" var="urlWizard" htmlEscape="true"/>
                <a class="dropdown-item" href="${urlWizard}"><spring:message code="wizardA" /></a>
                <spring:url value="/patrones/wizard_includeFile" context="x21aAppWar" var="urlWizardIncFile" htmlEscape="true"/>
                <a class="dropdown-item" href="${urlWizardIncFile}"><spring:message code="wizardB" htmlEscape="true"/></a>
                <spring:url value="/patrones/wizard_jspInclude" context="x21aAppWar" var="urlWizardIncJsp" htmlEscape="true"/>
                <a class="dropdown-item" href="${urlWizardIncJsp}"><spring:message code="wizardC" htmlEscape="true"/></a>
                <spring:url value="/patrones/wizard_jstlImport" context="x21aAppWar" var="urlWizardJstlImport" htmlEscape="true"/>
                <a class="dropdown-item" href="${urlWizardJstlImport}"><spring:message code="wizardD" htmlEscape="true"/></a>
                <spring:url value="/patrones/wizard_dinamico" context="x21aAppWar" var="urlWizardDinamico" htmlEscape="true"/>
                <a class="dropdown-item" href="${urlWizardDinamico}"><spring:message code="wizardE" /></a>
              </div>
            </div>

            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="${urlHashtag}">Arbol</a>
              <div class="dropdown-menu menu-right" >
              	<spring:url value="/patrones/trees" context="x21aAppWar" var="urlTrees" htmlEscape="true"/>
                <a class="dropdown-item" href="${urlTrees}"><spring:message code="tree_multiple_configuraciones" /></a>
                <spring:url value="/patrones/treeDAD" context="x21aAppWar" var="urlTreesDAD" htmlEscape="true"/>
                <a class="dropdown-item" href="${urlTreesDAD}"><spring:message code="tree_multiple_d&d"/></a>
              </div>
            </div>

            <div class="dropdown-divider"></div>
            <spring:url value="/patrones/autocomplete" context="x21aAppWar" var="urlAutocomplete" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlAutocomplete}"><spring:message code="autocomplete" /></a>

            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#">Combo</a>
              <div class="dropdown-menu menu-right">
              	<spring:url value="/patrones/comboSimple" context="x21aAppWar" var="urlComboSimpe" htmlEscape="true"/>
                <a class="dropdown-item" href="${urlComboSimpe}"><spring:message code="comboSimple" /></a>
                <spring:url value="/patrones/comboEnlazadoSimple" context="x21aAppWar" var="urlComboEnlazadoSimple" htmlEscape="true"/>
                <a class="dropdown-item" href="${urlComboEnlazadoSimple}"><spring:message code="comboEnlazadoSimple" /></a>
                <spring:url value="/patrones/comboEnlazadoMultiple" context="x21aAppWar" var="urlComboEnlazadoMulti" htmlEscape="true"/>
                <a class="dropdown-item" href="urlurlComboEnlazadoMulti"><spring:message code="comboEnlazadoMulti" /></a>
                <spring:url value="/patrones/multicombo" context="x21aAppWar" var="urlMulticombo" htmlEscape="true"/>
                <a class="dropdown-item" href="${urlMulticombo}"><spring:message code="multicombo" /></a>
              </div>
            </div>

			<spring:url value="/patrones/slider" context="x21aAppWar" var="urlSlider" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlSlider}">Deslizador</a>
            <spring:url value="/patrones/date" context="x21aAppWar" var="urlDate" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlDate}"><i class="fa fa-calendar" aria-hidden="true"></i><spring:message code="date" /></a>
            <spring:url value="/patrones/form" context="x21aAppWar" var="urlForm" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlForm}"><spring:message code="form" /></a>
            <spring:url value="/patrones/time" context="x21aAppWar" var="urlTime" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlTime}"><i class="fa fa-clock-o" aria-hidden="true"></i><spring:message code="time" /></a>
            <spring:url value="/patrones/spinner" context="x21aAppWar" var="urlSpinner" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlSpinner}">Spinner</a>
            <spring:url value="/patrones/upload" context="x21aAppWar" var="urlUpload" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlUpload}"><spring:message code="upload" /></a>
            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="${urlHashtag}"><spring:message code="validate" /></a>
              <div class="dropdown-menu menu-right">
              	<spring:url value="/patrones/validate" context="x21aAppWar" var="urlValidate" htmlEscape="true"/>
              	<a class="dropdown-item" href="${urlValidate}">Configuración</a>
              	<spring:url value="/patrones/validateRules" context="x21aAppWar" var="urlValidateRules" htmlEscape="true"/>
                <a class="dropdown-item" href="${urlValidateRules}">Reglas de validación</a>
                
              </div>
            </div>
            
            <div class="dropdown-divider"></div>
            <spring:url value="/patrones/charts" context="x21aAppWar" var="urlCharts" htmlEscape="true"/>
            <a class="dropdown-item" href="${urlCharts}"><i class="fa fa-bar-chart" aria-hidden="true"></i><spring:message code="charts.charts" /></a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="${urlHashtag}" id="tableDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Tabla</a>
        <div class="dropdown-menu" aria-labelledby="tableDropdown">
          <spring:url value="/table/filtroSimple"  context="x21aAppWar" var="urlTFiltroSimple" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlTFiltroSimple}">Filtro simple</a>
          <spring:url value="/table/formEditAutogenerated" context="x21aAppWar" var="urlTFormEdAutogen" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlTFormEdAutogen}">Edición en formulario (autogenerado)</a>
          <spring:url value="/table/formEdit" context="x21aAppWar" var="urlTFormEd" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlTFormEd}">Edición en formulario</a>
          <spring:url value="/table/formEditMultiselection" context="x21aAppWar" var="urlTFormEdMulti" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlTFormEdMulti}">Edición en formulario con multiselección</a>
          <spring:url value="/table/inlineEditExcelMode" context="x21aAppWar" var="urlTInlineExcel" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlTInlineExcel}">Edición en línea (Modo Excel)</a>
          <spring:url value="/table/inlineEdit" context="x21aAppWar" var="urlTInline" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlTInline}">Edición en línea</a>
          <spring:url value="/table/inlineEditMultiselection" context="x21aAppWar"var="urlTInlineMulti" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlTInlineMulti}">Edición en línea con multiselección</a>
          <spring:url value="/table/grouping" context="x21aAppWar" var="urlTGrouping" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlTGrouping}">Tabla con agrupamiento</a>
          <spring:url value="/table/masterDetail" context="x21aAppWar" var="urlTMasterDetail" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlTMasterDetail}">Maestro - Detalle</a>
          <spring:url value="/table/tableLoadOnStartUp" context="x21aAppWar" var="urlTLoadOnStartup" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlTLoadOnStartup}">LoadOnStartUp = false</a>
          <spring:url value="/table/dialog" context="x21aAppWar" var="urlTDialog" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlTDialog}">Tabla en diálogo</a>
          <spring:url value="/table/tableRadiobutton" context="x21aAppWar" var="urlTRadiobtn" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlTRadiobtn}">tableRadiobutton</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="gridResponsiveDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Grid responsive</a>
        <div class="dropdown-menu" aria-labelledby="gridResponsiveDropdown">
          <spring:url value="/bootstrap/stackedHorizontal" context="x21aAppWar" var=urlBsStackHorizontal"" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlBsStackHorizontal}">Stacked to horizontal</a>
          <spring:url value="/bootstrap/mobileDesktop" context="x21aAppWar" var="urlBsMobileDesktop" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlBsMobileDesktop}">Mobile and desktop</a>
          <spring:url value="/bootstrap/mobileTabletDesktop" context="x21aAppWar" var="urlBsMobileTabletDesktop" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlBsMobileTabletDesktop}">Mobile, Tablet and desktop</a>
          <spring:url value="/bootstrap/exampleForm" context="x21aAppWar" var="urlBsExampleForm" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlBsExampleForm}">Formulario ejemplo</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dashboardDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><spring:message code="dashboard" /></a>
        <div class="dropdown-menu" aria-labelledby="dashboardDropdown">
          <spring:url value="/dashboard/dashboardSimple" context="x21aAppWar" var="urlDashboardSimple" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlDashboardSimple}"><spring:message code="dashboard.simple.title" /></a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="styleGuideDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Guía de estilos</a>
        <div class="dropdown-menu" aria-labelledby="styleGuideDropdown">
          <spring:url value="/styleGuide" context="x21aAppWar" var="urlStyleGuide" htmlEscape="true"/>
          <a class="dropdown-item" href="${urlStyleGuide}">Guía de estilos</a>
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