<template>
  <div class="admin_bg">
    <div class="form_left">
      <img src="../../assets/img/water.png" />
    </div>

    <div class="form_right">
      <form>
        <header>
          <h1>华辰二手租借</h1>
          <p>请先登录</p>
        </header>

        <el-form
          label-position="top"
          :model="userForm"
          :rules="loginRule"
          ref="loginForm"
        >
          <el-form-item class="form_acc" prop="userName">
            <p class="form_acc_text">账号：</p>
            <el-input
              v-model="userForm.userName"
              placeholder="请填写账号"
              clearable
            />
          </el-form-item>

          <el-form-item class="form_pass" prop="password">
            <p class="form_pass_text">密码：</p>
            <el-input
              v-model="userForm.password"
              placeholder="请填写密码"
              type="password"
              show-password
              clearable
            />
          </el-form-item>

          <div class="bt_group">
            <el-button type="primary" id="bt_login" @click="loginUser"
              >登录</el-button
            >

            <el-button type="success" id="bt_reset" @click="toRegister"
              >注册</el-button
            >
          </div>
        </el-form>
      </form>
    </div>

    <el-dialog
      v-model="dialogVisible"
      title="用户注册"
      width="40%"
      :before-close="handleClose"
    >
      <el-form
        ref="registerFormRef"
        :model="registerForm"
        :rules="registerRules"
        label-width="120px"
        class="demo-dynamic"
      >
        <el-form-item label="账号：" prop="userName">
          <el-input
            v-model="registerForm.userName"
            autocomplete="off"
            clearable
          />
        </el-form-item>

        <el-form-item label="密码：" prop="password">
          <el-input
            v-model="registerForm.password"
            show-password
            type="password"
            autocomplete="off"
            clearable
          />
        </el-form-item>

        <el-form-item label="名称：" prop="name">
          <el-input v-model="registerForm.name" autocomplete="off" clearable />
        </el-form-item>

        <el-form-item label="性别:" prop="sex">
          <el-radio-group v-model="registerForm.sex" class="ml-4">
            <el-radio label="男" size="large">男</el-radio>
            <el-radio label="女" size="large">女</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="证件号：" prop="idCard">
          <el-input
            v-model="registerForm.idCard"
            autocomplete="off"
            clearable
          />
        </el-form-item>

        <el-form-item label="联系方式：" prop="tel">
          <el-input v-model="registerForm.tel" autocomplete="off" clearable />
        </el-form-item>

        <el-form-item label="电子邮箱：" prop="email">
          <el-input v-model="registerForm.email" autocomplete="off" clearable />
        </el-form-item>

        <el-button @click="addUser" class="save_btn" color="#488c6d" plain>
          注册
        </el-button>

        <el-button @click="resetUser" class="res_btn" color="#488c6d" plain>
          重置
        </el-button>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
const checkUsername = (rules, value, cb) => {
  const usernameReg = /^[a-zA-Z0-9]+$/;
  if (usernameReg.test(value)) {
    return cb();
  } else {
    return cb("账号必须是英文或数字");
  }
};

const cehckEmail = (rules, value, cb) => {
  const emailReg = /^[a-zA_Z0-9_-]+@([a-zA_Z0-9_-])+(\.[a-zA_Z0-9_-])+/;

  if (emailReg.test(value)) {
    return cb();
  }
  return cb("请输入正确的邮箱信息");
};

const checktel = (rules, value, cb) => {
  const telReg =
    /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
  if (telReg.test(value)) {
    return cb();
  }
  return cb("请输入正确的电话号码");
};
const checkIdCard = (rules, value, cb) => {
  const idcardReg =
    /^((1[1-5])|(2[1-3])|(3[1-7])|(4[1-6])|(5[0-4])|(6[1-5])|[7-9]1)\d{4}(19|20|21)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
  if (idcardReg.test(value)) {
    return cb();
  } else {
    return cb("请输入正确的身份证号码");
  }
};
export default {
  name: "LoginPage",
  data() {
    return {
      userForm: {
        userName: "",
        password: "",
      },
      loginRule: {
        userName: [
          { required: true, message: "请输入用户账号", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入用户密码", trigger: "blur" },
        ],
      },
      registerForm: {
        userName: "",
        password: "",
        name: "",
        sex: "",
        idCard: "",
        tel: "",
        email: "",
      },
      registerRules: {
        userName: [
          { required: true, message: "账号不能为空", trigger: "blur" },
          { validator: checkUsername, trigger: "blur" },
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" },
        ],
        sex: [{ required: true, message: "性别不能为空", trigger: "blur" }],
        name: [{ required: true, message: "名称不能为空", trigger: "blur" }],
        tel: [
          { required: true, message: "联系方式不能为空", trigger: "blur" },
          { validator: checktel, trigger: "blur" },
        ],
        idCard: [
          { required: true, message: "身份信息不能为空", trigger: "blur" },
          { validator: checkIdCard, trigger: "blur" },
        ],
        email: [
          { required: true, message: "邮箱不能为空", trigger: "blur" },
          { validator: cehckEmail, trigger: "blur" },
        ],
      },
      dialogVisible: false,
    };
  },

  methods: {
    async loginUser() {
      this.$refs.loginForm.validate(async (res) => {
        if (!res) {
          return;
        } else {
          this.$http.post("/user/login", this.$data.userForm).then((res) => {
            if (res.data.code == "200") {
              let user = {
                userId: res.data.data.userId,
                name: res.data.data.name,
                userToken: res.data.data.userToken,
              };
              this.$store.dispatch("asyncUpdateSessionStoreUser", user);
              this.$router.push("/index");
            } else {
              this.$message.error(res.data.msg);
            }
          });
        }
      });
    },

    toRegister() {
      this.$data.dialogVisible = true;
    },

    handleClose() {
      this.$data.dialogVisible = false;
    },

    async addUser() {
      this.$refs.registerFormRef.validate(async (res) => {
        if (!res) {
          return;
        } else {
          await this.$http
            .post("/user/add", this.$data.registerForm)
            .then((res) => {
              if (res.data.code == "200") {
                this.$message.success(res.data.data);
                this.handleClose();
                Object.assign(
                  this.$data.registerForm,
                  this.$options.data().registerForm
                );
              } else {
                this.$message.error(res.data.msg);
                this.$message.info("请重新输入信息");
                Object.assign(
                  this.$data.registerForm,
                  this.$options.data().registerForm
                );
              }
            });
        }
      });
    },

    resetUser() {
      Object.assign(this.$data.registerForm, this.$options.data().registerForm);
      this.$message.info("重置成功");
    },
  },
};
</script>

<style scoped>
.admin_bg {
  background: url("../../assets/bg/ferrai_qian.jpg") no-repeat center;
  height: 100%;

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
  margin-top: 10rem;
  margin-left: 23rem;
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
  margin-left: 36rem;
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

.form_acc,
.form_pass {
  margin-left: 5rem;
}

.form_acc_text,
.form_pass_text {
  font-size: 1rem;
  color: #aeaeae;
  margin-left: -3rem;
}

.form_acc {
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

#bt_login,
#bt_reset {
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

.cx {
  width: 100%;
  height: 100%;
  background-color: #94dfd8;
}

.save_btn,
.res_btn {
  width: 6rem;
  height: 3rem;
  margin-top: 1rem;
}

.save_btn {
  margin-left: 10rem;
}

.res_btn {
  margin-left: 3rem !important;
}
</style>