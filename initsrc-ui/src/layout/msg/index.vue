<template>
  <el-drawer title="消息中心" class="dshfn-commonDrawer" :visible.sync="drawer" :direction="direction" :size="$store.state.ts.ISDEVICE==0?'400px':'100%'">
    <el-row :gutter="24" class="dshfn-content-main notify-box ">
      <el-col :md="24" :lg="24" :xl="24" class="col-padding">
        <div class="dshfn-card dshfn-card-bottom">
          <div class="dshfn-card-body">
            <div class="media">
              <div style="font-size: 20px;height: 2.5rem;width: 2.5rem;margin-right: 1rem;">
                <span class="avatar-title bg-soft-primary text-primary rounded">
                  <i class="el-icon-star-on"></i>
                </span>
              </div>
              <div class="media-body">
                <div style="margin-top: 0.5rem;">INITSRC</div>
              </div>
            </div>
            <div class="row">
                我们的友谊就这样结束了？...
            </div>
          </div>
        </div>
      </el-col>
      <el-col :md="24" :lg="24" :xl="24" class="col-padding">
        <div class="dshfn-card dshfn-card-bottom">
          <div class="dshfn-card-body">
            <div class="media">
              <div style="font-size: 20px;height: 2.5rem;width: 2.5rem;margin-right: 1rem;">
                <span class="avatar-title bg-soft-primary text-primary rounded">
                  <i class="el-icon-star-on"></i>
                </span>
              </div>
              <div class="media-body">
                <div style="margin-top: 0.5rem;">INITSRC</div>
              </div>
            </div>
            <div class="row">
                我们的友谊就这样结束了？...
            </div>
          </div>
        </div>
      </el-col>
      <el-col :md="24" :lg="24" :xl="24" class="col-padding">
        <div class="dshfn-card dshfn-card-bottom">
          <div class="dshfn-card-body">
            <div class="media">
              <div style="font-size: 20px;height: 2.5rem;width: 2.5rem;margin-right: 1rem;">
                <span class="avatar-title bg-soft-primary text-primary rounded">
                  <i class="el-icon-star-on"></i>
                </span>
              </div>
              <div class="media-body">
                <div style="margin-top: 0.5rem;">INITSRC</div>
              </div>
            </div>
            <div class="row">
                我们的友谊就这样结束了？...
            </div>
          </div>
        </div>
      </el-col>
      </el-row>
  </el-drawer>
</template>

<script>
  export default {
    data() {
      return {
        THEAM: {},
        drawer: false,
        direction: 'rtl',
      }
    },
    methods: {
      init() {
        let that = this;
        that.drawer = true;
        that.THEAM = JSON.parse(JSON.stringify(that.$store.state.ts))
      },
      saveSetting() {
        this.drawer = false;
        this.$store.commit('theamSetting', this.THEAM)
      }
    },
  }
</script>

<style scoped>
  .notify-box {
      padding: 10px 0;
      cursor: pointer;
      /* background-color: #f6f8f9; */
  }

  .row{
    padding-top: 10px;
  }

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
</style>
