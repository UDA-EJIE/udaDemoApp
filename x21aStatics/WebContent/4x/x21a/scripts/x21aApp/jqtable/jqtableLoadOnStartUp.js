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
jQuery(function($){
	
	// No pueden resolverse resources i18n de rup hasta que haya terminado de cargarlos
	initRupI18nPromise.then(function() {
		$("#comarca").rup_jqtable({
			url: "../jqGridComarca",
			sortorder: "asc",
			sortname: "code",
			primaryKey: "code" ,
			loadOnStartUp:false,
			autowidth: true,
			colNames: [
				"code",
				"descEs",
				"descEu",
				"css",
				"Provincia",
				"Provincia"
			],
			  
			colModel: [
				{ name: "code",
					label: "code",
					index: "code",
					width: "150",
					editable: true,
					edittype: "text",
					key:true
				},
				{ name: "descEs",
					label: "descEs",
					index: "descEs",
					width: "150",
					editable: true,
					edittype: "text"
				},
				{ name: "descEu",
					label: "descEu",
					index: "descEu",
					width: "150",
					editable: true,
					edittype: "text"
				},
				{ name: "css",
					label: "css",
					index: "css",
					width: "150",
					editable: true,
					edittype: "text"
				},
				{ name: "provincia.code",
					label: "provincia.code",
					index: "provincia.code",
					editable: true,
					hidden: true,
					edittype: "text",
					rupType: "combo",
					editoptions: {
						source : "../jqGridComarca/provincia",
						sourceParam : {label:"descEs", value:"code"},
						blank : "",
						edithidden:true
					},
					editrules:{
						edithidden:true
					}
				},
				{ name: "provincia.descEs",
					label: "provincia.descEs",
					index: "provincia.descEs",
					editable: false
				}
	        ],
	        usePlugins:[
	 			"formEdit",
	        	"feedback",
				"toolbar",
	        	"contextMenu",
	        	"responsive",
	        	"filter",
	        	"search",
	        	"multifilter"
	        ],
	        editOptions:{
	        	fillDataMethod:"clientSide"
	        },
	        multifilter:{ idFilter:"onLoadFalse",labelSize:255,userFilter:"udaPruebas"}
	       
		});
	});

});
