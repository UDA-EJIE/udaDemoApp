$(function($) {
   //$('#example').tabs();
   //$('#example').tabs('paging', {  cycle: false });*/
   
	
	//nav menu sin jquery tabs
   var $tabs=$('#example').rup_navMenu({navigation:true,visible:3} );
		
   //con jquery tabs
   $('#example2').tabs().scrollabletab({
		'closable':false, //Default false
		'animationSpeed':50, //Default 100
		'loadLastTab':true, //Default false
		'resizable':false //Default false
});

   

	
   /*var $tabs= $('#example')
	.tabs()
	.scrollabletab({
		'closable':false, //Default false
		'animationSpeed':50, //Default 100
		'loadLastTab':true, //Default false
		'resizable':false, //Default false
		'resizeHandles':'e,s,se', //Default 'e,s,se'
		'easing':'easeInOutExpo' //Default 'swing'
	});*/
   
   
});