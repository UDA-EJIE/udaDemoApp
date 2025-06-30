jQuery(function($){
	$('#horariosatencion_feedback').rup_feedback({
		block : false
	});
	
	function fncVolver(){		
		window.location.href = "../../calendarioservicio/maint";
	}
	
	$("#horariosatencion").rup_table({
		url: "../horariosatencion",
		toolbar: {
			 id: "horariosatencion_toolbar"
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
			txtTipoHorario,			
			txtResumen
		],
		colModel: [
			{ 	name: "id020", 
			 	label: "label.id",
				align: "", 
				hidden: true
			},
			{ 	name: $.rup.lang === 'es' ? "nivelUsuarioEs"
					: "nivelUsuarioEu",
			 	label: "label.tipoHorario",
			 	index : $.rup.lang === 'es' ? "NIVELUSUARIOES"
						: "NIVELUSUARIOEU",
				align: "left", 
				width: 100, 
				fixed: false

			},			
			{ 	name: "resumen", 
			 	label: "label.resumen",
				align: "left", 
				width: 225,
				fixed: false,
				sortable : false
			}
			
        ],
        model:"HorariosAtencion",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",        	
        	"fluid"
         	],
		primaryKey: "id020",
		sortname: "id020",
		sortorder: "asc",
		loadOnStartUp: true,
        formEdit:{
        	detailForm: "#horariosatencion_detail_div",		
         	validate:{           		
         		messages:{ "horasParejasReq":$.rup.i18n.app.validaciones.horasParejasReq,  "horaFin":$.rup.i18n.app.validaciones.horaFin },                 		
         		rules:{  
    				"id020":{
						required: true
    					},
    				"nivelUsuario020":{
						required: true
    					},    				
    				"horasHorarioses[0].horaIniLjM021":{ required: true, hora: true},	
    				"horasHorarioses[0].horaFinLjM021":{ required: true, hora: true, horaFin: true},
    				"horasHorarioses[0].horaIniVM021":{ required: true, hora: true},
    				"horasHorarioses[0].horaFinVM021":{ required: true, hora: true, horaFin: true},
    				"horasHorarioses[0].horaIniLjT021":{ hora: true, horarioTardeMayor: true},	
    				"horasHorarioses[0].horaFinLjT021":{ hora: true, horaFin: true, horasParejasReq:true},
    				"horasHorarioses[0].horaIniVT021":{ hora: true, horarioTardeMayor: true},
    				"horasHorarioses[0].horaFinVT021":{ hora: true, horaFin: true, horasParejasReq:true},
    				
    				"horasHorarioses[1].horaIniLjM021":{ required: true, hora: true},	
    				"horasHorarioses[1].horaFinLjM021":{ required: true, hora: true, horaFin: true},
    				"horasHorarioses[1].horaIniVM021":{ required: true, hora: true},
    				"horasHorarioses[1].horaFinVM021":{ required: true, hora: true, horaFin: true},
    				"horasHorarioses[1].horaIniLjT021":{ hora: true, horarioTardeMayor: true},	
    				"horasHorarioses[1].horaFinLjT021":{ hora: true, horaFin: true, horasParejasReq:true},
    				"horasHorarioses[1].horaIniVT021":{ hora: true, horarioTardeMayor: true},
    				"horasHorarioses[1].horaFinVT021":{ hora: true, horaFin: true, horasParejasReq:true}
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
	jQuery('#horariosatencion_toolbar\\#\\#'+ $.rup.i18n.app.boton.volver).addClass('izda');	

			
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
	
	
	jQuery("#horariosatencion").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
		//La accion agregar borra los valores de los inputs,y necesito que estos tengan valor diferenciador
		$("input[name='horasHorarioses[0].indVerano021']").val("I");
		$("input[name='horasHorarioses[1].indVerano021']").val("V");
				
		/*if (!($('#nivelUsuario020n').attr("checked")==="checked") && !($('#nivelUsuario020v').attr("checked")==="checked")){
			$('#nivelUsuario020n').attr('checked',true);
		}	*/	
		$("#horariosatencion").rup_table("updateSavedData", function(savedData){
			savedData["horasHorarioses[0].indVerano021"]=$("input[name='horasHorarioses[0].indVerano021']").val();
			savedData["horasHorarioses[1].indVerano021"]=$("input[name='horasHorarioses[1].indVerano021']").val();
			//savedData["nivelUsuario020"]=$('input:radio[name="nivelUsuario020"]:checked').val();			
		});
		
	});	

	setFocusFirstInput("#horariosatencion_filter_form");
	jQuery("#horariosatencion_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
		setFocusFirstInput("#horariosatencion_detail_form");
	});
	
	$("#horariosatencion").on("rupTable_afterFormFillDataServerSide", function(){
		if ($('#nivelUsuario020').val() === 'N')
			$('#txtTipo').text(txtTipoHorario +": "+ txtNormal);
		else $('#txtTipo').text(txtTipoHorario +": "+ txtVip);
	 });
		

});