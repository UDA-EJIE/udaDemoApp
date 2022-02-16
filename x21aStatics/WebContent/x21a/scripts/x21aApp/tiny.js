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
jQuery(function($) {

    $('textarea').tinymce({
        // Location of TinyMCE script
        script_url : window.STATICS + '/tiny_mce/tiny_mce.js',
        
        // General options
        theme : 'advanced',
        plugins : 'autolink,lists,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,advlist',

        // Theme options
        theme_advanced_buttons1 : 'save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect',
        theme_advanced_buttons2 : 'cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor',
        theme_advanced_buttons3 : 'tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen',
        theme_advanced_buttons4 : 'insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak',
        theme_advanced_toolbar_location : 'top',
        theme_advanced_toolbar_align : 'left',
        theme_advanced_statusbar_location : 'bottom',
        theme_advanced_resizing : true
    });

    //Enlaces con funciones
    $('#show').click(function() {
        $('#editor').tinymce().show();
    });
    
    $('#hide').click(function() {
        $('#editor').tinymce().hide();
    });
    
    $('#bold').click(function() {
        $('#editor').tinymce().execCommand('Bold');
    });
    
    $('#getContents').click(function() {
        alert($('#editor').html());
    });
    
    $('#getHTML').click(function() {
        alert($('#editor').tinymce().selection.getContent());
    });
    
    $('#getText').click(function() {
        alert($('#editor').tinymce().selection.getContent({format : 'text'}));
    });
    
    $('#getElement').click(function() {
        alert($('#editor').tinymce().selection.getNode().nodeName);
    });
    
    $('#insertHTML').click(function() {
        $('#editor').tinymce().execCommand('mceInsertContent',false,'<b>Hello world!!</b>');
    });

    $('#replace').click(function() {
        $('#editor').tinymce().execCommand('mceReplaceContent',false,'<b>{$selection}</b>');
    });


    $('.contenedor').addClass('show');
});	
    