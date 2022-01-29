<template>
  <div class="dshfn-content"  v-loading="loading">
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
    <div class="dshfn-content-select">
      <div class="dshfn-scenetabs-item" @click="selectType('1')">
        <span class="scenetabs-item--wrapper" :class="type=='1'?'is-active':null"><span>生成信息</span></span>
      </div>
      <div class="dshfn-scenetabs-item" @click="selectType('2')">
        <span class="scenetabs-item--wrapper" :class="type=='2'?'is-active':null"><span>字段信息</span></span>
      </div>
      <div class="dshfn-scenetabs-item" @click="selectType('3')">
        <span class="scenetabs-item--wrapper" :class="type=='3'?'is-active':null"><span>关联表信息</span></span>
      </div>
    </div>
    <el-row :gutter="24" class="dshfn-content-main add-background add-footer" style="top: 25px;">
      <el-form :model="form" :rules="rules" ref="ruleForm" size="medium" class="dshfn-card dshfn-form-content add-top">
        <el-row v-show="type == '2'">
          <el-col :md="24" :lg="24" :xl="24">
            <div class="dshfn-card dshfn-table-content">
           <!--region table 表格-->
           <i-table ref="table" :optionsObj="options"  ></i-table>
           <!--endregion-->
            </div>
          </el-col>
        </el-row>
        <div v-show="type == '3'">
          <el-row>
            <el-col :md="24" :lg="16" :xl="8">
              <el-form-item label="是否含有子级:" prop="isRef">
                <el-select v-model="form.isRef" placeholder="是否含有主子关系" style="width: 100%;">
                  <el-option label="否" value="0">
                  </el-option>
                  <el-option label="是" value="1">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="form.isRef == '1'">
            <el-col :md="24" :lg="16" :xl="8">
              <el-form-item label="关联子表的表名 :" prop="subTableId">
                <el-select v-model="form.subTableId" placeholder="请选择" style="width: 100%;" @change="selectSub($event)">
                  <el-option v-for="item in form.tables" :key="item.key" :label="item.tableName" :value="item.tableId">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :md="24" :lg="16" :xl="8" style="padding-left: 10px;">
              <el-form-item label="子表关联的外键 :" prop="subKey">
                <el-select v-model="form.subKey" placeholder="请选择" style="width: 100%;">
                  <el-option v-for="item in subKeyList" :key="item.key" :label="item.columnName" :value="item.columnId">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :md="24" :lg="16" :xl="8">
              <el-form-item label="列表页类型:" prop="vueTableType">
                <el-select v-model="form.vueTableType" placeholder="列表页类型" style="width: 100%;" @change="selectTableType($event)">
                  <el-option label="正常列表" value="1">
                  </el-option>
                  <el-option label="左侧列表" value="2">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row  v-if="form.vueTableType != '1'">
            <el-col :md="24" :lg="16" :xl="8">
              <el-form-item label="关联字段:" prop="columnKey">
                <el-select v-model="form.columnKey" placeholder="请选择" style="width: 100%;">
                  <el-option v-for="item in form.columns" :key="item.key" :label="item.columnName" :value="item.columnName">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :md="24" :lg="16" :xl="8" >
              <el-form-item label="所属菜单 :" prop="permId">
                 <i-tree-select v-model="form.permId" :normalizer="normalizer"  placeholder="请选择所属菜单" :appendToBody="true" :options="permList"></i-tree-select>
              </el-form-item>
            </el-col>
          </el-row>
        </div>
        <div v-show="type == '1'">
          <el-row>
            <el-col :md="24" :lg="16" :xl="8">
              <el-form-item label="表名称:" prop="tableName">
                <el-input v-model="form.tableName" placeholder="请输入表名称" readonly maxlength="30" show-word-limit></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :md="24" :lg="16" :xl="8">
              <el-form-item label="表描述:" prop="tableComment">
                <el-input v-model="form.tableComment" placeholder="请输入表描述" maxlength="30" show-word-limit></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :md="24" :lg="16" :xl="8">
              <el-form-item label="实体类名称:" prop="className">
                <el-input v-model="form.className" placeholder="请输入实体类名称" maxlength="20" show-word-limit></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :md="24" :lg="16" :xl="8">
              <el-form-item label="作者:" prop="genAuthor">
                <el-input v-model="form.genAuthor" placeholder="请输入作者" maxlength="20" show-word-limit></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :md="24" :lg="16" :xl="8">
              <el-form-item label="页面路径:" prop="viewPath">
                <el-input v-model="form.viewPath" placeholder="请输入页面路径" maxlength="150" show-word-limit></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :md="24" :lg="16" :xl="8">
              <el-form-item label="生成包路径:" prop="packageName">
                <el-input v-model="form.packageName" placeholder="请输入生成包路径" maxlength="150" show-word-limit></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :md="24" :lg="16" :xl="8">
              <el-form-item label="生成模块名:" prop="moduleName">
                <el-input v-model="form.moduleName" placeholder="请输入生成模块名" maxlength="50" show-word-limit></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :md="24" :lg="16" :xl="8">
              <el-form-item label="业务名称:" prop="bizName">
                <el-input v-model="form.bizName" placeholder="请输入业务名称" maxlength="50" show-word-limit></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :md="24" :lg="16" :xl="8">
              <el-form-item label="生成功能名:" prop="functionName">
                <el-input v-model="form.functionName" placeholder="请输入生成功能名" maxlength="150" show-word-limit></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :md="24" :lg="16" :xl="8">
              <el-form-item label="生成模块:" prop="isCategory">
                <el-select v-model="form.isCategory" placeholder="请选择模板" style="width: 100%;">
                  <el-option v-for="item in genModule" :key="item.key" :label="item.label" :value="item.key">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="form.isCategory == 2">
            <el-col :md="24" :lg="16" :xl="8" >
              <el-form-item label="父类字段:" prop="parentKey">
                <el-select v-model="form.parentKey" placeholder="请选择父类字段" style="width: 100%;">
                 <el-option v-for="item in form.columns" :key="item.key" :label="item.columnName" :value="item.columnName">
                 </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
		  <el-row>
		    <el-col :md="24" :lg="16" :xl="8">
		      <el-form-item label="是否生成导入导出:" prop="isExcel">
		        <el-select v-model="form.isExcel" placeholder="列表页类型" style="width: 100%;" @change="selectTableType($event)">
		          <el-option label="不需要" value="0">
		          </el-option>
		          <el-option label="需要" value="1">
		          </el-option>
		        </el-select>
		      </el-form-item>
		    </el-col>
		  </el-row>
          <el-row>
            <el-col :md="24" :lg="16" :xl="8">
              <el-form-item label="详情模块类型:" prop="vueDetailType">
                <el-select v-model="form.vueDetailType" placeholder="请选择详情模块类型" style="width: 100%;">
                  <el-option v-for="item in vueDetailModule" :key="item.key" :label="item.label" :value="item.key">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :md="24" :lg="16" :xl="8">
              <el-form-item label="编辑模块类型:" prop="vueEditType">
                <el-select v-model="form.vueEditType" placeholder="请选择编辑模块类型" style="width: 100%;">
                  <el-option v-for="item in vueModule" :key="item.key" :label="item.label" :value="item.key">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </div>
      </el-form>
    </el-row>
    <is-footer-btn :option="footerList">
    </is-footer-btn>
    <el-dialog title="条件设置" width="600px" :visible.sync="dialogFormVisible" @close="closeDialog"
    :fullscreen="$store.state.ts.ISDEVICE==0?false:true">
      <el-form :model="formSearch" :rules="searchRules" ref="ruleSearchForm">
        <el-row :gutter="20">
          <div v-if="formSearch.htmlType == '9' || formSearch.htmlType == '10' || formSearch.htmlType == '11' ||formSearch.htmlType == '12' ||
          formSearch.htmlType == '13'">
          <el-col :md="24" :lg="24" :xl="20">
            <el-form-item label="查询方式" prop="isSearch">
              <el-select v-model="formSearch.isSearch" placeholder="请选择查询方式" style="width: 100%;">
                <el-option label="字典查询" value="0"></el-option>
                <el-option label="数据库表查询" value="1"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
            <div v-if="formSearch.isSearch =='1'">
              <el-col :md="24" :lg="16" :xl="10">
                <el-form-item label="关联表名称:" prop="leftTable">
                  <el-select v-model="formSearch.leftTable" placeholder="请选择"  style="width: 100%;"
                    @change="selectLeftTree($event)">
                    <el-option v-for="item in form.tables" :key="item.key" :label="item.tableName" :value="item.tableName">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :md="24" :lg="16" :xl="10">
                <el-form-item label="关联主键字段:" prop="leftKey">
                  <el-select v-model="formSearch.leftKey" placeholder="请选择" style="width: 100%;">
                    <el-option v-for="item in leftTreeList" :key="item.key" :label="item.columnName" :value="item.columnName">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :md="24" :lg="16" :xl="10" v-if="formSearch.htmlType == '11'">
                <el-form-item label="关联父类字段:" prop="leftParent">
                  <el-select v-model="formSearch.leftParent" placeholder="请选择" style="width: 100%;">
                    <el-option v-for="item in leftTreeList" :key="item.key" :label="item.columnName" :value="item.columnName">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :md="24" :lg="16" :xl="10">
                <el-form-item label="关联名称字段:" prop="leftLabel">
                  <el-select v-model="formSearch.leftLabel" placeholder="请选择" style="width: 100%;">
                    <el-option v-for="item in leftTreeList" :key="item.key" :label="item.columnName" :value="item.columnName">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :md="24" :lg="16" :xl="10">
                <el-form-item label="关联名称字段别名:" prop="leftAlias">
                   <el-input v-model="formSearch.leftAlias" placeholder="请输入生成模块名" maxlength="50" show-word-limit></el-input>
                </el-form-item>
              </el-col>
            </div>
            <div v-else>
              <el-col :md="24" :lg="24" :xl="20">
                <el-form-item label="字典查询:" prop="dictType">
                  <el-select v-model="formSearch.dictType" placeholder="请选择" style="width: 100%;">
                    <el-option v-for="(value,key) in dictList" :key="key" :label="key" :value="key">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </div>
          </div>
          <el-col :md="24" :lg="24" :xl="20">
            <el-form-item label="正则表达式:" prop="verifyName">
              <el-select v-model="formSearch.verifyName" placeholder="请选择正则名称" style="width: 100%;">
                <el-option v-for="(value,key) in ruleCommonList" :key="key" :label="value.label" :value="value.key">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveSearch('ruleSearchForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script src="../js/indexEdit.js">
</script>

<style>
</style>
