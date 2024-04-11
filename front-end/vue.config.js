const { defineConfig } = require('@vue/cli-service')
const {resolve} = require("path");
module.exports = defineConfig({
  transpileDependencies: true,
  configureWebpack: {
    resolve: {
      alias: {
        '@': resolve(__dirname, 'src')
      }
    }
  },
  devServer: {
    port: 8081,
    proxy: {
      '/api': {
        // target: 'http://localhost:8080',
        target: 'http://52.220.121.147:8080',
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
})
