<template>
  <div class="dshfn-content" v-loading="loading">
    <el-row :gutter="24" class="dshfn-content-main">
      <el-col :md="24" :lg="24" :xl="24" style="padding: 0px;">
        <el-col :md="24" :lg="12" :xl="12" class="col-padding">
          <div class="dshfn-card dshfn-card-bottom">
            <div class="dshfn-card-body" style="position: relative;">
              <h4 class="card-title">CPU <span v-if="form.cpu">({{form.cpu.cpuNum}}核)</span></h4>
              <div>
                <el-col :md="24" :lg="12" :xl="12" style="padding: 0px;">
                  <apexchart width="100%" height="282" :options="chartOptionsCpu" :series="chartOptionsCpu.series"></apexchart>
                </el-col>
                <el-col :md="24" :lg="10" :xl="10" style="padding: 0px;margin-top: 30px;">
                  <el-row :gutter="24">
                    <el-col :span="12" style="padding-bottom: 1rem;padding-top: 1rem;">
                      <p class="mb-1 text-truncate"><i class="el-icon-s-help" style="color: #3b5de7;"></i> 用户使用率</p>
                      <h5 v-if="form.cpu">{{form.cpu.used}}%</h5>

                    </el-col>
                    <el-col :span="12" style="padding-bottom: 1rem;padding-top: 1rem;">
                      <p class="mb-1 text-truncate"><i class="el-icon-s-help" style="color: #45cb85;"></i> 系统使用率</p>
                      <h5 v-if="form.cpu">{{form.cpu.sys}}%</h5>

                    </el-col>
                    <el-col :span="12" style="padding-bottom: 1rem;padding-top: 1rem;">
                      <p class="mb-1 text-truncate"><i class="el-icon-s-help" style="color: #eeb902;"></i>
                        当前空闲率</p>
                      <h5 v-if="form.cpu">{{form.cpu.free}}%</h5>

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
              <h4 class="card-title">内存</h4>
              <div>
                <el-col :md="24" :lg="12" :xl="12" style="padding: 0px;">
                  <apexchart width="100%" height="282" :options="chartOptionsMem" :series="chartOptionsMem.series"></apexchart>
                </el-col>
                <el-col :md="24" :lg="10" :xl="10" style="padding: 0px;margin-top: 30px;">
                  <el-row :gutter="24">
                    <table cellspacing="0" class="detail-info-table">
                      <thead>
                        <tr>
                          <th class="is-leaf">
                            <div class="cell">属性</div>
                          </th>
                          <th class="is-leaf">
                            <div class="cell">内存</div>
                          </th>
                          <th class="is-leaf">
                            <div class="cell">JVM</div>
                          </th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>
                            <div class="cell">总内存</div>
                          </td>
                          <td>
                            <div class="cell" v-if="form.mem">{{ form.mem.total }}G</div>
                          </td>
                          <td>
                            <div class="cell" v-if="form.jvm">{{ form.jvm.total }}M</div>
                          </td>
                        </tr>
                        <tr>
                          <td>
                            <div class="cell">已用内存</div>
                          </td>
                          <td>
                            <div class="cell" v-if="form.mem">{{ form.mem.used}}G</div>
                          </td>
                          <td>
                            <div class="cell" v-if="form.jvm">{{ form.jvm.used}}M</div>
                          </td>
                        </tr>
                        <tr>
                          <td>
                            <div class="cell">剩余内存</div>
                          </td>
                          <td>
                            <div class="cell" v-if="form.mem">{{ form.mem.free }}G</div>
                          </td>
                          <td>
                            <div class="cell" v-if="form.jvm">{{ form.jvm.free }}M</div>
                          </td>
                        </tr>
                        <tr>
                          <td>
                            <div class="cell">使用率</div>
                          </td>
                          <td>
                            <div class="cell" v-if="form.mem" :class="{'text-danger': form.mem.usage > 80}">{{ form.mem.usage }}%</div>
                          </td>
                          <td>
                            <div class="cell" v-if="form.jvm" :class="{'text-danger': form.jvm.usage > 80}">{{ form.jvm.usage }}%</div>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </el-row>
                </el-col>
              </div>
            </div>
          </div>
        </el-col>
        <el-col :span="24" class="col-padding">
          <div class="dshfn-card dshfn-card-bottom">
            <div class="dshfn-card-body">
              <h4 class="card-title">服务器信息</h4>
              <el-row :gutter="24" class="dshfn-detail-info">
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="12">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">服务器名称：</label>
                    <label class="dshfn-label-item__content" v-if="form.sys">{{form.sys.computerName}}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="12">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">操作系统：</label>
                    <label class="dshfn-label-item__content" v-if="form.sys">{{form.sys.osName}}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="12">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">服务器IP：</label>
                    <label class="dshfn-label-item__content" v-if="form.sys">{{form.sys.computerIp}}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="12">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">系统架构：</label>
                    <label class="dshfn-label-item__content" v-if="form.sys">{{form.sys.osArch}}</label>
                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-col>
        <el-col :span="24" class="col-padding">
          <div class="dshfn-card dshfn-card-bottom">
            <div class="dshfn-card-body">
              <h4 class="card-title">Java虚拟机信息</h4>
              <el-row :gutter="24" class="dshfn-detail-info">
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="8">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">Java名称：</label>
                    <label class="dshfn-label-item__content" v-if="form.jvm">{{form.jvm.name}}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="8">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">Java版本：</label>
                    <label class="dshfn-label-item__content" v-if="form.jvm">{{form.jvm.version}}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="8">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">启动时间：</label>
                    <label class="dshfn-label-item__content" v-if="form.jvm">{{form.jvm.startTime}}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="8">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">运行时长：</label>
                    <label class="dshfn-label-item__content" v-if="form.jvm">{{form.jvm.runTime}}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="8">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">安装路径：</label>
                    <label class="dshfn-label-item__content" v-if="form.jvm">{{form.jvm.home}}</label>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="8">
                  <div class="dshfn-label-item">
                    <label class="dshfn-label-item__label">项目路径：</label>
                    <label class="dshfn-label-item__content" v-if="form.sys">{{form.sys.userDir }}</label>
                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-col>
        <el-col :span="24" class="col-padding">
          <div class="dshfn-card dshfn-card-bottom">
            <div class="dshfn-card-body">
              <h4 class="card-title">磁盘状态</h4>
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
    width: 90px !important;
    text-align: left !important;
  }

  .detail-info-table {
    border-spacing: 0;
    border-collapse: collapse;
    width: 100%;
  }

  .detail-info-table td {
    border-bottom: 1px solid #dfe6ec;
    padding: 10px 5px;
    line-height: 1.42857143;
    color: #8687a7;
    font-size: 14px;
  }

  .detail-info-table th {
    border-bottom: 1px solid #dfe6ec;
    text-align: left;
    padding: 5px;
    font-weight: 400;
    font-size: 14px;
    color: #999;
    line-height: 1.42857143;
  }
</style>
