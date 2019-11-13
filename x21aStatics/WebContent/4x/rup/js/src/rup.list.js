/* eslint-env jquery,amd */
/*!
 * @author Lander Laparra
 */

/**
 * Presenta los elementos que presenta una tabla rup_table en formato listado. Pensado para movilidad.
 *
 * @summary Componente RUP List.
 * @module rup_list
 * @example
 * $('#rup-list').rup_list({
 *      action: '/demo/list/filter',
 *      filterForm: 'listFilterForm',
 *      feedback: 'rup-list-feedback',
 *      visiblePages: 3,
 *      key: 'codigoPK',
 *      selectable: {
 *          multi: true,
 *          selector: '.list-item'
 *      },
 *      sidx: {
 *          source: [{
 *              value: 'USUARIO',
 *              i18nCaption: 'Usuario'
 *          }, {
 *              value: 'EDAD',
 *              i18nCaption: 'Edad'
 *          }, {
 *              value: 'CODCLIENTE',
 *              i18nCaption: 'Codigo cliente'
 *          }],
 *          value: 'EDAD,USUARIO'
 *      },
 *      rowNum: {
 *          source: [{
 *              value: '5',
 *              i18nCaption: 'Cinco'
 *          }, {
 *              value: '10',
 *              i18nCaption: 'Diez'
 *          }, {
 *              value: '20',
 *              i18nCaption: 'Veinte'
 *          }],
 *          value: '5'
 *      },
 *      isMultiSort: true,
 *      modElement: (ev, item, json) => {
 *          var userVal = item.find('#usuario_value_' + json.codigoPK);
 *          userVal.text(userVal.text() + ' -Added');
 *      },
 *      load: () => {}
 * });
 */
(function (factory) {
    if (typeof define === 'function' && define.amd) {

        // AMD. Register as an anonymous module.
        define(['jquery', 'rup.base', 'rup.button'], factory);
    } else {

        // Browser globals
        factory(jQuery);
    }
}(function ($) {
    //*******************************************************
    // DEFINICIÓN DE LA CONFIGURACION POR DEFECTO DEL PATRON
    //*******************************************************

    /**
     * @description Opciones por defecto de configuración del componente.
     * @name defaults
     * @property {String} [action=null] - Determina la url contra la que se hacen las llamadas del listado.
     * @property {String} [filterForm=null] - Determina el selector del formulario de filtrado del listado.
     * @property {String} [feedback=null] - Determina el selector del feedback.
     * @property {Number} [visiblePages=5] - Determina el número de páginas que serán visibles desde la paginación (mínimo 3).
     * @property {String} [key=null] - Determina el identificador de cada tarjeta que vendrá especificado en el JSON.
     * @property {Object} [rowNum=Object] - Determina la configuracion de la seleccion de elementos por página.
     * @property {Object} [sidx = Object] - Determina los campos por los que se podrán ordenar los elementos
     * @property {String} [sord=null] - Determina la dirección de la ordenación
     * @property {Number} [page=1] - Determina página en la que se inicia por defecto
     * @property {boolean} [createFooter=true] - Si es true crea una copia del header en la parte inferior del listado
     * @property {String} [sord=null] - Determina la dirección de la ordenación
     * @property {Funcion} [modElement=() =>{}] - Callback que se ejecuta antes del añadido de cada tarjeta al listado
     * @property {Funcion} [load=() => {}] - Callback que se ejecuta tras cada filtrado
     * @property {Object} [selectable=Object] - Determina la configuración de la selección
     * @property {boolean} [isMultiSort=false] - Si es true el modo de ordenación cambia a multiordenación
     * @property {boolean} [isScrollList=false] - Si es true quita la paginación a favor de una carga dinámica de las tarjetas
     *
     */

    /**
    * @description Opciones por defecto de configuración de rowNum
    * @name defaultsRowNum
    * 
    * @property {Array} [sorce=Array] - Es un array de objetos con las propiedades value e i18nCaption que serán los elementos disponibles para la seleccion de los elementos por página
    * @property {String} [value=5] - Valor del source por defecto 
    */

    /**
    * @description Opciones por defecto de configuración de sidx
    * @name defaultsSidx
    * 
    * @property {Array} [sorce=Array] - Es un array de objetos con las propiedades value e i18nCaption que serán los elementos disponibles para la seleccion de la ordenacion.
    * @property {String} [value=5] - Valor del source por defecto. En caso de ser multiOrdenacion se pueden añadir campos separados por comas
    */

    /**
    * @description Opciones por defecto de configuración de selectable
    * @name defaultsSelectable
    * 
    * @property {boolean} [multi=null] - Si es true será de selección múltiple, de ser false será de seleccion simple.
    * @property {selector} [value=null] - Selctor JQuery sobre el que se deberá hacer click para seleccionar o deseleccionar elementos.
    */

    /**
    * @description Eventos lanzados sobre rup-list
    * @name defaultsEvents
    * 
    * @property [initComplete] - Se lanza una vez el componente ha sido inicializado.
    * @property [listAfterMultiselection] - Se lanza tras finalizar operaciones de multiseleccion desde el desplegable.
     * @property [rup_list-mord-inited] - Se lanza una vez se ha inicializado la característica de multiorder
     * @property [rup_list-mord-changed] - Se lanza cuando se vería la multiordenación
    */

    $.widget('$.rup_list', {
        options: {
            filterForm: null,
            action: null,
            feedback: null,
            key: null,
            colNames: {
                source: [],
                value: null
            },
            rowNum: {
                source: [{
                    value: '5',
                    i18nCaption: '5'
                }, {
                    value: '10',
                    i18nCaption: '10'
                }, {
                    value: '20',
                    i18nCaption: '20'
                }, {
                    value: '30',
                    i18nCaption: '30'
                }],
                value: '5'
            },
            page: 1,
            sidx: {
                source: [],
                value: null
            },
            sord: 'asc',
            visiblePages: 5,
            createFooter: true,
            modElement: () => {},
            load: function () {}
        },

        /**
         * Método interno para cambiar el valor de algunas opciones
         * 
         * @name _changeOption
         * @function
         * @param {String} key 
         * @param {*} value 
         */
        _changeOption: function (key, value) {
            var opciones = this.options;
            switch (key) {
            case 'rowNum':
                opciones.rowNum.value = value;
                opciones.page = 1;
                this.reload();
                break;
            case 'page':
                opciones.page = value;
                this.reload();
                break;
            case 'sidx':
                opciones.sidx.value = value;
                opciones.page = 1;
                this.reload();
                break;
            case 'sord':
                opciones.sord = value;
                opciones.page = 1;
                this.reload();
                break;
            }
        },

        /**
         * Método interno que valida que el esqueleto html es válido para el componente
         * @name _validateSkeleton
         * @function
         */
        _validateSkeleton: function () {
            var id = this.element.attr('id');
            if (this.options.selectable && this.options.selectable.multi) {
                return $('#' + id + '-header-selectables').length;
            }
            return $('#' + id + '-content').length &&
                $('#' + id + '-header').length &&
                $('select').filter(function () {
                    return this.id == (id + '-header-rowNum');
                }).length &&
                $('select').filter(function () {
                    return this.id == (id + '-header-sidx');
                }).length &&
                $('button, a').filter(function () {
                    return this.id == (id + '-header-sord');
                }).length &&
                $('nav, div').filter(function () {
                    return this.id == (id + '-header-nav');
                }).length &&
                $('#' + id + '-itemTemplate').length;
        },

        /**
         * Método interno que configura el componente
         * @name _create
         * @function
         */
        _create: function () {
            global.initRupI18nPromise.then(() => {
                const self = this;
                const selfId = self.element.attr('id');

                if (!self._validateSkeleton()) {
                    $.rup_messages('msgAlert', {
                        title: 'Esqueleto no válido',
                        message: 'El esqueleto HTML sobre el que montar el listado no es correcto'
                    });
                    return;
                }

                var opciones = self.options;

                $(self.element).addClass('rup_list');

                // Si el número de páginas visibles se ha definido menor que 3 se eleva a 3 que es el mínimo
                opciones.visiblePages = opciones.visiblePages < 3 ? 3 : opciones.visiblePages;

                /**
                 * CONTENT
                 */
                opciones._idContent = selfId + '-content';
                opciones._content = $('#' + opciones._idContent);
                opciones._content.addClass('rup_list-content');
                opciones._content.hide();

                /**
                 * OVERLAY (Lock & Unlock)
                 */
                opciones._idOverlay = selfId + '-overlay';
                opciones._overlay = jQuery('<div id="' + opciones._idOverlay + '" class="rup_list-overlay"/>');
                opciones._overlay
                    .append('<div class="rup_list-overlay-layer"/>')
                    .append('<div class="rup_list-overlay-loader"/>');
                opciones._overlay.addClass('rup_list-overlay');

                /**
                 * FEEDBACK
                 */
                opciones.feedback = $('#' + opciones.feedback).rup_feedback({
                    gotoTop: false
                });
                opciones.feedback.addClass('rup_list-feedback');

                /**
                 * TEMPLATE
                 */
                opciones._idItemTemplate = selfId + '-itemTemplate';
                opciones._itemTemplate = $('#' + opciones._idItemTemplate);
                opciones._itemTemplate.addClass('rup_list-itemTemplate');

                /**
                 * HEADER & FOOTER
                 */
                var populateHeaderfooterOpts = (isFooter) => {
                    let idObj = isFooter ? '_idListFooter' : '_idListHeader';
                    let obj = isFooter ? '_footer' : '_header';
                    let label = isFooter ? '-footer' : '-header';

                    // HEADER ELEMENTS IDs MAP
                    opciones[idObj] = {};
                    opciones[idObj].multiSort = {};
                    opciones[idObj].header = selfId + label;
                    opciones[idObj].pagenav = selfId + label + '-nav';
                    opciones[idObj].pagePrev = selfId + label + '-page-prev';
                    opciones[idObj].pageNext = selfId + label + '-page-next';
                    opciones[idObj].pagenav = selfId + label + '-nav';
                    opciones[idObj].rowNum = selfId + label + '-rowNum';
                    opciones[idObj].sidx = selfId + label + '-sidx';
                    opciones[idObj].sord = selfId + label + '-sord';
                    opciones[idObj].selectables = selfId + label + '-selectables';
                    // HEADER $OBJECTS MAP
                    opciones[obj] = {};
                    opciones[obj].obj = $('#' + opciones[idObj].header);
                    opciones[obj].multiSort = {};
                    opciones[obj].pagenav = $('#' + opciones[idObj].pagenav);
                    opciones[obj].pagePrev = $('#' + opciones[idObj].pagePrev);
                    opciones[obj].pageNext = $('#' + opciones[idObj].pageNext);
                    opciones[obj].rowNum = $('#' + opciones[idObj].rowNum);
                    opciones[obj].sidx = $('#' + opciones[idObj].sidx);
                    opciones[obj].sord = $('#' + opciones[idObj].sord);
                    opciones[obj].selectables = $('#' + opciones[idObj].selectables);
                    // HEADER $OBJECTS CLASS ASSIGNMENT
                    opciones[obj].obj.addClass('rup_list' + label);
                    opciones[obj].pagenav.addClass('rup_list' + label + '-nav');
                    opciones[obj].pagePrev.addClass('rup_list' + label + '-page-prev');
                    opciones[obj].pageNext.addClass('rup_list' + label + '-page-next');
                    opciones[obj].rowNum.addClass('rup_list' + label + '-rowNum');
                    opciones[obj].sidx.addClass('rup_list' + label + '-sidx');
                    opciones[obj].sord.addClass('rup_list' + label + '-sord');
                    opciones[obj].selectables.addClass('rup_list' + label + '-selectables');
                };
                populateHeaderfooterOpts();
                if (opciones.createFooter) {
                    var footerHTML = $('<div>').append(opciones._header.obj.clone()).html().replace(/header/g, 'footer');
                    $('#' + selfId).after(footerHTML);
                }
                populateHeaderfooterOpts(true);

                /**
                 * MULTISORT DIALOG
                 */


                /**
                 * ROWNUM
                 */ 
                self._rownumInit.apply(self);

                /**
                 * SORT - MULTISORT
                 */
                if (!opciones.isMultiSort) {
                    // Sidx select to rup-combo
                    self._sidxComboInit.apply(self);
                    // Inicialización sord
                    self._sordButtonInit(self);
                } else {
                    // Inicialización del multisort
                    self._multisortInit.apply(self);
                }

                // Asociación de eventos
                $('#' + self.element[0].id).on('load', opciones.load);
                $('#' + self.element[0].id).on('modElement', (e, item, json) => {
                    opciones.modElement(e, item, json);
                    if(opciones.isScrollList){
                        self.element.prepend(item);
                    } else {
                        self.element.append(item);
                    }
                });

                /**
                 * PAGENAV
                 */
                self._pagenavInit.apply(self);

                //Gestion de multiselección
                if (opciones.selectable) {
                    $(opciones.selectable.selector).attr('rup-list-selector', 'enabled');
                    $('[rup-list-selector="enabled"]').click(function (e) {
                        let clickedElemIdArr = e.currentTarget.id.split('_');
                        let clickedPK = clickedElemIdArr[clickedElemIdArr.length - 1];
                        if (opciones.multiselection.selectedIds == null) {
                            opciones.multiselection.selectedIds = [];
                        }
                        if (opciones.multiselection.selectedRowsPerPage == null) {
                            opciones.multiselection.selectedRowsPerPage = [];
                        }
                        let select = (clickedPK) => {
                            if (!opciones.selectable.multi) {
                                opciones.multiselection.selectedAll = false;
                                opciones.multiselection.selectedIds = [];
                                opciones.multiselection.selectedRowsPerPage = [];
                            }
                            opciones.multiselection.selectedRowsPerPage.push({
                                id: self.element[0].id + '-itemTemplate_' + clickedPK,
                                line: (function () {
                                    let cont = 0;
                                    let final = 0;
                                    self.element.children().toArray().forEach(element => {
                                        if (element.id == self.element.id + '-itemTemplate_' + clickedPK) {
                                            final = cont;
                                        }
                                        cont++;
                                    });
                                    return final;
                                })(),
                                page: opciones.page
                            });
                            opciones.multiselection.selectedIds.push(clickedPK);
                            $('#' + self.element[0].id + '-itemTemplate_' + clickedPK).addClass('list-item-selected');
                        };
                        let deselect = (clickedPK) => {
                            let index = opciones.multiselection.selectedIds.indexOf(clickedPK);
                            opciones.multiselection.selectedIds.splice(index, 1);
                            opciones.multiselection.selectedRowsPerPage = opciones.multiselection.selectedRowsPerPage.filter(elem =>
                                elem.id != self.element[0].id + '-itemTemplate_' + clickedPK
                            );
                            $('#' + self.element[0].id + '-itemTemplate_' + clickedPK).removeClass('list-item-selected');
                        };
                        let selectAll = (clickedPK) => {
                            deselect(clickedPK);
                            $('#' + self.element[0].id + '-itemTemplate_' + clickedPK).addClass('list-item-selected');
                        };
                        let deselectAll= (clickedPK) => {
                            select(clickedPK);
                            $('#' + self.element[0].id + '-itemTemplate_' + clickedPK).removeClass('list-item-selected');
                        };
                        if (opciones.multiselection.selectedIds.includes(clickedPK)) {
                            if(opciones.multiselection.selectedAll){
                                selectAll(clickedPK);
                            } else {
                                deselect(clickedPK);
                            }
                        } else {
                            if(opciones.multiselection.selectedAll){
                                deselectAll(clickedPK);
                            } else {
                                select(clickedPK);
                            }
                        }
                        if (opciones.multiselection.selectedIds.length == 0) {
                            opciones.multiselection.selectedIds = null;
                        }
                        if (opciones.multiselection.selectedRowsPerPage.length == 0) {
                            opciones.multiselection.selectedRowsPerPage = null;
                        }
                    });
                    opciones.multiselection = {
                        selectedIds: null,
                        selectedAll: false,
                        selectedRowsPerPage: null
                    };
                    self._generateSelectablesBtnGroup();
                }

                if(opciones.isScrollList){
                    self._scrollListInit();
                }

                $('#' + opciones._idItemTemplate).hide();
                if(opciones.isMultisort){
                    $('#' + self.element[0].id).on('rup_list-mord-inited', () => {
                        $('#' + self.element[0].id).trigger('initComplete');
                    });
                } else {
                    $('#' + self.element[0].id).trigger('initComplete');
                }
            });
        },

        /**
         * Método interno que crea el scrollList
         * @name _scrollListInit
         * @function
         */
        _scrollListInit: function() {
            let self = this;
            let opciones = self.options;

            opciones._header.pagenav.remove();
            opciones._footer.pagenav.remove();

            // TODO: Crear una lista invisible para el target <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

            let $scrollSignal = $('<div id="scrollSignal" ></div>');
            $(self.element).append($scrollSignal.clone());
        },

        /**
         * Método interno que configura el boton de alternar el sord en la ordenación simple
         * @name _sordButtonInit
         * @function
         */
        _sordButtonInit: function () {
            const self = this;
            const opciones = self.options;

            var sordH = opciones._header.sord.find('i');
            var sordF = opciones._footer.sord.find('i');
            if (opciones.sord === 'asc') {
                sordH.addClass('fa-sort-amount-asc');
                sordF.addClass('fa-sort-amount-asc');
                sordH.removeClass('fa-sort-amount-desc');
                sordF.removeClass('fa-sort-amount-desc');
            } else {
                sordH.addClass('fa-sort-amount-desc');
                sordF.addClass('fa-sort-amount-desc');
                sordH.removeClass('fa-sort-amount-asc');
                sordF.removeClass('fa-sort-amount-asc');
            }
            // Funcionamiento botón sord
            $('#' + opciones._idListHeader.sord + ', #' + opciones._idListFooter.sord).on('click', function () {
                sordH.toggleClass('fa-sort-amount-asc');
                sordH.toggleClass('fa-sort-amount-desc');
                sordF.toggleClass('fa-sort-amount-asc');
                sordF.toggleClass('fa-sort-amount-desc');
                self._changeOption('sord', sordH.hasClass('fa-sort-amount-asc') ? 'asc' : 'desc');
            });
        },

        /**
         * Método interno que configura el combo de seleccion de sidx en la ordenación simple
         * @name _sidxComboInit
         * @function
         */
        _sidxComboInit: function () {
            const self = this;
            const opciones = self.options;

            var sidxRupConf = {
                source: opciones.sidx.source,
                width: 'initial',
                selected: opciones.sidx.value,
                rowStriping: true,
                ordered: false,
                change: function () {
                    if (!$('#' + this.id).rup_combo('isDisabled')) {
                        opciones._header.sidx.rup_combo('disable');
                        opciones._footer.sidx.rup_combo('disable');
                        opciones._header.sidx.rup_combo('setRupValue', $('#' + this.id).rup_combo('getRupValue'));
                        opciones._footer.sidx.rup_combo('setRupValue', $('#' + this.id).rup_combo('getRupValue'));
                        self._changeOption('sidx', $('#' + this.id).rup_combo('getRupValue'));
                        opciones._header.sidx.rup_combo('enable');
                        opciones._footer.sidx.rup_combo('enable');
                    }
                }
            };
            opciones._header.sidx.rup_combo(sidxRupConf);
            opciones._header.sidx = $('#' + opciones._idListHeader.sidx);
            opciones._footer.sidx.rup_combo(sidxRupConf);
            opciones._footer.sidx = $('#' + opciones._idListFooter.sidx);
        },

        /**
         * Método interno que configura los elementos de la multiordenación.
         * @name _multisortInit
         * @function
         */
        _multisortInit: function () {
            const self = this;
            const opciones = self.options;
            const selfId = self.element.attr('id');

            // Creamos un apartado en opciones
            opciones.multiorder = {
                sidx: opciones.sidx.value,
                sord: (() => {
                    if(opciones.sord){
                        let sordArr = opciones.sord.split(',');
                        let sidxArr = opciones.sidx.value.split(',');
                        if(sordArr.length == sidxArr.length){
                            return opciones.sord;
                        }
                        if(sordArr.length > sidxArr.length){
                            return sordArr.splice(0, sidxArr.length).join(',');
                        }
                        if(sordArr.length < sidxArr.length){
                            let diff = sidxArr.length - sordArr.length;
                            for(let i = 0; i<diff; i++) {
                                sordArr.push('asc');
                            }
                            return sordArr.join(',');
                        }
                    } else {
                        let sordArr = [];
                        for(let i = 0; i < opciones.sidx.value.split(',').length; i++) {
                            sordArr.push('asc');
                        }
                        return sordArr.join(',');
                    }
                })()
            };

            let doInit = (isFooter) => {
                let idObj = isFooter ? '_idListFooter' : '_idListHeader';
                let obj = isFooter ? '_footer' : '_header';
                let label = isFooter ? '-footer-' : '-header-';

                opciones[idObj].multiSort.dialog = selfId + label + 'mord-dialog';
                opciones[idObj].multiSort.summary = selfId + label + 'mord-summary';
                opciones[idObj].multiSort.edit = selfId + label + 'mord-edit';

                // Generamos un span para el resumen
                let $spanResumen = $('<ul id="' + opciones[idObj].multiSort.summary + '" class="rup_list-mord-summary p-0"/>');
                let $tmpWrapSummary = $('<div class="tmp-orderchange"/>');
                opciones[obj].sidx.wrap($tmpWrapSummary);
                $tmpWrapSummary = opciones[obj].sidx.parent();
                $tmpWrapSummary.children().remove();
                $tmpWrapSummary.append($spanResumen);
                $spanResumen.unwrap();
                opciones[obj].multiSort.summary = $('#' + opciones[idObj].multiSort.summary);

                // Se rellena el resumen con el order por defecto
                opciones.multiorder.sidx.split(',').map((e) => {
                    return e.trim();
                }).forEach((e, i) => {
                    let $tmpSum = $('<li class="rup_list-mord-summary-badge badge badge-pill badge-primary rounded-0 mr-1"/>');
                    let geti18n = (val) => {
                        let srcVal = opciones.sidx.source.filter(x => x.value == val);
                        return srcVal[0].i18nCaption;
                    };
                    let sordBadge = $('<span class="rup_list-mord-summary-badge-sord"/>');
                    sordBadge.text(' ');
                    let arrSord = opciones.multiorder.sord.split(',').map((e) => {
                        return e.trim();
                    });
                    if (arrSord[i] == 'asc') {
                        sordBadge.addClass('mdi mdi-chevron-up');
                    } else {
                        sordBadge.addClass('mdi mdi-chevron-down');
                    }
                    $tmpSum.append(geti18n(e)).append(sordBadge.clone());
                    $spanResumen.append($tmpSum.clone());
                });

                // Creamos el botón para el dialogo
                var $btnOrderDialog = $('<button id="' + opciones[idObj].multiSort.edit + '" class="rup_list-mord-dialogbtn mdi mdi-pencil"/>');
                let $tmpWrapEditMord = $('<div class="tmp-orderchange"/>');
                opciones[obj].sord.wrap($tmpWrapEditMord);
                $tmpWrapEditMord = opciones[obj].sord.parent();
                $tmpWrapEditMord.children().remove();
                $tmpWrapEditMord.append($btnOrderDialog);
                $btnOrderDialog.unwrap();
                opciones[obj].multiSort.edit = $('#' + opciones[idObj].multiSort.edit);

                // Establecemos el boton para el dialogo
                opciones[obj].multiSort.edit.click(() => {
                    opciones._multiSortDialog.rup_dialog('open');
                });
                

                let arrSidx = opciones.multiorder.sidx.split(',').map(a => a.trim());
                let arrSord = opciones.multiorder.sord.split(',').map(a => a.trim());

                if (arrSidx.length > 0) {
                    let cont = 0;
                    $(self.element).on('rup_list-mord-changed', () => {
                        cont++;
                        if (cont == arrSidx.length) {
                            $(self.element).trigger('rup_list-mord-inited');
                        }
                    });
                    arrSidx.forEach((elem, i) => {
                        $('button[data-ordValue="' + elem + '"]').trigger('click', [arrSord[i]]);
                    });
                } else {
                    $(self.element).trigger('rup_list-mord-inited');
                }
            };

            //Creamos el dialogo
            $('<div id="' + opciones._idMultiSortDialog + '" class="rup_list-mord-dialog" style="display:none"/>')
                .append('<div class="rup_list-mord-orderfields"/>')
                .append('<div class="rup_list-mord-ordersort"/>')
                .appendTo('body');
            opciones._multiSortDialog = $('#' + opciones._idMultiSortDialog);

            //Creamos el contenido del diálogo
            opciones.sidx.source.forEach((el) => {
                let $btn = $('<button/>')
                    .attr('data-ordValue', el.value)
                    .text(el.i18nCaption);
                $('.rup_list-mord-orderfields').append($btn.clone());
            });
            $('.rup_list-mord-orderfields').children().on('click', function (e, param) {
                self._actualizarOrdenMulti.apply(self, [e, param]);
            });

            //Creamos el componente para el dialogo
            opciones._multiSortDialog.rup_dialog({
                type: $.rup.dialog.DIV,
                autoOpen: false,
                modal: true,
                resizable: false,
                width: 'auto',
                title: 'Multiordenación',
                buttons: [{
                    text: 'cerrar',
                    click: () => {
                        opciones._multiSortDialog.rup_dialog('close');
                        self.element.rup_list('filter');
                    }
                }]
            });

            doInit();
            doInit(true);
        },

        /**
         * Método interno que configura el combo de elementos de lista por página
         * @name _rownumInit
         * @function
         */
        _rownumInit: function () {
            const self = this;
            const opciones = self.options;

            var rowNumRupConf = {
                source: opciones.rowNum.source,
                width: 'initial',
                selected: opciones.rowNum.value,
                rowStriping: true,
                ordered: false,
                change: function () {
                    if (!$('#' + this.id).rup_combo('isDisabled')) {
                        if (this.id == 'rup-list-footer-rowNum' && !opciones._header.rowNum.rup_combo('isDisabled')) {
                            opciones._header.rowNum.rup_combo('setRupValue', $('#' + this.id).rup_combo('getRupValue'));
                            opciones._header.rowNum.rup_combo('disable');
                        }
                        if (this.id == 'rup-list-header-rowNum' && !opciones._footer.rowNum.rup_combo('isDisabled')) {
                            opciones._footer.rowNum.rup_combo('setRupValue', $('#' + this.id).rup_combo('getRupValue'));
                            opciones._footer.rowNum.rup_combo('disable');
                        }
                        self._changeOption('rowNum', $('#' + this.id).rup_combo('getRupValue'));
                        opciones._header.rowNum.rup_combo('enable');
                        opciones._footer.rowNum.rup_combo('enable');
                    }
                }
            };
            opciones._header.rowNum.rup_combo(rowNumRupConf);
            opciones._header.rowNum = $('#' + opciones._idListHeader.rowNum);
            opciones._footer.rowNum.rup_combo(rowNumRupConf);
            opciones._footer.rowNum = $('#' + opciones._idListFooter.rowNum);
        },

        /**
         * Método interno que configura el nav de la paginación
         * @name _pagenavInit
         * @function
         */
        _pagenavInit: function () {
            const self = this;
            const opciones = self.options;

            var onPageChange = (elem) => {
                if ($(elem).is('.disabled')) {
                    return false;
                }
                let maxpage = $('.page').eq(-1).attr('data-page');
                let actualPage = opciones._header.pagenav.find('.page-item.page.active').attr('data-page');
                if (actualPage == 1) {
                    opciones._header.pagePrev.addClass('disabled');
                    opciones._footer.pagePrev.addClass('disabled');
                    opciones._header.pageNext.removeClass('disabled');
                    opciones._footer.pageNext.removeClass('disabled');
                    return true;
                }
                if (actualPage == maxpage) {
                    opciones._header.pagePrev.removeClass('disabled');
                    opciones._footer.pagePrev.removeClass('disabled');
                    opciones._header.pageNext.addClass('disabled');
                    opciones._footer.pageNext.addClass('disabled');
                    return true;
                }
                opciones._header.pagePrev.removeClass('disabled');
                opciones._footer.pagePrev.removeClass('disabled');
                opciones._header.pageNext.removeClass('disabled');
                opciones._footer.pageNext.removeClass('disabled');
                return true;
            };
            opciones._header.pagePrev.on('click', function () {
                if (!onPageChange(this)) {
                    return;
                }
                self._changeOption('page', opciones._header.pagenav.find('.page-item.page.active').prev('[data-page]').data('page'));
            });
            opciones._header.pageNext.on('click', function () {
                if (!onPageChange(this)) {
                    return;
                }
                self._changeOption('page', opciones._header.pagenav.find('.page-item.page.active').next('[data-page]').data('page'));
            });
            opciones._footer.pagePrev.on('click', function () {
                if (!onPageChange(this)) {
                    return;
                }
                self._changeOption('page', opciones._header.pagenav.find('.page-item.page.active').prev('[data-page]').data('page'));
            });
            opciones._footer.pageNext.on('click', function () {
                if (!onPageChange(this)) {
                    return;
                }
                self._changeOption('page', opciones._header.pagenav.find('.page-item.page.active').next('[data-page]').data('page'));
            });
        },

        /**
         * Método interno que crea la estructura de las líneas en la multiordenación
         * 
         * @name _actualizarOrdenMulti
         * @function
         * @param {Event} e 
         * @param {JQueryObj} self  Objeto JQuery del botón
         * @param {String} ord  Direccion de la ordenación con la que se va a generar la línea
         */
        _actualizarOrdenMulti: function (e, ord = 'asc') {
            const self = this;
            const opciones = self.options;

            var sortDiv = $('.rup_list-mord-ordersort');

            // Añadimos las opciones al componente
            if (opciones.multiorder.sidx.length) {
                let tmpSidxArr = opciones.multiorder.sidx.split(',').map((e) => {
                    return e.trim();
                });
                let tmpSordArr = opciones.multiorder.sord.split(',').map((e) => {
                    return e.trim();
                });
                if (tmpSidxArr.indexOf($(e.target).attr('data-ordValue')) == -1) {
                    tmpSidxArr.push($(e.target).attr('data-ordValue'));
                    tmpSordArr.push(ord);
                }
                opciones.multiorder.sidx = tmpSidxArr.join(',');
                opciones.multiorder.sord = tmpSordArr.join(',');
            }
            //Creamos la linea
            let $operateLine = $('<div class="rup_list-ord-line btn-group mb-3"/>')
                .attr('data-ordValue', $(e.target).attr('data-ordValue'));

            //Creamos los apartados de ordenacion, label y sord
            let $apOrd = $('<div class="rup_list-apord input-group-text"/>');
            let $btnUp = $('<button type="button" class="rup_list-mord-up btn btn-secondary p-1 mdi mdi-arrow-up"/>');
            let $btnDown = $('<button type="button" class="rup_list-mord-down btn btn-secondary p-1 mdi mdi-arrow-down"/>');
            let $labelOrd = $('<div class="rup_list-mord-label input-group-text rounded-0 w-50"/>')
                .text($(e.target).text());
            let $sord = $('<button class="rup_list-mord btn btn-secondary mdi"/>')
                .attr('data-direction', ord);
            let $quitOrd = $('<button class="rup_list-mord-remove btn btn-danger mdi mdi-close-circle"/>');

            $operateLine
                .append($apOrd)
                .append($btnUp)
                .append($btnDown)
                .append($labelOrd)
                .append($sord)
                .append($quitOrd);
            sortDiv.append($operateLine.clone());

            self._fnOrderOfOrderFields.apply(self, $('[data-ordValue="' + $(e.target).attr('data-ordValue') + '"]', sortDiv));
            $(e.target).remove();
        },
        
        /**
         * Método interno que da funcionalidad a cada línea en la multiordenación
         * 
         * @name _fnOrderOfOrderFields
         * @function
         * @param {JQuery} ctx La instancia de rup_list
         * @param {JQuery} line Objeto JQuery de la línea a la que se va a dar funcionalidad
         */
        _fnOrderOfOrderFields: function (line) {
            const self = this;
            const opciones = self.options;

            //Función de guardado de la multiordenación
            var save = () => {
                let sordDefault = opciones.multiorder.sord;
                opciones.multiorder.sidx = '';
                opciones.multiorder.sord = '';
                var sortDiv = $('.rup_list-mord-ordersort');
                let sidxArr = [];
                let sordArr = [];
                sortDiv.children().toArray().forEach((elem) => {
                    if (sidxArr.indexOf($(elem).attr('data-ordValue')) == -1) {
                        sidxArr.push($(elem).attr('data-ordValue'));
                        sordArr.push($('.rup_list-mord', $(elem)).attr('data-direction'));
                    }
                });
                if (sidxArr.length > 0) {
                    opciones.multiorder.sidx = sidxArr.join(',');
                    opciones.multiorder.sord = sordArr.join(',');
                } else {
                    // Si no hay parámetros de ordenación se usa la ordenación por defecto
                    opciones.multiorder.sidx = opciones.sidx.value;
                    opciones.multiorder.sord = sordDefault;
                }

                //Crear el label de resumen
                opciones._content.find('.rup_list-mord-summary').empty();
                opciones.multiorder.sidx.split(',').map((e) => {
                    return e.trim();
                }).forEach((e, i) => {
                    let geti18n = (val) => {
                        let srcVal = opciones.sidx.source.filter(x => x.value == val);
                        return srcVal[0].i18nCaption;
                    };
                    let sordBadge = $('<span/>')
                        .text(' ');
                    let arrSord = opciones.multiorder.sord.split(',').map((e) => {
                        return e.trim();
                    });
                    if (arrSord[i] == 'asc') {
                        sordBadge.addClass('mdi mdi-chevron-up');
                    } else {
                        sordBadge.addClass('mdi mdi-chevron-down');
                    }
                    $('<li class="rup_list-mord-summary-badge badge badge-pill badge-primary rounded-0 mr-1"/>')
                        .append(geti18n(e)).append(sordBadge.clone())
                        .appendTo(opciones._content.find('.rup_list-mord-summary'));
                });

                // Función para dehabilitar los botones de reordenación correspondientes
                $(self.element).find('.rup_list-mord-up, .rup_list-mord-down').attr('disabled', false);
                $(self.element).find('.rup_list-mord-up:first, .rup_list-mord-down:last').attr('disabled', true);

                $(self.element).trigger('rup_list-mord-changed');
            };

            // Funcionalidad de los botones de reordenación
            $('.rup_list-mord-up', line).click(function () {
                if ($(line).is(':first-child')) {
                    return;
                }
                $(line).prev().before(line);
                save();
            });
            $('.rup_list-mord-down', line).click(function () {
                if ($(line).is(':last-child')) {
                    return;
                }
                $(line).next().after(line);
                save();
            });

            //ponemos icono al sord
            if ($('.rup_list-mord', line).attr('data-direction') == 'asc') {
                $('.rup_list-mord', line).addClass('mdi-chevron-up');
            } else {
                $('.rup_list-mord', line).addClass('mdi-chevron-down');
            }
            $('.rup_list-mord').text(' ');

            //funcionalidad del sord
            $('.rup_list-mord', line).off('click');
            $('.rup_list-mord', line).click(() => {
                if ($('.rup_list-mord', line).attr('data-direction') == 'asc') {
                    $('.rup_list-mord', line).attr('data-direction', 'desc');
                    $('.rup_list-mord', line).addClass('mdi-chevron-down');
                    $('.rup_list-mord', line).removeClass('mdi-chevron-up');
                } else {
                    $('.rup_list-mord', line).attr('data-direction', 'asc');
                    $('.rup_list-mord', line).addClass('mdi-chevron-up');
                    $('.rup_list-mord', line).removeClass('mdi-chevron-down');
                }
                save();
            });

            //funcionalidad de retirar la ordenación
            $('.rup_list-mord-remove', line).click(() => {
                //recreamos el botón
                let $btn = $('<button class="btn-material btn-material-sm btn-material-primary-low-emphasis"/>')
                    .attr('data-ordValue', $(line).attr('data-ordValue'))
                    .text(opciones.sidx.source.filter(x => x.value == $(line).attr('data-ordValue'))[0].i18nCaption);
                $('.rup_list-mord-orderfields').append($btn.clone());
                $('.rup_list-mord-orderfields').children().off('click');
                $('.rup_list-mord-orderfields').children().on('click', function (e) {
                    self._actualizarOrdenMulti.apply(self, [e]);
                });
                // Eliminamos la linea
                $(line).remove();
                save();
            });

            save();
        },

        /**
         * Método interno para seleccionar todos los elementos de la lista.
         * 
         * @name _selectAll
         * @function
         */
        _selectAll: function () {
            const self = this;
            const opciones = self.options;

            opciones.multiselection.selectedAll = true;
            opciones.multiselection.selectedIds = [];
            opciones.multiselection.selectedRowsPerPage = [];

            self._getPageIds().forEach((elem) => {
                $('#' + elem).addClass('list-item-selected');
            });
            $('#' + self.element[0].id).trigger('listAfterMultiselection');
        },

        /**
         * Método interno para deseleccionar todos los elementos de la lista
         * 
         * @name _deselectAll
         * @function
         */
        _deselectAll: function () {
            const self = this;
            const opciones = self.options;

            opciones.multiselection.selectedAll = false;
            opciones.multiselection.selectedIds = null;
            opciones.multiselection.selectedRowsPerPage = null;
            self._getPageIds().forEach((elem) => {
                $('#' + elem).removeClass('list-item-selected');
            });
            $('#' + self.element[0].id).trigger('listAfterMultiselection');
        },

        /**
         * Método interno para seleccionar todos los elementos en la página actual
         * 
         * @name _selectPage
         * @function
         */
        _selectPage: function () {
            const self = this;
            const opciones = self.options;

            if (opciones.multiselection.selectedIds == null) {
                opciones.multiselection.selectedIds = [];
            }
            if (opciones.multiselection.selectedRowsPerPage == null) {
                opciones.multiselection.selectedRowsPerPage = [];
            }
            if (opciones.multiselection.selectedAll == false) {
                self._getPageIds().forEach((arrElem) => {
                    let tmp = opciones.multiselection.selectedRowsPerPage.filter(x => x.id == arrElem);
                    if (tmp.length == 0) {
                        let id = arrElem.split('_').pop();
                        opciones.multiselection.selectedIds.push(id);
                        opciones.multiselection.selectedRowsPerPage.push({
                            id: arrElem,
                            line: (function () {
                                let cont = 0;
                                let final = 0;
                                self.element.children().toArray().forEach(element => {
                                    if (element.id == arrElem) {
                                        final = cont;
                                    }
                                    cont++;
                                });
                                return final;
                            })(),
                            page: opciones.page
                        });
                        $('#' + arrElem).addClass('list-item-selected');
                    }
                });
            } else {
                self._getPageIds().forEach((arrElem) => {
                    let tmp = opciones.multiselection.selectedRowsPerPage.fiter(x => x.id == arrElem);
                    if (tmp.length == 0) {
                        let id = arrElem.split('_').pop();
                        opciones.multiselection.selectedIds = opciones.multiselection.selectedIds.filter(z => z != id);
                        opciones.multiselection.selectedRowsPerPage = opciones.multiselection.selectedRowsPerPage.filter(z => z.id != arrElem);
                        $('#' + arrElem).addClass('list-item-selected');
                    }
                });
            }
            if (opciones.multiselection.selectedIds.length == 0) {
                opciones.multiselection.selectedIds = null;
            }
            if (opciones.multiselection.selectedRowsPerPage.length == 0) {
                opciones.multiselection.selectedRowsPerPage = null;
            }
            $('#' + self.element[0].id).trigger('listAfterMultiselection');
        },

        /**
         * Método interno para deseleccionar todos los elementos en la página actual
         * 
         * @name _deselectPage
         * @function
         */
        _deselectPage: function () {
            const self = this;
            const opciones = self.options;

            if (opciones.multiselection.selectedIds == null) {
                opciones.multiselection.selectedIds = [];
            }
            if (opciones.multiselection.selectedRowsPerPage == null) {
                opciones.multiselection.selectedRowsPerPage = [];
            }
            if (opciones.multiselection.selectedAll == false) {
                self._getPageIds().forEach((arrElem) => {
                    let tmp = opciones.multiselection.selectedRowsPerPage.filter(x => x.id == arrElem);
                    if (tmp.length > 0) {
                        let id = arrElem.split('_').pop();
                        opciones.multiselection.selectedIds = opciones.multiselection.selectedIds.filter(z => z != id);
                        opciones.multiselection.selectedRowsPerPage = opciones.multiselection.selectedRowsPerPage.filter(z => z.id != arrElem);
                        $('#' + arrElem).removeClass('list-item-selected');
                    }
                });
            } else {
                self._getPageIds().forEach((arrElem) => {
                    let tmp = opciones.multiselection.selectedRowsPerPage.filter(x => x.id == arrElem);
                    if (tmp.length == 0) {
                        let id = arrElem.split('_').pop();
                        opciones.multiselection.selectedIds.push(id);
                        opciones.multiselection.selectedRowsPerPage.push({
                            id: arrElem,
                            line: (function () {
                                let cont = 0;
                                let final = 0;
                                self.element.children().toArray().forEach(element => {
                                    if (element.id == arrElem) {
                                        final = cont;
                                    }
                                    cont++;
                                });
                                return final;
                            })(),
                            page: opciones.page
                        });
                    }
                    $('#' + arrElem).removeClass('list-item-selected');
                });
            }
            if (opciones.multiselection.selectedIds.length == 0) {
                opciones.multiselection.selectedIds = null;
            }
            if (opciones.multiselection.selectedRowsPerPage.length == 0) {
                opciones.multiselection.selectedRowsPerPage = null;
            }
            $('#' + self.element[0].id).trigger('listAfterMultiselection');
        },

        /**
         * Método interno que genera el desplegable de multiseleccion
         * 
         * @name _generateSelectablesBtnGroup
         * @function
         */
        _generateSelectablesBtnGroup: function () {
            const self = this;
            const selfId = self.element.attr('id');
            const opciones = self.options;

            let $btnGroup = $('<div class="btn-group h-100" role="group"/>');
            let $displayButton = $('<button></button>');
            $displayButton.addClass('dropdown-toggle')
                .attr('id', selfId + '-display-selectables').attr('type', 'button')
                .attr('data-toggle', 'dropdown').attr('aria-haspopup', true)
                .attr('aria-expanded', false).text(opciones._header.selectables.text());
            let $menudiv = $('<div></div>').addClass('dropdown-menu').attr('aria-labelledby', selfId + '-display-selectables');

            let $selectPage = $('<a></a>').addClass('dropdown-item selectable-selectPage').text('Seleccionar la página actual');
            let $deselectPage = $('<a></a>').addClass('dropdown-item selectable-deselectPage').text('Deseleccionar la página actual');
            let $selectAll = $('<a></a>').addClass('dropdown-item selectable-selectAll').text('Seleccionar todo');
            let $deselectAll = $('<a></a>').addClass('dropdown-item selectable-deselectAll').text('Deseleccionar todo');

            $menudiv.append($selectPage).append($deselectPage).append($selectAll).append($deselectAll);

            $btnGroup.append($displayButton).append($menudiv);
            opciones._header.selectables.text('');
            opciones._header.selectables.append($btnGroup.clone());

            if (opciones.createFooter) {
                opciones._footer.selectables.text('');
                opciones._footer.selectables.append($btnGroup.clone());
            }

            //Creamos funcionalidad
            $('.selectable-selectAll').on('click', () => {
                self._selectAll.apply(self);
            });
            $('.selectable-deselectAll').on('click', () => {
                self._deselectAll.apply(self);
            });
            $('.selectable-selectPage').on('click', () => {
                self._selectPage.apply(self);
            });
            $('.selectable-deselectPage').on('click', () => {
                self._deselectPage.apply(self);
            });
        },

        /**
         * Método interno para obtener los Ids de la página actual
         * 
         * @name _getPageIds
         * @function
         */
        _getPageIds: function () {
            var self = this;
            var keys = [];
            $('#' + self.element[0].id).children().toArray().forEach((elem) => {
                keys.push($(elem)[0].id);
            });
            return keys;
        },

        /**
         * Método interno que otorga funcionalidad a la paginación
         * 
         * @name _pagenavManagement
         * @function
         * @param {Number} numPages Número total de páginas
         */
        _pagenavManagement: function (numPages) {
            var self = this;
            var opciones = this.options;
            var $pagenavH = opciones._header.pagenav;
            var $pagenavH_prev = opciones._header.pagePrev;
            var $pagenavH_next = opciones._header.pageNext;
            var $pagenavF = opciones._footer.pagenav;
            var $pagenavF_prev = opciones._footer.pagePrev;
            var $pagenavF_next = opciones._footer.pageNext;

            // Si el número de páginas a mostrar es superior a las configuradas como visibles hay que mostrar el separador
            if (numPages > opciones.visiblePages + 1) {
                // Mostrar las páginas visibles antes del separador
                var initPage = 1;
                var endPage = 1;
                if (opciones.page >= opciones.visiblePages) {
                    if (opciones.page + opciones.visiblePages - 1 > numPages) {
                        initPage = numPages - opciones.visiblePages + 1;
                    } else {
                        initPage = opciones.page - 1;
                    }

                    // Se añade la página inicial
                    var page = '<li data-page="' + 1 + '" class="page-item page"><a class="page-link" href="javascript:void(0)">' + 1 + '</a></li>';
                    $pagenavH.find('.page-separator:first').before(page);
                    $pagenavF.find('.page-separator:first').before(page);

                    if (opciones.page - opciones.visiblePages > 0) {
                        // Mostrar el separador de inicio
                        $pagenavH.find('.page-separator:first').show();
                        $pagenavF.find('.page-separator:first').show();
                    }
                }
                endPage = initPage + opciones.visiblePages < numPages ? initPage + opciones.visiblePages : numPages + 1;
                for (var i = initPage; i < endPage; i++) {
                    let page = '<li data-page="' + i + '" class="page-item page"><a class="page-link" href="javascript:void(0)">' + i + '</a></li>';
                    $pagenavH.find('.page-separator:last').before(page);
                    $pagenavF.find('.page-separator:last').before(page);
                }

                if (opciones.page + opciones.visiblePages - 1 < numPages) {
                    // Mostrar el separador de fin
                    $pagenavH.find('.page-separator:last').show();
                    $pagenavF.find('.page-separator:last').show();
                }

                if (endPage < numPages) {
                    // Añadir el número de la página final
                    let page = '<li data-page="' + numPages + '" class="page-item page"><a class="page-link" href="javascript:void(0)">' + numPages + '</a></li>';
                    $pagenavH.find('.page-separator:last').after(page);
                    $pagenavF.find('.page-separator:last').after(page);
                }
            } else {
                // Añadir todas las páginas al nav
                for (let i = numPages; i > 0; i--) {
                    let page = '<li data-page="' + i + '" class="page-item page"><a class="page-link" href="javascript:void(0)">' + i + '</a></li>';
                    $pagenavH_prev.after(page);
                    $pagenavF_prev.after(page);
                }
            }

            // Ocultar el pagenav si sólo se muestra una única página
            if (numPages > 1) {
                opciones._header.pagenav.show();
                opciones._footer.pagenav.show();
            } else {
                opciones._header.pagenav.hide();
                opciones._footer.pagenav.hide();
            }

            // Marcar la página actual como activa
            $('#' + opciones._idListHeader.pagenav + ' ' + '.page[data-page="' + opciones.page + '"]').toggleClass('active');
            $('#' + opciones._idListFooter.pagenav + ' ' + '.page[data-page="' + opciones.page + '"]').toggleClass('active');

            // Funcionamiento del pagenav
            $('#' + opciones._idListHeader.pagenav + ' .page-item.page, #' + opciones._idListFooter.pagenav + ' .page-item.page')
                .on('click', function () {
                    // La página activa se desactiva
                    $('#' + opciones._idListHeader.pagenav + ' .page-item.page.active').toggleClass('active');
                    $('#' + opciones._idListFooter.pagenav + ' .page-item.page.active').toggleClass('active');
                    // La página seleccionada se activa
                    $(this).toggleClass('active');
                    self._changeOption('page', $(this).data('page'));
                });

            if (opciones.page > 1) {
                $pagenavH_prev.removeClass('disabled');
                $pagenavF_prev.removeClass('disabled');
            } else {
                $pagenavH_prev.addClass('disabled');
                $pagenavF_prev.addClass('disabled');
            }
            if (opciones.page < numPages) {
                $pagenavH_next.removeClass('disabled');
                $pagenavF_next.removeClass('disabled');
            } else {
                $pagenavH_next.addClass('disabled');
                $pagenavF_next.addClass('disabled');
            }
        },

        /**
         * Método que bloquea el componente
         * 
         * @name lock
         * @function
         * @example
         * $('#rup-list').rup_list('lock');
         */
        lock: function () {
            this._lock();
        },

        /**
         * Método que desbloquea el componente
         * 
         * @name unlock
         * @function
         * @example
         * $('#rup-list').rup_list('unlock');
         */
        unlock: function () {
            this._unlock();
        },

        /**
         * Método interno que se encarga del bloqueo del componente
         * 
         * @name _lock
         * @function
         */
        _lock: function () {
            const self = this;
            const opciones = self.options;

            opciones._header.obj.css('opacity', '0.3');
            self.element.css('opacity', '0.3');
            opciones._footer.obj.css('opacity', '0.3');

            $('#' + opciones._idOverlay).remove();
            opciones._overlay.prependTo(opciones._content);

            opciones._overlay.height(opciones._content.height());
        },

        /**
         * Método interno que se encarga del desbloqueo del componente
         * 
         * @name _unlock
         * @function
         */
        _unlock: function () {
            const self = this;
            const opciones = self.options;

            opciones._header.obj.css('opacity', '1');
            self.element.css('opacity', '1');
            opciones._footer.obj.css('opacity', '1');
            $('#' + opciones._idOverlay).remove();
        },

        /**
         * Método para destruir el componente
         * 
         * @name destroy
         * @function
         * @example
         * $('#rup-list').rup_list('destroy');
         */ 
        destroy: function () {
            const self = this;
            const opciones = this.options;

            $(self.element).empty();
            opciones._footer.obj.remove();
            opciones.feedback.rup_feedback('destroy');
            $.Widget.prototype.destroy.apply(this, arguments);
        },

        /**
         * Método interno que se encarga de realizar el filtrado y construir la lista desde los datos recibidos
         * 
         * @name _doFilter
         * @function
         */
        _doFilter: function () {
            const self = this;
            const opciones = this.options;
            const $itemTemplate = opciones._itemTemplate;
            const $pagenavH = opciones._header.pagenav;
            const $pagenavF = opciones._footer.pagenav;

            // Validar si la ordenacion es simple o múltiple
            var sidx = '';
            var sord = '';
            if (opciones.isMultiSort) {
                sidx = opciones.multiorder.sidx;
                sord = opciones.multiorder.sord;
            } else {
                sidx = opciones.sidx.value;
                sord = opciones.sord;
            }
            // Componer el filtro
            var filter = {
                filter: $('#' + opciones.filterForm).rup_form('formToJson'),
                page: opciones.page,
                rows: opciones.rowNum.value,
                sidx: sidx,
                sord: sord,
                multiselection: opciones.multiselection,
                core: {
                    pkNames: [opciones.key],
                    pkToken: '~'
                }
            };

            // opciones.feedback.rup_feedback('hide');

            if ($('#' + opciones.filterForm).rup_form('valid')) {
                jQuery.rup_ajax({
                    url: opciones.action,
                    type: 'POST',
                    dataType: 'json',
                    data: JSON.stringify(filter),
                    contentType: 'application/json',
                    success: function (xhr) {
                        $pagenavH.find('.page').remove();
                        $pagenavF.find('.page').remove();

                        $pagenavH.find('.page-separator').hide();
                        $pagenavF.find('.page-separator').hide();

                        if (xhr === null || xhr.length === 0) {
                            opciones._header.obj.hide();
                            self.element.hide();
                            opciones._footer.obj.hide();
                            opciones.feedback.rup_feedback('set', $.rup.i18n.base.rup_table.errors.errorOnGet, 'error');
                            opciones._content.slideDown();

                            self.element.trigger('load');
                            self._unlock();
                        } else {
                            if (xhr.rows && xhr.rows.length > 0) {
                                var initRecord = ((opciones.page - 1) * parseInt(opciones.rowNum.value)) + 1;
                                var endRecord = initRecord + xhr.rows.length - 1;
                                var records = parseInt(xhr.records) == 0 ? xhr.rows.length : xhr.records;
                                var msgRecords =
                                    $.rup.i18nTemplate($.rup.i18n.base.rup_table.defaults, 'recordtext', initRecord, endRecord, records);
                                opciones.feedback.rup_feedback({});
                                opciones.feedback.rup_feedback('set', msgRecords, 'ok');

                                self._pagenavManagement(Math.ceil(xhr.records / opciones.rowNum.value));

                                $.each(xhr.rows, function (index, elem) {
                                    var $item = $itemTemplate.clone(true, true);
                                    $item.attr('id', $item.attr('id') + '_' + elem[opciones.key]);

                                    var elemArr = $.rup_utils.jsontoarray(elem);
                                    var elemArrKeys = Object.keys($.rup_utils.jsontoarray(elem));
                                    if (opciones.selectable) {
                                        let selectorElement = $(opciones.selectable.selector, $item);
                                        selectorElement.attr('id', selectorElement.attr('id') + '_' + elem[opciones.key]);
                                    }
                                    if (xhr.reorderedSelection) {
                                        let tmp = xhr.reorderedSelection.filter(arrItem => arrItem.pk[opciones.key] == elem[opciones.key]);
                                        if ((tmp.length > 0 && !xhr.selectedAll) || (tmp.length == 0 && xhr.selectedAll)) {
                                            $item.addClass('list-item-selected');
                                        }
                                    }

                                    for (var i = 0; i < elemArrKeys.length; ++i) {
                                        $item.find('[id="' + elemArrKeys[i] + '_label"]')
                                            .text(opciones.colNames[elemArrKeys[i]] ? opciones.colNames[elemArrKeys[i]] : elemArrKeys[i])
                                            .attr('id', elemArrKeys[i] + '_label_' + elem[opciones.key]);
                                        $item.find('[id="' + elemArrKeys[i] + '_value"]')
                                            .text(elemArr[elemArrKeys[i]])
                                            .attr('id', elemArrKeys[i] + '_value_' + elem[opciones.key]);
                                    }
                                    $('#' + self.element[0].id).trigger('modElement', [$item, elem]);
                                });

                                // si ha resultados se muestran cabecera/pie y listado
                                opciones._header.obj.show();
                                self.element.show();
                                opciones._footer.obj.show();

                                // Si no se está mostrando el content se despliega
                                opciones._content.slideDown();
                            } else {
                                // Si no se devuelven resultados
                                opciones._header.obj.hide();
                                self.element.hide();
                                opciones._footer.obj.hide();
                                opciones.feedback.rup_feedback('set', $.rup.i18n.base.rup_table.defaults.emptyrecords, 'alert');
                                opciones._content.slideDown();
                            }
                        }

                        self.element
                            .children().each(function (i, e) {
                                setTimeout(function () {
                                    $(e).show('drop', {}, 200, function () {
                                        if ($(e).next().length == 0) {
                                            self.element.css('height', 'auto');
                                        }
                                    });
                                }, 50 + (i * 50));
                            });

                        self.element.trigger('load');
                        // $('#' + self.element[0].id).trigger('load');
                        self._unlock();
                    },
                    error: function (XMLHttpResponse) {
                        opciones.feedback.rup_feedback('set', XMLHttpResponse.responseText, 'error');
                        opciones._header.obj.hide();
                        self.element.hide();
                        opciones._footer.obj.hide();
                        opciones._content.slideDown();

                        self.element.trigger('load');
                        self._unlock();
                    }
                });
            } else {
                self.element.trigger('load');
                self._unlock();
            }
        },

        /**
         * Método que se encarga de realizar una recarga de la lista
         * 
         * @name reload
         * @function
         * @example
         * $('#rup-list').rup_list('reload');
         */
        reload: function () {
            var self = this;

            self._lock();

            if (self.element.children().length > 0) {
                // Eliminar el listado actual y buscar el nuevo
                self.element
                    .css('height', self.element.outerHeight() + 16)
                    .children().each(function (i, e) {
                        setTimeout(function () {
                            $(e).hide('drop', {}, 200, function () {
                                $(this).remove();

                                // Si hemos llegado al Ãºltimo elemento procedemos a buscar el nuevo listado
                                if (self.element.children().length == 0) {
                                    self._doFilter();
                                }
                            });
                        }, 50 + (i * 50));
                    });
            } else {
                self._doFilter();
            }
        },

        /**
         * Método que se encarga de realizar el filtrado de la lista
         * 
         * @name filter
         * @function
         * @example
         * $('#rup-list').rup_list('filter');
         */
        filter: function () {
            var self = this;
            var opciones = this.options;
            opciones.page = 1;

            self._lock();

            if (self.element.children().length > 0) {
                // Eliminar el listado actual y buscar el nuevo
                self.element
                    .css('height', self.element.outerHeight() + 16)
                    .children().each(function (i, e) {
                        setTimeout(function () {
                            $(e).hide('drop', {}, 200, function () {
                                $(this).remove();

                                // Si hemos llegado al Ãºltimo elemento procedemos a buscar el nuevo listado
                                if (self.element.children().length == 0) {
                                    self._doFilter();
                                }
                            });
                        }, 50 + (i * 50));
                    });
            } else {
                self._doFilter();
            }
        },

        /**
         * Método para cambiar la página actual.
         * 
         * @name page
         * @function
         * @param {Number} page La página a la que navegar
         * @example
         * $('#rup-list').rup_list('page', 3);
         */
        page: function (page) {
            var self = this;
            self._changeOption('page', page);
        },

        /**
         * Método que obtiene la información de la selección actual
         * 
         * @name getSelectedIds
         * @function
         * @example
         * $('#rup-list').rup_list('getSelectedIds');
         */
        getSelectedIds: function () {
            var self = this;
            var options = self.options.multiselection;
            return {
                selectedIds: options.selectedIds,
                selectedAll: options.selectedAll
            };
        }
    });
}));
