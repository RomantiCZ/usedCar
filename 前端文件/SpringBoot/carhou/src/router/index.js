import {createRouter, createWebHistory} from 'vue-router'

import AdminLogin from "@/views/AdminLogin";
import HomePage from "@/views/HomePage";


import NotFoundPage from "@/views/UtilsPageCompment/NotFoundPage";
import AdminManage from "@/views/AdminPage/AdminManage";
import SaleCarManage from "@/views/AdminPage/SaleCarManage";
import RentalCarManage from "@/views/AdminPage/RentalCarManage";
import UserManage from "@/views/AdminPage/UserManage";
import OrderManage from "@/views/AdminPage/OrderManage";
import HistoryOrder from "@/views/AdminPage/HistoryOrder";

import RentalCarsAdd from "@/views/AdminPage/RentalCarsAdd";
import RentalCarsUpdate from "@/views/AdminPage/RentalCarsUpdate";

import SaleCarAdd from "@/views/AdminPage/SaleCarAdd";
import SaleCarsUpdate from "@/views/AdminPage/SaleCarsUpdate";

const routes = [

    {
        path: "/login",
        name: "adminLogin",
        component: AdminLogin
    },
    {
        path: "/",
        component: AdminLogin
    },

    {
        path: "/homePage",
        name: "HomePage",
        component: HomePage,
        children: [
            {path: "/adminManage", name: "adminManage", component: AdminManage},
            {path: "/adminmanage", component: AdminManage},

            {path: "/RentalCarManage", name: "rentalCarManage", component: RentalCarManage},
            {path: "/rentalCarManage", component: RentalCarManage},

            {path: "/SaleCarManage", name: "saleCarManage", component: SaleCarManage},
            {path: "/saleCarManage", component: SaleCarManage},

            {path: "/UserManage", name: "userManage", component: UserManage},
            {path: "/userManage", component: UserManage},

            {path: "/OrderManage", name: "orderManage", component: OrderManage},
            {path: "/orderManage", component: OrderManage},
            {path: "/HistoryOrder", name: "historyOrder", component: HistoryOrder},
            {path: "/historyOrder", component: HistoryOrder},

            {path:"/rentalCarsAdd",name: "RentalCarsAdd",component: RentalCarsAdd},
            {path:"/saleCarAdd",name: "SaleCarAdd",component: SaleCarAdd },

            {path:"/rentalCarUpdate",name:"RentalCarUpdate",component: RentalCarsUpdate},
            {path: "/saleCarUpdate",name:"SaleCarUpdate",component: SaleCarsUpdate}
        ]
    },

    {
        path: "/homepage",
        component: HomePage
    },

    {
        path: '/:catchAll(.*)',
        component: NotFoundPage
    }

]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

router.beforeEach((to, form, next) => {
    if (to.path === '/login' || to.path === '/') {
        return next()
    }
    // 检查页面是否需要验证权限
    let res = isAuthenticated()

    if (!res) {
        return next('/login')
    } else {
        return next()
    }

})

// 定义一个函数，检查用户是否已登录
function isAuthenticated() {
    // 在这里写入验证逻辑，比如检查 Cookies 或者 localStorage 中是否存在登录状态
    let AdminSeesion = JSON.parse(window.sessionStorage.getItem("admin"))
    // 如果用户已登录，则返回 true，否则返回 false
    if (!AdminSeesion) {
        return false
    } else {
        return true
    }
}

export default router
