/*!
 * Copyright 2011 E.J.I.E., S.A.
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
jQuery(document).ready(function () {

	jQuery("#boton").rup_button({});
	
	jQuery("#dropdownHtmlListButton").rup_button({
		dropdown:{
			dropdownListId:"dropdownHtmlList"
		}
		
	});
	
	$("#dropdownElem1").on("click", function(){
		alert("Seleccionado elemento 1");
	});
	
	
	// Dropdown dialog
	
	jQuery("#dropdownDialogButton").rup_button({
		dropdown:{
			dropdownDialog: "dropdownDialog",
			dropdownDialogConfig:{
				title:"<span class='rup-icon rup-icon-filter'/>Administración de filtros",
				width:"380px",
				buttons: [{
					text: "Guardar",
					click: function () { 
					}
				},
				{
					text: "Aceptar",
					click: function () { 
					}
				},
				{
					text: "Eliminar",
					click: function () { 
					}
				},
				{
					text: "Cancelar",
					click: function () { 
						$("#dropdownDialog").dialog("close"); 
					},
					btnType: $.rup.dialog.LINK
				}
				]	
			}
		}
	});
	
	var options_ejie_combo = {
			source : [
			   {label:"Si", value:"0"},
			   {label:"No", value:"1"}
			],
			width: 120,
			blank: ""
		};

	
	jQuery("#dropdownButton-combo").rup_combo(options_ejie_combo);
	
});