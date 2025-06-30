<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
 
<nav class="rup-navbar navbar">
  <button type="button" class="navbar-toggler hidden-lg-up"
	type="button" data-toggle="collapse"
	aria-controls="navbarResponsive" aria-expanded="false"
	aria-label="Toggle navigation"></button>
  <div id="navbarResponsive" class="collapse navbar-toggleable-md">
    <ul class="nav navbar-nav" id="menuPrincipal">
      <li class="nav-item dropdown active">
			<a class="nav-link" href="/aa79bItzulnetWar">
				<i class="fa fa-home" aria-hidden="true"></i>
				<spring:message code="inicio" />
			</a>
		</li>
	  <sec:authorize access="hasAnyRole('ROLE_AA79B_TECNICO_GESTOR', 'ROLE_AA79B_TRADUCTOR', 'ROLE_AA79B_INTERPRETE', 'ROLE_AA79B_ASIGNADOR')">
      <li class="nav-item dropdown">
        <!-- <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Componentes <span class="caret"></span></a> -->
        <a class="nav-link dropdown-toggle" href="#" id="responsiveNavbarDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        	<spring:message code="menu.administracion" />
        </a>
        <div class="dropdown-menu" aria-labelledby="responsiveNavbarDropdown">
            <sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#"><spring:message code="menu.gestionEntidades" /></a>
              <div class="dropdown-menu menu-right" >
                <a class="dropdown-item" href="${appConfiguration['x54j.url']}&destino=busquedaDepartamento" target="_blank" ><spring:message code="menu.departamentos" /></a>
                <a class="dropdown-item" href="${appConfiguration['x54j.url']}&destino=busquedaEntidad" target="_blank"><spring:message code="menu.entidadesConveniadas" /></a>
                <a class="dropdown-item" href="${appConfiguration['x54u.url']}/infraestructura/x54uEmpresa/maint?aplic=AA79B&locale=eu" target="_blank"><spring:message code="menu.empresas" /></a>               
			</div>
            </div>
            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#"><spring:message code="menu.gestionPersonas" /></a>
              <div class="dropdown-menu menu-right" >
                <a class="dropdown-item"  href="${appConfiguration['x54j.url']}&destino=busquedaTrabajador" target="_blank" ><spring:message code="menu.personalTrabajador" /></a>
                <a class="dropdown-item" href="${appConfiguration['x54u.url']}/x54uconsultaroles/maint?aplic=AA79B&locale=eu" target="_blank"><spring:message code="menu.configuracionTrabajadores" /></a>
                
              </div>
            </div>
           </sec:authorize>
           <sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
           	<div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#"><spring:message code="menu.mantenimientoTablasMaestras" /></a>
              <div class="dropdown-menu menu-right" >
                <a class="dropdown-item"  href="/aa79bItzulnetWar/administracion/datosmaestros/motivosrechazo/maint" ><spring:message code="menu.motivosRechazo" /></a>
                <a class="dropdown-item"  href="/aa79bItzulnetWar/administracion/datosmaestros/motivosanulacion/maint"><spring:message code="menu.motivosAnulacion" /></a>
                <a class="dropdown-item"  href="/aa79bItzulnetWar/administracion/datosmaestros/modosinterpretacion/maint"><spring:message code="menu.modosInterpretacion" /></a>
                <a class="dropdown-item"  href="/aa79bItzulnetWar/administracion/datosmaestros/tiposinterpretacion/maint"><spring:message code="menu.tiposInterpretacion" /></a>
                <a class="dropdown-item"  href="/aa79bItzulnetWar/administracion/datosmaestros/tiporelevancia/maint" ><spring:message code="menu.tiposRelevancia" /></a>
                <a class="dropdown-item"  href="/aa79bItzulnetWar/administracion/datosmaestros/tipostarea/maint"><spring:message code="menu.tiposTarea" /></a>
                <a class="dropdown-item"  href="/aa79bItzulnetWar/administracion/datosmaestros/formatosfichero/maint"><spring:message code="menu.formatosFichero" /></a>
                <a class="dropdown-item" href="/aa79bItzulnetWar/administracion/datosmaestros/tiposdocumento/maint"><spring:message code="menu.tiposDocumento" /></a>
                <a class="dropdown-item"  href="/aa79bItzulnetWar/administracion/datosmaestros/tiposrevision/maint" ><spring:message code="menu.tiposRevision" /></a>
                <a class="dropdown-item"  href="/aa79bItzulnetWar/administracion/datosmaestros/metadatosbusqueda/maint" ><spring:message code="menu.metadatosBusqueda" /></a>
                <a class="dropdown-item"  href="/aa79bItzulnetWar/administracion/datosmaestros/plantillas/maint" ><spring:message code="menu.plantillas" /></a>
                <a class="dropdown-item"  href="/aa79bItzulnetWar/administracion/datosmaestros/nivelesdecalidad/maint"><spring:message code="menu.nivelesDeCalidad" /></a>
              </div>
            </div>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
            <a class="dropdown-item" href="/aa79bItzulnetWar/administracion/grupostrabajo/maint">
            	<spring:message code="menu.gestionGruposTrabajo" />
           	</a>
           	</sec:authorize>
           	<a class="dropdown-item" href="/aa79bItzulnetWar/administracion/calendariopersonal/maint">
            	<spring:message code="menu.calendarioPersonal" />
           	</a>
           	<sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
           	<a class="dropdown-item" href="/aa79bItzulnetWar/administracion/calendarioservicio/maint">
            	<spring:message code="menu.calendarioServicio" />
           	</a>
           	</sec:authorize>
           	<!-- div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#"><spring:message code="menu.calendarioServicio" /></a>
              <div class="dropdown-menu menu-right" >
                <a class="dropdown-item"  href="/aa79bItzulnetWar/administracion/calendarioservicio/calendarioservicio/maint" ><spring:message code="menu.calendarioServicio" /></a>
                <a class="dropdown-item" href="/aa79bItzulnetWar/administracion/calendarioservicio/horarioslaborales/maint" ><spring:message code="menu.horarioLaboralServicio" /></a>
                <a class="dropdown-item" href="/aa79bItzulnetWar/administracion/calendarioservicio/horariosatencion/maint"><spring:message code="menu.horarioAtencionPublico" /></a>
              </div>
            </div-->
            <sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
            <a class="dropdown-item" href="/aa79bItzulnetWar/administracion/empresasproveedoras/maint">
            	<spring:message code="menu.configuracionLotes" />
           	</a>
           	</sec:authorize>
           	<sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
           	<div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#"><spring:message code="menu.configuracionTarifas" /></a>
              <div class="dropdown-menu menu-right" >
                <a class="dropdown-item"  href="/aa79bItzulnetWar/administracion/configuraciontarifas/ordenprecios/maint" ><spring:message code="menu.ordenPreciosPublicos" /></a>
                <a class="dropdown-item" href="/aa79bItzulnetWar/administracion/configuraciontarifas/excepcionfacturacion/maint" ><spring:message code="menu.datosFacturacionEntidad" /></a>
              </div>
            </div>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
           	<div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#"><spring:message code="menu.configuracionAuditoria" /></a>
              <div class="dropdown-menu menu-right" >
                <a class="dropdown-item"  href="/aa79bItzulnetWar/administracion/configuracionauditoria/pesosvaloracioncalidad/maint" ><spring:message code="menu.pesosValoracionCalidad" /></a>
                <a class="dropdown-item"  href="/aa79bItzulnetWar/administracion/configuracionauditoria/seccionescontroldecalidad/maint" ><spring:message code="menu.seccionesControlDeCalidad" /></a>
                <a class="dropdown-item"  href="/aa79bItzulnetWar/administracion/configuracionauditoria/camposcontrolcalidad/maint" ><spring:message code="menu.camposControlCalidad" /></a>
              </div>
            </div>
            </sec:authorize>
            
           	<a class="dropdown-item" href="/aa79bItzulnetWar/administracion/ayudaaplicacion/maint">
            	<spring:message code="menu.edicionAyuda" />
           	</a>
           	<sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
           	 <a class="dropdown-item" href="/aa79bItzulnetWar/administracion/datosBasicos/maint">
            	<spring:message code="menu.datosBasicos" />
           	</a>
           	</sec:authorize>
           	<sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
	           	<a class="dropdown-item" href="/w05uUdaWar/parametrizacion/dir3PorAplicacion/maint?aplicacion=20" target="_blank">
	            	<spring:message code="menu.gestionarDir3" />
	           	</a>
           	</sec:authorize>
        </div>
      </li>
      <!-- TRAMITACION EXPEDIENTES -->
      <sec:authorize access="hasAnyRole('ROLE_AA79B_TECNICO_GESTOR', 'ROLE_AA79B_ASIGNADOR', 'ROLE_AA79B_TRADUCTOR', 'ROLE_AA79B_INTERPRETE')">
      <li class="nav-item dropdown">
	       <!-- <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Componentes <span class="caret"></span></a> -->
	       <a class="nav-link dropdown-toggle" href="#" id="responsiveNavbarDropdownTramitacion" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	       	<spring:message code="menu.tramitacionExpedientes" />
	       </a>
	       <div class="dropdown-menu" aria-labelledby="responsiveNavbarDropdown">
	       		<sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
		            <div class="dropdown-submenu" >
			             <a class="dropdown-item dropdown-toggle" href="#"><spring:message code="menu.gestionExpedientes" /></a>
			             <div class="dropdown-menu menu-right" >
				             <a class="dropdown-item" href="/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/altaexpedientes/maint"  ><spring:message code="menu.altaExpediente" /></a>
				             <a class="dropdown-item" href="/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/maint"  ><spring:message code="menu.estudioExpedientes" /></a>
				             <a class="dropdown-item" href="/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/gestionanulaciones/maint"  ><spring:message code="menu.gestionAnulaciones" /></a>              
		            	</div>
		            </div>
	            </sec:authorize>
	            <sec:authorize access="hasAnyRole('ROLE_AA79B_TECNICO_GESTOR', 'ROLE_AA79B_ASIGNADOR')">
	            <a class="dropdown-item"  href="/aa79bItzulnetWar/tramitacionexpedientes/otrostrabajos/maint" ><spring:message code="menu.gestionOtrosTrabajos" /></a>
	            </sec:authorize>
	            <div class="dropdown-submenu" >
	            	<sec:authorize access="hasAnyRole('ROLE_AA79B_ASIGNADOR', 'ROLE_AA79B_TRADUCTOR', 'ROLE_AA79B_INTERPRETE')">
			             <a class="dropdown-item dropdown-toggle" href="#"><spring:message code="menu.planificacion" /></a>
			             <div class="dropdown-menu menu-right" >
				             <sec:authorize access="hasRole('ROLE_AA79B_ASIGNADOR')">
				             	<a class="dropdown-item" href="/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/general/maint"  ><spring:message code="menu.planificacionExpediente" /></a>
				             </sec:authorize>
				             <sec:authorize access="hasAnyRole('ROLE_AA79B_ASIGNADOR', 'ROLE_AA79B_TRADUCTOR', 'ROLE_AA79B_INTERPRETE')">
		             			<a class="dropdown-item" href="/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/maint" target="_blank"  ><spring:message code="menu.cargaTrabajo" /></a>
		             		</sec:authorize>
		             		<sec:authorize access="hasRole('ROLE_AA79B_ASIGNADOR')">
		             			<a class="dropdown-item" href="/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/maint" target="_blank"  ><spring:message code="menu.asignadoProveedores" /></a>
		             		</sec:authorize>
		            	</div>
					</sec:authorize>
	            </div>
	            <sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
		            <div class="dropdown-submenu" >
			             <a class="dropdown-item dropdown-toggle" href="#"><spring:message code="menu.facturacionYPagos" /></a>
			             <div class="dropdown-menu menu-right" >
				             	<a class="dropdown-item" href="/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion/busquedarevision/maint"  ><spring:message code="menu.revisiondatosfacturacion" /></a>
				             	<a class="dropdown-item" href="/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/maint"  ><spring:message code="menu.emisiondefacturas" /></a>
				             	<a class="dropdown-item" href="/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/consultafacturas/maint"  ><spring:message code="menu.consultaFacturas" /></a>
								<a class="dropdown-item" href="/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/pagamentos/maint"   ><spring:message code="menu.PagoProveedores" /></a>
								<a class="dropdown-item" href="/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/albaranes/maint"><spring:message code="menu.facturacion.albaranes" /></a>
		            	</div>
		            </div>
	            </sec:authorize>
            </div>
       </li>
       </sec:authorize>
       <!-- SERVICIOS --> 
       <li class="nav-item dropdown">
	       <!-- <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Componentes <span class="caret"></span></a> -->
	       <a class="nav-link dropdown-toggle" href="#" id="responsiveNavbarDropdownServicios" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	       	<spring:message code="menu.servicios" />
	       </a>
	       <div class="dropdown-menu" aria-labelledby="responsiveNavbarDropdown">
       		   <div class="dropdown-submenu" >
       		   	   <a class="dropdown-item dropdown-toggle" href="#"><spring:message code="menu.trataFicherosConfi"/></a>
		           <div class="dropdown-menu menu-right" >
			       	   <a class="dropdown-item" href="/aa79bItzulnetWar/servicios/trataFicherosConfi/encripFicheros/maint">
			           		<spring:message code="menu.encripFicheros" />
			           </a>
			           <sec:authorize access="hasAnyRole('ROLE_AA79B_TECNICO_GESTOR', 'ROLE_AA79B_TRADUCTOR')">
				           <a class="dropdown-item" href="/aa79bItzulnetWar/servicios/trataFicherosConfi/ficherosConfidenciales/maint">
				           		<spring:message code="menu.ficherosConfidenciales" />
				           </a>
			           </sec:authorize>
			          
		           </div>
		       </div>
		       <sec:authorize access="hasRole('ROLE_AA79B_ASIGNADOR')">
	           <a class="dropdown-item" href="/aa79bItzulnetWar/servicios/requerirsubsanacionexpedientes/maint">
	           		<spring:message code="menu.requerirSub" />
	           </a>
	           </sec:authorize>
		       <sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
	           <a class="dropdown-item" href="/aa79bItzulnetWar/servicios/actualizacionalbaran/maint">
	           		<spring:message code="menu.actualizacionAlbaran" />
	           </a>
	            <sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
				           <a class="dropdown-item" href="/aa79bItzulnetWar/servicios/actDatosFacturacion/maint">
				           		<spring:message code="menu.actualizacionDatosFacturacion" />
				           </a>
			           </sec:authorize>
			           <sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
				           <a class="dropdown-item" href="/aa79bItzulnetWar/servicios/cambioEstadoExpediente/maint">
				           		<spring:message code="menu.cambioEstadoExpediente" />
				           </a>
			           </sec:authorize>
	           <a class="dropdown-item" href="/aa79bItzulnetWar/servicios/clonacionexpedientes/maint"><spring:message code="menu.clonacionExpedientes" /></a>
	           </sec:authorize>
           </div>
       </li>
       <!-- CONSULTAS --> 
       
       <li class="nav-item dropdown">
	       <a class="nav-link dropdown-toggle" href="#" id="responsiveNavbarDropdownConsultas" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	       	<spring:message code="menu.consultas" />
	       </a>
	       <div class="dropdown-menu" aria-labelledby="responsiveNavbarDropdown">
	       	<sec:authorize access="hasAnyRole('ROLE_AA79B_TECNICO_GESTOR', 'ROLE_AA79B_TRADUCTOR', 'ROLE_AA79B_INTERPRETE')">
            <a class="dropdown-item" href="/aa79bItzulnetWar/consultas/consultaexpedientes/maint"><spring:message code="menu.consultaExpedientes" /></a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_AA79B_ASIGNADOR')">
            <a class="dropdown-item" href="/aa79bItzulnetWar/consultas/consultaplanificacionexpedientes/maint"><spring:message code="menu.consultaPlanificacionExpedientes" /></a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
            <a class="dropdown-item" href="/aa79bItzulnetWar/consultas/consultaregistroacciones/maint"><spring:message code="menu.consultaRegistroAcciones" /></a>
            </sec:authorize>
           </div>
       </li>
       <!-- INFORMES --> 
       <li class="nav-item dropdown">
	       <a class="nav-link dropdown-toggle" href="/aa79bItzulnetWar/informes/maint" id="responsiveNavbarDropdownInformes" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	       	<spring:message code="menu.informes" />
	       </a>
	       <div class="dropdown-menu" aria-labelledby="responsiveNavbarDropdown">
           		<a class="dropdown-item" href="/aa79bItzulnetWar/informes/maint">
            		<spring:message code="menu.informes" />
            	</a>
           </div>
       </li>
       <!-- AUDITORÍA --> 
       <li class="nav-item dropdown">
	       <a class="nav-link dropdown-toggle" href="/aa79bItzulnetWar/auditoria/maint" id="responsiveNavbarDropdownAuditoria" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	       	<spring:message code="menu.auditoria" />
	       </a>
	       <div class="dropdown-menu" aria-labelledby="responsiveNavbarDropdown">
           		<a class="dropdown-item" href="/aa79bItzulnetWar/auditoria/maint">
            		<spring:message code="menu.auditoria" />
            	</a>
           </div>
       </li>
       
       <!-- PRUEBAS -->
       <!-- <li class="nav-item dropdown">
	       <a class="nav-link dropdown-toggle" href="#" id="pruebas" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	       		Pruebas
	       </a>
	       <div class="dropdown-menu" aria-labelledby="responsiveNavbarDropdown">
	            <div class="dropdown-submenu" >
		             <a class="dropdown-item dropdown-toggle" href="#">Pruebas</a>
		             <div class="dropdown-menu menu-right" >
		                <a class="dropdown-item" href="/aa79bItzulnetWar/pruebas/maint">
				 			<spring:message code="menu.pifpid" />, Encriptar, Firmar
				 		</a>              
		               <a class="dropdown-item" href="/aa79bItzulnetWar/pruebas/nora/maint">
				 			Nora
				 		</a>        
				 		<a class="dropdown-item" href="/aa79bItzulnetWar/pruebas/capas/maint">
				 			Capas
				 		</a>           
	            	</div>
	            </div>
            </div>
       </li>-->
      </sec:authorize>
    </ul>
    <ul class="nav navbar-nav float-md-right rup-nav-tools">
      <!--  <li class="nav-item">
        <a class="nav-link rup-nav-tool-icon" href="#" id="aa79bItzulnetWar_language" data-toggle="dropdown"><i class="fa fa-globe" aria-hidden="true"></i><span data-rup-lang-current=""></span></a>
			<div class="dropdown-menu" aria-labelledby="aa79bItzulnetWar_language">
        </div>
      </li>-->
      <!-- <li class="nav-item">
        <a class="nav-link rup-nav-tool-icon" href="#"><i class="fa fa-cog " aria-hidden="true"></i></a>
      </li>-->
      <sec:authorize access="hasAnyRole('ROLE_AA79B_TECNICO_GESTOR', 'ROLE_AA79B_TRADUCTOR','ROLE_AA79B_INTERPRETE', 'ROLE_AA79B_ASIGNADOR')">
      <li class="nav-item">
        <a class="nav-link rup-nav-user rup-nav-tool-icon" href="#"><i class="fa fa-user-circle-o " aria-hidden="true"></i>${xlnetAuthenticationProvider.userCredentials.fullName}</a>
      </li>
      </sec:authorize>
      <li class="nav-item">
	  	<a class="nav-link rup-nav-tool-icon" id="botonAyuda" href="javascript:void(null)" title="<spring:message code="language.ayuda" />">
			<i class="fa fa-question " aria-hidden="true"></i>
		</a>
	  </li>
      <li class="nav-item">
			<a class="nav-link rup-nav-user rup-nav-tool-icon" href="/aa79bItzulnetWar/logout">
				<i class="fa fa-sign-out " aria-hidden="true"></i>
			</a>
		</li>
    </ul>
  </div>
</nav>

<div id="overlay"></div>
