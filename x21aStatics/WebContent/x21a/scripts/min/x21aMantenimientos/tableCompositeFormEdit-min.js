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
jQuery(function(a){a("#table").rup_table({url:"../multipk",colNames:["ida","idb","nombre","apellido1","apellido2"],colModel:[{name:"ida",label:"ida",index:"ida",width:"150",editable:true,edittype:"text"},{name:"idb",label:"idb",index:"idb",width:"150",editable:true,edittype:"text"},{name:"nombre",label:"nombre",index:"nombre",width:"150",editable:true,edittype:"text"},{name:"apellido1",label:"apellido1",index:"apellido1",width:"150",editable:true,edittype:"text"},{name:"apellido2",label:"apellido2",index:"apellido2",width:"150",editable:true,edittype:"text"}],primaryKey:["ida","idb"],usePlugins:["formEdit","feedback","toolbar","contextMenu","fluid","filter","search","multiselection"],editOptions:{fillDataMethod:"clientSide"},sortname:"ida",formEdit:{validate:{rules:{nombre:{required:true},apellido1:{required:true}}}}});a("#ejie_search").rup_combo({source:[{i18nCaption:"0",value:"0"},{i18nCaption:"1",value:"1"}],i18nId:"GRID_simple##ejie",width:120,blank:""});a("#fechaAlta_search").rup_date();a("#fechaBaja_search").rup_date()});