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
jQuery(document).ready(function(){$("#hora").rup_time({labelMaskId:"hora-mask",showSecond:true,timeFormat:"hh:mm:ss",showButtonPanel:true});$("#horaPlaceholder").rup_time({placeholderMask:true,showSecond:true,timeFormat:"hh:mm:ss",showButtonPanel:true});$("#hora2").rup_time({placeholderMask:true,showTime:false,ampm:true,hour:8,minute:30,hourMin:8,hourMax:18,stepHour:2,stepMinute:10});$("#hora_inline").rup_time({hourGrid:5,minuteGrid:10});$("#hora_inline").rup_time("setTime",new Date())});