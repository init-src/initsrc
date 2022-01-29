<template>
  <el-dialog :title="formName" width="700px" :fullscreen="$store.state.ts.ISDEVICE==0?false:true" :visible.sync="dialogFormVisible"
    :close-on-click-modal="false" @close="closeDialog" v-loading="loading">
    <el-form :model="form" :rules="rules" ref="ruleForm" label-width="90px">
      <el-row :gutter="20">
        <el-col :span="20">
          <el-form-item label="上级部门:" prop="parentId">
            <i-tree-select v-model="form.parentId" :normalizer="normalizer" placeholder="请选择上级部门" :options="parentIdList"></i-tree-select>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="部门编号:" prop="code">
            <el-input v-model="form.code" placeholder="请输入部门编号" clearable maxlength="30" show-word-limit></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="部门名称:" prop="name">
            <el-input v-model="form.name" placeholder="请输入部门名称" clearable maxlength="30" show-word-limit></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="负责人:" prop="linkMan">
            <el-input v-model="form.linkMan" placeholder="请输入负责人" clearable maxlength="30" show-word-limit></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="联系电话:" prop="linkMobile">
            <el-input v-model="form.linkMobile" placeholder="请输入联系电话" clearable maxlength="30" show-word-limit></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="排序:" prop="sort">
            <el-input-number size="medium" v-model="form.sort" controls-position="right" placeholder="请输入排序" style="width: 100%;"
              :min="0">
            </el-input-number>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="状态:" prop="status">
            <el-radio-group v-model="form.status" style="width: 100%;">
              <template v-for="(item, index) in statusList">
                <el-radio-button :label="item.key">{{item.label}}</el-radio-button>
              </template>
            </el-radio-group>
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

<script src="../js/indexEdit.js">
  
</script>

<style scoped>
  /deep/.el-dialog__body {
    height: 50vh;
    overflow: auto;
  }
</style>
