<template>

  <div class="header_all">

    <div class="header_left">
      <span class="sp1">Rental Vehicles</span>

      <span class="sp2">details</span>

      <span class="sp3">租借车辆</span>

      <span class="sp4">详情</span>

    </div>

    <div class="header_right">

      <div class="right_characters">
        <el-breadcrumb separator-icon="ArrowRight">
          <el-breadcrumb-item :to="{ path: '/homepage' }">
            <span>首页</span>
          </el-breadcrumb-item>

          <el-breadcrumb-item :to="{ path: '/rentalCarManage' }">
            <span>租借车辆管理</span>
          </el-breadcrumb-item>

          <el-breadcrumb-item>
            <span>租借车辆详情</span>
          </el-breadcrumb-item>

        </el-breadcrumb>
      </div>


    </div>
  </div>

  <div class="update_main">

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

    <div class="main_formData">
      <el-form
          class="details_form"
          label-position="left"
          ref="updateFormRef"
          size="large"
          :model=" rentalCarMsg"
          :rules="updateRules">

        <el-form-item label="产品ID：" class="rentalCarMsg_brand">
          {{ rentalCarMsg.vehicleId }}
        </el-form-item>


        <el-form-item label="品牌名称：" prop="brand" class="rentalCarMsg_brand">
          <el-input v-model="rentalCarMsg.brand" clearable/>
        </el-form-item>

        <el-form-item label="款式名称：" prop="model" class="rentalCarMsg_model">
          <el-input v-model="rentalCarMsg.model" clearable/>
        </el-form-item>


        <el-form-item label="每日租金：" prop="rentalPrice" class="rentalCarMsg_rentalPrice">
          <el-input v-model="rentalCarMsg.rentalPrice" clearable placeholder="xx.xx带两位小数"/>
        </el-form-item>

        <el-form-item label="生成年份：" prop="year" class="rentalCarMsg_year">
          <el-input v-model="rentalCarMsg.year" clearable placeholder="19xx-20xx"/>
        </el-form-item>

        <el-form-item label="里程数：" prop="mileage" class="rentalCarMsg_mileage">
          <el-input v-model="rentalCarMsg.mileage" clearable placeholder="xx.xx带两位小数"/>
          <span class="mileage_unit">
            <el-tag class="ml-2" type="success" effect="plain">W KM</el-tag>
          </span>
        </el-form-item>

        <el-form-item label="当期状态：" class="rentalCarMsg_status">

          <div class="status_tag">
            <template v-if="rentalCarMsg.status == '0' ">
              <el-tag class="ml-2" size="large" effect="dark">
                活跃
              </el-tag>
            </template>

            <template v-else-if="rentalCarMsg.status == '1' ">
              <el-tag class="ml-2" size="large" type="success" effect="dark">
                出租中
              </el-tag>
            </template>

            <template v-else class="ml-2">
              <el-tag size="large" type="danger" effect="dark">
                下架
              </el-tag>
            </template>
          </div>

        </el-form-item>

        <el-form-item label="创建时间：" class="rentalCarMsg_ct">
          <el-tag class="ml-2 ct_tag" size="large" type="success" effect="dark">
            {{ rentalCarMsg.createTime }}
          </el-tag>
        </el-form-item>

        <el-form-item label="更新时间：" class="rentalCarMsg_ut">
          <el-tag class="ml-2 ut_tag" size="large" type="success" effect="dark">
            {{ rentalCarMsg.updateTime }}
          </el-tag>
        </el-form-item>

        <TinymceEditor width="50px"
                       v-model="rentalCarMsg.description">

        </TinymceEditor>

        <template v-if="rentalCarMsg.status=='1'" >
          <el-button @click="saveUpdate" disabled  class="save_btn" color="#488c6d" plain>
            保存
          </el-button>
        </template>

        <template v-else>
          <el-button @click="saveUpdate" class="save_btn" color="#488c6d" plain>
            保存
          </el-button>
        </template>

        <el-button @click="returnManage"  class="return_btn" color="#488c6d" plain>
          返回
        </el-button>


      </el-form>
    </div>

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
  name: "RentalCarsUpdate",
  components: {TinymceEditor},
  created() {
    this.getmsg()
  },
  data() {
    return {
      imageUrl: '',
      utilsimageUrl: '',

      rentalCarMsg: {
        vehicleId: '',
        brand: '',
        model: '',
        year: '',
        mileage: '',
        img: '',
        description: '',
        rentalPrice: '',
        status: "",
        createTime: "",
        updateTime: "",
        createAdmins: '',
        updateAdmins: '',
        isDelete: ''
      },
      updateRules: {
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
        rentalPrice:[
          {required: true,message:"每日租金不能为空",trigger:'blur'},
          {validator:testCarPrice,trigger: 'blur'}
        ]
      },

    }
  },
  methods: {

    async getmsg() {
      let id = this.$route.query.id
      await this.$http.get("/carRental/getCarRental/" + id).then(res => {
         // console.log(res)
        this.$data.rentalCarMsg = res.data.data
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
        const response = await this.$http.get("/carRental/downloadImg", {
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
      if (this.$data.rentalCarMsg.status=='1'){
        this.$message.error("当前车辆修改禁止信息")
        return
      }

      let formdata = new FormData();
      formdata.append("multipartPhoto", file)
      await this.$http.post("/carRental/addImg", formdata,
          {
            headers: {'Content-Type': 'multipart/form-data'}
          }).then(res => {
        if (res.data.code == '200') {
          this.$message.success(res.data.msg)
          this.$data.utilsimageUrl = res.data.data
          // this.$data.imageUrl = this.$http.defaults.baseURL + "/carshop/" + this.$data.utilsimageUrl
          this.$data.rentalCarMsg.img = res.data.data

          this.handleAvatarSuccess()
        } else {
          this.$message.error(res.data.msg)
        }

      }).catch(err => {
        console.log(err)
      })
    },

    async saveUpdate() {
      this.$refs.updateFormRef.validate(async res=>{
        if (!res){
          return
        }else{
          // console.log(this.$data.rentalCarMsg)
          await this.$http.post("/carRental/updateCarRental",this.$data.rentalCarMsg)
          .then(res=>{
            // console.log(res)
            if (res.data.code=="200"){
              this.$message.success(res.data.data)
              this.$router.push("/RentalCarManage")
            }else{
              this.$message.error(res.data.msg)
            }
          })
        }
      })
    },

    returnManage(){
      this.$router.push("/rentalCarManage")
    }
  }
}
</script>

<style scoped>
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
  margin-left: 15.3rem;
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

/*图片*/
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

/*表单*/

.main_formData {
  float: right;
  margin-right: 0rem;
  margin-top: -30rem;
}

.rentalCarMsg_brand {
  width: 20rem;
}

.rentalCarMsg_model {
  width: 35rem;
}

.rentalCarMsg_rentalPrice {
  width: 15rem;
}

.rentalCarMsg_year {
  width: 15rem;
  margin-left: 20rem;
  margin-top: -4rem;
}

.rentalCarMsg_mileage {
  width: 14.3rem;
  margin-left: 0.7rem;
}

.mileage_unit {
  margin-left: 10.2rem;
  margin-top: -2.5rem;
}

.rentalCarMsg_status {
  margin-left: 20rem;
  margin-top: -4rem;
}

.status_tag {
  margin-left: 3.5rem;
}

.rentalCarMsg_ut {
  margin-left: 20rem;
  margin-top: -4rem;
}

.ct_tag {
  margin-left: 2rem;
}

.ut_tag {
  margin-left: 1rem;
}

.save_btn {
  width: 7rem;
  height: 3rem;
  float: left;
  margin-left: 10rem;
  margin-top: 1rem;
}
.return_btn{
  float: left;
  width: 7rem;
  height: 3rem;
  margin-left: 10rem !important;
  margin-top: 1rem;
}
</style>