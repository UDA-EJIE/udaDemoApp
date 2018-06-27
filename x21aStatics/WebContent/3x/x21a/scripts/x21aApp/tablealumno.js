jQuery(function($){
	$("#GRID_alumno").rup_table({
		hasMaint: true,
		url: "../administracion/alumno",
		colNames: [
			$.rup.i18n.app.GRID_alumno.id,
			$.rup.i18n.app.GRID_alumno.usuario,
			$.rup.i18n.app.GRID_alumno.nombre,
//			$.rup.i18n.app.GRID_alumno.apellido1,
//			$.rup.i18n.app.GRID_alumno.apellido2,
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
				editable: false,
				hidden:true,
				edittype: "text"
			},
			{ name: "usuario",
				label: "usuario",
				index: "usuario",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "nombreCompleto",
				label: "nombreCompleto",
				index: "nombreCompleto",
				width: "150",
				editable: true,
				edittype: "text",
				formatter:function (cellval, opts, rwd, act) {
					return rwd.apellido1+" "+rwd.apellido2+", "+rwd.nombre;
				},
				formatterOnUpdate:function($form){
					return $("#apellido1").val()+" "+$("#apellido2").val()+", "+$("#nombre").val();
				}
			},
//			{ name: "nombre",
//				label: "nombre",
//				index: "nombre",
//				width: "150",
//				editable: true,
//				edittype: "text"
//			},
//			{ name: "apellido1",
//				label: "apellido1",
//				index: "apellido1",
//				width: "150",
//				editable: true,
//				edittype: "text"
//			},
//			{ name: "apellido2",
//				label: "apellido2",
//				index: "apellido2",
//				width: "150",
//				editable: true,
//				edittype: "text"
//			},
			{ name: "dni",
				label: "dni",
				index: "dni",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "importeMatricula",
				label: "importeMatricula",
				index: "importeMatricula",
				width: "150",
				editable: true,
				edittype: "text",
				formatoptions:{decimalSeparator:",", thousandsSeparator: ",", decimalPlaces: 2, prefix: "€ "}
			},
//			{ name: "municipio.id",
//				label: "municipio.id",
//				index: "municipio.id",
//				width: "150",
//				editable: true,
//				edittype: "text",
//				rupType: "combo",
//				editoptions:{
//					source:"",
//					sourceParam : {label:"dsO", value:"id"}
//				},
//				formatoptions:{decimalSeparator:",", thousandsSeparator: ",", decimalPlaces: 2, prefix: "€ "}
//			},
			{ name: "municipio.dsO",
				label: "municipio.dsO",
				index: "municipio.dsO",
				width: "150",
				editable: true,
				edittype: "text",
				updateFromDetail:function($form){
					return $("#municipio").rup_combo("label");
				},
				formatoptions:{decimalSeparator:",", thousandsSeparator: ",", decimalPlaces: 2, prefix: "€ "}
			},
			{ name: "campo.valor",
				label: "campo.valor",
				index: "campo.valor"
			}
        ],
        editOptions:{
        	width:1000
        },
        detailForm:"#detalleAlumno",
        fluidBaseLayer:"#alumno",
        rowNum:10, 
        rowList:[10,20,30], 
        pager: "#pager", 
        searchForm: "#searchForm",
        sortname: 'id',
        sortable: true, // permite realizar drag and drop de las columnas.
        resizable: true, // permite redimensionar el grid mediante el ratón.
        toolbar: {id: "toolbar"}
		
	}).on("jqGridAddEditBeforeShowForm jqGridAddEditAfterFillData", function(event, $form, oper){
		if (oper === 'edit') {
			var nombreImagen = $("#nombreImagen").val();
			$("#divOldPassword", $form).show();
	    	
	    	if($("#pais", $form).rup_combo("getRupValue")==="108"){
				$("#divDireccionExtranjera", $form).hide();
				$("#divDireccionPais, #divAutonomia", $form).show();
			}else{
				$("#divDireccionExtranjera", $form).show();
				$("#divDireccionPais, #divAutonomia", $form).hide();
			}
	    	
	    	if (nombreImagen!==undefined && nombreImagen!==null && nombreImagen!==''){
				$("#imagen", $form).attr("src","http://local.ejiedes.net:7001/x21aMantenimientosWar/administracion/alumno/imagen/"+$("#id", $form).val()+"?" + new Date());
			}else{
				$("#imagen", $form).attr("src",$.rup.STATICS+"/x21a/images/no_picture.gif");
			}
	    }else{
	    	$("#divDireccionPais", $form).show();
	    	$("#divDireccionExtranjera, #divOldPassword", $form).hide();
	    	$("#pais", $form).rup_combo("select","108");
			$("#imagen", $form).attr("src",$.rup.STATICS+"/x21a/images/no_picture.gif");
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
		source : $.rup.CTX_PATH + "nora/pais",
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
		source : $.rup.CTX_PATH + "nora/autonomia",
		sourceParam : {label:"dsO", value:"id"},
		width : 400,
		blank : "",
		loadFromSelect:true
	});
	
	jQuery("#provincia").rup_combo({
		parent: ["autonomia"],
		source : $.rup.CTX_PATH + "nora/provincia",
		firstLoad:[{"value":"01","label":"Alava/Araba"},{"value":"20","label":"Gipuzkoa"},{"value":"48","label":"Bizkaia"}],
		sourceParam : {label:"dsO", value:"id"},
		width : 400,
		blank : ""
	});
	
	jQuery("#municipio").rup_combo({
		source : $.rup.CTX_PATH + "nora/municipio",
		sourceParam : {label:"dsO", value:"id"},
		parent: ["provincia"],
		width : 400,
		blank : ""
	});
	
	jQuery("#calle").rup_autocomplete({
		source : $.rup.CTX_PATH + "nora/calle",
		sourceParam : {label:"dsO", value:"id"},
		minLength: 4
	});
	
	$("#fechaNacimiento").rup_date();
	
//	$("#capa1").rup_dialog({type: $.rup.dialog.DIV});
//	$("#capa2").rup_dialog({type: $.rup.dialog.DIV});
//	$("#capa3").rup_dialog({type: $.rup.dialog.DIV});
//	$("#capa4").rup_dialog({type: $.rup.dialog.DIV});
	
	$("#botonCapa").bind("click",function(){
		$("#capa1").rup_dialog({type: $.rup.dialog.DIV});
	});
	
	$("#botonCapa1").bind("click",function(){
		$("#capa2").rup_dialog({type: $.rup.dialog.DIV});
	});
	
	$("#botonCapa2").bind("click",function(){
		$("#capa3").rup_dialog({type: $.rup.dialog.DIV});
	});
	
	$("#botonCapa3").bind("click",function(){
		$("#capa4").rup_dialog({type: $.rup.dialog.DIV});
	});
	
	$("#contentLayer").css("opacity","100");
});