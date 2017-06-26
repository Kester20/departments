"use strict";

const path = require("path");
let webpack = require("webpack");

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
                loader: "style-loader!css-loader"
            },
            {
                test: /\.html$/,
                loader: "html-loader"
            }
        ]
    },

    devtool: "source-map"
};