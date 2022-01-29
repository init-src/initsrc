<template>
  <div class="dshfn-header-tabs" :style="THEAM.ISFIXED==1?THEAM.ISDEVICE==1||THEAM.ISCROSS?'position:fixed;':(THEAM.ISCOLLAPSE?'position:fixed;width: calc(100% - 60px);':
  'position:fixed;width: calc(100% - 200px);'):''">
    <el-tabs class="dshfn-tabs-content" v-model="$store.state.ps.ACTIVE_TABS" closable type="card" @tab-click="clickTab"
      @tab-remove="removeTab">
      <template v-for="(item, index) in $store.state.ps.TABS_LIST">
        <el-tab-pane :key="item.name" :label="item.title" :name="item.name">
        </el-tab-pane>
      </template>
    </el-tabs>
    <el-dropdown class="dshfn-tabs-more" @command="handleCommand">
      <span class="el-dropdown-link">
        <i class="el-icon-menu"></i>
      </span>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item icon="el-icon-close" command="other">关闭其他</el-dropdown-item>
          <el-dropdown-item icon="el-icon-back" command="left">关闭左侧</el-dropdown-item>
          <el-dropdown-item icon="el-icon-right" command="right">关闭右侧</el-dropdown-item>
          <el-dropdown-item icon="el-icon-circle-close" command="all">关闭全部</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        THEAM: {},
      }
    },
    mounted() {
      this.THEAM = this.$store.state.ts
    },
    methods: {
      removeTab(targetName) {
        this.$store.commit("_SET_TABS_LIST", targetName);
      },
      clickTab(tab) {
        this.$router.push(tab.name)
      },
      //操作tabs
      handleCommand(command) {
        this.$store.commit("_SET_TABS_M", command);
      }
    }
  }
</script>

<style>
</style>
