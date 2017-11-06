"use strict";

const path = require("path");
const webpack = require("webpack");
const PATHS = {
    source: path.join(__dirname, '/')
}

module.exports = {
    entry: PATHS.source + "js/index.js",
    output: {
        path: PATHS.source + "js",
        filename: "bundle.js"
    },

    plugins: [
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery",
            'window.$': "jquery",
            'window.jQuery': "jquery"
        })
    ],

    module: {
        loaders: [
            {
                test: /\.css$/,
                loader: "style-loader!css-loader!sass-loader"
            },
            {
                test: /\.html$/,
                loader: "html-loader"
            },
            {
                test : /\.jsx?/,
                loader : 'babel-loader'
            },
            {
                test: /\.scss$/,
                use: [{
                    loader: "style-loader" // creates style nodes from JS strings
                }, {
                    loader: "css-loader" // translates CSS into CommonJS
                }, {
                    loader: "sass-loader" // compiles Sass to CSS
                }]
            },
        ]
    },

    devtool: "source-map"
};