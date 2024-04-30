module.exports = {
    devServer: {
        hot: true,//热更新自动刷新
        proxy: {
            "/": {
                target: 'http://localhost:8080',//代理地址 凡是使用/api
                changeOrigin: true,//允许跨域请求
                secure: false,
                ws: false
            },
        }
    }
}
