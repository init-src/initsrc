<template>
  <div class="dshfn-content" v-loading="loading">
    <el-row :gutter="24" class="dshfn-content-main">
      <el-col :md="24" :lg="24" :xl="24" style="padding: 0px;">
        <el-col :md="24" :lg="24" :xl="12" class="col-padding">
          <div class="dshfn-card dshfn-card-bottom">
            <div class="dshfn-card-body" style="position: relative;">
              <h4 class="card-title">服务器</h4>
              <el-row :gutter="24" class="dshfn-detail-info">
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="12">
                  <div class="dshfn-label-item isBox">
                    <label class="dshfn-label-item__label">Redis版本：</label>
                    <label class="dshfn-label-item__content green" v-if="form.info">{{form.info.redis_version }}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="12">
                  <div class="dshfn-label-item isBox">
                    <label class="dshfn-label-item__label">OS：</label>
                    <label class="dshfn-label-item__content green" v-if="form.info">{{form.info.os}}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="12">
                  <div class="dshfn-label-item isBox">
                    <label class="dshfn-label-item__label">运行模式：</label>
                    <label class="dshfn-label-item__content green" v-if="form.info">{{ form.info.redis_mode == "standalone" ? "单机" : "集群" }}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="12">
                  <div class="dshfn-label-item isBox">
                    <label class="dshfn-label-item__label">运行天数(天)：</label>
                    <label class="dshfn-label-item__content green" v-if="form.info">{{ form.info.uptime_in_days }}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="12">
                  <div class="dshfn-label-item isBox">
                    <label class="dshfn-label-item__label">端口：</label>
                    <label class="dshfn-label-item__content green" v-if="form.info">{{form.info.tcp_port }}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="12">
                  <div class="dshfn-label-item isBox">
                    <label class="dshfn-label-item__label">进程ID：</label>
                    <label class="dshfn-label-item__content green" v-if="form.info">{{form.info.process_id}}</label>
                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-col>
        <el-col :md="24" :lg="24" :xl="6" class="col-padding">
          <div class="dshfn-card dshfn-card-bottom">
            <div class="dshfn-card-body" style="position: relative;">
              <h4 class="card-title">内存</h4>
              <el-row :gutter="24" class="dshfn-detail-info">
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                  <div class="dshfn-label-item isBox">
                    <label class="dshfn-label-item__label">已用内存：</label>
                    <label class="dshfn-label-item__content green" v-if="form.info">{{form.info.used_memory_human }}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                  <div class="dshfn-label-item isBox">
                    <label class="dshfn-label-item__label">内存占用峰值：</label>
                    <label class="dshfn-label-item__content green" v-if="form.info">{{form.info.used_memory_peak_human }}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                  <div class="dshfn-label-item isBox">
                    <label class="dshfn-label-item__label">Lua占用内存：</label>
                    <label class="dshfn-label-item__content green" v-if="form.info">{{form.info.used_memory_lua_human }}</label>
                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-col>
        <el-col :md="24" :lg="24" :xl="6" class="col-padding">
          <div class="dshfn-card dshfn-card-bottom">
            <div class="dshfn-card-body" style="position: relative;">
              <h4 class="card-title">状态</h4>
              <el-row :gutter="24" class="dshfn-detail-info">
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                  <div class="dshfn-label-item isBox">
                    <label class="dshfn-label-item__label">客户连接数：</label>
                    <label class="dshfn-label-item__content green" v-if="form.info">{{form.info.connected_clients }}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                  <div class="dshfn-label-item isBox">
                    <label class="dshfn-label-item__label">历史连接数：</label>
                    <label class="dshfn-label-item__content green" v-if="form.info">{{form.info.total_connections_received }}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                  <div class="dshfn-label-item isBox">
                    <label class="dshfn-label-item__label">历史命令数：</label>
                    <label class="dshfn-label-item__content green" v-if="form.info">{{form.info.total_commands_processed }}</label>
                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-col>
        <el-col :md="24" :lg="24" :xl="12" class="col-padding">
          <div class="dshfn-card dshfn-card-bottom">
            <div class="dshfn-card-body">
              <h4 class="card-title">命令统计</h4>
              <el-row :gutter="24" class="dshfn-detail-info">
                <apexchart width="100%" height="300" :options="chartOptionsCommon" :series="chartOptionsCommon.series"></apexchart>
              </el-row>
            </div>
          </div>
        </el-col>
        <el-col :md="24" :lg="24" :xl="12" class="col-padding">
          <div class="dshfn-card dshfn-card-bottom">
            <div class="dshfn-card-body">
              <h4 class="card-title">内存消耗情况</h4>
              <el-row :gutter="24" class="dshfn-detail-info">
                <apexchart width="100%" height="300" :options="chartOptionsMem" :series="chartOptionsMem.series"></apexchart>
              </el-row>
            </div>
          </div>
        </el-col>
        <el-col :span="24" class="col-padding">
          <div class="dshfn-card dshfn-card-bottom">
            <div class="dshfn-card-body">
              <h4 class="card-title">Redis信息全集</h4>
              <el-row :gutter="24" class="dshfn-detail-info">
               <!--region table 表格-->
               <i-table ref="table" :optionsObj="options"  ></i-table>
               <!--endregion-->
              </el-row>
            </div>
          </div>
        </el-col>
      </el-col>
    </el-row>
  </div>
</template>

<script src="../js/index.js">

</script>

<style scoped>
  .dshfn-label-item__label {
    width: inherit !important;
  }

  .isBox {
    background-color: #f6f8f9;
    border-radius: 4px;
    padding: 5px;
    border: 1px solid lavender;
  }

  .green {
    color: limegreen;
  }
</style>
