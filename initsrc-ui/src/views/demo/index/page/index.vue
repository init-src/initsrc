<template>
  <div class="dshfn-content">
    <div class="dshfn-content-header dshfn-card">
      <el-col :span="24">
        <div>
          <h4 class="dshfn-content-title">Table1</h4>
          <div class="dshfn-content-right">
            <el-button size="mini" type="primary" icon="initsrc-icon iconshuru" @click="handleAdd()" plain>新增</el-button>
            <el-button size="mini" type="danger" icon="initsrc-icon iconshanchu" @click="handleDels()" plain>批量删除</el-button>
            <el-button size="mini" type="success" icon="initsrc-icon icondaoru"  @click="handleImport()" plain>导入</el-button>
            <el-button size="mini" type="success" icon="initsrc-icon icondaochu" @click="handleExport()" plain>导出</el-button>
          </div>
        </div>
      </el-col>
    </div>
    <el-row class="dshfn-content-main ">
      <el-col :md="24" :lg="24" :xl="24">
        <div class="dshfn-card dshfn-table-content">
          <div class="operate-btn">
            <el-row :gutter="24">
              <el-col :md="24" :lg="3" :xl="3" class="input-padding">
                <el-input size="small" placeholder="请输入内容" @change="search()" suffix-icon="el-icon-date" v-model="params.a">
                </el-input>
              </el-col>
              <el-col :md="24" :lg="3" :xl="3" class="input-padding">
                <el-input size="small" placeholder="请输入内容" @change="search()" suffix-icon="el-icon-date" v-model="params.a">
                </el-input>
              </el-col>
              <el-col :md="24" :lg="3" :xl="3" class="input-padding">
                <el-input size="small" placeholder="请输入内容" @change="search()" suffix-icon="el-icon-date" v-model="params.a">
                </el-input>
              </el-col>
              <el-col :md="24" :lg="3" :xl="3" class="input-padding">
                <el-input size="small" placeholder="请输入内容" @change="search()" suffix-icon="el-icon-date" v-model="params.a">
                </el-input>
              </el-col>
              <el-col :md="24" :lg="3" :xl="3" class="input-padding">
                <el-input size="small" placeholder="请输入内容" @change="search()" suffix-icon="el-icon-date" v-model="params.a">
                </el-input>
              </el-col>
              <el-col :md="24" :lg="8" :xl="8" style="margin-top: -5px;">
                <el-button type="text" @click="expand()" icon="el-icon-d-caret">展开</el-button>
                <el-button type="danger" icon="el-icon-refresh" size="mini" @click="reload()">重置</el-button>
              </el-col>
            </el-row>
            <el-row :gutter="24" v-if="isExpand">
              <el-col :md="24" :lg="3" :xl="3" class="input-padding">
                <el-input size="small" placeholder="请输入内容" suffix-icon="el-icon-date" v-model="params.a">
                </el-input>
              </el-col>
              <el-col :md="24" :lg="3" :xl="3" class="input-padding">
                <el-input size="small" placeholder="请输入内容" suffix-icon="el-icon-date" v-model="params.a">
                </el-input>
              </el-col>
              <el-col :md="24" :lg="3" :xl="3" class="input-padding">
                <el-input size="small" placeholder="请输入内容" suffix-icon="el-icon-date" v-model="params.a">
                </el-input>
              </el-col>
              <el-col :md="24" :lg="3" :xl="3" class="input-padding">
                <el-input size="small" placeholder="请输入内容" suffix-icon="el-icon-date" v-model="params.a">
                </el-input>
              </el-col>
              <el-col :md="24" :lg="3" :xl="3" class="input-padding">
                <el-input size="small" placeholder="请输入内容" suffix-icon="el-icon-date" v-model="params.a">
                </el-input>
              </el-col>
            </el-row>
          </div>
          <!--region table 表格-->
          <i-table ref="table" :optionsObj="options" :params="params" :removeScHeight="removeScHeight" @handleSelectionChange="handleSelectionChange"></i-table>
          <!--endregion-->
        </div>
      </el-col>
    </el-row>
    <index-edit ref="indexEdit"></index-edit>
    <index-detail ref="indexDetail"></index-detail>
    <index-import ref="indexImport"></index-import>
    <index-export ref="indexExport"></index-export>
  </div>
</template>

<script src="../js/index.js">
</script>

<style>
</style>
