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
jQuery(function(a){a("#table").rup_table({url:"../jqGridUsuario",colNames:tableColNames,colModel:tableColModels,multiboxonly:true,model:"Usuario",usePlugins:["formEdit","feedback","toolbar","contextMenu","fluid","filter","search","multiselection","report","multifilter"],primaryKey:["id"],sortname:"id",formEdit:{detailForm:"#table_detail_div",validate:{rules:{nombre:{required:true},apellido1:{required:true},fechaAlta:{date:true},fechaBaja:{date:true}}}},multiselection:{},filter:{validate:{rules:{fechaAlta:{date:true},fechaBaja:{date:true}}}},multifilter:{idFilter:"formEditMulti",labelSize:255},report:options_table_report})});