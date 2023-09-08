<template>
  <div class="header_all">

    <div class="header_left">
      <span class="sp1">Selling Vehicles</span>

      <span class="sp2">List</span>

      <span class="sp3">售卖车辆</span>

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

          <el-breadcrumb-item :to="{ path: '/saleCarManage' }">
            <span>售卖车辆管理</span>
          </el-breadcrumb-item>
        </el-breadcrumb>
      </div>

      <el-button class="right_btn" color="#488c6d" plain @click="toadd">
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

      <el-button
      :disabled="buttonDisable"
      @click="changePicShow" v-else class="right_btn2" color="#488c6d" plain>
        <el-icon>
          <component is="TrendCharts"/>
        </el-icon>
        <span>图示</span>
      </el-button>

    </div>

  </div>



  <div class="SaleCar_main">
    <template v-if="picshow">
      <div id="myCharts" :style="{ width: '30rem', height: '30rem' }"></div>
      
      <div id="myBar" :style="{ width: '45rem', height: '35rem'}"></div>
    
</template>

    <template v-else>
      <el-table
          style="width: 100%"
          type="selection"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}"
          ref="tableRef"
          @selection-change="getSelection"
          :row-style="{height:'10px'}"
          :data="saleCarMsg"
      >
        <template #empty>
          <div class="flex items-center justify-center h-100%">
            <el-empty />
          </div>
        </template>

        <el-table-column type="selection" width="70" />

        <el-table-column prop="brand" label="品牌名称：" width="120"/>


        <el-table-column prop="model" label="款式名称：" width="160"/>

        <el-table-column label="出产年月：" width="150">
          <template #default="scope">
            <el-tag class="ml-2" size="large" type="success" effect="plain">
              {{ scope.row.year }}
            </el-tag>
          </template>
        </el-table-column>

        <el-table-column label="图片" width="160">
          <template #default="scope">
            <div class="demo-image__preview">
              <el-image
                  style="width: 100px; height: 100px"
                  :src="getImgUrl(scope.row.img)"
                  :zoom-rate="1.2"
                  :initial-index="4"
                  :preview-src-list="[getImgUrl(scope.row.img)]"
                  fit="cover"
                  :preview-teleported="true"
              />
            </div>
          </template>
        </el-table-column>

        <el-table-column label="价格" width="150">
          <template #default="scope">
            <el-tag class="ml-2" size="large" type="info" effect="dark">
              {{ scope.row.price}}  W
            </el-tag>
          </template>
        </el-table-column>

        <el-table-column label="车辆状态" width="150">
          <template #default="scope">
            <el-tag v-if="scope.row.status == '0'" class="ml-2" size="large" effect="dark">
              在售
            </el-tag>

            <el-tag v-else-if="scope.row.status == '1'" class="ml-2" size="large" type="success" effect="dark">
              已售出
            </el-tag>

            <el-tag v-else class="ml-2" size="large" type="danger" effect="dark">
              下架
            </el-tag>

          </template>

        </el-table-column>

        <el-table-column prop="updateTime" label="更新时间:" width="100"/>

        <el-table-column label="more">
          <template #default="scope">

            <el-dropdown>

              <span class="el-dropdown-link">
              <el-icon class="el-icon--right">
                 <component is="More"/>
              </el-icon>
              </span>

              <template #dropdown>
                <el-dropdown-menu>

                  <el-dropdown-item icon="DocumentCopy" @click="toedit(scope.row.vehicleId)">
                    详情
                  </el-dropdown-item>

                  <template v-if="scope.row.status == 1">
                    <el-dropdown-item disabled icon="Delete">
                      删除
                    </el-dropdown-item>
                  </template>

                  <template v-else>
                    <el-dropdown-item icon="Delete" @click="deleteone(scope.row.vehicleId)">
                      删除
                    </el-dropdown-item>
                  </template>


                  <template v-if="scope.row.status==0">
                    <el-dropdown-item icon="TurnOff" @click="banStatus(scope.row.vehicleId)">
                      禁用
                    </el-dropdown-item>
                  </template>

                  <template v-else-if="scope.row.status==1">
                    <el-dropdown-item disabled icon="Switch">
                      已出售
                    </el-dropdown-item>
                  </template>

                  <template v-else-if="scope.row.status==2">
                    <el-dropdown-item icon="Open" @click="enableStatus(scope.row.vehicleId)">
                      启用
                    </el-dropdown-item>
                  </template>


                </el-dropdown-menu>


              </template>

            </el-dropdown>

          </template>
        </el-table-column>


      </el-table>

      <div class="page_msg">

        <div class="demo-pagination-block">
          <div class="demonstration"></div>
          <el-pagination
              v-model:current-page="pageNum"
              v-model:page-size="pageSize"
              :page-sizes="[5, 10, 15, 20]"
              :background="true"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
          />
        </div>
      </div>

      <div class="delete_btn">
        <el-button class="delete_button" color="#488c6d" plain @click="deleteCars">
          <el-icon>
            <Delete/>
          </el-icon>
          删除
        </el-button>
      </div>

    </template>
  </div>




</template>

<script>
export default {
name: "SaleCarManage",
  created() {
  
  },
  mounted(){
    this.$nextTick(() => {
      // 在这里访问父组件的属性或方法
      this.initPie();
      this.initBar()
    });
  },
  data(){
    return{
      searchmsg:"",
      picshow:true,
      saleCarMsg:[],
      selectionarray: [],
      pageNum: 1,
      pageSize: 5,
      total: 100,
      buttonDisable: false,
    }
  },
  methods:{
    refreshMsg(){
      this.$data.searchmsg = "";
      if(this.$data.picshow){
        this.initPie();
        this.initBar();
      }else{
        this.getCarSaleMsg()
      }
    },
    async search(){

      await this.$http.get("/carsSale/search",{
        params:{
          keyword:this.$data.searchmsg
        }
      }).then(res=>{
       
        if (res.data.code=="200"){
           // console.log(res.data.data.length)
          this.$data.saleCarMsg=res.data.data
          this.$data.total = res.data.data.length
          this.$message.success(res.data.msg)

        }else{
          this.$message.error(res.data.msg)
        }
      })
    },

    toadd(){
      this.$router.push("/saleCarAdd")
    },

    changePicShow(){
      this.$data.picshow = !this.$data.picshow
      if(this.$data.picshow){
          this.initPie()
          this.initBar()
      }else{
        this.getCarSaleMsg()
      }
    },
    getSelection(val) {
      let res = []
      val.forEach(item => {
        res.push(item.vehicleId)
      })
      this.$data.selectionarray = res
    },

    getImgUrl(img) {
      // console.log(img)
      return `http://localhost:8888/${img}`
    },

    async getCarSaleMsg(){
      await this.$http.get("/carsSale/page",{
        params:{
          pageNum:this.$data.pageNum,
          pageSize:this.$data.pageSize
        }
      }).then(res=>{
        // console.log(res)
        this.$data.total = res.data.data.total
        this.$data.saleCarMsg = res.data.data.records

      })
    },

    async handleSizeChange(pageSize){
      this.$data.pageSize = pageSize
      await this.getCarSaleMsg()
    },

    async handleCurrentChange(pageNum){
      this.$data.pageNum = pageNum
      await this.getCarSaleMsg()
    },

    async deleteCars(){
      await this.$http.post("/carsSale/deleteByIds",this.$data.selectionarray)
       .then(res=>{

         if (res.data.code){
           this.$message.success(res.data.data)
         }else {
           this.$message.error(res.data.msg)
         }
         this.getCarSaleMsg()
       })
    },

     toedit(id){
      // console.log(id)
        this.$router.push(
            {path:  "/saleCarUpdate",
            query:{
             id:id
             }
           })
     },

    async deleteone(id){
      await this.$http.get("/carsSale/deleteByid/"+id).then(res=>{

        if (res.data.code=="200"){
          this.$message.success(res.data.data)
          this.getCarSaleMsg()
        }else{
          this.$message.error(res.data.msg)
        }

      })

    },

    async banStatus(id){
      await this.$http.get("/carsSale/ban/"+id)
      .then(res=>{
        if (res.data.code=="200"){
          this.$message.success(res.data.data)
          this.getCarSaleMsg()
        }else{
          this.$message.error(res.data.msg)
        }
      })
    },

    async enableStatus(id){
      await this.$http.get("/carsSale/enable/"+id)
          .then(res=>{
            if (res.data.code=="200"){
              this.$message.success(res.data.data)
              this.getCarSaleMsg()
            }else{
              this.$message.error(res.data.msg)
            }
          })
    },
    initPie(){
      let charts = document.getElementById("myCharts");
      
      if(charts){
        let myCharts = this.$echarts.init(charts);
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
        this.getBrand()
      
      }else{
        let newPromise = new Promise((resolve) => {
          resolve();
        });
        newPromise.then(()=>{
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
          this.getBrand();
        })
      }
    },

    initBar(){
      console.log('执行了mybar函数')
      let charts = document.getElementById("myBar");
      if(charts){
        let myCharts = this.$echarts.init(charts);
        myCharts.setOption({
          xAxis: {
            type: "category",
            data: ["宝马", "奔驰", "丰田", "奥迪", "Fri", "Sat", "Sun"],
          },
          yAxis: {
            type: "value",
          },
          series: [
            {
              data: [5, 10, 15, 20, 25, 30, 35],
              type: "bar",
              showBackground: true,
              backgroundStyle: {
                color: "rgba(180, 180, 180, 0.2)",
              },
            },
          ],
        });
        this.getYearCount();
      }else{
        let newPromise = new Promise((resolve) => {
          resolve();
        });
        newPromise.then(() => {
          let myCharts = this.$echarts.init(document.getElementById("myBar"));
          myCharts.setOption({
            xAxis: {
              type: "category",
              data: ["宝马", "奔驰", "丰田", "奥迪", "Fri", "Sat", "Sun"],
            },
            yAxis: {
              type: "value",
            },
            series: [
              {
                data: [5, 10, 15, 20, 25, 30, 35],
                type: "bar",
                showBackground: true,
                backgroundStyle: {
                  color: "rgba(180, 180, 180, 0.2)",
                },
              },
            ],
          });

           this.getYearCount();
        });
      }

      
    },

    async getBrand(){
      await this.$http.get("/carsSale/getBrand").then((res) => {
        let myCharts = this.$echarts.init(document.getElementById("myCharts"));
        myCharts.setOption({
          series: [
            {
              data: res.data.data,
            },
          ],
        });
      });
    },

    async getYearCount(){
      await this.$http.get("/carsSale/getYear").then((res) => {
        let myCharts = this.$echarts.init(document.getElementById("myBar"));

        let nameValues = [];
        let dataValues = [];

        res.data.data.forEach((item) => {
          nameValues.push(item.name);
          dataValues.push(item.value);
        });

        myCharts.setOption({
          xAxis: {
            data: nameValues,
          },
          series: [
            {
              data: dataValues,
            },
          ],
        });
      });
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
.SaleCar_main{
    margin-top: 10rem;
}

.demo-pagination-block + .demo-pagination-block {
  margin-top: 10px;
}

.demo-pagination-block .demonstration {
  margin-bottom: 16px;
}

.delete_btn {
  box-sizing: border-box;
  float: right;
}

.delete_button {
  float: left;
  width: 6rem;
  height: 2.3rem;
  margin-top: -2.5rem;
}
#myCharts {
  margin-left: 2rem;
  margin-top: 5rem;
}
#myBar {
  float: left;
  margin-top: -32rem;
  margin-left: 30rem;
}
</style>