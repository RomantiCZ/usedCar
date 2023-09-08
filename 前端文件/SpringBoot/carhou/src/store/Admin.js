const admin = {

    //公共state对象，存储所有组件的状态
    state:{
        admin: {
            adminId: '',
            name:'',
            role: '',
            adminToken: '',
        }
    },
    getters:{
        getAdmin(state){
            return state.admin
        },
        getSessionStoreAdmin(){
            let admin = JSON.parse(window.sessionStorage.getItem("admin"))
            return admin
        }
    },
    //唯一修改方法，同步阻塞的
    mutations: {
        updateAdmin(state, admin) {
            state.admin = admin
        },
        //登录并注入SessionStore里面
        updateSessionStoreAdmin(state,admin){
            window.sessionStorage.setItem("admin",JSON.stringify(admin))
            state.admin = admin
        },

        //退出登录
        logoutAdmin(state){
            state.admin={
                    adminId: '',
                    name:'',
                    role: '',
                    adminToken: '',
            }
            window.sessionStorage.removeItem("admin")
        }


    },
    //异步调用mutations方法
    actions: {
        asyncUpdateAdmin(context, admin) {
            context.commit('updateAdmin', admin)
        },
        //异步调用
        asyncUpdateSessionStoreAdmin(context,admin){
            context.commit('updateSessionStoreAdmin',admin)
        },

        asyncLogoutAdmin(context){
            context.commit("logoutAdmin")
        },


    },
    modules: {},

}
export default admin
