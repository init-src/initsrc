<template>
  <div>
    <el-row :gutter="24" class="dshfn-content-main ">
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
                <div style="margin-top: 0.5rem;">New Orders</div>
              </div>
            </div>
            <h4 style="margin-top: 1.5rem;">1,068</h4>
            <div class="row">
              <el-col :span="10" style="padding: 0px;">
                <p class="mb-0"><span style="color: #45cb85;"> 0.26% <i class="el-icon-top"></i> </span></p>
              </el-col>
              <el-col :span="14">
                <el-progress :text-inside="true" :stroke-width="20" :percentage="60"></el-progress>
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
                <div style="margin-top: 0.5rem;">New Person</div>
              </div>
            </div>
            <h4 style="margin-top: 1.5rem;">1,868</h4>
            <div class="row">
              <el-col :span="10" style="padding: 0px;">
                <p class="mb-0"><span style="color: #45cb85;"> 0.29% <i class="el-icon-top"></i> </span></p>
              </el-col>
              <el-col :span="14">
                <el-progress :text-inside="true" :stroke-width="20" status="success" :percentage="80"></el-progress>
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
                <div style="margin-top: 0.5rem;">New Car</div>
              </div>
            </div>
            <h4 style="margin-top: 1.5rem;">968</h4>
            <div class="row">
              <el-col :span="10" style="padding: 0px;">
                <p class="mb-0"><span style="color: #45cb85;"> 0.28% <i class="el-icon-top"></i> </span></p>
              </el-col>
              <el-col :span="14">
                <el-progress :text-inside="true" :stroke-width="20" status="warning" :percentage="56"></el-progress>
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
                <div style="margin-top: 0.5rem;">New Food</div>
              </div>
            </div>
            <h4 style="margin-top: 1.5rem;">1,368</h4>
            <div class="row">
              <el-col :span="10" style="padding: 0px;">
                <p class="mb-0"><span style="color: #45cb85;"> 0.28% <i class="el-icon-top"></i> </span></p>
              </el-col>
              <el-col :span="14">
                <el-progress :text-inside="true" :stroke-width="20" status="exception" :percentage="70"></el-progress>
              </el-col>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :md="24" :lg="12" :xl="12" class="col-padding">
        <div class="dshfn-card dshfn-card-bottom">
          <div class="dshfn-card-body" style="position: relative;">
            <h4 class="card-title">Sales Report</h4>
            <div>
              <apexchart height="282" :options="chartOptions" :series="chartOptions.series"></apexchart>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :md="24" :lg="6" :xl="6" class="col-padding">
        <div class="dshfn-card">
          <div class="dshfn-card-body" style="position: relative;">
            <h4 class="card-title">Revenue</h4>
            <div>
              <apexchart height="282" width="100%" :options="chartOptionsColumn" :series="chartOptionsColumn.series"></apexchart>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :md="24" :lg="6" :xl="6" class="col-padding">
        <div class="dshfn-card dshfn-card-bottom">
          <div class="dshfn-card-body" style="position: relative;">
            <h4 class="card-title">Yearly sales</h4>
            <div>
              <apexchart height="282" width="100%" :options="chartOptionsRadar" :series="chartOptionsRadar.series"></apexchart>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :md="24" :lg="12" :xl="12" class="col-padding">
        <div class="dshfn-card dshfn-card-bottom">
          <div class="dshfn-card-body" style="position: relative;">
            <h4 class="card-title">Sales Analytics</h4>
            <div>
              <el-col :md="24" :lg="12" :xl="12" style="padding: 0px;">
                <apexchart width="100%" height="282" :options="chartOptionsDonut" :series="chartOptionsDonut.series"></apexchart>
              </el-col>
              <el-col :md="24" :lg="10" :xl="10" style="padding: 0px;margin-top: 30px;">
                <el-row :gutter="24">
                  <el-col :span="12" style="padding-bottom: 1rem;padding-top: 1rem;">
                    <p class="mb-1 text-truncate"><i class="el-icon-s-help" style="color: #3b5de7;"></i> Online</p>
                    <h5>$ 2,652</h5>

                  </el-col>
                  <el-col :span="12" style="padding-bottom: 1rem;padding-top: 1rem;">
                    <p class="mb-1 text-truncate"><i class="el-icon-s-help" style="color: #45cb85;"></i> Offline</p>
                    <h5>$ 2,284</h5>

                  </el-col>
                  <el-col :span="12" style="padding-bottom: 1rem;padding-top: 1rem;">
                    <p class="mb-1 text-truncate"><i class="el-icon-s-help" style="color: #eeb902;"></i>
                      Marketing</p>
                    <h5>$ 1,753</h5>

                  </el-col>
                </el-row>
              </el-col>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :md="24" :lg="12" :xl="12" class="col-padding">
        <div class="dshfn-card dshfn-card-bottom">
          <div class="dshfn-card-body" style="position: relative;">
            <h4 class="card-title">Monthly Sales</h4>
            <div>
              <apexchart height="245" width="100%" :options="chartOptionsScatter" :series="chartOptionsColumn.series"></apexchart>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>

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
        chartOptionsColumn: {
          chart: {
            type: 'bar',
            height: "200px",
            stacked: true,
            toolbar: {
              show: false
            },
            zoom: {
              enabled: true
            }
          },
          plotOptions: {
            bar: {
              horizontal: false,
              columnWidth: '20%',
              endingShape: 'rounded'
            }
          },
          dataLabels: {
            enabled: false
          },
          xaxis: {
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun']
          },
          colors: ['#eef3f7', '#ced6f9', '#3b5de7'],
          fill: {
            opacity: 1
          },
          series: [{
            name: 'Series A',
            data: [11, 17, 15, 15, 21, 14]
          }, {
            name: 'Series B',
            data: [13, 23, 20, 8, 13, 27]
          }, {
            name: 'Series C',
            data: [44, 55, 41, 67, 22, 43]
          }]
        },
        chartOptions: {
          series: [{
            name: "2018",
            type: 'line',
            data: [20, 34, 27, 59, 37, 26, 38, 25]
          }, {
            name: "2019",
            data: [10, 24, 17, 49, 27, 16, 28, 15],
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
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug'],
            title: {
              text: 'Month'
            }
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
        chartOptionsDonut: {
          series: [38, 26, 14],
          chart: {
            height: 230,
            type: 'donut'
          },
          labels: ["Online", "Offline", "Marketing"],
          plotOptions: {
            pie: {
              donut: {
                size: '75%'
              }
            }
          },
          legend: {
            show: false
          },
          colors: ['#3b5de7', '#45cb85', '#eeb902']
        },
        chartOptionsScatter: {
          series: [{
            name: "Series A",
            data: [
              [2, 5],
              [7, 2],
              [4, 3],
              [5, 2],
              [6, 1],
              [1, 3],
              [2, 7],
              [8, 0],
              [9, 8],
              [6, 0],
              [10, 1]
            ]
          }, {
            name: "Series B",
            data: [
              [15, 13],
              [7, 11],
              [5, 8],
              [9, 17],
              [11, 4],
              [14, 12],
              [13, 14],
              [8, 9],
              [4, 13],
              [7, 7],
              [5, 8],
              [4, 3]
            ]
          }],
          chart: {
            height: 230,
            type: 'scatter',
            toolbar: {
              show: false
            },
            zoom: {
              enabled: true,
              type: 'xy'
            }
          },
          colors: ['#3b5de7', '#45cb85'],
          xaxis: {
            tickAmount: 10
          },
          legend: {
            position: 'top'
          },
          yaxis: {
            tickAmount: 7
          }
        },
        chartOptionsRadar: {
          series: [{
            name: 'Series 1',
            data: [80, 50, 30, 40, 100, 20]
          }, {
            name: 'Series 2',
            data: [20, 30, 40, 80, 20, 80]
          }, {
            name: 'Series 3',
            data: [44, 76, 78, 13, 43, 10]
          }],
          chart: {
            height: 250,
            type: 'radar',
            dropShadow: {
              enabled: true,
              blur: 1,
              left: 1,
              top: 1
            },
            toolbar: {
              show: false
            }
          },
          stroke: {
            width: 0
          },
          fill: {
            opacity: 0.4
          },
          markers: {
            size: 0
          },
          colors: ['#3b5de7', '#5fd195', '#eeb902'],
          xaxis: {
            categories: ['2014', '2015', '2016', '2017', '2018', '2019']
          }
        }
      }
    },
    methods: {

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

  .card-title {
    color: #495057;
    font-size: 15px;
    margin: 0 0 7px 0;
    font-weight: 600;
  }
</style>
