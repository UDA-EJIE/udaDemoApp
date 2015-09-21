//what we need from javascriptmvc or other places
steal('funcunit/qunit')
	.then('funcunit/browser/resources/jquery.js', function(){
		if(!window.FuncUnit){
			window.FuncUnit = {};
		}
		FuncUnit.jQuery = jQuery.noConflict(true);
	})
	.then('funcunit/browser/resources/json.js', 'funcunit/syn')
	.then('funcunit/browser/core.js')
	.then('funcunit/browser/open.js', 'funcunit/browser/actions.js', 
		'funcunit/browser/getters.js', 'funcunit/browser/traversers.js', 'funcunit/browser/queue.js', 
		'funcunit/browser/waits.js')
	.then('funcunit/funcunit_test.js')
	//UDA TEST CODE
	.then('pages-tests/'+testJs);
	