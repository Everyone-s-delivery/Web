const path = require('path');
const { merge } = require('webpack-merge');
const baseConfig = require('./webpack.base');
const HtmlWebPackPlugin = require('html-webpack-plugin');

const PROJECT_ROOT = path.resolve(__dirname, '../..');
const TEMPLATE_PATH = path.resolve(PROJECT_ROOT, 'public');

module.exports = merge(baseConfig, {
  mode: 'development',
  devtool: 'eval-source-map',
  devServer: {
    hot: true,
    port: '5050',
    host: '127.0.0.1',
    historyApiFallback: true,
  },
  module: {
    rules: [
    
    ],
  },
  plugins: [
    new HtmlWebPackPlugin({
      template: path.resolve(TEMPLATE_PATH, 'index.html'),
      minify: {
        removeComments: true,
      },
      favicon: path.resolve(TEMPLATE_PATH, 'favicon.ico'),
    }),
  ],
});
