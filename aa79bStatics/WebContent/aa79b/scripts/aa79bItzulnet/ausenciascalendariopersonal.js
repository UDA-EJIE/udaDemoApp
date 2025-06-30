jQuery(function($){

	$("#ausenciascalendario").rup_table({
		
		url: "/aa79bItzulnetWar/ausenciascalendariopersonal",
		colNames: [
			"codTrabajador025",
			"anyo025",
			"idDiaCalendario025",
			"fechaDesde025",
			"fechaHasta025",
			"tipoJornada025",
			"observDia025",
			"numDiasHabiles025"
		],
		colModel: [
			{ 	name: "calendarioPersonal.codTrabajador025", 
			 	label: "codTrabajador025",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "calendarioPersonal.anyo025", 
			 	label: "anyo025",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "idDiaCalendario025", 
			 	label: "idDiaCalendario025",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "fechaDesde025", 
			 	label: "fechaDesde025",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "fechaHasta025", 
			 	label: "fechaHasta025",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "tipoJornada025", 
			 	label: "tipoJornada025",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "observDia025", 
			 	label: "observDia025",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "numDiasHabiles025", 
			 	label: "numDiasHabiles025",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
        ],

        model:"AusenciasCalendarioPersonal",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"contextMenu",
        	"responsive",
        	"filter",
        	"search"
         	],
		primaryKey: "codTrabajador025;anyo025;idDiaCalendario025",
		sortname: "codTrabajador025",
		sortorder: "asc",
		loadOnStartUp: true,
        formEdit:{
        	detailForm: "#ausenciascalendario_detail_div",
			fillDataMethod: "clientSide",
         	validate:{ 
    			rules:{
    				"calendarioPersonal.codTrabajador025":{
						required: false
    					},
    				"calendarioPersonal.anyo025":{
						required: false
    					},
    				"idDiaCalendario025":{
						required: false
    					},
    				"fechaDesde025":{
						required: false
    					},
    				"fechaHasta025":{
						required: false
    					},
    				"tipoJornada025":{
						required: false
    					},
    				"observDia025":{
						required: false
    					},
    				"numDiasHabiles025":{
						required: false
    					}
    				}
    		}
        }
	});
});