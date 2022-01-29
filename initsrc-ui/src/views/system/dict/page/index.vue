<template>
  <div class="dshfn-content">
    <div class="dshfn-content-header dshfn-card">
      <el-col :span="24">
        <div>
          <h4 class="dshfn-content-title">字典管理</h4>
          <div class="dshfn-content-right">
            <div class="opr-btn">
              <template v-for="(column, index) in headBtn">
                <el-button size="mini" :type="column.type" :icon="column.icon" @click="column.method()" :plain="column.plain">
                  {{column.label}}
                </el-button>
              </template>
            </div>
          </div>
        </div>
      </el-col>
    </div>
    <el-row class="dshfn-content-main ">
      <el-col :md="24" :lg="24" :xl="24" >
        <div class="dshfn-card dshfn-table-content">
          <div class="operate-btn">
            <el-row :gutter="24">
              <el-col :md="24" :lg="3" :xl="3" class="input-padding">
                <el-input size="small" placeholder="请输入字典名称" @change="search()" clearable v-model="params.dictKey">
                </el-input>
              </el-col>
              <el-col :md="24" :lg="8" :xl="8">
                <el-button type="danger" icon="el-icon-refresh" size="mini" @click="reload()">重置
                </el-button>
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
  </div>
</template>

<script src="../js/index.js">

</script>

<style>
</style>
