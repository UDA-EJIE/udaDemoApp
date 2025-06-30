jQuery(function($){

	$('#horarioslaborales_feedback').rup_feedback({
		block : false
	});
	function fncVolver(){		
		window.location.href = "../../calendarioservicio/maint";
	}
	
	$("#horarioslaborales").rup_table({
		url: "../horarioslaborales",
		toolbar: {
			 id: "horarioslaborales_toolbar"
				 ,newButtons : [      
				       {obj : {
							i18nCaption: $.rup.i18n.app.boton.volver
							,css: "fa fa-arrow-left"
							,index: 0
							,right: false
						 }
						 ,json_i18n : $.rup.i18n.app.simpelMaint
						 ,click : 
							 function(){
							 	fncVolver();
							}
						}
					]
				 ,defaultButtons:{
					 add : false
					,edit : true
					,cancel : false
					,save : false
					,clone : false
					,"delete" : false
					,filter : false					
				 }				  
		},		
		colNames: [
			"",						
			txtResumen
		],
		colModel: [
			{ 	name: "id038", 
			 	label: "label.id",
				align: "", 
				hidden: true
			},			
			{ 	name: "resumen", 
			 	label: "label.resumen",
				align: "left", 
				width: 225,
				fixed: false,
				sortable : false
			}			
        ],
        model:"HorariosLaborales",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",        
        	"fluid"
         	],
		primaryKey: "id038",
		sortname: "id038",
		sortorder: "asc",
		loadOnStartUp: true,
        formEdit:{
        	detailForm: "#horarioslaborales_detail_div",
         	validate:{           		
         		messages:{ "horasParejasReq":$.rup.i18n.app.validaciones.horasParejasReq,  "horaFin":$.rup.i18n.app.validaciones.horaFin },                 		
         		rules:{   
    				"id038":{required: true},
    				"horasHorarioses[0].horaIniLjM039":{ required: true, hora: true},	
    				"horasHorarioses[0].horaFinLjM039":{ required: true, hora: true, horaFin: true},
    				"horasHorarioses[0].horaIniVM039":{ required: true, hora: true},
    				"horasHorarioses[0].horaFinVM039":{ required: true, hora: true, horaFin: true},
    				"horasHorarioses[0].horaIniLjT039":{ hora: true, horarioTardeMayor: true},	
    				"horasHorarioses[0].horaFinLjT039":{ hora: true, horaFin: true, horasParejasReq:true},
    				"horasHorarioses[0].horaIniVT039":{ hora: true, horarioTardeMayor: true},
    				"horasHorarioses[0].horaFinVT039":{ hora: true, horaFin: true, horasParejasReq:true},
    				
    				"horasHorarioses[1].horaIniLjM039":{ required: true, hora: true},	
    				"horasHorarioses[1].horaFinLjM039":{ required: true, hora: true, horaFin: true},
    				"horasHorarioses[1].horaIniVM039":{ required: true, hora: true},
    				"horasHorarioses[1].horaFinVM039":{ required: true, hora: true, horaFin: true},
    				"horasHorarioses[1].horaIniLjT039":{ hora: true, horarioTardeMayor: true},	
    				"horasHorarioses[1].horaFinLjT039":{ hora: true, horaFin: true, horasParejasReq:true},
    				"horasHorarioses[1].horaIniVT039":{ hora: true, horarioTardeMayor: true},
    				"horasHorarioses[1].horaFinVT039":{ hora: true, horaFin: true, horasParejasReq:true}
				},
				showErrorsInFeedback: true,
         		showFieldErrorsInFeedback: false
    		},
    		addEditOptions:{
				 width:800,
				 fillDataMethod: "serverSide",
				 reloadAfterSubmit: true
				 
			 },
			 editOptions: {
				 mtype: "POST",
				 fillDataMethod: "serverSide",
				 reloadAfterSubmit : true,
				 ajaxEditOptions:{
					 contentType: 'application/json'
				}
			 },
			 addOptions: {
				 mtype: "POST",
				 reloadAfterSubmit : true,
				 ajaxEditOptions:{
					 contentType: 'application/json'
				}
			 }
        }
	}
	);
	//btn volver a la izda
	jQuery('#horarioslaborales_toolbar\\#\\#'+ $.rup.i18n.app.boton.volver).addClass('izda');
	/*function validarActivoPorTipo(){
			var datos = jQuery("#horarioslaborales_detail_form").rup_form().formToJson();
			var error = [true];
			$.ajax({
			   	 type: 'POST'
			   	 ,url: '/aa79bItzulnetWar/administracion/calendarioservicio/horarioslaborales/comprobarHorarioActivo'
			 	 ,dataType: 'json'
			 	 ,contentType: 'application/json' 
			     ,data: $.toJSON(datos)			
			     ,async: false
			   	 ,success:function(existe){
			   		if (existe) {
			   			$("#horarioslaborales_detail_feedback").rup_feedback("set", $.rup.i18n.app.validaciones.horarioActivoDuplicadoSinTipo, "error");			   			
			   			error =  [false];
			   		} else {
			   			error =  [true];
			   		}
			   	 }
		   	 	,error: function(){
			   		$('#horarioslaborales_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
			   		error =  [false];
			   	 }
			 });
			
			return error;
			
		}*/
		
	//poner la validación en la 2ª hora (fin)
	$.validator.addMethod("horasParejasReq", function(value, element) {    
	    var minElmName = element.id.replace("Fin","Ini");
	    var startHour = $("#"+minElmName).val(); 
	    if((value === "" && startHour === "") || (value !== "" && startHour !== "")) 
	    	return true;
	    return false;
	        
	}, $.rup.i18n.app.validaciones.horasParejasReq);
	
	//Comprueba si la hora de inicio de la tarde introducida es mayor que la hora de fin de la mañana...
	$.validator.addMethod("horarioTardeMayor", function(value, element) {    
	    
		if(value === ""){
			return true;
		}else{
			var FinMananaElmName = element.id.replace("Ini","Fin");
			FinMananaElmName = FinMananaElmName.replace("T","M");			
		    var hFinManana = $("#"+FinMananaElmName).val();		    
		    var finManana = fncObtenerMinutosHora(hFinManana);
            var ppioTarde = fncObtenerMinutosHora(value);            
            return !(ppioTarde - finManana<0);		  
		}    
	}, $.rup.i18n.app.validaciones.horasTardeManana);	
	
	function fncObtenerMinutosHora(laHora){
		return laHora.replace(":", "");
	}
	
	$.validator.addMethod("horaFin", function(value, element) {    
	    var minElmName = element.id.replace("Fin","Ini");
	    if ($("#"+minElmName).length){
	        var startHour = $("#"+minElmName).val(); 
	        if(value === "" || startHour === "") 
	        	return true;
	        try{
	            var end = fncObtenerMinutosHora(value);
	            var start = fncObtenerMinutosHora(startHour);
	            return !(end - start<0);
	        } catch(e){
	            return false;
	        }
	    }	    
	}, $.rup.i18n.app.validaciones.horaFin);
	    
	
	jQuery("#horarioslaborales").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
		//La accion agregar borra los valores de los inputs,y necesito que estos tengan valor diferenciador
		$("input[name='horasHorarioses[0].indVerano039']").val("I");
		$("input[name='horasHorarioses[1].indVerano039']").val("V");		
		$("#horarioslaborales").rup_table("updateSavedData", function(savedData){
			savedData["horasHorarioses[0].indVerano039"]=$("input[name='horasHorarioses[0].indVerano039']").val();
			savedData["horasHorarioses[1].indVerano039"]=$("input[name='horasHorarioses[1].indVerano039']").val();					
		});
	});	
		
	jQuery("#horarioslaborales_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
		setFocusFirstInput("#horarioslaborales_detail_form");
	});
});