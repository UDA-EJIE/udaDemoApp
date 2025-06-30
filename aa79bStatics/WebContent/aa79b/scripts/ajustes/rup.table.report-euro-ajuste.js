
(function(jQuery) {
	
	jQuery.fn.rup_table("extend",{
		postConfigureReport: function(settings){
			var $self = this,
				colModel = $self.rup_table("getColModel"),
				colNames = settings.colNames,
				reportsColums,
				reportSettings = settings.report;
			
			reportsColums = $self._getReportColumns(colModel, settings);
			
			/*
			 * INICIALIZACION DE VALORES POR DEFECTO 
			 */
			
			// Inicialización de la toolbar del componente table en caso de que no se especifique
			if (reportSettings.appendTo===undefined){
				reportSettings.appendTo = settings.toolbar.id;
			} 
			// Se toman los parámetros columns globales como base para los específicos
			jQuery.each(reportSettings.buttons[0].buttons, function(index, element){
				element.columns = $.extend(true, {}, reportSettings.columns, element.columns);
				if (element.columns.grid===undefined){
					element.columns.grid = settings.id;
				}
				
				if (element.columns.customNames===undefined){
					element.columns.customNames = reportsColums;
				}

				if (element.columns.click===undefined){
					element.columns.click = function(){};
				}
				
				
			});
				
			
			reportSettings.fncGetGridParam = function(){
				var $self = this, settings = $self.data("settings"),
				data={}, filterData = {};

				jQuery.each($self.jqGrid("getGridParam", "postData"), function(index, elem){
					if  (jQuery.inArray(index, settings.report.sendPostDataParams)!==-1){
						data[index] = elem;
					}
				});
				
				if (settings.filter !== undefined && settings.filter.$filterContainer!== undefined){
					filterData = $self.rup_table("getFilterParams");
				}
				
				jQuery.extend(true, data, filterData);
				
				return $.rup_utils.unnestjson(data);
			};
			
			/*
			 * FIN DE INICIALIZACION DE VALORES POR DEFECTO 
			 */
			
			jQuery.rup_report(reportSettings);
			
			/*
			 * INICIO DE LA MODIFICACION DE LA BOTONERA
			 */
			var column;
			var divCol1 = "<div id='print_div_"+reportSettings.buttons[0].id+"' class='columnasDiv'><ul>";
			var li = "";
			var showHiddenColumns = reportSettings.columns[0] != undefined ? reportSettings.columns[0].hidden != undefined ? reportSettings.columns[0].hidden
																														   : false
																		   : false;
			
			for (i=0;i<colModel.length;i++){
				column = colModel[i];
				if (column.name!='cb' 
						&& column.name!='rupInfoCol'
						&& column.name!='pkCol'
						&& (!column.hidden || showHiddenColumns)
						&& !column.noprint){
					if(colModel.length!=colNames.length){
						li = "<li><input type='checkbox' checked='checked' id='"+i+"' value='"
						+column.name + "' name='"
						+colNames[i-1] + "'/>" + colNames[i-1] + "</li>";
					}else{
						li = "<li><input type='checkbox' checked='checked' id='"+i+"' value='"
						+column.name + "' name='"
						+colNames[i] + "'/>" + colNames[i] + "</li>";
					}
					
					divCol1 = divCol1+ li;
				}
			}
			
			divCol1 = divCol1 + "</ul></div>";
			jQuery("[id="+reportSettings.appendTo+"##"+reportSettings.buttons[0].id+"-mbutton-container]").prepend($(divCol1));
			//$(document).unbind("click");
			
			jQuery("#print_div_"+reportSettings.buttons[0].id).children().bind('click', function(e){
				  e.stopPropagation();
				});
			
		},
		getSelectedRowData: function(){
			var $self = $(this);
			var rowid = $self.rup_table("getGridParam", "selrow");
			return $self.jqGrid("getRowData", rowid);
		}
		, isSelected: function(){
			var $self = $(this);
			if($self.rup_table("getGridParam", "usePlugins").indexOf("multiselection") != -1){
				var selec = $self.rup_table("getSelectedIds");
				return selec.selectedAll || selec.selectedIds != null;
			} else {
				return $self.rup_table("getGridParam", "selrow") != null;
			}
		}
	});
	
	$.rup_report("extend",{
		_configureButton: function(button, defaultDialog, customDialog, settings){
			var self = this,
			dialog = {};
			$.extend(dialog,defaultDialog); //Copiar el dialogo de por defecto
			//isInline a false por defecto
			button.isInline = (button.isInline===undefined)?false:button.isInline;
			button.detalleFila = (button.detalleFila===undefined)?false:button.detalleFila;
			button.detalleMulti = (button.detalleMulti===undefined)?false:button.detalleMulti;
			button.detalleInput = (button.detalleInput===undefined)?false:button.detalleInput;
			button.detalleColumna = (button.detalleColumna===undefined)?false:button.detalleColumna;
			button.limiteRegistros = (button.limiteRegistros===undefined)?0:button.limiteRegistros;
			button.detalleFiltro = (button.detalleFiltro===undefined)?false:button.detalleFiltro;
			//Si no es inLine
			if (!button.isInline){
				$("[id='"+button.id+"']").on("click", function (){
					if(button.dialogo!==undefined){
						jQuery("#"+button.dialogo+"_Aceptar").off();
						jQuery("#"+button.dialogo+"_Aceptar").on( "click", function() {
							//Ocultar MButton
							var $container = $(this).parents("div.rup-toolbar_menuButtonContainer");
							if ($container.length>0){
								$container.showMButton();
								$container.removeClass("rup-toolbar_menuButtonSlided");
							}									
		
							var data = {};
		
		
							if($("#print_div_"+button.divId+" input:checkbox").length>0) {
								var columnasSel = $("#print_div_"+button.divId+" input:checkbox:checked");
								if (columnasSel.length==0){
									// Mostrar mensaje de error
									alert($.rup.i18nParse($.rup.i18n.app, "comun.seleccionecolumna"));
									return false;
								}
								
								//Controlar columnas
								if (button.columns!==undefined){
									//GridParams
									var $grid = $($.find("#"+button.columns.grid)[0]);
									if($grid.getGridParam("records") > button.limiteRegistros
											&& button.limiteRegistros > 0){
										alert($.format($.rup.i18nParse($.rup.i18n.app, "comun.limiteExportar"), button.limiteRegistros));
										return false;
									} 
									if (jQuery.isFunction(settings.fncGetGridParam)){
										$.extend(true, data, jQuery.proxy(settings.fncGetGridParam, $grid)());
									}else{
										$.extend(true, data, $grid.jqGrid("getGridParam", "postData"));
									}
									if (settings.buttons[0].modo!==undefined){
										data["columns"] = $.toJSON(self._getColumnsNoGenerico($grid,columnasSel, button.columns.datos));
									}else{
										data["columns"] = $.toJSON(self._getColumns($grid,columnasSel));
									}
									data["criterios"] = $.toJSON(self._getFilter($grid));
								}
							}
							
							if(button.detalleFila){
								var $grid = $($.find("#"+button.grid)[0]);
								var idFila = $grid.jqGrid("getGridParam", "selrow");
								if(!idFila){
									alert($.rup.i18nParse($.rup.i18n.app, "comun.warningSeleccion"));
									return false;
								}
								data["id"] = idFila;
							} else if(button.detalleMulti){
								var $grid = $($.find("#"+button.grid)[0]);						
								if(!$grid.rup_table("isSelected")){
									alert($.rup.i18nParse($.rup.i18n.app, "comun.warningSeleccion"));
									return false;
								}else{
									if (jQuery.isFunction(settings.fncGetGridParam)){
										$.extend(true, data, jQuery.proxy(settings.fncGetGridParam, $grid)());
									}else{
										$.extend(true, data, $grid.jqGrid("getGridParam", "postData"));
									}
									data["selectedIds"] = $grid.rup_table("getSelectedIds").selectedIds;
									data["selectedAll"] = $.toJSON($grid.rup_table("getSelectedIds").selectedAll);
								}
	
							}else if(button.detalleInput){
								var $input = $($.find("#"+button.idInput)[0]);
								data[$input.attr("name") || button.idInput] = $input.val();
							} else if(button.detalleColumna){
								data["id"] = $("#"+button.grid).rup_table("getSelectedRowData")[button.nameColumna];
							} else if(button.detalleFiltro){
								var $grid = $($.find("#"+button.grid)[0]);
								var $form = $.find("#"+$grid.getGridParam("filter").id)[0];
								var	filterParams = form2object($form);
								jQuery.extend(data, filterParams);
								data["criterios"] = $.toJSON(self._getFilter($grid));
							}
							//Dialogo de espera
							var $reportFileWait = $("#"+dialog.waitDiv);
							$reportFileWait.rup_dialog({
							    type: $.rup.dialog.TEXT,
							    autoOpen: false,
							    modal: true,
								resizable: false,
								close: function(event, ui) {
									if ($.rup.browser.isIE){
										//IE
										document.execCommand('Stop');
									} else {
										//Netscape/Mozilla/Firefox
										window.stop();
									}
								}
							});
							if (standarDialog){
								//Titulo
								$reportFileWait.rup_dialog("setOption", "title", dialog.wait.title);
								//Contenido
									var content = $reportFileWait.html().split($reportFileWait.text()),
										html = "";
									for (var i=0; i<content.length; i++){
										if (content[i]===""){
											html += dialog.wait.msg;
										} else {
											html += content[i];
										}
									}
									$reportFileWait.html(html);
							}
							$reportFileWait.rup_dialog("open");
							
							//Lanzar petición
						    $.fileDownload($.rup_utils.setNoPortalParam(button.url), {
						    	httpMethod: "POST",
								data: jQuery.rup_utils.unnestjson(data),
						        successCallback: function (url) {
						        	if (dialog.successCallback!==undefined){
						        		dialog.successCallback();
									}  
						        	$reportFileWait.rup_dialog("close");
						        },
						        failCallback: function (responseHtml, url) {
						        	$reportFileWait.rup_dialog("close");
						        	var $reportFileError = $("#"+dialog.errorDiv);
						        	$reportFileError.rup_dialog({
									    type: $.rup.dialog.TEXT,
									    autoOpen: false,
									    modal: true,
										resizable: false
									});
						            if (standarDialog){
						            	//Titulo
						            	$reportFileError.rup_dialog("setOption", "title", dialog.error.title);
										//Contenido
						            	//$reportFileError.rup_dialog("setOption", "message", dialog.error.msg);
						            	$reportFileError.html(dialog.error.msg);
						            }
						            $reportFileError.rup_dialog("open");
						            
						            if (dialog.failCallback!==undefined){
						            	dialog.failCallback();
									} 
						        }
						    });
						    jQuery("#"+button.dialogo).rup_dialog("close");				
						    return false;
						});
						jQuery("#"+button.dialogo).rup_dialog("open");
					}else{
						//Ocultar MButton
						var $container = $(this).parents("div.rup-toolbar_menuButtonContainer");
						if ($container.length>0){
							$container.showMButton();
							$container.removeClass("rup-toolbar_menuButtonSlided");
						}									
	
						var data = {};
	
						if($("#print_div_"+button.divId+" input:checkbox").length>0) {
							var columnasSel = $("#print_div_"+button.divId+" input:checkbox:checked");
							if (columnasSel.length==0){
								if(button.detalleMulti){	
									// Mostrar mensaje de error
									alert($.rup.i18nParse($.rup.i18n.app, "comun.seleccionedestreza"));
								}else{
									// Mostrar mensaje de error
									alert($.rup.i18nParse($.rup.i18n.app, "comun.seleccionecolumna"));
								}
								return false;
								
							}
							
							//Controlar columnas
							if (button.columns!==undefined){
								//GridParams
								var $grid = $($.find("#"+button.columns.grid)[0]);
								if($grid.getGridParam("records") > button.limiteRegistros
										&& button.limiteRegistros > 0){
									alert($.format($.rup.i18nParse($.rup.i18n.app, "comun.limiteExportar"), button.limiteRegistros));
									return false;
								} 
								if (jQuery.isFunction(settings.fncGetGridParam)){
									$.extend(true, data, jQuery.proxy(settings.fncGetGridParam, $grid)());
								}else{
									$.extend(true, data, $grid.jqGrid("getGridParam", "postData"));
								}
								if (settings.buttons[0].modo!==undefined){
									data["columns"] = $.toJSON(self._getColumnsNoGenerico($grid,columnasSel, button.columns.datos));
								}else{
									data["columns"] = $.toJSON(self._getColumns($grid,columnasSel));
								}
								data["criterios"] = $.toJSON(self._getFilter($grid));
							}
						}
						
						if(button.detalleFila){
							var $grid = $($.find("#"+button.grid)[0]);
							var idFila = $grid.jqGrid("getGridParam", "selrow");
							if(!idFila){
								alert($.rup.i18nParse($.rup.i18n.app, "comun.warningSeleccion"));
								return false;
							}
							data["id"] = idFila;
						} else if(button.detalleMulti){													
							var $grid = $($.find("#"+button.grid)[0]);						
							if(!$grid.rup_table("isSelected")){
								alert($.rup.i18nParse($.rup.i18n.app, "comun.warningSeleccion"));
								return false;
							}else{
//								if (jQuery.isFunction(settings.fncGetGridParam)){
//									data = jQuery.proxy(settings.fncGetGridParam, $grid)();
//								}else{
//									data = $grid.jqGrid("getGridParam", "postData");
//								}
								data["selectedIds"] = $.toJSON($grid.rup_table("getSelectedIds").selectedIds);
								data["selectedAll"] = $.toJSON($grid.rup_table("getSelectedIds").selectedAll);
							}

						}else if(button.detalleInput){
							var $input = $($.find("#"+button.idInput)[0]);
							data[$input.attr("name") || button.idInput] = $input.val();
						} else if(button.detalleColumna){
							data["id"] = $("#"+button.grid).rup_table("getSelectedRowData")[button.nameColumna];
						} else if(button.detalleFiltro){
							var $grid = $($.find("#"+button.grid)[0]);
							var $form = $.find("#"+$grid.getGridParam("filter").id)[0];
							var	filterParams = form2object($form);
							jQuery.extend(data, filterParams);
							data["criterios"] = $.toJSON(self._getFilter($grid));
						}
						
						//Dialogo propio?
						var standarDialog = true;
						if (button.customDialog !== undefined){
							//Buscar el dialogo correspondiente
							var actualDialog = customDialog[button.customDialog];
		
							/** WAIT **/
							//Sobreescritura del defaultDialog-wait
							if (actualDialog.waitDiv===undefined){
								dialog.wait = actualDialog.wait;
							//Dialogo propio completo
							} else {
								dialog.waitDiv = actualDialog.waitDiv;
								$("#"+dialog.waitDiv).addClass("rup_report");
							}
								
							/** ERROR **/
							//Sobreescritura del defaultDialog-error
							if (actualDialog.errorDiv===undefined){
								dialog.error = actualDialog.error;
							//Dialogo propio completo	
							} else {
								dialog.errorDiv = actualDialog.errorDiv;
								$("#"+dialog.errorDiv).addClass("rup_report");
							}
		
							dialog.successCallback = actualDialog.successCallback;
							dialog.failCallback = actualDialog.failCallback;
						} 
						
						//Dialogo de espera
					var $reportFileWait = $("#"+dialog.waitDiv);
					$reportFileWait.rup_dialog({
					    type: $.rup.dialog.TEXT,
					    autoOpen: false,
					    modal: true,
						resizable: false,
						close: function(event, ui) {
							if ($.rup.browser.isIE){
								//IE
								document.execCommand('Stop');
							} else {
								//Netscape/Mozilla/Firefox
								window.stop();
							}
						}
					});
					if (standarDialog){
						//Titulo
						$reportFileWait.rup_dialog("setOption", "title", dialog.wait.title);
						//Contenido
							var content = $reportFileWait.html().split($reportFileWait.text()),
								html = "";
							for (var i=0; i<content.length; i++){
								if (content[i]===""){
									html += dialog.wait.msg;
								} else {
									html += content[i];
								}
							}
							$reportFileWait.html(html);
					}
					$reportFileWait.rup_dialog("open");
					
					//Lanzar petición
				    $.fileDownload($.rup_utils.setNoPortalParam(button.url), {
				    	httpMethod: "POST",
						data: jQuery.rup_utils.unnestjson(data),
				        successCallback: function (url) {
				        	if (dialog.successCallback!==undefined){
				        		dialog.successCallback();
							}  
				        	$reportFileWait.rup_dialog("close");
				        },
				        failCallback: function (responseHtml, url) {
				        	$reportFileWait.rup_dialog("close");
				        	var $reportFileError = $("#"+dialog.errorDiv);
				        	$reportFileError.rup_dialog({
							    type: $.rup.dialog.TEXT,
							    autoOpen: false,
							    modal: true,
								resizable: false
							});
				            if (standarDialog){
				            	//Titulo
				            	$reportFileError.rup_dialog("setOption", "title", dialog.error.title);
								//Contenido
				            	//$reportFileError.rup_dialog("setOption", "message", dialog.error.msg);
				            	$reportFileError.html(dialog.error.msg);
				            }
				            $reportFileError.rup_dialog("open");
				            
				            if (dialog.failCallback!==undefined){
				            	dialog.failCallback();
							} 
				        }
				    });
									
					
				    return false;
				}
			}
			);
		} else {
			$("[id='"+button.id+"']").on("click", function (){
				window.open(button.url+"?isInline=true","_blank");
			});
		}
			
		},_init : function(args) {
			if (args.length > 1) {
				$.rup.errorGestor($.rup.i18n.base.rup_global.initError + $(this).attr("id"));
			}
			else {				
				//Se recogen y cruzan las paremetrizaciones del objeto
				var self = this,
					settings = $.extend(true, {}, $.rup_report.defaults, args[0]),
					//Objetos componente (params)
					buttons = settings.buttons,
					defaultDialog = settings.dialog,
					customDialog = settings.customDialog,
					//Contenedor de botones
					$container = $.find("[id='"+settings.appendTo+"']");
				
				//Existe capa contenedora ?
				if ($.isEmptyObject($container)){
					alert('No existe objeto al que añadir el componente');
					return false;
				}
				$container = $($container[0]);
				
				//Guardar settings
				$container.data("report", settings);
			
				
				if(!$("#reportFileWait").length>0){
				
				//Añadir dialogo por defecto
				var $defaultDialog_wait = $("<div />")
										.attr("id","reportFileWait")
										.attr("title",defaultDialog.wait.title)
										.text(defaultDialog.wait.msg)
										.addClass("rup_report")
										.hide()
										//progressbar
										.append($("<div />").addClass("ui-progressbar ui-progressbar-value ui-corner-left ui-corner-right")),
					$defaultDialog_error = $("<div />")
										.attr("id","reportFileError")
										.attr("title",defaultDialog.error.title)
										.text(defaultDialog.error.msg)
										.addClass("rup_report")
										.hide(),
					$defaultDialog = $("<div />")
									.attr("id", "rup_report_dialogsContainer")
									.append($defaultDialog_wait)
									.append($defaultDialog_error);
				$container.after($defaultDialog);
				//Guardar datos dialogo por defecto
						
				
			}
				defaultDialog["waitDiv"] = "reportFileWait";
				defaultDialog["errorDiv"] = "reportFileError";		
				
				//El contenedor es un mButton
				var isMButton = false, $mbutton;
				if ($container.hasClass("rup-toolbar_menuButtonContainer")){
					$mbutton = $("[id='"+$container.attr("id").substring("8")+"']");
					$container =  $mbutton.parents(".rup-toolbar");
					isMButton = true;
					if ($.grep(buttons, function(object, index){ return object.buttons!==undefined; }).length>0){
						alert('No se pueden añadir MButtons a un contenedor MButton');
						return false;
					};
				} 
				
				//Recorrer los botones
				var errors = [];
				$.each(buttons, function(index, object){
					if (object.buttons!==undefined){
						//MBUTTON
						object["click"] = $container.showMButton;
						$mbutton = $container.addMButton(object, object["json_i18n"]);
						$container.addButtonsToMButton(object.buttons, $mbutton, object["json_i18n"]);
						//add click
						$.each(object.buttons, function(index, object){
							self._checkButton(object, errors);
							self._configureButton(object, defaultDialog, customDialog, settings);
						});
					} else {
						//BUTTON
						if (!isMButton){
							$container.addButton(object, object["json_i18n"]);
							object["id"] = $container.attr("id")+"##"+((object.id)?object.id:object.i18nCaption);
						} else {
							//Añadir botones a un MButton ya existente
							$container.addButtonsToMButton(new Array(object), $mbutton, object["json_i18n"]);
							//Mover el botón al final
							$mbuttonDiv = $("[id='mbutton_"+$mbutton.attr("id")+"']");
							$mbuttonDiv.find("li:first").appendTo($mbuttonDiv.children("ul"));
						}
						//add click
						self._checkButton(object, errors);
						self._configureButton(object, defaultDialog, customDialog, settings);
					}
				});
				
				if (errors.length>0){
					var txtErrors = "";
					for (var i=0; i<errors.length; i++){
						txtErrors += errors[i]+'<br/>';
					}
					alert(txtErrors);
				}
				
			}
		},
		_getColumns: function($grid, columnasSel){	
			//Preparar datos 
			var colModel = $grid.jqGrid("getGridParam", "colModel"),
				colNames = $grid.jqGrid("getGridParam", "colNames"),
				colNumber = colNames.length,
				columnsArray = [],
				column = [];
			for(i=0;i<colModel.length;i++){
				for(j=0;j<columnasSel.length;j++){
					if(colModel[i].name==columnasSel[j].value){
						var column = [];
						column.push(colModel[i].name);
						column.push(colModel[i].label);
						column.push(colModel[i].width);
						column.push(colModel[i].align!=undefined? colModel[i].align: "left");
						column.push(colModel[i].isNumeric!=undefined? colModel[i].isNumeric: false);
						column.push(colModel[i].isDate!=undefined? colModel[i].isDate: false);
						columnsArray.push(column);
						break;
					}
				}
			}
			
			return columnsArray;
		},
		_getColumnsNoGenerico: function($grid, columnasSel, columnas){
			//Preparar datos 
			var colModel = $grid.jqGrid("getGridParam", "colModel"),
				colNames = $grid.jqGrid("getGridParam", "colNames"),
				colNumber = colNames.length,
				columnsArray = [],
				column = [];
			for(i=0;i<columnasSel.length;i++){
				//if (colNames[columnasSel[i].id].indexOf("type='checkbox'")!==-1){ continue; } 
				var column = [];
				column.push(columnas[columnasSel[i].id].name);
				column.push(columnas[columnasSel[i].id].label);
				column.push(columnas[columnasSel[i].id].width);
				column.push(columnas[columnasSel[i].id].align!=undefined? columnas[columnasSel[i].id].align: "left");
				column.push(columnas[columnasSel[i].id].isNumeric!=undefined? columnas[columnasSel[i].id].isNumeric: false);
				column.push(columnas[columnasSel[i].id].isDate!=undefined? columnas[columnasSel[i].id].isDate: false);
				columnsArray.push(column);
			}
			return columnsArray;
		},
		_getFilter: function($grid){
			var settings = $grid.data("settings");
			var searchString = " ", temp = "", searchForm,
			field, fieldId, fieldName, fieldValue;
			if ( settings.filter!=null ) {
				var searchForm = settings.filter.$filterContainer[0],
				filterMulticombo = new Array();
				var columnsArray = [];
				for (var i = 0; i < searchForm.length; i++) {
					var column = [];
					//CAMPO a tratar
					field = searchForm[i];
					if(jQuery(field).is("fieldset") || field.type==="hidden" || field.type==="button" || $(field).hasClass("noPrint")){
						continue;
					}
					if (field.type === "radio" && !field.checked){
						continue;
					}
					//ID del campo
					fieldId = field.id;
					//ID para elementos tipo rup.combo
					/*if ($(field).attr("ruptype") === "combo"){
						if ($(field).next(".ui-multiselect").length==0){
							fieldId += "-button";
						}
					}*/
					//ID para elementos tipo rup.autocomplete
					if ($(field).attr("ruptype") === "autocomplete"){
						fieldId = fieldId.substring(0, fieldId.indexOf("_label"));
					}
					
					//NAME
					label = $("label[for^='" + fieldId + "']",searchForm);
					if (label.length>0){
						if(label.attr("data-i18n")===null || label.attr("data-i18n") === undefined){
							fieldName = label.html();
						}else{
							fieldName = label.attr("data-i18n");
						}
					}
					
					if (fieldName === null || fieldName === undefined){
						fieldName = "";
					}
					
					//VALUE
					fieldValue = "";
					switch($(field)[0].tagName){
						case "INPUT":
							fieldValue = fieldValue + $(field).val();
							if ($(field)[0].type === "checkbox" || $(field)[0].type === "radio"){
								if($(field)[0].checked){
									if($(field).attr("data-on-text")!= undefined){
										fieldValue = $(field).attr("data-on-text");
									}else{
										fieldValue = "S";
									}
									
								}else{
									if($(field).attr("data-off-text")!= undefined){
										fieldValue = $(field).attr("data-off-text");
									}else{
										fieldValue = "N";
									}
								}
							}
							if($(field).attr("data-concat")){
								if (field.value!=""){
									var valCursoHasta = parseInt($(field).val())+1;
									fieldValue = $(field).val() +"/"+ valCursoHasta ;
								}
							}
							break;
						case "SELECT":
							if ($(field).next(".ui-multiselect").length==0){
								if(field.value!=""){
									fieldValue = fieldValue + $("option[value='"+field.value+"']",field).html();
								}
							} else {
								if ($.inArray($(field).attr("id"), filterMulticombo)===-1){
									numSelected = $(field).rup_combo("value").length;
									if (numSelected !== 0){
										//fieldValue += numSelected;
										fieldValue = $(field).find("option:selected").map(function () {return " "+$(this).text();}).get().join().trim();
									} else {
										fieldValue = "";
									}
									filterMulticombo.push($(field).attr("id"));
								} else {
									fieldValue = "";
								}
							}
							break;
					}
					
					//Parsear NAME
					var parseableChars = new Array(":","=");
					for (var j=0; j<parseableChars.length; j++){
						if (fieldName !== "" && fieldName.indexOf(parseableChars[j])!== -1){
							fieldName = fieldName.substring(0,fieldName.indexOf(parseableChars[j]));
							break;
						}
					}
					
					//Controlar rup.combo con valor vacío
					while (fieldValue.indexOf("&amp;nbsp;")!==-1){
						fieldValue = fieldValue.replace ("&amp;nbsp;","");
					}
					
					//Si no tiene NAME sacar solo el valor
					if (fieldName === "" && fieldValue.indexOf(" = ")!==-1){
						fieldValue = fieldValue.substring(2, fieldValue.length); 
					}
					
					if(fieldName != ""){
						column.push(fieldName);
						column.push(fieldValue);
						columnsArray.push(column);
					}
				}
			}
			return columnsArray;
		}
	});
	
})(jQuery);