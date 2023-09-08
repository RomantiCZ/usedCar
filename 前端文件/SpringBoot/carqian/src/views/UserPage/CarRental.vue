<template>
  <div class="my_body">
    <div>
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
            <el-menu-item @click="logout">退出</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </div>
    </div>

    <!-- 按钮搜索框 -->
    <div class="head_search">
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
            <el-icon class="el-input__icon" @click="search">
              <search />
            </el-icon>
          </template>
        </el-input>
      </div>
    </div>

    <!-- 车辆展示区 -->
    <div class="show_listCar">
      <template v-for="(car, index) in rentalCarList">
        <el-card :body-style="{ padding: '0px' }" class="my_card">
          <div class="demo-image__preview">
            <el-image
              style="width: 20rem; height: 18rem"
              :src="getImgUrl(car.img)"
              :zoom-rate="1.2"
              :preview-src-list="[getImgUrl(car.img)]"
              :initial-index="4"
              fit="cover"
            />
          </div>

          <div style="padding: 14px" class="card_tag">
            <span class="card_span">品牌</span>
            <el-tag class="card_show_tag ml-2" type="success">
              <h3>{{ car.brand }}</h3>
            </el-tag>
          </div>

          <div class="card_price">
            <el-text class="mx-1 detail_price" type="danger">
              $ {{ car.rentalPrice }}
            </el-text>
          </div>

          <div class="card_model">
            <el-text truncated line-clamp="2" class="model_detail">
              {{ car.model }}
            </el-text>
          </div>

          <div class="more_bt">
            <el-button text class="to_btn" @click="toCarInfo(car.vehicleId)">
              <span class="btn_span">More</span>
            </el-button>
          </div>
        </el-card>
      </template>
    </div>

    <div class="pageInfo">
      <div class="demo-pagination-block" v-show="pageShow">
        <el-pagination
          :v-show="pageShow"
          v-model:current-page="pageNum"
          v-model:page-size="pageSize"
          :page-sizes="[8, 16, 24, 32]"
          :small="false"
          background
          layout=" sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </div>

    <div class="end"></div>
  </div>
</template>

<script>
export default {
  name: "CarRental",
  components: {},
  data() {
    return {
      head_img: require("../../assets/img/Porsche.png"),
      searchmsg: "",
      activeIndex: "",
      rentalCarList: [],
      pageNum: 1,
      pageSize: 8,
      total: 11,
      pageShow: true,
    };
  },
  computed: {},
  watch: {},
  methods: {
    logout() {
      this.$store.dispatch("asyncLogoutUser");
      this.$router.push("/login");
    },
    async refreshMsg() {
      await this.getRentalCar();
      this.$message.success("刷新成功")
    },
    async search() {
      await this.$http
        .get("/carRental/search", {
          params: {
            keyword: this.$data.searchmsg,
          },
        })
        .then((res) => {
         
          if (res.data.code == "200") {
            this.$data.pageShow = false;
            this.$message.success(res.data.msg);
            this.$data.rentalCarList = res.data.data;
          } else {
            this.$message.error(res.data.msg);
            this.refreshMsg();
          }
        })
        .catch((err) => {
          this.$message.error("发生未知错误");
        });
    },
    getImgUrl(img) {
      return `http://localhost:8888/${img}`;
    },

    async getRentalCar() {
      await this.$http
        .get("/carRental/userpage", {
          params: {
            pageNum: this.$data.pageNum,
            pageSize: this.$data.pageSize,
          },
        })
        .then((res) => {
          // console.log(res)
          this.$data.rentalCarList = res.data.data.records;
          this.$data.total = res.data.data.total;
        });
    },
    async handleSizeChange(pageSize) {
      this.$data.pageSize = pageSize;
      await this.getRentalCar();
    },
    async handleCurrentChange(pageNum) {
      this.$data.pageNum = pageNum;
      await this.getRentalCar();
    },

    toCarInfo(vehicleId){
      this.$router.push({
        path:"/carinfo",
        query:{
          vehicleId:vehicleId,
          type:"rental"
        }
      })
    }
  },
  created() {
    this.getRentalCar();
  },
  mounted() {},
  beforeCreate() {},
  beforeMount() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  activated() {},
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

.head_search {
  margin-left: 2rem;
}
.left_btn {
  width: 6rem;
  height: 2.3rem;
}

.left_search {
  margin-left: 7.5rem;
  margin-top: -2.3rem;
}

.input_msg {
  width: 15rem;
  height: 2.3rem;
}

/**卡片 */

.show_listCar {
  display: flex;
  flex-wrap: wrap;

  width: 100%;
  margin-top: 3rem;
}

.my_card {
  width: 20rem;
  height: 25rem;
  margin-top: 3rem;
  margin-left: 1.5rem;
}

/**放大图片 */
.demo-image__error .image-slot {
  font-size: 30px;
}
.demo-image__error .image-slot .el-icon {
  font-size: 30px;
}
.demo-image__error .el-image {
  width: 100%;
  height: 200px;
}
.card_span {
  font-size: 16px;
}
.card_show_tag {
  margin-left: 1rem;
}
.card_price {
  margin-left: 15rem;
  margin-top: -2.3rem;
}
.detail_price {
  font-size: 1.5rem;
}
.card_model {
  width: 15rem;
  margin-top: 2rem;
}
.model_detail {
  font-size: 1rem;
  font-weight: 525;
  color: black;
}
.more_bt {
  margin-left: 16rem;
  margin-top: -1.8rem;
}
.btn_span {
  color: #488c6d;
}
/**分页 */
.demo-pagination-block + .demo-pagination-block {
  margin-top: 10px;
}
.demo-pagination-block .demonstration {
  margin-bottom: 16px;
}
.pageInfo {
  margin-top: 3rem;
  margin-left: 1rem;
}
.end {
  margin-top: 1rem;
}
</style>