jQuery(function($){
	$("#GRID_usuarioJerarquia").rup_grid({
		url: "../usuariojerarquia",
		hasMaint: true,
		headertitles: true, //tooltip en cabeceras
		width: 950,
		sortorder: "asc",
		sortname: "nombre",
		colNames: [
		    "level", "hasChildren", "parentNodes", "filter", "treeNodes", //Jerarquia
		    "id",
			"nombre",
			"apellido1",
			"apellido2",
			"ejie",
			"fechaAlta",
			"fechaBaja",
			"idPadre",
			"grupo"
		],
		
		grouping:true,
	    groupingView : {
	    	groupField : ['grupo']
	    },
		
	   	multiselect: true, 
		
		/** Eventos colorear grupos **/
	    //Como se agrupa este evento no es compatible
	    afterInsertRow:   function(rowid, rowdata, rowelem) {
		},
		//Alternativa al evento afterInsertRow
		loadComplete: function(data){
			if (!$.isEmptyObject(data.rows)){
				for (var i=0; i<data.rows.length; i++){
					if (data.rows[i].grupo == "CAC"){
						$("[aria-describedby='GRID_usuarioJerarquia_grupo']",$("tr[id='"+data.rows[i].id+"']")).css("color","red");
					}
				}
			}
		},
		
		colModel: [
		    //Jerarquia
		    { name: "level", hidden: true },
		    { name: "hasChildren", hidden: true },
		    { name: "parentNodes", hidden: true },
		    { name: "filter", hidden: true },
		    { name: "treeNodes", hidden: true },
		    //Fin Jerarquia
		    { name: "id",
				label: "id",
				index: "id",
				hidden: true,
				editable: true,
				key: true
			},
			{ name: "nombre",
				label: "nombre",
				index: "nombre",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "apellido1",
				label: "apellido1",
				index: "apellido1",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "apellido2",
				label: "apellido2",
				index: "apellido2",
				width: "150",
				editable: true,
				edittype: "text"
			},
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
			{ name: "fechaAlta",
				label: "fechaAlta",
				index: "fechaAlta",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "fechaBaja",
				label: "fechaBaja",
				index: "fechaBaja",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "idPadre",
				label: "idPadre",
				index: "idPadre",
				hidden: true,
				editable: true,
				edittype: "text"
			},
			{ name: "grupo",
				label: "grupo",
				index: "grupo",
				editable: true,
				edittype: "text",
				formatter: function (cellval, opts, rowObject, action) {
					var groupIdPrefix = opts.gid + "ghead_",
						groupIdPrefixLength = groupIdPrefix.length;
					if (opts.rowId.substr(0, groupIdPrefixLength) === groupIdPrefix && typeof action === "undefined") {
						//RESUMEN
				        return "Agrupación: " + cellval;
				    }
					//CELDA
					return "<u>" + cellval + "</u>" +  "<span class='ui-icon ui-icon-extlink rup_external_link'>&nbsp;&nbsp;&nbsp;&nbsp;</span>";
				}
			}
        ]
	});

	$("#usuarioJerarquia").rup_maint({
		jQueryGrid: "GRID_usuarioJerarquia",
		primaryKey: "id",
		modelObject: "UsuarioJerarquia",
		detailButtons: $.rup.maint.detailButtons.SAVE,
		searchForm: "searchForm",
		showMessages: true
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
	
	$("#GRID_usuarioJerarquia").rup_grid_jerarquia({
		expandColName : "nombre",
		relatedColName : "id",
		resetEvents: {
			click: ["bt_search_usuarioJerarquia", "clean_search_usuarioJerarquia"],
			keydown : [ function(event){ if (event.keyCode === 13) { return false; } }, "searchForm" ]
		},
		resetMultiEvents: {
			click: ["bt_search_usuarioJerarquia", "clean_search_usuarioJerarquia"],
			keydown : [ function(event){ if (event.keyCode === 13) { return false; } }, "searchForm" ]
		}
		,
		multiMenu : {
			hijos : true,
			descendientes : true
		}
	});
});