function bloquearPantalla(mensaje,callback){
	var localMensaje = $.rup.i18nParse($.rup.i18n.app,"comun.cargando");
	
	if(mensaje !== undefined && typeof mensaje === "string"){
		localMensaje = mensaje;
	}
	
	localMensaje = localMensaje+" ...";
	
	jQuery.blockUI({	
		css: {
			    border: 'none', 
			    padding: '15px',
			    width:	'100%',
			    height: 'auto',
			    left: '0px',
			    backgroundColor: '#FFF',
			    '-webkit-border-radius': '0px', 
			    '-moz-border-radius': '0px',
			    'border-radius': '0px',
				'background-size': '12%',
			    opacity: 0.8, 
			    color: '#F0256F'
		}
		, message: function(){
			return(
					jQuery('<div class="loadText">')
						.html(localMensaje)
						.prepend(jQuery("<img>")
							.addClass("imgLoadMessage")
							.attr("src",$.rup.APP_STATICS+"/images/loader.gif")));
		}
		, baseZ: 1004
		, onBlock: function() {
			if(callback !== undefined && typeof callback === "function"){
				callback();
			}
        }
	});
	//jQuery('.blockUI').fitText(2); 
}

/**
 * bloquear pantalla con funcion callback y parametro para el mismo
 */
function bloquearPantallaCallbackParams(mensaje,callback, param){
	var localMensaje = $.rup.i18nParse($.rup.i18n.app,"comun.cargando");
	
	if(mensaje !== undefined && typeof mensaje === "string"){
		localMensaje = mensaje;
	}
	
	localMensaje = localMensaje+" ...";
	
	jQuery.blockUI({	
		css: {
			    border: 'none', 
			    padding: '15px',
			    width:	'100%',
			    height: 'auto',
			    left: '0px',
			    backgroundColor: '#FFF',
			    '-webkit-border-radius': '0px', 
			    '-moz-border-radius': '0px',
			    'border-radius': '0px',
				'background-size': '12%',
			    opacity: 0.8, 
			    color: '#F0256F'
		}
		, message: function(){
			return(
					jQuery('<div class="loadText">')
						.html(localMensaje)
						.prepend(jQuery("<img>")
							.addClass("imgLoadMessage")
							.attr("src",$.rup.APP_STATICS+"/images/loader.gif")));
		}
		, baseZ: 1004
		, onBlock: function() {
			if(callback !== undefined && typeof callback === "function"){
				callback(param);
			}
        }
	});
	//jQuery('.blockUI').fitText(2); 
}

function desbloquearPantalla(callback){
	jQuery('.aa79b-content')
		.addClass('displayNone')
		.removeClass('oculto')
		.show('fade', 200, function(){
			jQuery.unblockUI({ 
                onUnblock: function(){ 
                	jQuery('.aa79b-content').removeClass('displayNone');
                	if(callback !== undefined && typeof callback === "function"){
        				callback();
        			}
                } 
            });
		});
}

function bloquearElemento($el, mensaje, callback){
	var localMensaje = $.rup.i18nParse($.rup.i18n.app,"comun.cargando");
	
	if(mensaje !== undefined && typeof mensaje === "string"){
		localMensaje = mensaje;
	}
	
	localMensaje = localMensaje+" ...";
	
	$el.block({	
		css: {
			    border: 'none', 
			    padding: '15px',
			    width:	'100%',
			    height: 'auto',
			    left: '0px',
			    backgroundColor: '#FFF',
			    '-webkit-border-radius': '0px', 
			    '-moz-border-radius': '0px',
			    'border-radius': '0px',
				'background-size': '12%',
				'z-index': '1002',
			    opacity: 0.8, 
			    color: '#F0256F'
		}
		, message: function(){
			return(
					jQuery('<div class="loadText">')
					.html(localMensaje)
					.prepend(jQuery("<img>")
							.addClass("imgLoadMessage")
							.attr("src",$.rup.APP_STATICS+"/images/loader.gif")));
		}
		, baseZ: 1002
		, onBlock: function() {
			if(callback !== undefined && typeof callback === "function"){
				callback();
			}
        }
	});
	jQuery('.blockUI').fitText(2); 
}

function desbloquearElemento($el, callback){
	$el.unblock({
	    onUnblock: function(){ 
	    	jQuery('.aa79b-content').removeClass('displayNone'); 
	    	if(callback !== undefined && typeof callback === "function"){
				callback();
			}
	    } 
	});
}