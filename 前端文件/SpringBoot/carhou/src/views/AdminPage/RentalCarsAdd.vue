<template>

  <div class="header_all">

    <div class="header_left">
      <span class="sp1">Rental Vehicles</span>

      <span class="sp2">Add</span>

      <span class="sp3">租借车辆</span>

      <span class="sp4">添加</span>

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

          <el-breadcrumb-item :to="{ path: '/rentalCarsAdd' }">
            <span>租借车辆添加</span>
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
          :http-request="httpUpload"
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

    <el-dialog v-model="dialogVisible">
      <img w-full :src="dialogImageUrl" alt="Preview Image" />
    </el-dialog>

    <div class="main_form_details">
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
        </el-form-item>

        <el-form-item label="里程数：" prop="mileage" class="addform_mileage">
          <el-input v-model="addform.mileage" clearable placeholder="公里数为多少万KM 后面可带两位小数"/>
        </el-form-item>

        <el-form-item label="每天租金：" prop="rentalPrice" class="addform_rentalPrice">
          <el-input v-model="addform.rentalPrice" clearable placeholder="输入格式为xxxx.xx后面带两位小数"/>
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
  name: "RentalCarsAdd",
  components: {TinymceEditor},
  data() {
    return {
      imageUrl: "",
      utilsimageUrl: "",
      dialogVisible:false,
      dialogImageUrl:'',
      fileUtils: "",
      addform: {
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
        rentalPrice:[
          {required: true,message:"每日租金不能为空",trigger:'blur'},
          {validator:testCarPrice,trigger: 'blur'}
        ]
      },

    }
  },
  methods: {
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
          this.$data.addform.img = res.data.data

          this.handleAvatarSuccess()
        } else {
          this.$message.error(res.data.msg)
        }

      }).catch(err => {
        console.log(err)
      })
    },



    httpUpload(requet) {

    },


    async saveCar() {
     this.$refs.addFormRef.validate(async res=>{
       if (!res){
         return false
       }else{
         await this.$http.post("/carRental/addCar",this.$data.addform)
         .then(res=>{
           console.log(res)
           if (res.data.code=="200"){
             this.$message.success(res.data.data)
             this.$router.push("/rentalCarManage")
           }else{
             this.reset()
             this.$message.error(res.data.msg)

           }

         })
       }
     })
    },
    reset() {
      Object.assign(this.$data.addform, this.$options.data().addform)
      this.$message.info("重置成功")
    },

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

.main_form_details {

  width: 50rem;
  margin-left: 25rem;
  margin-top: -30rem;
}

.details_form {

}

.addform_brand {
  width: 30rem;

}

.addform_model {
  width: 30rem;

}

.addform_year {
  width: 20rem;

}

.addform_mileage {
  width: 29.1rem;
  margin-left: 0.9rem;
}

.addform_rentalPrice {
  width: 30rem;

}

.addform_description {
  margin-right: 2rem;
}

.save_btn {
  width: 7rem;
  height: 3rem;
  margin-left: 5rem;
  margin-top: 1rem;
}

.reset_btn {
  width: 7rem;
  height: 3rem;
  margin-top: 1rem;
  margin-left: 20rem !important;
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