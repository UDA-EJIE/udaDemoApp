
(function ($) {
	
	$.fn.rup_wizard("extend", {
		_gotoNextStep : function (rupWizard, settings, event){
			//Obtener paso siguiente
			var nextStep = parseInt($("#steps li.current").attr("id").substring(8))+1;					
			//Comprobar que no está deshabilitado (o buscar el siguiente habilitado)
			if (rupWizard.isStepDisabled(nextStep)){
				nextStep = $("#stepDesc"+(nextStep-1)).nextAll("li:not(.disabled)").first().attr("id");
				if (nextStep !== undefined){
					nextStep = parseInt(nextStep.substring(8));
				} else {
					return false;
				}
				
				//Si fuera necesario, se generar el resumen
				if(jQuery("#steps").children().size()-1 === nextStep){
					if (settings.summaryFnc_PRE && settings.summaryFnc_PRE.call() === false){
						return false;
					} else {
						rupWizard._generateSummary(nextStep, rupWizard, settings);
					}
				}
			}
			
			//Invocar f(x) del paso (si existe)
			if (settings.stepFnc[nextStep] !== undefined){
				if ($("#stepDesc"+nextStep).not(".rup-wizard_summary").length>0){ //Evitar resumen (mala configuracion desarrollador)
					if (settings.stepFnc[nextStep].call() === false){
						event.stopImmediatePropagation();
						return false;
					}
				} 
			}
			
			//Cambiar de paso
			$("#stepDesc"+nextStep).rup_wizard("step", nextStep);
		}
	});
		
})(jQuery);