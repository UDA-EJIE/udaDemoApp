jQuery(function($){
	
	
	$("#alumno").rup_jqtable({
		
		url: "../alumno",
		colNames: [
			$.rup.i18n.app.GRID_alumno.id,
			$.rup.i18n.app.GRID_alumno.usuario,
			$.rup.i18n.app.GRID_alumno.nombre,
//					$.rup.i18n.app.GRID_alumno.apellido1,
//					$.rup.i18n.app.GRID_alumno.apellido2,
			$.rup.i18n.app.GRID_alumno.dni,
			$.rup.i18n.app.GRID_alumno.importeMatricula,
			"provincia",
			"prueba"
		],
		colModel: [
					{ name: "id",
						label: "id",
						index: "id",
						width: "150",
						hidden:true,
					},
					{ name: "usuario",
						label: "usuario",
						index: "usuario",
						width: "150",
					},
					{ name: "nombreCompleto",
						label: "nombreCompleto",
						index: "apellido1",
						width: "150",
						formatter:function (cellval, opts, rwd, act) {
							return rwd.apellido1+" "+rwd.apellido2+", "+rwd.nombre;
						},
						formatterOnUpdate:function($form){
							return $("#apellido1").val()+" "+$("#apellido2").val()+", "+$("#nombre").val();
						}
					},
					{ name: "dni",
						label: "dni",
						index: "dni",
						width: "150",
					},
					{ name: "importeMatricula",
						label: "importeMatricula",
						index: "importe_matricula",
						width: "150",
						formatter:'currency',
						formatoptions:{decimalSeparator:",", thousandsSeparator: ",", decimalPlaces: 2, suffix: " € "}
					},
//							{ name: "municipio.id",
//								label: "municipio.id",
//								index: "municipio.id",
//								width: "150",
//								editable: true,
//								edittype: "text",
//								rupType: "combo",
//								editoptions:{
//									source:"",
//									sourceParam : {label:"dsO", value:"id"}
//								},
//								formatoptions:{decimalSeparator:",", thousandsSeparator: ",", decimalPlaces: 2, prefix: "€ "}
//							},
					{ name: "municipio.dsO",
						label: "municipio.dsO",
						index: "municipio.dsO",
						width: "150",
						sortable:false,
						updateFromDetail:function($form){
							return $("#municipio").rup_combo("label");
						},
					},
					{ name: "campo.valor",
						sortable:false,
						label: "campo.valor",
						index: "campo.valor"
					}
		        ],
        primaryKey:["id"],
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"contextMenu",
        	"fluid",
        	"filter",
        	"search"
//        	,"report"
        ],
        rowNum:10, 
        rowList:[10,20,30], 
        sortname: 'id',
        formEdit:{
        	addEditOptions:{
        		width:1100
        	},
        	detailForm: "#alumno_detail_div",
        	validate:{
    			messages:{
    				"password_confirm":$.rup.i18n.app.alumno.password_confirm,
    				"email_confirm":$.rup.i18n.app.alumno.email_confirm
    			},
    			rules:{
//    				"nombre":{required:true},
//    				"apellido1":{required:true},
//    				"usuario":{required:true},
//    				"password":{required:function(){
//    					if ($("#divOldPassword").is(":hidden")) {
//    						return true;
//    					}else{
//    						if ($("#oldPassword").val()!==''){
//    							return true;
//    						}else{
//    							return false;
//    						}
//    					}
//    				}},
//    				"importeMatricula":{number:true},
//    				"password_confirm":{equalTo:"#password"},
//    				"dni":{required:true, dni:true},
//    				"email":{email:true},
//    				"email_confirm":{equalTo:"#email"},
//    				"pais":{required:true},
//    				"autonomia":{required:true},
//    				"provincia":{required:true}
    			}
    		},
        },
        filter:{
        	validate:{
        		rules:{
        			"dni":{dni:true},
    			}
        	}
        },
        search:{
        	validate:{
        		rules:{
        			"dni":{dni:true},
    			}
        	}
        }
//        ,report: options_table_report
		
	}).on({"rupTable_afterFormFillDataServerSide": function(event, xhr, $detailFormToPopulate, ajaxOptions){
		var $self = $(this);
		if ($self.data("settings").opermode === 'edit') {
        	if (xhr.nombreImagen!==undefined && xhr.nombreImagen!==null && xhr.imagen!==''){
				$("#imagen").attr("src","http://desarrollo.jakina.ejiedes.net:7001/x21aMantenimientosWar/administracion/alumno/imagen/"+xhr.id+"?" + new Date());
			}else{
				$("#imagen").attr("src",$.rup.STATICS+"/x21a/images/no_picture.gif");
			}
        }
		
		$("#email_confirm").val($("#email").val());
		
		$self.rup_jqtable("updateSavedData", function(savedData){
			savedData["imagenAlumno"] = '';
			savedData["oldPassword"] = '';
			savedData["password"] = '';
			savedData["password_confirm"] = '';
			savedData["email_confirm"] = $("#email_confirm").val();
			savedData["calle.id_label"] = '';
		});
		
	},"jqGridAddEditBeforeShowForm":function(event, $form, frmoper){
		var $self = $(this);
		if (frmoper === 'edit') {
        	$("#divOldPassword").show();
        	
        	if($("#pais").rup_combo("getRupValue")==="108"){
				$("#divDireccionExtranjera").hide();
				$("#divDireccionPais").show();
				$("#divAutonomia").show();
			}else{
				$("#divDireccionExtranjera").show();
				$("#divDireccionPais").hide();
				$("#divAutonomia").hide();
			}
        }else{
			$("#pais").rup_combo("select","108");
			$("#divDireccionExtranjera").hide();
			$("#divDireccionPais").show();
			$("#divOldPassword").hide();
			$("#imagen").attr("src",$.rup.STATICS+"/x21a/images/no_picture.gif");
        }
		
		
	},
	"jqGridAddEditAfterSubmit": function(event, data, postdata, frmoper){
		postdata = $.rup_utils.unnestjson(postdata);
	}
	
	});

	
	
	
	$('#sexo').rup_combo({
		source : [
			{i18nCaption: "masculino", value:"M"},
			{i18nCaption: "femenino", value:"F"}
		],
		i18nId:"sexo"
	});
	
	jQuery("#pais").rup_combo({
		source : "../../nora/pais",
		sourceParam : {label:"dsO", value:"id"},
		blank : "",
		width : 400,
		loadFromSelect:true,
		change:function(event,elem){
			if(elem.value==="108"){
				$("#divDireccionExtranjera").hide();
				$("#divDireccionPais").show();
				$("#divAutonomia").show();
			}else{
				$("#divDireccionExtranjera").show();
				$("#divDireccionPais").hide();
				$("#divAutonomia").hide();
			}
		}
		
	});
	
	jQuery("#autonomia").rup_combo({
		source : "../../nora/autonomia",
		sourceParam : {label:"dsO", value:"id"},
		width : 400,
		blank : "",
		loadFromSelect:true
	});
	
	jQuery("#provincia").rup_combo({
		parent: ["autonomia"],
		source : "../../nora/provincia",
		firstLoad:[{"value":"01","label":"Alava/Araba"},{"value":"20","label":"Gipuzkoa"},{"value":"48","label":"Bizkaia"}],
		sourceParam : {label:"dsO", value:"id"},
		width : 400,
		blank : ""
	});
	
	jQuery("#municipio").rup_combo({
		source : "../../nora/municipio",
		sourceParam : {label:"dsO", value:"id"},
		parent: ["provincia"],
		width : 400,
		blank : "",
		select: function(){
			$('#municipio_dsO').val(jQuery("#municipio").rup_combo('label'))}
	});
	
	jQuery("#calle").rup_autocomplete({
		source : "../../nora/calle",
		sourceParam : {label:"dsO", value:"id"},
		minLength: 4
	});
	
	$("#fechaNacimiento").rup_date();
	
//	$("#capa1").rup_dialog({type: $.rup.dialog.DIV});
//	$("#capa2").rup_dialog({type: $.rup.dialog.DIV});
//	$("#capa3").rup_dialog({type: $.rup.dialog.DIV});
//	$("#capa4").rup_dialog({type: $.rup.dialog.DIV});
	
//	$("#botonCapa").bind("click",function(){
//		$("#capa1").rup_dialog({type: $.rup.dialog.DIV});
//	});
//	
//	$("#botonCapa1").bind("click",function(){
//		$("#capa2").rup_dialog({type: $.rup.dialog.DIV});
//	});
//	
//	$("#botonCapa2").bind("click",function(){
//		$("#capa3").rup_dialog({type: $.rup.dialog.DIV});
//	});
//	
//	$("#botonCapa3").bind("click",function(){
//		$("#capa4").rup_dialog({type: $.rup.dialog.DIV});
//	});
//	
//	$("#contentLayer").css("opacity","100");
	
	
//	
//	$("#GRID_alumno").rup_grid({
//		hasMaint: true,
//		url: "../alumno",
//		pagerName: "pager",
//		rowNum: "10",
//		width: "auto",
//		sortorder: "asc",
//		sortname: "id",
//		colNames: [
//			$.rup.i18n.app.GRID_alumno.id,
//			$.rup.i18n.app.GRID_alumno.usuario,
//			$.rup.i18n.app.GRID_alumno.nombre,
////			$.rup.i18n.app.GRID_alumno.apellido1,
////			$.rup.i18n.app.GRID_alumno.apellido2,
//			$.rup.i18n.app.GRID_alumno.dni,
//			$.rup.i18n.app.GRID_alumno.importeMatricula,
//			"provincia",
//			"prueba"
//		],
//		colModel: [
//			{ name: "id",
//				label: "id",
//				index: "id",
//				width: "150",
//				editable: false,
//				hidden:true,
//				edittype: "text"
//			},
//			{ name: "usuario",
//				label: "usuario",
//				index: "usuario",
//				width: "150",
//				editable: true,
//				edittype: "text"
//			},
//			{ name: "nombreCompleto",
//				label: "nombreCompleto",
//				index: "nombreCompleto",
//				width: "150",
//				editable: true,
//				edittype: "text",
//				formatter:function (cellval, opts, rwd, act) {
//					return rwd.apellido1+" "+rwd.apellido2+", "+rwd.nombre;
//				},
//				formatterOnUpdate:function($form){
//					return $("#apellido1").val()+" "+$("#apellido2").val()+", "+$("#nombre").val();
//				}
//			},
////			{ name: "nombre",
////				label: "nombre",
////				index: "nombre",
////				width: "150",
////				editable: true,
////				edittype: "text"
////			},
////			{ name: "apellido1",
////				label: "apellido1",
////				index: "apellido1",
////				width: "150",
////				editable: true,
////				edittype: "text"
////			},
////			{ name: "apellido2",
////				label: "apellido2",
////				index: "apellido2",
////				width: "150",
////				editable: true,
////				edittype: "text"
////			},
//			{ name: "dni",
//				label: "dni",
//				index: "dni",
//				width: "150",
//				editable: true,
//				edittype: "text"
//			},
//			{ name: "importeMatricula",
//				label: "importeMatricula",
//				index: "importeMatricula",
//				width: "150",
//				editable: true,
//				edittype: "text",
//				formatoptions:{decimalSeparator:",", thousandsSeparator: ",", decimalPlaces: 2, prefix: "€ "}
//			},
////			{ name: "municipio.id",
////				label: "municipio.id",
////				index: "municipio.id",
////				width: "150",
////				editable: true,
////				edittype: "text",
////				rupType: "combo",
////				editoptions:{
////					source:"",
////					sourceParam : {label:"dsO", value:"id"}
////				},
////				formatoptions:{decimalSeparator:",", thousandsSeparator: ",", decimalPlaces: 2, prefix: "€ "}
////			},
//			{ name: "municipio.dsO",
//				label: "municipio.dsO",
//				index: "municipio.dsO",
//				width: "150",
//				editable: true,
//				edittype: "text",
//				updateFromDetail:function($form){
//					return $("#municipio").rup_combo("label");
//				},
//				formatoptions:{decimalSeparator:",", thousandsSeparator: ",", decimalPlaces: 2, prefix: "€ "}
//			},
//			{ name: "campo.valor",
//				label: "campo.valor",
//				index: "campo.valor"
//			}
//        ]
//		
//	});
//	

//	$("#alumno").rup_maint({
//		jQueryGrid: "GRID_alumno",
//		primaryKey: "id",
//		modelObject: "Alumno",
//		detailButtons: $.rup.maint.detailButtons.SAVE,
//		searchForm: "searchForm",
//		showMessages: true,
//		detailForm:"detalleAlumnoForm",
////		fluidOffset : 0,
//		validate:{
//			messages:{
//				"password_confirm":$.rup.i18n.app.alumno.password_confirm,
//				"email_confirm":$.rup.i18n.app.alumno.email_confirm
//			},
//			rules:{
//				"nombre":{required:true},
//				"apellido1":{required:true},
//				"usuario":{required:true},
//				"password":{required:function(){
//					if ($("#divOldPassword").is(":hidden")) {
//						return true;
//					}else{
//						if ($("#oldPassword").val()!==''){
//							return true;
//						}else{
//							return false;
//						}
//					}
//				}},
////				"password":{required:true},
//				"importeMatricula":{number:true},
//				"password_confirm":{equalTo:"#password"},
//				"dni":{required:true, dni:true},
//				"email":{email:true},
//				"email_confirm":{equalTo:"#email"},
//				"pais":{required:true},
//				"autonomia":{required:true},
//				"provincia":{required:true}
//			}
//		},
//		detailDivWidth:"1100",
//		onbeforeDetailShow:function(mnt, xhr) {
//			if (this.prop.MODO === 'new') {
//				$("#pais").rup_combo("select","108");
//				$("#divDireccionExtranjera").hide();
//				$("#divDireccionPais").show();
//				$("#divOldPassword").hide();
//				$("#imagen").attr("src",$.rup.STATICS+"/x21a/images/no_picture.gif");
//            }else{
//            	$("#divOldPassword").show();
//            	
//            	if($("#pais").rup_combo("getRupValue")==="108"){
//    				$("#divDireccionExtranjera").hide();
//    				$("#divDireccionPais").show();
//    				$("#divAutonomia").show();
//    			}else{
//    				$("#divDireccionExtranjera").show();
//    				$("#divDireccionPais").hide();
//    				$("#divAutonomia").hide();
//    			}
//            	
//            	            	
//            	if (xhr.nombreImagen!==undefined && xhr.nombreImagen!==null && xhr.imagen!==''){
//    				$("#imagen").attr("src","http://desarrollo.jakina.ejiedes.net:7001/x21aMantenimientosWar/administracion/alumno/imagen/"+xhr.id+"?" + new Date());
//    			}else{
//    				$("#imagen").attr("src",$.rup.STATICS+"/x21a/images/no_picture.gif");
//    			}
//            }
//			
//		}
//	});
	
});