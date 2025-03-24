/*!
 * Copyright 2024 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la "Licencia");
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 *      http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
 * el programa distribuido con arreglo a la Licencia se distribuye "TAL CUAL",
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */

jQuery(function($) {
	window.initRupI18nPromise.then(function () {
	 	 	 	 	
		const tableColModel = [
			
			{
				name: "idAreaT21",
				index: "idAreaT21",
				editable: false,
				
				hidden: false
			},
			
			{
				name: "areaActivaT21",
				index: "areaActivaT21",
				editable: true,
				
				hidden: false
			},
			
			{
				name: "idLote3T21",
				index: "idLote3T21",
				editable: true,
				
				hidden: false
			},
			
			{
				name: "nombreAreaCastT21",
				index: "nombreAreaCastT21",
				editable: true,
				
				hidden: false
			},
			
			{
				name: "nombreAreaEuskT21",
				index: "nombreAreaEuskT21",
				editable: true,
				
				hidden: false
			}
			
		];

		$("#areas").rup_table({
			primaryKey: "idAreaT21",
			loadOnStartUp: true,
			colReorder: {},
			order: [
				[1, "asc"]
			],
			filter: {
				id: "areas_filter_form",
				filterToolbar: "areas_filter_toolbar",
				collapsableLayerId: "areas_filter_fieldset"
			},
			
			
			formEdit: {
				titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base, "rup_table.edit.editCaption"),
				detailForm: "#areas_detail_div",
				
				
				validate: { 
					rules: {
						
						"areaActivaT21": {
							required: false,
							
							
						},
						
						"idLote3T21": {
							required: false,
							
							
						},
						
						"nombreAreaCastT21": {
							required: false,
							
							
						},
						
						"nombreAreaEuskT21": {
							required: false,
							
							
						}
						
					}
				}
				
			},
			
			
			select: {
				activate: true
			},
			
			
			buttons: {
				activate: true
				
				
			},
			
			colModel: tableColModel
		});
	});
});
