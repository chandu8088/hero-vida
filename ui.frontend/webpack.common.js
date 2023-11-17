/* eslint-disable */
"use strict";

const path = require("path");
const webpack = require("webpack");
const ESLintPlugin = require("eslint-webpack-plugin");
const StylelintPlugin = require("stylelint-webpack-plugin");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const TSConfigPathsPlugin = require("tsconfig-paths-webpack-plugin");
const CopyWebpackPlugin = require("copy-webpack-plugin");
const { CleanWebpackPlugin } = require("clean-webpack-plugin");
const mqplugin = require("css-mquery-packer");

const SOURCE_ROOT = __dirname + "/src/main/webpack";

const resolve = {
  extensions: [".js", ".ts", ".scss"],
  plugins: [
    new TSConfigPathsPlugin({
      configFile: "./tsconfig.json"
    })
  ]
};

// set environment as development if not found
const environment = process.env.NODE_ENV ? process.env.NODE_ENV : "development";
const isDevelopment = environment === "development";

module.exports = {
  resolve: resolve,
  entry: {
    site: SOURCE_ROOT + "/site/main.ts"
  },
  output: {
    filename: (chunkData) => {
      return chunkData.chunk.name === "dependencies"
        ? "clientlib-dependencies/[name].js"
        : "clientlib-site/[name].js";
    },
    path: path.resolve(__dirname, "dist")
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        loader: ["babel-loader", "eslint-loader"]
      },
      {
        test: /\.tsx?$/,
        exclude: /node_modules/,
        use: [
          {
            loader: "ts-loader"
          },
          {
            loader: "glob-import-loader",
            options: {
              resolve: resolve
            }
          }
        ]
      },
      {
        test: /(\.css|\.scss)$/,
        use: (function () {
          const config = [
            MiniCssExtractPlugin.loader,
            {
              loader: "css-loader",
              options: {
                url: false,
                sourceMap: isDevelopment
              }
            },
            {
              loader: "postcss-loader",
              options: {
                plugins() {
                  return [
                    require("autoprefixer"),
                    mqplugin({
                      sort: true
                    }),
                    require("cssnano")({
                      preset: [
                        "default",
                        {
                          discardComments: {
                            removeAll: true
                          },
                          discardDuplicates: true,
                          discardOverridden: true,
                          mergeRules: true
                        }
                      ]
                    })
                  ];
                }
              }
            },
            {
              loader: "sass-loader",
              options: {
                url: false,
                sourceMap: isDevelopment
              }
            },
            {
              loader: "webpack-import-glob-loader",
              options: {
                url: false
              }
            }
          ];
          if (!isDevelopment) {
            config.splice(2, 0, {
              loader: "replace-string-loader",
              options: {
                search: /clientlib-site/g,
                replace:
                  "etc.clientlibs/vida/clientlibs/clientlib-site/resources",
                file: false
              }
            });
          }
          return config;
        })()
      }
    ]
  },
  plugins: [
    new CleanWebpackPlugin(),
    new webpack.NoEmitOnErrorsPlugin(),
    new MiniCssExtractPlugin({
      filename: "clientlib-[name]/[name].css"
    }),
    new CopyWebpackPlugin([
      {
        from: path.resolve(__dirname, SOURCE_ROOT + "/resources"),
        to: "./clientlib-site/"
      }
    ]),
    new ESLintPlugin({
      files: ["src/**/*.js", "src/**/*.ts"],
      fix: true
    }),
    new StylelintPlugin({
      files: ["src/**/*.css", "src/**/*.scss"],
      fix: true
    }),
    new webpack.optimize.LimitChunkCountPlugin({
      maxChunks: 1
    })
  ],
  stats: {
    assetsSort: "chunks",
    builtAt: true,
    children: false,
    chunkGroups: true,
    chunkOrigins: true,
    colors: false,
    errors: true,
    errorDetails: true,
    env: true,
    modules: false,
    performance: true,
    providedExports: false,
    source: false,
    warnings: true
  }
};
