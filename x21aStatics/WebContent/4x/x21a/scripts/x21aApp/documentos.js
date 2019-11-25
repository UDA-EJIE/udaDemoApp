$(document).ready(function () {

    // Referencia al div que contiene el iframe y va a mostrarse como diálogo
    var $div = jQuery('#divDocumentosIFrame');
    // Referencia al iframe en el que se va a mostrar el contenido de la pantalla de documentos
    var $iframe = jQuery('#iframeDocumentos');
    
    // Url base que referencia la pantalla de documentos. Se deberá de sustituir por la url correspondiente.
    var baseUrl = 'http://desarrollo.jakina.ejiedes.net:7001/y52bComunWar/documentosExpedienteExternal';
    
    // Creación del diálogo mediante el componente RUP Dialogo. 
    $div.rup_dialog({
        type:		$.rup.dialog.DIV,
        modal:		true,		 
        title: 		'Documentos',
        autoOpen:	false,
        showLoading:true,
        height:		700,
        width:		1024,	
        async:		false, 
        resizable:	true,
        buttons: [{
            text: 'Cerrar',      
            click: 	function() {
                // Se cierra el dialogo
                $div.rup_dialog('close');
            }
        }]
    }).on('dialogclose', function(){
        // Se elimina el contenido del iframe
        $iframe.attr('src','');
    });
    
    
    /*
     * Método que gestiona la apertura 
     */
    function showDocuments(folderID, codigoExpediente){
        // Se modifica el valor de la propiedad src del iframe para que cargue los documentos correspondientes
        $iframe.attr('src', baseUrl+'?folderID='+folderID+'&codigoExpediente='+codigoExpediente+'&grupoFamilia=0');
        // Se abre el dialogo que contiene el iframe
        $div.rup_dialog('open');
    }
    
    // Handler para el evento click del boton 1. 
    $('#btnDocumentosIFrame1').bind('click', function () {
        showDocuments('R02TC49FD6D97DAED3497D9EEC956EAD873B9B7A1BFC', 'RSP_9661%2F2014_06');
    });

    // Handler para el evento click del boton 2. 
    $('#btnDocumentosIFrame2').bind('click', function () {
        showDocuments('R02TBD2AF21DD3578A9FA663D4242612B611841CA2F9', 'NBNC_RSP_9678%2F2013_02');
    });

});