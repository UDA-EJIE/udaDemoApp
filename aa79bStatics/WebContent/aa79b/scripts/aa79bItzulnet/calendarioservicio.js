var today = new Date();
var y = today.getFullYear();

var arrJornada = new Array();

//Vbles para el control de cambios
var controlCambiosFestivosIniciales = "";
var controlCambiosFormJornada = $('#calendarioservicio_detail_form').rup_form("formSerialize");
var controlCambiosComboAnyo =0;

//Para generar el PDF
var img = new Image();
img.src= $.rup.APP_STATICS+"/images/ivap.png";
var form = $('#previsualizardiv');


function controlDeCambios(){
	if ( $('#capaOcultar').is(":visible") && (controlCambiosFormJornada != $('#calendarioservicio_detail_form').rup_form("formSerialize")
			|| controlCambiosFestivosIniciales != $('#previewcalendario').rup_date("getRupValue"))){
		return false;
	}else{
		return true;
	}
}

jQuery(function($){
	$('#calendarioservicio_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	$("#calendarioservicio_toolbar").rup_toolbar({
      adapter: "toolbar_jqueryui",
  		buttons:[  		   
  		    {i18nCaption:$.rup.i18n.app.boton.imprimir,id: "btnImprimirCal", css:"fa fa-file-pdf-o", click: obtenerPdf, right: true },
  		    {i18nCaption:$.rup.i18n.app.boton.horariosatencion, css:"fa fa-clock-o", click: horariosAtencion, right: true },
  		    {i18nCaption:$.rup.i18n.app.boton.horarioslaborales, css:"fa fa-clock-o", click: horariosLaborales, right: true }
  		]
	});
	
	fnFechaDesdeHasta("veranoDesde", "veranoHasta");
	
	$("#calendarioservicio_detail_form").rup_validate({
		feedback: $('#calendarioservicio_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"anyoCalendario": {required: true, integer: true, min: 0},			
			"veranoDesde": {required: true, date: true, min: y+'/01/01', max: y+'/12/31'},
			"veranoHasta": {required: true, date: true,fechaHastaMayor:"veranoDesde",  min: y+'/01/01', max: y+'/12/31'}
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});
	
	function creaCalendario(fechas){
		var mdy;
		$('#previewcalendario').rup_date({ //maxPicks: 6,		
			multiSelect: 8,
			changeMonth : false,
			changeYear	: false,			
			numberOfMonths: [3,4],
			defaultDate: y+'/1/1',
			dateFormat: "yy/mm/dd",
			minDate: y+'/1/1',
			maxDate: y+'/12/31'
			,beforeShowDay: function (date){
				mdy = date.getFullYear() + '/' + ("0" + (date.getMonth() + 1)).slice(-2) + '/' + ("0" + (date.getDate())).slice(-2);
				if ($.inArray(mdy, arrJornada) > -1) {			    	
			        return [true, 'jornadaI', $.rup.i18nParse($.rup.i18n.app,"label.jornadaIntensiva")];
			    } else {
			        return [true, ''];
			    }
			}
		});		
		if (fechas.length >0){
			//formatear fechas en funcion del idioma
			if("es".localeCompare($.rup.lang)===0){
				$('#previewcalendario').multiDatesPicker('addDates', formatearFechasJs(fechas));
			}else{
				$('#previewcalendario').multiDatesPicker('addDates', fechas);
			}
		}
		//Desmarcar la fecha actual y desactivar los botone sde cambio de mes, que por propiedad no funciona
		$('.ui-datepicker-current-day').removeClass('ui-datepicker-current-day');
		$('.ui-datepicker-today').removeClass('ui-datepicker-today');		
		$("a.ui-state-highlight").removeClass('ui-state-highlight');		
		$(".ui-datepicker-prev, .ui-datepicker-next").remove();
	}
	
	function formatearFechasJs(fechas){
		var fechasFormateadas = new Array();
		fechas.forEach(function(fecha) {
			 var parts = fecha.split("/");
			 fechasFormateadas.push(parts[2]+"/"+parts[1]+"/"+parts[0]);
		});
		return fechasFormateadas;
	}
	
	function leerCalendario(paramAnio){		
		
		if ( paramAnio !== undefined)
			y = paramAnio;
		if ($('#previewcalendario div').length >0){//si está el rup
			$('#previewcalendario').rup_date("setRupValue","");
			$('#previewcalendario').rup_date("destroy");
			$('#previewcalendario').multiDatesPicker('destroy');
			var laCapa = $('.calendarioServicio div')[1];
//			$('.calendarioServicio').html('<label class="control-label izda" for="festivos">'+$.rup.i18n.app.boton.editarFestivos+':</label>');
			$('.calendarioServicio').append(laCapa);
			$('.calendarioServicio').removeAttr('ruptype');
			
		}
		arrJornada = new Array(); //Vacio el array de dias de jornada intensiva
		$.ajax({
		   	 type: 'POST'
		   	 ,url: '/aa79bItzulnetWar/administracion/calendarioservicio/cargaCalendario'
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,data: $.toJSON(y)			
		     ,async: false
		   	 ,success:function(calendarioServ){		 
		   		if (calendarioServ.anyoCalendario!=null) {
		   			if($('#capaOcultar').is(':hidden')){
				   		$('#capaOcultar').show();
			  	 		$('#capaBotones').show();
			  	 		$('[id^="calendarioservicio_toolbar##btnImprimirCal"]').show();
			   		 }
		   			//Colorear todos los días de jornada intensiva...
		   			var fechaInicioJornada = dateToISOFormat(calendarioServ.veranoDesde, $.rup.lang);
		   			var fechaFinJornada   = dateToISOFormat(calendarioServ.veranoHasta, $.rup.lang);
		   			if(fechaInicioJornada && fechaFinJornada){
		   				while(fechaFinJornada.getTime() >= fechaInicioJornada.getTime()){
			   				arrJornada.push(fechaInicioJornada.getFullYear() + '/' + ("0" + (fechaInicioJornada.getMonth() + 1)).slice(-2) + '/' + ("0" + (fechaInicioJornada.getDate())).slice(-2));		   				
			   				fechaInicioJornada.setDate(fechaInicioJornada.getDate() + 1);		   			
			   			}
			   			
			   			//Carga de los dias festivos de bbdd
			   			var soloFechas = new Array();
			   			calendarioServ.festivosCalendarioServicios.forEach(function(unFestivo) {
			   				soloFechas.push( unFestivo.diaCalendario023 );
			   			});
						creaCalendario(soloFechas);									
						
						$('#enlaceInicializar').hide();
				   		$('#veranoDesde').val(calendarioServ.veranoDesde);
				   		$('#veranoHasta').val(calendarioServ.veranoHasta);
				   		$("#calendarioservicio_detail_form").rup_validate({
				   			feedback: $('#calendarioservicio_feedback'),
				   			liveCheckingErrors: false,				
				   			block:false,
				   			delay: 3000,
				   			gotoTop: true, 
				   			rules:{
				   				"anyoCalendario": {required: true, integer: true, min: 0},
				   				"veranoDesde": {required: true, date: true, min: y+'/01/01'},
				   				"veranoHasta": {required: true, date: true,fechaHastaMayor:"veranoDesde", max: y+'/12/31'}
				   			},
				   			showFieldErrorAsDefault: false,
				   			showErrorsInFeedback: true,
				   	 		showFieldErrorsInFeedback: false
				   		});
				   		//Control de cambios... Comrpobar si no hay info o no existe el rup_date
						controlCambiosFestivosIniciales = $('#previewcalendario').rup_date("getRupValue");
				   		controlCambiosFormJornada = $('#calendarioservicio_detail_form').rup_form("formSerialize");
				   		desbloquearPantalla();
		   			}else{
		   				$('#veranoDesde').val('');
				   		$('#veranoHasta').val('');
			   			$('#capaOcultar').hide();
			  	 		$('#capaBotones').hide();
			  	 		$('[id^="calendarioservicio_toolbar##btnImprimirCal"]').hide();
			  	 		$('#enlaceInicializar').show();
			  	 		controlCambiosFestivosIniciales="";
			  	 		$('#calendarioservicio_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.calendarioAnioSinDatos, "alert");
		   			}
		   		} else {
		   			$('#veranoDesde').val('');
			   		$('#veranoHasta').val('');
		   			$('#capaOcultar').hide();
		  	 		$('#capaBotones').hide();
		  	 		$('[id^="calendarioservicio_toolbar##btnImprimirCal"]').hide();
		  	 		$('#enlaceInicializar').show();
		  	 		controlCambiosFestivosIniciales="";
		  	 		$('#calendarioservicio_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.calendarioAnioSinDatos, "alert");
		   		}	
		   		controlCambiosFormJornada = $('#calendarioservicio_detail_form').rup_form("formSerialize");
		   		
		   		desbloquearPantalla();
		   	 }
	  	 	,error: function(){
	  	 		$('#veranoDesde').val('');
		   		$('#veranoHasta').val('');
	  	 		$('#capaOcultar').hide();
	  	 		$('#capaBotones').hide();
	  	 		$('[id^="calendarioservicio_toolbar##btnImprimirCal"]').hide();
	  	 		$('#enlaceInicializar').hide();
	  	 		controlCambiosFestivosIniciales="";
	  	 		controlCambiosFormJornada = $('#calendarioservicio_detail_form').rup_form("formSerialize");
	  	 		$('#calendarioservicio_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
	  	 		desbloquearPantalla();
		   	 }
		 });
	}
	 
	function cambioAnyo(elAnyo){
		if (elAnyo != controlCambiosComboAnyo){
			bloquearPantalla();
			controlCambiosComboAnyo = elAnyo;
			$("#calendarioservicio_detail_form").rup_validate('resetForm');
			$("#calendarioservicio_detail_form").rup_validate('destroy');
			leerCalendario(elAnyo);
		}
	}
	jQuery('#anyoCalendario').rup_combo({
		source : [ y-1, y, y+1 ]
//		,width: "100"
		,ordered: false	
		,rowStriping: true
		,change: function(){	
			if ( $('#veranoDesde').val() != '' && controlCambiosComboAnyo != this.value ){
				if (controlDeCambios()){
					cambioAnyo(this.value);
				}else{
					$.rup_messages("msgConfirm", {
						title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
						message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
						OKFunction: function(){
							cambioAnyo($('#anyoCalendario').rup_combo("getRupValue"));
						},
						CANCELFunction: function(e){
							$('#anyoCalendario').rup_combo("select", controlCambiosComboAnyo);
						}
					});			
				}
			}else{
				cambioAnyo(this.value);
			}
			
		}
		,open: function(){
			jQuery('#anyoCalendario-menu').width(jQuery('#anyoCalendario-button').innerWidth());
		}
	});
	$('#anyoCalendario').rup_combo("select", 1); //seleccionar año actual
	
	function createPDF() {
        getCanvas().then(function(canvas) {
        var
          cal = canvas.toDataURL('image/png'),
          doc = new jsPDF({
              orientation:'landscape',
              unit: 'px',
              format: 'a4',
          });        
         doc.addImage(cal, 'PNG', 25, 65);
         doc.text($.rup.i18nParse($.rup.i18n.app,"label.calendarioServicio")+" - "+y, 115, 40);
         doc.addImage(img, 'PNG', 25, 15);
         doc.save("calendarioServicio"+y+".pdf");         
         $('#previsualizardiv').remove();
         desbloquearPantalla();
        });
    }
 
	// create canvas object
	function getCanvas() {
	        return html2canvas(form, {
	         removeContainer: true
	        });
    }	
	
	function obtenerPdf(event){
        event.preventDefault();
        event.stopImmediatePropagation();
        if (controlDeCambios()){
        	generarPDF();
		}else{
			$.rup_messages("msgConfirm", {
				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
				OKFunction: function(){
					generarPDF();
				}
			});			
		}
    }
	
	function generarPDF(){
		bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.generandoPDF"));
        jQuery('.calendarioServicio').append('<div id="previsualizardiv"></div>');
        $('#previsualizardiv').append($('#previewcalendario').html());
        $('#previsualizardiv').addClass('calendarPDF');     
        $('#previsualizardiv').addClass('calendarioSinActive');   
        $('#previsualizardiv').addClass('hasDatepicker');           
        form = $('#previsualizardiv');         
        createPDF();   
	}
	
	function horariosAtencion(event){	
		event.preventDefault();
        event.stopImmediatePropagation();
		if (controlDeCambios()){
			window.location.href = "./horariosatencion/maint";
		}else{
			$.rup_messages("msgConfirm", {
				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
				OKFunction: function(){
					window.location.href = "./horariosatencion/maint";
				}
			});			
		}
	}
	function horariosLaborales(event){		
		event.preventDefault();
        event.stopImmediatePropagation();
        if (controlDeCambios()){
        	window.location.href = "./horarioslaborales/maint";
		}else{
			$.rup_messages("msgConfirm", {
				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
				OKFunction: function(){
					window.location.href = "./horarioslaborales/maint";
				}
			});			
		}
	}
	
	$('#calendarioservicio_link_cancel').click(function() {
		bloquearPantalla();
		$("#calendarioservicio_detail_form").rup_validate('resetForm');
		$("#calendarioservicio_detail_form").rup_validate('destroy');
		leerCalendario();
		
	});
	
		
	$('#enlaceInicializar').click(function() {		
		$.ajax({
		   	 type: 'POST'
		   	 ,url: '/aa79bItzulnetWar/administracion/calendarioservicio/inicializaCalendarioDefecto'
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,data: $.toJSON($('#anyoCalendario').val())			
		     ,async: false
		   	 ,success:function(){
		   		$('#calendarioservicio_feedback').rup_feedback("close"); 
		   		$('#calendarioservicio_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.calendarioInicializarOK, "ok");	
		   		leerCalendario($('#anyoCalendario').val());
		   	 }
	  	 	,error: function(){	  	 		
	  	 		$('#calendarioservicio_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.calendarioErrorInicializar, "error");
		   	 }
		 });
		
	});	
	
	
	$( "#calendarioservicio_detail_button_save").click(function() {

		var arrayFestivos = new Array();
		if ($('#previewcalendario').rup_date("getRupValue")!== ""){
			$('#previewcalendario').rup_date("getRupValue").split(",").forEach(function(unFestivo) {
				arrayFestivos.push( { "anyoCalendario023": $('#anyoCalendario').val(),"idDiaCalendario023": 0,"diaCalendario023":unFestivo });
			});
		}
		
		var jsonObject = 
		{
			"anyoCalendario": $('#anyoCalendario').val(),
			"veranoDesde": $('#veranoDesde').val(),
			"veranoHasta":  $('#veranoHasta').val(),
			"festivosCalendarioServicios": arrayFestivos	    				    					
		};		
		
		if ($("#calendarioservicio_detail_form").valid()){			
			$.ajax({
			   	 type: 'POST'
			   	 ,url: '/aa79bItzulnetWar/administracion/calendarioservicio/guardar'
			 	 ,dataType: 'json'
			 	 ,contentType: 'application/json' 
			     ,data: $.toJSON(jsonObject)			 	
			     ,async: false
			   	 ,success:function(){			   		
			   		$('#calendarioservicio_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
			   		//Por si hay que redibujar la jornada intensiva
			   		leerCalendario($('#anyoCalendario').val());
			   	 }
		   	 	,error: function(){		   	 	
			   		$('#calendarioservicio_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
			   	 }
			 });
		}		
	});

});