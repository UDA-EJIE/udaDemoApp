jQuery(document).ready(function(){
	
	$('#combo').rup_combo({
		//source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"],
		source : [
			{i18nCaption: "asp", value:"asp_value"},
			{i18nCaption: "c", value:"c_value"},
			{i18nCaption: "c++", value:"c++_value"},
			{i18nCaption: "coldfusion", value:"coldfusion_value"},
			{i18nCaption: "groovy", value:"groovy_value"},
			{i18nCaption: "haskell", value:"haskell_value"},
			{i18nCaption: "java", value:"java_value"},
			{i18nCaption: "javascript", value:"javascript_value"},
			{i18nCaption: "perl", value:"perl_value"},
			{i18nCaption: "php", value:"php_value"},
			{i18nCaption: "python", value:"python_value"},
			{i18nCaption: "ruby", value:"ruby_value"},
			{i18nCaption: "scala", value:"scala_value"}
		],
		selected: "perl_value",
		width: 300,
		blank : "0" 
	});
	
	
	$('#comboRemoto').rup_combo({
		source : "comboSimple/remote",
		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
		selected: "Combo",
		width: 300
	});

	
	$('#comboLargo').rup_combo({
		/*source: ["John Doe - 78 West Main St Apt 3A | Bloomsburg, PA 12345 (footer text)", 
				"Jane Doe - 78 West Main St Apt 3A | Bloomsburg, PA 12345 (footer text)", 
				"Joseph Doe - 78 West Main St Apt 3A | Bloomsburg, PA 12345 (footer text)", 
				"Mad Doe Kiiid - 78 West Main St Apt 3A | Bloomsburg, PA 12345 (footer text)"
		],*/
		source : [
			{i18nCaption: "jon_doe", value:"jon"},
			{i18nCaption: "jane_doe", value:"jane"},
			{i18nCaption: "joseph_doe", value:"joseph"},
			{i18nCaption: "mad_doe", value:"mad"}
		],
		width: 400,
		format: "default"
	});
	
	
	$('#comboGrupos').rup_combo({
		/*sourceGroup : [ 
			{"Futbol" : ["Alaves", "Athletic", "Real Sociedad"]},
			{"Baloncesto" : ["Caja Laboral", "BBB", "Lagun Aro"]},
			{"Formula 1" : [ "Fernando Alonso", "Hamilton", "Vettel"]}
		],
		imgs : [
			{Alaves: "delete"},
			{Athletic: "filter"},
			{Real Sociedad: "print"}
		]*/
		sourceGroup : [ 
			{"futbol" : [
				{i18nCaption: "alaves", value:"alaves_value", style:"delete"},
				{i18nCaption: "ath", value:"ath_value", style:"filter"},
				{i18nCaption: "real", value:"real_value", style:"print"}
			]},
			{"baloncesto" : [
				{i18nCaption: "laboral", value:"laboral_value"},
				{i18nCaption: "bilbao", value:"bilbao_value"},
				{i18nCaption: "lagun aro", value:"lagun aro_value"}
			]},
			{"formula1" : [
				{i18nCaption: "falonso", value:"falonso_value"},
				{i18nCaption: "hamilton", value:"hamilton_value"},
				{i18nCaption: "vettel", value:"vettel_value"}
			]}
		]
	});
	
	$('#comboGruposRemoto').rup_combo({
		sourceGroup : "comboSimple/remoteGroup",
		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
		width: 300
	});
		 
		 
	$('#comboImgs').rup_combo({
		source : ["Borrar", "Filtrar", "Imprimir"],
		imgs : [
			{Borrar: "delete"},
			{Filtrar: "filter"},
			{Imprimir: "print"}
		]
	});
	
	
});