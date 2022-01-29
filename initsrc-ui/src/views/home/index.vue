<template>
  <div class="dshfn-content">
    <el-row :gutter="24" class="dshfn-content-main ">
      <el-col :md="24" :lg="24" :xl="24" class="col-padding">
        <div class="dshfn-card dshfn-card-bottom">
          <div class="dshfn-card-body" style="position: relative;">
            <h4 class="card-title">欢迎来到INITSRC管理系统</h4>
            <div style="padding: 10px;">
              <el-row :gutter="24">
                <el-col :md="24" :lg="12" :xl="12" style="font-size: 13px;">
                  INITSRC是一款面向个人、中小企业快速开发的开源的前后端管理项目。用户可以基于该项目进行网站管理后台、商城、OA等开发和学习。
                  该项目后台基于Springboot+Mybaits-plus+Shiro+Jwt等技术来实现；前端基于Vue+Router+Vuex+Axios等技术来实现。
                  <div style="margin-top: 10px;">
                    <el-button size="medium"><a style="color: inherit;" href="http://docs.initsrc.com/#/document/main?id=序章">文档</a></el-button>
                    <el-button type="primary" size="medium"><a style="color: inherit;" href="https://gitee.com/initsrc">Gitee</a></el-button>
                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :md="24" :lg="6" :xl="6" class="col-padding">
        <div class="dshfn-card dshfn-card-bottom">
          <div class="dshfn-card-body">
            <div class="media">
              <div style="font-size: 20px;height: 2.5rem;width: 2.5rem;margin-right: 1rem;">
                <span class="avatar-title bg-soft-primary text-primary rounded">
                  <i class="el-icon-star-on"></i>
                </span>
              </div>
              <div class="media-body">
                <div style="margin-top: 0.5rem;">操作登录总数</div>
              </div>
              <h4 style="margin-top: 0.5rem; font-size: 20px;">{{form.loginCount}}</h4>
            </div>
            <div class="row">
              <el-col :span="24">
                <el-progress :text-inside="true" :stroke-width="20" v-if="form"
                  :percentage="form.loginCountP"></el-progress>
              </el-col>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :md="24" :lg="6" :xl="6" class="col-padding">
        <div class="dshfn-card dshfn-card-bottom">
          <div class="dshfn-card-body">
            <div class="media">
              <div style="font-size: 20px;height: 2.5rem;width: 2.5rem;margin-right: 1rem;">
                <span class="avatar-title bg-soft-primary text-primary rounded">
                  <i class="el-icon-star-on"></i>
                </span>
              </div>
              <div class="media-body">
                <div style="margin-top: 0.5rem;">操作新增总数</div>
              </div>
              <h4 style="margin-top: 0.5rem; font-size: 20px;">{{form.addCount}}</h4>
            </div>
            <div class="row">
              <el-col :span="24">
                <el-progress :text-inside="true" :stroke-width="20" status="success" v-if="form"
                  :percentage="form.addCountP"></el-progress>
              </el-col>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :md="24" :lg="6" :xl="6" class="col-padding">
        <div class="dshfn-card dshfn-card-bottom">
          <div class="dshfn-card-body">
            <div class="media">
              <div style="font-size: 20px;height: 2.5rem;width: 2.5rem;margin-right: 1rem;">
                <span class="avatar-title bg-soft-primary text-primary rounded">
                  <i class="el-icon-star-on"></i>
                </span>
              </div>
              <div class="media-body">
                <div style="margin-top: 0.5rem;">操作编辑总数</div>
              </div>
              <h4 style="margin-top: 0.5rem; font-size: 20px;">{{form.editCount}}</h4>
            </div>
            <div class="row">
              <el-col :span="24">
                <el-progress :text-inside="true" :stroke-width="20" status="warning" v-if="form"
                  :percentage="form.editCountP"></el-progress>
              </el-col>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :md="24" :lg="6" :xl="6" class="col-padding">
        <div class="dshfn-card dshfn-card-bottom">
          <div class="dshfn-card-body">
            <div class="media">
              <div style="font-size: 20px;height: 2.5rem;width: 2.5rem;margin-right: 1rem;">
                <span class="avatar-title bg-soft-primary text-primary rounded">
                  <i class="el-icon-star-on"></i>
                </span>
              </div>
              <div class="media-body">
                <div style="margin-top: 0.5rem;">操作删除总数</div>
              </div>
              <h4 style="margin-top: 0.5rem; font-size: 20px;">{{form.delCount}}</h4>
            </div>
            <div class="row">
              <el-col :span="24">
                <el-progress :text-inside="true" :stroke-width="20" status="exception" v-if="form"
                  :percentage="form.delCountP"></el-progress>
              </el-col>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :md="24" :lg="12" :xl="12" class="col-padding">
        <div class="dshfn-card dshfn-card-bottom">
          <div class="dshfn-card-body" style="position: relative;min-height: 400px;">
            <h4 class="card-title">近七日登录统计</h4>
            <div>
              <apexchart height="282" :options="chartOptions" :series="chartOptions.series"></apexchart>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :md="24" :lg="12" :xl="12" class="col-padding">
        <div class="dshfn-card dshfn-card-bottom">
          <div class="dshfn-card-body" style="position: relative;min-height: 400px;">
            <h4 class="card-title">通知公告</h4>
            <div>
              <template v-for="item in noticeList">
                <div class="el-collapse-item__header" @click="openDetail(item.noticeId)">
                  <span v-html="'【'+common.transformTime(item.createTime)+'】'" style="margin-right: 10px;"></span>{{item.title}} <i class="el-collapse-item__arrow el-icon-arrow-right"></i></div>
              </template>

              <el-pagination style="margin-top: 10px;" :pager-count="5" layout="prev, pager, next" :total="noticeTotal"
                @current-change="handleCurrentChange">
              </el-pagination>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :md="24" :lg="24" :xl="24" class="col-padding">
        <div class="dshfn-card dshfn-card-bottom">
          <div class="dshfn-card-body" style="position: relative;">
            <h4 class="card-title">系统操作日志</h4>
           <!--region table 表格-->
           <i-table ref="table" :optionsObj="options"></i-table>
           <!--endregion-->
          </div>
        </div>
      </el-col>
    </el-row>
    <el-drawer class="dshfn-commonDrawer" :visible.sync="drawer" :direction="direction" :wrapperClosable="true"
      append-to-body :size="'100%'" title="通知公告">
      <article class="details">
        <div class="title" >
          <h1 style="text-align: center;">{{noticeDetail.title}}</h1>
          <span style="font-size: 15px;" v-html="common.transformTime(noticeDetail.createTime)"></span>
        </div>

        <div class="w-e-text content" v-html="noticeDetail.content">
        </div>
      </article>
    </el-drawer>
  </div>
</template>

<script>
  import VueApexCharts from 'vue-apexcharts'
  export default {
    components: {
      'apexchart': VueApexCharts
    },
    data() {
      return {
        drawer: false,
        direction: "rtl",
        noticeList: [],
        form: {},
        noticeDetail:{},
        params: {
          page: 1,
          limit: this.$store.state.ps.PAGE_SIZE,
        },
        params2: {
          page: 1,
          limit: this.$store.state.ps.PAGE_SIZE,
        },
        noticeLoading: true,
        chartOptions: {
          series: [{
            data: [0, 0, 0, 0, 0, 0, 0],
            type: 'area'
          }],
          chart: {
            height: 260,
            type: 'line',
            toolbar: {
              show: false
            },
            zoom: {
              enabled: false
            }
          },
          colors: ['#45cb85', '#3b5de7'],
          dataLabels: {
            enabled: false
          },
          stroke: {
            curve: 'smooth',
            width: '3',
            dashArray: [4, 0]
          },
          markers: {
            size: 3
          },
          xaxis: {
            categories: [],
          },
          fill: {
            type: 'solid',
            opacity: [1, 0.1]
          },
          legend: {
            position: 'top',
            horizontalAlign: 'right'
          }
        },
        table: null, //表格
        //表格基本参数
        options: {
          api: this.$api.baseRequest.logList, //请求API
          page: true,
          columns: [{
            prop: 'requestName',
            label: '操作用户',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },{
            prop: 'title',
            label: '模块标题',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'bizType',
            label: '日志类型',
            formatter: (row, column, cellValue) => {
              return this.common.transformDict(cellValue, JSON.parse(this.$store.state.ps.DICT_LIST.sysLogType))
            },
          },
          {
            prop: 'method',
            label: '访问方法名',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'requestType',
            label: '请求方式',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'platformType',
            label: '设备类型',
            formatter: (row, column, cellValue) => {
              return this.common.transformDict(cellValue, JSON.parse(this.$store.state.ps.DICT_LIST.platformType))
            },
          },
          {
            prop: 'requestIp',
            label: '请求IP',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'requestUrl',
            label: '请求地址',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'status',
            label: '请求状态',
            formatter: (row, column, cellValue) => {
              return this.common.transformDict(cellValue, JSON.parse(this.$store.state.ps.DICT_LIST.sysLogStatus))
            },
          },
          {
            prop: 'createTime',
            label: '请求时间',
            formatter: (row, column, cellValue) => {
              return this.common.transformTime(cellValue);
            },
          },
          ],
        },
        noticeTotal: 0,

      }
    },
    methods: {
      init() {
        let that = this;
        let dayList = JSON.parse(JSON.stringify(that.getRencentlySevenDay(new Date()))).reverse();
        var params = {
          beginTime: dayList[0] + " 00:00:00",
          endTime: dayList[6] + " 23:59:59"
        }
        that.$refs.table.init()
        that.initNotice();
        that.$api.baseRequest.homeInfo(params)
          .then(res => {
            res = res.data
            if (res.code == 0) {
              that.form = res.data;
              that.form.loginCountP =Number(((that.form.loginCount/that.form.totalCount)*100).toFixed(2))
              that.form.addCountP =Number(((that.form.addCount/that.form.totalCount)*100).toFixed(2))
              that.form.editCountP =Number(((that.form.editCount/that.form.totalCount)*100).toFixed(2))
              that.form.delCountP =Number(((that.form.delCount/that.form.totalCount)*100).toFixed(2))
              that.chartOptions = {
                series: [{
                  name: "登录次数",
                  data: that.form.loginList,
                }],
                chart: {
                  height: 320,
                  type: 'area',
                  toolbar: {
                    show: false
                  },
                  zoom: {
                    enabled: false
                  }
                },
                colors: [ '#3b5de7'],
                dataLabels: {
                  enabled: false
                },
                stroke: {
                  curve: 'smooth',
                },
                markers: {
                  size: 3
                },
                xaxis: {
                   type: 'datetime',
                  categories: dayList,
                },
                fill: {
                  type: 'solid',
                  opacity: [0.2]
                },
                legend: {
                  position: 'top',
                  horizontalAlign: 'right'
                }
              }
            } else {
              this.$notify.error({
                title: '错误提示',
                message: res.msg
              });
            }
          })
      },
      initNotice() {
        let that = this;
        that.noticeLoading = true;
        that.$api.baseRequest.noticeList(that.params2)
          .then(res => {
            res = res.data
            that.noticeLoading = false;
            if (res.code == 0) {
              that.noticeList = res.data.pageList;
              that.noticeTotal = res.data.total
            } else {
              this.$notify.error({
                title: '错误提示',
                message: res.msg
              });
            }
          })
      },
      openDetail(data){
        let that = this;
        that.$api.baseRequest.detailNotice({
          id:data
        })
          .then(res => {
            res = res.data
            if (res.code == 0) {
              that.noticeDetail = res.data;
              that.drawer = true;
            } else {
              this.$notify.error({
                title: '错误提示',
                message: res.msg
              });
            }
          })
      },
      handleCurrentChange(page) {
        this.params2.page = page;
        this.initNotice();
      },
      // 切换每页显示的数量
      handleSizeChange(pagination) {
        this.pagination = pagination
        this.params.limit = this.pagination.pageSize
        this.init();
      },
      // 切换页码
      handleIndexChange(pagination) {
        this.pagination = pagination
        this.params.page = this.pagination.pageIndex
        this.init();
      },
      getRencentlySevenDay(tarDay) {
        var tarDay1 = this.dateFormat(new Date(tarDay.getTime() - (0 * 24 * 60 * 60 * 1000)));
        var tarDay2 = this.dateFormat(new Date(tarDay.getTime() - (1 * 24 * 60 * 60 * 1000)));
        var tarDay3 = this.dateFormat(new Date(tarDay.getTime() - (2 * 24 * 60 * 60 * 1000)));
        var tarDay4 = this.dateFormat(new Date(tarDay.getTime() - (3 * 24 * 60 * 60 * 1000)));
        var tarDay5 = this.dateFormat(new Date(tarDay.getTime() - (4 * 24 * 60 * 60 * 1000)));
        var tarDay6 = this.dateFormat(new Date(tarDay.getTime() - (5 * 24 * 60 * 60 * 1000)));
        var tarDay7 = this.dateFormat(new Date(tarDay.getTime() - (6 * 24 * 60 * 60 * 1000)));
        return [tarDay1, tarDay2, tarDay3, tarDay4, tarDay5, tarDay6, tarDay7] //return其实在内部是进行了一个操作，声明了一个变量的。
      },
      dateFormat(date) {
        var year = date.getFullYear().toString();
        var month = date.getMonth() + 1;
        if (month < 10) {
          month = "0" + month.toString();
        } else {
          month = month.toString();
        }
        var day = date.getDate();
        if (day < 10) {
          day = "0" + day.toString();
        } else {
          day = day.toString();
        }
        return year + '-' + month + '-' + day; //2020-11-30
      }
    },
    mounted() {
      this.init();
    }
  }
</script>

<style scoped>
  .avatar-title {
    align-items: center;
    background-color: #3b5de7;
    color: #fff;
    display: flex;
    font-weight: 500;
    height: 100%;
    justify-content: center;
    width: 100%;
  }

  .bg-soft-primary {
    background-color: rgba(59, 93, 231, 0.25) !important;
  }

  .media {
    margin-bottom: 40px;
  }

  .card-title {
    color: #495057;
    font-size: 15px;
    margin: 0 0 7px 0;
    font-weight: 600;
  }

  .details {
    width: 80%;
    margin: 0 auto;
  }

  .details .title {
    border-bottom: 2px solid red;
    padding-top: 10px;
  }

  .details .content{
    padding-top: 20px;
  }
</style>
