
(function ($) {
	
	$.extend($.rup, {
		size : {
//			Forma correcta de identificar los diferentes tamaños(sizes) de las pantallas
//			xs : (window.matchMedia("(max-width: 767px)").matches), 
//			sm : (window.matchMedia("(min-width: 768px)").matches) && !(window.matchMedia("(min-width: 992px)").matches),
//			md : (window.matchMedia("(min-width: 992px)").matches) && !(window.matchMedia("(min-width: 1200px)").matches),
//			lg : (window.matchMedia("(min-width: 1200px)").matches),
			
//			Forma compatible
			xs : function(){return(768 > $(window).width());}, 
			sm : function(){return(($(window).width() >= 768) && ($(window).width() < 992));},
			md : function(){return(($(window).width() >= 992) && ($(window).width() < 1200));},
			lg : function(){return($(window).width() >= 1200);}
		}
	});
	
	$.fn.rup_combo("extend", {
		//Obtener la opción vacía (del fichero de la app o el por defecto)
		_getBlankLabel : function (id){
			var app = $.rup.i18n.app;
			if (app[id] && app[id]["_blank"]){
				return app[id]["_blank"];
			} 
			return app["comun"]["_blank"];
		}
	});
	$.fn.rup_form("extend", {
		disable : function (excepciones){
			var $self = this;
			$self.find(":input").not(excepciones).each(function (i, element) {
				$this = $(element);
				if($this.is(":checkbox") && $this.parents(".has-switch").length > 0) {
					if (!$this.bootstrapSwitch("isDisabled")) {
						$this.bootstrapSwitch("setDisabled", true);
					}
				} else if ($this.is(".rup_combo")) {
					if (!$this.rup_combo("isDisabled")) {
						$this.rup_combo("disable");
					}
				} else if (!$this.attr("disabled")){
					$this.attr("disabled", true);
				}
			});
		}
		, enable: function (id){
			var $self = this;
			$self.find(":input").each(function (i, element) {
				$this = $(element);
				if($this.is(":checkbox") && $this.parents(".has-switch").length > 0) {
					if ($this.bootstrapSwitch("isDisabled")) {
						$this.bootstrapSwitch("setDisabled", false);
					}
				} else if ($this.is(".rup_combo")) {
					if ($this.rup_combo("isDisabled")) {
						$this.rup_combo("enable");
					}
				} else if ($this.attr("disabled")){
					$this.removeAttr("disabled");
				}
			});
		}
		, clearValidation: function (){
			 var $form = $(this);
           jQuery('.rup-maint_validateIcon, label.error', $form).remove();
           jQuery('input.error', $form).removeClass('error');
           if ($form.data('validator')) {
               $form.rup_validate('resetElements');
               $form.data("settings").feedback.rup_feedback("hide");
           }
           $form.rup_form("clearForm");
			$(":checkbox", $form).each(function(i, element) {
				if ($(element).parents(".has-switch").length > 0) {
					$(element).bootstrapSwitch('setState', false)
				}
			});
		}
	});
	
})(jQuery);