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
var tableColNames=[$.rup.i18n.app.table.id,"",$.rup.i18n.app.table.nombre,$.rup.i18n.app.table.apellido1,$.rup.i18n.app.table.apellido2,$.rup.i18n.app.table.ejie,$.rup.i18n.app.table.fechaAlta,$.rup.i18n.app.table.fechaBaja,$.rup.i18n.app.table.rol],tableColModels=[{name:"id",index:"id",hidden:true},{name:"idPadre",index:"idPadre",hidden:true},{name:"nombre",index:"nombre"},{name:"apellido1",index:"apellido1",classes:"ui-ellipsis"},{name:"apellido2",index:"apellido2",classes:"ui-ellipsis"},{name:"ejie",index:"ejie",edittype:"checkbox",formatter:"checkbox",align:"center",editoptions:{value:"1:0"},searchoptions:{rupType:"combo",source:[{label:"---",value:""},{label:"Si",value:"1"},{label:"No",value:"0"}]}},{name:"fechaAlta",index:"fechaAlta",editoptions:{labelMaskId:"fecha-mask",showButtonPanel:true,showOtherMonths:true,noWeekend:true}},{name:"fechaBaja",index:"fechaBaja",editoptions:{labelMaskId:"fecha-mask",showButtonPanel:true,showOtherMonths:true,noWeekend:true}},{name:"rol",index:"rol",editoptions:{source:[{label:"---",value:""},{label:$.rup.i18n.app["GRID_simple##rol"]["administrador"],value:"administrador"},{label:$.rup.i18n.app["GRID_simple##rol"]["desarrollador"],value:"desarrollador"},{label:$.rup.i18n.app["GRID_simple##rol"]["espectador"],value:"espectador"},{label:$.rup.i18n.app["GRID_simple##rol"]["informador"],value:"informador"},{label:$.rup.i18n.app["GRID_simple##rol"]["manager"],value:"manager"}]}}],options_ejie_combo={source:[{label:"---",value:""},{i18nCaption:"0",value:"0"},{i18nCaption:"1",value:"1"}],i18nId:"GRID_simple##ejie",width:120},options_role_combo={source:[{label:"---",value:""},{label:$.rup.i18n.app["GRID_simple##rol"]["administrador"],value:"administrador"},{label:$.rup.i18n.app["GRID_simple##rol"]["desarrollador"],value:"desarrollador"},{label:$.rup.i18n.app["GRID_simple##rol"]["espectador"],value:"espectador"},{label:$.rup.i18n.app["GRID_simple##rol"]["informador"],value:"informador"},{label:$.rup.i18n.app["GRID_simple##rol"]["manager"],value:"manager"}]};jQuery("#ejie_filter_table").rup_combo(options_ejie_combo);jQuery("#rol_filter_table").rup_combo(options_role_combo);jQuery("#fechaAlta_filter_table").rup_date();jQuery("#fechaBaja_filter_table").rup_date();jQuery("#fechaAlta_detail_table").rup_date();jQuery("#fechaBaja_detail_table").rup_date();jQuery("#rol_detail_table").rup_combo(options_role_combo);