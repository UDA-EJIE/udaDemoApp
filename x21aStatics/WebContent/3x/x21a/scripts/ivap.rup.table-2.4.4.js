

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
	
		
	//*******************************************************
	// DEFINICIÓN DE LA CONFIGURACION POR DEFECTO DEL PATRON  
	//*******************************************************
	jQuery.fn.rup_table.plugins.ivap = {};
	jQuery.fn.rup_table.plugins.ivap.defaults = {
	};	
	
	
	
})(jQuery);