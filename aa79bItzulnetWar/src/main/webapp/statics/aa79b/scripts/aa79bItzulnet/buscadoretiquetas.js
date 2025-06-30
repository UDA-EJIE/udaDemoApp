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
 * 		$("#buscadorPersonas").buscador_etiquetas(); -- crea el buscador
 * 		
 * 
 * 		$("#buscadorPersonas").buscador_etiquetas("open"); -- abre el buscador 
 *   
 * HACER USO DEL BUSCADOR DE PERSONAS - FIN */ 
var idBuscadorEtiquetas;	
var gruposTrabajoResponsableUsuarioBuscPers = [];
var colNames;
var colModel;





(function ( $ ) {
 
	$.fn.buscador_etiquetas = function( options ) {
 
		var buscador_etiquetas_methods = {
		        open : function( ) {  

		        	this.rup_dialog("open");
		        	$("#"+idBuscadorEtiquetas+"buscador").rup_table("clearGridData", true);
		        },
		    };
		if ( buscador_etiquetas_methods[options] ) {
            return buscador_etiquetas_methods[ options ].apply( this, Array.prototype.slice.call( arguments, 1 ));
        } else if ( typeof options === 'object' || ! options ) {
        	// This is the easiest way to have default options.
            var settings = $.extend({
                // These are the defaults.
                multiselect: options.multiselect,
                callback: function(event, object) {
                	
                }
            }, options );
            var etiquetaSeleccionadas = [];
            idBuscadorEtiquetas=this.attr("id");
            var name=this.attr("name");
            var html="<div class='aa79b-content-modal'>";
             html+="<div id='"+idBuscadorEtiquetas+"buscador_div' class='rup-table-container'>";
             html+="<div id='"+idBuscadorEtiquetas+"buscador_feedback'></div>";						
             html+="<div id='buscador_toolbar'></div>";							
             html+="<div id='contenFormularios' class='filterForm'>";
             html+="<form id='"+idBuscadorEtiquetas+"buscador_filter_form' Class='form-horizontal'>";				
             html+="<div id='"+idBuscadorEtiquetas+"buscador_filter_toolbar' class='formulario_legend'></div>";	
             html+="<fieldset id='"+idBuscadorEtiquetas+"buscador_filter_fieldset' class='rup-table-filter-fieldset'>";
             html+="<div class='p-2'>";
	             html+="<input type='hidden' name='estado' id='estado_filter_table' value='A'></input>";
	             html+="<div class='row'>";
	            	 html+="<div class='form-group col-xs-12 col-md-1'>";
		            		 html+="<label for='id_filter_table' class='control-label' data-i18n='label.id'>".concat($.rup.i18n.app.label.id,"</label>");
	             		 	 	html+="<input type='text' name='id' class='form-control' id='id_filter_table' maxlength='12'/>";
	        		 html+="</div>";
		             html+="<div class='form-group col-xs-12 col-md-4'>";
			            	 html+="<label for='descEu_filter_table' class='control-label' data-i18n='label.descEu'>".concat($.rup.i18n.app.label.descEu,"</label>");
			            	 	html+="<input type='text' name='descEu' class='form-control' id='descEu_filter_table' maxlength='100'/>";
		             html+="</div>";
	             	html+="<div class='form-group col-xs-12 col-md-5'>";
	             			html+="<label for='descEs_filter_table' class='control-label col-xs-12' data-i18n='label.descEs'>".concat($.rup.i18n.app.label.descEs,"</label>");
	             				html+="<input type='text' name='descEs' class='form-control' id='descEs_filter_table' maxlength='100'/>";
	             	html+="</div>";
	             html+="</div>";
             html+="</div>";
             html+="<div class='form-group'>";
             html+="<div id='"+idBuscadorEtiquetas+"buscador_filter_buttonSet' class='pull-right'>";
             html+="<button id='"+idBuscadorEtiquetas+"buscador_filter_filterButton' type='button' class='ui-button ui-widget ui-state-default ui-corner-all'>".concat($.rup.i18n.app.boton.filtrar,"</button>");
             html+="<a id='"+idBuscadorEtiquetas+"limpiarButton' href='javascript:void(0)' class='rup-enlaceCancelar'>".concat($.rup.i18n.app.boton.limpiar,"</a>");
             html+="</div>";
             html+="</div>";
             html+="</div>";
             html+="</fieldset>";
             html+="</form>";
             html+="</div>";
    		 html+="<div id='contenedor_tabla' class='table pb-2 '>";
             html+="<table id='"+idBuscadorEtiquetas+"buscador'></table>";
             html+="</div>";
             html+="<div id='"+idBuscadorEtiquetas+"buscador_pager'></div>";
             html+="</div>";
             html+="</div>";
             this.append(html);
             
            $('#'+idBuscadorEtiquetas+'limpiarButton').each(function() {
    			$(this).on("click", function() {
    					$("#"+idBuscadorEtiquetas+"buscador_filter_form").rup_form("clearForm");
		    			$("#"+idBuscadorEtiquetas+"buscador").rup_table("filter");
    			});
    		});
         /*
          * Inicializacion de componentes - INICIO 
          */
            $('#'+idBuscadorEtiquetas+'buscador_feedback').rup_feedback({
        		block : false,
        		delay: 1000
        	});
            
            	
        	colNames = [ 
    			$.rup.i18nParse($.rup.i18n.app, "label.id"),
    			$.rup.i18nParse($.rup.i18n.app, "label.descEu"),
    			$.rup.i18nParse($.rup.i18n.app, "label.descEs"),
    			''
    		];
            
        	colModel = [
        		{ 	name: "id", 
    			 	label: "label.id",
    			 	index: "ID_019",
    				align: "right", 
    				width: 30, 
    				isNumeric: true,
    				editable: false, 
    				fixed: false, 
    				hidden: false, 
    				resizable: true, 
    				sortable: true
    			},
    			{ 	name: "descEu", 
    			 	label: "label.descEu",
    			 	index: "DESCNORMEU",
    				align: "", 
    				width: 150, 
    				editable: true, 
    				fixed: false, 
    				hidden: false, 
    				resizable: true, 
    				sortable: true
    			},
    			{ 	name: "descEs", 
    			 	label: "label.descEs",
    			 	index: "DESCNORMES",
    				align: "", 
    				width: 150, 
    				editable: true, 
    				fixed: false, 
    				hidden: false, 
    				resizable: true, 
    				sortable: true
    			},
    			{ 	name: "estado", 
    			 	label: "label.estado",
    				hidden: true 
    			}
            ];
            
        	$("#"+idBuscadorEtiquetas+"buscador").rup_table({
        		url: "/aa79bItzulnetWar/administracion/datosmaestros/metadatosbusqueda",
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
					i18nCaption:  $.rup.i18n.app.boton.volver
					,css: "fa fa-arrow-left"
					,index: 0
				}
				,json_i18n : $.rup.i18n.app.simpelMaint
				,click : 
					function(){
					$("#"+idBuscadorEtiquetas).rup_dialog("close");
					}
				},
				{obj : {
					i18nCaption : $.rup.i18n.app.boton.seleccionarContinuar
					, css: "fa fa-floppy-o"
					, index: 1
				}
				,json_i18n : $.rup.i18n.app.simpelMaint
				,click : 
					function(){ 
    					//comprobar que se ha seleccionado algo
    					var ids = $("#"+idBuscadorEtiquetas+"buscador").rup_table('getSelectedRows');
    					var listaDatosEtiqueta = [];
                 		if(ids.length>=1){
                 			for(var i=0;i<ids.length;i++){
                 				var descEu = $("#"+idBuscadorEtiquetas+"buscador").rup_table("getCol", ids[i], "descEu")
                 				var descEs = $("#"+idBuscadorEtiquetas+"buscador").rup_table("getCol", ids[i], "descEs")
                 				var estado = $("#"+idBuscadorEtiquetas+"buscador").rup_table("getCol", ids[i], "estado")
                 				listaDatosEtiqueta.push({
                 					"id": ids[i],
                 					"descEu": descEu,
                 					"descEs": descEs,
                 					"estado": estado
                 				});
                 			}
                 			
                 			options.callback.call(this, listaDatosEtiqueta);
                 		}else{
                 			$('#'+idBuscadorEtiquetas+'buscador_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorSeleccionEtiqueta, "error");
                 		}
    				}
				}
    		]
        		},
        		colNames: colNames,
        		colModel: colModel,
                model:"DatosPersona",
                usePlugins:
                		["feedback",
            			"toolbar",
            			"responsive",
                    	"filter",
                    	"fluid"
                     	]
                ,onSelectRow:function(row, selected){
                 		if(!settings.multiselect){
                 			var a = $("#"+idBuscadorEtiquetas+"buscador").rup_table('getSelectedRows');
                     		if(a.length>1){
                     			$("#"+idBuscadorEtiquetas+"buscador").rup_table("setSelection", a[0], false, false);
                     		}
                 		}
                 	},
             	multiselection:{
             			headerContextMenu_enabled: false
        			},
                //multiSort: true,
                primaryKey: "id",
        		sortname: "DESCNORMEU",
        		sortorder: "asc",
        		loadOnStartUp: false,
           	 	filter:{
					beforeFilter: function(){
						$("#"+idBuscadorEtiquetas+"buscador").rup_table("resetSelection");
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
        		dialogClass:"buscadorEtiquetas",
        		modal: true,
        		resizable: false,
        		title: $.rup.i18n.app.comun.buscadorDeEtiquetas,
    		close: function(){
    			deseleccionarFilas();
    			$("#"+idBuscadorEtiquetas+"buscador_filter_form").rup_form("clearForm");
            	$("#"+idBuscadorEtiquetas+"buscador").rup_table("clearGridData", true);
    		}
        	});
            
            function deseleccionarFilas(){
            	var selected =  $("#"+idBuscadorEtiquetas+"buscador").rup_table('getSelectedRows');
				if(selected != null){
					var selectedLength = selected.length; 
					for(var k=0;k<selectedLength;k++){
							$("#"+idBuscadorEtiquetas+"buscador").rup_table("setSelection", selected[0], false, false);
						}
				}
            }

            return this;
        } else {
            $.error( 'Method ' +  methodOrOptions + ' does not exist on jQuery.tooltip' );
        }  
	
    };
   
}( jQuery ));