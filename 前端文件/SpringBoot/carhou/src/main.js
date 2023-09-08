import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import installElementPlus from './plugins/element'
import * as echarts from "echarts"
import axios from "axios";

import 'tinymce/tinymce'


import * as ElementPlusIconsVue from '@element-plus/icons-vue'

axios.defaults.baseURL = "http://localhost:8888"
let superAdminMenu=[
    {
        path: "/Homepage",
        name: "首页",
        icon: "Crop",
        index:"1"
    },
    {
        path: "/adminmanage",
        name: "管理员管理",
        icon: "Finished",
        index:"2"
    },
    {
        name: "车辆管理",
        icon: "DataLine",
        index:"3",
        children: [
            {path: "/rentalCarManage", name: "租借车辆", icon: "PieChart",index:"3-1"},
            {path: "/saleCarManage", name: "售卖车辆", icon: "Compass",index:"3-2"}
        ]
    },
    {
        name: "用户管理",
        icon: "UserFilled",
        index:"4",
        children: [
            {path: "/userManage", name: "信息管理", icon: "Avatar",index:"4-1"}
        ]
    },
    {
        name: "订单管理",
        icon:"Grid",
        index:"5",
        children: [
            {path:"/orderManage",name:"订单信息",icon: "Management",index:"5-1"},
            {path:"/historyOrder",name:"历史订单",icon: "Checked",index:"5-2",}
        ]
    }

]
let OrdinaryAdminMenu=[
    {
        path: "/Homepage",
        name: "首页",
        icon: "Crop",
        index:"1",
    },
    {
        name: "车辆管理",
        icon: "DataLine",
        index:"2",
        children: [
            {path: "/rentalCarManage", name: "租借车辆", icon: "PieChart",index:"2-1"},
            {path: "/saleCarManage", name: "售卖车辆", icon: "Compass",index:"2-2",}
        ]
    },
    {
        name: "用户管理",
        icon: "UserFilled",
        index:"3",
        children: [
            {path: "/userManage", name: "信息管理", icon: "Avatar",index:"3-2",}
        ]
    },
    {
        name: "订单管理",
        icon:"Grid",
        index:"4",
        children: [
            {path:"/orderManage",name:"订单信息",icon: "Management",index:"4-1"},
            {path:"/historyOrder",name:"历史订单",icon: "Checked",index:"4-2"}
        ]
    }
]




const app = createApp(App)
app.config.globalProperties.$http = axios
app.config.globalProperties.$SuperAdminMenu=superAdminMenu
app.config.globalProperties.$OrdinaryAdminMenu=OrdinaryAdminMenu
app.config.globalProperties.$echarts = echarts;

Object.keys(ElementPlusIconsVue).forEach(key => {
    app.component(key, ElementPlusIconsVue[key])
})



installElementPlus(app)

app.use(store).use(router).mount('#app')