function cargarPruebasTriggers(){
	
	var table = $('#example');
	
	table.on('tableEditFormAfterInsertRow', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableAfterComplete', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiSelectSelectAll', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiSelectBeforeSelectRow', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableEditFormClickRow', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableButtonsOpenContextMenu', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableInit', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiSelectAfterSelectRow', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableSeekerBeforeSearch', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableSeekerAfterSearch', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableSeekerBeforeClear', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableSeekerAfterClear', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableFilterBeforeShow', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableFilterInitialize', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableFilterSearch', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableFilterReset', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableEditFormBeforeCallAjax', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableEditFormAfterInsertRow', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableEditFormAddEditBeforeInitData', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableEditFormAddEditBeforeShowForm', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableEditFormAddEditAfterShowForm', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiFilterFillForm', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableSeekerAfterCreateToolbar', function(event){
		console.log('---Trigger--- ' + event.type);
		
		$('#' + table[0].id + ' tbody td:eq(6)').on('mouseenter.qtip', function(event){
			console.log('---Trigger--- ' + event.type);
		});
		
		$('#' + table[0].id + ' tbody tr:eq(6)').on('tableHighlightRowAsSelected', function(event){
			console.log('---Trigger--- ' + event.type);
		});
		
		$('#' + table[0].id + ' tbody tr:eq(6)').on('tableButtonsOpenContextMenu', function(event){
			console.log('---Trigger--- ' + event.type);
		});
		
		$('#' + table[0].id + ' tbody tr:eq(6)').on('tableMultiSelectBeforeSelectRow', function(event){
			console.log('---Trigger--- ' + event.type);
		});
		
		$('#' + table[0].id + ' tbody tr:eq(6)').on('tableMultiSelectAfterSelectRow', function(event){
			console.log('---Trigger--- ' + event.type);
		});
		
		$('#' + table[0].id + ' tbody tr:eq(5)').on('tableSelectBeforeSelectRow', function(event){
			console.log('---Trigger--- ' + event.type);
		});
		
		$('#' + table[0].id + ' tbody tr:eq(5)').on('tableSelectAfterSelectRow', function(event){
			console.log('---Trigger--- ' + event.type);
		});
		
		$('#' + table[0].id + ' tbody tr:eq(1) td span').on('tablaGroupingClickGroup', function(event){
			console.log('---Trigger--- ' + event.type);
		});
	});
	
	table.on('tableSeekerBeforeSearch', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableSeekerSearchSucess', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableEditFormAfterDelete', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableButtonsBeforeEditClick', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableButtonsBeforeAddClick', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableButtonsBeforeCloneClick', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableEditFormAfterFillData', function(event){
		console.log('---Trigger--- ' + event.type);
	});	
	
	table.on(' tableEditFormInternalFeedbackClose', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on(' tableEditFormFeedbackShow', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on(' tableMultiSelectionRowNumberUpdate', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableSeekerSearchSucess', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableBeforeInit', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableAfterInit', function(event){
		console.log('---Trigger--- ' + event.type);
	});	
	
	table.on('tableSeekerSearchError', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	
	// SEEKER
	table.on('tableSeekerSearchComplete', function(event){
		console.log('---Trigger--- ' + event.type);
	});	
	
	table.on(' tableButtonsBeforeCopyClick', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	
	// BUTTONS
	table.on(' tableButtonsAfterCopyClick', function(event){
		console.log('---Trigger--- ' + event.type);
	});
		
	table.on(' tableButtonsAfterAddClick', function(event){
		console.log('---Trigger--- ' + event.type);
	});	
	
	table.on('tableButtonsAfterEditClick', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableButtonsAfterCloneClick', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableButtonsBeforeDeleteClick', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableButtonsAfterDeleteClick', function(event){
		console.log('---Trigger--- ' + event.type);
	});	
	
	table.on('tableButtonsSuccessReportsRequestData', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableButtonsCompleteReportsRequestData', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableButtonsErrorReportsRequestData', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableButtonsOpenContextMenu', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	
	// EDITFORM	
	table.on('tableEditFormSuccessCallSaveAjax', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableEditFormCompleteCallSaveAjax', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableEditFormErrorCallSaveAjax', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	
	// MULTIFILTER	
	table.on('tableMultiFilterBeforeConfigureMultifilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiFilterAfterConfigureMultifilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});	
	
	table.on('tableMultiFilterSuccessConfigureMultifilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiFilterCompleteConfigureMultifilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiFilterErrorConfigureMultifilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});	
	
	table.on('tableMultiFilterBeforeDeleteFilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiFilterAfterDeleteFilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});	
	
	table.on('tableMultiFilterSuccessDeleteFilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiFilterCompleteDeleteFilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiFilterErrorDeleteFilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});	
	
	table.on('tableMultiFilterBeforeAddFilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiFilterAfterAddFilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});	
	
	table.on('tableMultiFilterSuccessAddFilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiFilterCompleteAddFilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiFilterErrorAddFilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});	
	
	table.on('tableMultiFilterSuccessGetDefaultFilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiFilterCompleteGetDefaultFilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});	
	
	table.on('tableMultiFilterErrorGetDefaultFilter', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiFilterBeforeCleanFilterForm', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiFilterAfterCleanFilterForm', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	
	// MULTISELECT	
	table.on('tableMultiSelectFeedbackSelectAll', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiSelectFeedbackDeselectAll', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
	table.on('tableMultiSelectFeedbackDestroy', function(event){
		console.log('---Trigger--- ' + event.type);
	});
	
}
