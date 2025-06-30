var configFeedback = {
	block : false,
	delay: 3000
};

var tamanoFichero = 0;

//formatter para la ruptable
function formatEnlace(cellval, opts, rowObject) {
	if (isNotEmpty(rowObject.oidFichero)){
		return '<a href="#" onclick="descargarPlantilla('+rowObject.idFicheroPlantilla0A7+')">' + rowObject.nombrePlantilla0A7 + '</a>';	
	}else{
		return rowObject.nombrePlantilla0A7;		
	}
}

//FUNCION PARA DESCARGAR EL ARCHIVO DE LA PLANTILLA
function descargarPlantilla(idFichero) {
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/2/'+idFichero);
}


/* DIALOGO DE EDTALLE */
//DIALOGO DETALLE PARA LA MODIFICACION DEL ARCHIVO DE PLANTILLA
function showDetalleForm(idfila) {
	$("#subidaFicheroPid_form").rup_form('clearForm', true);
	clearValidation('#subidaFicheroPid_form');		
	$("#plantillas_detail_feedback").rup_feedback("close");
	eliminarMensajesValidacionPorCapa('plantillas_detail_div');
	if(idfila != ''){
		// editar plantilla
		$('#nombrePlantilla0A7_detail_table').val($("#plantillas").rup_table("getCol", idfila, "nombrePlantilla0A7"));
		$('#varPlantilla0A7_detail_table').val($("#plantillas").rup_table("getCol", idfila, "varPlantilla0A7"));
		//ocultos de interes
		$('#id0A7').val($("#plantillas").rup_table("getCol", idfila, "id0A7"));
		$('#idFicheroPlantilla0A7').val($("#plantillas").rup_table("getCol", idfila, "idFicheroPlantilla0A7"));
		if (isNotEmpty($("#plantillas").rup_table("getCol", idfila, "oidFichero"))){
			var nombre = $("#plantillas").rup_table("getCol", idfila, "nombre");
			if(nombre.length > 35){
				nombre = nombre.substring(0,35) + "...";
			}
			$('#enlaceNombre').html('<a href="#" title='+$("#plantillas").rup_table("getCol", idfila, "nombre")+' onclick="descargarPlantilla('+$('#idFicheroPlantilla0A7').val()+')">'+ nombre +'</a>');	
		}else{
			$('#enlaceNombre').html('');
		}
		
		// deshabilitamos campos
		$('#nombrePlantilla0A7_detail_table').prop('readonly', false);
		$('#nombrePlantilla0A7_detail_table').prop('disabled', false);
		$('#varPlantilla0A7_detail_table').prop('readonly', false);
		$('#varPlantilla0A7_detail_table').prop('disabled', false);
		$("#ficheroNombre").rules("remove", "required");
		
	}else{
		// nueva plantilla
		// habilitamos campos
		$('#nombrePlantilla0A7_detail_table').prop('readonly', false);
		$('#nombrePlantilla0A7_detail_table').prop('disabled', false);
		$('#varPlantilla0A7_detail_table').prop('readonly', false);
		$('#varPlantilla0A7_detail_table').prop('disabled', false);
		$('#enlaceNombre').html('');
		$("#ficheroNombre").rules("add", "required");
	}
	
	$('#enlaceNombre').show();
	$("#plantillas_detail_div").rup_dialog({
		type : $.rup.dialog.DIV,
		autoOpen : false,
		modal : true,
		resizable : false,
		width : "750",
		title : $.rup.i18n.app.label.editarPlantilla
	});
	// cambiar titulo de dialog
	$("#plantillas_detail_div").dialog('option', 'title', idfila != '' ? $.rup.i18n.app.label.editarPlantilla : $.rup.i18n.app.label.crearPlantilla);
	//PONEMOS LOS VALORES EN BLANCO
	$('#ficheroFile').val("");
	//$('#ficheroNombre').val("");
	$('#plantillas_detail_div').rup_dialog("open");
}

//TRIGGER BOTON
function clickPidButtonFinal() {
	$('#ficheroFile').click();
}



/* FIN DIALOGO DE EDTALLE */

jQuery(function($) {
	$.validator.addMethod("checkFileSize", function(value, element, params) {
		if (tamanoFichero > 15000000){
			return false;
		}else{
			return true;
		}
	},$.rup.i18n.app.validaciones.checkFileSize);
	
	$('#plantillas_feedback').rup_feedback(configFeedback);
	$('#plantillas_detail_feedback').rup_feedback(configFeedback);
	$("#plantillas").rup_table({
			url : "/aa79bItzulnetWar/administracion/datosmaestros/plantillas/",
			toolbar : {
				id : "plantillas_toolbar",
				newButtons : [ 
					{obj : {
							i18nCaption : $.rup.i18nParse($.rup.i18n.base,"rup_table.new"),
							css : "fa fa-file-o",
							index : 0
						},
						json_i18n : $.rup.i18n.app.simpelMaint,
						click : function(e) {
							showDetalleForm('');
						}
					},
					{obj : {
							i18nCaption : $.rup.i18nParse($.rup.i18n.base,"rup_table.modify"),
							css : "fa fa-pencil-square-o",
							index : 0
						},
						json_i18n : $.rup.i18n.app.simpelMaint,
						click : function(e) {
							if (!$('#plantillas').rup_table("isSelected")) {
								e.preventDefault();
								$.rup_messages("msgAlert", {message : $.rup.i18n.app.comun.warningSeleccion});
								return false;
							} else {
								showDetalleForm($('#plantillas').rup_table("getSelectedRows"));
							}
						}
					}
				],
				defaultButtons : {
					add : false,
					edit : false,
					cancel : false,
					save : false,
					clone : false,
					"delete" : false,
					filter : true
				}
			},
			colNames : [ nombrePlantilla0A7,"", "", "", "", "", "", "", "", "", "", "", "", "", "" ],
			colModel : [
					{
						name : "nombrePlantilla0A7Enlace",
						label : "label.nombrePlantilla",
						index : "NOMBREPLANTILLA0A7",
						align : "left",
						editable : false,
						fixed : true,
						hidden : false,
						resizable : false,
						sortable : false,
						formatter : function(cellval, opts, rowObject) {
							return formatEnlace(cellval, opts, rowObject);
						}
					}, 
					{name : "nombrePlantilla0A7",hidden : true}, 
					{name : "id0A7", hidden : true}, 
					{name : "varPlantilla0A7",hidden : true}, 
					{name : "idFicheroPlantilla0A7",hidden : true}, 
					{name : "fichero",hidden : true}, 
					{name : "codigo",hidden : true}, 
					{name : "titulo",hidden : true}, 
					{name : "extension",hidden : true}, 
					{name : "contentType",hidden : true}, 
					{name : "tamano",hidden : true}, 
					{name : "encriptado",hidden : true}, 
					{name : "rutaPif",hidden : true}, 
					{name : "oidFichero",hidden : true}, 
					{name : "nombre",hidden : true} 
			],
			model : "Plantillas",
			usePlugins : [ "feedback", "toolbar", "responsive", "filter" ],
			primaryKey : [ "id0A7" ],
			sortname : "id0A7",
			sortorder : "asc",
			loadOnStartUp : true,
			ondblClickRow : function() {
				showDetalleForm($('#plantillas').rup_table("getSelectedRows"));
			}
		});
		//TRIGGER PARA EL BOTON DE EXAMINAR
	$("#ficheroFile").change(function() {
		if ($("#ficheroFile").val() !== ''){
				tamanoFichero = this.files[0].size;
				$("#ficheroNombre").rules("remove", "required");
				$("#nombrePlantilla0A7_detail_table").rules("remove", "required");
				//ENVIAMOS EL FORMULARIO Y PONEMOS EL NOMBRE EN EL RECUADRO
				$("#subidaFicheroPid_form").submit();
		}
	});

	//ACCION DEL BOTON EXAMINAR
	$('#subidaFicheroPid_form').rup_form({
		url: "/aa79bItzulnetWar/ficheros/subidaFichero"
		, feedback:$("#plantillas_detail_feedback")
		, dataType: "json"
		, beforeSend: function () {
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.subiendo"));
			return "skip";
		},
		validate:{ 
			rules:{
				"ficheroNombre": {
					checkFileSize: tamanoFichero
				}
			},
			showFieldErrorAsDefault: false,
	        showErrorsInFeedback: true,
	        showFieldErrorsInFeedback: false,
		}
		, success: function(archivoPIF){
			if (archivoPIF.error === null){ 
				//Subida correcta
				$.ajax({
				   	 type: 'POST'
				   	 ,url: '/aa79bItzulnetWar/ficheros/pasoPIFaPID'
				 	 ,dataType: 'json'
				 	 ,contentType: 'application/json' 
				     ,data: $.toJSON(archivoPIF)			
				   	 ,success:function(data){
				   		if (data.error === null){
				   			$('#extension').val(data.extension);
				   			$('#contentType').val(data.contentType);
				   			$('#tamano').val(data.tamano);
				   			$('#oidFichero').val(data.oid);
				   			$('#nombre').val(data.nombre);
				   			$('#encriptado').val(data.encriptado);		
				   			$("#ficheroNombre").val(data.nombre);
				   			$("#enlaceNombre").hide();
				   		}else{
							mostrarMensajeFeedback("plantillas_detail_feedback",data.error,"error"); 
						}
			   			desbloquearPantalla();
				   	 }
			   	 	,error: function(){
			   	 		desbloquearPantalla();
						mostrarMensajeFeedback("plantillas_detail_feedback",$.rup.i18n.app.validaciones.errorLlamadaAjax,"error"); 
				   	 }
				 });
			}else{
				//subida incorrecta
	   			desbloquearPantalla();
				mostrarMensajeFeedback("plantillas_detail_feedback",archivoPIF.error,"error"); 
			}
		 }
		, error: function(archivoPIF){
			desbloquearPantalla();
			mostrarMensajeFeedback("plantillas_detail_feedback",archivoPIF.error,"error"); 
		}
	});
	//ACCION DEL BOTON CANCELAR DEL DIALOGO
	$("#plantillas_detail_button_cancel").click(function(e) {
		e.preventDefault();
		e.stopImmediatePropagation();
		if (isNotEmpty($("#ficheroNombre").val())){
			$.rup_messages("msgConfirm", {
					title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
					message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
					OKFunction: function(){
						setTimeout(function() {
							$('#plantillas_detail_div').rup_dialog("close");
						}, 20);
						
					}
				});
		} else {
			setTimeout(function() {
				$('#plantillas_detail_div').rup_dialog("close");
			}, 20);
		}
		
	});
	
	//ACCION DEL BOTON GUARDAR DEL DIALOGO
	$("#plantillas_detail_button_save").click(function() {
		var plantilla = {
			"contentType" : $('#contentType').val(),
			"encriptado" : $('#encriptado').val(),
			"extension" : $('#extension').val(),
			"nombre" : $('#nombre').val(),
			"oidFichero" : $('#oidFichero').val(),
			"tamano" : $('#tamano').val()	,
			"idFicheroPlantilla0A7":$('#idFicheroPlantilla0A7').val(),
			"id0A7": $('#id0A7').val(),
			"nombrePlantilla0A7": $('#nombrePlantilla0A7_detail_table').val(),
			"varPlantilla0A7": $('#varPlantilla0A7_detail_table').val()
		};
		$("#nombrePlantilla0A7_detail_table").rules("add", "required");
		if ($("#subidaFicheroPid_form").valid()){
			$.ajax({
			   	 type: 'POST'
			   	 ,url: '/aa79bItzulnetWar/administracion/datosmaestros/plantillas/crearActualizarPlantilla'
			 	 ,dataType: 'json'
			 	 ,contentType: 'application/json' 
			     ,data: $.toJSON(plantilla)
			     ,async: false
			   	 ,success:function(){
			   		$("#plantillas").rup_table("filter");
			   		$('#plantillas_detail_div').rup_dialog("close");
			   		$('#plantillas_detail_div').rup_dialog("destroy");
					mostrarMensajeFeedback("plantillas_feedback",$.rup.i18n.app.mensajes.guardadoCorrecto,"ok");
			   		
			   	}
		   	 	,error: function(){	  
					mostrarMensajeFeedback("plantillas_detail_feedback",$.rup.i18n.app.mensajes.errorGuardandoDatos,"error"); 	
			   	 }
			 });
		}		
		
	});	

		
});