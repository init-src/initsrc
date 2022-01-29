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
        <el-col :md="24" :lg="16" :xl="12">
          <el-row>
            <el-col :md="24" :lg="24" :xl="15">
              <el-form-item label="角色名称:" prop="roleName">
                <el-input v-model="form.roleName" placeholder="请输入角色名称" clearable maxlength="60" show-word-limit></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :md="24" :lg="24" :xl="15">
              <el-form-item label="查询权限:" prop="isSearch">
                <el-select v-model="form.isSearch" placeholder="请选择查询权限" style="width: 100%;" clearable>
                  <el-option v-for="item in isSearchList" :key="item.key" :label="item.label" :value="item.key">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="form.isSearch == '1'">
            <el-col :md="24" :lg="24" :xl="15">
              <el-form-item label="自定义部门数组:" prop="powerDepts">
                <i-tree-select v-model="form.powerDepts" :normalizer="normalizer" :multiple="true" :flat="true"
                  placeholder="请选择自定义部门数组" :appendToBody="true" :options="powerDeptsList"></i-tree-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :md="24" :lg="24" :xl="15">
              <el-form-item label="状态:" prop="status">
                <el-radio-group v-model="form.status" style="width: 100%;">
                  <template v-for="(item, index) in statusList">
                    <el-radio-button :label="item.key">{{item.label}}</el-radio-button>
                  </template>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :md="24" :lg="24" :xl="15">
              <el-form-item label="描述:" prop="des">
                <el-input type="textarea" v-model="form.des" placeholder="请输入描述" clearable maxlength="150"
                  show-word-limit></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-col>
        <el-col :md="24" :lg="16" :xl="12">
          <el-row :gutter="24">
            <el-col :md="24" :lg="24" :xl="15">
              <el-form-item label="角色权限:">
                <el-checkbox v-model="checkAll" @change="checkAllPerm($event,permList)">全选</el-checkbox>
                <div class="permission" v-for="(item, index) in permList">
                  <h4>{{item.name}}
                    <el-checkbox v-model="item.checked" @change="checkPerm($event,item)">&nbsp;</el-checkbox>
                  </h4>
                  <ul>
                    <template v-for="(it, index) in item.children">
                      <li v-if="it.children == null || it.children[0].resource != 0">{{it.name}}
                        <!-- <el-tooltip class="item" effect="dark" content="设置首页便捷菜单,最多只能8个(多角色去重默认保留8个)" placement="top-end">
                        <el-switch v-model="it.isEasy">
                      </el-switch>
                       </el-tooltip> -->
                        <el-checkbox v-model="it.checked" @change="checkPerm($event,it)">&nbsp;</el-checkbox>
                        <p>
                          <template v-for="(its, index) in it.children">
                            <el-tag :type="'success'" style="margin-left: 5px;">{{its.name}}
                              <el-checkbox v-model="its.checked" @change="checkPerm($event,its)" class="isBtnCheck">&nbsp;</el-checkbox>
                            </el-tag>&nbsp;
                          </template>
                        </p>
                      </li>
                      <div v-else>
                        <li>
                          <h3>{{it.name}}</h3>
                        </li>
                        <template v-for="(its, index) in it.children">
                          <li>
                            {{its.name}}
                            <el-checkbox v-model="its.checked" @change="checkPerm($event,its)">&nbsp;</el-checkbox>
                            <p>
                              <template v-for="(its1, index) in its.children">
                                <el-tag :type="'success'" style="margin-left: 5px;">{{its1.name}}
                                  <el-checkbox v-model="its1.checked" @change="checkPerm($event,its1)" class="isBtnCheck">&nbsp;</el-checkbox>
                                </el-tag>&nbsp;
                              </template>
                            </p>
                          </li>
                        </template>
                      </div>
                    </template>
                  </ul>
                </div>
              </el-form-item>
            </el-col>
          </el-row>
        </el-col>
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
