import {
  createRouter,
  createWebHistory
} from 'vue-router'

import NotFoundPage from "@/views/UtilsCompent/NotFoundPage";

import LoginPage from "@/views/Login/LoginPage";
import UserHomePage from "@/views/UserPage/UserHomePage";

import CarRental from '@/views/UserPage/CarRental';
import CarSale from '@/views/UserPage/CarSale';
import UserInfo from '@/views/UserPage/UserInfo';
import OrderInfo from '@/views/UserPage/OrderInfo';
import CarInfo from '@/views/UserPage/CarInfo';


const routes = [
  {
    path: "/tologin",
    name: LoginPage,
    component: LoginPage
  },
  {
    path: "/login",
    component: LoginPage
  },


  {
    path: "/index",
    name: UserHomePage,
    component: UserHomePage
  },
  {
    path:"/sale",
    name:CarSale,
    component:CarSale
  },{
    path:"/carInfo",
    name:CarInfo,
    component:CarInfo
  },
  {
    path:"/carinfo",
    component:CarInfo
  },
  {
    path:"/rental"
    ,name:CarRental,
    component:CarRental
  },{
    path:"/userInfo",
    name:UserInfo,
    component:UserInfo
  },
  {
    path:"/order",
    name:OrderInfo,
    component:OrderInfo
    
  },


  {
    path: "/",
    component: UserHomePage
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
  if (to.path == "/login" || to.path == "/login" || to.path == "/index" || to.path == "/") {
    return next()
  }

  let res = checkUserlogin()

  if (!res) {
    return next("/login")
  } else {
    return next()
  }
})



function checkUserlogin() {
  let user =  JSON.parse(window.sessionStorage.getItem("user"))
  if (user) {
    return true
  } else {
    return false
  }
}


export default router