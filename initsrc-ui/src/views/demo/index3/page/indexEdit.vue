<template>
  <el-drawer class="dshfn-commonDrawer" :title="formName" :visible.sync="drawer" :direction="direction"
    :wrapperClosable="true" :before-close="handleClose" append-to-body :size="$store.state.ts.ISDEVICE==0?'1000px':'100%'">
    <el-form :model="form" :rules="rules" class="dshfn-form dshfn-card-body add-footer" ref="ruleForm" >
      <el-row :gutter="24">
        <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="8">
          <el-form-item label="部门名称:" prop="name">
            <el-input v-model="form.name" placeholder="请输入部门名称"  maxlength="20" show-word-limit></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="8">
          <el-form-item label="部门编号:" prop="code">
            <el-input v-model="form.code" placeholder="请输入部门编号" maxlength="20" show-word-limit></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="8">
          <el-form-item label="上级部门:" prop="parentId">
            <el-select v-model="form.parentId" placeholder="请选择上级部门" style="width: 100%;">
              <el-option label="区域一" value="shanghai"></el-option>
              <el-option label="区域二" value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="8">
          <el-form-item label="上级部门:" prop="parentId">
            <el-select v-model="form.parentId" placeholder="请选择上级部门" style="width: 100%;">
              <el-option label="区域一" value="shanghai"></el-option>
              <el-option label="区域二" value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="8">
          <el-form-item label="上级部门:" prop="parentId">
            <el-select v-model="form.parentId" placeholder="请选择上级部门" style="width: 100%;">
              <el-option label="区域一" value="shanghai"></el-option>
              <el-option label="区域二" value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="14" :md="10" :lg="10" :xl="8">
          <el-form-item label="部门主管:" prop="region">
            <el-select v-model="form.region" placeholder="请选择部门主管" style="width: 100%;">
              <el-option label="区域一" value="shanghai"></el-option>
              <el-option label="区域二" value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-col>

      </el-row>

    </el-form>
    <div class="footer">
      <el-button type="primary" @click="save('ruleForm')" :loading="loading">确 定</el-button>
     <el-button @click="drawer = false">取 消</el-button>
    </div>
  </el-drawer>
</template>

<script src="../js/indexEdit.js">

</script>

<style scoped>
  /deep/.el-dialog__body {
    height: 40vh;
    overflow: auto;
  }
</style>
