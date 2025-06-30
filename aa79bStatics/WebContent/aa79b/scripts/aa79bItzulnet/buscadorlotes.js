/* HACER USO DEL BUSCADOR DE LOTES - INICIO 
 * 
 * 1 - Incluir en el -includes.jsp (o si no la tiene en la misma jsp): 
 *		<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadorlotes.js" type="text/javascript"></script>
 *
 * 2 - En la jsp, inlcuir:
 * 		<div id="buscadorLotes" style="display:none"></div>
 * 
 * 3 - En la js de la pantalla incluir:
 * 
 * 		$("#buscadorLotes").buscador_lotes(); -- crea el buscador
 * 		
 * 
 * 		$("#buscadorLotes").buscador_lotes("open"); -- abre el buscador 
 *   
 * HACER USO DEL BUSCADOR DE LOTES - FIN */ 
var idBuscadorLotes;	
var colNames;
var colModel;

function ocultarFeedbackNoResultados(){
	if($('#noLotes_feedback').is(':visible')){
		$('#noLotes_feedback').hide();
	}
}

(function ( $ ) {
	
	$.fn.buscador_lotes = function( options ) {
 
		var buscador_lotes_methods = {
		        open : function( ) {  
		        	this.rup_dialog("open");
		        	var registrosRupTable = $("#"+idBuscadorLotes+"buscador").rup_table("getDataIDs");
		        	if (registrosRupTable.length === 0){
		        		$('#buscadorLotesbuscador_noRecords td').text($.rup.i18nParse($.rup.i18n.app,"mensajes.buscadorLotesNoRecords"));
		        	}
		        },
		    };
		
		if ( buscador_lotes_methods[options] ) {
            return buscador_lotes_methods[ options ].apply( this, Array.prototype.slice.call( arguments, 1 ));
        } else if ( typeof options === 'object' || ! options ) {
        	// This is the easiest way to have default options.
            var settings = $.extend({
                // These are the defaults.
                multiselect: options.multiselect,
                anyo: options.anyo,
                numExp: options.numExp,
                documentos: options.documentos,
                callback: function(event, object) {
                	
                }
            }, options );
            var lotesSeleccionados = [];
            var expediente = {
            		anyo: settings.anyo,
            		numExp: settings.numExp,
            		documentos: settings.documentos
            }
         	var rutaPdfAyudaLotes = STATICS + '/aa79b/files/20230111_Eskatzailea_sorta.pdf';
            
            idBuscadorLotes=this.attr("id");
            var name=this.attr("name");
            var html="<div class='aa79b-content-modal'>";
             html+="<div id='"+idBuscadorLotes+"buscador_div' class='rup-table-container'>";
             html+="<div id='"+idBuscadorLotes+"buscador_feedback'></div>";		
             html+="<div id='noLotes_feedback'></div>";	
             html+="<div id='buscador_toolbar'></div>";							
             html+="<div id='contenFormularios' class='filterForm oculto'>";
             html+="<form id='"+idBuscadorLotes+"buscador_filter_form' Class='form-horizontal'>";				
             html+="<div id='"+idBuscadorLotes+"buscador_filter_toolbar' class='formulario_legend'></div>";	
             html+="<fieldset id='"+idBuscadorLotes+"buscador_filter_fieldset' class='rup-table-filter-fieldset'>";
             html+="<div class='p-2'>";
	             html+="<input type='hidden' name='anyo' id='"+idBuscadorLotes+"anyoExpedienteBuscadorLotes'></input>";
	             html+="<input type='hidden' name='numExp' id='"+idBuscadorLotes+"numExpedienteBuscadorLotes'></input>";
	             html+="<input type='hidden' name='documentos' id='"+idBuscadorLotes+"documentosBuscadorLotes'></input>";
             html+="</div>";
             html+="<div class='form-group oculto'>";
             html+="<div id='"+idBuscadorLotes+"buscador_filter_buttonSet' class='pull-right'>";
             html+="<button id='"+idBuscadorLotes+"buscador_filter_filterButton' type='button' class='ui-button ui-widget ui-state-default ui-corner-all'>".concat($.rup.i18n.app.boton.filtrar,"</button>");
             html+="<a id='"+idBuscadorLotes+"limpiarButton' href='javascript:void(0)' class='rup-enlaceCancelar'>".concat($.rup.i18n.app.boton.limpiar,"</a>");
             html+="</div>";
             html+="</div>";
             html+="</div>";
             html+="</fieldset>";
             html+="</form>";
             html+="</div>";
    		 html+="<div id='contenedor_tabla' class='table '>";
             html+="<table id='"+idBuscadorLotes+"buscador'></table>";
             html+="</div>";
             html+="<div id='"+idBuscadorLotes+"buscador_pager'></div>";
             html+="<div id='div_aviso_lote_defecto'><p style='font-style: italic;font-size: 1.2em;'><span class='ico-ficha-encriptado'><i class='fa fa-info-circle' aria-hidden='true'></i></span> <span id='aviso_lote_defecto'></span></p></div>";
			 html+="<div id='div_ayuda_pdf_lote' class='pb-2'><a href='"+rutaPdfAyudaLotes+"'class='document-eusk' download >".concat($.rup.i18n.app.label.pdfAyudaLotes,"</a></div> ");
             html+="<div class='pb-2'><label style='color: #ba1944; font-weight:bold' class='control-label'>".concat($.rup.i18n.app.mensajes.avisoImporteLoteInsuficiente,"</label></div>");
             html+="<div id='calculo_importes_filter_div' class='form-group col-md-12' style='padding:0; float:none'>";
             html+="<fieldset id='calculo_importes_filter_fieldset' style='margin:0!important'>";
             html+="<legend>"+$.rup.i18nParse($.rup.i18n.app,"label.calculoImportes")+"</legend>";
             html+="<div class='row'>";
             html+="<div class='form-group'>";
             html+="<label class='control-label'>"+$.rup.i18nParse($.rup.i18n.app,"label.importeTotalAsignadoAlLote")+"</label>";
             html+="</div>";
             html+="</div>";
             html+="<div class='row'>";
             html+="<div class='form-group'>";
             html+="<label class='control-label'>"+$.rup.i18nParse($.rup.i18n.app,"label.importeTotalConsumido")+"</label>";
             html+="</div>";
             html+="</div>";
             html+="<div class='row'>";
             html+="<div class='form-group'>";
             html+="<label class='control-label'>"+$.rup.i18nParse($.rup.i18n.app,"label.importeTotalPrevisto")+"</label>";
             html+="</div>";
             html+="</div>";
             html+="<div class='row'>";
             html+="<div class='form-group'>";
             html+="<label class='control-label'>"+$.rup.i18nParse($.rup.i18n.app,"label.importeTotalDisponible")+"</label>";
             html+="</div>";
             html+="</div>";
             html+="</fieldset>";
             html+="</div>";
             
             html+="</div>";
             html+="</div>";
             this.append(html);
             
            $('#'+idBuscadorLotes+'limpiarButton').each(function() {
    			$(this).on("click", function() {
    					$("#"+idBuscadorLotes+"buscador_filter_form").rup_form("clearForm");
	    				$('#'+idBuscadorLotes+'anyoExpedienteBuscadorLotes').val(expediente.anyo);
	    				$('#'+idBuscadorLotes+'numExpedienteBuscadorLotes').val(expediente.numExp);
	    				$('#'+idBuscadorLotes+'documentosBuscadorLotes').val(expediente.documentos);
	                	$("#"+idBuscadorLotes+"buscador").rup_table("filter");
    			});
    		});

             //seteamos el anyo y el numero de expediente
             $('#'+idBuscadorLotes+'anyoExpedienteBuscadorLotes').val(expediente.anyo);
             $('#'+idBuscadorLotes+'numExpedienteBuscadorLotes').val(expediente.numExp);
             $('#'+idBuscadorLotes+'documentosBuscadorLotes').val(expediente.documentos);
             
         /*
          * Inicializacion de componentes - INICIO 
          */
            $('#'+idBuscadorLotes+'buscador_feedback').rup_feedback({
        		block : false,
        		delay: 1000
        	});
            $('#noLotes_feedback').rup_feedback({
            	block : false
            });
            

        	colNames = [ 
    			"",
    			"",
    			"",
    			$.rup.i18nParse($.rup.i18n.app, "label.nombre"),
    			$.rup.i18nParse($.rup.i18n.app, "label.empresaProveedora"),
    			$.rup.i18nParse($.rup.i18n.app, "label.importeTotal"),
    			$.rup.i18nParse($.rup.i18n.app, "label.importeConsumido"),
    			$.rup.i18nParse($.rup.i18n.app, "label.importePrevisto"),
    			$.rup.i18nParse($.rup.i18n.app, "label.importeDisponible"),
    			$.rup.i18nParse($.rup.i18n.app, "label.costeExpediente")
    		];
            
        	colModel = [
    			{
    				name: "idLote",
    				hidden: true
    			},
    			{
    				name: "empresasProveedoras.codigo",
    				hidden: true
    			},
    			{
    				name: "empresasProveedoras.tipo",
    				hidden: true
    			},
    			{
    				name : "nombreLote",
    				label : "label.nombre",
    				index: "NOMBRELOTE",
    				align : "left",
    				width : 50,
    				editable : false,
    				fixed : false,
					cellattr: function (rowId, tv, rawObject, cm, rdata) { 
					    return 'style="white-space: normal;"';
					}
    			}, 
    			{ 	 
    				name : $.rup.lang == 'es' ? "empresasProveedoras.descAmpEs"
    						: "empresasProveedoras.descAmpEu",
    				label : "label.empresaProv",
    				index: $.rup.lang == 'es' ? "DESCAMPES"
    						: "DESCAMPEU",
    				align : "",
    				width : 80,
    				editable : false,
    				fixed : false,
					cellattr: function (rowId, tv, rawObject, cm, rdata) { 
					    return 'style="white-space: normal;"';
					}
    			},
    			{
    				name : "importeAsignado",
    				label : "label.importeTotal",
    				index: "IMPORTEASIGNADO",
    				align : "center",
    				width : 50
    			} ,
    			{
    				name : "importeConsumido",
    				label : "label.importeConsumido",
    				index: "IMPORTECONSUMIDO",
    				align : "center",
    				width : 50
    			} ,
    			{
    				name : "importePrevisto",
    				label : "label.importePrevisto",
    				index: "IMPORTEPREVISTO",
    				align : "center",
    				width : 50
    			} ,
    			{
    				name : "importeDisponible",
    				label : "label.importeDisponible",
    				index: "IMPORTEDISPONIBLE",
    				align : "center",
    				width : 50,
    				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
					    var costeExpediente = rawObject.costeExpediente;
					    var importeDisponible = rawObject.importeDisponible;
					    if (Number(costeExpediente.replace(/\./g,"").replace(",", ".").replace("€", "")) > Number(importeDisponible.replace(/\./g,"").replace(",", ".").replace("€", ""))){
					    	return 'style="white-space: normal; color: #ba1944;"';
					    }
    					return 'style="white-space: normal;"';
					}
    			} ,
    			{
    				name : "costeExpediente",
    				label : "label.costeExpediente",
    				index: "COSTEEXPEDIENTE",
    				align : "center",
    				width : 50
    			}
            ];
            
        	$("#"+idBuscadorLotes+"buscador").rup_table({
        		url: "/aa79bItzulnetWar/lotes/filterLotesVigentesExp",
        		resizable:false,
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
					,index: 2
				}
				,json_i18n : $.rup.i18n.app.simpelMaint
				,click : 
					function(){
					$("#"+idBuscadorLotes).rup_dialog("close");
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
					
    					var ids = $("#"+idBuscadorLotes+"buscador").rup_table('getSelectedRows');
    					var listaLotes = [];
    					var idLote;
    					var nombreEmpresa;
                 		if(ids.length>=1){
                 			for(var i=0;i<ids.length;i++){
                 				idLote = ids[i];
                 				nombreEmpresa = $.rup.lang == 'es' ? "empresasProveedoras.descAmpEs"
                						: "empresasProveedoras.descAmpEu";
                 				listaLotes.push({
                 					"idLote": parseInt(idLote),
                 					"nombreLote": $("#"+idBuscadorLotes+"buscador").rup_table("getCol", idLote, "nombreLote"),
                 					"empresasProveedoras.descAmpEu": $("#"+idBuscadorLotes+"buscador").rup_table("getCol", idLote, nombreEmpresa),
                 					"empresasProveedoras.codigo": $("#"+idBuscadorLotes+"buscador").rup_table("getCol", idLote, "empresasProveedoras.codigo"),
                 					"empresasProveedoras.tipo": $("#"+idBuscadorLotes+"buscador").rup_table("getCol", idLote, "empresasProveedoras.tipo")
                 				});
                 			}
                 			
                 			if(typeof options.callback == 'function'){
         						options.callback.call(this, listaLotes);
        						$("#"+idBuscadorLotes).rup_dialog("close");
        					}

                 		}else{
                 			$('#'+idBuscadorLotes+'buscador_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorSeleccionLote, "error");
                 		}
    				}
				}
    		]
        		},
        		colNames: colNames,
        		colModel: colModel,
                model:"Lotes",
                usePlugins:!settings.multiselect ? 
                		["feedback",
            			"toolbar",
                    	"filter",
                    	"fluid"
                     	] : 
                     	["feedback",
	        			"toolbar",
	                	"filter",
	                	"multiselection",
                    	"fluid"
	                	]
                ,onSelectRow:function(row, selected){
                		if(!settings.multiselect){
                 			var a = $("#"+idBuscadorLotes+"buscador").rup_table('getSelectedRows');
                     		if(a.length>1){
                     			$("#"+idBuscadorLotes+"buscador").rup_table("setSelection", a[0], false, false);
                     		}
      					    var costeExpediente = $("#"+idBuscadorLotes+"buscador").rup_table("getCol", row, "costeExpediente");
                			var importeDisponible = $("#"+idBuscadorLotes+"buscador").rup_table("getCol", row, "importeDisponible");
                     		
                			if (Number(costeExpediente.replace(/\./g,"").replace(",", ".").replace("€", "")) > Number(importeDisponible.replace(/\./g,"").replace(",", ".").replace("€", ""))){
	                 			if ($.rup.lang === "es"){
	        						$("[id='buscador_toolbar##Aceptar']").button("disable");
		        				} else if ($.rup.lang === "eu"){
		        						$("[id='buscador_toolbar##Onartu']").button("disable");
		        				}
	                		}else{
                				if ($.rup.lang === "es"){
	        						$("[id='buscador_toolbar##Aceptar']").button("enable");
	        					} else if ($.rup.lang === "eu"){
	        						$("[id='buscador_toolbar##Onartu']").button("enable");
	        					}
                			}
                 		}
                 },
                multiSort: true,
        		primaryKey: ["idLote"],
        		sortname: "idLote",
        		sortorder: "asc",
        		loadOnStartUp: true,
        		rowNum: 30,
        		loadComplete: function(data){
        			if(!data || data.records==0){
        				//mostrar mensaje de que no hay lotes
        				$('#noLotes_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.noHayLotesParaMostrar, "alert");
        			}else{
        				ocultarFeedbackNoResultados();
        			} 
        		},
           	 	filter:{
					beforeFilter: function(){
						$("#"+idBuscadorLotes+"buscador").rup_table("resetSelection");
					}
           	 	}
        	});
        	
        	$("#buscadorLotesbuscador_pager_left").hide();
        	$("#buscadorLotesbuscador_pager_center").hide();
        	$("#buscadorLotesbuscador_pager_right").hide();
        	
        	
        	/** recuperamos información del lote que se debería asignar*/
        	$.rup_ajax({
   			 type: "GET",
   			 url: '/aa79bItzulnetWar/lotes/getLoteDefectoExp/'+expediente.anyo+'/'+expediente.numExp,
   			 dataType: "json",
   			 contentType: "application/json",
   			 cache: false,	
   			 success: function(data){	
   				 if (data){
   					 if (data.idLote != null){
						let mensaje = $.format($.rup.i18nParse($.rup.i18n.app, "mensajes.avisoLoteDefecto"), data.nombreLote); 
						if (data.comentarioEntidad){
							mensaje += '. ' + data.comentarioEntidad;
						}
						$("#div_aviso_lote_defecto").show();
   	   					$("#aviso_lote_defecto").html(mensaje)
   					 }else{
   						$("#div_aviso_lote_defecto").hide();
   	   					$("#aviso_lote_defecto").html(' ')
   					 }
   					
   	            }
   				desbloquearPantalla();
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
        		height: 'auto',
        		minHeight: 695,
        		dialogClass:"buscadorLotes",
        		modal: true,
        		resizable: false,
        		title: $.rup.i18n.app.comun.buscadorDeLotes,
    		close: function(){
    			deseleccionarFilas();
    			$("#"+idBuscadorLotes+"buscador_filter_form").rup_form("clearForm");
                $('#'+idBuscadorLotes+'anyoExpedienteBuscadorLotes').val(expediente.anyo);
                $('#'+idBuscadorLotes+'numExpedienteBuscadorLotes').val(expediente.numExp);
                $('#'+idBuscadorLotes+'documentosBuscadorLotes').val(expediente.documentos);
            	$("#"+idBuscadorLotes+"buscador").rup_table("clearGridData", true);
    		}
        	});
            
            function deseleccionarFilas(){
            	var selected =  $("#"+idBuscadorLotes+"buscador").rup_table('getSelectedRows');
				if(selected != null){
					var selectedLength = selected.length; 
					for(var k=0;k<selectedLength;k++){
							$("#"+idBuscadorLotes+"buscador").rup_table("setSelection", selected[0], false, false);
						}
				}
            }

            return this;
        } else {
            $.error( 'Method ' +  methodOrOptions + ' does not exist on jQuery.tooltip' );
        }
	
    };
   
}( jQuery ));