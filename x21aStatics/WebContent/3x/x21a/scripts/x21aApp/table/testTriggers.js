jQuery(function($){
	
	var table = $('#example');
	
	table.on('tableBeforeInit', function(event){
		console.log('---Trigger--- ' + event.type);
	});
});