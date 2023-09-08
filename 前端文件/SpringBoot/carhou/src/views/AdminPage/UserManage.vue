<template>
  <!-- 头部 -->
  <div class="header_all">
    <div class="header_left">
      <span class="sp1">USER SHOW</span>
      <span class="sp2">List</span>
      <span class="sp3">用户管理</span>
      <span class="sp4">列表</span>
      <div class="left_bt">
        <el-button @click="refreshMsg" class="left_btn" color="#488c6d" plain>
          <el-icon>
            <Refresh />
          </el-icon>
          刷新
        </el-button>
      </div>

      <div class="left_search">
        <el-input
          v-model="searchmsg"
          class="w-50 m-2 input_msg"
          intput-style="border-radius:20% ;"
          clearable
          placeholder="请输入关键词"
        >
          <template #suffix>
            <el-icon class="el-input__icon" @click="tosearch">
              <search />
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

          <el-breadcrumb-item :to="{ path: '/userManage' }">
            <span>用户管理</span>
          </el-breadcrumb-item>
        </el-breadcrumb>
      </div>

      <el-button
        :disabled="buttonDisable"
        @click="changePicShow"
        v-if="picshow"
        class="right_btn2"
        color="#488c6d"
        plain
      >
        <el-icon>
          <component is="List" />
        </el-icon>
        <span>详情</span>
      </el-button>

      <el-button
        :disabled="buttonDisable"
        @click="changePicShow"
        v-else
        class="right_btn2"
        color="#488c6d"
        plain
      >
        <el-icon>
          <component is="TrendCharts" />
        </el-icon>
        <span>图示</span>
      </el-button>
    </div>
  </div>

  <div class="user_body">
    <template v-if="picshow">
      <!-- echarts图示 -->
      <div id="myCharts" :style="{ width: '30rem', height: '29rem' }"></div>
    </template>

    <template v-else>
      <!--表单  -->
      <div class="my_table">
        <el-table
          style="width: 100%"
          type="selection"
          class="table_details"
          :header-cell-style="{ 'text-align': 'center' }"
          :cell-style="{ 'text-align': 'center' }"
          ref="tableRef"
          :data="userlist"
          :row-style="{ height: '10px' }"
        >
          <template #empty>
            <div class="flex items-center justify-center h-100%">
              <el-empty />
            </div>
          </template>

          <el-table-column class="table_userName" label="账号" width="180">
            <template #default="scope">
              <span style="font-size: 1.1rem"> {{ scope.row.userName }}</span>
            </template>
          </el-table-column>

          <el-table-column label="头像" width="180">
            <template #default="scope">
              <div class="user_img">
                <el-image
                  style="width: 7rem; height: 7rem; border-radius: 50%"
                  :src="getImgUrl(scope.row.img)"
                  :zoom-rate="1.2"
                  :preview-src-list="[getImgUrl(scope.row.img)]"
                  :initial-index="4"
                  fit="cover"
                  :preview-teleported="true"
                />
              </div>
            </template>
          </el-table-column>

          <el-table-column label="名称" width="180">
            <template #default="scope">
              <span style="font-size: 1rem">{{ scope.row.name }}</span>
            </template>
          </el-table-column>

          <el-table-column label="性别" width="80">
            <template #default="scope">
              <el-tag
                v-if="scope.row.sex == '男'"
                effect="dark"
                class="mx-1"
                size="large"
              >
                <span style="font-size: 1rem"> 男 </span>
              </el-tag>

              <el-tag
                v-else
                effect="dark"
                class="mx-1"
                size="large"
                type="danger"
              >
                <span style="font-size: 1rem"> 女 </span>
              </el-tag>
            </template>
          </el-table-column>

          <el-table-column label="联系方式" width="180">
            <template #default="scope">
              <span style="font-size: 1rem">
                {{ scope.row.tel }}
              </span>
            </template>
          </el-table-column>

          <el-table-column label="邮箱" width="120">
            <template #default="scope">
              <span style="font-size: 1rem">
                {{ scope.row.email }}
              </span>
            </template>
          </el-table-column>

          <el-table-column label="用户状态" width="180">
            <template #default="scope">
              <el-tag
                v-if="scope.row.status == '0'"
                effect="dark"
                class="mx-1"
                size="large"
                type="success"
              >
                <span> 活跃 </span>
              </el-tag>
              <el-tag
                v-else
                effect="dark"
                class="mx-1"
                size="large"
                type="danger"
              >
                <span> 禁用 </span>
              </el-tag>
            </template>
          </el-table-column>

          <el-table-column label="MORE">
            <template #default="scope">
              <el-dropdown>
                <span class="el-dropdown-link">
                  <el-icon class="el-icon--right">
                    <component is="More" />
                  </el-icon>
                </span>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item
                      icon="DocumentCopy"
                      @click="showDetails(scope.row.userId)"
                    >
                      详情
                    </el-dropdown-item>

                    <template v-if="scope.row.status == '0'">
                      <el-dropdown-item
                        icon="TurnOff"
                        @click="banStatus(scope.row.userId)"
                      >
                        禁用
                      </el-dropdown-item>
                    </template>

                    <template v-else>
                      <el-dropdown-item
                        icon="Open"
                        @click="enableStatus(scope.row.userId)"
                      >
                        启用
                      </el-dropdown-item>
                    </template>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
            </template>
          </el-table-column>
        </el-table>

        <div class="page_info" v-show="serachChoice">
          <el-pagination
            v-model:current-page="pageNum"
            v-model:page-size="pageSize"
            :page-sizes="[5, 10, 15, 20]"
            :small="false"
            :background="true"
            layout="sizes, prev, pager, next, jumper"
            :total="total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
          />
        </div>
      </div>
    </template>
  </div>
  <el-dialog v-model="dialogVisible" title="用户详情" width="50%">
    <el-form :model="userMsg" label-width="120px">
      <el-form-item label="账号：">
        <span class="userMsg_userName">
          {{ userMsg.userName }}
        </span>
      </el-form-item>

      <el-form-item label="名称：" class="label_name">
        <span class="userMsg_name">
          {{ userMsg.name }}
        </span>
      </el-form-item>

      <el-form-item label="性别：" class="label_sex">
        <el-tag
          v-if="userMsg.sex == '男'"
          effect="dark"
          class="mx-1"
          size="large"
        >
          <span> 男 </span>
        </el-tag>

        <el-tag v-else effect="dark" class="mx-1" size="large" type="danger">
          <span> 女 </span>
        </el-tag>
      </el-form-item>

      <el-form-item label="状态：" class="label_status">
        <el-tag
          v-if="userMsg.status == '0'"
          effect="dark"
          class="mx-1"
          size="large"
          type="success"
        >
          <span> 活跃 </span>
        </el-tag>
        <el-tag v-else effect="dark" class="mx-1" size="large" type="danger">
          <span> 禁用 </span>
        </el-tag>
      </el-form-item>

      <el-form-item label="联系方式：" class="label_tel">
        <span>
          {{ userMsg.tel }}
        </span>
      </el-form-item>

      <el-form-item label="电子邮箱：" class="label_email">
        <span>
          {{ userMsg.email }}
        </span>
      </el-form-item>

      <el-form-item label="证件号码：" class="label_idCard">
        <span>
          {{ userMsg.idCard }}
        </span>
      </el-form-item>

      <el-form-item label="创建时间：" class="label_createTime">
        <span>
          {{ userMsg.createTime }}
        </span>
      </el-form-item>

      <el-form-item label="更新时间：" class="label_updateTime">
        <span>
          {{ userMsg.updateTime }}
        </span>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
export default {
  name: "UserManage",
  components: {},
  data() {
    return {
      picshow: true,
      searchmsg: "",
      userlist: [],
      total: 100,
      pageNum: 1,
      pageSize: 5,
      dialogVisible: false,
      serachChoice: true,
      userMsg: {},
    };
  },
  computed: {},
  watch: {},
  methods: {
    changePicShow() {
      this.$data.picshow = !this.$data.picshow;

      if (this.$data.picshow) {
        this.initPie();
      } else {
        this.getUserlist();
      }
    },
    getImgUrl(img) {
      return `http://localhost:8888/${img}`;
    },
    async getUserlist() {
      await this.$http
        .get("/user/page", {
          params: {
            pageNum: this.$data.pageNum,
            pageSize: this.$data.pageSize,
          },
        })
        .then((res) => {
          if (res.data.code == "200") {
            this.$data.userlist = res.data.data.records;
            this.$data.total = res.data.data.total;
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          this.$message.error("发生未知错误");
        });
    },

    async tosearch() {
      if (this.$data.searchmsg == "") {
        this.$message.error("输入的关键词不能为空");
      } else {
        await this.$http
          .get("/user/search", {
            params: {
              keyword: this.$data.searchmsg,
            },
          })
          .then((res) => {
            if (res.data.code == "200") {
              this.$data.serachChoice = false;
              this.$message.success(res.data.msg);
              this.$data.userlist = res.data.data;
            } else {
              this.$message.error(res.data.msg);
            }
          })
          .catch((err) => {
            this.$message.error("发生未知错误");
          });
      }
    },

    async handleSizeChange(pageSize) {
      this.$data.pageSize = pageSize;
      await this.getUserlist();
    },
    async handleCurrentChange(pageNum) {
      this.$data.pageNum = pageNum;
      await this.getUserlist();
    },
    async showDetails(id) {
      this.$data.dialogVisible = true;
      await this.$http
        .get("/user/getUserInfo/" + id)
        .then((res) => {
          if (res.data.code == "200") {
            this.$data.userMsg = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          this.$message.error("发生未知错误");
        });
    },

    initPie() {
      let charts = document.getElementById("myCharts");
      if (charts) {
        let myCharts = this.$echarts.init(charts);
        myCharts.setOption({
          title: {
            text: "用户性别分布",
            subtext: "总览",
            left: "center",
          },
          tooltip: {
            trigger: "item",
          },
          legend: {
            orient: "vertical",
            left: "left",
          },
          series: [
            {
              // name: 'Access From',
              type: "pie",
              radius: "50%",
              data: [
                {
                  name: "雷克萨斯",
                  value: "1",
                },
                {
                  name: "奔驰",
                  value: "1",
                },
                {
                  name: "凯迪拉克",
                  value: "1",
                },
                {
                  name: "宝马",
                  value: "2",
                },
              ],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: "rgba(0, 0, 0, 0.5)",
                },
              },
            },
          ],
        });
        this.getSexOrder();
      } else {
        let newPromise = new Promise((resolve) => {
          resolve();
        });
        newPromise.then(() => {
          let myCharts = this.$echarts.init(
            document.getElementById("myCharts")
          );
          myCharts.setOption({
            title: {
              text: "车辆总数",
              subtext: "总览",
              left: "center",
            },
            tooltip: {
              trigger: "item",
            },
            legend: {
              orient: "vertical",
              left: "left",
            },
            series: [
              {
                // name: 'Access From',
                type: "pie",
                radius: "50%",
                data: [
                  {
                    name: "雷克萨斯",
                    value: "1",
                  },
                  {
                    name: "奔驰",
                    value: "1",
                  },
                  {
                    name: "凯迪拉克",
                    value: "1",
                  },
                  {
                    name: "宝马",
                    value: "2",
                  },
                ],
                emphasis: {
                  itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: "rgba(0, 0, 0, 0.5)",
                  },
                },
              },
            ],
          });
          this.getSexOrder();
        });
      }
    },
    async getSexOrder() {
      await this.$http
        .get("/user/getSexOrder")
        .then((res) => {
          let myCharts = this.$echarts.init(
            document.getElementById("myCharts")
          );
          myCharts.setOption({
            series: [
              {
                data: res.data.data,
              },
            ],
          });
        })
        .catch((err) => {
          this.$message.error("发生未知错误");
        });
    },

    async banStatus(userId) {
      await this.$http
        .get("/user/ban/" + userId)
        .then((res) => {
          if (res.data.code == "200") {
            this.$message.success(res.data.data);
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          this.$message.error("发生未知错误");
          console.log(err)
        });
      this.getUserlist();
    },

    async enableStatus(userId) {
      await this.$http
        .get("/user/enable/" + userId)
        .then((res) => {
          if (res.data.code == "200") {
            this.$message.success(res.data.data);
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          this.$message.error("发生未知错误");
        });
      this.getUserlist();
    },
  },
  created() {},
  mounted() {
    this.initPie();
  },
  beforeCreate() {},
  beforeMount() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  activated() {},
};
</script>
<style  scoped>
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
  margin-left: 4rem;
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
  border-radius: 5rem;
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

.right_btn2 {
  margin-top: 2.8rem;
  width: 6rem;
  height: 2.3rem;
  margin-left: 5rem;
}

.el-table--enable-row-hover {
  margin-top: 10rem;
}
.el-table__cell cell {
  font-size: 2rem;
}
.page_info {
  margin-top: 1rem;
}

.userMsg_userName,
.userMsg_name {
  font-size: 1.2rem;
}
.label_name {
  margin-left: 15rem;
  margin-top: -3rem;
}
.label_status {
  margin-left: 15rem;
  margin-top: -3rem;
}

.label_tel span,
.label_email span,
.label_idCard span,
.label_createTime span,
.label_updateTime span {
  font-size: 1.2rem;
}
.label_tel,
.label_email,
.label_idCard,
.label_createTime,
.label_updateTime {
  margin-left: 1.8rem;
}
#myCharts {
  margin-top: 10rem;
  margin-left: 20rem;
}
</style>