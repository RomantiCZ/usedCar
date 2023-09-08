const User = {

//公共state对象，存储所有组件的状态
    state: {
        user: {
            userId: '',
            name: '',
            img:'',
            userToken: '',
        }
    },
    getters:{
        getUser(state){
            return state.user
        },
        getTokenUser(){
            let user = JSON.parse(window.sessionStorage.getItem("user"))
            return user
        }

    },
    //唯一修改方法，同步阻塞的
    mutations:{
            updateUser(state,user){
                state.user = user
            },
            updateSessionStoreUser(state,user){
                window.sessionStorage.setItem("user",JSON.stringify(user))
                state.user = user
            },
            logoutUser(state){
                state.user={
                    userId: '',
                    name: '',
                    img:'',
                    userToken: '',
                }
                window.sessionStorage.removeItem("user")
            }

    },
    //异步调用mutations方法
    actions:{
        asyncUpdateUser(context,user){
            context.commit("updateUser",user)
        },
        asyncUpdateSessionStoreUser(context,user){
            context.commit("updateSessionStoreUser",user)
        },
        asyncLogoutUser(context){
            context.commit("logoutUser")
        }
    },
    modules: {},

}
export default User