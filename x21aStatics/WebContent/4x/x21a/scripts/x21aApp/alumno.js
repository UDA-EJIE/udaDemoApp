jQuery(function ($) {


	$("#alumno").rup_jqtable({

		url: "../alumno",
		colNames: [
			$.rup.i18n.app.GRID_alumno.id,
			$.rup.i18n.app.GRID_alumno.usuario,
			$.rup.i18n.app.GRID_alumno.nombre,
			$.rup.i18n.app.GRID_alumno.dni,
			$.rup.i18n.app.GRID_alumno.importeMatricula,
			"provincia",
			"prueba"
		],
		colModel: [{
				name: "id",
				label: "id",
				index: "id",
				width: "150",
				hidden: true,
			},
			{
				name: "usuario",
				label: "usuario",
				index: "usuario",
				width: "150",
			},
			{
				name: "nombreCompleto",
				label: "nombreCompleto",
				index: "apellido1",
				width: "150",
				formatter: function (cellval, opts, rwd, act) {
					return rwd.apellido1 + " " + rwd.apellido2 + ", " + rwd.nombre;
				},
				formatterOnUpdate: function ($form) {
					return $("#apellido1").val() + " " + $("#apellido2").val() + ", " + $("#nombre").val();
				}
			},
			{
				name: "dni",
				label: "dni",
				index: "dni",
				width: "150",
			},
			{
				name: "importeMatricula",
				label: "importeMatricula",
				index: "importe_matricula",
				width: "150",
				formatter: 'currency',
				formatoptions: {
					decimalSeparator: ",",
					thousandsSeparator: ",",
					decimalPlaces: 2,
					suffix: " € "
				}
			},
			{
				name: "municipio.dsO",
				label: "municipio.dsO",
				index: "municipio.dsO",
				width: "150",
				sortable: false,
				updateFromDetail: function ($form) {
					return $("#municipio").rup_combo("label");
				},
			},
			{
				name: "campo.valor",
				sortable: false,
				label: "campo.valor",
				index: "campo.valor"
			}
		],
		primaryKey: ["id"],
		usePlugins: [
			"formEdit",
			"feedback",
			"toolbar",
			"contextMenu",
			"fluid",
			"filter",
			"search"
		],
		rowNum: 10,
		rowList: [10, 20, 30],
		sortname: 'id',
		formEdit: {
			addEditOptions: {
				width: 1100
			},
			detailForm: "#alumno_detail_div",
			validate: {
				messages: {
					"password_confirm": $.rup.i18n.app.alumno.password_confirm,
					"email_confirm": $.rup.i18n.app.alumno.email_confirm
				},
				rules: {
					"nombre": {
						required: true
					},
					"apellido1": {
						required: true
					},
					"usuario": {
						required: true
					},
					"password": {
						required: function () {
							if ($("#divOldPassword").is(":hidden")) {
								return true;
							} else {
								if ($("#oldPassword").val() !== '') {
									return true;
								} else {
									return false;
								}
							}
						}
					},
					"importeMatricula": {
						number: true
					},
					"password_confirm": {
						equalTo: "#password"
					},
					"dni": {
						required: true,
						dni: true
					},
					"email": {
						email: true
					},
					"email_confirm": {
						equalTo: "#email"
					},
					"pais": {
						required: true
					},
					"autonomia": {
						required: true
					},
					"provincia": {
						required: true
					}
				}
			},
		},
		filter: {
			validate: {
				rules: {
					"dni": {
						dni: true
					},
				}
			}
		},
		search: {
			validate: {
				rules: {
					"dni": {
						dni: true
					},
				}
			}
		}

	}).on({
		"rupTable_afterFormFillDataServerSide": function (event, xhr, $detailFormToPopulate, ajaxOptions) {
			var $self = $(this);
			if ($self.data("settings").opermode === 'edit') {
				if (xhr.nombreImagen !== undefined && xhr.nombreImagen !== null && xhr.imagen !== '') {
					$("#imagen").attr("src", "http://desarrollo.jakina.ejiedes.net:7001/x21aMantenimientosWar/administracion/alumno/imagen/" + xhr.id + "?" + new Date());
				} else {
					$("#imagen").attr("src", $.rup.STATICS + "/x21a/images/no_picture.gif");
				}
			}

			$("#email_confirm").val($("#email").val());

			$self.rup_jqtable("updateSavedData", function (savedData) {
				savedData.imagenAlumno = '';
				savedData.oldPassword = '';
				savedData.password = '';
				savedData.password_confirm = '';
				savedData.email_confirm = $("#email_confirm").val();
				savedData.calle.id_label = '';
			});

		},
		"jqGridAddEditBeforeShowForm": function (event, $form, frmoper) {
			var $self = $(this);
			if (frmoper === 'edit') {
				$("#divOldPassword").show();

				if ($("#pais").rup_combo("getRupValue") === "108") {
					$("#divDireccionExtranjera").hide();
					$("#divDireccionPais").show();
					$("#divAutonomia").show();
				} else {
					$("#divDireccionExtranjera").show();
					$("#divDireccionPais").hide();
					$("#divAutonomia").hide();
				}
			} else {
				$("#pais").rup_combo("select", "108");
				$("#divDireccionExtranjera").hide();
				$("#divDireccionPais").show();
				$("#divOldPassword").hide();
				$("#imagen").attr("src", $.rup.STATICS + "/x21a/images/no_picture.gif");
			}


		},
		"jqGridAddEditAfterSubmit": function (event, data, postdata, frmoper) {
			postdata = $.rup_utils.unnestjson(postdata);
		}

	});




	$('#sexo').rup_combo({
		source: [{
				i18nCaption: "masculino",
				value: "M"
			},
			{
				i18nCaption: "femenino",
				value: "F"
			}
		],
		i18nId: "sexo"
	});

	jQuery("#pais").rup_combo({
		source: "../../nora/pais",
		sourceParam: {
			label: "dsO",
			value: "id"
		},
		blank: "",
		width: 400,
		loadFromSelect: true,
		change: function (event, elem) {
			if (elem.value === "108") {
				$("#divDireccionExtranjera").hide();
				$("#divDireccionPais").show();
				$("#divAutonomia").show();
			} else {
				$("#divDireccionExtranjera").show();
				$("#divDireccionPais").hide();
				$("#divAutonomia").hide();
			}
		}

	});

	jQuery("#autonomia").rup_combo({
		source: "../../nora/autonomia",
		sourceParam: {
			label: "dsO",
			value: "id"
		},
		width: 400,
		blank: "",
		loadFromSelect: true
	});

	jQuery("#provincia").rup_combo({
		parent: ["autonomia"],
		source: "../../nora/provincia",
		firstLoad: [{
			"value": "01",
			"label": "Alava/Araba"
		}, {
			"value": "20",
			"label": "Gipuzkoa"
		}, {
			"value": "48",
			"label": "Bizkaia"
		}],
		sourceParam: {
			label: "dsO",
			value: "id"
		},
		width: 400,
		blank: ""
	});

	jQuery("#municipio").rup_combo({
		source: "../../nora/municipio",
		sourceParam: {
			label: "dsO",
			value: "id"
		},
		parent: ["provincia"],
		width: 400,
		blank: "",
		select: function () {
			$('#municipio_dsO').val(jQuery("#municipio").rup_combo('label'));
		}
	});

	jQuery("#calle").rup_autocomplete({
		source: "../../nora/calle",
		sourceParam: {
			label: "dsO",
			value: "id"
		},
		minLength: 4
	});

	$("#fechaNacimiento").rup_date();

});