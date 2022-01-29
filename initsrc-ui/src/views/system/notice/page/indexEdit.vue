<template>
  <div class="dshfn-content" v-loading="loading">
    <div class="dshfn-content-header dshfn-card">
      <el-row :gutter="24">
        <el-col :span="24" class="col-no-padding">
          <div style="padding: 4px;">
            <el-page-header @back="common.goBack" :content="formName">
            </el-page-header>
          </div>
        </el-col>
      </el-row>
    </div>
    <el-row :gutter="24" class="dshfn-content-main add-background add-footer">
      <el-form :model="form" :rules="rules" ref="ruleForm" class="dshfn-form-content" label-position="top">
        <el-row>
          <el-col :md="24" :lg="16" :xl="8">
            <el-form-item label="公告标题:" prop="title">
              <el-input v-model="form.title" placeholder="请输入公告标题" clearable maxlength="30" show-word-limit></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :md="24" :lg="16" :xl="12">
            <el-form-item label="公告内容:" prop="content">
              <editor-bar v-model="form.content"></editor-bar>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :md="24" :lg="16" :xl="8">
            <el-form-item label="公告状态:" prop="status">
              <el-radio-group v-model="form.status" style="width: 100%;">
                <template v-for="(item, index) in statusList">
                  <el-radio-button :label="item.key">{{item.label}}</el-radio-button>
                </template>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-row>
    <is-footer-btn :option="footerList">
    </is-footer-btn>
  </div>
</template>

<script src="../js/indexEdit.js">
  
</script>

<style scoped>
  /deep/.el-dialog__body {
    height: 50vh;
    overflow: auto;
  }
</style>
