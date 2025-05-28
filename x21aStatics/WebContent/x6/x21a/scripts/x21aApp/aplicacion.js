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
				name: "codigo",
				index: "codigo",
				editable: false,
				
				hidden: false
			},
			
			{
				name: "cdescripcion",
				index: "cdescripcion",
				editable: true,
				
				hidden: false
			},
			
			{
				name: "contexto",
				index: "contexto",
				editable: true,
				
				hidden: false
			},
			
			{
				name: "dptoprod",
				index: "dptoprod",
				editable: true,
				
				hidden: false
			},
			
			{
				name: "edescripcion",
				index: "edescripcion",
				editable: true,
				
				hidden: false
			}
			
		];

		$("#comarca").rup_table({
			primaryKey: "codigo",
			loadOnStartUp: true,
			colReorder: {},
			order: [
				[1, "asc"]
			],
			filter: {
				id: "comarca_filter_form",
				filterToolbar: "comarca_filter_toolbar",
				collapsableLayerId: "comarca_filter_fieldset"
			},
			
			
			formEdit: {
				titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base, "rup_table.edit.editCaption"),
				detailForm: "#comarca_detail_div",
						
						
				validate: { 
					rules: {
							
							"Cdescripcion": {
							required: false,
											
											
							},
									
							"Contexto": {
							required: false,
											
											
							},
									
							"Dptoprod": {
							required: false,
											
											
							},
									
							"Edescripcion": {
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