<template>
  <div class="dshfn-content">
    <div class="dshfn-content-header dshfn-card">
      <el-row :gutter="24">
        <el-col :span="24" class="col-no-padding">
          <div  style="padding: 4px;">
          <el-page-header @back="common.goBack" content="代码生成预览">
          </el-page-header>
          </div>
        </el-col>
      </el-row>
    </div>
    <el-row :gutter="24" class="dshfn-content-main">
      <el-col :md="24" :lg="24" :xl="24" style="padding: 0px;">
        <el-col :span="24" class="col-padding">
          <div class="dshfn-card dshfn-card-bottom">
            <div class="dshfn-card-body">
              <el-row :gutter="24" class="dshfn-detail-info">
                <el-tabs v-model="activeName" >
                    <template v-for="(value,key,index) in form">
                      <el-tab-pane :label="key" :name="String(index)">
                       <div :class="'cpy copy-btn'+index" @mouseenter="copy('copy-btn'+index)" :data-clipboard-text="value">复制代码</div>
                          <pre><code class="hljs" v-html="showCode(value, key)"></code></pre>
                      </el-tab-pane>
                    </template>
                  </el-tabs>
              </el-row>
            </div>
          </div>
        </el-col>
      </el-col>
    </el-row>
  </div>
</template>

<script src="../js/indexDetail.js">
</script>

<style scoped>
  .width-label {
    width: 120px !important;
  }
  .cpy{
    position: absolute;
    right: 0;
    margin: 5px;
    padding: 5px;
    color: #FFFFFF;
    cursor: pointer;
    z-index: 10;
    border: #409EFF 1px solid;
  }
</style>
