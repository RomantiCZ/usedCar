<template>

  <div class="header_all">

    <div class="header_left">
      <span class="sp1">Sale Vehicles</span>

      <span class="sp2">Add</span>

      <span class="sp3">出售车辆</span>

      <span class="sp4">修改</span>

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
        :model="updateForm"
        :rules="updateRules"
    >
      <el-form-item label="品牌名称：" prop="brand" class="addform_brand">
        <el-input v-model="updateForm.brand" clearable/>
      </el-form-item>

      <el-form-item label="款式名称：" prop="model" class="addform_model">
        <el-input v-model="updateForm.model" clearable/>
      </el-form-item>

      <el-form-item label="制造年份：" prop="year" class="addform_year">
        <el-input v-model="updateForm.year" clearable placeholder="输入格式为YYYY 比如2023"/>
        <el-tag class="ml-2 yaer_tag" size="large"  effect="dark">
          年
        </el-tag>
      </el-form-item>

      <el-form-item label="里程数：" prop="mileage" class="addform_mileage">
        <el-input v-model="updateForm.mileage" clearable placeholder="可带两位小数"/>
        <el-tag class="ml-2 mileage_tag" size="large" type="success"  effect="dark">
          万 公里
        </el-tag>
      </el-form-item>

      <el-form-item label="价格：" prop="price" class="addform_Price">
        <el-input v-model="updateForm.price" clearable placeholder="输入格式为xxxx.xx后面带两位小数"/>
        <el-tag class="ml-2 price_tag" size="large" type="success"  effect="dark">
          W
        </el-tag>
      </el-form-item>


      <el-form-item label="当期状态：" class="CarMsg_status">

        <div class="status_tag">
          <template v-if="updateForm.status == '0' ">
            <el-tag class="ml-2" size="large" effect="dark">
              在售
            </el-tag>
          </template>

          <template v-else-if="updateForm.status == '1' ">
            <el-tag class="ml-2" size="large" type="success" effect="dark">
              已售出
            </el-tag>
          </template>

          <template v-else class="ml-2">
            <el-tag size="large" type="danger" effect="dark">
              下架
            </el-tag>
          </template>
        </div>

      </el-form-item>

      <el-form-item label="创建时间：" class="CarMsg_ct">
        <el-tag class="ml-2 ct_tag" size="large" type="success" effect="plain">
          {{ updateForm.createTime }}
        </el-tag>
      </el-form-item>

      <el-form-item label="更新时间：" class="CarMsg_ut">
        <el-tag class="ml-2 ut_tag" size="large" type="success" effect="plain">
          {{ updateForm.updateTime }}
        </el-tag>
      </el-form-item>


      <el-form-item label="详情：" prop="description" class="addform_description">
        <TinymceEditor v-model="updateForm.description">

        </TinymceEditor>
      </el-form-item>

      <template v-if="updateForm.status=='1'">
        <el-button disabled class="save_btn" color="#488c6d" plain>
          保存
        </el-button>
      </template>
      <template v-else>
        <el-button @click="saveCar" class="save_btn" color="#488c6d" plain>
          保存
        </el-button>
      </template>


      <el-button @click="returnManage" class="reset_btn" color="#488c6d" plain>
        返回
      </el-button>

    </el-form>
  </div>

</template>

<script>
import TinymceEditor from "@/components/TinymceEditor";

const testCarYear = (rules, value, cb) => {

  const yearRules = /^19[9][0-9]|200[0-9]|20[1-9][0-9]$/
  if (yearRules.test(value)) {
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
  name: "SaleCarsUpdate",
  components:{TinymceEditor},
  created() {
   this.getmsg()
  },
  data() {
    return {
      id:'',
      imageUrl: "",
      utilsimageUrl: "",
      updateForm:{
        vehicleId: '',
        brand: '',
        model: '',
        year: '',
        mileage: '',
        img: '',
        description: '',
        price: '',
        status:'',
        createTime: '',
        updateTime: '',
      },
      updateRules:{
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
          {required: true,message:"金额不能为空",trigger:'blur'},
          {validator:testCarPrice,trigger: 'blur'}
        ]
      },
    }
  },

  methods: {

   async getmsg(){
      let id = this.$route.query.id
      this.$data.id = id
      await this.$http.get("/carsSale/getMsg/"+id)
          .then(res=>{
            // console.log(res)
            this.$data.updateForm = res.data.data
            this.$data.utilsimageUrl = res.data.data.img
            if (res.data.data.img == null){
              this.$message.info("该车辆没有图片")
            }else {
              this.handleAvatarSuccess()
            }
          })
   },

    async handleAvatarSuccess() {
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

    async beforeAvatarUpload(file) {

     if (this.$data.updateForm.status=='1'){
       this.$message.error("当前车辆修改禁止信息")
       return
     }

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
          this.$data.updateForm.img = res.data.data

          this.handleAvatarSuccess()
        } else {
          this.$message.error(res.data.msg)
        }

      }).catch(err => {
        console.log(err)
        this.$message.error("发生未知错误")
      })
    },

    async saveCar(){
      await this.$http.post("/carsSale/update",this.$data.updateForm)
      .then(res=>{

        if (res.data.code=="200"){
          this.$message.success(res.data.data)
        }else {
          this.$message.error(res.data.msg)
        }
        this.$router.push("/saleCarManage")

      })
    },
    returnManage(){
     this.$router.push("/SaleCarManage")
    }
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
.CarMsg_status{
  margin-top: -3.8rem;
  margin-left: 35rem;
}
.CarMsg_ct{
  margin-left: 2rem;
}
.CarMsg_ut{
  margin-top: -3.85rem;
  margin-left: 30rem;
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