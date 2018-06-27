var path = require('path');
const webpack = require('webpack');

module.exports = {
  entry: {
        board: "./app/board.js",
        detail: "./app/detail.js"
  },
  output: {
    filename: '[name]-bundle.js',
    path: path.resolve(__dirname, 'dist')
  },
  module: {
      // loaders: [
      //     { test: path.join(__dirname, 'app'),
      //       loader: 'babel-loader',
      //       query: {
      //         presets: ['es2015']
      //       }
      //     }
      // ]
  },
  stats: {
         colors: true
  },
  node: {
  fs: 'empty'
},
plugins: [
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery"
        })
    ],
resolve:
  {
    alias: {
      'handlebars' : 'handlebars/dist/handlebars.js',
      'jquery': "jquery/dist/jquery.js",
      'jquery-ui': "jquery-ui-dist/jquery-ui.js"
    }
  },

  devtool: 'source-map'
};
