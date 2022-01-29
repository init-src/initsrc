<template>
  <el-dialog :title="formName" width="700px" :fullscreen="$store.state.ts.ISDEVICE==0?false:true" :visible.sync="dialogFormVisible"
    :close-on-click-modal="false" @close="closeDialog" >
    <el-form :model="form" :rules="rules" ref="ruleForm" label-position="top" v-loading="loading">
      <el-row :gutter="20">
        <el-col :span="20">
          <el-form-item label="所属菜单:" prop="parentId">
            <i-tree-select v-model="form.parentId" :normalizer="normalizer" placeholder="请选择所属菜单" :options="parentIdList"></i-tree-select>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="菜单名称:" prop="name">
            <el-input v-model="form.name" placeholder="请输入菜单名称" clearable maxlength="30" show-word-limit></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="菜单路径:" prop="path">
            <el-input v-model="form.path" placeholder="请输入菜单路径" clearable maxlength="60" show-word-limit></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="菜单类型:" prop="resource">
            <el-radio-group v-model="form.resource" style="width: 100%;">
              <template v-for="(item, index) in resourceList">
                <el-radio-button :label="item.key">{{item.label}}</el-radio-button>
              </template>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="路由组件:" prop="component">
            <el-input v-model="form.component" placeholder="请输入路由组件" clearable maxlength="200" show-word-limit></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="shiro权限:" prop="perm">
            <el-input v-model="form.perm" placeholder="请输入shiro权限" clearable maxlength="200" show-word-limit></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="菜单图标:" prop="icon">
            <el-select v-model="form.icon" placeholder="请选择图标" style="width: 100%;">
              <el-option v-for="item in iconList" :key="item.key" :label="item.label" :value="item.key">
                <span style="float: left;color:yello;">
                  <i :class="item.key"></i>
                </span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.label }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="按钮颜色:" prop="color">
            <el-select v-model="form.color" placeholder="请选择按钮颜色" style="width: 100%;" clearable>
              <el-option v-for="item in colorList" :key="item.key" :label="item.label" :value="item.key">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="是否缓存:" prop="isCache">
            <el-radio-group v-model="form.isCache" style="width: 100%;">
              <template v-for="(item, index) in isCacheList">
                <el-radio-button :label="item.key">{{item.label}}</el-radio-button>
              </template>
            </el-radio-group>
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
          <el-form-item label="链接类型:" prop="linkType">
            <el-radio-group v-model="form.linkType" style="width: 100%;">
              <template v-for="(item, index) in linkTypeList">
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
    height: 60vh;
    overflow: auto;
  }
</style>
