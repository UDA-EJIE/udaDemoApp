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
jQuery(function($) {
    
    // Creacion de los componentes feedback
    $('#feedback_fileupload_form').rup_feedback();
    $('#feedback_fileupload_form_multiple').rup_feedback();
    
    
    // Se utiliza jquery.form para realizar el submit de los formularios por AJAX
    $('#uploadForm').ajaxForm(function(){
        $('#feedback_fileupload_form').rup_feedback('set','Los datos se han enviado correctamente');
    });

    $('#uploadFormMultiple').ajaxForm(function(){
        $('#feedback_fileupload_form_multiple').rup_feedback('set','Los datos se han enviado correctamente');
    });


    
    // Upload integrado en formulario
    $('#fileupload_pif_form').rup_upload({
        fileInput: $('#file_pif_form'),
        url: '../upload/pifForm',
        pif:{
            folderPath: '/x21a',
            fileTtl: 60,
            preserveName:true
        }
    }); 
    
    $("#cmdSubmit").bind("click", function () {
        file = $("#fileInput")[0].files[0];
         alert(file);
         file = $("#fileInput").prop('files')[0];
         alert(file);
         
     }); 

 

    $('.contenedor').addClass('show');
    
    $("#pruebaButton").unbind().click(function(){
    	prueba();
    });
    
    function prueba(){
    	const file = document.querySelector("#file_pif_form").files[0];
    	var data = new FormData();
    	data.append("file", file);
    	//data.append("codExpediente36", codExpediente36);
    	//data.append("tipoDocumento", $("#dialogAportacion #file_pif_form").val());
    	var url = "../upload/aportarDocumento";
		 $.rup_ajax({
             url: url,
             type: "POST",
             enctype: 'multipart/form-data',
             processData: false,
             contentType: false,
             cache: false,
             timeout: 600000,
             data: data,
             success: function (data){
		    	  console.log(data);
             },
             beforeSend: function(){

				},
             error:function(data){
         	  $("#dialogAportacion").rup_dialog("close");
		    	  Ad27bUtils.desBloquearInterfaz();
		    	  $("#tabTramitacionExpediente_detail_feedback").rup_feedback('set',data.responseText,"error");
             }
         });
    }
});