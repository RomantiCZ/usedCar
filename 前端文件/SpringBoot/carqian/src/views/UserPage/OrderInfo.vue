<template>
  <div class="order_body">
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

  <div class="main_order">
    <el-table
      style="width: 100%"
      :header-cell-style="{ 'text-align': 'center' }"
      :cell-style="{ 'text-align': 'center' }"
      ref="tableRef"
      :data="orderlist"
      :row-style="{ height: '10px' }"
    >
    <template #empty>
        <div class="flex items-center justify-center h-100%">
          <el-empty />
        </div>
      </template>
     
      <el-table-column label="订单号" width="220">
        <template #default="scope">
          <span style="font-size: 1rem">
            {{ scope.row.orderId }}
          </span>
        </template>
      </el-table-column>

      <el-table-column label="类型" width="180">
        <template #default="scope">
          <span style="font-size: 1rem">
            {{ scope.row.orderType }}
          </span>
        </template>
      </el-table-column>

      <el-table-column label="品牌" width="180">
        <template #default="scope">
          <el-tag class="ml-2" type="success" effect="plain">
              <span style="font-size: 1rem">
            {{ scope.row.carsRental.brand }}
          </span>
          </el-tag>
        
        </template>
      </el-table-column>

      <el-table-column label="款式" width="180">
        <template #default="scope">
          <span style="font-size: 1rem">
            {{ scope.row.carsRental.model }}
          </span>
        </template>
      </el-table-column>

      <el-table-column label="车辆图片" width="180">
        <template #default="scope">
          <el-image
            style="width: 100px; height: 100px"
            :src="getImgUrl(scope.row.carsRental.img)"
            :zoom-rate="1.2"
            :preview-src-list="[getImgUrl(scope.row.carsRental.img)]"
            :initial-index="4"
            fit="cover"
            :preview-teleported="true"
          />
        </template>
      </el-table-column>

      <el-table-column label="预约时间" width="110">
        <template #default="scope">
          <span>
            {{ scope.row.startTime }}
          </span>
        </template>
      </el-table-column>


      <el-table-column label="结束时间" width="110">
        <template #default="scope">
          <span>
            {{ scope.row.endTime }}
          </span>
        </template>
      </el-table-column>

      <el-table-column label="订单状态" width="180">
        <template #default="scope">
          <el-tag v-if="scope.row.status == '0'" type="warning" effect="dark">
            <span style="font-size: 0.7rem"> 执行中 </span>
          </el-tag>

          <el-tag v-else type="success" effect="dark">
            <span style="font-size: 0.7rem"> 完成 </span>
          </el-tag>
        </template>
      </el-table-column>

    </el-table>
  </div>
</template>

<script>
export default {
  name: "OrderInfo",
  components: {},
  data() {
    return {
      head_img: require("../../assets/img/Porsche.png"),
      orderlist:[
    
      ],
      mylist:[  {
          orderId: "1675156890144333789",
          userId: "1672172007230828545",
          vehicleId: "1667153384267341825",
          orderType: "租借",
          status: 1,
          amount: 750.0,
          deposit: null,
          startTime: "2023-07-03 22:58:11",
          endTime: "2023-07-03 22:58:11",
          createTime: null,
          updateTime: null,
          updateAdmins: null,
          isDelete: 0,
          userToken: null,
          user: {
            userId: "1672172007230828545",
            userName: null,
            password: null,
            name: "蔡虚鲲",
            sex: null,
            idCard: null,
            tel: null,
            email: null,
            img: "7912685b-f0b3-4670-81fb-3b16e71c60c2.png",
            status: 0,
            createTime: null,
            updateTime: null,
            isDelete: null,
            userToken: null,
          },
          carsRental: {
            vehicleId: "1667153384267341825",
            brand: "奔驰",
            model: "奔驰E级(进口) 2020款 E 260 运动版",
            year: null,
            mileage: null,
            img: "7912685b-f0b3-4670-81fb-3b16e71c60c2.png",
            description: null,
            rentalPrice: null,
            status: 0,
            createTime: null,
            updateTime: null,
            createAdmins: null,
            updateAdmins: null,
            isDelete: null,
          },
        },]
    };
  },
  computed: {},
  watch: {},
  methods: {
    logout() {
      this.$store.dispatch("asyncLogoutUser");
      this.$router.push("/login");
    },

    getImgUrl(img) {
      return `http://localhost:8888/${img}`;
    },

    async getOrder(){
      let userId = this.$store.getters.getTokenUser.userId
      await this.$http.get("/order/userGet/"+userId).then(res=>{
          if(res.data.code=="200"){
            this.$data.orderlist = res.data.data
          }else{
            this.$message.info(res.data.msg)
          }
      }).catch(err=>{
        this.$message.error("发生未知错误")
      })
    }
  },
  created() {
    this.getOrder()
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
<style  scoped>
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
.main_order{
  margin-top: 3rem;
}
</style>