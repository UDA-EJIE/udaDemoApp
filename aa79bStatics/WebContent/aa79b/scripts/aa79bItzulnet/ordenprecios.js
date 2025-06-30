var initFormDetalle = '';
var initFormTarif = '';
var initFormCuantiasInter = '';

/* OTROS DATOS DE TARIFICACION - INICIO */
function cargaOtrosDatosTarif(id){
	jQuery.ajax({
		type: "GET",
		url:"/aa79bItzulnetWar/tarifexptradrev/"+id,
		dataType: "json",
		cache: false,
		async: false,
		success:function(data){			
			$('#idOrden_tarificacionexp_table').val(id);
			if (data != null){
				$('#porRecargoDif_detail_table').val(data.porRecargoDif);
				$('#porRecargoUrg_detail_table').val(data.porRecargoUrg);	
				$('#precioMinimo_detail_table').val(data.precioMinimo);
				$('#numPalabrasTarifConcor_detail_table').val(data.numPalabrasTarifConcor);
				$('#porPalabraConcor95100_detail_table').val(data.porPalabraConcor95100);
				$('#porPalabraConcor8594_detail_table').val(data.porPalabraConcor8594);
				$('#porPalabraConcor084_detail_table').val(data.porPalabraConcor084);
				$('#existeReg_detail_table').val(data.existeReg);
			}else {
				clearValidation('#tarificacionexp_detail_form');
				$('#existeReg_detail_table').val("false");
			}											
			initFormTarif = $("#tarificacionexp_detail_form").rup_form("formSerialize");	
		}
	});
}
/* OTROS DATOS DE TARIFICACION - FIN */

/* CUANTIAS DE INTERPRETACION - INICIO */
function cargaCuantiasInterpretacion(id){
	jQuery.ajax({
		type: "GET",
		url:"/aa79bItzulnetWar/cuantiainter/"+id,
		dataType: "json",
		cache: false,
		async: false,
		success:function(data){			
			$('#idOrden_cuantiainter_table').val(id);
				    
			if (data != null){
				$('#precMinCae_detail_table').val(data.precMinCae);
				$('#precMinNoCae_detail_table').val(data.precMinNoCae);
	
				$('#precJornCongresoEu_detail_table').val(data.precJornCongresoEu);
				$('#precMediaCongresoEu_detail_table').val(data.precMediaCongresoEu);
				$('#precHoraCongresoEu_detail_table').val(data.precHoraCongresoEu);
				
				$('#precJornCongresoNoEu_detail_table').val(data.precJornCongresoNoEu);
				$('#precMediaCongresoNoEu_detail_table').val(data.precMediaCongresoNoEu);
				$('#precHoraCongresoNoEu_detail_table').val(data.precHoraCongresoNoEu);
				
				$('#jornCompHorasDesde_detail_table').val(data.jornCompHorasDesde);
				$('#jornCompHorasHasta_detail_table').val(data.jornCompHorasHasta);
				$('#jornMediaHorasDesde_detail_table').val(data.jornMediaHorasDesde);
				$('#jornMediaHorasHasta_detail_table').val(data.jornMediaHorasHasta);
				
				$('#precHoraReunionCae_detail_table').val(data.precHoraReunionCae);
				$('#precHoraReunionNoCae_detail_table').val(data.precHoraReunionNoCae);
				
				$('#existeRegCuantiaInter_detail_table').val(data.existeReg);
			}else {
				clearValidation('#cuantiainter_detail_form');
				$('#existeRegCuantiaInter_detail_table').val("false");
			}		
			initFormCuantiasInter = $("#cuantiainter_detail_form").rup_form("formSerialize");						
		}
	});
}
/* CUANTIAS DE INTERPRETACION - FIN */


function showDetalleForm(id){
	ivaHidden(false);
	clearValidation('#datosGeneralesform');
	clearValidation('#tarificacionexp_detail_form');
	clearValidation('#cuantiainter_detail_form');
	
	if(id !== ''){
		$('#idOrden_detail_filter_table').val(id);
		$('#idOrdenCuantiaTrad_detail_filter_table').val(id);
		$('#idOrdenCuantiaRev_detail_filter_table').val(id);
		
		$('#ivapreciospublicos').rup_table('filter');
		$('#cuantiatrad').rup_table('filter');
		$('#cuantiarev').rup_table('filter');

		$('#id_detail_form').val($("#preciospublicos").rup_table("getCol", id, "id"));
		$('#titulo_detail_form').val($("#preciospublicos").rup_table("getCol", id, "titulo"));
		$('#urlOrden_detail_form').val($("#preciospublicos").rup_table("getCol", id, "urlOrden"));
		$('#fechaVigor_detail_form').rup_date("setDate", $("#preciospublicos").rup_table("getCol", id, "fechaVigor"));
		$('#fechaFinVigor_detail_form').rup_date("setDate", $("#preciospublicos").rup_table("getCol", id, "fechaFinVigor"));
		
		enableButton('preciospublicosdetalle_toolbar-rightButtons');				
		
		if($("#preciospublicos").rup_table("getCol", id, "indVigor") === ''){
			$('#indVigor_detail_form').bootstrapSwitch('setState', false);
		}else{
			$('#indVigor_detail_form').bootstrapSwitch('setState', true);
		}
		
		ivaHidden(true);
		$('#nuevoIvaDiv').removeClass('in');
		$('#nuevoIvaDiv').attr('style', "display: none");
		setTimeout(function() {
			$('#nuevoIvaDiv').addClass('collapsed');
			}, 500);
		
		$("#accordionPreciosPublicos").rup_accordion("enable");
		
		
		/* OTROS DATOS DE TARIFICACION - INICIO: carga inicial*/	
		cargaOtrosDatosTarif(id);
		/* OTROS DATOS DE TARIFICACION - FIN */
		/* CUANTIAS DE INTERPRETACION - INICIO: carga inicial*/	
		cargaCuantiasInterpretacion(id);
		/* CUANTIAS DE INTERPRETACION - FIN */
		
	}else{
		$('#id_detail_form').val('');
		$('#indVigor_detail_form').bootstrapSwitch('setState', false);
		$("#accordionPreciosPublicos").rup_accordion("disable");
		$('#existeReg_detail_table').val("false");
		$('#existeRegCuantiaInter_detail_table').val("false");
		//Aunque sea un alta necesito serializar los formularios vacíos...
		initFormCuantiasInter = $("#cuantiainter_detail_form").rup_form("formSerialize");
		initFormTarif = $("#tarificacionexp_detail_form").rup_form("formSerialize");
		disableButton('preciospublicosdetalle_toolbar-rightButtons');		
		
		$('#nuevoIvaDiv').removeAttr('style');
		setTimeout(function() {
			$('#nuevoIvaDiv').removeClass('collapsed');
			$('#nuevoIvaDiv').addClass('in');
			}, 500);
	}
	
	closeFeedbacks();
	$("#accordionPreciosPublicos").rup_accordion("activate", false);
	
	$('#divPreciosPublicos').removeClass('in');
	setTimeout(function() {
		$('#divPreciosPublicos').addClass('collapsed');
		$('#divPreciosPublicosDetalle').removeClass('collapsed');
		$('#divPreciosPublicosDetalle').addClass('in');
		}, 500);
	
	//Para comprob cambios en el form detalle
	initFormDetalle = $("#datosGeneralesform").rup_form("formSerialize");	
	
	$('#datosGeneralesform :input:visible:enabled:first').focus();
}

function establecerCamposVuelta(){
	$('#idOrden_detail_filter_table').val('');
	$('#idOrdenCuantiaTrad_detail_filter_table').val('');
	$('#idOrdenCuantiaRev_detail_filter_table').val('');
	$('#idOrden_tarificacionexp_table').val('');
	$('#idOrden_cuantiainter_table').val('');
	
	
	$("#preciospublicos").trigger('reloadGrid');
	$('#divPreciosPublicosDetalle').removeClass('in');
	$('#nuevoIvaDiv').removeAttr('style');
	$('#nuevoIvaDiv').removeClass('in');
	ivaHidden(true);
	initFormDetalle = '';
	initFormTarif = '';
	initFormCuantiasInter = '';	
	setTimeout(function() {
		$('#divPreciosPublicosDetalle').addClass('collapsed');
		$('#divPreciosPublicos').removeClass('collapsed');
		$('#divPreciosPublicos').addClass('in');
		$('#nuevoIvaDiv').addClass('collapsed');
		}, 500);
	
	setFocusFirstInput("#preciospublicos_filter_form");
}

function showOrdenPrecios(){
	//Comprobar si hay cambios en el form detalle
	if ( (initFormDetalle !== $("#datosGeneralesform").rup_form("formSerialize"))
			|| (initFormCuantiasInter !== $("#cuantiainter_detail_form").rup_form("formSerialize"))
			|| (initFormTarif !== $("#tarificacionexp_detail_form").rup_form("formSerialize")) 
			)
	{
		$.rup_messages("msgConfirm", {
			title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
			message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
			OKFunction: function(){								
				establecerCamposVuelta();
			}
		});			
	}else{
		establecerCamposVuelta();
	}
	
}

function closeFeedbacks(){
	$("#ivapreciospublicos_feedback").rup_feedback("close");
	$("#preciospublicosdetalle_feedback").rup_feedback("close");
	$('#cuantiatrad_feedback').rup_feedback("close");
	$('#cuantiarev_feedback').rup_feedback("close");
}

function ivaHidden(hidden){
	if(hidden){
		$("#porIva_detail_form").prop("type", "hidden");
		$("#fechaDesdeVigencia_detail_form").prop("type", "hidden");
	}else{
		$("#porIva_detail_form").prop("type", "text");
		$("#fechaDesdeVigencia_detail_form").prop("type", "text");
	}
}


jQuery(function($){
	
	setFocusFirstInput("#preciospublicos_filter_form");
	
	/* MANTENIMIENTO GENERAL - INICIO */
	var estadoOption = {
			loadFromSelect: true
			,width: "150"
			,ordered: false	
			,rowStriping: true
		};
	jQuery('#indVigor_filter_table').rup_combo(estadoOption);

	$("#preciospublicos").rup_table({
		url: "../ordenprecios",
		toolbar: {
			 id: "preciospublicos_toolbar"
			 ,newButtons : [      
					{obj : {
						i18nCaption:  $.rup.i18nParse($.rup.i18n.base,"rup_table.new")
						,css: "fa fa-file-o"
						,index: 0
					 }
					 ,json_i18n : $.rup.i18n.app.simpelMaint
					 ,click : 
						 function(){
						 	showDetalleForm('');
						}
					},
					{obj : {
						i18nCaption: $.rup.i18nParse($.rup.i18n.base,"rup_table.modify")
						,css: "fa fa-pencil-square-o"
						,index: 0
					 }
					 ,json_i18n : $.rup.i18n.app.simpelMaint
					 ,click : 
						 function(e){
						 	if(!$('#preciospublicos').rup_table("isSelected")){
								e.preventDefault();
								$.rup_messages("msgAlert", {
									message: $.rup.i18n.app.comun.warningSeleccion
								});		
								return false;
							 }else{
								 showDetalleForm($('#preciospublicos').rup_table("getSelectedRows"));
							 }
						}
					}
				]
			 ,defaultButtons:{
				 add : false
				,edit : false
				,cancel : false
				,save : false
				,clone : false
				,"delete" : false
				,filter : true
			 }
			},
		colNames: [
			"",
			txtTitulo,
			txtFechaEntrada,
			txtEnVigor,
			txtFechaFin,
			txtIva,
			txtVigenciaIva,
			""
		],
		colModel: [
			{ 	name: "id", 
			 	label: "label.id",
				align: "",
				index : "ID",
				editable: false, 
				fixed: false, 
				hidden: true, 
				resizable: false, 
				sortable: false
			},
			{ 	name: "titulo", 
			 	label: "label.titulo",
			 	index : "TITULO030",
				align: "", 
				width: 200, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "fechaVigor", 
			 	label: "label.fechaEntrada",
			 	index : "FECHAVIGOR030",
			 	align: "",
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "indVigor", 
			 	label: "label.enVigor",
			 	index : "INDVIGOR030",
				align: "center", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: formatterCheck
			},
			{ 	name: "fechaFinVigor", 
			 	label: "label.fechaFin",
			 	index : "FECHAFINVIGOR030",
			 	align: "",
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "ivaVigente.porIva", 
			 	label: "label.iva",
			 	index : "IVAPORIVA031",
				align: "right", 
				width: 75, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "ivaVigente.vigencia", 
			 	label: "label.vigenciaIva",
			 	index : "ivaVigente.vigencia",
				align: "", 
				width: 175, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "urlOrden", 
			 	label: "label.url",
			 	index : "URLORDEN030",
				editable: false, 
				fixed: false, 
				hidden: true, 
				resizable: false, 
				sortable: false
			}
        ],

        model:"OrdenPrecios",
        usePlugins : [ 
			"toolbar",
			"responsive", 
			"fluid",
			"filter"
			],
		primaryKey: "id",
		sortname: "id",
		sortorder: "asc",
		loadOnStartUp: true,
        formEdit:{
        	detailForm: "#preciospublicos_detail_div"
        },
        ondblClickRow: function() {
        	showDetalleForm($('#preciospublicos').rup_table("getSelectedRows"));
        }
	});
	
	$('#preciospublicos_feedback').rup_feedback({
		block : false
	});
	/* MANTENIMIENTO GENERAL - FIN */
	
	/* FORMULARIO DETALLE - INICIO */
	$("#preciospublicosdetalle_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,css: "fa fa-arrow-left"
				,click : 
					 function(e){
						e.preventDefault();
						e.stopImmediatePropagation();
					 	showOrdenPrecios();
					}
			},
			{id:"reports", i18nCaption:$.rup.i18n.app.comun.exportar, right:true,
				buttons:[
					{ i18nCaption: $.rup.i18n.app.tabla.pdf
						,css: "fa fa-file-pdf-o"
							,right:true
							,click : 
								 function(){
									var idOrden = $('#id_detail_form').val();
									window.location.href="/aa79bItzulnetWar/administracion/configuraciontarifas/ordenprecios/generarPdf/"+idOrden;
								}
					}
				 ]}
		]
	});
	
	jQuery('#preciospublicosdetalle_toolbar\\#\\#'+ $.rup.i18n.app.boton.volver).addClass('izda');
	
	var acordeonOption = {
			animated: "bounceslide",
			active: false,
			heightStyle: "content",
			collapsible: true
		};
	jQuery('#accordionPreciosPublicos').rup_accordion(acordeonOption);
	
	var abrirAcordeon = false;
	var nuevoIndexSeleccionado;
	
	$( "#accordionPreciosPublicos" ).rup_accordion({
		
		beforeActivate: function( event, ui ) {
			if (ui.newHeader.size()){ //Comprobar que no un replegado del que está abierto...
				nuevoIndexSeleccionado = ($(ui.newHeader)[0].id).substring(6) -1 ;
			}
			if(abrirAcordeon){
				abrirAcordeon = false
			}else if(initFormTarif !== $("#tarificacionexp_detail_form").rup_form("formSerialize")){
				event.preventDefault();
				event.stopImmediatePropagation();
				$.rup_messages("msgConfirm", {
					title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
					message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
					OKFunction: function(){						
						cargaOtrosDatosTarif($('#idOrden_detail_filter_table').val());
						abrirAcordeon = true;						
						$("#accordionPreciosPublicos").rup_accordion('activate',  nuevoIndexSeleccionado);						
					}					
				});			
			}else if(initFormCuantiasInter !== $("#cuantiainter_detail_form").rup_form("formSerialize")){
				event.preventDefault();
				event.stopImmediatePropagation();
				$.rup_messages("msgConfirm", {
					title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
					message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
					OKFunction: function(){						
						cargaCuantiasInterpretacion($('#idOrden_detail_filter_table').val());
						abrirAcordeon = true;						
						$("#accordionPreciosPublicos").rup_accordion('activate',  nuevoIndexSeleccionado);						
					}					
				});			
			}
	  }
	});
	
	/* FORMULARIO DETALLE - FIN */
	
	/* DATOS GENERALES - INICIO */
	$('#preciospublicosdetalle_feedback').rup_feedback({
		block : false,
		delay : 3000
	});
	
	$("#datosGeneralesform").rup_form({
		url: "../ordenprecios",
		dataType: "json",
		feedback:$("#preciospublicosdetalle_feedback"),
		type: "POST",
		success: function(data){
			ivaHidden(true);
			$('#nuevoIvaDiv').removeClass('in');
			$('#nuevoIvaDiv').attr('style', "display: none");
			setTimeout(function() {
				$('#nuevoIvaDiv').addClass('collapsed');
				}, 500);
			
			$("#accordionPreciosPublicos").rup_accordion("enable");
			$('#idOrden_detail_filter_table').val(data.id);
			$('#idOrdenCuantiaTrad_detail_filter_table').val(data.id);
			$('#idOrdenCuantiaRev_detail_filter_table').val(data.id);
			$('#idOrden_tarificacionexp_table').val(data.id);
			$('#idOrden_cuantiainter_table').val(data.id);			
			$('#id_detail_form').val(data.id);
			$('#ivapreciospublicos').rup_table('filter');
			$('#cuantiatrad').rup_table('filter');
			$('#cuantiarev').rup_table('filter');
			
			$("#preciospublicosdetalle_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.guardadoCorrecto"), "ok");
			
			//Para comprob cambios en el form detalle
			initFormDetalle = $("#datosGeneralesform").rup_form("formSerialize");
			initFormTarif = $("#tarificacionexp_detail_form").rup_form("formSerialize");	
			initFormCuantiasInter = $("#cuantiainter_detail_form").rup_form("formSerialize");			
		},
		validate:{ 
			rules:{
				"titulo":{ required: true, maxlength: 250 },
				"fechaVigor":{ required: true, date: true },
				"fechaFinVigor" : {date: true, fechaHastaMayor:"fechaVigor"},
				"urlOrden":{ required: true, maxlength: 250, url: true },
				"ivaVigente.porIva":{required: true, range: [0, 100]},
				"ivaVigente.fechaDesdeVigencia":{required: true}
			},
			showFieldErrorAsDefault: false,
	        showErrorsInFeedback: true,
	        showFieldErrorsInFeedback: false
		},
		beforeSubmit: function(){
			return comprobarOrdenes();
		}			
	});
	
	fnFechaDesdeHasta("fechaVigor_detail_form", "fechaFinVigor_detail_form");
	
	$("#datosGeneralesform_detail_button_save").button().click(function(){
		$("#datosGeneralesform").submit();
    });
	
	$("#datosGeneralesform_detail_link_cancel").click(function(){
		if(initFormDetalle !== $("#datosGeneralesform").rup_form("formSerialize")){
			$.rup_messages("msgConfirm", {
				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
				OKFunction: function(){
					initFormDetalle = "";					
					showDetalleForm($('#idOrden_detail_filter_table').val());
				}
			});			
		}
    });
	
	$('#fechaDesdeVigencia_detail_form').rup_date({
		
	});
	/* DATOS GENERALES - FIN */
	
	/* IVA - INICIO */
	$("#ivapreciospublicos").rup_table({
		url: "../ivaprecios",
		toolbar: {
			 id: "ivapreciospublicos_toolbar"
				 ,defaultButtons:{
					 add : true
					,edit : true
					,cancel : false
					,save : false
					,clone : false
					,"delete" : false
					,filter : false
					
				 }
		},
		colNames: [
			txtIva,
			txtVigenciaIva,
			txtEnVigor
		],
		colModel: [
			{ 	name: "porIva", 
			 	label: "label.iva",
			 	index: "PORIVA031",
				align: "right", 
				width: 50, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "vigencia", 
			 	label: "label.vigenciaIva",
				align: "", 
				width: 250, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "indVigente", 
			 	label: "label.enVigor",
			 	index: "INDVIGENTE031",
				align: "", 
				width: 150,  
				fixed: false,
				sortable: true,
				formatter: function(cellvalue) {
					var descVigente = $.rup.i18nParse($.rup.i18n.app, "comun.no");
					if(cellvalue === 'S'){
						descVigente = $.rup.i18nParse($.rup.i18n.app, "comun.si");
					}
					return descVigente.toUpperCase();
				}
			}
        ],
        model:"IvaPrecios",
        usePlugins : [
        	"formEdit",
			"toolbar",
			"feedback",
			"responsive", 
			"fluid",
			"filter"
			],
		primaryKey: "id",
		sortname: "id",
		sortorder: "asc",
		loadOnStartUp: false,
        formEdit:{
        	detailForm: "#ivapreciospublicos_detail_div",
    		validate:{ 
         		showErrorsInFeedback: true,
                showFieldErrorsInFeedback: false,
    			rules:{
    				"porIva":{required: true, range: [0, 100]},
    				"fechaDesdeVigencia":{required: true, date: true},
					"fechaHastaVigencia" : {date: true, fechaHastaMayor:"fechaDesdeVigencia"},
				}
    		},
    		addEditOptions: {
    			reloadAfterSubmit: true,
    			beforeShowForm: function(){
      				$('#idOrdenIva_detail_table').val($('#idOrden_detail_filter_table').val());
      				$('#indVigente_detail_table').bootstrapSwitch('setState', $('#indVigente_detail_table').is(':checked'));
      				$("#indVigente_detail_table").bootstrapSwitch('setDisabled',false);
      				$("#ivapreciospublicos").rup_table("updateSavedData", function(savedData){
      					savedData["idOrden"]=$('#idOrdenIva_detail_table').val();      					
      				});
      				return true;
      			}
    		},
    		editOptions: {
    			reloadAfterSubmit: true,
    			beforeShowForm: function(){
      				$('#idOrdenIva_detail_table').val($('#idOrden_detail_filter_table').val());
      				$('#indVigente_detail_table').bootstrapSwitch('setState', $('#indVigente_detail_table').is(':checked'));
      				var ids = $("#ivapreciospublicos").rup_table("getDataIDs");
      				if(ids.length === 1){
      					$("#indVigente_detail_table").bootstrapSwitch('setDisabled',true);
      				}else{
      					$("#indVigente_detail_table").bootstrapSwitch('setDisabled',false);
      				}
      				return true;
      			}
    		}
        } 
	});
	
	$('#ivapreciospublicos_feedback').rup_feedback({
		block : false
	});
	
	fnFechaDesdeHasta("fechaDesdeVigencia_detail_table", "fechaHastaVigencia_detail_table");
	
	/* IVA - FIN */
	
	/* SERVICIOS TRADUCCIÓN - INICIO */
	$("#cuantiatrad").rup_table({
		url: "../cuantiatrad",
		toolbar: {
			 id: "cuantiatraddec_toolbar"
			 ,defaultButtons:{
				 add : false
				,edit : true
				,cancel : false
				,save : false
				,clone : false
				,"delete" : false
				,filter : true
			 }
		},
		colNames: [
			txtRelevancia,
			txtTarifaEsEu,
			txtTarifaEuEs,
			"",
			""
		],
		colModel: [
			{ 	name: "tipoRelevancia.descRelevanciaEu", 
			 	label: "label.relevancia",
			 	index: "DESCRELEVANCIAEU010",
				align: "", 
				width: 150, 
				editable: false, 
				fixed: false
			},
			{ 	name: "tarifaEsEu", 
			 	label: "label.tarifaEsEu",
			 	index: "TARIFAESEU033",
				align: "center", 
				width: 150, 
				editable: true, 
				fixed: false
			},
			{ 	name: "tarifaEuEs", 
			 	label: "label.tarifaEuEs",
			 	index: "TARIFAEUES033",
			 	align: "center", 
				width: 150,  
				editable: true, 
				fixed: false,
			},
			{ 	name: "idOrden", 
			 	label: "idOrden",
			 	index: "",
			 	align: "", 
				editable: true, 
				fixed: false,
				hidden: true
			},
			{ 	name: "idTipoRelevancia", 
			 	label: "idTipoRelevancia",
			 	align: "", 
				editable: false, 
				fixed: false,
				hidden: true
			}
        ],
        model:"CuantiaTrad",
		usePlugins:[
			"feedback",
			"toolbar",
			"responsive", 
			"fluid",
			"filter",
			"formEdit"
		],
		primaryKey: ["idOrden", "idTipoRelevancia"],
		sortname: 'IDTIPORELEVANCIA010',
		loadOnStartUp: false,
		formEdit:{
        	detailForm: "#cuantiatrad_detail_div",
        	validate:{
    			showErrorsInFeedback: true,
                showFieldErrorsInFeedback: false,
    			rules:{
    				"tarifaEsEu":{required:true, customDecimal: ["0", "9,9999", 0, 9.9999]},
    				"tarifaEuEs":{required:true, customDecimal: ["0", "9,9999", 0, 9.9999]}
    			}
        	},
    		addEditOptions: {
    			reloadAfterSubmit: true,
    			fillDataMethod: "serverSide",
    			beforeShowForm: function(){
      				$('#idOrdenCuantiaTrad_detail_table').val($('#idOrden_detail_filter_table').val());
      				
      				$("#cuantiatrad").rup_table("updateSavedData", function(savedData){
      					savedData["tarifaEuEs"]=$("#tarifaEuEsTrad_detail_table").val();
      					savedData["tarifaEsEu"]=$("#tarifaEsEuTrad_detail_table").val();      						
      				});
      				
      				return true;
      			}
    		}
        }
	});
	
	$('#cuantiatrad_feedback').rup_feedback({
		block : false
	});
	/* SERVICIOS TRADUCCIÓN - FIN */
	
	/* SERVICIOS REVISIÓN - INICIO */
	$("#cuantiarev").rup_table({
		url: "../cuantiarev",
		toolbar: {
			 id: "cuantiarev_toolbar"
			 ,defaultButtons:{
				 add : false
				,edit : true
				,cancel : false
				,save : false
				,clone : false
				,"delete" : false
				,filter : true
			 }
		},
		colNames: [
			txtRelevancia,
			txtTarifaEsEu,
			txtTarifaEuEs,
			"",
			""
		],
		colModel: [
			{ 	name: "tipoRelevancia.descRelevanciaEu", 
			 	label: "label.relevancia",
			 	index: "DESCRELEVANCIAEU010",
				align: "", 
				width: 150, 
				editable: false, 
				fixed: false
			},
			{ 	name: "tarifaEsEu", 
			 	label: "label.tarifaEsEu",
			 	index: "TARIFAESEU034",
				align: "center", 
				width: 150, 
				editable: true
			},
			{ 	name: "tarifaEuEs", 
			 	label: "label.tarifaEuEs",
			 	index: "TARIFAEUES034",
			 	align: "center", 
				width: 150,  
				editable: true
			},
			{ 	name: "idOrden", 
			 	label: "idOrden",
			 	align: "", 
				editable: true, 
				hidden: true
			},
			{ 	name: "idTipoRelevancia", 
			 	label: "idTipoRelevancia",
			 	align: "", 
				editable: false, 
				fixed: false,
				hidden: true
			}
        ],
        model:"CuantiaRev",
		usePlugins:[
			"feedback",
			"toolbar",
			"responsive", 
			"fluid",
			"filter",
			"formEdit"
		],
		primaryKey: ["idOrden", "idTipoRelevancia"],
		sortname: 'IDTIPORELEVANCIA010',
		loadOnStartUp: false,
		formEdit:{
        	detailForm: "#cuantiarev_detail_div",
        	validate:{
    			showErrorsInFeedback: true,
                showFieldErrorsInFeedback: false, 
    			rules:{
    				"tarifaEsEu":{required:true, customDecimal: ["0", "9,9999", 0, 9.9999]},
    				"tarifaEuEs":{required:true, customDecimal: ["0", "9,9999", 0, 9.9999]}
    			}
        	},
    		addEditOptions: {
    			reloadAfterSubmit: true,
    			fillDataMethod: "serverSide",
    			beforeShowForm: function(){
      				$('#idOrdenCuantiaRev_detail_table').val($('#idOrden_detail_filter_table').val());
      				return true;
      			}
    		}
        }
	});
	
	$('#cuantiarev_feedback').rup_feedback({
		block : false
	});
	/* SERVICIOS REVISIÓN - FIN */
	
	/* OTROS DATOS DE TARIFICACION - INICIO */
	$("#tarificacionexp_toolbar").rup_toolbar({
		buttons: [
			{
				id : "tarificacionexp_detail_button_save",
				i18nCaption : $.rup.i18nParse($.rup.i18n.base, "rup_global.save"),
				css : "fa fa-floppy-o",
				click : function(event) {
					event.preventDefault();
					event.stopImmediatePropagation();
					$(this).parent().siblings("form").submit();
				}
			},
			{
				id : "tarificacionexp_detail_link_cancel",
				i18nCaption : $.rup.i18nParse($.rup.i18n.base, "rup_global.cancel"),
				css : "fa fa-close",
				click : function(event) {
					event.preventDefault();
					event.stopImmediatePropagation();
					if(initFormTarif !== $("#tarificacionexp_detail_form").rup_form("formSerialize")){
						$.rup_messages("msgConfirm", {
							title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
							message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
							OKFunction: function(){
								initFormTarif = "";
								cargaOtrosDatosTarif($('#idOrden_detail_filter_table').val());
							}
						});			
					}
				}
			}
		]
		
	});
	
	$("#tarificacionexp_detail_form").rup_form({
		url: "/aa79bItzulnetWar/tarifexptradrev",
		dataType: "json",		
		feedback:$("#preciospublicosdetalle_feedback"),		
		type: "POST",
		success: function(){
			$("#preciospublicosdetalle_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.guardadoCorrecto"), "ok");
			$('#existeReg_detail_table').val("true");
			initFormTarif = $("#tarificacionexp_detail_form").rup_form("formSerialize");
			
		},
		validate:{ 
			rules:{
				"porRecargoDif":{ required: true, maxlength: 3, integer: true, range: [0, 100] },
				"porRecargoUrg":{ required: true, maxlength: 3, integer: true, range: [0, 100] },				
				"precioMinimo":{ required: true, customDecimal: ["0", "999.999,99", 0, 999999.99] },
				"numPalabrasTarifConcor":{ required: true, maxlength: 6, integer: true },
				"porPalabraConcor95100":{ required: true, maxlength: 3, integer: true, range: [0, 100]},
				"porPalabraConcor8594":{ required: true, maxlength: 3, integer: true, range: [0, 100] },
				"porPalabraConcor084":{ required: true, maxlength: 3, integer: true, range: [0, 100] },								
				},
				showFieldErrorAsDefault: false,
		        showErrorsInFeedback: true,
		        showFieldErrorsInFeedback: false
		}
	});	
	/* OTROS DATOS DE TARIFICACION - FIN */
	
	
	
	/* CUANTIAS DE INTERPRETACION - INICIO */
	
	$("#cuantiainter_toolbar").rup_toolbar({
		buttons: [
			{
				id : "cuantiainter_detail_button_save",
				i18nCaption : $.rup.i18nParse($.rup.i18n.base, "rup_global.save"),
				css : "fa fa-floppy-o",
				click : function(event) {
					event.preventDefault();
					event.stopImmediatePropagation();
					$(this).parent().siblings("form").submit();
				}
			},
			{
				id : "cuantiainter_detail_link_cancel",
				i18nCaption : $.rup.i18nParse($.rup.i18n.base, "rup_global.cancel"),
				css : "fa fa-close",
				click : function(event) {
					event.preventDefault();
					event.stopImmediatePropagation();
					if(initFormCuantiasInter !== $("#cuantiainter_detail_form").rup_form("formSerialize")){
						$.rup_messages("msgConfirm", {
							title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
							message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
							OKFunction: function(){
								initFormCuantiasInter = "";
								cargaCuantiasInterpretacion($('#idOrden_detail_filter_table').val());
							}
						});			
					}
				}
			}
		]
		
	});


	$("#cuantiainter_detail_form").rup_form({
		url: "/aa79bItzulnetWar/cuantiainter",
		dataType: "json",		
		feedback:$("#preciospublicosdetalle_feedback"),		
		type: "POST",
		success: function(){
			$("#preciospublicosdetalle_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.guardadoCorrecto"), "ok");
			$('#existeRegCuantiaInter_detail_table').val("true");
			initFormCuantiasInter = $("#cuantiainter_detail_form").rup_form("formSerialize");
			
		},
		validate:{ 
			rules:{  		    
				"jornCompHorasDesde":{ required: true, maxlength: 1, integer: true },
				"jornCompHorasHasta":{ required: true, maxlength: 1, integer: true, mayorQueCampo: ["jornCompHorasDesde"] },				
				"jornMediaHorasDesde":{ required: true, maxlength: 1, integer: true },
				"jornMediaHorasHasta":{ required: true, maxlength: 1, integer: true, mayorQueCampo: ["jornMediaHorasDesde"] },
				
				"precMinCae":{ required: true, customDecimal: ["0", "9.999,99", 0, 9999.99]},
				"precMinNoCae":{ required: true, customDecimal: ["0", "9.999,99", 0, 9999.99] },
				
				"precHoraReunionCae":{ required: true, customDecimal: ["0", "999,99", 0, 999.99] },				
				"precHoraReunionNoCae":{ required: true, customDecimal: ["0", "999,99", 0, 999.99] },
				
				"precJornCongresoEu":{ required: true, customDecimal: ["0", "9.999,99", 0, 9999.99] },		
				"precMediaCongresoEu":{ required: true, customDecimal: ["0", "9.999,99", 0, 9999.99] },		
				"precHoraCongresoEu":{ required: true, customDecimal: ["0", "999,99", 0, 999.99] },	
				
				"precJornCongresoNoEu":{ required: true, customDecimal: ["0", "9.999,99", 0, 9999.99] },		
				"precMediaCongresoNoEu":{ required: true, customDecimal: ["0", "9.999,99", 0, 9999.99] },		
				"precHoraCongresoNoEu":{ required: true, customDecimal: ["0", "999,99", 0, 999.99] },		
				
				},
				showFieldErrorAsDefault: false,
		        showErrorsInFeedback: true,
		        showFieldErrorsInFeedback: false
		}
	});
	
	
	/* CUANTIAS DE INTERPRETACION - FIN */
	
	function comprobarOrdenes(){
		var error = true;
		var enVigor = $('#indVigor_detail_form').bootstrapSwitch('state');
		var idOrden = $('#id_detail_form').val();
		if(enVigor){
			$.ajax({
			   	 type: 'POST'
			   	 ,url: '/aa79bItzulnetWar/administracion/configuraciontarifas/ordenprecios/comprobarOrdenes'
			   	 ,dataType: 'json'
			   	 ,data: $.toJSON(idOrden)
				 ,contentType: 'application/json' 
			     ,async: false
			     ,cache: false
			   	 ,success:function(check){
			   		if (check) {
			   			$("#preciospublicosdetalle_feedback").rup_feedback("set", $.rup.i18n.app.validaciones.ordenEnVigor, "error");		
			   			error = false;
			   		} else {
			   			error =  true;
			   		}
			   	 }
		  	 	,error: function(){
		   			$("#preciospublicosdetalle_feedback").rup_feedback("set", $.rup.i18n.app.validaciones.ordenEnVigor, "error");
		   			error = false;
			   	 }
			 });
		}
		return error;
	}
	
});