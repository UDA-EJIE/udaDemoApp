$(function($) {
    jQuery('#nomBuzonCas').rup_select({
        url: '../experimental/getBuzonesNivel3',
        sourceParam : {
            text: 	'nomBuzonCastT28', 
            id:	'idbuzonT28'
        },
        combo: false
    });
});