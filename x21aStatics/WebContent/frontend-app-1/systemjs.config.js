/**
 * System configuration for Angular samples
 * Adjust as necessary for your application needs.
 */
(function (global) {
  System.config({
    paths: {
      // paths serve as alias
      'npm:': '/x21aStatics/frontend-app-1/node_modules/',
      'rup': 'npm:uda-rup/src',
      'rup-adapter': 'npm:uda-rup/src/adapter',

       "request": "npm:request"
    },
    // map tells the System loader where to look for things
    map: {
      // our app is within the app folder
      'app': '/x21aStatics/app',
      'plugin-babel': 'npm:systemjs-plugin-babel/plugin-babel.js',
      'systemjs-babel-build': 'npm:systemjs-plugin-babel/systemjs-babel-browser.js',
      'rup': 'npm:uda-rup/src',

      "jquery": "npm:jquery/dist/jquery.js",
      "jquery-migrate": "npm:jquery-migrate/dist/jquery-migrate.js",
      "jquery-ui": "npm:jquery-ui-dist/jquery-ui.js",
      "backbone": "npm:backbone/backbone.js",
      // "bootstrap": "npm:bootstrap/dist/js/bootstrap.min.js",
      "bootstrap": "npm:bootstrap/js/src",
      "bt3": "../../dist/js/externals/bt3.min.js",
      "bt4": "npm:uda-rup/dist/js/externals/bt4.min.js",
      "underscore": "npm:underscore/underscore.js",
      "handlebars": "npm:handlebars/dist/handlebars.js",
      "handlebars-i18n": "../js/handlebars-helper-i18n.js",
      "marionette": "npm:backbone.marionette/lib/backbone.marionette.js",
      "chartjs": "npm:chart.js/dist/Chart.js",
      "qtip2": "npm:qtip2/dist/jquery.qtip.js",
      "jquery.fileDownload": "../../src/core/utils/jquery.fileDownload.js",
      "tether": "npm:tether/dist/js/tether.js",
      "rcarousel": "../../src/core/jquery.ui.rcarousel.js",

      //"highlight": "npm:highlight.js/lib/highlight",
      "highlight": "../js/highlight.pack.js",
      "templates": "../templates.js",
      "blockUI": "npm:block-ui/jquery.blockUI.js",
      "jquery-contextMenu": "npm:jquery-contextmenu/dist/jquery.contextMenu.js",
      "jquery.form": "npm:jquery-form/jquery.form.js",
      "jquery.validate": "npm:jquery-validation/dist/jquery.validate.js",
      "jquery.validate.additional": "npm:jquery-validation/dist/additional-methods.js",
      "jquery.scrollTo": "npm:jquery.scrollto/jquery.scrollTo.js",

      "jquery.fileupload": "npm:blueimp-file-upload/js/jquery.fileupload.js",
      "jquery.fileupload-ui": "npm:blueimp-file-upload/js/jquery.fileupload-ui.js",
      "jquery.fileupload-jquery-ui": "npm:blueimp-file-upload/js/jquery.fileupload-jquery-ui.js",
      "jquery.fileupload-process": "npm:blueimp-file-upload/js/jquery.fileupload-process.js",
      "jquery.fileupload-image": "npm:blueimp-file-upload/js/jquery.fileupload-image.js",
      "jquery.fileupload-audio": "npm:blueimp-file-upload/js/jquery.fileupload-audio.js",
      "jquery.fileupload-video": "npm:blueimp-file-upload/js/jquery.fileupload-video.js",
      "jquery.fileupload-validate": "npm:blueimp-file-upload/js/jquery.fileupload-validate.js",
      "jquery.xdr-transport": "npm:blueimp-file-upload/js/cors/jquery.xdr-transport.js",
      "jquery.ui.widget": "npm:jquery-ui/ui/widget.js",
      "tmpl": "npm:blueimp-tmpl/js/tmpl.js",
      "load-image": "npm:blueimp-load-image/js/load-image.js",
      "load-image-meta": "npm:blueimp-load-image/js/load-image-meta.js",
      "load-image-exif": "npm:blueimp-load-image/js/load-image-exif.js",
      "canvas-to-blob": "npm:blueimp-canvas-to-blob/js/canvas-to-blob.js",

      // menuDeps
      // "jquery-1.7": "../../src/core/jquery-1.7.2",
      "jquery.ui.autocomplete": "../../src/core/ui/jquery.ui.autocomplete.js",
      //"jquery.ui.selectmenu": "../../src/core/ui/jquery.ui.selectmenu",
      //"jquery.multiselect": "../../src/core/ui/jquery.multiselect",
      "jquery-json": "npm:uda-rup/src/core/utils/jquery.json-2.2.js",
      "jquery-ui-timepicker": "npm:uda-rup/src/core/ui/jquery-ui.timepicker.js",
      "jquery-ui-multidatespicker": "npm:uda-rup/src/core/ui/jquery-ui.multidatespicker.js",

      "form2object": "npm:uda-rup/src/core/utils/form2object.js",
      "gridstack": "npm:gridstack/dist/gridstack.js",
      "lodash": "npm:lodash/lodash.js",
      "jquery-jstree": "npm:uda-rup/src/core/utils/jquery.jstree.js",
      "jquery-hotkeys": "npm:uda-rup/src/core/utils/jquery.hotkeys.js",

      "request": "npm:request/request.js"
      // "request/*": "npm:request/lib/*.js"

    },
    meta: {
        'jquery-ui': {
        	"deps": ["jquery", "jquery-migrate"],
          "format": "amd" // or try AMD
        },
        'bt4': {
        	"deps": ["jquery", "tether"],
          "format": "global" // or try AMD
        },
        "tether": {
        	// "deps": ["jquery", "tether"],
          "format": "global" // or try AMD
        },

        // 'request':{
        //   format:'esm'
        // },
        // 'jquery': {
        //     format: 'global',  //note I have tried global and cjs and there is no difference except
        //                     // the mechanism by which jQuery gets loaded (script tag or xhr)
        //     exports: '$'
        // }

      },
    // packages tells the System loader how to load when no filename and/or no extension
    transpiler: 'plugin-babel',
    packages: {
      app: {
        main: './main.js',
        defaultExtension: 'js',
        deps:["jquery"]
      },
      rup: {
    	 main: './main.js',
    	 defaultExtension: 'js'
     },
     bootstrap: {
      main: './main.js',
      defaultExtension: 'js'
     }




    }
  });
})(this);
