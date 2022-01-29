<template>
  <el-dialog title="导出账单合计"  width="400px" :fullscreen="$store.state.ts.ISDEVICE==0?false:true" :visible.sync="dialogFormVisible" :close-on-click-modal="false"
    v-loading="loading">
    <el-form :model="form"  ref="ruleForm" label-position="top" >
      <el-row :gutter="20">
        <el-col :span="20">
          <el-form-item label="导出账单内容" prop="name">
            <el-radio-group v-model="form.name">
              <el-radio label="1">当前账号</el-radio>
              <el-radio label="2">全部账号</el-radio>
            </el-radio-group>
          </el-form-item>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="账期" prop="time">
            <el-date-picker v-model="form.time" type="monthrange" align="right" unlink-panels range-separator="-"
              start-placeholder="开始月份" end-placeholder="结束月份" :picker-options="pickerOptions">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>

    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="save('ruleForm')" :loading="loading">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script src="../js/indexExport.js">

</script>

<style scoped>
  /deep/.el-dialog__body {
    height: 30vh;
    overflow: auto;
  }
</style>
