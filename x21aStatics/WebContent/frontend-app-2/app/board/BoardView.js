;
'use strict'

// import { View } from '../base/View.js';
//
//
// import {qs, qsa, $on, $delegate} from '../util/helper.js';
//
// import { ListHandler } from '../list/ListHandler.js';
import Handlebars from 'handlebars';

import Marionette from 'backbone.marionette';

import {qs, qsa, $on, $delegate} from '../util/helper.js';

// import 'jquery';
//
// // import { RupButton } from 'rup/rup.button';
// import RupButton from 'uda-rup/src/rup.button.js';
// // import { RupDialog } from 'rup/rup.dialog';
// import RupDialog from 'uda-rup/src/rup.dialog.js';

Handlebars.registerHelper("showHr", function(index_count,block) {

  if(parseInt(index_count+1)%3=== 0){
    return block.fn(this);}


});

// export const BoardView = Marionette.View.extend({
//   template: "board-template"
// });

export default class BoardView extends Marionette.View {
  constructor(...args) {
    super(...args);
    this.template = Handlebars.compile(qs("#board-template").innerHTML);
  }
}

//  const TodoView = Marionette.LayoutView.extend({
//   template: "#board-template"
//
// });




//
// class BoardView extends Marionette.LayoutView {
//   constructor(...args) {
//     super(...args);
//     this.template = "#board-template";
//   }
// }
