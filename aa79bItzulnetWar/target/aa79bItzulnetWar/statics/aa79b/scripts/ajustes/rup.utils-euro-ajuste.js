$.extend($.rup_utils, {
		
		populateForm: function populateForm(aData, formid) {
			//rellena un formulario que recibe como segundo parametro con los datos que recibe en el segundo parametro
			var ruptype, formElem;
			var tree_data, selectorArray;
			if (aData) {

				for (var i in aData) {
					tree_data = new Array();
					formElem = $('[name=\'' + i + '\']', formid);
					if (formElem.length == 0) {
						selectorArray = i.substr(0, i.indexOf('['));
						formElem = $('[name=\'' + selectorArray + '\']', formid);
					}
					if (formElem.is('[ruptype]')) {
						if (formElem.hasClass('jstree')) {

							for (var a in aData) {
								if (a.substr(0, a.indexOf('[')) == selectorArray) {
									tree_data.push(aData[a]);
								}
							}
							formElem['rup_' + formElem.attr('ruptype')]('setRupValue', tree_data);
							var $arbol = [];
							$arbol[selectorArray] = tree_data;
							formElem.on('loaded.jstree', function (event, data) {
								var selectorArbol = this.id;
								//$(this).rup_tree("setRupValue",$arbol[selectorArbol]);

								$(this).trigger('rup_filter_treeLoaded', $arbol[selectorArbol]);
							});
						} else { 
							// Forma de evitar el EVAL
							if (formElem.hasClass('rup_combo') && formElem.attr("multiple")) {
								formElem['rup_' + formElem.attr('ruptype')]( 'clear' );
								formElem['rup_' + formElem.attr('ruptype')]('setRupValue', aData[i].split(','));
							}else{
								formElem['rup_' + formElem.attr('ruptype')]('setRupValue', aData[i]);
							}
						}
					} else if (formElem.is('input:radio') || formElem.is('input:checkbox')) {
						formElem.each(function () {
							if ($(this).val() == aData[i]) {
								$(this).attr('checked', 'checked');
							} else {
								$(this).removeAttr('checked');
							}
						});
					} else if (formElem.is('select')) {
						formElem.val(aData[i]).click();
					} else if (formElem.is(':not(img)')) {
						// this is very slow on big table and form.
						formElem.val(aData[i]);
					}
				}
			}
		}
	});