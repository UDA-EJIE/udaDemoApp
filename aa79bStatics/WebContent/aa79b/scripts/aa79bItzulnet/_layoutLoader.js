/*!
 * Copyright 2012 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 *      http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 * el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */

jQuery(document).ready(function(){

	// Evitar conflictos entre Bootstrap y jQueryUI
	$.fn.bootstrapBtn = $.fn.button.noConflict();

	//logo
	$("#rup_dept_logo").attr("src", $.rup.APP_STATICS + "/images/dept_logo_" + $.rup.lang + ".gif");
		
	//rastro de migas
	$("#aa79bItzulnetWar_migas").rup_breadCrumb({
		breadCrumb: {
			logOutUrl : "/aa73bItzulnetWar/logout",
			"infoLegal" : {
				"i18nCaption" : "avisolegal"
			},
			"administracion" : {
				"i18nCaption" : "administracion",
				"datosmaestros" : {
					"i18nCaption" : "mantenimientoTablasMaestras",
					"tiporelevancia" : {"i18nCaption" : "tiporelevancia"},
					"motivosrechazo" : {"i18nCaption" : "motivosRechazo"},
					"motivosanulacion" : {"i18nCaption" : "motivosAnulacion"},
					"tiposrevision" : {"i18nCaption" : "tiposrevision"},
					"tiposdocumento" : {"i18nCaption" : "tiposdocumento"},
					"tipostarea" : {"i18nCaption" : "tipostarea"},
					"libroregistro" : {"i18nCaption" : "libroregistro"},
					"modosinterpretacion" : {"i18nCaption" : "modosinterpretacion"},
					"formatosfichero" : {"i18nCaption" : "formatosfichero"},
					"tiposinterpretacion" : {"i18nCaption" : "tiposinterpretacion"},
					"metadatosbusqueda":{"i18nCaption":"metadatosBusqueda"},
					"plantillas":{"i18nCaption":"plantillas"},
					"nivelesdecalidad" : {"i18nCaption" : "nivelesDeCalidad"}
				},
				"configuraciontarifas":{
					"i18nCaption" : "configuraciontarifas",
					"ordenprecios" : {"i18nCaption" : "ordenprecios"},
					"excepcionfacturacion" : {"i18nCaption" : "excepcionfacturacion"}
				},
				"calendariopersonal":{
					"i18nCaption" : "calendariopersonal"
				},
				"empresasproveedoras":{
					"i18nCaption" : "lotes"
				},
				"calendarioservicio":{
					"i18nCaption" : "calendarioServicio",					
					"horariosatencion":{"i18nCaption" : "horariosatencion"},
					"horarioslaborales":{"i18nCaption" : "horarioslaborales"}
				},
				"ayudaaplicacion":{
					"i18nCaption" : "ayudaAplicacion"
				}
				, "datosBasicos" : {"i18nCaption" : "datosBasicos"}
				, "grupostrabajo" : {"i18nCaption" : "gruposTrabajo"}
				, "configuracionauditoria" : {
					"i18nCaption" : "configuracionAuditoria",
					"pesosvaloracioncalidad":{"i18nCaption" : "pesosValoracionCalidad"},
					"seccionescontroldecalidad":{"i18nCaption" : "seccionesControlDeCalidad"},
					"camposcontrolcalidad":{"i18nCaption" : "camposControlCalidad"}
				}
	 	
			},
			"tramitacionexpedientes":{
				"i18nCaption":"tramitacionexpedientes",
				"gestionexpedientes":{
					"i18nCaption" : "gestionexpedientes",
					"estudioexpedientes":{
						"i18nCaption" : "estudioexpedientes"
					},
					"altaexpedientes":{
						"i18nCaption" : "altaexpedientes"
					},
					"gestionanulaciones":{
						"i18nCaption" : "gestionanulaciones"
					}
				}
			    ,"otrostrabajos": {"i18nCaption" : "otrostrabajos"}
				,"planificacion":{
					"i18nCaption" : "planificacion",
					"planificacionexpediente":{
						"i18nCaption" : "planificacionexpediente"
					},
					
				}
				,"planificacionCarga":{
					"i18nCaption" : "planificacion",
					"cargatrabajo":{
						"i18nCaption" : "cargatrabajo"
					},
					"tareas":{
						"i18nCaption" : "asignadoProveedores"
					}
				}
				,"facturacionypagos":{
					"i18nCaption" : "facturacionypagos",
					"revisiondatosfacturacion":{
						"i18nCaption" : "revisiondatosfacturacion"
					},
					"consultafacturas":{
						"i18nCaption" : "consultafacturas"
					},
					"emisiondefacturas":{
						"i18nCaption" : "emisiondefacturas"
					},
					"pagamentos":{
						"i18nCaption" : "pagamentos"
					},
					"albaranes":{
						"i18nCaption" : "consultaalbaranes"
					}

				}
			},
			"servicios":{
				"i18nCaption":"servicios",
				"trataFicherosConfi":{
					"i18nCaption" : "trataFicherosConfi",
					"encripFicheros":{
						"i18nCaption" : "encripFicheros"
					},
					"ficherosConfidenciales":{
						"i18nCaption" : "ficherosConfidencialesAsoc"
					}
				},
				"actualizacionalbaran":{
					"i18nCaption" : "servicioActializarAlbaran"
				},
				"actDatosFacturacion":{
					"i18nCaption" : "actDatosFacturacion"
				},
				"cambioEstadoExpediente":{
					"i18nCaption" : "cambioEstadoExpediente"
				},
				"requerirsubsanacionexpedientes":{
					"i18nCaption" : "requerirsubsanacionexpedientes"
				},
				"clonacionexpedientes":{
					"i18nCaption" : "clonacionexpedientes"
				}
			},
			"consultas":{
				"i18nCaption":"consultas",
				"consultaexpedientes":{
					"i18nCaption" : "consultaexpedientes"
				},
				"consultaplanificacionexpedientes":{
					"i18nCaption" : "consultaplanificacionexpedientes"
				},
				"consultaregistroacciones":{
					"i18nCaption" : "consultaregistroacciones"
				}
			},
			"informes":{
				"i18nCaption":"informes"
			},
			"auditoria":{
				"i18nCaption":"auditoria"
			},
			"pruebapid":{
				"i18nCaption":"pifPid"
			}
		}	
	});

	var menuDir = window.location.pathname;
	$("#menuPrincipal .nav-item.dropdown.active").removeClass('active');
	if(menuDir.search("pruebas")>0){
		$("#menuPrincipal a.nav-link[id='pruebas']").parent().addClass('active');
	}else if (menuDir.search("administracion")>0) {
		$("#menuPrincipal a.nav-link[id='responsiveNavbarDropdown']").parent().addClass('active');
	}else if(menuDir.search("tramitacionexpedientes")>0){
		$("#menuPrincipal a.nav-link[id='responsiveNavbarDropdownTramitacion']").parent().addClass('active');
	}else if(menuDir.search("servicios")>0){
		$("#menuPrincipal a.nav-link[id='responsiveNavbarDropdownServicios']").parent().addClass('active');
	}else if(menuDir.search("consultas")>0){
		$("#menuPrincipal a.nav-link[id='responsiveNavbarDropdownConsultas']").parent().addClass('active');
	}else if(menuDir.search("informes")>0){
		$("#menuPrincipal a.nav-link[id='responsiveNavbarDropdownInformes']").parent().addClass('active');
	}else if(menuDir.search("informes")>0){
		$("#menuPrincipal a.nav-link[id='responsiveNavbarDropdownAuditoria']").parent().addClass('active');
	}else{
		//botón de inicio
		$("#menuPrincipal .nav-item.dropdown :first").parent().addClass('active');
	}
	
	
	//idioma
	$("#aa79bItzulnetWar_language").rup_language({languages: $.rup.AVAILABLE_LANGS_ARRAY, modo: "default"}); //$.rup.AVAILABLE_LANGS_ARRAY
	
	//menu
	$("#aa79bItzulnetWar_menu").rup_menu({
		display: ($.rup.LAYOUT === "vertical" ? 'vertical' : 'horizontal')
	});
	if ($.rup.LAYOUT === "mixto") {
		$("#aa79bItzulnetWar_menu_mixto").rup_menu({
			display: 'vertical'
		});
	}
	
	//NAVBAR Menu
	if($("#navbarResponsive").length){
		$("#navbarResponsive").rup_navbar();
	}
	
	jQuery('input:checkbox[data-switch="true"]').each(function(index, element){
		jQuery(element)
		.bootstrapSwitch()
		.bootstrapSwitch('setSizeClass', 'switch-small')
		.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
		.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
		});
	
	//NAVBAR Menu
	//$.fn.rup_navbar();
	
	$(window).off('scroll'); // FIXME - END: Sticky navbar en aplicativos embebidos en portal
	
	//pie
	$(".footer [title]").rup_tooltip();
	
	/* MOSTRAR EN PANTALLA */
	$('.aa79b-content').addClass('in');
	
	$("#botonAyuda").on('click', function(e){
		var url = "/aa79bItzulnetWar/administracion/ayudaaplicacion/generarPdf";
        window.open(url);
	});
	
	/*$(document)
	    .ajaxStart(function() {
	        bloquearPantalla();
	    })
	    .ajaxStop(function() {
	       desbloquearPantalla();
	    });*/

	$('.rup-feedback').each(function(){
		$(this).rup_feedback('close');
	});

	
	/*STICKY HEADER*/
	$(document).scroll(function(){
		var sticky = $('.rup-navbar.navbar'),
		scroll = $(window).scrollTop();
		if (scroll >= 79) {
			sticky.addClass('rup-navbar-sticky');
		}
		else {
			sticky.removeClass('rup-navbar-sticky');
		}
	  });
		/*END STICKY HEADER*/
	
	/*Cierre de botones desplegables de toolbar al pulsar fuera*/
	$(window).on('click',function(){
		$('.rup-button').not('[id*="report"]').removeClass('rup-mbutton-open');
		$('.rup-mbutton-container').not('[id*="report"]').removeClass('rup-mbutton-open').hide();
		});

});
