<template>
  <div class="header_all">
    <div class="header_left">
      <span class="sp1">Order Manage</span>
      <span class="sp2">List</span>
      <span class="sp3">订单管理</span>
      <span class="sp4">列表</span>
    </div>
    <div class="header_right">
      <div class="right_characters">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/homepage' }">
            <span>首页</span>
          </el-breadcrumb-item>

          <el-breadcrumb-item :to="{ path: '/orderManage' }">
            <span>订单管理</span>
          </el-breadcrumb-item>
        </el-breadcrumb>
      </div>
    </div>
  </div>

  <div class="main_body">
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

      <el-table-column label="类型" width="180">
        <template #default="scope">
          <span style="font-size: 1rem">
            {{ scope.row.orderType }}
          </span>
        </template>
      </el-table-column>

      <el-table-column label="用户名称" width="180">
        <template #default="scope">
          <span style="font-size: 1rem">
            {{ scope.row.user.name }}
          </span>
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

      <el-table-column label="预约时间" width="100">
        <template #default="scope">
          <span>
            {{ scope.row.startTime }}
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
                  @click="showDetail(scope.row)"
                >
                  其他
                </el-dropdown-item>

                <template v-if="scope.row.status == '0'">
                  <el-dropdown-item icon="Open" @click="handleClick(scope.row)">
                    完成
                  </el-dropdown-item>
                </template>

                <template v-else>
                  <el-dropdown-item icon="Open" :disabled="true">
                    已完成
                  </el-dropdown-item>
                </template>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="showdialog" title="其他信息" width="40%">
      <el-form label-width="120px" style="font-size: 1rem">
        <el-form-item label="开始时间">
          <span style="font-size: 1rem">
            {{ dialogCar.startTime }}
          </span>
        </el-form-item>

        <el-form-item label="结束时间">
          <span style="font-size: 1rem">
            {{ dialogCar.endTime }}
          </span>
        </el-form-item>

        <el-form-item label="每日租金">
          <span style="font-size: 1rem; color: brown">
            $ {{ dialogCar.amount }}
          </span>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "OrderManage",
  components: {},
  data() {
    return {
      orderlist: [
        {
          orderId: "1675156890144333789",
          userId: "1672172007230828545",
          vehicleId: "1667153384267341825",
          orderType: "租借",
          status: 0,
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
        },
        {
          orderId: "1675156890144333789",
          userId: "1672172007230828545",
          vehicleId: "1667153384267341825",
          orderType: "租借",
          status: 0,
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
        },
      ],
      showdialog: false,
      dialogCar: {
        orderId: "1675156890144333826",
        userId: "1672172695461588993",
        vehicleId: "1667152734355742721",
        orderType: "租借",
        status: 0,
        amount: null,
        deposit: null,
        startTime: "2023-07-03 22:58:11",
        endTime: "2023-07-04 22:58:11",
        createTime: null,
        updateTime: null,
        updateAdmins: null,
        isDelete: 0,
        userToken: null,
        user: {
          userId: "1672172695461588993",
          userName: null,
          password: null,
          name: "周志鲲",
          sex: null,
          idCard: null,
          tel: null,
          email: null,
          img: "a4102c79-96f4-49a8-b939-a4804ffe030e.png",
          status: 0,
          createTime: null,
          updateTime: null,
          isDelete: null,
          userToken: null,
        },
        carsRental: {
          vehicleId: "1667152734355742721",
          brand: "雷克萨斯",
          model: "雷克萨斯RX 2020款 改款 300 四驱典雅版",
          year: null,
          mileage: null,
          img: "a4102c79-96f4-49a8-b939-a4804ffe030e.png",
          description: null,
          rentalPrice: null,
          status: 0,
          createTime: null,
          updateTime: null,
          createAdmins: null,
          updateAdmins: null,
          isDelete: null,
        },
      },
    };
  },
  computed: {},
  watch: {},
  methods: {
    async getOrderList() {
      this.$http
        .get("/order/getAll")
        .then((res) => {
          if (res.data.code == "200") {
            this.$data.orderlist = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          this.$message.error("发生未知错误");
        });
    },
    getImgUrl(img) {
      return `http://localhost:8888/${img}`;
    },
    showDetail(car) {
      this.$data.showdialog = true;
      this.$data.dialogCar = car;
    },
    async handleClick(order) {
      
      let formdata= new FormData()
      formdata.append("orderId", order.orderId)
      formdata.append("carId", order.carsRental.vehicleId)

      await this.$http
        .post("order/handleOrder",formdata)
        .then((res) => {
          if (res.data.code == "200") {
            this.$message.success(res.data.data);
            this.getOrderList()
          
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          this.$message.error("发生未知错误");
        });

    },
  },
  created() {
    this.getOrderList();
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

.header_right {
  box-sizing: border-box;
  float: right;
  margin-right: 1rem;
  margin-top: 1rem;
}

.right_characters {
  margin-left: 5rem;
}
</style>