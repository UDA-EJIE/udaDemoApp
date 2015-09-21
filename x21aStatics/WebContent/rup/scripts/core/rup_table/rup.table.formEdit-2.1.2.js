/*!
 * Copyright 2013 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 *      http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 * el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */

(function ($) {

	jQuery.rup_table.registerPlugin("formEdit",{
		loadOrder:5,
		preConfiguration: function(settings){
			var $self = this;
			
			return $self.rup_table("preConfigureFormEdit", settings);
		},
		postConfiguration: function(settings){
			var $self = this;
			
			return $self.rup_table("postConfigureFormEdit", settings);
		}
	});
	
	//********************************
	// DEFINICIÓN DE MÉTODOS PÚBLICOS
	//********************************
	
	/**
	 * Extensión del componente rup_table para permitir la edición de los registros mediante un formulario. 
	 * 
	 * Los métodos implementados son:
	 * 
	 * configureDetailForm(settings): Realiza la configuración interna necesaria para la gestión correcta de la edición mediante un formulario.
	 * deleteElement(rowId, options): Realiza el borrado de un registro determinado.
	 * editElement(rowId, options): Lanza la edición de un registro medainte un formulario de detalle.
	 * newElement(): Inicia el proceso de inserción de un nuevo registro.
	 * showServerValidationFieldErrors(errors): Función encargada de mostrar los errores producidos en la gestión de los datos del mantenimiento.
	 * 
	 * Las propiedades de esta extensión almacenadas en el settings son las siguientes:
	 * 
	 * settings.$detailForm : Referencia al formulario de detalle mediante el que se realizan las modificaciones e inserciones de registros.
	 * settings.$detailFormDiv : Referencia al div que arropa el formulario de detalle y sobre el que se inicializa el componente rup_dialog. 
	 *  
	 */
	jQuery.fn.rup_table("extend",{
		preConfigureFormEdit: function(settings){
			var $self = this;
			/*
			 * Inicialización de propiedades del componente para simplificar su configuración
			 */ 
			// En caso de no especificarse una url para la edición de un elemento, se utiliza por defecto la indicada en la propiedad url.
			if (settings.formEdit.editurl===undefined){
				settings.formEdit.editurl=settings.url;
				settings.formEdit.editOptions.ajaxEditOptions.url=settings.url;
				settings.formEdit.addOptions.ajaxEditOptions.url=settings.url;
			}
			
			
			if (settings.formEdit.detailOptions.ajaxDetailOptions.url===undefined){
				settings.formEdit.detailOptions.ajaxDetailOptions.url=settings.url;
			}

			settings.formEdit.deleteOptions.ajaxDelOptions = $.extend(true, settings.formEdit.deleteOptions.ajaxDelOptions, {
				success: function(){
					settings.$feedback.rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_maint.deletedOK"), "ok");
				}
			});
			
			// Configuración de edit/add
			settings.formEdit.addOptions = $.extend(true,{}, settings.formEdit.addEditOptions, settings.formEdit.addOptions);
			settings.formEdit.editOptions = $.extend(true,{}, settings.formEdit.addEditOptions, settings.formEdit.editOptions);
		},
		/*
		 * Realiza la configuración interna necesaria para la gestión correcta de la edición mediante un formulario.
		 * 
		 * TODO: internacionalizar mensajes de error.
		 */
		postConfigureFormEdit: function(settings){
			var $self = this, $objDetailForm;
			
			// Se comprueba si se ha especificado un formulario propio por parte del usuario.
			if (settings.formEdit.detailForm){
				// Se comprueba que el identificador especificado para el diálogo sea válido
				if (jQuery(settings.formEdit.detailForm).length === 0){
					alert("El identificador especificado para el fomulario de detalle no existe.");
				}else{
					$objDetailForm = $(settings.detailForm);
					if ($objDetailForm.is("form")){
						if ($objDetailForm.parent().is("div")){
							settings.formEdit.$detailFormDiv = $objDetailForm.parent();
							settings.formEdit.$detailForm = $objDetailForm;
						}else{
							alert("El fomrmulario no está incluido dentro de un div");
						}
					}else if ($objDetailForm.is("div")){
						var $objFormAux = $objDetailForm.find("form");
						if ($objFormAux.length===1){
							settings.formEdit.$detailFormDiv = $objDetailForm;
							settings.formEdit.$detailForm = $objFormAux;
						}
					}
					
					// Inicialización del componente rup_form sobre del formulario de detalle
					settings.formEdit.$detailForm.rup_form(settings.formEdit.editOptions);
				}
			}else{
				// No se configura un formulario de detalle
				beforeSendUserEvent = settings.beforeSend;
				var defaultBeforeSend = function (jqXHR, ajaxOptions){
					ajaxOptions.beforeSend = beforeSendUserEvent;
					ajaxOptions.validate = settings.formEdit.validate;
					ajaxOptions.feedback = settings.$detailFeedback; 
					settings.formEdit.$detailForm.rup_form("ajaxSubmit", ajaxOptions);
					rp_ge[settings.id].processing = false;
					return false;
				};
				
				settings.formEdit.editOptions.ajaxEditOptions.beforeSend = defaultBeforeSend;
				settings.formEdit.addOptions.ajaxEditOptions.beforeSend = defaultBeforeSend;
			}
			
			settings.getDetailTotalRowCount = function(){
				var $self = this;
				return $self.rup_table("getGridParam", "records");
			};
			
			settings.getDetailCurrentRowCount = function(){
				var $self = this,page, rowNum, rowId;
				
				// Obtenemos la pagina actual
				page = parseInt($self.rup_table("getGridParam", "page"),10);
				// Obtenemos el identificador del registro seleccionado
				rowId = $self.rup_table("getGridParam","selrow");
				// Obtenemos el numero de linea
				rowNum = $self.jqGrid("getInd", rowId);
				// Numero de registros por pagina que se visualizan
				rowsPerPage = parseInt($self.rup_table("getGridParam", "rowNum"),10);
				// Flag de todos los registros seleccionados
				cont = (page * rowsPerPage) - rowsPerPage + rowNum;
				
				return cont;
			};
			
			
			
			settings.getRowForEditing = function(){
				var $self = this,
				selrow=$self.jqGrid('getGridParam','selrow');
				
				return (selrow===null?false:selrow);
			};
			
			settings.fncHasSelectedElements = function(){
				var $self = this;
				return jQuery.proxy(settings.getRowForEditing, $self)()!==false;
			};
			
			settings.fncGetNavigationParams = function getNavigationParams(linkType ){
				var $self = this, execute = false, changePage = false, index=0,	newPage=0, newPageIndex=0,
				npos = jQuery.proxy(jQuery.jgrid.getCurrPos, $self[0])(),
				page = parseInt($self.rup_table("getGridParam", "page"),10),
				rowsPerPage = parseInt($self.rup_table("getGridParam", "rowNum"),10),
				lastPage = parseInt(Math.ceil($self.rup_table("getGridParam", "records")/$self.rup_table("getGridParam", "rowNum")),10);
				
				npos[0] = parseInt(npos[0],10);
				
				switch (linkType){
				case 'first':
					if(!(page===1 && npos[0]===0)) {
						execute = true;
						index = 0;
						if (page>1){
							changePage = true;
							newPage = 1;
							newPageIndex=0;
						}
					}
					break;
				case 'prev':
					if(!(page===1 && npos[0]===0)) {
						execute = true;
						index = npos[0]-1;
						if (index<0){
							changePage = true;
							newPage = page -1;
							newPageIndex= rowsPerPage;
						}
					}
					break;
				case 'next':
					if(!(page===lastPage && npos[0]===npos[1].length-1)) {
						execute = true;
						index = npos[0]+1;
						if (index>npos[1].length-1){
							changePage = true;
							newPage = page+1;
							newPageIndex=0;
						}
					}
					break;
				case 'last':
					if(!(page===lastPage && npos[0]===npos[1].length-1)) {
						execute = true;
						index = npos[1].length-1;
						if (page<lastPage){
							changePage = true;
							newPage = lastPage;
							newPageIndex=rowsPerPage;
						}
					}
					break;
				}
				
				return [linkType, execute, changePage, index, npos, newPage, newPageIndex];
			};
			
			settings.doNavigation = function(arrParams, execute, changePage, index, npos, newPage, newPageIndex ){
				var $self = this, settings = $self.data("settings"), props = rp_ge[$self.attr("id")],
				linkType, execute, changePage, index, npos, newPage, newPageIndex;
				
				if ($.isArray(arrParams)){
					linkType = arrParams[0];
					execute = arrParams[1];
					changePage = arrParams[2];
					index = arrParams[3];
					npos = arrParams[4];
					newPage = arrParams[5];
					newPageIndex = arrParams[6];
							
					if (execute){
						$self.rup_table("hideFormErrors", settings.formEdit.$detailForm);
						$self.triggerHandler("jqGridAddEditClickPgButtons", [linkType, settings.formEdit.$detailForm, npos[1][npos[index]]]);
						if (changePage){
							$self.trigger("reloadGrid",[{page: newPage}]);
							$self.on("jqGridAfterLoadComplete.pagination",function(event,data){
								var nextPagePos = jQuery.proxy(jQuery.jgrid.getCurrPos, $self[0])(),
								newIndexPos = (newPageIndex<nextPagePos[1].length?newPageIndex:nextPagePos[1].length-1);
								$self.jqGrid("setSelection", nextPagePos[1][newIndexPos]);
								jQuery.proxy(jQuery.jgrid.fillData, $self[0])(nextPagePos[1][newIndexPos], $self[0], settings.formEdit.$detailForm.attr("id"), settings.opermode);
								jQuery.proxy(jQuery.jgrid.updateNav, $self[0])(nextPagePos[1][newIndexPos],npos[1].length-1);
								$self.off("jqGridAfterLoadComplete.pagination");
							});
						}else{
							jQuery.proxy(jQuery.jgrid.fillData, $self[0])(npos[1][index], $self[0], settings.formEdit.$detailForm.attr("id"), settings.opermode);
							$self.jqGrid("setSelection", npos[1][index]);
							jQuery.proxy(jQuery.jgrid.updateNav, $self[0])(npos[1][index],npos[1].length-1);
						}
						$self.triggerHandler("jqGridAddEditAfterClickPgButtons", [linkType,settings.formEdit.$detailForm,npos[1][npos[index]]]);
						if(jQuery.isFunction(props.afterclickPgButtons)) {
							props.afterclickPgButtons.call($self, 'next',settings.formEdit.$detailForm,npos[1][npos[index]]);
						}
					}
				}
			};
			
			
			/*
			 * CALLBACKS PARA LOS ESTADOS DE LA TABLA
			 */
			settings.isOnEdit = function(){
				var $self = this, settings = $self.data("settings");
				return jQuery.proxy(settings.fncHasSelectedElements, $self)();
			};
			
			settings.isOnAdd = function(){
				return true;
			};
			
			settings.isOnDelete = function(){
				var $self = this, settings = $self.data("settings");
				return jQuery.proxy(settings.fncHasSelectedElements, $self)();
			};
			
			settings.isOnCancel = function(){
				return false;
			};
			
			settings.isOnSave = function(){
				return false;
			};
			
			// Campturador del evento jqGridInlineAfterSaveRow.
			$self.on({
				"jqGridLoadComplete.rupTable.formEditing": function(data){
					var $self = $(this), settings = $self.data("settings"), nPos;
					
					if (settings.formEdit.autoselectFirstRecord){
						nPos = jQuery.proxy(jQuery.jgrid.getCurrPos, $self[0])();
						$self.jqGrid("setSelection", nPos[1][0], false);
					}
					
				},
				"jqGridAddEditInitializeForm.rupTable.formEditing": function(event, $form ,modo){
					
					/*
					 * Creación de los componentes rup
					 */
					var $self = $(this), settings = $self.data("settings"), colModel = settings.colModel;

					jQuery.each(colModel, function (index, colObj) {
						if (colObj.rupType){
							
							if(colObj.editoptions && colObj.editoptions.i18nId === undefined){
								colObj.editoptions.i18nId = $self.attr("id") + "##" + this.name;
							}
							
							$("#"+colObj.name, $form)["rup_"+colObj.rupType](colObj.editoptions);
						}
					});
					 
					//Se crea el toolbar de error mediante un componente rup_tooltip
					settings.$detailFeedback = $("#FormError", $form).rup_feedback({
						closeLink: true,
						gotoTop: false,
						block: false
//						delay: 1000,
//						fadeSpeed: 500
					}).attr("ruptype","feedback");
				},
				"jqGridAddEditAfterSubmit.rupTable.formEditing": function(event, res, postData, oper){
					// Una vez se haya realizado el guardado del registro se muestra el mensaje correspondiente en el feedback dependiendo del modo en el que se encuentra.
					var settings = $self.data("settings"), formEditSaveType = $self.data("tmp.formEditSaveType");
					$self.removeData("tmp.formEditSaveType"), id;
					// Dependiendo del boton de guardado seleccionado se realiza el comportamiento correspondiente
					if (formEditSaveType === "SAVE"){
						if (oper === 'edit') {
							settings.$feedback.rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_maint.modifyOK"), "ok");
						} else {
							settings.$feedback.rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_maint.insertOK"), "ok");
						}
						settings.formEdit.$detailFormDiv.rup_dialog("close");
					}else if (formEditSaveType === "SAVE_REPEAT"){
						if (oper === 'edit') {
							settings.$detailFeedback.rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_maint.modifyOK"), "ok");
						} else {
							settings.$detailFeedback.rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_maint.insertOK"), "ok");
						}
					}
					
					// Se obtiene el json de respuesta
					try{
						responseJson = $.parseJSON(res.responseText);
					}catch(e){
						responseJson = postData;
					}
					
					id = responseJson[settings.primaryKey?settings.primaryKey:$self[0].p.prmNames.id];
					return [true,"",id];
				 },
				 "jqGridAddEditErrorTextFormat.rupTable.formEditing":function(event, data, oper){
					 var responseJSON;
					 if (data.status === 406 && data.responseText!== ""){
						 // TODO : Comprobar que lo que devuelve es un JSON valido
						 responseJSON = jQuery.parseJSON(data.responseText);
						 if (responseJSON.rupErrorFields){
							 $self.rup_table("showServerValidationFieldErrors",responseJSON);
						 }
					 }
				 },
				 "jqGridDblClickRow.rupTable.formEditing": function (rowid, iRow, iCol, e){
					$self.rup_table('editElement', iRow);
				 },
				 "jqGridAddEditBeforeShowForm.rupTable.formEditing": function(event, $detailForm, frmoper){
					 var $self = $(this), settings = $self.data("settings"),
					 $title = jQuery("#ui-dialog-title-"+settings.formEdit.$detailFormDiv.attr("id"));
					 
					 if (frmoper==="add"){
						 $title.html(rp_ge[$self[0].p.id].addCaption);
						 $("#pagination_"+settings.id+",#pag_"+settings.id).hide();
					 }else{
						 $title.html(rp_ge[$self[0].p.id].editCaption);
						 $("#pagination_"+settings.id+",#pag_"+settings.id).show();
					 }
				 }
			});
			
		},
		createDetailNavigation: function(){
			var $self = $(this), settings = $self.data("settings"), jqGridID = $self.attr("id"),
			paginationBarTmpl = jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.templates.detailForm.paginationBar"),
			paginationLinkTmpl = jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.templates.detailForm.paginationLink"),
			elementCounterTmpl = jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.templates.detailForm.elementCounter"),
			$separator = $(jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.templates.detailForm.separator")),
			$elementCounter = $(jQuery.jgrid.format(elementCounterTmpl, jqGridID, jQuery.rup.STATICS, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.numResult"))),
			$paginationBar = $(jQuery.jgrid.format(paginationBarTmpl, jqGridID)),
			$firstPaginationLink = $(jQuery.jgrid.format(paginationLinkTmpl, 'first_'+jqGridID, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.first"))),
			$backPaginationLink = $(jQuery.jgrid.format(paginationLinkTmpl, 'back_'+jqGridID, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.previous"))),
			$forwardPaginationLink = $(jQuery.jgrid.format(paginationLinkTmpl, 'forward_'+jqGridID, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.next"))),
			$lastPaginationLink = $(jQuery.jgrid.format(paginationLinkTmpl, 'last_'+jqGridID, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.last"))),
			extpost;
			
			$paginationBar.append($firstPaginationLink)
			.append($backPaginationLink)
			.append($forwardPaginationLink)
			.append($lastPaginationLink);
			
			function doLinkNavigation(linkId){
				var retNavParams = $.proxy(settings.fncGetNavigationParams,$self)(linkId);
				if($.proxy($.jgrid.checkUpdates, $self[0])(extpost, function(){
					$.proxy(settings.doNavigation,$self)(retNavParams);
				})){
					$.proxy(settings.doNavigation,$self)(retNavParams);
				}
			}
			
			// Elemento primero
			$firstPaginationLink.on("click", function(){
				doLinkNavigation('first');
			});
			
			// Elemento anterior
			$backPaginationLink.on("click", function(){
				doLinkNavigation('prev');
			});
			
			// Elemento siguiente
			$forwardPaginationLink.on("click", function(){
				doLinkNavigation('next');
			});
			
			// Elemento ultimo
			$lastPaginationLink.on("click", function(){
				doLinkNavigation('last');
			});
			
			return $elementCounter.after($paginationBar).after($separator);
		},
		/*
		 * Realiza el borrado de un registro determinado.
		 */
		deleteElement: function (rowId, options){
			var $self = this, 
				settings = $self.data("settings"),
//				deleteOptions = jQuery.extend(true, {}, jQuery.fn.rup_table.defaults.deleteOptions, options),
				deleteOptions = jQuery.extend(true, {}, settings.formEdit.deleteOptions, options),
				selectedRow = (rowId===undefined?$self.jqGrid('getGridParam','selrow'):rowId);

			// En caso de especificarse el uso del método HTTP DELETE, se anyade el identificador como PathParameter
			if (deleteOptions.mtype==="DELETE"){
				deleteOptions.url = settings.formEdit.editurl+"/"+selectedRow;
			}
			
			$self.jqGrid('delGridRow',selectedRow, deleteOptions);
			
			return $self;
		},
		/*
		 * Lanza la edición de un registro medainte un formulario de detalle.
		 */
		editElement: function (rowId, options){
			var $self = this, 
				settings = $self.data("settings"),
//				selectedRow = (rowId===undefined?$self.jqGrid('getGridParam','selrow'):rowId);
				selectedRow = (rowId===undefined?$.proxy(settings.getRowForEditing,$self)():rowId);
			if (selectedRow !== false){
				$self.jqGrid('editGridRow', selectedRow, settings.formEdit.editOptions);
			}
			
			return $self;
		},
		/*
		 * Oculta los mensajes de error del formulario indicado
		 */
		hideFormErrors: function ($form){
			// Ocultamos el feedback de error
			jQuery("#FormError", $form).hide();
			jQuery(".rup-maint_validateIcon", $form).remove();
			jQuery(".rup-maint_validateIcon", $form).remove();
			jQuery("input.error", $form).removeClass("error");
			
		},
		/*
		 * Inicia el proceso de inserción de un nuevo registro.
		 */
		newElement : function(){
			var $self = this, 
			settings = $self.data("settings");
			$self.jqGrid('editGridRow', "new", settings.formEdit.addOptions);
			
			return $self;
		},
		/*
		 * Función encargada de mostrar los errores producidos en la gestión de los datos del mantenimiento.
		 */
		showServerValidationFieldErrors: function(errors){
			// console.log("maint - showFieldValidationErrors");
			var $self = $(this), settings = $self.data("settings");
//			mant=this, 
//			errorTXT = $.rup.i18nParse($.rup.i18n.base,"rup_maint.validateError"), 
//			errorKey = null, 
//			detailFormName, 
//			preMode;
			
			if(errors.rupErrorFields!==undefined || errors.rupFeedback!==undefined){
				settings.formEdit.$detailForm.validate().submitted=$.extend(true, settings.formEdit.$detailForm.validate().submitted,errors.rupErrorFields);
				settings.formEdit.$detailForm.validate().invalid=errors.rupErrorFields;
				settings.formEdit.$detailForm.validate().showErrors(errors.rupErrorFields);
			}else if(errors.rupFeedback!==undefined){
				settings.formEdit.$detailForm.validate().settings.feedback.rup_feedback("set", $.rup_utils.printMsg(errors.rupFeedback.message), (errors.rupFeedback.imgClass!==undefined?errors.rupFeedback.imgClass:null));
			}
				
		}
	});
	
		
	//*******************************************************
	// DEFINICIÓN DE LA CONFIGURACION POR DEFECTO DEL PATRON  
	//*******************************************************
	jQuery.fn.rup_table.plugins.formEdit = {};
	jQuery.fn.rup_table.plugins.formEdit.defaults = {
			toolbar:{
				defaultSave: false,
				defaultCancel: false
			},
			formEdit:{
				autoselectFirstRecord: true
			}
	};	
	
	
	// Parámetros de configuración por defecto para la acción de eliminar un registro.
//	jQuery.fn.rup_table.defaults.deleteOptions = {
	jQuery.fn.rup_table.plugins.formEdit.defaults.formEdit.deleteOptions = {
			bSubmit: jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_message.aceptar"),
			cancelicon:[true, "left", "icono_cancelar"],
			delicon:[false],
			linkStyleButtons: ["#eData"],
			msg: '<div id="rup_msgDIV_msg_icon" class="rup-message_icon-confirm"></div><div id="rup_msgDIV_msg" class="rup-message_msg-confirm white-space-normal">'+jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_maint.deleteAll")+'</div>',
			mtype:"DELETE",
			width: 320,
			reloadAfterSubmit:false, 
			resize:false,
			useDataProxy:true
	};
	
	// Parámetros de configuración por defecto para la acción de añadir y editar un registro.
//	jQuery.fn.rup_table.defaults.addEditOptions = {
	jQuery.fn.rup_table.plugins.formEdit.defaults.formEdit.addEditOptions = {
			bSubmit: jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_message.aceptar"),
			closeicon:[false],
			checkOnUpdate:true,
			fillDataMethod:"serverSide", // clientSide || serverSide
			saveicon:[false],
			linkStyleButtons: ["#cData"],
			msg: '<div id="rup_msgDIV_msg_icon" class="rup-message_icon-confirm"></div><div id="rup_msgDIV_msg" class="rup-message_msg-confirm white-space-normal">'+jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_maint.deleteAll")+'</div>',
			mtype: "PUT",
			reloadAfterSubmit:false, 
			resize:false,
			viewPagerButtons: false, // TODO: no permitir el habilitarlo
			width:569,
			serializeEditData:function serializeEditData_default(postdata){
				var $self = $(this), 
					settings = $self.data("settings"),
					jsonData = jQuery.toJSON(form2object(settings.formEdit.$detailForm[0],null,false));
				
				return jsonData.split('null').join(''); //IE FIX (null values)
			},
			ajaxEditOptions:{
				contentType: 'application/json',
				type:"PUT",
				dataType: 'json'
			}
	};

	// Parámetros de configruación específicos para la acción de añadir un registro
//	jQuery.fn.rup_table.defaults.addOptions = {
	jQuery.fn.rup_table.plugins.formEdit.defaults.formEdit.addOptions = {
			mtype: "POST",
			ajaxEditOptions:{
				type:"POST"
			}
	};
	
	// Parámetros de configruación específicos para la acción de editar un registro
//	jQuery.fn.rup_table.defaults.editOptions = {
	jQuery.fn.rup_table.plugins.formEdit.defaults.formEdit.editOptions = {
			mtype: "PUT",
			ajaxEditOptions:{
				type:"PUT"
			}
	};
	
	// Parámetros de configuración por defecto para la obtención del detalle de un registro
//	jQuery.fn.rup_table.defaults.detailOptions = {
	jQuery.fn.rup_table.plugins.formEdit.defaults.formEdit.detailOptions = {
			ajaxDetailOptions:{
				dataType: 'json',
				type: "GET",
				async: false,
				contentType: 'application/json'
			}
	};
	
})(jQuery);