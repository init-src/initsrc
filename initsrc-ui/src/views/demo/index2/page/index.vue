<template>
  <div class="dshfn-content">
    <div class="dshfn-content-header dshfn-card">
      <el-col :span="24">
        <div  class="col-no-padding">
          <h4 class="dshfn-content-title">Table2</h4>
          <div class="dshfn-content-right">
            <el-button size="mini" type="primary" icon="initsrc-icon iconshuru" @click="handleAdd()" plain>新增</el-button>
          </div>
        </div>
      </el-col>
    </div>
    <el-row  class="dshfn-content-main-index">
      <el-col :xs="24" :sm="4" :md="4" :lg="4" :xl="4" class="dshfn-card dshfn-tree-content" v-loading="treeloading">
        <div class="tree">
          <el-tree ref="tree" node-key="id" :data="treeData" :props="defaultProps" @node-click="handleNodeClick"
            highlight-current :expand-on-click-node="false" >
            <span class="span-ellipsis" slot-scope="{ node, data }" :title="node.label">
              <span>{{ node.label }}</span>
            </span>
          </el-tree>
        </div>
      </el-col>
      <el-col :xs="24" :sm="20" :md="20" :lg="20" :xl="20">
        <div  class="dshfn-card dshfn-table-content" >
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
            </el-row>
          </div>
          <!--region table 表格-->
          <i-table ref="table" :optionsObj="options" :params="params" :removeScHeight="removeScHeight" ></i-table>
          <!--endregion-->
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script src="../js/index.js">
</script>

<style>
</style>
