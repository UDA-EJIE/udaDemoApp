/*!
 * Copyright 2012 E.J.I.E., S.A.
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
jQuery(function($){
//	jQuery.fn.rup_table.defaults.defaultPlugins = ["ivap"];
//	jQuery.fn.rup_table("extend",{
//		highlightEditableRow: function($row){
//			var $self = this, settings = $self.data("settings"), 
//			page = parseInt($self.rup_table("getGridParam", "page"),10), 
//			firstSelectedLine, firstSelectedId, $row;
//			
//			$self.rup_table("clearHighlightedEditableRows");
//			
//			if ($self._hasPageSelectedElements(page)){
//				firstSelectedLine = $self._getFirstSelectedElementOfPage(page);
//				firstSelectedId = $self.jqGrid("getDataIDs")[firstSelectedLine-1];
//				settings.multiselection.rowForEditing=firstSelectedId;
//				$row = jQuery($self.jqGrid("getInd",firstSelectedId, true));
//				$row.find("td[aria-describedby='"+settings.id+"_rupInfoCol'] span").addClass("ui-icon ui-icon-rupInfoCol ui-icon-pencil");
//			}
//		}
//	});
//	jQuery(function($){
//		jQuery.extend(jQuery.fn.rup_table.plugins.multiselection.defaults,{
//			  onSelectRow: function(){
//			    var $self = $(this);
//			    $self.rup_table("highlightFirstEditableRow");
//			  }		  
//		});
//	});
	
	
//	jQuery(function($){
//		jQuery.fn.rup_table("extend",{
//			editElement: function (rowId, options){
//				var $self = this, 
//				settings = $self.data("settings"),
//				multiselectionSettings = settings.multiselection,
//				page = parseInt($self.rup_table("getGridParam", "page"),10),
//				firstPage, firstSelectedLine, firstSelectedId;
//				
//				if (multiselectionSettings.selectedPages.length > 0){
//					firstPage = multiselectionSettings.selectedPages[0];
//					
//					$self.on("jqGridAfterLoadComplete.ivap.editFirstElement",function(event,data){
//						
//						firstSelectedLine = $self._getFirstSelectedElementOfPage(firstPage);
//						firstSelectedId = $self.jqGrid("getDataIDs")[firstSelectedLine-1];
//						settings.multiselection.rowForEditing=firstSelectedId;
//						$self.rup_table("highlightEditableRow", $self.jqGrid("getInd",firstSelectedId, true));
//						
//						$self.rup_table("editElement", firstSelectedId);
//						
//						$self.off("jqGridAfterLoadComplete.ivap.editFirstElement");
//					});
//					
//					if (firstPage !== page){
//						
////						$self.on("jqGridAfterLoadComplete.ivap.editFirstElement",function(event,data){
////													
////							firstSelectedLine = $self._getFirstSelectedElementOfPage(firstPage);
////							firstSelectedId = $self.jqGrid("getDataIDs")[firstSelectedLine-1];
////							settings.multiselection.rowForEditing=firstSelectedId;
////							$self.rup_table("highlightEditableRow", $self.jqGrid("getInd",firstSelectedId, true));
////							
////							$self.rup_table("editElement", firstSelectedId);
////							
////							$self.off("jqGridAfterLoadComplete.ivap.editFirstElement");
////						});
//						
//						$self.trigger("reloadGrid",[{page: firstPage}]);
//						
//					}else{
//						$self.triggerHandler("jqGridAfterLoadComplete.ivap.editFirstElement");
//					}
//				}
//				
//				
//			}
//			
//		});
//	});
//	
	
	$("#table").rup_table({
		url: "../jqGridUsuario",
		colNames: tableColNames,
		colModel: tableColModels,
		multiboxonly: true,
        model:"Usuario",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"contextMenu",
        	"responsive",
        	"filter",
        	"search",
        	"multiselection",
        	"report",
        	"multifilter"
        ],
        primaryKey: ["id"],
        sortname: 'id',
        formEdit:{
        	detailForm: "#table_detail_div",
        	validate:{
    			rules:{
    				"nombre":{required:true},
    				"apellido1":{required:true},
    				"fechaAlta":{date:true},
    				"fechaBaja":{date:true}
    			}
    		}
        },
       
		multiselection: {
//			headerContextMenu: {
//				items: {
//		            "edit": {name: "Edit", icon: "edit", accesskey: "e"},
//		            "cut": {name: "Cut", icon: "cut", accesskey: "c"},
//		            // first unused character is taken (here: o)
//		            "copy": {name: "Copy", icon: "copy", accesskey: "c o p y"},
//		            // words are truncated to their first letter (here: p)
//		            "paste": {name: "Paste", icon: "paste", accesskey: "cool paste"},
//		            "delete": {name: "Delete", icon: "delete"},
//		            "sep1": "---------",
//		            "quit": {name: "Quit", icon: "quit"}
//		        }
//			}
		},
        filter:{
        	validate:{
        		rules:{
    				"fechaAlta":{date:true},
    				"fechaBaja":{date:true}
    			}
        	}
        },
        multifilter:{ idFilter:"formEditMulti",labelSize:255},
        report: options_table_report
	});
//	
//	$("#table").on("jqGridSelectRow.rupTable.multiselection", function(){
//	
//	$("#table").rup_table("highlightFirstEditableRow");
//});
	
});