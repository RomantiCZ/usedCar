<template>
  <div class="info_body">
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
        <el-menu-item index="/rental"> 租借车辆 </el-menu-item>

        <el-menu-item index="/sale"> 售卖车辆 </el-menu-item>

        <el-sub-menu index="3">
          <template #title>个人中心</template>

          <el-menu-item index="/order"> 订单信息 </el-menu-item>
          <el-menu-item index="/userInfo"> 我的信息 </el-menu-item>
          <el-menu-item @click="logout">退出</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>

    <!-- 展示图片 -->
    <div class="show_img">
      <el-card class="show_card">
        <el-image
          style="width: 100%; height: 28rem"
          :src="getImgUrl(car.img)"
          :zoom-rate="1.2"
          :preview-src-list="[getImgUrl(car.img)]"
          :initial-index="4"
          fit="cover"
        />
      </el-card>
    </div>

    <div class="show_msg">
      <el-descriptions
        class="margin-top"
        title="Car Msg 车辆详情"
        :column="2"
        :size="size"
        border
      >
        <!-- 品牌 -->
        <el-descriptions-item>
          <template #label>
            <div class="cell-item" style="width: 5rem">
              <el-icon class="brand_icon">
                <Notification />
              </el-icon>
              <span class="des_brand">品牌</span>
            </div>
          </template>
          <div class="des_brand_detail">
            <el-tag class="ml-2 des_brand_tag" type="success" effect="light">
              <span>{{ car.brand }}</span>
            </el-tag>
          </div>
        </el-descriptions-item>

        <!-- 款式 -->
        <el-descriptions-item>
          <template #label>
            <div class="cell-item" style="width: 5rem">
              <el-icon class="model_icon">
                <Guide />
              </el-icon>
              <span class="des_model">款式</span>
            </div>
          </template>
          <div class="des_model_detail">
            <span>{{ car.model }}</span>
          </div>
        </el-descriptions-item>

        <!-- 年份 -->
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              <el-icon class="year_icon">
                <DCaret />
              </el-icon>
              <span class="des_year">年份</span>
            </div>
          </template>
          <div class="des_year_detail">
            <el-tag class="ml-2 des_yeartag" effect="dark">
              <span>{{ car.year }}</span>
            </el-tag>
          </div>
        </el-descriptions-item>

        <!-- 公里数 -->
        <el-descriptions-item>
          <template #label>
            <div class="cell-item" style="width: 5rem">
              <el-icon class="mileage_icon">
                <Bicycle />
              </el-icon>
              <span class="des_mileage">公里数</span>
            </div>
          </template>
          <div class="des_mileage_detail">
            <span>{{ car.mileage }}W</span>
          </div>
        </el-descriptions-item>

        <!-- 租金 -->
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              <el-icon class="price_icon">
                <Histogram />
              </el-icon>
              <span class="des_price">
                <template v-if="carType"> 每日租金 </template>
                <template v-else> 价格 </template>
              </span>
            </div>
          </template>
          <div class="des_price_detail" style="width: 10rem">
            <template v-if="carType">
              <span>
                <el-text class="mx-1 detail_price1" type="danger">
                  $ {{ car.rentalPrice }}
                </el-text>
              </span>
            </template>

            <template v-else>
              <span>
                <el-text class="mx-1 detail_price" type="danger">
                  {{ car.price }} W
                </el-text>
              </span>
            </template>
          </div>
        </el-descriptions-item>

        <!-- 详情 -->

        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              <el-icon class="details_icon">
                <Edit />
              </el-icon>
              <span class="des_details"> 详情 </span>
            </div>
          </template>
          <div class="des_details_detail">
            <div class="my_details" v-html="car.description"></div>
          </div>
        </el-descriptions-item>
      </el-descriptions>

      <el-button class="order_button" color="#488c6d" plain @click="orderCar">
        <span>下单</span>
      </el-button>
      <el-button class="back_button" color="#488c6d" plain @click="backPage"
        ><span> 返回 </span>
      </el-button>
    </div>

    <el-dialog
      v-model="dialogVisible"
      title="订单"
      width="40%"
      :before-close="handleClose"
    >
      <div class="dia_brand">
        <el-icon class="brand_icon">
          <Notification />
        </el-icon>
        <span class="des_brand">品牌：</span>
      </div>

      <div class="dia_brand_details">
        <el-tag class="ml-2 des_brand_tag" type="success" effect="light">
          <span style="font-size: 1rem">{{ car.brand }}</span>
        </el-tag>
      </div>

      <div class="dia_model">
        <el-icon class="model_icon">
          <Guide />
        </el-icon>
        <span class="des_model">款式：</span>
      </div>

      <div class="dia_model_details">
        <span>{{ car.model }}</span>
      </div>

      <div class="time_sel">
        <div class="block">
          <span class="demonstration">时间选择：</span>
          <el-date-picker
            v-model="value1"
            type="datetimerange"
            range-separator="To"
            start-placeholder="Start date"
            end-placeholder="End date"
          />
        </div>

        <el-button
          @click="orderClick"
          plain
          class="order_click"
          color="#488c6d"
        >
          确定
        </el-button>

        <el-button
          @click="handleClose"
          plain
          class="order_back"
          color="#488c6d"
        >
          返回
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  name: "CarInfo",
  components: {},
  data() {
    return {
      head_img: require("../../assets/img/Porsche.png"),
      carType: true,
      car: {
        vehicleId: "1667152734355742721",
        brand: "雷克萨斯",
        model: "雷克萨斯RX 2020款 改款 300 四驱典雅版",
        year: "2020",
        mileage: "4.10",
        description: "<p>金属面原漆，贴了车衣，加装360，极品车况</p>",
        rentalPrice: "300",
        price: "11.00",
        img: "",
      },
      value1:
        ref <
        [Date, Date] >
        [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
      dialogVisible: false,
      rentalorder: {
        vehicleId: "",
        brand: "",
        model: "",
        userId: "",
        startTime: "",
        endTime: "",
        rentalPrice: "",
        type: "租借",
      },
      saleOrder: {
        vehicleId: "",
        brand: "",
        model: "",
        userId: "",
        startTime: "",
        endTime: "",
        price: "",
        type: "租借",
      },
    };
  },
  computed: {},
  watch: {},
  methods: {
    logout() {
      this.$store.dispatch("asyncLogoutUser");
      this.$router.push("/login");
    },

    async getCarMsg() {
      let type = this.$route.query.type;

      let vehicleId = this.$route.query.vehicleId;
      if (type == "rental") {
        this.$data.carType = true;
        await this.$http
          .get("/carRental/getCarRental/" + vehicleId)
          .then((res) => {
            if (res.data.code == "200") {
              this.$data.car = res.data.data;
            }
          });
      } else {
        this.$data.carType = false;
        await this.$http.get("/carsSale/getMsg/" + vehicleId).then((res) => {
          if (res.data.code == "200") {
            this.$data.car = res.data.data;
          }
        });
      }
    },

    getImgUrl(img) {
      return `http://localhost:8888/${img}`;
    },
    async orderCar() {},

     localDateTimeChange(now) {

      let year = now.getFullYear();
      let month = ("0" + (now.getMonth() + 1)).slice(-2);
      let day = ("0" + now.getDate()).slice(-2);
      let hours = ("0" + now.getHours()).slice(-2);
      let minutes = ("0" + now.getMinutes()).slice(-2);
      let seconds = ("0" + now.getSeconds()).slice(-2);
      let formattedDateTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
      return formattedDateTime;
    },

    backPage() {
      if (this.$data.carType) {
        this.$router.push("/rental");
      } else {
        this.$router.push("/sale");
      }
    },
    orderCar() {
      this.$data.dialogVisible = true;
    },

    async orderClick() {
      let type = this.$route.query.type;

      if (!this.$data.value1) {
        this.$message.error("请选择相应的时间");
        return;
      }

      if (type == "rental") {
        let rentalorder = {
          vehicleId: this.$data.car.vehicleId,
          userId: this.$store.getters.getTokenUser.userId,
          startTime: this.localDateTimeChange(this.$data.value1[0]) ,
          endTime: this.localDateTimeChange(this.$data.value1[1]),
          amount: this.$data.car.rentalPrice,
          orderType: "租借",
          userToken:this.$store.getters.getTokenUser.userToken
        };
       
        await this.$http.post("/order/addOrder/rental"
        ,rentalorder)
        .then(res=>{
          if(res.data.code=="200"){
            this.$message.success(res.data.data)
            this.backPage()
          }else{
            this.$message.error(res.data.msg)
            this.backPage()
          }
        }).then(err=>{
          console.log(err)

          this.backPage()
       
         
        })
        
      } else {
        let saleOrder = {
          vehicleId: this.$data.car.vehicleId,
          userId: this.$store.getters.getTokenUser.userId,
          startTime: "",
          endTime: "",
          deposit: this.$data.car.price,
          orderType: "购买",
          userToken:this.$store.getters.getTokenUser.userToken
        };
        await this.$http.post("/order/addOrder/sale"
        ,saleOrder)
        .then(res=>{
          if(res.data.code=="200"){
            this.$message.success(res.data.data)
            this.backPage()
          }else{
            this.$message.error(res.data.msg)
            this.backPage()
          }
        }).then(err=>{
          this.$message.error("发生未知错误")
          this.backPage()
        })
        
      }
    },
    handleClose() {
      this.$data.dialogVisible = false;
    },
  },
  created() {
    this.getCarMsg();
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
/**图片展示 */
.show_img {
  margin-top: 5rem;
  margin-left: 0.5rem;
}
.head_menu {
  width: 30rem;
  margin-left: 57.5rem;
  margin-top: -3rem;
}
.show_card {
  width: 25rem;
  height: 30rem;
}

/*展示的图片 */

/**详情 */
.show_msg {
  width: 60rem;
  margin-left: 26rem;
  margin-top: -20rem;
}
/**详情的图标 */
.brand_icon,
.model_icon,
.year_icon,
.mileage_icon,
.details_icon,
.price_icon {
  font-size: 1.5rem;
}
/**图标后的字体 */
.des_brand,
.des_model,
.des_year,
.des_mileage,
.des_details,
.des_price {
  font-size: 1rem;
}
/**另一个框的字体 */
.des_brand_detail span {
  font-size: 1.2rem;
}

.cell-item {
  text-align: center;
  display: flex;
}
/**model另一个框的字体 */
.des_model_detail span,
.des_mileage_detail {
  font-size: 1.2rem;
}
.des_year_detail span {
  font-size: 1rem;
}
/**year的tag */

/**brand另一个框的tag */

/**year另一个框的字体 */

.detail_price,
.detail_price1 {
  font-size: 1.5rem;
}
.order_button,
.back_button {
  width: 8rem;
  height: 3rem;
}
.order_button {
  margin-top: 3rem;
  margin-left: 12rem;
}
.back_button {
  margin-top: 3rem;
  margin-left: 10rem !important;
}

.dia_brand {
  margin-top: 3rem;
}
.dia_brand_details {
  margin-top: -1.5rem;
  margin-left: 5rem;
}
.dia_model {
  margin-top: 5rem;
}
.dia_model_details {
  font-size: 1rem;
  margin-top: -1.3rem;
  margin-left: 5rem;
}
.time_sel {
  margin-top: 5rem;
}
.demonstration {
  font-size: 1rem;
}
.order_click,
.order_back {
  width: 6rem;
  height: 2.8rem;
  margin-top: 2rem;
  margin-left: 8rem;
}
.order_back {
  margin-left: 5rem !important;
}
</style>