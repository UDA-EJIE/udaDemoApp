jQuery(function($) {

	$('#ayudaaplicacion_feedback').rup_feedback({
		block : false
	});

	/* Codigo JSon del Ã¡rbol de tareas */
	
	var treeTypes = {
		"valid_children" : [ "menu" ],
		"types" : {
			"menu" : {
				"valid_children" : [ "menu" ]
			}
		}
	};
	
	var uniqueDemo = $("#uniqueDemo");
	var ayudaAplicacion_pdfCreatorButton = $("#ayudaAplicacion_pdfCreatorButton");
	var ayudaAplicacion_create_createButton = $('#ayudaAplicacion_create_createButton');
	var ayudaAplicacion_rename_renameButton = $('#ayudaAplicacion_rename_renameButton');
	var ayudaAplicacion_remove_removeButton = $('#ayudaAplicacion_remove_removeButton');
	var ayudaAplicacion_content_div = $("#ayudaAplicacion_content_div");
	var contenidoAyuda = $("#contenidoAyuda");
	var idAyudaContenido = $('#idAyudaContenido');

	/*
	 * Ejemplo de nodos, en el mismo subgrupo, que tengan diferente nombre (no
	 * puede haber dos iguales)
	 */
	var tree = uniqueDemo.rup_tree({
		"themes" : {
		      "dots" : true,
		      "icons" : false
		   },
		"core" : {
			"initially_open" : [ "nodeRaiz" ]
		},
		"contextmenu" : {
			"enable" : true,
			 items: customMenu 
		},
		"json_data" : {
			"ajax" : {
				"url" : "../ayudaaplicacion/ajaxTree"
			}
		},
		"types" : treeTypes,
		"crrm" : {
			"move" : {
				
				"check_move" : function (moveObject){
		            var moveObjectParent = this._get_parent(moveObject.o);
		            if(!moveObjectParent) return true;
		            moveObjectParent = moveObjectParent == -1 ? this.get_container() : moveObjectParent;
		            if(moveObjectParent === moveObject.np) return true;
		            if(moveObjectParent[0] && moveObject.np[0] && moveObjectParent[0] === moveObject.np[0]) return true;
		            return true;
		         }
			
			},
			"create_node" : function(node, position, js, callback) {}
		},
		"dnd" : {
			"enable" : true,
			"drop_target" : false,
			"drag_target" : false
		},
		"select" : {
			"select_limit" : "1",
			"initially_select" : ["0"]
		}
	}).on('create_node.jstree', function(e, data) {
		
		var obj = data.args[0][0];
		var idAyudaPadre = obj.id;
		var levelPadre = obj.getAttribute('level');
		var titulo = data.args[2].data[0];
		var level = parseInt(levelPadre) + 1;
		var urlCreateNode = '../ayudaaplicacion/addElement';
		
		var jsonObject = 
		{
			"idAyudaPadre": idAyudaPadre, 
			"titulo": titulo
		};
		
		$.ajax({
            
            type: 'POST', 
            url: urlCreateNode, 
            dataType: 'json', 
            contentType: 'application/json', 
            data: $.toJSON(jsonObject), 
            async: false, 
            success:function(json){
            	data.inst._get_node(data.rslt.obj)[0].id = json.idAyuda;
            	data.rslt.obj.attr('orden',json.orden);
            	data.rslt.obj.attr('level', level);
            	
            	enableButton("ayudaAplicacion_pdfCreatorButton");
            }, 
	   	 	error: function(){}
		
		});
		
	}).on('select_node.jstree', function(evt, data){
        
        var idAyuda = data.inst._get_node(data.rslt.obj)[0].id;
        var tituloAyuda = data.inst.get_json()[0].data;
        var level = $("#"+idAyuda).attr("level");
    	
    	var idAyudaContent = idAyudaContenido.val();
    	
    	if (idAyuda !== '0' && idAyuda!==idAyudaContent){
    		
    		var contentAyuda = contenidoAyuda.val();
    		var contenido = obtenerContenidoForm();
    		
    		// Eliminamos el código html para comparar las cadenas de caracteres
    		var texto = $.trim(contenido.replace(/<[^>]*>?/g, ''));
    		
    		if(contentAyuda === "" && texto === "" && contenido.indexOf("<img") === -1 ){
    			inicializarDatosSelectNode(idAyuda, tituloAyuda, level);
    		} else if(contentAyuda !== contenido){
        		$.rup_messages("msgConfirm", {
    				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
    				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
    				OKFunction: function(){
    					inicializarDatosSelectNode(idAyuda, tituloAyuda, level);
    				},
     				CANCELFunction: function(e){
     					reseleccionarNodo();
     				}
    			});
        	} else{
        		inicializarDatosSelectNode(idAyuda, tituloAyuda, level);
        	}
    		
    	} else {
    		inicializarDatosSeleccion(level);
    		if (idAyuda == '0'){
    			ayudaAplicacion_content_div.addClass('oculto');
    		} else {
    			ayudaAplicacion_content_div.removeClass('oculto');
    		} 
    	}
        
        
    }).on('move_node.jstree', function(evt, data){
    	 
    	var identificadorNodoActual = data.rslt.o[0].id;
    	var nodoActual = $("#"+identificadorNodoActual);
    	var padre = nodoActual.parents("li:first");
    	var idAyudaPadre = padre.attr("id");
    	var levelPadre = padre.attr("level");
    	
    	if (levelPadre === undefined || levelPadre === "3" || 
    			(levelPadre === "2" && nodoActual.children("ul").children("li").length > 0)){
    		$.rup_messages("msgError", {
				message: $.rup.i18nParse($.rup.i18n.app,"rup_tree.errorMovingNode"),
				title: $.rup.i18nParse($.rup.i18n.base,"rup_global.error")
			});
    		
    		tree.rollback(data.rlbk);
    		
    		tree.jstree("deselect_all", true);
    		uniqueDemo.rup_tree("reselect");
    	} else {
    		
    		bloquearPantalla($.rup.i18nParse($.rup.i18n.base,"rup_tree.loading"), null);
    		
    		var arrayAyudaAplicacion = new Array();
        	var jsonObject;
        	var idAyuda;
        	var orden;
        	var hijo;
        	var listHijos = padre.children("ul").children("li");
        	var levelHijo = parseInt(levelPadre) + 1;
        	
        	for (var i = 0; i < listHijos.length; i++) {
        		hijo = listHijos[i];
        		idAyuda = hijo.id;
        		orden = i + 1;
        		
        		hijo.setAttribute("orden", orden);
        		hijo.setAttribute("level", levelHijo);
        		
        		arrayAyudaAplicacion.push( { "idAyuda": idAyuda, "idAyudaPadre": idAyudaPadre, "orden": orden });
        	}
        	
        	var jsonObject = 
    		{
    			"listaAyudas": arrayAyudaAplicacion	    				    					
    		};	
        	
        	var urlMoveNode = '../ayudaaplicacion/moveElement';
        	var identificadorAyuda = "";
    		
    		$.ajax({
                
                type: 'POST', 
                url: urlMoveNode, 
                dataType: 'json', 
                contentType: 'application/json', 
                data: $.toJSON(jsonObject), 
                async: false, 
                success:function(data){
                	
            		if (uniqueDemo.rup_tree("get_selected").size() > 0){
            			identificadorAyuda = uniqueDemo.rup_tree("get_selected").attr("id");
            		}
            		
            		if (identificadorNodoActual === identificadorAyuda){
            			gestionUniqueDemoButtonsToolbar(levelHijo);
            		}
            		
                	desbloquearPantalla();
                }, 
    	   	 	error: function(error){
    	   	 		desbloquearPantalla();
    	   	 	}
    		
    		});
    	}
    	
    }).on('rename_node.jstree', function(evt, data){
    	
    	var idAyuda = data.inst._get_node(data.rslt.obj)[0].id;
    	var titulo = data.rslt.name;
    	
    	if (titulo.length > 250){
    		$.rup_messages("msgError", {
    			message: $.format($.rup.i18nParse($.rup.i18n.base,"rup_validate.messages.maxlength"), 250),
    			title: $.rup.i18nParse($.rup.i18n.base,"rup_global.error")
    		});
    		
    		tree.rollback(data.rlbk);
    	} else {
    		var jsonObject = 
	    		{
	    			"idAyuda": idAyuda,
	    			"titulo": titulo
	    		};
	    	
			var urlRenameNode = '../ayudaaplicacion/renameElement';
			
			$.ajax({
	            
	            type: 'POST', 
	            url: urlRenameNode, 
	            dataType: 'json', 
	            contentType: 'application/json', 
	            data: $.toJSON(jsonObject), 
	            async: false, 
	            success:function(data){}, 
		   	 	error: function(error){}
			
			});
    	}
    	
    }).on('loaded.jstree', function(evt, data){
    	
    	inicializarDatos();
    	
    });

		tinymce
			.init({
				branding : false,
				elementpath: false,
				resize: false,
				selector : 'textarea',
				menubar: false, 
				height : 300,
				theme : 'modern',
				plugins : 'print preview fullpage searchreplace autolink directionality visualblocks visualchars fullscreen image link media template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists textcolor imagetools contextmenu colorpicker textpattern help',
				toolbar : 'fontselect fontsizeselect | bold italic underline forecolor backcolor | cut copy paste | link image | alignleft aligncenter alignright alignjustify  | numlist bullist outdent indent  | removeformat | preview ',
				fontsize_formats: '8pt 10pt 12pt 14pt 18pt 24pt 36pt',
				image_advtab : true,
				templates : [ {
					title : 'Test template 1',
					content : 'Test 1'
				}, {
					title : 'Test template 2',
					content : 'Test 2'
				} ],
				body_class: 'ayudaaplicacion-content',
				content_css : [
						'//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
						'/aa79bStatics/aa79b/styles/aa79b.css' ],
						image_title: true, 
						  // enable automatic uploads of images represented by blob or data URIs
						  automatic_uploads: true,
						  // URL of our upload handler (for more details check: https://www.tinymce.com/docs/configure/file-image-upload/#images_upload_url)
						  // images_upload_url: 'postAcceptor.php',
						  // here we add custom filepicker only to Image dialog
						  file_picker_types: 'image', 
						  // and here's our custom image picker
						  file_picker_callback: function(cb, value, meta) {
							var input = $("#fileOculto")[0];
//						    var input = document.createElement('input');
//						    input.setAttribute('type', 'file');
						    input.setAttribute('accept', 'image/*');
						    
						    // Note: In modern browsers input[type="file"] is functional without 
						    // even adding it to the DOM, but that might not be the case in some older
						    // or quirky browsers like IE, so you might want to add it to the DOM
						    // just in case, and visually hide it. And do not forget do remove it
						    // once you do not need it anymore.

						    input.onchange = function() {
						      var file = this.files[0];
						      
						      var reader = new FileReader();
						      reader.onload = function () {
						        // Note: Now we need to register the blob in TinyMCEs image blob
						        // registry. In the next release this part hopefully won't be
						        // necessary, as we are looking to handle it internally.
						        var id = 'blobid' + (new Date()).getTime();
						        var ed = tinyMCE.activeEditor; 
						        ed.focus(); 
						        var blobCache =  ed.editorUpload.blobCache;
						        var base64 = reader.result.split(',')[1];
						        var blobInfo = blobCache.create(id, file, base64);
						        blobCache.add(blobInfo);

						        // call the callback and populate the Title field with the file name
						        cb(blobInfo.blobUri(), { title: file.name });
						      };
						      reader.readAsDataURL(file);
						    };
						    
						    $(input).trigger("click");
						  }
			});

	function removeElement(idAyuda){
		var urlDeleteNode = '../ayudaaplicacion/removeElement';
		
		$.ajax({
            
            type: 'POST', 
            url: urlDeleteNode, 
            dataType: 'json', 
            contentType: 'application/json', 
            data: $.toJSON(idAyuda), 
            async: false, 
            success:function(jsonData){
            	
            	var hijosNodoRaiz = $("#0").children("ul").children("li");
            	var numHijosNodoRaiz = hijosNodoRaiz.length;
            	
            	if (numHijosNodoRaiz == 1 &&
    					hijosNodoRaiz.attr("id") == idAyuda) {
            		disableButton("ayudaAplicacion_pdfCreatorButton");
    			} else if (numHijosNodoRaiz > 0) {
    				enableButton("ayudaAplicacion_pdfCreatorButton");
    			} else {
    				disableButton("ayudaAplicacion_pdfCreatorButton");
            	}
        		
            }, 
	   	 	error: function(){}
		
		});
	}
	
	function customMenu(node)
	{
	    var items = { 
				 create: { 
                     label: $.rup.i18n.app.comun.crear, 
                     action: function (obj) { this.create(obj); }, 
                     seperator_after : false, 
                     seperator_before : false 
                 }, 
                 create_root: false,
				 rename: { 
                     label: $.rup.i18n.app.comun.renombrar, 
                     action: function (obj) { this.rename(obj); }, 
                     seperator_after: false, 
                     seperator_before: true 
                 }, 
                 remove: { 
                     label: $.rup.i18n.app.comun.eliminar, 
                     action: function (obj) { 
                 		$.rup_messages("msgConfirm", {
             				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
             				message: $.rup.i18nParse($.rup.i18n.app,"rup_tree.removeElementAndContinue"),
             				OKFunction: function(){
             					removeElement(obj[0].id);
             					tree.remove(obj);
             				},
             				CANCELFunction: function(e){
             				}
             			});
                     }, 
                     seperator_after: false, 
                     seperator_before: false 
                 } , 
                 ccp: false
             }

	    if (node[0].attributes.level.nodeValue === '0') {
	    	delete items.rename;
	    	delete items.remove;
	    }
	    
	    if (node[0].attributes.level.nodeValue === '3') {
	        delete items.create;
	    }

	    return items;
	}
	
	function inicializarDatos()
	{
		if ($("#0").children("ul").children("li").length > 0) {
			// En caso de que el nodo raíz (id = 0) tenga hijos
    		// se habilita el botón Exportar a PDF
			enableButton("ayudaAplicacion_pdfCreatorButton");
    	} else {
    		// En caso de que el nodo raíz (id = 0) tenga hijos
    		// se deshabilita el botón Exportar a PDF
    		disableButton("ayudaAplicacion_pdfCreatorButton");
    	}
		
		ayudaAplicacion_create_createButton.attr('disabled','disabled');
		ayudaAplicacion_create_createButton.addClass('ui-button-disabled ui-state-disabled');
		ayudaAplicacion_rename_renameButton.attr('disabled','disabled');
		ayudaAplicacion_rename_renameButton.addClass('ui-button-disabled ui-state-disabled');
		ayudaAplicacion_remove_removeButton.attr('disabled','disabled');
		ayudaAplicacion_remove_removeButton.addClass('ui-button-disabled ui-state-disabled');
		
		reseleccionarNodo();
	}
	
	function getContenido(idAyuda, tituloAyuda){
		
		if(tituloAyuda !== undefined && typeof tituloAyuda === "string"){
			$('#labelContenidoAyuda')[0].textContent = tituloAyuda;
		}
		
		idAyudaContenido.val(idAyuda);
		
		if (idAyuda != 0){
        	
        	bloquearPantalla($.rup.i18nParse($.rup.i18n.base,"rup_tree.loading"), null);
        	
            var urlGetContent = '../ayudaaplicacion/getContenido';
    		
    		$.ajax({
                
                type: 'POST', 
                url: urlGetContent, 
                dataType: 'json', 
                contentType: 'application/json', 
                data: $.toJSON(idAyuda), 
                async: true, 
                success:function(data){
                	var contenido_form_ifr_body = $("#contenido_form_ifr").contents().find("body");
                	contenido_form_ifr_body.html(data);
                	contenido_form_ifr_body.html($.trim(contenido_form_ifr_body.html()));
                	contenidoAyuda.val(data);
                	ayudaAplicacion_content_div.removeClass('oculto');
                	desbloquearPantalla();
                }, 
    	   	 	error: function(){
    	   	 		desbloquearPantalla();
    	   	 	}
    		
    		});
        	
        } else {
        	ayudaAplicacion_content_div.addClass('oculto');
        }
		
	}
	
	function removeItem(idAyuda){
		$.rup_messages("msgConfirm", {
			title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
			message: $.rup.i18nParse($.rup.i18n.app,"rup_tree.removeElementAndContinue"),
			OKFunction: function(){
				removeElement(idAyuda);
				uniqueDemo.rup_tree("remove");
			},
			CANCELFunction: function(e){
			}
		});
	}
	
	function gestionUniqueDemoButtonsToolbar(level){
		
		if (level == '0'){
			ayudaAplicacion_create_createButton.removeAttr('disabled');
			ayudaAplicacion_create_createButton.removeClass('ui-button-disabled ui-state-disabled');
			ayudaAplicacion_rename_renameButton.attr('disabled','disabled');
			ayudaAplicacion_rename_renameButton.addClass('ui-button-disabled ui-state-disabled');
			ayudaAplicacion_remove_removeButton.attr('disabled','disabled');
			ayudaAplicacion_remove_removeButton.addClass('ui-button-disabled ui-state-disabled');
		} else if (level == '3'){
			ayudaAplicacion_create_createButton.attr('disabled','disabled');
			ayudaAplicacion_create_createButton.addClass('ui-button-disabled ui-state-disabled');
			ayudaAplicacion_rename_renameButton.removeAttr('disabled');
			ayudaAplicacion_rename_renameButton.removeClass('ui-button-disabled ui-state-disabled');
			ayudaAplicacion_remove_removeButton.removeAttr('disabled');
			ayudaAplicacion_remove_removeButton.removeClass('ui-button-disabled ui-state-disabled');
		} else {
			ayudaAplicacion_create_createButton.removeAttr('disabled');
			ayudaAplicacion_create_createButton.removeClass('ui-button-disabled ui-state-disabled');
			ayudaAplicacion_rename_renameButton.removeAttr('disabled');
			ayudaAplicacion_rename_renameButton.removeClass('ui-button-disabled ui-state-disabled');
			ayudaAplicacion_remove_removeButton.removeAttr('disabled');
			ayudaAplicacion_remove_removeButton.removeClass('ui-button-disabled ui-state-disabled');
		}
		
	}
	
	function inicializarDatosSelectNode(idAyuda, tituloAyuda, level){
		getContenido(idAyuda, tituloAyuda);
		inicializarDatosSeleccion(level);
	}
	
	function inicializarDatosSeleccion(level){
		gestionUniqueDemoButtonsToolbar(level);
		
		uniqueDemo.rup_tree("save_selected");
	}
	
	function reseleccionarNodo(){
		uniqueDemo.rup_tree("deselect_all");
		uniqueDemo.rup_tree("reselect");
	}
	
	function obtenerContenidoForm(){
		var contenido_form = $("#contenido_form");
		contenido_form.tinymce().save();
		
		return contenido_form.val();
	}
	
	$('#ayudaAplicacion_save_saveButton').on("click", function (){
		
		var idAyuda = idAyudaContenido.val();
		var contenido = obtenerContenidoForm();
		
		if (contenido.length > 4000000000){
			$.rup_messages("msgError", {
    			message: $.format($.rup.i18nParse($.rup.i18n.base,"rup_validate.messages.max"), "4 GB"),
    			title: $.rup.i18nParse($.rup.i18n.base,"rup_global.error")
    		});
    		
    		tree.rollback(data.rlbk);
    	} else  {
    		
    		var contentAyuda = contenidoAyuda.val();
    		
    		// Eliminamos el código html para comparar las cadenas de caracteres
    		var texto = $.trim(contenido.replace(/<[^>]*>?/g, ''));
    		
    		if(contentAyuda === "" && texto === "" && contenido.indexOf("<img") === -1 ){
    			$.rup_messages("msgOK", {
    				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.emptyChanges"),
    				title: $.rup.i18nParse($.rup.i18n.base,"rup_global.save")
    			});
    		} else if(contentAyuda !== contenido){
    			var jsonObject = 
		    		{
		    			"idAyuda": idAyuda,
		    			"contenidoStr": contenido
		    		};
		    	
				var urlUpdateNode = '../ayudaaplicacion/updateElement';
				
				$.ajax({
		            
		            type: 'POST', 
		            url: urlUpdateNode, 
		            dataType: 'json', 
		            contentType: 'application/json', 
		            data: $.toJSON(jsonObject), 
		            async: false, 
		            success:function(data){
		            	contenidoAyuda.val(contenido);
		            	$.rup_messages("msgOK", {
		        			message: $.rup.i18nParse($.rup.i18n.app,"rup_tree.saveContent"),
		        			title: $.rup.i18nParse($.rup.i18n.base,"rup_global.save")
		        		});
		            }, 
			   	 	error: function(error){
				   	 	$.rup_messages("msgError", {
							message: $.rup.i18nParse($.rup.i18n.app,"rup_tree.errorSavingContent"),
							title: $.rup.i18nParse($.rup.i18n.base,"rup_global.error")
						});
			   	 	}
				
				});
    			
    		} else {
    			$.rup_messages("msgOK", {
    				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.emptyChanges"),
    				title: $.rup.i18nParse($.rup.i18n.base,"rup_global.save")
    			});
    		}
    		
    	}
		
	});
	
	ayudaAplicacion_pdfCreatorButton.on("click", function (){
		
        var url = "../ayudaaplicacion/generarPdf";
        //Se hace la llamada
        window.open(url);
	});
	
	$('#ayudaAplicacion_cancel_cancelButton').on("click", function (){
		
		var idAyuda = idAyudaContenido.val();
        
		getContenido(idAyuda);
		
	});
	
	ayudaAplicacion_create_createButton.on("click", function (){
		
		var level = "";
		
		if (uniqueDemo.rup_tree("get_selected").size() > 0){
			level = uniqueDemo.rup_tree("get_selected").attr("level");
		}
		
		if (level  !== '3'){
			uniqueDemo.rup_tree("create");
		}
		
	});
	
	ayudaAplicacion_rename_renameButton.on("click", function (){
		
		var level = "";
		
		if (uniqueDemo.rup_tree("get_selected").size() > 0){
			level = uniqueDemo.rup_tree("get_selected").attr("level");
		}
		
		if (level  !== '0'){
			uniqueDemo.rup_tree("rename");
		}
		
	});
	
	ayudaAplicacion_remove_removeButton.on("click", function (){
		
		var idAyuda = "";
		var level = "";
		
		if (uniqueDemo.rup_tree("get_selected").size() > 0){
			level = uniqueDemo.rup_tree("get_selected").attr("level");
			idAyuda = uniqueDemo.rup_tree("get_selected").attr("id");
		}
		
		if (level  !== '0'){
			removeItem(idAyuda);
		}
		
	});
	
	disableButton("ayudaAplicacion_pdfCreatorButton");
	
});