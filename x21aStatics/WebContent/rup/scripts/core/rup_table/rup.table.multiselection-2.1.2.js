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

	
	jQuery.rup_table.registerPlugin("multiselection",{
		loadOrder:7,
		preConfiguration: function(settings){
			var $self = this;
			$self.rup_table("preConfigureMultiselection",settings);
		},
		postConfiguration: function(settings){
			var $self = this;
			
			if (settings.multiselect===true){
				$self.rup_table("postConfigureMultiselection",settings);
			}
		}
	});
	
	//********************************
	// DEFINICIÓN DE MÉTODOS PÚBLICOS
	//********************************
	/**
	 *  
	 */
	jQuery.fn.rup_table("extend",{
		preConfigureMultiselection: function(settings){
			// Añadimos la columna por defecto para mostrar la información del registro en edición
			settings.colNames = $.merge([""], settings.colNames);
			settings.colModel = $.merge([settings.multiselection.defaultEditableInfoCol], settings.colModel);
			
			// Se configura la propiedad multiselecta true para que el plugin subyacente se configure en modo multiseleccion
			settings.multiselect = true;
			/*
			 * Definición del método serializeGridData para que añada al postData la información relativa a la multiseleccion.
			 */
			settings.serializeGridData = function(postData){
				var $self = $(this);
				if (settings && settings.multiselection){
					if (settings.multiselection.numSelected>0){
						if (!settings.multiselection.selectedAll){
							postData["multiselectionIds"] = $.toJSON(settings.multiselection.selectedIds);
							postData["selectedAll"] = false;
						}else{
							postData["multiselectionIds"] = $.toJSON(settings.multiselection.deselectedIds);
							postData["selectedAll"] = true;
						}
					}
				}
				return postData;
			};
		},
		postConfigureMultiselection: function(settings){
			var $self = this;

			// Inicialización de las propiedades asociadas a la gestión de los registros seleccionados
			$self._initializeMultiselectionProps(settings);
			// Se almacena la referencia del check de (de)seleccionar todos los registros
			settings.$selectAllCheck = jQuery("#cb_"+settings.id);
			
			settings.fncHasSelectedElements = function(){
				return settings.multiselection.numSelected>0;
			};
			
			settings.getRowForEditing = function(){
				var $self = this, settings = $self.data("settings"),
				page = parseInt($self.rup_table("getGridParam", "page"),10),
				nPos = jQuery.proxy(jQuery.jgrid.getCurrPos, $self[0])(),
				index, retNavParams;
				
				if ($self._hasPageSelectedElements(page)){
					if (settings.multiselection.rowForEditing!==undefined){
						return settings.multiselection.rowForEditing;
					}
					index = $self._getSelectedLinesOfPage(page)[0];
					return nPos[1][index-1];
				}else{
					retNavParams = $.proxy(settings.fncGetNavigationParams,$self)('first');
					
					execute = retNavParams[1];
					newPage = retNavParams[5];
					newPageIndex = retNavParams[6];
					
					if (execute){
						$self.trigger("reloadGrid",[{page: newPage}]);
						$self.on("jqGridAfterLoadComplete.multiselection.editRow",function(event,data){
							var nextPagePos = jQuery.proxy(jQuery.jgrid.getCurrPos, $self[0])(),
							newIndexPos = nextPagePos[1][$self._getSelectedLinesOfPage(newPage)[0]-1];
							$self.jqGrid('editGridRow', newIndexPos, settings.editOptions);
							$self.off("jqGridAfterLoadComplete.multiselection.editRow");
						});
					}
					
					return false;
				}
			};
			
			settings.getDetailTotalRowCount = function(){
				var $self = this, settings = $self.data("settings");
				return settings.multiselection.numSelected;
			};
			
			settings.getDetailCurrentRowCount = function(){
				var $self = this, settings = $self.data("settings"),
				page = parseInt($self.rup_table("getGridParam", "page"),10),
				currentRow = jQuery.proxy(jQuery.jgrid.getCurrPos, $self[0])(),
				rowsPerPage = parseInt($self.rup_table("getGridParam", "rowNum"),10),
				selectedPagesArrayIndex,
				cont=0;
				
				// Comprobamos si se han seleccionado todos los registros de la tabla
				if (!settings.multiselection.selectedAll){
					// En caso de que no se hayan seleccionado
					// Se obtiene el indice de la página actual dentro del array de páginas deseleccionadas para  
					selectedPagesArrayIndex = jQuery.inArray(page, settings.multiselection.selectedPages)+1;
					
					for (var i=1;i<selectedPagesArrayIndex;i++){
						cont+=settings.multiselection.selectedLinesPerPage[i].length;
					}
					
					cont+=$.inArray(currentRow[0]+1, settings.multiselection.selectedLinesPerPage[selectedPagesArrayIndex])+1;
				}else{
					cont = (page>1?((page-1)-settings.multiselection.deselectedPages.length)*rowsPerPage:0);
					for (var i=0;i<settings.multiselection.deselectedPages.length && settings.multiselection.deselectedPages[i]!==page;i++){
						cont+=rowsPerPage-settings.multiselection.deselectedLinesPerPage[settings.multiselection.deselectedPages[i]].length;
					}
					cont+=jQuery.inArray(currentRow[0]+1, $self._getSelectedLinesOfPage(page))+1;
				}
				
				return cont;
			};
			
			settings.fncGetNavigationParams = function getNavigationParams_multiselection(linkType ){
				var $self = this, settings = $self.data("settings"), execute = false, changePage = false, index=0, newPageIndex=0,
				npos = jQuery.proxy(jQuery.jgrid.getCurrPos, $self[0])(),
				page = parseInt($self.rup_table("getGridParam", "page"),10),
				newPage=page,
				lastPage = parseInt(Math.ceil($self.rup_table("getGridParam", "records")/$self.rup_table("getGridParam", "rowNum")),10),
				currentArrayIndex, selectedLines;
				
				npos[0] = parseInt(npos[0],10);
				$("#FormError", settings.$detailForm).hide();
				switch (linkType){
					case 'first':
						// Navegar al primer elemento seleccionado
						execute = true;
						// Si no se han seleccionado todos los elementos
						if (!settings.multiselection.selectedAll){
							// Se comprueba si la página en la que nos encontramos es la primera en la que se han seleccionado registros
							if (settings.multiselection.selectedPages[0]!==page){
								// Marcamos el flag changePage para indicar que se debe de realizar una paginación
								changePage = true;
								// La nueva página será la primera página en la que se ha realizado una selección de registros
								newPage = settings.multiselection.selectedPages[0];
							}
						}else{
							// En el caso de que se hayan seleccionado todos los elementos de la tabla
							// Recorremos las páginas buscando la primera en la que existan elementos seleccionados
							for (var pageAux=1;pageAux<=lastPage;pageAux++){
								if ($self._hasPageSelectedElements(pageAux)){
									if (pageAux!==page){
										newPage = pageAux;
										changePage = true;
									}
									break;
								}
							}
						}
						// Recuperamos el primer registro seleccionado del la página
						index = $self._getFirstSelectedElementOfPage(newPage);
						newPageIndex = index;
						break;
					case 'prev':
						// Navegar al anterior elemento seleccionado
						execute = true;
						// Obtenemos un array con los index de los registros seleccionados en la página actual
						selectedLines = $self._getSelectedLinesOfPage(page);
						// Obtenemos la posición que ocupa el elemento actual en el array de seleccionados
						currentArrayIndex = $.inArray(npos[0]+1,selectedLines);
						// Se comprueba si ocupa el lugar del primer elemento seleccionado
						if (currentArrayIndex===0){
							// En caso de tratarse del primer elemento seleccionado de la página, se deberá de realizar una navegación a la página anterior que disponga de elementos seleccionados
							changePage = true;
							// Recorremos las páginas anteriores
							for (var pageAux = page-1;pageAux>=0;pageAux--){
								// En caso de que la página disponga de elementos selecciondados.
								if ($self._hasPageSelectedElements(pageAux)){
									newPage = pageAux;
									// Obtenemos los identificadores de los registros seleccionados de la nueva página
									selectedLines = $self._getSelectedLinesOfPage(pageAux);
									// Obtenemos el último registro seleccionado 
									index = selectedLines[selectedLines.length-1];
									break;
								}
							}
						}else{
							// En caso de no tratarse del último elemento de la página, recuperamos el elemento anterior que haya sido seleccionado también
							index = selectedLines[currentArrayIndex-1];
						}
						
						newPageIndex = index;
						break;
					case 'next':
						// Navegar al siguiente elemento seleccionado
						execute = true;
						// Obtenemos un array con los index de los registros seleccionados en la página actual
						selectedLines = $self._getSelectedLinesOfPage(page);
						// Obtenemos la posición que ocupa el elemento actual en el array de seleccionados
						currentArrayIndex = $.inArray(npos[0]+1,selectedLines);
						// Se comprueba si ocupa el lugar del último elemento seleccionado
						if (currentArrayIndex===selectedLines.length-1){
							// En caso de tratarse del último elemento seleccionado de la página, se deberá de realizar una navegación a la página siguiente que disponga de elementos seleccionados
							changePage = true;
							// Recorremos las páginas siguientes
							for (var pageAux = page+1;pageAux<=lastPage;pageAux++){
								// En caso de que la página disponga de elementos selecciondados.
								if ($self._hasPageSelectedElements(pageAux)){
									newPage = pageAux;
									// Obtenemos los identificadores de los registros seleccionados de la nueva página
									selectedLines = $self._getSelectedLinesOfPage(pageAux);
									// Obtenemos el primer registro seleccionado 
									index = selectedLines[0];
									break;
								}
							}
						}else{
							// En caso de no tratarse del último elemento de la página, recuperamos el elemento anterior que haya sido seleccionado también
							index = selectedLines[currentArrayIndex+1];
						}
						
						newPageIndex = index;
						break;
					case 'last':
						// Navegar al ultimo elemento seleccionado
						execute = true;
						// Si no se han seleccionado todos los elementos
						if (!settings.multiselection.selectedAll){
							// Se comprueba si la página en la que nos encontramos es la primera en la que se han seleccionado registros
							if (settings.multiselection.selectedPages[settings.multiselection.selectedPages.length-1]!==page){
								// Marcamos el flag changePage para indicar que se debe de realizar una paginación
								changePage = true;
								// La nueva página será la primera página en la que se ha realizado una selección de registros
								newPage = settings.multiselection.selectedPages[settings.multiselection.selectedPages.length-1];
							}
						}else{
							// En el caso de que se hayan seleccionado todos los elementos de la tabla
							// Recorremos las páginas buscando la primera en la que existan elementos seleccionados
							for (var pageAux=lastPage;pageAux>0;pageAux--){
								if ($self._hasPageSelectedElements(pageAux)){
									if (pageAux!==page){
										newPage = pageAux;
										changePage = true;
									}
									break;
								}
							}
						}
						selectedLines = $self._getSelectedLinesOfPage(newPage);
						// Recuperamos el último registro seleccionado del la página
						index = selectedLines[selectedLines.length-1];
						newPageIndex = index;
				}
				
				return [linkType, execute, changePage, index-1, npos, newPage, newPageIndex-1];
			};
			
			settings.doNavigation = function(arrParams, execute, changePage, index, npos, newPage, newPageIndex ){
				var $self = this, settings = $self.data("settings"), props = rp_ge[$self.attr("id")],
				linkType, execute, changePage, index, npos, newPage, newPageIndex, fncAfterclickPgButtons;
				
				if ($.isArray(arrParams)){
					linkType = arrParams[0];
					execute = arrParams[1];
					changePage = arrParams[2];
					index = arrParams[3];
					npos = arrParams[4];
					newPage = arrParams[5];
					newPageIndex = arrParams[6];
					
					if (execute){
						$self.rup_table("hideFormErrors", settings.$detailForm);
						$self.triggerHandler("jqGridAddEditClickPgButtons", [linkType, settings.$detailForm, npos[1][npos[index]]]);
						if (changePage){
							$self.trigger("reloadGrid",[{page: newPage}]);
							$self.on("jqGridAfterLoadComplete.pagination",function(event,data){
								var nextPagePos = jQuery.proxy(jQuery.jgrid.getCurrPos, $self[0])(),
								newIndexPos = nextPagePos[1][newPageIndex];
//								$self.jqGrid("setSelection", nextPagePos[1][newIndexPos]);
								jQuery.proxy(jQuery.jgrid.fillData, $self[0])(newIndexPos, $self[0], settings.$detailForm.attr("id"), settings.opermode);
								jQuery.proxy(jQuery.jgrid.updateNav, $self[0])();
								
								$self.find("td[aria-describedby='"+settings.id+"_infoEditable'] img.ui-icon.ui-icon-pencil").remove();
								settings.multiselection.rowForEditing=newIndexPos;
								$($self.jqGrid("getInd",newIndexPos, true)).find("td[aria-describedby='"+settings.id+"_infoEditable']").html($("<img/>").addClass("ui-icon ui-icon-pencil")[0]);
								
								$self.off("jqGridAfterLoadComplete.pagination");
							});
						}else{
							jQuery.proxy(jQuery.jgrid.fillData, $self[0])(npos[1][index], $self[0], settings.$detailForm.attr("id"), settings.opermode);
//							$self.jqGrid("setSelection", npos[1][index]);
							jQuery.proxy(jQuery.jgrid.updateNav, $self[0])();
							$self.find("td[aria-describedby='"+settings.id+"_infoEditable'] img.ui-icon.ui-icon-pencil").remove();
							settings.multiselection.rowForEditing=npos[1][index];
							$($self.jqGrid("getInd",npos[1][index], true)).find("td[aria-describedby='"+settings.id+"_infoEditable']").html($("<img/>").addClass("ui-icon ui-icon-pencil")[0]);
							
						}
						$self.triggerHandler("jqGridAddEditAfterClickPgButtons", [linkType,settings.$detailForm,npos[1][npos[index]]]);
						fncAfterclickPgButtons = (props!==undefined?props.afterclickPgButtons:settings.afterclickPgButtons);
						if(jQuery.isFunction(fncAfterclickPgButtons)) {
							props.fncAfterclickPgButtons.call($self, linkType, settings.$detailForm,npos[1][npos[index]]);
						}
					}
				}
			};

			
			// Configuracion de los handler de los eventos
			$self.on({
				/*
				 * Capturador del evento jqGridSelectRow. 
				 * Se ejecuta cuando se selecciona una fila. 
				 * Realiza la gestión interna sobre la acción de (de)selección del usuario.
				 * 
				 * 	event: Objeto event.
				 * 	id: Identificador de la línea.
				 *  status: true en caso de selección, false en caso de deselección.
				 *  obj: Objeto interno del jqGrid.
				 */
				"jqGridSelectRow.rupTable.multiselection": function(event, id, status, obj){
					var page, firstRow, rowArrayIndex, fistSelectedId;
					if (obj!==false){
						// Se gestiona la selección o deselección del registro indicado
						$self._processSelectedRow(settings, id, status);
						// Actualización del número de registros seleccionados
						$self.rup_table("updateSelectedRowNumber");
						// Se cierra el feedback para (de)seleccionar el resto de registros
						settings.$internalFeedback.rup_feedback("close");
						
						// Se gestiona el icono de linea editable
						$self.find("td[aria-describedby='"+settings.id+"_infoEditable'] img.ui-icon.ui-icon-pencil").remove();
						if (status){
							settings.multiselection.rowForEditing=id;
							$($self.jqGrid("getInd",id, true)).find("td[aria-describedby='"+settings.id+"_infoEditable']").html($("<img/>").addClass("ui-icon ui-icon-pencil")[0]);
						}else{
							page = parseInt($self.rup_table("getGridParam", "page"),10);
							if ($self._hasPageSelectedElements(page)){
								fistSelectedId = settings.multiselection.selectedRowsPerPage[page][0];
								settings.multiselection.rowForEditing=fistSelectedId;
								$($self.jqGrid("getInd",fistSelectedId, true)).find("td[aria-describedby='"+settings.id+"_infoEditable']").html($("<img/>").addClass("ui-icon ui-icon-pencil")[0]);
							}
						}
					}
				},
				/*
				 * Capturador del evento jqGridLoadComplete. 
				 * Se ejecuta una vez se haya completado la carga de la tabla.
				 * 
				 * 	data: Objeto que contiene la carga de la tabla.
				 * 
				 */
				"jqGridLoadComplete.rupTable.multiselection": function(data){
					var self = $self[0], 
					internalProps = self.p,
					page = $self.rup_table("getGridParam", "page"),
					rowNum = $self.rup_table("getGridParam", "rowNum"),
					rows,
					selectedRows = settings.multiselection.selectedRowsPerPage[page];
					deselectedRows = settings.multiselection.deselectedRowsPerPage[page] || [];
					
					/*
					 * Gestión de la persistencia de la multiselección entre páginas.
					 * 
					 * El siguiente algoritmo permite mantener la selección de registros mientras se pagina.
					 * Los registros seleccionados se mantienen almacenando sus identificadores. 
					 * En caso de seleccionar todos los elementos de la tabla se trabaja mediante lógica inversa, de modo que se almacenan los registros deseleccionados.
					 */
					// Se comprueba si se han seleccionado todos los registros de la tabla.
					if (settings.multiselection.selectedAll){
						internalProps.selarrrow = [];
						rows = self.rows;
						// Para cada línea que muestra la tabla:
						for(var i=0;i<rows.length;i++){
							
							// Se comprueba si el registro se encuentra en el array de deseleccionados.
							if (jQuery.inArray(rows[i].id,deselectedRows)===-1){
								// En caso de no ser un elemento deseleccionado se marca como seleccionado.
								$self.rup_table("highlightRowAsSelected", $(rows[i]));
							}
						};
						
						// En caso de estar todos los elementos de la página seleccionados marcamos el check general
						
						if (deselectedRows.length===0){
							settings.$selectAllCheck[internalProps.useProp ? 'prop': 'attr']("checked",true);
						}
						
					}else{
						// No se han seleccionado todos los resgistros de la página.
						if (selectedRows){
							rows = self.rows;
							internalProps.selarrrow = [];
							// Para cada línea que muestra la tabla:
							for(var i=0;i<rows.length;i++){
								// Se comprueba si el registro se encuentra en el array de seleccionados
								if (jQuery.inArray(rows[i].id,selectedRows)!==-1){
									// En caso de ser un elemento seleccionado, se marca como tal.
									$self.rup_table("highlightRowAsSelected", $(rows[i]));
								}
							};
							
							// En caso de estar todos los elementos de la página seleccionados marcamos el check general
							if (selectedRows.length===rowNum){
								settings.$selectAllCheck[internalProps.useProp ? 'prop': 'attr']("checked",true);
							}
						}
					}
					// Se cierra el feedback para seleccionar/deseleccionar el resto de registros
					settings.$internalFeedback.rup_feedback("close");

					// Se gestiona el icono de linea editable
					if ($self._hasPageSelectedElements(page)){
						fistSelectedId = settings.multiselection.selectedRowsPerPage[page][0];
						settings.multiselection.rowForEditing=fistSelectedId;
						$($self.jqGrid("getInd",fistSelectedId, true)).find("td[aria-describedby='"+settings.id+"_infoEditable']").html($("<img/>").addClass("ui-icon ui-icon-pencil")[0]);
					}
				},
				/*
				 * Capturador del evento jqGridSelectAll. 
				 * Se ejecuta cuando se realice una selección de todos los elementos de la página.
				 * 
				 * 	event: Objeto event de jQuery
				 *  selectedRows: Array con los identificadores de los registros seleccionados en la página
				 *  status: true en caso de selección, false en caso de deselección.
				 */
				"jqGridSelectAll.rupTable.multiselection": function(event, selectedRows, status){
					var page = $self.rup_table("getGridParam", "page"),
					selectMsg, deselectMsg, elementosRestantes, selectRestMsg, remainingSelectButton, remainingDeselectButton;
					
					// Se gestiona la selección de todos los registros de la página
					for (var i=0;i<selectedRows.length;i++){
						$self._processSelectedRow(settings, selectedRows[i], status);
					}
					
					
					selectMsg = jQuery.rup.i18nTemplate($.rup.i18n.base,"rup_grid.selectMsg", "<b>" + selectedRows.length + "</b>", "<b>" + page + "</b>");
					deselectMsg = jQuery.rup.i18nTemplate($.rup.i18n.base,"rup_grid.deselectMsg", "<b>" + selectedRows.length + "</b>", "<b>" + page + "</b>");
					
					// Se comprueba el valor de status para determinar si se está seleccionando (true) o deseleccionando (false) todos los registos de la página
					if (status){
						// Se obtienen el número de registros restantes que quedan por seleccionar
						elementosRestantes = $self._getRemainingRecordNum(settings, selectedRows);
						if (elementosRestantes!==0){
							// En caso de existir registros sin seleccionar se muestra el mensaje junto con un botón para permitir la selecón de dichos elementos
							selectRestMsg = jQuery.rup.i18nTemplate($.rup.i18n.base,"rup_table.selectRestMsg", elementosRestantes);
							remainingSelectButton = jQuery.rup.i18nTemplate(jQuery.rup.i18n.base,"rup_table.templates.multiselection.selectRemainingRecords", $self[0].id, selectRestMsg, $.rup.i18nParse($.rup.i18n.base,"rup_grid.selectAll"));
							settings.$internalFeedback.rup_feedback("set",  selectMsg + remainingSelectButton);
						} else {
							// Si no hay elementos restantes por seleccionar se muestra solo un mensaje informativo
							settings.$internalFeedback.rup_feedback("set",  selectMsg);
						}
						
						// Se asocia el handler al evento click del botón de seleccionar el resto de registros
						$("#rup_grid_"+$self[0].id+"_selectAll").on("click", function(event){
							
							$self._initializeMultiselectionProps(settings);
							// Se marca el flag de todos seleccionados a true
							settings.multiselection.selectedAll=true;
							// Numero de registros seleccionados
							settings.multiselection.numSelected=$self.rup_table("getGridParam", "records");
							
							// Se cierra el feedback para seleccionar/deseleccionar el resto de registros
							$self.rup_table("updateSelectedRowNumber");
							settings.$internalFeedback.rup_feedback("close");
						});
					}else{
						// En caso de existir elementos seleccionados se muestra un mensaje que incluye un botón para permitir la deselección del todos los elementos seleccionados
						if (settings.multiselection.numSelected>0){
							selectRestMsg = jQuery.rup.i18nTemplate($.rup.i18n.base,"rup_table.deselectRestMsg", settings.multiselection.numSelected);
							remainingDeselectButton = jQuery.rup.i18nTemplate(jQuery.rup.i18n.base,"rup_table.templates.multiselection.deselectRemainingRecords", $self[0].id, selectRestMsg, $.rup.i18nParse($.rup.i18n.base,"rup_grid.deSelectAll"));
							settings.$internalFeedback.rup_feedback("set",  deselectMsg + remainingDeselectButton);
						}
						
						// Se asocia el handler al evento click del botón de deseleccionar el resto de registros
						$("#rup_grid_"+$self[0].id+"_deselectAll").on("click", function(event){
							$self._initializeMultiselectionProps(settings);
							
							// Se cierra el feedback para seleccionar/deseleccionar el resto de registros
							$self.rup_table("updateSelectedRowNumber");
							settings.$internalFeedback.rup_feedback("close");
						});
					}
					
					// Se actualiza el contador de elementos seleccionados
					$self.rup_table("updateSelectedRowNumber");
				},
				"rupTableAfterSearchNav.rupTable.multiselection rupTableSearchSuccess.rupTable.multiselection": function(){
					var $self = $(this);
					$self.rup_table("resetSelecion");
				}
			});
			
		},
		resetSelecion: function(){
			var $self = this, settings = $self.data("settings");
			
			$self.jqGrid("resetSelection");
			$self._initializeMultiselectionProps(settings);
			$self.rup_table("updateSelectedRowNumber");
		},
		/*
		 * Actualiza el contador de la tabla que indica los registros seleccionados.
		 */
		updateSelectedRowNumber: function(){
			var $self = $(this), settings = $self.data("settings");
			$("div .ui-paging-selected",settings.$pager).html(settings.multiselection.numSelected+" "+jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_grid.pager.selected"));
			$self.triggerHandler("rupTableSelectedRowNumberUpdated");
		}
	});
	
	//*******************************************************
	// DEFINICIÓN DE MÉTODOS PRIVADOS  
	//*******************************************************
	
	jQuery.fn.rup_table("extend",{
		_hasPageSelectedElements: function(paramPage){
			var $self = this, settings = $self.data("settings"), rowsPerPage,
			page = (typeof paramPage ==="string"?parseInt(paramPage,10):paramPage);
			// Se comprueba si se han seleccionado todos los registros de la tabla
			if (!settings.multiselection.selectedAll){
				// En caso de no haberse seleccionado todos los registros de la tabla
				// Comprobamos si en la página indicada se ha seleccionado un elemento
				return (jQuery.inArray(page, settings.multiselection.selectedPages)!== -1);
				
			}else{
				// En caso de haberse seleccionado todos los registros de la tabla
				// Generamos un array inicializado con los index de las lineas de las tablas
				rowsPerPage = parseInt($self.rup_table("getGridParam", "rowNum"),10);
				
				// Obtenemos el número de registro por página que se visualizan
				// Se comprueba si el número de registros deseleccionados es igual al número de registros por página, en cuyo caso significará que no hay elementos seleccionados
				if (jQuery.inArray(page, settings.multiselection.deselectedPages) !==-1 && settings.multiselection.deselectedLinesPerPage[page].length===rowsPerPage){
					return false;
				}
				
				return true;
			}
		},
		_initializeMultiselectionProps: function(settings){
			// Se almacenan en los settings internos las estructuras de control de los registros seleccionados
			if (settings.multiselection===undefined){
				settings.multiselection={};
			}
			// Numero de registros seleccionados
			settings.multiselection.numSelected=0;
			// Propiedades de selección de registros
			settings.multiselection.selectedRowsPerPage=[];
			settings.multiselection.selectedLinesPerPage=[];
			settings.multiselection.selectedRows=[];
			settings.multiselection.selectedIds=[];
			settings.multiselection.selectedPages=[];
			// Propiedades de deselección de registros
			settings.multiselection.deselectedRowsPerPage=[];
			settings.multiselection.deselectedLinesPerPage=[];
			settings.multiselection.deselectedRows=[];
			settings.multiselection.deselectedIds=[];
			settings.multiselection.deselectedPages=[];
			// Flag indicador de selección de todos los registros
			settings.multiselection.selectedAll=false;
		},
		_getFirstSelectedElementOfPage: function(paramPage){
			var $self = this, settings = $self.data("settings"), rowsPerPage, 
			page = (typeof paramPage ==="string"?parseInt(paramPage,10):paramPage);
			
			// Se comprueba si se han seleccionado todos los registros de la tabla
			if (!settings.multiselection.selectedAll){
				// En caso de no haberse seleccionado todos los registros de la tabla
				// Comprobamos si en la página indicada se ha seleccionado un elemento
				if (jQuery.inArray(page, settings.multiselection.selectedPages)=== -1){
					return false;
				}
				// En caso de que se haya seleccionado un elemento en la página indicada se devuelve el primero
				return settings.multiselection.selectedLinesPerPage[page][0];
				
			}else{
				// En caso de haberse seleccionado todos los registros de la tabla
				// Si no se han deseleccionado registros en la página se devuelve el primer indice
				if (jQuery.inArray(page, settings.multiselection.deselectedPages)=== -1){
					return 1;
				}
				// Obtenemos el número de registro por página que se visualizan
				rowsPerPage = parseInt($self.rup_table("getGridParam", "rowNum"),10);
				// Se comprueba si el número de registros deseleccionados es igual al número de registros por página, en cuyo caso significará que no hay eleme
				if (settings.multiselection.deselectedLinesPerPage[page].length===rowsPerPage){
					return false;
				}
				
				// Obtenemos el primer elemento de la página que no ha sido deseleccionado 
				for (var i=1;i<=rowsPerPage;i++){
					if (jQuery.inArray(i, settings.multiselection.deselectedLinesPerPage[page])=== -1){
						return i;
					}
				}
			}
		},
		_getRemainingRecordNum: function(settings, selectedRows){
			var $self = this, totalRegistros = $self.rup_table("getGridParam", "records"),
			registrosPagina = $self.rup_table("getGridParam", "reccount"),
			registrosSelPagina = selectedRows.length,
			registrosSelTotal = settings.multiselection.numSelected,
			elementosRestantes = ((totalRegistros - registrosPagina) !== 0 )?
										totalRegistros - registrosPagina - (registrosSelTotal - registrosSelPagina)  : 0 ;
										
			return elementosRestantes;
		},
		_getSelectedLinesOfPage: function(page){
			var $self = this, settings = $self.data("settings"), rowsPerPage, records, lastPage, inverseArray=[];
			
			// Se comprueba si se han seleccionado todos los registros de la tabla
			if (!settings.multiselection.selectedAll){
				// En caso de no haberse seleccionado todos los registros de la tabla
				// Comprobamos si en la página indicada se ha seleccionado un elemento
				if (jQuery.inArray(page, settings.multiselection.selectedPages)=== -1){
					return [];
				}
				// En caso de que se haya seleccionado un elemento en la página indicada se devuelve el array de seleccionados
				return settings.multiselection.selectedLinesPerPage[page];
				
			}else{
				// En caso de haberse seleccionado todos los registros de la tabla
				// Generamos un array inicializado con los index de las lineas de las tablas
				rowsPerPage = parseInt($self.rup_table("getGridParam", "rowNum"),10);
				records = $self.rup_table("getGridParam", "records");
				lastPage = parseInt(Math.ceil(records/rowsPerPage,10));

				// En caso de ser la última página se recalcula el número de elementos que se muestran en ella
				if (page===lastPage){
					rowsPerPage = records - ((lastPage-1)*rowsPerPage);
				}
				
				for(var i=1;i<=rowsPerPage;i++){
					inverseArray[i-1]=i;
				}
				// Si no se han deseleccionado registros en la página se devuelve el array al completo
				if (jQuery.inArray(page, settings.multiselection.deselectedPages)=== -1){
					return inverseArray;
				}
				// Obtenemos el número de registro por página que se visualizan
				// Se comprueba si el número de registros deseleccionados es igual al número de registros por página, en cuyo caso significará que no hay elementos seleccionados
				if (settings.multiselection.deselectedLinesPerPage[page].length===rowsPerPage){
					return [];
				}
				// Se eliminan del array inicializado con todos los identificadores de las lineas, las que han sido deseleccionadas
				return jQuery.grep(inverseArray, function(elem){
				    return (jQuery.inArray(elem, settings.multiselection.deselectedLinesPerPage[page])===-1);
				});
			}
		},
		_processSelectedRow: function(settings, rowId, status){
			var $self = this, page = $self.rup_table("getGridParam", "page"), pageInt = parseInt(page), lineIndex, indexInArray, indexAtPage, indexPage;
			// Se selecciona o deselecciona el elemento en los arrays que almacenan los registros seleccionados.
			if (status){
				if (settings.multiselection.selectedAll){
					// Se ha deseleccionado un elemento
					// Se almacena el Id del registro seleccionado
					indexInArray = jQuery.inArray(rowId, settings.multiselection.deselectedIds);
					if (indexInArray!=-1){
						settings.multiselection.deselectedIds.splice(indexInArray,1);
						settings.multiselection.deselectedRows.splice(indexInArray,1);
						indexAtPage = jQuery.inArray(rowId, settings.multiselection.deselectedRowsPerPage[page]);
						settings.multiselection.deselectedRowsPerPage[page].splice(indexAtPage,1);
						settings.multiselection.deselectedLinesPerPage[page].splice(indexAtPage,1);
						if (settings.multiselection.deselectedRowsPerPage[page].length===0){
							indexPage = jQuery.inArray(pageInt, settings.multiselection.deselectedPages);
							if (indexPage!==-1){
								settings.multiselection.deselectedPages.splice(indexPage, 1);
							}
						}
						settings.multiselection.numSelected++;
					}
				}else{
					// Se ha seleccionado un elemento
					// Se comprueba si está creado el array correspondiente para la página actual
					if (settings.multiselection.selectedRowsPerPage[page]===undefined){
						settings.multiselection.selectedRowsPerPage[page]=[];
						settings.multiselection.selectedLinesPerPage[page]=[];
					}
					// Se almacena el Id del registro seleccionado
					if (jQuery.inArray(rowId, settings.multiselection.selectedIds)===-1){
						settings.multiselection.selectedIds.push(rowId);
						settings.multiselection.selectedRows.push({id:rowId, page:page});
						lineIndex = $.rup_utils.insertSorted(settings.multiselection.selectedLinesPerPage[page], $self.jqGrid("getInd",rowId));
						settings.multiselection.selectedRowsPerPage[page].splice(lineIndex,0,rowId);
						if (settings.multiselection.selectedRowsPerPage[page].length>0
								&& jQuery.inArray(pageInt, settings.multiselection.selectedPages)===-1){
							$.rup_utils.insertSorted(settings.multiselection.selectedPages, pageInt);
						}
						settings.multiselection.numSelected++;
					}
				}
			}else{
				if (settings.multiselection.selectedAll){
					// Se ha seleccionado un elemento
					// Se comprueba si está creado el array correspondiente para la página actual
					if (settings.multiselection.deselectedRowsPerPage[page]===undefined){
						settings.multiselection.deselectedRowsPerPage[page]=[];
						settings.multiselection.deselectedLinesPerPage[page]=[];
					}
					// Se almacena el Id del registro seleccionado
					if (jQuery.inArray(rowId, settings.multiselection.deselectedIds)===-1){
						settings.multiselection.deselectedIds.push(rowId);
						settings.multiselection.deselectedRows.push({id:rowId, page:page});
						lineIndex = $.rup_utils.insertSorted(settings.multiselection.deselectedLinesPerPage[page], $self.jqGrid("getInd",rowId));
						settings.multiselection.deselectedRowsPerPage[page].splice(lineIndex,0,rowId);
						if (settings.multiselection.deselectedRowsPerPage[page].length>0
								&& jQuery.inArray(pageInt, settings.multiselection.deselectedPages)===-1){
							$.rup_utils.insertSorted(settings.multiselection.deselectedPages, pageInt);
						}
						settings.multiselection.numSelected--;
					}
				}else{
					// Se ha deseleccionado un elemento
					// Se almacena el Id del registro seleccionado
					index = jQuery.inArray(rowId, settings.multiselection.selectedIds);
					if (index!=-1){
						settings.multiselection.selectedIds.splice(index,1);
						settings.multiselection.selectedRows.splice(index,1);
						indexAtPage = jQuery.inArray(rowId, settings.multiselection.selectedRowsPerPage[page]);
						settings.multiselection.selectedRowsPerPage[page].splice(indexAtPage,1);
						settings.multiselection.selectedLinesPerPage[page].splice(indexAtPage,1);
						if (settings.multiselection.selectedRowsPerPage[page].length===0){
							indexPage = jQuery.inArray(pageInt, settings.multiselection.selectedPages);
							if (indexPage!==-1){
								settings.multiselection.selectedPages.splice(indexPage, 1);
							}
						}
						settings.multiselection.numSelected--;
					}
				}
			}
			$self.rup_table("updateSelectedRowNumber");
		}
	});
		
	//*******************************************************
	// DEFINICIÓN DE LA CONFIGURACION POR DEFECTO DEL PATRON  
	//*******************************************************
	jQuery.fn.rup_table.plugins.multiselection = jQuery.fn.rup_table.plugins.multiselection || {};
	jQuery.fn.rup_table.plugins.multiselection.defaults = {
			formEdit:{
				autoselectFirstRecord: false
			},
			inlineEdit:{
				autoselectFirstRecord: false
			},
			multiselection:{
				defaultEditableInfoCol:{
					name: "infoEditable", index: "infoEditable", editable:false, width:"15em", search:false
				}
			}
	};
		
	jQuery.fn.rup_table.defaults.multiselection = {
		loadBeforeSend: function rup_table_defaults_loadBeforeSend(xhr, settings){
			// Se modifica la request para incluir las siguientes cabeceras:
			// Se añade la cabecera JQGridModel para indicar que la petición ha sido realizada por el componente rup_table
			xhr.setRequestHeader("JQGridModel", "true");
			// Se indica que el tipo de contenido enviado en la cabecera es application/jsons
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	};
	
	
})(jQuery);