/**
 * Created by Arsalan on 17.06.2017.
 */
const path = require("path");
let webpack = require("webpack");

const PATHS = {
    source: path.join(__dirname, '\\')
}

module.exports = {
    entry: PATHS.source + "js\\index.js",
    output: {
        path: PATHS.source + "js",
        filename: "bundle.js"
    },

    plugins: [
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery"
        })
    ]
}