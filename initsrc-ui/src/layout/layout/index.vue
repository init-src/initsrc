<template>
  <div class="dshfn-admin" id="layout-wrapper" :class="
  THEAM.THEME_HEADER_STYLE==1?'header-color1':THEAM.THEME_HEADER_STYLE==2?'header-color2':THEAM.THEME_HEADER_STYLE==2?'header-color2':
  THEAM.THEME_HEADER_STYLE==3?'header-color3':THEAM.THEME_HEADER_STYLE==4?'header-color4':THEAM.THEME_HEADER_STYLE==5?'header-color5':null">
    <is-header v-if="THEAM.ISCROSS==false"></is-header>
    <is-cross-header v-if="THEAM.ISCROSS"></is-cross-header>
    <el-container class="dshfn-main">
      <is-menu></is-menu>
      <is-main></is-main>
    </el-container>
    <setting ref="setting"></setting>
     <msg ref="msg"></msg>
  </div>
</template>

<script>
  import setting from '@/layout/setting/index.vue'
  import msg from '@/layout/msg/index.vue'
  export default {
    components: {
      setting,
      msg,
    },
    data() {
      return {
        THEAM: {},
      }
    },
    mounted() {
      let that = this;
      that.THEAM = that.$store.state.ts
      var screenWidth = document.body.clientWidth;
      that.$store.commit('_SET_IS_DEVICE', screenWidth)
      window.onresize = () => {
        return (() => {
          var screenWidth = document.body.clientWidth;
          that.$store.commit('_SET_IS_DEVICE', screenWidth)
        })();
      };
    },
  }
</script>

<style>
</style>
