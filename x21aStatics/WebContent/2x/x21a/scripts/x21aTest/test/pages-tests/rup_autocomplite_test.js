	
	test("rup_autocomplete public functions Test 1 parte", function(){
		
		//prueba de funciones publicas
		var autopomplete1 = S.win.$("#autocomplete_label"); 
		var autopomplete2 = S.win.$("#patron_label");
		var layerOptions1 = S.win.$(".ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible");
		var layerOptions2 = S.win.$(".ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:not(:visible)");
		
		
		//getRupValue method
		equals(autopomplete1.rup_autocomplete("getRupValue"), "java", "Prueba función getRupValue.");
		
		//val method
		equals(autopomplete1.rup_autocomplete("val"), "java", "Prueba función val.");
		
		//setRupValue method
		autopomplete1.rup_autocomplete("setRupValue", "coldfusion");
		equals(autopomplete1.rup_autocomplete("getRupValue"), "coldfusion", "Prueba función setRupValue.");
		
		//"data not accepted with the method setRupValue" test
		autopomplete1.rup_autocomplete("setRupValue", "no debe aceptar el valor" );
		S("body").click();
		notEqual(autopomplete1.rup_autocomplete("getRupValue"), "no debe aceptar el valor", "Prueba función setRupValue no debe aceptar valor.");
		
		//close method
		autopomplete1.rup_autocomplete("close");
		equals(layerOptions1.css("display"), "none", "Prueba función close.");
		
		//search method
		autopomplete1.rup_autocomplete("search", "perl");
		equals(S(".ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:first li a:first").html(), "<strong>perl</strong>", "Prueba función search.");
		
	});
	
	test("rup_autocomplete public functions Test 2 parte (options)", function(){
		
		var autopomplete1 = S.win.$("#autocomplete_label");
		var layerOptions1 = S.win.$(".ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible");
		
		//option method
		autopomplete1.rup_autocomplete("close");
		S("#autocomplete_label").type("[left]\b[left]\b[left]\b[left]\b");
		autopomplete1.rup_autocomplete("option", "minLength", 5);
		
		S("#autocomplete_label").type("groo").then(function(){
			equals(layerOptions1.css("display"), "none", "Se ha cambiado el tamaño mínimo a 5, por lo que al escribir \"groo\" debe haber 0 sugerencias.");
			
			S("#autocomplete_label").type("v").then(function(){
				S('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:first').visible(function(){
					equals(S(".ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:first li a:first").html(), "<strong>groov</strong>y", "Se ha cambiado el tamaño mínimo a 5, por lo que al escribir una \"v\" adicional debe sugerir <strong>groov</strong>y.");
				});
			});
		});
	});
	
	test("rup_autocomplete public functions Test 3 parte (enable/disabled)", function(){
		
		var autopomplete1 = S.win.$("#autocomplete_label"); 
	
		//Disable/enable methods
		autopomplete1.rup_autocomplete("close");
		autopomplete1.rup_autocomplete("setRupValue", "");
		S("#autocomplete_label").click().then(function(){
			equals(S("#autocomplete_label:focus").size(), 1, "Inicialmente la selección del elemento es posible.");
		});
		autopomplete1.rup_autocomplete("disable");
		S("#autocomplete_label").then(function(){
			equals(S("#autocomplete_label:focus").attr("disabled"), "disabled", "Se deshabilita el elemento por lo que la selección ya no es posible.");
			autopomplete1.rup_autocomplete("enable");
			S("#autocomplete_label").click().type("java").then(function(){});
			equals(S(".ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:first li").size(), 2, "Se vuelve a habilitar el elemento por lo que vuelve a sugerir opciones.");
		});
		
		
	});
	
	test("rup_autocomplete public functions Test 4 parte (on/off)", function(){
		
		var autopomplete1 = S.win.$("#autocomplete_label"); 
	
		//On/off methods
		autopomplete1.rup_autocomplete("close");
		autopomplete1.rup_autocomplete("setRupValue", "");
		autopomplete1.rup_autocomplete("off");
		S("#autocomplete_label").click().type("java").then(function(){
			equals(S(".ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible li").size(), 0, "Se pone en \"off\" el componente, por lo que no sugiere ninguna opción.");
			autopomplete1.rup_autocomplete("on");
			equals(S(".ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:first li").size(), 2, "Se pone de nuevo en \"on\" el componente, por lo que sugiere 2 opciones.");
		});
	});
	

	test("rup_autocomplete functional preload", function(){
		
		var autopomplete1 = S.win.$("#autocomplete_label");
		
		//preload
		equals(S("#autocomplete_label").val(), "java", "Prueba de pre-carga");
		equals(S(".ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:first li a:first").html(), "<strong>java</strong>", " 1ª sugerencia <strong>java</strong>.");
		equals(S(".ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:first li a:last").html(), "<strong>java</strong>script", "2ª sugerencia <strong>java</strong>script.");
	});

	test("rup_autocomplete functional load Test", function(){
		
		var autopomplete1 = S.win.$("#autocomplete_label"); 
		
		//"suggesting data correct" test
		autopomplete1.rup_autocomplete("close");
		autopomplete1.rup_autocomplete("setRupValue", "");
		S("#autocomplete_label").click().type("p");
		
		S('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:first').visible(function(){
		    equal( S(".ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:first li").size(), 3, "Hay 3 resultados");
		    equals(S(".ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:first li a:first").html(), "<strong>p</strong>erl", " 1ª sugerencia <strong>p</strong>erl.");
		    equals(S(".ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:first li a:lt(2):gt(0)").html(), "<strong>p</strong>h<strong>p</strong>", " 2ª sugerencia <strong>p</strong>h<strong>p</strong>.");
		    equals(S(".ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:first li a:last").html(), "<strong>p</strong>ython", " 3ª sugerencia <strong>p</strong>ython.");
		});
	});
	
	test("rup_autocomplete select data Test 1", function(){
		
		var autopomplete1 = S.win.$("#autocomplete_label"); 
		
		autopomplete1.rup_autocomplete("close");
//		autopomplete1.rup_autocomplete("setRupValue", "");
		S("#autocomplete_label").click().type("[left]\b[left]\b[left]\b[left]\bhask");
		
		S('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:first').visible(function(){
			S('#autocomplete_label').move(".ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:first a:first").visible().click();
			S('#patron_label').click(function(){
				equals(S("#autocomplete_label").val(), "haskell", "Prueba selección de valor.");
			});
		});
		
	});
	
	test("rup_autocomplete select data Test 2", function(){
		
		var autopomplete1 = S.win.$("#autocomplete_label"); 
		
		autopomplete1.rup_autocomplete("close");
//		autopomplete1.rup_autocomplete("setRupValue", "");
		S("#autocomplete_label").click().type("[left]\b[left]\b[left]\b[left]\bhask").then(function(){
		
			S('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:first').visible(function(){
				S('#patron_label').click(function(){
					notEqual(S("#autocomplete_label").val(), "hask", "Prueba borrado bajo no selección de valor.");
				});
			});
		});
		
	});

	
  
  




