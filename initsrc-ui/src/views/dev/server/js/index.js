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
            prop: 'dirName',
            label: '盘符路径',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'sysTypeName',
            label: '文件系统',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'typeName',
            label: '盘符类型',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'total',
            label: '总大小',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'free',
            label: '可用大小',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'used',
            label: '已用大小',
            formatter: (row, column, cellValue) => {
              return this.common.isNull(cellValue)
            },
          },
          {
            prop: 'usage',
            label: '已用百分比',
            formatter: (row, column, cellValue) => {
              return this.common.transformDoubleByTwo(cellValue) + "%"
            },
          },
        ],
      },
      chartOptionsCpu: {
        series: [0, 0, 100],
        chart: {
          height: 230,
          type: 'donut'
        },
        responsive: [{
          breakpoint: 480,
          options: {
            legend: {
              position: 'bottom'
            }
          }
        }],
        labels: ["用户使用率", "系统使用率", "当前空闲率"],
        title: {
          text: "CPU使用率",
          align: 'center',
        },
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
          show: false
        },
        colors: ['#3b5de7', '#45cb85', '#eeb902'],
      },
      chartOptionsMem: {
        series: [0, 0],
        chart: {
          height: 390,
          type: 'radialBar',
        },
        plotOptions: {
          radialBar: {
            offsetY: 0,
            startAngle: 0,
            endAngle: 270,
            hollow: {
              margin: 5,
              size: '30%',
              background: 'transparent',
              image: undefined,
            },
            dataLabels: {
              name: {
                show: false,
              },
              value: {
                show: false,
              }
            }
          }
        },
        title: {
          text: "CPU使用率",
          align: 'center',
        },
        colors: ['#1ab7ea', '#0084ff', ],
        labels: ['内存', 'JVM', ],
        legend: {
          show: true,
          floating: true,
          fontSize: '12px',
          position: 'left',
          offsetX: 0,
          offsetY: 40,
          labels: {
            useSeriesColors: true,
          },
          markers: {
            size: 0
          },
          formatter: function(seriesName, opts) {
            return seriesName + ":  " + opts.w.globals.series[opts.seriesIndex] + "%"
          },
          itemMargin: {
            vertical: 3
          }
        },
        responsive: [{
          breakpoint: 480,
          options: {
            legend: {
              show: false
            }
          }
        }]
      },
    }
  },
  methods: {
    init() {
      let that = this;
      that.$api.devRequest.serverDetail({})
        .then(res => {
          res = res.data
          that.loading = false;
          that.options.loading = false;
          if (res.code == 0) {
            that.form = res.data
            var usage = [];
            if (that.form.mem != null) {
              usage[0] = that.form.mem.usage
            }
            if (that.form.jvm != null) {
              usage[1] = that.form.jvm.usage
            }
            that.chartOptionsMem.series = usage;
            if (that.form.cpu != null) {
              var use = [];
              use[0] = that.form.cpu.used;
              use[1] = that.form.cpu.sys;
              use[2] = that.form.cpu.free;
              that.chartOptionsCpu.series = use;
            }
            that.options.data = that.form.sysFiles;
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
    that.timer = setInterval(that.init, 8000);
    that.init();
  }
}
