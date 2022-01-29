import VueApexCharts from 'vue-apexcharts'
  export default {
    components: {
      'apexchart': VueApexCharts
    },
    data() {
      return {
        loading: true,
        timer: null,
        form: {
          sysFiles: []
        },
        //表格基本参数
        options: {
          data: [], //静态数据API
          operates: {
            width: 200, //操作栏宽
            fixed: 'right',
            hideCount: 3, //按钮更多
            list: []
          },
          columns: [{
            prop: 'key',
            label: 'key',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'value',
            label: 'value',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          ],
        },
        chartOptionsCommon: {
          series: [],
          chart: {
            height: 230,
            type: 'donut'
          },
          labels: [],
          plotOptions: {
            pie: {
              donut: {
                labels: {
                  show: true,
                },
                size: '60%'
              }
            }
          },
          legend: {
            show: true
          },
        },
        chartOptionsMem: {
          series: [],
          chart: {
            height: 230,
            type: 'donut'
          },
          labels: ["剩余内存","消耗内存"],
          plotOptions: {
            pie: {
              pie: {
                startAngle: -90,
                endAngle: 90,
                offsetY: 10
              }
            }
          },
          legend: {
            show: true
          },
        },
      }
    },
    methods: {
      init() {
        let that = this;
        that.$api.devRequest.cacheDetail({})
          .then(res => {
            res = res.data
            that.loading = false;
            that.options.loading = false;
            if (res.code == 0) {
              that.form = res.data
              for (let key of Object.keys(that.form.info)) {
                var obj = {
                  key: key,
                  value: that.form.info[key]
                }
                that.options.data.push(obj)
              }
              that.chartOptionsCommon = {
                series: that.form.commandStats.data,
                chart: {
                  height: 300,
                  type: 'donut'
                },
                labels: that.form.commandStats.label,
                plotOptions: {
                  pie: {
                    donut: {
                      labels: {
                        show: true,
                      },
                      size: '60%'
                    }
                  }
                },
                legend: {
                  show: true
                },
              }
              var mem = [];
              mem.push(that.form.info.used_memory_rss - Number(that.form.info.used_memory_human.substring(0,that.form.info.used_memory_human.length-1)))
              mem.push(Number(that.form.info.used_memory_human.substring(0,that.form.info.used_memory_human.length-1)))
              that.chartOptionsMem.series = mem;
            } else {
              this.$notify.error({
                title: '错误提示',
                message: res.msg
              });
              that.drawer = true
            }
          })
      },
      //返回上一页
      goBack() {
        this.$router.go(-1)
      },
    },
    beforeDestroy() {
      clearInterval(this.timer);
      this.timer = null;
    },
    created() {
      let that = this;
      that.timer = setInterval(that.init, 5000);
      that.init();
    }
  }
