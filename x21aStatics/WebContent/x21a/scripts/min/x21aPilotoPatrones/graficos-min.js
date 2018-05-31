/*
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
jQuery(document).ready(function(){var a=new Object();a.ui=new Object();a.ui.graficoLine="#graficoLine",a.ui.graficoBar="#graficoBar",a.ui.graficoRadar="#graficoRadar",a.ui.graficoPolar="#graficoPolar",a.ui.graficoPie="#graficoPie",a.ui.graficoDoughnut="#graficoDoughnut",a.ui.graficoBubble="#graficoBubble";a.mesesData=[],a.colorsData=[],a.radarData=[],a.bubbleData=[];fncInitialize(a);fncOnDomRefresh(a)});function fncInitialize(a){a.mesesData={labels:$.rup.i18n.app.charts.mesesLabels,datasets:[{label:$.rup.i18n.app.charts.datasetRadar.dataset1,data:[65,59,80,81,56,55,40]}]};a.colorsData={labels:$.rup.i18n.app.charts.colorLabels,datasets:[{data:[300,50,100],backgroundColor:["#FF6384","#36A2EB","#FFCE56"],hoverBackgroundColor:["#FF6384","#36A2EB","#FFCE56"]}]};a.radarData={labels:$.rup.i18n.app.charts.radarLabels,datasets:[{label:$.rup.i18n.app.charts.datasetRadar.dataset1,data:[65,59,90,81,56,55,40]},{label:$.rup.i18n.app.charts.datasetRadar.dataset2,data:[28,48,40,19,96,27,100]}]};a.bubbleData={datasets:[{label:$.rup.i18n.app.charts.datasetRadar.dataset1,data:[{x:20,y:30,r:15},{x:40,y:10,r:10}],backgroundColor:"#FF6384",hoverBackgroundColor:"#FF6384",}]}}function fncOnDomRefresh(a){var b={legend:{display:true}};$(a.ui.graficoLine).rup_chart({type:"line",data:a.mesesData,options:b});$(a.ui.graficoBar).rup_chart({type:"bar",data:a.mesesData,options:b});$(a.ui.graficoRadar).rup_chart({type:"radar",data:a.radarData});$(a.ui.graficoPolar).rup_chart({type:"polarArea",data:a.colorsData});$(a.ui.graficoPie).rup_chart({type:"pie",data:a.colorsData});$(a.ui.graficoDoughnut).rup_chart({type:"doughnut",data:a.colorsData});$(a.ui.graficoBubble).rup_chart({type:"bubble",data:a.bubbleData})};