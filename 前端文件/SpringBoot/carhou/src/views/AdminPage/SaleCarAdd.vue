<template>
  <div class="header_all">

    <div class="header_left">
      <span class="sp1">Sale Vehicles</span>

      <span class="sp2">Add</span>

      <span class="sp3">出售车辆</span>

      <span class="sp4">添加</span>

    </div>

    <div class="header_right">

      <div class="right_characters">
        <el-breadcrumb separator-icon="ArrowRight">
          <el-breadcrumb-item :to="{ path: '/homepage' }">
            <span>首页</span>
          </el-breadcrumb-item>

          <el-breadcrumb-item :to="{ path: '/saleCarManage' }">
            <span>出售车辆管理</span>
          </el-breadcrumb-item>

          <el-breadcrumb-item>
            <span>出售车辆添加</span>
          </el-breadcrumb-item>

        </el-breadcrumb>
      </div>


    </div>
  </div>

  <div class="add_main">
    <div class="main_img">

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
            <img :src="imageUrl" class="avatar"/>

            <el-icon class="avatar_edit">
              <Edit/>
            </el-icon>

          </template>

          <template v-else>
            <el-icon class="avatar-uploader-icon">
              <Plus/>
            </el-icon>
          </template>


        </div>


      </el-upload>


    </div>
  </div>

  <div class="add_form">
    <el-form
        class="details_form"
        label-position="right"
        ref="addFormRef"
        size="large"
        :model="addform"
        :rules="addRules"
    >
      <el-form-item label="品牌名称：" prop="brand" class="addform_brand">
        <el-input v-model="addform.brand" clearable/>
      </el-form-item>

      <el-form-item label="款式名称：" prop="model" class="addform_model">
        <el-input v-model="addform.model" clearable/>
      </el-form-item>

      <el-form-item label="制造年份：" prop="year" class="addform_year">
        <el-input v-model="addform.year" clearable placeholder="输入格式为YYYY 比如2023"/>
        <el-tag class="ml-2 yaer_tag" size="large"  effect="dark">
            年
        </el-tag>
      </el-form-item>

      <el-form-item label="里程数：" prop="mileage" class="addform_mileage">
        <el-input v-model="addform.mileage" clearable placeholder="可带两位小数"/>
        <el-tag class="ml-2 mileage_tag" size="large" type="success"  effect="dark">
          万 公里
        </el-tag>
      </el-form-item>

      <el-form-item label="价格：" prop="price" class="addform_Price">
        <el-input v-model="addform.price" clearable placeholder="输入格式为xxxx.xx后面带两位小数"/>
        <el-tag class="ml-2 price_tag" size="large" type="success"  effect="dark">
          W
        </el-tag>
      </el-form-item>

      <el-form-item label="详情：" prop="description" class="addform_description">
        <TinymceEditor v-model="addform.description">

        </TinymceEditor>
      </el-form-item>

      <el-button @click="saveCar" class="save_btn" color="#488c6d" plain>
        保存
      </el-button>

      <el-button @click="reset" class="reset_btn" color="#488c6d" plain>
        重置
      </el-button>

    </el-form>
  </div>

</template>

<script>
import TinymceEditor from "@/components/TinymceEditor";
const testCarYear = (rules, value, cb) => {

  const yearRules = /^19[9][0-9]|200[0-9]|20[1-9][0-9]$/
  if (yearRules.test(value) ) {
    return cb()
  } else {
    return cb("请输入正确的年月格式")
  }
}

const testMileage = (rules, value, cb) => {
  const mileageRules = /^\d+(?:\.\d{1,2})?$/
  if (mileageRules.test(value)) {
    return cb()
  } else {
    return cb("请输入正确的里程数")
  }
}

const testCarPrice = (rules, value, cb) => {
  const priceRules = /^\d+(?:\.\d{1,2})?$/
  if (priceRules.test(value)) {
    return cb()
  } else {
    return cb("请输入正确的金额")
  }
}
export default {
  name: "SaleCarAdd",
  components: {TinymceEditor},
  data() {
    return {
      imageUrl: "",
      utilsimageUrl: "",
      addform:{
        brand:'',
        model:'',
        year:'',
        mileage:'',
        img:'',
        price:'',
        description:'',
      },
      addRules: {
        brand: [
          {required: true, message: "品牌名称不能为空", trigger: 'blur'}
        ],

        model: [
          {required: true, message: "款式名称不能为空", trigger: 'blur'}
        ],

        year: [{required: true, message: "制造年份不能为空", trigger: 'blur'}
          , {validator: testCarYear, trigger: "blur"}
        ],

        mileage: [{required: true, message: "行驶里程数不能为空", trigger: 'blur'}
          , {validator: testMileage, trigger: 'blur'}
        ],
        price:[
          {required: true,message:"每日租金不能为空",trigger:'blur'},
          {validator:testCarPrice,trigger: 'blur'}
        ]
      },


    }
  },methods:{
    async handleAvatarSuccess(){
      try {
        const response = await this.$http.get("/carsSale/downloadImg", {
          params: {name: this.$data.utilsimageUrl},
          responseType: "blob"
        }).then(res => {
          // console.log(res)
          let databolb = new Blob([res.data])
          let url = window.URL.createObjectURL(databolb)
          this.$data.imageUrl = url

        })
      } catch (e) {
        this.$message.error("发生未知错误")
      }
    },
    async beforeAvatarUpload(file){
      let formdata = new FormData();
      formdata.append("multipartPhoto", file)
      await this.$http.post("/carsSale/addImg", formdata,
          {
            headers: {'Content-Type': 'multipart/form-data'}
          }).then(res => {
        if (res.data.code == '200') {
          this.$message.success(res.data.msg)
          this.$data.utilsimageUrl = res.data.data
          // this.$data.imageUrl = this.$http.defaults.baseURL + "/carshop/" + this.$data.utilsimageUrl
          this.$data.addform.img = res.data.data
          this.handleAvatarSuccess()
        } else {
          this.$message.error(res.data.msg)
        }

      }).catch(err => {
        console.log(err)
      })
    },
    async saveCar(){
      this.$refs.addFormRef.validate(async res=>{
        if (!res){
          this.$message.error("请正确填写车辆信息")
          return false
        }else{
          console.log(this.$data.addform)
          this.$http.post("/carsSale/add",this.$data.addform)
          .then(res=>{
            // console.log(res)
            if (res.data.code=="200"){
              this.$message.success(res.data.data)
               this.$router.push("/saleCarManage")
            }else{
              this.$message.error(res.data.msg)
            }
          })
        }
      })
    },
    reset(){
      Object.assign(this.$data.addform,this.$options.data().addform)
      this.$message.info("重置成功")
    },
  },
}
</script>

<style scoped>

/**
头部
 */
.header_all {
  box-sizing: border-box;

}

.header_left {
  box-sizing: border-box;
  width: 25rem;
  height: 8rem;
  float: left;

}

.header_left span {
  font-family: Microsoft YaHei;
  display: block;
}

.sp1 {
  font-size: 2rem;
}

.sp2 {
  float: right;
  margin-right: 7rem;
  margin-top: -1.5rem;
  color: #777777;
}

.sp3 {
  margin-left: 4rem;
  font-size: 1.2rem;
}

.sp4 {
  color: #777777;
  margin-top: -1.5rem;
  margin-left: 16rem;
}

.input_msg /deep/ .el-input__wrapper {
  border-radius: 5rem
}

.header_right {
  box-sizing: border-box;
  float: right;
  margin-right: 1rem;
  margin-top: 1rem;
}

.right_characters {
  margin-left: 5rem;
}

.my_upload {
  margin-top: 10rem;
  margin-left: 2rem;
}


.avatar-uploader .avatar {
  width: 20rem;
  height: 25rem;
  display: block;
}

.avatar_edit {
  font-size: 45px;
  color: #409EFF;
  /*width: 178px;*/
  /*height: 178px;*/
  float: left;
  margin-top: -15rem;
  margin-left: 8.8rem;
}
.add_form{
  margin-left: 25rem;
  margin-top: -30rem;
}
.addform_brand{
  width: 15rem;
}
.addform_model{
  width: 30rem;
}
.addform_year{
  width: 19rem;
}
.yaer_tag{
  margin-left: 14rem;
  margin-top: -2.5rem;
}
.addform_mileage{
  width: 22rem;
  margin-top: -3.9rem;
  margin-left: 23rem;
}
.mileage_tag{
  margin-top: -2.5rem;
  margin-left: 18rem;
}

.addform_Price{
  width: 23rem;
  margin-left: 1.8rem;
}
.price_tag{
  margin-top: -2.5rem;
  margin-left: 20rem;
}
.save_btn,.reset_btn{
  width: 7rem;
  height: 3rem;
}
.save_btn{
  margin-left: 12rem;
}
.reset_btn{
  margin-left: 10rem!important;
}


</style>


<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
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
  width: 20rem;
  height: 25rem;
  /*text-align: center;*/
}

/*
新增的
*/

</style>