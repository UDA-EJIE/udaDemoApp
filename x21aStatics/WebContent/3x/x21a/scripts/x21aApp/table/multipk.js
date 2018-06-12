jQuery(function($){
	
	var listaPlugins = 'editForm,colReorder,selection,seeker,buttons,';
	
	 
	function loadTable(){
		$('#MultiPk').rup_datatable(loadPlugins());
	}
	
	function loadPlugins(){

		if(localStorage.plugins === undefined){//si esta undefined es que es la primera vez.
			localStorage.plugins = listaPlugins;
		}
		
		var plugins = {};
		
		plugins.primaryKey = "ida;idb";
		plugins.loadOnStartUp = true;
        
		var fixedHeader = {
            footer: false,
            header:true
        };
	    plugins.fixedHeader = fixedHeader;
	    
	    var filter = {
		    	  id:"MultiPk_filter_form",
		    	  filterToolbar:"MultiPk_filter_toolbar",
		    	  collapsableLayerId:"MultiPk_filter_fieldset"
		    }
		plugins.filter = filter;
	    
		if(localStorage.plugins.indexOf('multiSelection') > -1){
		    var multiSelect = {
		            style:    'multi'
		        };
		    plugins.multiSelect = multiSelect;
		    $('#selection').prop('checked', false);
		    $('#multiSelection').prop('checked', true);

		}else{
			$('#multiSelection').prop('checked', false);
		}
		
		if(localStorage.plugins.indexOf('selection') > -1){
		    var select = {
		            activate:    true
		        };
		    plugins.select = select;
		    $('#selection').prop('checked', true);
		}else{
			$('#selection').prop('checked', false);
		}
		
		if(localStorage.plugins.indexOf('noSelection') > -1){
			console.log("Sin selección");
		    $('#noSelection').prop('checked', true);
		}else{
			$('#noSelection').prop('checked', false);
		}
		
		if(localStorage.plugins.indexOf('editForm') > -1){
	        var formEdit = {
	            	detailForm: "#MultiPk_detail_div",
	    			fillDataMethod: "clientSide",
	             	validate:{ 
	        			rules:{
	        				"ida":{
	    						required: false
	        					},
	        				"idb":{
	    						required: false
	        					},
	        				"nombre":{
	    						required: false
	        					},
	        				"apellido1":{
	    						required: false
	        					},
	        				"apellido2":{
	    						required: false
	        					}
	        				}
	        		   }
	        		 ,titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base,'rup_table.edit.editCaption')  
	        		}
		    plugins.formEdit = formEdit;

		    $('#editForm').prop('checked', true);
		}else{
			$('#selection').prop('checked', false);
			$('#editForm').prop('checked', false);
		}
		
		if(localStorage.plugins.indexOf('buttons') > -1){
		    var buttons = {
		            activate:    true
		        };
		    plugins.buttons = buttons;
		    $('#buttons').prop('checked', true);

		}else{
			$('#buttons').prop('checked', false);
		}
		
		if(localStorage.plugins.indexOf('seeker') > -1){
		    var seeker = {
		    		activate: true
		        };
		    plugins.seeker = seeker;
		    $('#seeker').prop('checked', true);
		}else{
			$('#seeker').prop('checked', false);
		}

		
		if(localStorage.plugins.indexOf('colReorder') > -1){
		    var colReorder = {
		    		fixedColumnsLeft: 1
		        };
		    plugins.colReorder = colReorder;
		    $('#colReorder').prop('checked', true);
		}else{
			$('#colReorder').prop('checked', false);
		}
		
		if(localStorage.plugins.indexOf('groups') > -1){
		    var groups = {
		            style:    'multi'
		        };
		    plugins.groups = groups;
		    $('#groups').prop('checked', true);
		}else{
			$('#groups').prop('checked', false);
		}
		localStorage.clear();
		return plugins;
	}
	
	$("#multipk_aplicar").click(function(){
		if(localStorage.plugins === undefined){
			localStorage.plugins = '';
		}
		$.each($(".pluginsControl input"), function( ) {
			if($('#'+this.id).prop('checked')){
				localStorage.plugins = localStorage.plugins+this.id+","
			}
		});
		location.reload();
	});
	
	loadTable();

});