var buscadorPersonasCreado = false; 

//function volverADetalleExpediente(){
//	$("#divReceptoresAutorizados").detach();
//	$("#divCapaDetalle").append("<div id='divDetalleExpediente'></div>");
//	$("#divDetalleExpediente").html(capaDetalleExpediente);
//}

function seleccionarPersonasReceptoras(obj, personas){
	if(obj!=null && obj.length>0){
		var jsonObject = {
				listaReceptoresAutorizados : []
		}
		for(var i=0; i<obj.length; i++){
			var persona = obj[i];
			var entidad = {
					"tipo": persona.entidad["tipo"],
 					"codigo": persona.entidad["codigo"]
			};
			jsonObject.listaReceptoresAutorizados.push({
				"anyo": $('#anyoreceptoresAutorizados_filter_table').val(),
				"numExp": $('#numExpreceptoresAutorizados_filter_table').val(),
				"dni": persona.dni,
				"entidad": entidad
				
			});

		}
			
			jQuery.ajax({
				type: "POST",
				url:"/aa79bItzulnetWar/receptoresautorizados/comprobarYGuardar",
				dataType: "json",
				contentType: 'application/json',
				data: $.toJSON(jsonObject),
				cache: false,
				success: function(data){
					if(data!=null){
						$("#receptoresAutorizados").rup_table("filter");
					}else{
						$('#receptoresAutorizados_feedback').rup_feedback("set", "No se han podido guardar los datos", "error");
					}
				}
			});
	}
}

function eliminarReceptorAutorizado(){
	
	var selectedRows = $("#receptoresAutorizados").rup_table('getSelectedRows');
	var row;
	
	for(var i=0;i<selectedRows.length;i++){
		row = selectedRows[i];
 	}
	
	var receptor = $("#receptoresAutorizados").rup_table('getRowData', row);
	
	var jsonObject = {
		"anyo": receptor.anyo,
		"numExp": receptor.numExp,
		"dni": receptor.dni
   	};
	
	jQuery.ajax({
    	type: "DELETE",
    	url: "/aa79bItzulnetWar/receptoresautorizados/eliminarReceptorAutorizado",
    	dataType: "json",
    	contentType: 'application/json',
    	data: $.toJSON(jsonObject),
    	cache: false,
    	success:function(){
    		$('#receptoresAutorizados').rup_table('filter');
    		
        	$.rup_messages("msgOK", {
    			message: $.rup.i18nParse($.rup.i18n.base,"rup_table.feedback.deletedOK"),
    			title: $.rup.i18nParse($.rup.i18n.base,"rup_global.save")
    		});
        }, 
   	 	error: function(){
	   	 	$.rup_messages("msgError", {
				message: $.rup.i18nParse($.rup.i18n.app,"rup_table.feedback.deletedError"),
				title: $.rup.i18nParse($.rup.i18n.base,"rup_global.error")
			});
   	 	}
    });
	
}
 
jQuery(function($){
    	
	$("#anyoreceptoresAutorizados_filter_table").val(anyoExpediente);
	$("#numExpreceptoresAutorizados_filter_table").val(idExpediente);
	$("#buscadorPersonasReceptores").buscador_personas({destino:'R' , multiselect:true , anyo: $("#anyoreceptoresAutorizados_filter_table").val(), numExp:$("#numExpreceptoresAutorizados_filter_table").val(), callback: seleccionarPersonasReceptoras});
	
	$('#receptoresAutorizados_feedback').rup_feedback({
 		block : false
 	});

	$("#receptoresAutorizados").rup_table({
		url: "/aa79bItzulnetWar/receptoresautorizados",
		toolbar:{
			id: "receptoresAutorizados_toolbar"
			, defaultButtons:{
				add:false,
				edit:false,
				cancel:false,
				save:false,
				clone:false,
				"delete":false,
				filter:true
			},newButtons : [      
				{obj : {
					i18nCaption: $.rup.i18n.app.boton.volver
					,css: "fa fa-arrow-left"
					,index: 1
					,right: false
				 }
				 ,json_i18n : $.rup.i18n.app.simpelMaint
				 ,click : 
					 function(e){			
					 	$("#buscadorPersonasReceptores").remove();
					 	$("#receptorDiv").html("<div id='buscadorPersonasReceptores' class='oculto'></div>");
//					 	volverADetalleExpediente();
					 	volverADetalleExpedienteDesdeAccionesPlanif('divReceptoresAutorizados');
					}
				},
				{obj : {
					i18nCaption: $.rup.i18n.app.boton.anyadir
					,css: "fa fa-file-o"
					,index: 2
					,right: false
				 }
				 ,json_i18n : $.rup.i18n.app.simpelMaint
				 ,click : 
					 function(e){
					 $("#buscadorPersonasReceptores").buscador_personas("open");
					}
				},
				{obj: {
					i18nCaption: $.rup.i18n.app.boton.borrar
	 				,css: "fa fa-trash-o"
		 			}
		 			,json_i18n : $.rup.i18n.app.simpelMaint
		 			,click : 
		 				function(e){
	   		 			if(!$('#receptoresAutorizados').rup_table("isSelected")){
							e.preventDefault();
							$.rup_messages("msgAlert", {
								message: $.rup.i18n.app.comun.warningSeleccion
							});	
							return false;
						 }else{
							 $.rup_messages("msgConfirm", {
	             				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
	             				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.del.msg"),
	             				OKFunction: function(){
	             					eliminarReceptorAutorizado();
	             				}
	             			});
						 }
		 				}	
	 		}
			]
		},
		colNames: [
			"",
			"",
			"",
			$.rup.i18nParse($.rup.i18n.app, "label.dni"),
			$.rup.i18nParse($.rup.i18n.app, "label.nombre"),
			$.rup.i18nParse($.rup.i18n.app, "label.apellidos"),
			$.rup.i18nParse($.rup.i18n.app, "label.tipoEntidad"),
			$.rup.i18nParse($.rup.i18n.app, "label.entidad")
		],
		colModel: [
			{
				name: "anyo",
				hidden: true
			},
			{
				name: "numExp",
				hidden: true
			},
			{
				name: "dni",
				hidden: true
			},
			{ 	name: "dniCompleto", 
			 	label: "label.dni",
			 	index: "DNI",
				align: "right", 
				width: 70, 
				editable: true, 
				fixed:false,
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "nombre", 
			 	label: "label.nombre",
			 	index: "NOMBRE",
				align: "left", 
				editable: true, 
				fixed:false,
				hidden: false, 
				resizable: true, 
				sortable: true,
				width: 80,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "apellidos", 
			 	label: "label.apellidos",
			 	index: "APELLIDOS",
				align: "left", 
				editable: true, 
				fixed:false,
				hidden: false, 
				resizable: true, 
				sortable: true,
				width: 150,
				formatter: function (cellvalue, options, rowObject){
					return rowObject.apellido1+" , "+rowObject.apellido2;
				},
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "entidad.tipoDesc", 
			 	label: "label.tipoEntidad",
			 	index: "TIPOENTIDADDESCEU",
				align: "left", 
				editable: true, 
				fixed:false,
				hidden: false, 
				resizable: true, 
				sortable: true,
				width: 40
			},
			{ 	name : $.rup.lang == 'es' ? "entidad.descEs"
					: "entidad.descEu",
			 	label: "label.entidad",
			 	index : $.rup.lang == 'es' ? "DESCES"
						: "DESCEU",
				align: "left", 
				width:250,
				editable: true,
				fixed:false,
				ruptype: "combo", 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			}
        ],
        model:"ReceptorAutorizado",
        usePlugins:[
        	"feedback",
        	"toolbar",
        	"filter",
        	"responsive",
        	"fluid"
         	],
		primaryKey: "dni",
		sortname: "DNI",
		sortorder: "asc",
		loadOnStartUp: true,
		loadComplete: function(){ 
            $('td[grid_tooltip]').each(function(){
            $(this).attr('title', $(this).attr('grid_tooltip'));
            $(this).tooltip();
            }); 
        },
	     filter:{clearSearchFormMode:"reset" }
	});
});