<template>
  <el-dialog :title="formName"  width="700px" :fullscreen="$store.state.ts.ISDEVICE==0?false:true" :visible.sync="dialogFormVisible" :close-on-click-modal="false"
    @close="closeDialog" v-loading="loading">
    <el-form :model="form" :rules="rules" ref="ruleForm" label-width="90px" >
      <el-row :gutter="20">
        <el-col :span="20">
          <el-form-item label="部门名称:" prop="name">
            <el-input v-model="form.name" placeholder="请输入部门名称" maxlength="20" show-word-limit></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="部门编号:" prop="code">
            <el-input v-model="form.code" placeholder="请输入部门编号" maxlength="20" show-word-limit></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="上级部门:" prop="parentId">
            <el-select v-model="form.parentId" placeholder="请选择上级部门" style="width: 100%;">
              <el-option label="区域一" value="shanghai"></el-option>
              <el-option label="区域二" value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="部门主管:" prop="region">
            <el-select v-model="form.region" placeholder="请选择部门主管" style="width: 100%;">
              <el-option label="区域一" value="shanghai"></el-option>
              <el-option label="区域二" value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="部门额度:" prop="resource">
            <el-radio-group v-model="form.resource">
              <el-radio label="不限"></el-radio>
              <el-radio label="现在"></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>

    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="save('ruleForm')" :loading="loading">确 定</el-button>
    </div>
    <!-- <select-user ref="selectUser"></select-user> -->
  </el-dialog>
</template>

<script src="../js/indexEdit.js">

</script>

<style scoped>
  /deep/.el-dialog__body {
    height: 40vh;
    overflow: auto;
  }
</style>
