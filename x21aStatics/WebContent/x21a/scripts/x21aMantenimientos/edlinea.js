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
jQuery(function($){
	$("#GRID_edlinea").rup_grid({
		editable: true,
		
		url: "../usuario",
		hasMaint: true,
		headertitles: true, //tooltip en cabeceras
		pagerName: "pager",
		rowNum: "10",
		sortorder: "asc",
		sortname: "id",
		colNames: [ "id", "nombre", "apellido1", "apellido2", "ejie", "fechaAlta", "fechaBaja" ],
		colModel: [
		    //label: etiqueta del detalle
		    
			{ name: "id", index: "id", editable: true
				,validationrules:{required:true,digits:true}
				,key: true 
			},
			{ name: "nombre", index: "nombre", editable: true 
//				,validationrules:{required:true}
			},
			{ name: "apellido1", index: "apellido1", editable: true },
			{ name: "apellido2", index: "apellido2", editable: true },
			
			//Definición para combo normal
//			{ name: "ejie", index: "ejie", editable: true,
//				editoptions: { //Define parseo 
//					value:{"1":"Sí","0":"No"} 
//				}, 
//				formatter: "select", //Aplicar parseo
//				edittype: "select" //Aplica combo en detalle
//			},
			//Definición para rup_combo 
			{ name: "ejie", index: "ejie", editable: true,
				editoptions: {
					source : [
			   			{i18nCaption: "0", value:"0"},
			   			{i18nCaption: "1", value:"1"}
			   		],
			   		i18nId:'GRID_edlinea##ejie'
				},
				formatter: "rup_combo",
				rupType: "combo"
			},
			{ name: "fechaAlta",  
				index: "fechaAlta",
//				validationrules:{required:true, date:true},
				editable: true,
				rupType: "date",
				formatoptions:{newformat:"RupDate"}
			},
			{ name: "fechaBaja", 
				index: "fechaBaja", 
//				validationrules:{date:true},
				editable: true,
				rupType: "date",
				formatoptions:{newformat:"RupDate"}
			}
        ],
        readOnlyFields :  [ "id" ]
	});
	

	$("#edlinea").rup_maint({
		jQueryGrid: "GRID_edlinea",
		primaryKey: "id",
		modelObject: "Usuario",
		detailButtons: $.rup.maint.detailButtons.SAVE,
		searchForm: "searchForm",
		showMessages: true,
		onbeforeDetailShow: function(rowId){
			var fieldsArray = jqGrid[0].p.readOnlyFields,
				row = $(jqGrid).find("#"+rowId);
	        if (row.hasClass("addElement")){
	        	//NEW
	        	for (var i = 0; i < fieldsArray.length; i++) {
	        		field = row.find("#"+rowId+ "_" + fieldsArray[i]).removeAttr('readonly');
				}
	        } else {
	        	//EDIT
	           	for (var i = 0; i < fieldsArray.length; i++) {
	           		field = row.find("#"+rowId+ "_" + fieldsArray[i]).attr('readonly', true);
	           	}
	        }
		},
		validation:{
			rules:{
				"nombre":{required:true}
			}
		}
	});
	
	
	//Formulario de filtrado
	$('#ejie_search').rup_combo({
		source : [
		   {i18nCaption: "0", value:"0"},
		   {i18nCaption: "1", value:"1"}
		],
		i18nId: "GRID_edlinea##ejie",
		width: 120,
		blank: ""
	});
	
	$("#fechaAlta_search").rup_date();
	$("#fechaBaja_search").rup_date();
});