jQuery(function($){
	
	$("#GRID_alumno").rup_grid({
		hasMaint: true,
		url: "../alumno",
		pagerName: "pager",
		rowNum: "10",
		sortorder: "asc",
		sortname: "id",
		colNames: [
			$.rup.i18n.app.GRID_alumno.id,
			$.rup.i18n.app.GRID_alumno.usuario,
			$.rup.i18n.app.GRID_alumno.nombre,
			$.rup.i18n.app.GRID_alumno.apellido1,
			$.rup.i18n.app.GRID_alumno.apellido2,
			$.rup.i18n.app.GRID_alumno.dni,
			$.rup.i18n.app.GRID_alumno.importeMatricula
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
				formatoptions:{decimalSeparator:",", thousandsSeparator: ".", decimalPlaces: 2, prefix: "$ "}
			}
        ]
		
	});
	

	$("#alumno").rup_maint({
		jQueryGrid: "GRID_alumno",
		primaryKey: "id",
		modelObject: "Alumno",
		detailButtons: $.rup.maint.detailButtons.SAVE,
		searchForm: "searchForm",
		showMessages: true,
		detailForm:"detalleAlumnoForm",
		validation:{
			messages:{
				"password_confirm":$.rup.i18n.app.alumno.password_confirm,
				"email_confirm":$.rup.i18n.app.alumno.email_confirm
			},
			rules:{
				"nombre":{required:true},
				"apellido1":{required:true},
				"usuario":{required:true},
				"password":{required:function(){
					if ($("#divOldPassword").is(":hidden")) {
						return true;
					}else{
						if ($("#oldPassword").val()!==''){
							return true;
						}else{
							return false;
						}
					}
				}},
//				"password":{required:true},
				"importeMatricula":{number:true},
				"password_confirm":{equalTo:"#password"},
				"dni":{required:true, dni:true},
				"email":{email:true},
				"email_confirm":{equalTo:"#email"},
				"pais":{required:true},
				"autonomia":{required:true},
				"provincia":{required:true}
			}
		},
		detailDivWidth:"1100",
		onbeforeDetailShow:function(mnt, xhr) {
			if (this.prop.MODO === 'new') {
				$("#pais").rup_combo("select","108");
				$("#divDireccionExtranjera").hide();
				$("#divDireccionPais").show();
				$("#divOldPassword").hide();
				$("#imagen").attr("src",$.rup.STATICS+"/x21a/images/no_picture.gif");
            }else{
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
            	
            	            	
            	if (xhr.nombreImagen!==undefined && xhr.nombreImagen!==null && xhr.imagen!==''){
    				$("#imagen").attr("src","http://desarrollo.jakina.ejiedes.net:7001/x21aMantenimientosWar/administracion/alumno/imagen/"+xhr.id+"?" + new Date());
    			}else{
    				$("#imagen").attr("src",$.rup.STATICS+"/x21a/images/no_picture.gif");
    			}
            }
			
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
	
	jQuery("#municipio").rup_autocomplete({
		source : "../../nora/municipio",
		sourceParam : {label:"dsO", value:"id"},
		minLength: 4
	});
	
	$("#fechaNacimiento").rup_date();
	
});