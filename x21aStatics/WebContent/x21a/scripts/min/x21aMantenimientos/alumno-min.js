jQuery(function(a){a("#alumno").rup_table({url:"../alumno",colNames:[a.rup.i18n.app.GRID_alumno.id,a.rup.i18n.app.GRID_alumno.usuario,a.rup.i18n.app.GRID_alumno.nombre,a.rup.i18n.app.GRID_alumno.dni,a.rup.i18n.app.GRID_alumno.importeMatricula,"provincia","prueba"],colModel:[{name:"id",label:"id",index:"id",width:"150",hidden:true,},{name:"usuario",label:"usuario",index:"usuario",width:"150",},{name:"nombreCompleto",label:"nombreCompleto",index:"apellido1",width:"150",formatter:function(e,d,b,c){return b.apellido1+" "+b.apellido2+", "+b.nombre},formatterOnUpdate:function(b){return a("#apellido1").val()+" "+a("#apellido2").val()+", "+a("#nombre").val()}},{name:"dni",label:"dni",index:"dni",width:"150",},{name:"importeMatricula",label:"importeMatricula",index:"importe_matricula",width:"150",formatter:"currency",formatoptions:{decimalSeparator:",",thousandsSeparator:",",decimalPlaces:2,suffix:" € "}},{name:"municipio.dsO",label:"municipio.dsO",index:"municipio.dsO",width:"150",sortable:false,updateFromDetail:function(b){return a("#municipio").rup_combo("label")},},{name:"campo.valor",sortable:false,label:"campo.valor",index:"campo.valor"}],primaryKey:["id"],usePlugins:["formEdit","feedback","toolbar","contextMenu","fluid","filter","search"],rowNum:10,rowList:[10,20,30],sortname:"id",formEdit:{addEditOptions:{width:1100},detailForm:"#alumno_detail_div",validate:{messages:{password_confirm:a.rup.i18n.app.alumno.password_confirm,email_confirm:a.rup.i18n.app.alumno.email_confirm},rules:{nombre:{required:true},apellido1:{required:true},usuario:{required:true},password:{required:function(){if(a("#divOldPassword").is(":hidden")){return true}else{if(a("#oldPassword").val()!==""){return true}else{return false}}}},importeMatricula:{number:true},password_confirm:{equalTo:"#password"},dni:{required:true,dni:true},email:{email:true},email_confirm:{equalTo:"#email"},pais:{required:true},autonomia:{required:true},provincia:{required:true}}},},filter:{validate:{rules:{dni:{dni:true},}}},search:{validate:{rules:{dni:{dni:true},}}}}).on({rupTable_afterFormFillDataServerSide:function(c,e,f,b){var d=a(this);if(d.data("settings").opermode==="edit"){if(e.nombreImagen!==undefined&&e.nombreImagen!==null&&e.imagen!==""){a("#imagen").attr("src","http://desarrollo.jakina.ejiedes.net:7001/x21aMantenimientosWar/administracion/alumno/imagen/"+e.id+"?"+new Date())}else{a("#imagen").attr("src",a.rup.STATICS+"/x21a/images/no_picture.gif")}}a("#email_confirm").val(a("#email").val());d.rup_table("updateSavedData",function(g){g.imagenAlumno="";g.oldPassword="";g.password="";g.password_confirm="";g.email_confirm=a("#email_confirm").val();g["calle.id_label"]=""})},jqGridAddEditBeforeShowForm:function(d,b,c){var e=a(this);if(c==="edit"){a("#divOldPassword").show();if(a("#pais").rup_combo("getRupValue")==="108"){a("#divDireccionExtranjera").hide();a("#divDireccionPais").show();a("#divAutonomia").show()}else{a("#divDireccionExtranjera").show();a("#divDireccionPais").hide();a("#divAutonomia").hide()}}else{a("#pais").rup_combo("select","108");a("#divDireccionExtranjera").hide();a("#divDireccionPais").show();a("#divOldPassword").hide();a("#imagen").attr("src",a.rup.STATICS+"/x21a/images/no_picture.gif")}},jqGridAddEditAfterSubmit:function(d,e,c,b){c=a.rup_utils.unnestjson(c)}});a("#sexo").rup_combo({source:[{i18nCaption:"masculino",value:"M"},{i18nCaption:"femenino",value:"F"}],i18nId:"sexo"});jQuery("#pais").rup_combo({source:"../../nora/pais",sourceParam:{label:"dsO",value:"id"},blank:"",width:400,loadFromSelect:true,change:function(c,b){if(b.value==="108"){a("#divDireccionExtranjera").hide();a("#divDireccionPais").show();a("#divAutonomia").show()}else{a("#divDireccionExtranjera").show();a("#divDireccionPais").hide();a("#divAutonomia").hide()}}});jQuery("#autonomia").rup_combo({source:"../../nora/autonomia",sourceParam:{label:"dsO",value:"id"},width:400,blank:"",loadFromSelect:true});jQuery("#provincia").rup_combo({parent:["autonomia"],source:"../../nora/provincia",firstLoad:[{value:"01",label:"Alava/Araba"},{value:"20",label:"Gipuzkoa"},{value:"48",label:"Bizkaia"}],sourceParam:{label:"dsO",value:"id"},width:400,blank:""});jQuery("#municipio").rup_combo({source:"../../nora/municipio",sourceParam:{label:"dsO",value:"id"},parent:["provincia"],width:400,blank:"",select:function(){a("#municipio_dsO").val(jQuery("#municipio").rup_combo("label"))}});jQuery("#calle").rup_autocomplete({source:"../../nora/calle",sourceParam:{label:"dsO",value:"id"},minLength:4});a("#fechaNacimiento").rup_date()});