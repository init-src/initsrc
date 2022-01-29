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
        <el-row :gutter="20">
          <el-col :md="24" :lg="16" :xl="8">
            <el-form-item label="所属部门:" prop="deptId">
              <i-tree-select v-model="form.deptId" :normalizer="normalizer" placeholder="请选择所属部门" :appendToBody="true"
                :options="deptIdList"></i-tree-select>
            </el-form-item>
            <el-form-item label="昵称:" prop="nickName">
              <el-input v-model="form.nickName" placeholder="请输入昵称" clearable maxlength="30" show-word-limit></el-input>
            </el-form-item>
            <el-form-item label="用户名:" prop="userName">
              <el-input v-model="form.userName" placeholder="请输入用户名" clearable maxlength="30" :disabled="form.userId != null?true:false" show-word-limit></el-input>
            </el-form-item>
            <el-form-item label="用户密码:" prop="userPwd">
              <el-input v-model="form.userPwd" placeholder="请输入用户密码" clearable maxlength="30" show-word-limit></el-input>
            </el-form-item>
            <el-form-item label="头像:" prop="headImg">
              <el-upload class="avatar-uploader" action="" :auto-upload="false" :show-file-list="false" :on-change="handleHeadImg">
                <img v-if="form.headImg" :src="form.headImg" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
            <el-form-item label="性别:" prop="sex">
              <el-radio-group v-model="form.sex" style="width: 100%;">
                <template v-for="(item, index) in sexList">
                  <el-radio-button :label="item.key">{{item.label}}</el-radio-button>
                </template>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="生日日期:" prop="birthday">
              <el-date-picker v-model="form.birthday" value-format="timestamp" type="datetime" placeholder="选择生日日期时间"
                clearable style="width: 100%;">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="邮箱:" prop="email">
              <el-input v-model="form.email" placeholder="请输入邮箱" clearable :disabled="form.userId != null?true:false"
                maxlength="30" show-word-limit></el-input>
            </el-form-item>
            <el-form-item label="手机号:" prop="mobile">
              <el-input v-model="form.mobile" placeholder="请输入手机号" clearable :disabled="form.userId != null?true:false"
                maxlength="30" show-word-limit></el-input>
            </el-form-item>
            <el-form-item label="状态:" prop="status">
              <el-radio-group v-model="form.status" style="width: 100%;">
                <template v-for="(item, index) in statusList">
                  <el-radio-button :label="item.key">{{item.label}}</el-radio-button>
                </template>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="角色:" prop="roleId">
              <el-select v-model="form.roleId" multiple  placeholder="请选择角色" style="width: 100%;" clearable>
                <el-option v-for="item in roleIdList"  :disabled="item.id == '1'" :key="item.key" :label="item.label" :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="备注:" prop="remark">
              <el-input type="textarea" v-model="form.remark" placeholder="请输入备注" maxlength="150" show-word-limit></el-input>
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
