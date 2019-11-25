/*!
 * Copyright 2011 E.J.I.E., S.A.
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
$(function () {

	//LOCAL
	$('#comboAbuelo').rup_combo({
		source: [{
				i18nCaption: "a",
				value: "01"
			},
			{
				i18nCaption: "b",
				value: "02"
			},
			{
				i18nCaption: "g",
				value: "03"
			}
		],
		selected: "02",
		width: '99%',
		customClasses: ["select-material"],
		blank: ""
	});

	$('#comboPadre').rup_combo({
		parent: ["comboAbuelo"],
		source: {
			"01": [{
				i18nCaption: "a1",
				value: "a1_value"
			}, {
				i18nCaption: "a2",
				value: "a2_value"
			}, {
				i18nCaption: "a3",
				value: "a3_value"
			}],
			"02": [{
				i18nCaption: "b1",
				value: "b1_value"
			}, {
				i18nCaption: "b2",
				value: "b2_value"
			}, {
				i18nCaption: "b3",
				value: "b3_value"
			}],
			"03": [{
				i18nCaption: "g1",
				value: "g1_value"
			}, {
				i18nCaption: "g2",
				value: "g2_value"
			}, {
				i18nCaption: "g3",
				value: "g3_value"
			}]

		},
		selected: "b1_value",
		width: '99%',
		customClasses: ["select-material"]
	});

	$('#comboHijo').rup_combo({
		parent: ["comboPadre"],
		source: {
			"b1_value": [{
				i18nCaption: "Bilbao",
				value: "b1_1_value"
			}, {
				i18nCaption: "Basauri",
				value: "b1_2_value"
			}, {
				i18nCaption: "Galdakao",
				value: "b1_3_value"
			}],
			"b2_value": [{
				i18nCaption: "Leioa",
				value: "b2_1_value"
			}, {
				i18nCaption: "Las Arenas",
				value: "b2_2_value"
			}, {
				i18nCaption: "Getxo",
				value: "b2_3_value"
			}],
			"b3_value": [{
				i18nCaption: "Sestao",
				value: "b3_1_value"
			}, {
				i18nCaption: "Barakaldo",
				value: "b3_2_value"
			}, {
				i18nCaption: "Portu",
				value: "b3_3_value"
			}]

		},
		selected: "b1_2_value",
		width: '99%',
		customClasses: ["select-material"]

	});


	//REMOTO
	$('#comboAbueloRemoto').rup_combo({
		source: "comboEnlazadoSimple/remoteEnlazadoProvincia",
		sourceParam: {
			label: "desc" + $.rup_utils.capitalizedLang(),
			value: "code",
			style: "css"
		},
		blank: "",
		selected: "2",
		width: '99%',
		customClasses: ["select-material"]
	});

	$('#comboPadreRemoto').rup_combo({
		parent: ["comboAbueloRemoto"],
		source: "comboEnlazadoSimple/remoteEnlazadoComarca",
		sourceParam: {
			label: "desc" + $.rup_utils.capitalizedLang(),
			value: "code",
			style: "css"
		},
		blank: "",
		selected: "7",
		width: '99%',
		customClasses: ["select-material"]
	});

	$('#comboHijoRemoto').rup_combo({
		parent: ["comboPadreRemoto"],
		source: "comboEnlazadoSimple/remoteEnlazadoLocalidad",
		sourceParam: {
			label: "desc" + $.rup_utils.capitalizedLang(),
			value: "code",
			style: "css"
		},
		blank: "",
		selected: "8",
		width: '99%',
		customClasses: ["select-material"]
	});


	//MIXTO I
	$('#mixto_comboAbueloRemoto').rup_combo({
		source: "comboEnlazadoSimple/remoteEnlazadoProvincia",
		sourceParam: {
			label: "desc" + $.rup_utils.capitalizedLang(),
			value: "code",
			style: "css"
		},
		selected: 2,
		blank: "0",
		width: '99%',
		customClasses: ["select-material"]
	});

	$('#mixto_comboPadre').rup_combo({
		parent: ["mixto_comboAbueloRemoto"],
		source: {
			"1": [{
				i18nCaption: "a1",
				value: "1"
			}, {
				i18nCaption: "a2",
				value: "2"
			}, {
				i18nCaption: "a3",
				value: "3"
			}],
			"2": [{
				i18nCaption: "b1",
				value: "7"
			}, {
				i18nCaption: "b2",
				value: "8"
			}, {
				i18nCaption: "b3",
				value: "9"
			}],
			"3": [{
				i18nCaption: "g1",
				value: "4"
			}, {
				i18nCaption: "g2",
				value: "5"
			}, {
				i18nCaption: "g3",
				value: "6"
			}]
		},
		width: '99%',
		customClasses: ["select-material"]
	});

	$('#mixto_comboHijoRemoto').rup_combo({
		parent: ["mixto_comboPadre"],
		source: "comboEnlazadoSimple/remoteEnlazadoLocalidad",
		sourceParam: {
			label: "desc" + $.rup_utils.capitalizedLang(),
			value: "code",
			style: "css"
		},
		width: '99%',
		customClasses: ["select-material"]
	});


	//MIXTO II
	$('#mixto2_comboAbuelo').rup_combo({
		source: [{
				i18nCaption: "a",
				value: "1"
			},
			{
				i18nCaption: "b",
				value: "2"
			},
			{
				i18nCaption: "g",
				value: "3"
			}
		],
		selected: 2,
		blank: "0",
		width: '98%',
		customClasses: ["select-material"]
	});

	$('#mixto2_comboPadreRemoto').rup_combo({
		parent: ["mixto2_comboAbuelo"],
		source: "comboEnlazadoSimple/remoteEnlazadoComarca",
		sourceParam: {
			label: "desc" + $.rup_utils.capitalizedLang(),
			value: "code",
			style: "css"
		},
		width: '98%',
		customClasses: ["select-material"]
	});

	$('#mixto2_comboHijo').rup_combo({
		parent: ["mixto2_comboPadreRemoto"],
		source: {
			"7": ["Bilbao", "Basauri", "Galdakao"],
			"8": ["Leioa", "Las Arenas", "Getxo"],
			"9": ["Sestao", "Barakaldo", "Portu"]
		},
		width: '98%',
		customClasses: ["select-material"]
	});


	// Remote group
	$('#remoteGroup_comboPadre').rup_combo({
		source: "comboEnlazadoSimple/remoteEnlazadoProvincia",
		sourceParam: {
			label: "desc" + $.rup_utils.capitalizedLang(),
			value: "code",
			style: "css"
		},
		selected: 2,
		blank: "0",
		width: '98%',
		customClasses: ["select-material"]
	});

	$('#remoteGroup_comboHijo').rup_combo({
		sourceGroup: "comboSimple/remoteGroupEnlazado",
		parent: ["remoteGroup_comboPadre"],
		sourceParam: {
			label: "desc" + $.rup_utils.capitalizedLang(),
			value: "code",
			style: "css"
		},
		//width: '99%',
		customClasses: ["select-material"],
		multiselect: true
	});
});