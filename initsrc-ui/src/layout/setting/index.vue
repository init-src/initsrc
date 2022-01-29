<template>
  <el-drawer title="主题设置" class="dshfn-commonDrawer" :visible.sync="drawer" :direction="direction" :size="$store.state.ts.ISDEVICE==0?'370px':'100%'">
    <div class="dshfn-form">
      <el-form ref="form" class="dshfn-card-body add-footer" :model="THEAM" label-width="80px">
        <el-form-item label="主题颜色:">
          <el-select v-model="THEAM.THEME_STYLE" placeholder="请选择主题颜色">
            <el-option label="亮色" value="light"></el-option>
            <el-option label="暗黑" value="dark"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="头部主题:">
          <el-select v-model="THEAM.THEME_HEADER_STYLE" placeholder="请选择头部颜色">
            <el-option label="蓝色妖姬" :value="1"></el-option>
            <el-option label="海洋之心" :value="2"></el-option>
            <el-option label="暗黑幽梦" :value="3"></el-option>
            <el-option label="紫罗妖艳" :value="4"></el-option>
            <el-option label="桔黄桔绿" :value="5"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="界面布局:">
          <el-select v-model="THEAM.ISCROSS" placeholder="请选择界面布局">
            <el-option label="正常" :value="false"></el-option>
            <el-option label="横向" :value="true"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="横向属性:" v-if="THEAM.ISCROSS">
          <el-select v-model="THEAM.ISCROSSPROP" placeholder="请选择横向属性">
            <el-option label="正常" :value="false"></el-option>
            <el-option label="仅图标" :value="true"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标签设置:">
          <el-select v-model="THEAM.ISFIXED" placeholder="请选择标签设置">
            <el-option label="固定" :value="1"></el-option>
            <el-option label="不固定" :value="2"></el-option>
            <!--   <el-option label="不展示" :value="3"></el-option> -->
          </el-select>
        </el-form-item>
      </el-form>
      <div class="footer">
        <el-button type="primary" @click="saveSetting('ruleForm')">确 定</el-button>
        <el-button @click="drawer = false">取 消</el-button>
      </div>
    </div>
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

<style>
</style>
