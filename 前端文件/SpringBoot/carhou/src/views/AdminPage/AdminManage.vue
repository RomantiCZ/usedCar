<template>
  <div class="header_all">

    <div class="header_left">
      <span class="sp1">Administrator</span>

      <span class="sp2">List</span>

      <span class="sp3">管理员</span>

      <span class="sp4">列表</span>

      <div class="left_bt">
        <el-button @click="refreshMsg" class="left_btn" color="#488c6d" plain>

          <el-icon>
            <Refresh/>
          </el-icon>
          刷新
        </el-button>
      </div>

      <div class="left_search">
        <el-input v-model="searchmsg"
                  class="w-50 m-2 input_msg"
                  intput-style="border-radius:20% ;"
                  clearable
                  placeholder="请输入关键词">

          <template #suffix>
            <el-icon class="el-input__icon" @click="search">
              <search/>
            </el-icon>
          </template>
        </el-input>
      </div>

    </div>

    <div class="header_right">

      <div class="right_characters">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/homepage' }">
            <span>首页</span>
          </el-breadcrumb-item>

          <el-breadcrumb-item :to="{ path: '/adminmanage' }">
            <span>管理员管理</span>
          </el-breadcrumb-item>
        </el-breadcrumb>
      </div>

      <el-button class="right_btn" color="#488c6d" plain @click="addShow">
        <el-icon>
          <component is="Plus"/>
        </el-icon>
        <span>NEW</span>
      </el-button>

      <el-button @click="changePicShow" v-if="picshow" class="right_btn2" color="#488c6d" plain>
        <el-icon>
          <component is="List"/>
        </el-icon>
        <span>详情</span>
      </el-button>


      <el-button @click="changePicShow" v-else class="right_btn2" color="#488c6d" plain>
        <el-icon>
          <component is="TrendCharts"/>
        </el-icon>
        <span>图示</span>
      </el-button>

    </div>

  </div>

  <div class="main_body">

    <div>
      <el-table
          type="selection"
          style="width: 100%;"
          :data="adminData"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}"
          ref="tableRef"
          @selection-change="getSelection"
      >
        <el-table-column type="selection" width="55"/>
        <!--        <el-table-column type="index" label="ID" width="70"/>-->

        <el-table-column prop="name" label="Name" width="150"/>
        <el-table-column prop="sex" label="sex" width="150"/>
        <el-table-column prop="tel" label="tel" width="200"/>
        <el-table-column prop="idCard" label="IdCard" width="180"/>
        <el-table-column label="Role" width="180">
          <template #default="scope">
            <el-tag v-if=" scope.row.role == '超级管理员'"
                    effect="light">
              {{ scope.row.role }}
            </el-tag>

            <el-tag v-else effect="light" type="success">
              {{ scope.row.role }}
            </el-tag>

          </template>

        </el-table-column>

        <el-table-column prop="createTime" label="createTime" width="110"/>

        <el-table-column label="status" width="100">
          <!--        <template #default="scope">-->
          <!--          <el-switch-->
          <!--            v-model="scope.row.status"-->
          <!--            inline-prompt-->
          <!--            active-color="#13ce66"-->
          <!--            inactive-color="#ff4949"-->
          <!--            active-icon="Check"-->
          <!--            inactive-icon="Close"-->
          <!--          >-->
          <!--          </el-switch>-->
          <!--        </template>-->
          <template #default="scope">
            <el-text v-if="scope.row.status == 0">
              活跃
            </el-text>
            <el-text v-else type="danger">
              禁用
            </el-text>
          </template>

        </el-table-column>

        <el-table-column label="more" width="150">
          <template #default="scope">

            <!--        <el-icon>-->
            <!--          <component is="More">-->
            <!--          </component>-->
            <!--        </el-icon>-->
            <el-dropdown>
        <span class="el-dropdown-link">
        <el-icon class="el-icon--right">
           <component is="More"/>
        </el-icon>
        </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item icon="DocumentCopy" @click="editShow(scope.row.adminId)">详情</el-dropdown-item>

                  <el-dropdown-item icon="Delete" @click="deleteone(scope.row.adminId)">
                    删除
                  </el-dropdown-item>

                  <template v-if="scope.row.status ===0 ">
                    <el-dropdown-item icon="TurnOff" @click="editstatus(scope.row)">
                      禁用
                    </el-dropdown-item>
                  </template>

                  <template v-else>
                    <el-dropdown-item icon="Open" @click="editstatus(scope.row)">
                      启用
                    </el-dropdown-item>
                  </template>

                  <el-dropdown-item icon="Key" @click="resetPassword(scope.row.adminId)">
                    重置密码
                  </el-dropdown-item>



                </el-dropdown-menu>


              </template>
            </el-dropdown>

          </template>
        </el-table-column>


      </el-table>

      <div class="page_info">
        <div class="demonstration">Total item count</div>
        <el-pagination
            v-model:current-page="pagenum"
            v-model:page-size="pagesize"
            :page-sizes="[5,10,15,20]"
            background="true"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalInfo"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
        />
      </div>

      <div class="delete_info">
        <el-button class="delete_button" color="#488c6d" plain @click="deleteAdmin">
          <el-icon>
            <Delete/>
          </el-icon>
          删除
        </el-button>
      </div>
    </div>


  </div>

  <el-dialog
      v-model="addshowVisible"
      title="新增管理员"
      width="35%"
      destroy-on-close
      center
      :before-close="addtableClear"
  >
    <el-form
        ref="addFormref"
        :model="addform"
        label-width="100px"
        :rules="addformRules"
    >

      <el-form-item label="账号:" prop="username">
        <el-input v-model="addform.username" class="edit_username" clearable/>
      </el-form-item>

      <el-form-item label="名称:" prop="name">
        <el-input v-model="addform.name" clearable class="edit_password"/>
      </el-form-item>

      <el-form-item label="性别:" prop="sex">
        <el-radio-group v-model="addform.sex" class="ml-4">
          <el-radio label="男" size="large">男</el-radio>
          <el-radio label="女" size="large">女</el-radio>
        </el-radio-group>
      </el-form-item>


      <el-form-item label="联系方式:" prop="tel">
        <el-input v-model="addform.tel" clearable class="edit_tel"/>
      </el-form-item>

      <el-form-item label="身份信息:" prop="idCard">
        <el-input v-model="addform.idCard" clearable class="edit_idCard"
        />
      </el-form-item>

      <el-form-item label="电子邮箱:" prop="email">
        <el-input clearable v-model="addform.email"
                  class="edit_email"/>
      </el-form-item>

      <el-button class="save_btn" type="success" color="" plain @click="addAdminSave">
        保存
      </el-button>

    </el-form>


  </el-dialog>

  <el-dialog
      v-model="editVisible"
      title="修改管理员信息"
      width="32%"
      destroy-on-close
      center
  >

    <el-form
        ref="editFormRef"
        :model="editform"
        :rules="addformRules"
        label-width="120px"
    >

      <el-form-item label="名称:" prop="name">
        <el-input v-model="editform.name" class="edit_name"
        />
      </el-form-item>

      <el-form-item label="账号:" prop="username">
        <el-input v-model="editform.username" disabled class="edit_username"/>
      </el-form-item>

      <el-form-item label="性别:" prop="sex">
        <el-radio-group v-model="editform.sex" class="ml-4">
          <el-radio label="男" size="large">男</el-radio>
          <el-radio label="女" size="large">女</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="联系方式:" prop="tel">
        <el-input v-model="editform.tel" class="edit_tel"/>
      </el-form-item>

      <el-form-item label="证件信息:" prop="idCard">
        <el-input v-model="editform.idCard" class="edit_idCard"
        />
      </el-form-item>

      <el-form-item label="电子邮箱:" prop="email">
        <el-input v-model="editform.email" class="edit_email"
        />
      </el-form-item>

      <el-form-item label="权限：" prop="role" >

         <el-tag v-if=" editform.role == '超级管理员'"
                 effect="light">
           {{ editform.role }}
         </el-tag>

         <el-tag v-else type="success" effect="light">
           {{editform.role }}
         </el-tag>

      </el-form-item>

      <el-form-item label="状态：" prop="status">
        <el-text class="w-125px" v-if="editform.status=='0'">
          活跃
        </el-text>
        <el-text class="w-125px" type="danger" v-else>
          禁用
        </el-text>

      </el-form-item>

      <el-form-item label="创建时间：" >
        <el-text class="w-125px" truncated>
          {{ editform.createTime }}
        </el-text>
      </el-form-item>

      <el-form-item label="修改时间：" >
        <el-text class="w-125px" truncated>
          {{ editform.updateTime }}
        </el-text>
      </el-form-item>

      <el-button @click="editsave" class="save_btn1" color="#488c6d" plain >

        保存
      </el-button>


    </el-form>

  </el-dialog>

</template>

<script>

const checkUsername = (rules, value, cb) => {
  const usernameReg = /^[a-zA-Z0-9]+$/
  if (usernameReg.test(value)) {
    return cb()
  } else {
    return cb('账号必须是英文或数字')
  }
}

const cehckEmail = (rules, value, cb) => {
  const emailReg = /^[a-zA_Z0-9_-]+@([a-zA_Z0-9_-])+(\.[a-zA_Z0-9_-])+/

  if (emailReg.test(value)) {
    return cb()
  }
  return cb('请输入正确的邮箱信息')
}

const checktel = (rules, value, cb) => {
  const telReg = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
  if (telReg.test(value)) {
    return cb()
  }
  return cb('请输入正确的电话号码')
}
const checkIdCard = (rules, value, cb) => {
  const idcardReg = /^((1[1-5])|(2[1-3])|(3[1-7])|(4[1-6])|(5[0-4])|(6[1-5])|[7-9]1)\d{4}(19|20|21)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/
  if (idcardReg.test(value)) {
    return cb()
  } else {
    return cb('请输入正确的身份证号码')
  }
}
export default {
  name: "AdminManage",
  data() {
    return {
      searchmsg: "",
      picshow: true,
      adminData: [{
        adminId: 1650831416566484993,
        username: "caixukun",
        name: "蔡徐坤",
        sex: "男",
        tel: "13020088876",
        idCard: "411082199311100619",
        email: "1051902103@qq.com",
        role: "超级管理员",
        status: 0,
        createTime: "2023-04-26 18:23:37",
        updateTime: "2023-04-26 18:23:37",
        updateAdmins: 1650831416566484993,
      }, {
        adminId: 1650831416566484993,
        username: "caixukun",
        name: "蔡徐坤",
        sex: "男",
        tel: "13020088876",
        idCard: "411082199311100619",
        email: "1051902103@qq.com",
        role: "普通管理员",
        status: 1,
        createTime: "2023-04-26 18:23:37",
        updateTime: "2023-04-26 18:23:37",
        updateAdmins: 1650831416566484993,
      }
      ],
      selectionarray: [],
      totalInfo: 1314,
      pagesize: 5,
      pagenum: 1,
      addshowVisible: false,
      addform: {
        username: "",
        name: "",
        sex: "",
        tel: "",
        idCard: "",
        email: ""
      },
      addformRules: {
        username: [
          {required: true, message: "账号不能为空", trigger: 'blur'},
          {validator: checkUsername, trigger: 'blur'}
        ],
        sex: [
          {required: true, message: "性别不能为空", trigger: 'blur'}
        ],
        name: [
          {required: true, message: "名称不能为空", trigger: 'blur'},
        ],
        tel: [
          {required: true, message: "联系方式不能为空", trigger: 'blur'},
          {validator: checktel, trigger: 'blur'}
        ],
        idCard: [
          {required: true, message: "身份信息不能为空", trigger: 'blur'},
          {validator: checkIdCard, trigger: 'blur'}
        ],
        email: [
          {required: true, message: "邮箱不能为空", trigger: 'blur'},
          {validator: cehckEmail, trigger: 'blur'}
        ]
      },
      editVisible: false,
      editform: {
        adminId: "",
        username: "",
        name: "",
        sex: "",
        tel: "",
        idCard: "",
        email: "",
        role: "超级管理员",
        status: "1",
        createTime: "",
        updateTime: "",
      }

    }

  },

  created() {
    this.getAdminMsg()
  },
  mounted() {
  },
  methods: {
    async search() {
      // this.$message.success(this.$data.searchmsg)
      await this.$http("/admin/getMsg", {
        params: {
          keyword: this.$data.searchmsg
        }
      }).then(res => {
        //console.log(res)
        if (res.data.code == '200') {
          this.$message.success(res.data.msg)
          this.$data.adminData = res.data.data
          this.$data.totalInfo = res.data.data.length
        } else {
          this.$message.error(res.data.msg)
        }


      })
    },
    changePicShow() {
      this.picshow = !this.picshow
    },

    refreshMsg() {
      Object.assign(this.$data.searchmsg, this.$options.data().searchmsg)
      Object.assign(this.$data.pagesize, this.$options.data().pagesize)
      Object.assign(this.$data.pagenum, this.$options.data().pagenum)
      this.getAdminMsg()
      this.$message.success('刷新成功')
    },

    getSelection(val) {
      let res = []
      val.forEach(item => {
        res.push(item.adminId)
      })
      this.$data.selectionarray = res
    },

    async deleteAdmin() {
      //   console.log("选择了数组：")
      console.log(this.$data.selectionarray)
      await this.$http.post("/admin/deleteByIds", this.$data.selectionarray)
          .then(res => {
            // console.log(res)
            if (res.data.code == '200') {
              this.$message.success(res.data.data)
            } else {
              this.$message.error(res.data.data)
            }
            this.getAdminMsg()
          })
    },
    async deleteone(id) {
      await this.$http.get("/admin/delete/" + id).then(res => {
        // console.log(res)
        if (res.data.code == '200') {
          this.$message.success(res.data.data)
        } else {
          this.$message.error(res.data.data)
        }
        this.getAdminMsg()
      })
    },

    async editShow(id) {
      // console.log(id)
      this.$data.editVisible = true
      await this.$http.get("/admin/getAdminMsg/"+id).then(res=>{
        this.$data.editform = res.data.data
      })
    },
    editcolse(){
      this.$data.editVisible = false
      this.getAdminMsg()
    },
    async editsave(){
        await this.$http.post("/admin/updateAdmin",this.$data.editform).then(res=>{
          // console.log(res)
          if (res.data.code=='200'){
            this.$message.success(res.data.data)
            this.editcolse()
          }else {
            this.$message.error(res.data.data)
            this.editcolse()
          }
        })
    },

    async resetPassword(id){
      // console.log(id)
    await this.$http.get("/admin/reset/"+id).then(res=>{
      if (res.data.code=='200'){
        this.$message.success("该管理员"+res.data.data)
      }else{
        this.$message.error("该管理员"+res.data.data)
      }
    })
    },
    async editstatus(msg){
        // console.log(msg)
      if (msg.status == 0){
        // console.log('禁用')
        await this.$http.get('/admin/ban/'+msg.adminId).then(res=>{
          if (res.data.code == '200'){
            this.$message.success(res.data.data)
          }else{
            this.$message.error(res.data.data)
          }
          this.getAdminMsg()
        })
      }else{
        await this.$http.get('/admin/enable/'+msg.adminId).then(res=>{
          if (res.data.code == '200'){
            this.$message.success(res.data.data)
          }else{
            this.$message.error(res.data.data)
          }
          this.getAdminMsg()
        })

      }
    },
    addShow() {
      this.$data.addshowVisible = true
    },
    async getAdminMsg() {
      let pagenum = this.$data.pagenum
      let pagesize = this.$data.pagesize
      // pagenum = pagenum - 1
      await this.$http.get(
          "/admin/page", {
            params: {
              pagenum: pagenum,
              pagesize: pagesize
            }
          }).then(res => {
        // console.log(res)
        this.$data.adminData = res.data.data.records
        this.$data.totalInfo = res.data.data.total
      })
    },
    async handleSizeChange(pagesize) {
      this.$data.pagesize = pagesize
      let pagenum = this.$data.pagenum - 1
      await this.$http.get(
          "/admin/page", {
            params: {
              pagenum: pagenum,
              pagesize: pagesize
            }
          }).then(res => {
        this.$data.adminData = res.data.data.records
        this.$data.totalInfo = res.data.data.total
        this.$data.pagenum = res.data.data.current
      })
    },
    async handleCurrentChange(pagenum) {
      let current = this.$data.pagenum = pagenum
      current = current - 1
      await this.$http.get(
          "/admin/page", {
            params: {
              pagenum: pagenum,
              pagesize: this.$data.pagesize
            }
          }).then(res => {
        this.$data.adminData = res.data.data.records
        this.$data.totalInfo = res.data.data.total
        this.$data.pagenum = res.data.data.current
      })
    },

    addtableClear() {
      // this.$message.success("clear")
      this.$data.addshowVisible = false
      Object.assign(this.$data.addform, this.$options.data().addform)
    },
    async addAdminSave() {
      this.$refs.addFormref.validate(async res => {
        if (!res) {
          return false
        } else {
          if (this.$data.addform.sex == '') {
            this.$message.error("性别不能为空")
          } else {
            await this.$http.post("/admin/insert", this.$data.addform).then(res => {
              if (res.data.code == "200") {
                this.$message.success(res.data.data)
              } else {
                this.$message.error(res.data.msg)
              }
              this.addtableClear()
            })
            // this.$message.success("成功")
          }

        }
      })
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
  margin-right: 8.5rem;
  margin-top: -1.5rem;
  color: #777777;
}

.sp3 {
  margin-left: 5rem;
  font-size: 1.2rem;
}

.sp4 {
  color: #777777;
  margin-top: -1.3rem;
  margin-left: 14.6rem;
}

.left_bt {
  margin-top: 1rem;
}

.left_btn {
  width: 6rem;
  height: 2.3rem;
}

.left_search {
  margin-left: 6.5rem;
  margin-top: -2.3rem;
}

.input_msg {
  width: 15rem;
  height: 2.3rem;
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

.right_btn {
  width: 6rem;
  height: 2.3rem;
  margin-top: 2.8rem;
}

.right_btn2 {
  margin-top: 2.8rem;
  width: 6rem;
  height: 2.3rem;
}

.delete_info {
  box-sizing: border-box;
  float: right;
}

.delete_button {
  float: left;
  width: 6rem;
  height: 2.3rem;
  margin-top: -2.5rem;
}

.save_btn {
  width: 6rem;
  height: 2.3rem;
  margin-left: 12rem;
}
.save_btn1{
  width: 6rem;
  height: 2.3rem;
  margin-left: 10rem;
}


</style>