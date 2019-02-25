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
jQuery(document).ready(function(){$("#spinner").rup_spinner();$("#disable").click(function(){if($("#spinner").spinner("option","disabled")){$("#spinner").spinner("enable")}else{$("#spinner").spinner("disable")}});$("#destroy").click(function(){if($("#spinner").spinner("instance")){$("#spinner").spinner("destroy")}else{$("#spinner").rup_spinner()}});$("#getvalue").click(function(){alert($("#spinner").rup_spinner("getRupValue"))});$("#setvalue").click(function(){$("#spinner").rup_spinner("setRupValue",5)});$("#button").button()});