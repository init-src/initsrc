<template>
  <el-header>
    <div class="dshfn-header-logo" @click="home()">
      <span class="logo-lg">
        <img src="../../assets/img/minilogo.png" alt="" height="30">
      </span>
      <span class="logo-sm">
        <div style="float: left;">
          <img src="../../assets/img/logo.png" alt="" height="50">
        </div>
      </span>
    </div>
    <div class="dshfn-tap" @click="collapse"><i :class="THEAM.ISCOLLAPSE?'el-icon-s-unfold':'el-icon-s-fold'"></i></div>
    <div class="dshfn-right">
      <div class="dshfn-tap" @click="fullScreen()"><i :class="THEAM.ISSCREENFULL?'initsrc-icon iconsuoxiao':'initsrc-icon iconfangda'"
          style="font-size: 20px;"></i></div>
      <div class="dshfn-tap" @click="msg"><i class="el-icon-bell"></i></div>
      <div class="dshfn-tap header-info-fade">
        <el-dropdown @command="handleUserInfo">
          <span class="el-dropdown-link">
            <div class="header-info">
              <el-avatar :size="30" :src="USER_INFO.headImg != null && USER_INFO.headImg != ''?USER_INFO.headImg:require('@/assets/img/head.png')"
                style="vertical-align: middle;margin-top: -6px;"></el-avatar>
              <div class="name">
                <span class="dshfn-header-name">{{USER_INFO.nickName}}</span>
                <i class="el-icon-arrow-down el-icon--right name-arrow"></i>
              </div>
            </div>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="logout">
              <span style="color: #FF715B !important;font-weight: 400;">
                <i class="el-icon-switch-button"></i>退 出
              </span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
      <div class="dshfn-tap" @click="setting"><i class="el-icon-setting"></i></div>
    </div>
  </el-header>
</template>

<script>
  import screenfull from 'screenfull'
  export default {
    data() {
      return {
        THEAM: {},
        USER_INFO: {},
      }
    },
    methods: {
      //回到首页
      home() {
        this.$router.push({
          path: "/"
        })
      },
      //收缩菜单栏
      collapse() {
        let that = this;
        if (that.THEAM.ISDEVICE === 1) {
          that.$store.state.ts.ISCOLLAPSE = false;
          that.$store.state.ts.ISDEVICEMENU = !that.$store.state.ts.ISDEVICEMENU;
        } else {
          that.$store.state.ts.ISCOLLAPSE = !that.$store.state.ts.ISCOLLAPSE;
          if (that.$store.state.ts.ISCOLLAPSE) {
            that.$store.state.ts.ISCOLLAPSESIZE = "64px";
          } else {
            that.$store.state.ts.ISCOLLAPSESIZE = "200px";
          }
        }
      },
      //全屏按钮
      fullScreen() {
        this.THEAM.ISSCREENFULL = !this.THEAM.ISSCREENFULL;
        screenfull.toggle();
      },
      //用户操作
      handleUserInfo(command) {
        let that = this;
        if (command == 'logout') {
          this.$api.baseRequest
            .outLogin({})
            .then(res => {
              res = res.data
              if (res.code == 0) {
                that.$store.commit("_SET_LOGIN_OUT")
                that.$router.push({ //你需要接受路由的参数再跳转
                  path: "/login"
                });
              } else {
                this.$notify.error({
                  title: '提示',
                  message: res.msg
                });
              }
            })
        }
      },
      //设置主题事件
      setting() {
        this.$parent.$refs.setting.init();
      },
      msg() {
        this.$parent.$refs.msg.init();
      }
    },
    mounted() {
      this.USER_INFO = this.$store.state.ps.USER_INFO
      this.THEAM = this.$store.state.ts
    }
  }
</script>

<style>
</style>
