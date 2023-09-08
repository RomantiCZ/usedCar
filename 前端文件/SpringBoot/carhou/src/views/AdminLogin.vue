<template>

  <div class="admin_bg">

    <div class="form_left">
      <img src="../assets/img/head.png">
    </div>

    <div class="form_right">

      <form>
        <header>
          <h1>欢迎回来管理员</h1>
          <p>请先登录</p>
        </header>

        <el-form
            label-position="top"
            :model="adminForm"
            :rules="loginRule"
            ref="loginForm"
        >

          <el-form-item class="form_acc" prop="username">
            <p class="form_acc_text">
              账号：
            </p>
            <el-input v-model="adminForm.username"
                      placeholder="请填写账号" clearable/>
          </el-form-item>

          <el-form-item class="form_pass" prop="password">
            <p class="form_pass_text">密码：</p>
            <el-input v-model="adminForm.password"
                      placeholder="请填写密码" type="password" show-password clearable/>
          </el-form-item>

          <div class="bt_group">
            <el-button type="primary" id="bt_login" @click="loginAdmin">登录</el-button>

            <el-button type="success" id="bt_reset" @click="reset">重置</el-button>

          </div>

        </el-form>

      </form>

    </div>

  </div>




</template>

<script>

export default {
  name: "AdminLogin",
  data() {
    return {
      adminForm: {
        username: "",
        password: "",
      },
      loginRule: {
        username: [
          {required: true, message: '请输入管理员账号', trigger: 'blur'},

        ],
        password: [
          {required: true, message: "请输入管理员密码", trigger: 'blur'}
        ]
      }


    }
  },

  methods: {
    async loginAdmin() {
      this.$refs.loginForm.validate(async res => {
        if (!res) {
          return
        } else {

          let admin = {
            username: this.$data.adminForm.username,
            password: this.$data.adminForm.password
          }
          await this.$http.post("/admin/login", admin)
              .then(res => {
                // console.log(res)
                let {data} = res
                //console.log(data)
                if (data.code == "200") {
                  this.$message.success(data.msg)
                  let admin = {
                    adminId: data.data.adminId,
                    name: data.data.name,
                    role: data.data.role,
                    adminToken: data.data.adminToken
                  }
                  this.$store.commit('updateSessionStoreAdmin',admin)
                  // await this.$store.dispatch("asyncUpdateSessionStoreAdmin",{
                  //       adminId: data.data.adminId, name: data.data.name,
                  //       role: data.data.role, adminToken: data.data.adminToken
                  //     })

                   // console.log( this.$store.getters.getAdmin)
                  this.$router.push("/homepage")
                }else{
                  this.$message.error(data.msg)
                }

              })

        }
      })
    },
    reset() {
      this.$data.adminForm.username = ''
      this.$data.adminForm.password = ''
      this.$message.success("信息重置成功")
    }
  }
}
</script>

<style scoped>


.admin_bg {
  background: url("../assets/bg/ferrari_hou.png") no-repeat center;
  height: 100%;
  margin: -1rem;
  width: 102.3%;
  background-size: cover;
  position: fixed;
}

.form_left {
  background-color: rgba(255, 255, 255, 0.9);
  border-radius: 1.5rem 0 0 1.5rem;
  padding: 0 5rem;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  position: relative;
  width: 13rem;
  height: 30rem;
  margin-top: 8rem;
  margin-left: 30rem;
  z-index: 1;

}

.form_left img {
  display: block;
  position: relative;
  width: 10rem;
  height: 10rem;
  border-radius: 50%;
  box-shadow: 0 5px 5px rgba(0, 0, 0, 0.2);
  z-index: 10;
  margin-left: -3rem;
  background-color: rgb(255, 255, 255);
}

.form_right {

  text-align: center;
  margin-bottom: 15px;
  width: 30rem;
  height: 30rem;
  position: relative;
  box-sizing: border-box;
  display: flex;
  position: relative;
  margin-left: 43rem;
  margin-top: -30rem;
  border-radius: 0 1.5rem 1.5rem 0;
  background-color: #ffffff;
  background-color: rgba(46, 44, 44, 0.5);
}

.form_right form header {
  color: #fff;
  text-align: center;
  margin-left: 3.2rem;
  margin-top: 1.5rem;
}

.form_right form header h1 {
  font-size: 2rem;
}

.form_right form header p {
  margin-top: 1.5rem;
}

.form_acc, .form_pass {
  margin-left: 5rem;

}

.form_acc_text, .form_pass_text {
  font-size: 1rem;
  color: #aeaeae;
  margin-left: -3rem;
}
.form_acc{
  margin-top: 2rem;
}
.form_pass {
  margin-top: 5rem;
}

.el-input {
  width: 22rem;
}

.bt_group {
  position: fixed;
}

#bt_login, #bt_reset {
  width: 8rem;
  height: 3rem;
  margin-top: 3rem;
}

#bt_login {
  margin-left: 6rem;
}

#bt_reset {
  margin-left: 3rem;
}
.cx{
  width: 100%;
  height: 100%;
  background-color: #94dfd8;
}

</style>