<template>
  <el-header>
    <div class="dshfn-header-logo" @click="home()">
      <span class="logo-lg">
        <img src="../../assets/img/minilogo.png" alt="" height="20">
      </span>
      <span class="logo-sm">
        <div style="float: left;">
          <img src="../../assets/img/logo.png" alt="" height="40">
        </div>
      </span>
    </div>
    <div class="dshfn-tap" v-if="THEAM.ISDEVICE == 1" @click="collapse"><i :class="THEAM.ISCOLLAPSE?'el-icon-s-unfold':'el-icon-s-fold'"></i></div>
     <div class="dshfn-menu-tap" v-if="THEAM.ISDEVICE == 0">
       <el-menu class="el-menu-demo" :default-active="$store.state.ps.ACTIVE_MENU"  active-text-color="#007dff" mode="horizontal"
         router>
         <template v-for="(item, index) in $store.state.ps.MENU_LIST">
           <el-submenu :index="item.id" v-if="item.children != null && item.children[0].resource == 0">
             <template slot="title">
               <i :class="item.icon" style="font-size: 16px;"></i>
               <span v-if="!THEAM.ISCROSSPROP">{{item.name}}</span>
             </template>
             <template v-for="(it, index) in item.children">
               <el-menu-item :index="it.linkType != 2?it.path:'?'+it.id" v-if="it.children == null || it.children[0].resource != 0">
                 <span v-if="it.linkType != 2" slot="title" >{{it.name}}</span>
                 <a  target="_blank" :href="it.path" v-else>{{it.name}}</a>
                 </el-menu-item>
               </el-menu-item>
               <el-menu-item-group v-else>
                 <template slot="title">{{it.name}}</template>
                 <template v-for="(its, index) in it.children">
                   <el-menu-item :index="its.linkType != 2?its.path:'?'+its.id">
                     <span v-if="its.linkType != 2">{{its.name}}</span>
                     <a class="el-menu-item" target="_blank" :href="its.path" v-else>{{its.name}}</a></el-menu-item>
                 </template>
               </el-menu-item-group>
             </template>
           </el-submenu>
           <el-menu-item :index="item.linkType != 2?item.path:'?'+item.id" v-else>
             <i :class="item.icon"></i>
             <span v-if="item.linkType != 2"><span v-if="!THEAM.ISCROSSPROP">{{item.name}}</span></span>
             <a class="el-menu-item" target="_blank" :href="item.path" v-else><span v-if="!THEAM.ISCROSSPROP">{{item.name}}</span></a>
           </el-menu-item>
         </template>
       </el-menu>
     </div>
    <div class="dshfn-right">
     <div class="dshfn-tap" @click="fullScreen()"><i :class="THEAM.ISSCREENFULL?'initsrc-icon iconsuoxiao':'initsrc-icon iconfangda'" style="font-size: 20px;"></i></div>
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
          that.$store.state.ts.ISDEVICEMENU =  !that.$store.state.ts.ISDEVICEMENU;
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
      setting(){
        this.$parent.$refs.setting.init();
      },
      msg(){
        this.$parent.$refs.msg.init();
      }
    },
    mounted() {
     this.USER_INFO = this.$store.state.ps.USER_INFO
      this.THEAM = this.$store.state.ts
    }
  }
</script>

<style scoped>
	/*包含以下四种的链接*/
	a {
	    text-decoration: none;
	    color: inherit;
	}

</style>
