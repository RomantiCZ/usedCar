<template>
  <div class="common-layout">
    <el-container>


      <el-aside :width=sliderWidth>
        <div class="all_aside">

          <div class="aside_img">
            <img :src="asideImg"
                 :style="{ width: asideImgWidth + 'px', height: asideImgHeight + 'px' }"
                 class="animate"
            >

          </div>

          <div class="aside_menu">

            <div class="aside_menu_detail" v-if="this.$store.getters.getSessionStoreAdmin.role == '超级管理员'">

              <el-menu

                  default-active="1"
                  class="el-menu-vertical-demo menu_show"
                  :collapse="collapse"
                  :width="asideImgWidth"
                  :router="true"
                  background-color="#304156"
                  text-color="#f9f9f9"
                  collapse-transition="true"
                  popper-class="my-popper"
                  active-text-color="#f0f0f0"
                  placement="bottom-start"
              >
                <template v-for="(item,index) in this.$SuperAdminMenu">
                  <template v-if="item.children && item.children.length">

                    <el-sub-menu class="sub_menu2"  :index="item.index" :route="item.path">
                      <template #title>
                        <el-icon><component :is="item.icon" /></el-icon>
                        <span>{{ item.name }}</span>
                      </template>
                      <div class="menu_show sub_show">
                      <el-menu-item  class="sub_menu23" v-for="(j,k) in item.children" :index="j.index" :route="j.path">
                        <template #title>
                          <el-icon><component :is="j.icon" /></el-icon>
                          <span>{{ j.name }}</span>
                        </template>
                      </el-menu-item>
                      </div>
                    </el-sub-menu>

                  </template>

                  <template v-else>
                    <el-menu-item :index="item.index" :route="item.path">
                      <el-icon><component :is="item.icon" /></el-icon>
                      <template #title>
                       <span class="show_span">{{item.name}}</span>
                      </template>
                    </el-menu-item>
                  </template>

                </template>


              </el-menu>

            </div>

            <div class="aside_menu_detail" v-else="">
              <el-menu

                  default-active="1"
                  class="el-menu-vertical-demo menu_show"
                  :collapse="collapse"
                  :width="asideImgWidth"
                  :router="true"
                  background-color="#304156"
                  text-color="#f9f9f9"
                  collapse-transition="true"
                  popper-class="my-popper"
                  active-text-color="#229d5b"
                  placement="bottom-start"
              >
                <template v-for="(item,index) in this.$OrdinaryAdminMenu">
                  <template v-if="item.children && item.children.length">

                    <el-sub-menu class="sub_menu2"  :index="item.index" :route="item.path">
                      <template #title>
                        <el-icon><component :is="item.icon" /></el-icon>
                        <span>{{ item.name }}</span>
                      </template>
                      <div class="menu_show sub_show">
                        <el-menu-item  class="sub_menu23" v-for="(j,k) in item.children" :index="j.index" :route="j.path">
                          <template #title>
                            <el-icon><component :is="j.icon" /></el-icon>
                            <span>{{ j.name }}</span>
                          </template>
                        </el-menu-item>
                      </div>
                    </el-sub-menu>

                  </template>

                  <template v-else>
                    <el-menu-item class="sub_menu2" :index="item.index" :route="item.path">
                      <el-icon><component :is="item.icon" /></el-icon>
                      <template #title>
                        <span class="show_span">{{item.name}}</span>
                      </template>
                    </el-menu-item>
                  </template>

                </template>


              </el-menu>

            </div>


          </div>

        </div>

      </el-aside>

      <el-container>

        <el-header>
          <div class="all_header">

            <div class="change_icon">
              <el-icon class="head_icon" color=#2494f2 @click="sliderChange()">
                <ElementPlus/>
              </el-icon>
            </div>

            <div class="title">
              <p>
                二手车后台管理系统
              </p>

            </div>

            <div class="admin_msg">

              <div class="admin_drop_menu">
                <el-dropdown placement="bottom-end" fixed="bottom" trigger="hover" @command="handleCommand">
                  <span class="el-dropdown-link">

                    <el-icon class="el-icon--right drown_icon">
                      <caret-bottom/>
                    </el-icon>
                  </span>
                  <template #dropdown>
                    <el-dropdown-menu>
                      <el-dropdown-item command="editMsg">个人信息</el-dropdown-item>
                      <el-dropdown-item command="logout">退出系统</el-dropdown-item>

                    </el-dropdown-menu>
                  </template>
                </el-dropdown>

              </div>

              <div class="block admin_Img">
                <el-avatar :size="60" :src="adminImg"/>
              </div>


              <div class="admin_name">
                <p>
                  {{admin_Name}}
                </p>
              </div>

            </div>

          </div>

        </el-header>


        <el-main>

          <template v-if="this.$route.path === '/Homepage'">
            123456
          </template>

            <router-view />



        </el-main>

      </el-container>

    </el-container>

  </div>

  <el-dialog
      v-model="editLoginAdminVisible"
      title="Edit Admin"
      width="35%"
  >
    <el-form
        ref="adminUpdateFormRef"
        :model="editAdminForm"
        :rules="adminEditRules"
        label-width="120px"
    >

      <el-form-item label="名称:" prop="name" >
        <el-input v-model="editAdminForm.name" class="edit_name"
                  :disabled="this.$store.getters.getSessionStoreAdmin.role =='超级管理员'?false:true"
        />
      </el-form-item>

      <el-form-item label="账号:" prop="username" >
        <el-input v-model="editAdminForm.username" disabled class="edit_username"/>
      </el-form-item>

      <el-form-item label="密码:" prop="password" >
        <el-input type="password" show-password v-model="editAdminForm.password" clearable class="edit_password" />
      </el-form-item>

      <el-form-item label="性别:" prop="sex" >
        <el-radio-group v-model="editAdminForm.sex" class="ml-4">
          <el-radio label="男" size="large">男</el-radio>
          <el-radio label="女" size="large">女</el-radio>
        </el-radio-group>
      </el-form-item>


      <el-form-item label="联系方式:" prop="tel" >
        <el-input v-model="editAdminForm.tel" class="edit_tel" />
      </el-form-item>

      <el-form-item label="身份信息:" prop="idCard" >
        <el-input v-model="editAdminForm.idCard" class="edit_idCard"
                  :disabled="this.$store.getters.getSessionStoreAdmin.role =='超级管理员'?false:true"
        />
      </el-form-item>

      <el-form-item label="电子邮箱:" prop="email" >
        <el-input v-model="editAdminForm.email"
                  class="edit_email"/>

      </el-form-item>

    <el-button class="save_bt" color="#488c6d" plain @click="updateAdmin">
       保存
    </el-button>
    </el-form>

  </el-dialog>


</template>

<script>
import {ArrowDown, Operation, ElementPlus, CaretBottom} from "@element-plus/icons-vue"
const cehckEmail=(rules,value,cb)=>{
  const emailReg=/^[a-zA_Z0-9_-]+@([a-zA_Z0-9_-])+(\.[a-zA_Z0-9_-])+/

  if (emailReg.test(value)){
    return cb()
  }
  return cb('非法邮箱')
}

const checktel=(rules,value,cb)=>{
  const telReg=/^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
  if (telReg.test(value)){
    return cb()
  }
  return cb('请输入正确的电话号码')
}

export default {
  name: "HomePage",
  data() {
    return {
      sliderFlag: true,
      collapse: false,
      sliderWidth: " 8rem ",
      adminImg: require("../assets/img/omo.png"),
      asideImg: require("../assets/img/leaf.png"),
      asideImgWidth: "128",
      asideImgHeight: "128",
      icon: "location",
      editLoginAdminVisible:false,
      admin_Name:this.$store.getters.getSessionStoreAdmin.name,
      editAdminForm:{
        adminId:'204070615',
        username:"xxxxxxxx",
        password:"gnqwoegnoiwe2156",
        name:"xxxx",
        sex:"男",
        tel:"11020088888",
        idCard:"411082199311100619",
        email:"1051100100@qq.com",
      },
      adminEditRules:{
        password:[
          {required: true, message: "密码不能为空", trigger: 'blur'}
        ],
        tel:[
          {required: true, message: "电话不能为空", trigger: 'blur'},
          {validator:checktel,trigger: 'blur'}
            ],
        idCard:[
          {required: true, message: "身份证信息不能为空", trigger: 'blur'}

          ],
        email:[
          {required: true, message: "电子邮箱不能为空", trigger: 'blur'},
          {validator:cehckEmail,trigger:'blur'}
          ],

      }
    }
  },
  components: {
    Operation, ElementPlus, ArrowDown, CaretBottom
  },
  mounted() {
    window.addEventListener("storage",event=>{

    })
    },
  created() {
   // this.getAdminMsg()
  },
  methods: {
    sliderChange() {
      if (this.$data.sliderFlag) {
        this.$data.sliderWidth = "3.975rem"
        this.$data.sliderFlag = false
        this.$data.collapse = true
        this.$data.asideImgHeight = this.$data.asideImgWidth = 45
        // this.$nextTick(() => {
        //   const el = this.$el.querySelector('.animate')
        //   el.classList.remove('animate')
        //   void el.offsetWidth;
        //   el.classList.add('animate')
        // })
      } else {
        this.$data.sliderWidth = "8rem"
        this.$data.sliderFlag = true
        this.$data.collapse = false
        this.$data.asideImgHeight = this.$data.asideImgWidth = 128
        // this.$nextTick(() => {
        //   const el = this.$el.querySelector('.animate')
        //   el.classList.remove('animate')
        //   void el.offsetWidth;
        //   el.classList.add('animate')
        // })
      }
    },


    async handleCommand(command) {
      if (command == "logout") {
       await this.$store.dispatch("asyncLogoutAdmin")
            .then( res => {
              this.$message.info("退出登录")
              this.$router.push("/login")
            })

      } else {
        this.$data.editLoginAdminVisible=true
        //console.log(this.$store.getters.getSessionStoreAdmin.adminId)
        await this.$http.get(`/admin/getAdminMsg/`
            +this.$store.getters.getSessionStoreAdmin.adminId).then(res=>{
              this.$data.editAdminForm = res.data.data
        })
      }
    },

    async getAdminMsg(){
      let msg = this.$store.getters.getSessionStoreAdmin
      console.log("当前状态为：")
      console.log(msg)
    },

    async updateAdmin(){
         this.$refs.adminUpdateFormRef.validate(async res=>{
           if (!res){
             return
           }else{
            // this.$message.success("yes")
            //  console.log(this.$data.editAdminForm)
            await this.$http.post("/admin/updateAdmin",this.$data.editAdminForm).then(async res=>{
               if (res.data.code=="200"){
                 this.$message.success(res.data.msg)
                 await this.$store.dispatch("asyncUpdateSessionStoreAdmin",{
                       adminId: this.$data.editAdminForm.adminId, name: this.$data.editAdminForm.name,
                       role: this.$data.editAdminForm.role,
                       adminToken: this.$store.getters.getSessionStoreAdmin.adminToken
                 }

                 )

               }else{
                 this.$message.success(res.data.msg)
               }
              this.$data.editLoginAdminVisible=false
              location.reload()
            })
           }
         })
    }


  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}

.common-layout {

  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
}

.el-header {
  background: linear-gradient(to right, #535a6c, #548f8b);
  height: 5rem;
  box-sizing: border-box;
}

.el-container {
  height: 100%;
}

.el-aside {
  /*background: linear-gradient(to bottom, #535a6c, #548f8b);*/
  transition: width 0.5s ease-out;
  box-sizing: border-box;
  background-color: #304156;
}

.el-main {
  background-color: #f2f4f8;
}

.all_header {
  width: 100%;
}

.all_aside {
  box-sizing: border-box;
}


.head_icon {
  font-size: 2.5rem;
}

.admin_msg {
  float: right;
  margin-right: 0.5rem;
  position: relative;
  margin-top: -2rem;
}

.admin_Img {
  float: right;
  margin-top: -2.3rem;

}

.title {
  font-size: 1.5rem;

}

.title p {
  color: #f0f0f0;
}

.admin_name {
  float: left;
  margin-left: 1rem;
}

.admin_name p {
  color: #f0f0f0;
}

.admin_drop_menu {
  float: right;
  margin-top: 0.5rem;
  /*position: absolute;*/
  margin-left: -0.5rem;
}

.drown_icon {
  color: #2a282a;
}

.aside_img {
  transition: all 5s ease-in-out 4s;
}

.animate {
  animation: living 1s ease-in-out 1;
}


@keyframes living {
  0% {
    transform: scale(1);
    opacity: 0;
  }
  50% {
    transform: scale(1);
    opacity: 0;
  }
  100% {
    transform: scale(1);
    opacity: 0.5;
  }
}


.menu_show{
  transition: width 0.5s ease-out;
  box-sizing: border-box;
}
.sub_show span{
  color: #d5cece;
}


.sub_menu23{
  min-width: 128px!important;
}
.save_bt{
  width: 6rem;
  height: 2.3rem;
  margin-left: 13rem;
  margin-top: 1rem;
}



</style>