/* HACER USO DEL BUSCADOR DE PERSONAS - INICIO 
 * 
 * 1 - Incluir en el -includes.jsp (o si no la tiene en la misma jsp): 
 *		<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadorpersonas.js" type="text/javascript"></script>
 *
 * 2 - En la jsp, inlcuir:
 * 		<div id="buscadorPersonas" style="display:none"></div>
 * 
 * 3 - En la js de la pantalla incluir:
 * 
 * 		$("#buscadorPersonas").buscador_personas(); -- crea el buscador
 * 		
 * 
 * 		$("#buscadorPersonas").buscador_personas("open"); -- abre el buscador 
 *   
 * HACER USO DEL BUSCADOR DE PERSONAS - FIN */ 
var idBuscadorPersonas;	
var gruposTrabajoResponsableUsuarioBuscPers = [];
var colNames;
var colModel;
function cambiarLabelTipoEntidad(tipoEntidad){
	if('B'.localeCompare(tipoEntidad)===0){
		$('#'+idBuscadorPersonas+'labelEntidadBuscadorPersonas_filter_table').text($.rup.i18n.app.label.entidad);
	}else if('E'.localeCompare(tipoEntidad)===0){
		$('#'+idBuscadorPersonas+'labelEntidadBuscadorPersonas_filter_table').text($.rup.i18n.app.label.departamento);
	}else if('L'.localeCompare(tipoEntidad)===0){
		$('#'+idBuscadorPersonas+'labelEntidadBuscadorPersonas_filter_table').text($.rup.i18n.app.label.empresa);
	}else{
		$('#'+idBuscadorPersonas+'labelEntidadBuscadorPersonas_filter_table').text($.rup.i18n.app.label.todasTipoEntidad);
	}
}

function creaComboMultipleGruposTrabajo(){
	$('#gruposTrabajo_filter_table').rup_combo({
		source : "/aa79bItzulnetWar/administracion/grupostrabajo/listCombo/A",
		sourceParam : {
			value: ""+"id"+"",
			label : "descEu" 
		} 
		,submitAsString: true
		   ,rowStriping: true
		   ,multiselect: true
		,width: "400",
		open: function(){
			 jQuery('#gruposTrabajo_filter_table-menu').width(jQuery('#gruposTrabajo_filter_table-button').innerWidth());
	    },
	    onLoadSuccess: function() {
	    	if(gruposTrabajoResponsableUsuarioBuscPers && gruposTrabajoResponsableUsuarioBuscPers.length){
	    		$("#gruposTrabajo_filter_table").rup_combo( "selectLabel",gruposTrabajoResponsableUsuarioBuscPers );
	    		$("#"+idBuscadorPersonas+"buscador").rup_table("filter");
	    	}
	    	
	    }
	});	
}

function obtenerGruposDeTrabajoUsuarioSesion(){
	//obtenemos grupos de trabajo del usuario en sesion
	var idTipoExp;
	if(typeof(tipoExp) != "undefined" && tipoExp){
		if("I".localeCompare(tipoExp)==0){
			idTipoExp = 0;
		}
		else{
			idTipoExp = 1;
		}
		gruposTrabajoResponsableUsuarioBuscPers = [];
		$.rup_ajax({
		   	 url: "/aa79bItzulnetWar/administracion/grupostrabajo/findAllGruposTrabajoConIdTipoExp/"+idTipoExp
		   	 ,dataType: "json"
		   		,submitAsString: true
		   	 ,contentType: 'application/json'
		   	 ,success:function(data, textStatus, jqXHR){
		   		if(data && data.length){
		   			for(var i = 0; i < data.length ; i++){
		   				gruposTrabajoResponsableUsuarioBuscPers.push(data[i].descEu);
		   			}
		   		}
		   		creaComboMultipleGruposTrabajo();
		   	 },
		   	 error: function (XMLHttpRequest, textStatus, errorThrown){
				alert($.rup.i18n.app.mensajes.errorGenericoCapas);
		   	 }
		 });
	}else{
		$.rup_ajax({
		   	 url: "/aa79bItzulnetWar/administracion/grupostrabajo/findAllGruposTrabajo"
		   	 ,dataType: "json"
		   		,submitAsString: true
		   	 ,contentType: 'application/json'
		   	 ,success:function(data, textStatus, jqXHR){
		   		if(data && data.length){
		   			for(var i = 0; i < data.length ; i++){
		   				gruposTrabajoResponsableUsuarioBuscPers.push(data[i].descEu);
		   			}
		   		}
		   		creaComboMultipleGruposTrabajo();
		   	 },
		   	 error: function (XMLHttpRequest, textStatus, errorThrown){
				alert($.rup.i18n.app.mensajes.errorGenericoCapas);
		   	 }
		 });
	}
}


(function ( $ ) {
 
	$.fn.buscador_personas = function( options ) {
 
		var buscador_personas_methods = {
		        open : function( ) {  

		        	this.rup_dialog("open");
		        	$("#"+idBuscadorPersonas+"buscador").rup_table("clearGridData", true);
		        },
		    };
		var tipoEntidad = {
			    ENTIDAD: 'B',
			    DEPARTAMENTO: 'E',
			    EMPRESA: 'L'
			};
		var tipoPersona = {
				SOLICITANTE: 'S',
				RECEPTOR: 'R',
				PERSONAL_IZO: 'I',
				PROVEEDOR: 'P'
		}
		
		if ( buscador_personas_methods[options] ) {
            return buscador_personas_methods[ options ].apply( this, Array.prototype.slice.call( arguments, 1 ));
        } else if ( typeof options === 'object' || ! options ) {
        	// This is the easiest way to have default options.
            var settings = $.extend({
                // These are the defaults.
                multiselect: options.multiselect,
                destino: options.destino,
                anyo: options.anyo,
                numExp: options.numExp,
                callback: function(event, object) {
                	
                }
            }, options );
            var personasSeleccionadas = [];
            var datosPersona = {
            		rol: settings.destino,
            		anyo: settings.anyo,
            		numExp: settings.numExp
            }
         
            
            idBuscadorPersonas=this.attr("id");
            var name=this.attr("name");
            var html="<div class='aa79b-content-modal'>";
             html+="<div id='"+idBuscadorPersonas+"buscador_div' class='rup-table-container'>";
             html+="<div id='"+idBuscadorPersonas+"buscador_feedback'></div>";						
             html+="<div id='buscador_toolbar'></div>";							
             html+="<div id='contenFormularios' class='filterForm'>";
             html+="<form id='"+idBuscadorPersonas+"buscador_filter_form' Class='form-horizontal'>";				
             html+="<div id='"+idBuscadorPersonas+"buscador_filter_toolbar' class='formulario_legend'></div>";	
             html+="<fieldset id='"+idBuscadorPersonas+"buscador_filter_fieldset' class='rup-table-filter-fieldset'>";
             html+="<div class='p-2'>";
	             html+="<input type='hidden' name='entidad.tipo' id='"+idBuscadorPersonas+"entidadTipoBuscadorPersonas'></input>";
	             html+="<input type='hidden' name='rol' id='"+idBuscadorPersonas+"rolDatosPersonaBuscadorPersonas'></input>";
	             html+="<input type='hidden' name='anyo' id='"+idBuscadorPersonas+"anyoDatosPersonaBuscadorPersonas'></input>";
	             html+="<input type='hidden' name='numExp' id='"+idBuscadorPersonas+"numExpDatosPersonaBuscadorPersonas'></input>";
	             
	             if (tipoPersona.PERSONAL_IZO.localeCompare(datosPersona.rol)===0){
	            	 html+="<div class='row'>";
		             	html+="<div class='form-group col-xs-12 col-md-8'>";
				             	html+="<label for='gruposTrabajo_filter_table' class='control-label' data-i18n='label.grupoTrabajo'>".concat($.rup.i18n.app.label.grupoTrabajo,"</label>");
				             	html+="<div class='divComboW125'>";
				             		html+="<select id='gruposTrabajo_filter_table' class='form-control' name='idsGrupoTrabajo'></select>";
			             		html+="</div>";
	             		html+="</div>";
	             	 html+="</div>";
	             }else{
	            	 html+="<div class='row' id='camposTipoEntidad'>";
		             	html+="<div class='form-group col-xs-12 col-md-8' id='radioButtonsTipoEntidad'>";
				             	html+="<label for='tiposEntidad_filter_table' class='control-label' data-i18n='label.tipoEntidadGestora'>".concat($.rup.i18n.app.label.tipoEntidad,"</label>");
				             	html+="<div id='tiposEntidad_filter_table' >";
					             	html+="<div class='col-md-3'>";
						             	html+="<input type='radio' name='"+idBuscadorPersonas+"tipoEntidadBuscadorPersonas' id='"+idBuscadorPersonas+"tipoEntidadBuscadorPersonas_A' value='' data-on-text='<spring:message code='label.todas'/>";
						             	html+="<label for='"+idBuscadorPersonas+"tipoEntidadBuscadorPersonas_A' class='radio-inline rup-table-filter-fieldset' data-i18n='label.tipoEntidad'>".concat($.rup.i18n.app.label.todos,"</label>");
					             	html+="</div>";
					             	html+="<div class='col-md-3'>";
						             	html+="<input type='radio' name='"+idBuscadorPersonas+"tipoEntidadBuscadorPersonas' id='"+idBuscadorPersonas+"tipoEntidadBuscadorPersonas_B' value=".concat(tipoEntidad.ENTIDAD,"  data-on-text='<spring:message code='${tipoEntidad.label}'/>");
						             	html+="<label for='"+idBuscadorPersonas+"tipoEntidadBuscadorPersonas_B' class='radio-inline rup-table-filter-fieldset' data-i18n='label.tipoEntidad'>".concat($.rup.i18n.app.label.entidad,"</label>");
					             	html+="</div>";
					             	html+="<div class='col-md-3'>";
						             	html+="<input type='radio' name='"+idBuscadorPersonas+"tipoEntidadBuscadorPersonas' id='"+idBuscadorPersonas+"tipoEntidadBuscadorPersonas_E' value=".concat(tipoEntidad.DEPARTAMENTO,"  data-on-text='<spring:message code='${tipoEntidad.label}'/>");
						             	html+="<label for='"+idBuscadorPersonas+"tipoEntidadBuscadorPersonas_E' class='radio-inline rup-table-filter-fieldset' data-i18n='label.tipoEntidad'>".concat($.rup.i18n.app.label.departamento,"</label>");
					             	html+="</div>";
					             	html+="<div class='col-md-3'>";
						             	html+="<input type='radio' name='"+idBuscadorPersonas+"tipoEntidadBuscadorPersonas' id='"+idBuscadorPersonas+"tipoEntidadBuscadorPersonas_L' value=".concat(tipoEntidad.EMPRESA,"  data-on-text='<spring:message code='${tipoEntidad.label}'/>");
						             	html+="<label for='"+idBuscadorPersonas+"tipoEntidadBuscadorPersonas_L' class='radio-inline rup-table-filter-fieldset' data-i18n='label.tipoEntidad'>".concat($.rup.i18n.app.label.empresa,"</label>");
					             	html+="</div>";
			             		html+="</div>";
	             		html+="</div>";
	             		html+="<div class='form-group col-xs-12 col-md-5'>";
		             		html+="<label id='"+idBuscadorPersonas+"labelEntidadBuscadorPersonas_filter_table' for='"+idBuscadorPersonas+"entidadBuscadorPersonas_filter_table' class='control-label' data-i18n='label.todasTipoEntidad'>".concat($.rup.i18n.app.label.todasTipoEntidad,"</label>");
		             		html+="<input id='"+idBuscadorPersonas+"entidadBuscadorPersonas_filter_table' class='form-control' name='entidad.codigoCompleto' />";
		             	html+="</div>";
	             	 html+="</div>";
	             } 
	             html+="<div class='row'>";
	            	 html+="<div class='form-group col-xs-12 col-md-2'>";
		            		 html+="<label for='dniDatosPersonaBuscadorPersonas_filter_table' class='control-label' data-i18n='label.dni'>".concat($.rup.i18n.app.label.dni,"</label>");
	             		 	 	html+="<input type='text' name='dni' class='form-control' id='dniDatosPersonaBuscadorPersonas_filter_table' maxlength='12'/>";
	        		 html+="</div>";
		             html+="<div class='form-group col-xs-12 col-md-4'>";
			            	 html+="<label for='nombreDatosPersonaBuscadorPersonas_filter_table' class='control-label' data-i18n='label.nombre'>".concat($.rup.i18n.app.label.nombre,"</label>");
			            	 	html+="<input type='text' name='nombre' class='form-control' id='nombreDatosPersonaBuscadorPersonas_filter_table' maxlength='20'/>";
		             html+="</div>";
	             	html+="<div class='form-group col-xs-12 col-md-5'>";
	             			html+="<label for='apellidosDatosPersonaBuscadorPersonas_filter_table' class='control-label col-xs-12' data-i18n='label.nombre'>".concat($.rup.i18n.app.label.apellidos,"</label>");
	             				html+="<input type='text' name='apellidos' class='form-control' id='apellidosDatosPersonaBuscadorPersonas_filter_table' maxlength='100'/>";
	             	html+="</div>";
	             html+="</div>";
	             
	             if (tipoPersona.PERSONAL_IZO.localeCompare(datosPersona.rol)===0){
	            	 html+="<div class='row'>";
		             	html+="<div class='form-group col-xs-12 col-md-3'>";
				             	html+="<label for='perfiles_filter_table' class='control-label' data-i18n='label.perfil'>".concat($.rup.i18n.app.label.perfil,"</label>");
				             	html+="<select id='perfiles_filter_table' class='form-control' name='permisoX54'></select>";
	             		html+="</div>";
	             	 html+="</div>";
	             } else if (tipoPersona.PROVEEDOR.localeCompare(datosPersona.rol)===0){
	            	 html+="<div class='row oculto'>";
		             	html+="<div class='form-group col-xs-12 col-md-8'>";
				             html+="<label for='perfiles_filter_table' class='control-label' data-i18n='label.perfil'>".concat($.rup.i18n.app.label.perfil,"</label>");
				             html+="<input type='hidden' name='permisoX54' id='perfiles_filter_table' value='4'></input>";
	             		html+="</div>";
	             	 html+="</div>";
	             }
	             
             html+="</div>";
             html+="<div class='form-group'>";
             html+="<div id='"+idBuscadorPersonas+"buscador_filter_buttonSet' class='pull-right'>";
             html+="<button id='"+idBuscadorPersonas+"buscador_filter_filterButton' type='button' class='ui-button ui-widget ui-state-default ui-corner-all'>".concat($.rup.i18n.app.boton.filtrar,"</button>");
             html+="<a id='"+idBuscadorPersonas+"limpiarButton' href='javascript:void(0)' class='rup-enlaceCancelar'>".concat($.rup.i18n.app.boton.limpiar,"</a>");
             html+="</div>";
             html+="</div>";
             html+="</div>";
             html+="</fieldset>";
             html+="</form>";
             html+="</div>";
    		 html+="<div id='contenedor_tabla' class='table pb-2 '>";
             html+="<table id='"+idBuscadorPersonas+"buscador'></table>";
             html+="</div>";
             html+="<div id='"+idBuscadorPersonas+"buscador_pager'></div>";
             html+="</div>";
             html+="</div>";
             this.append(html);
             
            $('#'+idBuscadorPersonas+'limpiarButton').each(function() {
    			$(this).on("click", function() {
    					$("#"+idBuscadorPersonas+"buscador_filter_form").rup_form("clearForm");
	    				$("#"+idBuscadorPersonas+"rolDatosPersonaBuscadorPersonas").val(datosPersona.rol);
	    				$('#'+idBuscadorPersonas+'anyoDatosPersonaBuscadorPersonas').val(datosPersona.anyo);
	    				$('#'+idBuscadorPersonas+'numExpDatosPersonaBuscadorPersonas').val(datosPersona.numExp);
	    				if(tipoPersona.RECEPTOR.localeCompare(datosPersona.rol)==0){
//	    					clickar departamento

	                    	$('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:first').prop('disabled', true);
	                    	$('#'+idBuscadorPersonas+'tipoEntidadBuscadorPersonas_A').prop('user-select', 'none');
	                    	$('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:eq(1)').prop('disabled', true);
	                    	$('#'+idBuscadorPersonas+'tipoEntidadBuscadorPersonas_B').prop('user-select', 'none');
	                    	$('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:last').prop('disabled', true);
	                    	$('#'+idBuscadorPersonas+'tipoEntidadBuscadorPersonas_L').prop('user-select', 'none');
	                    	$('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:eq(2)').click();

	            			jQuery('#'+idBuscadorPersonas+'entidadTipoBuscadorPersonas').val(tipoEntidad.DEPARTAMENTO);
	            			$('#'+idBuscadorPersonas+'labelEntidadBuscadorPersonas_filter_table').text($.rup.i18n.app.label.departamento);
	    				} else if(tipoPersona.PROVEEDOR.localeCompare(datosPersona.rol)==0){
	    					$('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:eq(3)').click();
	    	            	$('#radioButtonsTipoEntidad').addClass('oculto');
	    					$('#'+idBuscadorPersonas+'entidadTipoBuscadorPersonas').val(tipoEntidad.EMPRESA);
	            			$('#'+idBuscadorPersonas+'labelEntidadBuscadorPersonas_filter_table').text($.rup.i18n.app.label.empresa);
	    				}else{
	    					jQuery('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:first').click();
	    				}
	    				if(tipoPersona.PERSONAL_IZO.localeCompare(datosPersona.rol)==0){
	    					$("#"+idBuscadorPersonas+"buscador").rup_table("clearGridData",true);
		    			}else{
		    				$("#"+idBuscadorPersonas+"buscador").rup_table("filter");
		    			}
	    				
	    				if(gruposTrabajoResponsableUsuarioBuscPers && gruposTrabajoResponsableUsuarioBuscPers.length){
	    		    		$("#gruposTrabajo_filter_table").rup_combo( "selectLabel",gruposTrabajoResponsableUsuarioBuscPers );
	    		    		$("#"+idBuscadorPersonas+"buscador").rup_table("filter");
	    		    	}
    			});
    		});

             //seteamos el rol de la persona para mapearlo correctamente
             $('#'+idBuscadorPersonas+'rolDatosPersonaBuscadorPersonas').val(datosPersona.rol);
             $('#'+idBuscadorPersonas+'anyoDatosPersonaBuscadorPersonas').val(datosPersona.anyo);
             $('#'+idBuscadorPersonas+'numExpDatosPersonaBuscadorPersonas').val(datosPersona.numExp);
             
             if(tipoPersona.RECEPTOR.localeCompare(datosPersona.rol)===0){
//             	clickar departamento
//             	deshabilitar checkbox
             	$('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:first').prop('disabled', true);
            	$('#'+idBuscadorPersonas+'tipoEntidadBuscadorPersonas_A').prop('user-select', 'none');
             	$('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:eq(1)').prop('disabled', true);
            	$('#'+idBuscadorPersonas+'tipoEntidadBuscadorPersonas_B').prop('user-select', 'none');
             	$('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:last').prop('disabled', true);
            	$('#'+idBuscadorPersonas+'tipoEntidadBuscadorPersonas_L').prop('user-select', 'none');
             	$('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:eq(2)').click();
             	$('#'+idBuscadorPersonas+'labelEntidadBuscadorPersonas_filter_table').text($.rup.i18n.app.label.departamento);
             } else if(tipoPersona.PROVEEDOR.localeCompare(datosPersona.rol)==0){
            	 $('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:eq(3)').click();
            	 $('#radioButtonsTipoEntidad').addClass('oculto');
            	 $('#'+idBuscadorPersonas+'entidadTipoBuscadorPersonas').val(tipoEntidad.EMPRESA);
            	 $('#'+idBuscadorPersonas+'labelEntidadBuscadorPersonas_filter_table').text($.rup.i18n.app.label.empresa);
             } else{
             	jQuery('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:first').click();
             }
             
         /*
          * Inicializacion de componentes - INICIO 
          */
            $('#'+idBuscadorPersonas+'buscador_feedback').rup_feedback({
        		block : false,
        		delay: 1000
        	});
            
            
            if (tipoPersona.PERSONAL_IZO.localeCompare(datosPersona.rol)!==0){
            	var url;
            	if (tipoPersona.PROVEEDOR.localeCompare(datosPersona.rol)===0){
            		url = "/aa79bItzulnetWar/administracion/empresasproveedoras/findEmpresasProveedoras";
            	} else if (tipoPersona.RECEPTOR.localeCompare(datosPersona.rol)==0){
            		url = "/aa79bItzulnetWar/entidad/entidadReceptor";
            	} else {
            		url = "/aa79bItzulnetWar/entidad/entidadSolicitante";
            	}
            	
            	$('#'+idBuscadorPersonas+'entidadBuscadorPersonas_filter_table').rup_autocomplete({
            		source : url,
            		sourceParam : {
            			value: "codigoCompleto",
            			label : "descAmp" + $.rup_utils.capitalizedLang()
            		},
            		getText: false,
            		menuAppendTo: $('#'+idBuscadorPersonas),
            		open : function() {
            			jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#'+idBuscadorPersonas+'entidadBuscadorPersonas_filter_table').innerWidth());
            			$('#'+idBuscadorPersonas+'entidadBuscadorPersonas_filter_table_menu').css("z-index", '999');
             			$('#'+idBuscadorPersonas+'entidadBuscadorPersonas_filter_table_menu').removeClass("ui-front");
            		}
            	});
            	
            } else {
            	creaComboPerfiles();
            	obtenerGruposDeTrabajoUsuarioSesion();
            }
            
            jQuery('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas').change(function(){
        		var parametros = $(this).val();
        		$('#'+idBuscadorPersonas+'entidadBuscadorPersonas_filter_table').rup_autocomplete("option", "extraParams", {tipo:parametros});
        		$('#'+idBuscadorPersonas+'entidadBuscadorPersonas_filter_table').rup_autocomplete("set", "", "");
        		$('#'+idBuscadorPersonas+'entidadBuscadorPersonas_filter_table_label').removeData("tmp.loadObjects.term");
        		cambiarLabelTipoEntidad($(this).val());
    			jQuery('#'+idBuscadorPersonas+'entidadTipoBuscadorPersonas').val($(this).val());
        	});

            if (tipoPersona.PERSONAL_IZO.localeCompare(datosPersona.rol)!==0){
            	colNames = [ 
        			"",
        			"",
        			"",
        			$.rup.i18nParse($.rup.i18n.app, "label.dni"),
        			$.rup.i18nParse($.rup.i18n.app, "label.nombre"),
        			$.rup.i18nParse($.rup.i18n.app, "label.apellidos"),
        			$.rup.i18nParse($.rup.i18n.app, "label.tipoEntidad"),
        			$.rup.i18nParse($.rup.i18n.app, "label.entidad")
        		];
            } else {
            	colNames = [ 
        			"",
        			"",
        			"",
        			$.rup.i18nParse($.rup.i18n.app, "label.dni"),
        			$.rup.i18nParse($.rup.i18n.app, "label.nombre"),
        			$.rup.i18nParse($.rup.i18n.app, "label.apellidos"),
        			$.rup.i18nParse($.rup.i18n.app, "label.teletrabajo"),
        			$.rup.i18nParse($.rup.i18n.app, "label.horasGestionExpedientes")
        		];
            }
            
            if (tipoPersona.PERSONAL_IZO.localeCompare(datosPersona.rol)!==0){
            	colModel = [
        			{
        				name: "apellido1",
        				hidden: true
        			},
        			{
        				name: "apellido2",
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
        				fixed:false,
        				hidden: false, 
        				resizable: true, 
        				sortable: true,
        				width: 60
        			},
        			{ 	name: "nombre", 
        			 	label: "label.nombre",
        			 	index: "NOMBRE",
        				align: "left", 
        				fixed:false,
        				hidden: false, 
        				resizable: true, 
        				sortable: true,
        				width: 100
        			},
        			{ 	name: "apellidos", 
        			 	label: "label.apellidos",
        			 	index: "APELLIDOS",
        				align: "left", 
        				fixed:false,
        				hidden: false, 
        				resizable: true, 
        				sortable: true,
        				width: 150,
        				formatter: function (cellvalue, options, rowObject){
        					return rowObject.apellido1+" "+rowObject.apellido2;
        				}
        			},
        			{ 	name: "entidad.tipoDesc", 
        			 	label: "label.tipoEntidad",
        			 	index: "TIPOENTIDADDESCEU",
        				align: "left", 
        				fixed:false,
        				hidden: false, 
        				resizable: true, 
        				sortable: true,
        				width: 80
        			},
        			{ 	name : $.rup.lang == 'es' ? "entidad.descAmpEs"
        					: "entidad.descAmpEu",
        			 	label: "label.entidad",
        			 	index : $.rup.lang == 'es' ? "DESCAMPES"
        						: "DESCAMPEU",
        				align: "left", 
        				width:240,
        				fixed:false,
        				ruptype: "combo", 
        				hidden: false, 
        				resizable: true, 
        				sortable: true
        			}
                ];
            } else {
            	colModel = [
        			{
        				name: "apellido1",
        				hidden: true
        			},
        			{
        				name: "apellido2",
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
        				fixed:false,
        				hidden: false, 
        				resizable: true, 
        				sortable: true,
        				width: 40
        			},
        			{ 	name: "nombre", 
        			 	label: "label.nombre",
        			 	index: "NOMBRE",
        				align: "left", 
        				fixed:false,
        				hidden: false, 
        				resizable: true, 
        				sortable: true,
        				width: 100
        			},
        			{ 	name: "apellidos", 
        			 	label: "label.apellidos",
        			 	index: "APELLIDOS",
        				align: "left", 
        				fixed:false,
        				hidden: false, 
        				resizable: true, 
        				sortable: true,
        				width: 150,
        				formatter: function (cellvalue, options, rowObject){
        					return rowObject.apellido1+" "+rowObject.apellido2;
        				}
        			},
        			{ 	name: "personalIZO.calendarioPersonal.descIndTeletrabajo", 
        			 	label: "label.teletrabajo",
        			 	index: "DESCINDTELETRABAJO",
        				align: "", 
        				fixed:false,
        				hidden: false, 
        				resizable: true, 
        				sortable: true,
        				width: 30
        			},
        			{ 	name: "personalIZO.calendarioPersonal.horasGestionExp", 
        			 	label: "label.horasGestionExpedientes",
        			 	index: "HORASGESTIONEXP",
        				align: "left", 
        				fixed:false,
        				hidden: false, 
        				resizable: true, 
        				sortable: true,
        				width: 100
        			}
                ];
            }
            
        	$("#"+idBuscadorPersonas+"buscador").rup_table({
        		url: "/aa79bItzulnetWar/datospersona/filterByRolPersona",
        		resizable:false,
        		rowNum: 10,
        		toolbar: {
        			 id: "buscador_toolbar"
        				 ,defaultButtons:{
        					 add : false
        					,edit : false
        					,cancel : false
        					,save : false
        					,clone : false
        					,"delete" : false
        					,filter : true
        					
        				 },
        	newButtons : [  
        		{obj : {
					i18nCaption:  $.rup.i18n.app.boton.cancelar
					,css: "fa fa-arrow-left"
					,index: 0
				}
				,json_i18n : $.rup.i18n.app.simpelMaint
				,click : 
					function(){
					$("#"+idBuscadorPersonas).rup_dialog("close");
					}
				},
				{obj : {
					i18nCaption : $.rup.i18n.app.boton.aceptar
					, css: "fa fa-floppy-o"
					, index: 1
				}
				,json_i18n : $.rup.i18n.app.simpelMaint
				,click : 
					function(){ 
    					//comprobar que se ha seleccionado algo
    					var dnis = $("#"+idBuscadorPersonas+"buscador").rup_table('getSelectedRows');
    					var jsonObject = {
    							listaDatosPersona : []
    					}
                 		if(dnis.length>=1){
                 			for(var i=0;i<dnis.length;i++){
                 				jsonObject.listaDatosPersona.push({
                 					"rol": datosPersona.rol,
                 					"dni": dnis[i]
                 				});
                 			}
                 			var personas = [];
                     		jQuery.ajax({
                     			type: "POST",
                     			url:"/aa79bItzulnetWar/datospersona/comprobarYGuardar",
                     			dataType: "json",
                     			contentType: 'application/json',
                     			data: $.toJSON(jsonObject),
                     			cache: false,
                     			success: function(data){
                     				if(data != null && data.listaDatosPersona!=null && data.listaDatosPersona.length>0){
                     					
                     					for(var i=0;i<data.listaDatosPersona.length;i++){
                     						var aux = data.listaDatosPersona[i];
                     							personas.push(aux);
                     					}

                    					//recoger los datos y devolverlos a la función de callback.
                     					if(typeof options.callback == 'function'){
                     						options.callback.call(this, personas);
                    						$("#"+idBuscadorPersonas).rup_dialog("close");
                    					}

                     				}else{
                             			$('#'+idBuscadorPersonas+'buscador_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
                     				}
                     			},error: function (){
   			    		    		$('#'+idBuscadorPersonas+'buscador_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorSeleccionPersona, "error");
   			    		    	}
                     		});

                 		}else{
                 			$('#'+idBuscadorPersonas+'buscador_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorSeleccionPersona, "error");
                 		}
    				}
				},
        		{obj : {
					i18nCaption:  $.rup.i18n.app.boton.configurarPersonal
					,right:true
					,css: "fa fa-users"
					,index: 0
				 }
				 ,json_i18n : $.rup.i18n.app.simpelMaint
				 ,click : 
					 function(){
					 	window.open(urlConfigurarPersonal);
					}
				}
    		]
        		},
        		colNames: colNames,
        		colModel: colModel,
                model:"DatosPersona",
                usePlugins:!settings.multiselect ? 
                		["feedback",
            			"toolbar",
            			"responsive",
                    	"filter",
                    	"fluid"
                     	] : 
                     	["feedback",
	        			"toolbar",
	        			"responsive",
	                	"filter",
	                	"multiselection",
                    	"fluid"
	                	]
                ,onSelectRow:function(row, selected){
                 		if(!settings.multiselect){
                 			var a = $("#"+idBuscadorPersonas+"buscador").rup_table('getSelectedRows');
                     		if(a.length>1){
                     			$("#"+idBuscadorPersonas+"buscador").rup_table("setSelection", a[0], false, false);
                     		}
                 		}
                 	},
             	multiselection:{
             			headerContextMenu_enabled: false
        			},
                multiSort: true,
        		primaryKey: ["dni"],
        		sortname: "dni",
        		sortorder: "asc",
        		loadOnStartUp: false,
           	 	filter:{
					beforeFilter: function(){
						$("#"+idBuscadorPersonas+"buscador").rup_table("resetSelection");
					}
           	 	}
        	});
        	
            /*
             * Inicializacion de componentes - FIN 
             */
        	
        	
            this.rup_dialog({
        		type: $.rup.dialog.DIV,
        		autoOpen: false,
        		width: 915,
        		maxWidth: 915,
        		minWidth: 915,
        		height: 695,
        		maxHeight: 695,
        		minHeight: 695,
        		dialogClass:"buscadorPersonas",
        		modal: true,
        		resizable: false,
        		title: $.rup.i18n.app.comun.buscadorDePersonas,
    		close: function(){
    			deseleccionarFilas();
    			$("#"+idBuscadorPersonas+"buscador_filter_form").rup_form("clearForm");
            	$("#"+idBuscadorPersonas+"rolDatosPersonaBuscadorPersonas").val(datosPersona.rol);
                $('#'+idBuscadorPersonas+'anyoDatosPersonaBuscadorPersonas').val(datosPersona.anyo);
                $('#'+idBuscadorPersonas+'numExpDatosPersonaBuscadorPersonas').val(datosPersona.numExp);
                if(tipoPersona.RECEPTOR.localeCompare(datosPersona.rol)===0){
                	$('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:first').prop('disabled', true);
                	$('#'+idBuscadorPersonas+'tipoEntidadBuscadorPersonas_A').prop('user-select', 'none');
                	$('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:eq(1)').prop('disabled', true);
                	$('#'+idBuscadorPersonas+'tipoEntidadBuscadorPersonas_B').prop('user-select', 'none');
                	$('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:last').prop('disabled', true);
                	$('#'+idBuscadorPersonas+'tipoEntidadBuscadorPersonas_L').prop('user-select', 'none');
                	$('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:eq(2)').click();
                }else{
                	jQuery('input[name='+idBuscadorPersonas+'tipoEntidadBuscadorPersonas]:first').click();
                }
                if(tipoPersona.PERSONAL_IZO.localeCompare(datosPersona.rol)===0){
	                if(gruposTrabajoResponsableUsuarioBuscPers && gruposTrabajoResponsableUsuarioBuscPers.length){
			    		$("#gruposTrabajo_filter_table").rup_combo( "selectLabel",gruposTrabajoResponsableUsuarioBuscPers );
			    	}
                }
            	$("#"+idBuscadorPersonas+"buscador").rup_table("clearGridData", true);
    		}
        	});
            
           
            
            function creaComboPerfiles(){
            	$('#perfiles_filter_table').rup_combo("destroy");
            	
        		$('#perfiles_filter_table').rup_combo({
        			source : "/aa79bItzulnetWar/datospersona/findAllPerfiles"
        			,sourceParam : {
        				value: "id",
        				label : "desc" 
        			}
        			,blank: ""
        			,width: "150"
        			,ordered: false	
        			,rowStriping: true
        			,open: function(){
        				var id = $(this).attr("id");
        		        $("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
        		    }
        		});	
        	}
            

            
            function deseleccionarFilas(){
            	var selected =  $("#"+idBuscadorPersonas+"buscador").rup_table('getSelectedRows');
				if(selected != null){
					var selectedLength = selected.length; 
					for(var k=0;k<selectedLength;k++){
							$("#"+idBuscadorPersonas+"buscador").rup_table("setSelection", selected[0], false, false);
						}
				}
            }

            return this;
        } else {
            $.error( 'Method ' +  methodOrOptions + ' does not exist on jQuery.tooltip' );
        }  
	
    };
   
}( jQuery ));