<template>
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
        <el-menu-item index="/rental"> 租借车辆 </el-menu-item>

        <el-menu-item index="/sale"> 售卖车辆 </el-menu-item>

        <el-sub-menu index="3">
          <template #title>个人中心</template>

          <el-menu-item index="/order"> 订单信息 </el-menu-item>
          <el-menu-item index="/userInfo"> 我的信息 </el-menu-item>
          <template v-if="getUserSattus()">
            <el-menu-item @click="logout">退出</el-menu-item>
          </template>
        </el-sub-menu>
      </el-menu>
    </div>

    <!-- 轮播图 -->
    <div class="my_lunbo">
      <el-carousel class="custom-carousel" interval="2000">
        <el-carousel-item
          v-for="(item, index) in picitem"
          :key="index"
          class="custom-carousel-item"
        >
          <img class="carousel-img" :src="item" />
        </el-carousel-item>
      </el-carousel>
    </div>

    <!-- echarts -->
    <div class="mycharts">
      <div class="mychart_title">
        <h2>品牌分布</h2>
      </div>

      <div class="show_charts">
        <!-- 租借车辆的rental_pie -->
        <div
          id="rental_pie"
          class="rental_pie"
          :style="{ width: '45rem', height: '35rem' }"
        ></div>

        <!-- 租借车辆的sale_pie -->
        <div
          id="sale_pie"
          class="sale_pie"
          :style="{ width: '45rem', height: '35rem' }"
        ></div>
      </div>
    </div>

    <!-- 车辆展示 -->

    <div class="mycars">
      <div class="mycars_title">
        <h2>租借车辆展示</h2>
      </div>

      <div class="carsRental">
        <div class="rental_list1">
          <div class="show_list">
            <el-card class="show_card" :body-style="{ padding: '0px' }">
              <el-image
                style="width: 20rem; height: 20rem"
                :src="getImgUrl(rentalCarItem[0]?.img)"
                :zoom-rate="1.2"
                :preview-src-list="[getImgUrl(rentalCarItem[0]?.img)]"
                :initial-index="4"
                class="showcard_image"
                fit="cover"
              />

              <div style="padding: 14px" class="span_div">
                <span class="mycard_span">
                  <el-tag class="ml-2" type="success" effect="light">
                    <h3 class="span_brand">{{ rentalCarItem[0]?.brand }}</h3>
                  </el-tag>
                </span>

                <div class="cardshow_bottom">
                  <time class="cardshow_time">
                    {{ rentalCarItem[0]?.model }}
                  </time>
                  <el-button
                    text
                    class="cardshow_button"
                    @click="moreMsg(rentalCarItem[0])"
                  >
                    MORE
                  </el-button>
                </div>
              </div>
            </el-card>
          </div>
        </div>

        <div class="rental_list2">
          <div class="show_list">
            <el-card class="show_card" :body-style="{ padding: '0px' }">
              <el-image
                style="width: 20rem; height: 20rem"
                :src="getImgUrl(rentalCarItem[1]?.img)"
                :zoom-rate="1.2"
                :preview-src-list="[getImgUrl(rentalCarItem[1]?.img)]"
                :initial-index="4"
                class="showcard_image"
                fit="cover"
              />

              <div style="padding: 14px" class="span_div">
                <span class="mycard_span">
                  <el-tag class="ml-2" type="success" effect="light">
                    <h3 class="span_brand">{{ rentalCarItem[1]?.brand }}</h3>
                  </el-tag>
                </span>

                <div class="cardshow_bottom">
                  <time class="cardshow_time">
                    {{ rentalCarItem[1]?.model }}
                  </time>
                  <el-button
                    text
                    class="cardshow_button"
                    @click="moreMsg(rentalCarItem[1])"
                  >
                    MORE
                  </el-button>
                </div>
              </div>
            </el-card>
          </div>
        </div>

        <div class="rental_list3">
          <div class="show_list">
            <el-card class="show_card" :body-style="{ padding: '0px' }">
              <el-image
                style="width: 20rem; height: 20rem"
                :src="getImgUrl(rentalCarItem[2]?.img)"
                :zoom-rate="1.2"
                :preview-src-list="[getImgUrl(rentalCarItem[2]?.img)]"
                :initial-index="4"
                class="showcard_image"
                fit="cover"
              />

              <div style="padding: 14px" class="span_div">
                <span class="mycard_span">
                  <el-tag class="ml-2" type="success" effect="light">
                    <h3 class="span_brand">{{ rentalCarItem[2]?.brand }}</h3>
                  </el-tag>
                </span>

                <div class="cardshow_bottom">
                  <time class="cardshow_time">
                    {{ rentalCarItem[2]?.model }}
                  </time>
                  <el-button
                    text
                    class="cardshow_button"
                    @click="moreMsg(rentalCarItem[2])"
                  >
                    MORE
                  </el-button>
                </div>
              </div>
            </el-card>
          </div>
        </div>
      </div>

      <div class="mycars_title1">
        <h2>售卖车辆展示</h2>
      </div>

      <div class="carsSale">
        <div class="sale_list1">
          <div class="show_list">
            <el-card class="show_card" :body-style="{ padding: '0px' }">
              <el-image
                style="width: 20rem; height: 20rem"
                :src="getImgUrl(saleCarItem[0]?.img)"
                :zoom-rate="1.2"
                :preview-src-list="[getImgUrl(saleCarItem[0]?.img)]"
                :initial-index="4"
                class="showcard_image"
                fit="cover"
              />

              <div style="padding: 14px" class="span_div">
                <span class="mycard_span">
                  <el-tag class="ml-2" type="success" effect="light">
                    <h3 class="span_brand">{{ saleCarItem[0]?.brand }}</h3>
                  </el-tag>
                </span>

                <div class="cardshow_bottom">
                  <time class="cardshow_time">
                    {{ saleCarItem[0]?.model }}
                  </time>
                  <el-button
                    text
                    class="cardshow_button"
                    @click="moreMsg(saleCarItem[0])"
                  >
                    MORE
                  </el-button>
                </div>
              </div>
            </el-card>
          </div>
        </div>

        <div class="sale_list2">
          <div class="show_list">
            <el-card class="show_card" :body-style="{ padding: '0px' }">
              <el-image
                style="width: 20rem; height: 20rem"
                :src="getImgUrl(saleCarItem[1]?.img)"
                :zoom-rate="1.2"
                :preview-src-list="[getImgUrl(saleCarItem[1]?.img)]"
                :initial-index="4"
                class="showcard_image"
                fit="cover"
              />

              <div style="padding: 14px" class="span_div">
                <span class="mycard_span">
                  <el-tag class="ml-2" type="success" effect="light">
                    <h3 class="span_brand">{{ saleCarItem[1]?.brand }}</h3>
                  </el-tag>
                </span>

                <div class="cardshow_bottom">
                  <time class="cardshow_time">
                    {{ saleCarItem[1]?.model }}
                  </time>
                  <el-button
                    text
                    class="cardshow_button"
                    @click="moreMsg(saleCarItem[1])"
                  >
                    MORE
                  </el-button>
                </div>
              </div>
            </el-card>
          </div>
        </div>

        <div class="sale_list3">
          <div class="show_list">
            <el-card class="show_card" :body-style="{ padding: '0px' }">
              <el-image
                style="width: 20rem; height: 20rem"
                :src="getImgUrl(saleCarItem[2]?.img)"
                :zoom-rate="1.2"
                :preview-src-list="[getImgUrl(saleCarItem[2]?.img)]"
                :initial-index="4"
                class="showcard_image"
                fit="cover"
              />

              <div style="padding: 14px" class="span_div">
                <span class="mycard_span">
                  <el-tag class="ml-2" type="success" effect="light">
                    <h3 class="span_brand">{{ saleCarItem[2]?.brand }}</h3>
                  </el-tag>
                </span>

                <div class="cardshow_bottom">
                  <time class="cardshow_time">
                    {{ saleCarItem[2]?.model }}
                  </time>
                  <el-button
                    text
                    class="cardshow_button"
                    @click="moreMsg(saleCarItem[2])"
                  >
                    MORE
                  </el-button>
                </div>
              </div>
            </el-card>
          </div>
        </div>
      </div>
    </div>

    <div class="end_div"></div>
  </div>
</template>

<script>
import * as echarts from "echarts";

export default {
  name: "UserHomePage",

  created() {
    this.getRentalCarNew();
    this.getSaleCarNew();
  },
  mounted() {
    // 等待组件加载完毕后再访问父组件的属性或方法
    this.$nextTick(() => {
      // 在这里访问父组件的属性或方法
      this.initRentalPie();
      this.intitSalePie();
    });
  },
  data() {
    return {
      head_img: require("../../assets/img/Porsche.png"),

      picitem: [
        require("../../assets/img/lunbo1.png"),
        require("../../assets/img/lunbo2.png"),
        require("../../assets/img/lunbo3.png"),
        require("../../assets/img/lunbo4.png"),
        require("../../assets/img/lunbo5.png"),
      ],

      saleCarItem: [],
      rentalCarItem: [],
    };
  },

  methods: {
    initRentalPie() {
      let charts = document.getElementById("rental_pie");

      if (charts) {
        let myCharts = this.$echarts.init(charts);

        myCharts.setOption({
          title: {
            text: "租借车辆总数",
            subtext: "数量总览",
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

        this.getBrand();
      } else {
        let newPromise = new Promise((resolve) => {
          resolve();
        });
        newPromise.then(() => {
          let myCharts = this.$echarts.init(
            document.getElementById("rental_pie")
          );

          myCharts.setOption({
            title: {
              text: "租借车辆总数",
              subtext: "数量总览",
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
          this.getBrand();
        });
      }
    },

    async getBrand() {
      await this.$http.get("/carRental/getBrand").then((res) => {
        let myCharts = this.$echarts.init(
          document.getElementById("rental_pie")
        );
        myCharts.setOption({
          series: [
            {
              data: res.data.data,
            },
          ],
        });
      });
    },

    intitSalePie() {
      let charts = document.getElementById("sale_pie");
      if (charts) {
        let myCharts = this.$echarts.init(charts);
        myCharts.setOption({
          title: {
            text: "出售车辆总数",
            subtext: "数量总览",
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
        this.getSaleBrand();
      } else {
        let newPromise = new Promise((resolve) => {
          resolve();
        });
        newPromise.then(() => {
          let myCharts = this.$echarts.init(
            document.getElementById("sale_pie")
          );
          myCharts.setOption({
            title: {
              text: "租借车辆总数",
              subtext: "数量总览",
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
          this.getSaleBrand();
        });
      }
    },

    async getSaleBrand() {
      await this.$http.get("/carsSale/getBrand").then((res) => {
        let myCharts = this.$echarts.init(document.getElementById("sale_pie"));
        myCharts.setOption({
          series: [
            {
              data: res.data.data,
            },
          ],
        });
      });
    },

    async getRentalCarNew() {
      await this.$http.get("/carRental/getNew").then((res) => {
        this.$data.rentalCarItem = res.data.data;
      });
    },

    async getSaleCarNew() {
      await this.$http.get("/carsSale/getNew").then((res) => {
        this.$data.saleCarItem = res.data.data;
      });
    },

    getImgUrl(img) {
      return `http://localhost:8888/${img}`;
    },
    moreMsg(car) {
      if (car.rentalPrice) {
        this.$router.push({
          path: "/carinfo",
          query: {
            vehicleId: car.vehicleId,
            type: "rental",
          },
        });
      } else {
        this.$router.push({
          path: "/carinfo",
          query: {
            vehicleId: car.vehicleId,
            type: "sale",
          },
        });
      }
    },
    getUserSattus() {
      let user = JSON.parse(window.sessionStorage.getItem("user"));
      if (user) {
        return true;
      } else {
        return false;
      }
    },
    logout() {
      this.$store.dispatch("asyncLogoutUser");
      this.$router.push("/login");
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

.my_lunbo {
  width: 100%;
  margin-top: 2rem;
  height: 36rem;
}

.custom-carousel {
  height: 36rem;
}
.custom-carousel-item {
  height: 36rem;
}
.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

::v-deep .el-carousel__arrow--left {
  margin-top: 8rem;
  font-size: 30px;
}
::v-deep .el-carousel__arrow--right {
  margin-top: 8rem;
  font-size: 30px;
}

.head_menu {
  width: 30rem;
  margin-left: 57.5rem;
  margin-top: -3rem;
}
.el-menu {
  border-right: none;
}

#rental_pie {
  margin-left: 1rem;
  margin-top: 2rem;
}
.mycharts {
  margin-top: 10rem;
}
.mychart_title {
  font-size: 1.5rem;
  margin-left: 38rem;
}
.show_charts {
  margin-top: 6rem;
}

#sale_pie {
  margin-left: 42.5rem;
  margin-top: -35rem;
}

.mycars {
  margin-top: 10rem;
}
.mycars_title {
  font-size: 1.5rem;
  margin-left: 38rem;
}

/**卡片的css */
.show_list {
  display: inline-block;
  display: flex; /* 使用 Flexbox 布局 */
  justify-content: space-between; /* 子元素之间的间隔相等 */
  align-items: center; /* 水平居中 */
}

.show_card {
  width: 20rem;
  height: 28rem;
}

.showcard_image {
  width: 20rem;
  height: 20rem;
  display: block;
}

.cardshow_time {
  font-size: 15px;
  line-height: 1.5rem;
  color: #767474;
  width: 9rem;
}
.cardshow_bottom {
  margin-top: 21px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

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

.rental_list1 {
  margin-top: 5rem;
  margin-left: 5rem;
}
.rental_list2 {
  margin-top: -28.2rem;
  margin-left: 35rem;
}
.rental_list3 {
  margin-top: -28.2rem;
  margin-left: 65rem;
}
.mycars_title1 {
  font-size: 1.5rem;
  margin-left: 38rem;
  margin-top: 10rem;
}
.sale_list1 {
  margin-top: 6rem;
  margin-left: 5rem;
}
.sale_list2 {
  margin-top: -28.2rem;
  margin-left: 35rem;
}
.sale_list3 {
  margin-top: -28.2rem;
  margin-left: 65rem;
}
.end_div {
  margin-top: 10rem;
}
</style>


<!-- 全局样式 -->
<style>
.el-menu--collapse .el-menu .el-submenu,
.el-menu--popup {
  min-width: 6rem !important;
}

/**卡片的css
 */
</style>