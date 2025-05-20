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
jQuery(function($) {
	window.initRupI18nPromise.then(function () {
	    $('#fecha').rup_date({
			datetimepicker:true,
	        placeholderMask : true,
	        mask: 'dd/mm/yyyy HH:mm:ss.S',
	        dateFormat: 'dd/mm/yy',
	        timeFormat: 'HH:mm:ss',
	        showButtonPanel : true,
	        showOtherMonths : true
	    });
	    
	    $('#fechaPlaceholder').rup_date({
	        placeholderMask : true,
	        showButtonPanel : true,
	        showOtherMonths : true,
	        noWeekend : true
	    });
	    
	    $('#fecha_multi').rup_date({
	        multiSelect: 3,
	        placeholderMask : true,
	    });
	    
	    $.rup_date({		
	        from: 'desde',
	        to: 'hasta',
	        placeholderMask : true,
	        numberOfMonths: 3
	    });
	    
	    $.rup_date({		
	        from: 'desdeDateTime',
	        to: 'hastaDateTime',
	        placeholderMask : true,
	        numberOfMonths: 3,
	        datetimepicker:true,
	        showButtonPanel : true,
	        showOtherMonths : true,
	        noWeekend : true,
	        mask: 'dd/mm/yyyy HH:mm',
	        showSecond: false,
	        dateFormat: 'dd/mm/yy',
	        timeFormat: 'HH:mm'
	    });
	
	    $('#fecha_inline').rup_date({
	        changeMonth : false,
	        changeYear	: false,
	        numberOfMonths : [2, 3],
	        stepMonths : 6,
	        showWeek: true,
	        minDate: $.rup_utils.createDate(1, 1, 2012),
	        maxDate: $.rup_utils.createDate(31, 12, 2012)		
	    });
	    
	    $('#fecha').rup_date('setDate', new Date());
	});
	
	$('.contenedor').addClass('show');
});
