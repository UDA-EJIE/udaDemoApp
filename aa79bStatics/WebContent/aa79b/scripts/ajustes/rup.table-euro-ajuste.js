

(function ($) {

	/**
	 * Definición de los métodos principales que configuran la inicialización del plugin.
	 * 
	 * preConfiguration: Método que se ejecuta antes de la invocación del componente jqGrid.
	 * postConfiguration: Método que se ejecuta después de la invocación del componente jqGrid.
	 * 
	 */
	jQuery.rup_table.registerPlugin("ivap",{
		loadOrder:30,
		preConfiguration: function(settings){
			var $self = this;
			
			if (jQuery.inArray("multiselection", settings.usePlugins) !== -1 && jQuery.inArray("formEdit", settings.usePlugins) !== -1){
				return $self.rup_table("preConfigureIvap", settings);
			}
		},
		postConfiguration: function(settings){
			var $self = this;
			
			if (jQuery.inArray("multiselection", settings.usePlugins) !== -1 && jQuery.inArray("formEdit", settings.usePlugins) !== -1){
				return $self.rup_table("postConfigureIvap", settings);
			}
		}
	});
	
	//********************************
	// DEFINICIÓN DE MÉTODOS PÚBLICOS
	//********************************
	
	/**
	 */
	jQuery.fn.rup_table("extend",{
		preConfigureIvap: function(settings){
			var $self = this, colModel = settings.colModel;
			
			
			settings.core.operations["edit"] = {
				name: $.rup.i18nParse($.rup.i18n.base,"rup_table.modify"),
				icon: "rup-icon rup-icon-edit", 
				enabled: function(){
					var $self = this, settings = $self.data("settings");
					return jQuery.proxy(settings.fncHasSelectedElements, $self)();
				},
				callback: function(key, options){
					$self.rup_table("editElementIvap");			
				}
			};
			
			
		},
		filasSeleccionadas:function(settings){
			var $self = this;
			settings = $self.data("settings");
			return settings.multiselection.numSelected;
		},
		postConfigureIvap: function(settings){
			var $self = this, $objDetailForm;
			
			
		}
	});
	
	
	/**
	 */
	jQuery.fn.rup_table("extend",{
		editElementIvap: function (rowId, options){
			var $self = this, 
			settings = $self.data("settings"),
			multiselectionSettings = settings.multiselection,
			page = parseInt($self.rup_table("getGridParam", "page"),10),
			firstPage, firstSelectedLine, firstSelectedId;
			
			if (multiselectionSettings.selectedPages.length > 0){
				firstPage = multiselectionSettings.selectedPages[0];
				
				$self.on("jqGridAfterLoadComplete.ivap.editFirstElement",function(event,data){
					
					firstSelectedLine = $self._getFirstSelectedElementOfPage(firstPage);
					firstSelectedId = $self.jqGrid("getDataIDs")[firstSelectedLine-1];
					settings.multiselection.rowForEditing=firstSelectedId;
					$self.rup_table("clearHighlightedEditableRows");
					$self.rup_table("highlightEditableRow", $self.jqGrid("getInd",firstSelectedId, true));
					
					$self.rup_table("editElement", firstSelectedId);
					
					$self.off("jqGridAfterLoadComplete.ivap.editFirstElement");
				});
				
				if (firstPage !== page){
					$self.trigger("reloadGrid",[{page: firstPage}]);
					
				}else{
					$self.triggerHandler("jqGridAfterLoadComplete.ivap.editFirstElement");
				}
			}
		}
			
	});
	
		
	//***************************************
	// LA CONFIGURACION AÑADIDA DE RUP_TABLE  
	//***************************************
	
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
			
			for (i=0;i<colModel.length;i++){
				column = colModel[i];
				if (column.name!='cb' 
						&& column.name!='rupInfoCol'
						&& !column.hidden 
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
			jQuery("[id=mbutton_"+reportSettings.appendTo+"##"+reportSettings.buttons[0].id+"]").prepend($(divCol1));
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
		, selectAllRows: function selectAllRows(event) {
            var $self = this, settings = $self.data('settings'), arr, $row;
            $self.rup_table('selectRemainingRows');
            jQuery('#cb_' + settings.id).attr('checked', 'checked');
            // Se marcan los registros de la tabla como marcados
            arr = $self.jqGrid('getDataIDs');
            for (var i = 0; i < arr.length; i++) {
                $row = jQuery($self.jqGrid('getInd', arr[i], true));
                $self.rup_table('highlightRowAsSelected', $row);
            }
            $self.rup_table('highlightFirstEditableRow');
            if ($.isFunction(settings.onSelectAllRows)) {
            	settings.onSelectAllRows.call(settings);
            }
        }
		, deselectAllRows: function deselectAllRows(event) {
            var $self = this, settings = $self.data('settings'), arr, $row, internalProps = $self[0].p;
            $self.rup_table('deselectRemainingRows');
            $self.rup_table('clearHighlightedEditableRows');
            jQuery('#cb_' + settings.id).removeAttr('checked');
            // Se marcan los registros de la tabla como marcados
            arr = $self.jqGrid('getDataIDs');
            internalProps.selarrrow = [];
            for (var i = 0; i < arr.length; i++) {
                $row = jQuery($self.jqGrid('getInd', arr[i], true));
                $self.rup_table('clearHighlightedRowAsSelected', $row);
            }
            if ($.isFunction(settings.onSelectAllRows)) {
            	settings.onSelectAllRows.call(settings);
            }
        }
	});
	
	//*******************************************************
	// DEFINICIÓN DE LA CONFIGURACION POR DEFECTO DEL PATRON  
	//*******************************************************
	jQuery.fn.rup_table.plugins.ivap = {};
	jQuery.fn.rup_table.plugins.ivap.defaults = {
	};	
	
	$.fn.rup_combo("extend", {
		 _getBlankLabel: function (id) {
            var app = $.rup.i18n.app;
            if (app[id] && app[id]._blank) {
                return app[id]._blank;
            }
            return $.rup.i18nParse($.rup.i18n.app, "comun._blank");
        }
	});
	jQuery.fn.rup_table("extend",{
		showSearchCriteria: function(){
			var $self = this, settings = $self.data("settings"),
			searchString = " ", temp = "", aux, searchForm,
			field, fieldId, fieldName, fieldValue,
			aux = settings.filter.$filterContainer.serializeArray();
			searchForm = settings.filter.$filterContainer,
			filterMulticombo = new Array();  
			var obj;
			
			//añadir arbol
			var nombreArbol=$('.jstree',settings.filter.$filterContainer)
			var arboles=	$('.jstree',settings.filter.$filterContainer);
			$.each(arboles,function( index,item ){
				obj= new Object();
				obj.name=$(item).attr("name")
				obj.value=$(item).rup_tree("getRupValue").length;
				obj.type="rup_tree";
				aux.push(obj);
			});

			for (var i = 0; i < aux.length; i++) {
				if (aux[i].value !== ""  && $.inArray(aux[i].name,settings.filter.excludeSummary)!=0) {

					//CAMPO a tratar
					field = $("[name='" + aux[i].name + "']",searchForm);

					//Comprobar si se debe excluir el campo
					if ($.inArray(field.attr("id"), settings.filter.filterExclude) !== -1){
						continue;
					}
					
					//Seleccionar radio
					if (field.length > 1){
						field = $("[name='" + aux[i].name + "']:checked",searchForm);
					}
					//Omitir campos hidden
					if ($(field).attr("type") === "hidden"){
						continue;
					}
					
					//ID del campo
					fieldId = $(field).attr("id");
						//ID para elementos tipo rup.combo
						if ($(field).attr("ruptype") === "combo"){
							if (field.next(".ui-multiselect").length==0){
								fieldId += "-button";
							}
						}
						//ID para elementos tipo rup.autocomplete
						if ($(field).attr("ruptype") === "autocomplete"){
							fieldId = fieldId.substring(0, fieldId.indexOf("_label"));
						}
					
					//NAME
					label = $("label[for^='" + fieldId + "']",searchForm);
					if (label.length>0){
						// <label for='xxx' />
						fieldName = label.html();
					} else {
						// <div />
						// <div />
						if ($(field).attr("ruptype") !== "combo"){
							//fieldName= $("[name='" + aux[i].name + "']",searchForm).prev('div').html();
							fieldName= $("[name='" + aux[i].name + "']",searchForm).prev('div').find('label').first().html();
						} else {
							//fieldName= $("[name='" + aux[i].name + "']",searchForm).parent().prev('div').html();
							
							// Buscamos el label asociado al combo
							// Primero por id 
							var $auxField = $("[name='" + aux[i].name + "']",searchForm), $labelField;
							
							$labelField = jQuery("[for='"+$auxField.attr("id")+"']");
							
							if ($labelField.length>0){
								fieldName = $labelField.first().text();
							}else{
							
									fieldName= $("[name='" + aux[i].name + "']",searchForm).parent().prev('div').find('label').first().html();
								
							}
						}
					}
					if (fieldName === null || fieldName === undefined){
						fieldName = "";
					}
					
					//VALUE
					fieldValue = " = ";
					switch($(field)[0].tagName){
						case "INPUT":
							fieldValue = fieldValue + $(field).val();
							if ($(field)[0].type === "checkbox" || $(field)[0].type === "radio"){
								fieldValue = "";
							}
							if($(field).attr("data-concat")){
								if (field.value!=""){
									var valCursoHasta = parseInt($(field).val())+1;
									fieldValue +="/"+ valCursoHasta ;
								}
							}
							break;
						//Rup-tree
						case "DIV":
							$.each(aux,function( index,item ){
								if (item.name==field.attr('id')){
									if (item.value!=0){
									fieldValue +=" = "+ item.value;
									}
								} else {
									fieldValue = "";
								}
								
								
							});
							if (fieldValue==""){
								fieldName = "";
							}	
							break;
						case "SELECT":
							if (field.next(".ui-multiselect").length==0){
								fieldValue = fieldValue + $("option[value='"+aux[i].value+"']",field).html();
							} else {
								if ($.inArray($(field).attr("id"), filterMulticombo)===-1){
									numSelected = field.rup_combo("value").length;
									if (numSelected !== 0){
										fieldValue += numSelected; 
									} else {
										fieldName = "";
										fieldValue = "";
									}
									filterMulticombo.push($(field).attr("id"));
								} else {
									fieldName = "";
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
					
					
					//Si no tiene NAME ni VALUE omitir
					if (fieldName === "" && $.trim(fieldValue) === ""){
						continue;
					}
					searchString = searchString + fieldName + fieldValue + ", ";
				}
			}
			
			if (jQuery.isFunction(settings.filter.fncSearchCriteria)){
				searchString = jQuery.proxy(settings.filter.fncSearchCriteria, $self, searchString)();
			}
			
			if ($.trim(searchString).length-1==$.trim(searchString).lastIndexOf(","))
			{
				searchString=searchString.substr(0,searchString.lastIndexOf(','))+" ";
			}
			
			if (settings.multifilter) {
				if (jQuery.isFunction(settings.multifilter.fncFilterName)){
					searchString = jQuery.proxy(settings.multifilter.fncFilterName, $self, searchString)();
				}
			}
			//Contiene criterios
//			if (searchString.length>1){
				//searchString = searchString.substring(0, searchString.length-2);
				
				var initialHeight = $('#titleSearch_' + settings.id.name).css("height"),
					height,
					tmp = searchString,
					tooltip = false;

				//Añadir criterios
				while(true){
					settings.filter.$filterSummary.html(" <i>" + tmp + "</i>");
					height = $('#titleSearch_' + settings.id.name).css("height");
					if (height === initialHeight){
						break;
					}
					tmp = tmp.substring(0, tmp.lastIndexOf(",")) + " <b>...</b>";
					tooltip = true;
				}

				//Tooltip con criterios
				if (tooltip){
					settings.filter.$filterSummary
						.rup_tooltip({
							content: {
								text: searchString.substring(1)
							},
							position: {
								my: 'bottom center',
								at: 'top center'
							}
						});
				}
			}
	});
	
	$.extend($.jgrid,{
		compareData:function compareData(nObj,oObj){
			var reg = new RegExp("_id$");
			var ret = false, key, unnestNObj = jQuery.rup_utils.unnestjson(nObj), unnestOObj = jQuery.rup_utils.unnestjson(oObj);
			for (key in unnestNObj) {
				if (unnestNObj.hasOwnProperty(key) && !reg.test(key) && String(unnestNObj[key]) !== String(unnestOObj[key])) {
					ret = true;
					// Descomentar para debug
					// console.log(" Compare data: "+ key+ " new: "+String(unnestNObj[key]) + " old: "+String(unnestOObj[key]));
					break;
				}
			}
			return ret;
		}
	});
	jQuery.fn.rup_table("extend",{
		getNumRegistrosSeleccionados :function (){
			var $self = this;
			var selOptions = $("#"+$self.data('settings').id).rup_table("getSelectedIds");
			var ret;
			if(selOptions.selectedAll){
				ret = $("#"+$self.data('settings').id).rup_table("getGridParam","records");
				if(selOptions.selectedIds){
					ret = ret - selOptions.selectedIds.length;
				}
			}else if(selOptions.selectedIds){
				ret = selOptions.selectedIds.length;
			}else{
				ret = 0;
			}
			return ret;
		}
	});
	
})(jQuery);