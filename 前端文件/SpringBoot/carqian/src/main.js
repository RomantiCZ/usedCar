import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import installElementPlus from './plugins/element'
import 'element-plus/theme-chalk/index.css'

import * as echarts from "echarts"
import axios from "axios";

import 'tinymce/tinymce'


import * as ElementPlusIconsVue from '@element-plus/icons-vue'

axios.defaults.baseURL = "http://localhost:8888"



const app = createApp(App)
app.config.globalProperties.$http = axios
app.config.globalProperties.$echarts = echarts;
Object.keys(ElementPlusIconsVue).forEach(key => {
    app.component(key, ElementPlusIconsVue[key])
})

installElementPlus(app)
app.use(store).use(router).mount('#app')