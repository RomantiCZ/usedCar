<template>
  <div class="my_body">
    <div class="head">
      <!-- 图片 -->

      <div class="head_img">
        <img :src="head_img" class="imgByhead" />
      </div>

      <!-- 标头 -->
      <div class="head_title">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 4500 300">
          <title>Porsche</title>
          <path
            d="M502 221c48.1 0 74-25.9 74-74V74c0-48.1-25.9-74-74-74H0v300h68v-79h434zm6-143v65c0 7.8-4.2 12-12 12H68V66h428c7.8 0 12 4.2 12 12zm228 222c-48.1 0-74-25.9-74-74V74c0-48.1 25.9-74 74-74h417c48.1 0 74 25.9 74 74v152c0 48.1-25.9 74-74 74H736zm411-66c7.8 0 12-4.2 12-12V78c0-7.8-4.2-12-12-12H742c-7.8 0-12 4.2-12 12v144c0 7.8 4.2 12 12 12h405zm675-36c39.844 16.757 67.853 56.1 68 102h-68c0-54-25-79-79-79h-361v79h-68V0h502c48.1 0 74 25.9 74 74v50.14c0 46.06-23.75 71.76-68 73.86zm-12-43c7.8 0 12-4.2 12-12V78c0-7.8-4.2-12-12-12h-428v89h428zm162-81c0-48.1 25.9-74 74-74h492v56h-486c-7.8 0-12 4.2-12 12v42c0 7.8 4.2 12 12 12h422c48.1 0 74 25.9 74 74v30c0 48.1-25.9 74-74 74h-492v-56h486c7.8 0 12-4.2 12-12v-42c0-7.8-4.2-12-12-12h-422c-48.1 0-74-25.9-74-74V74zm661 0c0-48.1 25.9-74 74-74h480v66h-474c-7.8 0-12 4.2-12 12v144c0 7.8 4.2 12 12 12h474v66h-480c-48.1 0-74-25.9-74-74V74zM3817 0v300h-68V183h-407v117h-68V0h68v117h407V0h68zm156 56v66h527v56h-527v66h527v56h-595V0h595v56h-527z"
          ></path>
        </svg>
      </div>
    </div>

    <!-- 菜单 -->

    <div class="head_menu">
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo my_menu"
        mode="horizontal"
        :router="true"
      >
        <el-menu-item index="/index"> 首页 </el-menu-item>

        <el-sub-menu index="2">
          <template #title>车辆信息</template>
          <el-menu-item index="/rental"> 租借车辆 </el-menu-item>
          <el-menu-item index="/sale"> 售卖车辆 </el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="3">
          <template #title>个人中心</template>

          <el-menu-item index="/order"> 订单信息 </el-menu-item>
          <el-menu-item index="/userInfo"> 我的信息 </el-menu-item>
          <el-menu-item @click="logout"> 退出 </el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>

    <div class="user_main">
      <!-- 头像上传 -->
      <div class="user_uploadHead">
        <el-upload
          class="avatar-uploader my_upload"
          :show-file-list="false"
          ref="uploadFile"
          accpet=".jpg,.png,.gif,.webp,.jepg"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :action="null"
          name="multipartPhoto"
        >
          <div>
            <template v-if="imageUrl">
              <img :src="imageUrl" class="avatar" />

              <el-icon class="avatar_edit">
                <Edit />
              </el-icon>
            </template>

            <template v-else>
              <el-icon class="avatar-uploader-icon">
                <Plus />
              </el-icon>
            </template>
          </div>
        </el-upload>
      </div>
    </div>

    <!-- 表单 -->
    <div class="user_form">
      <el-form
        ref="userInfoRef"
        :model="userInfo"
        status-icon
        :rules="updateRules"
        label-width="120px"
        class="demo-ruleForm">

        <el-form-item label="账号" prop="userName" class="userForm_userName">
          <el-input
            v-model="userInfo.userName"
            clearable
            disabled
            autocomplete="off"
          />
        </el-form-item>

        <el-form-item label="密码" prop="password" class="userForm_password">
          <el-input
            v-model="userInfo.password"
            type="password"
            clearable
            autocomplete="off"
          />
        </el-form-item>

        <el-form-item label="名称" prop="name" class="userForm_name">
          <el-input
            v-model="userInfo.name"
            clearable
           
            autocomplete="off"
          />
        </el-form-item>

      

        <el-form-item label="性别:" prop="sex">
          <el-radio-group v-model="userInfo.sex" class="ml-4">
            <el-radio label="男" size="large">男</el-radio>
            <el-radio label="女" size="large">女</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="证件号码" prop="idCard" class="userForm_idCard">
          <el-input
            v-model="userInfo.idCard"
            clearable
            autocomplete="off"
          />
        </el-form-item>


        <el-form-item label="联系方式" prop="tel" class="userForm_tel">
          <el-input
            v-model="userInfo.tel"
            clearable
            autocomplete="off"
          />
        </el-form-item>

        <el-form-item label="电子邮箱" prop="email" class="userForm_email">
          <el-input
            v-model="userInfo.email"
            clearable
            autocomplete="off"
          />
        </el-form-item>


        <el-button @click="updateUser" class="save_btn" color="#488c6d" plain>
          保存
        </el-button>

  



      </el-form>
    </div>
  </div>
</template>

<script>
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
  name: "UserInfo",
  created(){
      this.getUserInfo()
  },  
  data() {
    return {
      head_img: require("../../assets/img/Porsche.png"),
      imageUrl: "",
      utilsimageUrl: "",
      userInfo: {
        userId: "", //用户ID
        userName: "", //用户名
        password: "", //密码
        name: "", //名称
        sex: "", //性别
        idCard: "", //身份证信息
        tel: "", //电话号码
        email: "", //电子邮箱
        img: "", //头像
        status: "", //用户状态
        createTime: "", //创建时间
        updateTime: "", //更新时间
      },
      updateRules: {
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
    };
  },
  methods: {
    logout() {
      this.$store.dispatch("asyncLogoutUser");
      this.$router.push("/login");
    },

    async  getUserInfo(){
      let userId = this.$store.getters.getTokenUser.userId
      // console.log(userId)
       await this.$http.get("/user/getUserInfo/"+userId)
       .then(res=>{
        this.$data.userInfo = res.data.data
        if(res.data.data.img!=null){
          this.$data.utilsimageUrl = res.data.data.img
          this.handleAvatarSuccess()
        }else{
          
        }

       })

       
     
    },

    async beforeAvatarUpload(file) {
      let formdata = new FormData();
      formdata.append("multipartPhoto", file);
      await this.$http
        .post("/user/addImg", formdata, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then((res) => {
          if (res.data.code == "200") {
            this.$message.success(res.data.msg);
            this.$data.utilsimageUrl = res.data.data;
            this.$data.userInfo.img = res.data.data;
            this.handleAvatarSuccess();
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          console.log(err);
          this.$message.error("发生未知错误");
        });
    },

    async handleAvatarSuccess() {
      try {
        const response = await this.$http
          .get("/user/downloadImg", {
            params: { name: this.$data.utilsimageUrl },
            responseType: "blob",
          })
          .then((res) => {
            let databolb = new Blob([res.data]);
            let url = window.URL.createObjectURL(databolb);
            this.$data.imageUrl = url;
          });
      } catch (error) {
        this.$message.error("发生未知错误");
      }
    },
    async updateUser(){
        // console.log(this.$data.userInfo)
          await this.$http.post("/user/update",this.$data.userInfo).then(res=>{
            if(res.data.code=="200")
            {
              this.$message.success(res.data.data)
              this.$router.push("/index")
            }else{
              this.$message.error(res.data.msg)
            }
          }).catch(err=>{
            this.$message.error("发生未知错误")
          })
      },
  },
};
</script>

<style scoped>
.head {
  /* background: #99a9bf; */
  height: 8rem;
}
.head_img {
  float: left;
  width: 10rem;
  height: 8rem;
  margin-left: 1rem;
}
.head_title {
  float: left;
  margin-left: 23rem;
  margin-top: 3rem;
  width: 20rem;
}

.head_menu {
  width: 30rem;
  margin-left: 57.5rem;
  margin-top: -3rem;
}

/* 上传头像的 */

.my_upload {
  margin-top: 10rem;
  margin-left: 10rem;
}

.avatar-uploader .avatar {
  width: 15rem;
  height: 15rem;
  display: block;
}

.avatar_edit {
  font-size: 45px;
  color: #409eff;
  /*width: 178px;*/
  /*height: 178px;*/
  float: left;
  margin-top: -9rem;
  margin-left: 6.5rem;
}

/* 表单 */
.user_form{
  width:50rem;
  margin-left: 30rem;
  margin-top: -18rem;
}

.save_btn{
  width: 8rem;
  height: 3rem; 
  margin-top: 1rem;
  margin-left: 20rem;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 10rem;
  cursor: pointer;
  position: relative;
  overflow: hidden;

  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 15rem;
  height: 15rem;

  /*text-align: center;*/
}

/*
新增的
*/
</style>