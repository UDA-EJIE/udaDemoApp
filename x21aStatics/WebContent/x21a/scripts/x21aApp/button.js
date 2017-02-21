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

	$("#btnDefault").rup_button({});

    // Botón con icono HTML
	$("#btnIconHtml").rup_button();

    // Botón con icono JS
	$("#btnIconJs").rup_button({
      iconCss: "fa fa-cog"
    });

    // Botón Rwd HTML Sd
	$("#btnRwdHtmlSm").rup_button();
	$("#btnRwdHtmlMd").rup_button();
	$("#btnRwdJsSm").rup_button({
      iconCss: "fa fa-cog",
      labelCss: "hidden-sm-down"
    });
	$("#btnRwdJsMd").rup_button({
      iconCss: "fa fa-cog",
      labelCss: "hidden-md-down"
    });

    // MButton

    $("#btnMButton").rup_button({});

    $("#fabButton").rup_button({
      // fab: true
    });

	$("#fabButtonLayer").rup_button({
      // fab: true
    });

	$("#fabButtonFixed").rup_button({
      // fab: true
    });

	$("#btnDropdownList").rup_button({
  		dropdown:{
  			dropdownListId:"dropdownHtmlList"
  		}
  	});

    // Eventos click
	$("#btnClickJQuery").rup_button().on("click", function(){
      $.rup_messages("msgOK", {
        title: "Evento Click",
        message: "Se ha capturado el evento click mediante un handler de jQuery."
      });
    });
	$("#btnClickRup").rup_button({
      iconCss: "fa fa-cog",
      click: function(){
        $.rup_messages("msgOK", {
          title: "Evento Click",
          message: "Se ha capturado el evento click mediante un handler especificado en la propiedad click."
        });
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