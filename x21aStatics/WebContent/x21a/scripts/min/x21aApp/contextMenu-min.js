/*
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
jQuery(document).ready(function(){var a={edit:{name:"Edit",icon:"edit"},cut:{name:"Cut",icon:"cut"},copy:{name:"Copy",icon:"copy"},paste:{name:"Paste",icon:"paste"},"delete":{name:"Delete",icon:"delete"},sep1:"---------",quit:{name:"Quit",icon:"quit"}};$("#contextMenu").rup_contextMenu({callback:function(c,b){alert("clicked: "+c)},items:{edit:{name:"Clickable",icon:"edit",disabled:false},cut:{name:"Disabled",icon:"cut",disabled:true}}});$(".contextMenu-left").rup_contextMenu({trigger:"left",callback:function(c,b){alert("clicked: "+c)},items:{edit:{name:"Edit",icon:"edit",accesskey:"e"},cut:{name:"Cut",icon:"cut",accesskey:"c"},copy:{name:"Copy",icon:"copy",accesskey:"c o p y"},paste:{name:"Paste",icon:"paste",accesskey:"cool paste"},"delete":{name:"Delete",icon:"delete"},sep1:"---------",quit:{name:"Quit",icon:"quit"}}});$("#contextMenu-hover").rup_contextMenu({trigger:"hover",callback:function(c,b){alert("clicked: "+c)},items:{edit:{name:"Edit",icon:"edit"},cut:{name:"Cut",icon:"cut"},sep1:"---------",quit:{name:"Quit",icon:"quit"},sep2:"---------",fold1:{name:"Sub group",items:{"foo bar":{name:"Foo bar"},fold2:{name:"Sub group 2",items:{alpha:{name:"alpha"},bravo:{name:"bravo"},charlie:{name:"charlie"}}},delta:{name:"delta"}}},fold1a:{name:"Other group",items:{echo:{name:"echo"},foxtrot:{name:"foxtrot"},golf:{name:"golf"}}}}});$(".contextMenu-other").rup_contextMenu({trigger:"none",build:function(b,c){return{callback:function(e,d){alert("clicked: "+e)},items:{edit:{name:"Edit",icon:"edit"},cut:{name:"Cut",icon:"cut"},copy:{name:"Copy",icon:"copy"},paste:{name:"Paste",icon:"paste"},"delete":{name:"Delete",icon:"delete"},sep1:"---------",quit:{name:"Quit",icon:"quit"}}}}});$("#activate-menu").on("click",function(b){$(".contextMenu-other").rup_contextMenu("show")})});