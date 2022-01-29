<template>
  <el-dialog :title="formName" width="700px" :fullscreen="$store.state.ts.ISDEVICE==0?false:true" :visible.sync="dialogFormVisible"
    :close-on-click-modal="false" @close="closeDialog" v-loading="loading">
    <el-form :model="form" :rules="rules" ref="ruleForm">
      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="字典名称:" prop="dictKey">
            <el-input v-model="form.dictKey" placeholder="请输入字典名称" maxlength="20" show-word-limit></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="字典类型:" prop="type">
            <el-radio-group v-model="form.type" style="width: 100%;" @change="changeType()">
              <template v-for="(item, index) in typeList">
                <el-radio-button :label="item.key">{{item.label}}</el-radio-button>
              </template>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="24" v-if="form.type == 1">
          <el-form-item label="字典值:" prop="value">
            <el-input type="textarea" v-model="form.value" placeholder="请输入字典值"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24" v-if="form.type == 2">
          <el-form-item label="字典值:">
            <div style="margin-top: 50px;">
              <template v-for="(items, index) in form.item">
                <el-col :span="24" style="margin-bottom: 20px;">
                  <el-col :xs="24" :sm="14" :md="10" :lg="8" :xl="8">
                    <el-form-item label="key" label-width="60px" :prop="'item.' + index + '.key'" :rules="rules.key">
                      <el-input size="medium" v-model="items.key" placeholder="请输入key"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :xs="24" :sm="14" :md="10" :lg="8" :xl="8">
                    <el-form-item label="label" label-width="60px" :prop="'item.' + index + '.label'" :rules="rules.label">
                      <el-input size="medium" v-model="items.label" placeholder="请输入label"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-button type="primary" size="mini" @click="addTap()" v-if="index==0">新增</el-button>
                  <el-button type="danger" size="mini" @click="remTap(index)" v-if="index!=0">删除</el-button>
                  <el-button type="succes" size="mini" @click="upMove(index)">上移</el-button>
                  <el-button type="info" size="mini" @click="downMove(index)">下移</el-button>
                </el-col>
              </template>
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="配置描述:" prop="des">
            <el-input type="textarea" v-model="form.des" placeholder="请输入配置描述" maxlength="150" show-word-limit></el-input>
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
    height: 60vh;
    overflow: auto;
  }
</style>
