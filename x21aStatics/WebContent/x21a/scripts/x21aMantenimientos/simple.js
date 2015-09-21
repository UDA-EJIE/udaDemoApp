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
	$("#GRID_simple").rup_grid({
		url: "../usuario",
		hasMaint: true,
		headertitles: true, //tooltip en cabeceras
		pagerName: "pager",
		rowNum: "10",
//		fluidBaseLayer: "#simple",
		sortorder: "asc",
		sortname: "id",
		colNames: [ "id", "nombre", "apellido1", "apellido2", "ejie", "fechaAlta", "fechaBaja" ],
		colModel: [
		    //label: etiqueta del detalle
		    
			{ name: "id", index: "id", editable: true, key: true
				,validationrules:{required:true,digits:true}
			},
			{ name: "nombre", index: "nombre", editable: true
				,validationrules:{required:true}
			},
			{ name: "apellido1", index: "apellido1", editable: true, classes:'ui-ellipsis' },
			{ name: "apellido2", index: "apellido2", editable: true },
			
//			//Definición para combo normal
//			{ name: "ejie", index: "ejie", editable: true,
//				editoptions: { //Define parseo 
//					value:{"1":"Sí","0":"No"} 
//				}, 
//				formatter: "select", //Aplicar parseo
//				edittype: "select" //Aplica combo en detalle
//			},
//			Definición para rup_combo 
			{ name: "ejie", index: "ejie", editable: true,
				editoptions: {
					source : [
					   {i18nCaption: "0", value:"0"},
					   {i18nCaption: "1", value:"1"}
					]
				},
				formatter: "rup_combo",
				rupType: "combo"
			},
// 			PRUEBA DE CARGA DE COMBOS DEPENDIENTES LOCALES			
//			{ name: "tipo", index: "tipo", editable: true,
//				editoptions: {
//					i18nId:"GRID_simple##tipo",
//					parent: [ "ejie" ],
//					
//					source : {
//					   "0":[
//					         {i18nCaption: "0A", value:"0A"},
//					         {i18nCaption: "0B", value:"0B"}],
//					   "1":[
//							   {i18nCaption: "1A", value:"1A"},
//							   {i18nCaption: "1B", value:"1B"}]
//					}
//				},
//				formatter: "rup_combo",
//				rupType: "combo"
//			},
//			{ name: "subtipo", index: "subtipo", editable: true,
//				editoptions: {
//					i18nId:"GRID_simple##subtipo",
//					parent: [ "ejie","tipo" ],
//					source : {
//					   "0##0A":[
//					         {i18nCaption: "0_0A_A", value:"0_0A_A"},
//					         {i18nCaption: "0_0A_B", value:"0_0A_B"}],
//					   "0##0B":[
//					         {i18nCaption: "0_0B_A", value:"0_0B_A"},
//							 {i18nCaption: "0_0B_B", value:"0_0B_B"}],
//						 "1##0A":[
//							         {i18nCaption: "1_0A_A", value:"1_0A_A"},
//							         {i18nCaption: "1_0A_B", value:"1_0A_B"}],
//					"1##0B":[
//						         {i18nCaption: "1_0B_A", value:"1_0B_A"},
//								 {i18nCaption: "1_0B_B", value:"1_0B_B"}],
//								 "0##1A":[
//									         {i18nCaption: "0_1A_A", value:"0_1A_A"},
//									         {i18nCaption: "0_1A_B", value:"0_1A_B"}],
//									   "0##1B":[
//									         {i18nCaption: "0_1B_A", value:"0_1B_A"},
//											 {i18nCaption: "0_1B_B", value:"0_1B_B"}],
//										 "1##1A":[
//											         {i18nCaption: "1_1A_A", value:"1_1A_A"},
//											         {i18nCaption: "1_1A_B", value:"1_1A_B"}],
//									"1##1B":[
//										         {i18nCaption: "1_1B_A", value:"1_1B_A"},
//												 {i18nCaption: "1_1B_B", value:"1_1B_B"}]
//					}
//				},
//				formatter: "rup_combo",
//				rupType: "combo"
//			},
			{ name: "fechaAlta",  index: "fechaAlta", editable: true,
				rupType: "date",
				validationrules:{required:true, date:true},
				editoptions:{
					labelMaskId : "fecha-mask",
					showButtonPanel : true,
					showOtherMonths : true,
					noWeekend : true
				}
			},
			{ name: "fechaBaja", index: "fechaBaja", editable: true,
				rupType: "datepicker",
				validationrules:{date:true},
				editoptions:{
					labelMaskId : "fecha-mask",
					showButtonPanel : true,
					showOtherMonths : true,
					noWeekend : true
				}
			}
        ],
        readOnlyFields :  [ "id" ]
	});
	

	$("#simple").rup_maint({
		jQueryGrid: "GRID_simple",
		primaryKey: "id",
		modelObject: "Usuario",
		detailButtons: $.rup.maint.detailButtons.SAVE,
		searchForm: "searchForm",
		showMessages: true,
        toolbar: {
        	//No creamos el botón de filtro por defecto
			defaultFilter : false,
			newButtons : [{
				obj : {
					i18nCaption: "actualizar", 
					css: "rup-maint_filter", 
					index: 4
				},
				json_i18n : $.rup.i18n.app.simpelMaint,
				click : function(){$("#simple").rup_maint("getFilterBootonDefaultFunction").call();}
			}]
			
		},
		onbeforeDetailShow: function(rowId){
			var fieldsArray = jqGrid[0].p.readOnlyFields;
	        if (this.prop.MODO === 'new'){
	        	for (var i = 0; i < fieldsArray.length; i++) {
	        		$("#" + this.prop.detailForm.attr("id") + "_" + fieldsArray[i], this.prop.detailForm).removeAttr('readonly');
				}
	        } else {
	        	for (var i = 0; i < fieldsArray.length; i++) {
	        		$("#" + this.prop.detailForm.attr("id") + "_" + fieldsArray[i], this.prop.detailForm).attr('readonly', true);;
				}
	        }
		}
	});
	
	
	//Formulario de filtrado
	$('#ejie_search').rup_combo({
		source : [
		   {i18nCaption: "0", value:"0"},
		   {i18nCaption: "1", value:"1"}
		],
		i18nId: "GRID_simple##ejie",
		width: 120,
		blank: ""
	});
	
	$("#fechaAlta_search").rup_date();
	$("#fechaBaja_search").rup_date();
	
});