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

jQuery(document).ready(
		function() {

			var vista=new Object();
			vista.ui= new Object();

			vista.ui.graficoLine = "#graficoLine",
					vista.ui.graficoBar = "#graficoBar",
					vista.ui.graficoRadar = "#graficoRadar",
					vista.ui.graficoPolar = "#graficoPolar",
					vista.ui.graficoPie = "#graficoPie",
					vista.ui.graficoDoughnut = "#graficoDoughnut",
					vista.ui.graficoBubble = "#graficoBubble";

			vista.mesesData = [], vista.colorsData = [], vista.radarData = [],
			vista.bubbleData = [];

			fncInitialize(vista);
			fncOnDomRefresh(vista);

		});

function fncInitialize(vista) {
	/* data */
	
	vista.mesesData = {
		labels : $.rup.i18n.app.charts.mesesLabels,
		datasets : [ {
			label : $.rup.i18n.app.charts.datasetRadar.dataset1,
			data : [ 65, 59, 80, 81, 56, 55, 40 ]
		} ]
	};
	vista.colorsData = {

		labels : $.rup.i18n.app.charts.colorLabels,
		datasets : [ {
			data : [ 300, 50, 100 ],
			backgroundColor : [ "#FF6384", "#36A2EB", "#FFCE56" ],
			hoverBackgroundColor : [ "#FF6384", "#36A2EB", "#FFCE56" ]
		} ]
	};
	vista.radarData = {
		labels : $.rup.i18n.app.charts.radarLabels,
		datasets : [ {
			label : $.rup.i18n.app.charts.datasetRadar.dataset1,
			data : [ 65, 59, 90, 81, 56, 55, 40 ]
		}, {
			label : $.rup.i18n.app.charts.datasetRadar.dataset2,
			data : [ 28, 48, 40, 19, 96, 27, 100 ]
		} ]
	};

	vista.bubbleData = {
		datasets : [ {
			label : $.rup.i18n.app.charts.datasetRadar.dataset1,
			data : [ {
				x : 20,
				y : 30,
				r : 15
			}, {
				x : 40,
				y : 10,
				r : 10
			} ],
			backgroundColor : "#FF6384",
			hoverBackgroundColor : "#FF6384",
		} ]
	};
}

function fncOnDomRefresh(vista) {

	var options = {
		legend : {
			display : true
		}

	};
	// line chart
	$(vista.ui.graficoLine).rup_chart({
		type : "line",
		data : vista.mesesData,
		options : options
	});

	// bar chart
	$(vista.ui.graficoBar).rup_chart({
		type : "bar",
		data : vista.mesesData,
		options : options
	});

	// radar chart
	$(vista.ui.graficoRadar).rup_chart({
		type : "radar",
		data : vista.radarData
	});

	// polar chart
	$(vista.ui.graficoPolar).rup_chart({
		type : "polarArea",
		data : vista.colorsData

	});

	// pie chart
	$(vista.ui.graficoPie).rup_chart({
		type : "pie",
		data : vista.colorsData

	});

	// doughnut chart
	$(vista.ui.graficoDoughnut).rup_chart({
		type : "doughnut",
		data : vista.colorsData

	});

	// bubble chart
	$(vista.ui.graficoBubble).rup_chart({
		type : 'bubble',
		data : vista.bubbleData

	});
}
