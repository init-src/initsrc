<template>
  <el-aside :width="THEAM.ISCROSS?'0px':THEAM.ISCOLLAPSESIZE" :style="THEAM.ISDEVICEMENU?'width:100%;display: inline-block;position: absolute;z-index:200;':''">
    <div class="dshfn-aside-user" v-if="THEAM.ISCOLLAPSE==false">
      <div>
        <img :src="USER_INFO.headImg != null && USER_INFO.headImg != ''?USER_INFO.headImg:require('../../assets/img/head.png')"
          alt="" class="user-img">
      </div>
      <div class="user-info">
        <a href="#">{{USER_INFO.nickName}}</a>
        <p class="text-body">
          <template v-for="item in USER_INFO.roles">
            {{item.roleName}}
          </template>
        </p>
      </div>
    </div>
    <div class="dshfn-aside-user" v-else>
      <div>
        <img :src="USER_INFO.headImg != null && USER_INFO.headImg != ''?USER_INFO.headImg:require('../../assets/img/head.png')" style="width: 40px;height: 40px;" alt="" class="user-img">
      </div>
      <div class="user-info">
        <a href="#">{{USER_INFO.nickName}}</a>
      </div>
    </div>
    <div class="dshfn-aside-menu" :style="THEAM.ISCOLLAPSE || THEAM.ISDEVICEMENU?'height: calc(100% - 118px);':''">
      <el-menu :default-active="$store.state.ps.ACTIVE_MENU" :unique-opened="true" :collapse="THEAM.ISCOLLAPSE" @select="handleSelect"
        :background-color="THEAM.THEAM_MENU.bc" :text-color="THEAM.THEAM_MENU.tc" :active-text-color="THEAM.THEAM_MENU.atc"
        router>
        <template v-for="(item, index) in $store.state.ps.MENU_LIST">
          <el-submenu :index="item.id" v-if="item.children != null && item.children[0].resource == 0">
            <template slot="title">
              <i :class="item.icon" style="font-size: 16px;"></i>
              <span>{{item.name}}</span>
            </template>
            <template v-for="(it, index) in item.children">
              <el-menu-item :index="it.linkType != 2?it.path:'?'+it.id" v-if="it.children == null || it.children[0].resource != 0">
                <span v-if="it.linkType != 2" slot="title">{{it.name}}</span>
                <a target="_blank" :href="it.path" v-else>{{it.name}}</a>
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
            <span v-if="item.linkType != 2">{{item.name}}</span>
            <a class="el-menu-item" target="_blank" :href="item.path" v-else>{{item.name}}</a>
          </el-menu-item>
        </template>
      </el-menu>
    </div>
  </el-aside>
</template>

<script>
  export default {
    data() {
      return {
        THEAM: {
          THEAM_MENU: {}
        },
        ISSHOW: false,
        USER_INFO: {}, //用户信息
      }
    },
    methods: {
      //点击菜单事件
      handleSelect() {
        let that = this;
        if (that.THEAM.ISDEVICE == 1) {
          that.$store.state.ts.ISDEVICEMENU = false;
        }
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
